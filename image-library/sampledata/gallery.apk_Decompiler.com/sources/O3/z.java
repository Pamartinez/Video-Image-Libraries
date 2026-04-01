package O3;

import com.samsung.android.gallery.app.controller.internals.UpdateFavoriteInListCmd;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class z implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ UpdateFavoriteInListCmd e;

    public /* synthetic */ z(UpdateFavoriteInListCmd updateFavoriteInListCmd, int i2) {
        this.d = i2;
        this.e = updateFavoriteInListCmd;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        UpdateFavoriteInListCmd updateFavoriteInListCmd = this.e;
        switch (i2) {
            case 0:
                updateFavoriteInListCmd.lambda$onExecute$1((Boolean) obj);
                return;
            default:
                updateFavoriteInListCmd.lambda$onExecute$0((InstantSubscriberListener) obj);
                return;
        }
    }
}
