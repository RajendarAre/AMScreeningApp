package com.am.android.amscreen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.am.android.amscreen.MainActivity;
import com.am.android.amscreen.R;
import com.am.android.amscreen.common.Constants;
import com.am.android.amscreen.model.Button;
import com.am.android.amscreen.model.Promotions;
import com.squareup.picasso.Picasso;


/**
 * Created by Rajendar Are on 9/28/15.
 */
public class PromotionDetailsFragment extends Fragment implements View.OnClickListener {

    private Promotions mPromotions;
    private android.widget.Button mPromotionButton;
    private Button mButtonModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_promotion_details, container, false);

        getActivity().setTitle("Promotion Details");

        mPromotions = (Promotions) getArguments().getSerializable("promotion");
        mPromotionButton = (android.widget.Button) view.findViewById(R.id.promotion_button);
        mPromotionButton.setOnClickListener(this);

        if (null != mPromotions) {
            ImageView imageView = (ImageView) view.findViewById(R.id.featuredImg);
            Picasso.with(getActivity()).load(mPromotions.getImage()).into(imageView);

            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(mPromotions.getTitle());

            TextView htmlTextView = (TextView) view.findViewById(R.id.description);
            htmlTextView.setText(mPromotions.getDescription());

            android.widget.Button promotionButton = (android.widget.Button) view.findViewById(R.id.promotion_button);
            mButtonModel = mPromotions.getButtonPojo();
            if (null == mButtonModel) {
                Button[] buttonPojoArray = mPromotions.getButtonPojoArray();
                if (null != buttonPojoArray) {
                    mButtonModel = buttonPojoArray[0];
                }
            }

            String buttonTitle = mButtonModel.getTitle();
            promotionButton.setText(buttonTitle);

            if (null != mPromotions.getFooter()) {

                TextView footerView = (TextView) view.findViewById(R.id.footer);
                footerView.setText(Html.fromHtml(mPromotions.getFooter(), null, null));
            }
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.promotion_button) {
            PromotionWebViewFragment promotionWebViewFragment = new PromotionWebViewFragment();
            Bundle bundle = new Bundle();
            String target = mButtonModel.getTarget();
            bundle.putString(Constants.URL, target);
            promotionWebViewFragment.setArguments(bundle);
            ((MainActivity) getActivity()).addFragment(promotionWebViewFragment, Constants.WEBVIEW);
        }
    }
}
