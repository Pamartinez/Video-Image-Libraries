package com.samsung.android.gallery.module.thumbnail.logic;

import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class ThumbnailHelper {
    public static int getVideoDecodingSize(ThumbKind thumbKind, int i2, int i7) {
        if (thumbKind == ThumbKind.XLARGE_NC_KIND) {
            return MediaDefs.Meta.XMP.XMP_MIX_RESERVED_SIZE;
        }
        int max = Math.max(i2, i7);
        if (max == 1280) {
            return 640;
        }
        if (max == 1440) {
            return 720;
        }
        return Math.min(max, 960);
    }

    public static int getVideoMaxSize(int i2, int i7) {
        int i8 = i2 * i7;
        if (i8 > 8294400) {
            return 912;
        }
        if (i8 > 2073600) {
            if (i7 > i2) {
                return 720;
            }
            return 640;
        } else if (i8 == 2073600) {
            return 640;
        } else {
            if (Math.max(i2, i7) < 1280 || i7 <= i2) {
                return 512;
            }
            return 640;
        }
    }
}
