package com.samsung.android.gallery.app.controller.sharing.request;

import A.a;
import A4.I;
import D3.k;
import F9.e;
import I3.i;
import O3.u;
import Qb.c;
import S3.d;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.share.result.ContentDownloadResult;
import com.samsung.android.sum.core.Def;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestCreateStory extends RequestDownloadContent {
    private String mBgmName;
    private int mDownloadedContentsCount;
    private String mFilterName;
    private final ArrayList<MediaItem> mItemList = new ArrayList<>();
    private String mStoryName;
    private String mThemeName;
    private final Map<Integer, String[]> mTotalCropInfo = new HashMap();
    private final ArrayList<Uri> mUriList = new ArrayList<>();

    public RequestCreateStory(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
        Optional.ofNullable(eventContext.getHeaderItem()).ifPresent(new c(18, this));
    }

    /* access modifiers changed from: private */
    /* renamed from: createStory */
    public void lambda$onScanCompleted$9(Map<Long, String[]> map) {
        int size = this.mItemList.size();
        SimpleThreadPool.getInstance().execute(new e((Object) this, (Object) new ArrayList(this.mItemList), size, (Object) map, 3));
    }

    /* access modifiers changed from: private */
    /* renamed from: createStoryAfterScan */
    public void lambda$postDownloadContent$3(List<ContentDownloadResult.DownloadedContent> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        list.forEach(new i(arrayList, arrayList2, 1));
        if (!arrayList.isEmpty()) {
            this.mDownloadedContentsCount = arrayList.size();
            MediaScannerConnection.scanFile(AppResources.getAppContext(), (String[]) arrayList.toArray(new String[0]), (String[]) arrayList2.toArray(new String[0]), new u(1, this));
        }
    }

    private String getBgmExtraInfo() {
        BgmExtraInfo bgmExtraInfo = new BgmExtraInfo();
        bgmExtraInfo.bgmName = this.mBgmName;
        bgmExtraInfo.isFragmentedBgm = true;
        bgmExtraInfo.isMyMusic = false;
        return BgmExtraInfo.getBgmExtraInfoString(bgmExtraInfo);
    }

    private static Integer getTotalCropInfoKey(FileItemInterface fileItemInterface, List<ContentDownloadResult.DownloadedContent> list) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        list.stream().filter(new d(7)).filter(new I(10, fileItemInterface)).findFirst().ifPresent(new k(atomicInteger, 1));
        return Integer.valueOf(atomicInteger.get());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createStory$11(ArrayList arrayList, int i2, Map map) {
        ArrayList arrayList2 = arrayList;
        int i7 = i2;
        int createStory = DbCompat.storyApi().createStory(arrayList2, this.mStoryName, 0, StoryType.MANUAL, (String) null, i7);
        if (createStory >= 0) {
            a.k(createStory, "createStory succeed, storyId=", this.TAG);
            updateUserCuration(createStory, arrayList2);
            StoryUpdateNotifier.getInstance().notifyStory(getContext(), true);
            postCreateStory(createStory, map);
            return;
        }
        a.B(i7, "createStory failed, selectedCount=", this.TAG);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$createStoryAfterScan$6(List list, List list2, ContentDownloadResult.DownloadedContent downloadedContent) {
        if (downloadedContent != null && downloadedContent.getFileUri() != null && !TextUtils.isEmpty(downloadedContent.getFileUri().toString()) && !TextUtils.isEmpty(downloadedContent.getMimeType())) {
            list.add(downloadedContent.getFileUri().getPath());
            list2.add(downloadedContent.getMimeType());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createStoryAfterScan$7(String str, Uri uri) {
        onScanCompleted(uri);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getTotalCropInfoKey$5(AtomicInteger atomicInteger, ContentDownloadResult.DownloadedContent downloadedContent) {
        if (downloadedContent.getFileUri() != null && !TextUtils.isEmpty(downloadedContent.getFileUri().getPath())) {
            atomicInteger.set(downloadedContent.getFileUri().getPath().hashCode());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(MediaItem mediaItem) {
        this.mStoryName = mediaItem.getTitle();
        this.mThemeName = MediaItemMde.getThemeName(mediaItem);
        this.mFilterName = MediaItemMde.getFilterName(mediaItem);
        this.mBgmName = MediaItemMde.getBgmName(mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onScanCompleted$10() {
        UriItemLoader.loadUri(this.mUriList, this.mItemList);
        if (this.mItemList.isEmpty()) {
            Log.w(this.TAG, "There are no downloaded contents.");
            return;
        }
        HashMap hashMap = new HashMap();
        this.mItemList.forEach(new N3.c(21, this, hashMap));
        SimpleThreadPool.getInstance().execute(new Ob.a(14, this, hashMap));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onScanCompleted$8(Map map, MediaItem mediaItem) {
        String[] strArr = this.mTotalCropInfo.get(Integer.valueOf(mediaItem.getPath().hashCode()));
        if (strArr != null && strArr.length > 1) {
            map.put(Long.valueOf(mediaItem.getMediaId()), strArr);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$postDownloadContent$1(FileItemInterface fileItemInterface) {
        if (TextUtils.isEmpty(MediaItemMde.getTotalSmartCropRatio(fileItemInterface)) || TextUtils.isEmpty(MediaItemMde.getTotalSmartCropDeviceRatio(fileItemInterface))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$postDownloadContent$2(List list, FileItemInterface fileItemInterface) {
        this.mTotalCropInfo.put(getTotalCropInfoKey(fileItemInterface, list), new String[]{MediaItemMde.getTotalSmartCropRatio(fileItemInterface), MediaItemMde.getTotalSmartCropDeviceRatio(fileItemInterface)});
    }

    private void onScanCompleted(Uri uri) {
        this.mDownloadedContentsCount--;
        this.mUriList.add(uri);
        if (this.mDownloadedContentsCount == 0) {
            ThreadUtil.postOnBgThreadDelayed(new Qb.e(15, this), Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS);
        }
    }

    private void updateStoryInfo(int i2) {
        if (!TextUtils.isEmpty(this.mThemeName) && !TextUtils.isEmpty(this.mFilterName) && !TextUtils.isEmpty(this.mBgmName)) {
            DbCompat.storyApi().updateStoryThemeAndBgm(i2, this.mThemeName, this.mFilterName, getBgmExtraInfo());
        }
    }

    private void updateStoryItemInfo(Map<Long, String[]> map) {
        if (!SdkConfig.atLeast(SdkConfig.GED.T)) {
            return;
        }
        if (map.isEmpty()) {
            Log.w(this.TAG, "updateStoryItemInfo, but there are no total crop data.");
        } else {
            DbCompat.storyApi().updateStoryTotalCropInfo(map);
        }
    }

    private static void updateUserCuration(int i2, ArrayList<FileItemInterface> arrayList) {
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            DbCompat.storyApi().updateUserCuration(i2, arrayList, 2);
        }
    }

    public void postCreateStory(int i2, Map<Long, String[]> map) {
        String str = this.TAG;
        Log.d(str, "postCreateStory name[t,f,b][" + this.mThemeName + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mFilterName + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mBgmName + "]");
        updateStoryInfo(i2);
        updateStoryItemInfo(map);
    }

    public void postDownloadContent(ContentDownloadResult contentDownloadResult) {
        List<ContentDownloadResult.DownloadedContent> successList = contentDownloadResult.getSuccessList();
        getMdeItemList().stream().filter(new d(5)).filter(new d(6)).forEach(new N3.c(22, this, successList));
        SimpleThreadPool.getInstance().execute(new Ob.a(15, this, successList));
        super.postDownloadContent(contentDownloadResult);
    }
}
