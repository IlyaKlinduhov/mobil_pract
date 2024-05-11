package ru.mirea.klinduhovir.mireaproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yandex.mapkit.geometry.Point;

import java.util.ArrayList;

import ru.mirea.klinduhovir.mireaproject.map.Location;
import ru.mirea.klinduhovir.mireaproject.map.MapActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link mapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mapFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<Location> locations = new ArrayList<>();

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    static final int REQUEST_MAP_ACTIVITY = 1;

    public mapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static mapFragment newInstance(String param1, String param2) {
        mapFragment fragment = new mapFragment();
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

        locations.add(new Location(55.761092, 37.578308,"Зоопарк", "Здесь живут животные", "Большая Грузинская улица, 1с1"));
        locations.add(new Location(55.794259, 37.701448,"МИРЭА", "Учебное заведение", "улица Стромынка, 20"));
        locations.add(new Location(55.760257, 37.618536,"Большой театр", "Здесь выступают актеры", "Театральная площадь, 1"));
        locations.add(new Location(55.777057, 37.654913,"Вокзал", "Здесь тусуются маргиналы",  "Комсомольская площадь, 3"));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        imageView1 = view.findViewById(R.id.imageView1);
        imageView2 = view.findViewById(R.id.imageView2);
        imageView3 = view.findViewById(R.id.imageView3);
        imageView4 = view.findViewById(R.id.imageView4);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                Log.d("Сработало", String.valueOf(v.getId()));
                Intent intent = new Intent(getContext(), MapActivity.class);

                if (id == R.id.imageView1){
                    intent.putExtra("location", locations.get(0));
                }
                if (id == R.id.imageView2){
                    intent.putExtra("location", locations.get(1));
                }
                if (id == R.id.imageView3){
                    intent.putExtra("location", locations.get(2));
                }
                if (id == R.id.imageView4){
                    intent.putExtra("location", locations.get(3));
                }
                startActivityForResult(intent, REQUEST_MAP_ACTIVITY);

            }
        };
        imageView1.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);
        imageView3.setOnClickListener(onClickListener);
        imageView4.setOnClickListener(onClickListener);


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_MAP_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
               
            }
        }
    }
}