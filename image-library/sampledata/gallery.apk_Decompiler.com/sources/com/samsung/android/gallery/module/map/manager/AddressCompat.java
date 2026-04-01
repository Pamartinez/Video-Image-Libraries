package com.samsung.android.gallery.module.map.manager;

import A4.C0375j;
import A4.C0384t;
import A4.C0386v;
import C9.a;
import android.database.Cursor;
import android.location.Address;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.module.cache.AbsCacheMgr$EvictListener;
import com.samsung.android.gallery.module.cache.MemoryCacheMgr;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.AddressBundleWrapper;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddressCompat {
    public static final boolean SUPPORT_PLAIN_ADDRESS = (!PocFeatures.isEnabled(PocFeatures.FullAddressDisplay));
    public final String addressPlain;
    public final String addressSimple;
    public final String addressText;
    public final double latitude;
    public final double longitude;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AddressCache {
        static final AddressCache instance = new AddressCache(200);
        final MemoryCacheMgr<String, Address> cache;

        public AddressCache(int i2) {
            this.cache = new MemoryCacheMgr<>(i2, (AbsCacheMgr$EvictListener) null);
        }

        private String key(double d, double d2) {
            return Locale.getDefault() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + d + GlobalPostProcInternalPPInterface.SPLIT_REGEX + d2;
        }

        public Address get(double d, double d2) {
            return this.cache.getCache(key(d, d2));
        }

        public void put(double d, double d2, Address address) {
            this.cache.addCache(key(d, d2), address);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LocaleHolder {
        static final HashSet<String> japan = new HashSet<>(List.of("日本", "Japan", "japan", "일본"));
        static final HashSet<String> korea = new HashSet<>(List.of("대한민국", "한국", "大韓民國", "韓國"));
        static final HashSet<String> usa = new HashSet<>(List.of(new String[]{"United States", "US", "U.S", "U.S.", "USA", "U.S.A", "U.S.A.", "United States of America", "America", "미국", "美合衆國", "美國", "米國"}));

        public static boolean isKorea(String str) {
            if (str == null) {
                return false;
            }
            if (korea.contains(str) || str.toLowerCase().contains("korea")) {
                return true;
            }
            return false;
        }

        public static boolean isKorean(Locale locale) {
            return "ko".equalsIgnoreCase(locale.getLanguage());
        }

        public static boolean isUsa(String str) {
            if (str == null) {
                return false;
            }
            HashSet<String> hashSet = usa;
            if (hashSet.contains(str) || ((Boolean) hashSet.stream().map(new a(str, 0)).findFirst().orElse(Boolean.FALSE)).booleanValue()) {
                return true;
            }
            return false;
        }
    }

    public AddressCompat(double d, double d2, String str, String str2, String str3) {
        this.latitude = d;
        this.longitude = d2;
        this.addressText = str;
        this.addressSimple = str2;
        this.addressPlain = str3;
    }

    public static Address getCache(double d, double d2) {
        return AddressCache.instance.get(d, d2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$toAddressText$1(String str) {
        return !TextUtils.isEmpty(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$toAddressTextChn$0(String str) {
        return !TextUtils.isEmpty(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$toFormattedAddressText$2(String str) {
        if (TextUtils.isEmpty(str)) {
            return "null";
        }
        return str;
    }

    public static List<String> listOfAsc(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str) && !"null".equalsIgnoreCase(str) && !arrayList.contains(str)) {
                arrayList.add(0, str);
            }
        }
        return arrayList;
    }

    public static List<String> listOfDesc(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str) && !"null".equalsIgnoreCase(str) && !arrayList.contains(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static AddressCompat of(double d, double d2, String str, String str2, String str3) {
        return new AddressCompat(d, d2, str, str2, str3);
    }

    public static String parseAddressText(String str) {
        if (str != null) {
            String[] split = str.split("\\|");
            if (split.length > 3) {
                return split[split.length - 1];
            }
        }
        return null;
    }

    public static String parsePlainAddressText(String str) {
        String str2 = null;
        if (str != null) {
            String[] split = str.split("\\|");
            if (split.length > 3) {
                Locale locale = Locale.getDefault();
                String str3 = split[0];
                String str4 = split[1];
                String str5 = split[2];
                String str6 = split[3];
                if (split.length > 4) {
                    str2 = split[4];
                }
                return toPlainAddressText(locale, str3, str4, str5, str6, str2);
            }
        }
        return null;
    }

    public static String parseSimpleAddressText(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split("\\|");
        if (split.length <= 3 || TextUtils.isEmpty(split[0]) || TextUtils.isEmpty(split[3])) {
            return null;
        }
        return C0212a.B(split[3], ArcCommonLog.TAG_COMMA, split[0]);
    }

    public static void putCache(double d, double d2, Address address) {
        if (address != null) {
            AddressCache.instance.put(d, d2, address);
        }
    }

    public static Address queryAddressFromSecMp(double d, double d2, long j2) {
        String str;
        Cursor rawQuery;
        Address cache = getCache(d, d2);
        if (cache != null) {
            return cache;
        }
        Locale locale = Locale.getDefault();
        StringBuilder sb2 = new StringBuilder("select * from location_view where");
        if (j2 > 0) {
            str = A.a.e(j2, " sec_media_id=", " AND");
        } else {
            str = "";
        }
        sb2.append(str);
        sb2.append(" locale='");
        sb2.append(locale);
        sb2.append("' AND ABS(latitude - ");
        sb2.append(d);
        sb2.append(") < 0.00000000001 AND ABS(longitude - ");
        sb2.append(d2);
        sb2.append(") < 0.00000000001");
        try {
            rawQuery = new SecMpQueryExecutor().rawQuery(sb2.toString(), "queryAddressFromSecMp");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    Address address = new Address(locale);
                    address.setAddressLine(0, rawQuery.getString(rawQuery.getColumnIndex("address_text")));
                    address.setLatitude(rawQuery.getDouble(rawQuery.getColumnIndex("latitude")));
                    address.setLongitude(rawQuery.getDouble(rawQuery.getColumnIndex("longitude")));
                    address.setCountryName(rawQuery.getString(rawQuery.getColumnIndex("country_name")));
                    address.setCountryCode(rawQuery.getString(rawQuery.getColumnIndex("country_code")));
                    address.setLocality(rawQuery.getString(rawQuery.getColumnIndex(AddressBundleWrapper.BUNDLE_KEY_LOCALITY)));
                    address.setSubLocality(rawQuery.getString(rawQuery.getColumnIndex("sub_locality")));
                    address.setAdminArea(rawQuery.getString(rawQuery.getColumnIndex("admin_area")));
                    address.setThoroughfare(rawQuery.getString(rawQuery.getColumnIndex("street_name")));
                    address.setSubThoroughfare(rawQuery.getString(rawQuery.getColumnIndex("street_number")));
                    address.setPostalCode(rawQuery.getString(rawQuery.getColumnIndex("postal_code")));
                    address.setPremises(rawQuery.getString(rawQuery.getColumnIndex("premises")));
                    address.setSubAdminArea(rawQuery.getString(rawQuery.getColumnIndex("sub_admin_area")));
                    if (TextUtils.equals(address.getAdminArea(), address.getLocality())) {
                        address.setLocality((String) null);
                    }
                    if (TextUtils.equals(address.getFeatureName(), address.getSubThoroughfare())) {
                        address.setFeatureName((String) null);
                    }
                    address.setUrl("content://secmedia/location_view");
                    putCache(d, d2, address);
                    rawQuery.close();
                    return address;
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("queryAddressFromSecMp failed. e="), "AddressCompat");
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return null;
        throw th;
    }

    public static String toAddressText(Address address) {
        if (address == null) {
            return null;
        }
        if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
            return toAddressTextChn(address);
        }
        String str = (String) IntStream.range(0, address.getMaxAddressLineIndex() + 1).mapToObj(new C0386v(1, address)).filter(new C0375j(9)).collect(Collectors.joining(ArcCommonLog.TAG_COMMA));
        if (TextUtils.isEmpty(str)) {
            return address.getFeatureName();
        }
        return str;
    }

    public static String toAddressTextChn(Address address) {
        if (address != null) {
            return (String) Arrays.stream(new String[]{address.getAdminArea(), address.getLocality(), address.getSubLocality(), address.getThoroughfare(), address.getSubThoroughfare(), address.getPremises(), address.getPostalCode(), address.getCountryName()}).filter(new C0375j(10)).collect(Collectors.joining(ArcCommonLog.TAG_COMMA));
        }
        return null;
    }

    public static String toFormattedAddressText(Address address) {
        return (String) Arrays.stream(new String[]{address.getCountryName(), address.getAdminArea(), address.getSubAdminArea(), address.getLocality(), address.getSubLocality(), null, address.getThoroughfare(), null, null}).map(new C0384t(27)).collect(Collectors.joining("|"));
    }

    public static String toPlainAddressText(Address address) {
        if (address == null) {
            return null;
        }
        return toPlainAddressText(Locale.getDefault(), address.getCountryName(), address.getAdminArea(), address.getSubAdminArea(), address.getLocality(), address.getSubLocality());
    }

    public static String toSimpleAddressText(Address address) {
        if (address == null) {
            return "";
        }
        String locality = address.getLocality();
        String countryName = address.getCountryName();
        if (TextUtils.isEmpty(locality) || TextUtils.isEmpty(countryName)) {
            return "";
        }
        return C0212a.B(locality, ArcCommonLog.TAG_COMMA, countryName);
    }

    public String getAddress() {
        if (!SUPPORT_PLAIN_ADDRESS) {
            return this.addressText;
        }
        String str = this.addressPlain;
        if (str != null) {
            return str;
        }
        return this.addressText;
    }

    public boolean hasNoAddress() {
        if (!TextUtils.isEmpty(this.addressText) || !TextUtils.isEmpty(this.addressSimple) || !TextUtils.isEmpty(this.addressPlain)) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AddressCompat{");
        sb2.append(this.latitude);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.longitude);
        sb2.append(",[");
        sb2.append(this.addressSimple);
        sb2.append("],[");
        sb2.append(this.addressPlain);
        sb2.append("],[");
        return C0212a.p(sb2, this.addressText, "]}");
    }

    public static AddressCompat of(double d, double d2, String str, String str2) {
        return new AddressCompat(d, d2, str, str2, (String) null);
    }

    public static String toPlainAddressText(Locale locale, String str, String str2, String str3, String str4, String str5) {
        if (LocaleHolder.isUsa(str)) {
            List<String> listOfAsc = listOfAsc(str, str2, str4);
            if (!listOfAsc.isEmpty()) {
                return String.join(ArcCommonLog.TAG_COMMA, listOfAsc);
            }
            return null;
        } else if (!LocaleHolder.isKorea(str)) {
            return null;
        } else {
            if (LocaleHolder.isKorean(locale)) {
                List<String> listOfDesc = listOfDesc(str, str2, str3, str4, str5);
                if (!listOfDesc.isEmpty()) {
                    return String.join(" ", listOfDesc);
                }
                return null;
            }
            List<String> listOfAsc2 = listOfAsc(str, str2, str3, str4, str5);
            if (!listOfAsc2.isEmpty()) {
                return String.join(ArcCommonLog.TAG_COMMA, listOfAsc2);
            }
            return null;
        }
    }
}
