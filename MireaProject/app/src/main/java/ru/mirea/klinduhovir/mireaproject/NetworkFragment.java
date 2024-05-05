package ru.mirea.klinduhovir.mireaproject;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.mirea.klinduhovir.mireaproject.ip.IpClient;
import ru.mirea.klinduhovir.mireaproject.ip.IpResponse;
import ru.mirea.klinduhovir.mireaproject.ip.IpService;
import ru.mirea.klinduhovir.mireaproject.weather.WeatherClient;
import ru.mirea.klinduhovir.mireaproject.weather.WeatherResponse;
import ru.mirea.klinduhovir.mireaproject.weather.WeatherService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NetworkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NetworkFragment extends Fragment {
    private double lat;
    private double lon;
    TextView textViewWeather;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NetworkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NetworkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NetworkFragment newInstance(String param1, String param2) {
        NetworkFragment fragment = new NetworkFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_network, container, false);

        textViewWeather = view.findViewById(R.id.textViewWeather);

        textViewWeather.setText("Loading weather...");

        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo = null;
        if (connectivityManager != null) {

            networkinfo = connectivityManager.getActiveNetworkInfo();

        }
        if (networkinfo != null && networkinfo.isConnected()) {

            getIp();

        } else {

            Toast.makeText(getContext(), "Нет интернета", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void getIp(){
        IpService ipService = IpClient.getIpService();

        Call<IpResponse> call = ipService.getIp();

        call.enqueue(new Callback<IpResponse>() {
            @Override
            public void onResponse(Call<IpResponse> call, Response<IpResponse> response) {
                lat = response.body().getLat();
                lon = response.body().getLon();
                getWeather();
            }

            @Override
            public void onFailure(Call<IpResponse> call, Throwable throwable) {

            }
        });


    }

    private void getWeather(){
        String token = "3a709ca285eea36680c02ce9c1837d2c";

        WeatherService apiService = WeatherClient.getWeatherService();

        Call<WeatherResponse> call = apiService.getWeather(lat, lon, token);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                String textTemp = "Текущая температура в вашем месте: " + response.body().getCurrentTemp()+"℃";
                textViewWeather.setText(textTemp);
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable throwable) {

            }
        });
    }
}