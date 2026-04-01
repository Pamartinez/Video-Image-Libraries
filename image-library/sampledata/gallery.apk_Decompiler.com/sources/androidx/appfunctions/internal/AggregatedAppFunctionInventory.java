package androidx.appfunctions.internal;

import L1.d;
import androidx.appfunctions.metadata.AppFunctionComponentsMetadata;
import androidx.appfunctions.metadata.CompileTimeAppFunctionMetadata;
import j.C0216a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import me.f;
import ne.C1196n;
import ne.C1203u;
import ne.z;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R'\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048FX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u001b\u0010\u0010\u001a\u00020\f8FX\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u00118&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Landroidx/appfunctions/internal/AggregatedAppFunctionInventory;", "Landroidx/appfunctions/internal/AppFunctionInventory;", "<init>", "()V", "", "", "Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "functionIdToMetadataMap$delegate", "Lme/f;", "getFunctionIdToMetadataMap", "()Ljava/util/Map;", "functionIdToMetadataMap", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "componentsMetadata$delegate", "getComponentsMetadata", "()Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "componentsMetadata", "", "getInventories", "()Ljava/util/List;", "inventories", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AggregatedAppFunctionInventory implements AppFunctionInventory {
    private final f componentsMetadata$delegate = d.q(new C0216a(this, 1));
    private final f functionIdToMetadataMap$delegate = d.q(new C0216a(this, 0));

    /* access modifiers changed from: private */
    public static final AppFunctionComponentsMetadata componentsMetadata_delegate$lambda$0(AggregatedAppFunctionInventory aggregatedAppFunctionInventory) {
        if (aggregatedAppFunctionInventory.getInventories().isEmpty()) {
            return new AppFunctionComponentsMetadata((Map) null, 1, (e) null);
        }
        Iterable<AppFunctionInventory> inventories = aggregatedAppFunctionInventory.getInventories();
        ArrayList arrayList = new ArrayList(C1196n.w0(inventories, 10));
        for (AppFunctionInventory componentsMetadata : inventories) {
            arrayList.add(componentsMetadata.getComponentsMetadata().getDataTypes());
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            while (it.hasNext()) {
                next = z.c0((Map) next, (Map) it.next());
            }
            return new AppFunctionComponentsMetadata((Map) next);
        }
        throw new UnsupportedOperationException("Empty collection can't be reduced.");
    }

    /* access modifiers changed from: private */
    public static final Map functionIdToMetadataMap_delegate$lambda$0(AggregatedAppFunctionInventory aggregatedAppFunctionInventory) {
        if (aggregatedAppFunctionInventory.getInventories().isEmpty()) {
            return C1203u.d;
        }
        Iterable<AppFunctionInventory> inventories = aggregatedAppFunctionInventory.getInventories();
        ArrayList arrayList = new ArrayList(C1196n.w0(inventories, 10));
        for (AppFunctionInventory functionIdToMetadataMap : inventories) {
            arrayList.add(functionIdToMetadataMap.getFunctionIdToMetadataMap());
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            while (it.hasNext()) {
                next = z.c0((Map) next, (Map) it.next());
            }
            return (Map) next;
        }
        throw new UnsupportedOperationException("Empty collection can't be reduced.");
    }

    public final AppFunctionComponentsMetadata getComponentsMetadata() {
        return (AppFunctionComponentsMetadata) this.componentsMetadata$delegate.getValue();
    }

    public final Map<String, CompileTimeAppFunctionMetadata> getFunctionIdToMetadataMap() {
        return (Map) this.functionIdToMetadataMap$delegate.getValue();
    }

    public abstract List<AppFunctionInventory> getInventories();
}
