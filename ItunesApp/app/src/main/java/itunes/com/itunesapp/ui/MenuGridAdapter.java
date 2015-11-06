package itunes.com.itunesapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import itunes.com.itunesapp.R;
import itunes.com.itunesapp.classes.Category;

/**
 * Created by @jvillafane on 2/11/15.
 */
public class MenuGridAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Category> categoriesArrayList;

    public MenuGridAdapter(Context context, ArrayList<Category> categoriesArrayList) {
        this.context = context;
        this.categoriesArrayList = categoriesArrayList;
    }

    @Override
    public int getCount() {
        return categoriesArrayList.size();
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
            convertView = vi.inflate(R.layout.category_gridview_item, null);

            mHolder = new ViewHolder();

            mHolder.categoryIconImage     = (ImageView) convertView.findViewById(R.id.category_item_icon);
            mHolder.categoryTitleTextView = (TextView)  convertView.findViewById(R.id.category_item_textview);

            convertView.setTag(mHolder);

        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        Category category = categoriesArrayList.get(position);

        mHolder.categoryTitleTextView.setText(category.getCategoryLabel());

        return convertView;
    }

    private class ViewHolder {
        ImageView categoryIconImage;
        TextView  categoryTitleTextView;
    }
}
