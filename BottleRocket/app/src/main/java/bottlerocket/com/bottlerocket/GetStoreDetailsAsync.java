package bottlerocket.com.bottlerocket;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sneha on 11-Feb-16.
 */
public class GetStoreDetailsAsync extends AsyncTask {

    Context ctxt = null;
    ProgressDialog progress = null;

    public GetStoreDetailsAsync(SplashScreen ct) {
        ctxt = ct;
        progress = new ProgressDialog(ct);
        progress.setTitle(R.string.loading);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress.show();
    }

    @Override
    protected Object doInBackground(Object[] params) {

        String data = PersistentStorage.retrieveData(ctxt);
        if(data != null)
        {
           return data;
        }
        else
        {
            InputStream is = null;
            String URL = "http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/stores.json";
            try {
                java.net.URL url = new URL(URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();

                String contentAsString = convertInputStreamToString(is);
                return contentAsString;
            } catch (Exception ex) {
                Log.e("EXCEPTION", ex.getLocalizedMessage());
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        try {
            progress.cancel();
            PersistentStorage.storeData(ctxt,o.toString());
            showMap(o.toString(), ctxt);
        } catch (Exception ex) {
            Log.e("EXCEPTION", ex.getLocalizedMessage());
        }

    }

    private void showMap(String obj, Context ct) {
        try {
            Gson gson = new Gson();

            StoreDetails[] events = gson.fromJson(obj, StoreDetails[].class);
            SplashScreen activity = new SplashScreen();
            for (StoreDetails cat : events) {
                String name = cat.name;
                String address = cat.address;
                String phone = cat.phone;
                String logoUrl = cat.storeLogoURL;


                activity.storeDetailsArr.add(new StoreDetails(name, address, phone, logoUrl));
            }

            Intent intent = new Intent(ct, MainActivity.class);
            intent.putParcelableArrayListExtra("Data", activity.storeDetailsArr);
            ct.startActivity(intent);


        } catch (Exception ex) {
            Log.e("EXCEPTION", ex.getLocalizedMessage());
        }
    }

    public String convertInputStreamToString(InputStream stream) {
        java.util.Scanner s = new java.util.Scanner(stream).useDelimiter("\\A");
        String ret =  s.hasNext() ? s.next() : "";
        return  ret.substring(11, ret.length()-1);
    }
}
