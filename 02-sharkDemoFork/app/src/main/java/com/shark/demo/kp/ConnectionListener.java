package com.shark.demo.kp;

import net.sharkfw.peer.KEPConnection;

/**
 * Created by jetmir on 24.03.2015.
 */
public interface ConnectionListener {

    void onConnectionEstablished(KEPConnection connection);

}
