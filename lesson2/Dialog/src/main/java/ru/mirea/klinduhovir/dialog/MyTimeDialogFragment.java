package ru.mirea.klinduhovir.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import java.sql.Time;
import java.util.Calendar;

public class MyTimeDialogFragment extends DialogFragment {

    private TimeSelectedListener timeSelectedListener;

    public void setTimeSelectedListener(TimeSelectedListener listener) {
        this.timeSelectedListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(requireActivity(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time= hourOfDay + " : " + minute;
                        if (timeSelectedListener != null){
                            timeSelectedListener.onTimeSelected(time);
                        }
                    }
                }, hour, minute, false);
    }

}
