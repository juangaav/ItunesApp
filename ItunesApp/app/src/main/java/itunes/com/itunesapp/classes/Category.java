package itunes.com.itunesapp.classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Contains infomation about the app's category
 * Created by @jvillafane on 2/11/15.
 */
public class Category implements Parcelable {
    private int    categoryId;
    private String categoryTerm;
    private String categoryLabel;
    private String categoryScheme;

    public Category() {
    }

    public Category(int categoryId, String categoryTerm, String categoryLabel, String categoryScheme) {
        this.categoryId = categoryId;
        this.categoryTerm = categoryTerm;
        this.categoryLabel = categoryLabel;
        this.categoryScheme = categoryScheme;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTerm() {
        return categoryTerm;
    }

    public void setCategoryTerm(String categoryTerm) {
        this.categoryTerm = categoryTerm;
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public String getCategoryScheme() {
        return categoryScheme;
    }

    public void setCategoryScheme(String categoryScheme) {
        this.categoryScheme = categoryScheme;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.categoryId);
        dest.writeString(this.categoryTerm);
        dest.writeString(this.categoryLabel);
        dest.writeString(this.categoryScheme);
    }

    protected Category(Parcel in) {
        this.categoryId = in.readInt();
        this.categoryTerm = in.readString();
        this.categoryLabel = in.readString();
        this.categoryScheme = in.readString();
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
