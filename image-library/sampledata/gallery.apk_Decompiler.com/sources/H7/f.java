package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.HighlightFrcHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2345a;
    public final /* synthetic */ HighlightFrcHandler b;

    public /* synthetic */ f(HighlightFrcHandler highlightFrcHandler, int i2) {
        this.f2345a = i2;
        this.b = highlightFrcHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2345a;
        HighlightFrcHandler highlightFrcHandler = this.b;
        switch (i2) {
            case 0:
                highlightFrcHandler.initView(objArr);
                return;
            default:
                highlightFrcHandler.onPlayTimeUpdated(objArr);
                return;
        }
    }
}
