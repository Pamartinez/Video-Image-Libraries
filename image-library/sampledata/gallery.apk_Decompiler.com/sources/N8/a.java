package n8;

import com.samsung.android.gallery.database.dal.cmh.helper.CmhStoryApiImpl;
import com.samsung.android.gallery.database.dal.mp.impl.MpStoryApiImpl;
import java.util.function.IntConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements IntConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2677a;
    public final /* synthetic */ String[] b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int[] f2678c;

    public /* synthetic */ a(String[] strArr, int[] iArr, int i2) {
        this.f2677a = i2;
        this.b = strArr;
        this.f2678c = iArr;
    }

    public final void accept(int i2) {
        switch (this.f2677a) {
            case 0:
                CmhStoryApiImpl.lambda$deleteStoryFromSaType$1(this.b, this.f2678c, i2);
                return;
            default:
                MpStoryApiImpl.lambda$deleteStoryFromSaType$2(this.b, this.f2678c, i2);
                return;
        }
    }
}
