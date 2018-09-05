package com.example.nurture.dragging;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DrawableImageSharing extends AppCompatActivity {

    Button btnShareImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_image_sharing);

        btnShareImg = findViewById(R.id.btnShareImg);
        btnShareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.download);
                String path = getExternalCacheDir()+"/shareimage.jpg";
                java.io.OutputStream out = null;
                java.io.File file=new java.io.File(path);
                try {
                    out = new java.io.FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                path = file.getPath();
                Uri bmpUri = Uri.parse("file://" + path);

                Intent shareIntent = new Intent();
                shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                shareIntent.setType("image/jpg");
                startActivity(Intent.createChooser(shareIntent, "Share with"));
            }
        });
    }
}
