package d8;

import android.os.Bundle;
import com.samsung.android.gallery.bixby.bixby.handler.GetSelectedContentsActionHandler;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements InstantSubscriberListener {
    public final /* synthetic */ GetSelectedContentsActionHandler d;
    public final /* synthetic */ int e;

    public /* synthetic */ a(GetSelectedContentsActionHandler getSelectedContentsActionHandler, int i2) {
        this.d = getSelectedContentsActionHandler;
        this.e = i2;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        this.d.lambda$handleExecutable$0(this.e, obj, bundle);
    }
}
