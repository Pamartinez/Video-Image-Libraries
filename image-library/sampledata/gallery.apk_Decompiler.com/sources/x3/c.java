package X3;

import com.samsung.android.gallery.app.provider.SharedItemUploader;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.module.fileio.abstraction.FileOperation;
import com.samsung.android.sum.core.buffer.SharedBufferManager;
import java.util.function.IntPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements IntPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2467a;
    public final /* synthetic */ int b;

    public /* synthetic */ c(int i2, int i7) {
        this.f2467a = i7;
        this.b = i2;
    }

    public final boolean test(int i2) {
        int i7 = this.f2467a;
        int i8 = this.b;
        switch (i7) {
            case 0:
                return SharedItemUploader.lambda$isSefTypeChanged$5(i8, i2);
            case 1:
                return SharedBufferManager.lambda$colorFormatFromHalFormat$0(i8, i2);
            case 2:
                return PicturesFragment.lambda$hasInValidOldGridSize$7(i8, i2);
            default:
                return FileOperation.lambda$isActiveAll$0(i8, i2);
        }
    }
}
