package D3;

import com.samsung.android.gallery.app.activity.external.CropImageActivity;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ CropImageActivity e;

    public /* synthetic */ a(CropImageActivity cropImageActivity, int i2) {
        this.d = i2;
        this.e = cropImageActivity;
    }

    public final Object get() {
        int i2 = this.d;
        CropImageActivity cropImageActivity = this.e;
        switch (i2) {
            case 0:
                return cropImageActivity.lambda$onStart$0();
            default:
                return cropImageActivity.getIntent();
        }
    }
}
