package com.samsung.android.motionphoto.utils.v2;

import com.samsung.android.motionphoto.utils.v2.play.MPPlaybackBoomerang;
import com.samsung.android.motionphoto.utils.v2.play.MPPlaybackSlowMotion;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MotionPhotoPlayback {

    /* renamed from: com.samsung.android.motionphoto.utils.v2.MotionPhotoPlayback$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$motionphoto$utils$v2$MotionPhotoPlayback$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.samsung.android.motionphoto.utils.v2.MotionPhotoPlayback$Type[] r0 = com.samsung.android.motionphoto.utils.v2.MotionPhotoPlayback.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$motionphoto$utils$v2$MotionPhotoPlayback$Type = r0
                com.samsung.android.motionphoto.utils.v2.MotionPhotoPlayback$Type r1 = com.samsung.android.motionphoto.utils.v2.MotionPhotoPlayback.Type.BOOMERANG     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$motionphoto$utils$v2$MotionPhotoPlayback$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.motionphoto.utils.v2.MotionPhotoPlayback$Type r1 = com.samsung.android.motionphoto.utils.v2.MotionPhotoPlayback.Type.SLOW_MOTION     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MotionPhotoPlayback.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        BOOMERANG,
        SLOW_MOTION
    }

    static MotionPhotoPlayback of(Type type, File file) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$motionphoto$utils$v2$MotionPhotoPlayback$Type[type.ordinal()];
        if (i2 == 1) {
            return new MPPlaybackBoomerang(file);
        }
        if (i2 == 2) {
            return new MPPlaybackSlowMotion(file);
        }
        throw new IncompatibleClassChangeError();
    }

    long getEndTime(TimeUnit timeUnit);

    long getEndTimeMS() {
        return getEndTime(TimeUnit.MILLISECONDS);
    }

    long getEndTimeUs() {
        return getEndTime(TimeUnit.MICROSECONDS);
    }

    float getPlaySpeed();

    long getStartTime(TimeUnit timeUnit);

    long getStartTimeMs() {
        return getStartTime(TimeUnit.MILLISECONDS);
    }

    long getStartTimeUs() {
        return getStartTime(TimeUnit.MICROSECONDS);
    }

    Stream<MotionPhotoPlayback> stream();
}
