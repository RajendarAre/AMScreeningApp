package com.am.android.amscreen.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.am.android.amscreen.R;
import com.am.android.amscreen.common.Constants;
import com.am.android.amscreen.util.CommonUtil;


/**
 * Created by Rajendar Are on 9/28/15.
 */
public class PromotionWebViewFragment extends Fragment {

    private WebView mWebView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.promotion_web_layout, container, false);
        getActivity().setTitle(getString(R.string.promotion_view_title));
        String url = getArguments().getString(Constants.URL);
        mWebView = (WebView) view.findViewById(R.id.promotion_webview);
        //don't show view
        mWebView.setVisibility(View.GONE);
        final WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        mWebView.setWebViewClient(new PromotionWebViewClient());
        mWebView.loadUrl(url);
        return view;
    }

    class PromotionWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            CommonUtil.startProgressDialog(getActivity(), getString(R.string.loading));
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            view.loadUrl("javascript:" +
                    "$('.global-navigation-bar').hide();");
            //show view once navigation bar removed from web page.
            mWebView.setVisibility(View.VISIBLE);
            CommonUtil.stopProgressDialog();
        }
    }
}
