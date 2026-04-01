package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture;

import A4.J;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.externals.StartSimplePhotoEditorCmd;
import com.samsung.android.gallery.app.controller.internals.ShowSnackBarForViewerCmd;
import com.samsung.android.gallery.app.controller.viewer.CopyToClipboardCmd;
import com.samsung.android.gallery.app.controller.viewer.EraseObjectCmd;
import com.samsung.android.gallery.app.controller.viewer.RequestDismissKeyGuardCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.OnViewLongPress;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.menu.PasteClipboardMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.clip.IClipInfo;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureTask;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.module.editor.ModeInfo;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.clip.objectcapture.ObjectCaptureView;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.lang.ref.WeakReference;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import v7.w;
import x7.C0539a;
import x7.C0540b;
import x7.c;
import x7.d;
import x7.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ObjectCaptureHandler extends ViewerObject implements FragmentLifeCycle, IClipInfo, OnViewLongPress {
    private final ActionInvokeListener mBackKeyListener = new C0540b(this, 8);
    WeakReference<Bitmap> mBitmapHolder;
    boolean mIsDirty;
    boolean mIsObjectCapturing;
    boolean mIsPlayModeChanged;
    ObjectCaptureHelper mObjectCaptureHelper;
    ObjectCaptureView mObjectCaptureView;
    boolean mObjectCaptureViewVisible;
    private final AtomicBoolean mPaused = new AtomicBoolean(false);
    ObjectCaptureTask mTask;

    private void clear() {
        this.mModel.setObjectCaptureState(ObjectCaptureHelper.State.NONE);
        ObjectCaptureView objectCaptureView = this.mObjectCaptureView;
        if (objectCaptureView != null) {
            objectCaptureView.destroy();
            ViewUtils.setVisibility(this.mObjectCaptureView, 8);
        }
        ObjectCaptureHelper objectCaptureHelper = this.mObjectCaptureHelper;
        if (objectCaptureHelper != null) {
            objectCaptureHelper.clear();
            this.mObjectCaptureHelper = null;
        }
        Optional.ofNullable(this.mBitmapHolder).ifPresent(new w(12));
        this.mBitmapHolder = null;
        this.mIsDirty = false;
        this.mObjectCaptureViewVisible = false;
        this.mPaused.set(false);
    }

    /* access modifiers changed from: private */
    public void enableObjectCaptureView() {
        setExclusiveListener(true);
        ObjectCaptureView objectCaptureView = this.mObjectCaptureView;
        if (objectCaptureView != null) {
            objectCaptureView.setObjectCaptureHelper(this.mObjectCaptureHelper);
            setObjectCaptureViewVisibility(true);
        }
    }

    private boolean hasData() {
        ObjectCaptureHelper objectCaptureHelper = this.mObjectCaptureHelper;
        if (objectCaptureHelper == null || !objectCaptureHelper.hasData()) {
            return false;
        }
        return true;
    }

    private boolean isActionModeSupported() {
        ConcurrentHashMap<Class<?>, ViewerMenuItem> enabledMenuMap = this.mModel.getContainerModel().getEnabledMenuMap();
        if (enabledMenuMap != null) {
            return enabledMenuMap.containsKey(PasteClipboardMenuItem.class);
        }
        return false;
    }

    private boolean isPlayModeChanged() {
        boolean z = this.mIsPlayModeChanged;
        this.mIsPlayModeChanged = false;
        return z;
    }

    private boolean isValid(MediaItem mediaItem) {
        if (LocationKey.isSecondDepthGroupPanelView(this.mModel.getContainerModel().getLocationKey())) {
            return false;
        }
        if ((mediaItem == null || !mediaItem.isPostProcessing()) && mediaItem != null && !mediaItem.isDrm() && !mediaItem.isBroken() && !TrashData.isTrash(mediaItem) && !mediaItem.isMtp() && !mediaItem.isUriItem()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mIsObjectCapturing = true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mIsObjectCapturing = false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$disableObjectCaptureView$2() {
        ObjectCaptureHelper objectCaptureHelper = this.mObjectCaptureHelper;
        if (objectCaptureHelper != null) {
            objectCaptureHelper.setToolbarVisibility(false);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCapture$3() {
        this.mActionInvoker.invoke(ViewerAction.SET_VIEW_PAGER_TOUCH_ENABLED, Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCapture$4(float f, float f5, ObjectCaptureHelper objectCaptureHelper) {
        this.mActionInvoker.invoke(ViewerAction.OBJECT_CAPTURE_PROGRESS_END, new Object[0]);
        if (!this.mPaused.get()) {
            this.mObjectCaptureHelper = objectCaptureHelper;
            objectCaptureHelper.setAnalyticsDetailLogCallback(new d(this, 0));
            this.mObjectCaptureHelper.setOnDragStartCallback(new C0539a(this, 2));
            this.mObjectCaptureHelper.setRequestDismissKeyGuardCallback(new d(this, 1));
            onCaptureDone(f, f5);
        }
        this.mThread.runOnUiThread(new C0539a(this, 1), 300);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewReady$6() {
        ObjectCaptureHelper objectCaptureHelper = this.mObjectCaptureHelper;
        if (objectCaptureHelper == null || !objectCaptureHelper.isObjectSelected()) {
            onConsumeEvent(new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewReady$7(boolean z) {
        if (z) {
            postAnalyticsDetailLogPressLog();
        } else {
            disableObjectCaptureView(new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishObjectCaptureResetConsumer$8(int i2, Integer num) {
        if (this.mObjectCaptureHelper != null && !this.mObjectCaptureViewVisible && num.intValue() == i2) {
            Log.w((CharSequence) this.TAG, "capture helper is reset by dnd", num);
            this.mObjectCaptureHelper.clear();
            this.mObjectCaptureHelper = null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showPasteIfExist$9(float f, float f5) {
        this.mActionInvoker.invoke(ViewerAction.START_ACTION_MODE, Float.valueOf(f), Float.valueOf(f5));
    }

    /* access modifiers changed from: private */
    public void onBottomSheetSlide(Object... objArr) {
        if (this.mObjectCaptureViewVisible) {
            disableObjectCaptureView(new Object[0]);
        }
    }

    private void onCaptureDone(float f, float f5) {
        Optional.ofNullable(this.mBitmapHolder).ifPresent(new w(12));
        if (isPlayModeChanged()) {
            this.mObjectCaptureHelper.clearVariables();
        } else if (isAlreadyUp()) {
            Log.e(this.TAG, "skip request object capture, already touch up");
            this.mObjectCaptureHelper.clearVariables();
            this.mModel.setObjectCaptureState(ObjectCaptureHelper.State.NONE);
        } else {
            RectF displayRect = getDisplayRect();
            if (hasData() && displayRect != null) {
                float width = displayRect.width() / ((float) this.mObjectCaptureHelper.getSourceWidth());
                int topMargin = (int) (((f5 - displayRect.top) - ((float) getTopMargin())) / width);
                if (this.mObjectCaptureHelper.isObjectAt((int) ((f - displayRect.left) / width), topMargin)) {
                    this.mThread.runOnUiThread(new C0539a(this, 3));
                    sendCaptureResult(true);
                    postAnalyticsDetailLogPressLog();
                    return;
                }
            }
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "unable to capture object {" + this.mObjectCaptureHelper + "}");
            if (!SystemUi.isInMultiWindowMode(this.mModel.getActivity())) {
                showPasteIfExist(f, f5, displayRect);
            }
            sendCaptureResult(false);
        }
    }

    /* access modifiers changed from: private */
    public void onClipDragEnd(Object... objArr) {
        if (!this.mObjectCaptureViewVisible) {
            Optional.ofNullable(this.mObjectCaptureHelper).ifPresent(new w(10));
        }
    }

    /* access modifiers changed from: private */
    public void onConsumeEvent(Object... objArr) {
        this.mActionInvoker.invoke(ViewerAction.RESET_EXIT_GESTURE, new Object[0]);
        getBlackboard().erase("data://shrink_active");
        disableObjectCaptureView(objArr);
    }

    /* access modifiers changed from: private */
    public void onObjectCaptureRequest(Object... objArr) {
        this.mModel.setObjectCaptureState(ObjectCaptureHelper.State.CAPTURING);
        float floatValue = objArr[0].floatValue();
        float floatValue2 = objArr[1].floatValue();
        if (!isValid(this.mModel.getMediaItem())) {
            Log.e(this.TAG, "unable to capture object, invalid data");
            sendCaptureResult(false);
            return;
        }
        ObjectCaptureHelper objectCaptureHelper = objArr[3];
        if (objectCaptureHelper != null) {
            this.mObjectCaptureHelper = objectCaptureHelper;
            objectCaptureHelper.setAnalyticsDetailLogCallback(new d(this, 0));
            this.mObjectCaptureHelper.setOnDragStartCallback(new C0539a(this, 2));
            this.mObjectCaptureHelper.setRequestDismissKeyGuardCallback(new d(this, 1));
            this.mActionInvoker.invoke(ViewerAction.OBJECT_CAPTURE_PROGRESS_END, new Object[0]);
            onCaptureDone(floatValue, floatValue2);
            return;
        }
        this.mBitmapHolder = new WeakReference<>(objArr[2]);
        capture(floatValue, floatValue2);
    }

    /* access modifiers changed from: private */
    public void onToggleOsd(Object... objArr) {
        if (DeviceInfo.isDexMode(this.mModel.getContext())) {
            refreshLayout();
        }
    }

    /* access modifiers changed from: private */
    public void onViewReady(Object... objArr) {
        ObjectCaptureView objectCaptureView = (ObjectCaptureView) objArr[0].findViewById(R.id.object_capture_view);
        this.mObjectCaptureView = objectCaptureView;
        objectCaptureView.setOnToggleConsumeListener(new c(this));
        this.mObjectCaptureView.setOnViewLongClickListener(new c(this));
        ViewUtils.setVisibility(this.mObjectCaptureView, 8);
    }

    /* access modifiers changed from: private */
    public void postAnalyticsDetailLog(String[] strArr) {
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        if (eventContext == null) {
            return;
        }
        if (strArr.length == 1) {
            AnalyticsLogger.getInstance().postLog(eventContext.getScreenId(), strArr[0]);
        } else if (strArr.length == 2) {
            AnalyticsLogger.getInstance().postLog(eventContext.getScreenId(), strArr[0], strArr[1]);
        }
    }

    /* access modifiers changed from: private */
    public void publishObjectCaptureResetConsumer() {
        getBlackboard().publish("data://object_capture_reset", new J((Object) this, getPosition(), 14));
    }

    /* access modifiers changed from: private */
    public void requestDismissKeyGuardCallback(Runnable runnable) {
        new RequestDismissKeyGuardCmd().execute(this.mModel.getContainerModel().getEventContext(), runnable);
    }

    private void setExclusiveListener(boolean z) {
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.BACK_KEY_PRESSED;
        boolean hasExclusive = actionInvoker.hasExclusive(viewerAction, this.mBackKeyListener);
        if (z && !hasExclusive) {
            this.mActionInvoker.addExclusive(viewerAction, this.mBackKeyListener, this.TAG);
        } else if (!z && hasExclusive) {
            this.mActionInvoker.removeExclusive(viewerAction, this.mBackKeyListener);
        }
    }

    private void setObjectCaptureViewVisibility(boolean z) {
        int i2;
        if (this.mObjectCaptureViewVisible != z) {
            this.mObjectCaptureViewVisible = z;
            ObjectCaptureView objectCaptureView = this.mObjectCaptureView;
            if (z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            ViewUtils.setVisibility(objectCaptureView, i2);
            bindObjectCaptureView();
            this.mIsDirty = false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        r5 = new com.samsung.android.gallery.support.utils.SafeClipboard(r2.mModel.getContext());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void showPasteIfExist(float r3, float r4, android.graphics.RectF r5) {
        /*
            r2 = this;
            if (r5 == 0) goto L_0x004d
            boolean r5 = r5.contains(r3, r4)
            if (r5 != 0) goto L_0x0009
            goto L_0x004d
        L_0x0009:
            com.samsung.android.gallery.support.utils.PreferenceFeatures r5 = com.samsung.android.gallery.support.utils.PreferenceFeatures.PasteClipboardViewer
            boolean r5 = com.samsung.android.gallery.support.utils.PreferenceFeatures.isEnabled(r5)
            if (r5 == 0) goto L_0x0043
            boolean r5 = r2.isActionModeSupported()
            if (r5 == 0) goto L_0x0043
            com.samsung.android.gallery.support.utils.SafeClipboard r5 = new com.samsung.android.gallery.support.utils.SafeClipboard
            com.samsung.android.gallery.app.ui.viewer2.model.ContentModel r0 = r2.mModel
            android.content.Context r0 = r0.getContext()
            r5.<init>(r0)
            android.content.ClipData r0 = r5.getPrimaryClip()
            if (r0 == 0) goto L_0x0043
            int r0 = r0.getItemCount()
            r1 = 1
            if (r0 != r1) goto L_0x0043
            java.lang.String r0 = "image/*"
            boolean r5 = r5.hasMimeType(r0)
            if (r5 == 0) goto L_0x0043
            com.samsung.android.gallery.widget.ViewerObjectThread r5 = r2.mThread
            g5.c r0 = new g5.c
            r1 = 2
            r0.<init>(r2, r3, r4, r1)
            r5.runOnUiThread(r0)
            return
        L_0x0043:
            android.content.Context r2 = com.samsung.android.gallery.support.utils.AppResources.getAppContext()
            r3 = 2131886737(0x7f120291, float:1.9408061E38)
            com.samsung.android.gallery.support.utils.Utils.showToast((android.content.Context) r2, (int) r3)
        L_0x004d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureHandler.showPasteIfExist(float, float, android.graphics.RectF):void");
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.DISABLE_OBJECT_CAPTURE_VIEW, new C0540b(this, 9));
        this.mActionInvoker.add(ViewerAction.TOGGLE_OSD, new C0540b(this, 0));
        this.mActionInvoker.add(ViewerAction.DETAILS_SLIDE, new C0540b(this, 1));
        this.mActionInvoker.add(ViewerAction.OBJECT_CAPTURE, new C0540b(this, 2));
        this.mActionInvoker.add(ViewerAction.OBJECT_CAPTURE_MENU_CLICKED, new C0540b(this, 3));
        this.mActionInvoker.add(ViewerAction.OBJECT_CAPTURE_VIEW, new C0540b(this, 4));
        this.mActionInvoker.add(ViewerAction.OBJECT_CAPTURE_CLIP_DRAG_END, new C0540b(this, 5));
        this.mActionInvoker.add(ViewerAction.OBJECT_CAPTURE_PROGRESS_START, new C0540b(this, 6));
        this.mActionInvoker.add(ViewerAction.OBJECT_CAPTURE_PROGRESS_END, new C0540b(this, 7));
    }

    public abstract void bindObjectCaptureView();

    public abstract void capture(float f, float f5);

    public void disableObjectCaptureView(Object... objArr) {
        setExclusiveListener(false);
        ObjectCaptureView objectCaptureView = this.mObjectCaptureView;
        if (objectCaptureView != null) {
            objectCaptureView.post(new C0539a(this, 0));
            this.mObjectCaptureView.setObjectCaptureHelper((ObjectCaptureHelper) null);
            setObjectCaptureViewVisibility(false);
        }
        Optional.ofNullable(this.mTask).ifPresent(new w(11));
        Optional.ofNullable(this.mBitmapHolder).ifPresent(new w(12));
        this.mModel.setObjectCaptureState(ObjectCaptureHelper.State.NONE);
    }

    public String getContainerKey() {
        return this.mModel.getContainerModel().toString();
    }

    public Context getContext() {
        return this.mModel.getContext();
    }

    public abstract RectF getDisplayRect();

    public MediaItem getMediaItem() {
        return this.mModel.getMediaItem();
    }

    public int getPosition() {
        return this.mModel.getContainerModel().getPosition();
    }

    public Dialog getProgressDialog() {
        return new ProgressCircleBuilder(this.mModel.getContext()).setLightTheme(true).create();
    }

    public abstract int getTopMargin();

    public abstract boolean isAlreadyUp();

    public boolean isObjectCaptured() {
        ObjectCaptureHelper objectCaptureHelper = this.mObjectCaptureHelper;
        if (objectCaptureHelper == null || !objectCaptureHelper.isCaptured()) {
            return false;
        }
        return true;
    }

    public boolean isSegmentSelected() {
        ObjectCaptureHelper objectCaptureHelper = this.mObjectCaptureHelper;
        if (objectCaptureHelper == null || !objectCaptureHelper.isSegmentSelected()) {
            return false;
        }
        return true;
    }

    public void onApplyWindowInsets() {
        refreshLayout();
    }

    public void onCapture(float f, float f5) {
        this.mActionInvoker.invoke(ViewerAction.SET_VIEW_PAGER_TOUCH_ENABLED, Boolean.FALSE);
        float f8 = f;
        float f10 = f5;
        this.mTask = new ObjectCaptureTask(this.mObjectCaptureHelper, this, f8, f10, new e(this, f, f5));
        this.mActionInvoker.invoke(ViewerAction.OBJECT_CAPTURE_PROGRESS_START, Float.valueOf(f8), Float.valueOf(f10));
        DeepSkyHelper.post(this.mTask);
    }

    public void onConfigurationChanged(Configuration configuration) {
        setExclusiveListener(false);
        clear();
    }

    public void onInitialized() {
        this.mActionInvoker.invoke(ViewerAction.REG_VIEW_LONG_PRESS_LISTENER, this, 20);
    }

    public void onMenuClicked(Object... objArr) {
        int intValue = objArr[0].intValue();
        MediaItem mediaItem = objArr[1];
        if (intValue == 0) {
            new CopyToClipboardCmd().execute(this.mModel.getContainerModel().getEventContext(), new MediaItem[]{mediaItem});
        } else if (intValue == 1) {
            new ShareViaCmd().execute(this.mModel.getContainerModel().getEventContext(), new MediaItem[]{mediaItem}, null);
        } else if (intValue == 4) {
            new StartSimplePhotoEditorCmd().execute(this.mModel.getContainerModel().getEventContext(), mediaItem, StartSimplePhotoEditorCmd.PhotoEditorMode.spe_lasso, null, Boolean.FALSE, new ModeInfo(0, (Uri) null));
        } else if (intValue == 6) {
            if (mediaItem != null) {
                new ShowSnackBarForViewerCmd().execute(this.mModel.getContainerModel().getEventContext(), mediaItem, this.mModel.getContext().getString(R.string.gif_saved), Boolean.FALSE, AnalyticsEventId.EVENT_DETAIL_VIEW_CLIPPED_IMAGE_VIEW_SAVED_GIF.toString());
            } else {
                Log.e(this.TAG, "unable to show snack bar for saved gif");
            }
        } else if (intValue == 2) {
            if (mediaItem != null) {
                new ShowSnackBarForViewerCmd().execute(this.mModel.getContainerModel().getEventContext(), mediaItem, this.mModel.getContext().getString(R.string.toast_image_saved), Boolean.FALSE);
            } else {
                Log.e(this.TAG, "unable to show snack bar for saved image");
            }
        } else if (intValue == 100) {
            new EraseObjectCmd().execute(this.mModel.getContainerModel().getEventContext(), this.mObjectCaptureHelper, mediaItem);
        }
        disableObjectCaptureView(new Object[0]);
    }

    public void onPause() {
        this.mPaused.set(true);
        disableObjectCaptureView(new Object[0]);
    }

    public void onResume() {
        this.mPaused.set(false);
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        if (this.mObjectCaptureView == null) {
            this.mActionInvoker.invoke(ViewerAction.OBJECT_CAPTURE_VIEW_INFLATE, new Object[0]);
        }
    }

    public void onViewDetached() {
        super.onViewDetached();
        if (isObjectCaptured()) {
            this.mIsDirty = true;
        }
        disableObjectCaptureView(new Object[0]);
    }

    public boolean onViewLongPress(float f, float f5) {
        if (!SystemUi.isInMultiWindowMode(this.mModel.getActivity()) || !this.mModel.isTextExtractionFullState()) {
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("capture(LP) req");
            sb2.append(Logger.v("(" + f + GlobalPostProcInternalPPInterface.SPLIT_REGEX + f5 + ")", this.mObjectCaptureHelper));
            Log.d(stringCompat, sb2.toString());
            onObjectCaptureRequest(Float.valueOf(f), Float.valueOf(f5), null, null);
            return true;
        }
        Log.d(this.TAG, "capture(LP) skip, multi window text extraction full state mode");
        return false;
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        clear();
    }

    public void postAnalyticsDetailLogPressLog() {
        String str;
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        if (eventContext != null) {
            AnalyticsLogger instance = AnalyticsLogger.getInstance();
            String screenId = eventContext.getScreenId();
            String analyticsEventId = AnalyticsEventId.EVENT_DETAIL_VIEW_LONG_PRESS_CAPTURE.toString();
            if (isSegmentSelected()) {
                str = AnalyticsDetail.EVENT_DETAIL_CAPTURE_LONG_PRESS_TYPE_NONE_REPRESENTATIVE_CLIP.toString();
            } else {
                str = AnalyticsDetail.EVENT_DETAIL_CAPTURE_LONG_PRESS_TYPE_CLIP.toString();
            }
            instance.postLog(screenId, analyticsEventId, str);
        }
    }

    public abstract void refreshLayout();

    public void sendCaptureResult(boolean z) {
        ObjectCaptureHelper.State state;
        this.mActionInvoker.invoke(ViewerAction.OBJECT_CAPTURE_DONE, Boolean.valueOf(z));
        ContentModel contentModel = this.mModel;
        if (z) {
            state = ObjectCaptureHelper.State.CAPTURED;
        } else {
            state = ObjectCaptureHelper.State.NONE;
        }
        contentModel.setObjectCaptureState(state);
    }
}
