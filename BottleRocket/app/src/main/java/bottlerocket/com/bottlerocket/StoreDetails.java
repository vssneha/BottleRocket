package bottlerocket.com.bottlerocket;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sneha on 11-Feb-16.
 */
public class StoreDetails implements Parcelable
{
    public static final Parcelable.Creator<StoreDetails> CREATOR = new Parcelable.Creator<StoreDetails>() {
        public StoreDetails createFromParcel(Parcel in) {
            return new StoreDetails(in);
        }

        public StoreDetails[] newArray(int size) {
            return new StoreDetails[size];
        }
    };

    String name;
    String address;
    String phone;
    String storeLogoURL;

    public StoreDetails(String storeName, String storeAddress, String phoneNumber, String url)
    {
        name = storeName;
        address = storeAddress;
        phone = phoneNumber;
        storeLogoURL = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private StoreDetails(Parcel in) {

        name = in.readString();
        address = in.readString();
        phone = in.readString();
        storeLogoURL = in.readString();

    }

    @Override
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeString(name);
        out.writeString(address);
        out.writeString(phone);
        out.writeString(storeLogoURL);
    }
}
