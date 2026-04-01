package g7;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditList;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItem;
import com.samsung.android.gallery.module.aiedit.AiEditBixbyRequest;
import java.util.function.Predicate;

/* renamed from: g7.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0461d implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2645a;
    public final /* synthetic */ AiEditBixbyRequest b;

    public /* synthetic */ C0461d(AiEditBixbyRequest aiEditBixbyRequest, int i2) {
        this.f2645a = i2;
        this.b = aiEditBixbyRequest;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2645a;
        AiEditBixbyRequest aiEditBixbyRequest = this.b;
        AiEditItem aiEditItem = (AiEditItem) obj;
        switch (i2) {
            case 0:
                return AiEditList.lambda$requestLoadAndExecuteDirectly$10(aiEditBixbyRequest, aiEditItem);
            default:
                return aiEditItem.getType().equals(aiEditBixbyRequest.type);
        }
    }
}
