package androidx.appfunctions.internal;

import Ae.b;
import androidx.appsearch.app.SearchResults;
import kotlin.Metadata;
import qe.C1227c;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@C1273e(c = "androidx.appfunctions.internal.AppSearchUtilsKt", f = "AppSearchUtils.kt", l = {60, 63}, m = "readAll", v = 1)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppSearchUtilsKt$readAll$1<T> extends C1271c {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    public AppSearchUtilsKt$readAll$1(C1227c cVar) {
        super(cVar);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return AppSearchUtilsKt.readAll((SearchResults) null, (b) null, this);
    }
}
