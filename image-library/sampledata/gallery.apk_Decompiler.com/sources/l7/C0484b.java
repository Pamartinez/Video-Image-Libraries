package l7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripSeekerDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* renamed from: l7.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0484b implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2667a;
    public final /* synthetic */ FilmStripSeekerDelegate b;

    public /* synthetic */ C0484b(FilmStripSeekerDelegate filmStripSeekerDelegate, int i2) {
        this.f2667a = i2;
        this.b = filmStripSeekerDelegate;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2667a;
        FilmStripSeekerDelegate filmStripSeekerDelegate = this.b;
        switch (i2) {
            case 0:
                filmStripSeekerDelegate.onVideoPlayTimeUpdated(objArr);
                return;
            case 1:
                filmStripSeekerDelegate.lambda$setActionInvokeListener$0(objArr);
                return;
            case 2:
                filmStripSeekerDelegate.lambda$setActionInvokeListener$1(objArr);
                return;
            case 3:
                filmStripSeekerDelegate.lambda$setActionInvokeListener$2(objArr);
                return;
            case 4:
                filmStripSeekerDelegate.lambda$setActionInvokeListener$3(objArr);
                return;
            case 5:
                filmStripSeekerDelegate.lambda$setActionInvokeListener$5(objArr);
                return;
            default:
                filmStripSeekerDelegate.lambda$setActionInvokeListener$6(objArr);
                return;
        }
    }
}
