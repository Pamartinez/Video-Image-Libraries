package com.samsung.android.sdk.mobileservice.social.feedback;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.mobileservice.common.api.SeMobileServiceApi;
import com.samsung.android.sdk.mobileservice.common.exception.NotConnectedException;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.social.common.IBundleProgressResultCallback;
import com.samsung.android.sdk.mobileservice.social.feedback.EmotionMemberList;
import com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundlePartialResultCallback;
import com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundleResultCallback;
import com.samsung.android.sdk.mobileservice.social.feedback.result.FeedbackResult;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupContract;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.EngramBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.GraphPathEdgeBundleWrapper;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FeedbackApi extends SeMobileServiceApi {
    public static final String API_NAME = "FeedbackApi";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface FeedbackDownloadResultCallback<T> {
        void onPartialResult(T t);

        void onResult(FeedbackResult<T> feedbackResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface FeedbackResultCallback<T> {
        void onResult(FeedbackResult<T> feedbackResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ProfileImageListResultCallback<T> {
        void onProgress(T t);

        void onResult(FeedbackResult<List<T>> feedbackResult);
    }

    public FeedbackApi(SeMobileServiceSession seMobileServiceSession) {
        super(seMobileServiceSession, "FeedbackApi");
        checkAuthorized(0, 2, 1);
    }

    /* access modifiers changed from: private */
    public <T extends ContentId> EmotionMemberList<T> bundleToActivityEmotionMemberList(T t, String str, Bundle bundle) {
        Uri uri;
        if (bundle == null) {
            debugLog("bundle is null : bundleToActivityEmotionMemberList");
            return null;
        }
        String string = bundle.getString("nextMemberGuid");
        ArrayList arrayList = new ArrayList();
        ArrayList<Bundle> parcelableArrayList = bundle.getParcelableArrayList("members");
        if (parcelableArrayList != null) {
            for (Bundle bundle2 : parcelableArrayList) {
                String string2 = bundle2.getString("memberName", (String) null);
                String string3 = bundle2.getString("memberId");
                long j2 = bundle2.getLong("updateTime", 0);
                int i2 = bundle2.getInt("emotionType", 0);
                String string4 = bundle2.getString("profileImageContentUri", (String) null);
                if (!TextUtils.isEmpty(string4)) {
                    uri = Uri.parse(string4);
                } else {
                    uri = null;
                }
                arrayList.add(new EmotionMemberList.EmotionMember(new UserProfile(string3, string2, uri), j2, i2));
            }
        }
        debugLog("bundleToActivityEmotionMemberList size : " + arrayList.size());
        return new EmotionMemberList<>(t, str, string, arrayList);
    }

    private Comment bundleToComment(Bundle bundle) {
        Uri uri = null;
        if (bundle == null) {
            debugLog("bundle is null : bundleToComment");
            return null;
        }
        ArrayList<Bundle> parcelableArrayList = bundle.getParcelableArrayList("emotions");
        ArrayList arrayList = new ArrayList();
        if (parcelableArrayList != null) {
            for (Bundle bundleToEmotion : parcelableArrayList) {
                arrayList.add(bundleToEmotion(bundleToEmotion));
            }
        }
        String string = bundle.getString("commentId");
        String string2 = bundle.getString(GraphPathEdgeBundleWrapper.BUNDLE_KEY_COMMENT);
        String string3 = bundle.getString(GroupContract.Group.LEADER_ID);
        String string4 = bundle.getString("ownerName", (String) null);
        long j2 = bundle.getLong(GroupContract.Group.CREATED_TIME, 0);
        int i2 = bundle.getInt("myEmotionType", -1);
        String string5 = bundle.getString("profileImageContentUri", (String) null);
        if (!TextUtils.isEmpty(string5)) {
            uri = Uri.parse(string5);
        }
        return new Comment(string, string2, new UserProfile(string3, string4, uri), j2, new EmotionList(i2, arrayList));
    }

    /* access modifiers changed from: private */
    public CommentList bundleToCommentList(Bundle bundle) {
        if (bundle == null) {
            debugLog("bundle is null : bundleToCommentList");
            return null;
        }
        ArrayList<Bundle> parcelableArrayList = bundle.getParcelableArrayList("comments");
        ArrayList arrayList = new ArrayList();
        if (parcelableArrayList != null) {
            for (Bundle bundleToComment : parcelableArrayList) {
                arrayList.add(bundleToComment(bundleToComment));
            }
        }
        String string = bundle.getString("nextCommentId", (String) null);
        debugLog("bundleToCommentList size : " + arrayList.size());
        return new CommentList(string, arrayList);
    }

    private ContentId bundleToContentId(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (bundle.getInt("contentIdType", -1) == 1) {
            return new ActivityContentId(bundle);
        }
        debugLog("bundleToContentId is error" + bundle.getInt("contentIdType", -1));
        return null;
    }

    /* access modifiers changed from: private */
    public Emotion bundleToEmotion(Bundle bundle) {
        if (bundle != null) {
            return new Emotion(bundle.getInt("emotionType", 0), bundle.getInt("count", 0));
        }
        debugLog("bundle is null : bundleToEmotion");
        return null;
    }

    /* access modifiers changed from: private */
    public Feedback<?> bundleToFeedback(Bundle bundle) {
        if (bundle == null) {
            debugLog("bundle is null : bundleToActivityFeedback");
            return null;
        }
        ArrayList<Bundle> parcelableArrayList = bundle.getParcelableArrayList("comments");
        ArrayList arrayList = new ArrayList();
        if (parcelableArrayList != null) {
            for (Bundle bundleToComment : parcelableArrayList) {
                arrayList.add(bundleToComment(bundleToComment));
            }
        }
        ArrayList<Bundle> parcelableArrayList2 = bundle.getParcelableArrayList("emotions");
        ArrayList arrayList2 = new ArrayList();
        if (parcelableArrayList2 != null) {
            for (Bundle bundleToEmotion : parcelableArrayList2) {
                arrayList2.add(bundleToEmotion(bundleToEmotion));
            }
        }
        ContentId bundleToContentId = bundleToContentId(bundle);
        int i2 = bundle.getInt("myEmotionType", -1);
        return new Feedback<>(bundleToContentId, new EmotionList(i2, arrayList2), bundle.getInt("commentCount"), new CommentList(bundle.getString("nextCommentId"), arrayList));
    }

    /* access modifiers changed from: private */
    public <T extends ContentId> List<Feedback<T>> bundleToFeedbackList(Bundle bundle, List<Feedback<T>> list) {
        if (bundle == null) {
            debugLog("bundle is null : bundleToActivityFeedback");
            return null;
        }
        ArrayList<Bundle> parcelableArrayList = bundle.getParcelableArrayList(EngramBundleWrapper.BUNDLE_KEY_ACTIVITIES);
        if (parcelableArrayList != null) {
            for (Bundle bundleToFeedback : parcelableArrayList) {
                list.add(bundleToFeedback(bundleToFeedback));
            }
        }
        debugLog("bundleToFeedbackList size : " + list.size());
        return list;
    }

    private List<Notification<?>> bundleToNotificationList(Bundle bundle) {
        Uri uri;
        Bundle bundle2 = bundle;
        if (bundle2 == null) {
            debugLog("bundle is null : bundleToNotificationList");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList<Bundle> parcelableArrayList = bundle2.getParcelableArrayList("notifications");
        if (parcelableArrayList != null) {
            for (Bundle bundle3 : parcelableArrayList) {
                String string = bundle3.getString("notificationId");
                String string2 = bundle3.getString("senderGuid");
                String string3 = bundle3.getString("senderName", (String) null);
                ContentId bundleToContentId = bundleToContentId(bundle3);
                String string4 = bundle3.getString("commentId", (String) null);
                String string5 = bundle3.getString(GraphPathEdgeBundleWrapper.BUNDLE_KEY_COMMENT, (String) null);
                int i2 = bundle3.getInt("emotionType", -1);
                int i7 = Notification.FEEDBACK_TYPE_COMMENT;
                if (bundle3.containsKey("emotionType")) {
                    i7 = Notification.FEEDBACK_TYPE_EMOTION;
                }
                long j2 = bundle3.getLong("timestamp", 0);
                String string6 = bundle3.getString("profileImageContentUri", (String) null);
                if (!TextUtils.isEmpty(string6)) {
                    uri = Uri.parse(string6);
                } else {
                    uri = null;
                }
                arrayList.add(new Notification(bundleToContentId, string, i7, new UserProfile(string2, string3, uri), string4, string5, i2, j2));
            }
        }
        debugLog("bundleToNotificationList size : " + arrayList.size());
        return arrayList;
    }

    /* access modifiers changed from: private */
    public UserProfile bundleToProfileImage(Bundle bundle) {
        Uri uri = null;
        if (bundle == null) {
            debugLog("bundle is null : bundleToProfileImage");
            return null;
        }
        String string = bundle.getString("guid");
        String string2 = bundle.getString("profileImageContentUri", (String) null);
        if (!TextUtils.isEmpty(string2)) {
            uri = Uri.parse(string2);
        }
        return new UserProfile(string, uri);
    }

    /* access modifiers changed from: private */
    public List<UserProfile> bundleToProfileImageList(Bundle bundle) {
        if (bundle == null) {
            debugLog("bundle is null : bundleToProfileImageList");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList<Bundle> parcelableArrayList = bundle.getParcelableArrayList("images");
        if (parcelableArrayList != null) {
            for (Bundle bundleToProfileImage : parcelableArrayList) {
                arrayList.add(bundleToProfileImage(bundleToProfileImage));
            }
        }
        debugLog("bundleToImageList size : " + arrayList.size());
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

    private <T> FeedbackResult<T> getErrorFeedbackResult(int i2) {
        if (!isSupportedSemsAgentVersionMoreThan(i2)) {
            return new FeedbackResult<>(new CommonResultStatus(-7), null);
        }
        return null;
    }

    private boolean isResultSuccess(CommonResultStatus commonResultStatus) {
        if (commonResultStatus == null) {
            return true;
        }
        return false;
    }

    public String[] getEssentialServiceNames() {
        return new String[]{"SocialService"};
    }

    public FeedbackResult<List<Notification<?>>> getNotificationList(int i2, boolean z) {
        debugLog("getNotificationList");
        FeedbackResult<List<Notification<?>>> errorFeedbackResult = getErrorFeedbackResult(CommonConstants.SupportedApiMinVersion.VERSION_10_0);
        if (errorFeedbackResult != null) {
            return errorFeedbackResult;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("contentIdType", i2);
            bundle.putBoolean("requestClear", z);
            Bundle notification = getSocialService().getNotification(bundle);
            CommonResultStatus bundleToResult = bundleToResult(notification);
            if (isResultSuccess(bundleToResult)) {
                debugLog("getNotificationList success ");
                return new FeedbackResult<>(new CommonResultStatus(1), bundleToNotificationList(notification));
            }
            debugLog("getNotificationList fail");
            return new FeedbackResult<>(bundleToResult, null);
        } catch (NotConnectedException e) {
            secureLog(e);
            return new FeedbackResult<>(new CommonResultStatus(-8), null);
        } catch (Exception e7) {
            secureLog(e7);
            return new FeedbackResult<>(new CommonResultStatus(-1), null);
        }
    }

    public <T extends ContentId> int requestCommentCreation(T t, String str, final FeedbackResultCallback<String> feedbackResultCallback) {
        debugLog("requestCommentCreation : contentId =[" + t + "]");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putBundle(EngramQueryOptionBundleWrapper.BUNDLE_KEY_CONTENT_ID, t.toBundle());
            bundle.putString(GraphPathEdgeBundleWrapper.BUNDLE_KEY_COMMENT, str);
            getSocialService().requestCommentCreation(bundle, new IFeedbackBundleResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    FeedbackApi feedbackApi = FeedbackApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestCommentCreation onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    feedbackApi.debugLog(p6.toString());
                    if (feedbackResultCallback != null) {
                        feedbackResultCallback.onResult(new FeedbackResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), null));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    FeedbackApi.this.debugLog("requestCommentCreation onSuccess ");
                    if (feedbackResultCallback != null) {
                        feedbackResultCallback.onResult(new FeedbackResult(new CommonResultStatus(1), bundle.getString("commentId")));
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

    public <T extends ContentId> int requestCommentDeletion(T t, String str, final FeedbackResultCallback<Boolean> feedbackResultCallback) {
        debugLog("requestCommentDeletion : contentId=[" + t + "] commentId=[" + str + "] ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putBundle(EngramQueryOptionBundleWrapper.BUNDLE_KEY_CONTENT_ID, t.toBundle());
            bundle.putString("commentId", str);
            getSocialService().requestCommentDeletion(bundle, new IFeedbackBundleResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    FeedbackApi feedbackApi = FeedbackApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestCommentDeletion onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    feedbackApi.debugLog(p6.toString());
                    if (feedbackResultCallback != null) {
                        feedbackResultCallback.onResult(new FeedbackResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), Boolean.FALSE));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    FeedbackApi.this.debugLog("requestCommentDeletion onSuccess ");
                    FeedbackResultCallback feedbackResultCallback = feedbackResultCallback;
                    if (feedbackResultCallback != null) {
                        feedbackResultCallback.onResult(new FeedbackResult(new CommonResultStatus(1), Boolean.TRUE));
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

    public <T extends ContentId> int requestCommentList(T t, int i2, FeedbackDownloadResultCallback<CommentList> feedbackDownloadResultCallback) {
        debugLog("requestCommentList : contentId=[" + t + "] ");
        return requestCommentList(t, i2, (String) null, feedbackDownloadResultCallback);
    }

    public <T extends ContentId> int requestEmotionCancellation(T t, int i2, FeedbackResultCallback<Emotion> feedbackResultCallback) {
        debugLog("requestEmotionCancellation : contentId=[" + t + "] previousEmotionType=[" + i2 + "] ");
        return requestEmotionCancellation(t, (String) null, i2, feedbackResultCallback);
    }

    public <T extends ContentId> int requestEmotionMemberList(T t, int i2, FeedbackDownloadResultCallback<EmotionMemberList<T>> feedbackDownloadResultCallback) {
        debugLog("requestEmotionMemberList : contentId=[" + t + "] ");
        return requestEmotionMemberList(t, (String) null, i2, (String) null, feedbackDownloadResultCallback);
    }

    public <T extends ContentId> int requestEmotionUpdate(T t, int i2, FeedbackResultCallback<Emotion> feedbackResultCallback) {
        debugLog("requestEmotionUpdate : contentId=[" + t + "] emotionType=[" + i2 + "] ");
        return requestEmotionUpdate(t, (String) null, i2, feedbackResultCallback);
    }

    public <T extends ContentId> int requestFeedback(T t, final FeedbackDownloadResultCallback<Feedback<T>> feedbackDownloadResultCallback) {
        debugLog("requestFeedback");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putBundle(EngramQueryOptionBundleWrapper.BUNDLE_KEY_CONTENT_ID, t.toBundle());
            getSocialService().requestFeedback(bundle, new IFeedbackBundlePartialResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    FeedbackApi feedbackApi = FeedbackApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestFeedback onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    feedbackApi.debugLog(p6.toString());
                    if (feedbackDownloadResultCallback != null) {
                        feedbackDownloadResultCallback.onResult(new FeedbackResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), null));
                    }
                }

                public void onPartialResult(Bundle bundle) {
                    FeedbackApi.this.debugLog("requestFeedback onPartialResult ");
                    FeedbackDownloadResultCallback feedbackDownloadResultCallback = feedbackDownloadResultCallback;
                    if (feedbackDownloadResultCallback != null) {
                        feedbackDownloadResultCallback.onPartialResult(FeedbackApi.this.bundleToFeedback(bundle));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    FeedbackApi.this.debugLog("requestFeedback onSuccess ");
                    FeedbackDownloadResultCallback feedbackDownloadResultCallback = feedbackDownloadResultCallback;
                    if (feedbackDownloadResultCallback != null) {
                        feedbackDownloadResultCallback.onResult(new FeedbackResult(new CommonResultStatus(1), FeedbackApi.this.bundleToFeedback(bundle)));
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

    public <T extends ContentId> int requestFeedbackForContentIdList(List<T> list, final FeedbackDownloadResultCallback<List<Feedback<T>>> feedbackDownloadResultCallback) {
        debugLog("requestFeedbackForContentIdList");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (T bundle : list) {
                arrayList.add(bundle.toBundle());
            }
            Bundle bundle2 = new Bundle();
            bundle2.putParcelableArrayList("contentIds", arrayList);
            getSocialService().requestFeedback(bundle2, new IFeedbackBundlePartialResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    FeedbackApi feedbackApi = FeedbackApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestFeedbackForContentIdList onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    feedbackApi.debugLog(p6.toString());
                    if (feedbackDownloadResultCallback != null) {
                        feedbackDownloadResultCallback.onResult(new FeedbackResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), null));
                    }
                }

                public void onPartialResult(Bundle bundle) {
                    FeedbackApi.this.debugLog("requestFeedbackForContentIdList onPartialResult ");
                    FeedbackDownloadResultCallback feedbackDownloadResultCallback = feedbackDownloadResultCallback;
                    if (feedbackDownloadResultCallback != null) {
                        feedbackDownloadResultCallback.onPartialResult(FeedbackApi.this.bundleToFeedbackList(bundle, new ArrayList()));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    FeedbackApi.this.debugLog("requestFeedbackForContentIdList onSuccess ");
                    FeedbackDownloadResultCallback feedbackDownloadResultCallback = feedbackDownloadResultCallback;
                    if (feedbackDownloadResultCallback != null) {
                        feedbackDownloadResultCallback.onResult(new FeedbackResult(new CommonResultStatus(1), FeedbackApi.this.bundleToFeedbackList(bundle, new ArrayList())));
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

    public <T extends ContentId> int requestMyCommentUpdate(T t, String str, String str2, final FeedbackResultCallback<Boolean> feedbackResultCallback) {
        debugLog("requestMyCommentUpdate : contentId=[" + t + "]");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putBundle(EngramQueryOptionBundleWrapper.BUNDLE_KEY_CONTENT_ID, t.toBundle());
            bundle.putString("commentId", str);
            bundle.putString(GraphPathEdgeBundleWrapper.BUNDLE_KEY_COMMENT, str2);
            getSocialService().requestCommentUpdate(bundle, new IFeedbackBundleResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    FeedbackApi feedbackApi = FeedbackApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestMyCommentUpdate onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    feedbackApi.debugLog(p6.toString());
                    if (feedbackResultCallback != null) {
                        feedbackResultCallback.onResult(new FeedbackResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), Boolean.FALSE));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    FeedbackApi.this.debugLog("requestMyCommentUpdate onSuccess ");
                    FeedbackResultCallback feedbackResultCallback = feedbackResultCallback;
                    if (feedbackResultCallback != null) {
                        feedbackResultCallback.onResult(new FeedbackResult(new CommonResultStatus(1), Boolean.TRUE));
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

    public int requestProfileImageList(List<String> list, final ProfileImageListResultCallback<UserProfile> profileImageListResultCallback) {
        debugLog("requestProfileImageList");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (String next : list) {
                if (!TextUtils.isEmpty(next)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("guid", next);
                    arrayList.add(bundle);
                }
            }
            Bundle bundle2 = new Bundle();
            bundle2.putParcelableArrayList("guids", arrayList);
            getSocialService().requestProfileImageList(bundle2, new IBundleProgressResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    FeedbackApi feedbackApi = FeedbackApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestProfileImageList onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    feedbackApi.debugLog(p6.toString());
                    if (profileImageListResultCallback != null) {
                        profileImageListResultCallback.onResult(new FeedbackResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), null));
                    }
                }

                public void onProgress(Bundle bundle) {
                    FeedbackApi.this.debugLog("requestProfileImageList onProgress ");
                    ProfileImageListResultCallback profileImageListResultCallback = profileImageListResultCallback;
                    if (profileImageListResultCallback != null) {
                        profileImageListResultCallback.onProgress(FeedbackApi.this.bundleToProfileImage(bundle));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    FeedbackApi.this.debugLog("requestProfileImageList onSuccess ");
                    ProfileImageListResultCallback profileImageListResultCallback = profileImageListResultCallback;
                    if (profileImageListResultCallback != null) {
                        profileImageListResultCallback.onResult(new FeedbackResult(new CommonResultStatus(1), FeedbackApi.this.bundleToProfileImageList(bundle)));
                    }
                }
            });
            return 1;
        } catch (NotConnectedException e) {
            debugLog("requestProfileImageList fail");
            secureLog(e);
            return -8;
        } catch (Exception e7) {
            debugLog("requestProfileImageList fail");
            secureLog(e7);
            return -1;
        }
    }

    public <T extends ContentId> int requestCommentList(T t, int i2, String str, final FeedbackDownloadResultCallback<CommentList> feedbackDownloadResultCallback) {
        debugLog("requestCommentList : contentId=[" + t + "] limit=[" + i2 + "] nextCommentId=[" + str + "] ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putBundle(EngramQueryOptionBundleWrapper.BUNDLE_KEY_CONTENT_ID, t.toBundle());
            bundle.putInt("limit", i2);
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("nextCommentId", str);
            }
            getSocialService().requestCommentList(bundle, new IFeedbackBundlePartialResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    FeedbackApi feedbackApi = FeedbackApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestCommentList onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    feedbackApi.debugLog(p6.toString());
                    if (feedbackDownloadResultCallback != null) {
                        feedbackDownloadResultCallback.onResult(new FeedbackResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), null));
                    }
                }

                public void onPartialResult(Bundle bundle) {
                    FeedbackApi.this.debugLog("requestCommentList onPartialResult ");
                    FeedbackDownloadResultCallback feedbackDownloadResultCallback = feedbackDownloadResultCallback;
                    if (feedbackDownloadResultCallback != null) {
                        feedbackDownloadResultCallback.onPartialResult(FeedbackApi.this.bundleToCommentList(bundle));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    FeedbackApi.this.debugLog("requestCommentList onSuccess ");
                    FeedbackDownloadResultCallback feedbackDownloadResultCallback = feedbackDownloadResultCallback;
                    if (feedbackDownloadResultCallback != null) {
                        feedbackDownloadResultCallback.onResult(new FeedbackResult(new CommonResultStatus(1), FeedbackApi.this.bundleToCommentList(bundle)));
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

    public <T extends ContentId> int requestEmotionCancellation(T t, String str, int i2, final FeedbackResultCallback<Emotion> feedbackResultCallback) {
        debugLog("requestEmotionCancellation : contentId=[" + t + "] commentId=[" + str + "] previousEmotionType=[" + i2 + "] ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putBundle(EngramQueryOptionBundleWrapper.BUNDLE_KEY_CONTENT_ID, t.toBundle());
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("commentId", str);
            }
            bundle.putBoolean("cancelAction", true);
            bundle.putInt("emotionType", i2);
            getSocialService().requestEmotionUpdate(bundle, new IFeedbackBundleResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    FeedbackApi feedbackApi = FeedbackApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestEmotionCancellation onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    feedbackApi.debugLog(p6.toString());
                    if (feedbackResultCallback != null) {
                        feedbackResultCallback.onResult(new FeedbackResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), null));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    FeedbackApi.this.debugLog("requestEmotionCancellation onSuccess ");
                    if (feedbackResultCallback != null) {
                        bundle.getInt("count", -1);
                        feedbackResultCallback.onResult(new FeedbackResult(new CommonResultStatus(1), FeedbackApi.this.bundleToEmotion(bundle)));
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

    public <T extends ContentId> int requestEmotionMemberList(T t, int i2, String str, FeedbackDownloadResultCallback<EmotionMemberList<T>> feedbackDownloadResultCallback) {
        debugLog("requestEmotionMemberList : contentId=[" + t + "] nextMemberGuid=[" + str + "] ");
        return requestEmotionMemberList(t, (String) null, i2, str, feedbackDownloadResultCallback);
    }

    public <T extends ContentId> int requestEmotionUpdate(T t, String str, int i2, final FeedbackResultCallback<Emotion> feedbackResultCallback) {
        debugLog("requestEmotionUpdate : contentId=[" + t + "] commentId=[" + str + "] ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putBundle(EngramQueryOptionBundleWrapper.BUNDLE_KEY_CONTENT_ID, t.toBundle());
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("commentId", str);
            }
            bundle.putBoolean("cancelAction", false);
            bundle.putInt("emotionType", i2);
            getSocialService().requestEmotionUpdate(bundle, new IFeedbackBundleResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    FeedbackApi feedbackApi = FeedbackApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestEmotionUpdate onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    feedbackApi.debugLog(p6.toString());
                    if (feedbackResultCallback != null) {
                        feedbackResultCallback.onResult(new FeedbackResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), null));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    FeedbackApi.this.debugLog("requestEmotionUpdate onSuccess ");
                    FeedbackResultCallback feedbackResultCallback = feedbackResultCallback;
                    if (feedbackResultCallback != null) {
                        feedbackResultCallback.onResult(new FeedbackResult(new CommonResultStatus(1), FeedbackApi.this.bundleToEmotion(bundle)));
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

    public <T extends ContentId> int requestEmotionMemberList(T t, String str, int i2, FeedbackDownloadResultCallback<EmotionMemberList<T>> feedbackDownloadResultCallback) {
        debugLog("requestEmotionMemberList : contentId=[" + t + "] commentId=[" + str + "] ");
        return requestEmotionMemberList(t, str, i2, (String) null, feedbackDownloadResultCallback);
    }

    public <T extends ContentId> int requestEmotionMemberList(final T t, final String str, int i2, String str2, final FeedbackDownloadResultCallback<EmotionMemberList<T>> feedbackDownloadResultCallback) {
        debugLog("requestEmotionMemberList : contentId=[" + t + "] commentId=[" + str + "] ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putBundle(EngramQueryOptionBundleWrapper.BUNDLE_KEY_CONTENT_ID, t.toBundle());
            bundle.putInt("limit", i2);
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("commentId", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                bundle.putString("nextMemberGuid", str2);
            }
            getSocialService().requestEmotionMemberList(bundle, new IFeedbackBundlePartialResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    FeedbackApi feedbackApi = FeedbackApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestEmotionMemberList onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    feedbackApi.debugLog(p6.toString());
                    if (feedbackDownloadResultCallback != null) {
                        feedbackDownloadResultCallback.onResult(new FeedbackResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), null));
                    }
                }

                public void onPartialResult(Bundle bundle) {
                    FeedbackApi.this.debugLog("requestEmotionMemberList onPartialResult ");
                    FeedbackDownloadResultCallback feedbackDownloadResultCallback = feedbackDownloadResultCallback;
                    if (feedbackDownloadResultCallback != null) {
                        feedbackDownloadResultCallback.onPartialResult(FeedbackApi.this.bundleToActivityEmotionMemberList(t, str, bundle));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    FeedbackApi.this.debugLog("requestEmotionMemberList onSuccess ");
                    if (feedbackDownloadResultCallback != null) {
                        feedbackDownloadResultCallback.onResult(new FeedbackResult(new CommonResultStatus(1), FeedbackApi.this.bundleToActivityEmotionMemberList(t, str, bundle)));
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
}
