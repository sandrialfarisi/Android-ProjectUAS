package com.example.uas_byk.Mapel;

import java.io.Serializable;

public class MapelItem {
    private String judul;
    private String penjelasan;

    public MapelItem() {

    }

    public MapelItem(String judul, String penjelasan) {
        this.judul = judul;
        this.penjelasan = penjelasan;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenjelasan() {
        return penjelasan;
    }

    public void setPenjelasan(String penjelasan) {
        this.penjelasan = penjelasan;
    }
}
