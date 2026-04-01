package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.effectfilter.Filter;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface BlurInterface {
    void applyFilter(Bitmap bitmap, BiConsumer<Bitmap, Filter> biConsumer);

    void bindBlurBitmap(MediaItem mediaItem, Bitmap bitmap);
}
