package com.samsung.android.gallery.module.story.smartrect;

import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SupportRatio {
    public final ArrayList<Float> mResolutionRatios;

    public SupportRatio() {
        ArrayList<Float> arrayList = new ArrayList<>();
        this.mResolutionRatios = arrayList;
        arrayList.add(Float.valueOf(10.0f));
        arrayList.add(Float.valueOf(1.0f));
        arrayList.add(Float.valueOf(0.75f));
        arrayList.add(Float.valueOf(1.3333334f));
        arrayList.add(Float.valueOf(0.5625f));
        arrayList.add(Float.valueOf(1.7777778f));
        arrayList.add(Float.valueOf(0.45f));
        arrayList.add(Float.valueOf(2.2222223f));
        arrayList.add(Float.valueOf(0.625f));
        arrayList.add(Float.valueOf(1.6f));
        arrayList.add(Float.valueOf(0.8007246f));
        arrayList.add(Float.valueOf(1.2488688f));
        arrayList.add(Float.valueOf(0.4f));
        arrayList.add(Float.valueOf(2.5f));
        arrayList.add(Float.valueOf(0.7f));
        arrayList.add(Float.valueOf(1.4285715f));
        arrayList.add(Float.valueOf(0.35f));
        arrayList.add(Float.valueOf(2.857143f));
        arrayList.add(Float.valueOf(0.80163044f));
        arrayList.add(Float.valueOf(1.2474576f));
    }

    public float get(int i2) {
        if (i2 < 0 || i2 >= size()) {
            return 1.0f;
        }
        return (float) this.mResolutionRatios.size();
    }

    public int size() {
        return this.mResolutionRatios.size();
    }

    public ArrayList<Float> get() {
        return this.mResolutionRatios;
    }
}
