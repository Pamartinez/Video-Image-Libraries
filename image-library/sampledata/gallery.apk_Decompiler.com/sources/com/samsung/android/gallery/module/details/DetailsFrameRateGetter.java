package com.samsung.android.gallery.module.details;

import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsFrameRateGetter {
    private static final DetailsFrameRateGetter sInstance = new DetailsFrameRateGetter();
    private final ConcurrentHashMap<String, Integer> mMap = new ConcurrentHashMap<>();

    public static DetailsFrameRateGetter getInstance() {
        return sInstance;
    }

    private String getKey(MediaItem mediaItem) {
        if (mediaItem.getFileId() > 0) {
            return String.valueOf(mediaItem.getFileId());
        }
        return null;
    }

    public Integer get(MediaItem mediaItem) {
        Integer num;
        String key = getKey(mediaItem);
        if (key != null) {
            num = this.mMap.get(key);
        } else {
            num = null;
        }
        if (num != null && num.intValue() > 0) {
            return num;
        }
        int i2 = VideoPropData.of(mediaItem).videoFrameRate;
        Integer valueOf = Integer.valueOf(i2);
        if (key != null && i2 > 0) {
            this.mMap.put(key, valueOf);
        }
        return valueOf;
    }
}
