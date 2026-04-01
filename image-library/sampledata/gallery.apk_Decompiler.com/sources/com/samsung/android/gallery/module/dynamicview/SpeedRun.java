package com.samsung.android.gallery.module.dynamicview;

import A4.C0371f;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpeedRun extends DynamicView {
    private ArrayList<PlaybackOption> getListPlayback() {
        return getRawPlayList();
    }

    private float getSpeed(boolean z) {
        if (z) {
            return 1.0f;
        }
        return 4.0f;
    }

    private ArrayList<PlaybackOption> getViewerPlayback() {
        ArrayList<PlaybackOption> arrayList = new ArrayList<>();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        getRawPlayList().stream().filter(new a(1)).forEach(new C0371f((Object) this, (Object) atomicInteger, (Object) arrayList, 24));
        int min = Math.min(this.fileDuration, 180000);
        if (atomicInteger.get() < min) {
            arrayList.add(createPlayback(atomicInteger.get(), min, getSpeed(false)));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getViewerPlayback$0(PlaybackOption playbackOption) {
        return !playbackOption.mIsPreOrPostAction;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getViewerPlayback$1(AtomicInteger atomicInteger, ArrayList arrayList, PlaybackOption playbackOption) {
        int i2 = atomicInteger.get();
        int i7 = playbackOption.mStartMs;
        if (i2 < i7) {
            arrayList.add(createPlayback(i2, i7, getSpeed(false)));
        }
        arrayList.add(createPlayback(playbackOption.mStartMs, playbackOption.mEndMs, getSpeed(true)));
        atomicInteger.set(playbackOption.mEndMs);
    }

    public AnalyticsDetail getAnalyticsDetail() {
        return AnalyticsDetail.EVENT_DETAIL_SPEED_RUN;
    }

    public ArrayList<PlaybackOption> getPlayList() {
        if (this.isForList) {
            return getListPlayback();
        }
        return getViewerPlayback();
    }

    public int getType() {
        return 1;
    }

    public int getTypeStingId() {
        return R$string.speed_run;
    }
}
