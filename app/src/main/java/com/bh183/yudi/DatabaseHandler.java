package com.bh183.yudi;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Queue;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 2;
    private final static String DATABASE_NAME = "db_musik";
    private final static String TABLE_MUSIK ="t_musik";
    private final static String KEY_ID_MUSIK = "ID_Musii";
    private final static String KEY_JUDUL = "Judul";
    private final static String KEY_TGL = "Perilisan";
    private final static String KEY_COVER = "Cover";
    private final static String KEY_PENULIS = "Penulis";
    private final static String KEY_PENYANYI = "Penyanyi";
    private final static String KEY_AGENSI = "Agensi";
    private final static String KEY_DESKRIPSI = "Deskripsi";
    private Context context;


    public DatabaseHandler(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_MUSIK = "CREATE TABLE " + TABLE_MUSIK
                + "(" + KEY_ID_MUSIK + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_JUDUL + " TEXT, " + KEY_TGL + " DATE, "
                + KEY_COVER + " TEXT, " + KEY_PENULIS + " TEXT, "
                + KEY_PENYANYI + " TEXT, " + KEY_AGENSI + " TEXT, " + KEY_DESKRIPSI + " TEXT);";

        db.execSQL(CREATE_TABLE_MUSIK);
        inisialisasiMusikAwal(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_MUSIK;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void tambahMusik(Musik dataMusik){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataMusik.getJudul());
        cv.put(KEY_TGL, dataMusik.getPerilisan());
        cv.put(KEY_COVER, dataMusik.getCover());
        cv.put(KEY_PENULIS, dataMusik.getPenulis());
        cv.put(KEY_PENYANYI, dataMusik.getPenyanyi());
        cv.put(KEY_AGENSI, dataMusik.getAgensi());
        cv.put(KEY_DESKRIPSI, dataMusik.getDeskripsi());


        db.insert(TABLE_MUSIK, null, cv);
        db.close();
    }

    public void tambahMusik(Musik dataMusik, SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataMusik.getJudul());
        cv.put(KEY_TGL, dataMusik.getPerilisan());
        cv.put(KEY_COVER, dataMusik.getCover());
        cv.put(KEY_PENULIS, dataMusik.getPenulis());
        cv.put(KEY_PENYANYI, dataMusik.getPenyanyi());
        cv.put(KEY_AGENSI, dataMusik.getAgensi());
        cv.put(KEY_DESKRIPSI, dataMusik.getDeskripsi());
        db.insert(TABLE_MUSIK, null, cv);
    }

    public void editMusik(Musik dataMusik){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataMusik.getJudul());
        cv.put(KEY_TGL, dataMusik.getPerilisan());
        cv.put(KEY_COVER, dataMusik.getCover());
        cv.put(KEY_PENULIS, dataMusik.getPenulis());
        cv.put(KEY_PENYANYI, dataMusik.getPenyanyi());
        cv.put(KEY_AGENSI, dataMusik.getAgensi());
        cv.put(KEY_DESKRIPSI, dataMusik.getDeskripsi());

        db.update(TABLE_MUSIK, cv, KEY_ID_MUSIK + "=?", new String[]{String.valueOf(dataMusik.getIdMusik())});
        db.close();
    }

    public void hapusMusik (int idMusik){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_MUSIK, KEY_ID_MUSIK + "=?", new String[]{String.valueOf(idMusik)});
        db.close();
    }

    public ArrayList<Musik> getAllMusik(){
        ArrayList<Musik> dataMusik = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_MUSIK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);
        if(csr.moveToFirst()){
            do {
                Musik tempMusik = new Musik(
                        csr.getInt(0),
                        csr.getString(1),
                        csr.getString(2),
                        csr.getString(3),
                        csr.getString(4),
                        csr.getString(5),
                        csr.getString(6),
                        csr.getString(7)

                );

                dataMusik.add(tempMusik);
            } while (csr.moveToNext());
        }

        return dataMusik;
    }

    private String storeImageFile(int id) {
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInternalStorage(image, context);
        return location;
    }

    private void inisialisasiMusikAwal(SQLiteDatabase db){
        int idMusik = 0;

        // Menambahkan data musik ke 1
        Musik musik1 = new Musik(
                idMusik,
                "Psycho",
                "23 Desember 2019",
                storeImageFile(R.drawable.musik1),
                "Kenzie, Andrew Scott",
                "Red Velvet",
                "SM Entertainmet",
                "Psycho adalah lagu yang direkam oleh girl grup Korea Selatan Red Velvet untuk album kompilasi pertama mereka The ReVe Festival: Finale , yang juga bertindak sebagai angsuran ketiga dan terakhir dari trilogi The ReVe Festival grup."
        );

        tambahMusik(musik1, db);
        idMusik++;

        // Menambahkan musik ke 2
        Musik musik2 = new Musik(
                idMusik,
                "Blood Sweat and Tears",
                "24 April 2019",
                storeImageFile(R.drawable.musik2),
                "RM, Suga, J-Hope",
                "BTS (BangtanBoys)",
                "BigHit Entertainment",
                "Blood Sweat & Tears adalah lagu yang direkam oleh boyband Korea Selatan BTS untuk album studio kedua mereka , Wings (2016)."
        );

        tambahMusik(musik2, db);
        idMusik++;

        // Menambahkan musik ke 3
        Musik musik3 = new Musik(
                idMusik,
                "What Is Love",
                "09 April 2018",
                storeImageFile(R.drawable.musik3),
                "Park Jin Young",
                "Twice",
                "JYP Entertainment",
                "What Is Love? Adalah lagu yang direkam oleh girl grup Korea Selatan Twice . Album ini dirilis oleh JYP Entertainment pada 9 April 2018, sebagai singel utama dari drama kelima mereka yang diperpanjang dengan nama yang sama."
        );

        tambahMusik(musik3, db);
        idMusik++;

        // Menambahkan musik ke 4
        Musik musik4 = new Musik(
                idMusik,
                "Kill This Love",
                "5 April 2019",
                storeImageFile(R.drawable.musik4),
                "Teddy & R.Tee",
                "BlackPink",
                "YG Entertainment",
                "Kill This Love adalah album mini berbahasa Korea kedua dari grup vokal wanita asal Korea Selatan Blackpink. Album ini merupakan mini album kedua mereka yang dirilis sejak Square Up pada juni 2018"
        );

        tambahMusik(musik4, db);
        idMusik++;

    }
}
