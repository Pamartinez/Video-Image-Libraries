package D5;

import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor.AbsEditorTransitionHandler;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ EventMessage e;

    public /* synthetic */ e(int i2, EventMessage eventMessage) {
        this.d = i2;
        this.e = eventMessage;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        EventMessage eventMessage = this.e;
        switch (i2) {
            case 0:
                ((FloatingRecommendationDelegate) obj).handleEvent(EventMessage.obtain(8018, eventMessage.obj));
                return;
            case 1:
                ((AbsEditorTransitionHandler) obj).onHandleEvent(eventMessage);
                return;
            default:
                ((Blackboard) obj).postEvent(eventMessage);
                return;
        }
    }
}
