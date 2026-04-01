package D3;

import android.database.Cursor;
import android.view.View;
import com.samsung.android.gallery.app.activity.external.StoriesViewNavigatorDelegate;
import com.samsung.android.gallery.app.controller.album.ReorderAlbumCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.publisher.CursorPublisher;
import com.samsung.android.gallery.module.publisher.StoriesDataPublisher;
import com.samsung.android.gallery.module.publisher.StoriesDataPublisherV2;
import com.samsung.android.gallery.module.remotegallery.RemoteClient;
import com.samsung.android.gallery.module.story.ondemand.OnDemandStoryProvider;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.widget.photoview.ImageProcessor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ long e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2298h;

    public /* synthetic */ j(RemoteClient remoteClient, long j2, SecureFile secureFile, AtomicBoolean atomicBoolean) {
        this.d = 6;
        this.f = remoteClient;
        this.e = j2;
        this.g = secureFile;
        this.f2298h = atomicBoolean;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((StoriesViewNavigatorDelegate) this.f).lambda$onHandle$1((AtomicInteger) this.g, (AtomicReference) this.f2298h, this.e);
                return;
            case 1:
                ((ReorderAlbumCmd) this.f).lambda$updateAlbumOrder$0((ArrayList) this.g, this.e, (MediaItem) this.f2298h);
                return;
            case 2:
                ((CursorPublisher) this.f).lambda$publishRecentFileData$7((Cursor[]) this.g, this.e, (String) this.f2298h);
                return;
            case 3:
                ((StoriesDataPublisher) this.f).lambda$publishStoryHideRuleData$7((Cursor[]) this.g, (Cursor[]) this.f2298h, this.e);
                return;
            case 4:
                ((StoriesDataPublisherV2) this.f).lambda$publishStoryHideRuleData$2((Cursor[]) this.g, (Cursor[]) this.f2298h, this.e);
                return;
            case 5:
                ((ImageProcessor) this.f).lambda$initTileTask$2((View) this.g, (Integer) this.f2298h, this.e);
                return;
            case 6:
                ((RemoteClient) this.f).lambda$download$4(this.e, (SecureFile) this.g, (AtomicBoolean) this.f2298h);
                return;
            default:
                ((OnDemandStoryProvider) this.f).lambda$setBlockListAtTrip$2((ArrayList) this.g, (ArrayList) this.f2298h, this.e);
                return;
        }
    }

    public /* synthetic */ j(Object obj, Object obj2, long j2, Object obj3, int i2) {
        this.d = i2;
        this.f = obj;
        this.g = obj2;
        this.e = j2;
        this.f2298h = obj3;
    }

    public /* synthetic */ j(Object obj, Object obj2, Serializable serializable, long j2, int i2) {
        this.d = i2;
        this.f = obj;
        this.g = obj2;
        this.f2298h = serializable;
        this.e = j2;
    }
}
