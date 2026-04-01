package com.samsung.android.motionphoto.utils.v2.play;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/play/MPPlaybackSlowMotion;", "Lcom/samsung/android/motionphoto/utils/v2/play/GroupMPPlayback;", "file", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MPPlaybackSlowMotion extends GroupMPPlayback {
    public static final Companion Companion = new Companion((e) null);
    private static final float SLOW_MOTION_PLAY_SPEED_FAST = 2.0f;
    private static final float SLOW_MOTION_PLAY_SPEED_SLOW = 0.25f;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/play/MPPlaybackSlowMotion$Companion;", "", "<init>", "()V", "SLOW_MOTION_PLAY_SPEED_FAST", "", "SLOW_MOTION_PLAY_SPEED_SLOW", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MPPlaybackSlowMotion(File file) {
        super(file);
        j.e(file, "file");
        if (getMpInfo().getVideoDurationUs() < 1000000) {
            long videoDurationUs = getMpInfo().getVideoDurationUs() >> 1;
            getPlaybacks().add(new SingleMPPlayback(0, videoDurationUs, 2.0f));
            getPlaybacks().add(new SingleMPPlayback(videoDurationUs + 1, getMpInfo().getVideoDurationUs(), 0.25f));
        } else if (getCaptureTimestampUs() <= 500000) {
            getPlaybacks().add(new SingleMPPlayback(0, getCaptureTimestampUs(), 0.25f));
            getPlaybacks().add(new SingleMPPlayback(getCaptureTimestampUs() + 1, getMpInfo().getVideoDurationUs(), 2.0f));
        } else if (getMpInfo().getVideoDurationUs() - getCaptureTimestampUs() <= 100000) {
            long captureTimestampUs = getCaptureTimestampUs() - ((long) GroupMPPlayback.EFFECT_MIN_TIME_US);
            getPlaybacks().add(new SingleMPPlayback(0, captureTimestampUs, 2.0f));
            getPlaybacks().add(new SingleMPPlayback(captureTimestampUs + 1, getMpInfo().getVideoDurationUs(), 0.25f));
        } else {
            long captureTimestampUs2 = getCaptureTimestampUs() - ((long) GroupMPPlayback.EFFECT_MIN_TIME_US);
            getPlaybacks().add(new SingleMPPlayback(0, captureTimestampUs2, 2.0f));
            getPlaybacks().add(new SingleMPPlayback(captureTimestampUs2 + 1, getCaptureTimestampUs(), 0.25f));
            getPlaybacks().add(new SingleMPPlayback(getCaptureTimestampUs() + 1, getMpInfo().getVideoDurationUs(), 2.0f));
        }
    }
}
