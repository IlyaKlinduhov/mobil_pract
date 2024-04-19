package ru.mirea.klinduhovir.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.mirea.klinduhovir.musicplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}