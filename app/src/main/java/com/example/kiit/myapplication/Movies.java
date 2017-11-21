package com.example.kiit.myapplication;

public class Movies {
    String mname,rating,date;
    String imgurl;

    public Movies(String mname, String rating, String date,String imgurl) {
        this.mname = mname;
        this.rating = rating;
        this.date = date;
        this.imgurl=imgurl;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
