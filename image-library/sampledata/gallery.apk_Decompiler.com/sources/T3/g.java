package T3;

import com.samsung.android.gallery.app.controller.sharing.request.RequestRestoreFromTrash;
import java.util.List;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ RequestRestoreFromTrash f2439a;
    public final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f2440c;
    public final /* synthetic */ List d;
    public final /* synthetic */ long e;

    public /* synthetic */ g(RequestRestoreFromTrash requestRestoreFromTrash, String str, String str2, List list, long j2) {
        this.f2439a = requestRestoreFromTrash;
        this.b = str;
        this.f2440c = str2;
        this.d = list;
        this.e = j2;
    }

    public final void accept(Object obj, Object obj2) {
        this.f2439a.lambda$request$0(this.b, this.f2440c, this.d, this.e, (List) obj, (Integer) obj2);
    }
}
