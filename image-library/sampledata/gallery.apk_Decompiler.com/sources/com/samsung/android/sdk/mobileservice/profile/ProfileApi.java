package com.samsung.android.sdk.mobileservice.profile;

import N2.j;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import android.text.TextUtils;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.mobileservice.common.api.SeMobileServiceApi;
import com.samsung.android.sdk.mobileservice.common.exception.NotConnectedException;
import com.samsung.android.sdk.mobileservice.common.result.BooleanResult;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.profile.IPrivacyUpdateResultCallback;
import com.samsung.android.sdk.mobileservice.profile.IProfileUpdateResultCallback;
import com.samsung.android.sdk.mobileservice.profile.ISyncResultCallback;
import com.samsung.android.sdk.mobileservice.profile.result.PrivacyResult;
import com.samsung.android.sdk.mobileservice.profile.result.ProfileBirthdayTypeResult;
import com.samsung.android.sdk.mobileservice.profile.result.ProfileImageUrlResult;
import com.samsung.android.sdk.mobileservice.profile.result.ProfileResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProfileApi extends SeMobileServiceApi {
    public static final String API_NAME = "ProfileApi";
    private static final int DEFAULT_BUFFER_SIZE = 1024;
    public static final int PRIVACY_TYPE_CONTACTS = 2;
    public static final int PRIVACY_TYPE_EVERYONE = 0;
    public static final String SERVICE_NAME = "ProfileService";
    private int mConnectedProfileVersion = 6;
    private int mPrivacyType = 0;

    /* renamed from: com.samsung.android.sdk.mobileservice.profile.ProfileApi$5  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sdk$mobileservice$profile$ProfileApi$ProfileBirthdayType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.sdk.mobileservice.profile.ProfileApi$ProfileBirthdayType[] r0 = com.samsung.android.sdk.mobileservice.profile.ProfileApi.ProfileBirthdayType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sdk$mobileservice$profile$ProfileApi$ProfileBirthdayType = r0
                com.samsung.android.sdk.mobileservice.profile.ProfileApi$ProfileBirthdayType r1 = com.samsung.android.sdk.mobileservice.profile.ProfileApi.ProfileBirthdayType.SOLAR_BIRTHDAY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$mobileservice$profile$ProfileApi$ProfileBirthdayType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sdk.mobileservice.profile.ProfileApi$ProfileBirthdayType r1 = com.samsung.android.sdk.mobileservice.profile.ProfileApi.ProfileBirthdayType.LUNAR_BIRTHDAY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$mobileservice$profile$ProfileApi$ProfileBirthdayType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sdk.mobileservice.profile.ProfileApi$ProfileBirthdayType r1 = com.samsung.android.sdk.mobileservice.profile.ProfileApi.ProfileBirthdayType.LEAP_BIRTHDAY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.profile.ProfileApi.AnonymousClass5.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface PrivacyUpdateResultCallback {
        void onResult(BooleanResult booleanResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ProfileBirthdayType {
        SOLAR_BIRTHDAY,
        LUNAR_BIRTHDAY,
        LEAP_BIRTHDAY,
        INVALID
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ProfileUpdateResultCallback {
        void onResult(BooleanResult booleanResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SyncResultCallback {
        void onResult(ProfileResult profileResult);
    }

    public ProfileApi(SeMobileServiceSession seMobileServiceSession) {
        super(seMobileServiceSession, "ProfileApi");
        checkAuthorized(0);
        try {
            this.mConnectedProfileVersion = getProfileService().exchangeProfileVersion(6);
        } catch (RemoteException e) {
            secureLog(e);
        }
    }

    private String convertProfileBirthdayTypeToString(ProfileBirthdayType profileBirthdayType) {
        int i2 = AnonymousClass5.$SwitchMap$com$samsung$android$sdk$mobileservice$profile$ProfileApi$ProfileBirthdayType[profileBirthdayType.ordinal()];
        if (i2 == 1) {
            return "0";
        }
        if (i2 == 2) {
            return "1";
        }
        if (i2 != 3) {
            return "";
        }
        return "2";
    }

    private byte[] getBytes(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    private boolean isAppIdNullOrEmpty() {
        if (!TextUtils.isEmpty(getAppId())) {
            return false;
        }
        debugLog("appId is null or empty");
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0044, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0048, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setPhotoBlobFromUriIfNeeded(com.samsung.android.sdk.mobileservice.profile.Profile r3) {
        /*
            r2 = this;
            com.samsung.android.sdk.mobileservice.profile.Profile$Photo r0 = r3.getPhotoInstance()
            java.lang.String r0 = r0.getPhotoFileUri()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0015
            java.lang.String r3 = "uriString for photo is null or empty."
            r2.debugLog(r3)
            return
        L_0x0015:
            com.samsung.android.sdk.mobileservice.profile.Profile$Photo r0 = r3.getPhotoInstance()
            java.lang.String r0 = r0.getPhotoFileUri()
            android.net.Uri r0 = android.net.Uri.parse(r0)
            if (r0 == 0) goto L_0x004e
            android.content.Context r1 = r2.getContext()
            android.content.ContentResolver r1 = r1.getContentResolver()
            java.io.InputStream r0 = r1.openInputStream(r0)
            if (r0 == 0) goto L_0x0049
            com.samsung.android.sdk.mobileservice.profile.Profile$Photo r3 = r3.getPhotoInstance()     // Catch:{ all -> 0x003d }
            byte[] r2 = r2.getBytes(r0)     // Catch:{ all -> 0x003d }
            r3.setPhoto(r2)     // Catch:{ all -> 0x003d }
            goto L_0x0049
        L_0x003d:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x003f }
        L_0x003f:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x0044 }
            goto L_0x0048
        L_0x0044:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x0048:
            throw r3
        L_0x0049:
            if (r0 == 0) goto L_0x004e
            r0.close()
        L_0x004e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.profile.ProfileApi.setPhotoBlobFromUriIfNeeded(com.samsung.android.sdk.mobileservice.profile.Profile):void");
    }

    public String[] getEssentialServiceNames() {
        return new String[]{"ProfileService"};
    }

    public PrivacyResult getPrivacy() {
        debugLog("getPrivacy ");
        if (isAppIdNullOrEmpty()) {
            return new PrivacyResult(new CommonResultStatus(-1, "appId id null or empty", ""), (Privacy) null);
        }
        try {
            if (getProfileService() == null) {
                debugLog("profile service is null ");
                return new PrivacyResult(new CommonResultStatus(5), (Privacy) null);
            }
            return new PrivacyResult(new CommonResultStatus(1), new Privacy(getProfileService().getPrivacy()));
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return new PrivacyResult(new CommonResultStatus(-1), (Privacy) null);
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return new PrivacyResult(new CommonResultStatus(-1), (Privacy) null);
        }
    }

    public ProfileResult getProfile() {
        debugLog("getProfile ");
        if (isAppIdNullOrEmpty()) {
            return new ProfileResult(new CommonResultStatus(-1, "appId id null or empty", ""), (Profile) null);
        }
        try {
            Profile profile = getProfileService().getProfile();
            try {
                setPhotoBlobFromUriIfNeeded(profile);
                return new ProfileResult(new CommonResultStatus(1), profile);
            } catch (IOException unused) {
                verboseLog("IOException");
                return new ProfileResult(new CommonResultStatus(-1, "IOException", "IOException"), (Profile) null);
            }
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return new ProfileResult(new CommonResultStatus(-1, "RemoteException", "RemoteException"), (Profile) null);
        } catch (OutOfMemoryError unused2) {
            verboseLog("OutOfMemoryError");
            return new ProfileResult(new CommonResultStatus(-1, "OutOfMemoryError", "OutOfMemoryError"), (Profile) null);
        }
    }

    public ProfileBirthdayTypeResult getProfileBirthdayType() {
        debugLog("getProfileBirthdayType ");
        if (isAppIdNullOrEmpty()) {
            return new ProfileBirthdayTypeResult(new CommonResultStatus(-1, "appId id null or empty", ""), "");
        }
        if (!isSupportedSaAgentVersion(CommonConstants.SupportedApiMinVersion.VERSION_11_0)) {
            return new ProfileBirthdayTypeResult(new CommonResultStatus(200), "");
        }
        try {
            return new ProfileBirthdayTypeResult(new CommonResultStatus(1), getProfileService().getProfileBirthdayType());
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return new ProfileBirthdayTypeResult(new CommonResultStatus(-1, e.getMessage(), e.getClass().getSimpleName()), "");
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return new ProfileBirthdayTypeResult(new CommonResultStatus(-1, "OutOfMemoryError", "OutOfMemoryError"), "");
        }
    }

    public ProfileImageUrlResult getProfileImageUrl() {
        debugLog("getProfileImageUrl");
        if (isAppIdNullOrEmpty()) {
            return new ProfileImageUrlResult(new CommonResultStatus(-1, "appId id null or empty", ""), "");
        }
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_6)) {
            return new ProfileImageUrlResult(new CommonResultStatus(200), "");
        }
        try {
            return new ProfileImageUrlResult(new CommonResultStatus(1), getProfileService().getProfileImageUrl());
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return new ProfileImageUrlResult(new CommonResultStatus(-1, "RemoteException", "RemoteException"), "");
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return new ProfileImageUrlResult(new CommonResultStatus(-1, "OutOfMemoryError", "OutOfMemoryError"), "");
        }
    }

    public int requestPrivacyUpdate(Privacy privacy, final PrivacyUpdateResultCallback privacyUpdateResultCallback) {
        debugLog("requestPrivacyUpdate ");
        if (isAppIdNullOrEmpty()) {
            privacyUpdateResultCallback.onResult(new BooleanResult(new CommonResultStatus(-1, "appId id null or empty", ""), false));
            return -1;
        }
        try {
            getProfileService().requestPrivacyUpdate(privacy.read(), new IPrivacyUpdateResultCallback.Stub() {
                public void onFailure(String str, String str2) {
                    ProfileApi.this.debugLog(j.d("requestPrivacyUpdate onFailure : code=[", str, "], message=[", str2, "] "));
                    if (privacyUpdateResultCallback != null) {
                        privacyUpdateResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(str), str2, str), false));
                    }
                }

                public void onResult() {
                    ProfileApi.this.debugLog("requestPrivacyUpdate onResult ");
                    PrivacyUpdateResultCallback privacyUpdateResultCallback = privacyUpdateResultCallback;
                    if (privacyUpdateResultCallback != null) {
                        privacyUpdateResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                    }
                }
            });
            return 1;
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return -1;
        }
    }

    public void requestProfileBirthdayTypeUpdate(ProfileBirthdayType profileBirthdayType, final ProfileUpdateResultCallback profileUpdateResultCallback) {
        debugLog("requestProfileBirthdayTypeUpdate");
        if (isAppIdNullOrEmpty()) {
            profileUpdateResultCallback.onResult(new BooleanResult(new CommonResultStatus(-1, "appId id null or empty", ""), false));
        }
        if (!isSupportedSaAgentVersion(CommonConstants.SupportedApiMinVersion.VERSION_11_0)) {
            profileUpdateResultCallback.onResult(new BooleanResult(new CommonResultStatus(200), false));
        }
        try {
            getProfileService().requestProfileBirthdayTypeUpdate(convertProfileBirthdayTypeToString(profileBirthdayType), new IProfileUpdateResultCallback.Stub() {
                public void onFailure(String str, String str2) {
                    ProfileApi.this.debugLog(j.d("requestProfileBirthdayTypeUpdate onFailure : code=[", str, "], message=[", str2, "] "));
                    if (profileUpdateResultCallback != null) {
                        profileUpdateResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(str), str2, str), false));
                    }
                }

                public void onResult() {
                    ProfileApi.this.debugLog("requestProfileBirthdayTypeUpdate onResult ");
                    ProfileUpdateResultCallback profileUpdateResultCallback = profileUpdateResultCallback;
                    if (profileUpdateResultCallback != null) {
                        profileUpdateResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                    }
                }
            });
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
        }
    }

    public int requestProfileUpdate(Profile profile, final ProfileUpdateResultCallback profileUpdateResultCallback) {
        debugLog("requestProfileUpdate ");
        if (isAppIdNullOrEmpty()) {
            profileUpdateResultCallback.onResult(new BooleanResult(new CommonResultStatus(-1, "appId id null or empty", ""), false));
            return -1;
        }
        try {
            profile.setConnectedProfileVersion(this.mConnectedProfileVersion);
            getProfileService().requestProfileUpdate(profile, new IProfileUpdateResultCallback.Stub() {
                public void onFailure(String str, String str2) {
                    ProfileApi.this.debugLog(j.d("requestProfileUpdate onFailure : code=[", str, "], message=[", str2, "] "));
                    if (profileUpdateResultCallback != null) {
                        profileUpdateResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(str), str2, str), false));
                    }
                }

                public void onResult() {
                    ProfileApi.this.debugLog("requestProfileUpdate onResult ");
                    ProfileUpdateResultCallback profileUpdateResultCallback = profileUpdateResultCallback;
                    if (profileUpdateResultCallback != null) {
                        profileUpdateResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                    }
                }
            });
            return 1;
        } catch (TransactionTooLargeException e) {
            verboseLog("TransactionTooLargeException occurred");
            secureLog(e);
            return -1;
        } catch (RemoteException | NotConnectedException | NullPointerException e7) {
            secureLog(e7);
            return -1;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return -1;
        }
    }

    @Deprecated
    public int requestSync(final SyncResultCallback syncResultCallback) {
        debugLog("requestSync ");
        if (isAppIdNullOrEmpty()) {
            syncResultCallback.onResult(new ProfileResult(new CommonResultStatus(-1, "appId is null or empty", ""), (Profile) null));
            return -1;
        }
        try {
            getProfileService().requestSync(new ISyncResultCallback.Stub() {
                public void onFailure(String str, String str2) {
                    ProfileApi.this.debugLog(j.d("requestSync onFailure : code=[", str, "], message=[", str2, "] "));
                    if (syncResultCallback != null) {
                        syncResultCallback.onResult(new ProfileResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(str), str2, str), (Profile) null));
                    }
                }

                public void onSuccess(Profile profile) {
                    ProfileApi.this.debugLog("requestSync onSuccess ");
                    SyncResultCallback syncResultCallback = syncResultCallback;
                    if (syncResultCallback != null) {
                        syncResultCallback.onResult(new ProfileResult(new CommonResultStatus(1), profile));
                    }
                }
            });
            return 1;
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return -1;
        }
    }
}
