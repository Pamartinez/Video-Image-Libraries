package androidx.appfunctions.internal;

import android.content.Context;
import androidx.appfunctions.metadata.AppFunctionDeprecationMetadata;
import androidx.appfunctions.metadata.AppFunctionMetadata;
import androidx.appfunctions.metadata.CompileTimeAppFunctionMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import qe.C1227c;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H@¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/appfunctions/internal/AppFunctionMetadataUtils;", "", "<init>", "()V", "Landroid/content/Context;", "context", "", "functionIdentifier", "Landroidx/appfunctions/metadata/AppFunctionMetadata;", "getAppFunctionMetadata", "(Landroid/content/Context;Ljava/lang/String;Lqe/c;)Ljava/lang/Object;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionMetadataUtils {
    public static final AppFunctionMetadataUtils INSTANCE = new AppFunctionMetadataUtils();

    private AppFunctionMetadataUtils() {
    }

    public final Object getAppFunctionMetadata(Context context, String str, C1227c cVar) {
        Dependencies dependencies = Dependencies.INSTANCE;
        AppFunctionInventory appFunctionInventory$appfunctions = dependencies.getAppFunctionInventory$appfunctions();
        if (appFunctionInventory$appfunctions != null) {
            CompileTimeAppFunctionMetadata compileTimeAppFunctionMetadata = appFunctionInventory$appfunctions.getFunctionIdToMetadataMap().get(str);
            if (compileTimeAppFunctionMetadata == null) {
                return null;
            }
            String id = compileTimeAppFunctionMetadata.getId();
            String packageName = context.getPackageName();
            j.d(packageName, "getPackageName(...)");
            return new AppFunctionMetadata(id, packageName, compileTimeAppFunctionMetadata.isEnabledByDefault(), compileTimeAppFunctionMetadata.getSchema(), compileTimeAppFunctionMetadata.getParameters(), compileTimeAppFunctionMetadata.getResponse(), appFunctionInventory$appfunctions.getComponentsMetadata(), compileTimeAppFunctionMetadata.getDescription(), (AppFunctionDeprecationMetadata) null, 256, (e) null);
        }
        AppSearchAppFunctionReader appSearchAppFunctionReader = new AppSearchAppFunctionReader(context, dependencies.getSchemaAppFunctionInventory$appfunctions());
        String packageName2 = context.getPackageName();
        j.d(packageName2, "getPackageName(...)");
        return appSearchAppFunctionReader.getAppFunctionMetadata(str, packageName2, cVar);
    }
}
