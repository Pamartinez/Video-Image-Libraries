package H3;

import com.samsung.android.gallery.module.dataset.AlbumDataHelper;
import java.util.function.Consumer;

/* renamed from: H3.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0396a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumDataHelper.CropRectInfo e;

    public /* synthetic */ C0396a(AlbumDataHelper.CropRectInfo cropRectInfo, int i2) {
        this.d = i2;
        this.e = cropRectInfo;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        AlbumDataHelper.CropRectInfo cropRectInfo = this.e;
        String str = (String) obj;
        switch (i2) {
            case 0:
                cropRectInfo.setWidth(Integer.parseInt(str));
                return;
            default:
                cropRectInfo.setHeight(Integer.parseInt(str));
                return;
        }
    }
}
