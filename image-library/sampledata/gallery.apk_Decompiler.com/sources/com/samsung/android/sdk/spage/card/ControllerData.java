package com.samsung.android.sdk.spage.card;

import android.text.TextUtils;
import com.samsung.android.sdk.spage.card.base.JsonFieldData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ControllerData extends JsonFieldData<ControllerData> {
    private int mFlags = 0;

    public ControllerData(String str) {
        if (!TextUtils.isEmpty(str)) {
            put("controllerType", str);
            return;
        }
        throw new IllegalArgumentException("controller type not valid");
    }

    public ControllerData addFlags(int i2) {
        int i7 = i2 | this.mFlags;
        this.mFlags = i7;
        return (ControllerData) put("flags", i7);
    }

    public ControllerData setState(int i2) {
        return (ControllerData) put("state", Integer.toString(i2));
    }
}
