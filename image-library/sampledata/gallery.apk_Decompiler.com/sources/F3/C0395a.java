package F3;

import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import java.util.function.Function;

/* renamed from: F3.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0395a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2321a;
    public final /* synthetic */ Blackboard b;

    public /* synthetic */ C0395a(Blackboard blackboard, int i2) {
        this.f2321a = i2;
        this.b = blackboard;
    }

    public final Object apply(Object obj) {
        int i2 = this.f2321a;
        Blackboard blackboard = this.b;
        String str = (String) obj;
        switch (i2) {
            case 0:
                return DataCollectionDelegate.lambda$getInstance$0(blackboard, str);
            case 1:
                return DeviceInfo.lambda$getDisplay$0(blackboard, str);
            case 2:
                return FoldStateManager.lambda$getInstance$0(blackboard, str);
            default:
                return PickerUtil.lambda$isPickerMode$0(blackboard, str);
        }
    }
}
