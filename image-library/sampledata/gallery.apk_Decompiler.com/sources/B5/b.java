package B5;

import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ ISearchPicturesView e;

    public /* synthetic */ b(ISearchPicturesView iSearchPicturesView, int i2) {
        this.d = i2;
        this.e = iSearchPicturesView;
    }

    public final Object get() {
        int i2 = this.d;
        ISearchPicturesView iSearchPicturesView = this.e;
        switch (i2) {
            case 0:
                return Boolean.valueOf(!iSearchPicturesView.isActive());
            default:
                return iSearchPicturesView.getLocationKey();
        }
    }
}
