package bottlerocket.com.bottlerocket;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sneha on 11-Feb-16.
 */
public class StoreAdapter extends ArrayAdapter<Stores>
{

    public StoreAdapter(Context context, int resource, List<Stores> items)
    {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.row, null);
        }

        Stores storeDetails = getItem(position);

        if (storeDetails != null) {
            ImageView imageView = (ImageView) v.findViewById(R.id.logo);
            TextView phone = (TextView) v.findViewById(R.id.phoneNumber);
            TextView address = (TextView) v.findViewById(R.id.address);

            imageView.setImageDrawable(storeDetails.storeLogoURL);
            phone.setText(storeDetails.phone);
            address.setText(storeDetails.address);
        }

        return v;
    }


}
