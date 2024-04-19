package ru.mirea.klinduhovir.mireaproject;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link lesson4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class lesson4Fragment extends Fragment{
    private Button button;
    private TextView resultTextView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public lesson4Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment lesson4Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static lesson4Fragment newInstance(String param1, String param2) {
        lesson4Fragment fragment = new lesson4Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson4, container, false);
        resultTextView = view.findViewById(R.id.resultTextView);
        button = view.findViewById(R.id.btn_start_task);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkRequest uploadWorkRequest =
                        new OneTimeWorkRequest.Builder(BackgroundWorker.class)
                                .build();
                WorkManager
                        .getInstance()
                        .enqueue(uploadWorkRequest);

                WorkManager.getInstance(requireContext()).getWorkInfoByIdLiveData(uploadWorkRequest.getId())
                        .observe(getViewLifecycleOwner(), new Observer<WorkInfo>() {
                            @Override
                            public void onChanged(WorkInfo workInfo) {
                                if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                    int randomNumber = workInfo.getOutputData().getInt("randomNumber", -1);
                                    resultTextView.setText("Сгенерированное число: " + randomNumber);
                                }
                            }
                        });
            }
        });

        return view;
    }

}