package com.mc2022.template;

import android.graphics.Bitmap;

public class model {
    Bitmap img;
    String title;
    String desc;
    public model(Bitmap img,String title, String desc){
        this.img=img;
        this.title=title;
        this.desc=desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
