package com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IViewCache {
    View loadOrCreate(int i2);

    void restore(int i2, View view);
}
