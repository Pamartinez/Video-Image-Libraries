package J9;

import android.database.sqlite.SQLiteDatabase;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.myquery.SearchMyQuery;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ String d;
    public final /* synthetic */ String e;
    public final /* synthetic */ int f;
    public final /* synthetic */ SearchMyQuery.CREATE_TYPE g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ MediaItem f2829h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ AtomicLong f2830i;

    public /* synthetic */ a(String str, String str2, int i2, SearchMyQuery.CREATE_TYPE create_type, MediaItem mediaItem, AtomicLong atomicLong) {
        this.d = str;
        this.e = str2;
        this.f = i2;
        this.g = create_type;
        this.f2829h = mediaItem;
        this.f2830i = atomicLong;
    }

    public final void accept(Object obj) {
        SearchMyQuery.lambda$insert$0(this.d, this.e, this.f, this.g, this.f2829h, this.f2830i, (SQLiteDatabase) obj);
    }
}
