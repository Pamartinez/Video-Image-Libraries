package com.samsung.android.gallery.module.map.manager;

import A.a;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ExifTag;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LocationCoordinate;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MathUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AddressHelper {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GetAddressListFromKeyword extends GetAddressTask {
        public GetAddressListFromKeyword(Context context) {
            super(context);
        }

        public Object[] doInBackground(Object... objArr) {
            Address findAddress = AddressHelper.findAddress(objArr[0]);
            double[] dArr = {0.0d, 0.0d};
            if (findAddress == null) {
                return null;
            }
            if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
                dArr = LocationCoordinate.convertGCJtoWGS(findAddress.getLatitude(), findAddress.getLongitude());
            } else {
                dArr[0] = findAddress.getLatitude();
                dArr[1] = findAddress.getLongitude();
            }
            Address findAddress2 = AddressHelper.findAddress(dArr[0], dArr[1]);
            if (findAddress2 != null) {
                return new Object[]{Double.valueOf(dArr[0]), Double.valueOf(dArr[1]), AddressHelper.toSimpleAddressString(findAddress2), AddressHelper.normalAddressText(findAddress2)};
            }
            return new Object[]{Double.valueOf(dArr[0]), Double.valueOf(dArr[1])};
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GetAddressListFromLocation extends GetAddressTask {
        public GetAddressListFromLocation(Context context) {
            super(context);
        }

        public Object[] doInBackground(Object... objArr) {
            Double d = objArr[0];
            double doubleValue = d.doubleValue();
            Double d2 = objArr[1];
            Address findAddress = AddressHelper.findAddress(doubleValue, d2.doubleValue());
            if (findAddress != null) {
                return new Object[]{d, d2, AddressHelper.toSimpleAddressString(findAddress), AddressHelper.normalAddressText(findAddress)};
            }
            return new Object[]{d, d2};
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class GetAddressTask extends AsyncTask<Object, Void, Object[]> {
        OnAddressUpdateListener mUpdateListener;

        public GetAddressTask(Context context) {
        }

        public void setUpdateListener(OnAddressUpdateListener onAddressUpdateListener) {
            this.mUpdateListener = onAddressUpdateListener;
        }

        public void onPostExecute(Object[] objArr) {
            OnAddressUpdateListener onAddressUpdateListener = this.mUpdateListener;
            if (onAddressUpdateListener != null) {
                onAddressUpdateListener.onAddressUpdate(objArr);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GetFormattedAddress extends GetAddressTask {
        public GetFormattedAddress(Context context) {
            super(context);
        }

        public Object[] doInBackground(Object... objArr) {
            Address findAddress = AddressHelper.findAddress(objArr[0].doubleValue(), objArr[1].doubleValue());
            if (findAddress != null) {
                return new Object[]{AddressHelper.toFormattedAddressString(findAddress)};
            }
            Log.w("AddressHelper", "GetFormattedAddress failed");
            return null;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GetNormalAddress extends GetAddressTask {
        public GetNormalAddress(Context context) {
            super(context);
        }

        private static boolean matchPosition(double d, double d2, ExifTag exifTag) {
            double[] dArr;
            if (!(exifTag == null || (dArr = exifTag.location) == null)) {
                if (MathUtils.equals(d, dArr[0], 1.0E-5d)) {
                    if (MathUtils.equals(d2, exifTag.location[1], 1.0E-5d)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public String getAddress(FileItemInterface fileItemInterface, double d, double d2) {
            if (fileItemInterface == null) {
                return null;
            }
            ExifTag exifTag = fileItemInterface.getExifTag();
            if (exifTag == null) {
                exifTag = MetadataManager.getInstance().loadExif(fileItemInterface);
            }
            if (matchPosition(d, d2, exifTag)) {
                return AddressCompat.parseAddressText(DetailsData.of(fileItemInterface).addressInDB);
            }
            return null;
        }

        public Object[] doInBackground(Object... objArr) {
            String str;
            Double d = objArr[0];
            Double d2 = objArr[1];
            String str2 = null;
            if (PreferenceFeatures.OneUi5x.SUPPORT_ADDRESS_FROM_DATABASE && objArr.length > 2 && (objArr[2] instanceof FileItemInterface)) {
                if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
                    Address queryAddressFromLocationTable = AddressHelper.queryAddressFromLocationTable(objArr[2], d.doubleValue(), d2.doubleValue());
                    str = queryAddressFromLocationTable != null ? AddressHelper.toNormalAddressStringChina(queryAddressFromLocationTable, new StringBuilder(256), ArcCommonLog.TAG_COMMA) : null;
                } else {
                    str = getAddress(objArr[2], d.doubleValue(), d2.doubleValue());
                }
                if (!TextUtils.isEmpty(str)) {
                    Log.d("AddressHelper", "GetNormalAddress(DB)");
                    return new Object[]{str};
                }
            }
            Address findAddress = AddressHelper.findAddress(d.doubleValue(), d2.doubleValue());
            if (findAddress != null) {
                str2 = AddressHelper.normalAddressText(findAddress);
            }
            if (!TextUtils.isEmpty(str2)) {
                return new Object[]{str2};
            }
            Log.w("AddressHelper", "GetNormalAddress failed");
            return new Object[]{AddressHelper.getLatLongString(d, d2)};
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GetSimpleAddress extends GetAddressTask {
        public GetSimpleAddress(Context context) {
            super(context);
        }

        public Object[] doInBackground(Object... objArr) {
            Address findAddress = AddressHelper.findAddress(objArr[0].doubleValue(), objArr[1].doubleValue());
            if (findAddress != null) {
                return new Object[]{AddressHelper.toSimpleAddressString(findAddress)};
            }
            Log.w("AddressHelper", "GetSimpleAddress failed");
            return null;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnAddressUpdateListener {
        void onAddressUpdate(Object[] objArr);
    }

    public static Address findAddress(String str) {
        Address address;
        Context appContext = AppResources.getAppContext();
        if (appContext != null) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                List<Address> fromLocationName = new Geocoder(appContext, Locale.getDefault()).getFromLocationName(str, 1);
                if (fromLocationName != null) {
                    if (fromLocationName.size() != 0) {
                        address = fromLocationName.get(0);
                        Log.d("AddressHelper", "fineAddress#Geocoder" + Logger.vt(currentTimeMillis));
                        return address;
                    }
                }
                address = null;
                Log.d("AddressHelper", "fineAddress#Geocoder" + Logger.vt(currentTimeMillis));
                return address;
            } catch (IOException | IllegalArgumentException e) {
                a.s(e, new StringBuilder("findAddress#Geocoder failed e="), "AddressHelper");
            }
        }
        return null;
    }

    public static String getLatLongString(Double d, Double d2) {
        return d + ArcCommonLog.TAG_COMMA + d2;
    }

    /* access modifiers changed from: private */
    public static String normalAddressText(Address address) {
        return AddressCompat.toAddressText(address);
    }

    public static Address queryAddressFromLocationTable(FileItemInterface fileItemInterface, double d, double d2) {
        if (fileItemInterface == null) {
            return null;
        }
        return AddressCompat.queryAddressFromSecMp(d, d2, fileItemInterface.getFileId());
    }

    /* access modifiers changed from: private */
    public static String toFormattedAddressString(Address address) {
        return AddressCompat.toFormattedAddressText(address);
    }

    /* access modifiers changed from: private */
    public static String toNormalAddressStringChina(Address address, StringBuilder sb2, String str) {
        return AddressCompat.toAddressTextChn(address);
    }

    /* access modifiers changed from: private */
    public static String toSimpleAddressString(Address address) {
        return AddressCompat.toSimpleAddressText(address);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0042 A[Catch:{ IOException | IllegalArgumentException -> 0x003c }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004c A[Catch:{ IOException | IllegalArgumentException -> 0x003c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.location.Address findAddress(double r12, double r14) {
        /*
            java.lang.String r0 = "findAddress#Geocoder"
            android.location.Address r1 = com.samsung.android.gallery.module.map.manager.AddressCompat.getCache(r12, r14)
            java.lang.String r2 = "AddressHelper"
            if (r1 == 0) goto L_0x0010
            java.lang.String r12 = "findAddress#Cache"
            com.samsung.android.gallery.support.utils.Log.d(r2, r12)
            return r1
        L_0x0010:
            android.content.Context r1 = com.samsung.android.gallery.support.utils.AppResources.getAppContext()
            r3 = 0
            if (r1 == 0) goto L_0x0072
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            android.location.Geocoder r6 = new android.location.Geocoder     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            java.util.Locale r7 = java.util.Locale.getDefault()     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            r6.<init>(r1, r7)     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            r11 = 1
            r7 = r12
            r9 = r14
            java.util.List r12 = r6.getFromLocation(r7, r9, r11)     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            r13 = 0
            if (r12 == 0) goto L_0x003f
            int r14 = r12.size()     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            if (r14 != 0) goto L_0x0035
            goto L_0x003f
        L_0x0035:
            java.lang.Object r12 = r12.get(r13)     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            android.location.Address r12 = (android.location.Address) r12     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            goto L_0x0040
        L_0x003c:
            r0 = move-exception
            r12 = r0
            goto L_0x0068
        L_0x003f:
            r12 = r3
        L_0x0040:
            if (r12 == 0) goto L_0x0045
            com.samsung.android.gallery.module.map.manager.AddressCompat.putCache(r7, r9, r12)     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
        L_0x0045:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            r14.<init>(r0)     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            if (r12 == 0) goto L_0x004d
            r13 = 1
        L_0x004d:
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r13)     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            java.lang.Long r15 = java.lang.Long.valueOf(r4)     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            java.lang.Object[] r13 = new java.lang.Object[]{r13, r15}     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            java.lang.String r13 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r13)     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            r14.append(r13)     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            java.lang.String r13 = r14.toString()     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            com.samsung.android.gallery.support.utils.Log.d(r2, r13)     // Catch:{ IOException | IllegalArgumentException -> 0x003c }
            return r12
        L_0x0068:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r14 = "findAddress#Geocoder failed e="
            r13.<init>(r14)
            A.a.s(r12, r13, r2)
        L_0x0072:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.map.manager.AddressHelper.findAddress(double, double):android.location.Address");
    }
}
