package bottlerocket.com.bottlerocket;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sneha on 12-Feb-16.
 */
public class PersistentStorage
{

    public static void storeData(Context ctx, String jsonStr)
    {
        SharedPreferences.Editor editor = ctx.getSharedPreferences("AppData",ctx.MODE_PRIVATE).edit();
        editor.putString("jsonStr", jsonStr);
        editor.commit();
    }

    public static String retrieveData(Context ctx)
    {
        String jsonData = null;
        SharedPreferences prefs = ctx.getSharedPreferences("AppData", ctx.MODE_PRIVATE);
        jsonData = prefs.getString("jsonStr", "");
        if(jsonData.equals(""))
        {
            return  null;
        }
        return  jsonData;
    }



}
