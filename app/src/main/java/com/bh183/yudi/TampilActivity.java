package com.bh183.yudi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TampilActivity extends AppCompatActivity {

    private ImageView imgCover;
    private TextView tvJudul, tvPerilisan, tvPenulis, tvPenyanyi, tvAgensi, tvDeskripsi;
    private String linkMusik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        imgCover = findViewById(R.id.iv_musik);
        tvJudul = findViewById(R.id.tv_judul_musik);
        tvPerilisan = findViewById(R.id.tv_perilisan);
        tvPenulis = findViewById(R.id.tv_penulis);
        tvPenyanyi = findViewById(R.id.tv_penyanyi);
        tvAgensi = findViewById(R.id.tv_agensi);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);

        Intent terimaData = getIntent();
        tvJudul.setText(terimaData.getStringExtra("JUDUL"));
        tvPerilisan.setText(terimaData.getStringExtra("PERILISAN"));
        tvPenulis.setText(terimaData.getStringExtra("PENULIS"));
        tvPenyanyi.setText(terimaData.getStringExtra("PENYANYI"));
        tvAgensi.setText(terimaData.getStringExtra("AGENSI"));
        tvDeskripsi.setText(terimaData.getStringExtra("DESKRIPSI"));
        String imgLocation = terimaData.getStringExtra("COVER");

        try {
            File file = new File(imgLocation);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imgCover.setImageBitmap(bitmap);
            imgCover.setContentDescription(imgLocation);
        } catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(this, "Gagal dalam mengambil gambar", Toast.LENGTH_SHORT).show();
        }

        linkMusik = terimaData.getStringExtra("LINK");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tampil_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.item_bagikan){
            Intent bagikanMusik = new Intent(Intent.ACTION_SEND);
            bagikanMusik.putExtra(Intent.EXTRA_SUBJECT, tvJudul.getText().toString());
            bagikanMusik.putExtra(Intent.EXTRA_TEXT, linkMusik);
            bagikanMusik.setType("text/plain");
            startActivity(Intent.createChooser(bagikanMusik, "Bagikan Musik"));
        }

        return super.onOptionsItemSelected(item);
    }
}

