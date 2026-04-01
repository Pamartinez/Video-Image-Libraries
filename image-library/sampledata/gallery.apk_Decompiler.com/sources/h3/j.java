package H3;

import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.controller.externals.FetchContentsForKnoxCmd;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaItem[] e;
    public final /* synthetic */ long f;
    public final /* synthetic */ EventContext g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object[] f2334h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ BaseCommand f2335i;

    public /* synthetic */ j(BaseCommand baseCommand, MediaItem[] mediaItemArr, long j2, EventContext eventContext, Object[] objArr, int i2) {
        this.d = i2;
        this.f2335i = baseCommand;
        this.e = mediaItemArr;
        this.f = j2;
        this.g = eventContext;
        this.f2334h = objArr;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((FileOpCmd) this.f2335i).lambda$onPreExecute$0(this.e, this.f, this.g, this.f2334h, (Integer) obj);
                return;
            case 1:
                ((FetchContentsForKnoxCmd) this.f2335i).lambda$onPreExecute$0(this.e, this.f, this.g, this.f2334h, (Integer) obj);
                return;
            default:
                ((ShareViaCmd) this.f2335i).lambda$onPreExecute$1(this.f2334h, this.g, this.e, this.f, (Integer) obj);
                return;
        }
    }

    public /* synthetic */ j(ShareViaCmd shareViaCmd, Object[] objArr, EventContext eventContext, MediaItem[] mediaItemArr, long j2) {
        this.d = 2;
        this.f2335i = shareViaCmd;
        this.f2334h = objArr;
        this.g = eventContext;
        this.e = mediaItemArr;
        this.f = j2;
    }
}
