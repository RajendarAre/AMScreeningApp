package com.am.android.amscreen.listeners;

import com.am.android.amscreen.model.Promotions;

import java.util.List;

/**
 * Created by Rajendar Are on 9/28/15.
 */
public interface PromotionLoadListener {

    void onPromotionsLoad(List<Promotions> promotionsList);
}
