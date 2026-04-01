package D7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.ImageLoader;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2302a;
    public final /* synthetic */ ImageLoader b;

    public /* synthetic */ f(ImageLoader imageLoader, int i2) {
        this.f2302a = i2;
        this.b = imageLoader;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2302a;
        ImageLoader imageLoader = this.b;
        switch (i2) {
            case 0:
                imageLoader.lambda$addActionInvokeListener$0(objArr);
                return;
            default:
                imageLoader.lambda$addActionInvokeListener$1(objArr);
                return;
        }
    }
}
