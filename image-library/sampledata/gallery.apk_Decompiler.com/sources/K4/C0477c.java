package k4;

import com.samsung.android.gallery.app.ui.container.menu.BottomTabSelectListener;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.function.Consumer;

/* renamed from: k4.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0477c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ BottomTabSelectListener e;

    public /* synthetic */ C0477c(BottomTabSelectListener bottomTabSelectListener, int i2) {
        this.d = i2;
        this.e = bottomTabSelectListener;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        BottomTabSelectListener bottomTabSelectListener = this.e;
        Blackboard blackboard = (Blackboard) obj;
        switch (i2) {
            case 0:
                bottomTabSelectListener.blockFocus(blackboard);
                return;
            default:
                bottomTabSelectListener.lambda$onTabReselected$0(blackboard);
                return;
        }
    }
}
