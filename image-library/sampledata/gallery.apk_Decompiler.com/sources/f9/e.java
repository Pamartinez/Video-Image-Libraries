package F9;

import android.net.Uri;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.internals.RemoveFromResultCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCreateStory;
import com.samsung.android.gallery.app.ui.dialog.hidescene.HideSceneSelectionDialog;
import com.samsung.android.gallery.app.ui.list.stories.category.CategoryTransition;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureFileHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.EditDetailsUpdater;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.module.mtp.MtpClient;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2804h;

    public /* synthetic */ e(Object obj, Object obj2, int i2, Object obj3, int i7) {
        this.d = i7;
        this.f = obj;
        this.g = obj2;
        this.e = i2;
        this.f2804h = obj3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((MetadataManager) this.f).lambda$loadMotionBitmap$2((FileItemInterface) this.g, this.e, (BiConsumer) this.f2804h);
                return;
            case 1:
                ((ObjectCaptureFileHandler) this.f).lambda$onSaveFileScanDone$12((MediaItem) this.g, (Uri) this.f2804h, this.e);
                return;
            case 2:
                ((RemoveFromResultCmd) this.f).lambda$removeFromResult$1((String) this.g, (MediaItem[]) this.f2804h, this.e);
                return;
            case 3:
                ((RequestCreateStory) this.f).lambda$createStory$11((ArrayList) this.g, this.e, (Map) this.f2804h);
                return;
            case 4:
                ((CategoryTransition) this.f).lambda$startSimpleAutoScroller$1((String) this.g, this.e, (Consumer) this.f2804h);
                return;
            case 5:
                ((EditDetailsUpdater) this.f).lambda$onTitleAndDateTimeUpdated$3((MediaItem) this.g, (String) this.f2804h, this.e);
                return;
            case 6:
                ((ViewerMenuDelegate) this.f).lambda$onMenuItemSelected$18((MediaItem) this.g, this.e, (View) this.f2804h);
                return;
            case 7:
                ((ArrayList) this.f).add(((MtpClient) this.g).getThumbnail(((MtpClient) this.g).getDeviceNameFromPath((String) this.f2804h), this.e));
                return;
            default:
                ((HideSceneSelectionDialog) this.f).lambda$bindView$0((RecyclerView) this.g, (MediaItem[]) this.f2804h, this.e);
                return;
        }
    }

    public /* synthetic */ e(Object obj, Object obj2, Object obj3, int i2, int i7) {
        this.d = i7;
        this.f = obj;
        this.g = obj2;
        this.f2804h = obj3;
        this.e = i2;
    }
}
