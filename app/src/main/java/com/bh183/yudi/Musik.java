package com.bh183.yudi;



public class Musik {
    private int idMusik;
    private String judul;
    private String perilisan;
    private String cover;
    private String penulis;
    private String penyanyi;
    private String agensi;
    private String deskripsi;


    public Musik(int idMusik, String judul, String perilisan, String cover, String penulis, String penyanyi, String agensi, String deskripsi) {
        this.idMusik = idMusik;
        this.judul = judul;
        this.perilisan = perilisan;
        this.cover = cover;
        this.penulis = penulis;
        this.penyanyi = penyanyi;
        this.agensi = agensi;
        this.deskripsi = deskripsi;

    }

    public Musik(int csrInt, String string, String tempDate, String string1, String string2, String string3, String string4, String string5, String string6) {
    }

    public int getIdMusik() {
        return idMusik;
    }

    public void setIdMusik(int idMusik) { this.idMusik = idMusik; }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPerilisan() {
        return perilisan;
    }

    public void setPerilisan(String perilisan) {
        this.perilisan = perilisan;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getPenyanyi() {
        return penyanyi;
    }

    public void setPenyanyi(String penyanyi) {
        this.penyanyi = penyanyi;
    }

    public String getAgensi() {
        return agensi;
    }

    public void setAgensi(String agensi) {
        this.agensi = agensi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
