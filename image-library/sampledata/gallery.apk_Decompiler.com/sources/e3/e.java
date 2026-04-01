package E3;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.activity.external.launcher.CameraQuickViewLauncher;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.SetAsWallpaperChooserDialogCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeLocationCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeLocationCmd2;
import com.samsung.android.gallery.app.controller.internals.EditDateAndTimeCmd;
import com.samsung.android.gallery.app.controller.internals.SetAsChooserDialogCmd;
import com.samsung.android.gallery.app.controller.sharing.ChooseSharedAlbumCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.RuntimeCompat;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ long e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2310h;

    public /* synthetic */ e(CameraQuickViewLauncher cameraQuickViewLauncher, long j2, MediaItem mediaItem, BiConsumer biConsumer) {
        this.d = 0;
        this.f = cameraQuickViewLauncher;
        this.e = j2;
        this.g = mediaItem;
        this.f2310h = biConsumer;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((CameraQuickViewLauncher) this.f).lambda$loadThumbnailSync$8(this.e, (MediaItem) this.g, (BiConsumer) this.f2310h, (Bitmap) obj);
                return;
            case 1:
                ((SetAsWallpaperChooserDialogCmd) this.f).lambda$onPreExecute$0((MediaItem[]) this.g, this.e, (EventContext) this.f2310h, (Integer) obj);
                return;
            case 2:
                ((ChangeLocationCmd) this.f).lambda$onPreExecute$0((MediaItem[]) this.g, this.e, (EventContext) this.f2310h, (Integer) obj);
                return;
            case 3:
                ((ChangeLocationCmd2) this.f).lambda$onPreExecute$0((MediaItem[]) this.g, this.e, (EventContext) this.f2310h, (Integer) obj);
                return;
            case 4:
                ((EditDateAndTimeCmd) this.f).lambda$onPreExecute$0((MediaItem[]) this.g, this.e, (EventContext) this.f2310h, (Integer) obj);
                return;
            case 5:
                ((SetAsChooserDialogCmd) this.f).lambda$onPreExecute$0((MediaItem[]) this.g, this.e, (EventContext) this.f2310h, (Integer) obj);
                return;
            case 6:
                ((ChooseSharedAlbumCmd) this.f).lambda$onPreExecute$0((MediaItem[]) this.g, this.e, (EventContext) this.f2310h, (Integer) obj);
                return;
            default:
                RuntimeCompat.lambda$du$0((HashMap) this.f, (File) this.g, (String) this.f2310h, this.e, (List) obj);
                return;
        }
    }

    public /* synthetic */ e(BaseCommand baseCommand, MediaItem[] mediaItemArr, long j2, EventContext eventContext, int i2) {
        this.d = i2;
        this.f = baseCommand;
        this.g = mediaItemArr;
        this.e = j2;
        this.f2310h = eventContext;
    }

    public /* synthetic */ e(HashMap hashMap, File file, String str, long j2) {
        this.d = 7;
        this.f = hashMap;
        this.g = file;
        this.f2310h = str;
        this.e = j2;
    }
}
