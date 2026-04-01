package com.samsung.android.gallery.module.mde;

import A5.a;
import D3.i;
import D9.c;
import D9.e;
import D9.f;
import O3.o;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.common.CommonUtils;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;
import com.samsung.android.sdk.mobileservice.social.share.ShareSnapshot;
import com.samsung.android.sdk.mobileservice.social.share.SharedContentDownloadSnapshot;
import com.samsung.android.sdk.mobileservice.social.share.SharedItem;
import com.samsung.android.sdk.mobileservice.social.share.Space;
import com.samsung.android.sdk.mobileservice.social.share.result.ContentDownloadResult;
import com.samsung.android.sdk.mobileservice.social.share.result.DownloadImageResult;
import com.samsung.android.sdk.mobileservice.social.share.result.ItemListResult;
import com.samsung.android.sdk.mobileservice.social.share.result.QuotaResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SharedItemListResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SharedItemResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SpaceResult;
import com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MdeSharingHelper {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MDE_QUOTA_TYPE {
        NONE,
        UNLIMITED,
        LIMITED
    }

    public static void changeSpaceCover(FileItemInterface fileItemInterface, String str, Consumer<Integer> consumer) {
        Map<String, Object> spaceMetaData = MediaItemMde.getSpaceMetaData(fileItemInterface);
        spaceMetaData.put("coverMediaId", MediaItemMde.getItemId(fileItemInterface));
        if (!MdeAlbumHelper.checkCoverRatio(str)) {
            str = "0, 0, 0, 0";
        }
        spaceMetaData.put("coverRectRatio", str);
        requestSpaceUpdate(MediaItemMde.getSpaceId(fileItemInterface), spaceMetaData, consumer);
    }

    private static void checkQuotaResult(BiConsumer<Long, Long> biConsumer, QuotaResult quotaResult) {
        if (quotaResult == null || quotaResult.getResult() == null) {
            biConsumer.accept(0L, 0L);
            return;
        }
        if (quotaResult.getResult().isUnlimited()) {
            setQuotaPreferenceValue(MDE_QUOTA_TYPE.UNLIMITED.ordinal());
        } else {
            setQuotaPreferenceValue(MDE_QUOTA_TYPE.LIMITED.ordinal());
        }
        biConsumer.accept(Long.valueOf(quotaResult.getResult().getTotalUsage()), Long.valueOf(quotaResult.getResult().getLimitUsage()));
    }

    public static void clearUnreadCount(String str) {
        MdeSharingService.getInstance().clearUnreadCount(str);
    }

    private static ShareApi.SharedItemWithMediaServiceContentIdRequest createSharedCloudItemRequest(FileItemInterface fileItemInterface) {
        ShareApi.SharedItemWithMediaServiceContentIdRequest sharedItemWithMediaServiceContentIdRequest = new ShareApi.SharedItemWithMediaServiceContentIdRequest(fileItemInterface.getCloudServerId(), fileItemInterface.getMimeType());
        if (MdeAlbumHelper.isCloudOnlyItem(fileItemInterface)) {
            sharedItemWithMediaServiceContentIdRequest.setContentSize(fileItemInterface.getCloudOriginalSize());
            return sharedItemWithMediaServiceContentIdRequest;
        }
        sharedItemWithMediaServiceContentIdRequest.setContentSize(new SecureFile(fileItemInterface.getPath()).length());
        return sharedItemWithMediaServiceContentIdRequest;
    }

    private static ShareApi.SharedItemWithUriRequest createSharedItemRequest(Context context, FileItemInterface fileItemInterface) {
        Uri uri = ContentUri.getUri(fileItemInterface);
        if (uri == null) {
            uri = Uri.parse(fileItemInterface.getPath());
            context.grantUriPermission(CommonUtils.MOBILE_SERVICE_PACKAGE_NAME, uri, 1);
        }
        return new ShareApi.SharedItemWithUriRequest(uri, fileItemInterface.getMimeType());
    }

    public static void getFamilyQuota(BiConsumer<Long, Long> biConsumer) {
        checkQuotaResult(biConsumer, MdeSharingService.getInstance().requestFamilyQuota());
    }

    public static int getMaxUploadCount(Context context) {
        return new OpenSessionApi().getShareLimit(context).getMaxCountOfUpload();
    }

    private static String getMediaIds(List<FileItemInterface> list) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        for (FileItemInterface mediaId : list) {
            stringJoiner.add(Long.toString(mediaId.getMediaId()));
        }
        return stringJoiner.toString();
    }

    public static void getMyQuota(BiConsumer<Long, Long> biConsumer) {
        checkQuotaResult(biConsumer, MdeSharingService.getInstance().requestMyQuota());
    }

    public static Bundle getShareBundleNotiMessages(Context context, List<FileItemInterface> list) {
        String str;
        String str2;
        Bundle bundle = null;
        if (list != null && !list.isEmpty()) {
            LinkedList linkedList = new LinkedList(list);
            MediaType mediaTypeOfItems = MdeAlbumHelper.getMediaTypeOfItems(linkedList);
            int sharingItemCompleted = MdeServiceString.getSharingItemCompleted(mediaTypeOfItems);
            int sharingItemPreparing = MdeServiceString.getSharingItemPreparing(mediaTypeOfItems);
            boolean z = true;
            if (linkedList.size() <= 1) {
                z = false;
            }
            if (z) {
                str = ShareApi.EXTRA_SHARE_NOTI_ALL_ITEMS_UPLOADED;
            } else {
                str = ShareApi.EXTRA_SHARE_NOTI_ONE_ITEM_UPLOADED;
            }
            if (z) {
                str2 = ShareApi.EXTRA_SHARE_NOTI_ITEMS_UPLOAD_PREPARING;
            } else {
                str2 = ShareApi.EXTRA_SHARE_NOTI_ONE_ITEM_UPLOAD_PREPARING;
            }
            if (sharingItemCompleted != -1) {
                bundle = new Bundle();
                bundle.putString(str, context.getResources().getString(sharingItemCompleted));
            }
            if (sharingItemPreparing != -1) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putString(str2, context.getResources().getString(sharingItemPreparing));
            }
        }
        return bundle;
    }

    private static ShareApi.ActionIntent getShareClickActionIntent(Context context, String str, String str2) {
        ShareApi.ActionIntent actionIntent = new ShareApi.ActionIntent();
        actionIntent.setShareCompleteIntent(AndroidCompat.createActivityPendingIntent(context, 0, MdeNotificationManager.getInstance(context).createOpenSharedAlbumDetailViewIntent(str, str2), 134217728));
        actionIntent.setQuotaExceededErrorIntent(AndroidCompat.createActivityPendingIntent(context, 1, MdeNotificationManager.getInstance(context).createOpenSharingStorageUseIntent(), 134217728));
        return actionIntent;
    }

    private static PendingIntent getShareClickPendingIntent(Context context, String str, String str2) {
        return AndroidCompat.createActivityPendingIntent(context, str.hashCode(), MdeNotificationManager.getInstance(context).createOpenSharedAlbumDetailViewIntent(str, str2), 134217728);
    }

    public static ShareApi.NotificationMessage getShareNotificationMessages(Context context, List<FileItemInterface> list) {
        ShareApi.NotificationMessage notificationMessage = new ShareApi.NotificationMessage();
        if (list != null && !list.isEmpty()) {
            LinkedList linkedList = new LinkedList(list);
            MediaType mediaTypeOfItems = MdeAlbumHelper.getMediaTypeOfItems(linkedList);
            boolean z = true;
            if (linkedList.size() <= 1) {
                z = false;
            }
            int sharingItemCompleted = MdeServiceString.getSharingItemCompleted(mediaTypeOfItems);
            if (sharingItemCompleted != -1) {
                if (z) {
                    notificationMessage.setMessageForAllItemsUploaded(context.getResources().getString(sharingItemCompleted));
                } else {
                    notificationMessage.setMessageForOneItemUploaded(context.getResources().getString(sharingItemCompleted));
                }
            }
            int sharingItemPreparing = MdeServiceString.getSharingItemPreparing(mediaTypeOfItems);
            if (sharingItemPreparing != -1) {
                if (z) {
                    notificationMessage.setMessageForPreparingUploadMultiItems(context.getResources().getString(sharingItemPreparing));
                } else {
                    notificationMessage.setMessageForPreparingUploadOneItem(context.getResources().getString(sharingItemPreparing));
                }
            }
        }
        notificationMessage.setMessageForQuotaExceededError(MdeServiceString.getMessageForQuotaExceededError(context));
        return notificationMessage;
    }

    private static ShareApi.SharedItemRequest getSharedItemRequest(Context context, FileItemInterface fileItemInterface, String str, String str2, Map<String, Object> map) {
        Uri uri = FileProviderUtil.getUri(context, str);
        context.grantUriPermission(CommonUtils.MOBILE_SERVICE_PACKAGE_NAME, uri, 1);
        ShareApi.SharedItemWithUriRequest sharedItemWithUriRequest = new ShareApi.SharedItemWithUriRequest(uri, fileItemInterface.getMimeType());
        sharedItemWithUriRequest.setItemId(MediaItemMde.getItemId(fileItemInterface));
        sharedItemWithUriRequest.setTitle(str2);
        sharedItemWithUriRequest.setMemo("");
        sharedItemWithUriRequest.setMetaData(MdeMediaItemHelper.createMetaDataMap(fileItemInterface, (String[]) null));
        sharedItemWithUriRequest.setInstantMetaData(map);
        return sharedItemWithUriRequest;
    }

    public static boolean isQuotaPreferenceValueEqual(int i2) {
        if (GalleryPreference.getInstance().loadInt(PreferenceName.MDE_QUOTA_TYPE, MDE_QUOTA_TYPE.NONE.ordinal()) == i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestItemDeletion$9(List list, AtomicInteger atomicInteger, int i2, BiConsumer biConsumer, ItemListResult itemListResult) {
        list.addAll(itemListResult.getFailureList());
        Log.shv("MdeSharingHelper", "requestItemDeletion {" + atomicInteger.get() + "/" + i2 + "}");
        if (atomicInteger.getAndIncrement() >= i2) {
            biConsumer.accept(list, Integer.valueOf(itemListResult.getStatus().getCode()));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestShare$3(BiConsumer biConsumer, SpaceResult spaceResult) {
        String str;
        Space result = spaceResult.getResult();
        Integer valueOf = Integer.valueOf(spaceResult.getStatus().getCode());
        if (result != null) {
            str = result.getSpaceId();
        } else {
            str = spaceResult.getStatus().getMessage();
        }
        biConsumer.accept(valueOf, str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestShareItem$1(BiConsumer biConsumer, SharedItemResult sharedItemResult) {
        String str;
        SharedItem result = sharedItemResult.getResult();
        Uri uri = null;
        if (result != null) {
            str = result.getStreamingUrl();
        } else {
            str = null;
        }
        Integer valueOf = Integer.valueOf(sharedItemResult.getStatus().getCode());
        if (!TextUtils.isEmpty(str)) {
            uri = Uri.parse(str);
        }
        biConsumer.accept(valueOf, uri);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestThumbnailDownload$11(BiConsumer biConsumer, DownloadImageResult downloadImageResult) {
        int code = downloadImageResult.getStatus().getCode();
        if (MdeResultCode.isSuccess(code)) {
            downloadImageResult.getResult().getImageUri();
            downloadImageResult.getResult().getHash();
            downloadImageResult.getResult().getItemId();
        }
        biConsumer.accept(Integer.valueOf(code), downloadImageResult.getStatus().getMessage());
    }

    public static void requestAddContents(Context context, String str, String str2, List<FileItemInterface> list, Consumer<SharedItemListResult> consumer) {
        ArrayList arrayList = new ArrayList();
        Map hashMap = new HashMap();
        if (PreferenceFeatures.OneUi6x.SUPPORT_SHARE_STORY && SdkConfig.atLeast(SdkConfig.GED.T)) {
            hashMap = DbCompat.storyApi().getStoryTotalCropInfo(getMediaIds(list));
        }
        for (FileItemInterface next : list) {
            Map<String, Object> createMetaDataMap = MdeMediaItemHelper.createMetaDataMap(next, (String[]) hashMap.get(Long.valueOf(next.getMediaId())));
            String replace = next.getDisplayName().replace("/", "");
            if (MdeAlbumHelper.isCloudExistedItem(next)) {
                ShareApi.SharedItemWithMediaServiceContentIdRequest createSharedCloudItemRequest = createSharedCloudItemRequest(next);
                createSharedCloudItemRequest.setContentName(replace);
                createSharedCloudItemRequest.setTitle(replace);
                createSharedCloudItemRequest.setMemo("");
                createSharedCloudItemRequest.setMetaData(createMetaDataMap);
                arrayList.add(createSharedCloudItemRequest);
            } else {
                ShareApi.SharedItemWithUriRequest createSharedItemRequest = createSharedItemRequest(context, next);
                createSharedItemRequest.setTitle(replace);
                createSharedItemRequest.setMemo("");
                createSharedItemRequest.setMetaData(createMetaDataMap);
                arrayList.add(createSharedItemRequest);
            }
        }
        if (Features.isEnabled(Features.SUPPORT_PRE_THUMB_BEFORE_UPLOADING)) {
            requestBulkShare(context, str, str2, arrayList, list, consumer);
        } else {
            requestShare(context, str, str2, arrayList, list, consumer);
        }
    }

    private static void requestBulkShare(Context context, String str, String str2, ArrayList<ShareApi.SharedItemRequest> arrayList, List<FileItemInterface> list, final Consumer<SharedItemListResult> consumer) {
        String str3 = str;
        ArrayList<ShareApi.SharedItemRequest> arrayList2 = arrayList;
        MdeSharingService.getInstance().requestBulkShare(str3, arrayList2, getShareClickActionIntent(context, str, str2), getShareNotificationMessages(context, list), true, new ShareApi.ShareUploadResultCallback<SharedItemListResult>() {
            public void onResult(SharedItemListResult sharedItemListResult) {
                consumer.accept(sharedItemListResult);
            }

            public void onProgress(ShareSnapshot shareSnapshot) {
            }

            public void onUploadComplete(ShareSnapshot shareSnapshot) {
            }
        });
    }

    public static void requestDeleteFromTrash(String str, String str2, List<FileItemInterface> list, BiConsumer<List<ItemListResult.SharedItemListFailureResult>, Integer> biConsumer) {
        MdeSharingService.getInstance().requestDeleteItemListFromTrashBin(str, str2, (List) list.stream().map(new i(10)).collect(Collectors.toList()), new c(biConsumer, 6));
    }

    public static void requestEmptyFromTrash(String str, String str2, BiConsumer<List<ItemListResult.SharedItemListFailureResult>, Integer> biConsumer) {
        MdeSharingService.getInstance().requestEmptyFromTrashBin(str, str2, new c(biConsumer, 5));
    }

    public static void requestItemDeletion(List<FileItemInterface> list, BiConsumer<List<ItemListResult.SharedItemListFailureResult>, Integer> biConsumer) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ((Map) list.stream().map(new i(10)).collect(Collectors.groupingBy(new a(4, new AtomicInteger(0))))).forEach(new f(((int) Math.ceil(((double) list.size()) / 100.0d)) - 1, MediaItemMde.getSpaceId(list.get(0)), atomicInteger, biConsumer));
    }

    public static void requestItemDownload(String str, List<String> list, PendingIntent pendingIntent, Bundle bundle, final Consumer<ContentDownloadResult> consumer) {
        MdeSharingService.getInstance().requestSharedContentDownload(str, list, new ShareApi.ContentDownloadingResultCallback() {
            public void onResult(ContentDownloadResult contentDownloadResult) {
                consumer.accept(contentDownloadResult);
            }

            public void onProgress(SharedContentDownloadSnapshot sharedContentDownloadSnapshot) {
            }
        }, pendingIntent, bundle);
    }

    public static void requestItemDownloadToHiddenFolder(String str, final List<String> list, final Consumer<ContentDownloadResult> consumer) {
        MdeSharingService.getInstance().requestSharedContentDownloadToHiddenFolder(str, list, new ShareApi.ContentDownloadingResultCallback() {
            public void onResult(ContentDownloadResult contentDownloadResult) {
                consumer.accept(contentDownloadResult);
            }

            public void onProgress(SharedContentDownloadSnapshot sharedContentDownloadSnapshot) {
            }
        });
    }

    public static void requestMoveToTrash(String str, String str2, List<FileItemInterface> list, BiConsumer<List<ItemListResult.SharedItemListFailureResult>, Integer> biConsumer) {
        MdeSharingService.getInstance().requestMoveItemListToTrashBin(str, str2, (List) list.stream().map(new i(10)).collect(Collectors.toList()), new c(biConsumer, 8));
    }

    public static void requestRestoreFromTrash(String str, String str2, List<FileItemInterface> list, BiConsumer<List<ItemListResult.SharedItemListFailureResult>, Integer> biConsumer) {
        MdeSharingService.getInstance().requestRestoreItemListFromTrashBin(str, str2, (List) list.stream().map(new i(10)).collect(Collectors.toList()), new c(biConsumer, 7));
    }

    public static void requestShare(String str, String str2, String str3, BiConsumer<Integer, String> biConsumer) {
        ShareApi.SpaceWithMediaServiceContentIdRequest spaceWithMediaServiceContentIdRequest = new ShareApi.SpaceWithMediaServiceContentIdRequest(str);
        if (PreferenceFeatures.OneUi6x.SUPPORT_SHARE_STORY && !TextUtils.isEmpty(str3)) {
            spaceWithMediaServiceContentIdRequest.setMemo(str3);
        }
        MdeSharingService.getInstance().requestSpaceCreation(str2, spaceWithMediaServiceContentIdRequest, new c(biConsumer, 10));
    }

    public static int requestShareItem(String str, String str2, BiConsumer<Integer, Uri> biConsumer) {
        return MdeSharingService.getInstance().requestShareItem(str, str2, new c(biConsumer, 4));
    }

    public static void requestShareItemSync(String str, BiConsumer<Integer, String> biConsumer) {
        MdeSharingService.getInstance().requestShareItemSync(str, new c(biConsumer, 9));
    }

    public static void requestShareItemUpdate(Context context, String str, String str2, FileItemInterface fileItemInterface, Map<String, Object> map, final Consumer<Integer> consumer) {
        String str3 = str2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSharedItemRequest(context, fileItemInterface, str, str3, map));
        MdeSharingService.getInstance().requestSharedItemUpdate(MediaItemMde.getSpaceId(fileItemInterface), arrayList, new ShareApi.ShareUploadResultCallback<SharedItemListResult>() {
            public void onResult(SharedItemListResult sharedItemListResult) {
                consumer.accept(Integer.valueOf(sharedItemListResult.getStatus().getCode()));
            }

            public void onProgress(ShareSnapshot shareSnapshot) {
            }

            public void onUploadComplete(ShareSnapshot shareSnapshot) {
            }
        }, (PendingIntent) null, (Bundle) null);
    }

    public static void requestSpaceDeletion(List<FileItemInterface> list, Consumer<Integer> consumer) {
        list.stream().map(new o(15)).forEach(new e(0, consumer));
    }

    public static void requestSpaceListSync(boolean z, BiConsumer<Integer, String> biConsumer) {
        MdeSharingService.getInstance().requestSpaceListSync(z, new c(biConsumer, 2));
    }

    public static void requestSpaceUpdate(String str, String str2, String str3, Consumer<Integer> consumer) {
        MdeSharingService.getInstance().requestSpaceUpdate(str, str2, str3, new D9.a(5, consumer));
    }

    public static void requestThumbnailDownload(String str, String str2, String str3, String str4, String str5, BiConsumer<Integer, String> biConsumer) {
        MdeSharingService.getInstance().requestThumbnailDownload(str, str2, str3, str4, str5, new c(biConsumer, 3));
    }

    public static int requestWebLinkEnabled(String str, String str2, boolean z, Consumer<SpaceResult> consumer) {
        MdeSharingService instance = MdeSharingService.getInstance();
        Objects.requireNonNull(consumer);
        return instance.requestWebLinkEnabled(str, str2, z, new D9.a(3, consumer));
    }

    public static void setQuotaPreferenceValue(int i2) {
        GalleryPreference.getInstance().saveState(PreferenceName.MDE_QUOTA_TYPE, i2);
    }

    public static void requestSpaceUpdate(String str, Map<?, ?> map, Consumer<Integer> consumer) {
        MdeSharingService.getInstance().requestSpaceUpdate(str, map, new D9.a(4, consumer));
    }

    private static void requestShare(Context context, String str, String str2, ArrayList<ShareApi.SharedItemRequest> arrayList, List<FileItemInterface> list, Consumer<SharedItemListResult> consumer) {
        final Consumer<SharedItemListResult> consumer2 = consumer;
        AnonymousClass3 r72 = new ShareApi.ShareResultCallback() {
            public void onResult(SharedItemListResult sharedItemListResult) {
                consumer2.accept(sharedItemListResult);
            }

            public void onProgress(ShareSnapshot shareSnapshot) {
            }

            public void onUploadComplete(ShareSnapshot shareSnapshot) {
            }
        };
        if (PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE) {
            Context context2 = context;
            MdeSharingService.getInstance().requestShare(str, (List<ShareApi.SharedItemRequest>) arrayList, getShareClickActionIntent(context2, str, str2), getShareNotificationMessages(context2, list), (ShareApi.ShareResultCallback) r72);
            return;
        }
        ArrayList<ShareApi.SharedItemRequest> arrayList2 = arrayList;
        String str3 = str2;
        ArrayList<ShareApi.SharedItemRequest> arrayList3 = arrayList2;
        Context context3 = context;
        MdeSharingService instance = MdeSharingService.getInstance();
        PendingIntent shareClickPendingIntent = getShareClickPendingIntent(context3, str, str3);
        Bundle shareBundleNotiMessages = getShareBundleNotiMessages(context3, list);
        instance.requestShare(str, (List<ShareApi.SharedItemRequest>) arrayList3, (ShareApi.ShareResultCallback) r72, shareClickPendingIntent, shareBundleNotiMessages);
    }
}
