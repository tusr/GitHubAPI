package com.example.tusharmahale.githubapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ImageDownloader extends AsyncTask<String,Void,Bitmap> {
    @Override
    protected Bitmap doInBackground(String... urls) {
       try{
           URL url = new URL(urls[0]);
           HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
           connection.connect();
           InputStream inputStream = connection.getInputStream();
           Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
           return myBitmap;
       }
       catch (Exception  e) {
           e.printStackTrace();
       }
        return null;
    }
}
