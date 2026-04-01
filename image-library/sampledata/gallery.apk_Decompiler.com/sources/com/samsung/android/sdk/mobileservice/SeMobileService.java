package com.samsung.android.sdk.mobileservice;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import com.samsung.android.sdk.mobileservice.common.CommonUtils;
import com.samsung.android.sdk.mobileservice.util.SdkLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeMobileService {
    private static final String APP_ID_DAILY_BOARD = "wbu28c1241";
    private static final String APP_ID_GALLERY = "22n6hzkam0";
    private static final String APP_ID_SES_CALENDAR = "ses_calendar";
    private static final String APP_ID_SOCIAL_APP = "504k7c7fnz";
    private static final int COMMON_COLUMN_INDEX_LATEST_VERSION = 0;
    private static final String MOBILE_SERVICE_PROVIDER_PATH_ACCOUNT_BASE_SERVICE = "accountbaseservice";
    private static final String MOBILE_SERVICE_PROVIDER_PATH_COMMON = "common";
    private static final String MOBILE_SERVICE_PROVIDER_PATH_SERVICE_FEATURE = "feature";
    private static final String MOBILE_SERVICE_PROVIDER_PATH_SERVICE_STATUS = "status";
    private static final String MOBILE_SERVICE_PROVIDER_PATH_SOCIALFEATURE = "socialfeature";
    private static final String MOBILE_SERVICE_PROVIDER_URI = "com.samsung.android.mobileservice.provider.MobileServiceCapabilityProvider";
    private static final String SAMSUNG_ACCOUNT_PROVIDER_URI = "com.samsung.android.samsungaccount.mobileservice.SamsungAccountCapabilityProvider";
    public static final String SOCIAL_FEATURE_ACCOUNT_BASED_SERVICE = "account_based_service";
    public static final String SOCIAL_FEATURE_ACTIVITY_FEEDBACK_SERVICE = "activity_feedback_service";
    public static final String SOCIAL_FEATURE_ACTIVITY_HASH_CONTENTS = "activity_hash_contents";
    public static final String SOCIAL_FEATURE_APP_UPDATE_AND_LAUNCH_FOR_BUDDY = "app_update_and_launch_for_buddy";
    public static final String SOCIAL_FEATURE_BUDDY_CERTIFICATE_SHARING = "buddy_certificate_sharing";
    public static final String SOCIAL_FEATURE_CAPABILITY_SHARING = "capability_sharing";
    public static final String SOCIAL_FEATURE_DYNAMIC_SHARING_SETTINGS = "dynamic_sharing_settings";
    public static final String SOCIAL_FEATURE_FAMILY_GROUP_SHARING = "family_group_sharing";
    public static final String SOCIAL_FEATURE_GLOBAL_GROUP_SHARING = "global_group_sharing";
    public static final String SOCIAL_FEATURE_INSTANT_SHARING = "instant_sharing";
    public static final String SOCIAL_FEATURE_INVITATION_LINK = "invitation_link";
    public static final String SOCIAL_FEATURE_LOCAL_GROUP_SHARING = "local_group_sharing";
    public static final String SOCIAL_FEATURE_NOTE_COEDIT = "note_coedit";
    public static final String SOCIAL_FEATURE_PROFILE_CARD = "profile_card";
    public static final String SOCIAL_FEATURE_SA_FAMILY_GROUP = "sa_family_group";
    public static final String SOCIAL_FEATURE_SA_FAMILY_GROUP_TRASHBIN = "sa_family_group_trashbin";
    public static final String SOCIAL_FEATURE_SHARE_DATE_TAKEN = "share_date_taken";
    public static final String SOCIAL_FEATURE_SHARE_QUOTA = "share_quota";
    public static final String SOCIAL_FEATURE_SHARE_WEBLINK = "share_weblink";
    public static final String SOCIAL_FEATURE_SOCIAL_ACTIVITY_SERVICE = "social_activity_service";
    private static final String TAG = "SeMobileService";

    public static int getAgentLatestVersionInGalaxyApps(Context context) {
        List<Object> commonListFromAgentProvider = getCommonListFromAgentProvider(context);
        if (!CommonUtils.isAgentSupportMinVersion(CommonConstants.SupportedApiMinVersion.VERSION_4_1, context)) {
            return -1;
        }
        try {
            if (commonListFromAgentProvider.size() > 0) {
                return Integer.valueOf((String) commonListFromAgentProvider.get(0)).intValue();
            }
        } catch (Exception unused) {
            SdkLog.i(TAG, "Exception during get agent latest version");
        }
        return -1;
    }

    public static int getAgentVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(CommonUtils.MOBILE_SERVICE_PACKAGE_NAME, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            SdkLog.s(e);
            return -1;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009b, code lost:
        if (r8 == null) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a1, code lost:
        if (r8 == null) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a3, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00aa, code lost:
        if (com.samsung.android.sdk.mobileservice.common.CommonUtils.isStandAloneSamsungAccountSupported(r9) == false) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00ac, code lost:
        mergeApiStatusListOfSaAgent(r9, r1, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00af, code lost:
        r9 = r10.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b7, code lost:
        if (r9.hasNext() == false) goto L_0x00ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b9, code lost:
        r10 = (java.lang.String) r9.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c3, code lost:
        if (r1.containsKey(r10) != false) goto L_0x00b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c5, code lost:
        r1.put(r10, -1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ce, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map<java.lang.String, java.lang.Integer> getApiStatusList(android.content.Context r9, java.lang.String[] r10) {
        /*
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r0 = 1303100000(0x4dabba60, float:3.60139776E8)
            java.lang.String r5 = java.lang.String.valueOf(r0)
            java.util.List r10 = java.util.Arrays.asList(r10)
            android.content.ContentResolver r2 = r9.getContentResolver()
            r0 = 410000000(0x18701a80, float:3.1032652E-24)
            boolean r0 = com.samsung.android.sdk.mobileservice.common.CommonUtils.isAgentSupportMinVersion(r0, r9)
            r8 = 0
            if (r0 != 0) goto L_0x001f
            return r8
        L_0x001f:
            android.net.Uri$Builder r0 = new android.net.Uri$Builder     // Catch:{ Exception -> 0x0093 }
            r0.<init>()     // Catch:{ Exception -> 0x0093 }
            java.lang.String r3 = "content"
            android.net.Uri$Builder r0 = r0.scheme(r3)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r3 = "com.samsung.android.mobileservice.provider.MobileServiceCapabilityProvider"
            android.net.Uri$Builder r0 = r0.encodedAuthority(r3)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r3 = "status"
            android.net.Uri$Builder r0 = r0.appendEncodedPath(r3)     // Catch:{ Exception -> 0x0093 }
            android.net.Uri r3 = r0.build()     // Catch:{ Exception -> 0x0093 }
            r6 = 0
            r7 = 0
            r4 = 0
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0093 }
            if (r8 == 0) goto L_0x009b
            int r0 = r8.getCount()     // Catch:{ Exception -> 0x0093 }
            if (r0 <= 0) goto L_0x009b
            r8.moveToFirst()     // Catch:{ Exception -> 0x0093 }
        L_0x004d:
            r0 = 0
            java.lang.String r0 = r8.getString(r0)     // Catch:{ Exception -> 0x0093 }
            r2 = 1
            java.lang.String r2 = r8.getString(r2)     // Catch:{ Exception -> 0x0093 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x0093 }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x0093 }
            r3 = -4
            if (r2 >= r3) goto L_0x0064
            r2 = -99
        L_0x0064:
            java.lang.String r3 = "SeMobileService"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0093 }
            r4.<init>()     // Catch:{ Exception -> 0x0093 }
            java.lang.String r5 = "api:"
            r4.append(r5)     // Catch:{ Exception -> 0x0093 }
            r4.append(r0)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r5 = ",status:"
            r4.append(r5)     // Catch:{ Exception -> 0x0093 }
            r4.append(r2)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0093 }
            com.samsung.android.sdk.mobileservice.util.SdkLog.i(r3, r4)     // Catch:{ Exception -> 0x0093 }
            boolean r3 = r10.contains(r0)     // Catch:{ Exception -> 0x0093 }
            if (r3 == 0) goto L_0x0095
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x0093 }
            r1.put(r0, r2)     // Catch:{ Exception -> 0x0093 }
            goto L_0x0095
        L_0x0090:
            r0 = move-exception
            r9 = r0
            goto L_0x00cf
        L_0x0093:
            r0 = move-exception
            goto L_0x009e
        L_0x0095:
            boolean r0 = r8.moveToNext()     // Catch:{ Exception -> 0x0093 }
            if (r0 != 0) goto L_0x004d
        L_0x009b:
            if (r8 == 0) goto L_0x00a6
            goto L_0x00a3
        L_0x009e:
            com.samsung.android.sdk.mobileservice.util.SdkLog.s(r0)     // Catch:{ all -> 0x0090 }
            if (r8 == 0) goto L_0x00a6
        L_0x00a3:
            r8.close()
        L_0x00a6:
            boolean r0 = com.samsung.android.sdk.mobileservice.common.CommonUtils.isStandAloneSamsungAccountSupported(r9)
            if (r0 == 0) goto L_0x00af
            mergeApiStatusListOfSaAgent(r9, r1, r10)
        L_0x00af:
            java.util.Iterator r9 = r10.iterator()
        L_0x00b3:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x00ce
            java.lang.Object r10 = r9.next()
            java.lang.String r10 = (java.lang.String) r10
            boolean r0 = r1.containsKey(r10)
            if (r0 != 0) goto L_0x00b3
            r0 = -1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.put(r10, r0)
            goto L_0x00b3
        L_0x00ce:
            return r1
        L_0x00cf:
            if (r8 == 0) goto L_0x00d4
            r8.close()
        L_0x00d4:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.SeMobileService.getApiStatusList(android.content.Context, java.lang.String[]):java.util.Map");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0058, code lost:
        if (r8 != null) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005e, code lost:
        if (r8 == null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0060, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0063, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<java.lang.Object> getCommonListFromAgentProvider(android.content.Context r8) {
        /*
            java.lang.String r0 = "latestVersionInGalaxyApps:"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.content.ContentResolver r2 = r8.getContentResolver()
            r8 = 0
            android.net.Uri$Builder r3 = new android.net.Uri$Builder     // Catch:{ Exception -> 0x0056 }
            r3.<init>()     // Catch:{ Exception -> 0x0056 }
            java.lang.String r4 = "content"
            android.net.Uri$Builder r3 = r3.scheme(r4)     // Catch:{ Exception -> 0x0056 }
            java.lang.String r4 = "com.samsung.android.mobileservice.provider.MobileServiceCapabilityProvider"
            android.net.Uri$Builder r3 = r3.encodedAuthority(r4)     // Catch:{ Exception -> 0x0056 }
            java.lang.String r4 = "common"
            android.net.Uri$Builder r3 = r3.appendEncodedPath(r4)     // Catch:{ Exception -> 0x0056 }
            android.net.Uri r3 = r3.build()     // Catch:{ Exception -> 0x0056 }
            r6 = 0
            r7 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0056 }
            if (r8 == 0) goto L_0x0058
            int r2 = r8.getCount()     // Catch:{ Exception -> 0x0056 }
            if (r2 <= 0) goto L_0x0058
            r8.moveToFirst()     // Catch:{ Exception -> 0x0056 }
            r2 = 0
            java.lang.String r2 = r8.getString(r2)     // Catch:{ Exception -> 0x0056 }
            java.lang.String r3 = "SeMobileService"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0056 }
            r4.<init>(r0)     // Catch:{ Exception -> 0x0056 }
            r4.append(r2)     // Catch:{ Exception -> 0x0056 }
            java.lang.String r0 = r4.toString()     // Catch:{ Exception -> 0x0056 }
            com.samsung.android.sdk.mobileservice.util.SdkLog.d(r3, r0)     // Catch:{ Exception -> 0x0056 }
            r1.add(r2)     // Catch:{ Exception -> 0x0056 }
            goto L_0x0058
        L_0x0054:
            r0 = move-exception
            goto L_0x0064
        L_0x0056:
            r0 = move-exception
            goto L_0x005b
        L_0x0058:
            if (r8 == 0) goto L_0x0063
            goto L_0x0060
        L_0x005b:
            com.samsung.android.sdk.mobileservice.util.SdkLog.s(r0)     // Catch:{ all -> 0x0054 }
            if (r8 == 0) goto L_0x0063
        L_0x0060:
            r8.close()
        L_0x0063:
            return r1
        L_0x0064:
            if (r8 == 0) goto L_0x0069
            r8.close()
        L_0x0069:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.SeMobileService.getCommonListFromAgentProvider(android.content.Context):java.util.List");
    }

    private static List<Object> getCommonListFromSaAgentProvider(Context context) {
        Throwable th;
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = context.getContentResolver().query(new Uri.Builder().scheme("content").encodedAuthority(SAMSUNG_ACCOUNT_PROVIDER_URI).appendEncodedPath(MOBILE_SERVICE_PROVIDER_PATH_COMMON).build(), (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        query.moveToFirst();
                        String string = query.getString(0);
                        SdkLog.d(TAG, "latestVersionInGalaxyApps:" + string);
                        arrayList.add(string);
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    query.close();
                    throw th3;
                }
            }
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (Exception e) {
            SdkLog.s(e);
            return arrayList;
        } catch (Throwable th4) {
            th.addSuppressed(th4);
        }
    }

    public static Map<String, List<String>> getFeatureList(Context context) {
        HashMap hashMap = new HashMap();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = null;
        if (!CommonUtils.isAgentSupportMinVersion(CommonConstants.SupportedApiMinVersion.VERSION_4_1, context)) {
            return null;
        }
        try {
            cursor = contentResolver.query(new Uri.Builder().scheme("content").encodedAuthority(MOBILE_SERVICE_PROVIDER_URI).appendEncodedPath(MOBILE_SERVICE_PROVIDER_PATH_SERVICE_FEATURE).build(), (String[]) null, (String) null, (String[]) null, (String) null);
            if (cursor != null) {
                if (cursor.getCount() >= 1) {
                    cursor.moveToFirst();
                    do {
                        String string = cursor.getString(0);
                        String string2 = cursor.getString(1);
                        SdkLog.i(TAG, "feature service:" + string + ",api:" + string2);
                        if (hashMap.containsKey(string)) {
                            ((List) hashMap.get(string)).add(string2);
                        } else {
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(string2);
                            hashMap.put(string, arrayList);
                        }
                    } while (cursor.moveToNext());
                    cursor.close();
                    if (CommonUtils.isStandAloneSamsungAccountSupported(context)) {
                        mergeFeatureListOfSaAgent(context, hashMap);
                    }
                    return hashMap;
                }
            }
            if (cursor != null) {
                cursor.close();
                return hashMap;
            }
        } catch (Exception e) {
            SdkLog.s(e);
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            if (cursor != null) {
                cursor.close();
            }
            throw th2;
        }
        return hashMap;
    }

    public static int getSaAgentLatestVersionInGalaxyApps(Context context) {
        List<Object> commonListFromSaAgentProvider = getCommonListFromSaAgentProvider(context);
        if (CommonUtils.isSaAgentSupportMinVersion(CommonConstants.SupportedApiMinVersion.VERSION_11_0, context) && commonListFromSaAgentProvider != null) {
            try {
                if (commonListFromSaAgentProvider.size() > 0) {
                    return Integer.valueOf((String) commonListFromSaAgentProvider.get(0)).intValue();
                }
            } catch (Exception unused) {
                SdkLog.i(TAG, "Exception during get samsung account agent latest version");
            }
        }
        return -1;
    }

    public static int getSaAgentVersion(Context context) {
        if (!CommonUtils.isStandAloneSamsungAccountSupported(context)) {
            return -1;
        }
        try {
            return context.getPackageManager().getPackageInfo(CommonUtils.SAMSUNG_ACCOUNT_PACKAGE_NAME, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            SdkLog.s(e);
            return -1;
        }
    }

    public static int getSdkVersionCode() {
        SdkLog.d(TAG, "SDK Version Code = 1303100000");
        return BuildConfig.VERSION_CODE;
    }

    public static String getSdkVersionName() {
        SdkLog.d(TAG, "SDK Version Name = 13.0.31");
        return BuildConfig.VERSION_NAME;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0072, code lost:
        if (r8 == null) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0078, code lost:
        if (r8 == null) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007a, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007d, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Set<java.lang.String> getSupportedSocialFeatureList(android.content.Context r8, java.lang.String r9) {
        /*
            int r0 = getAgentVersion(r8)
            r1 = 1010000000(0x3c336080, float:0.0109483)
            if (r0 >= r1) goto L_0x000e
            java.util.Set r8 = getSupportedSocialFeatureListLegacy(r0, r9)
            return r8
        L_0x000e:
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            android.content.ContentResolver r2 = r8.getContentResolver()
            r8 = 0
            android.net.Uri$Builder r0 = new android.net.Uri$Builder     // Catch:{ Exception -> 0x006f }
            r0.<init>()     // Catch:{ Exception -> 0x006f }
            java.lang.String r3 = "content"
            android.net.Uri$Builder r0 = r0.scheme(r3)     // Catch:{ Exception -> 0x006f }
            java.lang.String r3 = "com.samsung.android.mobileservice.provider.MobileServiceCapabilityProvider"
            android.net.Uri$Builder r0 = r0.encodedAuthority(r3)     // Catch:{ Exception -> 0x006f }
            java.lang.String r3 = "socialfeature"
            android.net.Uri$Builder r0 = r0.appendEncodedPath(r3)     // Catch:{ Exception -> 0x006f }
            android.net.Uri r3 = r0.build()     // Catch:{ Exception -> 0x006f }
            r6 = 0
            r7 = 0
            r4 = 0
            r5 = r9
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x006f }
            if (r8 == 0) goto L_0x0072
            int r9 = r8.getCount()     // Catch:{ Exception -> 0x006f }
            if (r9 <= 0) goto L_0x0072
            r8.moveToFirst()     // Catch:{ Exception -> 0x006f }
        L_0x0047:
            r9 = 0
            java.lang.String r9 = r8.getString(r9)     // Catch:{ Exception -> 0x006f }
            r1.add(r9)     // Catch:{ Exception -> 0x006f }
            java.lang.String r0 = "SeMobileService"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x006f }
            r2.<init>()     // Catch:{ Exception -> 0x006f }
            java.lang.String r3 = "feature: "
            r2.append(r3)     // Catch:{ Exception -> 0x006f }
            r2.append(r9)     // Catch:{ Exception -> 0x006f }
            java.lang.String r9 = r2.toString()     // Catch:{ Exception -> 0x006f }
            com.samsung.android.sdk.mobileservice.util.SdkLog.i(r0, r9)     // Catch:{ Exception -> 0x006f }
            boolean r9 = r8.moveToNext()     // Catch:{ Exception -> 0x006f }
            if (r9 != 0) goto L_0x0047
            goto L_0x0072
        L_0x006c:
            r0 = move-exception
            r9 = r0
            goto L_0x007e
        L_0x006f:
            r0 = move-exception
            r9 = r0
            goto L_0x0075
        L_0x0072:
            if (r8 == 0) goto L_0x007d
            goto L_0x007a
        L_0x0075:
            com.samsung.android.sdk.mobileservice.util.SdkLog.s(r9)     // Catch:{ all -> 0x006c }
            if (r8 == 0) goto L_0x007d
        L_0x007a:
            r8.close()
        L_0x007d:
            return r1
        L_0x007e:
            if (r8 == 0) goto L_0x0083
            r8.close()
        L_0x0083:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.SeMobileService.getSupportedSocialFeatureList(android.content.Context, java.lang.String):java.util.Set");
    }

    private static Set<String> getSupportedSocialFeatureListLegacy(int i2, String str) {
        HashSet hashSet = new HashSet();
        if (str.equals(APP_ID_GALLERY)) {
            if (i2 >= 410000000) {
                if (i2 < 1000000000) {
                    hashSet.add(SOCIAL_FEATURE_GLOBAL_GROUP_SHARING);
                    hashSet.add(SOCIAL_FEATURE_FAMILY_GROUP_SHARING);
                    return hashSet;
                }
                hashSet.add(SOCIAL_FEATURE_GLOBAL_GROUP_SHARING);
                hashSet.add(SOCIAL_FEATURE_LOCAL_GROUP_SHARING);
                hashSet.add(SOCIAL_FEATURE_FAMILY_GROUP_SHARING);
                return hashSet;
            }
        } else if (str.equals(APP_ID_DAILY_BOARD)) {
            if (i2 >= 420000000) {
                if (i2 < 1000000000) {
                    hashSet.add(SOCIAL_FEATURE_GLOBAL_GROUP_SHARING);
                    hashSet.add(SOCIAL_FEATURE_FAMILY_GROUP_SHARING);
                    return hashSet;
                }
                hashSet.add(SOCIAL_FEATURE_GLOBAL_GROUP_SHARING);
                hashSet.add(SOCIAL_FEATURE_LOCAL_GROUP_SHARING);
                hashSet.add(SOCIAL_FEATURE_FAMILY_GROUP_SHARING);
                return hashSet;
            }
        } else if (str.equals(APP_ID_SOCIAL_APP)) {
            if (i2 >= 1000000000) {
                hashSet.add(SOCIAL_FEATURE_ACTIVITY_FEEDBACK_SERVICE);
                hashSet.add(SOCIAL_FEATURE_SOCIAL_ACTIVITY_SERVICE);
                return hashSet;
            }
        } else if (!str.equals(APP_ID_SES_CALENDAR)) {
            SdkLog.d(TAG, "appId :" + str + " is not support in getSupportedSocialFeatureListLegacy versionCode : " + i2);
            return hashSet;
        } else if (i2 >= 1000600000) {
            hashSet.add(SOCIAL_FEATURE_FAMILY_GROUP_SHARING);
            return hashSet;
        }
        return hashSet;
    }

    public static boolean isAgentEnabled(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(CommonUtils.MOBILE_SERVICE_PACKAGE_NAME, 1).applicationInfo.enabled;
        } catch (PackageManager.NameNotFoundException unused) {
            SdkLog.i(TAG, "Agent not installed");
            return false;
        }
    }

    public static boolean isAgentInstalled(Context context) {
        try {
            context.getPackageManager().getPackageInfo(CommonUtils.MOBILE_SERVICE_PACKAGE_NAME, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            SdkLog.i(TAG, "Agent not installed");
            return false;
        }
    }

    public static boolean isSaAgentInstalled(Context context) {
        if (CommonUtils.isStandAloneSamsungAccountSupported(context)) {
            try {
                context.getPackageManager().getPackageInfo(CommonUtils.SAMSUNG_ACCOUNT_PACKAGE_NAME, 1);
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
                SdkLog.i(TAG, "Samsung Account Agent not installed");
            }
        }
        return false;
    }

    public static Boolean isSupportAccountBasedService(Context context) {
        Throwable th;
        if (getAgentVersion(context) < 1210000000) {
            return Boolean.FALSE;
        }
        ContentResolver contentResolver = context.getContentResolver();
        boolean z = false;
        try {
            Cursor query = contentResolver.query(new Uri.Builder().scheme("content").encodedAuthority(MOBILE_SERVICE_PROVIDER_URI).appendEncodedPath(MOBILE_SERVICE_PROVIDER_PATH_ACCOUNT_BASE_SERVICE).build(), (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        z = true;
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    query.close();
                    throw th3;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            SdkLog.s(e);
        } catch (Throwable th4) {
            th.addSuppressed(th4);
        }
        return Boolean.valueOf(z);
    }

    private static void mergeApiStatusListOfSaAgent(Context context, Map<String, Integer> map, List<String> list) {
        Throwable th;
        try {
            Cursor query = context.getContentResolver().query(new Uri.Builder().scheme("content").encodedAuthority(SAMSUNG_ACCOUNT_PROVIDER_URI).appendEncodedPath("status").build(), (String[]) null, String.valueOf(BuildConfig.VERSION_CODE), (String[]) null, (String) null);
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        query.moveToFirst();
                        do {
                            String string = query.getString(0);
                            int intValue = Integer.valueOf(query.getString(1)).intValue();
                            if (intValue < -4) {
                                intValue = -99;
                            }
                            SdkLog.i(TAG, "api:" + string + ",status:" + intValue);
                            if (list.contains(string)) {
                                map.put(string, Integer.valueOf(intValue));
                            }
                        } while (query.moveToNext());
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    query.close();
                    throw th3;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            SdkLog.s(e);
        } catch (Throwable th4) {
            th.addSuppressed(th4);
        }
    }

    private static void mergeFeatureListOfSaAgent(Context context, Map<String, List<String>> map) {
        Throwable th;
        try {
            Cursor query = context.getContentResolver().query(new Uri.Builder().scheme("content").encodedAuthority(SAMSUNG_ACCOUNT_PROVIDER_URI).appendEncodedPath(MOBILE_SERVICE_PROVIDER_PATH_SERVICE_FEATURE).build(), (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                try {
                    if (query.getCount() >= 1) {
                        query.moveToFirst();
                        do {
                            String string = query.getString(0);
                            String string2 = query.getString(1);
                            SdkLog.i(TAG, "feature service:" + string + ",api:" + string2);
                            if (map.containsKey(string)) {
                                map.get(string).add(string2);
                            } else {
                                ArrayList arrayList = new ArrayList();
                                arrayList.add(string2);
                                map.put(string, arrayList);
                            }
                        } while (query.moveToNext());
                        query.close();
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    query.close();
                    throw th3;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            SdkLog.s(e);
        } catch (Throwable th4) {
            th.addSuppressed(th4);
        }
    }
}
