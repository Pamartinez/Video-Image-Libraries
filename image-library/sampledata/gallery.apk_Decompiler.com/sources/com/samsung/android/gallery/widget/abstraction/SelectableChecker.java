package com.samsung.android.gallery.widget.abstraction;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SelectableChecker<T> {
    int getMaxCount();

    boolean isSelectable(T t) {
        return true;
    }

    boolean isSupported(T t);

    void showExceedMaxCountToast(Context context);

    void done(T[] tArr) {
    }
}
