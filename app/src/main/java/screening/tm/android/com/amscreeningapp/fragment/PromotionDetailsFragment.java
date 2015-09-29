package screening.tm.android.com.amscreeningapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import screening.tm.android.com.amscreeningapp.MainActivity;
import screening.tm.android.com.amscreeningapp.R;
import screening.tm.android.com.amscreeningapp.model.ButtonPojo;
import screening.tm.android.com.amscreeningapp.model.Promotions;

/**
 * Created by Rajendar Are on 9/28/15.
 */
public class PromotionDetailsFragment extends Fragment implements View.OnClickListener {

    private Promotions mPromotions;
    private Button mPromotionButton;
    private ButtonPojo mButtonPojo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_promotion_details, container, false);

        mPromotions = (Promotions) getArguments().getSerializable("promotion");
        mPromotionButton = (Button) view.findViewById(R.id.promotion_button);
        mPromotionButton.setOnClickListener(this);

        if (null != mPromotions) {
            ImageView imageView = (ImageView) view.findViewById(R.id.featuredImg);
            Picasso.with(getActivity()).load(mPromotions.getImage()).into(imageView);

            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(mPromotions.getTitle());

            TextView htmlTextView = (TextView) view.findViewById(R.id.description);
            htmlTextView.setText(mPromotions.getDescription());

            Button promotionButton = (Button) view.findViewById(R.id.promotion_button);
            mButtonPojo = mPromotions.getButtonPojo();
            if (null == mButtonPojo) {
                ButtonPojo[] buttonPojoArray = mPromotions.getButtonPojoArray();
                if (null != buttonPojoArray) {
                    mButtonPojo = buttonPojoArray[0];
                }
            }

            String buttonTitle = mButtonPojo.getTitle();
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
            String target = mButtonPojo.getTarget();
            bundle.putString("Url", target);
            promotionWebViewFragment.setArguments(bundle);
            ((MainActivity) getActivity()).addFragment(promotionWebViewFragment, "webview");
        }
    }
}
