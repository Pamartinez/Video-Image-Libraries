package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import B7.d;
import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.photoview.AliveZoomScheduler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AliveZoomDelegate extends AbsVuDelegate<IVuContainerView> {
    public AliveZoomDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    public static boolean support(String str) {
        if (!Features.isEnabled(Features.SUPPORT_ALIVE_ZOOM) || LocationKey.isRevitalizationView(str)) {
            return false;
        }
        return true;
    }

    public void onCreate(Bundle bundle) {
        AliveZoomScheduler.getInstance().open();
    }

    public void onDestroy() {
        AliveZoomScheduler.getInstance().close();
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.ENTER_TRANSITION_END, new d(15, this));
    }

    /* access modifiers changed from: private */
    public void onTransitionEnd(Object... objArr) {
    }
}
