package D9;

import Bb.m;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2794a;
    public final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ AtomicInteger f2795c;
    public final /* synthetic */ BiConsumer d;

    public /* synthetic */ f(int i2, String str, AtomicInteger atomicInteger, BiConsumer biConsumer) {
        this.f2794a = i2;
        this.b = str;
        this.f2795c = atomicInteger;
        this.d = biConsumer;
    }

    public final void accept(Object obj, Object obj2) {
        MdeSharingService.getInstance().requestSharedItemDeletion(this.b, (List) obj2, new m(new ArrayList(), this.f2795c, this.f2794a, this.d));
    }
}
