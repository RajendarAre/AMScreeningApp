package screening.tm.android.com.amscreeningapp.listeners;

import java.util.List;

import screening.tm.android.com.amscreeningapp.model.Promotions;

/**
 * Created by vivekkumar on 9/28/15.
 */
public interface PromotionLoadListener {

    void onPromotionsLoad(List<Promotions> promotionsList);
}
