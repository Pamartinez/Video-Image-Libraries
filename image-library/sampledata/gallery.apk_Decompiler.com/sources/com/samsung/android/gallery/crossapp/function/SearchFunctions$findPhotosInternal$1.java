package com.samsung.android.gallery.crossapp.function;

import androidx.appfunctions.AppFunctionContext;
import kotlin.Metadata;
import qe.C1227c;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@C1273e(c = "com.samsung.android.gallery.crossapp.function.SearchFunctions", f = "SearchFunctions.kt", l = {140}, m = "findPhotosInternal")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SearchFunctions$findPhotosInternal$1 extends C1271c {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SearchFunctions this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SearchFunctions$findPhotosInternal$1(SearchFunctions searchFunctions, C1227c cVar) {
        super(cVar);
        this.this$0 = searchFunctions;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.findPhotosInternal((AppFunctionContext) null, (FindContentsParams) null, this);
    }
}
