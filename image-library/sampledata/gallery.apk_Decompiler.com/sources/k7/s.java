package k7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.ViewPagerFilmScrollSyncDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2662a;
    public final /* synthetic */ ViewPagerFilmScrollSyncDelegate b;

    public /* synthetic */ s(ViewPagerFilmScrollSyncDelegate viewPagerFilmScrollSyncDelegate, int i2) {
        this.f2662a = i2;
        this.b = viewPagerFilmScrollSyncDelegate;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2662a;
        ViewPagerFilmScrollSyncDelegate viewPagerFilmScrollSyncDelegate = this.b;
        switch (i2) {
            case 0:
                viewPagerFilmScrollSyncDelegate.lambda$setActionInvokeListener$0(objArr);
                return;
            case 1:
                viewPagerFilmScrollSyncDelegate.onPageScrolled(objArr);
                return;
            default:
                viewPagerFilmScrollSyncDelegate.onPageScrollDone(objArr);
                return;
        }
    }
}
