package a5;

import com.samsung.android.gallery.app.ui.list.picker.pictures.PicturesPickerAdapter;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import java.util.function.Consumer;

/* renamed from: a5.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0417a implements Consumer {
    public final /* synthetic */ boolean d;
    public final /* synthetic */ long e;

    public /* synthetic */ C0417a(long j2, boolean z) {
        this.d = z;
        this.e = j2;
    }

    public final void accept(Object obj) {
        PicturesPickerAdapter.lambda$getFilesIdsCursor$1(this.d, this.e, (QueryParams) obj);
    }
}
