package com.samsung.android.sdk.mobileservice.social.share;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;
import com.samsung.android.sdk.mobileservice.common.exception.NotSupportedApiException;
import com.samsung.android.sdk.mobileservice.common.result.BooleanResult;
import com.samsung.android.sdk.mobileservice.social.group.GroupApi;
import com.samsung.android.sdk.mobileservice.social.share.result.ContentDownloadResult;
import com.samsung.android.sdk.mobileservice.social.share.result.DownloadImageResult;
import com.samsung.android.sdk.mobileservice.social.share.result.ItemListResult;
import com.samsung.android.sdk.mobileservice.social.share.result.OriginalFileListResult;
import com.samsung.android.sdk.mobileservice.social.share.result.QuotaResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SharedItemListDeletionResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SharedItemListResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SharedItemListWithContentListResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SharedItemListWithUriListResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SharedItemResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SharedItemWithUriListResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SpaceImageDownloadResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SpaceListResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SpaceResult;
import com.samsung.android.sdk.mobileservice.social.share.result.TrashedItemListResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareApi {
    public static final String API_NAME = "ShareApi";
    public static final String EXTRA_DOWNLOAD_NOTI_ALL_ITEMS_DOWNLOADED = "multi_downloaded";
    public static final String EXTRA_DOWNLOAD_NOTI_ALL_ITEMS_DOWNLOAD_FAILED = "download_multi_failed";
    public static final String EXTRA_DOWNLOAD_NOTI_GROUP_STATUS_CHANGED = "group_status_changed";
    public static final String EXTRA_DOWNLOAD_NOTI_ITEMS_DELETED = "items_deleted";
    public static final String EXTRA_DOWNLOAD_NOTI_ITEMS_DOWNLOADED_WITH_ITEMS_FAILED = "multi_download_multi_failed";
    public static final String EXTRA_DOWNLOAD_NOTI_ITEMS_DOWNLOADED_WITH_ONE_ITEM_FAILED = "multi_download_1_failed";
    public static final String EXTRA_DOWNLOAD_NOTI_ITEMS_DOWNLOADING = "downloading_multi";
    public static final String EXTRA_DOWNLOAD_NOTI_ITEMS_DOWNLOAD_PREPARING = "preparing_download_multi";
    public static final String EXTRA_DOWNLOAD_NOTI_ONE_ITEM_DELETED = "item_deleted";
    public static final String EXTRA_DOWNLOAD_NOTI_ONE_ITEM_DOWNLOADED = "1_downloaded";
    public static final String EXTRA_DOWNLOAD_NOTI_ONE_ITEM_DOWNLOADED_WITH_ITEMS_FAILED = "1_download_multi_failed";
    public static final String EXTRA_DOWNLOAD_NOTI_ONE_ITEM_DOWNLOADING = "downloading_1";
    public static final String EXTRA_DOWNLOAD_NOTI_ONE_ITEM_DOWNLOAD_FAILED = "download_1_failed";
    public static final String EXTRA_DOWNLOAD_NOTI_ONE_ITEM_DOWNLOAD_PREPARING = "preparing_download_1";
    public static final String EXTRA_DOWNLOAD_NOTI_ONE_SPACE_DELETED = "space_deleted";
    public static final String EXTRA_DOWNLOAD_NOTI_SPACES_DELETED = "spaces_deleted";
    public static final String EXTRA_SHARE_NOTI_ALL_ITEMS_UPLOADED = "multi_uploaded";
    public static final String EXTRA_SHARE_NOTI_ALL_ITEMS_UPLOAD_FAILED = "upload_multi_failed";
    public static final String EXTRA_SHARE_NOTI_GROUP_STATUS_CHANGED = "group_status_changed";
    public static final String EXTRA_SHARE_NOTI_ITEMS_DELETED = "items_deleted";
    public static final String EXTRA_SHARE_NOTI_ITEMS_UPLOADED_WITH_ITEMS_FAILED = "multi_uploaded_multi_failed";
    public static final String EXTRA_SHARE_NOTI_ITEMS_UPLOADED_WITH_ONE_ITEM_FAILED = "multi_uploaded_1_failed";
    public static final String EXTRA_SHARE_NOTI_ITEMS_UPLOADING = "uploading_multi";
    public static final String EXTRA_SHARE_NOTI_ITEMS_UPLOAD_PREPARING = "preparing_upload_multi";
    public static final String EXTRA_SHARE_NOTI_ONE_ITEM_DELETED = "item_deleted";
    public static final String EXTRA_SHARE_NOTI_ONE_ITEM_UPLOADED = "1_uploaded";
    public static final String EXTRA_SHARE_NOTI_ONE_ITEM_UPLOADED_WITH_ITEMS_FAILED = "1_uploaded_multi_failed";
    public static final String EXTRA_SHARE_NOTI_ONE_ITEM_UPLOADING = "uploading_1";
    public static final String EXTRA_SHARE_NOTI_ONE_ITEM_UPLOAD_FAILED = "upload_failed";
    public static final String EXTRA_SHARE_NOTI_ONE_ITEM_UPLOAD_PREPARING = "preparing_upload_1";
    public static final String EXTRA_SHARE_NOTI_ONE_SPACE_DELETED = "space_deleted";
    public static final String EXTRA_SHARE_NOTI_QUOTA_EXCEEDED_ERROR = "quota_exceeded_error";
    public static final String EXTRA_SHARE_NOTI_SPACES_DELETED = "spaces_deleted";
    public static final String HD_1280_SIZE_IMAGE = "hd";
    public static final String HQVGA_240_SIZE_IMAGE = "low";
    public static final String ORIGINAL_SIZE_IMAGE = "original";
    private static final String TAG = "ShareApi";
    private static final String TYPE_FULL_SYNC = "FULL_SYNC";
    private static final String TYPE_SYNC_WITH_LAST_THUMBNAIL = "SYNC_WITH_LAST_THUMBNAIL";
    public static final String WQHD_2560_SIZE_IMAGE = "wqhd";
    private final HashSet<Integer> featureIdSet;
    private String mCid;
    private int mFeatureId;
    private LegacyShareApiImpl mLegacyShareApiImpl;
    private ShareApiImpl mShareApiImpl;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ActionIntent {
        private PendingIntent mQuotaExceededErrorIntent;
        private PendingIntent mShareCompleteIntent;

        private void putPendingIntent(Bundle bundle, String str, PendingIntent pendingIntent) {
            if (pendingIntent != null) {
                bundle.putParcelable(str, pendingIntent);
            }
        }

        public PendingIntent getShareCompleteIntent() {
            return this.mShareCompleteIntent;
        }

        public void setQuotaExceededErrorIntent(PendingIntent pendingIntent) {
            this.mQuotaExceededErrorIntent = pendingIntent;
        }

        public void setShareCompleteIntent(PendingIntent pendingIntent) {
            this.mShareCompleteIntent = pendingIntent;
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            putPendingIntent(bundle, "share_complete_intent", this.mShareCompleteIntent);
            putPendingIntent(bundle, "quota_exceeded_error_intent", this.mQuotaExceededErrorIntent);
            return bundle;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ContentDownloadingResultCallback {
        void onProgress(SharedContentDownloadSnapshot sharedContentDownloadSnapshot);

        void onResult(ContentDownloadResult contentDownloadResult);
    }

    @Deprecated
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ImageDownloadingResultCallback {
        void onResult(SpaceImageDownloadResult spaceImageDownloadResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NotificationMessage {
        private String mAllItemsDownloadFailed;
        private String mAllItemsDownloaded;
        private String mAllItemsUploadFailed;
        private String mAllItemsUploaded;
        private String mDownloadingMultiItems;
        private String mDownloadingOneItem;
        private String mGroupStatusChanged;
        private String mMultiItemsDeleted;
        private String mMultiItemsDownloadedAndMultiItemsFailed;
        private String mMultiItemsDownloadedAndOneItemFailed;
        private String mMultiItemsUploadedAndMultiItemsFailed;
        private String mMultiItemsUploadedAndOneItemFailed;
        private String mMultiSpacesDeleted;
        private String mOneItemDeleted;
        private String mOneItemDownloadFailed;
        private String mOneItemDownloaded;
        private String mOneItemDownloadedAndMultiItemsFailed;
        private String mOneItemUploadFailed;
        private String mOneItemUploaded;
        private String mOneItemUploadedAndMultiItemsFailed;
        private String mOneSpaceDeleted;
        private String mPreparingDownloadMultiItems;
        private String mPreparingDownloadOneItem;
        private String mPreparingUploadMultiItems;
        private String mPreparingUploadOneItem;
        private String mQuotaExceededError;
        private String mUploadingMultiItems;
        private String mUploadingOneItem;

        private void putMessage(Bundle bundle, String str, String str2) {
            if (!TextUtils.isEmpty(str2)) {
                bundle.putString(str, str2);
            }
        }

        public void setMessageForAllItemsDownloadFailed(String str) {
            this.mAllItemsDownloadFailed = str;
        }

        public void setMessageForAllItemsDownloaded(String str) {
            this.mAllItemsDownloaded = str;
        }

        public void setMessageForAllItemsUploadFailed(String str) {
            this.mAllItemsUploadFailed = str;
        }

        public void setMessageForAllItemsUploaded(String str) {
            this.mAllItemsUploaded = str;
        }

        public void setMessageForDownloadingMultiItems(String str) {
            this.mDownloadingMultiItems = str;
        }

        public void setMessageForDownloadingOneItem(String str) {
            this.mDownloadingOneItem = str;
        }

        public void setMessageForGroupStatusChanged(String str) {
            this.mGroupStatusChanged = str;
        }

        public void setMessageForMultiItemsDeleted(String str) {
            this.mMultiItemsDeleted = str;
        }

        public void setMessageForMultiItemsDownloadedAndMultiItemsFailed(String str) {
            this.mMultiItemsDownloadedAndMultiItemsFailed = str;
        }

        public void setMessageForMultiItemsDownloadedAndOneItemFailed(String str) {
            this.mMultiItemsDownloadedAndOneItemFailed = str;
        }

        public void setMessageForMultiItemsUploadedAndMultiItemsFailed(String str) {
            this.mMultiItemsUploadedAndMultiItemsFailed = str;
        }

        public void setMessageForMultiItemsUploadedAndOneItemFailed(String str) {
            this.mMultiItemsUploadedAndOneItemFailed = str;
        }

        public void setMessageForMultiSpacesDeleted(String str) {
            this.mMultiSpacesDeleted = str;
        }

        public void setMessageForOneItemDeleted(String str) {
            this.mOneItemDeleted = str;
        }

        public void setMessageForOneItemDownloadFailed(String str) {
            this.mOneItemDownloadFailed = str;
        }

        public void setMessageForOneItemDownloaded(String str) {
            this.mOneItemDownloaded = str;
        }

        public void setMessageForOneItemDownloadedAndMultiItemsFailed(String str) {
            this.mOneItemDownloadedAndMultiItemsFailed = str;
        }

        public void setMessageForOneItemUploadFailed(String str) {
            this.mOneItemUploadFailed = str;
        }

        public void setMessageForOneItemUploaded(String str) {
            this.mOneItemUploaded = str;
        }

        public void setMessageForOneItemUploadedAndMultiItemsFailed(String str) {
            this.mOneItemUploadedAndMultiItemsFailed = str;
        }

        public void setMessageForOneSpaceDeleted(String str) {
            this.mOneSpaceDeleted = str;
        }

        public void setMessageForPreparingDownloadMultiItems(String str) {
            this.mPreparingDownloadMultiItems = str;
        }

        public void setMessageForPreparingDownloadOneItem(String str) {
            this.mPreparingDownloadOneItem = str;
        }

        public void setMessageForPreparingUploadMultiItems(String str) {
            this.mPreparingUploadMultiItems = str;
        }

        public void setMessageForPreparingUploadOneItem(String str) {
            this.mPreparingUploadOneItem = str;
        }

        public void setMessageForQuotaExceededError(String str) {
            this.mQuotaExceededError = str;
        }

        public void setMessageForUploadingMultiItems(String str) {
            this.mUploadingMultiItems = str;
        }

        public void setMessageForUploadingOneItem(String str) {
            this.mUploadingOneItem = str;
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            putMessage(bundle, ShareApi.EXTRA_SHARE_NOTI_ONE_ITEM_UPLOAD_PREPARING, this.mPreparingUploadOneItem);
            putMessage(bundle, ShareApi.EXTRA_SHARE_NOTI_ITEMS_UPLOAD_PREPARING, this.mPreparingUploadMultiItems);
            putMessage(bundle, ShareApi.EXTRA_SHARE_NOTI_ONE_ITEM_UPLOADING, this.mUploadingOneItem);
            putMessage(bundle, ShareApi.EXTRA_SHARE_NOTI_ITEMS_UPLOADING, this.mUploadingMultiItems);
            putMessage(bundle, ShareApi.EXTRA_SHARE_NOTI_ONE_ITEM_UPLOADED, this.mOneItemUploaded);
            putMessage(bundle, ShareApi.EXTRA_SHARE_NOTI_ALL_ITEMS_UPLOADED, this.mAllItemsUploaded);
            putMessage(bundle, ShareApi.EXTRA_SHARE_NOTI_ONE_ITEM_UPLOADED_WITH_ITEMS_FAILED, this.mOneItemUploadedAndMultiItemsFailed);
            putMessage(bundle, ShareApi.EXTRA_SHARE_NOTI_ITEMS_UPLOADED_WITH_ONE_ITEM_FAILED, this.mMultiItemsUploadedAndOneItemFailed);
            putMessage(bundle, ShareApi.EXTRA_SHARE_NOTI_ITEMS_UPLOADED_WITH_ITEMS_FAILED, this.mMultiItemsUploadedAndMultiItemsFailed);
            putMessage(bundle, ShareApi.EXTRA_SHARE_NOTI_ONE_ITEM_UPLOAD_FAILED, this.mOneItemUploadFailed);
            putMessage(bundle, ShareApi.EXTRA_SHARE_NOTI_ALL_ITEMS_UPLOAD_FAILED, this.mAllItemsUploadFailed);
            putMessage(bundle, ShareApi.EXTRA_DOWNLOAD_NOTI_ONE_ITEM_DOWNLOAD_PREPARING, this.mPreparingDownloadOneItem);
            putMessage(bundle, ShareApi.EXTRA_DOWNLOAD_NOTI_ITEMS_DOWNLOAD_PREPARING, this.mPreparingDownloadMultiItems);
            putMessage(bundle, ShareApi.EXTRA_DOWNLOAD_NOTI_ONE_ITEM_DOWNLOADING, this.mDownloadingOneItem);
            putMessage(bundle, ShareApi.EXTRA_DOWNLOAD_NOTI_ITEMS_DOWNLOADING, this.mDownloadingMultiItems);
            putMessage(bundle, ShareApi.EXTRA_DOWNLOAD_NOTI_ONE_ITEM_DOWNLOADED, this.mOneItemDownloaded);
            putMessage(bundle, ShareApi.EXTRA_DOWNLOAD_NOTI_ALL_ITEMS_DOWNLOADED, this.mAllItemsDownloaded);
            putMessage(bundle, ShareApi.EXTRA_DOWNLOAD_NOTI_ONE_ITEM_DOWNLOADED_WITH_ITEMS_FAILED, this.mOneItemDownloadedAndMultiItemsFailed);
            putMessage(bundle, ShareApi.EXTRA_DOWNLOAD_NOTI_ITEMS_DOWNLOADED_WITH_ONE_ITEM_FAILED, this.mMultiItemsDownloadedAndOneItemFailed);
            putMessage(bundle, ShareApi.EXTRA_DOWNLOAD_NOTI_ITEMS_DOWNLOADED_WITH_ITEMS_FAILED, this.mMultiItemsDownloadedAndMultiItemsFailed);
            putMessage(bundle, ShareApi.EXTRA_DOWNLOAD_NOTI_ONE_ITEM_DOWNLOAD_FAILED, this.mOneItemDownloadFailed);
            putMessage(bundle, ShareApi.EXTRA_DOWNLOAD_NOTI_ALL_ITEMS_DOWNLOAD_FAILED, this.mAllItemsDownloadFailed);
            putMessage(bundle, "item_deleted", this.mOneItemDeleted);
            putMessage(bundle, "items_deleted", this.mMultiItemsDeleted);
            putMessage(bundle, "space_deleted", this.mOneSpaceDeleted);
            putMessage(bundle, "spaces_deleted", this.mMultiSpacesDeleted);
            putMessage(bundle, "group_status_changed", this.mGroupStatusChanged);
            putMessage(bundle, ShareApi.EXTRA_SHARE_NOTI_QUOTA_EXCEEDED_ERROR, this.mQuotaExceededError);
            return bundle;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ShareBaseResultCallback<T> {
        void onResult(T t);
    }

    @Deprecated
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ShareResultCallback {
        void onProgress(ShareSnapshot shareSnapshot);

        void onResult(SharedItemListResult sharedItemListResult);

        void onUploadComplete(ShareSnapshot shareSnapshot);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ShareSyncResultCallback {
        void onResult(BooleanResult booleanResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ShareUploadResultCallback<T> {
        void onProgress(ShareSnapshot shareSnapshot);

        void onResult(T t);

        void onUploadComplete(ShareSnapshot shareSnapshot);
    }

    @Deprecated
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SharedItemDeletionListResultCallback {
        void onResult(SharedItemListDeletionResult sharedItemListDeletionResult);
    }

    @Deprecated
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SharedItemDeletionResultCallback {
        void onResult(BooleanResult booleanResult);
    }

    @Deprecated
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SharedItemListResultCallback {
        void onResult(SharedItemListResult sharedItemListResult);
    }

    @Deprecated
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SharedItemListWithContentListResultCallback {
        void onResult(SharedItemListWithContentListResult sharedItemListWithContentListResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class SharedItemRequest {
        @Deprecated
        public static final int REQUEST_CONTENT_ID = 2;
        public static final int REQUEST_DATA = 3;
        public static final int REQUEST_HASH = 1;
        public static final int REQUEST_RESOURCE_ID = 5;
        public static final int REQUEST_URI = 0;
        public static final int REQUEST_URIS = 4;
        private String mContentMimeType;
        private Map mInstantMetaData;
        private String mItemId;
        private String mMemo;
        private Map mMetaData;
        protected int mRequestType;
        private String mTitle;

        public String getContentMimeType() {
            return this.mContentMimeType;
        }

        public Map getInstantMetaData() {
            return this.mInstantMetaData;
        }

        public String getItemId() {
            return this.mItemId;
        }

        public String getMemo() {
            return this.mMemo;
        }

        public Map getMetaData() {
            return this.mMetaData;
        }

        public int getRequestType() {
            return this.mRequestType;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public void setContentMimeType(String str) {
            this.mContentMimeType = str;
        }

        public void setInstantMetaData(Map map) {
            this.mInstantMetaData = map;
        }

        public void setItemId(String str) {
            this.mItemId = str;
        }

        public void setMemo(String str) {
            this.mMemo = str;
        }

        public void setMetaData(Map map) {
            this.mMetaData = map;
        }

        public void setTitle(String str) {
            this.mTitle = str;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SharedItemResultCallback {
        void onResult(SharedItemResult sharedItemResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SharedItemSyncResultCallback {
        void onResult(BooleanResult booleanResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SharedItemUpdateResultCallback {
        void onProgress(ShareSnapshot shareSnapshot);

        void onResult(SharedItemResult sharedItemResult);

        void onUploadComplete(ShareSnapshot shareSnapshot);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SharedItemUpdateWithUriListRequest extends SharedItemWithUriListRequest {
        private boolean mFileReplaceRequired = false;
        private String mItemId;

        public SharedItemUpdateWithUriListRequest(String str, String str2) {
            super(str2);
            this.mItemId = str;
        }

        public String getItemId() {
            return this.mItemId;
        }

        public boolean isFileReplaceRequired() {
            return this.mFileReplaceRequired;
        }

        public void setFileReplaceRequired(boolean z) {
            this.mFileReplaceRequired = z;
        }
    }

    @Deprecated
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SharedItemWithContentListResultCallback {
        void onProgress(ShareSnapshot shareSnapshot);

        void onResult(SharedItemWithUriListResult sharedItemWithUriListResult);

        void onUploadComplete(ShareSnapshot shareSnapshot);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SharedItemWithDataRequest extends SharedItemRequest {
        public SharedItemWithDataRequest(Map map) {
            setMetaData(map);
            this.mRequestType = 3;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SharedItemWithUriListRequest extends SharedItemRequest {
        private String mCreator = "";
        private List<String> mMimeTypeList = new ArrayList();
        private String mReferredResourceId = "";
        private List<Uri> mUris = new ArrayList();

        public SharedItemWithUriListRequest(String str) {
            setTitle(str);
            this.mRequestType = 4;
        }

        public void addUri(Uri uri, String str) {
            this.mUris.add(uri);
            this.mMimeTypeList.add(str);
        }

        public String getCreator() {
            return this.mCreator;
        }

        public List<String> getMimeTypeList() {
            return this.mMimeTypeList;
        }

        public String getResourceId() {
            return this.mReferredResourceId;
        }

        public List<Uri> getUris() {
            return this.mUris;
        }

        public void setCreator(String str) {
            this.mCreator = str;
        }

        public void setResourceId(String str) {
            this.mReferredResourceId = str;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SpaceDeletionResultCallback {
        void onResult(BooleanResult booleanResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SpaceListResultCallback {
        void onResult(SpaceListResult spaceListResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SpaceListSyncResultCallback {
        void onResult(BooleanResult booleanResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class SpaceRequest {
        private boolean mIsStandAlone = false;
        private String mMemo;
        private Map mMetaData;
        private String mMimeType;
        private String mTitle;

        public SpaceRequest(String str) {
            this.mTitle = str;
        }

        public String getMemo() {
            return this.mMemo;
        }

        public Map getMetaData() {
            return this.mMetaData;
        }

        public String getMimeType() {
            return this.mMimeType;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public boolean isStandAlone() {
            return this.mIsStandAlone;
        }

        public void setMemo(String str) {
            this.mMemo = str;
        }

        public void setMetaData(Map map) {
            this.mMetaData = map;
        }

        public void setMimeType(String str) {
            this.mMimeType = str;
        }

        public void setStandAlone(boolean z) {
            this.mIsStandAlone = z;
        }

        public void setTitle(String str) {
            this.mTitle = str;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SpaceResultCallback {
        void onResult(SpaceResult spaceResult);
    }

    @Deprecated
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SpaceWithMediaServiceContentIdRequest extends SpaceRequest {
        private String mCoverImageName;
        private long mCoverImageSize;
        private String mMediaServiceContentId;

        public SpaceWithMediaServiceContentIdRequest(String str) {
            super(str);
        }

        public String getCoverImageName() {
            return this.mCoverImageName;
        }

        public long getCoverImageSize() {
            return this.mCoverImageSize;
        }

        public String getMediaServiceContentId() {
            return this.mMediaServiceContentId;
        }

        public void setCoverImageName(String str) {
            this.mCoverImageName = str;
        }

        public void setCoverImageSize(long j2) {
            this.mCoverImageSize = j2;
        }

        public void setMediaServiceContentId(String str) {
            this.mMediaServiceContentId = str;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SpaceWithSCloudHashRequest extends SpaceRequest {
        private String mCoverImageName;
        private long mCoverImageSize;
        private String mHash;

        public SpaceWithSCloudHashRequest(String str) {
            super(str);
        }

        public String getCoverImageName() {
            return this.mCoverImageName;
        }

        public long getCoverImageSize() {
            return this.mCoverImageSize;
        }

        public String getHash() {
            return this.mHash;
        }

        public void setCoverImageName(String str) {
            this.mCoverImageName = str;
        }

        public void setCoverImageSize(long j2) {
            this.mCoverImageSize = j2;
        }

        public void setHash(String str) {
            this.mHash = str;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SpaceWithUriRequest extends SpaceRequest {
        private Uri mCoverImageUri;

        public SpaceWithUriRequest(String str) {
            super(str);
        }

        public Uri getCoverImageUri() {
            return this.mCoverImageUri;
        }

        public void setCoverImageUri(Uri uri) {
            this.mCoverImageUri = uri;
        }
    }

    public ShareApi(SeMobileServiceSession seMobileServiceSession) {
        this.mCid = null;
        this.mFeatureId = -1;
        this.featureIdSet = new HashSet<>(Arrays.asList(new Integer[]{4, 32, 32, 101, 102, 103, 104, 107, 501}));
        this.mShareApiImpl = new ShareApiImpl(seMobileServiceSession);
        this.mLegacyShareApiImpl = new LegacyShareApiImpl(seMobileServiceSession);
    }

    private boolean isValidFeatureId(int i2) {
        return this.featureIdSet.contains(Integer.valueOf(i2));
    }

    public void clearUnreadCount(String str) {
        this.mShareApiImpl.clearUnreadCount(str, this.mCid, this.mFeatureId);
    }

    public QuotaResult getFamilyQuota() {
        return this.mShareApiImpl.getFamilyQuota(this.mFeatureId);
    }

    public QuotaResult getQuota() {
        return this.mShareApiImpl.getQuota(this.mFeatureId);
    }

    public OriginalFileListResult getVerifiedOriginalFileList(List<String> list) {
        return this.mShareApiImpl.getVerifiedOriginalFileList(list, this.mFeatureId);
    }

    public ShareController requestBulkItemShare(Context context, String str, List<SharedItemRequest> list, ActionIntent actionIntent, NotificationMessage notificationMessage, Boolean bool, ShareUploadResultCallback<SharedItemListResult> shareUploadResultCallback) {
        return this.mShareApiImpl.requestBulkItemShare(context, str, list, actionIntent, notificationMessage, this.mFeatureId, bool, shareUploadResultCallback);
    }

    public int requestDeleteAllItemsFromTrashBin(String str, String str2, ShareBaseResultCallback<ItemListResult> shareBaseResultCallback) {
        return this.mShareApiImpl.requestDeleteAllItemsFromTrashBin(str, str2, this.mCid, this.mFeatureId, shareBaseResultCallback);
    }

    public int requestDeleteItemListFromTrashBin(String str, String str2, List<String> list, ShareBaseResultCallback<ItemListResult> shareBaseResultCallback) {
        return this.mShareApiImpl.requestDeleteItemListFromTrashBin(str, str2, list, this.mCid, this.mFeatureId, shareBaseResultCallback);
    }

    @Deprecated
    public int requestInstantShare(GroupApi.InvitationRequest invitationRequest, List<SharedItemRequest> list, ShareResultCallback shareResultCallback) {
        return -1;
    }

    public int requestItemThumbnailDownload(String str, String str2, String str3, String str4, String str5, ShareBaseResultCallback<DownloadImageResult> shareBaseResultCallback) {
        return this.mShareApiImpl.requestItemThumbnailDownload(str, str2, str3, str4, str5, this.mFeatureId, shareBaseResultCallback);
    }

    public int requestMoveItemListToTrashBin(String str, String str2, List<String> list, ShareBaseResultCallback<ItemListResult> shareBaseResultCallback) {
        return this.mShareApiImpl.requestMoveItemListToTrashBin(str, str2, list, this.mCid, this.mFeatureId, shareBaseResultCallback);
    }

    public int requestRestoreItemListFromTrashBin(String str, String str2, List<String> list, ShareBaseResultCallback<ItemListResult> shareBaseResultCallback) {
        return this.mShareApiImpl.requestRestoreItemListFromTrashBin(str, str2, list, this.mCid, this.mFeatureId, shareBaseResultCallback);
    }

    public ShareController requestShare(String str, List<SharedItemRequest> list, ShareResultCallback shareResultCallback) {
        return this.mLegacyShareApiImpl.requestShare(str, list, this.mFeatureId, shareResultCallback, (PendingIntent) null, (Bundle) null);
    }

    @Deprecated
    public int requestSharedContentDownload(String str, String str2, List<String> list, ContentDownloadingResultCallback contentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle, String str3) {
        return -1;
    }

    public int requestSharedContentDownloadToHiddenFolder(String str, List<String> list, ContentDownloadingResultCallback contentDownloadingResultCallback) {
        return this.mShareApiImpl.requestSharedContentDownloadToHiddenFolder(str, list, this.mFeatureId, contentDownloadingResultCallback);
    }

    public int requestSharedItem(String str, String str2, SharedItemResultCallback sharedItemResultCallback) {
        return requestSharedItem((String) null, str, str2, sharedItemResultCallback);
    }

    public int requestSharedItemDeletion(String str, List<String> list, ShareBaseResultCallback<ItemListResult> shareBaseResultCallback) {
        return this.mShareApiImpl.requestSharedItemDeletion(str, list, this.mCid, this.mFeatureId, shareBaseResultCallback);
    }

    public int requestSharedItemList(String str, String str2, String str3, SharedItemListResultCallback sharedItemListResultCallback) {
        return this.mShareApiImpl.requestSharedItemList(str, str2, str3, this.mFeatureId, sharedItemListResultCallback);
    }

    public int requestSharedItemListInTrashBin(String str, String str2, String str3, ShareBaseResultCallback<TrashedItemListResult> shareBaseResultCallback) {
        return this.mShareApiImpl.requestSharedItemListInTrashBin(str, str2, this.mCid, str3, this.mFeatureId, shareBaseResultCallback);
    }

    public int requestSharedItemSync(String str, String str2, SharedItemSyncResultCallback sharedItemSyncResultCallback) {
        return this.mShareApiImpl.requestSharedItemSync(str, str2, this.mCid, this.mFeatureId, sharedItemSyncResultCallback);
    }

    public ShareController requestSharedItemUpdate(String str, List<SharedItemRequest> list, ShareUploadResultCallback<SharedItemListResult> shareUploadResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        return this.mShareApiImpl.requestSharedItemUpdate(str, list, this.mFeatureId, shareUploadResultCallback, pendingIntent, bundle);
    }

    public ShareController requestSharedItemWithUriListUpdate(String str, List<SharedItemUpdateWithUriListRequest> list, ShareUploadResultCallback<SharedItemListWithUriListResult> shareUploadResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        return this.mShareApiImpl.requestSharedItemWithUriListUpdate(str, list, this.mCid, this.mFeatureId, shareUploadResultCallback, pendingIntent, bundle);
    }

    public int requestSpace(String str, SpaceResultCallback spaceResultCallback) {
        return this.mShareApiImpl.requestSpace(str, this.mCid, this.mFeatureId, spaceResultCallback);
    }

    @Deprecated
    public int requestSpaceCoverImageDownload(String str, ImageDownloadingResultCallback imageDownloadingResultCallback) {
        return this.mShareApiImpl.requestSpaceCoverImageDownload(str, this.mFeatureId, imageDownloadingResultCallback);
    }

    public int requestSpaceCreation(String str, SpaceRequest spaceRequest, SpaceResultCallback spaceResultCallback) {
        return this.mShareApiImpl.requestSpaceCreation(str, spaceRequest, this.mCid, this.mFeatureId, spaceResultCallback);
    }

    @Deprecated
    public int requestSpaceDeletion(String str, SpaceDeletionResultCallback spaceDeletionResultCallback) {
        return this.mShareApiImpl.requestSpaceDeletion(str, this.mCid, this.mFeatureId, spaceDeletionResultCallback);
    }

    public int requestSpaceList(String str, SpaceListResultCallback spaceListResultCallback) {
        return this.mShareApiImpl.requestSpaceList(str, (String) null, this.mFeatureId, spaceListResultCallback);
    }

    @Deprecated
    public int requestSpaceListSync(SpaceListSyncResultCallback spaceListSyncResultCallback) {
        return this.mShareApiImpl.requestSpaceListSync(this.mCid, this.mFeatureId, spaceListSyncResultCallback);
    }

    public int requestSpaceUpdate(String str, Map map, SpaceResultCallback spaceResultCallback) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("meta_data", (HashMap) map);
        return this.mShareApiImpl.requestSpaceUpdate(str, bundle, this.mCid, this.mFeatureId, spaceResultCallback);
    }

    @Deprecated
    public int requestSpaceUpdateWithMediaServiceContentId(String str, String str2, String str3, String str4, long j2, SpaceResultCallback spaceResultCallback) {
        String str5 = str2;
        Bundle bundle = new Bundle();
        bundle.putString("media_service_content_id", str5);
        bundle.putString("mime_type", str3);
        bundle.putString("file_name", str4);
        bundle.putLong(BundleKey.FILE_SIZE, j2);
        return this.mShareApiImpl.requestSpaceUpdate(str, bundle, (String) null, this.mFeatureId, spaceResultCallback);
    }

    @Deprecated
    public int requestSync(ShareSyncResultCallback shareSyncResultCallback) {
        return this.mShareApiImpl.requestSync(this.mCid, this.mFeatureId, shareSyncResultCallback);
    }

    @Deprecated
    public int requestSyncWithLastThumbnail(SpaceListSyncResultCallback spaceListSyncResultCallback) {
        return this.mShareApiImpl.requestSpaceListSync(this.mCid, this.mFeatureId, spaceListSyncResultCallback);
    }

    @Deprecated
    public int requestThumbnailDownload(String str, String str2, String str3, String str4, String str5, ShareBaseResultCallback<DownloadImageResult> shareBaseResultCallback) {
        return this.mShareApiImpl.requestThumbnailDownload(str, str2, str3, str4, str5, this.mFeatureId, shareBaseResultCallback);
    }

    public int requestWebLinkEnabled(String str, String str2, boolean z, ShareBaseResultCallback<SpaceResult> shareBaseResultCallback) {
        return this.mShareApiImpl.requestWebLinkEnabled(str, str2, z, this.mFeatureId, shareBaseResultCallback);
    }

    public ShareController requestShare(String str, List<SharedItemRequest> list, ShareResultCallback shareResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        return this.mLegacyShareApiImpl.requestShare(str, list, this.mFeatureId, shareResultCallback, pendingIntent, bundle);
    }

    @Deprecated
    public int requestSharedContentDownload(String str, List<String> list, ContentDownloadingResultCallback contentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle, String str2) {
        return -1;
    }

    public int requestSharedItem(String str, String str2, String str3, SharedItemResultCallback sharedItemResultCallback) {
        return this.mShareApiImpl.requestSharedItem(str, str2, str3, this.mFeatureId, sharedItemResultCallback);
    }

    @Deprecated
    public int requestSharedItemDeletion(String str, String str2, SharedItemDeletionResultCallback sharedItemDeletionResultCallback) {
        return this.mShareApiImpl.requestSharedItemDeletion(str, str2, this.mFeatureId, sharedItemDeletionResultCallback);
    }

    public int requestSharedItemList(String str, String str2, String str3, SharedItemListWithContentListResultCallback sharedItemListWithContentListResultCallback) {
        return this.mShareApiImpl.requestSharedItemList(str, str2, str3, this.mCid, this.mFeatureId, sharedItemListWithContentListResultCallback);
    }

    @Deprecated
    public int requestSharedItemSync(String str, SharedItemSyncResultCallback sharedItemSyncResultCallback) {
        return this.mShareApiImpl.requestSharedItemSync(str, this.mFeatureId, sharedItemSyncResultCallback);
    }

    @Deprecated
    public ShareController requestSharedItemUpdate(String str, String str2, SharedItemRequest sharedItemRequest, SharedItemUpdateResultCallback sharedItemUpdateResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        return this.mShareApiImpl.requestSharedItemUpdate(str, str2, sharedItemRequest, this.mFeatureId, sharedItemUpdateResultCallback, pendingIntent, bundle);
    }

    public int requestSpaceList(SpaceListResultCallback spaceListResultCallback) {
        return this.mShareApiImpl.requestSpaceList(this.mCid, this.mFeatureId, spaceListResultCallback);
    }

    @Deprecated
    public int requestSync(boolean z, ShareSyncResultCallback shareSyncResultCallback) {
        return this.mLegacyShareApiImpl.requestSync(this.mCid, this.mFeatureId, z, TYPE_FULL_SYNC, shareSyncResultCallback);
    }

    @Deprecated
    public int requestSyncWithLastThumbnail(boolean z, ShareSyncResultCallback shareSyncResultCallback) {
        return this.mLegacyShareApiImpl.requestSync(this.mCid, this.mFeatureId, z, TYPE_SYNC_WITH_LAST_THUMBNAIL, shareSyncResultCallback);
    }

    @Deprecated
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SharedItemWithMediaServiceContentIdRequest extends SharedItemRequest {
        private String mContentName;
        private long mContentSize;
        private String mMediaServiceContentId;

        public SharedItemWithMediaServiceContentIdRequest(String str) {
            setTitle(str);
            this.mRequestType = 2;
        }

        public String getContentName() {
            return this.mContentName;
        }

        public long getContentSize() {
            return this.mContentSize;
        }

        public String getMediaServiceContentId() {
            return this.mMediaServiceContentId;
        }

        public void setContentName(String str) {
            this.mContentName = str;
        }

        public void setContentSize(long j2) {
            this.mContentSize = j2;
        }

        public void setMediaServiceContentId(String str) {
            this.mMediaServiceContentId = str;
        }

        public SharedItemWithMediaServiceContentIdRequest(String str, String str2) {
            setContentMimeType(str2);
            setMediaServiceContentId(str);
        }

        public SharedItemWithMediaServiceContentIdRequest(String str, String str2, long j2, String str3) {
            setContentMimeType(str2);
            setMediaServiceContentId(str);
            setContentSize(j2);
            setContentName(str3);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SharedItemWithSCloudHashRequest extends SharedItemRequest {
        private String mContentName;
        private long mContentSize;
        private String mHash;

        public SharedItemWithSCloudHashRequest(String str) {
            setTitle(str);
            this.mRequestType = 1;
        }

        public String getContentName() {
            return this.mContentName;
        }

        public long getContentSize() {
            return this.mContentSize;
        }

        public String getHash() {
            return this.mHash;
        }

        public void setContentName(String str) {
            this.mContentName = str;
        }

        public void setContentSize(long j2) {
            this.mContentSize = j2;
        }

        public void setHash(String str) {
            this.mHash = str;
        }

        public SharedItemWithSCloudHashRequest(String str, String str2) {
            setContentMimeType(str2);
            setHash(str);
        }

        public SharedItemWithSCloudHashRequest(String str, String str2, long j2, String str3) {
            setContentMimeType(str2);
            setHash(str);
            setContentSize(j2);
            setContentName(str3);
        }
    }

    public ShareController requestShare(String str, List<SharedItemRequest> list, ActionIntent actionIntent, NotificationMessage notificationMessage, ShareResultCallback shareResultCallback) {
        return this.mLegacyShareApiImpl.requestShare(str, list, actionIntent, notificationMessage, this.mFeatureId, shareResultCallback);
    }

    public int requestSharedContentDownload(String str, List<String> list, ContentDownloadingResultCallback contentDownloadingResultCallback) {
        return this.mShareApiImpl.requestSharedContentDownload(str, list, this.mFeatureId, contentDownloadingResultCallback, (PendingIntent) null, (Bundle) null, (String) null);
    }

    @Deprecated
    public int requestSharedItemDeletion(String str, List<String> list, SharedItemDeletionListResultCallback sharedItemDeletionListResultCallback) {
        return this.mShareApiImpl.requestSharedItemDeletion(str, list, this.mFeatureId, sharedItemDeletionListResultCallback);
    }

    @Deprecated
    public ShareController requestSharedItemUpdate(String str, String str2, SharedItemWithUriListRequest sharedItemWithUriListRequest, SharedItemWithContentListResultCallback sharedItemWithContentListResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        return this.mShareApiImpl.requestSharedItemUpdate(str, str2, sharedItemWithUriListRequest, this.mFeatureId, sharedItemWithContentListResultCallback, pendingIntent, bundle);
    }

    public int requestSync(SyncOption syncOption, ShareSyncResultCallback shareSyncResultCallback) {
        return this.mLegacyShareApiImpl.requestSync(this.mCid, this.mFeatureId, syncOption, shareSyncResultCallback);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SharedItemWithUriRequest extends SharedItemRequest {
        private final String FILE_URI = "file:///";
        private Uri mUri;

        public SharedItemWithUriRequest(String str) {
            setTitle(str);
            this.mRequestType = 0;
        }

        @Deprecated
        public Uri getContentUri() {
            return this.mUri;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public boolean isFileUri() {
            return this.mUri.toString().startsWith("file:///");
        }

        @Deprecated
        public void setContentUri(Uri uri) {
            this.mUri = uri;
        }

        public void setUri(Uri uri) {
            this.mUri = uri;
        }

        public SharedItemWithUriRequest(Uri uri, String str) {
            setContentMimeType(str);
            setUri(uri);
        }
    }

    public ShareController requestShare(String str, List<SharedItemWithUriListRequest> list, ShareUploadResultCallback<SharedItemListWithUriListResult> shareUploadResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        return this.mShareApiImpl.requestShare(str, list, this.mCid, this.mFeatureId, shareUploadResultCallback, pendingIntent, bundle);
    }

    public int requestSharedContentDownload(String str, List<String> list, ContentDownloadingResultCallback contentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        return this.mShareApiImpl.requestSharedContentDownload(str, list, this.mFeatureId, contentDownloadingResultCallback, pendingIntent, bundle, (String) null);
    }

    @Deprecated
    public int requestSpaceUpdate(String str, String str2, String str3, Uri uri, String str4, Map map, SpaceResultCallback spaceResultCallback) {
        String str5;
        String str6 = str2;
        Bundle bundle = new Bundle();
        bundle.putString("title", str6);
        bundle.putString("memo", str3);
        if (uri == null) {
            str5 = "";
        } else {
            str5 = uri.toString();
        }
        bundle.putString("content_uri", str5);
        bundle.putString("mime_type", str4);
        bundle.putSerializable("meta_data", (HashMap) map);
        return this.mShareApiImpl.requestSpaceUpdate(str, bundle, this.mCid, this.mFeatureId, spaceResultCallback);
    }

    @Deprecated
    public ShareController requestShare(String str, SharedItemWithUriListRequest sharedItemWithUriListRequest, SharedItemWithContentListResultCallback sharedItemWithContentListResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        return this.mLegacyShareApiImpl.requestShare(str, sharedItemWithUriListRequest, this.mFeatureId, sharedItemWithContentListResultCallback, pendingIntent, bundle);
    }

    public int requestSharedContentDownload(String str, String str2, List<String> list, ContentDownloadingResultCallback contentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        return this.mShareApiImpl.requestSharedContentDownload(str, str2, list, this.mCid, this.mFeatureId, contentDownloadingResultCallback, pendingIntent, bundle, (String) null);
    }

    public ShareApi(SeMobileServiceSession seMobileServiceSession, String str) {
        this(seMobileServiceSession);
        this.mCid = str;
    }

    public int requestSpaceUpdate(String str, String str2, String str3, SpaceResultCallback spaceResultCallback) {
        Bundle bundle = new Bundle();
        bundle.putString("title", str2);
        bundle.putString("memo", str3);
        return this.mShareApiImpl.requestSpaceUpdate(str, bundle, this.mCid, this.mFeatureId, spaceResultCallback);
    }

    public ShareApi(SeMobileServiceSession seMobileServiceSession, int i2) {
        this(seMobileServiceSession);
        if (isValidFeatureId(i2)) {
            this.mFeatureId = i2;
            return;
        }
        throw new NotSupportedApiException(i2 + " is not valid");
    }

    @Deprecated
    public int requestSpaceUpdate(String str, Uri uri, String str2, SpaceResultCallback spaceResultCallback) {
        String str3;
        Bundle bundle = new Bundle();
        if (uri == null) {
            str3 = "";
        } else {
            str3 = uri.toString();
        }
        bundle.putString("content_uri", str3);
        bundle.putString("mime_type", str2);
        return this.mShareApiImpl.requestSpaceUpdate(str, bundle, this.mCid, this.mFeatureId, spaceResultCallback);
    }

    @Deprecated
    public int requestSpaceUpdate(String str, String str2, String str3, String str4, long j2, SpaceResultCallback spaceResultCallback) {
        String str5 = str2;
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.CONTENT_HASH, str5);
        bundle.putString("mime_type", str3);
        bundle.putString("file_name", str4);
        bundle.putLong(BundleKey.FILE_SIZE, j2);
        return this.mShareApiImpl.requestSpaceUpdate(str, bundle, this.mCid, this.mFeatureId, spaceResultCallback);
    }

    public int requestSpaceUpdate(String str, boolean z, SpaceResultCallback spaceResultCallback) {
        return this.mShareApiImpl.requestSpaceUpdate(str, z, this.mCid, this.mFeatureId, spaceResultCallback);
    }
}
