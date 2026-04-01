package com.samsung.android.gallery.app.controller.sharing.request;

import Ba.h;
import N3.c;
import T3.a;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeServiceString;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.mobileservice.social.share.result.ItemListResult;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestDeleteContent extends AbsRequest {
    public RequestDeleteContent(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    private void finishStorageUseView() {
        String currentLocationKey = getCurrentLocationKey();
        if (isStorageUse(currentLocationKey)) {
            this.mBlackboard.post("command://FinishSharingPicturesStorageUse", ArgumentsUtil.getArgValue(currentLocationKey, "id", (String) null));
        }
    }

    private String getCurrentLocationKey() {
        return (String) this.mBlackboard.read("location://variable/currentv1");
    }

    private List<FileItemInterface> getUploadingItems(List<ItemListResult.SharedItemListFailureResult> list) {
        ArrayList arrayList = new ArrayList();
        list.forEach(new c(23, this, arrayList));
        return arrayList;
    }

    private void handleAllItemsAreUploading(Integer num) {
        if (getContext() != null && num.intValue() == 134) {
            List<FileItemInterface> mdeItemList = getMdeItemList();
            showToast(MdeServiceString.getFailedDeleteMessageBeforeUploading(getContext(), getItemTypeWithMediaList(mdeItemList), mdeItemList.size(), mdeItemList.size()));
        }
    }

    private void handleFailedCase(List<ItemListResult.SharedItemListFailureResult> list, Integer num) {
        if (list != null) {
            if (list.isEmpty()) {
                handleAllItemsAreUploading(num);
            } else if (!handleUploadingItemIsExisted(list)) {
                MdeAlbumHelper.handleMdeFailResultCode(this.mAppContext, num.intValue(), MdeServiceString.getDeleteUnknownError(this.mAppContext, list.size()));
            }
        }
    }

    private boolean handleUploadingItemIsExisted(List<ItemListResult.SharedItemListFailureResult> list) {
        List<FileItemInterface> uploadingItems = getUploadingItems(list);
        if (getContext() == null || uploadingItems.isEmpty()) {
            return false;
        }
        showToast(MdeServiceString.getFailedDeleteMessageBeforeUploading(getContext(), getItemTypeWithMediaList(uploadingItems), getMdeItemList().size(), uploadingItems.size()));
        return true;
    }

    private boolean isStorageUse(String str) {
        if (!PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE || !str.startsWith("location://sharing/albums/fileList/storageUsage")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getResultsCallback$0(List list, Integer num) {
        String str;
        String str2 = this.TAG;
        if (MdeResultCode.isSuccess(num.intValue())) {
            str = "succeed";
        } else {
            str = "fail";
        }
        Log.sh(str2, "getResultsCallback ".concat(str));
        handleFailedCase(list, num);
        finishStorageUseView();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$getUploadingItems$1(ItemListResult.SharedItemListFailureResult sharedItemListFailureResult, FileItemInterface fileItemInterface) {
        return isSameItemId(fileItemInterface, sharedItemListFailureResult.getItemId());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getUploadingItems$2(List list, ItemListResult.SharedItemListFailureResult sharedItemListFailureResult) {
        if (sharedItemListFailureResult != null && sharedItemListFailureResult.getItemId().startsWith("temp_")) {
            list.add(getMdeItemList().stream().filter(new a(0, this, sharedItemListFailureResult)).findAny().get());
        }
    }

    public List<FileItemInterface> getMdeItemList() {
        return (List) this.mParams[1];
    }

    public int getNetworkErrorStringId() {
        return MdeServiceString.getDeleteNetworkError(getItemTypeWithMediaList());
    }

    public BiConsumer<List<ItemListResult.SharedItemListFailureResult>, Integer> getResultsCallback() {
        return new h(10, this);
    }

    public int getRunningStringId() {
        return MdeServiceString.getDeleteRunning(getItemTypeWithMediaList());
    }

    public void onPostExecute(boolean z) {
        if (z && !isStorageUse(getCurrentLocationKey())) {
            this.mBlackboard.postEvent(EventMessage.obtain(1003));
        }
    }

    public void request() {
        if (getMdeItemList() != null && !getMdeItemList().isEmpty()) {
            String str = this.TAG;
            Log.sh(str, "deleteContent " + getMdeItemList().size());
            MdeSharingHelper.requestItemDeletion(getMdeItemList(), getResultsCallback());
        }
    }
}
