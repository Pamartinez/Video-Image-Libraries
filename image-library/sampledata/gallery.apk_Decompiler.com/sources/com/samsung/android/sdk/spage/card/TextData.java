package com.samsung.android.sdk.spage.card;

import com.samsung.android.sdk.spage.card.base.ActionFieldData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextData extends ActionFieldData<TextData> {
    private static final String KEY_RAW_STRING = "rawString";
    private static final String KEY_RES_NAME = "resName";
    private static final String KEY_TEXT_COLOR = "textColor";

    public TextData setText(String str) {
        remove(KEY_RES_NAME);
        return (TextData) put(KEY_RAW_STRING, str);
    }

    public TextData setTextColor(int i2) {
        return (TextData) put(KEY_TEXT_COLOR, i2);
    }

    public TextData setTextResName(String str) {
        remove(KEY_RAW_STRING);
        return (TextData) put(KEY_RES_NAME, str);
    }
}
