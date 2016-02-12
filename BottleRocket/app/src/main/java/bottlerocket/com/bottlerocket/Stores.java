package bottlerocket.com.bottlerocket;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by Sneha on 11-Feb-16.
 */
public class Stores
{
    String name;
    String address;
    String phone;
    Drawable storeLogoURL;

    public Stores(Context ctx, String storeName, String storeAddress, String phoneNumber, String url)
    {
        name = storeName;
        address = storeAddress;
        phone = phoneNumber;
        storeLogoURL = Utility.drawableFromUrl(ctx, url);
    }
}
