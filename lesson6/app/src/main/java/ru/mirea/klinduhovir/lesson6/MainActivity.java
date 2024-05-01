package ru.mirea.klinduhovir.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import ru.mirea.klinduhovir.lesson6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        SharedPreferences preferences = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);
        binding.editText1.setText(preferences.getString("GROUP", "XXXX-XX-XX"));
        binding.editText2.setText(String.valueOf(preferences.getInt("NUMBER", 0)));
        binding.editText3.setText(preferences.getString("FILM", "unknown"));

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("GROUP", binding.editText1.getText().toString());
                editor.putInt("NUMBER", Integer.parseInt(binding.editText2.getText().toString()));
                editor.putString("FILM", binding.editText3.getText().toString());
                editor.apply();
            }
        });
        setContentView(binding.getRoot());
    }
}