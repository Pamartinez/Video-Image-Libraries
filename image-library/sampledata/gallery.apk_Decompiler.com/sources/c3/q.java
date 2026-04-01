package C3;

import android.os.Bundle;
import com.samsung.android.gallery.app.activity.ViewNavigator;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ViewNavigator e;

    public /* synthetic */ q(ViewNavigator viewNavigator, int i2) {
        this.d = i2;
        this.e = viewNavigator;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        ViewNavigator viewNavigator = this.e;
        switch (i2) {
            case 0:
                viewNavigator.onCurrent(obj, bundle);
                return;
            case 1:
                viewNavigator.lambda$createSubscriberList$1(obj, bundle);
                return;
            case 2:
                viewNavigator.onActivityCreate(obj, bundle);
                return;
            case 3:
                viewNavigator.onActivityReenter(obj, bundle);
                return;
            case 4:
                viewNavigator.onActivityResult(obj, bundle);
                return;
            case 5:
                viewNavigator.onMoveUrlDirect(obj, bundle);
                return;
            case 6:
                viewNavigator.onMoveUrl(obj, bundle);
                return;
            case 7:
                viewNavigator.onMove(obj, bundle);
                return;
            case 8:
                viewNavigator.onRemove(obj, bundle);
                return;
            case 9:
                viewNavigator.onHandleEvent(obj, bundle);
                return;
            case 10:
                viewNavigator.onHandleBroadcastEvent(obj, bundle);
                return;
            case 11:
                viewNavigator.onShowDialog(obj, bundle);
                return;
            case 12:
                viewNavigator.onDialog(obj, bundle);
                return;
            case 13:
                viewNavigator.onPicker(obj, bundle);
                return;
            case 14:
                viewNavigator.onMoveView(obj, bundle);
                return;
            case 15:
                viewNavigator.onLocationEdit(obj, bundle);
                return;
            case 16:
                viewNavigator.onCropImage(obj, bundle);
                return;
            case 17:
                viewNavigator.onTagEdit(obj, bundle);
                return;
            case 18:
                viewNavigator.lambda$createSubscriberList$0(obj, bundle);
                return;
            default:
                viewNavigator.clearPreloadedData(obj, bundle);
                return;
        }
    }
}
