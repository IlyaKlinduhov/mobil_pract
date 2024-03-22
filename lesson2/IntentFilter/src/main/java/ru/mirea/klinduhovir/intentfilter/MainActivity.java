package ru.mirea.klinduhovir.intentfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button;

    EditText editTextInstitute;

    EditText editTextInitials;

    public void onButton(View view){
        Uri address = Uri.parse("https://www.mirea.ru/");
        Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
        startActivity(openLinkIntent);
    }

    public void onButtonFIO(View view){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, editTextInstitute.getText());
        shareIntent.putExtra(Intent.EXTRA_TEXT, editTextInitials.getText());
        startActivity(Intent.createChooser(shareIntent, "МОИ ФИО"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editTextInitials = findViewById(R.id.editTextInitials);
        editTextInstitute = findViewById(R.id.editTextInstitute);
    }
}