package com.samsung.android.gallery.module.dynamicview;

import A8.C0545b;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DynamicViewInfo implements Serializable {
    /* access modifiers changed from: private */
    public final List<ClipInfo> mClipInfoData = Collections.synchronizedList(new LinkedList());
    private int mEndMs = Integer.MIN_VALUE;
    /* access modifiers changed from: private */
    public final int mFileDuration;
    private int mStartMs = Integer.MAX_VALUE;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PlayInfoBuilder {
        private final DynamicViewInfo dvInfo;
        private int fileDuration = 1000;
        private boolean isForList;
        private int type;

        public PlayInfoBuilder(DynamicViewInfo dynamicViewInfo) {
            this.dvInfo = dynamicViewInfo;
        }

        private List<ClipInfo> createFakeClipInfo() {
            ArrayList arrayList = new ArrayList();
            int fileDuration2 = getFileDuration();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new PlaybackOption(0, fileDuration2, 1.0f));
            arrayList.add(new ClipInfo(0, 0, 1, 1.0f, 0, fileDuration2, arrayList2));
            return arrayList;
        }

        private List<ClipInfo> getClipInfo() {
            DynamicViewInfo dynamicViewInfo = this.dvInfo;
            if (dynamicViewInfo != null && dynamicViewInfo.mClipInfoData.size() > 0) {
                return this.dvInfo.mClipInfoData;
            }
            Log.e((CharSequence) "DynamicViewInfo", "no DynamicViewInfo, create fakeData", Integer.valueOf(this.fileDuration));
            return createFakeClipInfo();
        }

        private int getFileDuration() {
            DynamicViewInfo dynamicViewInfo = this.dvInfo;
            if (dynamicViewInfo != null) {
                return dynamicViewInfo.mFileDuration;
            }
            return Math.min(this.fileDuration, TextToSpeechConst.MAX_SPEECH_INPUT);
        }

        public DynamicViewPlayInfo build() {
            return new DynamicViewPlayInfo(DynamicView.get(this.type).setClipData(getClipInfo()).setFileDuration(getFileDuration()).setForList(this.isForList));
        }

        public PlayInfoBuilder setFileDuration(int i2) {
            this.fileDuration = i2;
            return this;
        }

        public PlayInfoBuilder setForList(boolean z) {
            this.isForList = z;
            return this;
        }

        public PlayInfoBuilder setPlayType(int i2) {
            this.type = i2;
            return this;
        }
    }

    public DynamicViewInfo(int i2) {
        this.mFileDuration = i2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$bestClipAvailable$1(ClipInfo clipInfo) {
        if (clipInfo == null || !DynamicViewType.isSupportShortClip(clipInfo.typeId)) {
            return false;
        }
        return true;
    }

    public void addClip(int i2, int i7, int i8, float f, int i10, int i11, ArrayList<PlaybackOption> arrayList) {
        this.mStartMs = Math.min(i10, this.mStartMs);
        this.mEndMs = Math.max(i11, this.mEndMs);
        this.mClipInfoData.add(new ClipInfo(i2, i7, i8, f, i10, i11, arrayList));
    }

    public boolean bestClipAvailable() {
        return this.mClipInfoData.stream().anyMatch(new a(0));
    }

    public void clipLoadCompleted() {
        this.mClipInfoData.sort(Comparator.comparingInt(new C0545b(21)));
    }

    public int getClipCount() {
        return this.mClipInfoData.size();
    }

    public List<ClipInfo> getClipInfoData() {
        return this.mClipInfoData;
    }

    public int getStartMs() {
        int i2 = this.mStartMs;
        if (i2 < this.mFileDuration) {
            return Math.max(i2, 0);
        }
        return 0;
    }
}
