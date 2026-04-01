package Fa;

import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.settings.ui.SearchCustomFragment;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class J implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchCustomFragment e;

    public /* synthetic */ J(SearchCustomFragment searchCustomFragment, int i2) {
        this.d = i2;
        this.e = searchCustomFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        SearchCustomFragment searchCustomFragment = this.e;
        FragmentActivity fragmentActivity = (FragmentActivity) obj;
        switch (i2) {
            case 0:
                searchCustomFragment.lambda$updateMainLayoutPaddingHorizontal$1(fragmentActivity);
                return;
            default:
                searchCustomFragment.lambda$onCreateView$0(fragmentActivity);
                return;
        }
    }
}
