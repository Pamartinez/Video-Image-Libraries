package com.samsung.android.sdk.mobileservice.social.datasync;

import android.os.Bundle;
import android.os.RemoteException;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import com.samsung.android.sdk.mobileservice.common.api.SeMobileServiceApi;
import com.samsung.android.sdk.mobileservice.common.exception.NotConnectedException;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback;
import com.samsung.android.sdk.mobileservice.social.datasync.result.DataSyncResult;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DataSyncApi extends SeMobileServiceApi {
    public static final String API_NAME = "DataSyncApi";
    private static final String BUNDLE_KEY_DATA_LIST = "data_list";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DataSyncResultCallback {
        void onResult(DataSyncResult dataSyncResult);
    }

    public DataSyncApi(SeMobileServiceSession seMobileServiceSession) {
        super(seMobileServiceSession, "DataSyncApi");
        checkAuthorized(0, 2);
    }

    public int delete(DataSyncRequest dataSyncRequest, final DataSyncResultCallback dataSyncResultCallback) {
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_6_50)) {
            return -7;
        }
        String serviceName = dataSyncRequest.getServiceName();
        if (serviceName == null || serviceName.isEmpty()) {
            infoLog("serviceName is invalid");
            return -1;
        }
        try {
            getSocialService().dataSyncDelete(serviceName, dataSyncRequest.toBundle(), new IBundleResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    DataSyncResultCallback dataSyncResultCallback = dataSyncResultCallback;
                    if (dataSyncResultCallback != null) {
                        dataSyncResultCallback.onResult(new DataSyncResult(new CommonResultStatus(-1, str, Long.toString(j2))));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    DataSyncResultCallback dataSyncResultCallback = dataSyncResultCallback;
                    if (dataSyncResultCallback != null) {
                        dataSyncResultCallback.onResult(new DataSyncResult(new CommonResultStatus(1)));
                    }
                }
            });
            return 1;
        } catch (RemoteException | NotConnectedException e) {
            secureLog(e);
            return -1;
        }
    }

    public String[] getEssentialServiceNames() {
        return new String[]{"SocialService"};
    }

    public int getSyncedData(DataSyncRequest dataSyncRequest, final DataSyncResultCallback dataSyncResultCallback) {
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_6_50)) {
            return -7;
        }
        final String serviceName = dataSyncRequest.getServiceName();
        if (serviceName == null || serviceName.isEmpty()) {
            infoLog("serviceName is invalid");
            return -1;
        }
        try {
            getSocialService().dataSyncGetSyncedData(serviceName, dataSyncRequest.toBundle(), new IBundleResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    DataSyncResultCallback dataSyncResultCallback = dataSyncResultCallback;
                    if (dataSyncResultCallback != null) {
                        dataSyncResultCallback.onResult(new DataSyncResult(new CommonResultStatus(-1, str, Long.toString(j2))));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    if (dataSyncResultCallback != null) {
                        ArrayList arrayList = new ArrayList();
                        ArrayList<Bundle> parcelableArrayList = bundle.getParcelableArrayList(DataSyncApi.BUNDLE_KEY_DATA_LIST);
                        if (parcelableArrayList != null) {
                            for (Bundle syncedData : parcelableArrayList) {
                                arrayList.add(new SyncedData(serviceName, syncedData));
                            }
                        }
                        dataSyncResultCallback.onResult(new DataSyncResult(new CommonResultStatus(1), arrayList));
                    }
                }
            });
            return 1;
        } catch (RemoteException | NotConnectedException e) {
            secureLog(e);
            return -1;
        }
    }

    public int upsert(DataSyncRequest dataSyncRequest, final DataSyncResultCallback dataSyncResultCallback) {
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_6_50)) {
            return -7;
        }
        if (dataSyncRequest.isInvalid().booleanValue()) {
            infoLog("request data is invalid");
            return -1;
        }
        try {
            final String serviceName = dataSyncRequest.getServiceName();
            getSocialService().dataSyncUpsert(serviceName, dataSyncRequest.toBundle(), new IBundleResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    DataSyncResultCallback dataSyncResultCallback = dataSyncResultCallback;
                    if (dataSyncResultCallback != null) {
                        dataSyncResultCallback.onResult(new DataSyncResult(new CommonResultStatus(-1, str, Long.toString(j2))));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    if (dataSyncResultCallback != null) {
                        ArrayList arrayList = new ArrayList();
                        ArrayList<Bundle> parcelableArrayList = bundle.getParcelableArrayList(DataSyncApi.BUNDLE_KEY_DATA_LIST);
                        if (parcelableArrayList != null) {
                            for (Bundle syncedData : parcelableArrayList) {
                                arrayList.add(new SyncedData(serviceName, syncedData));
                            }
                        }
                        dataSyncResultCallback.onResult(new DataSyncResult(new CommonResultStatus(1), arrayList));
                    }
                }
            });
            return 1;
        } catch (RemoteException | NotConnectedException e) {
            secureLog(e);
            return -1;
        }
    }
}
