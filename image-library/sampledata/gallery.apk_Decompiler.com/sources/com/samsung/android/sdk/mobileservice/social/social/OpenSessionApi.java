package com.samsung.android.sdk.mobileservice.social.social;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.sdk.mobileservice.SeMobileService;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import com.samsung.android.sdk.mobileservice.social.social.result.OpenSessionResult;
import com.samsung.android.sdk.mobileservice.social.social.result.PhoneNumberInfo;
import com.samsung.android.sdk.mobileservice.util.SdkLog;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OpenSessionApi {
    private static final int ACTION_VALUE = 0;
    private static final int DEFAULT_ITEM_LIMIT_DELETE_FROM_TRASH_BIN_MAX = 500;
    private static final int DEFAULT_ITEM_LIMIT_DELETE_MAX = 100;
    private static final int DEFAULT_ITEM_LIMIT_DOWNLOAD_MAX = 500;
    private static final int DEFAULT_ITEM_LIMIT_MOVE_TO_TRASH_BIN_MAX = 500;
    private static final int DEFAULT_ITEM_LIMIT_RESTORE_FROM_TRASH_BIN_MAX = 500;
    private static final int DEFAULT_ITEM_LIMIT_UPLOAD_MAX = 500;
    private static final int DEFAULT_ITEM_LIMIT_UPLOAD_MAX_UNDER_13 = 100;
    public static final int DENIED_CONTACT_READ_PERMISSION = 2;
    public static final String EDIT_AUTHORITY_READ_ONLY = "R/O";
    public static final String EDIT_AUTHORITY_WRITABLE = "R/W";
    public static final String EXTRA_FEATURE_ID = "featureId";
    private static final int EXTRA_KEY = 1;
    public static final String EXTRA_KEY_EDIT_AUTHORITY = "editAuthority";
    public static final String EXTRA_KEY_JSON_RESULT = "jsonResult";
    public static final String EXTRA_LINK_URL = "linkUrl";
    public static final String EXTRA_TITLE = "title";
    private static final int EXTRA_VALUE = 2;
    public static final int FOUND_NUMBER = 1;
    public static final int INVALID_PARAMETER = 4;
    private static final String INVITATION_LINK_URL_KEY = "invitation_link_url";
    public static final String ITEM_LIMIT_DELETE = "delete";
    private static final int ITEM_LIMIT_DELETE_FROM_TRASH_BIN_INDEX = 5;
    private static final int ITEM_LIMIT_DELETE_INDEX = 2;
    public static final String ITEM_LIMIT_DOWNLOAD = "download";
    private static final int ITEM_LIMIT_DOWNLOAD_INDEX = 1;
    private static final int ITEM_LIMIT_MOVE_TO_TRASH_BIN_INDEX = 3;
    private static final int ITEM_LIMIT_RESTORE_FROM_TRASH_BIN_INDEX = 4;
    public static final String ITEM_LIMIT_UPLOAD = "upload";
    private static final int ITEM_LIMIT_UPLOAD_INDEX = 0;
    private static final String METHOD_GET_CONTACT_INFO = "getContactInfo";
    private static final String METHOD_GET_MY_PHONE_NUMBER_INFO = "getMyPhoneNumberInfo";
    private static final String METHOD_GET_SHARE_VIA_INVITATION_LINK_INTENT = "getShareViaInvitationLinkIntent";
    private static final String MOBILE_SERVICE_PROVIDER_URI = "com.samsung.android.mobileservice.social.common.provider.OpenSessionProvider";
    public static final int NOT_FOUND_NUMBER = 0;
    public static final int NOT_SUPPORTED_API = 3;
    private static final String PATH_ACCOUNT_BASED_CONTACT_POLICY = "accountBasedContactPolicy";
    private static final String PATH_GDPR = "gdpr";
    private static final String PATH_GROUP_INVITATION_LINK = "groupInvitationLink";
    private static final String PATH_HAS_NUMBER_IN_CONTACT = "hasNumberInContact";
    private static final String PATH_INTRODUCTION = "introduction";
    private static final String PATH_PERSONAL_INFORMATION_COLLECTION_AGREEMENT = "personalInformationCollectionAgreement";
    private static final String PATH_SERVICE_NUMBER_REGISTRATION = "serviceNumberRegistration";
    private static final String PATH_SHARE_ITEM_LIMIT = "shareItemLimit";
    private static final String PATH_TERMS_AND_CONDITION = "termsAndCondition";
    public static final int RESULT_CODE_INVITE_FROM_SHARE_LINK = 55;

    /* renamed from: com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sdk$mobileservice$social$social$OpenSessionApi$LegalNoticeIntentType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi$LegalNoticeIntentType[] r0 = com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi.LegalNoticeIntentType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sdk$mobileservice$social$social$OpenSessionApi$LegalNoticeIntentType = r0
                com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi$LegalNoticeIntentType r1 = com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi.LegalNoticeIntentType.ACCOUNT_BASED_CONTACT_POLICY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$mobileservice$social$social$OpenSessionApi$LegalNoticeIntentType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi$LegalNoticeIntentType r1 = com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi.LegalNoticeIntentType.GDPR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$mobileservice$social$social$OpenSessionApi$LegalNoticeIntentType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi$LegalNoticeIntentType r1 = com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi.LegalNoticeIntentType.INTRODUCTION     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$mobileservice$social$social$OpenSessionApi$LegalNoticeIntentType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi$LegalNoticeIntentType r1 = com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi.LegalNoticeIntentType.PERSONAL_INFORMATION_COLLECTION_AGREEMENT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$mobileservice$social$social$OpenSessionApi$LegalNoticeIntentType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi$LegalNoticeIntentType r1 = com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi.LegalNoticeIntentType.TERMS_AND_CONDITION     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum LegalNoticeIntentType {
        ACCOUNT_BASED_CONTACT_POLICY,
        GDPR,
        INTRODUCTION,
        PERSONAL_INFORMATION_COLLECTION_AGREEMENT,
        TERMS_AND_CONDITION
    }

    private Bundle callGetContactInfo(Context context, String str) {
        try {
            return context.getContentResolver().call(new Uri.Builder().scheme("content").encodedAuthority(MOBILE_SERVICE_PROVIDER_URI).build(), METHOD_GET_CONTACT_INFO, str, (Bundle) null);
        } catch (Exception e) {
            secureLog(e);
            return null;
        }
    }

    private void debugLog(String str) {
        SdkLog.d("OpenSessionApi", str);
    }

    private Bundle getGroupDetailDataFromAgent(Context context, GroupDetailRequest groupDetailRequest) {
        return context.getContentResolver().call(new Uri.Builder().encodedAuthority(MOBILE_SERVICE_PROVIDER_URI).build(), "getGroupDetailIntent", (String) null, groupDetailRequest.toBundle());
    }

    private Intent getIntent(Context context, String str, String... strArr) {
        Exception exc;
        Intent intent;
        Throwable th;
        Throwable th2;
        ContentResolver contentResolver = context.getContentResolver();
        Uri.Builder appendPath = new Uri.Builder().scheme("content").encodedAuthority(MOBILE_SERVICE_PROVIDER_URI).appendPath(str);
        for (String appendPath2 : strArr) {
            appendPath.appendPath(appendPath2);
        }
        Uri build = appendPath.build();
        Intent intent2 = null;
        try {
            Cursor query = contentResolver.query(build, (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        query.moveToFirst();
                        intent = new Intent(query.getString(0));
                        while (query.moveToNext()) {
                            try {
                                String string = query.getString(1);
                                String string2 = query.getString(2);
                                debugLog("intent key: " + string + ", value: " + string2);
                                intent.putExtra(string, string2);
                            } catch (Throwable th3) {
                                th = th3;
                                try {
                                    throw th;
                                } catch (Exception e) {
                                    exc = e;
                                    secureLog(exc);
                                    return intent;
                                } catch (Throwable th4) {
                                    th.addSuppressed(th4);
                                }
                            }
                        }
                        intent2 = intent;
                    }
                } catch (Throwable th5) {
                    intent = null;
                    th = th5;
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return intent2;
            throw th2;
        } catch (Exception e7) {
            intent = intent2;
            exc = e7;
            secureLog(exc);
            return intent;
        }
    }

    private Bundle getResult(Context context, Uri uri, String str, String str2, Bundle bundle) {
        try {
            return context.getContentResolver().call(uri, str, str2, bundle);
        } catch (Exception e) {
            secureLog(e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:69:0x00c9 A[SYNTHETIC, Splitter:B:69:0x00c9] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.samsung.android.sdk.mobileservice.social.social.ShareLimit getShareLimitFromAgent(android.content.Context r18) {
        /*
            r17 = this;
            android.content.ContentResolver r0 = r18.getContentResolver()
            android.net.Uri$Builder r1 = new android.net.Uri$Builder
            r1.<init>()
            java.lang.String r2 = "content"
            android.net.Uri$Builder r1 = r1.scheme(r2)
            java.lang.String r2 = "com.samsung.android.mobileservice.social.common.provider.OpenSessionProvider"
            android.net.Uri$Builder r1 = r1.encodedAuthority(r2)
            java.lang.String r2 = "shareItemLimit"
            android.net.Uri$Builder r1 = r1.appendEncodedPath(r2)
            android.net.Uri r1 = r1.build()
            r4 = 0
            r5 = 0
            r6 = 500(0x1f4, float:7.0E-43)
            r7 = 100
            r2 = 0
            r3 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x00dc }
            if (r1 == 0) goto L_0x00c3
            int r0 = r1.getCount()     // Catch:{ all -> 0x0044 }
            if (r0 <= 0) goto L_0x00c3
            r1.moveToFirst()     // Catch:{ all -> 0x0044 }
            r0 = 0
            boolean r2 = r1.isNull(r0)     // Catch:{ all -> 0x0044 }
            if (r2 != 0) goto L_0x004d
            int r0 = r1.getInt(r0)     // Catch:{ all -> 0x0044 }
            r2 = r0
            goto L_0x004e
        L_0x0044:
            r0 = move-exception
            r2 = r0
            r3 = r6
            r4 = r3
            r5 = r4
            r8 = r7
        L_0x004a:
            r7 = r5
            goto L_0x00b1
        L_0x004d:
            r2 = r6
        L_0x004e:
            r0 = 1
            boolean r3 = r1.isNull(r0)     // Catch:{ all -> 0x005b }
            if (r3 != 0) goto L_0x0062
            int r0 = r1.getInt(r0)     // Catch:{ all -> 0x005b }
            r3 = r0
            goto L_0x0063
        L_0x005b:
            r0 = move-exception
            r3 = r2
            r4 = r6
            r5 = r4
            r8 = r7
            r2 = r0
            goto L_0x004a
        L_0x0062:
            r3 = r6
        L_0x0063:
            r0 = 2
            boolean r4 = r1.isNull(r0)     // Catch:{ all -> 0x006f }
            if (r4 != 0) goto L_0x0077
            int r7 = r1.getInt(r0)     // Catch:{ all -> 0x006f }
            goto L_0x0077
        L_0x006f:
            r0 = move-exception
            r4 = r3
            r5 = r6
            r8 = r7
            r3 = r2
            r7 = r5
        L_0x0075:
            r2 = r0
            goto L_0x00b1
        L_0x0077:
            r0 = 3
            boolean r4 = r1.isNull(r0)     // Catch:{ all -> 0x006f }
            if (r4 != 0) goto L_0x0084
            int r0 = r1.getInt(r0)     // Catch:{ all -> 0x006f }
            r4 = r0
            goto L_0x0085
        L_0x0084:
            r4 = r6
        L_0x0085:
            r0 = 4
            boolean r5 = r1.isNull(r0)     // Catch:{ all -> 0x0092 }
            if (r5 != 0) goto L_0x0099
            int r0 = r1.getInt(r0)     // Catch:{ all -> 0x0092 }
            r5 = r0
            goto L_0x009a
        L_0x0092:
            r0 = move-exception
            r5 = r4
            r8 = r7
            r4 = r3
            r7 = r6
        L_0x0097:
            r3 = r2
            goto L_0x0075
        L_0x0099:
            r5 = r6
        L_0x009a:
            r0 = 5
            boolean r8 = r1.isNull(r0)     // Catch:{ all -> 0x00ab }
            if (r8 != 0) goto L_0x00a5
            int r6 = r1.getInt(r0)     // Catch:{ all -> 0x00ab }
        L_0x00a5:
            r16 = r6
            r6 = r2
            r2 = r16
            goto L_0x00c7
        L_0x00ab:
            r0 = move-exception
            r8 = r7
            r7 = r5
            r5 = r4
            r4 = r3
            goto L_0x0097
        L_0x00b1:
            throw r2     // Catch:{ all -> 0x00b2 }
        L_0x00b2:
            r0 = move-exception
            r9 = r0
            r1.close()     // Catch:{ all -> 0x00b8 }
            goto L_0x00bc
        L_0x00b8:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch:{ Exception -> 0x00bd }
        L_0x00bc:
            throw r9     // Catch:{ Exception -> 0x00bd }
        L_0x00bd:
            r0 = move-exception
            r1 = r17
            r2 = r6
            r6 = r3
            goto L_0x00e4
        L_0x00c3:
            r2 = r6
            r3 = r2
            r4 = r3
            r5 = r4
        L_0x00c7:
            if (r1 == 0) goto L_0x00d5
            r1.close()     // Catch:{ Exception -> 0x00cd }
            goto L_0x00d5
        L_0x00cd:
            r0 = move-exception
            r1 = r17
            r8 = r7
            r7 = r5
            r5 = r4
            r4 = r3
            goto L_0x00e4
        L_0x00d5:
            r11 = r3
            r13 = r4
            r14 = r5
            r12 = r7
        L_0x00d9:
            r15 = r2
            r10 = r6
            goto L_0x00ec
        L_0x00dc:
            r0 = move-exception
            r1 = r17
            r2 = r6
            r4 = r2
            r5 = r4
            r8 = r7
            r7 = r5
        L_0x00e4:
            r1.secureLog(r0)
            r11 = r4
            r13 = r5
            r14 = r7
            r12 = r8
            goto L_0x00d9
        L_0x00ec:
            com.samsung.android.sdk.mobileservice.social.social.ShareLimit r9 = new com.samsung.android.sdk.mobileservice.social.social.ShareLimit
            r9.<init>(r10, r11, r12, r13, r14, r15)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi.getShareLimitFromAgent(android.content.Context):com.samsung.android.sdk.mobileservice.social.social.ShareLimit");
    }

    private Bundle getSocialPickerDataFromAgent(Context context, SocialPickerRequest socialPickerRequest) {
        return context.getContentResolver().call(new Uri.Builder().encodedAuthority(MOBILE_SERVICE_PROVIDER_URI).build(), "getSocialPickerIntent", (String) null, socialPickerRequest.toBundle());
    }

    private boolean isSupportedSemsAgentVersion(Context context, int i2) {
        if (SeMobileService.getAgentVersion(context) >= i2) {
            return true;
        }
        return false;
    }

    private boolean isSupportedSemsAgentVersionBetween(Context context, int i2, int i7) {
        int agentVersion = SeMobileService.getAgentVersion(context);
        if (agentVersion < i2 || agentVersion >= i7) {
            return false;
        }
        return true;
    }

    private ShareLimit makeDefaultItemLimit() {
        return new ShareLimit(100, 500, 100, 500, 500, 500);
    }

    private void secureLog(Exception exc) {
        SdkLog.s(exc);
    }

    public ContactInfo getContactInfo(Context context, String str) {
        int i2;
        debugLog(METHOD_GET_CONTACT_INFO);
        long j2 = -1;
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            secureLog(new Exception(C0212a.l("Not supported getContactInfo api : ", str)));
            i2 = 4;
        } else {
            if (isSupportedSemsAgentVersion(context, CommonConstants.SupportedApiMinVersion.VERSION_13_1_10)) {
                Bundle callGetContactInfo = callGetContactInfo(context, str);
                if (callGetContactInfo != null) {
                    int i7 = 0;
                    if (callGetContactInfo.getInt("value", 0) == 1 || Integer.parseInt(callGetContactInfo.getString("value", "0")) == 1) {
                        i7 = 1;
                    }
                    j2 = callGetContactInfo.getLong("contact_id", -1);
                    str2 = callGetContactInfo.getString("phone_number", str2);
                    i2 = i7;
                }
            } else {
                secureLog(new Exception("Not supported version"));
            }
            i2 = 3;
        }
        String str3 = str2;
        long j3 = j2;
        if (i2 == 3) {
            debugLog("getContactInfo. not supported version. use hasNumberInContact instead");
            i2 = hasNumberInContact(context, str);
        }
        int i8 = i2;
        debugLog(C0086a.i(i8, "result : "));
        return new ContactInfo(i8, j3, str3, str);
    }

    public Intent getIntentForGroupDetail(Context context, GroupDetailRequest groupDetailRequest) {
        debugLog("getIntentForGroupDetail");
        Intent intent = new Intent("com.samsung.android.mobileservice.action.ACTION_EXTERNAL_GROUP_DETAIL");
        if (isSupportedSemsAgentVersion(context, CommonConstants.SupportedApiMinVersion.VERSION_13_0_5_5)) {
            Bundle groupDetailDataFromAgent = getGroupDetailDataFromAgent(context, groupDetailRequest);
            if (groupDetailDataFromAgent == null) {
                debugLog("com.samsung.android.mobileservice.social.common.provider.OpenSessionProvider getGroupDetailIntent method call is null");
                intent.putExtras(groupDetailRequest.toBundle());
                return intent;
            }
            intent.putExtras(groupDetailDataFromAgent);
            return intent;
        }
        intent.putExtras(groupDetailRequest.toBundle());
        return intent;
    }

    public Intent getIntentForGroupInvitationLink(Context context, String str, boolean z) {
        String str2;
        debugLog(C0212a.l("getIntentForGroupInvitationLink:", str));
        if (isSupportedSemsAgentVersionBetween(context, CommonConstants.SupportedApiMinVersion.VERSION_10_8_9, CommonConstants.SupportedApiMinVersion.VERSION_11_0) || isSupportedSemsAgentVersion(context, CommonConstants.SupportedApiMinVersion.VERSION_13_1_10)) {
            if (z) {
                str2 = "1";
            } else {
                str2 = "0";
            }
            Intent intent = getIntent(context, PATH_GROUP_INVITATION_LINK, str, str2);
            intent.putExtra("invitation_link_url", str);
            return intent;
        }
        secureLog(new Exception("Not supported version"));
        return null;
    }

    public Intent getIntentForLegalNotice(Context context, LegalNoticeIntentType legalNoticeIntentType) {
        debugLog("getIntentForLegalNotice type = " + legalNoticeIntentType);
        String str = null;
        if (!isSupportedSemsAgentVersion(context, CommonConstants.SupportedApiMinVersion.VERSION_12_1)) {
            secureLog(new Exception("Not supported version"));
            return null;
        }
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$sdk$mobileservice$social$social$OpenSessionApi$LegalNoticeIntentType[legalNoticeIntentType.ordinal()];
        if (i2 == 1) {
            str = PATH_ACCOUNT_BASED_CONTACT_POLICY;
        } else if (i2 == 2) {
            str = PATH_GDPR;
        } else if (i2 == 3) {
            str = PATH_INTRODUCTION;
        } else if (i2 == 4) {
            str = PATH_PERSONAL_INFORMATION_COLLECTION_AGREEMENT;
        } else if (i2 == 5) {
            str = PATH_TERMS_AND_CONDITION;
        }
        return getIntent(context, str, new String[0]);
    }

    public Intent getIntentForServiceNumberRegistration(Context context) {
        debugLog("getIntentForServiceNumberRegistration ");
        if (isSupportedSemsAgentVersion(context, CommonConstants.SupportedApiMinVersion.VERSION_12_1)) {
            return getIntent(context, "serviceNumberRegistration", new String[0]);
        }
        secureLog(new Exception("Not supported version"));
        return null;
    }

    public Intent getIntentForShareViaInvitationLink(Context context, int i2, String str, String str2) {
        debugLog(C0212a.l("getIntentForShareViaInvitationLink:", str2));
        if (!isSupportedSemsAgentVersionBetween(context, CommonConstants.SupportedApiMinVersion.VERSION_10_8_9, CommonConstants.SupportedApiMinVersion.VERSION_11_0) && !isSupportedSemsAgentVersion(context, CommonConstants.SupportedApiMinVersion.VERSION_13_1_10)) {
            secureLog(new Exception("Not supported version"));
            return null;
        } else if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            secureLog(new Exception("groupName and linkUrl should be not empty"));
            return null;
        } else {
            Uri build = new Uri.Builder().scheme("content").encodedAuthority(MOBILE_SERVICE_PROVIDER_URI).build();
            Bundle bundle = new Bundle();
            bundle.putInt("featureId", i2);
            bundle.putString("title", str);
            bundle.putString(EXTRA_LINK_URL, str2);
            Intent intent = getIntent(context, build, METHOD_GET_SHARE_VIA_INVITATION_LINK_INTENT, (String) null, bundle);
            if (intent == null) {
                secureLog(new Exception("getIntentForShareViaInvitationLink result is null"));
            }
            return intent;
        }
    }

    public Intent getIntentForSocialPicker(Context context, SocialPickerRequest socialPickerRequest) {
        debugLog("getIntentForSocialPicker");
        Intent intent = new Intent("com.samsung.android.mobileservice.social.intent.action.SOCIAL_CONTACT_PICKER");
        if (isSupportedSemsAgentVersion(context, CommonConstants.SupportedApiMinVersion.VERSION_13_0_5_5)) {
            Bundle socialPickerDataFromAgent = getSocialPickerDataFromAgent(context, socialPickerRequest);
            if (socialPickerDataFromAgent == null) {
                debugLog("com.samsung.android.mobileservice.social.common.provider.OpenSessionProvider getSocialPickerIntent method call is null");
                intent.putExtras(socialPickerRequest.toBundle());
                return intent;
            }
            intent.putExtras(socialPickerDataFromAgent);
            return intent;
        }
        intent.putExtras(socialPickerRequest.toBundle());
        return intent;
    }

    public OpenSessionResult<PhoneNumberInfo> getMyPhoneNumberInfo(Context context) {
        OpenSessionApi openSessionApi;
        int i2;
        debugLog(METHOD_GET_MY_PHONE_NUMBER_INFO);
        PhoneNumberInfo phoneNumberInfo = null;
        if (isSupportedSemsAgentVersion(context, CommonConstants.SupportedApiMinVersion.VERSION_13_5_40)) {
            openSessionApi = this;
            Bundle result = openSessionApi.getResult(context, new Uri.Builder().scheme("content").encodedAuthority(MOBILE_SERVICE_PROVIDER_URI).build(), METHOD_GET_MY_PHONE_NUMBER_INFO, (String) null, (Bundle) null);
            if (result != null) {
                i2 = 0;
                if (!(result.getInt("value", 0) == 1 || Integer.parseInt(result.getString("value", "0")) == 1)) {
                    i2 = 101;
                }
                if (i2 == 0) {
                    phoneNumberInfo = new PhoneNumberInfo(result.getString("hash"), result.getString("phone_number"), PhoneNumberInfo.mapToType(result.getString("type")));
                    openSessionApi.debugLog("hash: " + phoneNumberInfo.getHash() + ", type: " + phoneNumberInfo.getType());
                }
            } else {
                i2 = 105;
            }
        } else {
            openSessionApi = this;
            i2 = 103;
        }
        openSessionApi.debugLog(C0086a.i(i2, "result : "));
        return new OpenSessionResult<>(i2, phoneNumberInfo);
    }

    public ShareLimit getShareLimit(Context context) {
        if (isSupportedSemsAgentVersion(context, CommonConstants.SupportedApiMinVersion.VERSION_13_0_0_15)) {
            return getShareLimitFromAgent(context);
        }
        secureLog(new Exception("Not supported version"));
        return makeDefaultItemLimit();
    }

    @Deprecated
    public int hasNumberInContact(Context context, String str) {
        debugLog(PATH_HAS_NUMBER_IN_CONTACT);
        if (TextUtils.isEmpty(str)) {
            secureLog(new Exception(C0212a.l("Not supported hasNumberInContact api : ", str)));
            return 3;
        } else if (isSupportedSemsAgentVersion(context, CommonConstants.SupportedApiMinVersion.VERSION_13_0_4)) {
            int hasNumberInContact = hasNumberInContact(context, new Uri.Builder().scheme("content").encodedAuthority(MOBILE_SERVICE_PROVIDER_URI).appendEncodedPath(PATH_HAS_NUMBER_IN_CONTACT).appendEncodedPath(str).build());
            debugLog(C0086a.i(hasNumberInContact, "result : "));
            return hasNumberInContact;
        } else {
            secureLog(new Exception("Not supported version"));
            return 3;
        }
    }

    private Intent getIntent(Context context, Uri uri, String str, String str2, Bundle bundle) {
        Bundle result = getResult(context, uri, str, str2, bundle);
        if (result != null) {
            return (Intent) result.getParcelable("value");
        }
        return null;
    }

    private int hasNumberInContact(Context context, Uri uri) {
        Throwable th;
        try {
            Cursor query = context.getContentResolver().query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                try {
                    if (query.moveToNext()) {
                        int i2 = query.getInt(0);
                        query.close();
                        return i2;
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    query.close();
                    throw th3;
                }
            }
            if (query == null) {
                return 3;
            }
            query.close();
            return 3;
        } catch (Exception e) {
            secureLog(e);
            return 3;
        } catch (Throwable th4) {
            th.addSuppressed(th4);
        }
    }
}
