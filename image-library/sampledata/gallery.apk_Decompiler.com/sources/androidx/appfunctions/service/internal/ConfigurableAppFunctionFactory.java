package androidx.appfunctions.service.internal;

import Ae.a;
import android.content.Context;
import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001:\u0001\u0010B!\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000b\u001a\u00028\u00002\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\r\u001a\u00028\u00002\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0004\b\r\u0010\fR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u000eR\u001c\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00058\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u000f¨\u0006\u0011"}, d2 = {"Landroidx/appfunctions/service/internal/ConfigurableAppFunctionFactory;", "", "T", "Landroid/content/Context;", "context", "Lkotlin/Function0;", "defaultFactory", "<init>", "(Landroid/content/Context;LAe/a;)V", "Ljava/lang/Class;", "enclosingClass", "createFromDefault", "(Ljava/lang/Class;)Ljava/lang/Object;", "createEnclosingClass", "Landroid/content/Context;", "LAe/a;", "AppFunctionInstantiationException", "appfunctions-service"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ConfigurableAppFunctionFactory<T> {
    private final Context context;
    private final a defaultFactory;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/appfunctions/service/internal/ConfigurableAppFunctionFactory$AppFunctionInstantiationException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "errorMessage", "", "<init>", "(Ljava/lang/String;)V", "appfunctions-service"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AppFunctionInstantiationException extends RuntimeException {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AppFunctionInstantiationException(String str) {
            super(str);
            j.e(str, "errorMessage");
        }
    }

    public ConfigurableAppFunctionFactory(Context context2, a aVar) {
        j.e(context2, "context");
        this.context = context2;
        this.defaultFactory = aVar;
    }

    private final T createFromDefault(Class<T> cls) {
        a aVar = this.defaultFactory;
        if (aVar != null) {
            return aVar.invoke();
        }
        throw new AppFunctionInstantiationException("Unable to instantiate " + cls + ". Either setup a custom factory with AppFunctionConfiguration or provide a public no-arg constructor.");
    }

    public final T createEnclosingClass(Class<T> cls) {
        j.e(cls, "enclosingClass");
        this.context.getApplicationContext();
        Log.d("AppFunctions", "Unable to find custom factory for [" + cls + ']');
        return createFromDefault(cls);
    }
}
