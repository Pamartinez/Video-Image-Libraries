package com.samsung.android.gallery.app.ui.viewer2.menu;

import A4.C0385u;
import Q7.a;
import Q7.b;
import Q7.c;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.VuAnalytics;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.app.controller.externals.PlayFastMotionVideoCmd;
import com.samsung.android.gallery.app.controller.externals.PlaySlowMotionVideoCmd;
import com.samsung.android.gallery.app.controller.externals.PlaySuperSlowMotionVideoCmd;
import com.samsung.android.gallery.app.controller.externals.StartAgifEditorCmd;
import com.samsung.android.gallery.app.controller.externals.StartImage360EditorCmd;
import com.samsung.android.gallery.app.controller.externals.StartSimplePhotoEditorCmd;
import com.samsung.android.gallery.app.controller.externals.StartVideoEditorCmd;
import com.samsung.android.gallery.app.controller.sharing.EditSharingItemCmd;
import com.samsung.android.gallery.app.controller.story.EditNotifiedContentCmd;
import com.samsung.android.gallery.app.controller.viewer.DirectorsViewEditCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.database.dal.local.table.HistoryTable;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.debugger.LocalProviderDebugHelper;
import com.samsung.android.gallery.module.editor.ModeInfo;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.nondestruction.NondestructiveEditor;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.shotmode.RecordingMode;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditMenuItem extends ViewerMenuItem {
    private static boolean ENABLE_PPP = false;
    private final Runnable[] mAppTransitionCallback = {null, new c(this, 0), new c(this, 1)};

    /* renamed from: com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$app$ui$viewer2$menu$EditMenuItem$VIDEO_TYPE;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem$VIDEO_TYPE[] r0 = com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem.VIDEO_TYPE.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$app$ui$viewer2$menu$EditMenuItem$VIDEO_TYPE = r0
                com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem$VIDEO_TYPE r1 = com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem.VIDEO_TYPE.FAST     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$ui$viewer2$menu$EditMenuItem$VIDEO_TYPE     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem$VIDEO_TYPE r1 = com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem.VIDEO_TYPE.SLOW     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$ui$viewer2$menu$EditMenuItem$VIDEO_TYPE     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem$VIDEO_TYPE r1 = com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem.VIDEO_TYPE.SUPER_SLOW     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum VIDEO_TYPE {
        NORMAL,
        FAST,
        SLOW,
        SUPER_SLOW
    }

    public EditMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.edit);
    }

    /* access modifiers changed from: private */
    /* renamed from: executeEditor */
    public void lambda$handleEditor$4(MediaItem mediaItem, Runnable runnable) {
        boolean supportGifEdit = supportGifEdit(mediaItem);
        if (!supportGifEdit) {
            this.mEventContext.getBlackboard().publish("data://viewer_app_transition_callback", this.mAppTransitionCallback);
        }
        if (mediaItem.isVideo()) {
            startVideoEditor(mediaItem, runnable);
        } else if (mediaItem.is360Image()) {
            new StartImage360EditorCmd().execute(this.mEventContext, mediaItem, runnable);
        } else if (supportGifEdit) {
            new StartAgifEditorCmd().execute(this.mEventContext, mediaItem);
        } else {
            startSimpleEditor(mediaItem, runnable);
        }
        LocalProviderDebugHelper.addOperationHistory(HistoryTable.ActionKeyword.EDIT, Collections.singletonList(mediaItem));
    }

    private String getAnalyticsDetailId(MediaItem mediaItem) {
        AnalyticsDetail analyticsDetail;
        if (mediaItem.isVideo()) {
            if (mediaItem.is360Video()) {
                analyticsDetail = AnalyticsDetail.PHOTO_EDITOR_TYPE_VIDEO_360;
            } else {
                analyticsDetail = AnalyticsDetail.PHOTO_EDITOR_TYPE_VIDEO;
            }
        } else if (mediaItem.is360Image()) {
            analyticsDetail = AnalyticsDetail.PHOTO_EDITOR_TYPE_IMAGE_360;
        } else if (supportGifEdit(mediaItem)) {
            analyticsDetail = AnalyticsDetail.PHOTO_EDITOR_TYPE_IMAGE_AGIF;
        } else if (mediaItem.isRawImage()) {
            analyticsDetail = AnalyticsDetail.PHOTO_EDITOR_TYPE_IMAGE_RAW;
        } else if (mediaItem.isJpeg()) {
            analyticsDetail = AnalyticsDetail.PHOTO_EDITOR_TYPE_IMAGE_JPEG;
        } else {
            analyticsDetail = AnalyticsDetail.PHOTO_EDITOR_TYPE_ETC;
        }
        return analyticsDetail.toString();
    }

    private Object getModeInfo(EventContext eventContext) {
        Object eventContextData = eventContext.getEventContextData("editSubInfo");
        if (eventContextData != null) {
            return new ModeInfo(5, (Uri) null, (String[]) eventContextData);
        }
        return null;
    }

    private void handleDirectorsViewEdit(MediaItem mediaItem, Runnable runnable) {
        new DirectorsViewEditCmd().execute(this.mEventContext, mediaItem, new a(this, runnable, 0));
    }

    private boolean isEditableImage(MediaItem mediaItem) {
        if (mediaItem.isGif()) {
            return Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT_GIF);
        }
        return Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT_PHOTO);
    }

    private boolean isEditableOnFamilyAlbum(MediaItem mediaItem, String str) {
        if (!MdeGroupHelper.isSAFamilyGroup(ArgumentsUtil.getArgValue(str, "groupId", (String) null))) {
            return false;
        }
        if (mediaItem.isImage()) {
            return isEditableImage(mediaItem);
        }
        return isEditableVideo(mediaItem);
    }

    private boolean isEditableVideo(MediaItem mediaItem) {
        if (useVideoEditorLite(mediaItem)) {
            return Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT_LITE_VIDEO);
        }
        return Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT_VIDEO);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$checkSharing$8(MediaItem mediaItem, String str) {
        if (mediaItem == null) {
            return false;
        }
        if (!mediaItem.isSharing() || isEditableOnFamilyAlbum(mediaItem, str)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeAfterDownloadInSharing$7(Runnable runnable, MediaItem mediaItem) {
        Activity readActivity = BlackboardUtils.readActivity(this.mEventContext.getBlackboard());
        String hiddenFilePath = MediaItemMde.getHiddenFilePath(mediaItem);
        Uri hiddenContentUri = MediaItemMde.getHiddenContentUri(mediaItem);
        if (TextUtils.isEmpty(hiddenFilePath) || hiddenContentUri == null || !SeApiCompat.isActivityResumed(readActivity)) {
            Log.e(this.TAG, "failed to start editor -> activity is paused");
        } else {
            ThreadUtil.runOnUiThread(new b(this, mediaItem, runnable, 1));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEditor$2(Runnable runnable, Object obj) {
        if (SeApiCompat.isActivityResumed(BlackboardUtils.readActivity(this.mEventContext.getBlackboard()))) {
            handleEditor((MediaItem) obj, runnable);
        } else {
            Log.d(this.TAG, "failed to start editor -> activity is paused");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEditor$3(Runnable runnable, Object obj) {
        if (SeApiCompat.isActivityResumed(BlackboardUtils.readActivity(this.mEventContext.getBlackboard()))) {
            handleEditor((MediaItem) obj, runnable);
        } else {
            Log.d(this.TAG, "failed to start editor -> activity is paused");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMenuSelectInternal$1(MediaItem mediaItem) {
        handleEditor(mediaItem, new c(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$0() {
        return MediaItemMde.isSharingEditedItemUploading(this.mEventContext.getCurrentItem());
    }

    private void startSimpleEditor(MediaItem mediaItem, Runnable runnable) {
        if (this.mEventContext == null) {
            Log.e(this.TAG, "startSimpleEditor failed. null eventContext or item");
            return;
        }
        this.mActionInvoker.invoke(ViewerAction.ZOOM_TO_MIN_SCALE, new Object[0]);
        if (runnable != null) {
            runnable.run();
        }
        StartSimplePhotoEditorCmd startSimplePhotoEditorCmd = new StartSimplePhotoEditorCmd();
        EventContext eventContext = this.mEventContext;
        startSimplePhotoEditorCmd.execute(eventContext, mediaItem, eventContext.getEventContextData("editMode"), null, Boolean.FALSE, getModeInfo(this.mEventContext));
    }

    private void startVideoEditor(MediaItem mediaItem, Runnable runnable) {
        int recordingMode = mediaItem.getRecordingMode();
        boolean z = false;
        if (RecordingMode.isSlowMo(recordingMode)) {
            startVideoEditor(mediaItem, runnable, VIDEO_TYPE.SLOW, false);
        } else if (RecordingMode.isSuperSlowMo(recordingMode)) {
            VIDEO_TYPE video_type = VIDEO_TYPE.SUPER_SLOW;
            if (recordingMode == 8) {
                z = true;
            }
            startVideoEditor(mediaItem, runnable, video_type, z);
        } else if ((RecordingMode.isLogVideo(recordingMode) && SdkConfig.lessThan(SdkConfig.SEM.B_MR5)) || mediaItem.is3dCapture()) {
            startVideoEditor(mediaItem, runnable, VIDEO_TYPE.NORMAL, true);
        } else if (recordingMode == 2) {
            startVideoEditor(mediaItem, runnable, VIDEO_TYPE.FAST, true);
        } else {
            startVideoEditor(mediaItem, runnable, VIDEO_TYPE.NORMAL, false);
        }
    }

    private boolean supportGEDEdit(Context context, MediaItem mediaItem) {
        return false;
    }

    private boolean supportGifEdit(MediaItem mediaItem) {
        if (!mediaItem.isGif()) {
            return false;
        }
        if (!mediaItem.isSharing() || Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT_GIF)) {
            return true;
        }
        return false;
    }

    private void updateInstantSlowMoOptionGuideRecognizedStatus() {
        PreferenceCache preferenceCache;
        Boolean bool = (Boolean) this.mEventContext.getEventContextData("is_instant_slow_mo_options_tip_recognize_enabled");
        if (bool != null && bool.booleanValue()) {
            if (Features.isEnabled(Features.SUPPORT_INSTANT_SLOW_MO_ON_SHARE_SHEET)) {
                preferenceCache = PreferenceCache.InstantSlowMoEditAndShareGuide;
            } else {
                preferenceCache = PreferenceCache.InstantSlowMoEditGuide;
            }
            preferenceCache.setBoolean(true);
        }
    }

    private boolean useVideoEditorLite(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_DOWNLOAD_VIDEO_EDITOR) || mediaItem.getRecordingMode() == 2 || mediaItem.getRecordingMode() == 8) {
            return true;
        }
        return false;
    }

    public ViewerMenuItem.Validator checkSharing() {
        return new b(this, 8);
    }

    public void executeAfterDownloadInSharing(MediaItem mediaItem, Runnable runnable) {
        Log.d(this.TAG, "downloadSharingItem");
        new EditSharingItemCmd().execute(this.mEventContext, mediaItem, new a(this, runnable, 1));
    }

    public void handleEditor(MediaItem mediaItem, Runnable runnable) {
        Context context = this.mEventContext.getContext();
        if (context == null) {
            Log.e(this.TAG, "set editor failed. null context");
        } else if (!supportGEDEdit(context, mediaItem)) {
            if (mediaItem.isSharing()) {
                executeAfterDownloadInSharing(mediaItem, runnable);
            } else if (isDownloadableNdeOriginal(mediaItem)) {
                executeAfterDownload(this.mEventContext, mediaItem, DownloadType.NDE_ORIGINAL, new a(this, runnable, 2));
            } else if (isDirectorsViewEdit(mediaItem)) {
                handleDirectorsViewEdit(mediaItem, runnable);
            } else if (ViewerMenuItem.isNotificationsData(mediaItem)) {
                new EditNotifiedContentCmd().execute(this.mEventContext, mediaItem);
            } else if (mediaItem.isCloudOnly()) {
                executeAfterDownload(this.mEventContext, mediaItem, DownloadType.EDIT, new a(this, runnable, 3));
            } else {
                ThreadUtil.runOnUiThread(new b(this, mediaItem, runnable, 0));
            }
        }
    }

    public boolean isDownloadableNdeOriginal(MediaItem mediaItem) {
        if (mediaItem.isLocalCloud() && SdkConfig.atLeast(SdkConfig.SEM.T_MR1)) {
            String hiddenOriginalPath = NondestructiveEditor.getHiddenOriginalPath(mediaItem.isVideo(), mediaItem.getPath());
            if (TextUtils.isEmpty(hiddenOriginalPath) || FileUtils.exists(hiddenOriginalPath)) {
                Log.d(this.TAG, "can't download nde original file -> ", Boolean.valueOf(TextUtils.isEmpty(hiddenOriginalPath)));
            } else if (!TextUtils.isEmpty(new FilesApi().getCloudOriginalBinaryHash(mediaItem.getFileId()))) {
                return true;
            } else {
                Log.d(this.TAG, "Empty cloud original binary hash");
            }
        }
        return false;
    }

    public boolean isRotateRecoveryRequired() {
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "Edit Menu Select failed: null item");
            return false;
        } else if (!SeApiCompat.isActivityResumed(this.mEventContext.getActivity())) {
            Log.d(this.TAG, "Edit Menu Select failed: paused");
            return false;
        } else {
            if (PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO) {
                updateInstantSlowMoOptionGuideRecognizedStatus();
            }
            ThreadUtil.runOnBgThread(new Ob.a(6, this, currentItem));
            postEditMenuAnalyticsLog(currentItem);
            return true;
        }
    }

    public void postEditMenuAnalyticsLog(MediaItem mediaItem) {
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAIL_VIEW_FS_EDIT.toString(), VuAnalytics.getViewerCustomDimensions(mediaItem, getAnalyticsDetailId(mediaItem)));
    }

    public void prepareEditor() {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem != null) {
            this.mActionInvoker.invoke(ViewerAction.PREPARE_EDITOR, currentItem);
            this.mActionInvoker.invoke(ViewerAction.SET_DECOR_VISIBILITY, Boolean.FALSE);
        }
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_detail_edit).setFastOptionMenu().setShowAsActionFlag(2).setDisabledDimCondition(new C0385u(11, this)).exclude("location://trash", "location://mtp/fileList", "location://dynamicViewList", "location://AllDayTimeLapse", "location://superSlowViewList", "location://highlightViewList", "location://SingleTakenShotviewer/suggestionHighlight", "location://SingleTakenShotviewer/highlight", "location://SingleTakenShotviewer/superslow", "location://colorCorrectView").validate(ViewerMenuItem.IS_NOT_POSTPROCESSING_FOR_PPPV3).validate(ViewerMenuItem.IS_NOT_DRM).validate(checkSharing()).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_URI_ITEM).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).setSupportPpp(ENABLE_PPP);
    }

    public EditMenuItem(EventContext eventContext, ActionInvoker actionInvoker, int i2) {
        super(eventContext, actionInvoker, i2);
    }

    private void startVideoEditor(MediaItem mediaItem, Runnable runnable, VIDEO_TYPE video_type, boolean z) {
        if (SdkConfig.atLeast(SdkConfig.GED.R) || video_type == VIDEO_TYPE.NORMAL) {
            if (!Features.isEnabled(Features.SUPPORT_APP_TRANSITION) && runnable != null) {
                runnable.run();
            }
            new StartVideoEditorCmd().execute(this.mEventContext, mediaItem, Boolean.valueOf(z));
            return;
        }
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$ui$viewer2$menu$EditMenuItem$VIDEO_TYPE[video_type.ordinal()];
        if (i2 == 1) {
            new PlayFastMotionVideoCmd().execute(this.mEventContext, mediaItem);
        } else if (i2 == 2) {
            new PlaySlowMotionVideoCmd().execute(this.mEventContext, mediaItem);
        } else if (i2 == 3) {
            new PlaySuperSlowMotionVideoCmd().execute(this.mEventContext, mediaItem);
        }
    }

    public void onFailExecuteVideoEditingApp() {
    }
}
