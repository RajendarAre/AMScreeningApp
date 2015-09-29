package screening.tm.android.com.amscreeningapp;

import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;

import junit.framework.Assert;

import screening.tm.android.com.amscreeningapp.fragment.MainActivityFragment;

/**
 * Created by Rajendar on 9/29/15.
 */
public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityTests() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }

    private Fragment startFragment(Fragment fragment, String tag) {
        getActivity().addFragment(fragment, tag);
        Fragment mainFragment = getActivity().getSupportFragmentManager().findFragmentByTag(tag);
        return mainFragment;
    }

    public void testMainActivity() {
        Assert.assertNotNull(getActivity());
    }

    public void testMainFragment() {
        Fragment mainFragment = new MainActivityFragment();
        Fragment frag = startFragment(mainFragment, "main");
        final ListView promotionsList = (ListView) frag.getView().findViewById(R.id.promotional_list);

        final View child0 = promotionsList.getChildAt(0);
        final long itemId = promotionsList.getAdapter().getItemId(0);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                promotionsList.performItemClick(child0, 0, itemId);
            }
        });
    }
}
