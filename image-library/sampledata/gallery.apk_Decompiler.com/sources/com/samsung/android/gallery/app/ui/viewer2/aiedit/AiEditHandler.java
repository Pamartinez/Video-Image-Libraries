package com.samsung.android.gallery.app.ui.viewer2.aiedit;

import A4.C0380o;
import Bb.g;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import bc.d;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.ExecuteTriggerType;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.aiedit.AiEditBixbyRequest;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.aiedit.TimeLapseLoader;
import com.samsung.android.gallery.module.bixby.BixbyKey;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dynamicview.a;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.ValueTrigger;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.o3dp.lib3dphotography.i;
import com.sec.android.gallery3d.R;
import g7.C0458a;
import g7.C0459b;
import g7.C0460c;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AiEditHandler extends ViewerObject implements FragmentLifeCycle {
    private AiEditList mAiEditList;
    private View mContainer;
    private final ValueTrigger<Boolean> mExecuteTrigger = new ValueTrigger<>(Boolean.FALSE, "aiEdit");
    private View.OnLayoutChangeListener mLayoutChangeListener;
    private boolean mPendingUpdate;
    private AlertDialog mProgressDialog;
    private View mView;
    private CoordinatorLayout mViewerLayout;

    private void addLayoutChangeListener() {
        if (BottomSheetState.isExpanded(this.mModel)) {
            if (this.mLayoutChangeListener == null) {
                this.mLayoutChangeListener = new g(10, this);
            }
            this.mContainer.addOnLayoutChangeListener(this.mLayoutChangeListener);
        }
    }

    /* access modifiers changed from: private */
    public void executeAfterDetailsCollapsed(Object... objArr) {
        ExecuteTriggerType executeTriggerType = ExecuteTriggerType.get(objArr[0].intValue());
        Runnable runnable = objArr[1];
        if (!BottomSheetState.Details.isClosed(this.mModel) || runnable == null) {
            if (runnable != null) {
                this.mExecuteTrigger.when(Boolean.TRUE).then(new d((Object) this, (Object) executeTriggerType, (Object) runnable, 16), "aiEdit execute after details collapsed");
            }
            if (executeTriggerType == ExecuteTriggerType.COLLAPSE_WITH_TRANSPARENT_PROGRESS) {
                showProgress();
            }
            this.mActionInvoker.invoke(ViewerAction.REQUEST_DETAILS_SHOW_OR_HIDE, Boolean.FALSE, Boolean.TRUE);
            return;
        }
        Log.d(this.TAG, "already closed. aiEdit execute directly.");
        if (executeTriggerType.needHideDecor()) {
            this.mActionInvoker.invoke(ViewerAction.SET_DECOR_VISIBILITY, Boolean.FALSE);
        }
        runnable.run();
    }

    private Size getSourceSize() {
        if (this.mModel.getMediaItem() == null || this.mModel.getMediaItem().getSourceSize() == null) {
            return new Size(1024, 1024);
        }
        return this.mModel.getMediaItem().getSourceSize();
    }

    private int[] getTargetViewSideLength() {
        int[] iArr = new int[2];
        Size sourceSize = getSourceSize();
        View view = this.mContainer;
        float width = ((float) sourceSize.getWidth()) / ((float) sourceSize.getHeight());
        float width2 = ((float) view.getWidth()) / ((float) view.getHeight());
        if (width > width2) {
            iArr[1] = (int) ((((float) view.getHeight()) - (((float) view.getWidth()) / width)) / 2.0f);
        } else if (width < width2) {
            iArr[0] = (int) ((((float) view.getWidth()) - (((float) view.getHeight()) * width)) / 2.0f);
        }
        iArr[1] = iArr[1] + ((int) (((float) DeviceInfo.getDisplayHeight(view.getContext())) / 2.0f));
        return iArr;
    }

    private void hideProgress() {
        ThreadUtil.runOnUiThread(new C0458a(this, 0));
    }

    private void initAiEditList() {
        View findViewById = this.mViewerLayout.findViewById(R.id.ai_edit_layout);
        if (ViewUtils.isViewStub(findViewById)) {
            this.mView = ((ViewStub) findViewById).inflate();
        }
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (eventContext == null || mediaItem == null) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "init ai edit list failed. " + MediaItemUtil.getSimpleLog(mediaItem));
            return;
        }
        this.mAiEditList = new AiEditList(eventContext, this.mActionInvoker, (ViewGroup) this.mView.findViewById(R.id.ai_edit_list_layout));
    }

    private boolean isAvailableState() {
        if (!BottomSheetState.Details.isExpanded(this.mModel) || OverlayViewState.isShow(this.mModel.getOverlayViewState())) {
            return false;
        }
        return true;
    }

    private boolean isItemReadyForAiMenu(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isWebp()) {
            return false;
        }
        return true;
    }

    private boolean isValid(MediaItem mediaItem, boolean z) {
        if (mediaItem == null || this.mModel.getMediaItem() == null) {
            Log.d(this.TAG, "skip");
            return false;
        } else if (!this.mModel.isViewConfirmed()) {
            Log.d(this.TAG, "skip::detached", Long.valueOf(mediaItem.getFileId()));
            return false;
        } else if (!MediaItemUtil.equalsId(mediaItem, this.mModel.getMediaItem())) {
            Log.d(this.TAG, "skip::invalid", Long.valueOf(mediaItem.getFileId()), Optional.ofNullable(this.mModel.getMediaItem()).map(new com.samsung.scsp.media.api.d(18)).orElse(-1L));
            return false;
        } else if (isAvailableState() || !z) {
            return true;
        } else {
            Log.d(this.TAG, "skip::unavailable state", Long.valueOf(mediaItem.getFileId()));
            return false;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mViewerLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mContainer = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        updateVisibilityIfExpanded(this.mModel.getMediaItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$4(Object[] objArr) {
        this.mThread.runOnUiThread(new i(24, this, objArr));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addLayoutChangeListener$12(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        if (!BottomSheetState.isExpanded(this.mModel)) {
            return;
        }
        if (i2 != i11 || i8 != i13 || i10 != i14) {
            updateMargin();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeAfterDetailsCollapsed$7(ExecuteTriggerType executeTriggerType, Runnable runnable) {
        if (executeTriggerType.needHideDecor()) {
            this.mActionInvoker.invoke(ViewerAction.SET_DECOR_VISIBILITY, Boolean.FALSE);
        }
        if (this.mModel.getMediaItem() != null && this.mModel.getMediaItem().isCloudOnly()) {
            hideProgress();
        }
        runnable.run();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hideProgress$6() {
        AlertDialog alertDialog = this.mProgressDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            Log.d(this.TAG, "dismiss ai menu progressDialog");
            this.mProgressDialog.dismiss();
        }
        this.mProgressDialog = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onRequestAiEditDirectly$14(MediaItem mediaItem) {
        return isValid(mediaItem, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTableModeChanged$9() {
        if (isAvailableState()) {
            AiEditList aiEditList = this.mAiEditList;
            if (aiEditList != null) {
                aiEditList.onConfigurationChanged();
            }
            updateMargin();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showProgress$5() {
        Log.d(this.TAG, "show ai menu progressDialog");
        AlertDialog create = new ProgressCircleBuilder(this.mModel.getContext()).setProgressMessage("").setFlexMode(false).removeCircle().removeDimBehind().create();
        this.mProgressDialog = create;
        create.show();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$startLiveEffectActivity$15(FastOptionView fastOptionView) {
        if (fastOptionView.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$updateAiEditVisibility$10(MediaItem mediaItem) {
        return isValid(mediaItem, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePosition$13(int i2, int i7, int i8) {
        View view = this.mView;
        if (view != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.leftMargin = i2;
            marginLayoutParams.topMargin = this.mView.getHeight() * -1;
            marginLayoutParams.rightMargin = i7;
            marginLayoutParams.bottomMargin = i8;
            this.mView.setLayoutParams(marginLayoutParams);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateVisibilityIfExpanded$8(MediaItem mediaItem) {
        if (isAvailableState()) {
            updateAiEditVisibility(mediaItem);
        }
    }

    /* access modifiers changed from: private */
    public void onBottomSheetSlide(Object... objArr) {
        updateMargin();
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        if (objArr[1].booleanValue() && this.mModel.isViewConfirmed()) {
            if (BottomSheetState.Details.isClosed(this.mModel)) {
                this.mExecuteTrigger.set(Boolean.TRUE);
            } else if (isAvailableState()) {
                this.mExecuteTrigger.clear();
            }
            addLayoutChangeListener();
            updateAiEditVisibility(this.mModel.getMediaItem());
        }
    }

    /* access modifiers changed from: private */
    public void onOverlayStateChanged(Object... objArr) {
        boolean isShow = OverlayViewState.isShow(objArr[0]);
        this.mAiEditList.updateVisibilityBy(isShow);
        this.mAiEditList.setImportantForAccessibility(isShow);
    }

    /* access modifiers changed from: private */
    public void onRemoveTimeLapseEffect(Object... objArr) {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null) {
            TimeLapseLoader.getInstance().removeInMap(mediaItem.getFileId());
            this.mPendingUpdate = true;
        }
    }

    /* access modifiers changed from: private */
    public void onRequestAiEditDirectly(Object... objArr) {
        AiEditType aiEditType = objArr[0];
        boolean booleanValue = objArr[1].booleanValue();
        BixbyKey.ErrorCallback errorCallback = objArr[2];
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (aiEditType != null && !BottomSheetState.Details.isInTransition(this.mModel)) {
            if (this.mAiEditList == null) {
                initAiEditList();
            }
            this.mAiEditList.requestDirectly(new AiEditBixbyRequest(aiEditType, this.mModel.getBitmap(), mediaItem, booleanValue, new C0460c(this, mediaItem, 1), errorCallback));
        }
    }

    private void removeLayoutChangeListener() {
        View.OnLayoutChangeListener onLayoutChangeListener = this.mLayoutChangeListener;
        if (onLayoutChangeListener != null) {
            this.mContainer.removeOnLayoutChangeListener(onLayoutChangeListener);
            this.mLayoutChangeListener = null;
        }
    }

    private void showProgress() {
        ThreadUtil.runOnUiThread(new C0458a(this, 1));
    }

    /* access modifiers changed from: private */
    /* renamed from: startLiveEffectActivity */
    public void lambda$addActionInvokeListener$3(Object[] objArr) {
        FastOptionView fastOptionView;
        Activity activity = objArr[0];
        MediaItem mediaItem = objArr[1];
        boolean booleanValue = objArr[3].booleanValue();
        boolean booleanValue2 = objArr[4].booleanValue();
        String str = "data://intent/gen-portrait/" + mediaItem.getComplexHashCode();
        Blackboard.getApplicationInstance().publish(str, new Object[]{mediaItem, objArr[2]});
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.plugins.portrait.LiveEffectActivity");
        intent.putExtra("data-key", str);
        intent.putExtra("caller-blackboard", activity.toString());
        intent.putExtra("system-no-status-bar", SystemUi.isInNoStatusBarMode(activity));
        intent.putExtra("system-status-bar-color", SystemUi.getStatusBarColor(activity));
        intent.putExtra("auto-save", booleanValue);
        intent.putExtra("show-init-progress", booleanValue2);
        ArrayList arrayList = new ArrayList();
        if (mediaItem.isImage()) {
            ContentModel contentModel = this.mModel;
            if (contentModel != null) {
                fastOptionView = contentModel.getContainerModel().getFastOptionView();
            } else {
                fastOptionView = null;
            }
            Optional.ofNullable(fastOptionView).filter(new a(25)).ifPresent(new f4.a(arrayList, 10));
        }
        activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, (Pair[]) arrayList.toArray(new Pair[0])).toBundle());
    }

    private void updateAiEditVisibility(MediaItem mediaItem) {
        if (this.mAiEditList == null) {
            initAiEditList();
        }
        if (isAvailableState()) {
            updateMargin();
            this.mAiEditList.updateMediaItemAndVisibility(mediaItem, this.mModel.getBitmap(), this.mPendingUpdate, new C0460c(this, mediaItem, 0));
            this.mPendingUpdate = false;
            return;
        }
        this.mAiEditList.hideView();
    }

    private void updateMargin() {
        View view;
        if (this.mView != null && (view = this.mContainer) != null) {
            int translationY = (int) view.getTranslationY();
            int max = Math.max(this.mModel.getContainerModel().getCutouts().left, RectUtils.getStart(this.mModel.getContainerModel().getSystemInsets()));
            if (this.mModel.getContainerModel().isTableMode()) {
                int[] targetViewSideLength = getTargetViewSideLength();
                updatePosition(targetViewSideLength[0] + max, 0, targetViewSideLength[1] - translationY);
                return;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mContainer.getLayoutParams();
            int i2 = marginLayoutParams.bottomMargin - translationY;
            if (ResourceCompat.isLandscape(this.mModel.getContext())) {
                i2 += this.mModel.getContainerModel().getSystemInsets().bottom;
            }
            updatePosition(marginLayoutParams.leftMargin + max, marginLayoutParams.rightMargin, i2);
        }
    }

    private void updatePosition(int i2, int i7, int i8) {
        ViewUtils.post(this.mView, new C0380o(this, i2, i7, i8, 2));
    }

    private void updateVisibilityIfExpanded(MediaItem mediaItem) {
        ThreadUtil.runOnUiThread(new i(25, this, mediaItem));
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new C0459b(this, 5));
        this.mActionInvoker.add(ViewerAction.CONTENT_CONTAINER, new C0459b(this, 6));
        this.mActionInvoker.add(ViewerAction.BOTTOM_SHEET_STATE_CHANGED, new C0459b(this, 7));
        this.mActionInvoker.add(ViewerAction.DETAILS_SLIDE, new C0459b(this, 8));
        this.mActionInvoker.add(ViewerAction.REMOVE_TIMELAPSE_INFO, new C0459b(this, 9));
        this.mActionInvoker.add(ViewerAction.EXECUTE_AI_EDIT_AFTER_COLLAPSED, new C0459b(this, 0));
        this.mActionInvoker.add(ViewerAction.WEBP_SET_DONE, new C0459b(this, 1));
        this.mActionInvoker.add(ViewerAction.REQUEST_AI_EDIT_DIRECTLY, new C0459b(this, 2));
        this.mActionInvoker.add(ViewerAction.OVERLAY_VIEW_STATE_CHANGED, new C0459b(this, 3));
        this.mActionInvoker.add(ViewerAction.START_LIVE_EFFECT_ACTIVITY, new C0459b(this, 4));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        AiEditList aiEditList;
        int i2 = eventMessage.what;
        if (i2 == 3041) {
            this.mPendingUpdate = true;
            return false;
        } else if (i2 == 3074) {
            this.mModel.setOverlayViewState(OverlayViewState.hide);
            this.mActionInvoker.invoke(ViewerAction.SET_DECOR_VISIBILITY, Boolean.TRUE);
            return false;
        } else {
            if (i2 == 3059 && (aiEditList = this.mAiEditList) != null) {
                Object obj = eventMessage.obj;
                if (obj instanceof Object[]) {
                    aiEditList.executeIntelligenceItem((Object[]) obj);
                }
            }
            return false;
        }
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        if (!MediaItemUtil.equals(mediaItem2, mediaItem)) {
            this.mPendingUpdate = true;
            updateVisibilityIfExpanded(mediaItem);
        }
    }

    public void onApplyWindowInsets() {
        updateMargin();
    }

    public void onConfigurationChanged(Configuration configuration) {
        AiEditList aiEditList = this.mAiEditList;
        if (aiEditList != null) {
            aiEditList.onConfigurationChanged();
        }
        updateMargin();
    }

    public void onFinalized() {
        if (this.mProgressDialog != null) {
            hideProgress();
        }
    }

    public void onResume() {
        if (this.mProgressDialog != null) {
            hideProgress();
        }
    }

    public void onTableModeChanged(boolean z, int i2) {
        ViewUtils.post(this.mView, new C0458a(this, 2));
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        this.mExecuteTrigger.clear();
        addLayoutChangeListener();
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (isItemReadyForAiMenu(mediaItem)) {
            updateVisibilityIfExpanded(mediaItem);
        }
    }

    public void onViewDetached() {
        super.onViewDetached();
        this.mExecuteTrigger.clear();
        this.mPendingUpdate = false;
        AiEditList aiEditList = this.mAiEditList;
        if (aiEditList != null) {
            aiEditList.hideView();
        }
        AlertDialog alertDialog = this.mProgressDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            Log.w(this.TAG, "progressDialog is still show");
            hideProgress();
        }
        removeLayoutChangeListener();
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        removeLayoutChangeListener();
        AiEditList aiEditList = this.mAiEditList;
        if (aiEditList != null) {
            aiEditList.recycle();
        }
    }
}
