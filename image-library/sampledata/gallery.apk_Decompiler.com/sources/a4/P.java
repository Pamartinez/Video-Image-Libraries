package A4;

import com.samsung.android.gallery.app.provider.GallerySearchProvider;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditList;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItem;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.database.dal.mp.impl.MpStoryApiImpl;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.tables.ClusterIndexer;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import com.samsung.android.gallery.support.utils.StorageInfo;
import java.util.Map;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class P implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StringBuilder e;

    public /* synthetic */ P(StringBuilder sb2, int i2) {
        this.d = i2;
        this.e = sb2;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        StringBuilder sb2 = this.e;
        switch (i2) {
            case 0:
                sb2.append(((MediaItem) obj).getThumbCacheKey());
                return;
            case 1:
                LabsDevManageFragment.lambda$buildGalleryPreferenceValues$54(sb2, (String) obj);
                return;
            case 2:
                LabsDevManageFragment.lambda$buildGalleryPreferenceValues$55(sb2, (String) obj);
                return;
            case 3:
                GallerySearchProvider.lambda$dumpNoMediaPath$0(".nomedia", sb2, (StorageInfo) obj);
                return;
            case 4:
                ClusterIndexer.lambda$getLog$1(sb2, (Map.Entry) obj);
                return;
            case 5:
                AiEditList.lambda$getVisibleListAnalyticsDetail$7(sb2, (AiEditItem) obj);
                return;
            case 6:
                ViewerMenuDelegate.lambda$getHash$17(sb2, (ViewerMenuItem) obj);
                return;
            default:
                MpStoryApiImpl.lambda$getRecapFilePath$7(sb2, (Integer) obj);
                return;
        }
    }
}
