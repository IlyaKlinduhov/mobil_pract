package ru.mirea.klinduhovir.mireaproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FileFragment extends Fragment{
    private static final int REQUEST_WEIGHT = 1;
    private static final int REQUEST_ANOTHER_ONE = 2;

    FileFragment instance;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText editTextFileName;
    EditText editTextContent;
    Button buttonSave;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FileFragment newInstance(String param1, String param2) {
        FileFragment fragment = new FileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file, container, false);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        editTextFileName = view.findViewById(R.id.editTextFileName);
        editTextContent = view.findViewById(R.id.editTextContent);
        buttonSave = view.findViewById(R.id.buttonSave);
        FileFragment instance = this;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFile fragment = new DialogFile();
                fragment.setTargetFragment(instance, REQUEST_WEIGHT);
                fragment.show(getFragmentManager(), fragment.getClass().getName());
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = editTextFileName.getText().toString()+".txt";
                try {
                    FileOutputStream outputStream = getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
                    outputStream.write(editTextContent.getText().toString().getBytes());
                    outputStream.close();
                    Toast.makeText(getContext(), "Successful writing", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_WEIGHT:
                    if (data != null && data.hasExtra(DialogFile.TAG_CREATE_SELECTED)){
                        String name = data.getStringExtra("create");
                        editTextFileName.setText(name);
                        editTextContent.setText("");
                        Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
                        break;
                    }
                case REQUEST_ANOTHER_ONE:
                    if (data != null && data.hasExtra(DialogFile.TAG_LOAD_SELECTED)){
                        String name = data.getStringExtra("load")+".txt";
                        File file = new File(getContext().getFilesDir(), name);
                        if (file.exists()) {
                            try {
                                FileInputStream inputStream = getContext().openFileInput(name);
                                InputStreamReader isr = new InputStreamReader(inputStream);
                                BufferedReader br = new BufferedReader(isr);
                                String line = br.readLine();
                                ArrayList<String> lines = new ArrayList<>();
                                while (line != null) {
                                    lines.add(line);
                                    line = br.readLine();
                                }
                                editTextContent.setText(String.join("\n", lines));
                                br.close();
                                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }else{
                            Toast.makeText(getContext(), "Файла не существует", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
            }
            }

        }
}