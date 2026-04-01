package com.samsung.android.gallery.module.dynamicview;

import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import i.C0212a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShortClip extends DynamicView {
    private int findShortClipPosition() {
        int size = this.clipInfoList.size();
        float f = -1.0f;
        int i2 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            ClipInfo clipInfo = this.clipInfoList.get(i7);
            if (clipInfo != null && DynamicViewType.isSupportShortClip(clipInfo.typeId)) {
                float f5 = clipInfo.score;
                if (f5 > f) {
                    i2 = i7;
                    f = f5;
                }
            }
        }
        return i2;
    }

    private float getBestClipSpeed(ClipInfo clipInfo) {
        if (clipInfo == null) {
            return 1.0f;
        }
        int i2 = clipInfo.clipEndMs - clipInfo.clipStartMs;
        if (i2 <= 2000) {
            return 0.5f;
        }
        if (i2 <= 8000) {
            return 2.0f;
        }
        return 4.0f;
    }

    public AnalyticsDetail getAnalyticsDetail() {
        return AnalyticsDetail.EVENT_DETAIL_TOP_CLIP;
    }

    public ArrayList<PlaybackOption> getPlayList() {
        int i2;
        int findShortClipPosition = findShortClipPosition();
        ArrayList<PlaybackOption> arrayList = this.clipInfoList.get(findShortClipPosition).playbackOptions;
        float bestClipSpeed = getBestClipSpeed(this.clipInfoList.get(findShortClipPosition));
        ArrayList<PlaybackOption> arrayList2 = new ArrayList<>();
        int i7 = 0;
        if (arrayList != null) {
            i2 = arrayList.get(0).mStartMs;
        } else {
            i2 = 0;
        }
        if (arrayList != null) {
            i7 = ((PlaybackOption) C0212a.i(arrayList, 1)).mEndMs;
        }
        arrayList2.add(new PlaybackOption(i2, i7, bestClipSpeed));
        return arrayList2;
    }

    public int getShortClipIndex() {
        return findShortClipPosition();
    }

    public int getType() {
        return 2;
    }

    public int getTypeStingId() {
        return R$string.clip;
    }

    public boolean supportBgm() {
        return false;
    }
}
