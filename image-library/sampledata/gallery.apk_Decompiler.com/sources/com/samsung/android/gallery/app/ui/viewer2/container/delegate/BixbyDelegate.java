package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import N2.j;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.app.controller.album.CreateAlbumAndMoveCmd;
import com.samsung.android.gallery.app.controller.externals.StartSimplePhotoEditorCmd;
import com.samsung.android.gallery.app.controller.internals.AddSingleTagCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.share.ShareConversionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.menu.DeleteMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.MoveToAlbumMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.RemasterSaveMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.SetAsWallpaperMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.ShareMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.SuggestionShareMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.module.bixby.BixbyAppStateUtil;
import com.samsung.android.gallery.module.bixby.BixbyKey;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.editor.ModeInfo;
import com.samsung.android.gallery.module.foldable.FoldUtils;
import com.samsung.android.gallery.module.share.ShareComponent;
import com.samsung.android.gallery.module.viewer.QuickCropHelper;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.ValueTrigger;
import g6.g;
import i4.C0468a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import k7.C0479a;
import k7.C0480b;
import k7.C0481c;
import k7.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BixbyDelegate extends AbsVuDelegate<IVuContainerView> {
    private static final HashMap<String, Object> VIEWER_OPTIONS = new HashMap<String, Object>() {
        {
            Boolean bool = Boolean.FALSE;
            put("KEY_ADD_TAG", bool);
            put("KEY_AI_EDIT", bool);
            put("KEY_DELETE", bool);
            put("KEY_EDIT", bool);
            put("KEY_GENERATIVE_EDIT", bool);
            put("KEY_MOVE_TO_ALBUM", 100);
            put("KEY_OBJECT_ERASER", bool);
            put("KEY_QUICK_CROP", 100);
            put("KEY_SAVE", bool);
            put("KEY_SET_AS", bool);
            put("KEY_SHARE", bool);
            put("KEY_SCREEN", Integer.valueOf(BixbyKey.ScreenType.VIEWER.toInt()));
        }
    };
    private StartSimplePhotoEditorCmd.PhotoEditorMode mEditMode = StartSimplePhotoEditorCmd.PhotoEditorMode.spe_crop;
    private final ValueTrigger<Boolean> mExecuteTrigger = new ValueTrigger<>(Boolean.FALSE, "edit");
    private boolean mResumed = false;
    private ShareComponent mShareComponent;
    private String mSubService;
    private String mSubState;
    private boolean mZoomed = false;

    public BixbyDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private void executeCommand(Uri uri) {
        this.mExecuteTrigger.clear();
        Log.bx("BixbyDelegate", "handle command [" + Logger.getEncodedString(uri.toString()) + "]");
        String lastPathSegment = uri.getLastPathSegment();
        if (!TextUtils.isEmpty(lastPathSegment)) {
            lastPathSegment.getClass();
            char c5 = 65535;
            switch (lastPathSegment.hashCode()) {
                case -1801078189:
                    if (lastPathSegment.equals("DETAIL_VIEW_SHARE")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case -1041093:
                    if (lastPathSegment.equals("DETAIL_VIEW_SET_AS")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case 634216694:
                    if (lastPathSegment.equals("DETAIL_VIEW_EDIT")) {
                        c5 = 2;
                        break;
                    }
                    break;
                case 634631273:
                    if (lastPathSegment.equals("DETAIL_VIEW_SAVE")) {
                        c5 = 3;
                        break;
                    }
                    break;
                case 1296432469:
                    if (lastPathSegment.equals("DETAIL_VIEW_AI_EDIT")) {
                        c5 = 4;
                        break;
                    }
                    break;
                case 1368059822:
                    if (lastPathSegment.equals("DETAIL_VIEW_QUICK_CROP")) {
                        c5 = 5;
                        break;
                    }
                    break;
                case 1821588238:
                    if (lastPathSegment.equals("DETAIL_VIEW_TAG")) {
                        c5 = 6;
                        break;
                    }
                    break;
                case 1979921101:
                    if (lastPathSegment.equals("DETAIL_VIEW_MOVE_TO_ALBUM")) {
                        c5 = 7;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                    handleShare(uri);
                    return;
                case 1:
                    handleSetAsWallpaper();
                    return;
                case 2:
                    ThreadUtil.postOnUiThreadDelayed(new C0480b(this, uri, 0), 200);
                    return;
                case 3:
                    handleSave();
                    return;
                case 4:
                    handleAiEdit(uri);
                    return;
                case 5:
                    handleQuickCrop(uri);
                    return;
                case 6:
                    handleAddTag(uri);
                    return;
                case 7:
                    handleMoveToAlbum(uri);
                    return;
                default:
                    Log.bxe("BixbyDelegate", "unable to handle command [" + lastPathSegment + "]");
                    return;
            }
        }
    }

    private String getAlbumName(String str) {
        String locationKey = ((ContainerModel) this.mModel).getLocationKey();
        if (LocationKey.isAlbumPictures(locationKey)) {
            return AlbumTitleHelper.getAlbumTitle(ArgumentsUtil.getArgValue(locationKey, "id", 0), (String) Optional.ofNullable(str).map(new C0468a(19)).orElse((Object) null));
        }
        return null;
    }

    private HashMap<String, Object> getInformationList(MediaItem mediaItem) {
        HashMap<String, Object> hashMap = new HashMap<>();
        ViewerMenuDelegate viewerMenuDelegate = (ViewerMenuDelegate) getDelegate(ViewerMenuDelegate.class);
        if (viewerMenuDelegate != null) {
            for (Map.Entry<String, Object> putInformation : BixbyAppStateUtil.OPTIONS.entrySet()) {
                putInformation(hashMap, putInformation, mediaItem, viewerMenuDelegate);
            }
        }
        putItemInformation(hashMap, mediaItem);
        return hashMap;
    }

    private Uri grantUri(Uri uri) {
        if (uri != null) {
            try {
                getContext().grantUriPermission(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, uri, 3);
                return uri;
            } catch (Exception e) {
                Log.e((CharSequence) "BixbyDelegate", "failed to grant uri permission", (Throwable) e);
            }
        }
        return uri;
    }

    private void handleAddTag(Uri uri) {
        String queryParameter = uri.getQueryParameter("KEY_TAG");
        Log.bx("BixbyDelegate", "handle add tag [" + Logger.getEncodedString(queryParameter) + "]");
        new AddSingleTagCmd().execute(((IVuContainerView) this.mView).getEventContext(), ((IVuContainerView) this.mView).getEventContext().getCurrentItem(), queryParameter, new g(13, this));
    }

    /* access modifiers changed from: private */
    public void handleAddTagDone(Void voidR) {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null && !BottomSheetState.Details.isExpanded(currentViewer.getModel())) {
            ThreadUtil.postOnUiThread(new C0481c(this, 2));
        }
    }

    private void handleMoveToAlbum(Uri uri) {
        Log.bx("BixbyDelegate", "handle move to album");
        new CreateAlbumAndMoveCmd().execute(((IVuContainerView) this.mView).getEventContext(), new MediaItem[]{((IVuContainerView) this.mView).getEventContext().getCurrentItem()}, uri);
    }

    private void handlePendedShare() {
        ThreadUtil.postOnBgThread(new C0481c(this, 0));
    }

    private void handleQuickCrop(Uri uri) {
        if (!((ContainerModel) this.mModel).isOsdVisible()) {
            this.mActionInvoker.invoke(ViewerAction.TOGGLE_OSD, new Object[0]);
        }
        boolean booleanQueryParameter = uri.getBooleanQueryParameter("KEY_IS_SHARE", false);
        String queryParameter = uri.getQueryParameter("KEY_TARGET_COMPONENT_PACKAGE");
        String queryParameter2 = uri.getQueryParameter("KEY_TARGET_COMPONENT_ACTIVITY");
        Log.bx("BixbyDelegate", "handle quick crop [" + booleanQueryParameter + "][" + Logger.getEncodedString(queryParameter) + "][" + Logger.getEncodedString(queryParameter2) + "]");
        ((IVuContainerView) this.mView).getActionInvoker().invoke(ViewerAction.CAPTURE, Boolean.valueOf(booleanQueryParameter), queryParameter, queryParameter2);
    }

    private void handleSave() {
        Log.bx("BixbyDelegate", "handle save");
        requestMenuSelect(RemasterSaveMenuItem.class, false);
    }

    private void handleSetAsWallpaper() {
        Log.bx("BixbyDelegate", "handle set as");
        requestMenuSelect(SetAsWallpaperMenuItem.class, true);
    }

    private void handleShare(Uri uri) {
        String queryParameter = uri.getQueryParameter("KEY_TARGET_COMPONENT_PACKAGE");
        String queryParameter2 = uri.getQueryParameter("KEY_TARGET_COMPONENT_ACTIVITY");
        Log.bx("BixbyDelegate", "handle share [" + Logger.getEncodedString(queryParameter) + "][" + Logger.getEncodedString(queryParameter2) + "]");
        this.mShareComponent = ShareComponent.builder().setPackageName(queryParameter).setClassName(queryParameter2).setFromBixby();
        ViewerMenuDelegate viewerMenuDelegate = (ViewerMenuDelegate) getDelegate(ViewerMenuDelegate.class);
        if (viewerMenuDelegate == null) {
            return;
        }
        if (isShareEnabled(viewerMenuDelegate)) {
            requestMenuSelect(ShareMenuItem.class, false);
        } else if (isSuggestionShareEnabled(viewerMenuDelegate)) {
            requestMenuSelect(SuggestionShareMenuItem.class, false);
        }
    }

    private boolean isAddTagEnabled(MediaItem mediaItem) {
        if (mediaItem.isBroken() || FileUtils.isEmptyDummyImage(mediaItem.getPath()) || Features.isEnabled(Features.IS_GED) || isTrash()) {
            return false;
        }
        return true;
    }

    private boolean isAiEditEnabled() {
        String locationKey = ((ContainerModel) this.mModel).getLocationKey();
        if (!LocationKey.supportDetails(locationKey) || LocationKey.isMtp(locationKey) || LocationKey.isSuggestionViewList(locationKey) || isFlipCover()) {
            return false;
        }
        return true;
    }

    private boolean isCleanOutData() {
        String locationKey = ((ContainerModel) this.mModel).getLocationKey();
        if (LocationKey.isCleanOutPictures(locationKey) || LocationKey.isCleanOutDuplicatedPictures(locationKey) || LocationKey.isCleanOutMotionPhoto(locationKey)) {
            return true;
        }
        return false;
    }

    private boolean isDeleteEnabled(ViewerMenuDelegate viewerMenuDelegate) {
        if ((!viewerMenuDelegate.isMenuEnabled(DeleteMenuItem.class) || !isViewerMode()) && !isTrash()) {
            return false;
        }
        return true;
    }

    private boolean isEditEnabled(ViewerMenuDelegate viewerMenuDelegate) {
        return viewerMenuDelegate.isMenuEnabled(EditMenuItem.class);
    }

    private boolean isFlipCover() {
        if (!FoldUtils.SUPPORT_FLIP_COVER_GALLERY || !FoldUtils.isFlipCoverScreen(((IVuContainerView) this.mView).getActivity())) {
            return false;
        }
        return true;
    }

    private boolean isGenerativeEditEnabled(MediaItem mediaItem, ViewerMenuDelegate viewerMenuDelegate) {
        if (mediaItem == null || !mediaItem.isImage() || !isEditEnabled(viewerMenuDelegate) || !PreferenceFeatures.OneUi6x.SUPPORT_PE_GEN_EDIT) {
            return false;
        }
        return true;
    }

    private boolean isObjectEraserEnabled(MediaItem mediaItem, ViewerMenuDelegate viewerMenuDelegate) {
        if (mediaItem == null || !mediaItem.isImage() || !isEditEnabled(viewerMenuDelegate) || !Features.isEnabled(Features.SUPPORT_OBJECT_ERASER) || Features.isEnabled(Features.IS_REPAIR_MODE)) {
            return false;
        }
        return true;
    }

    private boolean isQuickCropSupported(MediaItem mediaItem) {
        if (!QuickCropHelper.isSupported() || isCleanOutData() || LocationKey.isTempFile(((IVuContainerView) this.mView).getLocationKey()) || ((ContainerModel) this.mModel).isSelectMode() || mediaItem == null || mediaItem.getFileSize() >= 2147483648L || !mediaItem.isImage() || mediaItem.isBroken() || mediaItem.isDrm() || TrashData.isTrash(mediaItem) || !mediaItem.isLocal() || !QuickCropHelper.isSupportedFormat(mediaItem)) {
            return false;
        }
        return true;
    }

    private boolean isSaveEnabled(ViewerMenuDelegate viewerMenuDelegate) {
        return viewerMenuDelegate.isMenuEnabled(RemasterSaveMenuItem.class);
    }

    private boolean isSetAsEnabled(ViewerMenuDelegate viewerMenuDelegate) {
        if (!viewerMenuDelegate.isMenuEnabled(SetAsWallpaperMenuItem.class) || !isViewerMode()) {
            return false;
        }
        return true;
    }

    private boolean isShareEnabled(ViewerMenuDelegate viewerMenuDelegate) {
        if (!viewerMenuDelegate.isMenuEnabled(ShareMenuItem.class) || !isViewerMode()) {
            return false;
        }
        return true;
    }

    private boolean isSuggestionShareEnabled(ViewerMenuDelegate viewerMenuDelegate) {
        if (!viewerMenuDelegate.isMenuEnabled(SuggestionShareMenuItem.class) || !isViewerMode()) {
            return false;
        }
        return true;
    }

    private boolean isTrash() {
        String locationKey = ((ContainerModel) this.mModel).getLocationKey();
        if (locationKey == null || !locationKey.contains("location://trash")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleAddTagDone$3() {
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.REQUEST_DETAILS_SHOW_OR_HIDE;
        Boolean bool = Boolean.TRUE;
        actionInvoker.invoke(viewerAction, bool, bool);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleAiEdit$2(String str) {
        this.mBlackboard.post("data://bixby_command_done", new Object[]{str});
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEdit$4(Uri uri) {
        String queryParameter = uri.getQueryParameter("KEY_EDIT_MODE");
        Log.bx("BixbyDelegate", "handle edit [" + queryParameter + "]");
        this.mEditMode = StartSimplePhotoEditorCmd.PhotoEditorMode.getMode(queryParameter);
        this.mSubState = uri.getQueryParameter("KEY_EDIT_SUB_STATE");
        this.mSubService = uri.getQueryParameter("KEY_EDIT_SUB_SERVICE");
        requestMenuSelect(EditMenuItem.class, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handlePendedShare$5() {
        requestMenuSelect(ShareMenuItem.class, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handlePendedShare$6() {
        ShareComponent shareComponent;
        ShareConversionDelegate shareConversionDelegate = (ShareConversionDelegate) getDelegate(ShareConversionDelegate.class);
        if (shareConversionDelegate != null) {
            shareComponent = shareConversionDelegate.handlePendedShareIfExists(((IVuContainerView) this.mView).getLocationKey());
        } else {
            shareComponent = null;
        }
        this.mShareComponent = shareComponent;
        if (shareComponent != null) {
            ThreadUtil.postOnUiThreadDelayed(new C0481c(this, 1), 1000);
        }
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        boolean booleanValue = objArr[1].booleanValue();
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (booleanValue && currentViewer != null && currentViewer.getModel().isViewConfirmed()) {
            if (BottomSheetState.Details.isClosed((BottomSheetState.StateListener) this.mModel)) {
                this.mExecuteTrigger.set(Boolean.TRUE);
            } else if (BottomSheetState.Details.isExpanded((BottomSheetState.StateListener) this.mModel)) {
                this.mExecuteTrigger.clear();
            }
        }
    }

    /* access modifiers changed from: private */
    public void onZoomChanged(Object... objArr) {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null && BottomSheetState.Details.isClosed(currentViewer.getModel())) {
            this.mZoomed = objArr[0].booleanValue();
        }
    }

    private void putInformation(HashMap<String, Object> hashMap, Map.Entry<String, Object> entry, MediaItem mediaItem, ViewerMenuDelegate viewerMenuDelegate) {
        String key = entry.getKey();
        HashMap<String, Object> hashMap2 = VIEWER_OPTIONS;
        if (hashMap2.containsKey(key)) {
            hashMap.put(key, supportBixbyCommand(mediaItem, viewerMenuDelegate, key, hashMap2.get(key)));
        } else {
            hashMap.put(key, entry.getValue());
        }
    }

    private void putItemInformation(HashMap<String, Object> hashMap, MediaItem mediaItem) {
        hashMap.put("KEY_URI", BixbyAppStateUtil.getNonNullValue(grantUri(ContentUri.getUri(mediaItem))));
        hashMap.put("mime_type", BixbyAppStateUtil.getNonNullValue(mediaItem.getMimeType()));
        hashMap.put("KEY_CONTENT_ATTR", BixbyAppStateUtil.getContentType(mediaItem));
        hashMap.put("KEY_CONTENT_TYPE", Integer.valueOf(BixbyAppStateUtil.getStorageType(mediaItem, ((ContainerModel) this.mModel).getLocationKey())));
        hashMap.put("KEY_CONTENT_ID", Long.valueOf(mediaItem.getFileId()));
        hashMap.put("KEY_PATH", BixbyAppStateUtil.getNonNullValue(mediaItem.getPath()));
        hashMap.put("KEY_SIZE", Long.valueOf(mediaItem.getFileSize()));
        hashMap.put("KEY_RESOLUTION", mediaItem.getWidth() + "x" + mediaItem.getHeight());
        hashMap.put("KEY_ORIENTATION", Integer.valueOf(mediaItem.getOrientation()));
        hashMap.put("KEY_DURATION", Integer.valueOf(mediaItem.getFileDuration()));
        hashMap.put("KEY_ALBUM_NAME", BixbyAppStateUtil.getNonNullValue(getAlbumName(mediaItem.getPath())));
    }

    private <T extends ViewerMenuItem> void requestMenuSelect(Class<T> cls, boolean z) {
        Integer num;
        ViewerMenuDelegate viewerMenuDelegate = (ViewerMenuDelegate) getDelegate(ViewerMenuDelegate.class);
        if (viewerMenuDelegate != null) {
            num = viewerMenuDelegate.getMenuId(cls);
        } else {
            num = null;
        }
        if (num == null) {
            Log.bxe("BixbyDelegate", "unable to handle requested command");
        } else if (!z || !viewerMenuDelegate.supportFastOption()) {
            viewerMenuDelegate.onMenuItemSelected(num.intValue(), (View) null);
        } else {
            viewerMenuDelegate.onSecondDepthItemDirectlySelected(num.intValue());
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
    }

    public String getAppState() {
        MediaItem currentItem = ((IVuContainerView) this.mView).getEventContext().getCurrentItem();
        Log.bx("BixbyDelegate", "getAppState " + MediaItemUtil.getSimpleLog(currentItem));
        if (currentItem == null) {
            return null;
        }
        this.mBlackboard.publish("data://bixby_mediaItem", currentItem);
        return BixbyAppStateUtil.generate(this.mBlackboard.getName(), getInformationList(currentItem));
    }

    public StartSimplePhotoEditorCmd.PhotoEditorMode getEditMode() {
        StartSimplePhotoEditorCmd.PhotoEditorMode photoEditorMode = this.mEditMode;
        this.mEditMode = StartSimplePhotoEditorCmd.PhotoEditorMode.spe_crop;
        return photoEditorMode;
    }

    public String[] getEditSubInfo() {
        if (TextUtils.isEmpty(this.mSubState) && TextUtils.isEmpty(this.mSubService)) {
            return null;
        }
        String[] strArr = {this.mSubState, this.mSubService};
        this.mSubState = null;
        this.mSubService = null;
        return strArr;
    }

    public int getMoveToAlbumState(ViewerMenuDelegate viewerMenuDelegate) {
        if (viewerMenuDelegate.isMenuEnabled(MoveToAlbumMenuItem.class) && isViewerMode()) {
            return 0;
        }
        if (LocationKey.isAlbumPictures(((ContainerModel) this.mModel).getLocationKey())) {
            return 100;
        }
        return 500;
    }

    public int getQuickCropState(MediaItem mediaItem) {
        if (!isQuickCropSupported(mediaItem)) {
            return 100;
        }
        if (!this.mZoomed) {
            return 400;
        }
        return 0;
    }

    public ShareComponent getShareComponent() {
        ShareComponent shareComponent = this.mShareComponent;
        this.mShareComponent = null;
        return shareComponent;
    }

    public void handleAiEdit(Uri uri) {
        AiEditType aiEditType;
        if (!uri.getBooleanQueryParameter("KEY_INTERNAL_REQUEST", false)) {
            Log.bxe("BixbyDelegate", "skip handle ai edit");
            return;
        }
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        MediaItem currentMediaItem = ((ContainerModel) this.mModel).getCurrentMediaItem();
        if (currentMediaItem == null || currentViewer == null) {
            Log.bxe("BixbyDelegate", "handle ai edit failed");
            return;
        }
        String queryParameter = uri.getQueryParameter("KEY_EDIT_MODE");
        boolean booleanQueryParameter = uri.getBooleanQueryParameter("KEY_AUTO_SAVE", false);
        if (queryParameter != null) {
            aiEditType = AiEditType.of(queryParameter);
        } else {
            aiEditType = null;
        }
        String str = "";
        if (aiEditType != null) {
            StringBuilder k = j.k("handle ai edit [", queryParameter, ArcCommonLog.TAG_COMMA);
            if (booleanQueryParameter) {
                str = "autosave, ";
            }
            k.append(str);
            k.append(currentMediaItem.getFileId());
            k.append("]");
            Log.bx("BixbyDelegate", k.toString());
            ((IVuContainerView) this.mView).getActionInvoker().invoke(ViewerAction.REQUEST_AI_EDIT_DIRECTLY, aiEditType, Boolean.valueOf(booleanQueryParameter), new d(this));
        } else if ("removebgpeople".equals(queryParameter)) {
            this.mActionInvoker.invoke(ViewerAction.PREPARE_EDITOR, currentMediaItem);
            ActionInvoker actionInvoker = this.mActionInvoker;
            ViewerAction viewerAction = ViewerAction.SET_DECOR_VISIBILITY;
            Boolean bool = Boolean.FALSE;
            actionInvoker.invoke(viewerAction, bool);
            new StartSimplePhotoEditorCmd().execute(((IVuContainerView) this.mView).getEventContext(), currentMediaItem, StartSimplePhotoEditorCmd.PhotoEditorMode.spe_genedit, null, bool, new ModeInfo(6, (Uri) null, booleanQueryParameter));
            if (!booleanQueryParameter) {
                this.mBlackboard.post("data://bixby_command_done", new Object[]{"no_error"});
            }
        } else {
            StringBuilder k10 = j.k("invalid mode [", queryParameter, ArcCommonLog.TAG_COMMA);
            if (booleanQueryParameter) {
                str = "autosave, ";
            }
            k10.append(str);
            k10.append(currentMediaItem.getFileId());
            k10.append("]");
            Log.bxe("BixbyDelegate", k10.toString());
        }
    }

    /* renamed from: handleEdit */
    public void lambda$executeCommand$0(Uri uri) {
        ValueTrigger<Boolean> valueTrigger = this.mExecuteTrigger;
        Boolean bool = Boolean.TRUE;
        valueTrigger.when(bool).then(new C0480b(this, uri, 1), "edit after more info collapsed");
        if (BottomSheetState.Details.isClosed((BottomSheetState.StateListener) this.mModel)) {
            this.mExecuteTrigger.set(bool);
        } else {
            this.mActionInvoker.invoke(ViewerAction.REQUEST_DETAILS_SHOW_OR_HIDE, Boolean.FALSE, bool);
        }
    }

    public boolean isViewerMode() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer == null || currentViewer.getModel().getDetailsState() != 4) {
            return false;
        }
        return true;
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (!this.mResumed || eventMessage.what != 3008) {
            return false;
        }
        executeCommand((Uri) eventMessage.obj);
        return true;
    }

    public void onPause() {
        this.mResumed = false;
    }

    public void onResume() {
        this.mResumed = true;
        handlePendedShare();
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.BOTTOM_SHEET_STATE_CHANGED, new C0479a(this, 0));
        actionInvoker.add(ViewerAction.IMAGE_ZOOM, new C0479a(this, 1));
    }

    public Object supportBixbyCommand(MediaItem mediaItem, ViewerMenuDelegate viewerMenuDelegate, String str, Object obj) {
        if (mediaItem.isSharing() || mediaItem.isMtp() || (mediaItem.isUriItem() && !Objects.equals(str, "KEY_SHARE"))) {
            return obj;
        }
        str.getClass();
        boolean z = true;
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1968357831:
                if (str.equals("KEY_MOVE_TO_ALBUM")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1096278101:
                if (str.equals("KEY_DELETE")) {
                    c5 = 1;
                    break;
                }
                break;
            case -975301631:
                if (str.equals("KEY_GENERATIVE_EDIT")) {
                    c5 = 2;
                    break;
                }
                break;
            case -668509588:
                if (str.equals("KEY_SCREEN")) {
                    c5 = 3;
                    break;
                }
                break;
            case -666578097:
                if (str.equals("KEY_SET_AS")) {
                    c5 = 4;
                    break;
                }
                break;
            case -441549886:
                if (str.equals("KEY_QUICK_CROP")) {
                    c5 = 5;
                    break;
                }
                break;
            case -194387572:
                if (str.equals("KEY_OBJECT_ERASER")) {
                    c5 = 6;
                    break;
                }
                break;
            case 1312853002:
                if (str.equals("KEY_EDIT")) {
                    c5 = 7;
                    break;
                }
                break;
            case 1313267581:
                if (str.equals("KEY_SAVE")) {
                    c5 = 8;
                    break;
                }
                break;
            case 1972330684:
                if (str.equals("KEY_ADD_TAG")) {
                    c5 = 9;
                    break;
                }
                break;
            case 2056778175:
                if (str.equals("KEY_SHARE")) {
                    c5 = 10;
                    break;
                }
                break;
            case 2139621825:
                if (str.equals("KEY_AI_EDIT")) {
                    c5 = 11;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return Integer.valueOf(getMoveToAlbumState(viewerMenuDelegate));
            case 1:
                return Boolean.valueOf(isDeleteEnabled(viewerMenuDelegate));
            case 2:
                return Boolean.valueOf(isGenerativeEditEnabled(mediaItem, viewerMenuDelegate));
            case 3:
                return Integer.valueOf(BixbyKey.ScreenType.VIEWER.toInt());
            case 4:
                return Boolean.valueOf(isSetAsEnabled(viewerMenuDelegate));
            case 5:
                return Integer.valueOf(getQuickCropState(mediaItem));
            case 6:
                return Boolean.valueOf(isObjectEraserEnabled(mediaItem, viewerMenuDelegate));
            case 7:
                return Boolean.valueOf(isEditEnabled(viewerMenuDelegate));
            case 8:
                return Boolean.valueOf(isSaveEnabled(viewerMenuDelegate));
            case 9:
                return Boolean.valueOf(isAddTagEnabled(mediaItem));
            case 10:
                if (!isShareEnabled(viewerMenuDelegate) && !isSuggestionShareEnabled(viewerMenuDelegate)) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 11:
                return Boolean.valueOf(isAiEditEnabled());
            default:
                return Boolean.FALSE;
        }
    }
}
