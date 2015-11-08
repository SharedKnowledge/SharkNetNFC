package net.mnio.sharknetnfc.hce;

import android.annotation.TargetApi;
import android.nfc.cardemulation.HostApduService;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Arrays;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class SmartCardEmulationService extends HostApduService {

    public final static int DEFAULT_MAX_LENGTH = 200;

    private static EditText input;
    byte[] byteBuffer;

    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
        if (selectAidApdu(apdu)) {
            return getWelcomeMessage();
        } else {
            final String payload = new String(apdu);
            int maxLength = DEFAULT_MAX_LENGTH;
            if (payload.startsWith(IsoDepTransceiver.ISO_DEP_MAX_LENGTH)) {
                final String substring = payload.substring(IsoDepTransceiver.ISO_DEP_MAX_LENGTH.length(), payload.length());
                maxLength = new Integer(substring);
            }
            return getNextMessage(maxLength);
        }
    }

    @Override
    public void onDeactivated(int reason) {
    }

    private byte[] getWelcomeMessage() {
        return "Hello".getBytes();
    }

    byte[] getNextMessage(int maxLength) {
        if (null == byteBuffer) {
            byteBuffer = input.getText().toString().getBytes();
        }

        return getBytesFromBuffer(maxLength);
    }

    byte[] getBytesFromBuffer(int maxLength) {
        if (byteBuffer == null || 0 == byteBuffer.length) {
            byteBuffer = null;
            return null;
        }

        int length = Math.min(byteBuffer.length, maxLength);
        final byte[] currentBuffer = Arrays.copyOfRange(byteBuffer, 0, length);

        byteBuffer = Arrays.copyOfRange(byteBuffer, length, byteBuffer.length);

        return currentBuffer;
    }

    public static void setInput(EditText input) {
        SmartCardEmulationService.input = input;
    }

    private boolean selectAidApdu(byte[] apdu) {
        //TODO: how does this work?
        return apdu.length >= 2 && apdu[0] == (byte) 0 && apdu[1] == (byte) 0xa4;
    }
}