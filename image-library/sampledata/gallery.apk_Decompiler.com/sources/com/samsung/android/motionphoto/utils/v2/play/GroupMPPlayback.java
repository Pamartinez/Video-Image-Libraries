package com.samsung.android.motionphoto.utils.v2.play;

import L1.d;
import Sf.q;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoInfo;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoPlayback;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;
import ne.C1194l;
import ne.C1195m;
import ne.C1196n;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010!\n\u0002\b\u0006\b&\u0018\u0000 !2\u00020\u0001:\u0001!B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\nJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0017\u001a\u00020\u00128FX\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u001b\u001a\u00020\b8FX\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0014\u001a\u0004\b\u0019\u0010\u001aR\u001d\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u001c8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/play/GroupMPPlayback;", "Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoPlayback;", "Ljava/io/File;", "file", "<init>", "(Ljava/io/File;)V", "Ljava/util/concurrent/TimeUnit;", "timeUnit", "", "getStartTime", "(Ljava/util/concurrent/TimeUnit;)J", "getEndTime", "", "getPlaySpeed", "()F", "Ljava/util/stream/Stream;", "stream", "()Ljava/util/stream/Stream;", "Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoInfo;", "mpInfo$delegate", "Lme/f;", "getMpInfo", "()Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoInfo;", "mpInfo", "captureTimestampUs$delegate", "getCaptureTimestampUs", "()J", "captureTimestampUs", "", "playbacks", "Ljava/util/List;", "getPlaybacks", "()Ljava/util/List;", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GroupMPPlayback implements MotionPhotoPlayback {
    public static final Companion Companion = new Companion((e) null);
    public static final int EFFECT_MIN_TIME_US = 500000;
    public static final int MIN_DIFF_TIME_IMAGE_AND_VIDEO_US = 100000;
    public static final int MOTION_PHOTO_MIN_DURATION_US = 1000000;
    private final f captureTimestampUs$delegate = d.q(new q(25, this));
    private final f mpInfo$delegate;
    private final List<MotionPhotoPlayback> playbacks = new ArrayList();

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/play/GroupMPPlayback$Companion;", "", "<init>", "()V", "MOTION_PHOTO_MIN_DURATION_US", "", "EFFECT_MIN_TIME_US", "MIN_DIFF_TIME_IMAGE_AND_VIDEO_US", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public GroupMPPlayback(File file) {
        j.e(file, "file");
        this.mpInfo$delegate = d.q(new q(24, file));
    }

    /* access modifiers changed from: private */
    public static final long captureTimestampUs_delegate$lambda$1(GroupMPPlayback groupMPPlayback) {
        if (groupMPPlayback.getMpInfo().getCaptureTimestampUs() == 0 || groupMPPlayback.getMpInfo().getVideoDurationUs() < groupMPPlayback.getMpInfo().getCaptureTimestampUs()) {
            return groupMPPlayback.getMpInfo().getVideoDurationUs();
        }
        return groupMPPlayback.getMpInfo().getCaptureTimestampUs();
    }

    /* access modifiers changed from: private */
    public static final MotionPhotoInfo mpInfo_delegate$lambda$0(File file) {
        return MotionPhotoInfo.parse(file);
    }

    public final long getCaptureTimestampUs() {
        return ((Number) this.captureTimestampUs$delegate.getValue()).longValue();
    }

    public long getEndTime(TimeUnit timeUnit) {
        j.e(timeUnit, "timeUnit");
        return ((MotionPhotoPlayback) C1194l.T0(this.playbacks)).getEndTime(timeUnit);
    }

    public final MotionPhotoInfo getMpInfo() {
        Object value = this.mpInfo$delegate.getValue();
        j.d(value, "getValue(...)");
        return (MotionPhotoInfo) value;
    }

    public float getPlaySpeed() {
        double d;
        Iterable<MotionPhotoPlayback> iterable = this.playbacks;
        ArrayList<Number> arrayList = new ArrayList<>(C1196n.w0(iterable, 10));
        for (MotionPhotoPlayback playSpeed : iterable) {
            arrayList.add(Float.valueOf(playSpeed.getPlaySpeed()));
        }
        double d2 = MapUtil.INVALID_LOCATION;
        int i2 = 0;
        for (Number floatValue : arrayList) {
            d2 += (double) floatValue.floatValue();
            i2++;
            if (i2 < 0) {
                C1195m.u0();
                throw null;
            }
        }
        if (i2 == 0) {
            d = Double.NaN;
        } else {
            d = d2 / ((double) i2);
        }
        return (float) d;
    }

    public final List<MotionPhotoPlayback> getPlaybacks() {
        return this.playbacks;
    }

    public long getStartTime(TimeUnit timeUnit) {
        j.e(timeUnit, "timeUnit");
        return ((MotionPhotoPlayback) C1194l.L0(this.playbacks)).getStartTime(timeUnit);
    }

    public Stream<MotionPhotoPlayback> stream() {
        Stream<MotionPhotoPlayback> stream = this.playbacks.stream();
        j.d(stream, "stream(...)");
        return stream;
    }
}
