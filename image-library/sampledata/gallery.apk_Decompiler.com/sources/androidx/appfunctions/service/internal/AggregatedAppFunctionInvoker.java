package androidx.appfunctions.service.internal;

import L1.d;
import Sf.q;
import androidx.appfunctions.AppFunctionContext;
import androidx.appfunctions.AppFunctionFunctionNotFoundException;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import me.f;
import ne.C1182C;
import ne.C1196n;
import ne.v;
import qe.C1227c;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J8\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH@¢\u0006\u0004\b\u000b\u0010\fR!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\r8FX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u00138&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Landroidx/appfunctions/service/internal/AggregatedAppFunctionInvoker;", "Landroidx/appfunctions/service/internal/AppFunctionInvoker;", "<init>", "()V", "Landroidx/appfunctions/AppFunctionContext;", "appFunctionContext", "", "functionIdentifier", "", "", "parameters", "unsafeInvoke", "(Landroidx/appfunctions/AppFunctionContext;Ljava/lang/String;Ljava/util/Map;Lqe/c;)Ljava/lang/Object;", "", "supportedFunctionIds$delegate", "Lme/f;", "getSupportedFunctionIds", "()Ljava/util/Set;", "supportedFunctionIds", "", "getInvokers", "()Ljava/util/List;", "invokers", "appfunctions-service"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AggregatedAppFunctionInvoker implements AppFunctionInvoker {
    private final f supportedFunctionIds$delegate = d.q(new q(18, this));

    /* access modifiers changed from: private */
    public static final Set supportedFunctionIds_delegate$lambda$0(AggregatedAppFunctionInvoker aggregatedAppFunctionInvoker) {
        if (aggregatedAppFunctionInvoker.getInvokers().isEmpty()) {
            return v.d;
        }
        Iterable<AppFunctionInvoker> invokers = aggregatedAppFunctionInvoker.getInvokers();
        ArrayList arrayList = new ArrayList(C1196n.w0(invokers, 10));
        for (AppFunctionInvoker supportedFunctionIds : invokers) {
            arrayList.add(supportedFunctionIds.getSupportedFunctionIds());
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            while (it.hasNext()) {
                next = C1182C.b0((Set) next, (Set) it.next());
            }
            return (Set) next;
        }
        throw new UnsupportedOperationException("Empty collection can't be reduced.");
    }

    public abstract List<AppFunctionInvoker> getInvokers();

    public final Set<String> getSupportedFunctionIds() {
        return (Set) this.supportedFunctionIds$delegate.getValue();
    }

    public final Object unsafeInvoke(AppFunctionContext appFunctionContext, String str, Map<String, ? extends Object> map, C1227c cVar) {
        for (AppFunctionInvoker next : getInvokers()) {
            if (next.getSupportedFunctionIds().contains(str)) {
                return next.unsafeInvoke(appFunctionContext, str, map, cVar);
            }
        }
        throw new AppFunctionFunctionNotFoundException(C0212a.l("Unable to find ", str));
    }
}
