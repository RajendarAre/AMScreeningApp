package screening.tm.android.com.amscreeningapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import screening.tm.android.com.amscreeningapp.MainActivity;
import screening.tm.android.com.amscreeningapp.R;
import screening.tm.android.com.amscreeningapp.adapter.PromotionsAdapter;
import screening.tm.android.com.amscreeningapp.listeners.PromotionLoadListener;
import screening.tm.android.com.amscreeningapp.model.Promotions;
import screening.tm.android.com.amscreeningapp.task.PromotionsAsyncTask;

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

        loadPromotionsList();

        return view;

    }

    private void loadPromotionsList() {
        PromotionsAsyncTask promotionsAsyncTask = new PromotionsAsyncTask(getActivity());
        promotionsAsyncTask.setListener(this);
        promotionsAsyncTask.execute("http://www.abercrombie.com/anf/nativeapp/Feeds/promotions.json");
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
