package l7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* renamed from: l7.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0483a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2666a;
    public final /* synthetic */ FilmStripDelegate b;

    public /* synthetic */ C0483a(FilmStripDelegate filmStripDelegate, int i2) {
        this.f2666a = i2;
        this.b = filmStripDelegate;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2666a;
        FilmStripDelegate filmStripDelegate = this.b;
        switch (i2) {
            case 0:
                filmStripDelegate.lambda$setActionInvokeListener$0(objArr);
                return;
            case 1:
                filmStripDelegate.lambda$setActionInvokeListener$1(objArr);
                return;
            case 2:
                filmStripDelegate.onEnterTransitionStart(objArr);
                return;
            default:
                filmStripDelegate.onReenterTransitionEnd(objArr);
                return;
        }
    }
}
