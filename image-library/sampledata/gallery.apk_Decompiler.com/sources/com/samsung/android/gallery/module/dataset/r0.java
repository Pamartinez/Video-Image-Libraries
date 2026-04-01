package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.module.dataset.MediaDataStoriesV5;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r0 implements Consumer {
    public final /* synthetic */ MediaDataStoriesV5 d;
    public final /* synthetic */ MediaDataStoriesV5.DataInfo e;
    public final /* synthetic */ Cursor f;
    public final /* synthetic */ List g;

    public /* synthetic */ r0(MediaDataStoriesV5 mediaDataStoriesV5, MediaDataStoriesV5.DataInfo dataInfo, Cursor cursor, List list) {
        this.d = mediaDataStoriesV5;
        this.e = dataInfo;
        this.f = cursor;
        this.g = list;
    }

    public final void accept(Object obj) {
        this.d.lambda$loadData$0(this.e, this.f, this.g, (Integer) obj);
    }
}
