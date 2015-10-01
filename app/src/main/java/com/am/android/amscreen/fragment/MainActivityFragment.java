package com.am.android.amscreen.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.am.android.amscreen.MainActivity;
import com.am.android.amscreen.R;
import com.am.android.amscreen.adapter.PromotionsAdapter;
import com.am.android.amscreen.common.Constants;
import com.am.android.amscreen.listeners.PromotionLoadListener;
import com.am.android.amscreen.model.Promotions;
import com.am.android.amscreen.task.PromotionsAsyncTask;
import com.am.android.amscreen.util.CommonUtil;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements PromotionLoadListener, AdapterView.OnItemClickListener {

    private ListView mPromotionalListView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        mPromotionalListView = (ListView) view.findViewById(R.id.promotional_list);
        mPromotionalListView.setOnItemClickListener(this);
        getActivity().setTitle(getString(R.string.promotions_list_title));

        if (CommonUtil.isNetworkAvailable(getActivity())) {
            loadPromotionsList();
        }

        return view;

    }

    private void loadPromotionsList() {
        PromotionsAsyncTask promotionsAsyncTask = new PromotionsAsyncTask(getActivity());
        promotionsAsyncTask.setListener(this);
        promotionsAsyncTask.execute(Constants.NATIVEAPP_FEEDS_PROMOTIONS_JSON_URL);
    }

    @Override
    public void onPromotionsLoad(List<Promotions> promotionsList) {
        mPromotionalListView.setAdapter(new PromotionsAdapter(getActivity(), promotionsList));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Promotions promotions = (Promotions) mPromotionalListView.getAdapter().getItem(position);
        PromotionDetailsFragment promotionDetailsFragment = new PromotionDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("promotion", promotions);
        promotionDetailsFragment.setArguments(bundle);
        ((MainActivity) getActivity()).addFragment(promotionDetailsFragment, "details");
    }
}
