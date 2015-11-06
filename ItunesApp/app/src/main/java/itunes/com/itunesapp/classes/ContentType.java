package itunes.com.itunesapp.classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The item's content type (mostly apps)
 * Created by @jvillafane on 2/11/15.
 */
public class ContentType implements Parcelable {
    private String term;
    private String label;

    public ContentType() {
    }

    public ContentType(String term, String label) {
        this.term = term;
        this.label = label;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.term);
        dest.writeString(this.label);
    }

    protected ContentType(Parcel in) {
        this.term = in.readString();
        this.label = in.readString();
    }

    public static final Parcelable.Creator<ContentType> CREATOR = new Parcelable.Creator<ContentType>() {
        public ContentType createFromParcel(Parcel source) {
            return new ContentType(source);
        }

        public ContentType[] newArray(int size) {
            return new ContentType[size];
        }
    };
}
