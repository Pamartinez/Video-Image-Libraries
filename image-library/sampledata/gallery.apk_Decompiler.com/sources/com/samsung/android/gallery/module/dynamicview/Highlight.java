package com.samsung.android.gallery.module.dynamicview;

import U5.b;
import Z8.c;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Highlight extends DynamicView {
    /* access modifiers changed from: private */
    public int highlightClipCompare(ClipInfo clipInfo, ClipInfo clipInfo2) {
        if (clipInfo.priority >= clipInfo2.priority && clipInfo.score <= clipInfo2.score && clipInfo.clipStartMs >= clipInfo2.clipStartMs) {
            return 1;
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getPlayList$0(ArrayList arrayList, ClipInfo clipInfo) {
        arrayList.add(createPlayback(clipInfo.clipStartMs, clipInfo.clipEndMs, 1.0f));
    }

    public AnalyticsDetail getAnalyticsDetail() {
        return AnalyticsDetail.EVENT_DETAIL_HIGHLIGHTS;
    }

    public ArrayList<PlaybackOption> getPlayList() {
        ArrayList arrayList = new ArrayList(this.clipInfoList);
        arrayList.sort(new c(3, this));
        ArrayList<PlaybackOption> arrayList2 = new ArrayList<>();
        arrayList.forEach(new b(27, this, arrayList2));
        return arrayList2;
    }

    public int getType() {
        return 3;
    }

    public int getTypeStingId() {
        return R$string.highlights;
    }
}
