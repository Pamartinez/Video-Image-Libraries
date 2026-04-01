package e4;

import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import java.util.Observable;
import java.util.Observer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Observer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MvpBasePresenter f2628a;

    public /* synthetic */ d(MvpBasePresenter mvpBasePresenter) {
        this.f2628a = mvpBasePresenter;
    }

    public final void update(Observable observable, Object obj) {
        this.f2628a.invalidateOptionsMenuInternal(observable, obj);
    }
}
