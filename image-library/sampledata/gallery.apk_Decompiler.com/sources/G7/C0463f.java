package g7;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditList;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AbsRemasterAiEdit;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItem;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import java.util.function.Predicate;

/* renamed from: g7.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0463f implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2646a;
    public final /* synthetic */ AiEditType b;

    public /* synthetic */ C0463f(AiEditType aiEditType, int i2) {
        this.f2646a = i2;
        this.b = aiEditType;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2646a;
        AiEditType aiEditType = this.b;
        switch (i2) {
            case 0:
                return AiEditList.lambda$executeIntelligenceItem$6(aiEditType, (AiEditItem) obj);
            case 1:
                return ((AbsRemasterAiEdit) obj).getType().equals(aiEditType);
            default:
                return ((AbsRemasterAiEdit) obj).getType().equals(aiEditType);
        }
    }
}
