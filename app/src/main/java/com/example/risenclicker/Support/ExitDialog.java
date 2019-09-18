package com.example.risenclicker.Support;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.example.risenclicker.R;

public class ExitDialog extends DialogFragment {

    private Context context;

    public void setContextMine(Context c) {
        context = c;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        TextView titleView = new TextView(context);
        titleView.setText(R.string.dialExit);
        titleView.setTextColor(ContextCompat
                .getColor(context, R.color.textButtonStandard));
        titleView.setTextSize(16);
        titleView.setPadding(64, 64,64,64);


        builder.setCustomTitle(titleView)
                .setPositiveButton("ДА", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().finish();
                        getActivity().moveTaskToBack(true);

                    }
                })
                .setNegativeButton("НЕТ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog dialogFragment = builder.create();
        dialogFragment.getWindow().setBackgroundDrawableResource(R.color.backDial);
        return dialogFragment;
    }

}
