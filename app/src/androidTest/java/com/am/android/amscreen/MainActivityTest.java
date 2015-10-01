package com.am.android.amscreen;

import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import com.am.android.amscreen.fragment.MainActivityFragment;
import com.am.android.amscreen.fragment.PromotionDetailsFragment;
import com.am.android.amscreen.fragment.PromotionWebViewFragment;
import com.robotium.solo.Solo;

import junit.framework.Assert;


/**
 * Created by Rajendar Are on 9/29/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    private Fragment startFragment(Fragment fragment, String tag) {
        getActivity().addFragment(fragment, tag);
        getInstrumentation().waitForIdleSync();
        Fragment mainFragment = getActivity().getSupportFragmentManager().findFragmentByTag(tag);
        return mainFragment;
    }

    public void testMainActivity() {
        Assert.assertNotNull(getActivity());
    }

    public void testMainFragment() {
        Fragment mainFragment = new MainActivityFragment();
        Fragment frag = startFragment(mainFragment, "main");
        Assert.assertNotNull(frag);

        final ListView promotionsList = (ListView) frag.getView().findViewById(R.id.promotional_list);
        Assert.assertNotNull(promotionsList);
        solo.clickInList(1);
    }

    public void testPromotionDetailsFragment() {
        Fragment promotionDetailsFragment = new PromotionDetailsFragment();
        Fragment frag = startFragment(promotionDetailsFragment, "details");
        Assert.assertNotNull(frag);

        solo.clickOnButton(0);
    }

    public void testPromotionWebPage() {
        Fragment promotionWebViewFragment = new PromotionWebViewFragment();
        Fragment frag = startFragment(promotionWebViewFragment, "webview");
        Assert.assertNotNull(frag);
    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
        try {
            solo.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        super.tearDown();
    }
}
