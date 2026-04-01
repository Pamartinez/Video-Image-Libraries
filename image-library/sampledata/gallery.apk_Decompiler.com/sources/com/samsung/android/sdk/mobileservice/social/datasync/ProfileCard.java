package com.samsung.android.sdk.mobileservice.social.datasync;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.sdk.mobileservice.social.datasync.provider.DataSyncContract;
import com.samsung.android.sdk.mobileservice.util.SdkLog;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProfileCard implements IData {
    public static final String SYNC_SERVICE_NAME = "rMnFaz1GB6";
    private final String EXTRA_JSON_STRING;
    private final String JSON_KEY_BG_FILE_URI;
    private final String JSON_KEY_CUSTOM_BG_HASH;
    private final String JSON_KEY_CUSTOM_TIMESTAMP;
    private Uri mBgFileUri;
    private String mCustomBgHash;
    private long mCustomTimestamp;
    private MetaData mMeta;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MetaData {
        private static final String JSON_KEY_BG_SOURCE = "bg_source";
        private static final String JSON_KEY_BG_TYPE = "bg_type";
        private static final String JSON_KEY_FILE_EXTENSION = "file_extension";
        private static final String JSON_KEY_FONT_FACE = "font_face";
        private static final String JSON_KEY_FONT_WEIGHT = "font_weight";
        private static final String JSON_KEY_GRADIENT_ANGLE = "gradient_angle";
        private static final String JSON_KEY_NAME_ALIGN = "name_align";
        private static final String JSON_KEY_NAME_COLOR = "name_color";
        private static final String JSON_KEY_SQUARE_CROP = "square_crop_rect";
        private String mBgSource;
        private String mBgType;
        private String mFileExtension;
        private String mFontFace;
        private int mFontWeight;
        private float mGradientAngle;
        private int mNameAlign;
        private String mNameColor;
        private String mSquareCrop;

        public MetaData() {
        }

        /* access modifiers changed from: private */
        public Boolean isInvalid() {
            return Boolean.valueOf(TextUtils.isEmpty(this.mBgType));
        }

        public String getBgSource() {
            return this.mBgSource;
        }

        public String getBgType() {
            return this.mBgType;
        }

        public String getFileExtension() {
            return this.mFileExtension;
        }

        public String getFontFace() {
            return this.mFontFace;
        }

        public int getFontWeight() {
            return this.mFontWeight;
        }

        public float getGradientAngle() {
            return this.mGradientAngle;
        }

        public int getNameAlign() {
            return this.mNameAlign;
        }

        public String getNameColor() {
            return this.mNameColor;
        }

        public String getSquareCrop() {
            return this.mSquareCrop;
        }

        public MetaData(String str, String str2, int i2, int i7, String str3, float f, String str4, String str5, String str6) {
            this.mBgType = str;
            this.mFontFace = str2;
            this.mFontWeight = i2;
            this.mNameAlign = i7;
            this.mNameColor = str3;
            this.mGradientAngle = f;
            this.mSquareCrop = str4;
            this.mFileExtension = str5;
            this.mBgSource = str6;
        }

        public MetaData(JSONObject jSONObject) {
            try {
                String str = "";
                this.mBgType = jSONObject.has("bg_type") ? jSONObject.getString("bg_type") : str;
                this.mFontFace = jSONObject.has("font_face") ? jSONObject.getString("font_face") : str;
                int i2 = 0;
                this.mFontWeight = jSONObject.has("font_weight") ? jSONObject.getInt("font_weight") : 0;
                this.mNameAlign = jSONObject.has("name_align") ? jSONObject.getInt("name_align") : i2;
                this.mNameColor = jSONObject.has("name_color") ? jSONObject.getString("name_color") : str;
                this.mGradientAngle = jSONObject.has("gradient_angle") ? (float) jSONObject.getDouble("gradient_angle") : 0.0f;
                this.mSquareCrop = jSONObject.has("square_crop_rect") ? jSONObject.getString("square_crop_rect") : str;
                this.mFileExtension = jSONObject.has("file_extension") ? jSONObject.getString("file_extension") : str;
                this.mBgSource = jSONObject.has("bg_source") ? jSONObject.getString("bg_source") : str;
            } catch (JSONException e) {
                SdkLog.s(e);
            }
        }
    }

    public ProfileCard() {
        this.JSON_KEY_BG_FILE_URI = DataSyncContract.ProfileCardData.BACKGROUND_FILE_URI;
        this.JSON_KEY_CUSTOM_BG_HASH = DataSyncContract.ProfileCardData.CUSTOM_BACKGROUND_HASH;
        this.JSON_KEY_CUSTOM_TIMESTAMP = DataSyncContract.ProfileCardData.CUSTOM_TIMESTAMP;
        this.EXTRA_JSON_STRING = "json_string";
        this.mCustomTimestamp = 0;
    }

    private void fromJsonString(String str) {
        String str2;
        long j2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String str3 = "";
            if (jSONObject.has(DataSyncContract.ProfileCardData.BACKGROUND_FILE_URI)) {
                str2 = jSONObject.getString(DataSyncContract.ProfileCardData.BACKGROUND_FILE_URI);
            } else {
                str2 = str3;
            }
            this.mBgFileUri = Uri.parse(str2);
            if (jSONObject.has(DataSyncContract.ProfileCardData.CUSTOM_BACKGROUND_HASH)) {
                str3 = jSONObject.getString(DataSyncContract.ProfileCardData.CUSTOM_BACKGROUND_HASH);
            }
            this.mCustomBgHash = str3;
            if (jSONObject.has(DataSyncContract.ProfileCardData.CUSTOM_BACKGROUND_HASH)) {
                j2 = jSONObject.getLong(DataSyncContract.ProfileCardData.CUSTOM_TIMESTAMP);
            } else {
                j2 = 0;
            }
            this.mCustomTimestamp = j2;
            this.mMeta = new MetaData(jSONObject);
        } catch (JSONException e) {
            SdkLog.s(e);
        }
    }

    private Boolean isNull(Object obj) {
        boolean z;
        if (obj == null) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    private String toJsonString() {
        try {
            JSONObject jSONObject = new JSONObject();
            Uri uri = this.mBgFileUri;
            if (uri != null) {
                jSONObject.put(DataSyncContract.ProfileCardData.BACKGROUND_FILE_URI, uri.toString());
            }
            String str = this.mCustomBgHash;
            if (str != null) {
                jSONObject.put(DataSyncContract.ProfileCardData.CUSTOM_BACKGROUND_HASH, str);
            }
            long j2 = this.mCustomTimestamp;
            if (j2 != 0) {
                jSONObject.put(DataSyncContract.ProfileCardData.CUSTOM_TIMESTAMP, j2);
            }
            MetaData metaData = this.mMeta;
            if (metaData != null) {
                jSONObject.put(DataSyncContract.ProfileCardData.BACKGROUND_TYPE, metaData.getBgType());
                jSONObject.put(DataSyncContract.ProfileCardData.FONT_FACE, this.mMeta.getFontFace());
                jSONObject.put(DataSyncContract.ProfileCardData.FONT_WEIGHT, this.mMeta.getFontWeight());
                jSONObject.put(DataSyncContract.ProfileCardData.NAME_ALIGN, this.mMeta.getNameAlign());
                jSONObject.put(DataSyncContract.ProfileCardData.NAME_COLOR, this.mMeta.getNameColor());
                jSONObject.put(DataSyncContract.ProfileCardData.GRADIENT_ANGLE, (double) this.mMeta.getGradientAngle());
                jSONObject.put(DataSyncContract.ProfileCardData.SQUARE_CROP_RECT, this.mMeta.getSquareCrop());
                jSONObject.put(DataSyncContract.ProfileCardData.FILE_EXTENSION, this.mMeta.getFileExtension());
                jSONObject.put(DataSyncContract.ProfileCardData.BG_SOURCE, this.mMeta.getBgSource());
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            SdkLog.s(e);
            return null;
        }
    }

    public void fromBundle(Bundle bundle) {
        fromJsonString(bundle.getString("json_string"));
    }

    public Uri getBgFileUri() {
        return this.mBgFileUri;
    }

    public String getCustomBgHash() {
        return this.mCustomBgHash;
    }

    public long getCustomTimestamp() {
        return this.mCustomTimestamp;
    }

    public MetaData getMeta() {
        return this.mMeta;
    }

    public String getSyncServiceName() {
        return SYNC_SERVICE_NAME;
    }

    public Boolean isInvalid() {
        boolean z;
        if (isNull(this.mBgFileUri).booleanValue() || isNull(this.mCustomBgHash).booleanValue() || isNull(this.mMeta).booleanValue() || this.mMeta.isInvalid().booleanValue() || this.mCustomTimestamp == 0) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        String jsonString = toJsonString();
        if (jsonString != null) {
            bundle.putString("json_string", jsonString);
        }
        return bundle;
    }

    public ProfileCard(Uri uri, String str, long j2, MetaData metaData) {
        this.JSON_KEY_BG_FILE_URI = DataSyncContract.ProfileCardData.BACKGROUND_FILE_URI;
        this.JSON_KEY_CUSTOM_BG_HASH = DataSyncContract.ProfileCardData.CUSTOM_BACKGROUND_HASH;
        this.JSON_KEY_CUSTOM_TIMESTAMP = DataSyncContract.ProfileCardData.CUSTOM_TIMESTAMP;
        this.EXTRA_JSON_STRING = "json_string";
        this.mBgFileUri = uri;
        this.mCustomBgHash = str;
        this.mCustomTimestamp = j2;
        this.mMeta = metaData;
    }

    public ProfileCard(Bundle bundle) {
        this.JSON_KEY_BG_FILE_URI = DataSyncContract.ProfileCardData.BACKGROUND_FILE_URI;
        this.JSON_KEY_CUSTOM_BG_HASH = DataSyncContract.ProfileCardData.CUSTOM_BACKGROUND_HASH;
        this.JSON_KEY_CUSTOM_TIMESTAMP = DataSyncContract.ProfileCardData.CUSTOM_TIMESTAMP;
        this.EXTRA_JSON_STRING = "json_string";
        this.mCustomTimestamp = 0;
        fromBundle(bundle);
    }
}
