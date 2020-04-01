package com.example.androidsecurityapplication.UnauthorizedPersonsInfo;

import android.net.Uri;

public class PictureItem {
    public Uri uri;
    public String date;

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PictureItem() {
        this.uri = uri;
        this.date = date;
    }
}
