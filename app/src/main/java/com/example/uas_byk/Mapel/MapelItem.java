package com.example.uas_byk.Mapel;

import java.io.Serializable;

public class MapelItem {
    private String judul;
    private String desc1;
    private String desc2;
    private String desc3;

    public MapelItem() {

    }

    public MapelItem(String judul, String desc1, String desc2, String desc3) {
        this.judul = judul;
        this.desc1 = desc1;
        this.desc2 = desc2;
        this.desc3 = desc3;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public String getDesc3() {
        return desc3;
    }

    public void setDesc3(String desc3) {
        this.desc3 = desc3;
    }


}
