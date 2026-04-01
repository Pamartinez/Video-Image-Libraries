package A9;

import com.samsung.android.gallery.app.controller.internals.ChangeLocationCmd2;
import com.samsung.android.gallery.app.ui.list.dragdrop.NormalDragAndDrop;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResult;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.bixby.cmd.abstraction.BaseCmd;
import com.samsung.android.gallery.module.details.EditDetailsUpdater2;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplateStory;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2768a;

    public /* synthetic */ c(int i2) {
        this.f2768a = i2;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2768a) {
            case 0:
                Log.i("RecapWorker", "cancelWorkById : " + ((UUID) obj) + " = " + ((String) obj2));
                return;
            case 1:
                ((Blackboard) obj2).postEvent(EventMessage.obtain(104, 1, 0, "location://albums"));
                return;
            case 2:
                ChangeLocationCmd2.lambda$changeLocation$4((Integer) obj, (Integer) obj2);
                return;
            case 3:
                NormalDragAndDrop.lambda$addToFavorite$6((Boolean) obj, (List) obj2);
                return;
            case 4:
                ((ViewerObjectComposite) obj2).onViewRecycled();
                return;
            case 5:
                EditDetailsUpdater2.lambda$onSave$3((Integer) obj, (Integer) obj2);
                return;
            case 6:
                BaseCmd.lambda$finishGalleryActivity$0((String) obj, (Blackboard) obj2);
                return;
            case 7:
                ((ViewerObjectComposite) obj2).onViewRecycled();
                return;
            case 8:
                ((ViewerObjectComposite) obj2).onFinalized();
                return;
            case 9:
                ((ClusterResult) obj2).onPause();
                return;
            case 10:
                ((ClusterResult) obj2).resetItemClicked();
                return;
            case 11:
                ((ClusterResult) obj2).onDestroy();
                return;
            case 12:
                ((ClusterResult) obj2).onResume();
                return;
            default:
                RecapTemplateStory.lambda$buildScenes$0((String) obj, (RecapImage) obj2);
                return;
        }
    }
}
