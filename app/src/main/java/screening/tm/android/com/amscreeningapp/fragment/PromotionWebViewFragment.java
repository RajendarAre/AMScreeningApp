package screening.tm.android.com.amscreeningapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import screening.tm.android.com.amscreeningapp.R;

/**
 * Created by vivekkumar on 9/28/15.
 */
public class PromotionWebViewFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.promotion_web_layout, container, false);
        String url = getArguments().getString("Url");
        WebView webView = (WebView)view.findViewById(R.id.promotion_webview);
        webView.loadUrl(url);
        return view;
    }
}
