package D5;

import android.view.MotionEvent;
import android.view.View;
import com.samsung.android.gallery.app.ui.container.clipboard.ClipboardViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.ForegroundViewController;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements View.OnHoverListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ g(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final boolean onHover(View view, MotionEvent motionEvent) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                return ((ForegroundViewController) obj).onHover(view, motionEvent);
            default:
                return ((ClipboardViewAdapter) obj).lambda$setHoverListener$2(view, motionEvent);
        }
    }
}
