package hello.tech.wallpaper.View;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

import hello.tech.wallpaper.Adapters.ImageAdapter;
import hello.tech.wallpaper.Api.Utilitis;
import hello.tech.wallpaper.Modals.PhotosModal;
import hello.tech.wallpaper.Modals.SrcModal;
import hello.tech.wallpaper.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<SrcModal> list;
    ProgressDialog pd;
    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        list = new ArrayList<>();
        pd = new ProgressDialog(this);
        pd.setMessage("Loading Please Wait");
        adapter = new ImageAdapter(list, this);
        binding.recycle.setLayoutManager(new GridLayoutManager(this, 2));

        binding.recycle.setAdapter(adapter);
        binding.recycle.setHasFixedSize(true);
        pd.show();
        findPhotos();

        binding.nature.setOnClickListener(v -> {
            String query = "nature";
            pd.show();
            getSearchImage(query);
        });


        binding.trend.setOnClickListener(v -> findPhotos());


        binding.car.setOnClickListener(v -> {
            String query = "car";
            pd.show();
            getSearchImage(query);
        });

        binding.god.setOnClickListener(v -> {
            String query = "god";
            pd.show();
            getSearchImage(query);
        });

        binding.games.setOnClickListener(v -> {
            String query = "game";
            pd.show();
            getSearchImage(query);
        });


        binding.btn.setOnClickListener(v -> {
            String query = binding.query.getText().toString().trim();
            if (TextUtils.isEmpty(query)) {
                Toast.makeText(MainActivity.this, "You have Entered Anything", Toast.LENGTH_SHORT).show();
            } else {
                pd.show();
                getSearchImage(query);
            }
        });
    }


    private void getSearchImage(String query) {
        Utilitis.getApiInterface().getSearchImage(query, 1, 88).enqueue(new Callback<PhotosModal>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<PhotosModal> call, @NonNull Response<PhotosModal> response) {
                pd.dismiss();
                list.clear();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    list.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                } else {
                    pd.dismiss();
                    Toast.makeText(MainActivity.this, "Not able to get Images ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhotosModal> call, @NonNull Throwable t) {
                pd.dismiss();
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findPhotos() {
        list.clear();
        Utilitis.getApiInterface().getImage(1, 88).enqueue(new Callback<PhotosModal>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<PhotosModal> call, @NonNull Response<PhotosModal> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    list.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                } else {
                    pd.dismiss();
                    Toast.makeText(MainActivity.this, "Not able to get Images ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhotosModal> call, @NonNull Throwable t) {
                pd.dismiss();
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}