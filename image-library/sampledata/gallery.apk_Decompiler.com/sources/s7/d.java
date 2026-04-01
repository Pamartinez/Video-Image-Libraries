package S7;

import android.app.Activity;
import android.view.KeyEvent;
import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterLayoutHandler;
import com.samsung.android.gallery.widget.capture.ClipViewDelegate;
import com.samsung.android.gallery.widget.clip.ClipView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ KeyEvent.Callback f;
    public final /* synthetic */ boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ boolean f2428h;

    public /* synthetic */ d(Object obj, KeyEvent.Callback callback, boolean z, boolean z3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = callback;
        this.g = z;
        this.f2428h = z3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((RemasterLayoutHandler) this.e).lambda$updateLayoutComponent$6((Activity) this.f, this.g, this.f2428h);
                return;
            case 1:
                ((RemasterLayoutHandler) this.e).lambda$updateLayoutComponent$7((Activity) this.f, this.g, this.f2428h);
                return;
            default:
                ((ClipViewDelegate) this.e).lambda$bindViewInternal$0((ClipView) this.f, this.g, this.f2428h);
                return;
        }
    }
}
