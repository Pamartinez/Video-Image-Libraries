package com.samsung.android.sdk.mobileservice.place;

import N2.j;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import com.samsung.android.sdk.mobileservice.common.api.SeMobileServiceApi;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.place.IPlaceResultCallback;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlaceApi extends SeMobileServiceApi {
    public static final String API_NAME = "PlaceApi";
    public static final String SERVICE_NAME = "PlaceService";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PlaceRequest {
        private String mAddress;
        private String mBluetoothMacAddress;
        private String mBluetoothName;
        private double mLatitude;
        private double mLongitude;
        private String mName;
        private int mPlaceType;
        private String mWifiBssId;
        private String mWifiName;

        public PlaceRequest(String str, String str2, double d, double d2, String str3, String str4) {
            this(str, 4, str2, d, d2, str3, str4, (String) null, (String) null);
        }

        public String getAddress() {
            return this.mAddress;
        }

        public String getBluetoothMacAddress() {
            return this.mBluetoothMacAddress;
        }

        public String getBluetoothName() {
            return this.mBluetoothName;
        }

        public double getLatitude() {
            return this.mLatitude;
        }

        public double getLongitude() {
            return this.mLongitude;
        }

        public String getName() {
            return this.mName;
        }

        public int getPlaceType() {
            return this.mPlaceType;
        }

        public String getWifiBssId() {
            return this.mWifiBssId;
        }

        public String getWifiName() {
            return this.mWifiName;
        }

        public void setAddress(String str) {
            this.mAddress = str;
        }

        public void setBluetoothMacAddress(String str) {
            this.mBluetoothMacAddress = str;
        }

        public void setBluetoothName(String str) {
            this.mBluetoothName = str;
        }

        public void setLatitude(double d) {
            this.mLatitude = d;
        }

        public void setLongitude(double d) {
            this.mLongitude = d;
        }

        public void setName(String str) {
            this.mName = str;
        }

        public void setPlaceType(int i2) {
            this.mPlaceType = i2;
        }

        public void setWifiBssId(String str) {
            this.mWifiBssId = str;
        }

        public void setWifiName(String str) {
            this.mWifiName = str;
        }

        public Bundle writeToBundle() {
            Bundle bundle = new Bundle();
            bundle.putString("name", this.mName);
            bundle.putInt(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, this.mPlaceType);
            bundle.putString("address", this.mAddress);
            bundle.putDouble("latitude", this.mLatitude);
            bundle.putDouble("longitude", this.mLongitude);
            bundle.putString("wifi_name", this.mWifiName);
            bundle.putString("wifi_bss_id", this.mWifiBssId);
            bundle.putString("bluetooth_name", this.mBluetoothName);
            bundle.putString("bluetooth_mac_address", this.mBluetoothMacAddress);
            return bundle;
        }

        public PlaceRequest(int i2, String str, double d, double d2, String str2, String str3) {
            this((String) null, i2, str, d, d2, str2, str3, (String) null, (String) null);
        }

        public PlaceRequest(String str, String str2) {
            this((String) null, 3, (String) null, -1.0d, -1.0d, (String) null, (String) null, str, str2);
        }

        private PlaceRequest(String str, int i2, String str2, double d, double d2, String str3, String str4, String str5, String str6) {
            this.mName = str;
            this.mPlaceType = i2;
            this.mAddress = str2;
            this.mLatitude = d;
            this.mLongitude = d2;
            this.mWifiName = str3;
            this.mWifiBssId = str4;
            this.mBluetoothName = str5;
            this.mBluetoothMacAddress = str6;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface PlaceResultCallback<T> {
        void onResult(PlaceResult<T> placeResult);
    }

    public PlaceApi(SeMobileServiceSession seMobileServiceSession) {
        super(seMobileServiceSession, "PlaceApi");
        checkAuthorized(0);
    }

    private boolean isAppIdNullOrEmpty() {
        if (!TextUtils.isEmpty(getAppId())) {
            return false;
        }
        debugLog("appId is null or empty");
        return true;
    }

    public int getCurrentCount() {
        debugLog("getCurrentCount ");
        if (isAppIdNullOrEmpty()) {
            return -1;
        }
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            int currentCount = getPlaceService().getCurrentCount();
            debugLog("getCurrentCount : count=[" + currentCount + "] ");
            return currentCount;
        } catch (Exception e) {
            secureLog(e);
            return -1;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return -1;
        }
    }

    public String[] getEssentialServiceNames() {
        return new String[]{"PlaceService"};
    }

    public int getMaxCount() {
        debugLog("getMaxCount ");
        if (isAppIdNullOrEmpty()) {
            return -1;
        }
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        try {
            int maxCount = getPlaceService().getMaxCount();
            debugLog("getMaxCount : count=[" + maxCount + "] ");
            return maxCount;
        } catch (Exception e) {
            secureLog(e);
            return -1;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return -1;
        }
    }

    public List<Place> getPlaceList() {
        debugLog("getPlaces ");
        if (isAppIdNullOrEmpty()) {
            return new ArrayList();
        }
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        try {
            List<Bundle> placeList = getPlaceService().getPlaceList();
            debugLog("getPlaceList : list size=[" + placeList.size() + "] ");
            for (Bundle place : placeList) {
                arrayList.add(new Place(place));
            }
            return arrayList;
        } catch (Exception e) {
            secureLog(e);
            return new ArrayList();
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return new ArrayList();
        }
    }

    public int requestPlaceCreation(PlaceRequest placeRequest, final PlaceResultCallback<Place> placeResultCallback) {
        debugLog("requestPlaceCreation ");
        if (isAppIdNullOrEmpty()) {
            placeResultCallback.onResult(new PlaceResult(new CommonResultStatus(-1, "appId is null or empty", ""), null));
            return -1;
        } else if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            placeResultCallback.onResult(new PlaceResult(new CommonResultStatus(200), null));
            return -7;
        } else {
            try {
                getPlaceService().requestPlaceCreate(placeRequest.writeToBundle(), new IPlaceResultCallback.Stub() {
                    public void onFailure(String str, String str2) {
                        PlaceApi.this.debugLog(j.d("requestPlaceCreation onFailure : code=[", str, "], message=[", str2, "] "));
                        placeResultCallback.onResult(new PlaceResult(new CommonResultStatus(-1, str2, str), null));
                    }

                    public void onSuccess(Bundle bundle) {
                        Place place = new Place(bundle);
                        PlaceApi placeApi = PlaceApi.this;
                        placeApi.debugLog("requestPlaceCreation onSuccess : name=[" + place.getName() + "], createTime=[" + place.getUpdatedTime() + "]");
                        placeResultCallback.onResult(new PlaceResult(new CommonResultStatus(1), place));
                    }
                });
                return 1;
            } catch (Exception e) {
                secureLog(e);
                return -1;
            } catch (OutOfMemoryError unused) {
                verboseLog("OutOfMemoryError occurred");
                return -1;
            }
        }
    }

    public int requestPlaceDeletion(String str, final PlaceResultCallback<Boolean> placeResultCallback) {
        debugLog("requestPlaceDeletion ");
        if (isAppIdNullOrEmpty()) {
            placeResultCallback.onResult(new PlaceResult(new CommonResultStatus(-1, "appId is null or empty", ""), Boolean.FALSE));
            return -1;
        } else if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            placeResultCallback.onResult(new PlaceResult(new CommonResultStatus(200), Boolean.FALSE));
            return -7;
        } else {
            try {
                getPlaceService().requestPlaceDelete(str, new IPlaceResultCallback.Stub() {
                    public void onFailure(String str, String str2) {
                        PlaceApi.this.debugLog(j.d("requestPlaceDeletion onFailure : code=[", str, "], message=[", str2, "] "));
                        placeResultCallback.onResult(new PlaceResult(new CommonResultStatus(-1, str2, str), Boolean.FALSE));
                    }

                    public void onSuccess(Bundle bundle) {
                        PlaceApi.this.debugLog("requestPlaceDeletion onSuccess ");
                        placeResultCallback.onResult(new PlaceResult(new CommonResultStatus(1), Boolean.TRUE));
                    }
                });
                return 1;
            } catch (Exception e) {
                secureLog(e);
                return -1;
            } catch (OutOfMemoryError unused) {
                verboseLog("OutOfMemoryError occurred");
                return -1;
            }
        }
    }

    public int requestPlaceUpdate(Place place, final PlaceResultCallback<Place> placeResultCallback) {
        debugLog("requestPlaceUpdate ");
        if (isAppIdNullOrEmpty()) {
            placeResultCallback.onResult(new PlaceResult(new CommonResultStatus(-1, "appId is null or empty", ""), null));
            return -1;
        } else if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            placeResultCallback.onResult(new PlaceResult(new CommonResultStatus(200), null));
            return -7;
        } else {
            try {
                getPlaceService().requestPlaceUpdate(place.writeToBundle(), new IPlaceResultCallback.Stub() {
                    public void onFailure(String str, String str2) {
                        PlaceApi.this.debugLog(j.d("requestPlaceUpdate onFailure : code=[", str, "], message=[", str2, "] "));
                        placeResultCallback.onResult(new PlaceResult(new CommonResultStatus(-1, str2, str), null));
                    }

                    public void onSuccess(Bundle bundle) {
                        Place place = new Place(bundle);
                        PlaceApi placeApi = PlaceApi.this;
                        placeApi.debugLog("requestPlaceUpdate onSuccess : name=[" + place.getName() + "], updateTime=[" + place.getUpdatedTime() + "]");
                        placeResultCallback.onResult(new PlaceResult(new CommonResultStatus(1), place));
                    }
                });
                return 1;
            } catch (Exception e) {
                secureLog(e);
                return -1;
            } catch (OutOfMemoryError unused) {
                verboseLog("OutOfMemoryError occurred");
                return -1;
            }
        }
    }

    public int requestSync(final PlaceResultCallback<Boolean> placeResultCallback) {
        debugLog("requestSync ");
        if (isAppIdNullOrEmpty()) {
            placeResultCallback.onResult(new PlaceResult(new CommonResultStatus(-1, "appId is null or empty", ""), Boolean.FALSE));
            return -1;
        } else if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            placeResultCallback.onResult(new PlaceResult(new CommonResultStatus(200), Boolean.FALSE));
            return -7;
        } else {
            try {
                getPlaceService().requestSync(new IPlaceResultCallback.Stub() {
                    public void onFailure(String str, String str2) {
                        PlaceApi.this.debugLog(j.d("requestSync onFailure : code=[", str, "], message=[", str2, "] "));
                        placeResultCallback.onResult(new PlaceResult(new CommonResultStatus(-1, str2, str), Boolean.FALSE));
                    }

                    public void onSuccess(Bundle bundle) {
                        PlaceApi.this.debugLog("requestSync onSuccess ");
                        placeResultCallback.onResult(new PlaceResult(new CommonResultStatus(1), Boolean.TRUE));
                    }
                });
                return 1;
            } catch (Exception e) {
                secureLog(e);
                return -1;
            } catch (OutOfMemoryError unused) {
                verboseLog("OutOfMemoryError occurred");
                return -1;
            }
        }
    }

    public List<Place> getPlaceList(int i2) {
        debugLog("getPlaces ");
        if (isAppIdNullOrEmpty()) {
            return new ArrayList();
        }
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        try {
            List<Bundle> searchPlaces = getPlaceService().searchPlaces(i2);
            debugLog("getPlaceList(" + i2 + ") : list size=[" + searchPlaces.size() + "] ");
            for (Bundle place : searchPlaces) {
                arrayList.add(new Place(place));
            }
            return arrayList;
        } catch (Exception e) {
            secureLog(e);
            return new ArrayList();
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return new ArrayList();
        }
    }
}
