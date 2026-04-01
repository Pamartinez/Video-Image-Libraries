package com.samsung.android.sdk.mobileservice.social.activity;

import A.a;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.mobileservice.common.api.SeMobileServiceApi;
import com.samsung.android.sdk.mobileservice.common.exception.NotConnectedException;
import com.samsung.android.sdk.mobileservice.common.result.BooleanResult;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.social.activity.IActivityBundlePartialResultCallback;
import com.samsung.android.sdk.mobileservice.social.activity.IActivityBundleResultCallback;
import com.samsung.android.sdk.mobileservice.social.activity.IActivityResultCallback;
import com.samsung.android.sdk.mobileservice.social.activity.IDeleteAllActivityResultCallback;
import com.samsung.android.sdk.mobileservice.social.activity.result.ActivityResult;
import com.samsung.android.sdk.mobileservice.social.common.IBundleProgressResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupContract;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.EngramBundleWrapper;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActivityApi extends SeMobileServiceApi {
    public static final String API_NAME = "ActivityApi";
    private static final int MAX_SIZE_FOR_REQUEST_ACTIVITYLIST = 5;
    public static final int PRIVACY_TYPE_ALL = 0;
    public static final int PRIVACY_TYPE_CONTACTS = 2;
    public static final int PRIVACY_TYPE_NOT_SELECTED = 3;
    public static final int PRIVACY_TYPE_PRIVATE = 1;
    private final int ACTIVITY_TYPE_ALL = -1;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ActivityContentRequest extends ActivityRequest {
        private String mHash;

        public ActivityContentRequest(String str, String str2, int i2, String str3) {
            super(str, str2, i2);
            this.mHash = str3;
        }

        public String getContentHash() {
            return this.mHash;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ActivityDownloadResultCallback<T> {
        void onPartialResult(T t);

        void onResult(ActivityResult<T> activityResult);
    }

    @Deprecated
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ActivityImageListResultCallback<T> {
        void onProgress(T t);

        void onResult(ActivityResult<List<T>> activityResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ActivityResultCallback<T> {
        void onResult(ActivityResult<T> activityResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ClearMyActivityResultCallback {
        void onResult(BooleanResult booleanResult);
    }

    public ActivityApi(SeMobileServiceSession seMobileServiceSession) {
        super(seMobileServiceSession, "ActivityApi");
        checkAuthorized(0, 2, 1);
    }

    /* access modifiers changed from: private */
    public Activity bundleToActivity(Bundle bundle) {
        int i2;
        Bundle bundle2 = bundle;
        if (bundle2 == null) {
            debugLog("bundle is null : bundleToActivity");
            return null;
        }
        String string = bundle2.getString("activityId");
        String string2 = bundle2.getString("statusMessage", (String) null);
        String string3 = bundle2.getString("memo", string2);
        String string4 = bundle2.getString("activityType", (String) null);
        if (string4 != null && TextUtils.equals(string4, "post")) {
            i2 = 4;
        } else if (!TextUtils.isEmpty(string3) || !TextUtils.isEmpty(string2)) {
            i2 = 1;
        } else {
            i2 = 2;
        }
        int i7 = i2;
        Uri bundleToUri = bundleToUri(bundle2, "activityImageContentUri");
        return new Activity(string, string3, i7, bundle2.getLong(GroupContract.Group.CREATED_TIME, 0), bundle2.getLong("modifiedTime", 0), bundle2.getString("owner"), bundle2.getString("ownerName"), bundleToUri, bundleToUri(bundle2, "profileImageContentUri"), bundle2.getString(MediaDefs.Image.HEIF.HEIF_META_BOX, (String) null), bundleToContentInfoList(bundle));
    }

    /* access modifiers changed from: private */
    public ActivityImage bundleToActivityImage(Bundle bundle) {
        if (bundle == null) {
            debugLog("bundle is null : bundleToActivityImage");
            return null;
        }
        return new ActivityImage(bundle.getString("guid"), bundle.getString("activityId"), bundleToUri(bundle, "profileImageContentUri"), bundleToUri(bundle, "activityImageContentUri"));
    }

    /* access modifiers changed from: private */
    public List<ActivityImage> bundleToActivityImageList(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        if (bundle == null) {
            debugLog("bundle is null : bundleToActivityImageList");
            return arrayList;
        }
        ArrayList<Bundle> parcelableArrayList = bundle.getParcelableArrayList("images");
        if (parcelableArrayList != null) {
            for (Bundle bundleToActivityImage : parcelableArrayList) {
                arrayList.add(bundleToActivityImage(bundleToActivityImage));
            }
        }
        debugLog("bundleToActivityImageList size : " + arrayList.size());
        return arrayList;
    }

    /* access modifiers changed from: private */
    public List<Activity> bundleToActivityList(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        if (bundle == null) {
            debugLog("bundle is null : bundleToActivityList");
            return arrayList;
        }
        ArrayList<Bundle> parcelableArrayList = bundle.getParcelableArrayList(EngramBundleWrapper.BUNDLE_KEY_ACTIVITIES);
        if (parcelableArrayList != null) {
            for (Bundle bundleToActivity : parcelableArrayList) {
                arrayList.add(bundleToActivity(bundleToActivity));
            }
        }
        debugLog("bundleToActivityList size : " + arrayList.size());
        return arrayList;
    }

    private ContentInfo bundleToContentInfo(Bundle bundle) {
        if (bundle != null) {
            return new ContentInfo(bundle.getString("name"), bundle.getString("hash"), bundle.getLong("size", 0), bundle.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX), bundleToUri(bundle, "contentUri"));
        }
        debugLog("bundle is null : bundleToActivityContents");
        return null;
    }

    private List<ContentInfo> bundleToContentInfoList(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        if (bundle == null) {
            debugLog("bundle is null : bundleToContentInfoList");
            return arrayList;
        }
        ArrayList<Bundle> parcelableArrayList = bundle.getParcelableArrayList("contentsInfo");
        if (parcelableArrayList != null) {
            for (Bundle bundleToContentInfo : parcelableArrayList) {
                arrayList.add(bundleToContentInfo(bundleToContentInfo));
            }
        }
        debugLog("bundleToContentInfoList size : " + arrayList.size());
        return arrayList;
    }

    private CommonResultStatus bundleToResult(Bundle bundle) {
        if (bundle == null) {
            debugLog("bundle is null : bundleToResult");
            return null;
        }
        long j2 = bundle.getLong("errorCode", -1);
        if (j2 == -1) {
            debugLog("not error : bundleToResult");
            return null;
        }
        String string = bundle.getString("errorMessage", (String) null);
        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(j2);
        return new CommonResultStatus(convertErrorcode, string, Long.toString((long) convertErrorcode));
    }

    /* access modifiers changed from: private */
    public Uri bundleToUri(Bundle bundle, String str) {
        if (bundle == null) {
            debugLog("bundle is null : bundleToUri");
            return null;
        }
        String string = bundle.getString(str, (String) null);
        if (!TextUtils.isEmpty(string)) {
            return Uri.parse(string);
        }
        return null;
    }

    private <T> ActivityResult<T> getErrorActivityResult(int i2) {
        if (!isSupportedSemsAgentVersionMoreThan(i2)) {
            return new ActivityResult<>(new CommonResultStatus(-7), null);
        }
        return null;
    }

    private boolean isResultSuccess(CommonResultStatus commonResultStatus) {
        if (commonResultStatus == null) {
            return true;
        }
        return false;
    }

    public int getBuddyActivityCount() {
        debugLog("getBuddyActivityCount");
        return getBuddyActivityCount((String) null, -1, 3);
    }

    public ActivityResult<List<Activity>> getBuddyActivityList(long j2, int i2) {
        debugLog("getBuddyActivityList : timestamp=[" + j2 + "] limit=[" + i2 + "]");
        return getBuddyActivityList((String) null, -1, 3, j2, i2);
    }

    public String[] getEssentialServiceNames() {
        return new String[]{"SocialService"};
    }

    @Deprecated
    public int requestActivity(String str, String str2, ActivityDownloadResultCallback<Activity> activityDownloadResultCallback) {
        debugLog("requestActivity : activityId=[" + str2 + "]");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        return requestActivity(str, str2, 2, activityDownloadResultCallback);
    }

    public int requestActivityContent(ActivityContentRequest activityContentRequest, final ActivityResultCallback<Uri> activityResultCallback) {
        debugLog("requestActivityContent");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_5)) {
            return -7;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("guid", activityContentRequest.getGuid());
            bundle.putString("activityId", activityContentRequest.getActivityId());
            bundle.putInt("activityType", activityContentRequest.getActivityType());
            bundle.putString("hash", activityContentRequest.getContentHash());
            getSocialService().requestActivityContent(bundle, new IActivityBundleResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ActivityApi activityApi = ActivityApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestActivityContent onFailure : code=[", "], message=[", str);
                    p6.append("]");
                    activityApi.debugLog(p6.toString());
                    if (activityResultCallback != null) {
                        activityResultCallback.onResult(new ActivityResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), null));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    ActivityApi.this.debugLog("requestActivityContent onSuccess");
                    if (activityResultCallback != null) {
                        activityResultCallback.onResult(new ActivityResult(new CommonResultStatus(1), ActivityApi.this.bundleToUri(bundle, "contentUri")));
                    }
                }
            });
            return 1;
        } catch (NotConnectedException e) {
            secureLog(e);
            return -8;
        } catch (Exception e7) {
            debugLog("requestActivityContent fail");
            secureLog(e7);
            return -1;
        }
    }

    @Deprecated
    public int requestActivityImageList(List<ActivityRequest> list, final ActivityImageListResultCallback<ActivityImage> activityImageListResultCallback) {
        debugLog("requestActivityImageList");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (ActivityRequest next : list) {
                Bundle bundle = new Bundle();
                bundle.putString("guid", next.getGuid());
                bundle.putString("activityId", next.getActivityId());
                bundle.putInt("activityType", next.getActivityType());
                arrayList.add(bundle);
            }
            Bundle bundle2 = new Bundle();
            bundle2.putParcelableArrayList("requestInfo", arrayList);
            getSocialService().requestActivityImageList(bundle2, new IBundleProgressResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ActivityApi activityApi = ActivityApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestActivityImageList onFailure : code=[", "], message=[", str);
                    p6.append("]");
                    activityApi.debugLog(p6.toString());
                    if (activityImageListResultCallback != null) {
                        activityImageListResultCallback.onResult(new ActivityResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), null));
                    }
                }

                public void onProgress(Bundle bundle) {
                    ActivityApi.this.debugLog("requestActivityImageList onProgress");
                    ActivityImageListResultCallback activityImageListResultCallback = activityImageListResultCallback;
                    if (activityImageListResultCallback != null) {
                        activityImageListResultCallback.onProgress(ActivityApi.this.bundleToActivityImage(bundle));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    ActivityApi.this.debugLog("requestActivityImageList onSuccess");
                    ActivityImageListResultCallback activityImageListResultCallback = activityImageListResultCallback;
                    if (activityImageListResultCallback != null) {
                        activityImageListResultCallback.onResult(new ActivityResult(new CommonResultStatus(1), ActivityApi.this.bundleToActivityImageList(bundle)));
                    }
                }
            });
            return 1;
        } catch (NotConnectedException e) {
            debugLog("requestActivityImageList fail");
            secureLog(e);
            return -8;
        } catch (Exception e7) {
            debugLog("requestActivityImageList fail");
            secureLog(e7);
            return -1;
        }
    }

    public int requestActivityList(String str, long j2, ActivityDownloadResultCallback<List<Activity>> activityDownloadResultCallback) {
        debugLog("requestActivityList : timestamp:" + j2);
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        return requestActivityList(str, j2, 5, activityDownloadResultCallback);
    }

    public int requestActivitySync(final ActivityDownloadResultCallback<Boolean> activityDownloadResultCallback) {
        debugLog("requestActivitySync with ActivityDownloadResultCallback");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            getSocialService().requestActivityChanges(new IActivityBundlePartialResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ActivityApi activityApi = ActivityApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestActivitySync onFailure : code=[", "], message=[", str);
                    p6.append("]");
                    activityApi.debugLog(p6.toString());
                    if (activityDownloadResultCallback != null) {
                        activityDownloadResultCallback.onResult(new ActivityResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), Boolean.FALSE));
                    }
                }

                public void onPartialResult(Bundle bundle) {
                    ActivityApi.this.debugLog("requestActivitySync onPartialResult");
                    ActivityDownloadResultCallback activityDownloadResultCallback = activityDownloadResultCallback;
                    if (activityDownloadResultCallback != null) {
                        activityDownloadResultCallback.onPartialResult(Boolean.TRUE);
                    }
                }

                public void onSuccess(Bundle bundle) {
                    ActivityApi.this.debugLog("requestActivitySync onSuccess");
                    ActivityDownloadResultCallback activityDownloadResultCallback = activityDownloadResultCallback;
                    if (activityDownloadResultCallback != null) {
                        activityDownloadResultCallback.onResult(new ActivityResult(new CommonResultStatus(1), Boolean.TRUE));
                    }
                }
            });
            return 1;
        } catch (NotConnectedException e) {
            secureLog(e);
            return -8;
        } catch (Exception e7) {
            secureLog(e7);
            return -1;
        }
    }

    @Deprecated
    public int requestMyActivityClear(final ClearMyActivityResultCallback clearMyActivityResultCallback) {
        debugLog("requestMyActivityClear started");
        try {
            getSocialService().requestDeleteAllActivity(new IDeleteAllActivityResultCallback.Stub() {
                public void onFailure(int i2, String str) {
                    ActivityApi.this.debugLog("requestMyActivityClear onFailure");
                    if (clearMyActivityResultCallback != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(i2);
                        ActivityApi activityApi = ActivityApi.this;
                        StringBuilder h5 = a.h(convertErrorcode, i2, "requestDeleteAllActivity onFailure (", ArcCommonLog.TAG_COMMA, ArcCommonLog.TAG_COMMA);
                        h5.append(str);
                        h5.append(")");
                        activityApi.debugLog(h5.toString());
                        clearMyActivityResultCallback.onResult(new BooleanResult(new CommonResultStatus(convertErrorcode, str, Integer.toString(i2)), false));
                    }
                }

                public void onSuccess() {
                    ActivityApi.this.debugLog("requestMyActivityClear onSuccess");
                    ClearMyActivityResultCallback clearMyActivityResultCallback = clearMyActivityResultCallback;
                    if (clearMyActivityResultCallback != null) {
                        clearMyActivityResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
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

    @Deprecated
    public int requestMyActivityDeletion(String str, ActivityResultCallback<Boolean> activityResultCallback) {
        return requestMyActivityDeletion(str, 2, activityResultCallback);
    }

    public int requestMyActivityPrivacyType(final ActivityResultCallback<Integer> activityResultCallback) {
        debugLog("requestMyActivityPrivacyType");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            getSocialService().requestMyActivityPrivacy(new IActivityBundleResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ActivityApi activityApi = ActivityApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestMyActivityPrivacyType onFailure : code=[", "], message=[", str);
                    p6.append("]");
                    activityApi.debugLog(p6.toString());
                    if (activityResultCallback != null) {
                        activityResultCallback.onResult(new ActivityResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), null));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    Integer num;
                    ActivityApi.this.debugLog("requestMyActivityPrivacyType onSuccess");
                    if (activityResultCallback != null) {
                        if (bundle != null) {
                            num = Integer.valueOf(bundle.getInt("privacyType"));
                        } else {
                            num = null;
                        }
                        activityResultCallback.onResult(new ActivityResult(new CommonResultStatus(1), num));
                    }
                }
            });
            return 1;
        } catch (NotConnectedException e) {
            secureLog(e);
            return -8;
        } catch (Exception e7) {
            secureLog(e7);
            return -1;
        }
    }

    public int requestMyActivityPrivacyTypeUpdate(int i2, int i7, final ActivityResultCallback<Boolean> activityResultCallback) {
        debugLog("requestMyActivityPrivacyTypeUpdate");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("privacyType", i2);
            bundle.putInt("oldPrivacyType", i7);
            getSocialService().requestMyActivityPrivacyUpdate(bundle, new IActivityResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ActivityApi activityApi = ActivityApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestMyActivityPrivacyTypeUpdate onFailure : code=[", "], message=[", str);
                    p6.append("]");
                    activityApi.debugLog(p6.toString());
                    if (activityResultCallback != null) {
                        activityResultCallback.onResult(new ActivityResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), Boolean.FALSE));
                    }
                }

                public void onSuccess() {
                    ActivityApi.this.debugLog("requestMyActivityPrivacyTypeUpdate onSuccess");
                    ActivityResultCallback activityResultCallback = activityResultCallback;
                    if (activityResultCallback != null) {
                        activityResultCallback.onResult(new ActivityResult(new CommonResultStatus(1), Boolean.TRUE));
                    }
                }
            });
            return 1;
        } catch (NotConnectedException e) {
            secureLog(e);
            return -8;
        } catch (Exception e7) {
            secureLog(e7);
            return -1;
        }
    }

    public int setAllBuddyActivityListRead() {
        debugLog("setAllBuddyActivityListRead");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            if (isResultSuccess(bundleToResult(getSocialService().setBuddyActivityListRead(new Bundle())))) {
                debugLog("setAllBuddyActivityListRead success");
                return 1;
            }
            debugLog("setAllBuddyActivityListRead fail");
            return -1;
        } catch (NotConnectedException e) {
            debugLog("setAllBuddyActivityListRead fail");
            secureLog(e);
            return -8;
        } catch (Exception e7) {
            debugLog("setAllBuddyActivityListRead fail");
            secureLog(e7);
            return -1;
        }
    }

    public int setBuddyActivityListRead(List<ActivityRequest> list) {
        debugLog("setBuddyActivityListRead");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (ActivityRequest next : list) {
                Bundle bundle = new Bundle();
                bundle.putString("guid", next.getGuid());
                bundle.putString("activityId", next.getActivityId());
                arrayList.add(bundle);
            }
            Bundle bundle2 = new Bundle();
            bundle2.putParcelableArrayList("requestInfo", arrayList);
            if (isResultSuccess(bundleToResult(getSocialService().setBuddyActivityListRead(bundle2)))) {
                debugLog("setBuddyActivityListRead success");
                return 1;
            }
            debugLog("setBuddyActivityListRead fail");
            return -1;
        } catch (NotConnectedException e) {
            debugLog("setBuddyActivityListRead fail");
            secureLog(e);
            return -8;
        } catch (Exception e7) {
            debugLog("setBuddyActivityListRead fail");
            secureLog(e7);
            return -1;
        }
    }

    public int requestMyActivityDeletion(String str, int i2, final ActivityResultCallback<Boolean> activityResultCallback) {
        debugLog("requestMyActivityDeletion : activityId=[" + str + "]");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        if (TextUtils.isEmpty(str)) {
            debugLog("activityId is empty");
            return -1;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("activityId", str);
            bundle.putInt("activityType", i2);
            getSocialService().requestActivityDeletion(bundle, new IActivityResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ActivityApi.this.debugLog("requestMyActivityDeletion onFailure");
                    if (activityResultCallback != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(j2);
                        ActivityApi activityApi = ActivityApi.this;
                        activityApi.debugLog("requestMyActivityDeletion onFailure (" + convertErrorcode + ArcCommonLog.TAG_COMMA + j2 + ArcCommonLog.TAG_COMMA + str + ")");
                        activityResultCallback.onResult(new ActivityResult(new CommonResultStatus(convertErrorcode, str, Long.toString(j2)), Boolean.FALSE));
                    }
                }

                public void onSuccess() {
                    ActivityApi.this.debugLog("requestMyActivityDeletion onSuccess");
                    ActivityResultCallback activityResultCallback = activityResultCallback;
                    if (activityResultCallback != null) {
                        activityResultCallback.onResult(new ActivityResult(new CommonResultStatus(1), Boolean.TRUE));
                    }
                }
            });
            return 1;
        } catch (NotConnectedException e) {
            secureLog(e);
            return -8;
        } catch (Exception e7) {
            secureLog(e7);
            return -1;
        }
    }

    public int getBuddyActivityCount(int i2, int i7) {
        debugLog(a.d(i2, i7, "getBuddyActivityCount : activityType=[", "] readType=[", "]"));
        return getBuddyActivityCount((String) null, i2, i7);
    }

    public ActivityResult<List<Activity>> getBuddyActivityList(int i2, int i7, long j2, int i8) {
        StringBuilder h5 = a.h(i2, i7, "getBuddyActivityList : activityType=[", "] readType=[", "] timestamp=[");
        h5.append(j2);
        h5.append("] limit=[");
        h5.append(i8);
        h5.append("]");
        debugLog(h5.toString());
        return getBuddyActivityList((String) null, i2, i7, j2, i8);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ActivityRequest {
        private String mActivityId;
        private int mActivityType;
        private String mGuid;

        public ActivityRequest(String str, String str2) {
            this.mGuid = str;
            this.mActivityId = str2;
            this.mActivityType = 2;
        }

        public String getActivityId() {
            return this.mActivityId;
        }

        public int getActivityType() {
            return this.mActivityType;
        }

        public String getGuid() {
            return this.mGuid;
        }

        public ActivityRequest(String str, String str2, int i2) {
            this.mGuid = str;
            this.mActivityId = str2;
            this.mActivityType = i2;
        }
    }

    public int requestActivity(String str, String str2, int i2, final ActivityDownloadResultCallback<Activity> activityDownloadResultCallback) {
        debugLog("requestActivity : activityId=[" + str2 + "]");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            debugLog("guid or activityId is empty");
            return -1;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("guid", str);
            bundle.putString("activityId", str2);
            bundle.putInt("activityType", i2);
            getSocialService().requestActivity(bundle, new IActivityBundlePartialResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ActivityApi activityApi = ActivityApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestActivity onFailure : code=[", "], message=[", str);
                    p6.append("]");
                    activityApi.debugLog(p6.toString());
                    if (activityDownloadResultCallback != null) {
                        activityDownloadResultCallback.onResult(new ActivityResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), null));
                    }
                }

                public void onPartialResult(Bundle bundle) {
                    ActivityApi.this.debugLog("requestActivity onPartialResult");
                    ActivityDownloadResultCallback activityDownloadResultCallback = activityDownloadResultCallback;
                    if (activityDownloadResultCallback != null) {
                        activityDownloadResultCallback.onPartialResult(ActivityApi.this.bundleToActivity(bundle));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    ActivityApi.this.debugLog("requestActivity onSuccess");
                    ActivityDownloadResultCallback activityDownloadResultCallback = activityDownloadResultCallback;
                    if (activityDownloadResultCallback != null) {
                        activityDownloadResultCallback.onResult(new ActivityResult(new CommonResultStatus(1), ActivityApi.this.bundleToActivity(bundle)));
                    }
                }
            });
            return 1;
        } catch (NotConnectedException e) {
            secureLog(e);
            return -8;
        } catch (Exception e7) {
            secureLog(e7);
            return -1;
        }
    }

    @Deprecated
    public int requestActivityList(String str, long j2, int i2, final ActivityDownloadResultCallback<List<Activity>> activityDownloadResultCallback) {
        debugLog("requestActivityList : timestamp:" + j2 + ", limit:" + i2);
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        if (TextUtils.isEmpty(str)) {
            debugLog("guid is empty");
            return -1;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("guid", str);
            if (j2 > 0) {
                bundle.putLong("timestamp", j2);
            }
            if (i2 <= 0) {
                i2 = 5;
            }
            bundle.putInt("limit", i2);
            getSocialService().requestActivityList(bundle, new IActivityBundlePartialResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ActivityApi activityApi = ActivityApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestActivityList onFailure : code=[", "], message=[", str);
                    p6.append("]");
                    activityApi.debugLog(p6.toString());
                    if (activityDownloadResultCallback != null) {
                        activityDownloadResultCallback.onResult(new ActivityResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), null));
                    }
                }

                public void onPartialResult(Bundle bundle) {
                    ActivityApi.this.debugLog("requestActivityList onPartialResult");
                    if (activityDownloadResultCallback != null) {
                        activityDownloadResultCallback.onPartialResult(ActivityApi.this.bundleToActivityList(bundle));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    ActivityApi.this.debugLog("requestActivityList onSuccess");
                    if (activityDownloadResultCallback != null) {
                        activityDownloadResultCallback.onResult(new ActivityResult(new CommonResultStatus(1), ActivityApi.this.bundleToActivityList(bundle)));
                    }
                }
            });
            return 1;
        } catch (NotConnectedException e) {
            secureLog(e);
            return -8;
        } catch (Exception e7) {
            secureLog(e7);
            return -1;
        }
    }

    @Deprecated
    public int requestActivitySync(final ActivityResultCallback<Boolean> activityResultCallback) {
        debugLog("requestActivitySync");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            getSocialService().requestActivitySync(new IActivityBundleResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    ActivityApi activityApi = ActivityApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestActivitySync onFailure : code=[", "], message=[", str);
                    p6.append("]");
                    activityApi.debugLog(p6.toString());
                    if (activityResultCallback != null) {
                        activityResultCallback.onResult(new ActivityResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), Boolean.FALSE));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    ActivityApi.this.debugLog("requestActivitySync onSuccess");
                    ActivityResultCallback activityResultCallback = activityResultCallback;
                    if (activityResultCallback != null) {
                        activityResultCallback.onResult(new ActivityResult(new CommonResultStatus(1), Boolean.TRUE));
                    }
                }
            });
            return 1;
        } catch (NotConnectedException e) {
            secureLog(e);
            return -8;
        } catch (Exception e7) {
            secureLog(e7);
            return -1;
        }
    }

    public ActivityResult<List<Activity>> getBuddyActivityList(String str, int i2, int i7, long j2, int i8) {
        StringBuilder h5 = a.h(i2, i7, "getBuddyActivityList : activityType=[", "] readType=[", "] timestamp=[");
        h5.append(j2);
        h5.append("] limit=[");
        h5.append(i8);
        h5.append("]");
        debugLog(h5.toString());
        ActivityResult<List<Activity>> errorActivityResult = getErrorActivityResult(CommonConstants.SupportedApiMinVersion.VERSION_10_0);
        if (errorActivityResult != null) {
            return errorActivityResult;
        }
        try {
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("guid", str);
            }
            if (i2 != -1) {
                bundle.putInt("activityType", i2);
            }
            bundle.putInt("readType", i7);
            if (j2 > 0) {
                bundle.putLong("timestamp", j2);
            }
            if (i8 > 0) {
                bundle.putInt("limit", i8);
            }
            Bundle buddyActivityList = getSocialService().getBuddyActivityList(bundle);
            CommonResultStatus bundleToResult = bundleToResult(buddyActivityList);
            if (isResultSuccess(bundleToResult)) {
                debugLog("getBuddyActivityList success");
                return new ActivityResult<>(new CommonResultStatus(1), bundleToActivityList(buddyActivityList));
            }
            debugLog("getBuddyActivityList fail");
            return new ActivityResult<>(bundleToResult, null);
        } catch (NotConnectedException e) {
            debugLog("getBuddyActivityList fail");
            secureLog(e);
            return new ActivityResult<>(new CommonResultStatus(-8), null);
        } catch (Exception e7) {
            debugLog("getBuddyActivityList fail");
            secureLog(e7);
            return new ActivityResult<>(new CommonResultStatus(-1), null);
        }
    }

    public int getBuddyActivityCount(String str, int i2, int i7) {
        debugLog(a.d(i2, i7, "getBuddyActivityCount : activityType=[", "] readType=[", "]"));
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_1)) {
            return -7;
        }
        try {
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("guid", str);
            }
            if (i2 != -1) {
                bundle.putInt("activityType", i2);
            }
            bundle.putInt("readType", i7);
            Bundle buddyActivityCount = getSocialService().getBuddyActivityCount(bundle);
            if (isResultSuccess(bundleToResult(buddyActivityCount))) {
                int i8 = buddyActivityCount.getInt("totalCount", -1);
                debugLog("getBuddyActivityCount success - totalCount:" + i8);
                return i8;
            }
            debugLog("getBuddyActivityCount fail");
            return -1;
        } catch (NotConnectedException e) {
            debugLog("getBuddyActivityCount fail");
            secureLog(e);
            return -8;
        } catch (Exception e7) {
            debugLog("getBuddyActivityCount fail");
            secureLog(e7);
            return -1;
        }
    }
}
