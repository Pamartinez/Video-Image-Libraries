package q6;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.EmptyTouchHandler;
import java.util.function.Predicate;

/* renamed from: q6.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0508a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2697a;
    public final /* synthetic */ EmptyTouchHandler b;

    public /* synthetic */ C0508a(EmptyTouchHandler emptyTouchHandler, int i2) {
        this.f2697a = i2;
        this.b = emptyTouchHandler;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2697a;
        EmptyTouchHandler emptyTouchHandler = this.b;
        View view = (View) obj;
        switch (i2) {
            case 0:
                return emptyTouchHandler.isTouchableViewInRoot(view);
            case 1:
                return emptyTouchHandler.isTouchableViewInPager(view);
            default:
                return emptyTouchHandler.isTouchableOutsideView(view);
        }
    }
}
