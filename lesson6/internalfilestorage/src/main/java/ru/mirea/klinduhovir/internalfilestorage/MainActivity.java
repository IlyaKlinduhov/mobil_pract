package ru.mirea.klinduhovir.internalfilestorage;

import androidx.activity.result.ActivityResultCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ru.mirea.klinduhovir.internalfilestorage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "event.txt";
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                binding.editTextText.post(new Runnable() {
                    public void run() {
                        try {
                            binding.editTextText.setText(getTextFromFile());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        }).start();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = binding.editTextText.getText().toString();
                FileOutputStream outputStream;
                try {
                    outputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                    outputStream.write(text.getBytes());
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        setContentView(binding.getRoot());
    }

    public String getTextFromFile() throws IOException {
        try {
            FileInputStream fin = null;
            fin = openFileInput(FILE_NAME);
            byte [] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            return text;
        }
        catch (IOException ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}