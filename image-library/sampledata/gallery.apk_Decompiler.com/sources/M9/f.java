package M9;

import android.database.Cursor;
import com.samsung.android.gallery.app.controller.internals.OnDemandRemasterViewerCmd;
import com.samsung.android.gallery.module.publisher.CursorPublisher;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d = 1;
    public final /* synthetic */ long e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2842h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f2843i;

    public /* synthetic */ f(OnDemandRemasterViewerCmd onDemandRemasterViewerCmd, Runnable runnable, Blackboard blackboard, long j2, boolean z) {
        this.g = onDemandRemasterViewerCmd;
        this.f2842h = runnable;
        this.f2843i = blackboard;
        this.e = j2;
        this.f = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((CursorPublisher) this.g).lambda$publishFavoriteFileData$2(this.f, (Cursor[]) this.f2842h, this.e, (String) this.f2843i);
                return;
            default:
                ((OnDemandRemasterViewerCmd) this.g).lambda$onExecute$0((Runnable) this.f2842h, (Blackboard) this.f2843i, this.e, this.f);
                return;
        }
    }

    public /* synthetic */ f(CursorPublisher cursorPublisher, boolean z, Cursor[] cursorArr, long j2, String str) {
        this.g = cursorPublisher;
        this.f = z;
        this.f2842h = cursorArr;
        this.e = j2;
        this.f2843i = str;
    }
}
