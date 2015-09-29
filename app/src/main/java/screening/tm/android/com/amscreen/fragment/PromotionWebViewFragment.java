package screening.tm.android.com.amscreen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import screening.tm.android.com.amscreen.R;


/**
 * Created by Rajendar Are on 9/28/15.
 */
public class PromotionWebViewFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.promotion_web_layout, container, false);
        getActivity().setTitle("Promotion View");
        String url = getArguments().getString("Url");
        WebView webView = (WebView)view.findViewById(R.id.promotion_webview);
        webView.loadUrl(url);
        return view;
    }
}
