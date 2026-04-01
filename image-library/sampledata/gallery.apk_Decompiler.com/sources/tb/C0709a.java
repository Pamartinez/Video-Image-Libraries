package tb;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultContainer;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureProcessingHandler;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.widget.dialog.AppChooserBuilder;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import java.util.function.Function;

/* renamed from: tb.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0709a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3289a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0709a(int i2, Object obj) {
        this.f3289a = i2;
        this.b = obj;
    }

    public final Object apply(Object obj) {
        int i2 = this.f3289a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return AppChooserBuilder.lambda$queryPackages$2((PackageManager) obj2, (ResolveInfo) obj);
            case 1:
                return ((GalleryListView) obj2).findViewHolderForAdapterPosition(((Integer) obj).intValue());
            case 2:
                return ((SearchClusterResultContainer) obj2).lambda$getSearchResultCluster$3((ClusterResultType) obj);
            default:
                return ((ObjectCaptureProcessingHandler) obj2).lambda$hideLoadingView$2((ImageView) obj);
        }
    }
}
