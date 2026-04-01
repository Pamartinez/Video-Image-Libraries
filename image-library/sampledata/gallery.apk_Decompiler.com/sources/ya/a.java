package Ya;

import com.samsung.android.gallery.module.trash.factory.MpSTrashFactory;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ HashMap e;
    public final /* synthetic */ String f;

    public /* synthetic */ a(int i2, String str, HashMap hashMap) {
        this.d = i2;
        this.e = hashMap;
        this.f = str;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.put(this.f, (String) obj);
                return;
            default:
                MpSTrashFactory.lambda$getInternalTrash$0(this.e, this.f, (String) obj);
                return;
        }
    }
}
