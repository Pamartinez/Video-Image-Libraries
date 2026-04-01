package com.samsung.android.sdk.mobileservice.social.buddy;

import A.a;
import android.os.Bundle;
import android.os.RemoteException;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.mobileservice.common.api.SeMobileServiceApi;
import com.samsung.android.sdk.mobileservice.common.exception.NotConnectedException;
import com.samsung.android.sdk.mobileservice.common.result.BooleanResult;
import com.samsung.android.sdk.mobileservice.common.result.CommonResult;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.social.buddy.IBuddyInfoResultCallback;
import com.samsung.android.sdk.mobileservice.social.buddy.IPublicBuddyInfoResultCallback;
import com.samsung.android.sdk.mobileservice.social.buddy.ISyncResultCallback;
import com.samsung.android.sdk.mobileservice.social.buddy.PublicBuddyInfo;
import com.samsung.android.sdk.mobileservice.social.buddy.request.BuddyInfoRequest;
import com.samsung.android.sdk.mobileservice.social.buddy.request.TargetAppListRequest;
import com.samsung.android.sdk.mobileservice.social.buddy.result.Buddy;
import com.samsung.android.sdk.mobileservice.social.buddy.result.BuddyResult;
import com.samsung.android.sdk.mobileservice.social.buddy.result.PublicBuddyInfoResult;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BuddyApi extends SeMobileServiceApi {
    public static final String API_NAME = "BuddyApi";
    private static final String EXTRA_TIMESTAMP = "timestamp";
    private static final Long INVALID_TIMESTAMP = -1L;
    private static final String KEY_QUERY_RESULT = "QUERY_RESULT";
    private static final String KEY_QUERY_SELECTION = "QUERY_SELECTION";
    private static final int QUERY_TYPE_CERTIFICATE = 0;
    private static final int QUERY_TYPE_SYNCED_CONTACT_ID = 1;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface BuddyInfoCallback {
        void onResult(BuddyResult<Buddy> buddyResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface BuddyResultCallback<T> {
        void onResult(T t);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface PublicBuddyInfoResultCallback {
        void onResult(PublicBuddyInfoResult publicBuddyInfoResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SyncResultCallback {
        void onResult(BooleanResult booleanResult);
    }

    public BuddyApi(SeMobileServiceSession seMobileServiceSession) {
        super(seMobileServiceSession, "BuddyApi");
        checkAuthorized(0, 2, 1);
    }

    /* access modifiers changed from: private */
    public boolean checkItem(int i2, int i7) {
        if ((i2 & i7) == i7) {
            return true;
        }
        return false;
    }

    public void getBuddyInfo(final BuddyInfoRequest buddyInfoRequest, final BuddyInfoCallback buddyInfoCallback) {
        try {
            debugLog("getBuddyInfo");
            getSocialService().getBuddyInfo(buddyInfoRequest.toBundle(), new IBuddyInfoResultCallback.Stub() {
                public void onFailure(int i2, String str) {
                    BuddyApi buddyApi = BuddyApi.this;
                    buddyApi.debugLog("getBuddyInfo onFailure : code=[" + i2 + "], message=[" + str + "] ");
                    CommonResultStatus commonResultStatus = new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(i2), str, String.valueOf(i2));
                    BuddyInfoCallback buddyInfoCallback = buddyInfoCallback;
                    if (buddyInfoCallback != null) {
                        buddyInfoCallback.onResult(new BuddyResult(commonResultStatus, null));
                    }
                }

                /* JADX WARNING: type inference failed for: r8v2, types: [com.samsung.android.sdk.mobileservice.social.buddy.result.Buddy] */
                /* JADX WARNING: Multi-variable type inference failed */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onSuccess(android.os.Bundle r8) {
                    /*
                        r7 = this;
                        java.lang.String r0 = "QUERY_RESULT"
                        r1 = 1000(0x3e8, double:4.94E-321)
                        long r0 = r8.getLong(r0, r1)
                        com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus r2 = new com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus
                        r3 = 0
                        int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
                        r4 = 1
                        if (r3 != 0) goto L_0x0013
                        r0 = r4
                        goto L_0x0017
                    L_0x0013:
                        int r0 = com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor.convertErrorcode((long) r0)
                    L_0x0017:
                        r2.<init>(r0)
                        int r0 = r2.getCode()
                        r1 = 0
                        if (r0 != r4) goto L_0x0062
                        java.lang.String r0 = "contact_id"
                        java.lang.String r0 = r8.getString(r0)
                        com.samsung.android.sdk.mobileservice.social.buddy.BuddyApi r3 = com.samsung.android.sdk.mobileservice.social.buddy.BuddyApi.this
                        com.samsung.android.sdk.mobileservice.social.buddy.request.BuddyInfoRequest r5 = r5
                        int r5 = r5.getItemFlags()
                        boolean r3 = r3.checkItem(r5, r4)
                        if (r3 == 0) goto L_0x0041
                        com.samsung.android.sdk.mobileservice.social.buddy.result.Certificate r3 = new com.samsung.android.sdk.mobileservice.social.buddy.result.Certificate
                        java.lang.String r4 = "certificate"
                        java.lang.String r4 = r8.getString(r4)
                        r3.<init>(r4)
                        goto L_0x0042
                    L_0x0041:
                        r3 = r1
                    L_0x0042:
                        com.samsung.android.sdk.mobileservice.social.buddy.BuddyApi r4 = com.samsung.android.sdk.mobileservice.social.buddy.BuddyApi.this
                        com.samsung.android.sdk.mobileservice.social.buddy.request.BuddyInfoRequest r5 = r5
                        int r5 = r5.getItemFlags()
                        r6 = 2
                        boolean r4 = r4.checkItem(r5, r6)
                        if (r4 == 0) goto L_0x005c
                        com.samsung.android.sdk.mobileservice.social.buddy.result.SyncedContact r1 = new com.samsung.android.sdk.mobileservice.social.buddy.result.SyncedContact
                        java.lang.String r4 = "raw_contact_id"
                        java.lang.String r8 = r8.getString(r4)
                        r1.<init>(r8)
                    L_0x005c:
                        com.samsung.android.sdk.mobileservice.social.buddy.result.Buddy r8 = new com.samsung.android.sdk.mobileservice.social.buddy.result.Buddy
                        r8.<init>(r0, r3, r1)
                        r1 = r8
                    L_0x0062:
                        com.samsung.android.sdk.mobileservice.social.buddy.BuddyApi$BuddyInfoCallback r7 = r6
                        com.samsung.android.sdk.mobileservice.social.buddy.result.BuddyResult r8 = new com.samsung.android.sdk.mobileservice.social.buddy.result.BuddyResult
                        r8.<init>(r2, r1)
                        r7.onResult(r8)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.social.buddy.BuddyApi.AnonymousClass3.onSuccess(android.os.Bundle):void");
                }
            });
        } catch (RemoteException e) {
            secureLog(e);
            buddyInfoCallback.onResult(new BuddyResult(new CommonResultStatus(301), null));
        } catch (NotConnectedException e7) {
            secureLog(e7);
            buddyInfoCallback.onResult(new BuddyResult(new CommonResultStatus(-8), null));
        }
    }

    public CommonResult<Long> getBuddySyncTime() {
        debugLog("getBuddySyncTime ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_6_50)) {
            return new CommonResult<>(new CommonResultStatus(-7), INVALID_TIMESTAMP);
        }
        try {
            Bundle buddySyncTime = getSocialService().getBuddySyncTime();
            long longValue = INVALID_TIMESTAMP.longValue();
            if (buddySyncTime != null) {
                longValue = buddySyncTime.getLong("timestamp");
            }
            debugLog("getBuddySyncTime. result: " + longValue);
            return new CommonResult<>(new CommonResultStatus(1), Long.valueOf(longValue));
        } catch (Exception e) {
            secureLog(e);
            return new CommonResult<>(new CommonResultStatus(-1), INVALID_TIMESTAMP);
        }
    }

    public String[] getEssentialServiceNames() {
        return new String[]{"SocialService"};
    }

    public int requestAppUpdateAndLaunchForBuddy(TargetAppListRequest targetAppListRequest, final BuddyResultCallback<BooleanResult> buddyResultCallback) {
        debugLog("requestAppUpdateAndLaunchForBuddy ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_6_10)) {
            return -7;
        }
        try {
            getSocialService().requestAppUpdateAndLaunchForBuddy(targetAppListRequest.toBundle(), new IBuddyInfoResultCallback.Stub() {
                public void onFailure(int i2, String str) {
                    BuddyApi buddyApi = BuddyApi.this;
                    buddyApi.debugLog("requestAppUpdateAndLaunchForBuddy onFailure : code=[" + i2 + "], message=[" + str + "] ");
                    if (buddyResultCallback != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(i2);
                        BuddyApi buddyApi2 = BuddyApi.this;
                        StringBuilder h5 = a.h(convertErrorcode, i2, "requestAppUpdateAndLaunchForBuddy onFailure (", ArcCommonLog.TAG_COMMA, ArcCommonLog.TAG_COMMA);
                        h5.append(str);
                        h5.append(")");
                        buddyApi2.debugLog(h5.toString());
                        buddyResultCallback.onResult(new BooleanResult(new CommonResultStatus(convertErrorcode, str, Long.toString((long) i2)), false));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    BuddyApi.this.debugLog("requestAppUpdateAndLaunchForBuddy onSuccess ");
                    BuddyResultCallback buddyResultCallback = buddyResultCallback;
                    if (buddyResultCallback != null) {
                        buddyResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
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

    public int requestPublicBuddyInfo(String str, final PublicBuddyInfoResultCallback publicBuddyInfoResultCallback) {
        debugLog("requestPublicBuddyInfo ");
        secureLog(" - phoneNumber = [%s]", str);
        try {
            getSocialService().requestPublicBuddyInfo(str, new IPublicBuddyInfoResultCallback.Stub() {
                public void onFailure(int i2, String str) {
                    BuddyApi buddyApi = BuddyApi.this;
                    buddyApi.debugLog("requestPublicBuddyInfo onFailure : code=[" + i2 + "], message=[" + str + "] ");
                    if (publicBuddyInfoResultCallback != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(i2);
                        BuddyApi buddyApi2 = BuddyApi.this;
                        StringBuilder h5 = a.h(convertErrorcode, i2, "requestPublicBuddyInfo onFailure (", ArcCommonLog.TAG_COMMA, ArcCommonLog.TAG_COMMA);
                        h5.append(str);
                        h5.append(")");
                        buddyApi2.debugLog(h5.toString());
                        publicBuddyInfoResultCallback.onResult(new PublicBuddyInfoResult(new CommonResultStatus(convertErrorcode, str, Integer.toString(i2)), (PublicBuddyInfo) null));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    BuddyApi.this.debugLog("requestPublicBuddyInfo onSuccess ");
                    if (publicBuddyInfoResultCallback != null) {
                        String string = bundle.getString("extra_phone_number", (String) null);
                        String string2 = bundle.getString("extra_name", (String) null);
                        String string3 = bundle.getString("extra_status_msg", (String) null);
                        byte[] byteArray = bundle.getByteArray("extra_image");
                        String string4 = bundle.getString("extra_image_type");
                        ArrayList<String> stringArrayList = bundle.getStringArrayList("extra_app_list");
                        ArrayList<String> stringArrayList2 = bundle.getStringArrayList("extra_svc_list");
                        BuddyApi.this.secureLog("requestPublicBuddyInfo extra_phone_number : [%s]", string);
                        BuddyApi.this.secureLog("requestPublicBuddyInfo extra_status_msg : [%s]", string3);
                        ArrayList arrayList = new ArrayList();
                        if (!(stringArrayList == null || stringArrayList2 == null)) {
                            for (int i2 = 0; i2 < stringArrayList.size(); i2++) {
                                arrayList.add(new PublicBuddyInfo.Capability(stringArrayList.get(i2), stringArrayList2.get(i2)));
                            }
                        }
                        publicBuddyInfoResultCallback.onResult(new PublicBuddyInfoResult(new CommonResultStatus(1), new PublicBuddyInfo(string, string2, string3, byteArray, string4, arrayList)));
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
    public int requestSync(final SyncResultCallback syncResultCallback) {
        debugLog("requestSync ");
        try {
            getSocialService().requestSync(new ISyncResultCallback.Stub() {
                public void onFailure(int i2, String str) {
                    BuddyApi buddyApi = BuddyApi.this;
                    buddyApi.debugLog("requestSync onFailure : code=[" + i2 + "], message=[" + str + "] ");
                    if (syncResultCallback != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(i2);
                        BuddyApi buddyApi2 = BuddyApi.this;
                        StringBuilder h5 = a.h(convertErrorcode, i2, "requestSync onFailure (", ArcCommonLog.TAG_COMMA, ArcCommonLog.TAG_COMMA);
                        h5.append(str);
                        h5.append(")");
                        buddyApi2.debugLog(h5.toString());
                        syncResultCallback.onResult(new BooleanResult(new CommonResultStatus(convertErrorcode, str, Integer.toString(i2)), false));
                    }
                }

                public void onSuccess() {
                    BuddyApi.this.debugLog("requestSync onSuccess ");
                    SyncResultCallback syncResultCallback = syncResultCallback;
                    if (syncResultCallback != null) {
                        syncResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
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

    public int requestSync(int i2, final SyncResultCallback syncResultCallback) {
        debugLog("requestSync ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_11_1)) {
            return -7;
        }
        try {
            getSocialService().requestBuddySync(i2, new ISyncResultCallback.Stub() {
                public void onFailure(int i2, String str) {
                    BuddyApi buddyApi = BuddyApi.this;
                    buddyApi.debugLog("requestSync onFailure : code=[" + i2 + "], message=[" + str + "] ");
                    if (syncResultCallback != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(i2);
                        BuddyApi buddyApi2 = BuddyApi.this;
                        StringBuilder h5 = a.h(convertErrorcode, i2, "requestSync onFailure (", ArcCommonLog.TAG_COMMA, ArcCommonLog.TAG_COMMA);
                        h5.append(str);
                        h5.append(")");
                        buddyApi2.debugLog(h5.toString());
                        syncResultCallback.onResult(new BooleanResult(new CommonResultStatus(convertErrorcode, str, Integer.toString(i2)), false));
                    }
                }

                public void onSuccess() {
                    BuddyApi.this.debugLog("requestSync onSuccess ");
                    SyncResultCallback syncResultCallback = syncResultCallback;
                    if (syncResultCallback != null) {
                        syncResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
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
}
