package itunes.com.itunesapp;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import itunes.com.itunesapp.classes.App;

public class AppDetailActivity extends AppCompatActivity {

    private final String SELECTED_APP = "iTunesApp.SelectedApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
        setContentView(R.layout.activity_app_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        App selectedApp = getIntent().getExtras().getParcelable(SELECTED_APP);

        ImageView appIconImageView     = (ImageView) findViewById(R.id.app_detail_icon_image);
        TextView  appNameTextView      = (TextView)  findViewById(R.id.app_name_textview);
        TextView  releaseDateTextView  = (TextView)  findViewById(R.id.app_release_date_textview);
        TextView  summaryTextView      = (TextView)  findViewById(R.id.app_summary_textview);

        JSONObject imageURL = null;

        try {
            imageURL = new JSONObject(selectedApp.getAppImgUrl()[2]);
            String url = imageURL.getString("label");
            Picasso.with(this).load(url).into(appIconImageView);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        appNameTextView.setText(selectedApp.getAppName());
        releaseDateTextView.setText(selectedApp.getAppReleaseDate().getReadableDate());

        try {
            JSONObject summaryObject = new JSONObject(selectedApp.getAppSummary());
            summaryTextView.setText(summaryObject.getString("label"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
