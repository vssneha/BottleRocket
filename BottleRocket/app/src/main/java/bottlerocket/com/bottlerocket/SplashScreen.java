package bottlerocket.com.bottlerocket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity {

    public ArrayList<StoreDetails> storeDetailsArr = new ArrayList<StoreDetails>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

       new GetStoreDetailsAsync(this).execute();

    }
}
