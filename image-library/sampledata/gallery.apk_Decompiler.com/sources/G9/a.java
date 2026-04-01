package G9;

import android.content.Context;
import android.os.storage.StorageVolume;
import com.samsung.android.gallery.app.ui.dialog.CreateNewDialog;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.module.microsoft.YourPhone;
import com.samsung.android.gallery.support.library.v0.Sem80ApiCompatImpl;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2820a;
    public final /* synthetic */ Context b;

    public /* synthetic */ a(Context context, int i2) {
        this.f2820a = i2;
        this.b = context;
    }

    public final Object apply(Object obj) {
        switch (this.f2820a) {
            case 0:
                return Boolean.valueOf(YourPhone.computeConnection(this.b));
            case 1:
                return Sem80ApiCompatImpl.lambda$getMountState$2(this.b, (StorageVolume) obj);
            case 2:
                return DeepSkyHelper.lambda$getTextExtractionDrawHelper$1(this.b, (String) obj);
            case 3:
                return ((CategoryPropertyHelper) obj).getCategoryTitle(this.b);
            case 4:
                return CreateNewDialog.lambda$inflateView$0(this.b, (String) obj);
            default:
                return this.b.getDrawable(((Integer) obj).intValue());
        }
    }
}
