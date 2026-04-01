package com.samsung.android.gallery.module.publisher;

import com.samsung.android.gallery.module.publisher.SearchDataBasePublisher;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class O implements SearchDataBasePublisher.AutoCompleteResult, FutureListener {
    public final /* synthetic */ SearchDataPublisher d;
    public final /* synthetic */ Object e;

    public /* synthetic */ O(SearchDataPublisher searchDataPublisher, Object obj) {
        this.d = searchDataPublisher;
        this.e = obj;
    }

    public void onFutureDone(Future future) {
        this.d.lambda$publishCategoryData$10((String) this.e, future);
    }
}
