package ru.mirea.klinduhovir.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    public void onButton(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                "СТУДЕНТ No 14 ГРУППА БСБО-04-22 Количество символов -" + editText.getText().length(),
                Toast.LENGTH_LONG);
        toast.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
    }
}