package com.samsung.android.sdk.spage.card;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CardContentManager {
    private static final Uri BASE_CONTENT_URI;
    private static final Uri CARD_CONTENT_CHANGE_NOTIFICATION_URI;
    private static final Uri CARD_INFO_URI;
    private static final Uri CARD_URI;
    private static final Uri CUSTOMIZE_URI;
    private static final Uri INSTANT_URI;
    private static final String TAG = "CardContentManager";
    private static volatile CardContentManager sInstance = null;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CardInfo {
        public int cardId;
        public boolean enabled;
        public SettingState settingState;
        public String templateId;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public enum SettingState {
            NOT_SUPPORTED,
            CARD_ENABLED,
            CARD_DISABLED
        }
    }

    static {
        Uri parse = Uri.parse("content://com.samsung.android.app.spage.provider");
        BASE_CONTENT_URI = parse;
        CARD_INFO_URI = Uri.withAppendedPath(parse, "info");
        CARD_URI = Uri.withAppendedPath(parse, "card");
        INSTANT_URI = Uri.withAppendedPath(parse, "instant");
        CUSTOMIZE_URI = Uri.withAppendedPath(parse, "customize");
        CARD_CONTENT_CHANGE_NOTIFICATION_URI = Uri.withAppendedPath(parse, "change");
    }

    private CardContentManager() {
    }

    private void beforeUpdateContent(CardContent cardContent) {
        ContentValues cardData = cardContent.getCardData();
        if (CardContent.NO_CONTENTS.equals(cardData.get("extraState"))) {
            Iterator it = new ArrayList(cardData.keySet()).iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (str.startsWith("tag_data_")) {
                    String str2 = (String) cardData.get(str);
                    cardData.remove(str);
                    char c5 = 65535;
                    switch (str.hashCode()) {
                        case -59763423:
                            if (str.equals(CardContent.FIELD_1)) {
                                c5 = 0;
                                break;
                            }
                            break;
                        case -59763422:
                            if (str.equals(CardContent.FIELD_2)) {
                                c5 = 1;
                                break;
                            }
                            break;
                        case -59763421:
                            if (str.equals(CardContent.FIELD_3)) {
                                c5 = 2;
                                break;
                            }
                            break;
                        case -59763420:
                            if (str.equals(CardContent.FIELD_4)) {
                                c5 = 3;
                                break;
                            }
                            break;
                    }
                    switch (c5) {
                        case 0:
                            cardData.put("tag_data_no_content_3", str2);
                            break;
                        case 1:
                            cardData.put("tag_data_no_content_4", str2);
                            break;
                        case 2:
                            cardData.put("tag_data_no_content_5", str2);
                            break;
                        case 3:
                            cardData.put("tag_data_no_content_6", str2);
                            break;
                    }
                }
            }
        }
    }

    public static CardContentManager getInstance() {
        if (sInstance == null) {
            synchronized (CardContentManager.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new CardContentManager();
                    }
                    CardContentManager.class.notifyAll();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private boolean isContentProviderEnabled(Context context) {
        ContentProviderClient acquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(BASE_CONTENT_URI);
        if (acquireContentProviderClient == null) {
            Log.d(TAG, "content provider is null");
            return false;
        }
        acquireContentProviderClient.release();
        return true;
    }

    public CardInfo getCardInfo(Context context, int i2) {
        Cursor query;
        boolean z;
        if (context != null) {
            CardInfo cardInfo = new CardInfo();
            cardInfo.cardId = i2;
            if (!isContentProviderEnabled(context) || (query = context.getContentResolver().query(CARD_INFO_URI, (String[]) null, Integer.toString(i2), (String[]) null, (String) null, (CancellationSignal) null)) == null) {
                return cardInfo;
            }
            if (query.moveToFirst()) {
                if (query.getInt(query.getColumnIndex("card_enable")) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                cardInfo.enabled = z;
                cardInfo.templateId = query.getString(query.getColumnIndex("template_id"));
                int columnIndex = query.getColumnIndex("setting_state");
                if (columnIndex == -1) {
                    cardInfo.settingState = CardInfo.SettingState.NOT_SUPPORTED;
                } else if (query.getInt(columnIndex) == 1) {
                    cardInfo.settingState = CardInfo.SettingState.CARD_ENABLED;
                } else {
                    cardInfo.settingState = CardInfo.SettingState.CARD_DISABLED;
                }
            }
            query.close();
            return cardInfo;
        }
        throw new IllegalArgumentException("context is null");
    }

    public void notifyCardContentChange(Context context, int i2) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        } else if (isContentProviderEnabled(context)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("idNo", Integer.toString(i2));
            context.getContentResolver().update(CARD_CONTENT_CHANGE_NOTIFICATION_URI, contentValues, (String) null, (String[]) null);
        }
    }

    public void updateCardContent(Context context, CardContent cardContent) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        } else if (cardContent == null) {
            throw new IllegalArgumentException("card content is null");
        } else if (isContentProviderEnabled(context)) {
            beforeUpdateContent(cardContent);
            context.getContentResolver().update(CARD_URI, cardContent.getCardData(), (String) null, (String[]) null);
        }
    }

    public void updateInstantCardContent(Context context, CardContent cardContent, int i2) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        } else if (cardContent == null) {
            throw new IllegalArgumentException("card content is null");
        } else if (i2 == 0) {
            throw new IllegalArgumentException("update code is 0");
        } else if (isContentProviderEnabled(context)) {
            ContentValues cardData = cardContent.getCardData();
            cardData.put("updateCode", Integer.valueOf(i2));
            context.getContentResolver().update(INSTANT_URI, cardData, (String) null, (String[]) null);
        }
    }

    public void updateMultiInstancePreference(Context context, int i2, MultiInstancePreferenceData multiInstancePreferenceData) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        } else if (multiInstancePreferenceData != null) {
            JSONObject json = multiInstancePreferenceData.toJson();
            if (json == null) {
                throw new IllegalArgumentException("multi instance preference is not valid");
            } else if (isContentProviderEnabled(context)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("idNo", Integer.toString(i2));
                contentValues.put("multiInstancePreferenceData", json.toString());
                context.getContentResolver().update(CUSTOMIZE_URI, contentValues, (String) null, (String[]) null);
            }
        } else {
            throw new IllegalArgumentException("multi instance preference is null");
        }
    }
}
