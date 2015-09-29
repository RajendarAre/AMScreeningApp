package screening.tm.android.com.amscreen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import screening.tm.android.com.amscreen.R;
import screening.tm.android.com.amscreen.model.Promotions;

/**
 * Created by Rajendar Are on 9/28/15.
 */
public class PromotionsAdapter extends BaseAdapter{

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<Promotions> mPromotionsList;

    public PromotionsAdapter(Context context,List<Promotions> promotionsList){
        mPromotionsList = promotionsList;
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }
    @Override
    public int getCount() {
        return mPromotionsList.size();
    }

    @Override
    public Promotions getItem(int position) {
        return mPromotionsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.promotion_row_layout, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.promotion_image);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Promotions promotionItem =  getItem(position);
        holder.title.setText(promotionItem.getTitle());

        Picasso.with(mContext).load(promotionItem.getImage()).into(holder.imageView);


        return convertView;
    }
    static class ViewHolder {
        ImageView imageView;
        TextView title;
    }
}
