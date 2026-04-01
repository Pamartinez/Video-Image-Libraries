package ub;

import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.dragdrop.SplitDnDAnimation;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3295a;
    public final /* synthetic */ SplitDnDAnimation b;

    public /* synthetic */ a(SplitDnDAnimation splitDnDAnimation, int i2) {
        this.f3295a = i2;
        this.b = splitDnDAnimation;
    }

    public final boolean test(Object obj) {
        int i2 = this.f3295a;
        SplitDnDAnimation splitDnDAnimation = this.b;
        switch (i2) {
            case 0:
                return splitDnDAnimation.lambda$createAnimViews$0((Integer) obj);
            default:
                return splitDnDAnimation.lambda$createAnimViews$1((RecyclerView.ViewHolder) obj);
        }
    }
}
