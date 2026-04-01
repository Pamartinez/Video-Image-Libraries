package com.samsung.android.gallery.app.ui.viewer2.details;

import A4.C0385u;
import H3.l;
import H7.A;
import L7.a;
import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsDetailKey;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.details.DetailsBehavior;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsHandler extends ViewerObject implements FragmentLifeCycle {
    protected final ActionInvokeListener mBackKeyListener = new a(this, 0);
    private DetailsBehavior mBehavior;
    private DetailsView mDetailsView;
    private Runnable mPendingBackKeyAction;
    private CoordinatorLayout mViewerLayout;

    private void addExclusiveListener() {
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.BACK_KEY_PRESSED;
        if (!actionInvoker.hasExclusive(viewerAction, this.mBackKeyListener)) {
            this.mActionInvoker.addExclusive(viewerAction, this.mBackKeyListener, this.TAG);
        }
        ActionInvoker actionInvoker2 = this.mActionInvoker;
        ViewerAction viewerAction2 = ViewerAction.TOGGLE_OSD;
        ActionInvokeListener actionInvokeListener = ActionInvokeListener.EMPTY_LISTENER;
        if (!actionInvoker2.hasExclusive(viewerAction2, actionInvokeListener)) {
            this.mActionInvoker.addExclusive(viewerAction2, actionInvokeListener, this.TAG);
        }
    }

    /* access modifiers changed from: private */
    public void collapseDetails() {
        if (this.mModel.isViewConfirmed()) {
            this.mActionInvoker.invoke(ViewerAction.REQUEST_DETAILS_SHOW_OR_HIDE, Boolean.FALSE, Boolean.TRUE);
        }
    }

    private void createBehavior(Context context) {
        if (this.mBehavior == null) {
            DetailsBehavior detailsBehavior = new DetailsBehavior(context);
            this.mBehavior = detailsBehavior;
            this.mActionInvoker.invoke(ViewerAction.DETAILS_BEHAVIOR, detailsBehavior);
        }
    }

    private Pair<String, String>[] getDetailsCustomDimension(MediaItem mediaItem, boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair(AnalyticsDetailKey.DEFAULT.toString(), AnalyticsDetail.getDetailsTriggerType(z)));
        arrayList.add(new Pair(AnalyticsDetailKey.EXTENSION.toString(), FileUtils.getExtension(mediaItem.getPath()).toLowerCase()));
        arrayList.add(new Pair(AnalyticsDetailKey.SEF_TYPE.toString(), String.valueOf(mediaItem.getSefFileType())));
        arrayList.add(new Pair(AnalyticsDetailKey.STORAGE.toString(), MediaItemUtil.getStorage(mediaItem)));
        if (mediaItem.isVideo()) {
            arrayList.add(new Pair(AnalyticsDetailKey.RECORDING_MODE.toString(), String.valueOf(mediaItem.getRecordingMode())));
        }
        return (Pair[]) arrayList.toArray(new Pair[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mViewerLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        Runnable runnable = this.mPendingBackKeyAction;
        if (runnable != null) {
            runnable.run();
            this.mPendingBackKeyAction = null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        DetailsBehavior detailsBehavior;
        boolean booleanValue = objArr[0].booleanValue();
        DetailsView detailsView = this.mDetailsView;
        if (booleanValue) {
            detailsBehavior = null;
        } else {
            detailsBehavior = this.mBehavior;
        }
        setBehavior(detailsView, detailsBehavior);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initialize$3() {
        if (!this.mModel.getStateHelper().supportDetailsLargeScreen()) {
            return false;
        }
        if (ResourceCompat.isPortrait(this.mModel.getContext()) || this.mModel.getContainerModel().isTableMode()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$4(Object[] objArr) {
        ViewPager2 viewPager = this.mModel.getContainerModel().getViewPager();
        if (viewPager == null || viewPager.getScrollState() != 0) {
            Log.w(this.TAG, "waiting until view pager scroll is done");
            this.mPendingBackKeyAction = new l(22, this);
            return;
        }
        collapseDetails();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setEditClickListener$5(View view) {
        Boolean bool;
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        if (eventContext != null) {
            bool = (Boolean) eventContext.getEventContextData("is_input_blocked");
        } else {
            bool = null;
        }
        if (bool == null || !bool.booleanValue()) {
            this.mActionInvoker.invoke(ViewerAction.SET_INPUT_BLOCK, "details edit button", 500);
            getBlackboard().postEvent(EventMessage.obtain(3030));
        }
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        if (objArr[1].booleanValue()) {
            int intValue = objArr[0].intValue();
            if (intValue == 3) {
                if (!this.mModel.getContainerModel().isOsdVisible()) {
                    this.mActionInvoker.invoke(ViewerAction.TOGGLE_OSD, new Object[0]);
                }
                addExclusiveListener();
            } else if (intValue == 4 || intValue == 5) {
                removeExclusiveListener();
            }
        }
    }

    /* access modifiers changed from: private */
    public void postDetailsSaLog(Object... objArr) {
        boolean z;
        boolean z3 = false;
        MediaItem mediaItem = objArr[0];
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        if (mediaItem == null || eventContext == null) {
            StringCompat stringCompat = this.TAG;
            if (mediaItem != null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (eventContext != null) {
                z3 = true;
            }
            Log.e((CharSequence) stringCompat, "postDetailsSaLog failed", valueOf, Boolean.valueOf(z3));
            return;
        }
        AnalyticsLogger.getInstance().postCustomDimension(eventContext.getScreenId(), AnalyticsEventId.EVENT_DETAIL_VIEW_DETAILS.toString(), getDetailsCustomDimension(mediaItem, objArr[1].booleanValue()));
    }

    private void removeExclusiveListener() {
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.BACK_KEY_PRESSED;
        if (actionInvoker.hasExclusive(viewerAction, this.mBackKeyListener)) {
            this.mActionInvoker.removeExclusive(viewerAction, this.mBackKeyListener);
        }
        ActionInvoker actionInvoker2 = this.mActionInvoker;
        ViewerAction viewerAction2 = ViewerAction.TOGGLE_OSD;
        ActionInvokeListener actionInvokeListener = ActionInvokeListener.EMPTY_LISTENER;
        if (actionInvoker2.hasExclusive(viewerAction2, actionInvokeListener)) {
            this.mActionInvoker.removeExclusive(viewerAction2, actionInvokeListener);
        }
    }

    private void setBehavior(View view, CoordinatorLayout.Behavior<?> behavior) {
        ((CoordinatorLayout.LayoutParams) view.getLayoutParams()).setBehavior(behavior);
    }

    private void setEditClickListener() {
        this.mDetailsView.setEditClickListener(new A(7, this));
    }

    private void setLayout(ViewGroup viewGroup, View view) {
        if (view.getParent() == null) {
            viewGroup.addView(view, 1);
            ViewUtils.setMatchParentView(view);
            return;
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "setLayout skip " + view);
    }

    public static boolean supportDetails(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isCommonPostProcessing()) {
            return false;
        }
        return true;
    }

    private void updateDescendantFocusability() {
        int i2;
        DetailsView detailsView = this.mDetailsView;
        if (SheetBehaviorCompat.isClosed(this.mBehavior.getState())) {
            i2 = 393216;
        } else {
            i2 = 131072;
        }
        detailsView.setDescendantFocusability(i2);
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new a(this, 1));
        this.mActionInvoker.add(ViewerAction.BOTTOM_SHEET_STATE_CHANGED, new a(this, 2));
        this.mActionInvoker.add(ViewerAction.DETAILS_SHOW_ANALYTIC_LOGGING, new a(this, 3));
        this.mActionInvoker.add(ViewerAction.VIEW_PAGER_PAGE_SCROLL_DONE, new a(this, 4));
        this.mActionInvoker.add(ViewerAction.ACTION_FROM_EXTERNAL, new a(this, 5));
    }

    public void initialize() {
        DetailsView detailsView = new DetailsView(this.mModel.getContext(), new C0385u(6, this));
        this.mDetailsView = detailsView;
        this.mActionInvoker.invoke(ViewerAction.DETAILS_VIEW, detailsView);
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        updateDescendantFocusability();
    }

    public void onFinalized() {
        super.onFinalized();
        this.mDetailsView.onViewDestroy();
        this.mDetailsView = null;
    }

    public void onInitialized() {
        createBehavior(this.mModel.getContext());
        setLayout(this.mViewerLayout, this.mDetailsView);
        setBehavior(this.mDetailsView, this.mBehavior);
        setEditClickListener();
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mDetailsView.onViewRecycled();
        this.mBehavior.onViewRecycled();
        removeExclusiveListener();
        this.mPendingBackKeyAction = null;
    }
}
