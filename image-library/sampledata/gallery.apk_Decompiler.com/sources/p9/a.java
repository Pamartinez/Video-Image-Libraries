package p9;

import com.samsung.android.gallery.module.idleworker.jobs.CacheTrimJob;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List f3285a;
    public final /* synthetic */ List b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ List f3286c;
    public final /* synthetic */ ArrayList d;

    public /* synthetic */ a(List list, List list2, List list3, ArrayList arrayList) {
        this.f3285a = list;
        this.b = list2;
        this.f3286c = list3;
        this.d = arrayList;
    }

    public final void accept(Object obj, Object obj2) {
        CacheTrimJob.lambda$execute2$1(this.f3285a, this.b, this.f3286c, this.d, (String) obj, (Long) obj2);
    }
}
