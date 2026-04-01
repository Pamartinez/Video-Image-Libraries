package com.samsung.android.sdk.spage.card;

import com.samsung.android.sdk.spage.card.base.ActionFieldData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageData extends ActionFieldData<ImageData> {
    private static final String KEY_RES_NAME = "resName";
    private static final String KEY_URI_STRING = "uriString";

    public ImageData setImageResName(String str) {
        remove(KEY_URI_STRING);
        return (ImageData) put(KEY_RES_NAME, str);
    }

    public ImageData setImageUri(String str) {
        remove(KEY_RES_NAME);
        return (ImageData) put(KEY_URI_STRING, str);
    }
}
