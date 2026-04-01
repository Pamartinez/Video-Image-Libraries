package com.samsung.android.gallery.module.location;

import A.a;
import android.location.Address;
import android.location.Geocoder;
import android.location.Geocoder$GeocodeListener;
import android.os.AsyncTask;
import android.os.Build;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.map.manager.AddressCompat;
import com.samsung.android.gallery.module.map.manager.AddressHelper;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LocationCoordinate;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.scsp.media.file.FileApiContract;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocationManager {
    private static final LocationManager instance = new LocationManager();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PlainTextEnabler {
        private static boolean include(String str) {
            if ("ko".equalsIgnoreCase(str) || "en".equalsIgnoreCase(str)) {
                return true;
            }
            return false;
        }

        public static boolean support(Locale locale) {
            if (Features.isEnabled(Features.IS_CHINESE_DEVICE) || !include(locale.getLanguage())) {
                return false;
            }
            return true;
        }
    }

    public static LocationManager getInstance() {
        return instance;
    }

    public JSONObject buildJsonObject(double d, double d2, Double d3, String str, Long l, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("latitude", d);
            jSONObject.put("longitude", d2);
            jSONObject.put("address", str);
            if (d3 != null) {
                jSONObject.put("mapZoomLevel", d3.doubleValue());
            }
            if (l != null) {
                jSONObject.put("entryItem", l);
            }
            if (str2 != null) {
                jSONObject.put(FileApiContract.Parameter.PATH, str2);
            }
            return jSONObject;
        } catch (NullPointerException | JSONException e) {
            a.s(e, new StringBuilder("buildJsonObject failed. e="), "LocationManager");
            return null;
        }
    }

    public void loadLocation(FileItemInterface fileItemInterface, Consumer<AddressCompat> consumer) {
        if (fileItemInterface != null) {
            taskOf(fileItemInterface.getLatitude(), fileItemInterface.getLongitude(), fileItemInterface, consumer).include((!AddressCompat.SUPPORT_PLAIN_ADDRESS || !PlainTextEnabler.support(Locale.getDefault())) ? 3 : 11).start();
        } else {
            Log.e("LocationManager", "loadLocation skip. null item");
        }
    }

    public void loadLocationFull(double d, double d2, Consumer<AddressCompat> consumer) {
        int i2;
        if (!AddressCompat.SUPPORT_PLAIN_ADDRESS || !PlainTextEnabler.support(Locale.getDefault())) {
            i2 = 6;
        } else {
            i2 = 14;
        }
        taskOf(d, d2, (FileItemInterface) null, consumer).include(i2).start();
    }

    public void loadLocationSimple(double d, double d2, Consumer<AddressCompat> consumer) {
        taskOf(d, d2, (FileItemInterface) null, consumer).include(4).start();
    }

    public LocationTask taskOf(double d, double d2, FileItemInterface fileItemInterface, Consumer<AddressCompat> consumer) {
        if (Build.VERSION.SDK_INT >= 33) {
            return new LocationTask33(d, d2, fileItemInterface, consumer);
        }
        return new LocationTask(d, d2, fileItemInterface, consumer);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LocationTask33 extends LocationTask {
        static final boolean USE_ITEM_ADDRESS;

        static {
            boolean z;
            if (!PreferenceFeatures.OneUi5x.SUPPORT_ADDRESS_FROM_DATABASE || Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
                z = false;
            } else {
                z = true;
            }
            USE_ITEM_ADDRESS = z;
        }

        public LocationTask33(double d, double d2, FileItemInterface fileItemInterface, Consumer<AddressCompat> consumer) {
            super(d, d2, fileItemInterface, consumer);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$notifyCallback$2(AddressCompat addressCompat) {
            this.callback.accept(addressCompat);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$start$0() {
            AddressCompat fromDB = getFromDB();
            if (fromDB != null) {
                notifyCallback(fromDB);
            } else {
                loadFromGeocoder();
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$start$1(long j2, List list) {
            Address address;
            if (list.isEmpty()) {
                address = null;
            } else {
                address = (Address) list.get(0);
            }
            if (address != null) {
                this.latitude = address.getLatitude();
                this.longitude = address.getLongitude();
                if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
                    double[] convertGCJtoWGS = LocationCoordinate.convertGCJtoWGS(this.latitude, this.longitude);
                    this.latitude = convertGCJtoWGS[0];
                    this.longitude = convertGCJtoWGS[1];
                }
                AddressCompat build = build(address);
                String str = this.TAG;
                Log.d(str, "loadLocation(GK)#" + this.includeText + Logger.vt(Logger.getEncodedString(build.addressText), Long.valueOf(j2)) + "");
                notifyCallback(build);
            }
        }

        public AddressCompat build(Address address) {
            if (address != null) {
                String addressText = includeGeneric() ? AddressCompat.toAddressText(address) : null;
                String simpleAddressText = includeSimple() ? AddressCompat.toSimpleAddressText(address) : null;
                String plainAddressText = includePlain() ? AddressCompat.toPlainAddressText(address) : null;
                if (!(addressText == null && simpleAddressText == null && plainAddressText == null)) {
                    return AddressCompat.of(this.latitude, this.longitude, addressText, simpleAddressText, plainAddressText);
                }
            }
            return null;
        }

        public AddressCompat getFromCache() {
            Address cache = AddressCompat.getCache(this.latitude, this.longitude);
            if (cache != null) {
                return build(cache);
            }
            return null;
        }

        public AddressCompat getFromDB() {
            long j2;
            long currentTimeMillis = System.currentTimeMillis();
            double d = this.latitude;
            double d2 = this.longitude;
            FileItemInterface fileItemInterface = this.item;
            if (fileItemInterface != null) {
                j2 = fileItemInterface.getFileId();
            } else {
                j2 = 0;
            }
            Address queryAddressFromSecMp = AddressCompat.queryAddressFromSecMp(d, d2, j2);
            String str = null;
            if (queryAddressFromSecMp == null) {
                return null;
            }
            AddressCompat build = build(queryAddressFromSecMp);
            String str2 = this.TAG;
            StringBuilder sb2 = new StringBuilder("loadLocation(D)#");
            sb2.append(this.includeText);
            if (build != null) {
                str = Logger.getEncodedString(build.addressText);
            }
            sb2.append(Logger.vt(str, Long.valueOf(currentTimeMillis)));
            sb2.append("");
            Log.d(str2, sb2.toString());
            return build;
        }

        public AddressCompat getFromItem() {
            FileItemInterface fileItemInterface;
            String str;
            if (!USE_ITEM_ADDRESS || (fileItemInterface = this.item) == null || (str = DetailsData.of(fileItemInterface).addressInDB) == null) {
                return null;
            }
            return build(str);
        }

        public void loadFromGeocoder() {
            String str;
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                new Geocoder(AppResources.getAppContext(), Locale.getDefault()).getFromLocation(this.latitude, this.longitude, 1, new Geocoder$GeocodeListener() {
                    public void onError(String str) {
                        String str2;
                        Log.w(LocationTask33.this.TAG, "loadLocation(G)#" + LocationTask33.this.includeText + " onError from geocoder : " + str);
                        if (LocationTask33.this.includeGps()) {
                            str2 = LocationTask33.this.latitude + ArcCommonLog.TAG_COMMA + LocationTask33.this.longitude;
                        } else {
                            str2 = null;
                        }
                        String str3 = str2;
                        LocationTask33 locationTask33 = LocationTask33.this;
                        locationTask33.notifyCallback(AddressCompat.of(locationTask33.latitude, locationTask33.longitude, str3, (String) null, (String) null));
                    }

                    public void onGeocode(List<Address> list) {
                        Address address;
                        String str = null;
                        if (list.isEmpty()) {
                            address = null;
                        } else {
                            address = list.get(0);
                        }
                        if (address != null) {
                            LocationTask33 locationTask33 = LocationTask33.this;
                            AddressCompat.putCache(locationTask33.latitude, locationTask33.longitude, address);
                            AddressCompat build = LocationTask33.this.build(address);
                            Log.w(LocationTask33.this.TAG, "loadLocation(G)#" + LocationTask33.this.includeText + Logger.vt(Logger.getEncodedString(build.addressText), Long.valueOf(currentTimeMillis)) + "");
                            LocationTask33.this.notifyCallback(build);
                            return;
                        }
                        Log.w(LocationTask33.this.TAG, "loadLocation(G)#" + LocationTask33.this.includeText + " failed from geocoder");
                        if (LocationTask33.this.includeGps()) {
                            str = LocationTask33.this.latitude + ArcCommonLog.TAG_COMMA + LocationTask33.this.longitude;
                        }
                        LocationTask33 locationTask332 = LocationTask33.this;
                        locationTask332.notifyCallback(AddressCompat.of(locationTask332.latitude, locationTask332.longitude, str, (String) null, (String) null));
                    }
                });
            } catch (Exception e) {
                Log.w(this.TAG, "loadLocation(G)#" + this.includeText + " Exception : " + e);
                if (includeGps()) {
                    str = this.latitude + ArcCommonLog.TAG_COMMA + this.longitude;
                } else {
                    str = null;
                }
                notifyCallback(AddressCompat.of(this.latitude, this.longitude, str, (String) null, (String) null));
            }
        }

        public void notifyCallback(AddressCompat addressCompat) {
            ThreadUtil.runOnUiThread(new c(this, addressCompat));
        }

        public void start() {
            AddressCompat fromItem = getFromItem();
            if (fromItem == null) {
                fromItem = getFromCache();
            }
            if (fromItem != null) {
                notifyCallback(fromItem);
            } else {
                SimpleThreadPool.getInstance().execute(new b(this));
            }
        }

        public AddressCompat build(String str) {
            if (str != null) {
                String parseAddressText = includeGeneric() ? AddressCompat.parseAddressText(str) : null;
                String parseSimpleAddressText = includeSimple() ? AddressCompat.parseSimpleAddressText(str) : null;
                String parsePlainAddressText = includePlain() ? AddressCompat.parsePlainAddressText(str) : null;
                if (!(parseAddressText == null && parseSimpleAddressText == null && parsePlainAddressText == null)) {
                    return AddressCompat.of(this.latitude, this.longitude, parseAddressText, parseSimpleAddressText, parsePlainAddressText);
                }
            }
            return null;
        }

        public void start(String str) {
            new Geocoder(AppResources.getAppContext(), Locale.getDefault()).getFromLocationName(str, 1, new d(this, System.currentTimeMillis()));
        }
    }

    public void loadLocation(String str, Consumer<AddressCompat> consumer) {
        taskOf(MapUtil.INVALID_LOCATION, MapUtil.INVALID_LOCATION, (FileItemInterface) null, consumer).include(6).start(str);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LocationTask {
        protected final String TAG = getClass().getSimpleName();
        protected final Consumer<AddressCompat> callback;
        protected int includeText = 3;
        protected final FileItemInterface item;
        protected double latitude;
        protected double longitude;

        public LocationTask(double d, double d2, FileItemInterface fileItemInterface, Consumer<AddressCompat> consumer) {
            this.latitude = d;
            this.longitude = d2;
            this.item = fileItemInterface;
            this.callback = consumer;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$start$0(Object[] objArr) {
            if (objArr != null && objArr.length > 0) {
                this.callback.accept(AddressCompat.of(this.latitude, this.longitude, objArr[0], (String) null));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$start$3(Object[] objArr) {
            String str;
            if (objArr != null && objArr.length > 0) {
                Consumer<AddressCompat> consumer = this.callback;
                double doubleValue = objArr[0].doubleValue();
                double doubleValue2 = objArr[1].doubleValue();
                String str2 = null;
                if (objArr.length > 3) {
                    str = objArr[3];
                } else {
                    str = null;
                }
                if (objArr.length > 2) {
                    str2 = objArr[2];
                }
                consumer.accept(AddressCompat.of(doubleValue, doubleValue2, str, str2));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$startFull$2(Object[] objArr) {
            String str;
            if (objArr != null && objArr.length > 0) {
                Consumer<AddressCompat> consumer = this.callback;
                double d = this.latitude;
                double d2 = this.longitude;
                String str2 = null;
                if (objArr.length > 3) {
                    str = objArr[3];
                } else {
                    str = null;
                }
                if (objArr.length > 2) {
                    str2 = objArr[2];
                }
                consumer.accept(AddressCompat.of(d, d2, str, str2));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$startSimple$1(Object[] objArr) {
            if (objArr != null && objArr.length > 0) {
                this.callback.accept(AddressCompat.of(this.latitude, this.longitude, (String) null, objArr[0]));
            }
        }

        private void startFull() {
            AddressHelper.GetAddressListFromLocation getAddressListFromLocation = new AddressHelper.GetAddressListFromLocation(AppResources.getAppContext());
            getAddressListFromLocation.setUpdateListener(new a(this, 1));
            getAddressListFromLocation.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Object[]{Double.valueOf(this.latitude), Double.valueOf(this.longitude)});
        }

        private void startSimple() {
            AddressHelper.GetSimpleAddress getSimpleAddress = new AddressHelper.GetSimpleAddress(AppResources.getAppContext());
            getSimpleAddress.setUpdateListener(new a(this, 2));
            getSimpleAddress.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[]{Double.valueOf(this.latitude), Double.valueOf(this.longitude)});
        }

        public final LocationTask include(int i2) {
            this.includeText = i2;
            return this;
        }

        public final boolean includeGeneric() {
            if ((this.includeText & 2) > 0) {
                return true;
            }
            return false;
        }

        public final boolean includeGps() {
            if ((this.includeText & 1) > 0) {
                return true;
            }
            return false;
        }

        public final boolean includePlain() {
            if ((this.includeText & 8) > 0) {
                return true;
            }
            return false;
        }

        public final boolean includeSimple() {
            if ((this.includeText & 4) > 0) {
                return true;
            }
            return false;
        }

        public void start() {
            int i2 = this.includeText;
            if (i2 == 4) {
                startSimple();
            } else if (i2 == 6) {
                startFull();
            } else {
                AddressHelper.GetNormalAddress getNormalAddress = new AddressHelper.GetNormalAddress(AppResources.getAppContext());
                getNormalAddress.setUpdateListener(new a(this, 0));
                getNormalAddress.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Object[]{Double.valueOf(this.latitude), Double.valueOf(this.longitude), this.item});
            }
        }

        public void start(String str) {
            AddressHelper.GetAddressListFromKeyword getAddressListFromKeyword = new AddressHelper.GetAddressListFromKeyword(AppResources.getAppContext());
            getAddressListFromKeyword.setUpdateListener(new a(this, 3));
            getAddressListFromKeyword.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Object[]{str});
        }
    }

    public JSONObject buildJsonObject(FileItemInterface fileItemInterface, String str) {
        if (fileItemInterface == null) {
            return null;
        }
        return buildJsonObject(fileItemInterface.getLatitude(), fileItemInterface.getLongitude(), (Double) null, str, Long.valueOf(fileItemInterface.getFileId()), fileItemInterface.getPath());
    }
}
