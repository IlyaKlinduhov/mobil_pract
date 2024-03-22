package ru.mirea.klinduhovir.multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    public void onClickNewActivity(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("key", "MIREA - ФАМИЛИЯ ИМЯ ОТЧЕТСВО СТУДЕНТА");
        startActivity(intent);
    }

    public void onClickButtonSend(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("key", editText.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
    }


}