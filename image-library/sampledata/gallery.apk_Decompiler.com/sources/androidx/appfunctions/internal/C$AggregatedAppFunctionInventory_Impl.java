package androidx.appfunctions.internal;

import com.samsung.android.gallery.crossapp.function.C$AlbumFunctions_AppFunctionInventory;
import com.samsung.android.gallery.crossapp.function.C$PhotoFunctions_AppFunctionInventory;
import com.samsung.android.gallery.crossapp.function.C$SearchFunctions_AppFunctionInventory;
import java.util.List;
import kotlin.Metadata;
import ne.C1195m;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"androidx/appfunctions/internal/$AggregatedAppFunctionInventory_Impl", "Landroidx/appfunctions/internal/AggregatedAppFunctionInventory;", "<init>", "()V", "inventories", "", "Landroidx/appfunctions/internal/AppFunctionInventory;", "getInventories", "()Ljava/util/List;", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* renamed from: androidx.appfunctions.internal.$AggregatedAppFunctionInventory_Impl  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$AggregatedAppFunctionInventory_Impl extends AggregatedAppFunctionInventory {
    private final List<AppFunctionInventory> inventories = C1195m.q0(new C$AlbumFunctions_AppFunctionInventory(), new C$PhotoFunctions_AppFunctionInventory(), new C$SearchFunctions_AppFunctionInventory());

    public List<AppFunctionInventory> getInventories() {
        return this.inventories;
    }
}
