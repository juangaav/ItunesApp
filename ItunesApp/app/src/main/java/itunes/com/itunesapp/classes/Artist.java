package itunes.com.itunesapp.classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class stores the app's author information
 * Created by @jvillafane on 2/11/15.
 */
public class Artist implements Parcelable {
    private String artistName;
    private String artistUrl;

    public Artist() {
    }

    public Artist(String artistName, String artistUrl) {
        this.artistName = artistName;
        this.artistUrl = artistUrl;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistUrl() {
        return artistUrl;
    }

    public void setArtistUrl(String artistUrl) {
        this.artistUrl = artistUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.artistName);
        dest.writeString(this.artistUrl);
    }

    protected Artist(Parcel in) {
        this.artistName = in.readString();
        this.artistUrl = in.readString();
    }

    public static final Parcelable.Creator<Artist> CREATOR = new Parcelable.Creator<Artist>() {
        public Artist createFromParcel(Parcel source) {
            return new Artist(source);
        }

        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };
}
