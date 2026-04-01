package T3;

import com.samsung.android.gallery.app.controller.sharing.request.RequestDeleteFromTrash;
import java.util.List;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ RequestDeleteFromTrash f2433a;
    public final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f2434c;
    public final /* synthetic */ long d;

    public /* synthetic */ b(RequestDeleteFromTrash requestDeleteFromTrash, String str, String str2, long j2) {
        this.f2433a = requestDeleteFromTrash;
        this.b = str;
        this.f2434c = str2;
        this.d = j2;
    }

    public final void accept(Object obj, Object obj2) {
        this.f2433a.lambda$request$0(this.b, this.f2434c, this.d, (List) obj, (Integer) obj2);
    }
}
