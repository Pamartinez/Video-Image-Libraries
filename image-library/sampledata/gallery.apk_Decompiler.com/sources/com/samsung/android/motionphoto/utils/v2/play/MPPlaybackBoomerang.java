package com.samsung.android.motionphoto.utils.v2.play;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/play/MPPlaybackBoomerang;", "Lcom/samsung/android/motionphoto/utils/v2/play/GroupMPPlayback;", "file", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MPPlaybackBoomerang extends GroupMPPlayback {
    private static final float BOOMERANG_PLAY_SPEED = 2.0f;
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/play/MPPlaybackBoomerang$Companion;", "", "<init>", "()V", "BOOMERANG_PLAY_SPEED", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MPPlaybackBoomerang(File file) {
        super(file);
        i iVar;
        j.e(file, "file");
        if (getMpInfo().getVideoDurationUs() <= 1000000) {
            iVar = new i(0L, Long.valueOf(getMpInfo().getVideoDurationUs()));
        } else if (getMpInfo().getVideoDurationUs() - getCaptureTimestampUs() >= 500000) {
            long captureTimestampUs = getCaptureTimestampUs();
            long j2 = (long) GroupMPPlayback.EFFECT_MIN_TIME_US;
            iVar = new i(Long.valueOf(Math.max(0, captureTimestampUs - j2)), Long.valueOf(getCaptureTimestampUs() + j2));
        } else {
            iVar = new i(Long.valueOf(getMpInfo().getVideoDurationUs() - ((long) 1000000)), Long.valueOf(getMpInfo().getVideoDurationUs()));
        }
        long longValue = ((Number) iVar.d).longValue();
        long longValue2 = ((Number) iVar.e).longValue();
        getPlaybacks().add(new SingleMPPlayback(longValue, longValue2, 2.0f));
        getPlaybacks().add(new SingleMPPlayback(longValue2, longValue, 2.0f));
    }
}
