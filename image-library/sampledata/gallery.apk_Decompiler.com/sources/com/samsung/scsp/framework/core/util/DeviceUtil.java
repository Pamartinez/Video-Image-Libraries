package com.samsung.scsp.framework.core.util;

import A4.Y;
import D9.b;
import K.a;
import O3.o;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.identity.DeviceIdSupplier;
import java.util.Locale;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeviceUtil {
    private static final String[] CSC_FIELDS = {"ro.csc.sales_code", "persist.omc.sales_code", "ril.sales_code", "persist.audio.sales_code"};
    private static final String DEFAULT_CC = "KOR";
    private static final String DEFAULT_CC_ISO = "KO";
    private static final String DEFAULT_CSC = "";
    private static final String DEFAULT_MCC = "";
    private static final String DEFAULT_MNC = "";
    private static final boolean MU_ENABLED = true;
    private static final int PER_USER_RANGE = 100000;
    private static final int USER_SYSTEM = 0;
    public static Function<Context, String> androidId = new o(19);
    private static String deviceUniqueId = "";
    private static final Logger logger = Logger.get("DeviceUtil");

    public static String getAndroidDeviceName(Context context) {
        String str = (String) FaultBarrier.get(new b(context, 2), "", true).obj;
        if (StringUtil.isEmpty(str)) {
            return (String) FaultBarrier.get(new b(context, 3), "", true).obj;
        }
        return str;
    }

    public static String getAndroidId(Context context) {
        return androidId.apply(context);
    }

    public static String getCsc() {
        return (String) FaultBarrier.get(new a(25), "").obj;
    }

    public static String getDeviceName(Context context) {
        String str;
        if (isWatch(context)) {
            str = getWatchDeviceName();
        } else {
            str = getAndroidDeviceName(context);
        }
        if (TextUtils.isEmpty(str)) {
            return Build.MODEL;
        }
        return str;
    }

    public static String getDeviceUniqueId(Context context, DeviceIdSupplier deviceIdSupplier) {
        if (!StringUtil.isEmpty(deviceUniqueId)) {
            return deviceUniqueId;
        }
        if (deviceIdSupplier != null) {
            try {
                int phoneType = ((TelephonyManager) context.getSystemService("phone")).getPhoneType();
                if (isWatch(context) || phoneType == 0) {
                    deviceUniqueId = deviceIdSupplier.getSerial();
                } else {
                    deviceUniqueId = deviceIdSupplier.getImei();
                }
                if (!StringUtil.isEmpty(deviceUniqueId) && !deviceUniqueId.equals("0")) {
                    return deviceUniqueId;
                }
                throw new ScspException(ScspException.Code.RUNTIME_POLICY, "Runtime policy error. There is an exception while getting device id. {" + deviceUniqueId + "}");
            } catch (SecurityException unused) {
                throw new ScspException(ScspException.Code.NO_PERMISSION, "Runtime policy error. Permission is not granted.");
            }
        } else {
            throw new ScspException(ScspException.Code.RUNTIME_POLICY, "Runtime policy error. DeviceIdSupplier is null.");
        }
    }

    public static String getIso3CountryCode() {
        return (String) FaultBarrier.get(new a(26), DEFAULT_CC).obj;
    }

    private static String getMcc(String str) {
        if (str == null || str.isEmpty() || str.length() < 3) {
            return "";
        }
        return str.substring(0, 3);
    }

    private static String getMnc(String str) {
        if (str == null || str.isEmpty() || str.length() < 3) {
            return "";
        }
        return str.substring(3);
    }

    public static String getModelCode() {
        return (String) FaultBarrier.get(new a(22), "").obj;
    }

    public static String getModelName() {
        return Build.MODEL;
    }

    public static String getNetworkMcc(Context context) {
        logger.i("getNetworkMcc");
        return (String) FaultBarrier.get(new b(context, 4), "").obj;
    }

    public static String getNetworkMnc(Context context) {
        logger.i(" : getNetworkMnc()");
        return (String) FaultBarrier.get(new b(context, 6), "").obj;
    }

    public static String getOneUiVersion() {
        return (String) FaultBarrier.get(new a(27), "0.0.0").obj;
    }

    public static int getOneUiVersionValue() {
        return ((Integer) FaultBarrier.get(new a(24), 0).obj).intValue();
    }

    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static String getSimMcc(Context context) {
        logger.i("getSimMcc");
        return (String) FaultBarrier.get(new b(context, 1), "").obj;
    }

    public static String getSimMnc(Context context) {
        logger.i("getSimMnc");
        return (String) FaultBarrier.get(new b(context, 5), "").obj;
    }

    public static String getSystemProperties(String str, String str2) {
        return (String) FaultBarrier.get(new M8.a(str, 1), str2).obj;
    }

    public static int getUserId() {
        return Process.myUid() / 100000;
    }

    private static String getWatchDeviceName() {
        return (String) FaultBarrier.get(new a(23), "", true).obj;
    }

    public static boolean isTablet() {
        String systemProperties = getSystemProperties("ro.build.characteristics", "phone");
        if (systemProperties == null || !systemProperties.contains("tablet")) {
            return false;
        }
        return true;
    }

    public static boolean isVst(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.vst");
    }

    public static boolean isWatch(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getCsc$9() {
        for (String systemProperties : CSC_FIELDS) {
            String systemProperties2 = getSystemProperties(systemProperties, "");
            if (!StringUtil.isEmpty(systemProperties2)) {
                return systemProperties2;
            }
        }
        return "";
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getIso3CountryCode$8() {
        String iSO3Country = new Locale("", getSystemProperties("ro.csc.countryiso_code", DEFAULT_CC_ISO)).getISO3Country();
        if (iSO3Country.trim().length() == 3) {
            return iSO3Country;
        }
        return DEFAULT_CC;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getNetworkMcc$4(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        return getMcc(telephonyManager.getNetworkOperator());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getNetworkMnc$6(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        return getMnc(telephonyManager.getNetworkOperator());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getOneUiVersion$2() {
        int oneUiVersionValue = getOneUiVersionValue();
        String str = (oneUiVersionValue / 10000) + "." + ((oneUiVersionValue / 100) % 100) + "." + (oneUiVersionValue % 100);
        logger.i("One UI version : " + str);
        return str;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$getOneUiVersionValue$1() {
        int i2 = 0;
        int systemProperties = getSystemProperties("ro.build.version.oneui", 0);
        if (systemProperties <= 0) {
            systemProperties = getSystemProperties("ro.build.version.sep", 0) - 90000;
        }
        if (systemProperties >= 0) {
            i2 = systemProperties;
        }
        return Integer.valueOf(i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getSimMcc$5(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        return getMcc(telephonyManager.getSimOperator());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getSimMnc$7(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            return getMnc(telephonyManager.getSimOperator());
        }
        logger.e(" : getSimMnc() : The telephonyManager is null.");
        return "";
    }

    public static int getSystemProperties(String str, int i2) {
        return ((Integer) FaultBarrier.get(new Y((Object) str, i2, 4), Integer.valueOf(i2), true).obj).intValue();
    }
}
