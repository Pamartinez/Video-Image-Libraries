package com.samsung.android.gallery.app.controller;

import A.a;
import A9.b;
import B5.c;
import D7.g;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.app.controller.ppp.PppChecker;
import com.samsung.android.gallery.app.controller.viewer.DownloadForViewerCmd;
import com.samsung.android.gallery.database.dal.local.table.HistoryTable;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.debugger.LocalProviderDebugHelper;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.service.download.DownloadSyncMgr;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.dialog.MissingPackage;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseCommand {
    protected static final int MAX_SELECTED_COUNT;
    private static Runnable mToastRunnable;
    /* access modifiers changed from: protected */
    public String TAG = getClass().getSimpleName();
    private Context mAppContext;
    private Blackboard mBlackboard;
    private SparseArray<Boolean> mConfigs;
    private Consumer<Object> mConsumer;
    private EventContext mHandler;
    private Dialog mProgressDialog;
    private String mScreenId;
    private String mScreenLabel;
    private WeakReference<Toast> mToastReference = new WeakReference<>((Object) null);
    private HashMap<String, Object> params;

    static {
        int i2;
        if (Features.isEnabled(Features.IS_SEP_LOW_SEGMENT)) {
            i2 = 6000;
        } else {
            i2 = 12000;
        }
        MAX_SELECTED_COUNT = i2;
    }

    private boolean isPhotoUpperPositioned() {
        try {
            return getHandler().isPhotoUpperPositioned();
        } catch (Exception e) {
            a.s(e, new StringBuilder("isPhotoUpperPositioned failed e="), this.TAG);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeAfterDownload$4(EventContext eventContext, MediaItem mediaItem, Consumer consumer, DownloadType downloadType) {
        Object obj;
        DownloadSyncMgr downloadSyncMgr = new DownloadSyncMgr();
        if (eventContext == null || mediaItem == null || !downloadSyncMgr.valid(mediaItem.getFileId())) {
            String str = this.TAG;
            if (mediaItem != null) {
                obj = Long.valueOf(mediaItem.getFileId());
            } else {
                obj = "null";
            }
            Log.w((CharSequence) str, "skip downloading", obj);
            return;
        }
        new DownloadForViewerCmd().execute(eventContext, mediaItem, consumer, downloadType, downloadSyncMgr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hideProgress$2() {
        Dialog dialog = this.mProgressDialog;
        if (dialog != null && dialog.isShowing() && ViewUtils.isWindowAttachedToWindow(this.mProgressDialog.getWindow())) {
            this.mProgressDialog.dismiss();
        }
        this.mProgressDialog = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadAndExecute$3(EventContext eventContext, Consumer consumer) {
        MediaItem[] loadSelectedItems = loadSelectedItems(eventContext);
        hideProgress();
        consumer.accept(loadSelectedItems);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showProgress$1(boolean z) {
        AlertDialog create = new ProgressCircleBuilder(getContext()).setProgressMessage(getContext().getString(R.string.processing)).setFlexMode(checkFlexMode()).setLightTheme(z).create();
        this.mProgressDialog = create;
        create.show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showToast$0(String str, int i2) {
        Toast toast = this.mToastReference.get();
        if (toast != null) {
            toast.cancel();
        }
        Toast makeText = Toast.makeText(getContext(), str, i2);
        this.mToastReference = new WeakReference<>(makeText);
        makeText.show();
        mToastRunnable = null;
    }

    public BaseCommand addConfig(int i2) {
        if (this.mConfigs == null) {
            this.mConfigs = new SparseArray<>();
        }
        this.mConfigs.put(i2, Boolean.TRUE);
        return this;
    }

    public BaseCommand addExecuteListener(Consumer<Object> consumer) {
        this.mConsumer = consumer;
        return this;
    }

    public void addOperationHistory(HistoryTable.ActionKeyword actionKeyword, MediaItem[] mediaItemArr) {
        LocalProviderDebugHelper.addOperationHistory(actionKeyword, Arrays.asList(mediaItemArr));
    }

    public BaseCommand addParameter(String str, Object obj) {
        if (this.params == null) {
            this.params = new HashMap<>();
        }
        this.params.put(str, obj);
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x003a, code lost:
        if (r5 < 500) goto L_0x003e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addVideoTransitionInfo(android.content.Intent r8, com.samsung.android.gallery.module.data.MediaItem r9) {
        /*
            r7 = this;
            com.samsung.android.gallery.app.controller.EventContext r0 = r7.mHandler
            java.lang.String r1 = "app_transition_seek_position"
            java.lang.Object r0 = r0.getEventContextData(r1)
            boolean r1 = r0 instanceof java.lang.Integer
            r2 = 0
            if (r1 == 0) goto L_0x0014
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            goto L_0x0015
        L_0x0014:
            r0 = r2
        L_0x0015:
            if (r9 == 0) goto L_0x003d
            int r9 = com.samsung.android.gallery.module.abstraction.VideoPropData.getVideoDuration(r9)
            long r3 = (long) r9
            long r5 = (long) r0
            long r5 = r3 - r5
            java.lang.String r9 = r7.TAG
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            java.lang.Long r4 = java.lang.Long.valueOf(r5)
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r3, r4}
            java.lang.String r3 = "dur/resume "
            com.samsung.android.gallery.support.utils.Log.d(r9, r3, r1)
            r3 = 500(0x1f4, double:2.47E-321)
            int r9 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r9 >= 0) goto L_0x003d
            goto L_0x003e
        L_0x003d:
            r2 = r0
        L_0x003e:
            if (r2 < 0) goto L_0x0045
            java.lang.String r9 = "video_seek_position"
            r8.putExtra(r9, r2)
        L_0x0045:
            int r9 = com.samsung.android.gallery.module.utils.ShareList.getSharedMemoryHash()
            if (r9 <= 0) goto L_0x0050
            java.lang.String r0 = "bitmap_hash"
            r8.putExtra(r0, r9)
        L_0x0050:
            java.lang.String r0 = "launch_from"
            java.lang.String r1 = "From Gallery"
            r8.putExtra(r0, r1)
            java.lang.String r7 = r7.TAG
            java.lang.String r8 = "] bitmapHash["
            java.lang.String r0 = "]"
            java.lang.String r1 = "addVideoTransitionInfo, seekPos["
            java.lang.String r8 = A.a.d(r2, r9, r1, r8, r0)
            com.samsung.android.gallery.support.utils.Log.at(r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.BaseCommand.addVideoTransitionInfo(android.content.Intent, com.samsung.android.gallery.module.data.MediaItem):void");
    }

    public boolean checkFlexMode() {
        return false;
    }

    public final void checkNetworkStatus(EventContext eventContext, Consumer<Boolean> consumer) {
        DelegateHelper.checkNetworkStatus(eventContext, true, consumer);
    }

    public final void consumeExecuteListener(Object obj) {
        Consumer<Object> consumer = this.mConsumer;
        if (consumer != null) {
            consumer.accept(obj);
        }
    }

    public void execute(EventContext eventContext, Object... objArr) {
        Object obj;
        String str = "execute " + this.TAG;
        if (eventContext == null) {
            Log.e(this.TAG, str + " failed due to null handler");
            return;
        }
        if (objArr.length > 0) {
            MediaItem[] mediaItemArr = objArr[0];
            if (mediaItemArr instanceof MediaItem) {
                StringBuilder t = C0212a.t(str, " S");
                t.append(objArr[0].getFileId());
                str = t.toString();
            } else if (mediaItemArr instanceof MediaItem[]) {
                MediaItem[] mediaItemArr2 = mediaItemArr;
                if (mediaItemArr2.length > 0) {
                    StringBuilder t3 = C0212a.t(str, " A");
                    MediaItem mediaItem = mediaItemArr2[0];
                    if (mediaItem != null) {
                        obj = Long.valueOf(mediaItem.getFileId());
                    } else {
                        obj = "null";
                    }
                    t3.append(obj);
                    str = t3.toString();
                }
            }
        }
        if (includeMajorEvent()) {
            Log.majorEvent(this.TAG, str + "");
        } else {
            Log.d(this.TAG, str + "");
        }
        this.mHandler = eventContext;
        this.mScreenId = eventContext.getScreenId();
        this.mScreenLabel = eventContext.getScreenLabel();
        this.mBlackboard = this.mHandler.getBlackboard();
        this.mAppContext = this.mHandler.getApplicationContext();
        if (this.mHandler.getContext() == null || this.mBlackboard == null || this.mAppContext == null) {
            Log.e(this.TAG, str + " failed due to null context");
            return;
        }
        onPreExecute(eventContext, objArr);
    }

    public void executeAfterDownload(EventContext eventContext, MediaItem mediaItem, DownloadType downloadType, Consumer<Object> consumer) {
        ThreadUtil.postOnBgThread(new c(this, eventContext, mediaItem, consumer, downloadType, 3));
    }

    public final void executePppChecker(EventContext eventContext, MediaItem[] mediaItemArr, Consumer<Integer> consumer) {
        new PppChecker(eventContext, mediaItemArr, consumer).start();
    }

    public final Activity getActivity() {
        return this.mHandler.getActivity();
    }

    public final RectF getActivityBounds() {
        View findViewById = getActivity().findViewById(R.id.content_layout);
        if (findViewById == null) {
            return new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        }
        int[] iArr = new int[2];
        findViewById.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        int i2 = iArr[0];
        rect.left = i2;
        rect.top = iArr[1];
        rect.right = findViewById.getWidth() + i2;
        rect.bottom = findViewById.getHeight() + rect.top;
        int i7 = iArr[0];
        return new RectF((float) i7, (float) iArr[1], (float) (findViewById.getWidth() + i7), (float) (findViewById.getHeight() + iArr[1]));
    }

    public String getAnalyticsDetail() {
        return null;
    }

    public Long getAnalyticsValue() {
        return null;
    }

    public final Context getApplicationContext() {
        return this.mAppContext;
    }

    public final Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    public final Context getContext() {
        Context context = this.mHandler.getContext();
        if (context == null) {
            context = BlackboardUtils.readActivity(this.mBlackboard);
        }
        if (context != null) {
            return context;
        }
        return this.mAppContext;
    }

    public String getEventId() {
        return null;
    }

    public final EventContext getHandler() {
        return this.mHandler;
    }

    public Object getParameter(String str) {
        HashMap<String, Object> hashMap = this.params;
        if (hashMap != null) {
            return hashMap.get(str);
        }
        return null;
    }

    public final String getScreenId() {
        return this.mScreenId;
    }

    public final String getScreenLabel() {
        return this.mScreenLabel;
    }

    public void guideDownloadPackage(String str, String str2) {
        guideDownloadPackage(str, str2, false);
    }

    public final boolean guidePackageEnabling(String str) {
        return MissingPackage.showGotoSettingIfDisabled(getActivity(), str);
    }

    public void guideUpdatePackage(String str, String str2) {
        guideDownloadPackage(str, str2, true);
    }

    public boolean handleSharedItemEdit(MediaItem mediaItem, Intent intent) {
        if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT)) {
            return false;
        }
        String hiddenFilePath = MediaItemMde.getHiddenFilePath(mediaItem);
        if (TextUtils.isEmpty(hiddenFilePath)) {
            return false;
        }
        intent.putExtra("mde_hidden_file_path", hiddenFilePath);
        intent.putExtra("mde_space_id", MediaItemMde.getSpaceId(mediaItem));
        intent.putExtra("mde_item_id", MediaItemMde.getItemId(mediaItem));
        return true;
    }

    public final void hideProgress() {
        ThreadUtil.runOnUiThread(new g(6, this));
    }

    public boolean includeMajorEvent() {
        return true;
    }

    public boolean isAnalyticsEnabled() {
        return true;
    }

    public boolean isConfigured(int i2) {
        SparseArray<Boolean> sparseArray = this.mConfigs;
        if (sparseArray == null || !sparseArray.get(i2, Boolean.FALSE).booleanValue()) {
            return false;
        }
        return true;
    }

    public final boolean isDexMode() {
        return DeviceInfo.isDexMode(getContext());
    }

    public final boolean isKnox() {
        return Features.isEnabled(Features.IS_KNOX_MODE);
    }

    public boolean isLowStorage() {
        if (!StorageUtil.isLowStorage()) {
            return false;
        }
        showToast((int) R.string.operation_failed_low_storage, 1);
        return true;
    }

    public final boolean isLowStorageWithTrash() {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash) || !StorageUtil.checkLowStorage(true)) {
            return false;
        }
        return true;
    }

    public final boolean isOnSecureFolder() {
        return Features.isEnabled(Features.IS_ON_SECURE_FOLDER);
    }

    public final boolean isPackageDisabled(String str) {
        return PackageMonitorCompat.getInstance().isInstalledPackageDisabled(str);
    }

    public final boolean isPackageInstalled(String str) {
        return PackageMonitorCompat.getInstance().isPackageInstalled(str);
    }

    public final boolean isUpsm() {
        return Features.isEnabled(Features.IS_UPSM);
    }

    public final boolean isValidPackageVersion(String str, long j2) {
        return PackageMonitorCompat.getInstance().isValidPackageVersion(str, j2);
    }

    public final void loadAndExecute(EventContext eventContext, Consumer<MediaItem[]> consumer) {
        if (eventContext.getSelectedItemCount() < MAX_SELECTED_COUNT) {
            consumer.accept(loadSelectedItems(eventContext));
            return;
        }
        showProgress(false);
        SimpleThreadPool.getInstance().execute(new b(this, eventContext, consumer, 14));
    }

    public final MediaItem[] loadSelectedItems(EventContext eventContext) {
        long currentTimeMillis = System.currentTimeMillis();
        MediaItem[] selectedItems = eventContext.getSelectedItems();
        String str = this.TAG;
        a.A(new Object[]{Integer.valueOf(selectedItems.length), Long.valueOf(currentTimeMillis)}, new StringBuilder("loadSelectedItems"), str);
        return selectedItems;
    }

    public abstract void onExecute(EventContext eventContext, Object... objArr);

    public final void onPostExecute() {
        if (!isAnalyticsEnabled()) {
            return;
        }
        if (getScreenId() == null || getEventId() == null) {
            String str = this.TAG;
            Log.e(str, "SA failed {" + this.TAG + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getScreenId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getEventId() + "}");
            return;
        }
        postAnalyticsLog();
    }

    public void onPreExecute(EventContext eventContext, Object... objArr) {
        onExecute(eventContext, objArr);
        onPostExecute();
    }

    public void postAnalyticsLog() {
        String analyticsDetail = getAnalyticsDetail();
        if (analyticsDetail != null) {
            AnalyticsLogger.getInstance().postLog(getScreenId(), getEventId(), analyticsDetail);
            return;
        }
        Long analyticsValue = getAnalyticsValue();
        if (analyticsValue != null) {
            AnalyticsLogger.getInstance().postLog(getScreenId(), getEventId(), analyticsValue.longValue());
        } else {
            AnalyticsLogger.getInstance().postLog(getScreenId(), getEventId());
        }
    }

    public MediaItem[] replacePppItem(EventContext eventContext, MediaItem[] mediaItemArr) {
        MediaItem read;
        MediaData mediaData = eventContext.getMediaData();
        if (!LocationKey.isAlbumPictures(eventContext.getLocationKey()) || mediaData == null) {
            return eventContext.getSelectedItems();
        }
        MediaItem[] mediaItemArr2 = new MediaItem[mediaItemArr.length];
        long currentTimeMillis = System.currentTimeMillis();
        for (int i2 = 0; i2 < mediaItemArr.length; i2++) {
            MediaItem mediaItem = mediaItemArr[i2];
            mediaItemArr2[i2] = mediaItem;
            if (mediaItem.isPostProcessing() && (read = mediaData.read(mediaData.findPositionByFileId(mediaItem.getFileId()))) != null) {
                mediaItemArr2[i2] = read;
            }
        }
        a.x(new StringBuilder("replacePppItem: elapsed = "), currentTimeMillis, this.TAG);
        return mediaItemArr2;
    }

    public final void setIntentWithCommonExtra(Intent intent) {
        boolean isPhotoUpperPositioned = isPhotoUpperPositioned();
        a.v("setIntentCommonExtra {", "}", this.TAG, isPhotoUpperPositioned);
        if (isPhotoUpperPositioned) {
            intent.putExtra("photo_upper_positioned", true);
        }
    }

    public final void showProgress(boolean z) {
        ThreadUtil.runOnUiThread(new B4.c((Object) this, z, 4));
    }

    public void showToast(int i2) {
        showToast(getContext().getString(i2), 0);
    }

    public boolean startActivityWithTransition(Intent intent, int i2, String str) {
        Bundle bundle;
        View view = (View) getHandler().getEventContextData("app_transition_view");
        if (Features.isEnabled(Features.SUPPORT_APP_TRANSITION) && view != null && this.mBlackboard.read("viewer_force_rotated") == null) {
            intent.putExtra("with_transition", true);
            view.setTransitionName(str);
            if (view instanceof PhotoView) {
                ((PhotoView) view).postSetAlphaOnce(true);
            }
            String str2 = this.TAG;
            Log.at(str2, "startActivityWithTransition " + Logger.toSimpleString(view));
            getActivity().postponeEnterTransition();
            ActivityOptions makeSceneTransitionAnimation = ActivityOptions.makeSceneTransitionAnimation(getActivity(), view, str);
            Activity activity = getActivity();
            if (makeSceneTransitionAnimation != null) {
                bundle = makeSceneTransitionAnimation.toBundle();
            } else {
                bundle = null;
            }
            activity.startActivityForResult(intent, i2, bundle);
            return true;
        } else if (!"gallery_to_ve".equals(str)) {
            return false;
        } else {
            intent.putExtra("bitmap_hash", -1);
            return false;
        }
    }

    public void startService(Intent intent) {
        getContext().startForegroundService(intent);
    }

    private void guideDownloadPackage(String str, String str2, boolean z) {
        if (!Features.isEnabled(Features.IS_CHINESE_DEVICE) || !isKnox() || isOnSecureFolder()) {
            MissingPackage.showGotoStoreIfDeleted(getActivity(), str, str2, z);
            return;
        }
        Toast.makeText(getContext(), getContext().getString(R.string.can_not_edit_images_or_videos_in, new Object[]{KnoxUtil.getInstance().getCurrentContainerName()}), 0).show();
    }

    public void showToast(int i2, int i7) {
        showToast(getContext().getString(i2), i7);
    }

    public void showToast(String str) {
        showToast(str, 0);
    }

    public void showToast(String str, int i2) {
        Runnable runnable = mToastRunnable;
        if (runnable != null) {
            ThreadUtil.removeCallbackOnUiThread(runnable);
        }
        Ab.b bVar = new Ab.b((Object) this, (Object) str, i2, 11);
        mToastRunnable = bVar;
        ThreadUtil.postOnUiThreadDelayed(bVar, 100);
    }

    public final void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
        AnalyticsLogger.getInstance().postLog(getScreenId(), analyticsEventId.toString());
    }

    public final void postAnalyticsLog(AnalyticsEventId analyticsEventId, String str) {
        AnalyticsLogger.getInstance().postLog(getScreenId(), analyticsEventId.toString(), str);
    }
}
