package com.samsung.android.sdk.spage.card;

import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.FourWEventBundleWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MultiInstancePreferenceData {
    private static final String TAG = "MiPreferenceData";
    private ArrayList<Category> mCategoryList = new ArrayList<>();
    private String mTitle;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Card {
        /* access modifiers changed from: private */
        public int mId;
        /* access modifiers changed from: private */
        public String mTitle;

        public int getId() {
            return this.mId;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public Card setId(int i2) {
            this.mId = i2;
            return this;
        }

        public Card setTitle(String str) {
            this.mTitle = str;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Category {
        /* access modifiers changed from: private */
        public ArrayList<Card> mCardList = new ArrayList<>();
        /* access modifiers changed from: private */
        public int mId;
        /* access modifiers changed from: private */
        public String mTitle;

        public Category addCard(Card card) {
            if (card != null) {
                this.mCardList.add(card);
                return this;
            }
            throw new IllegalArgumentException("card is null");
        }

        public Category addCards(Collection<Card> collection) {
            if (collection == null || collection.contains((Object) null)) {
                throw new IllegalArgumentException("invalid cards");
            }
            this.mCardList.addAll(collection);
            return this;
        }

        public ArrayList<Card> getCardList() {
            return this.mCardList;
        }

        public int getId() {
            return this.mId;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public Category setId(int i2) {
            this.mId = i2;
            return this;
        }

        public Category setTitle(String str) {
            this.mTitle = str;
            return this;
        }
    }

    private JSONObject newJsonObject(int i2, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", i2);
        jSONObject.put("title", str);
        return jSONObject;
    }

    public MultiInstancePreferenceData addCategories(Collection<Category> collection) {
        if (collection == null || collection.contains((Object) null)) {
            throw new IllegalArgumentException("invalid categories");
        }
        this.mCategoryList.addAll(collection);
        return this;
    }

    public MultiInstancePreferenceData addCategory(Category category) {
        if (category != null) {
            this.mCategoryList.add(category);
            return this;
        }
        throw new IllegalArgumentException("category is null");
    }

    public ArrayList<Category> getCategoryList() {
        return this.mCategoryList;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public MultiInstancePreferenceData setTitle(String str) {
        this.mTitle = str;
        return this;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            if (!TextUtils.isEmpty(this.mTitle)) {
                jSONObject.put("title", this.mTitle);
            }
            Iterator<Category> it = this.mCategoryList.iterator();
            while (it.hasNext()) {
                Category next = it.next();
                if (next.mCardList.isEmpty()) {
                    Log.e(TAG, next.mTitle + " category has no cards, so skip it.");
                } else {
                    JSONArray jSONArray2 = new JSONArray();
                    Iterator it2 = next.mCardList.iterator();
                    while (it2.hasNext()) {
                        Card card = (Card) it2.next();
                        jSONArray2.put(newJsonObject(card.mId, card.mTitle));
                    }
                    JSONObject newJsonObject = newJsonObject(next.mId, next.mTitle);
                    newJsonObject.put("cardList", jSONArray2);
                    jSONArray.put(newJsonObject);
                }
            }
            jSONObject.put(FourWEventBundleWrapper.BUNDLE_KEY_CATEGORIES, jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "exception occurs when MultiInstancePreferenceData.toJson()", e);
            return null;
        }
    }
}
