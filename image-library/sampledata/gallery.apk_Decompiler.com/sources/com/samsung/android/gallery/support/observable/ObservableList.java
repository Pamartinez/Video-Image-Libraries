package com.samsung.android.gallery.support.observable;

import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ObservableList<T> extends List<T> {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnListChangedCallback<T extends ObservableList> {
        void onItemRangeChanged(T t, int i2, int i7);

        void onItemRangeInserted(T t, int i2, int i7) {
        }

        void onItemRangeRemoved(T t, int i2, int i7) {
        }
    }
}
