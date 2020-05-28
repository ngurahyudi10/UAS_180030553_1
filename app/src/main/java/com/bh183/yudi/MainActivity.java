package com.bh183.yudi;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Musik> dataMusik = new ArrayList<>();
    private RecyclerView rvMusik;
    private Adapter Adapter;
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMusik = findViewById(R.id.rv_tampil_musik);
        dbHandler = new DatabaseHandler(this);
        tampilDataMusik();
    }

    private void tampilDataMusik (){
        dataMusik = dbHandler.getAllMusik();
        Adapter = new Adapter(this, dataMusik);
        RecyclerView.LayoutManager layManager = new LinearLayoutManager(MainActivity.this);
        rvMusik.setLayoutManager(layManager);
        rvMusik.setAdapter(Adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.item_menu_tambah){
            Intent bukaInput = new Intent(getApplicationContext(), InputActivity.class);
            bukaInput.putExtra("OPERASI", "insert");
            startActivity(bukaInput);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilDataMusik();
    }
}

