package com.jcupzz.mycoviddiary;

public class Main_Vp_Names {
    String vp_name;
    String vp_description;
    int vp_image;

    public Main_Vp_Names(String vp_name,String vp_description, int vp_image) {
        this.vp_name = vp_name;
        this.vp_description = vp_description;
        this.vp_image = vp_image;
    }

    public String getVp_name() {
        return vp_name;
    }

    public void setVp_name(String vp_name) {
        this.vp_name = vp_name;
    }

    public int getVp_image() {
        return vp_image;
    }

    public void setVp_image(int vp_image) {
        this.vp_image = vp_image;
    }

    public String getVp_description() {
        return vp_description;
    }

    public void setVp_description(String vp_description) {
        this.vp_description = vp_description;
    }
}
