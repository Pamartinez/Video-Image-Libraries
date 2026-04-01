package com.samsung.android.sdk.spage.card;

import android.content.ContentValues;
import android.text.TextUtils;
import com.samsung.android.sdk.spage.card.base.FieldData;
import com.samsung.android.sdk.spage.card.base.JsonFieldData;
import com.samsung.android.sdk.spage.card.base.Manipulator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CardContent {
    public static final String AUTHORIZATION = "AUTHORIZATION";
    public static final String FIELD_1 = "tag_data_1";
    public static final String FIELD_10 = "tag_data_10";
    public static final String FIELD_11 = "tag_data_11";
    public static final String FIELD_12 = "tag_data_12";
    public static final String FIELD_13 = "tag_data_13";
    public static final String FIELD_14 = "tag_data_14";
    public static final String FIELD_15 = "tag_data_15";
    public static final String FIELD_16 = "tag_data_16";
    public static final String FIELD_17 = "tag_data_17";
    public static final String FIELD_18 = "tag_data_18";
    public static final String FIELD_19 = "tag_data_19";
    public static final String FIELD_2 = "tag_data_2";
    public static final String FIELD_20 = "tag_data_20";
    public static final String FIELD_21 = "tag_data_21";
    public static final String FIELD_22 = "tag_data_22";
    public static final String FIELD_23 = "tag_data_23";
    public static final String FIELD_24 = "tag_data_24";
    public static final String FIELD_25 = "tag_data_25";
    public static final String FIELD_26 = "tag_data_26";
    public static final String FIELD_27 = "tag_data_27";
    public static final String FIELD_28 = "tag_data_28";
    public static final String FIELD_29 = "tag_data_29";
    public static final String FIELD_3 = "tag_data_3";
    public static final String FIELD_30 = "tag_data_30";
    public static final String FIELD_31 = "tag_data_31";
    public static final String FIELD_32 = "tag_data_32";
    public static final String FIELD_33 = "tag_data_33";
    public static final String FIELD_34 = "tag_data_34";
    public static final String FIELD_35 = "tag_data_35";
    public static final String FIELD_36 = "tag_data_36";
    public static final String FIELD_37 = "tag_data_37";
    public static final String FIELD_38 = "tag_data_38";
    public static final String FIELD_39 = "tag_data_39";
    public static final String FIELD_4 = "tag_data_4";
    public static final String FIELD_40 = "tag_data_40";
    public static final String FIELD_41 = "tag_data_41";
    public static final String FIELD_42 = "tag_data_42";
    public static final String FIELD_43 = "tag_data_43";
    public static final String FIELD_44 = "tag_data_44";
    public static final String FIELD_45 = "tag_data_45";
    public static final String FIELD_46 = "tag_data_46";
    public static final String FIELD_47 = "tag_data_47";
    public static final String FIELD_48 = "tag_data_48";
    public static final String FIELD_49 = "tag_data_49";
    public static final String FIELD_5 = "tag_data_5";
    public static final String FIELD_50 = "tag_data_50";
    public static final String FIELD_51 = "tag_data_51";
    public static final String FIELD_52 = "tag_data_52";
    public static final String FIELD_53 = "tag_data_53";
    public static final String FIELD_54 = "tag_data_54";
    public static final String FIELD_55 = "tag_data_55";
    public static final String FIELD_56 = "tag_data_56";
    public static final String FIELD_57 = "tag_data_57";
    public static final String FIELD_58 = "tag_data_58";
    public static final String FIELD_59 = "tag_data_59";
    public static final String FIELD_6 = "tag_data_6";
    public static final String FIELD_60 = "tag_data_60";
    public static final String FIELD_7 = "tag_data_7";
    public static final String FIELD_8 = "tag_data_8";
    public static final String FIELD_9 = "tag_data_9";
    private static final String FIELD_OPTION = "FIELD_OPTION";
    public static final String NORMAL = "NORMAL";
    public static final String NO_CONTENTS = "NO_CONTENTS";
    public static final String ON_BOARDING = "ON_BOARDING";
    public static final String SIGN_IN = "SIGN_IN";
    private static final String TAG = "CardContent";
    private final ContentValues mCardData;
    private final int mCardId;
    private String mExtraState = NORMAL;
    private String mTemplateId;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface EXTRA_STATE {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface FIELD {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FieldPropertyPutter extends Manipulator<FieldPropertyPutter> {
        public FieldPropertyPutter(JsonFieldData jsonFieldData) {
            super(jsonFieldData);
        }

        public void clearFieldOption() {
            remove(CardContent.FIELD_OPTION);
        }

        public void setFieldOption(int i2) {
            if (i2 > 0) {
                put(CardContent.FIELD_OPTION, i2);
            }
        }
    }

    public CardContent(int i2) {
        ContentValues contentValues = new ContentValues();
        this.mCardData = contentValues;
        this.mCardId = i2;
        contentValues.put("idNo", Integer.toString(i2));
    }

    public ContentValues getCardData() {
        return this.mCardData;
    }

    public int getId() {
        return this.mCardId;
    }

    public void put(String str, FieldData fieldData) {
        if (fieldData == null) {
            throw new IllegalArgumentException("FieldData is null");
        } else if (str != null) {
            this.mCardData.put(str, fieldData.getData());
        } else {
            throw new IllegalArgumentException("Key is null");
        }
    }

    public void putExtra(String str, String str2) {
        if (str2 == null) {
            throw new IllegalArgumentException("Value is null");
        } else if (str != null) {
            put(str, (FieldData) new TextData().setText(str2));
        } else {
            throw new IllegalArgumentException("Key is null");
        }
    }

    public void setDescription(String str) {
        this.mCardData.put("description", str);
    }

    public void setDuration(long j2) {
        this.mCardData.put("validDuration", Long.toString(j2));
    }

    public void setExtraState(String str) {
        if (TextUtils.isEmpty(this.mTemplateId) || NORMAL.equals(str)) {
            this.mExtraState = str;
            this.mCardData.put("extraState", str);
            return;
        }
        throw new IllegalStateException("if template id already set, extra state should be NORMAL or not set.");
    }

    public void setShareMimeType(String str) {
        this.mCardData.put("shareMimeType", str);
    }

    public void setTag(String str) {
        this.mCardData.put("contentTag", str);
    }

    public void setTemplateIdForMultiTemplate(String str) {
        if (NORMAL.equals(this.mExtraState)) {
            this.mTemplateId = str;
            this.mCardData.put("templateId", str);
            return;
        }
        throw new IllegalStateException("if extra state is NOT NORMAL, template id cannot be set.");
    }

    public void put(int i2, FieldData fieldData) {
        put("tag_data_" + i2, fieldData);
    }

    public void put(String str, int i2, JsonFieldData jsonFieldData) {
        if (jsonFieldData == null) {
            throw new IllegalArgumentException("FieldData is null");
        } else if (str != null) {
            FieldPropertyPutter fieldPropertyPutter = new FieldPropertyPutter(jsonFieldData);
            fieldPropertyPutter.setFieldOption(i2);
            this.mCardData.put(str, fieldPropertyPutter.getData());
            fieldPropertyPutter.clearFieldOption();
        } else {
            throw new IllegalArgumentException("Key is null");
        }
    }

    public void put(int i2, int i7, JsonFieldData jsonFieldData) {
        put("tag_data_" + i2, i7, jsonFieldData);
    }
}
