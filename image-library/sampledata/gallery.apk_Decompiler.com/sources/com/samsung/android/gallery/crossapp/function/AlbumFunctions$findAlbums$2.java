package com.samsung.android.gallery.crossapp.function;

import Ae.c;
import L2.a;
import Vf.A;
import androidx.appfunctions.AppFunctionContext;
import com.google.android.appfunctions.schema.photos.v1.FindAlbumsParams;
import com.samsung.android.gallery.support.utils.Log;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.u;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "", "<anonymous>", "(LVf/A;)Z"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.gallery.crossapp.function.AlbumFunctions$findAlbums$2", f = "AlbumFunctions.kt", l = {}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AlbumFunctions$findAlbums$2 extends i implements c {
    final /* synthetic */ u $albumItems;
    final /* synthetic */ AppFunctionContext $appFunctionContext;
    final /* synthetic */ FindAlbumsParams $findAlbumsParams;
    int label;
    final /* synthetic */ AlbumFunctions this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AlbumFunctions$findAlbums$2(FindAlbumsParams findAlbumsParams, AlbumFunctions albumFunctions, AppFunctionContext appFunctionContext, u uVar, C1227c cVar) {
        super(2, cVar);
        this.$findAlbumsParams = findAlbumsParams;
        this.this$0 = albumFunctions;
        this.$appFunctionContext = appFunctionContext;
        this.$albumItems = uVar;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new AlbumFunctions$findAlbums$2(this.$findAlbumsParams, this.this$0, this.$appFunctionContext, this.$albumItems, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((AlbumFunctions$findAlbums$2) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            a.A(obj);
            String str = this.$findAlbumsParams.f1295a;
            Log.d("CrossApp#Album", "findAlbums " + str + "");
            List access$queryAlbum = this.this$0.queryAlbum(this.$appFunctionContext.getContext(), this.$findAlbumsParams);
            if (access$queryAlbum != null) {
                return Boolean.valueOf(((List) this.$albumItems.d).addAll(access$queryAlbum));
            }
            return null;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
