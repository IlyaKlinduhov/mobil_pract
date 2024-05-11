package ru.mirea.klinduhovir.mireaproject.map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import ru.mirea.klinduhovir.mireaproject.R;
import ru.mirea.klinduhovir.mireaproject.databinding.ActivityMainBinding;
import ru.mirea.klinduhovir.mireaproject.databinding.ActivityMapBinding;

public class MapActivity extends AppCompatActivity {

    private boolean isWork = false;
    private final int REQUEST_CODE_PERMISSION = 100;
    ActivityMapBinding binding;
    MapView mapView;
    private final Point SCREEN_CENTER = new Point(55.751426, 37.618879);
    Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        requestPermissions();
        if (isWork) {
            MapKitFactory.initialize(this);

            Bundle arguments = getIntent().getExtras();
            if (arguments != null) {
                location = (Location) arguments.getSerializable("location");
            }

            mapView = binding.mapView;
            mapView.getMap().move(new CameraPosition(SCREEN_CENTER, 10, 0, 0));

            setContentView(binding.getRoot());


            PlacemarkMapObject marker = mapView.getMap().getMapObjects().addPlacemark(
                    new Point(location.getLatitude(), location.getLongitude())
            );
            marker.setText(location.getName());
            marker.setIcon(ImageProvider.fromResource(this, com.yandex.maps.mobile.R.drawable.search_layer_pin_selected_default));
            marker.addTapListener(new MapObjectTapListener() {
                @Override
                public boolean onMapObjectTap(@NonNull MapObject mapObject, @NonNull Point point) {
                    MapDialogFragment dialogFragment = new MapDialogFragment();
                    Bundle args = new Bundle();
                    args.putString("address", location.getAddress());
                    args.putString("description", location.getDescription());
                    dialogFragment.setArguments(args);
                    dialogFragment.show(getSupportFragmentManager(), "custom");
                    return false;
                }
            });
        }
    }
    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_PERMISSION);
        } else {
            isWork = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isWork = true;
            } else {
                Toast.makeText(this, "Разрешение на определение местоположения не предоставлено",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}