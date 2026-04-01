package com.samsung.android.sdk.mobileservice.social.share;

import N2.j;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.mobileservice.common.api.SeMobileServiceApi;
import com.samsung.android.sdk.mobileservice.common.exception.NotConnectedException;
import com.samsung.android.sdk.mobileservice.common.result.BooleanResult;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.social.IMobileServiceSocial;
import com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.IContentDownloadingResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.IDownloadThumbnailResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.IShareBulkItemResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.IShareResultWithFileListCallback;
import com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISharedItemDeletionResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISharedItemListDeletionResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISharedItemListResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISharedItemResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISharedItemUpdateResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISpaceCoverImageDownloadingResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISpaceDeletionResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISpaceListResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISpaceResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ITrashBinResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.Quota;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;
import com.samsung.android.sdk.mobileservice.social.share.ShareController;
import com.samsung.android.sdk.mobileservice.social.share.SharedItemWithUriList;
import com.samsung.android.sdk.mobileservice.social.share.provider.SharedItemContract;
import com.samsung.android.sdk.mobileservice.social.share.result.ContentDownloadResult;
import com.samsung.android.sdk.mobileservice.social.share.result.DownloadImageResult;
import com.samsung.android.sdk.mobileservice.social.share.result.ItemListResult;
import com.samsung.android.sdk.mobileservice.social.share.result.OriginalFile;
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
import i.C0212a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ShareApiImpl extends SeMobileServiceApi {
    private static final int BULK_INSERT_CHUNK_SIZE = 250;
    private static final int FEATURE_ID_INVALID = -1;
    private static final String TAG = "ShareApiImpl";
    private final String APP_ID_REMINDER = "8o8b82h22a";
    private final ShareController.ShareControllerApiPicker mApiPicker = new ShareController.ShareControllerApiPicker() {
        public String getAppId() {
            return ShareApiImpl.this.getAppId();
        }

        public String getReference() {
            return ShareApiImpl.this.getReference();
        }

        public IMobileServiceSocial getSocialService() {
            return ShareApiImpl.this.getSocialService();
        }
    };

    public ShareApiImpl(SeMobileServiceSession seMobileServiceSession) {
        super(seMobileServiceSession, "ShareApi");
        checkAuthorized(0, 2);
    }

    private List<OriginalFile> bundleToOriginalFileList(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        ArrayList<Bundle> parcelableArrayList = bundle.getParcelableArrayList(BundleKey.ORIGINAL_FILE_LIST);
        if (parcelableArrayList != null) {
            for (Bundle bundle2 : parcelableArrayList) {
                String string = bundle2.getString("hash");
                String string2 = bundle2.getString("file_path");
                String string3 = bundle2.getString("content_uri");
                Uri uri = Uri.EMPTY;
                if (!TextUtils.isEmpty(string3)) {
                    uri = Uri.parse(string3);
                }
                arrayList.add(new OriginalFile(string, string2, uri));
            }
        }
        return arrayList;
    }

    private Quota bundleToQuota(Bundle bundle) {
        long j2;
        long j3;
        long j8;
        Bundle bundle2 = bundle;
        String str = "";
        if (bundle2 != null) {
            str = bundle2.getString(BundleKey.LIMIT_TYPE, str);
        }
        long j10 = -1;
        if (bundle2 != null) {
            j2 = bundle2.getLong(BundleKey.TOTAL_USAGE, -1);
        } else {
            j2 = -1;
        }
        if (bundle2 != null) {
            j3 = bundle2.getLong("limit", -1);
        } else {
            j3 = -1;
        }
        if (bundle2 != null) {
            j8 = bundle2.getLong(BundleKey.TOTAL_COUNT_USAGE, -1);
        } else {
            j8 = -1;
        }
        if (bundle2 != null) {
            j10 = bundle2.getLong(BundleKey.LIMIT_COUNT_USAGE, -1);
        }
        long j11 = j10;
        boolean z = false;
        if (bundle2 != null && bundle2.getBoolean(BundleKey.isUnlimited, false)) {
            z = true;
        }
        return new Quota(Quota.LimitType.toLimitType(str), j2, j3, j8, j11, z);
    }

    private boolean checkInvalidFeatureIdAndAgentVersion(int i2) {
        if (i2 != 501 || isSupportedSemsAgentVersionBetween(CommonConstants.SupportedApiMinVersion.VERSION_10_8_8, CommonConstants.SupportedApiMinVersion.VERSION_11_0) || isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_1_0)) {
            return false;
        }
        infoLog("isValidFeatureIdAndAgentVersion with" + i2 + "is not supported in this agent version");
        return true;
    }

    private IShareSyncResultCallback createShareSyncCallback(final ShareApi.ShareSyncResultCallback shareSyncResultCallback) {
        return new IShareSyncResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSync onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (shareSyncResultCallback != null) {
                    shareSyncResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                }
            }

            public void onSuccess() {
                ShareApiImpl.this.infoLog("requestSync onSuccess ");
                ShareApi.ShareSyncResultCallback shareSyncResultCallback = shareSyncResultCallback;
                if (shareSyncResultCallback != null) {
                    shareSyncResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                }
            }
        };
    }

    private int downloadThumbnail(String str, String str2, String str3, String str4, String str5, ShareApi.ShareBaseResultCallback<DownloadImageResult> shareBaseResultCallback) {
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        final ShareApi.ShareBaseResultCallback<DownloadImageResult> shareBaseResultCallback2 = shareBaseResultCallback;
        try {
            getSocialService().requestThumbnailDownload(getAppId(), str, str2, str3, str4, str5, new IDownloadThumbnailResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ShareApiImpl shareApiImpl = ShareApiImpl.this;
                    StringBuilder p6 = C0086a.p(j2, "requestThumbnailDownload onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    shareApiImpl.infoLog(p6.toString());
                    if (shareBaseResultCallback2 != null) {
                        shareBaseResultCallback2.onResult(new DownloadImageResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (DownloadImageResult.DownloadedImage) null));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    ShareApiImpl.this.infoLog("requestThumbnailDownload onSuccess ");
                    if (shareBaseResultCallback2 != null) {
                        Uri uri = null;
                        String string = bundle.getString(BundleKey.ITEM_ID, (String) null);
                        String string2 = bundle.getString(BundleKey.CONTENT_HASH, (String) null);
                        String string3 = bundle.getString(BundleKey.ITEM_THUMBNAIL_LOCAL_PATH, (String) null);
                        if (string3 != null) {
                            uri = Uri.parse(string3);
                        }
                        DownloadImageResult.DownloadedImage downloadedImage = new DownloadImageResult.DownloadedImage(string, string2, uri);
                        ShareApiImpl.this.debugLog(j.d("- item_id = [", string, "], thumbnail local path = [", string3, "] "));
                        shareBaseResultCallback2.onResult(new DownloadImageResult(new CommonResultStatus(1), downloadedImage));
                    }
                }
            });
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    private SharedItemWithUriList.Content getContentFromDB(Cursor cursor) {
        SharedItemWithUriList.Content content = new SharedItemWithUriList.Content(cursor.getString(cursor.getColumnIndex("hash")), cursor.getString(cursor.getColumnIndex("mime_type")), (Uri) null, cursor.getString(cursor.getColumnIndex("name")), cursor.getLong(cursor.getColumnIndex("size")), (String) null);
        content.setThumbnailContentUri(Uri.parse(cursor.getString(cursor.getColumnIndex("thumbnail_content_uri"))));
        return content;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0107 A[SYNTHETIC, Splitter:B:18:0x0107] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x010e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.samsung.android.sdk.mobileservice.social.share.SharedItem> getItemFromDB(android.content.Context r8, int r9, long r10) {
        /*
            r7 = this;
            java.lang.String r0 = r7.getAppId()
            android.net.Uri r2 = com.samsung.android.sdk.mobileservice.social.share.RequestDBConstants.getItemUri(r0, r9)
            java.lang.String r9 = "bulk_item_request_id = "
            java.lang.String r4 = A.a.f(r9, r10)
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch:{ Exception -> 0x010b }
            r5 = 0
            r6 = 0
            r3 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x010b }
            if (r8 == 0) goto L_0x0100
            boolean r10 = r8.moveToFirst()     // Catch:{ all -> 0x00fd }
            if (r10 == 0) goto L_0x0100
        L_0x0026:
            com.samsung.android.sdk.mobileservice.social.share.SharedItem r10 = new com.samsung.android.sdk.mobileservice.social.share.SharedItem     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = "itemId"
            int r11 = r8.getColumnIndex(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = r8.getString(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r0 = "spaceId"
            int r0 = r8.getColumnIndex(r0)     // Catch:{ all -> 0x00fd }
            java.lang.String r0 = r8.getString(r0)     // Catch:{ all -> 0x00fd }
            java.lang.String r1 = "owner"
            int r1 = r8.getColumnIndex(r1)     // Catch:{ all -> 0x00fd }
            java.lang.String r1 = r8.getString(r1)     // Catch:{ all -> 0x00fd }
            r10.<init>(r11, r0, r1)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = "title"
            int r11 = r8.getColumnIndex(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = r8.getString(r11)     // Catch:{ all -> 0x00fd }
            r10.setTitle(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = "memo"
            int r11 = r8.getColumnIndex(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = r8.getString(r11)     // Catch:{ all -> 0x00fd }
            r10.setMemo(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = "creator"
            int r11 = r8.getColumnIndex(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = r8.getString(r11)     // Catch:{ all -> 0x00fd }
            r10.setCreator(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = "last_modifier"
            int r11 = r8.getColumnIndex(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = r8.getString(r11)     // Catch:{ all -> 0x00fd }
            r10.setLastModifier(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = "createTime"
            int r11 = r8.getColumnIndex(r11)     // Catch:{ all -> 0x00fd }
            long r0 = r8.getLong(r11)     // Catch:{ all -> 0x00fd }
            r10.setCreatedTime(r0)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = "modifiedTime"
            int r11 = r8.getColumnIndex(r11)     // Catch:{ all -> 0x00fd }
            long r0 = r8.getLong(r11)     // Catch:{ all -> 0x00fd }
            r10.setModifiedTime(r0)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = "mime_type"
            int r11 = r8.getColumnIndex(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = r8.getString(r11)     // Catch:{ all -> 0x00fd }
            r10.setMimeType(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = "streaming_url"
            int r11 = r8.getColumnIndex(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = r8.getString(r11)     // Catch:{ all -> 0x00fd }
            r10.setStreamingUrl(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = "size"
            int r11 = r8.getColumnIndex(r11)     // Catch:{ all -> 0x00fd }
            long r0 = r8.getLong(r11)     // Catch:{ all -> 0x00fd }
            r10.setSize(r0)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = "is_owned_by_me"
            int r11 = r8.getColumnIndex(r11)     // Catch:{ all -> 0x00fd }
            int r11 = r8.getInt(r11)     // Catch:{ all -> 0x00fd }
            if (r11 <= 0) goto L_0x00d0
            r11 = 1
            goto L_0x00d1
        L_0x00d0:
            r11 = 0
        L_0x00d1:
            r10.setOwnedByMe(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = "thumbnail_content_uri"
            int r11 = r8.getColumnIndex(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = r8.getString(r11)     // Catch:{ all -> 0x00fd }
            android.net.Uri r11 = android.net.Uri.parse(r11)     // Catch:{ all -> 0x00fd }
            r10.setThumbnailContentUri(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = "meta_data"
            int r11 = r8.getColumnIndex(r11)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = r8.getString(r11)     // Catch:{ all -> 0x00fd }
            r10.setMetaString(r11)     // Catch:{ all -> 0x00fd }
            r9.add(r10)     // Catch:{ all -> 0x00fd }
            boolean r10 = r8.moveToNext()     // Catch:{ all -> 0x00fd }
            if (r10 != 0) goto L_0x0026
            goto L_0x0105
        L_0x00fd:
            r0 = move-exception
            r10 = r0
            goto L_0x010f
        L_0x0100:
            java.lang.String r10 = "content uri is wrong"
            r7.infoLog(r10)     // Catch:{ all -> 0x00fd }
        L_0x0105:
            if (r8 == 0) goto L_0x010e
            r8.close()     // Catch:{ Exception -> 0x010b }
            return r9
        L_0x010b:
            r0 = move-exception
            r8 = r0
            goto L_0x011e
        L_0x010e:
            return r9
        L_0x010f:
            throw r10     // Catch:{ all -> 0x0110 }
        L_0x0110:
            r0 = move-exception
            r11 = r0
            if (r8 == 0) goto L_0x011d
            r8.close()     // Catch:{ all -> 0x0118 }
            goto L_0x011d
        L_0x0118:
            r0 = move-exception
            r8 = r0
            r10.addSuppressed(r8)     // Catch:{ Exception -> 0x010b }
        L_0x011d:
            throw r11     // Catch:{ Exception -> 0x010b }
        L_0x011e:
            r7.secureLog(r8)
            java.lang.String r8 = "getItemFromDB exception is occurred while get response"
            r7.infoLog(r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.social.share.ShareApiImpl.getItemFromDB(android.content.Context, int, long):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0067 A[SYNTHETIC, Splitter:B:18:0x0067] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.samsung.android.sdk.mobileservice.social.share.SharedItemWithUriList> getItemWithUriListFromDB(android.content.Context r8, int r9, long r10) {
        /*
            r7 = this;
            java.lang.String r0 = r7.getAppId()
            android.net.Uri r2 = com.samsung.android.sdk.mobileservice.social.share.RequestDBConstants.getItemUri(r0, r9)
            java.lang.String r9 = "request_id = "
            java.lang.String r4 = A.a.f(r9, r10)
            java.util.HashMap r9 = new java.util.HashMap
            r9.<init>()
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch:{ Exception -> 0x007a }
            r5 = 0
            r6 = 0
            r3 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x007a }
            if (r8 == 0) goto L_0x0060
            boolean r10 = r8.moveToFirst()     // Catch:{ all -> 0x0044 }
            if (r10 == 0) goto L_0x0060
        L_0x0026:
            java.lang.String r10 = "itemId"
            int r10 = r8.getColumnIndex(r10)     // Catch:{ all -> 0x0044 }
            java.lang.String r10 = r8.getString(r10)     // Catch:{ all -> 0x0044 }
            java.lang.Object r11 = r9.get(r10)     // Catch:{ all -> 0x0044 }
            com.samsung.android.sdk.mobileservice.social.share.SharedItemWithUriList r11 = (com.samsung.android.sdk.mobileservice.social.share.SharedItemWithUriList) r11     // Catch:{ all -> 0x0044 }
            if (r11 == 0) goto L_0x0047
            java.util.List r10 = r11.getContentList()     // Catch:{ all -> 0x0044 }
            com.samsung.android.sdk.mobileservice.social.share.SharedItemWithUriList$Content r11 = r7.getContentFromDB(r8)     // Catch:{ all -> 0x0044 }
            r10.add(r11)     // Catch:{ all -> 0x0044 }
            goto L_0x0059
        L_0x0044:
            r0 = move-exception
            r10 = r0
            goto L_0x006b
        L_0x0047:
            com.samsung.android.sdk.mobileservice.social.share.SharedItemWithUriList r11 = r7.getSharedItemFromDB(r8, r10)     // Catch:{ all -> 0x0044 }
            java.util.List r0 = r11.getContentList()     // Catch:{ all -> 0x0044 }
            com.samsung.android.sdk.mobileservice.social.share.SharedItemWithUriList$Content r1 = r7.getContentFromDB(r8)     // Catch:{ all -> 0x0044 }
            r0.add(r1)     // Catch:{ all -> 0x0044 }
            r9.put(r10, r11)     // Catch:{ all -> 0x0044 }
        L_0x0059:
            boolean r10 = r8.moveToNext()     // Catch:{ all -> 0x0044 }
            if (r10 != 0) goto L_0x0026
            goto L_0x0065
        L_0x0060:
            java.lang.String r10 = "content uri is wrong"
            r7.infoLog(r10)     // Catch:{ all -> 0x0044 }
        L_0x0065:
            if (r8 == 0) goto L_0x007f
            r8.close()     // Catch:{ Exception -> 0x007a }
            goto L_0x007f
        L_0x006b:
            throw r10     // Catch:{ all -> 0x006c }
        L_0x006c:
            r0 = move-exception
            r11 = r0
            if (r8 == 0) goto L_0x0079
            r8.close()     // Catch:{ all -> 0x0074 }
            goto L_0x0079
        L_0x0074:
            r0 = move-exception
            r8 = r0
            r10.addSuppressed(r8)     // Catch:{ Exception -> 0x007a }
        L_0x0079:
            throw r11     // Catch:{ Exception -> 0x007a }
        L_0x007a:
            java.lang.String r8 = "getItemWithUriListFromDB exception is occurred while get response"
            r7.infoLog(r8)
        L_0x007f:
            java.util.ArrayList r7 = new java.util.ArrayList
            java.util.Collection r8 = r9.values()
            r7.<init>(r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.social.share.ShareApiImpl.getItemWithUriListFromDB(android.content.Context, int, long):java.util.List");
    }

    private SharedItemWithUriList getSharedItemFromDB(Cursor cursor, String str) {
        boolean z;
        SharedItemWithUriList sharedItemWithUriList = new SharedItemWithUriList(str, cursor.getString(cursor.getColumnIndex("spaceId")), cursor.getString(cursor.getColumnIndex("owner")));
        sharedItemWithUriList.setLastModifierId(cursor.getString(cursor.getColumnIndex(SharedItemContract.Item.LAST_MODIFIER)));
        sharedItemWithUriList.setTitle(cursor.getString(cursor.getColumnIndex("title")));
        sharedItemWithUriList.setMemo(cursor.getString(cursor.getColumnIndex("memo")));
        sharedItemWithUriList.setCreatedTime(cursor.getLong(cursor.getColumnIndex("createTime")));
        sharedItemWithUriList.setModifiedTime(cursor.getLong(cursor.getColumnIndex("modifiedTime")));
        if (cursor.getInt(cursor.getColumnIndex("is_owned_by_me")) > 0) {
            z = true;
        } else {
            z = false;
        }
        sharedItemWithUriList.setOwnedByMe(z);
        sharedItemWithUriList.setCreator(cursor.getString(cursor.getColumnIndex("creator")));
        sharedItemWithUriList.setResourceId(cursor.getString(cursor.getColumnIndex("referred_resource_id")));
        sharedItemWithUriList.setMetaString(cursor.getString(cursor.getColumnIndex("meta_data")));
        return sharedItemWithUriList;
    }

    private long insertBulkItemToDB(Context context, String str, List<ShareApi.SharedItemRequest> list, int i2) {
        int i7;
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        int i8 = 0;
        for (ShareApi.SharedItemRequest requestToContentValues : list) {
            String str2 = str;
            arrayList.add(ShareApiUtil.getInstance(getTag(), getReference(), getAppId()).requestToContentValues(requestToContentValues, str2, i8, currentTimeMillis));
            str = str2;
            i8++;
        }
        long clearCallingIdentity = Binder.clearCallingIdentity();
        Uri bulkItemUri = RequestDBConstants.getBulkItemUri(getAppId(), i2);
        if (arrayList.size() > BULK_INSERT_CHUNK_SIZE) {
            i7 = 0;
            for (int i10 = 0; i10 < arrayList.size(); i10 += BULK_INSERT_CHUNK_SIZE) {
                List subList = arrayList.subList(i10, Math.min(arrayList.size() - i10, BULK_INSERT_CHUNK_SIZE) + i10);
                infoLog("bulkInsert : Enter A=[" + subList.size() + "] ");
                i7 += context.getContentResolver().bulkInsert(bulkItemUri, (ContentValues[]) subList.toArray(new ContentValues[subList.size()]));
            }
        } else {
            infoLog("bulkInsert : Enter B=[0] ");
            i7 = context.getContentResolver().bulkInsert(bulkItemUri, (ContentValues[]) arrayList.toArray(new ContentValues[arrayList.size()]));
        }
        infoLog("bulkInsert : Uri=[" + bulkItemUri + "] ");
        infoLog("bulkInsert : count=[" + arrayList.size() + "] ");
        infoLog("bulkInsert : size=[" + i7 + "] ");
        Binder.restoreCallingIdentity(clearCallingIdentity);
        return currentTimeMillis;
    }

    private long insertBulkItemWithUriListToDB(Context context, String str, List<ShareApi.SharedItemWithUriListRequest> list, int i2) {
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        int i7 = 0;
        for (ShareApi.SharedItemWithUriListRequest requestToContentValues : list) {
            String str2 = str;
            arrayList.addAll(ShareApiUtil.getInstance(getTag(), getReference(), getAppId()).requestToContentValues(requestToContentValues, str2, i7, currentTimeMillis));
            str = str2;
            i7++;
        }
        long clearCallingIdentity = Binder.clearCallingIdentity();
        context.getContentResolver().bulkInsert(RequestDBConstants.getBulkItemUri(getAppId(), i2), (ContentValues[]) arrayList.toArray(new ContentValues[arrayList.size()]));
        Binder.restoreCallingIdentity(clearCallingIdentity);
        return currentTimeMillis;
    }

    public void clearUnreadCount(String str, String str2, int i2) {
        if (checkInvalidFeatureIdAndAgentVersion(i2)) {
            infoLog("clearUnreadCount is not supported in " + i2);
        } else if (i2 == 501) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", i2);
                getSocialService().clearSpaceUnreadCountWithData(getAppId(), bundle, str);
            } catch (RemoteException | NotConnectedException | NullPointerException e) {
                secureLog(e);
            }
        } else if (!TextUtils.isEmpty(str2)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(BundleKey.EXTRA_CID, str2);
            getSocialService().clearSpaceUnreadCountWithData(getAppId(), bundle2, str);
        } else {
            getSocialService().clearSpaceUnreadCount(getAppId(), str);
        }
    }

    public String[] getEssentialServiceNames() {
        return new String[]{"SocialService"};
    }

    public QuotaResult getFamilyQuota(int i2) {
        infoLog("getFamilyQuota ");
        if (!isSupportedSemsAgentVersionBetween(CommonConstants.SupportedApiMinVersion.VERSION_10_8_9, CommonConstants.SupportedApiMinVersion.VERSION_11_0) && !isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_1_10)) {
            return new QuotaResult(new CommonResultStatus(-7), (Quota) null);
        }
        if (checkInvalidFeatureIdAndAgentVersion(i2)) {
            return new QuotaResult(new CommonResultStatus(-7), (Quota) null);
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return new QuotaResult(new CommonResultStatus(-1), (Quota) null);
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("featureId", i2);
            bundle.putString("appId", getAppId());
            return new QuotaResult(new CommonResultStatus(1), bundleToQuota(getSocialService().getFamilyQuotaWithData(bundle)));
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return new QuotaResult(new CommonResultStatus(-1), (Quota) null);
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return new QuotaResult(new CommonResultStatus(-8), (Quota) null);
        }
    }

    public QuotaResult getQuota(int i2) {
        infoLog("getQuota ");
        if (!isSupportedSemsAgentVersionBetween(CommonConstants.SupportedApiMinVersion.VERSION_10_8_6, CommonConstants.SupportedApiMinVersion.VERSION_11_0) && !isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_0_1)) {
            return new QuotaResult(new CommonResultStatus(-7), (Quota) null);
        }
        if (checkInvalidFeatureIdAndAgentVersion(i2)) {
            return new QuotaResult(new CommonResultStatus(-7), (Quota) null);
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return new QuotaResult(new CommonResultStatus(-1), (Quota) null);
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("featureId", i2);
            bundle.putString("appId", getAppId());
            return new QuotaResult(new CommonResultStatus(1), bundleToQuota(getSocialService().getQuotaWithData(bundle)));
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return new QuotaResult(new CommonResultStatus(-1), (Quota) null);
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return new QuotaResult(new CommonResultStatus(-8), (Quota) null);
        }
    }

    public String getTag() {
        return TAG;
    }

    public OriginalFileListResult getVerifiedOriginalFileList(List<String> list, int i2) {
        infoLog("getVerifiedOriginalFileList : count=[" + list.size() + "]");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_1_0)) {
            return new OriginalFileListResult(new CommonResultStatus(-7), (List<OriginalFile>) null);
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return new OriginalFileListResult(new CommonResultStatus(-1), (List<OriginalFile>) null);
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("featureId", i2);
            bundle.putString("appId", getAppId());
            bundle.putStringArrayList(BundleKey.HASH_LIST, new ArrayList(list));
            return new OriginalFileListResult(new CommonResultStatus(1), bundleToOriginalFileList(getSocialService().getVerifiedOriginalFileListWithData(bundle)));
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return new OriginalFileListResult(new CommonResultStatus(-1), (List<OriginalFile>) null);
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return new OriginalFileListResult(new CommonResultStatus(-8), (List<OriginalFile>) null);
        }
    }

    public ShareController requestBulkItemShare(Context context, String str, List<ShareApi.SharedItemRequest> list, ShareApi.ActionIntent actionIntent, ShareApi.NotificationMessage notificationMessage, int i2, Boolean bool, ShareApi.ShareUploadResultCallback<SharedItemListResult> shareUploadResultCallback) {
        final int i7 = i2;
        infoLog("requestBulkItemShare : spaceId=[" + str + "] ");
        if (i7 == 501 || i7 == 102) {
            infoLog("requestBulkItemShare is not supported in " + i7);
            return null;
        } else if (getAppId() == null) {
            infoLog("app id is null ");
            return null;
        } else if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_6_50)) {
            int i8 = i7;
            return requestShare(str, list, actionIntent, notificationMessage, i8, shareUploadResultCallback);
        } else {
            final long insertBulkItemToDB = insertBulkItemToDB(context, str, list, i7);
            final Context context2 = context;
            final ShareApi.ShareUploadResultCallback<SharedItemListResult> shareUploadResultCallback2 = shareUploadResultCallback;
            AnonymousClass16 r0 = new IShareBulkItemResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ShareApiImpl shareApiImpl = ShareApiImpl.this;
                    StringBuilder p6 = C0086a.p(j2, "requestShare onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    shareApiImpl.infoLog(p6.toString());
                    if (shareUploadResultCallback2 != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(j2);
                        ShareApiImpl.this.debugLog(C0212a.m("requestShare Error Message [", str, "]"));
                        shareUploadResultCallback2.onResult(new SharedItemListResult(new CommonResultStatus(convertErrorcode, str, Long.toString(j2)), (List<SharedItem>) null, (List<ShareApi.SharedItemRequest>) null));
                    }
                }

                public void onProgress(Bundle bundle) {
                    ShareApiImpl.this.debugLog("requestShare onProgress ");
                    ShareApi.ShareUploadResultCallback shareUploadResultCallback = shareUploadResultCallback2;
                    if (shareUploadResultCallback != null) {
                        shareUploadResultCallback.onProgress(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                    }
                }

                public void onSuccess() {
                    ShareApiImpl.this.infoLog("requestShare onSuccess ");
                    if (shareUploadResultCallback2 != null) {
                        List access$7900 = ShareApiImpl.this.getItemFromDB(context2, i7, insertBulkItemToDB);
                        ShareApiImpl shareApiImpl = ShareApiImpl.this;
                        shareApiImpl.infoLog("successItemList size = " + access$7900.size());
                        shareUploadResultCallback2.onResult(new SharedItemListResult(new CommonResultStatus(1), access$7900, new ArrayList()));
                    }
                }

                public void onUploadComplete(Bundle bundle) {
                    ShareApiImpl.this.debugLog("requestShare onUploadComplete ");
                    ShareApi.ShareUploadResultCallback shareUploadResultCallback = shareUploadResultCallback2;
                    if (shareUploadResultCallback != null) {
                        shareUploadResultCallback.onUploadComplete(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                    }
                }
            };
            try {
                ArrayList arrayList = new ArrayList();
                for (ShareApi.SharedItemRequest requestToBundle : list) {
                    arrayList.add(ShareApiUtil.getInstance(getTag(), getReference(), getAppId()).requestToBundle(requestToBundle));
                }
                Bundle bundle = new Bundle();
                if (actionIntent != null) {
                    bundle.putParcelable(BundleKey.PENDING_INTENTS, actionIntent.toBundle());
                }
                if (notificationMessage != null) {
                    bundle.putParcelable(BundleKey.NOTIFICATION_MESSAGES, notificationMessage.toBundle());
                }
                if (bool != null) {
                    bundle.putBoolean(BundleKey.USE_PRE_THUMBNAIL, bool.booleanValue());
                }
                bundle.putString("app_id", getAppId());
                bundle.putString(BundleKey.SPACE_ID, str);
                bundle.putString("request_id", String.valueOf(insertBulkItemToDB));
                return new ShareController(this.mApiPicker, getSocialService().requestBulkItemShare(bundle, r0));
            } catch (RemoteException | NotConnectedException | NullPointerException e) {
                secureLog(e);
                return null;
            }
        }
    }

    public int requestDeleteAllItemsFromTrashBin(String str, String str2, String str3, int i2, final ShareApi.ShareBaseResultCallback<ItemListResult> shareBaseResultCallback) {
        infoLog("requestDeleteAllItemsFromTrashBin : spaceId=[" + str2 + "]");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_1_10)) {
            return -1;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass34 r0 = new ITrashBinResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestDeleteAllItemsFromTrashBin onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (shareBaseResultCallback != null) {
                    shareBaseResultCallback.onResult(new ItemListResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), new ArrayList(), new ArrayList()));
                }
            }

            public void onSuccess(List<Bundle> list) {
                ShareApiImpl.this.infoLog("requestDeleteAllItemsFromTrashBin onSuccess ");
                if (shareBaseResultCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (Bundle next : list) {
                        String string = next.getString(BundleKey.SPACE_ID);
                        String string2 = next.getString(BundleKey.ITEM_ID);
                        if (next.getBoolean("result")) {
                            arrayList.add(new ItemListResult.SharedItemListSuccessResult(string, string2));
                        } else {
                            arrayList2.add(new ItemListResult.SharedItemListFailureResult(string, string2, Long.valueOf(next.getLong("error_code"))));
                        }
                    }
                    shareBaseResultCallback.onResult(new ItemListResult(new CommonResultStatus(1), arrayList, arrayList2));
                }
            }
        };
        try {
            Bundle bundle = new Bundle();
            bundle.putString("appId", getAppId());
            if (i2 != -1) {
                bundle.putInt("featureId", i2);
            }
            if (!TextUtils.isEmpty(str3)) {
                bundle.putString(BundleKey.EXTRA_CID, str3);
            }
            bundle.putString("groupId", str);
            bundle.putString("spaceId", str2);
            getSocialService().requestDeleteAllItemsFromTrashBinWithData(bundle, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestDeleteItemListFromTrashBin(String str, String str2, List<String> list, String str3, int i2, final ShareApi.ShareBaseResultCallback<ItemListResult> shareBaseResultCallback) {
        StringBuilder k = j.k("requestDeleteItemListFromTrashBin : spaceId=[", str2, "], itemId size=");
        k.append(list.size());
        infoLog(k.toString());
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_1_10)) {
            return -1;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass33 r0 = new ITrashBinResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestDeleteItemListFromTrashBin onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (shareBaseResultCallback != null) {
                    shareBaseResultCallback.onResult(new ItemListResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), new ArrayList(), new ArrayList()));
                }
            }

            public void onSuccess(List<Bundle> list) {
                ShareApiImpl.this.infoLog("requestDeleteItemListFromTrashBin onSuccess ");
                if (shareBaseResultCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (Bundle next : list) {
                        String string = next.getString(BundleKey.SPACE_ID);
                        String string2 = next.getString(BundleKey.ITEM_ID);
                        if (next.getBoolean("result")) {
                            arrayList.add(new ItemListResult.SharedItemListSuccessResult(string, string2));
                        } else {
                            arrayList2.add(new ItemListResult.SharedItemListFailureResult(string, string2, Long.valueOf(next.getLong("error_code"))));
                        }
                    }
                    shareBaseResultCallback.onResult(new ItemListResult(new CommonResultStatus(1), arrayList, arrayList2));
                }
            }
        };
        try {
            Bundle bundle = new Bundle();
            bundle.putString("appId", getAppId());
            if (i2 != -1) {
                bundle.putInt("featureId", i2);
            }
            if (!TextUtils.isEmpty(str3)) {
                bundle.putString(BundleKey.EXTRA_CID, str3);
            }
            bundle.putString("groupId", str);
            bundle.putString("spaceId", str2);
            bundle.putStringArrayList(BundleKey.itemIdList, new ArrayList(list));
            getSocialService().requestDeleteItemListFromTrashBinWithData(bundle, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestItemThumbnailDownload(String str, String str2, String str3, String str4, String str5, int i2, ShareApi.ShareBaseResultCallback<DownloadImageResult> shareBaseResultCallback) {
        StringBuilder q = C0086a.q("requestItemThumbnailDownload. groupId=[", str, "] spaceId=[", str2, "] itemId=[");
        q.append(str3);
        q.append("] ");
        infoLog(q.toString());
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_12_1)) {
            return -7;
        }
        if (i2 == 501) {
            infoLog("requestItemThumbnailDownload is not supported in " + i2);
            return -1;
        }
        String str6 = str5;
        String str7 = str4;
        String str8 = str3;
        String str9 = str2;
        return downloadThumbnail(str, str9, str8, str7, str6, shareBaseResultCallback);
    }

    public int requestMoveItemListToTrashBin(String str, String str2, List<String> list, String str3, int i2, final ShareApi.ShareBaseResultCallback<ItemListResult> shareBaseResultCallback) {
        StringBuilder k = j.k("requestMoveItemListToTrashBin : spaceId=[", str2, "], itemId size=");
        k.append(list.size());
        infoLog(k.toString());
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_1_10)) {
            return -1;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass31 r0 = new ITrashBinResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestMoveItemListToTrashBin onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (shareBaseResultCallback != null) {
                    shareBaseResultCallback.onResult(new ItemListResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), new ArrayList(), new ArrayList()));
                }
            }

            public void onSuccess(List<Bundle> list) {
                ShareApiImpl.this.infoLog("requestMoveItemListToTrashBin onSuccess ");
                if (shareBaseResultCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (Bundle next : list) {
                        String string = next.getString(BundleKey.SPACE_ID);
                        String string2 = next.getString(BundleKey.ITEM_ID);
                        if (next.getBoolean("result")) {
                            arrayList.add(new ItemListResult.SharedItemListSuccessResult(string, string2));
                        } else {
                            arrayList2.add(new ItemListResult.SharedItemListFailureResult(string, string2, Long.valueOf(next.getLong("error_code"))));
                        }
                    }
                    shareBaseResultCallback.onResult(new ItemListResult(new CommonResultStatus(1), arrayList, arrayList2));
                }
            }
        };
        try {
            Bundle bundle = new Bundle();
            bundle.putString("appId", getAppId());
            if (i2 != -1) {
                bundle.putInt("featureId", i2);
            }
            if (!TextUtils.isEmpty(str3)) {
                bundle.putString(BundleKey.EXTRA_CID, str3);
            }
            bundle.putString("groupId", str);
            bundle.putString("spaceId", str2);
            bundle.putStringArrayList(BundleKey.itemIdList, new ArrayList(list));
            getSocialService().requestMoveItemListToTrashBinWithData(bundle, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestRestoreItemListFromTrashBin(String str, String str2, List<String> list, String str3, int i2, final ShareApi.ShareBaseResultCallback<ItemListResult> shareBaseResultCallback) {
        StringBuilder k = j.k("requestRestoreItemListFromTrashBin : spaceId=[", str2, "], itemId size=");
        k.append(list.size());
        infoLog(k.toString());
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_1_10)) {
            return -1;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass32 r0 = new ITrashBinResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestRestoreItemListFromTrashBin onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (shareBaseResultCallback != null) {
                    shareBaseResultCallback.onResult(new ItemListResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), new ArrayList(), new ArrayList()));
                }
            }

            public void onSuccess(List<Bundle> list) {
                ShareApiImpl.this.infoLog("requestRestoreItemListFromTrashBin onSuccess ");
                if (shareBaseResultCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (Bundle next : list) {
                        String string = next.getString(BundleKey.SPACE_ID);
                        String string2 = next.getString(BundleKey.ITEM_ID);
                        if (next.getBoolean("result")) {
                            arrayList.add(new ItemListResult.SharedItemListSuccessResult(string, string2));
                        } else {
                            arrayList2.add(new ItemListResult.SharedItemListFailureResult(string, string2, Long.valueOf(next.getLong("error_code"))));
                        }
                    }
                    shareBaseResultCallback.onResult(new ItemListResult(new CommonResultStatus(1), arrayList, arrayList2));
                }
            }
        };
        try {
            Bundle bundle = new Bundle();
            bundle.putString("appId", getAppId());
            if (i2 != -1) {
                bundle.putInt("featureId", i2);
            }
            if (!TextUtils.isEmpty(str3)) {
                bundle.putString(BundleKey.EXTRA_CID, str3);
            }
            bundle.putString("groupId", str);
            bundle.putString("spaceId", str2);
            bundle.putStringArrayList(BundleKey.itemIdList, new ArrayList(list));
            getSocialService().requestRestoreItemListFromTrashBinWithData(bundle, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public ShareController requestShare(String str, List<ShareApi.SharedItemWithUriListRequest> list, String str2, int i2, final ShareApi.ShareUploadResultCallback<SharedItemListWithUriListResult> shareUploadResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        String str3;
        infoLog("requestShare : spaceId=[" + str + "] ");
        if (checkInvalidFeatureIdAndAgentVersion(i2)) {
            return null;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return null;
        }
        AnonymousClass14 r72 = new IShareResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestShare onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (shareUploadResultCallback != null) {
                    int convertErrorcode = ErrorCodeConvertor.convertErrorcode(j2);
                    ShareApiImpl.this.debugLog(C0212a.m("requestShare Error Message [", str, "]"));
                    shareUploadResultCallback.onResult(new SharedItemListWithUriListResult(new CommonResultStatus(convertErrorcode, str, Long.toString(j2)), (List<SharedItemWithUriList>) null, (List<ShareApi.SharedItemWithUriListRequest>) null));
                }
            }

            public void onProgress(Bundle bundle) {
                ShareApiImpl.this.debugLog("requestShare onProgress ");
                ShareApi.ShareUploadResultCallback shareUploadResultCallback = shareUploadResultCallback;
                if (shareUploadResultCallback != null) {
                    shareUploadResultCallback.onProgress(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                }
            }

            public void onSuccess(List<Bundle> list, List<Bundle> list2) {
                ShareApiImpl.this.infoLog("requestShare onSuccess ");
                if (shareUploadResultCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    for (Bundle sharedItemWithUriList : list) {
                        arrayList.add(new SharedItemWithUriList(sharedItemWithUriList));
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (Bundle createShareFailedItemWithUriListResult : list2) {
                        arrayList2.add(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).createShareFailedItemWithUriListResult(createShareFailedItemWithUriListResult));
                    }
                    shareUploadResultCallback.onResult(new SharedItemListWithUriListResult(new CommonResultStatus(1), arrayList, arrayList2));
                }
            }

            public void onUploadComplete(Bundle bundle) {
                ShareApiImpl.this.debugLog("requestShare onUploadComplete ");
                ShareApi.ShareUploadResultCallback shareUploadResultCallback = shareUploadResultCallback;
                if (shareUploadResultCallback != null) {
                    shareUploadResultCallback.onUploadComplete(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                }
            }
        };
        try {
            ArrayList arrayList = new ArrayList();
            for (ShareApi.SharedItemWithUriListRequest requestToBundle : list) {
                arrayList.add(ShareApiUtil.getInstance(getTag(), getReference(), getAppId()).requestToBundle(requestToBundle));
            }
            if (i2 == 501) {
                Bundle bundle2 = new Bundle();
                bundle2.putInt("featureId", i2);
                str3 = getSocialService().requestShareWithItemFileListWithData(getAppId(), str, arrayList, bundle2, r72, pendingIntent, bundle);
            } else if (!TextUtils.isEmpty(str2)) {
                Bundle bundle3 = new Bundle();
                bundle3.putString(BundleKey.EXTRA_CID, str2);
                str3 = getSocialService().requestShareWithItemFileListWithData(getAppId(), str, arrayList, bundle3, r72, pendingIntent, bundle);
            } else {
                str3 = getSocialService().requestShareWithItemFileList(getAppId(), str, arrayList, r72, pendingIntent, bundle);
            }
            return new ShareController(this.mApiPicker, str3);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        }
    }

    public int requestSharedContentDownload(String str, List<String> list, int i2, final ShareApi.ContentDownloadingResultCallback contentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle, String str2) {
        infoLog("requestSharedContentDownload spaceId=[" + str + "] itemIdList=[" + list + "] ");
        if (i2 == 501) {
            infoLog("requestSharedContentDownload is not supported in " + i2);
            return -1;
        } else if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        } else {
            AnonymousClass13 r62 = new IContentDownloadingResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ShareApiImpl shareApiImpl = ShareApiImpl.this;
                    StringBuilder p6 = C0086a.p(j2, "requestSharedContentDownload onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    shareApiImpl.infoLog(p6.toString());
                    if (contentDownloadingResultCallback != null) {
                        contentDownloadingResultCallback.onResult(new ContentDownloadResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (ArrayList<ContentDownloadResult.DownloadedContent>) null, (ArrayList<ContentDownloadResult.DownloadedContent>) null));
                    }
                }

                public void onProgress(Bundle bundle) {
                    ShareApiImpl.this.debugLog("requestSharedContentDownload onProgress ");
                    ShareApi.ContentDownloadingResultCallback contentDownloadingResultCallback = contentDownloadingResultCallback;
                    if (contentDownloadingResultCallback != null) {
                        contentDownloadingResultCallback.onProgress(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToDownloadSnapShot(bundle));
                    }
                }

                public void onSuccess(List<Bundle> list, List<Bundle> list2) {
                    ShareApiImpl.this.infoLog("requestSharedContentDownload onSuccess ");
                    if (contentDownloadingResultCallback == null) {
                        return;
                    }
                    if (!list.isEmpty() || !list2.isEmpty()) {
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        for (Bundle bundleToDownloadContent : list) {
                            arrayList.add(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToDownloadContent(bundleToDownloadContent));
                        }
                        for (Bundle bundleToDownloadContent2 : list2) {
                            arrayList2.add(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToDownloadContent(bundleToDownloadContent2));
                        }
                        contentDownloadingResultCallback.onResult(new ContentDownloadResult(new CommonResultStatus(1), arrayList, arrayList2));
                        return;
                    }
                    ShareApiImpl.this.debugLog("requestSharedContentDownload bundle is empty!!");
                    contentDownloadingResultCallback.onResult(new ContentDownloadResult(new CommonResultStatus(1), (ArrayList<ContentDownloadResult.DownloadedContent>) null, (ArrayList<ContentDownloadResult.DownloadedContent>) null));
                }
            };
            try {
                if (TextUtils.isEmpty(str2)) {
                    getSocialService().requestOriginalSharedContentsDownload(getAppId(), str, (String[]) list.toArray(new String[0]), r62, pendingIntent, bundle);
                    return 1;
                }
                getSocialService().requestOriginalSharedContentsDownloadWithPath(getAppId(), str, (String[]) list.toArray(new String[0]), r62, pendingIntent, bundle, str2);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        }
    }

    public int requestSharedContentDownloadToHiddenFolder(String str, List<String> list, int i2, final ShareApi.ContentDownloadingResultCallback contentDownloadingResultCallback) {
        infoLog("requestSharedContentDownloadToHiddenFolder spaceId=[" + str + "] itemIdList=[" + list + "] ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_12_1)) {
            return -7;
        }
        if (i2 == 501) {
            infoLog("requestSharedContentDownloadToHiddenFolder is not supported in " + i2);
            return -1;
        } else if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        } else {
            AnonymousClass12 r62 = new IContentDownloadingResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ShareApiImpl shareApiImpl = ShareApiImpl.this;
                    StringBuilder p6 = C0086a.p(j2, "requestSharedContentDownload onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    shareApiImpl.infoLog(p6.toString());
                    if (contentDownloadingResultCallback != null) {
                        contentDownloadingResultCallback.onResult(new ContentDownloadResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (ArrayList<ContentDownloadResult.DownloadedContent>) null, (ArrayList<ContentDownloadResult.DownloadedContent>) null));
                    }
                }

                public void onProgress(Bundle bundle) {
                    ShareApiImpl.this.debugLog("requestSharedContentDownload onProgress ");
                    ShareApi.ContentDownloadingResultCallback contentDownloadingResultCallback = contentDownloadingResultCallback;
                    if (contentDownloadingResultCallback != null) {
                        contentDownloadingResultCallback.onProgress(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToDownloadSnapShot(bundle));
                    }
                }

                public void onSuccess(List<Bundle> list, List<Bundle> list2) {
                    ShareApiImpl.this.infoLog("requestSharedContentDownloadToHiddenFolder onSuccess ");
                    if (contentDownloadingResultCallback == null) {
                        return;
                    }
                    if (!list.isEmpty() || !list2.isEmpty()) {
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        for (Bundle bundleToDownloadContent : list) {
                            arrayList.add(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToDownloadContent(bundleToDownloadContent));
                        }
                        for (Bundle bundleToDownloadContent2 : list2) {
                            arrayList2.add(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToDownloadContent(bundleToDownloadContent2));
                        }
                        contentDownloadingResultCallback.onResult(new ContentDownloadResult(new CommonResultStatus(1), arrayList, arrayList2));
                        return;
                    }
                    ShareApiImpl.this.debugLog("requestSharedContentDownloadToHiddenFolder bundle is empty!!");
                    contentDownloadingResultCallback.onResult(new ContentDownloadResult(new CommonResultStatus(1), (ArrayList<ContentDownloadResult.DownloadedContent>) null, (ArrayList<ContentDownloadResult.DownloadedContent>) null));
                }
            };
            try {
                Bundle bundle = new Bundle();
                bundle.putString("appId", getAppId());
                bundle.putString("spaceId", str);
                bundle.putStringArrayList(BundleKey.itemIdList, new ArrayList(list));
                getSocialService().requestOriginalSharedContentsDownloadToHiddenFolder(bundle, r62);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        }
    }

    public int requestSharedItem(String str, String str2, String str3, int i2, final ShareApi.SharedItemResultCallback sharedItemResultCallback) {
        infoLog(j.d("requestSpace : spaceId=[", str2, "], itemId=[", str3, "] "));
        if (i2 == 501) {
            infoLog("requestSharedItem is not supported in " + i2);
            return -1;
        } else if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_4_1) || checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        } else {
            if (TextUtils.isEmpty(getAppId())) {
                infoLog("app id is empty ");
                return -1;
            }
            AnonymousClass29 r72 = new ISharedItemResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ShareApiImpl shareApiImpl = ShareApiImpl.this;
                    StringBuilder p6 = C0086a.p(j2, "requestSpace onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    shareApiImpl.infoLog(p6.toString());
                    if (sharedItemResultCallback != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(j2);
                        ShareApiImpl.this.debugLog(C0212a.m("requestSpace Error Message [", str, "]"));
                        sharedItemResultCallback.onResult(new SharedItemResult(new CommonResultStatus(convertErrorcode, str, Long.toString(j2)), (SharedItem) null));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    ShareApiImpl.this.infoLog("requestSpace onSuccess ");
                    ShareApi.SharedItemResultCallback sharedItemResultCallback = sharedItemResultCallback;
                    if (sharedItemResultCallback != null) {
                        sharedItemResultCallback.onResult(new SharedItemResult(new CommonResultStatus(1), ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).createSharedItemResult(bundle)));
                    }
                }
            };
            try {
                if (TextUtils.isEmpty(str)) {
                    getSocialService().requestSharedItem(getAppId(), str2, str3, r72);
                    return 1;
                }
                getSocialService().requestSharedItemWithGroupId(getAppId(), str, str2, str3, r72);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        }
    }

    public int requestSharedItemDeletion(String str, List<String> list, String str2, int i2, final ShareApi.ShareBaseResultCallback<ItemListResult> shareBaseResultCallback) {
        StringBuilder k = j.k("requestSharedItemDeletion : spaceId=[", str, "], itemId size=");
        k.append(list.size());
        infoLog(k.toString());
        if (checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass11 r72 = new ISharedItemListDeletionResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSharedItemDeletion onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (shareBaseResultCallback != null) {
                    shareBaseResultCallback.onResult(new ItemListResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), new ArrayList(), new ArrayList()));
                }
            }

            public void onSuccess(List<Bundle> list) {
                ShareApiImpl.this.infoLog("requestSharedItemDeletion onSuccess ");
                if (shareBaseResultCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (Bundle next : list) {
                        String string = next.getString(BundleKey.SPACE_ID);
                        String string2 = next.getString(BundleKey.ITEM_ID);
                        if (next.getBoolean("result")) {
                            arrayList.add(new ItemListResult.SharedItemListSuccessResult(string, string2));
                        } else {
                            arrayList2.add(new ItemListResult.SharedItemListFailureResult(string, string2, Long.valueOf(next.getLong("error_code"))));
                        }
                    }
                    shareBaseResultCallback.onResult(new ItemListResult(new CommonResultStatus(1), arrayList, arrayList2));
                }
            }
        };
        if (i2 == 501) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", i2);
                getSocialService().requestSharedItemListDeletionWithData(getAppId(), str, list, bundle, r72);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        } else {
            String str3 = str;
            List<String> list2 = list;
            if (!TextUtils.isEmpty(str2)) {
                Bundle bundle2 = new Bundle();
                bundle2.putString(BundleKey.EXTRA_CID, str2);
                getSocialService().requestSharedItemListDeletionWithData(getAppId(), str3, list2, bundle2, r72);
                return 1;
            }
            getSocialService().requestSharedItemListDeletion(getAppId(), str3, list2, r72);
            return 1;
        }
    }

    public int requestSharedItemList(String str, String str2, String str3, String str4, int i2, final ShareApi.SharedItemListWithContentListResultCallback sharedItemListWithContentListResultCallback) {
        StringBuilder q = C0086a.q("requestSharedItemList : groupId=[", str, "], spaceId=[", str2, "], resolution=[");
        q.append(str3);
        q.append("] ");
        infoLog(q.toString());
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_4_1) || checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass27 r82 = new ISharedItemListResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSpace onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (sharedItemListWithContentListResultCallback != null) {
                    int convertErrorcode = ErrorCodeConvertor.convertErrorcode(j2);
                    ShareApiImpl.this.debugLog(C0212a.m("requestSpace Error Message [", str, "]"));
                    sharedItemListWithContentListResultCallback.onResult(new SharedItemListWithContentListResult(new CommonResultStatus(convertErrorcode, str, Long.toString(j2)), (List<SharedItemWithUriList>) null));
                }
            }

            public void onSuccess(List<Bundle> list) {
                ShareApiImpl.this.infoLog("requestSharedItemList(with content list) onSuccess");
                if (sharedItemListWithContentListResultCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    for (Bundle sharedItemWithUriList : list) {
                        arrayList.add(new SharedItemWithUriList(sharedItemWithUriList));
                    }
                    sharedItemListWithContentListResultCallback.onResult(new SharedItemListWithContentListResult(new CommonResultStatus(1), arrayList));
                }
            }
        };
        if (i2 == 501) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", i2);
                getSocialService().requestSharedItemListWithFileListWithData(getAppId(), str, str2, str3, bundle, r82);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        } else {
            String str5 = str;
            String str6 = str2;
            String str7 = str3;
            if (!TextUtils.isEmpty(str4)) {
                Bundle bundle2 = new Bundle();
                bundle2.putString(BundleKey.EXTRA_CID, str4);
                getSocialService().requestSharedItemListWithFileListWithData(getAppId(), str5, str6, str7, bundle2, r82);
                return 1;
            }
            getSocialService().requestSharedItemListWithFileList(getAppId(), str5, str6, str7, r82);
            return 1;
        }
    }

    public int requestSharedItemListInTrashBin(String str, String str2, String str3, String str4, int i2, final ShareApi.ShareBaseResultCallback<TrashedItemListResult> shareBaseResultCallback) {
        StringBuilder q = C0086a.q("requestSharedItemListInTrashBin : groupId=[", str, "], spaceId=[", str2, "], resolution=[");
        q.append(str4);
        q.append("] ");
        infoLog(q.toString());
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_1_10)) {
            return -1;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass35 r0 = new ISharedItemListResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSharedItemListInTrashBin onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (shareBaseResultCallback != null) {
                    shareBaseResultCallback.onResult(new TrashedItemListResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), new ArrayList(), new ArrayList()));
                }
            }

            public void onSuccess(List<Bundle> list) {
                ShareApiImpl.this.infoLog("requestSharedItemListInTrashBin onSuccess ");
                if (shareBaseResultCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    for (Bundle createTrashedItemResult : list) {
                        arrayList.add(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).createTrashedItemResult(createTrashedItemResult));
                    }
                    shareBaseResultCallback.onResult(new TrashedItemListResult(new CommonResultStatus(1), arrayList, (List<ShareApi.SharedItemRequest>) null));
                }
            }
        };
        try {
            Bundle bundle = new Bundle();
            bundle.putString("appId", getAppId());
            if (i2 != -1) {
                bundle.putInt("featureId", i2);
            }
            if (!TextUtils.isEmpty(str3)) {
                bundle.putString(BundleKey.EXTRA_CID, str3);
            }
            bundle.putString("groupId", str);
            bundle.putString("spaceId", str2);
            if (str4 != null) {
                bundle.putString(BundleKey.THUMBNAIL_RESOLUTION, str4);
            }
            getSocialService().requestSharedItemListInTrashBinWithData(bundle, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestSharedItemSync(String str, String str2, String str3, int i2, final ShareApi.SharedItemSyncResultCallback sharedItemSyncResultCallback) {
        infoLog("requestSharedItemSync : spaceId=[" + str + "] ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_1) || checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass10 r0 = new IShareSyncResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSharedItemSync onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (sharedItemSyncResultCallback != null) {
                    sharedItemSyncResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                }
            }

            public void onSuccess() {
                ShareApiImpl.this.infoLog("requestSharedItemSync onSuccess ");
                ShareApi.SharedItemSyncResultCallback sharedItemSyncResultCallback = sharedItemSyncResultCallback;
                if (sharedItemSyncResultCallback != null) {
                    sharedItemSyncResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                }
            }
        };
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.SPACE_ID, str);
        bundle.putString(BundleKey.THUMBNAIL_RESOLUTION, str2);
        if (i2 == 501) {
            try {
                Bundle bundle2 = new Bundle();
                bundle2.putInt("featureId", i2);
                getSocialService().requestSharedItemSyncWithResolutionWithData(getAppId(), bundle, bundle2, r0);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        } else if (!TextUtils.isEmpty(str3)) {
            Bundle bundle3 = new Bundle();
            bundle3.putString(BundleKey.EXTRA_CID, str3);
            getSocialService().requestSharedItemSyncWithResolutionWithData(getAppId(), bundle, bundle3, r0);
            return 1;
        } else {
            getSocialService().requestSharedItemSyncWithResolution(getAppId(), bundle, r0);
            return 1;
        }
    }

    public ShareController requestSharedItemUpdate(String str, String str2, ShareApi.SharedItemWithUriListRequest sharedItemWithUriListRequest, int i2, final ShareApi.SharedItemWithContentListResultCallback sharedItemWithContentListResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        infoLog("requestSpace : spaceId=[" + str + "] ");
        if (i2 == 501) {
            infoLog("requestSharedItemUpdate is not supported in " + i2);
            return null;
        } else if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return null;
        } else {
            try {
                return new ShareController(this.mApiPicker, getSocialService().requestShareUpdateWithUriList(getAppId(), str, str2, ShareApiUtil.getInstance(getTag(), getReference(), getAppId()).requestToBundle(sharedItemWithUriListRequest), new IShareResultWithFileListCallback.Stub() {
                    public void onFailure(long j2, String str) {
                        ShareApiImpl shareApiImpl = ShareApiImpl.this;
                        StringBuilder p6 = C0086a.p(j2, "requestSpace onFailure : code=[", "], message=[", str);
                        p6.append("] ");
                        shareApiImpl.infoLog(p6.toString());
                        if (sharedItemWithContentListResultCallback != null) {
                            int convertErrorcode = ErrorCodeConvertor.convertErrorcode(j2);
                            ShareApiImpl.this.debugLog(C0212a.m("requestSpace Error Message [", str, "]"));
                            sharedItemWithContentListResultCallback.onResult(new SharedItemWithUriListResult(new CommonResultStatus(convertErrorcode, str, Long.toString(j2)), (SharedItemWithUriList) null));
                        }
                    }

                    public void onProgress(Bundle bundle) {
                        ShareApiImpl.this.debugLog("requestShare onProgress ");
                        ShareApi.SharedItemWithContentListResultCallback sharedItemWithContentListResultCallback = sharedItemWithContentListResultCallback;
                        if (sharedItemWithContentListResultCallback != null) {
                            sharedItemWithContentListResultCallback.onProgress(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                        }
                    }

                    public void onSuccess(Bundle bundle) {
                        ShareApiImpl.this.infoLog("requestSpace onSuccess ");
                        ShareApi.SharedItemWithContentListResultCallback sharedItemWithContentListResultCallback = sharedItemWithContentListResultCallback;
                        if (sharedItemWithContentListResultCallback != null) {
                            sharedItemWithContentListResultCallback.onResult(new SharedItemWithUriListResult(new CommonResultStatus(1), new SharedItemWithUriList(bundle)));
                        }
                    }

                    public void onUploadComplete(Bundle bundle) {
                        ShareApiImpl.this.debugLog("requestShare onUploadComplete ");
                        ShareApi.SharedItemWithContentListResultCallback sharedItemWithContentListResultCallback = sharedItemWithContentListResultCallback;
                        if (sharedItemWithContentListResultCallback != null) {
                            sharedItemWithContentListResultCallback.onUploadComplete(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                        }
                    }
                }, pendingIntent, bundle));
            } catch (RemoteException | NotConnectedException | NullPointerException e) {
                secureLog(e);
                return null;
            }
        }
    }

    public ShareController requestSharedItemWithUriListUpdate(String str, List<ShareApi.SharedItemUpdateWithUriListRequest> list, String str2, int i2, final ShareApi.ShareUploadResultCallback<SharedItemListWithUriListResult> shareUploadResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        String str3;
        infoLog("requestSpace : spaceId=[" + str + "] ");
        if (checkInvalidFeatureIdAndAgentVersion(i2)) {
            return null;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return null;
        }
        AnonymousClass15 r72 = new IShareResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSharedItemUpdate onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (shareUploadResultCallback != null) {
                    int convertErrorcode = ErrorCodeConvertor.convertErrorcode(j2);
                    ShareApiImpl.this.debugLog(C0212a.m("requestSharedItemUpdate Error Message [", str, "]"));
                    shareUploadResultCallback.onResult(new SharedItemListWithUriListResult(new CommonResultStatus(convertErrorcode, str, Long.toString(j2)), (List<SharedItemWithUriList>) null, (List<ShareApi.SharedItemWithUriListRequest>) null));
                }
            }

            public void onProgress(Bundle bundle) {
                ShareApiImpl.this.debugLog("requestSharedItemUpdate onProgress ");
                ShareApi.ShareUploadResultCallback shareUploadResultCallback = shareUploadResultCallback;
                if (shareUploadResultCallback != null) {
                    shareUploadResultCallback.onProgress(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                }
            }

            public void onSuccess(List<Bundle> list, List<Bundle> list2) {
                ShareApiImpl.this.infoLog("requestSharedItemUpdate onSuccess ");
                if (shareUploadResultCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    for (Bundle sharedItemWithUriList : list) {
                        arrayList.add(new SharedItemWithUriList(sharedItemWithUriList));
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (Bundle createShareFailedItemWithUriListResult : list2) {
                        arrayList2.add(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).createShareFailedItemWithUriListResult(createShareFailedItemWithUriListResult));
                    }
                    shareUploadResultCallback.onResult(new SharedItemListWithUriListResult(new CommonResultStatus(1), arrayList, arrayList2));
                }
            }

            public void onUploadComplete(Bundle bundle) {
                ShareApiImpl.this.debugLog("requestSharedItemUpdate onUploadComplete ");
                ShareApi.ShareUploadResultCallback shareUploadResultCallback = shareUploadResultCallback;
                if (shareUploadResultCallback != null) {
                    shareUploadResultCallback.onUploadComplete(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                }
            }
        };
        ArrayList arrayList = new ArrayList();
        for (ShareApi.SharedItemUpdateWithUriListRequest requestToBundle : list) {
            arrayList.add(ShareApiUtil.getInstance(getTag(), getReference(), getAppId()).requestToBundle(requestToBundle));
        }
        if (i2 == 501) {
            try {
                Bundle bundle2 = new Bundle();
                bundle2.putInt("featureId", i2);
                str3 = getSocialService().requestShareListUpdateWithItemFileListWithData(getAppId(), str, arrayList, bundle2, r72, pendingIntent, bundle);
            } catch (RemoteException | NotConnectedException | NullPointerException e) {
                secureLog(e);
                return null;
            }
        } else if (!TextUtils.isEmpty(str2)) {
            Bundle bundle3 = new Bundle();
            bundle3.putString(BundleKey.EXTRA_CID, str2);
            str3 = getSocialService().requestShareListUpdateWithItemFileListWithData(getAppId(), str, arrayList, bundle3, r72, pendingIntent, bundle);
        } else {
            str3 = getSocialService().requestShareListUpdateWithItemFileList(getAppId(), str, arrayList, r72, pendingIntent, bundle);
        }
        return new ShareController(this.mApiPicker, str3);
    }

    public int requestSpace(String str, String str2, int i2, final ShareApi.SpaceResultCallback spaceResultCallback) {
        infoLog("requestSpace : spaceId=[" + str + "] ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_4_1) || checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass5 r0 = new ISpaceResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSpace onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (spaceResultCallback != null) {
                    int convertErrorcode = ErrorCodeConvertor.convertErrorcode(j2);
                    ShareApiImpl.this.debugLog(C0212a.m("requestSpace Error Message [", str, "]"));
                    spaceResultCallback.onResult(new SpaceResult(new CommonResultStatus(convertErrorcode, str, Long.toString(j2)), (Space) null));
                }
            }

            public void onSuccess(Bundle bundle) {
                ShareApiImpl.this.infoLog("requestSpace onSuccess ");
                ShareApi.SpaceResultCallback spaceResultCallback = spaceResultCallback;
                if (spaceResultCallback != null) {
                    spaceResultCallback.onResult(new SpaceResult(new CommonResultStatus(1), ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).createSpaceResult(bundle)));
                }
            }
        };
        if (i2 == 501) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", i2);
                getSocialService().requestSpaceWithData(getAppId(), str, bundle, r0);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        } else if (!TextUtils.isEmpty(str2)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(BundleKey.EXTRA_CID, str2);
            getSocialService().requestSpaceWithData(getAppId(), str, bundle2, r0);
            return 1;
        } else {
            getSocialService().requestSpace(getAppId(), str, r0);
            return 1;
        }
    }

    public int requestSpaceCoverImageDownload(String str, int i2, final ShareApi.ImageDownloadingResultCallback imageDownloadingResultCallback) {
        infoLog("requestOriginalSpaceImageDownload spaceId=[" + str + "] ");
        if (checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        try {
            getSocialService().requestOriginalSpaceImageDownload(getAppId(), str, new ISpaceCoverImageDownloadingResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ShareApiImpl shareApiImpl = ShareApiImpl.this;
                    StringBuilder p6 = C0086a.p(j2, "requestOriginalSpaceImageDownload onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    shareApiImpl.infoLog(p6.toString());
                    if (imageDownloadingResultCallback != null) {
                        imageDownloadingResultCallback.onResult(new SpaceImageDownloadResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (SpaceImageDownloadResult.DownloadedImage) null));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    ShareApiImpl.this.infoLog("requestOriginalSpaceImageDownload onSuccess ");
                    if (imageDownloadingResultCallback != null) {
                        Uri uri = null;
                        String string = bundle.getString(BundleKey.SPACE_ID, (String) null);
                        String string2 = bundle.getString(BundleKey.DOWNLOADED_URI, (String) null);
                        if (string2 != null) {
                            uri = Uri.parse(string2);
                        }
                        SpaceImageDownloadResult.DownloadedImage downloadedImage = new SpaceImageDownloadResult.DownloadedImage(string, uri);
                        ShareApiImpl.this.debugLog(j.d("- space_id=[", string, "], downloaded_uri=[", string2, "] "));
                        imageDownloadingResultCallback.onResult(new SpaceImageDownloadResult(new CommonResultStatus(1), downloadedImage));
                    }
                }
            });
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestSpaceCreation(String str, ShareApi.SpaceRequest spaceRequest, String str2, int i2, final ShareApi.SpaceResultCallback spaceResultCallback) {
        if (checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass3 r72 = new ISpaceResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSpaceCreation onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (spaceResultCallback != null) {
                    spaceResultCallback.onResult(new SpaceResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (Space) null));
                }
            }

            public void onSuccess(Bundle bundle) {
                ShareApiImpl.this.infoLog("requestSpaceCreation onSuccess ");
                ShareApi.SpaceResultCallback spaceResultCallback = spaceResultCallback;
                if (spaceResultCallback != null) {
                    spaceResultCallback.onResult(new SpaceResult(new CommonResultStatus(1), ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).createSpaceResult(bundle)));
                }
            }
        };
        try {
            Bundle requestToBundle = ShareApiUtil.getInstance(getTag(), getReference(), getAppId()).requestToBundle(spaceRequest, str);
            if (i2 == 501) {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", i2);
                getSocialService().requestSpaceCreationWithData(getAppId(), str, requestToBundle, bundle, r72);
                return 1;
            }
            String str3 = str;
            if (!TextUtils.isEmpty(str2)) {
                Bundle bundle2 = new Bundle();
                bundle2.putString(BundleKey.EXTRA_CID, str2);
                getSocialService().requestSpaceCreationWithData(getAppId(), str3, requestToBundle, bundle2, r72);
                return 1;
            }
            getSocialService().requestSpaceCreation(getAppId(), str3, requestToBundle, r72);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestSpaceDeletion(String str, String str2, int i2, final ShareApi.SpaceDeletionResultCallback spaceDeletionResultCallback) {
        infoLog("requestSpaceDeletion : spaceId=[" + str + "] ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_4_1) || checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass7 r0 = new ISpaceDeletionResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSpaceDeletion onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (spaceDeletionResultCallback != null) {
                    spaceDeletionResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                }
            }

            public void onSuccess() {
                ShareApiImpl.this.infoLog("requestSpaceDeletion onSuccess ");
                ShareApi.SpaceDeletionResultCallback spaceDeletionResultCallback = spaceDeletionResultCallback;
                if (spaceDeletionResultCallback != null) {
                    spaceDeletionResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                }
            }
        };
        if (i2 == 501) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", i2);
                getSocialService().requestSpaceDeletionWithData(getAppId(), str, bundle, r0);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        } else if (!TextUtils.isEmpty(str2)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(BundleKey.EXTRA_CID, str2);
            getSocialService().requestSpaceDeletionWithData(getAppId(), str, bundle2, r0);
            return 1;
        } else {
            getSocialService().requestSpaceDeletion(getAppId(), str, r0);
            return 1;
        }
    }

    public int requestSpaceList(String str, String str2, int i2, final ShareApi.SpaceListResultCallback spaceListResultCallback) {
        infoLog("requestSpaceList : groupId=[" + str + "] ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_4_1) || checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass4 r0 = new ISpaceListResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSpaceList onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (spaceListResultCallback != null) {
                    spaceListResultCallback.onResult(new SpaceListResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (List<Space>) null));
                }
            }

            public void onSuccess(List<Bundle> list) {
                ShareApiImpl.this.infoLog("requestSpaceList onSuccess ");
                if (spaceListResultCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    for (Bundle next : list) {
                        String string = next.getString(BundleKey.SPACE_ID);
                        String string2 = next.getString(BundleKey.GROUP_ID);
                        String string3 = next.getString(BundleKey.OWNER_ID);
                        ShareApiImpl.this.debugLog(C0212a.p(C0086a.q("- groupId=[", string2, "], spaceId=[", string, "], ownerId=["), string3, "] "));
                        if (string == null || string2 == null || string3 == null) {
                            ShareApiImpl.this.debugLog("requestSpaceList is error (one of spaceId, ownerId and groupId is erro)");
                            spaceListResultCallback.onResult(new SpaceListResult(new CommonResultStatus(-1, "Invalid space information", ""), (List<Space>) null));
                            return;
                        }
                        arrayList.add(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).createSpaceResult(next));
                    }
                    spaceListResultCallback.onResult(new SpaceListResult(new CommonResultStatus(1), arrayList));
                }
            }
        };
        if (i2 == 501) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", i2);
                getSocialService().requestSpaceListWithData(getAppId(), str, bundle, r0);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        } else if (!TextUtils.isEmpty(str2)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(BundleKey.EXTRA_CID, str2);
            getSocialService().requestSpaceListWithData(getAppId(), str, bundle2, r0);
            return 1;
        } else {
            getSocialService().requestSpaceList(getAppId(), str, r0);
            return 1;
        }
    }

    public int requestSpaceListSync(String str, int i2, final ShareApi.SpaceListSyncResultCallback spaceListSyncResultCallback) {
        infoLog("requestSpaceListSync ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_4_1) || checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass9 r0 = new IShareSyncResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSpaceListSync onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (spaceListSyncResultCallback != null) {
                    spaceListSyncResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                }
            }

            public void onSuccess() {
                ShareApiImpl.this.infoLog("requestSpaceListSync onSuccess ");
                ShareApi.SpaceListSyncResultCallback spaceListSyncResultCallback = spaceListSyncResultCallback;
                if (spaceListSyncResultCallback != null) {
                    spaceListSyncResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                }
            }
        };
        if (i2 == 501) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", i2);
                getSocialService().requestSpaceListSyncWithData(getAppId(), bundle, r0);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        } else if (!TextUtils.isEmpty(str)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(BundleKey.EXTRA_CID, str);
            getSocialService().requestSpaceListSyncWithData(getAppId(), bundle2, r0);
            return 1;
        } else {
            getSocialService().requestSpaceListSync(getAppId(), r0);
            return 1;
        }
    }

    public int requestSpaceUpdate(String str, boolean z, String str2, int i2, ShareApi.SpaceResultCallback spaceResultCallback) {
        if (i2 != 501) {
            infoLog("requestSpaceUpdate with isStandAlone is not supported in " + i2);
            return -1;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean(BundleKey.IS_STANDALONE, z);
        return requestSpaceUpdate(str, bundle, str2, i2, spaceResultCallback);
    }

    public int requestSync(String str, int i2, ShareApi.ShareSyncResultCallback shareSyncResultCallback) {
        infoLog("requestSync ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_4_1) || checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        } else if (i2 == 501) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", i2);
                getSocialService().requestShareSyncWithData(getAppId(), bundle, createShareSyncCallback(shareSyncResultCallback));
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        } else if (!TextUtils.isEmpty(str)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(BundleKey.EXTRA_CID, str);
            getSocialService().requestShareSyncWithData(getAppId(), bundle2, createShareSyncCallback(shareSyncResultCallback));
            return 1;
        } else {
            getSocialService().requestShareSync(getAppId(), createShareSyncCallback(shareSyncResultCallback));
            return 1;
        }
    }

    public int requestThumbnailDownload(String str, String str2, String str3, String str4, String str5, int i2, ShareApi.ShareBaseResultCallback<DownloadImageResult> shareBaseResultCallback) {
        StringBuilder q = C0086a.q("requestThumbnailDownload. groupId=[", str, "] spaceId=[", str2, "] itemId=[");
        q.append(str3);
        q.append("] ");
        infoLog(q.toString());
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_11_2) || isNoMoreSupportedSemsAgentVersion(CommonConstants.SupportedApiMinVersion.VERSION_12_1)) {
            return -7;
        }
        if (i2 == 501) {
            infoLog("requestItemThumbnailDownload is not supported in " + i2);
            return -1;
        }
        String str6 = str5;
        String str7 = str4;
        String str8 = str3;
        String str9 = str2;
        return downloadThumbnail(str, str9, str8, str7, str6, shareBaseResultCallback);
    }

    public int requestWebLinkEnabled(String str, String str2, boolean z, int i2, final ShareApi.ShareBaseResultCallback<SpaceResult> shareBaseResultCallback) {
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_0_1) || checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass30 r0 = new IBundleResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestWebLinkEnabled onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (shareBaseResultCallback != null) {
                    shareBaseResultCallback.onResult(new SpaceResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (Space) null));
                }
            }

            public void onSuccess(Bundle bundle) {
                ShareApiImpl.this.infoLog("requestWebLinkEnabled onSuccess ");
                ShareApi.ShareBaseResultCallback shareBaseResultCallback = shareBaseResultCallback;
                if (shareBaseResultCallback != null) {
                    shareBaseResultCallback.onResult(new SpaceResult(new CommonResultStatus(1), ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).createSpaceResult(bundle)));
                }
            }
        };
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("featureId", i2);
            bundle.putString("appId", getAppId());
            bundle.putString("groupId", str);
            bundle.putString("spaceId", str2);
            getSocialService().requestWebLinkEnabled(bundle, z, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestSpaceUpdate(String str, Bundle bundle, String str2, int i2, final ShareApi.SpaceResultCallback spaceResultCallback) {
        infoLog("requestSpaceUpdate : spaceId=[" + str + "] ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_4_1) || checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass6 r72 = new ISpaceResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSpaceUpdate onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (spaceResultCallback != null) {
                    spaceResultCallback.onResult(new SpaceResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (Space) null));
                }
            }

            public void onSuccess(Bundle bundle) {
                ShareApiImpl.this.infoLog("requestSpaceUpdate onSuccess ");
                ShareApi.SpaceResultCallback spaceResultCallback = spaceResultCallback;
                if (spaceResultCallback != null) {
                    spaceResultCallback.onResult(new SpaceResult(new CommonResultStatus(1), ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).createSpaceResult(bundle)));
                }
            }
        };
        if (i2 == 501) {
            try {
                Bundle bundle2 = new Bundle();
                bundle2.putInt("featureId", i2);
                getSocialService().requestSpaceUpdateWithData(getAppId(), str, bundle2, bundle, r72);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        } else {
            String str3 = str;
            Bundle bundle3 = bundle;
            if (!TextUtils.isEmpty(str2)) {
                Bundle bundle4 = new Bundle();
                bundle4.putString(BundleKey.EXTRA_CID, str2);
                getSocialService().requestSpaceUpdateWithData(getAppId(), str3, bundle4, bundle3, r72);
                return 1;
            }
            getSocialService().requestSpaceUpdate(getAppId(), str3, bundle3, r72);
            return 1;
        }
    }

    public int requestSharedContentDownload(String str, String str2, List<String> list, String str3, int i2, ShareApi.ContentDownloadingResultCallback contentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle, String str4) {
        int i7 = i2;
        String str5 = str2;
        StringBuilder q = C0086a.q("requestSharedContentDownload spaceId=[", str, "] itemId=[", str5, "] hash = [");
        List<String> list2 = list;
        q.append(list2);
        q.append("] ");
        infoLog(q.toString());
        if (i7 == 501) {
            infoLog("requestSharedContentDownload is not supported in " + i7);
            return -1;
        } else if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        } else if (!TextUtils.equals("8o8b82h22a", getAppId())) {
            infoLog("app id is not reminder. this api use only reminder");
            return -1;
        } else {
            final ShareApi.ContentDownloadingResultCallback contentDownloadingResultCallback2 = contentDownloadingResultCallback;
            AnonymousClass18 r9 = new IContentDownloadingResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ShareApiImpl shareApiImpl = ShareApiImpl.this;
                    StringBuilder p6 = C0086a.p(j2, "requestSharedContentDownload onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    shareApiImpl.infoLog(p6.toString());
                    if (contentDownloadingResultCallback2 != null) {
                        contentDownloadingResultCallback2.onResult(new ContentDownloadResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (ArrayList<ContentDownloadResult.DownloadedContent>) null, (ArrayList<ContentDownloadResult.DownloadedContent>) null));
                    }
                }

                public void onProgress(Bundle bundle) {
                    ShareApiImpl.this.debugLog("requestSharedContentDownload onProgress ");
                    ShareApi.ContentDownloadingResultCallback contentDownloadingResultCallback = contentDownloadingResultCallback2;
                    if (contentDownloadingResultCallback != null) {
                        contentDownloadingResultCallback.onProgress(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToDownloadSnapShot(bundle));
                    }
                }

                public void onSuccess(List<Bundle> list, List<Bundle> list2) {
                    ShareApiImpl.this.infoLog("requestSharedContentDownload onSuccess ");
                    if (contentDownloadingResultCallback2 == null) {
                        return;
                    }
                    if (!list.isEmpty() || !list2.isEmpty()) {
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        for (Bundle bundleToDownloadContent : list) {
                            arrayList.add(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToDownloadContent(bundleToDownloadContent));
                        }
                        for (Bundle bundleToDownloadContent2 : list2) {
                            arrayList2.add(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToDownloadContent(bundleToDownloadContent2));
                        }
                        contentDownloadingResultCallback2.onResult(new ContentDownloadResult(new CommonResultStatus(1), arrayList, arrayList2));
                        return;
                    }
                    ShareApiImpl.this.debugLog("requestSharedContentDownload bundle is empty!!");
                    contentDownloadingResultCallback2.onResult(new ContentDownloadResult(new CommonResultStatus(1), (ArrayList<ContentDownloadResult.DownloadedContent>) null, (ArrayList<ContentDownloadResult.DownloadedContent>) null));
                }
            };
            try {
                if (!TextUtils.isEmpty(str3)) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(BundleKey.EXTRA_CID, str3);
                    if (TextUtils.isEmpty(str4)) {
                        AnonymousClass18 r10 = r9;
                        getSocialService().requestOriginalSharedContentWithFileListDownloadWithData(getAppId(), str, str2, list, bundle2, r10, pendingIntent, bundle);
                        return 1;
                    }
                    AnonymousClass18 r102 = r9;
                    getSocialService().requestOriginalSharedContentWithItemFileListDownloadWithPathWithData(getAppId(), str, str2, list, bundle2, r102, pendingIntent, bundle, str4);
                    return 1;
                } else if (TextUtils.isEmpty(str4)) {
                    getSocialService().requestOriginalSharedContentWithFileListDownload(getAppId(), str, str5, list2, r9, pendingIntent, bundle);
                    return 1;
                } else {
                    getSocialService().requestOriginalSharedContentWithItemFileListDownloadWithPath(getAppId(), str, str2, list, r9, pendingIntent, bundle, str4);
                    return 1;
                }
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        }
    }

    public ShareController requestSharedItemUpdate(String str, List<ShareApi.SharedItemRequest> list, int i2, final ShareApi.ShareUploadResultCallback<SharedItemListResult> shareUploadResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        StringBuilder k = j.k("requestSpace : spaceId=[", str, "], request size =[");
        k.append(list.size());
        k.append("] ");
        infoLog(k.toString());
        if (i2 == 501) {
            infoLog("requestSharedItemUpdate is not supported in " + i2);
            return null;
        } else if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return null;
        } else {
            AnonymousClass25 r62 = new IShareResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ShareApiImpl shareApiImpl = ShareApiImpl.this;
                    StringBuilder p6 = C0086a.p(j2, "requestSpace onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    shareApiImpl.infoLog(p6.toString());
                    if (shareUploadResultCallback != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(j2);
                        ShareApiImpl.this.debugLog(C0212a.m("requestShare Error Message [", str, "]"));
                        shareUploadResultCallback.onResult(new SharedItemListResult(new CommonResultStatus(convertErrorcode, str, Long.toString(j2)), (List<SharedItem>) null, (List<ShareApi.SharedItemRequest>) null));
                    }
                }

                public void onProgress(Bundle bundle) {
                    ShareApiImpl.this.debugLog("requestShare onProgress ");
                    ShareApi.ShareUploadResultCallback shareUploadResultCallback = shareUploadResultCallback;
                    if (shareUploadResultCallback != null) {
                        shareUploadResultCallback.onProgress(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                    }
                }

                public void onSuccess(List<Bundle> list, List<Bundle> list2) {
                    ShareApiImpl.this.infoLog("requestSharedItemUpdate onSuccess ");
                    if (shareUploadResultCallback != null) {
                        ArrayList arrayList = new ArrayList();
                        for (Bundle createSharedItemResult : list) {
                            arrayList.add(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).createSharedItemResult(createSharedItemResult));
                        }
                        ArrayList arrayList2 = new ArrayList();
                        for (Bundle createShareFailedItemResult : list2) {
                            arrayList2.add(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).createShareFailedItemResult(createShareFailedItemResult));
                        }
                        shareUploadResultCallback.onResult(new SharedItemListResult(new CommonResultStatus(1), arrayList, arrayList2));
                    }
                }

                public void onUploadComplete(Bundle bundle) {
                    ShareApiImpl.this.debugLog("requestShare onUploadComplete ");
                    ShareApi.ShareUploadResultCallback shareUploadResultCallback = shareUploadResultCallback;
                    if (shareUploadResultCallback != null) {
                        shareUploadResultCallback.onUploadComplete(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                    }
                }
            };
            try {
                ArrayList arrayList = new ArrayList();
                for (ShareApi.SharedItemRequest requestToBundle : list) {
                    arrayList.add(ShareApiUtil.getInstance(getTag(), getReference(), getAppId()).requestToBundle(requestToBundle));
                }
                return new ShareController(this.mApiPicker, getSocialService().requestSharedItemListUpdate(getAppId(), str, arrayList, r62, pendingIntent, bundle));
            } catch (RemoteException | NotConnectedException | NullPointerException e) {
                secureLog(e);
                return null;
            }
        }
    }

    public int requestSpaceList(String str, int i2, final ShareApi.SpaceListResultCallback spaceListResultCallback) {
        infoLog("requestSpaceList ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_4_1) || checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass8 r0 = new ISpaceListResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSpaceList onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (spaceListResultCallback != null) {
                    spaceListResultCallback.onResult(new SpaceListResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (List<Space>) null));
                }
            }

            public void onSuccess(List<Bundle> list) {
                ShareApiImpl.this.infoLog("requestSpaceList onSuccess ");
                if (spaceListResultCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    for (Bundle next : list) {
                        String string = next.getString(BundleKey.SPACE_ID);
                        String string2 = next.getString(BundleKey.GROUP_ID);
                        String string3 = next.getString(BundleKey.OWNER_ID);
                        if (string == null || string2 == null || string3 == null) {
                            spaceListResultCallback.onResult(new SpaceListResult(new CommonResultStatus(-1, "Invalid space information", ""), (List<Space>) null));
                            return;
                        }
                        arrayList.add(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).createSpaceResult(next));
                    }
                    spaceListResultCallback.onResult(new SpaceListResult(new CommonResultStatus(1), arrayList));
                }
            }
        };
        if (i2 == 501) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", i2);
                getSocialService().requestAllSpaceListWithData(getAppId(), bundle, r0);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        } else if (!TextUtils.isEmpty(str)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(BundleKey.EXTRA_CID, str);
            getSocialService().requestAllSpaceListWithData(getAppId(), bundle2, r0);
            return 1;
        } else {
            getSocialService().requestAllSpaceList(getAppId(), r0);
            return 1;
        }
    }

    public int requestSharedItemSync(String str, int i2, final ShareApi.SharedItemSyncResultCallback sharedItemSyncResultCallback) {
        infoLog("requestSharedItemSync : spaceId=[" + str + "] ");
        if (checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass23 r0 = new IShareSyncResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSharedItemSync onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (sharedItemSyncResultCallback != null) {
                    sharedItemSyncResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                }
            }

            public void onSuccess() {
                ShareApiImpl.this.infoLog("requestSharedItemSync onSuccess ");
                ShareApi.SharedItemSyncResultCallback sharedItemSyncResultCallback = sharedItemSyncResultCallback;
                if (sharedItemSyncResultCallback != null) {
                    sharedItemSyncResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                }
            }
        };
        if (i2 == 501) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", i2);
                getSocialService().requestSharedItemSyncWithData(getAppId(), str, bundle, r0);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        } else {
            getSocialService().requestSharedItemSync(getAppId(), str, r0);
            return 1;
        }
    }

    public int requestSharedItemDeletion(String str, String str2, int i2, final ShareApi.SharedItemDeletionResultCallback sharedItemDeletionResultCallback) {
        infoLog(j.d("requestSharedItemDeletion : spaceId=[", str, "], itemId=[", str2, "] "));
        if (checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass19 r72 = new ISharedItemDeletionResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSharedItemDeletion onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (sharedItemDeletionResultCallback != null) {
                    sharedItemDeletionResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                }
            }

            public void onSuccess() {
                ShareApiImpl.this.infoLog("requestSharedItemDeletion onSuccess ");
                ShareApi.SharedItemDeletionResultCallback sharedItemDeletionResultCallback = sharedItemDeletionResultCallback;
                if (sharedItemDeletionResultCallback != null) {
                    sharedItemDeletionResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                }
            }
        };
        if (i2 == 501) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", i2);
                getSocialService().requestSharedItemDeletionWithData(getAppId(), str, str2, bundle, r72);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        } else {
            getSocialService().requestSharedItemDeletion(getAppId(), str, str2, r72);
            return 1;
        }
    }

    public ShareController requestShare(String str, List<ShareApi.SharedItemRequest> list, ShareApi.ActionIntent actionIntent, ShareApi.NotificationMessage notificationMessage, int i2, final ShareApi.ShareUploadResultCallback<SharedItemListResult> shareUploadResultCallback) {
        String str2;
        infoLog("requestShare : spaceId=[" + str + "] ");
        if (i2 == 501) {
            infoLog("requestShare is not supported in " + i2);
            return null;
        } else if (getAppId() == null) {
            infoLog("app id is null ");
            return null;
        } else {
            AnonymousClass17 r62 = new IShareResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ShareApiImpl shareApiImpl = ShareApiImpl.this;
                    StringBuilder p6 = C0086a.p(j2, "requestShare onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    shareApiImpl.infoLog(p6.toString());
                    if (shareUploadResultCallback != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(j2);
                        ShareApiImpl.this.debugLog(C0212a.m("requestShare Error Message [", str, "]"));
                        shareUploadResultCallback.onResult(new SharedItemListResult(new CommonResultStatus(convertErrorcode, str, Long.toString(j2)), (List<SharedItem>) null, (List<ShareApi.SharedItemRequest>) null));
                    }
                }

                public void onProgress(Bundle bundle) {
                    ShareApiImpl.this.debugLog("requestShare onProgress ");
                    ShareApi.ShareUploadResultCallback shareUploadResultCallback = shareUploadResultCallback;
                    if (shareUploadResultCallback != null) {
                        shareUploadResultCallback.onProgress(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                    }
                }

                public void onSuccess(List<Bundle> list, List<Bundle> list2) {
                    ShareApiImpl.this.infoLog("requestShare onSuccess ");
                    if (shareUploadResultCallback != null) {
                        ArrayList arrayList = new ArrayList();
                        for (Bundle createSharedItemResult : list) {
                            arrayList.add(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).createSharedItemResult(createSharedItemResult));
                        }
                        ArrayList arrayList2 = new ArrayList();
                        for (Bundle createShareFailedItemResult : list2) {
                            arrayList2.add(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).createShareFailedItemResult(createShareFailedItemResult));
                        }
                        shareUploadResultCallback.onResult(new SharedItemListResult(new CommonResultStatus(1), arrayList, arrayList2));
                    }
                }

                public void onUploadComplete(Bundle bundle) {
                    ShareApiImpl.this.debugLog("requestShare onUploadComplete ");
                    ShareApi.ShareUploadResultCallback shareUploadResultCallback = shareUploadResultCallback;
                    if (shareUploadResultCallback != null) {
                        shareUploadResultCallback.onUploadComplete(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                    }
                }
            };
            try {
                ArrayList arrayList = new ArrayList();
                for (ShareApi.SharedItemRequest requestToBundle : list) {
                    arrayList.add(ShareApiUtil.getInstance(getTag(), getReference(), getAppId()).requestToBundle(requestToBundle));
                }
                if (isSupportedSemsAgentVersionBetween(CommonConstants.SupportedApiMinVersion.VERSION_10_8_6, CommonConstants.SupportedApiMinVersion.VERSION_11_0) || isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_0_1)) {
                    String str3 = str;
                    AnonymousClass17 r72 = r62;
                    Bundle bundle = new Bundle();
                    if (actionIntent != null) {
                        bundle.putParcelable(BundleKey.PENDING_INTENTS, actionIntent.toBundle());
                    }
                    if (notificationMessage != null) {
                        bundle.putParcelable(BundleKey.NOTIFICATION_MESSAGES, notificationMessage.toBundle());
                    }
                    str2 = getSocialService().requestShareWithData(getAppId(), str3, arrayList, bundle, r72);
                } else {
                    str2 = getSocialService().requestShareWithPendingIntent(getAppId(), str, arrayList, r62, actionIntent != null ? actionIntent.getShareCompleteIntent() : null, notificationMessage != null ? notificationMessage.toBundle() : null);
                }
                return new ShareController(this.mApiPicker, str2);
            } catch (RemoteException | NotConnectedException | NullPointerException e) {
                secureLog(e);
                return null;
            }
        }
    }

    public int requestSharedItemList(String str, String str2, String str3, int i2, final ShareApi.SharedItemListResultCallback sharedItemListResultCallback) {
        StringBuilder q = C0086a.q("requestSharedItemList : groupId=[", str, "], spaceId=[", str2, "], resolution=[");
        q.append(str3);
        q.append("] ");
        infoLog(q.toString());
        if (i2 == 501) {
            infoLog("requestSharedItemList is not supported in " + i2);
            return -1;
        } else if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_4_1) || checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        } else {
            if (TextUtils.isEmpty(getAppId())) {
                infoLog("app id is empty ");
                return -1;
            }
            try {
                getSocialService().requestSharedItemList(getAppId(), str, str2, str3, new ISharedItemListResultCallback.Stub() {
                    public void onFailure(long j2, String str) {
                        ShareApiImpl shareApiImpl = ShareApiImpl.this;
                        StringBuilder p6 = C0086a.p(j2, "requestSpace onFailure : code=[", "], message=[", str);
                        p6.append("] ");
                        shareApiImpl.infoLog(p6.toString());
                        if (sharedItemListResultCallback != null) {
                            ErrorCodeConvertor.convertErrorcode(j2);
                            ShareApiImpl.this.debugLog(C0212a.m("requestSpace Error Message [", str, "]"));
                        }
                    }

                    public void onSuccess(List<Bundle> list) {
                        ShareApiImpl.this.infoLog("requestSharedItemList(without content list) onSuccess ");
                        if (sharedItemListResultCallback != null) {
                            ArrayList arrayList = new ArrayList();
                            for (Bundle createSharedItemResult : list) {
                                arrayList.add(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).createSharedItemResult(createSharedItemResult));
                            }
                            sharedItemListResultCallback.onResult(new SharedItemListResult(new CommonResultStatus(1), arrayList, (List<ShareApi.SharedItemRequest>) null));
                        }
                    }
                });
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        }
    }

    public ShareController requestSharedItemUpdate(String str, String str2, ShareApi.SharedItemRequest sharedItemRequest, int i2, ShareApi.SharedItemUpdateResultCallback sharedItemUpdateResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        infoLog(j.d("requestSpace : spaceId=[", str, "], itemId=[", str2, "] "));
        if (i2 == 501) {
            infoLog("requestSharedItemUpdate is not supported in " + i2);
            return null;
        } else if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return null;
        } else {
            final ShareApi.SharedItemUpdateResultCallback sharedItemUpdateResultCallback2 = sharedItemUpdateResultCallback;
            try {
                return new ShareController(this.mApiPicker, getSocialService().requestSharedItemUpdate(getAppId(), str, str2, ShareApiUtil.getInstance(getTag(), getReference(), getAppId()).requestToBundle(sharedItemRequest), new ISharedItemUpdateResultCallback.Stub() {
                    public void onFailure(long j2, String str) {
                        ShareApiImpl shareApiImpl = ShareApiImpl.this;
                        StringBuilder p6 = C0086a.p(j2, "requestSpace onFailure : code=[", "], message=[", str);
                        p6.append("] ");
                        shareApiImpl.infoLog(p6.toString());
                        if (sharedItemUpdateResultCallback2 != null) {
                            int convertErrorcode = ErrorCodeConvertor.convertErrorcode(j2);
                            ShareApiImpl.this.debugLog(C0212a.m("requestSpace Error Message [", str, "]"));
                            sharedItemUpdateResultCallback2.onResult(new SharedItemResult(new CommonResultStatus(convertErrorcode, str, Long.toString(j2)), (SharedItem) null));
                        }
                    }

                    public void onProgress(Bundle bundle) {
                        ShareApiImpl.this.debugLog("requestShare onProgress ");
                        ShareApi.SharedItemUpdateResultCallback sharedItemUpdateResultCallback = sharedItemUpdateResultCallback2;
                        if (sharedItemUpdateResultCallback != null) {
                            sharedItemUpdateResultCallback.onProgress(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                        }
                    }

                    public void onSuccess(Bundle bundle) {
                        ShareApiImpl.this.infoLog("requestSpace onSuccess ");
                        ShareApi.SharedItemUpdateResultCallback sharedItemUpdateResultCallback = sharedItemUpdateResultCallback2;
                        if (sharedItemUpdateResultCallback != null) {
                            sharedItemUpdateResultCallback.onResult(new SharedItemResult(new CommonResultStatus(1), ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).createSharedItemResult(bundle)));
                        }
                    }

                    public void onUploadComplete(Bundle bundle) {
                        ShareApiImpl.this.debugLog("requestShare onUploadComplete ");
                        ShareApi.SharedItemUpdateResultCallback sharedItemUpdateResultCallback = sharedItemUpdateResultCallback2;
                        if (sharedItemUpdateResultCallback != null) {
                            sharedItemUpdateResultCallback.onUploadComplete(ShareApiUtil.getInstance(ShareApiImpl.this.getTag(), ShareApiImpl.this.getReference(), ShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                        }
                    }
                }, pendingIntent, bundle));
            } catch (RemoteException | NotConnectedException | NullPointerException e) {
                secureLog(e);
                return null;
            }
        }
    }

    public int requestSharedItemDeletion(String str, List<String> list, int i2, final ShareApi.SharedItemDeletionListResultCallback sharedItemDeletionListResultCallback) {
        StringBuilder k = j.k("requestSharedItemDeletion : spaceId=[", str, "], itemId size=");
        k.append(list.size());
        infoLog(k.toString());
        if (checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        AnonymousClass20 r72 = new ISharedItemListDeletionResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                ShareApiImpl shareApiImpl = ShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSharedItemDeletion onFailure : code=[", "], message=[", str);
                p6.append("] ");
                shareApiImpl.infoLog(p6.toString());
                if (sharedItemDeletionListResultCallback != null) {
                    sharedItemDeletionListResultCallback.onResult(new SharedItemListDeletionResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), new ArrayList()));
                }
            }

            public void onSuccess(List<Bundle> list) {
                ShareApiImpl.this.infoLog("requestSharedItemDeletion onSuccess ");
                if (sharedItemDeletionListResultCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    for (Bundle next : list) {
                        arrayList.add(new SharedItemListDeletionResult.SharedItemDeletionResult(next.getString(BundleKey.SPACE_ID), next.getString(BundleKey.ITEM_ID), next.getBoolean("result")));
                    }
                    sharedItemDeletionListResultCallback.onResult(new SharedItemListDeletionResult(new CommonResultStatus(1), arrayList));
                }
            }
        };
        if (i2 == 501) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", i2);
                getSocialService().requestSharedItemListDeletionWithData(getAppId(), str, list, bundle, r72);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        } else {
            getSocialService().requestSharedItemListDeletion(getAppId(), str, list, r72);
            return 1;
        }
    }
}
