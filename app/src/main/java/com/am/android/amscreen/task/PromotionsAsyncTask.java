package com.am.android.amscreen.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.am.android.amscreen.R;
import com.am.android.amscreen.listeners.PromotionLoadListener;
import com.am.android.amscreen.model.Button;
import com.am.android.amscreen.model.Promotions;
import com.am.android.amscreen.util.CommonUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajendar Are on 9/28/15.
 */
public class PromotionsAsyncTask extends AsyncTask<String, Void, List<Promotions>> {

    private final Context mContext;
    private PromotionLoadListener mPromotionLoadListener;

    public PromotionsAsyncTask(Context context) {
        mContext = context;
    }

    public void setListener(PromotionLoadListener promotionLoadListener) {
        mPromotionLoadListener = promotionLoadListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        CommonUtil.startProgressDialog(mContext, R.string.please_wait);
    }

    @Override
    protected List<Promotions> doInBackground(String... params) {
        String urlString = params[0];
        URL url;
        HttpURLConnection urlConnection = null;
        JSONArray response = new JSONArray();
        List<Promotions> promotionsList = null;
        try {
            url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String responseString = readStream(urlConnection.getInputStream());
                promotionsList = parseJson(responseString);
//                PromotionPojo promotionPojo = new Gson().fromJson(responseString, PromotionPojo.class);
            } else {
                Log.v("CatalogClient", "Response code:" + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return promotionsList;
    }

    private List<Promotions> parseJson(String responseString) throws JSONException {
        List<Promotions> promotionsList = new ArrayList<Promotions>();
        JSONObject jsonObject = new JSONObject(responseString);
        JSONArray promotionArray = jsonObject.getJSONArray("promotions");
        for (int i = 0; i < promotionArray.length(); i++) {
            Promotions promotions = new Promotions();
            JSONObject row = promotionArray.getJSONObject(i);
            if (row.get("button") instanceof JSONObject) {
                JSONObject rowJSONObject = row.getJSONObject("button");
                Button buttonModel = new Gson().fromJson(rowJSONObject.toString(), Button.class);
                promotions.setButtonPojo(buttonModel);
            } else if (row.get("button") instanceof JSONArray) {
                JSONArray rowJSONArray = row.getJSONArray("button");
                Button[] buttonPojoArray = new Gson().fromJson(rowJSONArray.toString(), Button[].class);
                promotions.setButtonPojoArray(buttonPojoArray);
            }
            final String description = row.getString("description");
            if (row.has("footer")) {
                final String footer = row.getString("footer");
                promotions.setFooter(footer);
            }
            if (row.has("image")) {
                final String image = row.getString("image");
                promotions.setImage(image);
            }
            final String title = row.getString("title");

            promotions.setTitle(title);

            promotions.setDescription(description);

            promotionsList.add(promotions);

        }

        return promotionsList;
    }

    @Override
    protected void onPostExecute(List<Promotions> promotionsList) {
        CommonUtil.stopProgressDialog();
        if (null != promotionsList && null != mPromotionLoadListener) {
            mPromotionLoadListener.onPromotionsLoad(promotionsList);
        }
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}
