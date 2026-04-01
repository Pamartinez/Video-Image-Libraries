package D9;

import com.samsung.android.sdk.mobileservice.common.result.BooleanResult;
import com.samsung.android.sdk.mobileservice.social.group.GroupApi;
import java.util.function.IntConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements GroupApi.GroupResultCallback {
    public final /* synthetic */ int d;
    public final /* synthetic */ IntConsumer e;

    public /* synthetic */ d(IntConsumer intConsumer, int i2) {
        this.d = i2;
        this.e = intConsumer;
    }

    public final void onResult(Object obj) {
        int i2 = this.d;
        IntConsumer intConsumer = this.e;
        BooleanResult booleanResult = (BooleanResult) obj;
        switch (i2) {
            case 0:
                intConsumer.accept(booleanResult.getStatus().getCode());
                return;
            default:
                intConsumer.accept(booleanResult.getStatus().getCode());
                return;
        }
    }
}
