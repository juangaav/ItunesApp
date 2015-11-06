package itunes.com.itunesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import itunes.com.itunesapp.classes.App;
import itunes.com.itunesapp.ui.MenuGridAdapter;
import itunes.com.itunesapp.ui.MenuListAdapter;
import itunes.com.itunesapp.ui.SelectedCategoryAppsGridAdapter;
import itunes.com.itunesapp.ui.SelectedCategoryAppsListAdapter;
import itunes.com.itunesapp.util.Constants;

public class CategoryItemsActivity extends AppCompatActivity {

    private final String ARRAYLIST_EXTRAS_NAME = "itunes.com.itunesapp.selectedCategoryApps";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
        setContentView(R.layout.activity_category_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ArrayList<App> receivedAppsArrayList = getIntent().getExtras().getParcelableArrayList(ARRAYLIST_EXTRAS_NAME);

        int orientation = Constants.getScreenOrientation(CategoryItemsActivity.this);
        switch (orientation){
            case 1:
                // 1 for Configuration.ORIENTATION_PORTRAIT
                GridView appsGridView = (GridView) findViewById(R.id.appsGridView);
                SelectedCategoryAppsGridAdapter selectedCategoryAppsGridAdapter = new SelectedCategoryAppsGridAdapter(CategoryItemsActivity.this, receivedAppsArrayList);
                appsGridView.setAdapter(selectedCategoryAppsGridAdapter);

                appsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        App selectedApp = receivedAppsArrayList.get(position);
                        goToAppDetail(selectedApp);
                    }
                });
                break;
            case 2:
                // 2 for Configuration.ORIENTATION_LANDSCAPE
                ListView categoryListView = (ListView) findViewById(R.id.appsListView);
                SelectedCategoryAppsListAdapter selectedCategoryAppsListAdapter = new SelectedCategoryAppsListAdapter(CategoryItemsActivity.this, receivedAppsArrayList);
                categoryListView.setAdapter(selectedCategoryAppsListAdapter);

                categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        App selectedApp = receivedAppsArrayList.get(position);
                        goToAppDetail(selectedApp);
                    }
                });
                break;
            default:
                break;
        }
    }

    private void goToAppDetail(App selectedApp){
        Intent appDetailIntent = new Intent(CategoryItemsActivity.this, AppDetailActivity.class);
        appDetailIntent.putExtra("iTunesApp.SelectedApp", selectedApp);
        startActivity(appDetailIntent);
    }

}
