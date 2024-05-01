package ru.mirea.klinduhovir.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import ru.mirea.klinduhovir.notebook.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    public void onSaveClick(View view){
        try {
            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(directory, binding.editTextFileName.getText().toString()+".txt");

            String text = binding.editTextQuote.getText().toString();
            FileOutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write(text);
            writer.close();

            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void onLoadClick(View view){
        try{
            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(directory, binding.editTextFileName.getText().toString()+".txt");
            FileInputStream inputStream = new FileInputStream(file.getAbsoluteFile());
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            List<String> text = new ArrayList<>();
            String line = reader.readLine();
            while (line!=null){
                text.add(line);
                line = reader.readLine();
            }

            binding.editTextQuote.setText(String.join("\n",text));
            reader.close();
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}