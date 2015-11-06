package itunes.com.itunesapp.classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Contains information about the release date of an app
 * Created by @jvillafane on 2/11/15.
 */
public class ReleaseDate implements Parcelable {
    private String formattedDate;
    private String readableDate;

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public String getReadableDate() {
        return readableDate;
    }

    public void setReadableDate(String readableDate) {
        this.readableDate = readableDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.formattedDate);
        dest.writeString(this.readableDate);
    }

    public ReleaseDate() {
    }

    protected ReleaseDate(Parcel in) {
        this.formattedDate = in.readString();
        this.readableDate = in.readString();
    }

    public static final Parcelable.Creator<ReleaseDate> CREATOR = new Parcelable.Creator<ReleaseDate>() {
        public ReleaseDate createFromParcel(Parcel source) {
            return new ReleaseDate(source);
        }

        public ReleaseDate[] newArray(int size) {
            return new ReleaseDate[size];
        }
    };
}
