package q4;

import android.view.View;
import com.samsung.android.gallery.app.ui.dialog.CreateNewDialog;
import com.samsung.android.gallery.app.ui.dialog.EraseObjectDialog;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.LastPageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ e(Object obj, int i2, int i7) {
        this.d = i7;
        this.f = obj;
        this.e = i2;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                ((CreateNewDialog) this.f).lambda$inflateView$3(this.e, view);
                return;
            case 1:
                ((EraseObjectDialog) this.f).lambda$inflateView$0(this.e, view);
                return;
            default:
                ((LastPageView) this.f).lambda$setOnClickListener$1(this.e, view);
                return;
        }
    }
}
