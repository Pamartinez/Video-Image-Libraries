package com.samsung.android.gallery.app.ui.list.stories.recap.lastpage;

import H.a;
import I4.b;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.PageDataLoader;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.recap.IRecapView;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import h4.C0464a;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapPageDataLoader extends PageDataLoader {
    public RecapPageDataLoader(IRecapView iRecapView) {
        super(iRecapView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadData$1(List list, Consumer consumer) {
        swap(list);
        Optional.ofNullable(consumer).ifPresent(new b(5));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadData$2(Consumer consumer) {
        long currentTimeMillis = System.currentTimeMillis();
        PageItem[] loadData = loadData();
        List list = (List) Stream.of(loadData).filter(new C0464a(25)).collect(Collectors.toList());
        Log.d("RecapPageDataLoader", "loadData" + Logger.vt(Integer.valueOf(loadData.length), Integer.valueOf(list.size()), Long.valueOf(currentTimeMillis)));
        ThreadUtil.postOnUiThread(new A9.b(this, list, consumer, 28));
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem[]] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadRecapCoverItem(com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem[] r4, int r5) {
        /*
            r3 = this;
            com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView r0 = r3.mView
            com.samsung.android.gallery.module.data.MediaItem r0 = r0.getHeaderItem()
            if (r0 == 0) goto L_0x0013
            com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView r0 = r3.mView
            com.samsung.android.gallery.module.data.MediaItem r0 = r0.getHeaderItem()
            com.samsung.android.gallery.module.data.MediaItem r0 = r0.clone()
            goto L_0x0014
        L_0x0013:
            r0 = 0
        L_0x0014:
            if (r0 == 0) goto L_0x0026
            com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView r3 = r3.mView
            com.samsung.android.gallery.module.data.MediaItem r3 = r3.getHeaderItem()
            com.samsung.android.gallery.module.data.MediaItem r0 = r3.clone()
            r1 = 1000000(0xf4240, double:4.940656E-318)
            r0.setVideoThumbStartTime(r1)
        L_0x0026:
            com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.RecapCoverItem r3 = new com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.RecapCoverItem
            r3.<init>(r0)
            if (r0 == 0) goto L_0x0032
            java.lang.String r1 = r0.getTitle()
            goto L_0x0034
        L_0x0032:
            java.lang.String r1 = "Recap page"
        L_0x0034:
            r3.setTitle(r1)
            if (r0 == 0) goto L_0x003e
            java.lang.String r0 = r0.getDate()
            goto L_0x0040
        L_0x003e:
            java.lang.String r0 = "Recap date"
        L_0x0040:
            r3.setSubTitle(r0)
            r4[r5] = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.stories.recap.lastpage.RecapPageDataLoader.loadRecapCoverItem(com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem[], int):void");
    }

    public void loadData(Consumer<Boolean> consumer) {
        SimpleThreadPool.getInstance().execute(new a(14, this, consumer));
    }

    public PageItem[] loadData() {
        PageItem[] pageItemArr = new PageItem[1];
        loadRecapCoverItem(pageItemArr, 0);
        return pageItemArr;
    }
}
