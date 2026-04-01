package g7;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditList;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItem;
import java.util.function.Consumer;

/* renamed from: g7.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0462e implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ AiEditList e;

    public /* synthetic */ C0462e(AiEditList aiEditList, int i2) {
        this.d = i2;
        this.e = aiEditList;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        AiEditList aiEditList = this.e;
        AiEditItem aiEditItem = (AiEditItem) obj;
        switch (i2) {
            case 0:
                aiEditList.lambda$updateMediaItemAndVisibility$0(aiEditItem);
                return;
            default:
                aiEditList.lambda$recycle$5(aiEditItem);
                return;
        }
    }
}
