package androidx.appfunctions.internal;

import L1.d;
import Sf.q;
import androidx.appfunctions.metadata.AppFunctionComponentsMetadata;
import androidx.appfunctions.metadata.AppFunctionSchemaMetadata;
import androidx.appfunctions.metadata.CompileTimeAppFunctionMetadata;
import java.util.Map;
import kotlin.Metadata;
import me.f;
import oe.C1217f;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R'\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048FX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Landroidx/appfunctions/internal/SchemaAppFunctionInventory;", "Landroidx/appfunctions/internal/AppFunctionInventory;", "<init>", "()V", "", "Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "schemaFunctionsMap$delegate", "Lme/f;", "getSchemaFunctionsMap", "()Ljava/util/Map;", "schemaFunctionsMap", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SchemaAppFunctionInventory implements AppFunctionInventory {
    private final f schemaFunctionsMap$delegate = d.q(new q(11, this));

    /* access modifiers changed from: private */
    public static final Map schemaFunctionsMap_delegate$lambda$0(SchemaAppFunctionInventory schemaAppFunctionInventory) {
        C1217f fVar = new C1217f();
        for (CompileTimeAppFunctionMetadata compileTimeAppFunctionMetadata : schemaAppFunctionInventory.getFunctionIdToMetadataMap().values()) {
            AppFunctionSchemaMetadata schema = compileTimeAppFunctionMetadata.getSchema();
            if (schema != null) {
                fVar.put(schema, compileTimeAppFunctionMetadata);
            }
        }
        return fVar.b();
    }

    public abstract /* synthetic */ AppFunctionComponentsMetadata getComponentsMetadata();

    public abstract /* synthetic */ Map getFunctionIdToMetadataMap();

    public final Map<AppFunctionSchemaMetadata, CompileTimeAppFunctionMetadata> getSchemaFunctionsMap() {
        return (Map) this.schemaFunctionsMap$delegate.getValue();
    }
}
