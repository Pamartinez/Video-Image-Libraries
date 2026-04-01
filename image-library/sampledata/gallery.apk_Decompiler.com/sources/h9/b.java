package h9;

import android.content.Context;
import android.net.Uri;
import com.samsung.android.gallery.module.fileio.database.helper.DatabaseOperation;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ DatabaseOperation d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Uri g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f3272h;

    public /* synthetic */ b(DatabaseOperation databaseOperation, int i2, Object obj, Uri uri, Object obj2) {
        this.d = databaseOperation;
        this.e = i2;
        this.f = obj;
        this.g = uri;
        this.f3272h = obj2;
    }

    public final void accept(Object obj) {
        this.d.lambda$immediateApply$7(this.e, this.f, this.g, this.f3272h, (Context) obj);
    }
}
