package D7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.GifImageLoader;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2301a;
    public final /* synthetic */ GifImageLoader b;

    public /* synthetic */ d(GifImageLoader gifImageLoader, int i2) {
        this.f2301a = i2;
        this.b = gifImageLoader;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2301a;
        GifImageLoader gifImageLoader = this.b;
        switch (i2) {
            case 0:
                gifImageLoader.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                gifImageLoader.lambda$addActionInvokeListener$1(objArr);
                return;
            default:
                gifImageLoader.onUpdatedRemasterStatus(objArr);
                return;
        }
    }
}
