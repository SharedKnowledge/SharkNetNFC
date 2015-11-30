package de.htw_berlin.sharkandroidstack.sharkFW.peer;

import android.content.Context;

import net.sharkfw.kep.KEPStub;
import net.sharkfw.kep.SharkProtocolNotSupportedException;
import net.sharkfw.knowledgeBase.Knowledge;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.peer.J2SEAndroidSharkEngine;
import net.sharkfw.peer.KnowledgePort;
import net.sharkfw.protocols.Stub;
import net.sharkfw.protocols.wifidirect.WifiDirectStreamStub;
import net.sharkfw.system.SharkSecurityException;

import java.io.IOException;

public class AndroidSharkEngine extends J2SEAndroidSharkEngine {
    //TODO: extract wifi stuff

    WifiDirectStreamStub _wifi;
    private Context _context;

    public AndroidSharkEngine(Context context) {
        super();
        _context = context;
    }


    public Context getContext() {
        return _context;
    }

    /*
     * Wifi Direct methods
     * @see net.sharkfw.peer.SharkEngine#createWifiDirectStreamStub(net.sharkfw.kep.KEPStub)
     */

    @Override
    protected Stub createWifiDirectStreamStub(KEPStub kepStub) throws SharkProtocolNotSupportedException {
        if (_wifi != null) {
            _wifi.stop();
        }
        _wifi = new WifiDirectStreamStub(getContext(), this, kepStub);
        _wifi.start();
        return _wifi;
    }

    @Override
    public void startWifiDirect() throws SharkProtocolNotSupportedException, IOException {
        this.createWifiDirectStreamStub(this.getKepStub());
    }

    public void stopWifiDirect() throws SharkProtocolNotSupportedException {
        _wifi.stop();
    }

    public void startNfc() throws SharkProtocolNotSupportedException, IOException {
        throw new SharkProtocolNotSupportedException("TODO: Mario");
    }

    public void stopNfc() throws SharkProtocolNotSupportedException {
        throw new SharkProtocolNotSupportedException("TODO: Mario");
    }

    public void startBluetooth() throws SharkProtocolNotSupportedException, IOException {
        throw new SharkProtocolNotSupportedException("TODO: Timm");
    }

    public void stopBluetooth() throws SharkProtocolNotSupportedException {
        throw new SharkProtocolNotSupportedException("TODO: Timm");
    }

    @Override
    public void sendKnowledge(Knowledge k, PeerSemanticTag recipient,
                              KnowledgePort kp) throws SharkSecurityException, SharkKBException,
            IOException {

        if (_wifi != null) {
            recipient.setAddresses(new String[]{_wifi.getConnectionStr()});
        }

        super.sendKnowledge(k, recipient, kp);
    }
}
