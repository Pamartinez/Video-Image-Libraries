package y5;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.search.pictures.creature.CreaturePicturesDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.FaceClusterMergeDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionMotionPhotoHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.directorsview.DirectorsViewHandler;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.plugins.compare.CompareActivity;
import com.samsung.android.gallery.plugins.filebrowser.FileListFragment;
import com.samsung.android.gallery.plugins.filebrowser.LogViewFragment;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((CreaturePicturesDelegate) obj2).lambda$new$0(obj);
                return;
            case 1:
                ((TextExtractionMotionPhotoHandler) obj2).lambda$onMotionPlayViewerChanged$7((EventContext) obj);
                return;
            case 2:
                ((RecapImage) obj).mTargetType = (RecapImage.TargetCandidate) obj2;
                return;
            case 3:
                ((CompareActivity) obj2).lambda$onActivityResult$6((MediaItem) obj);
                return;
            case 4:
                ((FaceClusterMergeDelegate) obj2).lambda$loadClusterItem$2((QueryParams) obj);
                return;
            case 5:
                ((DirectorsViewHandler) obj2).lambda$updateMainText$3((ShotMode) obj);
                return;
            case 6:
                ((FileListFragment) obj2).onClick((String) obj);
                return;
            default:
                ((LogViewFragment) obj2).onAdapterStateChanged(((Integer) obj).intValue());
                return;
        }
    }
}
