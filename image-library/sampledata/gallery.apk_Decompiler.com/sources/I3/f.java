package I3;

import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureChoiceCmd;
import com.samsung.android.gallery.app.controller.internals.CreateMyQueryCmd;
import com.samsung.android.gallery.app.service.DownloadService;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageSaveHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.service.notification.DownloadNotificationHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d = 3;
    public final /* synthetic */ String e;
    public final /* synthetic */ Serializable f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2364h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f2365i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Object f2366j;

    public /* synthetic */ f(View view, MediaItem mediaItem, int[] iArr, String str, String str2, EventContext eventContext) {
        this.f2365i = view;
        this.g = mediaItem;
        this.f2364h = iArr;
        this.e = str;
        this.f = str2;
        this.f2366j = eventContext;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((MergeCreatureChoiceCmd) this.f2365i).lambda$unmerge$5(this.e, (List) this.f2366j, (String) this.f, (String) this.g, (String) this.f2364h);
                return;
            case 1:
                ((CreateMyQueryCmd) this.f2365i).lambda$addMyQuery$2(this.e, (String) this.f, (EventContext) this.f2366j, (String) this.g, (String) this.f2364h);
                return;
            case 2:
                ((DownloadService) this.f2365i).lambda$onDownloadFinish$2((ArrayList) this.f, this.e, (ArrayList) this.g, (DownloadNotificationHelper) this.f2364h, (Blackboard) this.f2366j);
                return;
            case 3:
                CollageSaveHelper.lambda$saveGridVideoCollage$3((View) this.f2365i, (MediaItem) this.g, (int[]) this.f2364h, this.e, (String) this.f, (EventContext) this.f2366j);
                return;
            default:
                CollageSaveHelper.lambda$saveIrregularVideoCollage$4((MediaItem) this.f2365i, (int[]) this.f, (Bitmap) this.g, this.e, (Bitmap) this.f2364h, (EventContext) this.f2366j);
                return;
        }
    }

    public /* synthetic */ f(MergeCreatureChoiceCmd mergeCreatureChoiceCmd, String str, List list, String str2, String str3, String str4) {
        this.f2365i = mergeCreatureChoiceCmd;
        this.e = str;
        this.f2366j = list;
        this.f = str2;
        this.g = str3;
        this.f2364h = str4;
    }

    public /* synthetic */ f(CreateMyQueryCmd createMyQueryCmd, String str, String str2, EventContext eventContext, String str3, String str4) {
        this.f2365i = createMyQueryCmd;
        this.e = str;
        this.f = str2;
        this.f2366j = eventContext;
        this.g = str3;
        this.f2364h = str4;
    }

    public /* synthetic */ f(DownloadService downloadService, ArrayList arrayList, String str, ArrayList arrayList2, DownloadNotificationHelper downloadNotificationHelper, Blackboard blackboard) {
        this.f2365i = downloadService;
        this.f = arrayList;
        this.e = str;
        this.g = arrayList2;
        this.f2364h = downloadNotificationHelper;
        this.f2366j = blackboard;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [int[], java.io.Serializable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ f(com.samsung.android.gallery.module.data.MediaItem r2, int[] r3, android.graphics.Bitmap r4, java.lang.String r5, android.graphics.Bitmap r6, com.samsung.android.gallery.app.controller.EventContext r7) {
        /*
            r1 = this;
            r0 = 4
            r1.d = r0
            r1.<init>()
            r1.f2365i = r2
            r1.f = r3
            r1.g = r4
            r1.e = r5
            r1.f2364h = r6
            r1.f2366j = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: I3.f.<init>(com.samsung.android.gallery.module.data.MediaItem, int[], android.graphics.Bitmap, java.lang.String, android.graphics.Bitmap, com.samsung.android.gallery.app.controller.EventContext):void");
    }
}
