package itunes.com.itunesapp.classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class represents an app downloaded from the App Store
 * Created by @jvillafane on 2/11/15.
 */
public class App implements Parcelable {
    private String appName;
    private String appImgUrl[];
    private String appSummary;

    private Price  appPrice;

    private ContentType appContentType;

    private String appRights;
    private String appTitle;
    private String appLink;
    private String appId;

    private Artist appArtist;

    private Category appCategory;
    private ReleaseDate appReleaseDate;

    /**
     * Default empty constructor
     */
    public App() {
    }

    public App(String appName,
               String[] appImgUrl,
               String appSummary,
               Price appPrice,
               ContentType appContentType,
               String appRights,
               String appTitle,
               String appLink,
               String appId,
               Artist appArtist,
               Category appCategory,
               ReleaseDate appReleaseDate) {

        this.appName = appName;
        this.appImgUrl = appImgUrl;
        this.appSummary = appSummary;
        this.appPrice = appPrice;
        this.appContentType = appContentType;
        this.appRights = appRights;
        this.appTitle = appTitle;
        this.appLink = appLink;
        this.appId = appId;
        this.appArtist = appArtist;
        this.appCategory = appCategory;
        this.appReleaseDate = appReleaseDate;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String[] getAppImgUrl() {
        return appImgUrl;
    }

    public void setAppImgUrl(String[] appImgUrl) {
        this.appImgUrl = appImgUrl;
    }

    public String getAppSummary() {
        return appSummary;
    }

    public void setAppSummary(String appSummary) {
        this.appSummary = appSummary;
    }

    public Price getAppPrice() {
        return appPrice;
    }

    public void setAppPrice(Price appPrice) {
        this.appPrice = appPrice;
    }

    public ContentType getAppContentType() {
        return appContentType;
    }

    public void setAppContentType(ContentType appContentType) {
        this.appContentType = appContentType;
    }

    public String getAppRights() {
        return appRights;
    }

    public void setAppRights(String appRights) {
        this.appRights = appRights;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getAppLink() {
        return appLink;
    }

    public void setAppLink(String appLink) {
        this.appLink = appLink;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Artist getAppArtist() {
        return appArtist;
    }

    public void setAppArtist(Artist appArtist) {
        this.appArtist = appArtist;
    }

    public Category getAppCategory() {
        return appCategory;
    }

    public void setAppCategory(Category appCategory) {
        this.appCategory = appCategory;
    }

    public ReleaseDate getAppReleaseDate() {
        return appReleaseDate;
    }

    public void setAppReleaseDate(ReleaseDate appReleaseDate) {
        this.appReleaseDate = appReleaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.appName);
        dest.writeStringArray(this.appImgUrl);
        dest.writeString(this.appSummary);
        dest.writeParcelable(this.appPrice, flags);
        dest.writeParcelable(this.appContentType, flags);
        dest.writeString(this.appRights);
        dest.writeString(this.appTitle);
        dest.writeString(this.appLink);
        dest.writeString(this.appId);
        dest.writeParcelable(this.appArtist, flags);
        dest.writeParcelable(this.appCategory, flags);
        dest.writeParcelable(this.appReleaseDate, flags);
    }

    protected App(Parcel in) {
        this.appName = in.readString();
        this.appImgUrl = in.createStringArray();
        this.appSummary = in.readString();
        this.appPrice = in.readParcelable(Price.class.getClassLoader());
        this.appContentType = in.readParcelable(ContentType.class.getClassLoader());
        this.appRights = in.readString();
        this.appTitle = in.readString();
        this.appLink = in.readString();
        this.appId = in.readString();
        this.appArtist = in.readParcelable(Artist.class.getClassLoader());
        this.appCategory = in.readParcelable(Category.class.getClassLoader());
        this.appReleaseDate = in.readParcelable(ReleaseDate.class.getClassLoader());
    }

    public static final Parcelable.Creator<App> CREATOR = new Parcelable.Creator<App>() {
        public App createFromParcel(Parcel source) {
            return new App(source);
        }

        public App[] newArray(int size) {
            return new App[size];
        }
    };
}
