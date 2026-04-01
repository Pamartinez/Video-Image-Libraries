package com.samsung.android.gallery.module.details;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsLoadResult {
    public static final DetailsLoadResult EMPTY = new DetailsLoadResult(DetailsUpdateKey.DEFAULT);
    public final Object[] extras;
    public final DetailsUpdateKey key;

    public DetailsLoadResult(DetailsUpdateKey detailsUpdateKey) {
        this.key = detailsUpdateKey;
        this.extras = null;
    }

    public String toString() {
        return "Details[" + this.key.name() + ']';
    }

    public DetailsLoadResult(DetailsUpdateKey detailsUpdateKey, Object... objArr) {
        this.key = detailsUpdateKey;
        this.extras = objArr;
    }
}
