package com.samsung.android.gallery.module.dataset;

import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l0 implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2983a;
    public final /* synthetic */ MediaDataCursor b;

    public /* synthetic */ l0(MediaDataCursor mediaDataCursor, int i2) {
        this.f2983a = i2;
        this.b = mediaDataCursor;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f2983a;
        MediaDataCursor mediaDataCursor = this.b;
        switch (i2) {
            case 0:
                ((MediaDataSearchCreatureSelectForRelation) mediaDataCursor).lambda$onDestroy$3((String) obj, (MediaData) obj2);
                return;
            case 1:
                ((MediaDataSearchCreatureSelectForRelation) mediaDataCursor).lambda$reopenChildMediaData$5((String) obj, (MediaData) obj2);
                return;
            default:
                ((MediaDataMxAlbum) mediaDataCursor).lambda$onDestroy$1((String) obj, (MediaData) obj2);
                return;
        }
    }
}
