package de.htw_berlin.sharkandroidstack.setup;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import net.sharkfw.knowledgeBase.sync.SyncKB;
import net.sharkfw.knowledgeBase.sync.SyncKP;

import de.htw_berlin.sharkandroidstack.android.KbTextViewWriter;

public class SharkStack {

    private net.sharkfw.peer.AndroidSharkEngine _engine;
    private KbTextViewWriter _kbTextViewWriter;
    private SyncKB _kb;
    private SyncKP _kp;
    private MySimpleKp _wifiKp;

    public SharkStack(Context context) {
        _engine = new net.sharkfw.peer.AndroidSharkEngine(context);
        _engine.setConnectionTimeOut(20000); //TODO: needed?

        String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

        try {
            _kb = new KnowledgeBaseCreator().getKb(deviceId);
            _kp = new SyncKP(_engine, _kb, 1000);
        } catch (net.sharkfw.system.SharkException e) {
            Log.d("Internal", "Setting up the SyncKB failed.");
        }

        _wifiKp = new MySimpleKp(_engine, _kb.getOwner(), _kp);

        _kbTextViewWriter = KbTextViewWriter.getInstance();
        //_kbTextViewWriter.setOutputTextView(_text);
        _kb.addListener(_kbTextViewWriter);
        //_kbTextViewWriter.writeKbToTextView(_kb);
    }
}
