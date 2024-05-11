package ru.mirea.klinduhovir.mireaproject.map;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MapDialogFragment extends DialogFragment {
    private String address;
    private String description;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        address = getArguments().getString("address");
        description = getArguments().getString("description");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        return builder
                .setTitle(address)
                .setMessage(description)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
    }
}
