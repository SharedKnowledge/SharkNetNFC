package net.mnio.sharknetnfc.hce;

public interface OnMessageReceived {

    void onMessage(byte[] message);

    void onError(Exception exception);
}
