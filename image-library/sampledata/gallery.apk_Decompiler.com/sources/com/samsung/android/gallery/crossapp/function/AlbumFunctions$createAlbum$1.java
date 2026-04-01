package com.samsung.android.gallery.crossapp.function;

import androidx.appfunctions.AppFunctionContext;
import com.google.android.appfunctions.schema.photos.v1.CreateAlbumParams;
import kotlin.Metadata;
import qe.C1227c;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@C1273e(c = "com.samsung.android.gallery.crossapp.function.AlbumFunctions", f = "AlbumFunctions.kt", l = {171}, m = "createAlbum")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AlbumFunctions$createAlbum$1 extends C1271c {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AlbumFunctions this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AlbumFunctions$createAlbum$1(AlbumFunctions albumFunctions, C1227c cVar) {
        super(cVar);
        this.this$0 = albumFunctions;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.createAlbum((AppFunctionContext) null, (CreateAlbumParams) null, this);
    }
}
