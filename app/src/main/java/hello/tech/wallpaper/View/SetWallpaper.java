package hello.tech.wallpaper.View;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import hello.tech.wallpaper.databinding.ActivitySetWallaperBinding;

public class SetWallpaper extends AppCompatActivity {
    ActivitySetWallaperBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetWallaperBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        String url = intent.getStringExtra("image");
        Picasso.get().load(url).into(binding.wallpaper);

        binding.btnWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bitmap bitmap = ((BitmapDrawable) binding.wallpaper.getDrawable()).getBitmap();
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(SetWallpaper.this, "Wallpaper Set Successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(SetWallpaper.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}