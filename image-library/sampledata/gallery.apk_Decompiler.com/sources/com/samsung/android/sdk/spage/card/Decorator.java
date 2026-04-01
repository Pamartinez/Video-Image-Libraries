package com.samsung.android.sdk.spage.card;

import com.samsung.android.sdk.spage.card.base.JsonFieldData;
import com.samsung.android.sdk.spage.card.base.Manipulator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Decorator extends Manipulator<Decorator> {
    private static final String KEY_BACKGROUND_COLOR = "bgColor";

    public Decorator(JsonFieldData jsonFieldData) {
        super(jsonFieldData);
    }

    public Decorator setBackgroundColor(int i2) {
        return (Decorator) put(KEY_BACKGROUND_COLOR, i2);
    }
}
