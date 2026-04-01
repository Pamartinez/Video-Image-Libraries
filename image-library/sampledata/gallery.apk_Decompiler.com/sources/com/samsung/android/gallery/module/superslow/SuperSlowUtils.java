package com.samsung.android.gallery.module.superslow;

import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.shotmode.RecordingMode;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SuperSlowUtils {

    /* renamed from: com.samsung.android.gallery.module.superslow.SuperSlowUtils$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$superslow$SuperSlowUtils$SuperSlowType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.gallery.module.superslow.SuperSlowUtils$SuperSlowType[] r0 = com.samsung.android.gallery.module.superslow.SuperSlowUtils.SuperSlowType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$superslow$SuperSlowUtils$SuperSlowType = r0
                com.samsung.android.gallery.module.superslow.SuperSlowUtils$SuperSlowType r1 = com.samsung.android.gallery.module.superslow.SuperSlowUtils.SuperSlowType.Forward     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$superslow$SuperSlowUtils$SuperSlowType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.superslow.SuperSlowUtils$SuperSlowType r1 = com.samsung.android.gallery.module.superslow.SuperSlowUtils.SuperSlowType.ForwardAndReverse     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$superslow$SuperSlowUtils$SuperSlowType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.superslow.SuperSlowUtils$SuperSlowType r1 = com.samsung.android.gallery.module.superslow.SuperSlowUtils.SuperSlowType.Reverse     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.superslow.SuperSlowUtils.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SuperSlowInfo {
        final int orientation;
        final long thumbStartType;
        final SuperSlowType type;

        public SuperSlowInfo(SuperSlowType superSlowType, int i2, long j2) {
            this.type = superSlowType;
            this.thumbStartType = getThumbStartTime(superSlowType, j2);
            this.orientation = i2;
        }

        public long getThumbStartTime(SuperSlowType superSlowType, long j2) {
            int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$superslow$SuperSlowUtils$SuperSlowType[superSlowType.ordinal()];
            if (i2 == 1 || i2 == 2) {
                return j2 * 1000;
            }
            if (i2 != 3) {
                return -1;
            }
            return (j2 + 6000) * 1000;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SuperSlowType {
        Forward(R$string.superslow_effect_forward),
        Reverse(R$string.superslow_effect_reverse),
        ForwardAndReverse(R$string.superslow_effect_forward_and_reverse);
        
        public final int titleResId;

        private SuperSlowType(int i2) {
            this.titleResId = i2;
        }
    }

    public static MediaItem createSuperSlowItem(MediaItem mediaItem, SuperSlowInfo superSlowInfo) {
        MediaItem clone = mediaItem.clone();
        clone.setOrientation(superSlowInfo.orientation);
        clone.setTitle(AppResources.getString(superSlowInfo.type.titleResId));
        clone.setVideoThumbStartTime(superSlowInfo.thumbStartType);
        return clone;
    }

    public static int getSuperSlowOffset(int i2) {
        if (i2 == 3) {
            return 500;
        }
        return 1000;
    }

    public static boolean isSuperSlow(MediaItem mediaItem) {
        String str;
        if (!Features.isEnabled(Features.SUPPORT_SUPER_SLOW_MOTION) || mediaItem == null || !mediaItem.isVideo() || !RecordingMode.isSuperSlowMo(mediaItem.getRecordingMode())) {
            return false;
        }
        if (mediaItem.isCloudOnly()) {
            str = mediaItem.getCloudThumbPath();
        } else {
            str = mediaItem.getPath();
        }
        if (FileUtils.exists(str)) {
            return true;
        }
        return false;
    }
}
