package net.mnio.sharknetnfc;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import net.mnio.sharknetnfc.p2p.AndroidBeamHelper;

public class InputFragment extends Fragment {

    private EditText editText;

    public InputFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = (EditText) getView().findViewById(R.id.input);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();

        final MainActivity activity = (MainActivity) getActivity();
        editText.setText(activity.getMode().name());

        boolean success = AndroidBeamHelper.register(getActivity(), editText);
        if (!success) {
            Snackbar.make(getView(), "NFC not available", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        clearCursor(editText);
        AndroidBeamHelper.readData(getActivity().getIntent(), editText);
    }

    private void clearCursor(EditText output) {
        output.clearFocus();
        output.setCursorVisible(false);
        output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) v).setCursorVisible(true);
                v.setOnClickListener(null);
            }
        });
    }
}
