package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import a6.C0419b;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.samsung.android.gallery.app.controller.viewer.RequestDismissKeyGuardCmd;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.sec.android.gallery3d.R;
import k7.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NaviUpDelegate extends AbsVuDelegate<IVuContainerView> {
    public NaviUpDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private boolean isFromCamera() {
        return LaunchIntent.isFromCamera(this.mBlackboard);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initNaviUp$0(View view) {
        onNavigationPressed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchTimelineView$1() {
        this.mBlackboard.post("command://request_suicide", (Object) null);
    }

    /* access modifiers changed from: private */
    public void launchTimelineView() {
        if (this.mBlackboard != null) {
            try {
                Intent intent = new Intent();
                intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
                intent.setAction("com.android.gallery.action.PICTURES_VIEW");
                intent.putExtra("gallery_internal_caller", "navi-up-key");
                ((IVuContainerView) this.mView).getActivity().startActivity(intent);
                ThreadUtil.postOnBgThreadDelayed(new p(this, 1), 0);
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "launchTimelineView failed", (Throwable) e);
                this.mBlackboard.post("command://request_suicide", (Object) null);
            }
        }
    }

    private boolean supportLaunchTimeline() {
        return ((IVuContainerView) this.mView).isFirstFragment();
    }

    public void initNaviUp() {
        GalleryToolbar toolbar = ((IVuContainerView) this.mView).getToolbar();
        if (isNavigationUpEnabled()) {
            toolbar.setNavigationIcon((int) R.drawable.tw_ic_ab_back_mtrl_detailview);
            toolbar.setNavigationContentDescription(R.string.navigate_up);
            toolbar.setNavigationOnClickListener(new C0419b(18, this));
            return;
        }
        toolbar.setNavigationIcon((Drawable) null);
    }

    public boolean isNavigationUpEnabled() {
        LaunchIntent launchIntent;
        if (PocFeatures.TBD.OPEN_IN_OTHER_WINDOW && (launchIntent = LaunchIntent.get(this.mBlackboard)) != null && ((Boolean) launchIntent.getExtra("open_in_other_window", Boolean.FALSE)).booleanValue()) {
            return false;
        }
        if (!((IVuContainerView) this.mView).isFirstFragment() || isFromCamera()) {
            return !LocationKey.isFromExpand(((IVuContainerView) this.mView).getLocationKey());
        }
        return false;
    }

    public void onEnterTransitionStart() {
        initNaviUp();
    }

    public void onNavigationPressed() {
        if (!supportLaunchTimeline()) {
            ((IVuContainerView) this.mView).postAnalyticsLog(AnalyticsEventId.EVENT_UP_KEY);
            BlackboardUtils.publishBackKeyEvent(this.mBlackboard);
        } else if (((ContainerModel) this.mModel).getSystemUi().isScreenLocked()) {
            new RequestDismissKeyGuardCmd().execute(((IVuContainerView) this.mView).getEventContext(), new p(this, 0));
        } else {
            launchTimelineView();
        }
        Log.majorEvent(this.TAG, "onNavigationPressed");
    }
}
