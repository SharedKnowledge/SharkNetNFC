package net.mnio.sharknetnfc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Class<? extends NfcActivity> cls = null;
            switch (v.getId()) {
                case R.id.p2pButton:
                    cls = P2PActivity.class;
                    break;
                case R.id.hceButton:
                    cls = HceActivity.class;
                    break;
            }

            final Intent intent = new Intent(getApplicationContext(), cls);
            startActivity(intent);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        findViewById(R.id.hceButton).setOnClickListener(onClickListener);
        findViewById(R.id.p2pButton).setOnClickListener(onClickListener);
        final Spanned text = Html.fromHtml(getString(R.string.welcome));
        ((TextView) findViewById(R.id.welcomeText)).setText(text);
    }
}
