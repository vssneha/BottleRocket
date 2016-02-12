package bottlerocket.com.bottlerocket;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ListActivity {


    public StoreAdapter adapter = null;

    public ArrayList<StoreDetails> sdArr = new ArrayList<StoreDetails>();
    public ArrayList<Stores> storesArr = new ArrayList<Stores>();

    private static Object             obj                             = new Object();
    static boolean                    resultBool                      = false;
    ProgressDialog progress = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bdl = getIntent().getExtras();
        sdArr = bdl.getParcelableArrayList("Data");

        Toast.makeText(MainActivity.this,"Loading data...",Toast.LENGTH_SHORT).show();

        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.show();

        setAdapter();

        showListView();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                showListView();
            }
        }, 7000);

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                Stores value = (Stores)adapter.getItemAtPosition(position);



                Intent intent = new Intent(getApplicationContext(), BottleRocketWebview.class);
                intent.putExtra("URL", value.name);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void showListView()
    {
        progress.cancel();
        try
        {
            adapter = new StoreAdapter(MainActivity.this, R.id.linear, storesArr);
            setListAdapter(adapter);

        }
        catch (Exception ex)
        {
            Log.e("EXCEPTION", ex.getLocalizedMessage());
        }
    }


    public void setAdapter()
    {
        new Thread(new Runnable() {
            public void run() {
                try{
                    for(StoreDetails sd : sdArr)
                    {
                        Stores st = new Stores(MainActivity.this, sd.name,sd.address,sd.phone,sd.storeLogoURL);
                        storesArr.add(st);
                    }
                }
                catch (Exception ex){

                }

            }
        }).start();

    }


    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}
