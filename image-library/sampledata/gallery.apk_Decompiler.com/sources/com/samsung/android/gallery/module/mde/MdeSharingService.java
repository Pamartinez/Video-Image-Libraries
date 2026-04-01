package com.samsung.android.gallery.module.mde;

import A4.C0367b;
import Ab.b;
import Ad.j;
import Bb.l;
import D9.g;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.module.mde.abstraction.ConnectListener;
import com.samsung.android.gallery.module.mdebase.constants.MdeConstants;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.mobileservice.SeMobileService;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSessionFactory;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSessionImpl;
import com.samsung.android.sdk.mobileservice.auth.AuthApi;
import com.samsung.android.sdk.mobileservice.common.result.BooleanResult;
import com.samsung.android.sdk.mobileservice.social.SocialApi;
import com.samsung.android.sdk.mobileservice.social.datasync.DataSyncApi;
import com.samsung.android.sdk.mobileservice.social.datasync.DataSyncRequest;
import com.samsung.android.sdk.mobileservice.social.datasync.GallerySetting;
import com.samsung.android.sdk.mobileservice.social.datasync.result.DataSyncResult;
import com.samsung.android.sdk.mobileservice.social.group.GroupApi;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupInvitationListResult;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupInvitationResult;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupMemberResult;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupResult;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;
import com.samsung.android.sdk.mobileservice.social.share.SyncOption;
import com.samsung.android.sdk.mobileservice.social.share.result.DownloadImageResult;
import com.samsung.android.sdk.mobileservice.social.share.result.ItemListResult;
import com.samsung.android.sdk.mobileservice.social.share.result.OriginalFileListResult;
import com.samsung.android.sdk.mobileservice.social.share.result.QuotaResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SharedItemListResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SpaceResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MdeSharingService {
    AuthApi mAuthApi;
    boolean mAuthServiceEnabled = false;
    private final List<ConnectListenerData> mConnectListenerDataList = Collections.synchronizedList(new ArrayList());
    DataSyncApi mDataSyncApi;
    GroupApi mGroupApi;
    /* access modifiers changed from: private */
    public boolean mIsSessionConnecting = false;
    private Boolean mServiceAvailable;
    private Integer mServiceState;
    /* access modifiers changed from: private */
    public SeMobileServiceSessionImpl mSession;
    ShareApi mShareApi;
    SocialApi mSocialApi;
    boolean mSocialServiceEnabled = false;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ConnectListenerData {
        private final ConnectListener mConnectListener;
        private final int mRequestService;

        public ConnectListenerData(int i2, ConnectListener connectListener) {
            this.mRequestService = i2;
            this.mConnectListener = connectListener;
        }

        public ConnectListener getConnectListener() {
            return this.mConnectListener;
        }

        public int getRequestService() {
            return this.mRequestService;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class INSTANCE {
        static final MdeSharingService instance = new MdeSharingService();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class MobileServiceSessionResultCallback implements SeMobileServiceSession.ConnectionResultCallback {
        private final int mRequestService;

        public MobileServiceSessionResultCallback(int i2) {
            this.mRequestService = i2;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ec, code lost:
            if (r1.this$0.mAuthApi != null) goto L_0x00ee;
         */
        /* renamed from: prepareService */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void lambda$onSuccess$0(java.util.HashMap<java.lang.String, java.lang.Integer> r18) {
            /*
                r17 = this;
                r1 = r17
                r2 = r18
                java.lang.String r3 = "} +"
                java.lang.String r4 = "}, Social{"
                java.lang.String r5 = "connectSession Auth{"
                java.lang.String r6 = "MdeSharingService"
                java.lang.String r7 = "SocialService"
                java.lang.String r8 = "AuthService"
                java.lang.String r9 = "connectSession initialization failed. e="
                long r10 = java.lang.System.currentTimeMillis()
                r12 = 1
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.SeMobileServiceSessionImpl r0 = r0.mSession     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                java.lang.String r15 = "AuthApi"
                boolean r0 = r0.isSupportedApi(r15)     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                if (r0 == 0) goto L_0x004b
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.SeMobileServiceSessionImpl r0 = r0.mSession     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                boolean r0 = r0.isServiceConnected(r8)     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                if (r0 == 0) goto L_0x004b
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.auth.AuthApi r15 = new com.samsung.android.sdk.mobileservice.auth.AuthApi     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.SeMobileServiceSessionImpl r13 = r0.mSession     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                r15.<init>(r13)     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                r0.mAuthApi = r15     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                r0.mAuthServiceEnabled = r12     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                goto L_0x004b
            L_0x0045:
                r0 = move-exception
                goto L_0x01c9
            L_0x0048:
                r0 = move-exception
                goto L_0x018c
            L_0x004b:
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.SeMobileServiceSessionImpl r0 = r0.mSession     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                boolean r0 = r0.isServiceConnected(r7)     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                if (r0 == 0) goto L_0x00de
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.SeMobileServiceSessionImpl r0 = r0.mSession     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                java.lang.String r13 = "SocialApi"
                boolean r0 = r0.isSupportedApi(r13)     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                if (r0 == 0) goto L_0x0072
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.social.SocialApi r13 = new com.samsung.android.sdk.mobileservice.social.SocialApi     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.SeMobileServiceSessionImpl r15 = r0.mSession     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                r13.<init>(r15)     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                r0.mSocialApi = r13     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
            L_0x0072:
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.SeMobileServiceSessionImpl r0 = r0.mSession     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                java.lang.String r13 = "ShareApi"
                boolean r0 = r0.isSupportedApi(r13)     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                java.lang.String r13 = "32"
                if (r0 == 0) goto L_0x0093
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.social.share.ShareApi r15 = new com.samsung.android.sdk.mobileservice.social.share.ShareApi     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.SeMobileServiceSessionImpl r14 = r0.mSession     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                int r12 = java.lang.Integer.parseInt(r13)     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                r15.<init>((com.samsung.android.sdk.mobileservice.SeMobileServiceSession) r14, (int) r12)     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                r0.mShareApi = r15     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
            L_0x0093:
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.SeMobileServiceSessionImpl r0 = r0.mSession     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                java.lang.String r12 = "GroupApi"
                boolean r0 = r0.isSupportedApi(r12)     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                if (r0 == 0) goto L_0x00b2
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.social.group.GroupApi r12 = new com.samsung.android.sdk.mobileservice.social.group.GroupApi     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.SeMobileServiceSessionImpl r14 = r0.mSession     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                int r13 = java.lang.Integer.parseInt(r13)     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                r12.<init>(r14, r13)     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                r0.mGroupApi = r12     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
            L_0x00b2:
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.SeMobileServiceSessionImpl r0 = r0.mSession     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                java.lang.String r12 = "DataSyncApi"
                boolean r0 = r0.isSupportedApi(r12)     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                if (r0 == 0) goto L_0x00cd
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.social.datasync.DataSyncApi r12 = new com.samsung.android.sdk.mobileservice.social.datasync.DataSyncApi     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.SeMobileServiceSessionImpl r13 = r0.mSession     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                r12.<init>(r13)     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                r0.mDataSyncApi = r12     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
            L_0x00cd:
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.social.share.ShareApi r12 = r0.mShareApi     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                if (r12 == 0) goto L_0x00de
                com.samsung.android.sdk.mobileservice.social.group.GroupApi r12 = r0.mGroupApi     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                if (r12 == 0) goto L_0x00de
                com.samsung.android.sdk.mobileservice.social.SocialApi r12 = r0.mSocialApi     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                if (r12 == 0) goto L_0x00de
                r12 = 1
                r0.mSocialServiceEnabled = r12     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
            L_0x00de:
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                int r12 = r1.mRequestService     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                boolean r0 = r0.isAuthServiceRequested(r12)     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                if (r0 == 0) goto L_0x00f2
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.auth.AuthApi r0 = r0.mAuthApi     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                if (r0 == 0) goto L_0x00f0
            L_0x00ee:
                r0 = 1
                goto L_0x0105
            L_0x00f0:
                r0 = 0
                goto L_0x0105
            L_0x00f2:
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.sdk.mobileservice.auth.AuthApi r12 = r0.mAuthApi     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                if (r12 == 0) goto L_0x00f0
                com.samsung.android.sdk.mobileservice.social.share.ShareApi r12 = r0.mShareApi     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                if (r12 == 0) goto L_0x00f0
                com.samsung.android.sdk.mobileservice.social.group.GroupApi r12 = r0.mGroupApi     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                if (r12 == 0) goto L_0x00f0
                com.samsung.android.sdk.mobileservice.social.SocialApi r0 = r0.mSocialApi     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                if (r0 == 0) goto L_0x00f0
                goto L_0x00ee
            L_0x0105:
                com.samsung.android.gallery.module.mde.MdeSharingService r12 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                int r13 = r1.mRequestService     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                r16 = 1
                r0 = r0 ^ 1
                r12.handleConnectionResult(r13, r0)     // Catch:{ NotAuthorizedException | NotConnectedException | NotSupportedApiException -> 0x0048 }
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this
                r0.mIsSessionConnecting = false
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>(r5)
            L_0x011a:
                com.samsung.android.gallery.module.mde.MdeSharingService r5 = com.samsung.android.gallery.module.mde.MdeSharingService.this
                boolean r5 = r5.mAuthServiceEnabled
                r0.append(r5)
                r5 = 44
                r0.append(r5)
                java.lang.Object r8 = r2.get(r8)
                r0.append(r8)
                r0.append(r5)
                com.samsung.android.gallery.module.mde.MdeSharingService r8 = com.samsung.android.gallery.module.mde.MdeSharingService.this
                com.samsung.android.sdk.mobileservice.auth.AuthApi r8 = r8.mAuthApi
                java.lang.String r8 = com.samsung.android.gallery.support.utils.Logger.getSimpleName((java.lang.Object) r8)
                r0.append(r8)
                r0.append(r4)
                com.samsung.android.gallery.module.mde.MdeSharingService r4 = com.samsung.android.gallery.module.mde.MdeSharingService.this
                boolean r4 = r4.mSocialServiceEnabled
                r0.append(r4)
                r0.append(r5)
                java.lang.Object r2 = r2.get(r7)
                r0.append(r2)
                r0.append(r5)
                com.samsung.android.gallery.module.mde.MdeSharingService r2 = com.samsung.android.gallery.module.mde.MdeSharingService.this
                com.samsung.android.sdk.mobileservice.social.share.ShareApi r2 = r2.mShareApi
                java.lang.String r2 = com.samsung.android.gallery.support.utils.Logger.getSimpleName((java.lang.Object) r2)
                r0.append(r2)
                r0.append(r5)
                com.samsung.android.gallery.module.mde.MdeSharingService r2 = com.samsung.android.gallery.module.mde.MdeSharingService.this
                com.samsung.android.sdk.mobileservice.social.group.GroupApi r2 = r2.mGroupApi
                java.lang.String r2 = com.samsung.android.gallery.support.utils.Logger.getSimpleName((java.lang.Object) r2)
                r0.append(r2)
                r0.append(r5)
                com.samsung.android.gallery.module.mde.MdeSharingService r1 = com.samsung.android.gallery.module.mde.MdeSharingService.this
                com.samsung.android.sdk.mobileservice.social.SocialApi r1 = r1.mSocialApi
                java.lang.String r1 = com.samsung.android.gallery.support.utils.Logger.getSimpleName((java.lang.Object) r1)
                r0.append(r1)
                r0.append(r3)
                long r1 = java.lang.System.currentTimeMillis()
                long r1 = r1 - r10
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                com.samsung.android.gallery.support.utils.Log.sh(r6, r0)
                return
            L_0x018c:
                java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0045 }
                r12.<init>(r9)     // Catch:{ all -> 0x0045 }
                java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0045 }
                r12.append(r0)     // Catch:{ all -> 0x0045 }
                java.lang.String r0 = r12.toString()     // Catch:{ all -> 0x0045 }
                com.samsung.android.gallery.support.utils.Log.she(r6, r0)     // Catch:{ all -> 0x0045 }
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this     // Catch:{ all -> 0x0045 }
                com.samsung.android.sdk.mobileservice.auth.AuthApi r9 = r0.mAuthApi     // Catch:{ all -> 0x0045 }
                if (r9 != 0) goto L_0x01a8
                r9 = 0
                r0.mAuthServiceEnabled = r9     // Catch:{ all -> 0x0045 }
            L_0x01a8:
                com.samsung.android.sdk.mobileservice.social.share.ShareApi r9 = r0.mShareApi     // Catch:{ all -> 0x0045 }
                if (r9 == 0) goto L_0x01b4
                com.samsung.android.sdk.mobileservice.social.group.GroupApi r9 = r0.mGroupApi     // Catch:{ all -> 0x0045 }
                if (r9 == 0) goto L_0x01b4
                com.samsung.android.sdk.mobileservice.social.SocialApi r9 = r0.mSocialApi     // Catch:{ all -> 0x0045 }
                if (r9 != 0) goto L_0x01b7
            L_0x01b4:
                r9 = 0
                r0.mSocialServiceEnabled = r9     // Catch:{ all -> 0x0045 }
            L_0x01b7:
                int r9 = r1.mRequestService     // Catch:{ all -> 0x0045 }
                r12 = 1
                r0.handleConnectionResult(r9, r12)     // Catch:{ all -> 0x0045 }
                com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.this
                r0.mIsSessionConnecting = false
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>(r5)
                goto L_0x011a
            L_0x01c9:
                com.samsung.android.gallery.module.mde.MdeSharingService r9 = com.samsung.android.gallery.module.mde.MdeSharingService.this
                r9.mIsSessionConnecting = false
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                r9.<init>(r5)
                com.samsung.android.gallery.module.mde.MdeSharingService r5 = com.samsung.android.gallery.module.mde.MdeSharingService.this
                boolean r5 = r5.mAuthServiceEnabled
                r9.append(r5)
                r5 = 44
                r9.append(r5)
                java.lang.Object r8 = r2.get(r8)
                r9.append(r8)
                r9.append(r5)
                com.samsung.android.gallery.module.mde.MdeSharingService r8 = com.samsung.android.gallery.module.mde.MdeSharingService.this
                com.samsung.android.sdk.mobileservice.auth.AuthApi r8 = r8.mAuthApi
                java.lang.String r8 = com.samsung.android.gallery.support.utils.Logger.getSimpleName((java.lang.Object) r8)
                r9.append(r8)
                r9.append(r4)
                com.samsung.android.gallery.module.mde.MdeSharingService r4 = com.samsung.android.gallery.module.mde.MdeSharingService.this
                boolean r4 = r4.mSocialServiceEnabled
                r9.append(r4)
                r9.append(r5)
                java.lang.Object r2 = r2.get(r7)
                r9.append(r2)
                r9.append(r5)
                com.samsung.android.gallery.module.mde.MdeSharingService r2 = com.samsung.android.gallery.module.mde.MdeSharingService.this
                com.samsung.android.sdk.mobileservice.social.share.ShareApi r2 = r2.mShareApi
                java.lang.String r2 = com.samsung.android.gallery.support.utils.Logger.getSimpleName((java.lang.Object) r2)
                r9.append(r2)
                r9.append(r5)
                com.samsung.android.gallery.module.mde.MdeSharingService r2 = com.samsung.android.gallery.module.mde.MdeSharingService.this
                com.samsung.android.sdk.mobileservice.social.group.GroupApi r2 = r2.mGroupApi
                java.lang.String r2 = com.samsung.android.gallery.support.utils.Logger.getSimpleName((java.lang.Object) r2)
                r9.append(r2)
                r9.append(r5)
                com.samsung.android.gallery.module.mde.MdeSharingService r1 = com.samsung.android.gallery.module.mde.MdeSharingService.this
                com.samsung.android.sdk.mobileservice.social.SocialApi r1 = r1.mSocialApi
                java.lang.String r1 = com.samsung.android.gallery.support.utils.Logger.getSimpleName((java.lang.Object) r1)
                r9.append(r1)
                r9.append(r3)
                long r1 = java.lang.System.currentTimeMillis()
                long r1 = r1 - r10
                r9.append(r1)
                java.lang.String r1 = r9.toString()
                com.samsung.android.gallery.support.utils.Log.sh(r6, r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.mde.MdeSharingService.MobileServiceSessionResultCallback.lambda$onSuccess$0(java.util.HashMap):void");
        }

        public void onFailure(int i2) {
            Log.she("MdeSharingService", "connectSession failed" + Logger.v(Integer.valueOf(i2)));
            MdeSharingService mdeSharingService = MdeSharingService.this;
            mdeSharingService.mAuthServiceEnabled = false;
            mdeSharingService.mSocialServiceEnabled = false;
            if (i2 == SeMobileServiceSession.ConnectionResultCallback.CAUSE_AGENT_FORCE_UPDATE_REQUIRED) {
                mdeSharingService.handleConnectionResult(this.mRequestService, 2);
            } else {
                mdeSharingService.handleConnectionResult(this.mRequestService, 1);
            }
            MdeSharingService.this.mIsSessionConnecting = false;
        }

        public void onSuccess(HashMap<String, Integer> hashMap, boolean z) {
            SimpleThreadPool.getInstance().execute(new a(this, hashMap));
        }
    }

    private void addConnectListener(ConnectListenerData connectListenerData) {
        this.mConnectListenerDataList.add(connectListenerData);
    }

    private String addLogData(Map<String, Integer> map, int i2) {
        String str;
        StringBuilder sb2 = new StringBuilder("[statusResult is ");
        if (map == null) {
            str = "null]";
        } else if (map.isEmpty()) {
            str = "empty]";
        } else {
            str = "[AuthApi=" + map.get("AuthApi") + "][ShareApi=" + map.get("ShareApi") + "][GroupApi=" + map.get("GroupApi") + "]]";
        }
        sb2.append(str);
        sb2.append("[currentSharingServiceStatus=");
        sb2.append(i2);
        sb2.append("]");
        String sb3 = sb2.toString();
        DebugLogger.getMdeInstance().lambda$apply$0("getApiStatusList", sb3);
        return sb3;
    }

    /* access modifiers changed from: private */
    /* renamed from: connectSession */
    public void lambda$connectSessionAsync$3(int i2, ConnectListener connectListener) {
        boolean z;
        try {
            synchronized (MdeSharingService.class) {
                StringBuilder sb2 = new StringBuilder("connectSession");
                String reqServiceName = MdeConstants.getReqServiceName(i2);
                if (this.mSession != null) {
                    z = true;
                } else {
                    z = false;
                }
                sb2.append(Logger.v(reqServiceName, Boolean.valueOf(z)));
                Log.sh("MdeSharingService", sb2.toString());
                if (isRequestedServiceEnabled(i2)) {
                    Optional.ofNullable(connectListener).ifPresent(new l(16));
                    return;
                }
                if (this.mSession == null) {
                    SeMobileServiceSessionImpl seMobileServiceSessionImpl = (SeMobileServiceSessionImpl) new SeMobileServiceSessionFactory(getAppContext(), new MobileServiceSessionResultCallback(i2)).addService("SocialService").setAppId("22n6hzkam0").build();
                    this.mSession = seMobileServiceSessionImpl;
                    if (seMobileServiceSessionImpl == null) {
                        Log.she("MdeSharingService", "connectSession failed. session build failure");
                        return;
                    } else {
                        seMobileServiceSessionImpl.setSessionListener(new g(this));
                        this.mSession.setOnAgentUpdatedListener(new g(this));
                    }
                }
                this.mSession.connect();
            }
        } catch (Exception e) {
            Log.she("MdeSharingService", "connectSession failed. e=" + e.getMessage());
            this.mSession = null;
        } catch (Throwable th) {
            throw th;
        }
    }

    private Context getAppContext() {
        return AppResources.getAppContext();
    }

    public static MdeSharingService getInstance() {
        return INSTANCE.instance;
    }

    private Map<String, Integer> getServiceApiList() {
        return SeMobileService.getApiStatusList(getAppContext(), new String[]{"AuthApi", "ShareApi", "GroupApi"});
    }

    /* access modifiers changed from: private */
    public void handleConnectionResult(int i2, int i7) {
        Log.sh("MdeSharingService", "handleConnectionResult" + Logger.v(MdeConstants.getReqServiceName(i2), Integer.valueOf(i7), Integer.valueOf(this.mConnectListenerDataList.size()), Boolean.valueOf(this.mAuthServiceEnabled), Boolean.valueOf(this.mSocialServiceEnabled)));
        for (ConnectListenerData next : this.mConnectListenerDataList) {
            if (!(next == null || next.getConnectListener() == null)) {
                boolean isRequestedServiceEnabled = isRequestedServiceEnabled(next.getRequestService());
                ConnectListener connectListener = next.getConnectListener();
                if (isRequestedServiceEnabled) {
                    connectListener.onSuccess();
                } else {
                    connectListener.onFailure(i7);
                }
            }
        }
        this.mConnectListenerDataList.clear();
    }

    private boolean isAgentEnabled() {
        return SeMobileService.isAgentEnabled(getAppContext());
    }

    private boolean isInMultiUserMode() {
        if (Features.isEnabled(Features.IS_KNOX_MODE) || Features.isEnabled(Features.IS_ON_SECURE_FOLDER) || Features.isEnabled(Features.IS_MUM_MODE)) {
            return true;
        }
        if (!Features.isEnabled(Features.IS_AFW_MODE) || Features.isEnabled(Features.SUPPORT_SHARED_ALBUM_IN_AFW_GALAXY_TO_GO_MODE)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00d7, code lost:
        return r4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00d5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean isServiceEnabled() {
        /*
            r13 = this;
            java.lang.String r0 = "isServiceEnabled"
            java.lang.String r1 = "isServiceEnabled"
            java.lang.String r2 = "isServiceEnabled"
            monitor-enter(r13)
            com.samsung.android.gallery.support.utils.Features r3 = com.samsung.android.gallery.support.utils.Features.SUPPORT_SHARING     // Catch:{ all -> 0x0041 }
            boolean r3 = com.samsung.android.gallery.support.utils.Features.isEnabled(r3)     // Catch:{ all -> 0x0041 }
            r4 = 0
            if (r3 == 0) goto L_0x00e2
            com.samsung.android.gallery.support.utils.Features r3 = com.samsung.android.gallery.support.utils.Features.IS_UPSM     // Catch:{ all -> 0x0041 }
            boolean r3 = com.samsung.android.gallery.support.utils.Features.isEnabled(r3)     // Catch:{ all -> 0x0041 }
            if (r3 != 0) goto L_0x00e2
            boolean r3 = r13.isInMultiUserMode()     // Catch:{ all -> 0x0041 }
            if (r3 == 0) goto L_0x0020
            goto L_0x00e2
        L_0x0020:
            com.samsung.android.gallery.support.utils.Features r3 = com.samsung.android.gallery.support.utils.Features.SUPPORT_SHARING_SERVICE     // Catch:{ all -> 0x0041 }
            boolean r3 = com.samsung.android.gallery.support.utils.Features.isEnabled(r3)     // Catch:{ all -> 0x0041 }
            if (r3 == 0) goto L_0x00d8
            com.samsung.android.gallery.support.utils.GalleryPreference r3 = com.samsung.android.gallery.support.utils.GalleryPreference.getInstance()     // Catch:{ all -> 0x0041 }
            com.samsung.android.gallery.support.utils.PreferenceName r5 = com.samsung.android.gallery.support.utils.PreferenceName.SHARE_SERVICE_V2_STATUS     // Catch:{ all -> 0x0041 }
            java.lang.String r6 = r5.key()     // Catch:{ all -> 0x0041 }
            int r3 = r3.loadInt((java.lang.String) r6, (int) r4)     // Catch:{ all -> 0x0041 }
            r6 = 2
            r7 = 1
            if (r3 == 0) goto L_0x0044
            if (r3 != r6) goto L_0x00b0
            java.lang.Integer r8 = r13.mServiceState     // Catch:{ all -> 0x0041 }
            if (r8 != 0) goto L_0x00b0
            goto L_0x0044
        L_0x0041:
            r0 = move-exception
            goto L_0x0101
        L_0x0044:
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0041 }
            java.util.Map r10 = r13.getServiceApiList()     // Catch:{ all -> 0x0041 }
            boolean r11 = r13.isServiceEnabled(r10)     // Catch:{ all -> 0x0041 }
            if (r11 == 0) goto L_0x0053
            r6 = r7
        L_0x0053:
            if (r6 == r3) goto L_0x008a
            java.lang.String r1 = "MdeSharingService"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0041 }
            r11.<init>(r2)     // Catch:{ all -> 0x0041 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0041 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0041 }
            java.lang.String r3 = r13.addLogData(r10, r3)     // Catch:{ all -> 0x0041 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x0041 }
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r12, r3, r8}     // Catch:{ all -> 0x0041 }
            java.lang.String r2 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r2)     // Catch:{ all -> 0x0041 }
            r11.append(r2)     // Catch:{ all -> 0x0041 }
            java.lang.String r2 = r11.toString()     // Catch:{ all -> 0x0041 }
            com.samsung.android.gallery.support.utils.Log.sh(r1, r2)     // Catch:{ all -> 0x0041 }
            com.samsung.android.gallery.support.utils.GalleryPreference r1 = com.samsung.android.gallery.support.utils.GalleryPreference.getInstance()     // Catch:{ all -> 0x0041 }
            java.lang.String r2 = r5.key()     // Catch:{ all -> 0x0041 }
            r1.saveState((java.lang.String) r2, (int) r6)     // Catch:{ all -> 0x0041 }
            goto L_0x00af
        L_0x008a:
            java.lang.String r2 = "MdeSharingService"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0041 }
            r5.<init>(r1)     // Catch:{ all -> 0x0041 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0041 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0041 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x0041 }
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r3, r8}     // Catch:{ all -> 0x0041 }
            java.lang.String r1 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r1)     // Catch:{ all -> 0x0041 }
            r5.append(r1)     // Catch:{ all -> 0x0041 }
            java.lang.String r1 = r5.toString()     // Catch:{ all -> 0x0041 }
            com.samsung.android.gallery.support.utils.Log.sh(r2, r1)     // Catch:{ all -> 0x0041 }
        L_0x00af:
            r3 = r6
        L_0x00b0:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0041 }
            r13.mServiceState = r1     // Catch:{ all -> 0x0041 }
            java.lang.String r1 = "MdeSharingService"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0041 }
            r2.<init>(r0)     // Catch:{ all -> 0x0041 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0041 }
            java.lang.Object[] r0 = new java.lang.Object[]{r0}     // Catch:{ all -> 0x0041 }
            java.lang.String r0 = com.samsung.android.gallery.support.utils.Logger.v(r0)     // Catch:{ all -> 0x0041 }
            r2.append(r0)     // Catch:{ all -> 0x0041 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0041 }
            com.samsung.android.gallery.support.utils.Log.sh(r1, r0)     // Catch:{ all -> 0x0041 }
            if (r3 != r7) goto L_0x00d6
            r4 = r7
        L_0x00d6:
            monitor-exit(r13)
            return r4
        L_0x00d8:
            java.util.Map r0 = r13.getServiceApiList()     // Catch:{ all -> 0x0041 }
            boolean r0 = r13.isServiceEnabled(r0)     // Catch:{ all -> 0x0041 }
            monitor-exit(r13)
            return r0
        L_0x00e2:
            java.lang.String r0 = "MdeSharingService"
            java.lang.String r1 = "isServiceEnabled skip"
            com.samsung.android.gallery.support.utils.Features r2 = com.samsung.android.gallery.support.utils.Features.IS_UPSM     // Catch:{ all -> 0x0041 }
            boolean r2 = com.samsung.android.gallery.support.utils.Features.isEnabled(r2)     // Catch:{ all -> 0x0041 }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x0041 }
            boolean r3 = r13.isInMultiUserMode()     // Catch:{ all -> 0x0041 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x0041 }
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r3}     // Catch:{ all -> 0x0041 }
            com.samsung.android.gallery.support.utils.Log.d(r0, r1, r2)     // Catch:{ all -> 0x0041 }
            monitor-exit(r13)
            return r4
        L_0x0101:
            monitor-exit(r13)     // Catch:{ all -> 0x0041 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.mde.MdeSharingService.isServiceEnabled():boolean");
    }

    private boolean isServiceInstalled() {
        return SeMobileService.isAgentInstalled(getAppContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$connectSession$0(int i2, String str) {
        SeMobileServiceSessionImpl seMobileServiceSessionImpl;
        Log.sh("MdeSharingService", "connectSession#onSessionChanged" + Logger.v(Integer.valueOf(i2)));
        if (i2 == -1 && (seMobileServiceSessionImpl = this.mSession) != null) {
            seMobileServiceSessionImpl.reconnect();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$connectSession$1() {
        Log.sh("MdeSharingService", "connectSession#onAgentUpdated");
        recycleService();
        updateShareServiceStatus();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$connectSessionAsync$2(ConnectListener connectListener) {
        if (isReadyToUseShareService()) {
            connectListener.onSuccess();
        } else {
            connectListener.onFailure(3);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestSpaceListSortSync$4(DataSyncResult dataSyncResult) {
        if (dataSyncResult.getStatus().getCode() == 1 && !dataSyncResult.getResponse().isEmpty()) {
            GalleryPreference.getInstance().saveState(PreferenceName.SORT_BY_SHARING, Integer.parseInt(((GallerySetting) dataSyncResult.getResponse().get(0).getData()).getSortType()));
        }
    }

    private void recycleService() {
        this.mAuthApi = null;
        this.mGroupApi = null;
        this.mShareApi = null;
        this.mSocialApi = null;
        this.mDataSyncApi = null;
        this.mAuthServiceEnabled = false;
        this.mSocialServiceEnabled = false;
    }

    private void updateShareServiceStatus() {
        boolean isEnabled = Features.isEnabled(Features.SUPPORT_SHARING_SERVICE);
        Log.sh("MdeSharingService", "updateShareServiceStatus" + Logger.v(Boolean.valueOf(isEnabled)));
        ShareServiceStatus.update(isEnabled);
        if (isEnabled) {
            SeMobileServiceSessionImpl seMobileServiceSessionImpl = this.mSession;
            if (seMobileServiceSessionImpl != null) {
                seMobileServiceSessionImpl.disconnect();
                this.mSession.connect();
                return;
            }
            return;
        }
        MdeNotificationManager.getInstance(getAppContext()).cancelAll();
        getAppContext().sendBroadcast(new Intent("com.android.gallery.action.SHARE_SERVICE_STOPPED"));
    }

    public void clearServiceState() {
        this.mServiceAvailable = null;
    }

    public void clearShareServiceStatus() {
        Log.sh("MdeSharingService", "clearShareServiceStatus(V2)");
        GalleryPreference.getInstance().saveState(PreferenceName.SHARE_SERVICE_V2_STATUS, 0);
    }

    public void clearUnreadCount(String str) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.clearUnreadCount(str);
        }
    }

    public boolean computeShareServiceAvailable() {
        if (ShareServiceStatus.update(isServiceEnabled()) == 1) {
            return true;
        }
        return false;
    }

    public void connectSessionAsync(int i2, ConnectListener connectListener) {
        if (isRequestedServiceEnabled(i2)) {
            Optional.ofNullable(connectListener).ifPresent(new C0367b(25, this));
            return;
        }
        addConnectListener(new ConnectListenerData(i2, connectListener));
        if (!this.mIsSessionConnecting) {
            this.mIsSessionConnecting = true;
            new Thread(new b((Object) this, i2, (Object) connectListener, 8)).start();
        }
    }

    public Intent getIntentForGdprErrorPopup() {
        SocialApi socialApi = this.mSocialApi;
        if (socialApi == null) {
            return null;
        }
        return socialApi.getIntentForGdprErrorPopup();
    }

    public Intent getIntentInfoToUseShareService() {
        SocialApi socialApi;
        if (!(this.mAuthApi == null || (socialApi = this.mSocialApi) == null)) {
            if (socialApi.isAgreementProcedureNeeded(0)) {
                return this.mSocialApi.getIntentForAgreementProcedure(0);
            }
            if (!this.mAuthApi.getAccountValidation().getResult()) {
                return this.mAuthApi.getIntentForAccountValidationRequest(false, false);
            }
        }
        return null;
    }

    public OriginalFileListResult getVerifiedOriginalFileList(List<String> list) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            return shareApi.getVerifiedOriginalFileList(list);
        }
        return null;
    }

    public boolean isAuthServiceEnabled() {
        return this.mAuthServiceEnabled;
    }

    public boolean isAuthServiceRequested(int i2) {
        if (i2 == 1) {
            return true;
        }
        return false;
    }

    public boolean isReadyToUseShareService() {
        SocialApi socialApi;
        if (this.mAuthApi == null || (socialApi = this.mSocialApi) == null) {
            Log.sh("MdeSharingService", "isReadyToUseShareService {api not ready}");
            return false;
        } else if (socialApi.isAgreementProcedureNeeded(0)) {
            Log.sh("MdeSharingService", "isReadyToUseShareService {agreement procedure needed}");
            return false;
        } else if (this.mAuthApi.getAccountValidation().getResult()) {
            return true;
        } else {
            Log.sh("MdeSharingService", "isReadyToUseShareService {invalid account}");
            return false;
        }
    }

    public boolean isRequestedServiceEnabled(int i2) {
        if (isAuthServiceRequested(i2)) {
            return isAuthServiceEnabled();
        }
        if (!isSocialServiceRequested(i2) || !isSocialServiceEnabled()) {
            return false;
        }
        return true;
    }

    public boolean isServiceAvailable() {
        if (this.mServiceAvailable == null) {
            this.mServiceAvailable = Boolean.valueOf(isServiceEnabled());
        }
        return this.mServiceAvailable.booleanValue();
    }

    public boolean isServiceSupported() {
        return isServiceSupported(true);
    }

    public boolean isShareServiceAvailable() {
        if (ShareServiceStatus.get() == 1) {
            return true;
        }
        return false;
    }

    public boolean isSocialServiceEnabled() {
        return this.mSocialServiceEnabled;
    }

    public boolean isSocialServiceRequested(int i2) {
        if (i2 == 2) {
            return true;
        }
        return false;
    }

    public void requestBulkShare(String str, List<ShareApi.SharedItemRequest> list, ShareApi.ActionIntent actionIntent, ShareApi.NotificationMessage notificationMessage, boolean z, ShareApi.ShareUploadResultCallback<SharedItemListResult> shareUploadResultCallback) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.requestBulkItemShare(getAppContext(), str, list, actionIntent, notificationMessage, Boolean.valueOf(z), shareUploadResultCallback);
        }
    }

    public void requestDeleteItemListFromTrashBin(String str, String str2, List<String> list, ShareApi.ShareBaseResultCallback<ItemListResult> shareBaseResultCallback) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.requestDeleteItemListFromTrashBin(str, str2, list, shareBaseResultCallback);
        }
    }

    public void requestEmptyFromTrashBin(String str, String str2, ShareApi.ShareBaseResultCallback<ItemListResult> shareBaseResultCallback) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.requestDeleteAllItemsFromTrashBin(str, str2, shareBaseResultCallback);
        }
    }

    public void requestFamilyGroupCreation(GroupApi.FamilyGroupRequest familyGroupRequest, GroupApi.GroupResultCallback<GroupResult> groupResultCallback) {
        GroupApi groupApi = this.mGroupApi;
        if (groupApi != null) {
            groupApi.requestFamilyGroupCreation(familyGroupRequest, groupResultCallback);
        }
    }

    public void requestFamilyGroupDeletion(String str, GroupApi.GroupResultCallback<BooleanResult> groupResultCallback) {
        GroupApi groupApi = this.mGroupApi;
        if (groupApi != null) {
            groupApi.requestFamilyGroupDeletion(str, groupResultCallback);
        }
    }

    public QuotaResult requestFamilyQuota() {
        ShareApi shareApi = this.mShareApi;
        if (shareApi == null) {
            return null;
        }
        return shareApi.getFamilyQuota();
    }

    public void requestGroupCreation(GroupApi.GroupRequest groupRequest, GroupApi.InvitationRequest invitationRequest, GroupApi.GroupResultCallback<GroupInvitationResult> groupResultCallback) {
        GroupApi groupApi = this.mGroupApi;
        if (groupApi != null) {
            groupApi.requestGroupCreation(groupRequest, invitationRequest, groupResultCallback);
        }
    }

    public void requestGroupCreationForShare(GroupApi.GroupRequest groupRequest, GroupApi.GroupResultCallback<GroupInvitationResult> groupResultCallback) {
        GroupApi groupApi = this.mGroupApi;
        if (groupApi != null) {
            groupApi.requestGroupCreation(groupRequest, groupResultCallback);
        }
    }

    public void requestGroupInvitationAcceptance(String str, GroupApi.GroupResultCallback<BooleanResult> groupResultCallback) {
        GroupApi groupApi = this.mGroupApi;
        if (groupApi != null) {
            groupApi.requestGroupInvitationAcceptance(str, groupResultCallback);
        }
    }

    public void requestGroupInvitationRejection(String str, GroupApi.GroupResultCallback<BooleanResult> groupResultCallback) {
        GroupApi groupApi = this.mGroupApi;
        if (groupApi != null) {
            groupApi.requestGroupInvitationRejection(str, groupResultCallback);
        }
    }

    public void requestGroupMemberListSync(String str, GroupApi.GroupResultCallback<GroupMemberResult> groupResultCallback) {
        if (this.mGroupApi == null) {
            return;
        }
        if (MdeGroupHelper.isSAFamilyGroup(str)) {
            this.mGroupApi.requestFamilyGroupMemberList(str, groupResultCallback);
        } else {
            this.mGroupApi.requestGroupMemberList(str, groupResultCallback);
        }
    }

    public int requestLocalGroupDeletion(String str, GroupApi.GroupResultCallback<BooleanResult> groupResultCallback) {
        GroupApi groupApi = this.mGroupApi;
        if (groupApi == null) {
            return -1;
        }
        return groupApi.requestGroupDeletion(str, groupResultCallback);
    }

    public void requestLocalGroupLeave(String str, GroupApi.GroupResultCallback<BooleanResult> groupResultCallback) {
        GroupApi groupApi = this.mGroupApi;
        if (groupApi != null) {
            groupApi.requestLeave(str, groupResultCallback);
        }
    }

    public void requestMoveItemListToTrashBin(String str, String str2, List<String> list, ShareApi.ShareBaseResultCallback<ItemListResult> shareBaseResultCallback) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.requestMoveItemListToTrashBin(str, str2, list, shareBaseResultCallback);
        }
    }

    public void requestMyInvitationList(GroupApi.GroupResultCallback<GroupInvitationListResult> groupResultCallback) {
        GroupApi groupApi = this.mGroupApi;
        if (groupApi != null) {
            groupApi.requestMyInvitationList(groupResultCallback);
        }
    }

    public QuotaResult requestMyQuota() {
        ShareApi shareApi = this.mShareApi;
        if (shareApi == null) {
            return null;
        }
        return shareApi.getQuota();
    }

    public void requestRestoreItemListFromTrashBin(String str, String str2, List<String> list, ShareApi.ShareBaseResultCallback<ItemListResult> shareBaseResultCallback) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.requestRestoreItemListFromTrashBin(str, str2, list, shareBaseResultCallback);
        }
    }

    public void requestShare(String str, List<ShareApi.SharedItemRequest> list, ShareApi.ShareResultCallback shareResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.requestShare(str, list, shareResultCallback, pendingIntent, bundle);
        }
    }

    public int requestShareItem(String str, String str2, ShareApi.SharedItemResultCallback sharedItemResultCallback) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi == null) {
            return -1;
        }
        return shareApi.requestSharedItem(str, str2, sharedItemResultCallback);
    }

    public void requestShareItemSync(String str, ShareApi.SharedItemSyncResultCallback sharedItemSyncResultCallback) {
        boolean z;
        ShareApi shareApi = this.mShareApi;
        if (shareApi == null || this.mIsSessionConnecting) {
            StringBuilder sb2 = new StringBuilder("requestShareItemSync failed");
            if (this.mShareApi != null) {
                z = true;
            } else {
                z = false;
            }
            sb2.append(Logger.v(Boolean.valueOf(z), Boolean.valueOf(!this.mIsSessionConnecting)));
            Log.she("MdeSharingService", sb2.toString());
            return;
        }
        shareApi.requestSharedItemSync(str, sharedItemSyncResultCallback);
    }

    public void requestSharedContentDownload(String str, List<String> list, ShareApi.ContentDownloadingResultCallback contentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.requestSharedContentDownload(str, list, contentDownloadingResultCallback, pendingIntent, bundle);
        }
    }

    public void requestSharedContentDownloadToHiddenFolder(String str, List<String> list, ShareApi.ContentDownloadingResultCallback contentDownloadingResultCallback) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.requestSharedContentDownloadToHiddenFolder(str, list, contentDownloadingResultCallback);
        }
    }

    public void requestSharedItemDeletion(String str, List<String> list, ShareApi.ShareBaseResultCallback<ItemListResult> shareBaseResultCallback) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.requestSharedItemDeletion(str, list, shareBaseResultCallback);
        }
    }

    public void requestSharedItemUpdate(String str, List<ShareApi.SharedItemRequest> list, ShareApi.ShareUploadResultCallback<SharedItemListResult> shareUploadResultCallback, PendingIntent pendingIntent, Bundle bundle) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.requestSharedItemUpdate(str, list, shareUploadResultCallback, pendingIntent, bundle);
        }
    }

    public void requestSpaceCreation(String str, ShareApi.SpaceRequest spaceRequest, ShareApi.SpaceResultCallback spaceResultCallback) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.requestSpaceCreation(str, spaceRequest, spaceResultCallback);
        }
    }

    public void requestSpaceDeletion(String str, ShareApi.SpaceDeletionResultCallback spaceDeletionResultCallback) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.requestSpaceDeletion(str, spaceDeletionResultCallback);
        }
    }

    public void requestSpaceListSort(String str, DataSyncApi.DataSyncResultCallback dataSyncResultCallback) {
        if (this.mDataSyncApi != null) {
            this.mDataSyncApi.upsert(new DataSyncRequest(new ArrayList(Collections.singletonList(new GallerySetting(str)))), dataSyncResultCallback);
        }
    }

    public void requestSpaceListSortSync() {
        if (this.mDataSyncApi != null) {
            this.mDataSyncApi.getSyncedData(new DataSyncRequest(new ArrayList(Collections.singletonList(new GallerySetting()))), new j(10));
        }
    }

    public void requestSpaceListSync(boolean z, ShareApi.ShareSyncResultCallback shareSyncResultCallback) {
        boolean z3;
        ShareApi shareApi = this.mShareApi;
        if (shareApi == null || this.mIsSessionConnecting) {
            StringBuilder sb2 = new StringBuilder("requestSpaceListSync failed");
            if (this.mShareApi != null) {
                z3 = true;
            } else {
                z3 = false;
            }
            sb2.append(Logger.v(Boolean.valueOf(z3), Boolean.valueOf(!this.mIsSessionConnecting)));
            Log.she("MdeSharingService", sb2.toString());
            return;
        }
        shareApi.requestSync(new SyncOption(SyncOption.SyncType.SYNC_WITH_LAST_THUMBNAIL, true, z), shareSyncResultCallback);
        if (Features.isEnabled(Features.SUPPORT_SORT_SHARINGS)) {
            requestSpaceListSortSync();
        }
    }

    public void requestSpaceUpdate(String str, String str2, String str3, ShareApi.SpaceResultCallback spaceResultCallback) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.requestSpaceUpdate(str, str2, str3, spaceResultCallback);
        }
    }

    public void requestThumbnailDownload(String str, String str2, String str3, String str4, String str5, ShareApi.ShareBaseResultCallback<DownloadImageResult> shareBaseResultCallback) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.requestItemThumbnailDownload(str, str2, str3, str4, str5, shareBaseResultCallback);
        }
    }

    public int requestWebLinkEnabled(String str, String str2, boolean z, ShareApi.ShareBaseResultCallback<SpaceResult> shareBaseResultCallback) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi == null || !PreferenceFeatures.OneUi41.SHARING_ALBUM_WEB_LINK) {
            return -1;
        }
        return shareApi.requestWebLinkEnabled(str, str2, z, shareBaseResultCallback);
    }

    public boolean showSemsPermissionPopup() {
        if (!Features.isEnabled(Features.SUPPORT_SHARED_PERMISSION_POPUP) || isAgentEnabled() || isInMultiUserMode()) {
            return false;
        }
        return true;
    }

    public boolean isServiceSupported(boolean z) {
        if (PreferenceFeatures.CHINA.SHARING_SERVICE_ENABLER && z && !PreferenceFeatures.isEnabled(PreferenceFeatures.SharingServiceEnabled)) {
            Log.sh("MdeSharingService", "isServiceSupported {service not enabled}");
            return false;
        } else if (!Features.isEnabled(Features.SUPPORT_AUTO_BLOCKER) || !Features.isEnabled(Features.IS_SHARED_ALBUM_BLOCKED)) {
            if (!isInMultiUserMode()) {
                if (Features.isEnabled(Features.SUPPORT_SHARED_PERMISSION_POPUP)) {
                    if (isServiceInstalled()) {
                        return true;
                    }
                } else if (isServiceAvailable()) {
                    return true;
                }
            }
            return false;
        } else {
            Log.sh("MdeSharingService", "isServiceSupported {auto blocker is on}");
            return false;
        }
    }

    public void requestShare(String str, List<ShareApi.SharedItemRequest> list, ShareApi.ActionIntent actionIntent, ShareApi.NotificationMessage notificationMessage, ShareApi.ShareResultCallback shareResultCallback) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.requestShare(str, list, actionIntent, notificationMessage, shareResultCallback);
        }
    }

    public void requestSpaceUpdate(String str, Map map, ShareApi.SpaceResultCallback spaceResultCallback) {
        ShareApi shareApi = this.mShareApi;
        if (shareApi != null) {
            shareApi.requestSpaceUpdate(str, map, spaceResultCallback);
        }
    }

    private boolean isServiceEnabled(Map<String, Integer> map) {
        if (map != null) {
            try {
                return !map.isEmpty() && map.get("AuthApi").intValue() > 0 && map.get("ShareApi").intValue() > 0 && map.get("GroupApi").intValue() > 0;
            } catch (Exception e) {
                Log.she("MdeSharingService", "isServiceEnabled failed e=" + e.getMessage());
            }
        }
    }
}
