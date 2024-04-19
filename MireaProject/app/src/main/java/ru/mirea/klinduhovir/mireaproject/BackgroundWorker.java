package ru.mirea.klinduhovir.mireaproject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BackgroundWorker extends Worker {

    public BackgroundWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("TAG", "doWork: start");
        Data outputData = null;
        try {
            TimeUnit.SECONDS.sleep(5);
            Random random = new Random();
            int randomNumber = random.nextInt(100);
            outputData = new Data.Builder()
                    .putInt("randomNumber", randomNumber)
                    .build();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("TAG", "doWork: end");
        return Result.success(outputData);
    }
}
