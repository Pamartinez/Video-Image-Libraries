package T7;

import com.samsung.android.gallery.app.ui.viewer2.remaster.focusview.RemasterFocusViewHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2441a;
    public final /* synthetic */ RemasterFocusViewHandler b;

    public /* synthetic */ a(RemasterFocusViewHandler remasterFocusViewHandler, int i2) {
        this.f2441a = i2;
        this.b = remasterFocusViewHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2441a;
        RemasterFocusViewHandler remasterFocusViewHandler = this.b;
        switch (i2) {
            case 0:
                remasterFocusViewHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                remasterFocusViewHandler.onLoadedFocusData(objArr);
                return;
            default:
                remasterFocusViewHandler.onReadyRemasterView(objArr);
                return;
        }
    }
}
