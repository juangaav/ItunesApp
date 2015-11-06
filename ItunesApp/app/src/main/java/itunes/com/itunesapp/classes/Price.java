package itunes.com.itunesapp.classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class represents the price of the app on the Appstore with its corresponding currency
 * Created by @jvillafane on 2/11/15.
 */
public class Price implements Parcelable {
    private float amount;
    private String currency;

    public Price() {
    }

    public Price(float amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.amount);
        dest.writeString(this.currency);
    }

    protected Price(Parcel in) {
        this.amount = in.readFloat();
        this.currency = in.readString();
    }

    public static final Parcelable.Creator<Price> CREATOR = new Parcelable.Creator<Price>() {
        public Price createFromParcel(Parcel source) {
            return new Price(source);
        }

        public Price[] newArray(int size) {
            return new Price[size];
        }
    };
}
