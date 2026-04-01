package com.samsung.android.gallery.module.dynamicview;

import S3.d;
import com.samsung.android.gallery.support.utils.AppResources;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DynamicView implements PlayInfo {
    protected List<ClipInfo> clipInfoList;
    protected int fileDuration;
    protected boolean isForList;

    public static DynamicView get(int i2) {
        if (i2 == 0) {
            return new SpeedMix();
        }
        if (i2 == 1) {
            return new SpeedRun();
        }
        if (i2 == 2) {
            return new ShortClip();
        }
        if (i2 != 3) {
            return new SpeedMix();
        }
        return new Highlight();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getRawPlayList$0(ClipInfo clipInfo) {
        if (clipInfo == null || clipInfo.playbackOptions.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean available() {
        List<ClipInfo> list = this.clipInfoList;
        if (list == null || list.isEmpty()) {
            return false;
        }
        return true;
    }

    public final PlaybackOption createPlayback(int i2, int i7, float f) {
        return new PlaybackOption(i2, i7, f);
    }

    public final ArrayList<PlaybackOption> getRawPlayList() {
        ArrayList<PlaybackOption> arrayList = new ArrayList<>();
        if (available()) {
            this.clipInfoList.stream().filter(new d(29)).forEach(new B8.d(arrayList, 28));
        }
        return arrayList;
    }

    public abstract int getTypeStingId();

    public String getTypeString() {
        return AppResources.getString(getTypeStingId());
    }

    public DynamicView setClipData(List<ClipInfo> list) {
        this.clipInfoList = list;
        return this;
    }

    public DynamicView setFileDuration(int i2) {
        this.fileDuration = i2;
        return this;
    }

    public DynamicView setForList(boolean z) {
        this.isForList = z;
        return this;
    }
}
