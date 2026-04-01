package com.samsung.android.gallery.module.store;

import A.a;
import N2.j;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.samsung.android.gallery.compat.account.SamsungAccountInfoLoader;
import com.samsung.android.gallery.compat.oaid.SamsungDeviceId;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.scsp.framework.core.identity.IdentityApiContract;
import com.samsung.scsp.framework.core.network.HeaderSetup;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MarketHelper {
    static final byte[] CHECK_API = {125, 85, -85, -81, 102, 27, -16, -16, 99, 64, -84, -15, 102, 64, -78, -84, 96, 79, -72, -66, 101, 81, -84, -15, 118, 78, -78, -16, 102, 85, -86, -67, 58, 82, -85, -86, 119, 116, -81, -69, 116, 85, -70, -100, 125, 68, -68, -76, 59, 64, -84, 94};
    private static final String[] sResponseFields = {"appId", "versionCode", "versionName", "productId", "productName", OCRServiceConstant.KEY_RESULT_CODE, "resultMsg", "contentSize"};
    private final Bundle mBundle = new Bundle();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MarketUriBuilder {
        private String mCountryCode;
        private String mSecondaryUniqueId;
        private String mSimOperator;

        public MarketUriBuilder(Context context) {
            String str;
            System.currentTimeMillis();
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getSimOperator();
                } else {
                    str = "";
                }
                this.mSimOperator = str;
            } catch (Exception e) {
                a.s(e, new StringBuilder("MarketUriBuilder failed e="), "MarketHelper");
            }
            loadInfoFromService();
        }

        private String getAbiType() {
            if (Build.SUPPORTED_64_BIT_ABIS.length > 0) {
                return "64";
            }
            if (Build.SUPPORTED_32_BIT_ABIS.length > 0) {
                return "32";
            }
            return "ex";
        }

        private String getAndroidId() {
            try {
                return Settings.Secure.getString(AppResources.getAppContext().getContentResolver(), "android_id");
            } catch (Exception e) {
                a.s(e, new StringBuilder("getAndroidId failed. e="), "MarketHelper");
                return null;
            }
        }

        private String getCsc() {
            String systemPropertiesString = SeApiCompat.getSystemPropertiesString("ro.csc.sales_code", "NONE");
            if (TextUtils.isEmpty(systemPropertiesString)) {
                return "NONE";
            }
            return systemPropertiesString;
        }

        private String getDeviceId() {
            return Build.MODEL.replaceFirst("SAMSUNG-", "");
        }

        private String getMcc() {
            String str = this.mSimOperator;
            if (str == null || str.length() <= 3) {
                return "";
            }
            return this.mSimOperator.substring(0, 3);
        }

        private String getMnc() {
            String str = this.mSimOperator;
            if (str == null || str.length() <= 3) {
                return "";
            }
            return this.mSimOperator.substring(3);
        }

        private String getPd() {
            if (FileUtils.exists(Environment.getExternalStorageDirectory().getPath() + File.separator + "go_to_andromeda.test")) {
                return "1";
            }
            return "0";
        }

        private String getSdkVer() {
            return String.valueOf(Build.VERSION.SDK_INT);
        }

        private String getSystemId() {
            return Long.toString(System.currentTimeMillis() - SystemClock.elapsedRealtime());
        }

        private void loadInfoFromService() {
            ThreadUtil.assertBgThread("loadInfoFromService should be on worker thread");
            this.mCountryCode = new SamsungAccountInfoLoader().loadCountryCode(AppResources.getAppContext());
            if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
                this.mSecondaryUniqueId = new SamsungDeviceId().loadValue(AppResources.getAppContext());
            }
            if (this.mSecondaryUniqueId == null) {
                this.mSecondaryUniqueId = getAndroidId();
            }
        }

        public String build() {
            Uri.Builder appendQueryParameter = Uri.parse(Logger.decrypt(MarketHelper.CHECK_API)).buildUpon().appendQueryParameter("appId", "com.sec.android.gallery3d").appendQueryParameter("callerId", "com.sec.android.gallery3d").appendQueryParameter("versionCode", PackageMonitorCompat.getVersionCode()).appendQueryParameter("deviceId", getDeviceId()).appendQueryParameter(HeaderSetup.Key.MCC, getMcc()).appendQueryParameter(HeaderSetup.Key.MNC, getMnc()).appendQueryParameter(IdentityApiContract.Parameter.CSC, getCsc()).appendQueryParameter("sdkVer", getSdkVer()).appendQueryParameter("systemId", getSystemId()).appendQueryParameter("abiType", getAbiType()).appendQueryParameter("updateType", "self").appendQueryParameter("pd", getPd());
            if (!TextUtils.isEmpty(this.mCountryCode)) {
                appendQueryParameter.appendQueryParameter("cc", this.mCountryCode);
            }
            if (!TextUtils.isEmpty(this.mSecondaryUniqueId)) {
                appendQueryParameter.appendQueryParameter("extuk", this.mSecondaryUniqueId);
            }
            return appendQueryParameter.toString();
        }
    }

    public static int getApkVersion() {
        return GalleryPreference.getInstanceCache().loadInt("market_version_code", 1);
    }

    public static String getApkVersionName() {
        return GalleryPreference.getInstanceCache().loadString("market_version_name", "");
    }

    public static int getCheckedVersion() {
        return GalleryPreference.getInstanceCache().loadInt("market_checked_version_code", 1);
    }

    public static long getLastCheckTime() {
        return GalleryPreference.getInstanceCache().loadLong("market_check_time", 0);
    }

    public static boolean isApkAvailable() {
        return GalleryPreference.getInstanceCache().loadBoolean("market_available", false);
    }

    public static boolean isUpgradeAvailable() {
        if (GalleryPreference.getInstanceCache().loadInt("market_check_result", -1) == 2) {
            return true;
        }
        return false;
    }

    public static boolean isUpgradeCheckRequired() {
        return GalleryPreference.getInstanceCache().loadBoolean("market_check_required", false);
    }

    public static Intent makeGalaxyAppsIntent() {
        Uri build = Uri.parse("samsungapps://ProductDetail/com.sec.android.gallery3d/").buildUpon().appendQueryParameter("source", "Gallery").appendQueryParameter("fsOrigin", "stubUpdateCheck").appendQueryParameter("fsUpdateType", "self").build();
        Intent intent = new Intent();
        intent.setData(build);
        intent.putExtra("type", "cover");
        intent.addFlags(335544352);
        return intent;
    }

    private void parseDocument(XmlPullParser xmlPullParser, Bundle bundle) {
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    parseTag(xmlPullParser, bundle);
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException | XmlPullParserException e) {
            a.s(e, new StringBuilder("parseDocument e="), "MarketHelper");
        }
    }

    private void parseTag(XmlPullParser xmlPullParser, Bundle bundle) {
        String name = xmlPullParser.getName();
        try {
            String[] strArr = sResponseFields;
            int length = strArr.length;
            int i2 = 0;
            while (i2 < length) {
                if (!strArr[i2].equalsIgnoreCase(name)) {
                    i2++;
                } else if (xmlPullParser.next() == 4) {
                    bundle.putString(name, xmlPullParser.getText());
                    return;
                } else {
                    return;
                }
            }
        } catch (IOException | XmlPullParserException e) {
            a.s(e, j.k("parseTag(", name, ") e="), "MarketHelper");
        }
    }

    public static void setApkAvailable(boolean z) {
        GalleryPreference.getInstanceCache().saveState("market_available", z);
    }

    public static void setApkVersionInfo(int i2, String str) {
        GalleryPreference.getInstanceCache().saveState("market_version_code", i2);
        if (str != null) {
            GalleryPreference.getInstanceCache().saveState("market_version_name", str);
        }
    }

    public static void setCheckedVersion(int i2) {
        GalleryPreference.getInstanceCache().saveState("market_checked_version_code", i2);
    }

    public static void setLastCheckTime(long j2) {
        GalleryPreference.getInstanceCache().saveState("market_check_time", j2);
    }

    public static void setUpgradeCheckRequired(boolean z) {
        GalleryPreference.getInstanceCache().saveState("market_check_required", z);
    }

    public static void setUpgradeState(int i2) {
        GalleryPreference.getInstanceCache().saveState("market_check_result", i2);
    }

    public static void startGalaxyApps(Context context) {
        try {
            context.startActivity(makeGalaxyAppsIntent());
        } catch (ActivityNotFoundException | NullPointerException e) {
            Log.e("MarketHelper", "startGalaxyApps failed e=" + e.toString());
        }
    }

    public int getKnownResultCode(String str) {
        int i2 = UnsafeCast.toInt(str, 1000);
        if (i2 == 0 || i2 == 1 || i2 == 2 || i2 == 1000) {
            return i2;
        }
        return 3;
    }

    public int getResult(URL url, Bundle bundle) {
        InputStream inputStream;
        try {
            URLConnection openConnection = url.openConnection();
            openConnection.setConnectTimeout(30000);
            openConnection.setReadTimeout(30000);
            openConnection.connect();
            inputStream = openConnection.getInputStream();
            parseStream(inputStream, bundle);
            if (inputStream != null) {
                inputStream.close();
            }
            return getKnownResultCode(bundle.getString(OCRServiceConstant.KEY_RESULT_CODE, "1000"));
        } catch (SocketTimeoutException e) {
            Log.e("MarketHelper", "getResult failed(SocketTimeOutException) e=" + e.getMessage());
            return 5;
        } catch (IOException | IllegalArgumentException | XmlPullParserException e7) {
            Log.e("MarketHelper", "getResult failed(" + e7.getClass().getSimpleName() + ") e=" + e7.getMessage());
            return 3;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public String getURLSpec(Context context) {
        return new MarketUriBuilder(context.getApplicationContext()).build();
    }

    public int getUpgradeInfo(Context context) {
        ThreadUtil.assertBgThread("MarketHelper#getUpgradeInfo should run on background thread");
        try {
            return getResult(new URL(getURLSpec(context)), this.mBundle);
        } catch (Exception e) {
            Log.e("MarketHelper", e.toString());
            return 3;
        }
    }

    public String getVersionCode() {
        return this.mBundle.getString("versionCode", "0");
    }

    public String getVersionName() {
        return this.mBundle.getString("versionName", "0");
    }

    public void parseStream(InputStream inputStream, Bundle bundle) {
        XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
        newPullParser.setInput(inputStream, (String) null);
        parseDocument(newPullParser, bundle);
    }
}
