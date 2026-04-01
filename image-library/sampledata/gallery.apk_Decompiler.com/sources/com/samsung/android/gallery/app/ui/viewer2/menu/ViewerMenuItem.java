package com.samsung.android.gallery.app.ui.viewer2.menu;

import B5.c;
import F6.f;
import Q7.j;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.app.controller.externals.MediaCaptureCmd;
import com.samsung.android.gallery.app.controller.viewer.DownloadForViewerCmd;
import com.samsung.android.gallery.app.controller.viewer.DownloadNdeOriginalCmd;
import com.samsung.android.gallery.app.controller.viewer.RequestDismissKeyGuardCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.abstraction.ConvertingUsageType;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.abstraction.MediaCaptureMode;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.directorsview.DirectorsViewCache;
import com.samsung.android.gallery.module.foldable.FoldUtils;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.remotegallery.RemoteGalleryUtil;
import com.samsung.android.gallery.module.service.download.DownloadSyncMgr;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StorageUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.ViewTag;
import com.samsung.android.gallery.widget.popover.PopoverHelper;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewerMenuItem {
    public static final BooleanSupplier FALSE_SUPPLIER = new f(8);
    protected static final Validator IS_DELETABLE_FOR_SHARING = new l(21);
    protected static final Validator IS_GROUP_SHOT = new l(24);
    protected static final Validator IS_NOT_AFW = new s(5);
    protected static final Validator IS_NOT_APV = new l(23);
    protected static final Validator IS_NOT_AUTO_ALBUM = new l(22);
    protected static final Validator IS_NOT_BROKEN = new s(1);
    protected static final Validator IS_NOT_DRM = new l(28);
    protected static final Validator IS_NOT_DRM_WO_PRIVATE = new l(29);
    protected static final Validator IS_NOT_GROUP_SHOT = new l(25);
    protected static final Validator IS_NOT_HAVE_QUICK_VIEW_ARGUMENT = new s(8);
    protected static final Validator IS_NOT_KNOX = new s(4);
    protected static final Validator IS_NOT_POSTPROCESSING = new s(2);
    protected static final Validator IS_NOT_POSTPROCESSING_FOR_PPPV3 = new s(3);
    protected static final Validator IS_NOT_PRIVATE_ITEM = new l(18);
    protected static final Validator IS_NOT_REMOTE_ITEM = new l(17);
    protected static final Validator IS_NOT_SEP_LITE = new s(7);
    protected static final Validator IS_NOT_SHARING = new s(0);
    protected static final Validator IS_NOT_SINGLE_TAKE = new l(26);
    protected static final Validator IS_NOT_SUGGESTION_VIEW_LIST = new l(20);
    protected static final Validator IS_NOT_UPSM = new s(6);
    protected static final Validator IS_NOT_URI_ITEM = new s(9);
    protected static final Validator IS_NOT_VIDEO = new l(27);
    protected static final Validator IS_SHARING = new l(19);
    static final AtomicInteger sMenuIndex = new AtomicInteger(1);
    private final int BLOCK_MSEC_WHEN_MENU_SELECTED = 1000;
    protected final String TAG = getClass().getSimpleName();
    protected final ActionInvoker mActionInvoker;
    private Drawable mDrawable;
    protected final EventContext mEventContext;
    private final HashSet<String> mExcludedLocationKeySet;
    private int mGroupId;
    private int mIconResId;
    private Integer mIconTintId = null;
    private final HashSet<String> mIncludedLocationKeySet;
    private int mInputBlockTime;
    private BooleanSupplier mIsDisabledDim;
    private BooleanSupplier mIsEnabledDim;
    private boolean mIsExecutableOnScreenLocked;
    private boolean mIsFastOptionMenu;
    private BooleanSupplier mIsPppDim;
    private boolean mIsToolbarOnly;
    private final int mMenuId;
    private int mShowAsAction = 0;
    private boolean mSupportPpp;
    private final int mTitleResId;
    private final ArrayList<Validator> mValidatorList;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Validator {
        boolean isValid(MediaItem mediaItem, String str);
    }

    public ViewerMenuItem(EventContext eventContext, ActionInvoker actionInvoker, int i2) {
        BooleanSupplier booleanSupplier = FALSE_SUPPLIER;
        this.mIsDisabledDim = booleanSupplier;
        this.mIsEnabledDim = booleanSupplier;
        this.mIsPppDim = booleanSupplier;
        this.mInputBlockTime = 1000;
        this.mIncludedLocationKeySet = new HashSet<>();
        this.mExcludedLocationKeySet = new HashSet<>();
        this.mValidatorList = new ArrayList<>();
        this.mEventContext = eventContext;
        this.mActionInvoker = actionInvoker;
        this.mMenuId = sMenuIndex.getAndIncrement();
        this.mTitleResId = i2;
        setMenuAttribute();
    }

    public static boolean hasPortraitChanged(MediaItem mediaItem) {
        return mediaItem.hasSefFileTypes(3105);
    }

    public static boolean isAfw() {
        return Features.isEnabled(Features.IS_AFW_MODE);
    }

    public static boolean isCloudOnly(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isCloudOnly()) {
            return false;
        }
        return true;
    }

    public static boolean isDexMode(Context context) {
        return DeviceInfo.isDexMode(context);
    }

    public static boolean isGroupShot(MediaItem mediaItem) {
        if (mediaItem.isSimilarShot() || mediaItem.isBurstShot() || mediaItem.isSingleTakenShot()) {
            return true;
        }
        return false;
    }

    public static boolean isKnox() {
        return Features.isEnabled(Features.IS_KNOX_MODE);
    }

    public static boolean isLowStorage() {
        return StorageUtil.isLowStorage();
    }

    public static boolean isNotUnlockScreen(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.getMediaType() != MediaType.UnlockScreen) {
            return true;
        }
        return false;
    }

    public static boolean isNotificationsData(MediaItem mediaItem) {
        int storyType;
        if (mediaItem == null || (storyType = MediaItemStory.getStoryType(mediaItem)) <= 0 || (!StoryHelper.isAgif(storyType) && !StoryHelper.isCollage(storyType))) {
            return false;
        }
        return true;
    }

    public static boolean isRemasteringImage(MediaItem mediaItem) {
        String str;
        if (mediaItem == null || mediaItem.getPath() == null || (str = (String) mediaItem.getExtra(ExtrasID.ORIGIN_PATH)) == null || mediaItem.getPath().equals(str)) {
            return false;
        }
        return true;
    }

    public static boolean isUpsm() {
        return Features.isEnabled(Features.IS_UPSM);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeAfterDownload$26(DownloadType downloadType, EventContext eventContext, MediaItem mediaItem, Consumer consumer) {
        DownloadSyncMgr downloadSyncMgr = new DownloadSyncMgr();
        if (downloadType == DownloadType.NDE_ORIGINAL) {
            new DownloadNdeOriginalCmd().execute(eventContext, mediaItem, consumer, downloadType, downloadSyncMgr);
        } else if (downloadSyncMgr.valid(mediaItem.getFileId())) {
            new DownloadForViewerCmd().execute(eventContext, mediaItem, consumer, downloadType, downloadSyncMgr);
        } else {
            String str = this.TAG;
            Log.w(str, "skip downloading. [" + mediaItem.getFileId() + "] is not valid");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeMediaCaptureCmd$27(MediaItem mediaItem, ConvertingUsageType convertingUsageType) {
        new MediaCaptureCmd().execute(this.mEventContext, mediaItem, convertingUsageType, MediaCaptureMode.HIGHLIGHT_FRC);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$0() {
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$1(MediaItem mediaItem, String str) {
        if (mediaItem == null || !mediaItem.isSharing()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$10(MediaItem mediaItem, String str) {
        if (mediaItem == null || mediaItem.isPostProcessing()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$11(MediaItem mediaItem, String str) {
        if (Features.isEnabled(Features.SUPPORT_PPP_MENU)) {
            return true;
        }
        if (mediaItem == null || mediaItem.isPostProcessing()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$12(MediaItem mediaItem, String str) {
        return !isKnox();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$13(MediaItem mediaItem, String str) {
        return !isAfw();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$14(MediaItem mediaItem, String str) {
        return !isUpsm();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$15(MediaItem mediaItem, String str) {
        return !Features.isEnabled(Features.IS_SEP_LITE);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$16(MediaItem mediaItem, String str) {
        return !ArgumentsUtil.getArgValue(str, "quick_view", false);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$17(MediaItem mediaItem, String str) {
        if (mediaItem == null || mediaItem.isUriItem() || mediaItem.isStream() || mediaItem.isTemporary()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$18(MediaItem mediaItem, String str) {
        if (mediaItem == null || RemoteGalleryUtil.isRemoteFile(mediaItem)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$19(MediaItem mediaItem, String str) {
        if (mediaItem == null || mediaItem.isPrivateItem()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$2(MediaItem mediaItem, String str) {
        if (mediaItem == null || !isGroupShot(mediaItem)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$20(MediaItem mediaItem, String str) {
        return !LocationKey.isSuggestionViewList(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$21(MediaItem mediaItem, String str) {
        if (mediaItem == null) {
            return false;
        }
        if (!mediaItem.isSharing() || MediaItemMde.isOwnedByMe(mediaItem) || MdeGroupHelper.isSAFamilyGroup(ArgumentsUtil.getArgValue(str, "groupId", (String) null))) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$22(MediaItem mediaItem, String str) {
        if (ArgumentsUtil.getArgValue(str, "type", 0) != AlbumType.AutoUpdated.toInt()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$23(MediaItem mediaItem, String str) {
        return !MediaItemUtil.isApv(mediaItem);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$3(MediaItem mediaItem, String str) {
        if (mediaItem == null || isGroupShot(mediaItem)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$4(MediaItem mediaItem, String str) {
        if (mediaItem == null || mediaItem.isSingleTakenShot()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$5(MediaItem mediaItem, String str) {
        if (mediaItem == null || mediaItem.isVideo()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$6(MediaItem mediaItem, String str) {
        if (mediaItem == null || mediaItem.isDrm() || mediaItem.isPrivateItem()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$7(MediaItem mediaItem, String str) {
        if (mediaItem == null || mediaItem.isDrm()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$8(MediaItem mediaItem, String str) {
        if (mediaItem == null || mediaItem.isSharing()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$9(MediaItem mediaItem, String str) {
        if (mediaItem == null || mediaItem.isBroken()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: onMenuSelectAfterRotateRecovery */
    public boolean lambda$onMenuSelect$24(View view) {
        if (!isRotateRecoveryRequired() || this.mEventContext.getBlackboard().read("viewer_force_rotated") == null) {
            return lambda$onMenuSelectAfterRotateRecovery$25(view);
        }
        this.mEventContext.getBlackboard().postEvent(EventMessage.obtain(3002));
        ThreadUtil.postOnUiThreadDelayed(new j(this, view, 1), 240);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0034, code lost:
        if (com.samsung.android.gallery.support.utils.PreferenceCache.MotionPhotoExportNewBadge.getBoolean() != false) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0019, code lost:
        if (com.samsung.android.gallery.support.utils.PreferenceCache.RemasterNewBadge.getBoolean() != false) goto L_0x001b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setNewBadge(android.view.MenuItem r5) {
        /*
            r4 = this;
            int r0 = r4.getTitleResId()
            r1 = 2131888144(0x7f120810, float:1.9410915E38)
            r2 = 0
            r3 = 1
            if (r0 != r1) goto L_0x001d
            com.samsung.android.gallery.support.config.SdkConfig$GED r4 = com.samsung.android.gallery.support.config.SdkConfig.GED.S
            boolean r4 = com.samsung.android.gallery.support.config.SdkConfig.lessThan((com.samsung.android.gallery.support.config.SdkConfig.GED) r4)
            if (r4 == 0) goto L_0x0046
            com.samsung.android.gallery.support.utils.PreferenceCache r4 = com.samsung.android.gallery.support.utils.PreferenceCache.RemasterNewBadge
            boolean r4 = r4.getBoolean()
            if (r4 == 0) goto L_0x0046
        L_0x001b:
            r2 = r3
            goto L_0x0046
        L_0x001d:
            int r0 = r4.getTitleResId()
            r1 = 2131887120(0x7f120410, float:1.9408838E38)
            if (r0 != r1) goto L_0x0037
            com.samsung.android.gallery.support.config.SdkConfig$GED r4 = com.samsung.android.gallery.support.config.SdkConfig.GED.S
            boolean r4 = com.samsung.android.gallery.support.config.SdkConfig.match((com.samsung.android.gallery.support.config.SdkConfig.GED) r4)
            if (r4 == 0) goto L_0x0046
            com.samsung.android.gallery.support.utils.PreferenceCache r4 = com.samsung.android.gallery.support.utils.PreferenceCache.MotionPhotoExportNewBadge
            boolean r4 = r4.getBoolean()
            if (r4 == 0) goto L_0x0046
            goto L_0x001b
        L_0x0037:
            int r4 = r4.getTitleResId()
            r0 = 2131888133(0x7f120805, float:1.9410893E38)
            if (r4 != r0) goto L_0x0058
            com.samsung.android.gallery.support.utils.PreferenceCache r4 = com.samsung.android.gallery.support.utils.PreferenceCache.RemasterGifNewBadge
            boolean r2 = r4.getBoolean()
        L_0x0046:
            if (r2 == 0) goto L_0x0058
            boolean r4 = r5 instanceof androidx.appcompat.view.menu.SeslMenuItem
            if (r4 == 0) goto L_0x0058
            androidx.appcompat.view.menu.SeslMenuItem r5 = (androidx.appcompat.view.menu.SeslMenuItem) r5
            r4 = 2131887802(0x7f1206ba, float:1.9410221E38)
            java.lang.String r4 = com.samsung.android.gallery.support.utils.AppResources.getString(r4)
            r5.setBadgeText(r4)
        L_0x0058:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem.setNewBadge(android.view.MenuItem):void");
    }

    public static boolean supportKnoxMoveType(KnoxUtil.MoveType moveType) {
        return KnoxUtil.getInstance().isKnoxMoveOn(moveType);
    }

    public MenuItem attachToMenu(Menu menu, int i2) {
        return menu.add(getGroupId(), getMenuId(), i2, (CharSequence) null);
    }

    public ViewerMenuItem exclude(String... strArr) {
        if (this.mIncludedLocationKeySet.size() <= 0) {
            this.mExcludedLocationKeySet.addAll(Arrays.asList(strArr));
            return this;
        }
        throw new IllegalStateException("ValidLocation exist");
    }

    public final void executeAfterDownload(EventContext eventContext, MediaItem mediaItem, DownloadType downloadType, Consumer<Object> consumer) {
        ThreadUtil.postOnBgThread(new c(this, downloadType, eventContext, mediaItem, consumer, 7));
    }

    public void executeMediaCaptureCmd(MediaItem mediaItem, ConvertingUsageType convertingUsageType) {
        Object eventContextData = this.mEventContext.getEventContextData("is_highlight_frc_mode");
        if (eventContextData == null || !((Boolean) eventContextData).booleanValue()) {
            if (mediaItem.isLogVideo()) {
                this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_STOP, new Object[0]);
            }
            new MediaCaptureCmd().execute(this.mEventContext, mediaItem, convertingUsageType, MediaCaptureMode.NONE);
            return;
        }
        Log.d(this.TAG, "executeMediaCaptureCmd");
        this.mActionInvoker.invoke(ViewerAction.SAVE_HIGHLIGHT_FRC, new J6.c(this, mediaItem, convertingUsageType, 29));
    }

    public void forceSwipe(Blackboard blackboard) {
        this.mActionInvoker.invoke(ViewerAction.PREPARE_FORCE_SWIPE, new Object[0]);
        blackboard.postEvent(EventMessage.obtain(3015));
    }

    public Drawable getDrawable() {
        int i2;
        Integer num;
        Drawable drawable;
        Context context = this.mEventContext.getContext();
        if (this.mDrawable == null && context != null && (i2 = this.mIconResId) > 0) {
            this.mDrawable = context.getDrawable(i2);
            if (supportAnimatedDrawable() && (drawable = this.mDrawable) != null) {
                this.mDrawable = drawable.mutate();
            }
            Drawable drawable2 = this.mDrawable;
            if (!(drawable2 == null || (num = this.mIconTintId) == null)) {
                drawable2.setTint(context.getColor(num.intValue()));
            }
        }
        return this.mDrawable;
    }

    public int getGroupId() {
        return this.mGroupId;
    }

    public int getInputBlockTime() {
        return this.mInputBlockTime;
    }

    public int getMenuId() {
        return this.mMenuId;
    }

    public int getShowAsActionFlag() {
        return this.mShowAsAction;
    }

    public String getTitle() {
        return null;
    }

    public int getTitleResId() {
        return this.mTitleResId;
    }

    public ViewerMenuItem include(String... strArr) {
        if (this.mExcludedLocationKeySet.size() <= 0) {
            this.mIncludedLocationKeySet.addAll(Arrays.asList(strArr));
            return this;
        }
        throw new IllegalStateException("ExcludeLocation exist");
    }

    public boolean isDim() {
        if (isDisabledDim() || isEnabledDim()) {
            return true;
        }
        return false;
    }

    public boolean isDirectorsViewEdit(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi41.SUPPORT_DIRECTORS_VIEW || !DirectorsViewCache.isEditable(mediaItem)) {
            return false;
        }
        return true;
    }

    public boolean isDisabledDim() {
        if (this.mIsDisabledDim.getAsBoolean() || this.mIsPppDim.getAsBoolean()) {
            return true;
        }
        return false;
    }

    public boolean isEnabledDim() {
        return this.mIsEnabledDim.getAsBoolean();
    }

    public boolean isFastOptionMenu() {
        return this.mIsFastOptionMenu;
    }

    public boolean isFlipCover() {
        if (!FoldUtils.SUPPORT_FLIP_COVER_GALLERY || !FoldUtils.isFlipCoverScreen(this.mEventContext.getActivity())) {
            return false;
        }
        return true;
    }

    public boolean isFlipCoverTheme() {
        if (!isFlipCover()) {
            return false;
        }
        if (LaunchIntent.isFlipCoverViewerTheme(this.mEventContext.getBlackboard()) || LaunchIntent.isFlipCoverWidgetTheme(this.mEventContext.getBlackboard())) {
            return true;
        }
        return false;
    }

    public final boolean isFromCamera() {
        LaunchIntent launchIntent = (LaunchIntent) this.mEventContext.getBlackboard().read("data://launch_intent");
        if (launchIntent == null || !launchIntent.isFromCamera()) {
            return false;
        }
        return true;
    }

    public final boolean isFromFlipWidget() {
        return LaunchIntent.isFlipCoverWidgetTheme(this.mEventContext.getBlackboard());
    }

    public final boolean isInMultiWindowMode() {
        return SystemUi.isInMultiWindowMode(this.mEventContext.getActivity());
    }

    public boolean isRotateRecoveryRequired() {
        return false;
    }

    public boolean isScreenLocked() {
        if (!LaunchIntent.isFromLockScreen(this.mEventContext.getBlackboard()) || !SeApiCompat.isScreenLocked(AppResources.getAppContext())) {
            return false;
        }
        return true;
    }

    public boolean isShowText() {
        if ((this.mShowAsAction & 4) == 4) {
            return true;
        }
        return false;
    }

    public boolean isSupportPpp() {
        return this.mSupportPpp;
    }

    public boolean isToolbarOnly() {
        return this.mIsToolbarOnly;
    }

    public boolean isValid(MediaItem mediaItem, String str) {
        Iterator<Validator> it = this.mValidatorList.iterator();
        while (it.hasNext()) {
            if (!it.next().isValid(mediaItem, str)) {
                return false;
            }
        }
        return isNotUnlockScreen(mediaItem);
    }

    public boolean isValidLocation(String str) {
        if (this.mIncludedLocationKeySet.size() != 0) {
            return this.mIncludedLocationKeySet.contains(str);
        }
        if (this.mExcludedLocationKeySet.size() != 0) {
            return !this.mExcludedLocationKeySet.contains(str);
        }
        return true;
    }

    public boolean onMenuSelect(View view) {
        if (!isScreenLocked() || this.mIsExecutableOnScreenLocked) {
            return lambda$onMenuSelect$24(view);
        }
        new RequestDismissKeyGuardCmd().execute(this.mEventContext, new j(this, view, 0));
        return true;
    }

    /* renamed from: onMenuSelectInternal */
    public abstract boolean lambda$onMenuSelectAfterRotateRecovery$25(View view);

    public void postAnalyticsLog(String str, Pair<String, String>[] pairArr) {
        AnalyticsLogger.getInstance().postCustomDimension(this.mEventContext.getScreenId(), str, this.mEventContext.getScreenLabel(), pairArr);
    }

    public void publishPopoverInfo(int i2, View view) {
        if (!SystemUi.supportPopoverUi(this.mEventContext.getContext(), false)) {
            return;
        }
        if (view == null || view.getTag() != ViewTag.FAST_OPTION) {
            PopoverHelper.publishPopoverInfo(this.mEventContext.getBlackboard(), this.mEventContext.getToolbar(), 1, i2);
        } else {
            PopoverHelper.publishPopoverInfo(this.mEventContext.getBlackboard(), view, 2, i2);
        }
    }

    public void resetDrawableAlpha() {
        Drawable drawable = getDrawable();
        if (drawable != null && drawable.getAlpha() != 255) {
            drawable.setAlpha(ScoverState.TYPE_NFC_SMART_COVER);
        }
    }

    public ViewerMenuItem setDisabledDimCondition(BooleanSupplier booleanSupplier) {
        this.mIsDisabledDim = booleanSupplier;
        return this;
    }

    public ViewerMenuItem setEnabledDimCondition(BooleanSupplier booleanSupplier) {
        this.mIsEnabledDim = booleanSupplier;
        return this;
    }

    public ViewerMenuItem setExecutableOnScreenLocked() {
        this.mIsExecutableOnScreenLocked = true;
        return this;
    }

    public ViewerMenuItem setFastOptionMenu() {
        this.mIsFastOptionMenu = true;
        return this;
    }

    public void setGroupId(int i2) {
        this.mGroupId = i2;
    }

    public ViewerMenuItem setIconResId(int i2) {
        this.mIconResId = i2;
        return this;
    }

    public ViewerMenuItem setIconTintId(int i2) {
        this.mIconTintId = Integer.valueOf(i2);
        return this;
    }

    public ViewerMenuItem setInputBlockTime(int i2) {
        this.mInputBlockTime = i2;
        return this;
    }

    public abstract void setMenuAttribute();

    public void setMenuItemAttributes(MenuItem menuItem) {
        if (this.mShowAsAction != 0) {
            menuItem.setShowAsAction(2);
        }
        Drawable drawable = getDrawable();
        if (drawable != null) {
            if (isDim()) {
                drawable.setAlpha(115);
            } else {
                drawable.setAlpha(ScoverState.TYPE_NFC_SMART_COVER);
            }
            menuItem.setIcon(drawable);
        }
        if (isDisabledDim()) {
            menuItem.setEnabled(false);
        }
        if (!TextUtils.isEmpty(getTitle())) {
            menuItem.setTitle(getTitle());
        } else {
            menuItem.setTitle(getTitleResId());
        }
        setNewBadge(menuItem);
    }

    public void setPppDimCondition(BooleanSupplier booleanSupplier) {
        this.mIsPppDim = booleanSupplier;
    }

    public ViewerMenuItem setShowAsActionFlag(int i2) {
        this.mShowAsAction = i2;
        return this;
    }

    public ViewerMenuItem setSupportPpp(boolean z) {
        this.mSupportPpp = z;
        return this;
    }

    public ViewerMenuItem setToolbarOnly() {
        this.mIsToolbarOnly = true;
        return this;
    }

    public boolean supportAnimatedDrawable() {
        return false;
    }

    public final String tag() {
        return this.TAG;
    }

    public String toString() {
        String str;
        String str2;
        StringBuilder sb2 = new StringBuilder("Menu(");
        if (this.mIsFastOptionMenu) {
            str = "F";
        } else {
            str = "f";
        }
        sb2.append(str);
        if (this.mIsToolbarOnly) {
            str2 = "T";
        } else {
            str2 = "t";
        }
        sb2.append(str2);
        sb2.append(this.mShowAsAction);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mMenuId);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(AppResources.getAppContext().getString(getTitleResId()));
        sb2.append(")");
        return sb2.toString();
    }

    public ViewerMenuItem validate(Validator validator) {
        if (!this.mValidatorList.contains(validator)) {
            this.mValidatorList.add(validator);
            return this;
        }
        throw new IllegalStateException("already exist");
    }

    public void postAnalyticsLog(String str) {
        AnalyticsLogger.getInstance().postLog(this.mEventContext.getScreenId(), str);
    }
}
