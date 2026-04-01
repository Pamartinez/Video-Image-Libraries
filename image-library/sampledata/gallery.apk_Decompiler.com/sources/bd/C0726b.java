package Bd;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.hardware.HardwareBuffer;
import android.media.Image;
import android.util.Pair;
import com.samsung.android.gallery.app.ui.list.dragdrop.TwoHandedDragAndDrop;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.sum.core.buffer.BufferExtension;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MediaBufferFileReader;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.PluginDescriptorPair;
import com.samsung.android.sum.core.filter.StaplePluginFilter;
import com.samsung.android.vexfwk.sdk.docscan.VexFwkDocDetector;
import com.samsung.android.vexfwk.sdk.imageremovalsuggestion.VexFwkImageRemovalSuggestion;
import com.samsung.android.vexfwk.sdk.imagesegmentation.VexFwkHumanSegmentor;
import com.samsung.android.vexfwk.sdk.imgclipper.VexFwkImageClipper;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjSegmentor;
import com.samsung.android.vexfwk.sdk.winedetector.VexFwkWineDetector;
import com.samsung.scsp.framework.core.api.ResponsiveJob;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.media.api.OneDriveRangeUploadManager;
import java.util.function.Supplier;

/* renamed from: Bd.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0726b implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ C0726b(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return VexFwkWineDetector.detectWineImpl$lambda$8(this.e, (VexFwkWineDetector) this.f);
            case 1:
                return ((TwoHandedDragAndDrop) this.e).lambda$getSelectedItemsSupplier$0((ClipData) this.f);
            case 2:
                return ((BufferExtension) this.e).lambda$new$21((Image) this.f);
            case 3:
                return BufferExtension.lambda$findAvailableBinaryKey$49((Class) this.e, (Class) this.f);
            case 4:
                return ((BufferExtension) this.e).lambda$new$16((HardwareBuffer) this.f);
            case 5:
                return ((MediaBufferFileReader) this.e).lambda$read$2((String) this.f);
            case 6:
                return ((MutableMediaBuffer) this.e).lambda$containFlags$2((int[]) this.f);
            case 7:
                return ((PluginDescriptorPair) this.e).lambda$getExtra$4((String) this.f);
            case 8:
                return StaplePluginFilter.lambda$run$0((MediaBuffer) this.e, (MutableMediaBuffer) this.f);
            case 9:
                return VexFwkDocDetector.detectDocumentImpl$lambda$11(this.e, (VexFwkDocDetector) this.f);
            case 10:
                return ResponsiveJob.lambda$onStream$0((HttpRequest) this.e, (String) this.f);
            case 11:
                return OneDriveRangeUploadManager.lambda$getUploadSessionInfo$7((Pair) this.e, (String) this.f);
            case 12:
                return ((PicturesViewAdapter) this.e).lambda$recalculatePosition$10((GalleryGridLayoutManager) this.f);
            case 13:
                return VexFwkImageRemovalSuggestion.getMask$lambda$9((Bitmap) this.e, (VexFwkImageRemovalSuggestion) this.f);
            case 14:
                return VexFwkHumanSegmentor.doSegmentationImpl$lambda$9(this.e, (VexFwkHumanSegmentor) this.f);
            case 15:
                return VexFwkImageClipper.objectCapture$lambda$8((Bitmap) this.e, (VexFwkImageClipper) this.f);
            default:
                return VexFwkObjSegmentor.segmentObject$lambda$6((Bitmap) this.e, (VexFwkObjSegmentor) this.f);
        }
    }
}
