package e5;

import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2632a;
    public final /* synthetic */ PicturesViewAdapter b;

    public /* synthetic */ k(PicturesViewAdapter picturesViewAdapter, int i2) {
        this.f2632a = i2;
        this.b = picturesViewAdapter;
    }

    public final Object apply(Object obj) {
        int viewPosition;
        int i2 = this.f2632a;
        int intValue = ((Integer) obj).intValue();
        PicturesViewAdapter picturesViewAdapter = this.b;
        switch (i2) {
            case 0:
                viewPosition = picturesViewAdapter.getViewPosition(intValue);
                break;
            case 1:
                return picturesViewAdapter.getMediaItemSync(intValue);
            case 2:
                viewPosition = picturesViewAdapter.getSpanSize(intValue);
                break;
            default:
                viewPosition = picturesViewAdapter.getItemHeight(intValue);
                break;
        }
        return Integer.valueOf(viewPosition);
    }
}
