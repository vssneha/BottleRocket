package bottlerocket.com.bottlerocket;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sneha on 11-Feb-16.
 */
public class Utility
{
    public static Drawable drawableFromUrl(Context ctx, String url)
    {
        try
        {
            Bitmap x;

            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return  resize(ctx, d);
        }
        catch (Exception ex)
        {
            Log.e("EXCEPTION", ex.getLocalizedMessage());
        }
        return  null;

    }

    public static Drawable resize(Context ctx,Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 250, 150, false);
        return new BitmapDrawable(ctx.getResources(), bitmapResized);
    }
}
