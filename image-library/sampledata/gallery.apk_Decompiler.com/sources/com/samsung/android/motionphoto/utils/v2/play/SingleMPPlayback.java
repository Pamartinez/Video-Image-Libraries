package com.samsung.android.motionphoto.utils.v2.play;

import com.samsung.android.motionphoto.utils.v2.MotionPhotoPlayback;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/play/SingleMPPlayback;", "Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoPlayback;", "startTimeUs", "", "endTimeUs", "playSpeed", "", "<init>", "(JJF)V", "getStartTime", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "getEndTime", "getPlaySpeed", "stream", "Ljava/util/stream/Stream;", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SingleMPPlayback implements MotionPhotoPlayback {
    private final long endTimeUs;
    private final float playSpeed;
    private final long startTimeUs;

    public SingleMPPlayback(long j2, long j3, float f) {
        this.startTimeUs = j2;
        this.endTimeUs = j3;
        this.playSpeed = f;
    }

    public long getEndTime(TimeUnit timeUnit) {
        j.e(timeUnit, "timeUnit");
        return timeUnit.convert(this.endTimeUs, TimeUnit.MICROSECONDS);
    }

    public float getPlaySpeed() {
        return this.playSpeed;
    }

    public long getStartTime(TimeUnit timeUnit) {
        j.e(timeUnit, "timeUnit");
        return timeUnit.convert(this.startTimeUs, TimeUnit.MICROSECONDS);
    }

    public Stream<MotionPhotoPlayback> stream() {
        Stream<MotionPhotoPlayback> of2 = Stream.of(this);
        j.d(of2, "of(...)");
        return of2;
    }
}
