package androidx.appfunctions.internal;

import androidx.appfunctions.metadata.AppFunctionComponentsMetadata;
import androidx.appfunctions.metadata.CompileTimeAppFunctionMetadata;
import java.util.Map;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001R\u001e\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/appfunctions/internal/AppFunctionInventory;", "", "functionIdToMetadataMap", "", "", "Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "getFunctionIdToMetadataMap", "()Ljava/util/Map;", "componentsMetadata", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "getComponentsMetadata", "()Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface AppFunctionInventory {
    AppFunctionComponentsMetadata getComponentsMetadata();

    Map<String, CompileTimeAppFunctionMetadata> getFunctionIdToMetadataMap();
}
