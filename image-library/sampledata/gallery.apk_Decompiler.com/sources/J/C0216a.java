package j;

import Ae.a;
import androidx.appfunctions.internal.AggregatedAppFunctionInventory;

/* renamed from: j.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0216a implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ AggregatedAppFunctionInventory e;

    public /* synthetic */ C0216a(AggregatedAppFunctionInventory aggregatedAppFunctionInventory, int i2) {
        this.d = i2;
        this.e = aggregatedAppFunctionInventory;
    }

    public final Object invoke() {
        int i2 = this.d;
        AggregatedAppFunctionInventory aggregatedAppFunctionInventory = this.e;
        switch (i2) {
            case 0:
                return AggregatedAppFunctionInventory.functionIdToMetadataMap_delegate$lambda$0(aggregatedAppFunctionInventory);
            default:
                return AggregatedAppFunctionInventory.componentsMetadata_delegate$lambda$0(aggregatedAppFunctionInventory);
        }
    }
}
