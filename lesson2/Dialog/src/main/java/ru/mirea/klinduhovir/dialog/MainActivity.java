package ru.mirea.klinduhovir.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements TimeSelectedListener, DateSelectedListener{

    TextView textViewTime;
    TextView textViewDate;

    public void onButtonSnackbarClick(View view){
        Snackbar.make(view, "This is snackbar", Snackbar.LENGTH_LONG).show();
    }

    public void onTimeButtonClick(View view){
        MyTimeDialogFragment timeDialogFragment = new MyTimeDialogFragment();
        timeDialogFragment.setTimeSelectedListener(this);
        timeDialogFragment.show(getSupportFragmentManager(), "time");
    }

    public void onDateButtonClick(View view){
        MyDateDialogFragment dateDialogFragment = new MyDateDialogFragment();
        dateDialogFragment.setDateListener(this);
        dateDialogFragment.show(getSupportFragmentManager(), "date");
    }

    public void onButtonProgressClick(View view){
        MyProgressDialogFragment progressDialogFragment = new MyProgressDialogFragment();
        progressDialogFragment.show(getSupportFragmentManager(),"progress");
    }

    public void onClickShowDialog(View view) {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",
                Toast.LENGTH_LONG).show();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTime = findViewById(R.id.textViewTime);
        textViewDate = findViewById(R.id.textViewDate);
    }

    @Override
    public void onTimeSelected(String time) {
        textViewTime.setText(time);
        Snackbar.make(getWindow().getDecorView().getRootView(), time, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onDateSelected(String date) {
        textViewDate.setText(date);
        Snackbar.make(getWindow().getDecorView().getRootView(), date, Snackbar.LENGTH_LONG).show();
    }
}