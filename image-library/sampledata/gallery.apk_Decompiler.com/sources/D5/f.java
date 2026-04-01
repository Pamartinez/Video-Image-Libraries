package D5;

import android.view.MotionEvent;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.ForegroundViewController;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BottomDecoViewDelegate;
import com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior;
import com.samsung.android.gallery.widget.bottom.BottomBar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements View.OnTouchListener {
    public final /* synthetic */ int d;

    public /* synthetic */ f(int i2) {
        this.d = i2;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        switch (this.d) {
            case 0:
                return ForegroundViewController.lambda$new$0(view, motionEvent);
            case 1:
                return SearchPicturesLocationBehavior.lambda$onLayoutChild$0(view, motionEvent);
            case 2:
                return BottomBar.lambda$new$1(view, motionEvent);
            default:
                return BottomDecoViewDelegate.lambda$initView$0(view, motionEvent);
        }
    }
}
