package n8;

import com.samsung.android.gallery.database.dal.cmh.helper.CmhStoryApiImpl;
import com.samsung.android.gallery.database.dal.mp.impl.MpStoryApiImpl;
import java.util.function.IntConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements IntConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2679a;
    public final /* synthetic */ StringBuilder b;

    public /* synthetic */ b(StringBuilder sb2, int i2) {
        this.f2679a = i2;
        this.b = sb2;
    }

    public final void accept(int i2) {
        int i7 = this.f2679a;
        StringBuilder sb2 = this.b;
        switch (i7) {
            case 0:
                CmhStoryApiImpl.lambda$getStoryIdsFromSaType$5(sb2, i2);
                return;
            default:
                MpStoryApiImpl.lambda$getStoryIdsFromSaType$6(sb2, i2);
                return;
        }
    }
}
