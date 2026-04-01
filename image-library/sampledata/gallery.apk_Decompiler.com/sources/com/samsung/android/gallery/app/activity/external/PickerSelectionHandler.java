package com.samsung.android.gallery.app.activity.external;

import A4.C0375j;
import A8.C0545b;
import A9.d;
import C3.g;
import D3.f;
import D3.h;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.SetWallpaperCmd;
import com.samsung.android.gallery.app.controller.internals.StartCropImageCmd;
import com.samsung.android.gallery.database.dal.local.table.HistoryTable;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.album.ShortcutHelper;
import com.samsung.android.gallery.module.clipboard.ClipDataCompat;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.debugger.LocalProviderDebugHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileType;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.scsp.media.file.FileApiContract;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PickerSelectionHandler extends Subscriber implements EventContext {
    private boolean mIsLocalCloudItemSelected;
    private LaunchIntent mLaunchIntent;

    public PickerSelectionHandler(Blackboard blackboard) {
        super(blackboard);
    }

    private Uri addAuthority(Uri uri) {
        if (uri == null || !SeApiCompat.isMyUserIdAsUserCurrent() || !TextUtils.isEmpty(uri.getUserInfo())) {
            return uri;
        }
        Uri.Builder buildUpon = uri.buildUpon();
        return buildUpon.encodedAuthority("" + SeApiCompat.getMyUserId() + Log.TAG_SEPARATOR + uri.getEncodedAuthority()).build();
    }

    private void addGrantPermission(Intent intent) {
        intent.addFlags(getPermissionFlag());
    }

    private void buildGeneric(Intent intent, MediaItem[] mediaItemArr) {
        intent.putExtra("selectedItems", getUriList(mediaItemArr));
        intent.putExtra("selectedCount", mediaItemArr.length);
        if (this.mLaunchIntent.needToCheckCloudContentIncluded()) {
            intent.putExtra("cloud_included", this.mIsLocalCloudItemSelected);
        }
    }

    private void buildInternal(Intent intent, MediaItem[] mediaItemArr) {
        if (mediaItemArr != null && mediaItemArr.length > 0) {
            Blackboard.getInstance(this.mLaunchIntent.getIntent().getStringExtra("blackboard_name")).publish("data://user/selected", mediaItemArr);
        }
    }

    private void finish(Activity activity) {
        try {
            activity.finish();
        } catch (SecurityException e) {
            com.samsung.android.gallery.support.utils.Log.e((CharSequence) this.TAG, "finish failed", (Throwable) e);
            activity.setResult(0);
            activity.finish();
        }
    }

    private ClipData getClipData(MediaItem[] mediaItemArr) {
        return ClipDataCompat.of(List.of(mediaItemArr), "galleryUri", true, true);
    }

    private int getPermissionFlag() {
        if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
            return 65;
        }
        return 1;
    }

    private ArrayList<Uri> getUriList(MediaItem[] mediaItemArr) {
        boolean z;
        Activity activity = getActivity();
        ArrayList<Uri> arrayList = new ArrayList<>();
        if (activity == null) {
            com.samsung.android.gallery.support.utils.Log.pke(this.TAG, "getUriList() : activity is null");
            return arrayList;
        }
        String callingPackage = activity.getCallingPackage();
        if (TextUtils.isEmpty(callingPackage)) {
            com.samsung.android.gallery.support.utils.Log.pke(this.TAG, "callingPackage is null");
            return arrayList;
        }
        if ("com.sec.android.gallery3d".equals(callingPackage) || this.mLaunchIntent.isFromGalleryWidget() || this.mLaunchIntent.isExtraAllowMultiple()) {
            z = true;
        } else {
            z = false;
        }
        boolean z3 = z;
        Stream.of(mediaItemArr).filter(new C0375j(11)).forEach(new d(this, z3, activity, callingPackage, arrayList));
        com.samsung.android.gallery.support.utils.Log.d(this.TAG, "getUriList", callingPackage, Integer.valueOf(mediaItemArr.length), Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private boolean isFromSecretBox(MediaItem mediaItem) {
        return false;
    }

    private boolean isFromThemeStore() {
        return this.mLaunchIntent.isFromThemeStore();
    }

    private boolean isInternalPick() {
        if (this.mLaunchIntent.getIntent().getStringExtra("blackboard_name") != null) {
            return true;
        }
        return false;
    }

    private boolean isPickSupported(MediaItem mediaItem) {
        if (this.mLaunchIntent.isFromStoryCover()) {
            return !FileType.getMimeType(mediaItem.getPath()).contains("wmv");
        }
        if (this.mLaunchIntent.isFromAlbumCover() || isAlbumPick()) {
            return true;
        }
        StringCompat stringCompat = this.TAG;
        com.samsung.android.gallery.support.utils.Log.pke(stringCompat, "drm: " + mediaItem.isDrm() + ", cloudOnly: " + mediaItem.isCloudOnly() + ", ppp: " + mediaItem.isPostProcessing());
        if (isFromThemeStore()) {
            if (mediaItem.isDrm()) {
                return false;
            }
            if (mediaItem.isVideo() && mediaItem.isCloudOnly()) {
                return false;
            }
        }
        if ((!mediaItem.isBroken() || mediaItem.isDrm()) && !mediaItem.isPostProcessing()) {
            return true;
        }
        return false;
    }

    private boolean isSkipCrop(MediaItem mediaItem) {
        if (!this.mLaunchIntent.isFromStoryCover() || !PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW || !PreviewFactory.isPreviewableFormat(mediaItem)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getUriList$6(MediaItem mediaItem) {
        if (mediaItem == null || !FileUtils.exists(mediaItem.getPath())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getUriList$7(boolean z, Activity activity, String str, ArrayList arrayList, MediaItem mediaItem) {
        try {
            Uri addAuthority = addAuthority(ContentUri.getUri(mediaItem));
            if (addAuthority != null) {
                if (!z) {
                    activity.grantUriPermission(str, addAuthority, getPermissionFlag());
                }
                arrayList.add(addAuthority);
                if (!this.mIsLocalCloudItemSelected && mediaItem.getStorageType() == StorageType.LocalCloud) {
                    this.mIsLocalCloudItemSelected = true;
                }
            }
        } catch (Exception e) {
            StringCompat stringCompat = this.TAG;
            com.samsung.android.gallery.support.utils.Log.e(stringCompat, "getUriList failed {" + str + GlobalPostProcInternalPPInterface.SPLIT_REGEX + z + "} " + FileUtils.info(mediaItem.getPath()) + " " + mediaItem);
            throw e;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setResultAndFinish$3(Intent intent, MediaItem[] mediaItemArr) {
        intent.setClipData(getClipData(mediaItemArr));
        ThreadUtil.postOnUiThread(new h(this, intent, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setResultAndFinish$5(Intent intent, MediaItem[] mediaItemArr) {
        buildGeneric(intent, mediaItemArr);
        ThreadUtil.postOnUiThread(new h(this, intent, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startPrivateModeSetAsAlertDialog$0(MediaItem mediaItem, DialogInterface dialogInterface, int i2) {
        if (this.mLaunchIntent.isRequireCrop()) {
            launchCropViewer(mediaItem);
        } else {
            setResultAndFinish(mediaItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startPrivateModeSetAsAlertDialog$1(MediaItem mediaItem) {
        new AlertDialog.Builder(getActivity()).setTitle((int) R.string.secret_box).setMessage((int) R.string.personal_image_setas_alert_message).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) new g(1, this, mediaItem)).create().show();
    }

    private void launchCropViewer(MediaItem mediaItem) {
        if (isInternalPick()) {
            Blackboard.getInstance(this.mLaunchIntent.getIntent().getStringExtra("blackboard_name")).publish("data://user/selected", new MediaItem[]{mediaItem});
        }
        new StartCropImageCmd().execute(this, mediaItem, Boolean.valueOf(this.mLaunchIntent.isFromGallery()), Boolean.valueOf(this.mLaunchIntent.isFromStoryCover()), Boolean.valueOf(this.mLaunchIntent.isFromSharedAlbumCover()), Boolean.valueOf(this.mLaunchIntent.isFromAlbumCover()), Boolean.valueOf(this.mLaunchIntent.isGetRectResult()));
    }

    /* access modifiers changed from: private */
    public void onExecuteMultiple(Object obj, Bundle bundle) {
        if (obj == null) {
            com.samsung.android.gallery.support.utils.Log.pke(this.TAG, "data is null!!");
            return;
        }
        LaunchIntent launchIntent = (LaunchIntent) this.mBlackboard.read("data://launch_intent");
        this.mLaunchIntent = launchIntent;
        if (launchIntent == null) {
            com.samsung.android.gallery.support.utils.Log.w(this.TAG, "activity is destroyed!!");
        } else if (launchIntent.isStoryMultiPick()) {
            setResultAndFinishForStoriesPick((MediaItem[]) obj);
        } else if (this.mLaunchIntent.isAlbumMultiPick()) {
            setResultAndFinishForAlbumMultiPick((int[]) obj);
        } else if (this.mLaunchIntent.isCreatureMultiPick()) {
            setResultAndFinishForCreaturePick((MediaItem[]) obj);
        } else {
            setResultAndFinish((MediaItem[]) obj);
        }
    }

    /* access modifiers changed from: private */
    public void onExecuteSingle(Object obj, Bundle bundle) {
        int i2;
        if (obj == null) {
            com.samsung.android.gallery.support.utils.Log.w(this.TAG, "data is null!!");
            return;
        }
        LaunchIntent launchIntent = (LaunchIntent) this.mBlackboard.read("data://launch_intent");
        this.mLaunchIntent = launchIntent;
        if (launchIntent == null) {
            com.samsung.android.gallery.support.utils.Log.w(this.TAG, "activity is destroyed!!");
            return;
        }
        MediaItem mediaItem = (MediaItem) obj;
        if (!isPickSupported(mediaItem)) {
            Context context = getContext();
            if (this.mLaunchIntent.isFromStoryCover()) {
                i2 = R.string.unsupported_format;
            } else {
                i2 = R.string.unsupported_file;
            }
            Toast.makeText(context, i2, 0).show();
        } else if (this.mLaunchIntent.isRequireCrop() && !isSkipCrop(mediaItem)) {
            launchCropViewer(mediaItem);
        } else if (isAlbumPick()) {
            setResultAndFinishForAlbumMediaItem(mediaItem);
        } else if (isFromSecretBox(mediaItem)) {
            startPrivateModeSetAsAlertDialog(mediaItem);
        } else if (SdkConfig.lessThan(SdkConfig.SEM.U_MR5) && isFromThemeStore()) {
            startSetWallpaper(mediaItem);
        } else if (this.mLaunchIntent.isStoryPick()) {
            setResultAndFinishForStoryMediaItem(mediaItem);
        } else {
            setResultAndFinish(mediaItem);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onFinishGetUriList */
    public void lambda$setResultAndFinish$4(Intent intent) {
        if (getActivity() != null) {
            addGrantPermission(intent);
            StringCompat stringCompat = this.TAG;
            com.samsung.android.gallery.support.utils.Log.pk(stringCompat, "sendResult multi=" + Logger.toString(intent));
            getActivity().setResult(-1, intent);
            finish(getActivity());
            return;
        }
        com.samsung.android.gallery.support.utils.Log.pke(this.TAG, "onFinishGetUriList() : activity is null");
    }

    private void setResultAndFinish(MediaItem mediaItem) {
        LocalProviderDebugHelper.addOperationHistory(HistoryTable.ActionKeyword.PICKER, Collections.singletonList(mediaItem));
        Activity activity = getActivity();
        if (this.mLaunchIntent.isViewItem()) {
            ShortcutHelper.getInstance().setShortcut(activity, mediaItem, ShortcutHelper.UseType.FOR_TASK_EDGE_ITEM);
            return;
        }
        Intent intent = new Intent((String) null, ContentUri.getUri(mediaItem));
        if (mediaItem.isImage()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(addAuthority(ContentUri.getUri(mediaItem)));
            intent.putExtra(FileApiContract.Parameter.MIME_TYPE, "image/*");
            intent.putExtra("selectedItems", arrayList);
        } else if (mediaItem.isVideo()) {
            intent.putExtra(FileApiContract.Parameter.MIME_TYPE, "video/*");
        }
        if (this.mLaunchIntent.isExtraAllowMultiple()) {
            StringCompat stringCompat = this.TAG;
            com.samsung.android.gallery.support.utils.Log.pk(stringCompat, "sendResult allow_multiple {" + mediaItem.getMimeType() + "}");
            intent.setClipData(getClipData(new MediaItem[]{mediaItem}));
        }
        if (isInternalPick()) {
            Blackboard.getInstance(this.mLaunchIntent.getIntent().getStringExtra("blackboard_name")).publish("data://user/selected", new MediaItem[]{mediaItem});
        }
        addGrantPermission(intent);
        StringCompat stringCompat2 = this.TAG;
        com.samsung.android.gallery.support.utils.Log.pk(stringCompat2, "sendResult single " + Logger.toString(intent));
        activity.setResult(-1, intent);
        finish(activity);
    }

    private void setResultAndFinishForAlbumMediaItem(MediaItem mediaItem) {
        Activity activity = getActivity();
        if (this.mLaunchIntent.isFromTaskEdgeShortcut(activity.getCallingPackage())) {
            ShortcutHelper.getInstance().setShortcut(activity, mediaItem, ShortcutHelper.UseType.FOR_TASK_EDGE_ALBUM);
            com.samsung.android.gallery.support.utils.Log.pk(this.TAG, "sendResult shortcut=1");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("ALBUM_ID", mediaItem.getAlbumID());
        intent.putExtra("key-album-type", mediaItem.getAlbumType().toInt());
        intent.putExtra("AbsolutePath", mediaItem.getReferencePath());
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS && mediaItem.isMergedAlbum()) {
            intent.putExtra("bucketIds", TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, (List) Arrays.stream(mediaItem.getAlbumsInFolder()).mapToInt(new C0545b(2)).boxed().collect(Collectors.toList())));
        }
        addGrantPermission(intent);
        StringCompat stringCompat = this.TAG;
        com.samsung.android.gallery.support.utils.Log.pk(stringCompat, "sendResult album " + Logger.toString(intent));
        activity.setResult(-1, intent);
        finish(activity);
    }

    private void setResultAndFinishForAlbumMultiPick(int[] iArr) {
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 : iArr) {
            if (!this.mLaunchIntent.isIncludeVirtualAlbum() || !BucketUtils.isVirtualAlbum(i2)) {
                arrayList.add(Integer.valueOf(i2));
            } else {
                arrayList2.add(Integer.valueOf(i2));
            }
        }
        intent.putExtra("album_multi_ids", arrayList);
        intent.putExtra("virtual_album_multi_ids", arrayList2);
        intent.putExtra("selectedCount", iArr.length);
        addGrantPermission(intent);
        com.samsung.android.gallery.support.utils.Log.pk(this.TAG, "sendResult albums=" + iArr.length + " " + Logger.toString(intent));
        getActivity().setResult(-1, intent);
        finish(getActivity());
    }

    private void setResultAndFinishForCreaturePick(MediaItem[] mediaItemArr) {
        LocalProviderDebugHelper.addOperationHistory(HistoryTable.ActionKeyword.PICKER, Arrays.asList(mediaItemArr));
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (MediaItem mediaItem : mediaItemArr) {
            long unifiedIdentityId = IdentityCreatureUtil.getUnifiedIdentityId(mediaItem.getSubCategory());
            if (mediaItem.isPet()) {
                arrayList2.add(Long.valueOf(unifiedIdentityId));
            } else {
                arrayList.add(Long.valueOf(unifiedIdentityId));
            }
        }
        hashMap.put(0, arrayList);
        hashMap.put(1, arrayList2);
        intent.putExtra("face_recommended_ids", hashMap);
        intent.putExtra("selectedCount", mediaItemArr.length);
        addGrantPermission(intent);
        com.samsung.android.gallery.support.utils.Log.pk(this.TAG, "sendResult creatures=" + mediaItemArr.length + " " + Logger.toString(intent));
        getActivity().setResult(-1, intent);
        finish(getActivity());
    }

    private void setResultAndFinishForStoriesPick(MediaItem[] mediaItemArr) {
        LocalProviderDebugHelper.addOperationHistory(HistoryTable.ActionKeyword.PICKER, Arrays.asList(mediaItemArr));
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
        ArrayList arrayList = new ArrayList();
        for (MediaItem albumID : mediaItemArr) {
            arrayList.add(Integer.valueOf(albumID.getAlbumID()));
        }
        intent.putExtra("story_album_ids", arrayList);
        intent.putExtra("selectedCount", mediaItemArr.length);
        addGrantPermission(intent);
        com.samsung.android.gallery.support.utils.Log.pk(this.TAG, "sendResult stories=" + mediaItemArr.length + " " + Logger.toString(intent));
        getActivity().setResult(-1, intent);
        finish(getActivity());
    }

    private void setResultAndFinishForStoryMediaItem(MediaItem mediaItem) {
        Activity activity = getActivity();
        Intent intent = new Intent();
        intent.putExtra("story_album_id", MediaItemStory.getStoryId(mediaItem));
        addGrantPermission(intent);
        StringCompat stringCompat = this.TAG;
        com.samsung.android.gallery.support.utils.Log.pk(stringCompat, "sendResult story " + Logger.toString(intent));
        activity.setResult(-1, intent);
        finish(activity);
    }

    private void startPrivateModeSetAsAlertDialog(MediaItem mediaItem) {
        ThreadUtil.postOnUiThread(new A2.d(26, this, mediaItem));
    }

    private void startSetWallpaper(MediaItem mediaItem) {
        new SetWallpaperCmd().execute(this, mediaItem);
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("command://SinglePickerItemSelection", new f(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://MultiplePickerItemsSelection", new f(this, 1)).setWorkingOnUI());
    }

    public Activity getActivity() {
        return BlackboardUtils.readActivity(this.mBlackboard);
    }

    public Context getApplicationContext() {
        return BlackboardUtils.readAppContext(this.mBlackboard);
    }

    public Context getContext() {
        return BlackboardUtils.readActivity(this.mBlackboard);
    }

    public boolean isAlbumPick() {
        return PickerUtil.isAlbumPickLaunchMode(this.mBlackboard);
    }

    private void setResultAndFinish(MediaItem[] mediaItemArr) {
        LocalProviderDebugHelper.addOperationHistory(HistoryTable.ActionKeyword.PICKER, Arrays.asList(mediaItemArr));
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
        if (isInternalPick()) {
            buildInternal(intent, mediaItemArr);
        }
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("setResultAndFinish");
        Integer valueOf = Integer.valueOf(mediaItemArr.length);
        sb2.append(Logger.v(valueOf, "extra-allow=" + this.mLaunchIntent.isExtraAllowMultiple()));
        com.samsung.android.gallery.support.utils.Log.pk(stringCompat, sb2.toString());
        if (this.mLaunchIntent.isExtraAllowMultiple()) {
            ThreadUtil.postOnBgThread(new D3.g(this, intent, mediaItemArr, 0));
        } else {
            ThreadUtil.postOnBgThread(new D3.g(this, intent, mediaItemArr, 1));
        }
        StringCompat stringCompat2 = this.TAG;
        com.samsung.android.gallery.support.utils.Log.pk(stringCompat2, "sendResult multi=" + mediaItemArr.length);
    }
}
