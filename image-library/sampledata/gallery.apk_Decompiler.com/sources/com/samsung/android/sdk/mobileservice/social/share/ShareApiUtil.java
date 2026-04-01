package com.samsung.android.sdk.mobileservice.social.share;

import N2.j;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;
import com.samsung.android.sdk.mobileservice.social.share.result.ContentDownloadResult;
import com.samsung.android.sdk.mobileservice.util.SdkLog;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareApiUtil {
    private static ShareApiUtil sInstance;
    private final String APP_ID_GALLERY = "22n6hzkam0";
    private final String APP_ID_REMINDER = "8o8b82h22a";
    private final String SPLIT_TOKEN = "_*sems&_";
    private String mApiName;
    private String mAppId;
    private String mReference;

    private ShareApiUtil(String str, String str2, String str3) {
        this.mApiName = str;
        this.mReference = str2;
        this.mAppId = str3;
    }

    private void createSharedItemResult(SharedItem sharedItem, Bundle bundle) {
        sharedItem.setLastModifier(bundle.getString(BundleKey.LAST_MODIFIER_ID));
        sharedItem.setTitle(bundle.getString("title", ""));
        sharedItem.setMemo(bundle.getString("memo", ""));
        sharedItem.setCreatedTime(bundle.getLong(BundleKey.CREATED_TIME, 0));
        sharedItem.setModifiedTime(bundle.getLong(BundleKey.MODIFIED_TIME, 0));
        sharedItem.setMimeType(bundle.getString("mime_type", ""));
        String string = bundle.getString(BundleKey.THUMBNAIL_URI, "");
        if (string != null) {
            sharedItem.setThumbnailFileUri(Uri.parse(string));
        }
        String string2 = bundle.getString("thumbnail_content_uri", (String) null);
        if (string2 != null) {
            sharedItem.setThumbnailContentUri(Uri.parse(string2));
        }
        sharedItem.setOwnedByMe(bundle.getBoolean("is_owned_by_me", false));
        sharedItem.setMetaData((HashMap) bundle.getSerializable("meta_data"));
        sharedItem.setSize(bundle.getLong(BundleKey.FILE_SIZE, 0));
        sharedItem.setOriginalContentPath(bundle.getString("original_content_path", ""));
        String string3 = bundle.getString(BundleKey.ORIGINAL_CONTENT_URI, (String) null);
        if (string3 != null) {
            sharedItem.setOriginalContentUri(Uri.parse(string3));
        }
        sharedItem.setStreamingUrl(bundle.getString("streaming_url", ""));
        sharedItem.setSourceCid(bundle.getString("source_cid", ""));
        sharedItem.setResourceId(bundle.getString("referred_resource_id", (String) null));
        sharedItem.setCreator(bundle.getString("creator", (String) null));
        showLog("- itemId=[" + sharedItem.getItemId() + "], spaceId=[" + sharedItem.getSpaceId() + "], ownerId=[" + sharedItem.getLeaderId() + "], title=[" + sharedItem.getTitle() + "], memo=[" + sharedItem.getMemo() + "], createdTime=[" + sharedItem.getCreatedTime() + "], modifiedTime=[" + sharedItem.getModifiedTime() + "], mimeType=[" + sharedItem.getMimeType() + "], thumbnailUri=[" + string + "], lastModifier=[" + sharedItem.getLastModifier() + "]");
    }

    private Space createSpace(Bundle bundle) {
        String string = bundle.getString(BundleKey.SPACE_ID, (String) null);
        String string2 = bundle.getString(BundleKey.GROUP_ID, (String) null);
        String string3 = bundle.getString(BundleKey.OWNER_ID, (String) null);
        if (TextUtils.equals(this.mAppId, "22n6hzkam0")) {
            return new GallerySpace(string2, string, string3, bundle);
        }
        if (TextUtils.equals(this.mAppId, "8o8b82h22a")) {
            return new ReminderSpace(string2, string, string3, bundle);
        }
        return new Space(string2, string, string3);
    }

    private WebLink createWebLinkResult(Bundle bundle) {
        String string = bundle.getString("weblink_url", "");
        String string2 = bundle.getString("weblink_creator", "");
        long j2 = bundle.getLong("weblink_created_time");
        long j3 = bundle.getLong("weblink_expired_time");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || j2 == 0 || j3 == 0) {
            return null;
        }
        return new WebLink(string, string2, j2, j3);
    }

    public static ShareApiUtil getInstance(String str, String str2, String str3) {
        if (sInstance == null) {
            synchronized (ShareApiUtil.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new ShareApiUtil(str, str2, str3);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private String getMetaString(Map map) {
        StringBuilder sb2 = new StringBuilder();
        if (map != null) {
            for (Object obj : map.keySet()) {
                String obj2 = obj.toString();
                if (sb2.length() > 0) {
                    sb2.append("_*sems&_");
                }
                sb2.append(obj2);
                sb2.append("_*sems&_");
                sb2.append(map.get(obj2));
            }
        }
        return sb2.toString();
    }

    private void showLog(String str) {
        String str2 = this.mApiName;
        StringBuilder t = C0212a.t(str, " ");
        t.append(this.mReference);
        SdkLog.d(str2, t.toString());
    }

    public ContentDownloadResult.DownloadedContent bundleToDownloadContent(Bundle bundle) {
        Uri uri;
        Uri uri2 = null;
        String string = bundle.getString(BundleKey.SPACE_ID, (String) null);
        String string2 = bundle.getString(BundleKey.ITEM_ID, (String) null);
        String string3 = bundle.getString(BundleKey.CONTENT_HASH, (String) null);
        String string4 = bundle.getString(BundleKey.DOWNLOADED_URI, (String) null);
        String string5 = bundle.getString(BundleKey.DOWNLOADED_CONTENT_URI, (String) null);
        String string6 = bundle.getString("mime_type", (String) null);
        long j2 = bundle.getLong(BundleKey.FILE_SIZE, -1);
        if (string4 != null) {
            uri = Uri.parse(string4);
        } else {
            uri = null;
        }
        if (string5 != null) {
            uri2 = Uri.parse(string5);
        }
        showLog(C0212a.q(C0086a.q("- successList : space_id=[", string, "], item_id=[", string2, "], downloaded_uri=["), string4, "], mime_type=[", string6, "]"));
        return new ContentDownloadResult.DownloadedContent(string, string2, string3, uri, uri2, string6, j2);
    }

    public SharedContentDownloadSnapshot bundleToDownloadSnapShot(Bundle bundle) {
        Bundle bundle2 = bundle;
        long j2 = bundle2.getLong(BundleKey.totalBytes, 0);
        long j3 = bundle2.getLong(BundleKey.totalBytesTransferred, 0);
        int i2 = bundle2.getInt(BundleKey.totalFileCount, 0);
        int i7 = bundle2.getInt(BundleKey.totalFileCountTransferred, 0);
        long j8 = bundle2.getLong(BundleKey.currentFileBytes, 0);
        long j10 = bundle2.getLong(BundleKey.currentFileBytesTransferred, 0);
        int i8 = bundle2.getInt(BundleKey.currentFileIndex, 0);
        StringBuilder j11 = j.j(j2, "- totalBytes=[", "], totalBytesTransferred=[");
        j11.append(j3);
        j11.append("], totalFileCount=[");
        j11.append(i2);
        j11.append("], totalFileCountTransferred=[");
        j11.append(i7);
        j11.append("], currentFileBytes=[");
        j11.append(j8);
        j11.append("], currentFileBytesTransferred=[");
        j11.append(j10);
        j11.append("], currentFileIndex=[");
        showLog(C0086a.l(j11, i8, "] "));
        return new SharedContentDownloadSnapshot(j2, j3, i2, i7, j8, j10, i8);
    }

    public ShareSnapshot bundleToShareSnapShot(Bundle bundle) {
        Bundle bundle2 = bundle;
        long j2 = bundle2.getLong(BundleKey.totalBytes, 0);
        long j3 = bundle2.getLong(BundleKey.totalBytesTransferred, 0);
        int i2 = bundle2.getInt(BundleKey.totalFileCount, 0);
        int i7 = bundle2.getInt(BundleKey.totalFileCountTransferred, 0);
        long j8 = bundle2.getLong(BundleKey.currentFileBytes, 0);
        long j10 = bundle2.getLong(BundleKey.currentFileBytesTransferred, 0);
        int i8 = bundle2.getInt(BundleKey.currentFileIndex, 0);
        StringBuilder j11 = j.j(j2, "- totalBytes=[", "], totalBytesTransferred=[");
        j11.append(j3);
        j11.append("], totalFileCount=[");
        j11.append(i2);
        j11.append("], totalFileCountTransferred=[");
        j11.append(i7);
        j11.append("], currentFileBytes=[");
        j11.append(j8);
        j11.append("], currentFileBytesTransferred=[");
        j11.append(j10);
        j11.append("], currentFileIndex=[");
        showLog(C0086a.l(j11, i8, "] "));
        return new ShareSnapshot(j2, j3, i2, i7, j8, j10, i8);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: com.samsung.android.sdk.mobileservice.social.share.ShareApi$SharedItemWithUriRequest} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: com.samsung.android.sdk.mobileservice.social.share.ShareApi$SharedItemWithUriRequest} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: com.samsung.android.sdk.mobileservice.social.share.ShareApi$SharedItemWithUriRequest} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: com.samsung.android.sdk.mobileservice.social.share.ShareApi$SharedItemWithSCloudHashRequest} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: com.samsung.android.sdk.mobileservice.social.share.ShareApi$SharedItemWithMediaServiceContentIdRequest} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: com.samsung.android.sdk.mobileservice.social.share.ShareApi$SharedItemWithUriRequest} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: com.samsung.android.sdk.mobileservice.social.share.ShareApi$SharedItemWithUriRequest} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.samsung.android.sdk.mobileservice.social.share.ShareApi.SharedItemRequest createShareFailedItemResult(android.os.Bundle r10) {
        /*
            r9 = this;
            java.lang.String r0 = "title"
            java.lang.String r1 = ""
            java.lang.String r0 = r10.getString(r0, r1)
            java.lang.String r2 = "request_type"
            r3 = -1
            int r2 = r10.getInt(r2, r3)
            r3 = 0
            if (r2 == 0) goto L_0x005a
            r4 = 1
            java.lang.String r5 = "file_name"
            r6 = -1
            java.lang.String r8 = "file_size"
            if (r2 == r4) goto L_0x003d
            r4 = 2
            if (r2 == r4) goto L_0x0020
            goto L_0x006d
        L_0x0020:
            com.samsung.android.sdk.mobileservice.social.share.ShareApi$SharedItemWithMediaServiceContentIdRequest r3 = new com.samsung.android.sdk.mobileservice.social.share.ShareApi$SharedItemWithMediaServiceContentIdRequest
            r3.<init>(r0)
            java.lang.String r0 = "media_service_content_id"
            java.lang.String r0 = r10.getString(r0, r1)
            r3.setMediaServiceContentId(r0)
            long r6 = r10.getLong(r8, r6)
            r3.setContentSize(r6)
            java.lang.String r0 = r10.getString(r5, r1)
            r3.setContentName(r0)
            goto L_0x006d
        L_0x003d:
            com.samsung.android.sdk.mobileservice.social.share.ShareApi$SharedItemWithSCloudHashRequest r3 = new com.samsung.android.sdk.mobileservice.social.share.ShareApi$SharedItemWithSCloudHashRequest
            r3.<init>(r0)
            java.lang.String r0 = "content_hash"
            java.lang.String r0 = r10.getString(r0, r1)
            r3.setHash(r0)
            long r6 = r10.getLong(r8, r6)
            r3.setContentSize(r6)
            java.lang.String r0 = r10.getString(r5, r1)
            r3.setContentName(r0)
            goto L_0x006d
        L_0x005a:
            com.samsung.android.sdk.mobileservice.social.share.ShareApi$SharedItemWithUriRequest r4 = new com.samsung.android.sdk.mobileservice.social.share.ShareApi$SharedItemWithUriRequest
            r4.<init>(r0)
            java.lang.String r0 = "content_uri"
            java.lang.String r0 = r10.getString(r0, r3)
            android.net.Uri r0 = android.net.Uri.parse(r0)
            r4.setUri(r0)
            r3 = r4
        L_0x006d:
            if (r3 == 0) goto L_0x00d1
            java.lang.String r0 = "memo"
            java.lang.String r0 = r10.getString(r0, r1)
            r3.setMemo(r0)
            java.lang.String r0 = "mime_type"
            java.lang.String r0 = r10.getString(r0, r1)
            r3.setContentMimeType(r0)
            java.lang.String r0 = "meta_data"
            java.io.Serializable r0 = r10.getSerializable(r0)
            java.util.HashMap r0 = (java.util.HashMap) r0
            r3.setMetaData(r0)
            java.lang.String r0 = "instant_meta_data"
            java.io.Serializable r10 = r10.getSerializable(r0)
            java.util.HashMap r10 = (java.util.HashMap) r10
            r3.setInstantMetaData(r10)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r0 = " request tyep=["
            r10.<init>(r0)
            r10.append(r2)
            java.lang.String r0 = "], title=["
            r10.append(r0)
            java.lang.String r0 = r3.getTitle()
            r10.append(r0)
            java.lang.String r0 = "], memo=["
            r10.append(r0)
            java.lang.String r0 = r3.getMemo()
            r10.append(r0)
            java.lang.String r0 = "], mimeType=["
            r10.append(r0)
            java.lang.String r0 = r3.getContentMimeType()
            r10.append(r0)
            java.lang.String r0 = "]"
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            r9.showLog(r10)
        L_0x00d1:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.social.share.ShareApiUtil.createShareFailedItemResult(android.os.Bundle):com.samsung.android.sdk.mobileservice.social.share.ShareApi$SharedItemRequest");
    }

    public ShareApi.SharedItemWithUriListRequest createShareFailedItemWithUriListResult(Bundle bundle) {
        ShareApi.SharedItemWithUriListRequest sharedItemWithUriListRequest = new ShareApi.SharedItemWithUriListRequest(bundle.getString("title", ""));
        sharedItemWithUriListRequest.setMemo(bundle.getString("memo", ""));
        sharedItemWithUriListRequest.setMetaData((Map) bundle.getSerializable("meta_data"));
        sharedItemWithUriListRequest.setInstantMetaData((Map) bundle.getSerializable("instant_meta_data"));
        ArrayList<Bundle> parcelableArrayList = bundle.getParcelableArrayList(BundleKey.SHARE_FILE_LIST);
        if (parcelableArrayList != null) {
            for (Bundle bundle2 : parcelableArrayList) {
                String string = bundle2.getString("content_uri", "");
                sharedItemWithUriListRequest.addUri(Uri.parse(string), bundle2.getString("mime_type", ""));
            }
        }
        sharedItemWithUriListRequest.setCreator(bundle.getString("creator", ""));
        sharedItemWithUriListRequest.setResourceId(bundle.getString("referred_resource_id", ""));
        return sharedItemWithUriListRequest;
    }

    public Space createSpaceResult(Bundle bundle) {
        Space createSpace = createSpace(bundle);
        createSpace.setTitle(bundle.getString("title", ""));
        createSpace.setMemo(bundle.getString("memo", ""));
        createSpace.setCreatedTime(bundle.getLong(BundleKey.CREATED_TIME, 0));
        createSpace.setModifiedTime(bundle.getLong(BundleKey.MODIFIED_TIME, 0));
        String string = bundle.getString(BundleKey.THUMBNAIL_URI, (String) null);
        createSpace.setWebLink(createWebLinkResult(bundle));
        if (string != null) {
            createSpace.setCoverThumbnailFileUri(Uri.parse(string));
        }
        createSpace.setSourceCid(bundle.getString("source_cid", (String) null));
        createSpace.setUnreadCount(bundle.getInt("unread_count", 0));
        createSpace.setUnreadCount(bundle.getInt(BundleKey.ITEM_COUNT, 0));
        createSpace.setOwnedByMe(bundle.getBoolean("is_owned_by_me", false));
        createSpace.setMetaData((HashMap) bundle.getSerializable("meta_data"));
        createSpace.setSize(bundle.getLong(BundleKey.FILE_SIZE, 0));
        createSpace.setContentUpdatedTime(bundle.getLong("contents_update_time", 0));
        long j2 = bundle.getLong("my_usage", 0);
        createSpace.setMyUsage(j2);
        long j3 = bundle.getLong("my_count_usage", 0);
        createSpace.setMyCountUsage(j3);
        createSpace.setStandAlone(bundle.getBoolean(BundleKey.IS_STANDALONE, false));
        StringBuilder sb2 = new StringBuilder("- spaceId=[");
        sb2.append(createSpace.getSpaceId());
        sb2.append("], groupId=[");
        sb2.append(createSpace.getGroupId());
        sb2.append("], ownerId=[");
        sb2.append(createSpace.getLeaderId());
        sb2.append("], title=[");
        sb2.append(createSpace.getTitle());
        sb2.append("], memo=[");
        sb2.append(createSpace.getMemo());
        sb2.append("], coverImageUri=[");
        sb2.append(string);
        sb2.append("], myUsage=[");
        sb2.append(j2);
        sb2.append(" ], myCountUsage=[");
        showLog(C0212a.o(sb2, j3, " ]"));
        return createSpace;
    }

    public TrashedItem createTrashedItemResult(Bundle bundle) {
        TrashedItem trashedItem = new TrashedItem(bundle.getString(BundleKey.ITEM_ID), bundle.getString(BundleKey.SPACE_ID), bundle.getString(BundleKey.OWNER_ID));
        createSharedItemResult(trashedItem, bundle);
        trashedItem.setExpiryAt(bundle.getLong(BundleKey.EXPIRY_AT, 0));
        return trashedItem;
    }

    public Bundle requestToBundle(ShareApi.SpaceRequest spaceRequest, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("title", spaceRequest.getTitle());
        bundle.putString("memo", spaceRequest.getMemo());
        bundle.putBoolean(BundleKey.IS_STANDALONE, spaceRequest.isStandAlone());
        StringBuilder k = j.k("requestSpaceCreation : groupId=[", str, "] , title=[");
        k.append(spaceRequest.getTitle());
        k.append("], memo=[");
        k.append(spaceRequest.getMemo());
        k.append("]");
        showLog(k.toString());
        bundle.putString("mime_type", spaceRequest.getMimeType());
        if (spaceRequest instanceof ShareApi.SpaceWithUriRequest) {
            ShareApi.SpaceWithUriRequest spaceWithUriRequest = (ShareApi.SpaceWithUriRequest) spaceRequest;
            if (spaceWithUriRequest.getCoverImageUri() != null) {
                bundle.putString("content_uri", spaceWithUriRequest.getCoverImageUri().toString());
                showLog("requestSpaceCreation : coverImageUriString=[" + spaceWithUriRequest.getCoverImageUri().toString() + "]");
            }
        } else if (spaceRequest instanceof ShareApi.SpaceWithSCloudHashRequest) {
            ShareApi.SpaceWithSCloudHashRequest spaceWithSCloudHashRequest = (ShareApi.SpaceWithSCloudHashRequest) spaceRequest;
            if (spaceWithSCloudHashRequest.getHash() != null) {
                bundle.putString(BundleKey.CONTENT_HASH, spaceWithSCloudHashRequest.getHash());
                bundle.putLong(BundleKey.FILE_SIZE, spaceWithSCloudHashRequest.getCoverImageSize());
                bundle.putString("file_name", spaceWithSCloudHashRequest.getCoverImageName());
            }
        } else if (spaceRequest instanceof ShareApi.SpaceWithMediaServiceContentIdRequest) {
            ShareApi.SpaceWithMediaServiceContentIdRequest spaceWithMediaServiceContentIdRequest = (ShareApi.SpaceWithMediaServiceContentIdRequest) spaceRequest;
            if (spaceWithMediaServiceContentIdRequest.getMediaServiceContentId() != null) {
                bundle.putString("media_service_content_id", spaceWithMediaServiceContentIdRequest.getMediaServiceContentId());
                bundle.putLong(BundleKey.FILE_SIZE, spaceWithMediaServiceContentIdRequest.getCoverImageSize());
                bundle.putString("file_name", spaceWithMediaServiceContentIdRequest.getCoverImageName());
            }
        }
        bundle.putSerializable("meta_data", (HashMap) spaceRequest.getMetaData());
        return bundle;
    }

    public List<ContentValues> requestToContentValues(ShareApi.SharedItemWithUriListRequest sharedItemWithUriListRequest, String str, int i2, long j2) {
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < sharedItemWithUriListRequest.getUris().size(); i7++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", sharedItemWithUriListRequest.getTitle());
            contentValues.put("spaceId", str);
            contentValues.put("memo", sharedItemWithUriListRequest.getMemo());
            contentValues.put("meta_data", getMetaString(sharedItemWithUriListRequest.getMetaData()));
            contentValues.put("instant_meta_data", getMetaString(sharedItemWithUriListRequest.getInstantMetaData()));
            contentValues.put("creator", sharedItemWithUriListRequest.getCreator());
            contentValues.put("referred_resource_id", sharedItemWithUriListRequest.getResourceId());
            contentValues.put("content_uri", sharedItemWithUriListRequest.getUris().get(i7).toString());
            contentValues.put("mime_type", sharedItemWithUriListRequest.getMimeTypeList().get(i7));
            contentValues.put(RequestDBConstants.ITEM_IDX, Integer.valueOf(i2));
            contentValues.put("request_id", Long.valueOf(j2));
            arrayList.add(contentValues);
        }
        return arrayList;
    }

    public ContentValues requestToContentValues(ShareApi.SharedItemRequest sharedItemRequest, String str, int i2, long j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", sharedItemRequest.getTitle());
        contentValues.put("spaceId", str);
        contentValues.put("memo", sharedItemRequest.getMemo());
        contentValues.put("mime_type", sharedItemRequest.getContentMimeType());
        contentValues.put("meta_data", getMetaString(sharedItemRequest.getMetaData()));
        contentValues.put("instant_meta_data", getMetaString(sharedItemRequest.getInstantMetaData()));
        contentValues.put(RequestDBConstants.ITEM_IDX, Integer.valueOf(i2));
        contentValues.put("request_id", Long.valueOf(j2));
        if (sharedItemRequest instanceof ShareApi.SharedItemWithUriRequest) {
            ShareApi.SharedItemWithUriRequest sharedItemWithUriRequest = (ShareApi.SharedItemWithUriRequest) sharedItemRequest;
            contentValues.put(sharedItemWithUriRequest.isFileUri() ? RequestDBConstants.FILE_URI : "content_uri", sharedItemWithUriRequest.getUri().toString());
            return contentValues;
        } else if (sharedItemRequest instanceof ShareApi.SharedItemWithSCloudHashRequest) {
            ShareApi.SharedItemWithSCloudHashRequest sharedItemWithSCloudHashRequest = (ShareApi.SharedItemWithSCloudHashRequest) sharedItemRequest;
            contentValues.put("hash", sharedItemWithSCloudHashRequest.getHash());
            contentValues.put("size", Long.valueOf(sharedItemWithSCloudHashRequest.getContentSize()));
            contentValues.put("file_name", sharedItemWithSCloudHashRequest.getContentName());
            return contentValues;
        } else {
            if (sharedItemRequest instanceof ShareApi.SharedItemWithMediaServiceContentIdRequest) {
                ShareApi.SharedItemWithMediaServiceContentIdRequest sharedItemWithMediaServiceContentIdRequest = (ShareApi.SharedItemWithMediaServiceContentIdRequest) sharedItemRequest;
                contentValues.put("media_service_content_id", sharedItemWithMediaServiceContentIdRequest.getMediaServiceContentId());
                contentValues.put("size", Long.valueOf(sharedItemWithMediaServiceContentIdRequest.getContentSize()));
                contentValues.put("file_name", sharedItemWithMediaServiceContentIdRequest.getContentName());
            }
            return contentValues;
        }
    }

    public SharedItem createSharedItemResult(Bundle bundle) {
        SharedItem sharedItem = new SharedItem(bundle.getString(BundleKey.ITEM_ID), bundle.getString(BundleKey.SPACE_ID), bundle.getString(BundleKey.OWNER_ID));
        createSharedItemResult(sharedItem, bundle);
        return sharedItem;
    }

    public Bundle requestToBundle(ShareApi.SharedItemWithUriListRequest sharedItemWithUriListRequest) {
        Bundle bundle = new Bundle();
        bundle.putString("title", sharedItemWithUriListRequest.getTitle());
        bundle.putString("memo", sharedItemWithUriListRequest.getMemo());
        bundle.putString("mime_type", sharedItemWithUriListRequest.getContentMimeType());
        bundle.putInt(BundleKey.REQUEST_TYPE, 0);
        bundle.putSerializable("meta_data", (HashMap) sharedItemWithUriListRequest.getMetaData());
        bundle.putSerializable("instant_meta_data", (HashMap) sharedItemWithUriListRequest.getInstantMetaData());
        bundle.putString("creator", sharedItemWithUriListRequest.getCreator());
        bundle.putString("referred_resource_id", sharedItemWithUriListRequest.getResourceId());
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < sharedItemWithUriListRequest.getUris().size(); i2++) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("content_uri", sharedItemWithUriListRequest.getUris().get(i2).toString());
            bundle2.putString("mime_type", sharedItemWithUriListRequest.getMimeTypeList().get(i2));
            arrayList.add(bundle2);
        }
        bundle.putParcelableArrayList(BundleKey.SHARE_FILE_LIST, arrayList);
        showLog("- title=[" + sharedItemWithUriListRequest.getTitle() + "], memo=[" + sharedItemWithUriListRequest.getMemo() + "], mime_type=[" + sharedItemWithUriListRequest.getContentMimeType() + "] ");
        return bundle;
    }

    public Bundle requestToBundle(ShareApi.SharedItemUpdateWithUriListRequest sharedItemUpdateWithUriListRequest) {
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.ITEM_ID, sharedItemUpdateWithUriListRequest.getItemId());
        bundle.putString("title", sharedItemUpdateWithUriListRequest.getTitle());
        bundle.putString("memo", sharedItemUpdateWithUriListRequest.getMemo());
        bundle.putString("mime_type", sharedItemUpdateWithUriListRequest.getContentMimeType());
        bundle.putInt(BundleKey.REQUEST_TYPE, 0);
        bundle.putSerializable("meta_data", (HashMap) sharedItemUpdateWithUriListRequest.getMetaData());
        bundle.putSerializable("instant_meta_data", (HashMap) sharedItemUpdateWithUriListRequest.getInstantMetaData());
        bundle.putBoolean("file_replace_required", sharedItemUpdateWithUriListRequest.isFileReplaceRequired());
        bundle.putString("creator", sharedItemUpdateWithUriListRequest.getCreator());
        bundle.putString("referred_resource_id", sharedItemUpdateWithUriListRequest.getResourceId());
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < sharedItemUpdateWithUriListRequest.getUris().size(); i2++) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("content_uri", sharedItemUpdateWithUriListRequest.getUris().get(i2).toString());
            bundle2.putString("mime_type", sharedItemUpdateWithUriListRequest.getMimeTypeList().get(i2));
            arrayList.add(bundle2);
        }
        bundle.putParcelableArrayList(BundleKey.SHARE_FILE_LIST, arrayList);
        showLog("- title=[" + sharedItemUpdateWithUriListRequest.getTitle() + "], memo=[" + sharedItemUpdateWithUriListRequest.getMemo() + "], mime_type=[" + sharedItemUpdateWithUriListRequest.getContentMimeType() + "] ");
        return bundle;
    }

    public Bundle requestToBundle(ShareApi.SharedItemRequest sharedItemRequest) {
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.ITEM_ID, sharedItemRequest.getItemId());
        bundle.putString("title", sharedItemRequest.getTitle());
        bundle.putString("memo", sharedItemRequest.getMemo());
        bundle.putString("mime_type", sharedItemRequest.getContentMimeType());
        bundle.putSerializable("meta_data", (HashMap) sharedItemRequest.getMetaData());
        bundle.putSerializable("instant_meta_data", (HashMap) sharedItemRequest.getInstantMetaData());
        if (sharedItemRequest instanceof ShareApi.SharedItemWithUriRequest) {
            ShareApi.SharedItemWithUriRequest sharedItemWithUriRequest = (ShareApi.SharedItemWithUriRequest) sharedItemRequest;
            bundle.putString(sharedItemWithUriRequest.isFileUri() ? "content_file_uri" : "content_uri", sharedItemWithUriRequest.getUri() == null ? "" : sharedItemWithUriRequest.getUri().toString());
            bundle.putInt(BundleKey.REQUEST_TYPE, 0);
        } else if (sharedItemRequest instanceof ShareApi.SharedItemWithSCloudHashRequest) {
            ShareApi.SharedItemWithSCloudHashRequest sharedItemWithSCloudHashRequest = (ShareApi.SharedItemWithSCloudHashRequest) sharedItemRequest;
            bundle.putString(BundleKey.CONTENT_HASH, sharedItemWithSCloudHashRequest.getHash());
            bundle.putLong(BundleKey.FILE_SIZE, sharedItemWithSCloudHashRequest.getContentSize());
            bundle.putString("file_name", sharedItemWithSCloudHashRequest.getContentName());
            bundle.putInt(BundleKey.REQUEST_TYPE, 1);
        } else if (sharedItemRequest instanceof ShareApi.SharedItemWithMediaServiceContentIdRequest) {
            ShareApi.SharedItemWithMediaServiceContentIdRequest sharedItemWithMediaServiceContentIdRequest = (ShareApi.SharedItemWithMediaServiceContentIdRequest) sharedItemRequest;
            bundle.putString("media_service_content_id", sharedItemWithMediaServiceContentIdRequest.getMediaServiceContentId());
            bundle.putLong(BundleKey.FILE_SIZE, sharedItemWithMediaServiceContentIdRequest.getContentSize());
            bundle.putString("file_name", sharedItemWithMediaServiceContentIdRequest.getContentName());
            bundle.putInt(BundleKey.REQUEST_TYPE, 2);
        } else {
            bundle.putInt(BundleKey.REQUEST_TYPE, 0);
        }
        showLog("- title=[" + sharedItemRequest.getTitle() + "], memo=[" + sharedItemRequest.getMemo() + "], mime_type=[" + sharedItemRequest.getContentMimeType() + "] ");
        return bundle;
    }
}
