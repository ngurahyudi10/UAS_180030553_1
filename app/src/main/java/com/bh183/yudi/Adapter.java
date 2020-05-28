package com.bh183.yudi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.MusikViewHolder> {

    private Context context;
    private ArrayList<Musik> dataMusik;


    public Adapter(Context context, ArrayList<Musik> dataMusik) {
        this.context = context;
        this.dataMusik = dataMusik;
    }

    @NonNull
    @Override
    public MusikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_musik, parent, false);
        return new MusikViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusikViewHolder holder, int position) {
        Musik tempMusik = dataMusik.get(position);
        holder.idMusik = tempMusik.getIdMusik();
        holder.judul.setText(tempMusik.getJudul());
        holder.deskripsi.setText(tempMusik.getDeskripsi());
        holder.perilisan.setText(tempMusik.getPerilisan());
        holder.cover = tempMusik.getCover();
        holder.penulis.setText(tempMusik.getPenulis());
        holder.penyanyi.setText(tempMusik.getPenyanyi());
        holder.agensi.setText(tempMusik.getAgensi());

        try {
            File file = new File(holder.cover);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            holder.imgCover.setImageBitmap(bitmap);
            holder.imgCover.setContentDescription(holder.cover);
        } catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(context, "Gagal mengambil gambar dalam media penyimpanan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {

        return dataMusik.size();
    }

    public class MusikViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        public int idMusik;
        private ImageView imgCover;
        private TextView judul;
        private TextView deskripsi;
        private TextView penulis;
        private TextView penyanyi;
        private TextView agensi;
        private TextView perilisan;
        private String cover;

        public MusikViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCover = itemView.findViewById(R.id.iv_musik);
            judul = itemView.findViewById(R.id.tv_judul_musik);
            perilisan = itemView.findViewById(R.id.tv_perilisan);
            penulis = itemView.findViewById(R.id.tv_penulis);
            penyanyi = itemView.findViewById(R.id.tv_penyanyi);
            agensi = itemView.findViewById(R.id.tv_agensi);
            deskripsi = itemView.findViewById(R.id.tv_deskripsi);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent bukaMusik = new Intent(context, TampilActivity.class);
            bukaMusik.putExtra("ID", idMusik);
            bukaMusik.putExtra("JUDUL", judul.getText().toString());
            bukaMusik.putExtra("PERILISAN", perilisan.getText().toString());;
            bukaMusik.putExtra("COVER", cover);
            bukaMusik.putExtra("PENULIS", penulis.getText().toString());
            bukaMusik.putExtra("PENYANYI", penyanyi.getText().toString());
            bukaMusik.putExtra("AGENSI", agensi.getText().toString());
            bukaMusik.putExtra("DESKRIPSI", deskripsi.getText().toString());
            context.startActivity(bukaMusik);
        }

        @Override
        public boolean onLongClick(View v) {

            Intent bukaInput = new Intent(context, InputActivity.class);
            bukaInput.putExtra("OPERASI", "update");
            bukaInput.putExtra("ID", idMusik);
            bukaInput.putExtra("JUDUL", judul.getText().toString());
            bukaInput.putExtra("PERILISAN", perilisan.getText().toString());;
            bukaInput.putExtra("COVER", cover);
            bukaInput.putExtra("PENULIS", penulis.getText().toString());
            bukaInput.putExtra("PENYANYI", penyanyi.getText().toString());
            bukaInput.putExtra("AGENSI", agensi.getText().toString());
            bukaInput.putExtra("DESKRIPSI", deskripsi.getText().toString());
            context.startActivity(bukaInput);
            return true;
        }
    }
}
