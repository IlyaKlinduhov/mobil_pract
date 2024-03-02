package ru.mirea.klinduhovir.buttonclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button btnWhoAmI;
    private Button btnItIsNotMe;
    private CheckBox checkBox;


    public void onMyButtonClick(View view){
        textView.setText("Это не я сделал");
        checkBox.setChecked(false);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tvOut);
        btnWhoAmI = findViewById(R.id.btWhoAmI);
        btnItIsNotMe = findViewById(R.id.btnItIsNotMe);
        checkBox = findViewById(R.id.checkBox);

        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Мой номер по списку No 14");
                checkBox.setChecked(true);
            }
        };
        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);


    }
}