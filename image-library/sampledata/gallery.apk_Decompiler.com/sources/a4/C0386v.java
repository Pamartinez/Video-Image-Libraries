package A4;

import android.location.Address;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.library.v9.Sem111ApiCompatImpl;
import java.util.function.IntFunction;

/* renamed from: A4.v  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0386v implements IntFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2260a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0386v(int i2, Object obj) {
        this.f2260a = i2;
        this.b = obj;
    }

    public final Object apply(int i2) {
        int i7 = this.f2260a;
        Object obj = this.b;
        switch (i7) {
            case 0:
                return ((BaseListViewAdapter) obj).getMediaItemSync(i2);
            case 1:
                return ((Address) obj).getAddressLine(i2);
            case 2:
                return ((Sem111ApiCompatImpl) obj).toMimeType(i2);
            case 3:
                return Integer.toHexString(((byte[]) obj)[i2] & 255);
            default:
                return ((MediaData) obj).read(i2);
        }
    }
}
