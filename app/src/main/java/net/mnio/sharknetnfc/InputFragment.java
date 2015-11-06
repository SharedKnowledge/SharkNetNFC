package net.mnio.sharknetnfc;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import net.mnio.sharknetnfc.p2p.AndroidBeamHelper;

public class InputFragment extends Fragment {

    public InputFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView input = (TextView) getView().findViewById(R.id.input);

        boolean success = AndroidBeamHelper.register(getActivity(), input);
        if (!success) {
            Snackbar.make(getView(), "NFC not available", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        EditText output = (EditText) getView().findViewById(R.id.input);
        clearCursor(output);
        AndroidBeamHelper.readData(getActivity().getIntent(), output);
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
