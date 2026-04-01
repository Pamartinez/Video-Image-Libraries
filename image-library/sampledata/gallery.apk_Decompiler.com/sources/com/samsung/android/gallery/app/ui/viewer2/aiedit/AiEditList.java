package com.samsung.android.gallery.app.ui.viewer2.aiedit;

import A4.P;
import Ad.C0720a;
import a6.C0419b;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import androidx.core.util.Pair;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItem;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItemFactory;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterAutoTiltAiEdit;
import com.samsung.android.gallery.module.aiedit.AiEditBixbyRequest;
import com.samsung.android.gallery.module.aiedit.AiEditThread;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.aiedit.RemasterDetector;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.animations.textexpand.TextExpandAnimListView;
import com.samsung.android.gallery.widget.animations.textexpand.TextExpandAnimView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.o3dp.lib3dphotography.i;
import com.samsung.scsp.media.api.d;
import f4.a;
import g7.C0461d;
import g7.C0462e;
import g7.C0463f;
import g7.g;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AiEditList {
    private final ActionInvoker mActionInvoker;
    private final EventContext mEventContext;
    private ArrayList<AiEditItem> mImageList;
    private final Map<AiEditType, TextExpandAnimView> mItemViewMap = new HashMap();
    private TextExpandAnimListView mListView;
    private MediaItem mMediaItem;
    private ArrayList<AiEditItem> mVideoList;
    private final CopyOnWriteArrayList<AiEditItem> mVisibleItemList = new CopyOnWriteArrayList<>();

    public AiEditList(EventContext eventContext, ActionInvoker actionInvoker, ViewGroup viewGroup) {
        this.mEventContext = eventContext;
        this.mActionInvoker = actionInvoker;
        if (viewGroup != null) {
            TextExpandAnimListView textExpandAnimListView = (TextExpandAnimListView) viewGroup;
            this.mListView = textExpandAnimListView;
            textExpandAnimListView.updateMargin(false);
        }
    }

    /* access modifiers changed from: private */
    public TextExpandAnimView bindAndGetView(AiEditItem aiEditItem) {
        AiEditType type = aiEditItem.getType();
        TextExpandAnimView textExpandAnimView = this.mItemViewMap.get(type);
        if (textExpandAnimView != null) {
            return textExpandAnimView;
        }
        TextExpandAnimView textExpandAnimView2 = new TextExpandAnimView(this.mListView.getContext());
        textExpandAnimView2.setOnClickListener(new C0419b(12, aiEditItem));
        textExpandAnimView2.setIconAndTitle(type.drawableResId, type.titleResId);
        ViewUtils.addView(this.mListView, textExpandAnimView2);
        this.mItemViewMap.put(type, textExpandAnimView2);
        return textExpandAnimView2;
    }

    private void clearThread() {
        AiEditThread.removeCallbacks();
    }

    private ArrayList<AiEditItem> getCandidateList(boolean z) {
        if (z && this.mImageList == null) {
            this.mImageList = AiEditItemFactory.createList(this.mEventContext, this.mActionInvoker, true);
        } else if (!z && this.mVideoList == null) {
            this.mVideoList = AiEditItemFactory.createList(this.mEventContext, this.mActionInvoker, false);
        }
        if (z) {
            return this.mImageList;
        }
        return this.mVideoList;
    }

    private String getVisibleListAnalyticsDetail() {
        StringBuilder sb2 = new StringBuilder();
        this.mVisibleItemList.forEach(new P(sb2, 5));
        return sb2.toString();
    }

    private boolean isAiEditTypeForBixby(AiEditType aiEditType) {
        if (!aiEditType.equals(AiEditType.AutoTilt) || !RemasterDetector.SUPPORT_AUTO_TILT) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$executeIntelligenceItem$6(AiEditType aiEditType, AiEditItem aiEditItem) {
        if (aiEditItem.getType() == aiEditType) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getVisibleListAnalyticsDetail$7(StringBuilder sb2, AiEditItem aiEditItem) {
        sb2.append(aiEditItem.getType().eventId);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadAndUpdateVisibility$3(BooleanSupplier booleanSupplier, MediaItem mediaItem, ArrayList arrayList) {
        if (booleanSupplier.getAsBoolean()) {
            Trace.beginSection("AiEdit#updateView");
            Log.d("AiEditList", "update list", Long.valueOf(mediaItem.getFileId()), Integer.valueOf(arrayList.size()));
            this.mVisibleItemList.clear();
            this.mVisibleItemList.addAll(arrayList);
            this.mListView.updateViewList((ArrayList) this.mVisibleItemList.stream().map(new b(20, this)).collect(Collectors.toCollection(new C0720a(1))));
            updateVisibility();
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$recycle$5(AiEditItem aiEditItem) {
        ViewUtils.setVisibility(this.mItemViewMap.get(aiEditItem.getType()), 8);
        aiEditItem.recycle();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$requestLoadAndExecuteDirectly$10(AiEditBixbyRequest aiEditBixbyRequest, AiEditItem aiEditItem) {
        if (aiEditItem.getLoadableItem(aiEditBixbyRequest.type) != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestLoadAndExecuteDirectly$12(AiEditItem aiEditItem, AiEditBixbyRequest aiEditBixbyRequest) {
        boolean z;
        if (!aiEditBixbyRequest.autoSave || !aiEditBixbyRequest.supportAutoSave) {
            z = false;
        } else {
            z = true;
        }
        aiEditItem.onMenuSelect(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateMediaItemAndVisibility$0(AiEditItem aiEditItem) {
        ViewUtils.setVisibility(this.mItemViewMap.get(aiEditItem.getType()), 8);
    }

    /* access modifiers changed from: private */
    /* renamed from: loadAndUpdateVisibility */
    public void lambda$updateMediaItemAndVisibility$1(MediaItem mediaItem, Bitmap bitmap, BooleanSupplier booleanSupplier) {
        if (!unsupportableMediaItem(mediaItem) && booleanSupplier.getAsBoolean()) {
            long currentTimeMillis = System.currentTimeMillis();
            Trace.beginSection("AiEdit");
            if (MediaItemUtil.hasWatermarkFrameInfo(mediaItem)) {
                Trace.endSection();
                return;
            }
            ArrayList<AiEditItem> candidateList = getCandidateList(mediaItem.isImage());
            ArrayList arrayList = new ArrayList();
            try {
                Iterator<AiEditItem> it = candidateList.iterator();
                while (it.hasNext()) {
                    AiEditItem next = it.next();
                    if (!booleanSupplier.getAsBoolean()) {
                        Trace.endSection();
                        return;
                    }
                    Trace.beginSection("#" + next.TAG);
                    Optional.ofNullable(next.load(mediaItem, bitmap)).ifPresent(new a(arrayList, 11));
                    Trace.endSection();
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("load");
                Long valueOf = Long.valueOf(mediaItem.getFileId());
                sb2.append(Logger.vt(valueOf, arrayList.size() + "/" + candidateList.size(), Long.valueOf(currentTimeMillis)));
                Log.d("AiEditList", sb2.toString());
                arrayList.sort(Comparator.comparing(new d(19)));
                Trace.endSection();
                ThreadUtil.postOnUiThread(new a8.d((Object) this, (Object) booleanSupplier, mediaItem, (Object) arrayList, 11));
            } catch (Throwable th) {
                Throwable th2 = th;
                Trace.endSection();
                throw th2;
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: requestExecuteDirectlyForBixby */
    public void lambda$requestDirectly$8(AiEditBixbyRequest aiEditBixbyRequest) {
        if (unsupportableMediaItem(aiEditBixbyRequest.item) || !aiEditBixbyRequest.isValid()) {
            Log.e("AiEditList", "directly execute failed :invalid item");
            responseMessage(aiEditBixbyRequest, true);
            return;
        }
        RemasterAutoTiltAiEdit remasterAutoTiltAiEdit = new RemasterAutoTiltAiEdit(this.mEventContext, this.mActionInvoker);
        if (remasterAutoTiltAiEdit.support(aiEditBixbyRequest.item)) {
            Pair<Boolean, String> detectSingle = RemasterDetector.getInstance().detectSingle(aiEditBixbyRequest.item, new ArrayList(20).toString(), "AUTOTILT");
            Log.bx("AiEditList", "direct request from bixby " + detectSingle);
            if (detectSingle != null && ((Boolean) detectSingle.first).booleanValue() && remasterAutoTiltAiEdit.isValidValue((String) detectSingle.second)) {
                ThreadUtil.postOnUiThread(new i(27, remasterAutoTiltAiEdit, aiEditBixbyRequest));
                responseMessage(aiEditBixbyRequest, false);
                return;
            }
        }
        responseMessage(aiEditBixbyRequest, true);
    }

    /* access modifiers changed from: private */
    /* renamed from: requestLoadAndExecuteDirectly */
    public void lambda$requestDirectly$9(AiEditBixbyRequest aiEditBixbyRequest) {
        boolean z;
        if (unsupportableMediaItem(aiEditBixbyRequest.item) || !aiEditBixbyRequest.isValid()) {
            Log.e("AiEditList", "directly execute failed :invalid item");
            responseMessage(aiEditBixbyRequest, true);
            return;
        }
        AiEditItem aiEditItem = null;
        AiEditItem orElse = getCandidateList(aiEditBixbyRequest.item.isImage()).stream().filter(new C0461d(aiEditBixbyRequest, 0)).findFirst().orElse((Object) null);
        if (orElse != null) {
            long currentTimeMillis = System.currentTimeMillis();
            Log.d("AiEditList", "directly request load", Long.valueOf(aiEditBixbyRequest.item.getFileId()), orElse.getClass().getSimpleName());
            List<AiEditItem> load = orElse.load(aiEditBixbyRequest.item, aiEditBixbyRequest.bitmap);
            if (load != null) {
                aiEditItem = load.stream().filter(new C0461d(aiEditBixbyRequest, 1)).findFirst().orElse((Object) null);
            }
            StringBuilder sb2 = new StringBuilder("directly load done");
            if (aiEditItem != null) {
                z = true;
            } else {
                z = false;
            }
            A.a.A(new Object[]{Boolean.valueOf(z), Long.valueOf(aiEditBixbyRequest.item.getFileId()), Long.valueOf(currentTimeMillis)}, sb2, "AiEditList");
            if (aiEditItem != null) {
                aiEditBixbyRequest.setSupportAutoSave(aiEditItem.supportAutoSave());
                ThreadUtil.postOnUiThread(new i(26, aiEditItem, aiEditBixbyRequest));
                responseMessage(aiEditBixbyRequest, false);
                return;
            }
        }
        responseMessage(aiEditBixbyRequest, true);
    }

    private void responseMessage(AiEditBixbyRequest aiEditBixbyRequest, boolean z) {
        if (z) {
            aiEditBixbyRequest.acceptMsg("unsupported");
        } else if (!aiEditBixbyRequest.autoSave || !aiEditBixbyRequest.supportAutoSave) {
            aiEditBixbyRequest.acceptMsg("no_error");
        }
    }

    private boolean unsupportableMediaItem(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isBroken() || mediaItem.isDrm() || mediaItem.isPrivateItem() || mediaItem.isSharing() || mediaItem.isPostProcessing() || mediaItem.isUriItem()) {
            return true;
        }
        return false;
    }

    private void updateVisibility() {
        if (this.mVisibleItemList.isEmpty()) {
            this.mListView.hide();
            return;
        }
        this.mListView.showWithAnim();
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_DETAILS_NORMAL.toString(), AnalyticsEventId.EVENT_AI_EDIT_SHOW.toString(), getVisibleListAnalyticsDetail());
    }

    public void executeIntelligenceItem(Object[] objArr) {
        boolean z = false;
        try {
            Long l = objArr[0];
            long longValue = l.longValue();
            AiEditType aiEditType = objArr[1];
            Boolean bool = objArr[2];
            boolean booleanValue = bool.booleanValue();
            MediaItem mediaItem = this.mMediaItem;
            if (mediaItem == null || mediaItem.getFileId() == longValue) {
                AiEditItem orElse = this.mVisibleItemList.stream().filter(new C0463f(aiEditType, 0)).findAny().orElse((Object) null);
                if (orElse != null) {
                    z = true;
                }
                Log.d("AiEditList", "intelligence ai edits execute", l, aiEditType, Boolean.valueOf(z), bool);
                if (orElse != null) {
                    orElse.onMenuSelect(this.mItemViewMap.get(aiEditType), booleanValue);
                    return;
                }
                return;
            }
            Log.e((CharSequence) "AiEditList", "intelligence ai edits execute failed: invalid item", l, Long.valueOf(this.mMediaItem.getFileId()));
        } catch (ClassCastException e) {
            Log.e("AiEditList", "unexpected data" + e.getMessage());
        }
    }

    public void hideView() {
        TextExpandAnimListView textExpandAnimListView = this.mListView;
        if (textExpandAnimListView != null) {
            textExpandAnimListView.hide();
        }
    }

    public void onConfigurationChanged() {
        TextExpandAnimListView textExpandAnimListView = this.mListView;
        if (textExpandAnimListView != null) {
            textExpandAnimListView.updateMargin(true);
        }
    }

    public void recycle() {
        clearThread();
        this.mVisibleItemList.forEach(new C0462e(this, 1));
        this.mMediaItem = null;
        this.mVisibleItemList.clear();
    }

    public void requestDirectly(AiEditBixbyRequest aiEditBixbyRequest) {
        AiEditType aiEditType = aiEditBixbyRequest.type;
        if (aiEditType == null) {
            responseMessage(aiEditBixbyRequest, true);
        } else if (isAiEditTypeForBixby(aiEditType)) {
            AiEditThread.post(new g(this, aiEditBixbyRequest, 0));
        } else {
            AiEditThread.post(new g(this, aiEditBixbyRequest, 1));
        }
    }

    public void setImportantForAccessibility(boolean z) {
        this.mListView.setImportantForAccessibility(z);
    }

    public void updateMediaItemAndVisibility(MediaItem mediaItem, Bitmap bitmap, boolean z, BooleanSupplier booleanSupplier) {
        boolean z3;
        if (!unsupportableMediaItem(mediaItem)) {
            if (z || mediaItem.isShotMode("Dual capture")) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (!MediaItemUtil.equals(mediaItem, this.mMediaItem) || z3 || this.mVisibleItemList.isEmpty()) {
                this.mMediaItem = mediaItem;
                clearThread();
                this.mVisibleItemList.forEach(new C0462e(this, 0));
                this.mVisibleItemList.clear();
                AiEditThread.post(new a8.d((Object) this, (Object) mediaItem, (Object) bitmap, (Object) booleanSupplier, 12));
                return;
            }
            Log.d("AiEditList", "skip update.", Long.valueOf(mediaItem.getFileId()), Integer.valueOf(this.mVisibleItemList.size()));
            updateVisibility();
        }
    }

    public void updateVisibilityBy(boolean z) {
        ViewUtils.setVisibleOrGone(this.mListView, !z);
    }
}
