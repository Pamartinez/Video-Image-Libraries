package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction;

import G7.e;
import O8.C0576a;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.DocumentScanCmd;
import com.samsung.android.gallery.app.controller.internals.CleanCmhMediaContentInfoCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.OnViewLongPress;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.clip.IClipInfo;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;
import com.samsung.android.gallery.module.clip.textextraction.DocumentScanner;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionTask;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.ViewerUtils;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.clip.textextraction.TextExtractionView;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Optional;
import q8.a;
import v7.w;
import y7.C0542a;
import y7.b;
import y7.c;
import y7.d;
import y7.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TextExtractionHandler extends ViewerObject implements FragmentLifeCycle, IClipInfo, OnViewLongPress {
    private static final boolean SUPPORT_VEX_DOCUMENT_SCAN = Features.isEnabled(Features.SUPPORT_VEX_DOCUMENT_SCAN);
    private final View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate() {
        public void sendAccessibilityEvent(View view, int i2) {
            boolean z;
            int i7;
            StringBuilder sb2 = new StringBuilder(TextExtractionHandler.this.mModel.getContext().getString(R.string.extract_text));
            if (i2 == 32768) {
                TextExtractionHandler textExtractionHandler = TextExtractionHandler.this;
                if (!textExtractionHandler.mTextExtractionViewVisible || !textExtractionHandler.isFullStateExceptFilter()) {
                    z = false;
                } else {
                    z = true;
                }
                sb2.append(ArcCommonLog.TAG_COMMA);
                Context context = TextExtractionHandler.this.mModel.getContext();
                if (z) {
                    i7 = R.string.speak_item_selected;
                } else {
                    i7 = R.string.speak_item_unselected;
                }
                sb2.append(context.getString(i7));
                sb2.append(ArcCommonLog.TAG_COMMA);
                sb2.append(TextExtractionHandler.this.mModel.getContext().getString(R.string.speak_button));
                TextExtractionHandler.this.mTextExtractionButton.setContentDescription(sb2);
                TextExtractionHandler.this.mTextExtractionButton.setTooltipText((CharSequence) null);
            } else {
                TextExtractionHandler.this.mTextExtractionButton.setContentDescription(sb2);
                TextExtractionHandler.this.mTextExtractionButton.setTooltipText(sb2);
            }
            super.sendAccessibilityEvent(view, i2);
        }
    };
    private final ActionInvokeListener mBackKeyListener = new b(this, 8);
    WeakReference<Bitmap> mBitmapHolder;
    Runnable mDetectRunnable = null;
    TextExtractionTask mDetectTask;
    View mDocumentScanButton;
    ViewStub mDocumentScanButtonViewStub;
    TextExtractionTask mExtractTask;
    boolean mIsDecorViewVisible = true;
    boolean mIsDetecting;
    boolean mIsDirty;
    boolean mPaused = false;
    View mTextExtractionButton;
    ViewStub mTextExtractionButtonViewStub;
    View mTextExtractionCapsuleLayout;
    View mTextExtractionHint;
    View mTextExtractionLangManageLayout;
    View mTextExtractionLayout;
    TextExtractionView mTextExtractionView;
    boolean mTextExtractionViewVisibilityChanging;
    boolean mTextExtractionViewVisible;
    TextExtractionHelper mTextHelper;
    CoordinatorLayout mViewerLayout;

    private void enableTextExtractionView() {
        TextExtractionView textExtractionView = this.mTextExtractionView;
        if (textExtractionView != null) {
            textExtractionView.setLogTag(this.TAG.getTag());
            this.mTextExtractionView.setVisionTextHelper(this.mTextHelper);
            this.mTextExtractionView.setViewClickListener(new f(this));
            this.mTextExtractionView.setViewReadyListener(new f(this));
            setTextExtractionViewVisibility(true);
        }
    }

    private boolean hasBarcodeData() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper == null || !textExtractionHelper.hasBarcodeData()) {
            return false;
        }
        return true;
    }

    private void initView() {
        CoordinatorLayout coordinatorLayout = this.mViewerLayout;
        if (coordinatorLayout == null) {
            Log.w(this.TAG, "initView failed. not ready");
            return;
        }
        View view = this.mTextExtractionLayout;
        if (view == null || this.mTextExtractionButton == null) {
            if (view == null) {
                View findViewById = coordinatorLayout.findViewById(R.id.text_extraction_layout);
                if (findViewById instanceof ViewStub) {
                    this.mTextExtractionLayout = ((ViewStub) findViewById).inflate();
                } else {
                    this.mTextExtractionLayout = findViewById;
                }
            }
            if (this.mTextExtractionButton == null) {
                View findViewById2 = this.mViewerLayout.findViewById(R.id.text_extraction_button);
                if (findViewById2 instanceof ViewStub) {
                    ViewStub viewStub = (ViewStub) findViewById2;
                    this.mTextExtractionButtonViewStub = viewStub;
                    this.mTextExtractionButton = viewStub.inflate();
                } else {
                    this.mTextExtractionButton = findViewById2;
                }
            }
            if (PreferenceFeatures.isEnabled(PreferenceFeatures.TextExtractionCapsule)) {
                this.mTextExtractionCapsuleLayout = this.mViewerLayout.findViewById(R.id.capsule_layout);
                if (PreferenceFeatures.isEnabled(PreferenceFeatures.TextExtractionLangManage)) {
                    this.mTextExtractionLangManageLayout = this.mViewerLayout.findViewById(R.id.lang_manage_layout);
                }
            }
            if (SUPPORT_VEX_DOCUMENT_SCAN && this.mDocumentScanButton == null) {
                View findViewById3 = this.mViewerLayout.findViewById(R.id.document_scan_button);
                if (findViewById3 instanceof ViewStub) {
                    ViewStub viewStub2 = (ViewStub) findViewById3;
                    this.mDocumentScanButtonViewStub = viewStub2;
                    this.mDocumentScanButton = viewStub2.inflate();
                } else {
                    this.mDocumentScanButton = findViewById3;
                }
            }
            this.mTextExtractionHint = this.mViewerLayout.findViewById(R.id.text_extraction_hint);
            this.mTextExtractionView = (TextExtractionView) this.mViewerLayout.findViewById(R.id.text_extraction_view);
            ViewUtils.setVisibility(this.mDocumentScanButton, 8);
            ViewUtils.setVisibility(this.mTextExtractionButton, 8);
            ViewUtils.setVisibility(this.mTextExtractionCapsuleLayout, 8);
            ViewUtils.setVisibility(this.mTextExtractionHint, 8);
            ViewUtils.setVisibility(this.mTextExtractionLangManageLayout, 8);
            ViewUtils.setVisibility(this.mTextExtractionView, 8);
            TextExtractionView textExtractionView = this.mTextExtractionView;
            if (textExtractionView != null) {
                textExtractionView.setOnToggleConsumeListener(new f(this));
                this.mTextExtractionView.setAccessibilityTraversalAfter(R.id.text_extraction_button);
            }
        }
    }

    private boolean isFullIndirectState() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper == null || !textExtractionHelper.isFullIndirectState()) {
            return false;
        }
        return true;
    }

    private boolean isFullState() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper == null || !textExtractionHelper.isFullState()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean isFullStateExceptFilter() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper == null || !textExtractionHelper.isFullStateExceptFilter()) {
            return false;
        }
        return true;
    }

    private boolean isHdr() {
        MediaItem mediaItem;
        if (!SuperHdrConfig.SUPPORT || !SuperHdrConfig.isEnabled() || (mediaItem = getMediaItem()) == null) {
            return false;
        }
        if (mediaItem.isImage()) {
            if (mediaItem.hasPhotoHdrInfo() || (mediaItem.isPhotoHdrSupported() && mediaItem.isPhotoHdrCandidate())) {
                return true;
            }
            return false;
        } else if (!mediaItem.isVideo() || !mediaItem.isHdrVideo()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isNoneState(TextExtractionHelper.State state) {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        return textExtractionHelper == null || textExtractionHelper.isNoneState(state);
    }

    private boolean isPartialState() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper == null || !textExtractionHelper.isPartialState()) {
            return false;
        }
        return true;
    }

    private boolean isSelectedDirectCheck() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper == null) {
            return false;
        }
        if (textExtractionHelper.isTextSelected() || this.mTextHelper.isBarcodeSelected()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        onBottomSheetSlide(objArr[1].floatValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        setButtonVisibility(isButtonViewable(), false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        this.mViewerLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$clearTextExtractionViewVisibilityChangeFlag$4() {
        this.mTextExtractionViewVisibilityChanging = false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$clearTextSelection$5() {
        Optional.ofNullable(this.mTextHelper).ifPresent(new w(21));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$detectDone$6(boolean z) {
        setButtonVisibility(z, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$detectDone$7(TextExtractionHelper textExtractionHelper) {
        textExtractionHelper.setAnalyticsDetailLogCallback(new g6.f(14, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$extractDone$8() {
        if (hasData() || hasBarcodeData()) {
            enableTextExtractionView();
            this.mTextHelper.setDocumentScanClickConsumer(new d(this, 0));
            this.mTextHelper.setTranslateClickConsumer(new d(this, 1));
        } else {
            if (isPartialState()) {
                requestObjectCapture(this.mTextHelper.getInitX(), this.mTextHelper.getInitY());
            }
            clearInitPoint();
            disableTextExtractionView(new Object[0]);
        }
        updateDocumentScanButtonVisibility(hasData());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$10() {
        if (!isFullState()) {
            onConsumeEvent(new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTableModeChanged$3() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper != null) {
            textExtractionHelper.setPopupMenuVisibility(true);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTapTranslate$11(Boolean bool) {
        int i2;
        Optional.ofNullable(this.mTextExtractionView).ifPresent(new w(20));
        View view = this.mTextExtractionLangManageLayout;
        if (bool.booleanValue()) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(view, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$removeAllCapsules$9() {
        View view = this.mTextExtractionCapsuleLayout;
        if (view instanceof ViewGroup) {
            ((ViewGroup) view).removeAllViews();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setState$13() {
        setButtonVisibility(isButtonViewable(), false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setState$14(Object obj, Bundle bundle) {
        this.mThread.runOnUiThread(new C0542a(this, 5));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setState$15(EventContext eventContext) {
        eventContext.subscribeInstant("lifecycle://on_activity_resume", new e(8, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCapsuleMargin$17() {
        if (this.mTextExtractionButton != null) {
            ViewMarginUtils.setBottomMargin(this.mTextExtractionLangManageLayout, this.mTextExtractionButton.getResources().getDimensionPixelOffset(R.dimen.text_extraction_lang_manage_layout_margin_bottom) + this.mTextExtractionButton.getHeight() + getButtonBottomMargin());
            ViewMarginUtils.setStartMargin(this.mTextExtractionLangManageLayout, RectUtils.getStart(this.mModel.getContainerModel().getSystemInsetsIgnoringVisibility()));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDocumentScanButtonVisibility$16(View view) {
        onTapToDocumentScan(getMediaItem());
    }

    private void onBottomSheetSlide(float f) {
        int i2;
        if (hasData() && isButtonViewable()) {
            float decorAlphaInBottomSheetSlide = ViewerUtils.getDecorAlphaInBottomSheetSlide(f);
            ViewUtils.setAlpha(this.mTextExtractionButton, decorAlphaInBottomSheetSlide);
            View view = this.mTextExtractionButton;
            if (decorAlphaInBottomSheetSlide == 0.0f) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            ViewUtils.setVisibility(view, i2);
        }
        if (this.mTextExtractionViewVisible) {
            disableTextExtractionView(new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        if (BottomSheetState.isClosed(this.mModel)) {
            setButtonVisibility(isButtonViewable(), true);
        }
        ViewUtils.setVisibility(this.mTextExtractionCapsuleLayout, 8);
        ViewUtils.setVisibility(this.mTextExtractionLangManageLayout, 8);
    }

    /* access modifiers changed from: private */
    public void onConsumeEvent(Object... objArr) {
        this.mActionInvoker.invoke(ViewerAction.RESET_EXIT_GESTURE, new Object[0]);
        getBlackboard().erase("data://shrink_active");
        disableTextExtractionView(objArr);
    }

    /* access modifiers changed from: private */
    public void onTapToDocumentScan(MediaItem mediaItem) {
        ArrayList<PointF> vertexList = DocumentScanner.getInstance().getVertexList(mediaItem);
        disableTextExtractionView(new Object[0]);
        new DocumentScanCmd().execute(this.mModel.getContainerModel().getEventContext(), mediaItem, vertexList);
        postAnalyticsDetailLog(AnalyticsEventId.EVENT_DETAIL_VIEW_CAPSULE_TOUCH.toString(), AnalyticsDetail.EVENT_DETAIL_TEXT_EXTRACTION_CAPSULE_TYPE_SCAN.toString());
    }

    /* access modifiers changed from: private */
    public void onTapTranslate(Boolean bool) {
        ViewUtils.postDelayed(this.mTextExtractionView, new x6.f(5, this, bool), 100);
    }

    /* access modifiers changed from: private */
    public void onTextExtractionViewClick(boolean z, boolean z3, float f, float f5) {
        updateHint();
        if (z && !isFullState()) {
            if (z3) {
                if (isNoneState()) {
                    setState(this.mTextHelper, TextExtractionHelper.State.PARTIAL);
                }
                postAnalyticsDetailLogPressLog();
            }
            if (!isSelectedDirectCheck()) {
                disableTextExtractionView(new Object[0]);
            }
            clearInitPoint();
        }
    }

    /* access modifiers changed from: private */
    public void onTextExtractionViewReady(boolean z, boolean z3, float f, float f5) {
        clearTextExtractionViewVisibilityChangeFlag();
        if ((!z || (f > 0.0f && f5 > 0.0f)) && !isFullState()) {
            if (z3) {
                postAnalyticsDetailLogPressLog();
            } else {
                requestObjectCapture(f, f5);
            }
            if (!isSelectedDirectCheck()) {
                disableTextExtractionView(new Object[0]);
            }
        }
        clearInitPoint();
    }

    /* access modifiers changed from: private */
    public void onToggleOsd(Object... objArr) {
        if (DeviceInfo.isDexMode(this.mModel.getContext())) {
            refreshLayout();
        }
    }

    /* access modifiers changed from: private */
    public void onUpdateDecorVisibility(Object... objArr) {
        this.mIsDecorViewVisible = objArr[0].booleanValue();
        setButtonVisibility(isButtonViewable(), false);
    }

    /* access modifiers changed from: private */
    public void onWindowFocusChanged(Object... objArr) {
        if (objArr[0].booleanValue() && isPartialState() && !isSelectedDirectCheck()) {
            setState(this.mTextHelper, TextExtractionHelper.State.NONE);
        }
    }

    /* access modifiers changed from: private */
    public void postAnalyticsDetailLog(String str, String str2) {
        Optional.ofNullable(this.mModel.getContainerModel().getEventContext()).ifPresent(new C0576a(str, str2, 1));
    }

    private void postAnalyticsDetailLogPressLog() {
        postAnalyticsDetailLog(AnalyticsEventId.EVENT_DETAIL_VIEW_LONG_PRESS_CAPTURE.toString(), AnalyticsDetail.EVENT_DETAIL_CAPTURE_LONG_PRESS_TYPE_TEXT.toString());
    }

    private void removeAllCapsules() {
        ViewUtils.post(this.mTextExtractionCapsuleLayout, new C0542a(this, 2));
    }

    private void requestObjectCapture(float f, float f5) {
        ObjectCaptureHelper objectCaptureHelper = null;
        Bitmap bitmap = (Bitmap) Optional.ofNullable(this.mBitmapHolder).map(new a(26)).orElse((Object) null);
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.OBJECT_CAPTURE;
        Float valueOf = Float.valueOf(f);
        Float valueOf2 = Float.valueOf(f5);
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper != null) {
            objectCaptureHelper = textExtractionHelper.getObjectCaptureHelper();
        }
        actionInvoker.invoke(viewerAction, valueOf, valueOf2, bitmap, objectCaptureHelper);
        Optional.ofNullable(this.mTextHelper).ifPresent(new w(23));
        Optional.ofNullable(this.mBitmapHolder).ifPresent(new w(12));
    }

    private void setExclusiveListener(TextExtractionHelper.State state) {
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.BACK_KEY_PRESSED;
        boolean hasExclusive = actionInvoker.hasExclusive(viewerAction, this.mBackKeyListener);
        boolean isNoneState = isNoneState(state);
        if (isNoneState && hasExclusive) {
            this.mActionInvoker.removeExclusive(viewerAction, this.mBackKeyListener);
        } else if (!isNoneState && !hasExclusive) {
            this.mActionInvoker.addExclusive(viewerAction, this.mBackKeyListener, this.TAG);
        }
    }

    private void setTextExtractionViewVisibility(boolean z) {
        int i2;
        if (this.mTextExtractionViewVisible != z) {
            this.mTextExtractionViewVisible = z;
            TextExtractionView textExtractionView = this.mTextExtractionView;
            if (z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            SimpleAnimator.setVisibility((View) textExtractionView, i2, (int) MOCRLang.KHMER);
            bindTextExtractionView();
            updateButton();
            updateCapsule();
            updateHint();
            updateDocumentScanButtonVisibility(z);
            this.mIsDirty = false;
            if (!z) {
                clearTextExtractionViewVisibilityChangeFlag();
                this.mActionInvoker.invoke(ViewerAction.SET_DECOR_VISIBILITY, Boolean.valueOf(this.mModel.getContainerModel().isOsdVisible()));
            }
        } else if (z && isFullStateExceptFilter()) {
            resetCapsuleLayout();
        }
    }

    private void updateButton() {
        boolean z;
        int i2;
        int i7;
        if (this.mTextExtractionButton != null) {
            if (!this.mTextExtractionViewVisible || !isFullStateExceptFilter()) {
                z = false;
            } else {
                z = true;
            }
            View view = this.mTextExtractionButton;
            if (z) {
                i2 = R.drawable.gallery_detail_ai_icon_bg_dark;
            } else if (isHdr()) {
                i2 = R.drawable.gallery_detail_text_extraction_icon_hdr_bg;
            } else {
                i2 = R.drawable.gallery_detail_text_extraction_icon_bg;
            }
            view.setBackgroundResource(i2);
            ImageView imageView = (ImageView) this.mTextExtractionButton.findViewById(R.id.text_extraction_button_icon);
            if (imageView != null) {
                if (z) {
                    i7 = R.drawable.gallery_ic_detail_text_extraction_select;
                } else {
                    i7 = R.drawable.gallery_ic_detail_text_extraction_normal;
                }
                imageView.setImageResource(i7);
            }
        }
    }

    private void updateButtonMargin() {
        if (this.mTextExtractionButton != null) {
            int buttonBottomMargin = getButtonBottomMargin();
            ViewMarginUtils.setBottomMargin(this.mTextExtractionButton, buttonBottomMargin);
            ViewMarginUtils.setEndMargin(this.mTextExtractionButton, this.mTextExtractionButton.getResources().getDimensionPixelOffset(R.dimen.select_pictures_icon_container_side_margin) + RectUtils.getEnd(this.mModel.getContainerModel().getSystemInsets()));
            View view = this.mDocumentScanButton;
            if (view != null) {
                ViewMarginUtils.setBottomMargin(view, buttonBottomMargin);
            }
        }
    }

    private void updateCapsule() {
        int i2;
        updateCapsuleMargin();
        View view = this.mTextExtractionCapsuleLayout;
        int i7 = 8;
        if (isFullStateExceptFilter()) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(view, i2);
        View view2 = this.mTextExtractionLangManageLayout;
        if (isPartialState() || isFullState()) {
            i7 = 0;
        }
        ViewUtils.setVisibility(view2, i7);
    }

    private void updateCapsuleMargin() {
        if (this.mTextExtractionViewVisible) {
            View view = this.mTextExtractionCapsuleLayout;
            if (view != null) {
                ViewMarginUtils.setBottomMargin(view, this.mTextExtractionCapsuleLayout.getResources().getDimensionPixelOffset(R.dimen.text_extraction_capsule_layout_margin_bottom) + this.mModel.getContainerModel().getSystemInsetsIgnoringVisibility().bottom);
                ViewMarginUtils.setEndMargin(this.mTextExtractionCapsuleLayout, RectUtils.getEnd(this.mModel.getContainerModel().getSystemInsetsIgnoringVisibility()));
            }
            ViewUtils.post(this.mTextExtractionButton, new C0542a(this, 6));
        }
    }

    private void updateDocumentScanButtonVisibility(boolean z) {
        int i2;
        View view = this.mDocumentScanButton;
        if (view != null) {
            view.setOnClickListener(new c(this, 0));
            this.mDocumentScanButton.setAccessibilityDelegate(this.mAccessibilityDelegate);
            View view2 = this.mDocumentScanButton;
            if (!z || !supportDocumentScan()) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            ViewUtils.setVisibility(view2, i2);
        }
    }

    private void updateHint() {
        int i2 = 8;
        if (PreferenceCache.TextExtractionHint.getBoolean()) {
            updateHintMargin();
            updateHintBackgroundColor();
            View view = this.mTextExtractionHint;
            if (isFullState()) {
                i2 = 0;
            }
            SimpleAnimator.setVisibility(view, i2, 0);
            return;
        }
        SimpleAnimator.setVisibility(this.mTextExtractionHint, 8, 0);
    }

    private void updateHintBackgroundColor() {
        int i2;
        View view = this.mTextExtractionHint;
        if (view != null && this.mTextExtractionViewVisible) {
            if (isHdr()) {
                i2 = R.drawable.gallery_detail_text_extraction_hint_hdr_bg;
            } else {
                i2 = R.drawable.gallery_detail_text_extraction_hint_bg;
            }
            view.setBackgroundResource(i2);
        }
    }

    private void updateHintMargin() {
        int i2;
        View view = this.mTextExtractionHint;
        if (view != null && this.mTextExtractionViewVisible) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            int statusBarHeight = this.mModel.getSystemUi().getStatusBarHeight(this.mModel.getActivity());
            if (this.mModel.getContainerModel().isTableMode()) {
                i2 = 0;
            } else {
                i2 = SystemUi.getToolBarHeightWithPadding(this.mModel.getActivity());
            }
            layoutParams.topMargin = this.mTextExtractionHint.getResources().getDimensionPixelOffset(R.dimen.text_extraction_hint_margin_top) + statusBarHeight + i2;
            this.mTextExtractionHint.setLayoutParams(layoutParams);
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.BOTTOM_SHEET_STATE_CHANGED, new b(this, 0));
        this.mActionInvoker.add(ViewerAction.TOGGLE_OSD, new b(this, 1));
        this.mActionInvoker.add(ViewerAction.DISABLE_TEXT_EXTRACTION_VIEW, new b(this, 2));
        this.mActionInvoker.add(ViewerAction.DETAILS_SLIDE, new b(this, 3));
        this.mActionInvoker.add(ViewerAction.REQUEST_UPDATE_DECOR_LAYOUT_VISIBILITY, new b(this, 4));
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new b(this, 5));
        this.mActionInvoker.add(ViewerAction.WINDOW_FOCUS_CHANGED, new b(this, 6));
        this.mActionInvoker.add(ViewerAction.UPDATE_VIEWER_DECOR_VISIBILITY, new b(this, 7));
    }

    public abstract void bindTextExtractionView();

    public void clearInitPoint() {
        Optional.ofNullable(this.mTextHelper).ifPresent(new w(22));
    }

    public void clearTextExtractionViewVisibilityChangeFlag() {
        DeepSkyHelper.postDelayed(new C0542a(this, 1), 300);
    }

    public void clearTextSelection() {
        this.mIsDirty = false;
        this.mThread.runOnUiThread(new C0542a(this, 4));
    }

    public void detectDone() {
        boolean isButtonViewable = isButtonViewable();
        this.mIsDetecting = false;
        if (!isButtonViewable) {
            this.mActionInvoker.invoke(ViewerAction.LOAD_TIME_LAPSE, new Object[0]);
        }
        ViewUtils.post(this.mTextExtractionButton, new y7.e(this, isButtonViewable, 0));
        Optional.ofNullable(this.mTextHelper).ifPresent(new d(this, 2));
    }

    public void disableTextExtractionView(Object... objArr) {
        if (this.mTextExtractionView != null) {
            TextExtractionHelper textExtractionHelper = this.mTextHelper;
            if (textExtractionHelper != null) {
                if (textExtractionHelper.isFullFilterState()) {
                    this.mTextHelper.resetFilter();
                }
                this.mTextHelper.finishTextSelection();
                setState(this.mTextHelper, TextExtractionHelper.State.NONE);
            }
            this.mTextExtractionView.setVisionTextHelper((TextExtractionHelper) null);
            setTextExtractionViewVisibility(false);
        }
        Optional.ofNullable(this.mDetectTask).ifPresent(new w(24));
        Optional.ofNullable(this.mExtractTask).ifPresent(new w(24));
        Optional.ofNullable(this.mBitmapHolder).ifPresent(new w(12));
    }

    public abstract void extract(boolean z);

    public void extractDone() {
        ViewUtils.post(this.mTextExtractionView, new C0542a(this, 3));
    }

    public abstract String getAnalyticsLogDetail();

    public int getButtonBottomMargin() {
        int dimensionPixelOffset = this.mTextExtractionButton.getResources().getDimensionPixelOffset(R.dimen.play_button_view_bottom_margin) + this.mModel.getBottomOverlayHeight() + this.mModel.getContainerModel().getOverlaySizeIgnoringVisibility().bottom;
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.TextExtractionCapsule) || ViewerUtils.supportFilmStrip(this.mModel.getContainerModel().getLocationKey())) {
            return dimensionPixelOffset;
        }
        return this.mTextExtractionButton.getResources().getDimensionPixelOffset(R.dimen.photo_strip_view_out_height) + dimensionPixelOffset;
    }

    public String getContainerKey() {
        return this.mModel.getContainerModel().toString();
    }

    public Context getContext() {
        return this.mModel.getContext();
    }

    public MediaItem getMediaItem() {
        return this.mModel.getMediaItem();
    }

    public int getPosition() {
        return this.mModel.getContainerModel().getPosition();
    }

    public Dialog getProgressDialog() {
        return new ProgressCircleBuilder(this.mModel.getContext()).setLightTheme(true).create();
    }

    public TextExtractionTask getTextExtractionTask(TextExtractionTask.Mode mode, TextExtractionTask.OnCompleteListener onCompleteListener) {
        return new TextExtractionTask(this.mTextHelper, mode, this, onCompleteListener);
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        if (eventMessage.what != 3039) {
            return false;
        }
        handleDocumentScanSuccess();
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem == null) {
            return true;
        }
        new CleanCmhMediaContentInfoCmd().execute(this.mModel.getContainerModel().getEventContext(), Long.valueOf(mediaItem.getFileId()));
        return true;
    }

    public void handleButtonClickTextExtractionViewInvisibleState() {
        setState(this.mTextHelper, TextExtractionHelper.State.FULL);
        extract(false);
    }

    public void handleButtonClickTextExtractionViewVisibleState() {
        if (!isFullStateExceptFilter()) {
            if (isFullFilterState()) {
                resetFilter();
            } else if (isSelectedDirectCheck()) {
                removeAllCapsules();
                setState(this.mTextHelper, TextExtractionHelper.State.FULL_INDIRECT);
            } else {
                setState(this.mTextHelper, TextExtractionHelper.State.FULL);
            }
            extract(false);
            clearTextExtractionViewVisibilityChangeFlag();
        } else if (!isFullIndirectState() || !isSelectedDirectCheck()) {
            disableTextExtractionView(new Object[0]);
        } else {
            setState(this.mTextHelper, TextExtractionHelper.State.PARTIAL);
            clearTextExtractionViewVisibilityChangeFlag();
        }
        TextExtractionView textExtractionView = this.mTextExtractionView;
        if (textExtractionView != null) {
            textExtractionView.invalidate();
        }
    }

    public void handleDocumentScanSuccess() {
        if (this.mModel.getSystemUi().isScreenLocked() || LocationKey.isFile(this.mModel.getContainerModel().getLocationKey())) {
            getBlackboard().post("command://event/DataDirty", (Object) null);
        }
    }

    public boolean hasData() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper == null || !textExtractionHelper.hasData()) {
            return false;
        }
        return true;
    }

    public boolean isButtonViewable() {
        if (!hasData() || BottomSheetState.isExpanded(this.mModel) || BlackboardUtils.isViewerShrinkToCamera(getBlackboard())) {
            return false;
        }
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.TextExtractionCapsule) && isFullState()) {
            return true;
        }
        if (!this.mIsDecorViewVisible || !this.mModel.getContainerModel().isOsdVisible()) {
            return false;
        }
        return true;
    }

    public boolean isDetected() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper == null || !textExtractionHelper.isDetected()) {
            return false;
        }
        return true;
    }

    public boolean isDocumentScanned() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper == null || !textExtractionHelper.isDocumentScanned()) {
            return false;
        }
        return true;
    }

    public boolean isExtractable() {
        return true;
    }

    public boolean isExtracted() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper == null || !textExtractionHelper.isExtracted()) {
            return false;
        }
        return true;
    }

    public boolean isFullFilterState() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper == null || !textExtractionHelper.isFullFilterState()) {
            return false;
        }
        return true;
    }

    public boolean isTextHelperReady() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper == null || !textExtractionHelper.isReady()) {
            return false;
        }
        return true;
    }

    public boolean isValid(MediaItem mediaItem) {
        if (LocationKey.isSecondDepthGroupPanelView(this.mModel.getContainerModel().getLocationKey())) {
            return false;
        }
        if ((mediaItem == null || !mediaItem.isPostProcessing()) && mediaItem != null && !mediaItem.isDrm() && !mediaItem.isBroken() && !TrashData.isTrash(mediaItem) && !mediaItem.isMtp() && !mediaItem.isUriItem() && this.mTextHelper != null) {
            return true;
        }
        return false;
    }

    public void onApplyWindowInsets() {
        refreshLayout();
    }

    public void onButtonClick(View view) {
        Log.majorEvent("t button clicked");
        boolean z = false;
        this.mActionInvoker.invoke(ViewerAction.DISABLE_OBJECT_CAPTURE_VIEW, new Object[0]);
        if (this.mTextHelper == null) {
            Log.e(this.TAG, "unable to click button, textHelper is null");
        } else if (this.mTextExtractionViewVisibilityChanging) {
            Log.e(this.TAG, "unable to click button, previous request is running");
        } else {
            this.mTextExtractionViewVisibilityChanging = true;
            if (this.mTextExtractionViewVisible) {
                handleButtonClickTextExtractionViewVisibleState();
            } else {
                handleButtonClickTextExtractionViewInvisibleState();
                postAnalyticsDetailLog(AnalyticsEventId.EVENT_DETAIL_VIEW_EXTRACT_TEXT.toString(), getAnalyticsLogDetail());
            }
            if (PreferenceFeatures.isEnabled(PreferenceFeatures.TextExtractionCapsule)) {
                ActionInvoker actionInvoker = this.mActionInvoker;
                ViewerAction viewerAction = ViewerAction.SET_DECOR_VISIBILITY;
                if (!isFullStateExceptFilter() && this.mModel.getContainerModel().isOsdVisible()) {
                    z = true;
                }
                actionInvoker.invoke(viewerAction, Boolean.valueOf(z));
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        refreshLayout();
        resetCapsuleLayout();
    }

    public void onInitialized() {
        this.mActionInvoker.invoke(ViewerAction.REG_VIEW_LONG_PRESS_LISTENER, this, 10);
    }

    public void onPause() {
        this.mPaused = true;
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.TextExtractionCapsule)) {
            disableTextExtractionView(new Object[0]);
        }
    }

    public void onResolutionChanged() {
        View view = this.mTextExtractionButton;
        if (!(view == null || this.mTextExtractionButtonViewStub == null)) {
            boolean isVisible = ViewUtils.isVisible(view);
            ViewUtils.replaceView(this.mTextExtractionButton, this.mTextExtractionButtonViewStub);
            this.mTextExtractionButton = this.mTextExtractionButtonViewStub.inflate();
            setButtonVisibility(isVisible, false);
        }
        View view2 = this.mDocumentScanButton;
        if (view2 != null && this.mDocumentScanButtonViewStub != null) {
            boolean isVisible2 = ViewUtils.isVisible(view2);
            ViewUtils.replaceView(this.mDocumentScanButton, this.mDocumentScanButtonViewStub);
            this.mDocumentScanButton = this.mDocumentScanButtonViewStub.inflate();
            updateDocumentScanButtonVisibility(isVisible2);
        }
    }

    public void onResume() {
        this.mPaused = false;
        onWindowFocusChanged(Boolean.TRUE);
    }

    public void onTableModeChanged(boolean z, int i2) {
        if (!BottomSheetState.Details.isClosed(this.mModel)) {
            if (this.mTextExtractionViewVisible) {
                disableTextExtractionView(new Object[0]);
            }
            setButtonVisibility(false, false);
            return;
        }
        updateButtonMargin();
        updateCapsuleMargin();
        updateHintMargin();
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper != null) {
            textExtractionHelper.setPopupMenuVisibility(false);
            DeepSkyHelper.postDelayed(new C0542a(this, 0), 500);
        }
    }

    public void onViewAttached() {
        super.onViewAttached();
        this.mPaused = false;
        setButtonVisibility(isButtonViewable(), false);
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        this.mIsDecorViewVisible = this.mModel.getContainerModel().isOsdVisible();
        initView();
        resetCapsuleLayout();
    }

    public void onViewDetached() {
        super.onViewDetached();
        if (isExtracted()) {
            this.mIsDirty = true;
        }
        disableTextExtractionView(new Object[0]);
        this.mIsDetecting = false;
        Optional.ofNullable(this.mTextHelper).ifPresent(new w(25));
        Optional.ofNullable(this.mBitmapHolder).ifPresent(new w(12));
        Optional.ofNullable(this.mDetectRunnable).ifPresent(new w(26));
        this.mBitmapHolder = null;
        this.mDetectTask = null;
        this.mExtractTask = null;
        this.mIsDirty = false;
        this.mIsDetecting = false;
        this.mTextExtractionViewVisibilityChanging = false;
        this.mTextExtractionViewVisible = false;
    }

    public boolean onViewLongPress(float f, float f5) {
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("extract(LP) req");
        sb2.append(Logger.v("(" + f + GlobalPostProcInternalPPInterface.SPLIT_REGEX + f5 + ")", this.mTextHelper));
        Log.d(stringCompat, sb2.toString());
        if (this.mTextHelper == null || !isExtractable()) {
            return false;
        }
        this.mTextExtractionViewVisibilityChanging = true;
        this.mTextHelper.setInitPoint(f, f5);
        setState(this.mTextHelper, TextExtractionHelper.State.PARTIAL);
        extract(true);
        PreferenceCache.TextExtractionHint.setBoolean(false);
        return true;
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        Optional.ofNullable(this.mDetectRunnable).ifPresent(new w(26));
        ViewUtils.setVisibility(this.mTextExtractionButton, 8);
        ViewUtils.setVisibility(this.mTextExtractionCapsuleLayout, 8);
        ViewUtils.setVisibility(this.mTextExtractionHint, 8);
        ViewUtils.setVisibility(this.mTextExtractionLangManageLayout, 8);
        ViewUtils.setVisibility(this.mDocumentScanButton, 8);
        TextExtractionView textExtractionView = this.mTextExtractionView;
        if (textExtractionView != null) {
            textExtractionView.destroy();
            ViewUtils.setVisibility(this.mTextExtractionView, 8);
        }
        this.mTextExtractionLayout = null;
        this.mTextExtractionButton = null;
        this.mTextExtractionCapsuleLayout = null;
        this.mTextExtractionHint = null;
        this.mTextExtractionLangManageLayout = null;
        this.mTextExtractionView = null;
        this.mDocumentScanButton = null;
    }

    public abstract void refreshLayout();

    public void requestCaptureProgress(boolean z) {
        TextExtractionHelper textExtractionHelper;
        if (PocFeatures.CLIP_PARALLEL) {
            if (!z || (textExtractionHelper = this.mTextHelper) == null) {
                this.mActionInvoker.invoke(ViewerAction.OBJECT_CAPTURE_PROGRESS_END, new Object[0]);
                return;
            }
            float initX = textExtractionHelper.getInitX();
            float initY = this.mTextHelper.getInitY();
            if (initX != Float.MIN_VALUE && initY != Float.MIN_VALUE) {
                this.mActionInvoker.invoke(ViewerAction.OBJECT_CAPTURE_PROGRESS_START, Float.valueOf(this.mTextHelper.getInitX()), Float.valueOf(this.mTextHelper.getInitY()));
            }
        }
    }

    public void resetCapsuleLayout() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper != null) {
            textExtractionHelper.configurationChanged();
        }
    }

    public void setButtonVisibility(boolean z, boolean z3) {
        View view = this.mTextExtractionButton;
        if (view == null || this.mIsDetecting) {
            Log.d(this.TAG, "setButtonVisibility skip");
            return;
        }
        if (z) {
            view.setOnClickListener(new c(this, 1));
            this.mTextExtractionButton.setAccessibilityDelegate(this.mAccessibilityDelegate);
            this.mTextExtractionButton.setAlpha(1.0f);
            updateButton();
            updateDocumentScanButton();
            if (z3) {
                SimpleAnimator.setVisibility(this.mTextExtractionButton, 0, 200);
            } else {
                ViewUtils.setVisibility(this.mTextExtractionButton, 0);
            }
        } else {
            ViewUtils.setVisibility(view, 8);
        }
        updateButtonMargin();
    }

    public void setState(TextExtractionHelper textExtractionHelper, TextExtractionHelper.State state) {
        this.mModel.setTextExtractionState(state);
        setExclusiveListener(state);
        if (textExtractionHelper != null) {
            textExtractionHelper.setState(state);
            textExtractionHelper.setDim();
        }
        if (!this.mPaused || this.mIsDecorViewVisible) {
            setButtonVisibility(isButtonViewable(), false);
        } else {
            Optional.ofNullable(this.mModel.getContainerModel().getEventContext()).ifPresent(new d(this, 3));
        }
        updateButton();
    }

    public boolean supportDocumentScan() {
        MediaItem mediaItem;
        if (!isFullStateExceptFilter() || (mediaItem = getMediaItem()) == null || mediaItem.isSharing() || mediaItem.isDrm() || mediaItem.isPrivateItem() || (!DocumentScanner.getInstance().supportDocumentScan(mediaItem) && !DocumentScanner.getInstance().hasDocument(mediaItem))) {
            return false;
        }
        return true;
    }

    public String toString() {
        String str;
        String str2;
        StringBuilder sb2 = new StringBuilder("DelLT[t=");
        sb2.append(this.mTextHelper);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (this.mTextExtractionViewVisible) {
            str = MediaApiContract.Parameter.ENABLED;
        } else {
            str = "disabled";
        }
        sb2.append(str);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (this.mIsDirty) {
            str2 = "dirty";
        } else {
            str2 = "clean";
        }
        return C0212a.p(sb2, str2, "]");
    }

    public void updateDocumentScanButton() {
        View view;
        TextView textView;
        if (hasData() && (view = this.mDocumentScanButton) != null && (textView = (TextView) view.findViewById(R.id.button_text)) != null) {
            float dimensionPixelSize = (float) textView.getResources().getDimensionPixelSize(R.dimen.play_button_view_font_size);
            if (dimensionPixelSize <= 0.0f) {
                dimensionPixelSize = textView.getTextSize();
            }
            float f = textView.getResources().getConfiguration().fontScale;
            if (f > 1.3f) {
                dimensionPixelSize = (dimensionPixelSize / f) * 1.3f;
            }
            textView.setTextSize(0, dimensionPixelSize);
        }
    }

    public void updateMargin() {
        updateButtonMargin();
        updateCapsuleMargin();
        updateHintMargin();
    }

    private boolean isNoneState() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        return textExtractionHelper == null || textExtractionHelper.isNoneState();
    }

    public void resetFilter() {
    }
}
