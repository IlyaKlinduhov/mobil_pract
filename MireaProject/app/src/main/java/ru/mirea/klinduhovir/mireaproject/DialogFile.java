package ru.mirea.klinduhovir.mireaproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.HashMap;
import java.util.Map;

public class DialogFile extends DialogFragment {
    public static final String TAG_LOAD_SELECTED = "load";
    public static final String TAG_CREATE_SELECTED = "create";
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);

        EditText editTextName = view.findViewById(R.id.editTextName);
        Button buttonCreate = view.findViewById(R.id.buttonCreate);
        Button buttonLoad = view.findViewById(R.id.buttonLoad);

        buttonCreate.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            if(!name.equals("")){
                Intent intent = new Intent();
                intent.putExtra(TAG_CREATE_SELECTED, name);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                dismiss();
            }else{
                Toast.makeText(getContext(), "Введите не пустую строку", Toast.LENGTH_SHORT).show();
            }
        });

        buttonLoad.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            Intent intent = new Intent();
            intent.putExtra(TAG_LOAD_SELECTED, name);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
            dismiss();
        });

        builder.setView(view);
        return builder.create();
    }
}
