package com.example.labra1_3;

import android.os.AsyncTask;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.labra1_3.MainActivity.convertStreamToString;


public class DownloadFilesTask extends AsyncTask <URL, Void, String> {


    interface OnDownloadProgressUpdate {
        void downloadDone();
    }


    @Override
    protected String doInBackground(URL... url) {
        String htmlString = null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            htmlString = convertStreamToString(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return htmlString;
    }
}
