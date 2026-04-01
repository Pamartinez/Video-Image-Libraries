package D9;

import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements SeMobileServiceSession.ServiceConnectionListener, SeMobileServiceSession.OnAgentUpdatedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MdeSharingService f2796a;

    public /* synthetic */ g(MdeSharingService mdeSharingService) {
        this.f2796a = mdeSharingService;
    }

    public void onAgentUpdated() {
        this.f2796a.lambda$connectSession$1();
    }

    public void onChanged(int i2, String str) {
        this.f2796a.lambda$connectSession$0(i2, str);
    }
}
