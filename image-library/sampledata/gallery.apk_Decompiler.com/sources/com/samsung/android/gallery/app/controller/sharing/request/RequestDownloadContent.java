package com.samsung.android.gallery.app.controller.sharing.request;

import C3.C0392b;
import N3.c;
import T3.a;
import T3.d;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MimeType;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeServiceString;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mde.MdeUploadingChecker;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.SefFileUtils;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;
import com.samsung.android.sdk.mobileservice.social.share.result.ContentDownloadResult;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestDownloadContent extends AbsRequest {
    private final HashMap<String, ArrayList<String>> mGroupShotMap = new HashMap<>();

    public RequestDownloadContent(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    private void deleteGroupShotData(List<ContentDownloadResult.DownloadedContent> list) {
        list.forEach(new d(this, 1));
    }

    private PendingIntent getDownloadClickPendingIntent(String str) {
        Intent intent = new Intent("com.android.gallery.action.SHORTCUT_ALBUM_VIEW");
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
        intent.putExtra("ALBUM_ID", FileUtils.getBucketId(Environment.getExternalStorageDirectory().toString() + "/Download"));
        return AndroidCompat.createActivityPendingIntent(this.mAppContext, str.hashCode(), intent, 134217728);
    }

    private Bundle getDownloadNotiMessages(List<String> list) {
        String str;
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (list.size() > 1) {
            str = ShareApi.EXTRA_DOWNLOAD_NOTI_ALL_ITEMS_DOWNLOADED;
        } else {
            str = ShareApi.EXTRA_DOWNLOAD_NOTI_ONE_ITEM_DOWNLOADED;
        }
        String quantityString = this.mAppContext.getResources().getQuantityString(R.plurals.number_of_downloaded_contents, list.size(), new Object[]{Integer.valueOf(list.size())});
        Bundle bundle = new Bundle();
        bundle.putString(str, quantityString);
        return bundle;
    }

    private Consumer<ContentDownloadResult> getDownloadResultCallback() {
        return new d(this, 0);
    }

    private boolean hasBurstShotData(int i2, int i7) {
        if (i2 == 1 || i7 == SefInfo.BURST_SHOT_INFO.keyCode) {
            return true;
        }
        return false;
    }

    private boolean hasSingleTakenShotData(int i2) {
        if (i2 == 3) {
            return true;
        }
        return false;
    }

    private boolean isGroupShot(String str, String str2) {
        if (str2 == null || this.mGroupShotMap.get(str) == null) {
            return false;
        }
        ArrayList arrayList = this.mGroupShotMap.get(str);
        Objects.requireNonNull(arrayList);
        if (arrayList.stream().anyMatch(new C0392b(str2, 13))) {
            return true;
        }
        return false;
    }

    private boolean isTablet() {
        return Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$deleteGroupShotData$2(ContentDownloadResult.DownloadedContent downloadedContent) {
        if (downloadedContent != null && downloadedContent.getFileUri() != null && !TextUtils.isEmpty(downloadedContent.getFileUri().toString())) {
            String path = downloadedContent.getFileUri().getPath();
            if (isGroupShot("burst_shot", path)) {
                SefFileUtils.deleteBurstShotData(path);
                MediaScanner.scanFile(path, (MediaScannerListener) null);
            } else if (isGroupShot("Single Taken", path)) {
                SefFileUtils.deleteSingleTakenShotData(path);
                MediaScanner.scanFile(path, (MediaScannerListener) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getDownloadResultCallback$0(ContentDownloadResult contentDownloadResult) {
        int code = contentDownloadResult.getStatus().getCode();
        String message = contentDownloadResult.getStatus().getMessage();
        String str = this.TAG;
        Log.sh(str, "requestDownloadContents result" + Logger.v(Integer.valueOf(code), message));
        if (MdeResultCode.isSuccess(code)) {
            postDownloadContent(contentDownloadResult);
        } else if (!MdeResultCode.isTaskCanceled(message)) {
            MdeAlbumHelper.handleMdeFailResultCode(this.mAppContext, code, getUnknownErrorStringId());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$postDownloadContent$1(ContentDownloadResult contentDownloadResult) {
        deleteGroupShotData(contentDownloadResult.getSuccessList());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$showDownloadWarningAsPartialUploading$3(ContentDownloadResult.DownloadedContent downloadedContent, FileItemInterface fileItemInterface) {
        return isSameItemId(fileItemInterface, downloadedContent.getItemId());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDownloadWarningAsPartialUploading$4(List list, ContentDownloadResult.DownloadedContent downloadedContent) {
        if (downloadedContent != null && downloadedContent.getItemId().startsWith("temp_")) {
            list.add(getMdeItemList().stream().filter(new a(1, this, downloadedContent)).findAny().get());
        }
    }

    private void requestDownloadContents() {
        int i2;
        List<FileItemInterface> mdeItemList = getMdeItemList();
        if (mdeItemList == null || mdeItemList.isEmpty()) {
            Log.she(this.TAG, "requestDownloadContents failed. no items");
        } else if (!MdeUploadingChecker.showToastIfUploading(this.mAppContext, mdeItemList, this.TAG)) {
            ArrayList arrayList = new ArrayList();
            String str = null;
            int i7 = 0;
            for (FileItemInterface next : mdeItemList) {
                if (next != null) {
                    if (TextUtils.isEmpty(str)) {
                        str = MediaItemMde.getSpaceId(next);
                    }
                    if (SeApiCompat.supportHeif() || !MimeType.isHeif(next.getMimeType())) {
                        arrayList.add(MediaItemMde.getItemId(next));
                        if (hasBurstShotData(next.getSefFileType(), next.getGroupType())) {
                            if (!this.mGroupShotMap.containsKey("burst_shot")) {
                                this.mGroupShotMap.put("burst_shot", new ArrayList());
                            }
                            ArrayList arrayList2 = this.mGroupShotMap.get("burst_shot");
                            Objects.requireNonNull(arrayList2);
                            arrayList2.add(next.getDisplayName());
                        } else if (hasSingleTakenShotData(next.getGroupType())) {
                            if (!this.mGroupShotMap.containsKey("Single Taken")) {
                                this.mGroupShotMap.put("Single Taken", new ArrayList());
                            }
                            ArrayList arrayList3 = this.mGroupShotMap.get("Single Taken");
                            Objects.requireNonNull(arrayList3);
                            arrayList3.add(next.getDisplayName());
                        }
                    } else {
                        i7++;
                    }
                }
            }
            if (arrayList.isEmpty()) {
                Log.she(this.TAG, "requestDownloadContents failed. empty items");
            } else if (TextUtils.isEmpty(str)) {
                Log.she(this.TAG, "requestDownloadContents failed. empty spaceId");
            } else {
                if (i7 > 0) {
                    if (isTablet()) {
                        i2 = R.plurals.number_of_heif_contents_to_download_on_tablet;
                    } else {
                        i2 = R.plurals.number_of_heif_contents_to_download_on_phone;
                    }
                    showToast(this.mAppContext.getResources().getQuantityString(i2, i7, new Object[]{Integer.valueOf(i7)}));
                }
                String str2 = this.TAG;
                Log.sh(str2, "requestDownloadContents #" + arrayList.size());
                MdeSharingHelper.requestItemDownload(str, arrayList, getDownloadClickPendingIntent(str), getDownloadNotiMessages(arrayList), getDownloadResultCallback());
            }
        }
    }

    private void showDownloadWarningAsPartialUploading(List<ContentDownloadResult.DownloadedContent> list) {
        if (list == null || list.isEmpty()) {
            Log.d(this.TAG, "showDownloadWarningAsPartialUploading : no failed list.");
            return;
        }
        ArrayList arrayList = new ArrayList();
        list.forEach(new c(27, this, arrayList));
        if (getContext() != null && !arrayList.isEmpty()) {
            showToast(MdeServiceString.getFailedDownloadMessageBeforeUploading(getContext(), getItemTypeWithMediaList(arrayList), getMdeItemList().size(), arrayList.size()));
        }
    }

    public List<FileItemInterface> getMdeItemList() {
        return (List) this.mParams[1];
    }

    public int getNetworkErrorStringId() {
        return MdeServiceString.getDownloadNetworkError(getItemTypeWithMediaList());
    }

    public int getUnknownErrorStringId() {
        return MdeServiceString.getDownloadUnknownError(getItemTypeWithMediaList());
    }

    public void onPostExecute(boolean z) {
        if (z) {
            this.mBlackboard.postEvent(EventMessage.obtain(1003));
        }
    }

    public void postDownloadContent(ContentDownloadResult contentDownloadResult) {
        showDownloadWarningAsPartialUploading(contentDownloadResult.getFailedList());
        SimpleThreadPool.getInstance().execute(new Ob.a(16, this, contentDownloadResult));
    }

    public void request() {
        requestDownloadContents();
    }
}
