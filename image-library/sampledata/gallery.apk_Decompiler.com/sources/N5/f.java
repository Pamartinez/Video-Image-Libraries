package n5;

import android.view.View;
import androidx.core.content.res.ResourcesCompat;
import com.google.android.material.sidesheet.SideSheetBehavior;
import com.samsung.android.gallery.app.ui.dialog.CreateNameDialog;
import com.samsung.android.gallery.app.ui.list.search.category.people.PeopleSelectPresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.GuideDecoViewDelegate;
import com.samsung.android.gallery.widget.dialog.ProgressAvdCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ f(Object obj, int i2, int i7) {
        this.d = i7;
        this.f = obj;
        this.e = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((PeopleSelectPresenter) this.f).lambda$createAutoAlbum$6(this.e);
                return;
            case 1:
                ((GuideDecoViewDelegate) this.f).lambda$hideGuideDeco$2(this.e);
                return;
            case 2:
                ((CreateNameDialog) this.f).lambda$setError$4(this.e);
                return;
            case 3:
                ((ProgressAvdCompat) this.f).lambda$updateMessage$0(this.e);
                return;
            case 4:
                ((ResourcesCompat.FontCallback) this.f).lambda$callbackFailAsync$1(this.e);
                return;
            default:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) this.f;
                View view = (View) sideSheetBehavior.s.get();
                if (view != null) {
                    sideSheetBehavior.b(view, this.e, false);
                    return;
                }
                return;
        }
    }
}
