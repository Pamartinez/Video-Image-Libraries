package androidx.appfunctions.service.internal;

import Ad.C0721b;
import L1.d;
import androidx.appfunctions.internal.ClassUtilsKt;
import kotlin.Metadata;
import me.f;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\t\u001a\u00020\u00048@X\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Landroidx/appfunctions/service/internal/ServiceDependencies;", "", "<init>", "()V", "Landroidx/appfunctions/service/internal/AggregatedAppFunctionInvoker;", "aggregatedAppFunctionInvoker$delegate", "Lme/f;", "getAggregatedAppFunctionInvoker$appfunctions_service", "()Landroidx/appfunctions/service/internal/AggregatedAppFunctionInvoker;", "aggregatedAppFunctionInvoker", "appfunctions-service"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ServiceDependencies {
    public static final ServiceDependencies INSTANCE = new ServiceDependencies();
    private static final f aggregatedAppFunctionInvoker$delegate = d.q(new C0721b(28));

    private ServiceDependencies() {
    }

    /* access modifiers changed from: private */
    public static final AggregatedAppFunctionInvoker aggregatedAppFunctionInvoker_delegate$lambda$0() {
        return (AggregatedAppFunctionInvoker) ClassUtilsKt.findImpl(AggregatedAppFunctionInvoker.class, "$", "_Impl");
    }

    public final AggregatedAppFunctionInvoker getAggregatedAppFunctionInvoker$appfunctions_service() {
        return (AggregatedAppFunctionInvoker) aggregatedAppFunctionInvoker$delegate.getValue();
    }
}
