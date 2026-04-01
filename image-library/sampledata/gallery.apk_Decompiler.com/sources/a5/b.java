package A5;

import android.view.ViewGroup;
import android.widget.ImageView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ViewGroup e;

    public /* synthetic */ b(ViewGroup viewGroup, int i2) {
        this.d = i2;
        this.e = viewGroup;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ViewGroup viewGroup = this.e;
        switch (i2) {
            case 0:
                viewGroup.removeView((ImageView) obj);
                return;
            case 1:
                viewGroup.addView((ViewGroup) obj);
                return;
            default:
                ViewUtils.setVisibleOrGone(viewGroup, ((Boolean) obj).booleanValue());
                return;
        }
    }
}
