package net.mnio.sharknetnfc.hce;

import android.annotation.TargetApi;
import android.nfc.cardemulation.HostApduService;
import android.os.Build;
import android.os.Bundle;

import net.mnio.sharknetnfc.HceActivity;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class HceCardEmulationService extends HostApduService {

    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
        if (selectAidApdu(apdu)) {
            return getWelcomeMessage();
        } else {
            return getNextMessage();
        }
    }

    private byte[] getWelcomeMessage() {
        return "Hello Desktop!".getBytes();
    }

    private byte[] getNextMessage() {
        //TODO: implement ByteBuffer to be sent in pieces for long too messages
        String inputString = HceActivity.inputString;
        if (inputString != null) {
            HceActivity.inputString = null;
            return inputString.getBytes();
        }
        return null;
    }

    private boolean selectAidApdu(byte[] apdu) {
        return apdu.length >= 2 && apdu[0] == (byte) 0 && apdu[1] == (byte) 0xa4;
    }

    @Override
    public void onDeactivated(int reason) {
    }
}