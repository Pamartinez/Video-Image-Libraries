package com.samsung.android.gallery.crossapp.function;

import androidx.appfunctions.AppFunctionContext;
import com.google.android.appfunctions.schema.photos.v1.FindMediaItemsParams;
import kotlin.Metadata;
import qe.C1227c;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@C1273e(c = "com.samsung.android.gallery.crossapp.function.PhotoFunctions", f = "PhotoFunctions.kt", l = {84}, m = "findMediaItems")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PhotoFunctions$findMediaItems$1 extends C1271c {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PhotoFunctions this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PhotoFunctions$findMediaItems$1(PhotoFunctions photoFunctions, C1227c cVar) {
        super(cVar);
        this.this$0 = photoFunctions;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.findMediaItems((AppFunctionContext) null, (FindMediaItemsParams) null, this);
    }
}
