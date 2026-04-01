package H3;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.CreateFolderCmd;
import com.samsung.android.gallery.app.controller.album.UpdateOrderCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements UpdateOrderCmd.OrderUpdateCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CreateFolderCmd f2332a;
    public final /* synthetic */ EventContext b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f2333c;

    public /* synthetic */ f(CreateFolderCmd createFolderCmd, EventContext eventContext, String str) {
        this.f2332a = createFolderCmd;
        this.b = eventContext;
        this.f2333c = str;
    }

    public final void onOrderUpdated() {
        this.f2332a.lambda$checkOrderAndCreateFolder$0(this.b, this.f2333c);
    }
}
