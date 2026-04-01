package com.adobe.internal.xmp;

import com.adobe.internal.xmp.impl.Base64;
import com.adobe.internal.xmp.impl.ISO8601Converter;
import com.adobe.internal.xmp.impl.XMPUtilsImpl;
import com.adobe.internal.xmp.options.PropertyOptions;
import com.adobe.internal.xmp.options.TemplateOptions;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class XMPUtils {
    private XMPUtils() {
    }

    public static void appendProperties(XMPMeta xMPMeta, XMPMeta xMPMeta2, boolean z, boolean z3) {
        appendProperties(xMPMeta, xMPMeta2, z, z3, false);
    }

    public static void applyTemplate(XMPMeta xMPMeta, XMPMeta xMPMeta2, TemplateOptions templateOptions) {
        XMPUtilsImpl.applyTemplate(xMPMeta, xMPMeta2, templateOptions);
    }

    public static String catenateArrayItems(XMPMeta xMPMeta, String str, String str2, String str3, String str4, boolean z) {
        return XMPUtilsImpl.catenateArrayItems(xMPMeta, str, str2, str3, str4, z);
    }

    public static String convertFromBoolean(boolean z) {
        if (z) {
            return XMPConst.TRUESTR;
        }
        return XMPConst.FALSESTR;
    }

    public static String convertFromDate(XMPDateTime xMPDateTime) {
        return ISO8601Converter.render(xMPDateTime);
    }

    public static String convertFromDouble(double d) {
        return String.valueOf(d);
    }

    public static String convertFromInteger(int i2) {
        return String.valueOf(i2);
    }

    public static String convertFromLong(long j2) {
        return String.valueOf(j2);
    }

    public static boolean convertToBoolean(String str) {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty convert-string", 5);
        }
        String lowerCase = str.toLowerCase();
        try {
            if (Integer.parseInt(lowerCase) != 0) {
                return true;
            }
            return false;
        } catch (NumberFormatException unused) {
            if (SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(lowerCase) || "t".equals(lowerCase) || "on".equals(lowerCase) || "yes".equals(lowerCase)) {
                return true;
            }
            return false;
        }
    }

    public static XMPDateTime convertToDate(String str) {
        if (str != null && str.length() != 0) {
            return ISO8601Converter.parse(str);
        }
        throw new XMPException("Empty convert-string", 5);
    }

    public static double convertToDouble(String str) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    return Double.parseDouble(str);
                }
            } catch (NumberFormatException unused) {
                throw new XMPException("Invalid double string", 5);
            }
        }
        throw new XMPException("Empty convert-string", 5);
    }

    public static int convertToInteger(String str) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    if (str.startsWith("0x")) {
                        return Integer.parseInt(str.substring(2), 16);
                    }
                    return Integer.parseInt(str);
                }
            } catch (NumberFormatException unused) {
                throw new XMPException("Invalid integer string", 5);
            }
        }
        throw new XMPException("Empty convert-string", 5);
    }

    public static long convertToLong(String str) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    if (str.startsWith("0x")) {
                        return Long.parseLong(str.substring(2), 16);
                    }
                    return Long.parseLong(str);
                }
            } catch (NumberFormatException unused) {
                throw new XMPException("Invalid long string", 5);
            }
        }
        throw new XMPException("Empty convert-string", 5);
    }

    public static byte[] decodeBase64(String str) {
        try {
            return Base64.decode(str.getBytes());
        } catch (Throwable th) {
            throw new XMPException("Invalid base64 string", 5, th);
        }
    }

    public static void duplicateSubtree(XMPMeta xMPMeta, XMPMeta xMPMeta2, String str, String str2, String str3, String str4, PropertyOptions propertyOptions) {
        XMPUtilsImpl.duplicateSubtree(xMPMeta, xMPMeta2, str, str2, str3, str4, propertyOptions);
    }

    public static String encodeBase64(byte[] bArr) {
        return new String(Base64.encode(bArr));
    }

    public static void mergeFromJPEG(XMPMeta xMPMeta, XMPMeta xMPMeta2) {
        XMPUtilsImpl.mergeFromJPEG(xMPMeta, xMPMeta2);
    }

    public static void packageForJPEG(XMPMeta xMPMeta, StringBuilder sb2, StringBuilder sb3, StringBuilder sb4) {
        XMPUtilsImpl.packageForJPEG(xMPMeta, sb2, sb3, sb4);
    }

    public static void removeProperties(XMPMeta xMPMeta, String str, String str2, boolean z, boolean z3) {
        XMPUtilsImpl.removeProperties(xMPMeta, str, str2, z, z3);
    }

    public static void separateArrayItems(XMPMeta xMPMeta, String str, String str2, String str3, PropertyOptions propertyOptions, boolean z) {
        XMPUtilsImpl.separateArrayItems(xMPMeta, str, str2, str3, propertyOptions, z);
    }

    public static void appendProperties(XMPMeta xMPMeta, XMPMeta xMPMeta2, boolean z, boolean z3, boolean z7) {
        XMPUtilsImpl.appendProperties(xMPMeta, xMPMeta2, z, z3, z7);
    }
}
