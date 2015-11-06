package itunes.com.itunesapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import itunes.com.itunesapp.R;
import itunes.com.itunesapp.classes.App;
import itunes.com.itunesapp.classes.Category;

/**
 * Created by @jvillafane on 4/11/15.
 */
public class SelectedCategoryAppsGridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<App> selectedCategoryApps;

    public SelectedCategoryAppsGridAdapter(Context context, ArrayList<App> selectedCategoryApps) {
        this.context = context;
        this.selectedCategoryApps = selectedCategoryApps;
    }

    @Override
    public int getCount() {
        return selectedCategoryApps.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder mHolder;

        if (convertView == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            convertView = vi.inflate(R.layout.apps_list_gridview_item, null);

            mHolder = new ViewHolder();

            mHolder.appIconImage        = (ImageView) convertView.findViewById(R.id.apps_item_icon);
            mHolder.appTitleTextView    = (TextView)  convertView.findViewById(R.id.apps_item_textview);

            convertView.setTag(mHolder);

        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        App appItem = selectedCategoryApps.get(position);

        mHolder.appTitleTextView.setText(appItem.getAppName());

        JSONObject imageURL = null;
        try {
            imageURL = new JSONObject(appItem.getAppImgUrl()[1]);
            String url = imageURL.getString("label");
            Picasso.with(context).load(url).into(mHolder.appIconImage);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return convertView;
    }

    private class ViewHolder {
        ImageView appIconImage;
        TextView  appTitleTextView;
    }

}
