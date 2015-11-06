package itunes.com.itunesapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import itunes.com.itunesapp.classes.App;
import itunes.com.itunesapp.classes.Artist;
import itunes.com.itunesapp.classes.Category;
import itunes.com.itunesapp.classes.ContentType;
import itunes.com.itunesapp.classes.Price;
import itunes.com.itunesapp.classes.ReleaseDate;
import itunes.com.itunesapp.services.ConnectionChecker;
import itunes.com.itunesapp.ui.MenuGridAdapter;
import itunes.com.itunesapp.ui.MenuListAdapter;
import itunes.com.itunesapp.util.Constants;

public class MenuActivity extends AppCompatActivity {

    private ArrayList<App> appArrayList;
    private ArrayList<Category> categoryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
        setContentView(R.layout.activity_menu);

        //Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getResources().getString(R.string.service_url);

        appArrayList      = new ArrayList<>();
        categoryArrayList = new ArrayList<>();


        if (ConnectionChecker.isNetworkConnected(this)) {
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            appArrayList = getAppsFromJson(response);
                            categoryArrayList = getCategories(appArrayList);
                            int orientation = Constants.getScreenOrientation(MenuActivity.this);
                            loadUiOnOrientation(orientation);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);
        } else {
            String offlineJsonString = loadJSONFromAsset("offline.json");
            appArrayList = getAppsFromJson(offlineJsonString);
            categoryArrayList = getCategories(appArrayList);
            int orientation = Constants.getScreenOrientation(MenuActivity.this);
            loadUiOnOrientation(orientation);
        }
    }

    private ArrayList<App> getAppsFromJson(String jsonResponse){
        try {
            JSONObject responseJson = new JSONObject(jsonResponse);
            JSONObject feedObject   = responseJson.getJSONObject("feed");
            JSONArray  entriesArray = feedObject.getJSONArray("entry");

            //Parse app objects from json feed
            for(int i = 0; i < entriesArray.length(); i++){
                //Get individual entry object from feed
                JSONObject entry = entriesArray.getJSONObject(i);

                //Get App name
                JSONObject entryNameObject = entry.getJSONObject("im:name");
                String entryName = entryNameObject.getString("label");

                //Get images array
                JSONArray imagesJsonArray = entry.getJSONArray("im:image");
                String[] imagesArray = new String[imagesJsonArray.length()];
                for(int j = 0; j < imagesJsonArray.length(); j++){
                    imagesArray[j] = imagesJsonArray.getString(j);
                }

                //Get App summary
                String entrySummary = entry.getString("summary");

                //Get app price object
                JSONObject entryPrice = entry.getJSONObject("im:price");
                JSONObject priceAttributes = entryPrice.getJSONObject("attributes");
                Price price = new Price();
                price.setAmount((float) priceAttributes.getInt("amount"));
                price.setCurrency(priceAttributes.getString("currency"));

                //Get app content type
                JSONObject entryContentType = entry.getJSONObject("im:contentType").getJSONObject("attributes");
                ContentType appContentType = new ContentType();
                appContentType.setTerm(entryContentType.getString("term"));
                appContentType.setLabel(entryContentType.getString("label"));

                //Get app rights
                String entryRights = entry.getString("rights");

                //Get app Title
                String entryTitle  = entry.getString("title");

                //Get app link
                String entryLink   = entry.getString("link");

                //Get app id
                String entryId     = entry.getJSONObject("id").getJSONObject("attributes").getString("im:id");

                //Get app artist
                JSONObject entryArtist = entry.getJSONObject("im:artist");
                String artistName = entryArtist.getString("label");
                String artistUrl  = entryArtist.getJSONObject("attributes").getString("href");
                Artist appArtist  = new Artist();
                appArtist.setArtistName(artistName);
                appArtist.setArtistUrl(artistUrl);

                //Get app category
                JSONObject entryCategory = entry.getJSONObject("category").getJSONObject("attributes");
                Category appCategory = new Category();
                appCategory.setCategoryId(Integer.parseInt(entryCategory.getString("im:id")));
                appCategory.setCategoryTerm(entryCategory.getString("term"));
                appCategory.setCategoryScheme(entryCategory.getString("scheme"));
                appCategory.setCategoryLabel(entryCategory.getString("label"));

                //Get app release date
                JSONObject entryReleaseDate = entry.getJSONObject("im:releaseDate");
                String formattedReleaseDate = entryReleaseDate.getString("label");
                String readableDate         = entryReleaseDate.getJSONObject("attributes").getString("label");
                ReleaseDate appReleaseDate  = new ReleaseDate();
                appReleaseDate.setFormattedDate(formattedReleaseDate);
                appReleaseDate.setReadableDate(readableDate);

                //Instantiate app object
                App app = new App(entryName,
                        imagesArray,
                        entrySummary,
                        price,
                        appContentType,
                        entryRights,
                        entryTitle,
                        entryLink,
                        entryId,
                        appArtist,
                        appCategory,
                        appReleaseDate);

                appArrayList.add(app);
            }

            return appArrayList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void loadUiOnOrientation(int orientation){
        switch (orientation){
            case 1:
                // 1 for Configuration.ORIENTATION_PORTRAIT
                GridView categoryGridView = (GridView) findViewById(R.id.gridView);
                MenuGridAdapter menuGridAdapter = new MenuGridAdapter(MenuActivity.this, categoryArrayList);
                categoryGridView.setAdapter(menuGridAdapter);

                categoryGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        viewSelectedCategoryApps(position);
                    }
                });
                break;
            case 2:
                // 2 for Configuration.ORIENTATION_LANDSCAPE
                ListView categoryListView = (ListView) findViewById(R.id.listView);
                MenuListAdapter menuListAdapter = new MenuListAdapter(MenuActivity.this, categoryArrayList);
                categoryListView.setAdapter(menuListAdapter);

                categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        viewSelectedCategoryApps(position);
                    }
                });
                break;
            default:
                break;
        }
    }

    /**
     * Get categories from App ArrayList.
     * @param apps {@code ArrayList} of {@code App} objects
     * @return {@code ArrayList} of {@code Category} objects
     */
    private ArrayList<Category> getCategories(ArrayList<App> apps){

        ArrayList<Category> categories = new ArrayList<>();

        for(App app: apps){
            Category appCategory = app.getAppCategory();
            int categoryId = appCategory.getCategoryId();

            if(categories.size() == 0){
                categories.add(appCategory);
            }else{
                int i = 0;
                Boolean categoryExists = false;
                while(i < categories.size() && !categoryExists){
                    int catId = categories.get(i).getCategoryId();
                    if(!(categoryId == catId)){
                        i++;
                    }else{
                        categoryExists = true;
                    }
                }
                if (!categoryExists) {
                    categories.add(appCategory);
                }
            }
        }
        return categories;
    }

    private ArrayList<App> getAppsFromSelectedCategory(int selectedCategoryId){
        ArrayList<App> appsOnCategory = new ArrayList<App>();
        for(int i = 0; i < appArrayList.size(); i ++){
            App app = appArrayList.get(i);
            Category appCategory = app.getAppCategory();
            int appCategoryId = appCategory.getCategoryId();
            if(appCategoryId == selectedCategoryId){
                appsOnCategory.add(app);
            }
        }
        return appsOnCategory;
    }

    private void goToAppsListActivity(ArrayList<App> appsFromSelectedCategory){
        Intent goToAppsListIntent = new Intent(MenuActivity.this, CategoryItemsActivity.class);
        goToAppsListIntent.putParcelableArrayListExtra("itunes.com.itunesapp.selectedCategoryApps", appsFromSelectedCategory);
        startActivity(goToAppsListIntent);
    }

    private void viewSelectedCategoryApps(int position){
        Category selectedCategory = categoryArrayList.get(position);
        ArrayList<App> appsOnCategory = getAppsFromSelectedCategory(selectedCategory.getCategoryId());
        goToAppsListActivity(appsOnCategory);
    }

    public String loadJSONFromAsset(String jsonPath) {
        String json = null;
        try {
            InputStream is = this.getAssets().open(jsonPath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
