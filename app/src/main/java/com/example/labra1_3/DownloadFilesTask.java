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
        void downloadDone(String s);
    }

    private OnDownloadProgressUpdate callBack;

    public OnDownloadProgressUpdate getCallBack() {
        return callBack;
    }

    public void setCallBack(OnDownloadProgressUpdate callBack) {
        this.callBack = callBack;
    }


    @Override
    protected String doInBackground(URL... url) {
        String htmlString = null;
        int count = url.length;
        for (int i = 0; i < count; i++) {
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url[i].openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                htmlString = convertStreamToString(in);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return htmlString;
    }

    @Override
    protected void onPostExecute(String s) {
        if (callBack != null) {
            callBack.downloadDone(s);
        }
    }
}
