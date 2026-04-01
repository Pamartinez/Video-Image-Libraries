package U7;

import com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand.RemasterAnalyticsLoggingHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2454a;
    public final /* synthetic */ RemasterAnalyticsLoggingHandler b;

    public /* synthetic */ a(RemasterAnalyticsLoggingHandler remasterAnalyticsLoggingHandler, int i2) {
        this.f2454a = i2;
        this.b = remasterAnalyticsLoggingHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2454a;
        RemasterAnalyticsLoggingHandler remasterAnalyticsLoggingHandler = this.b;
        switch (i2) {
            case 0:
                remasterAnalyticsLoggingHandler.loggingRemasterType(objArr);
                return;
            default:
                remasterAnalyticsLoggingHandler.loggingErrorType(objArr);
                return;
        }
    }
}
