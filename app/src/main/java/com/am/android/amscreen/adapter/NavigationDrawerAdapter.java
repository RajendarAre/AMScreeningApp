package com.am.android.amscreen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.am.android.amscreen.R;
import com.am.android.amscreen.model.NavDrawerItem;

import java.util.Collections;
import java.util.List;


/**
 * Created by Rajendar Are on 9/18/15.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavDrawerItem> mNavigationList = Collections.emptyList();
    private LayoutInflater mInflater;
    private Context mContext;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mNavigationList = data;
    }

    public void delete(int position) {
        mNavigationList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current = mNavigationList.get(position);
        holder.title.setText(current.getTitle());
    }

    @Override
    public int getItemCount() {
        return mNavigationList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
