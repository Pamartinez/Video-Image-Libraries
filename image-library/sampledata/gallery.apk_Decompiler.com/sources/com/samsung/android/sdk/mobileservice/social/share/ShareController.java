package com.samsung.android.sdk.mobileservice.social.share;

import android.os.Bundle;
import android.os.RemoteException;
import c0.C0086a;
import com.samsung.android.sdk.mobileservice.common.exception.NotConnectedException;
import com.samsung.android.sdk.mobileservice.social.IMobileServiceSocial;
import com.samsung.android.sdk.mobileservice.social.share.IShareStatusCallback;
import com.samsung.android.sdk.mobileservice.util.SdkLog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareController {
    public static final int SHARE_COMPLETE = 2;
    public static final int SHARE_IN_PROGRESS = 100;
    public static final int SHARE_NONE = -1;
    public static final int SHARE_PAUSED = 1;
    private static final String TAG = "ShareController";
    private final ShareControllerApiPicker mApi;
    /* access modifiers changed from: private */
    public ShareStatusListener mListener;
    /* access modifiers changed from: private */
    public String mRequestId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ShareControllerApiPicker {
        String getAppId();

        String getReference();

        IMobileServiceSocial getSocialService();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ShareStatusListener {
        void onComplete(ShareSnapshot shareSnapshot);

        void onPause(ShareSnapshot shareSnapshot);

        void onResume(ShareSnapshot shareSnapshot);
    }

    public ShareController(ShareControllerApiPicker shareControllerApiPicker, String str) {
        SdkLog.d(TAG, "ShareController " + shareControllerApiPicker.getReference());
        this.mApi = shareControllerApiPicker;
        this.mRequestId = str;
    }

    @Deprecated
    public int cancel() {
        SdkLog.d(TAG, "cancel : mRequestId=[" + this.mRequestId + "] " + SdkLog.getReference(this.mListener));
        if (this.mApi.getAppId() == null) {
            SdkLog.d(TAG, "app id is null " + this.mApi.getReference());
            return -1;
        }
        try {
            this.mApi.getSocialService().cancelShare(this.mApi.getAppId(), this.mRequestId);
            return 1;
        } catch (RemoteException e) {
            SdkLog.s(e);
            return -1;
        } catch (NotConnectedException e7) {
            SdkLog.s(e7);
            return -8;
        }
    }

    @Deprecated
    public int getStatus() {
        if (this.mApi.getAppId() == null) {
            SdkLog.d(TAG, "app id is null " + this.mApi.getReference());
            return -1;
        }
        try {
            int shareStatus = this.mApi.getSocialService().getShareStatus(this.mApi.getAppId(), this.mRequestId);
            StringBuilder o2 = C0086a.o(shareStatus, "getStatus : status=[", "], mRequestId=[");
            o2.append(this.mRequestId);
            o2.append("] ");
            o2.append(SdkLog.getReference(this.mListener));
            SdkLog.d(TAG, o2.toString());
            return shareStatus;
        } catch (RemoteException e) {
            SdkLog.s(e);
            return -1;
        } catch (NotConnectedException e7) {
            SdkLog.s(e7);
            return -8;
        }
    }

    @Deprecated
    public int pause() {
        SdkLog.d(TAG, "pause : mRequestId=[" + this.mRequestId + "] " + SdkLog.getReference(this.mListener));
        if (this.mApi.getAppId() == null) {
            SdkLog.d(TAG, "app id is null " + this.mApi.getReference());
            return -1;
        }
        try {
            this.mApi.getSocialService().pauseShare(this.mApi.getAppId(), this.mRequestId);
            return 1;
        } catch (RemoteException e) {
            SdkLog.s(e);
            return -1;
        } catch (NotConnectedException e7) {
            SdkLog.s(e7);
            return -8;
        }
    }

    @Deprecated
    public int resume() {
        SdkLog.d(TAG, "resume : mRequestId=[" + this.mRequestId + "] " + SdkLog.getReference(this.mListener));
        if (this.mApi.getAppId() == null) {
            SdkLog.d(TAG, "app id is null " + this.mApi.getReference());
            return -1;
        }
        try {
            this.mApi.getSocialService().resumeShare(this.mApi.getAppId(), this.mRequestId);
            return 1;
        } catch (RemoteException e) {
            SdkLog.s(e);
            return -1;
        } catch (NotConnectedException e7) {
            SdkLog.s(e7);
            return -8;
        }
    }

    @Deprecated
    public int setShareStatusListener(ShareStatusListener shareStatusListener) {
        SdkLog.d(TAG, "setShareStatusListener : mRequestId=[" + this.mRequestId + "] " + SdkLog.getReference(this.mListener));
        if (this.mApi.getAppId() == null) {
            SdkLog.d(TAG, "app id is null " + this.mApi.getReference());
            return -1;
        } else if (shareStatusListener == null || this.mListener != null) {
            this.mListener = shareStatusListener;
            return 1;
        } else {
            AnonymousClass1 r1 = new IShareStatusCallback.Stub() {
                public void onComplete(Bundle bundle) {
                    Bundle bundle2 = bundle;
                    SdkLog.d(ShareController.TAG, "setShareStatusListener onComplete : mRequestId=[" + ShareController.this.mRequestId + "] " + SdkLog.getReference(ShareController.this.mListener));
                    if (ShareController.this.mListener != null) {
                        ShareController.this.mListener.onComplete(new ShareSnapshot(bundle2.getLong(BundleKey.totalBytes, 0), bundle2.getLong(BundleKey.totalBytesTransferred, 0), bundle2.getInt(BundleKey.totalFileCount, 0), bundle2.getInt(BundleKey.totalFileCountTransferred, 0), bundle2.getLong(BundleKey.currentFileBytes, 0), bundle2.getLong(BundleKey.currentFileBytesTransferred, 0), bundle2.getInt(BundleKey.currentFileIndex, 0)));
                    }
                }

                public void onPause(Bundle bundle) {
                    Bundle bundle2 = bundle;
                    SdkLog.d(ShareController.TAG, "setShareStatusListener onPause : mRequestId=[" + ShareController.this.mRequestId + "] " + SdkLog.getReference(ShareController.this.mListener));
                    if (ShareController.this.mListener != null) {
                        ShareController.this.mListener.onPause(new ShareSnapshot(bundle2.getLong(BundleKey.totalBytes, 0), bundle2.getLong(BundleKey.totalBytesTransferred, 0), bundle2.getInt(BundleKey.totalFileCount, 0), bundle2.getInt(BundleKey.totalFileCountTransferred, 0), bundle2.getLong(BundleKey.currentFileBytes, 0), bundle2.getLong(BundleKey.currentFileBytesTransferred, 0), bundle2.getInt(BundleKey.currentFileIndex, 0)));
                    }
                }

                public void onResume(Bundle bundle) {
                    Bundle bundle2 = bundle;
                    SdkLog.d(ShareController.TAG, "setShareStatusListener onResume : mRequestId=[" + ShareController.this.mRequestId + "] " + SdkLog.getReference(ShareController.this.mListener));
                    if (ShareController.this.mListener != null) {
                        ShareController.this.mListener.onResume(new ShareSnapshot(bundle2.getLong(BundleKey.totalBytes, 0), bundle2.getLong(BundleKey.totalBytesTransferred, 0), bundle2.getInt(BundleKey.totalFileCount, 0), bundle2.getInt(BundleKey.totalFileCountTransferred, 0), bundle2.getLong(BundleKey.currentFileBytes, 0), bundle2.getLong(BundleKey.currentFileBytesTransferred, 0), bundle2.getInt(BundleKey.currentFileIndex, 0)));
                    }
                }
            };
            this.mListener = shareStatusListener;
            try {
                this.mApi.getSocialService().setShareStatusListener(this.mApi.getAppId(), this.mRequestId, r1);
                return 1;
            } catch (RemoteException e) {
                SdkLog.s(e);
                return -1;
            } catch (NotConnectedException e7) {
                SdkLog.s(e7);
                return -8;
            }
        }
    }
}
