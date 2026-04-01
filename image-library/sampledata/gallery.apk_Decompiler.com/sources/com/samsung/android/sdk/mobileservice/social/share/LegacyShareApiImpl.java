package com.samsung.android.sdk.mobileservice.social.share;

import android.app.PendingIntent;
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
import com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.IShareResultWithFileListCallback;
import com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;
import com.samsung.android.sdk.mobileservice.social.share.ShareController;
import com.samsung.android.sdk.mobileservice.social.share.result.SharedItemListResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SharedItemWithUriListResult;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LegacyShareApiImpl extends SeMobileServiceApi {
    private static final int FEATURE_ID_INVALID = -1;
    private static final String TAG = "LegacyShareApiImpl";
    private final String APP_ID_REMINDER = "8o8b82h22a";
    private final ShareController.ShareControllerApiPicker mApiPicker = new ShareController.ShareControllerApiPicker() {
        public String getAppId() {
            return LegacyShareApiImpl.this.getAppId();
        }

        public String getReference() {
            return LegacyShareApiImpl.this.getReference();
        }

        public IMobileServiceSocial getSocialService() {
            return LegacyShareApiImpl.this.getSocialService();
        }
    };

    public LegacyShareApiImpl(SeMobileServiceSession seMobileServiceSession) {
        super(seMobileServiceSession, "ShareApi");
        checkAuthorized(0, 2);
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
                LegacyShareApiImpl legacyShareApiImpl = LegacyShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestSync onFailure : code=[", "], message=[", str);
                p6.append("] ");
                legacyShareApiImpl.infoLog(p6.toString());
                if (shareSyncResultCallback != null) {
                    shareSyncResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                }
            }

            public void onSuccess() {
                LegacyShareApiImpl.this.infoLog("requestSync onSuccess ");
                ShareApi.ShareSyncResultCallback shareSyncResultCallback = shareSyncResultCallback;
                if (shareSyncResultCallback != null) {
                    shareSyncResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                }
            }
        };
    }

    public String[] getEssentialServiceNames() {
        return new String[]{"SocialService"};
    }

    public String getTag() {
        return TAG;
    }

    public ShareController requestShare(String str, List<ShareApi.SharedItemRequest> list, int i2, final ShareApi.ShareResultCallback shareResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        infoLog("requestShare : spaceId=[" + str + "] ");
        if (i2 == 501) {
            infoLog("requestShare is not supported in " + i2);
            return null;
        } else if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return null;
        } else {
            AnonymousClass3 r62 = new IShareResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    LegacyShareApiImpl legacyShareApiImpl = LegacyShareApiImpl.this;
                    StringBuilder p6 = C0086a.p(j2, "requestShare onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    legacyShareApiImpl.infoLog(p6.toString());
                    if (shareResultCallback != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(j2);
                        LegacyShareApiImpl legacyShareApiImpl2 = LegacyShareApiImpl.this;
                        legacyShareApiImpl2.debugLog("requestShare Error Message [" + str + "]");
                        shareResultCallback.onResult(new SharedItemListResult(new CommonResultStatus(convertErrorcode, str, Long.toString(j2)), (List<SharedItem>) null, (List<ShareApi.SharedItemRequest>) null));
                    }
                }

                public void onProgress(Bundle bundle) {
                    LegacyShareApiImpl.this.debugLog("requestShare onProgress ");
                    ShareApi.ShareResultCallback shareResultCallback = shareResultCallback;
                    if (shareResultCallback != null) {
                        shareResultCallback.onProgress(ShareApiUtil.getInstance(LegacyShareApiImpl.this.getTag(), LegacyShareApiImpl.this.getReference(), LegacyShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                    }
                }

                public void onSuccess(List<Bundle> list, List<Bundle> list2) {
                    LegacyShareApiImpl.this.infoLog("requestShare onSuccess ");
                    if (shareResultCallback != null) {
                        ArrayList arrayList = new ArrayList();
                        for (Bundle createSharedItemResult : list) {
                            arrayList.add(ShareApiUtil.getInstance(LegacyShareApiImpl.this.getTag(), LegacyShareApiImpl.this.getReference(), LegacyShareApiImpl.this.getAppId()).createSharedItemResult(createSharedItemResult));
                        }
                        ArrayList arrayList2 = new ArrayList();
                        for (Bundle createShareFailedItemResult : list2) {
                            arrayList2.add(ShareApiUtil.getInstance(LegacyShareApiImpl.this.getTag(), LegacyShareApiImpl.this.getReference(), LegacyShareApiImpl.this.getAppId()).createShareFailedItemResult(createShareFailedItemResult));
                        }
                        shareResultCallback.onResult(new SharedItemListResult(new CommonResultStatus(1), arrayList, arrayList2));
                    }
                }

                public void onUploadComplete(Bundle bundle) {
                    LegacyShareApiImpl.this.debugLog("requestShare onUploadComplete ");
                    ShareApi.ShareResultCallback shareResultCallback = shareResultCallback;
                    if (shareResultCallback != null) {
                        shareResultCallback.onUploadComplete(ShareApiUtil.getInstance(LegacyShareApiImpl.this.getTag(), LegacyShareApiImpl.this.getReference(), LegacyShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                    }
                }
            };
            try {
                ArrayList arrayList = new ArrayList();
                for (ShareApi.SharedItemRequest requestToBundle : list) {
                    arrayList.add(ShareApiUtil.getInstance(getTag(), getReference(), getAppId()).requestToBundle(requestToBundle));
                }
                return new ShareController(this.mApiPicker, getSocialService().requestShareWithPendingIntent(getAppId(), str, arrayList, r62, pendingIntent, bundle));
            } catch (RemoteException | NotConnectedException | NullPointerException e) {
                secureLog(e);
                return null;
            }
        }
    }

    public int requestSync(String str, int i2, boolean z, String str2, ShareApi.ShareSyncResultCallback shareSyncResultCallback) {
        infoLog("requestSync with needToShowPermissionUI - syncType : " + str2 + " isPermissionUINeeded : " + z);
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_12_2_2) || checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return -1;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean(BundleKey.EXTRA_NEED_TO_SHOW_PERMISSION_UI, z);
            bundle.putString(BundleKey.EXTRA_SYNC_TYPE, str2);
            if (i2 == 501) {
                bundle.putInt("featureId", i2);
            } else if (!TextUtils.isEmpty(str)) {
                bundle.putString(BundleKey.EXTRA_CID, str);
            }
            getSocialService().requestShareSyncWithData(getAppId(), bundle, createShareSyncCallback(shareSyncResultCallback));
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public ShareController requestShare(String str, List<ShareApi.SharedItemRequest> list, ShareApi.ActionIntent actionIntent, ShareApi.NotificationMessage notificationMessage, int i2, final ShareApi.ShareResultCallback shareResultCallback) {
        String str2;
        infoLog("requestShare : spaceId=[" + str + "] ");
        if (i2 == 501) {
            infoLog("requestShare is not supported in " + i2);
            return null;
        } else if (getAppId() == null) {
            infoLog("app id is null ");
            return null;
        } else {
            AnonymousClass4 r62 = new IShareResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    LegacyShareApiImpl legacyShareApiImpl = LegacyShareApiImpl.this;
                    StringBuilder p6 = C0086a.p(j2, "requestShare onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    legacyShareApiImpl.infoLog(p6.toString());
                    if (shareResultCallback != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(j2);
                        LegacyShareApiImpl legacyShareApiImpl2 = LegacyShareApiImpl.this;
                        legacyShareApiImpl2.debugLog("requestShare Error Message [" + str + "]");
                        shareResultCallback.onResult(new SharedItemListResult(new CommonResultStatus(convertErrorcode, str, Long.toString(j2)), (List<SharedItem>) null, (List<ShareApi.SharedItemRequest>) null));
                    }
                }

                public void onProgress(Bundle bundle) {
                    LegacyShareApiImpl.this.debugLog("requestShare onProgress ");
                    ShareApi.ShareResultCallback shareResultCallback = shareResultCallback;
                    if (shareResultCallback != null) {
                        shareResultCallback.onProgress(ShareApiUtil.getInstance(LegacyShareApiImpl.this.getTag(), LegacyShareApiImpl.this.getReference(), LegacyShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                    }
                }

                public void onSuccess(List<Bundle> list, List<Bundle> list2) {
                    LegacyShareApiImpl.this.infoLog("requestShare onSuccess ");
                    if (shareResultCallback != null) {
                        ArrayList arrayList = new ArrayList();
                        for (Bundle createSharedItemResult : list) {
                            arrayList.add(ShareApiUtil.getInstance(LegacyShareApiImpl.this.getTag(), LegacyShareApiImpl.this.getReference(), LegacyShareApiImpl.this.getAppId()).createSharedItemResult(createSharedItemResult));
                        }
                        ArrayList arrayList2 = new ArrayList();
                        for (Bundle createShareFailedItemResult : list2) {
                            arrayList2.add(ShareApiUtil.getInstance(LegacyShareApiImpl.this.getTag(), LegacyShareApiImpl.this.getReference(), LegacyShareApiImpl.this.getAppId()).createShareFailedItemResult(createShareFailedItemResult));
                        }
                        shareResultCallback.onResult(new SharedItemListResult(new CommonResultStatus(1), arrayList, arrayList2));
                    }
                }

                public void onUploadComplete(Bundle bundle) {
                    LegacyShareApiImpl.this.debugLog("requestShare onUploadComplete ");
                    ShareApi.ShareResultCallback shareResultCallback = shareResultCallback;
                    if (shareResultCallback != null) {
                        shareResultCallback.onUploadComplete(ShareApiUtil.getInstance(LegacyShareApiImpl.this.getTag(), LegacyShareApiImpl.this.getReference(), LegacyShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
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
                    AnonymousClass4 r72 = r62;
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

    public int requestSync(String str, int i2, SyncOption syncOption, ShareApi.ShareSyncResultCallback shareSyncResultCallback) {
        infoLog("requestSync with syncOption - syncType : " + syncOption.getSyncType().toString() + " isPermissionUINeeded : " + syncOption.isPermissionUINeeded() + " isSyncWithQuota : " + syncOption.isSyncWithQuota());
        if ((!isSupportedSemsAgentVersionBetween(CommonConstants.SupportedApiMinVersion.VERSION_10_8_6, CommonConstants.SupportedApiMinVersion.VERSION_11_0) && !isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_0_1)) || checkInvalidFeatureIdAndAgentVersion(i2)) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        try {
            Bundle bundle = new Bundle();
            if (i2 == 501) {
                bundle.putInt("featureId", i2);
            } else if (!TextUtils.isEmpty(str)) {
                bundle.putString(BundleKey.EXTRA_CID, str);
            }
            bundle.putBoolean(BundleKey.EXTRA_NEED_TO_SHOW_PERMISSION_UI, syncOption.isPermissionUINeeded());
            bundle.putString(BundleKey.EXTRA_SYNC_TYPE, syncOption.getSyncType().toString());
            bundle.putBoolean(BundleKey.EXTRA_SYNC_IS_SYNC_WITH_QUOTA, syncOption.isSyncWithQuota());
            getSocialService().requestShareSyncWithOption(getAppId(), bundle, createShareSyncCallback(shareSyncResultCallback));
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public ShareController requestShare(String str, ShareApi.SharedItemWithUriListRequest sharedItemWithUriListRequest, int i2, final ShareApi.SharedItemWithContentListResultCallback sharedItemWithContentListResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        String str2;
        infoLog("requestShare : spaceId=[" + str + "] ");
        if (checkInvalidFeatureIdAndAgentVersion(i2)) {
            return null;
        }
        if (TextUtils.isEmpty(getAppId())) {
            infoLog("app id is empty ");
            return null;
        }
        AnonymousClass5 r62 = new IShareResultWithFileListCallback.Stub() {
            public void onFailure(long j2, String str) {
                LegacyShareApiImpl legacyShareApiImpl = LegacyShareApiImpl.this;
                StringBuilder p6 = C0086a.p(j2, "requestShare onFailure : code=[", "], message=[", str);
                p6.append("] ");
                legacyShareApiImpl.infoLog(p6.toString());
                if (sharedItemWithContentListResultCallback != null) {
                    int convertErrorcode = ErrorCodeConvertor.convertErrorcode(j2);
                    LegacyShareApiImpl legacyShareApiImpl2 = LegacyShareApiImpl.this;
                    legacyShareApiImpl2.debugLog("requestShare Error Message [" + str + "]");
                    sharedItemWithContentListResultCallback.onResult(new SharedItemWithUriListResult(new CommonResultStatus(convertErrorcode, str, Long.toString(j2)), (SharedItemWithUriList) null));
                }
            }

            public void onProgress(Bundle bundle) {
                LegacyShareApiImpl.this.debugLog("requestShare onProgress ");
                ShareApi.SharedItemWithContentListResultCallback sharedItemWithContentListResultCallback = sharedItemWithContentListResultCallback;
                if (sharedItemWithContentListResultCallback != null) {
                    sharedItemWithContentListResultCallback.onProgress(ShareApiUtil.getInstance(LegacyShareApiImpl.this.getTag(), LegacyShareApiImpl.this.getReference(), LegacyShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                }
            }

            public void onSuccess(Bundle bundle) {
                LegacyShareApiImpl.this.infoLog("requestShare IShareResultWithAttachedFilesCallback onSuccess ");
                sharedItemWithContentListResultCallback.onResult(new SharedItemWithUriListResult(new CommonResultStatus(1), new SharedItemWithUriList(bundle)));
            }

            public void onUploadComplete(Bundle bundle) {
                LegacyShareApiImpl.this.debugLog("requestShare onUploadComplete ");
                ShareApi.SharedItemWithContentListResultCallback sharedItemWithContentListResultCallback = sharedItemWithContentListResultCallback;
                if (sharedItemWithContentListResultCallback != null) {
                    sharedItemWithContentListResultCallback.onUploadComplete(ShareApiUtil.getInstance(LegacyShareApiImpl.this.getTag(), LegacyShareApiImpl.this.getReference(), LegacyShareApiImpl.this.getAppId()).bundleToShareSnapShot(bundle));
                }
            }
        };
        if (i2 == 501) {
            AnonymousClass5 r72 = r62;
            try {
                Bundle bundle2 = new Bundle();
                bundle2.putInt("featureId", i2);
                str2 = getSocialService().requestShareWithFileListWithData(getAppId(), str, ShareApiUtil.getInstance(getTag(), getReference(), getAppId()).requestToBundle(sharedItemWithUriListRequest), bundle2, r72, pendingIntent, bundle);
            } catch (RemoteException | NotConnectedException | NullPointerException e) {
                secureLog(e);
                return null;
            }
        } else {
            str2 = getSocialService().requestShareWithFileList(getAppId(), str, ShareApiUtil.getInstance(getTag(), getReference(), getAppId()).requestToBundle(sharedItemWithUriListRequest), r62, pendingIntent, bundle);
        }
        return new ShareController(this.mApiPicker, str2);
    }
}
