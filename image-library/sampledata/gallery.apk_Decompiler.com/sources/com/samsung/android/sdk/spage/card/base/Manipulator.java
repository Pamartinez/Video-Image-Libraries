package com.samsung.android.sdk.spage.card.base;

import com.samsung.android.sdk.spage.card.base.JsonFieldData;
import com.samsung.android.sdk.spage.card.base.Manipulator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Manipulator<T extends Manipulator> extends JsonFieldData<T> {
    private final JsonFieldData mFieldData;

    public Manipulator(JsonFieldData jsonFieldData) {
        this.mFieldData = jsonFieldData;
    }

    public String getData() {
        return this.mFieldData.getData();
    }

    public void remove(String str) {
        this.mFieldData.remove(str);
    }

    public T putList(String str, List<? extends JsonFieldData.Listable> list) {
        this.mFieldData.putList(str, list);
        return this;
    }

    public T put(String str, String str2) {
        this.mFieldData.put(str, str2);
        return this;
    }

    public T put(String str, int i2) {
        this.mFieldData.put(str, i2);
        return this;
    }
}
