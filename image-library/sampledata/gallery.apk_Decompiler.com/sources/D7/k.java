package D7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.RemasterGifImageLoader;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2306a;
    public final /* synthetic */ RemasterGifImageLoader b;

    public /* synthetic */ k(RemasterGifImageLoader remasterGifImageLoader, int i2) {
        this.f2306a = i2;
        this.b = remasterGifImageLoader;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2306a;
        RemasterGifImageLoader remasterGifImageLoader = this.b;
        switch (i2) {
            case 0:
                remasterGifImageLoader.onDemandRemastered(objArr);
                return;
            default:
                remasterGifImageLoader.lambda$addActionInvokeListener$0(objArr);
                return;
        }
    }
}
