package androidx.appfunctions.internal;

import androidx.appsearch.app.GlobalSearchSession;
import java.util.Set;
import kotlin.Metadata;
import qe.C1227c;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@C1273e(c = "androidx.appfunctions.internal.AppSearchAppFunctionReader", f = "AppSearchAppFunctionReader.kt", l = {214}, m = "searchTopLevelComponent", v = 1)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppSearchAppFunctionReader$searchTopLevelComponent$1 extends C1271c {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AppSearchAppFunctionReader this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppSearchAppFunctionReader$searchTopLevelComponent$1(AppSearchAppFunctionReader appSearchAppFunctionReader, C1227c cVar) {
        super(cVar);
        this.this$0 = appSearchAppFunctionReader;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.searchTopLevelComponent((GlobalSearchSession) null, (Set<String>) null, this);
    }
}
