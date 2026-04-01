package com.samsung.android.gallery.module.data;

import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Comparator {
    public final int compare(Object obj, Object obj2) {
        return Long.compare(MediaItemStory.getStoryFavorite((MediaItem) obj2), MediaItemStory.getStoryFavorite((MediaItem) obj));
    }
}
