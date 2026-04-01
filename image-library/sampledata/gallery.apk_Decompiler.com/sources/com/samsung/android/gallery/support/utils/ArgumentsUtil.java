package com.samsung.android.gallery.support.utils;

import A.a;
import N2.j;
import android.os.Bundle;
import i.C0212a;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ArgumentsUtil {
    public static final Pattern LAST_INDEX_PATTERN = Pattern.compile("([/][\\d]+)+[$]");

    public static String appendArgs(String str, String str2) {
        return appendArgs(str, str2, "1");
    }

    public static String appendArgsIfEmpty(String str, String str2, String str3) {
        if (getArgValue(str, str2) == null) {
            return appendArgs(str, str2, str3);
        }
        return str;
    }

    public static String appendUriKey(String str, String str2, boolean z) {
        String[] split = str.split("\\?");
        if (split.length != 2 || !z) {
            return C0212a.p(new StringBuilder(), split[0], str2);
        }
        StringBuilder sb2 = new StringBuilder();
        j.z(sb2, split[0], str2, "?");
        sb2.append(split[1]);
        return sb2.toString();
    }

    private static String cleanUpSplitter(String str) {
        String replace = str.replace("&&", "&").replace("?&", "?");
        if (replace.endsWith("&")) {
            replace = C0280e.d(1, 0, replace);
        }
        if (replace.endsWith("?")) {
            return C0280e.d(1, 0, replace);
        }
        return replace;
    }

    public static String decode(String str) {
        try {
            return URLDecoder.decode(str, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException | NullPointerException e) {
            a.s(e, new StringBuilder("decode e="), "ArgumentsUtil");
            return str;
        }
    }

    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException | NullPointerException e) {
            a.s(e, new StringBuilder("encode e="), "ArgumentsUtil");
            return str;
        }
    }

    public static boolean equals(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return removeArgs(str).equals(removeArgs(str2));
    }

    public static boolean getArgValue(String str, String str2, boolean z) {
        return UnsafeCast.toBoolean(getArgValue(str, str2), z);
    }

    public static Bundle getArgs(String str) {
        Bundle bundle = new Bundle();
        String[] split = str.split("\\?");
        bundle.putString("_SUBSCRIBE_KEY", split[0]);
        if (split.length == 2) {
            for (String split2 : split[1].split("\\&")) {
                try {
                    String[] split3 = split2.split("=");
                    bundle.putString(split3[0], decode(split3[1]));
                } catch (ArrayIndexOutOfBoundsException unused) {
                    Log.d("ArgumentsUtil", "wrong args format : ".concat(str));
                }
            }
        }
        return bundle;
    }

    public static String getLastSegment(String str) {
        try {
            int lastIndexOf = str.lastIndexOf(47) + 1;
            int indexOf = str.indexOf(63);
            if (indexOf < 0) {
                return str.substring(lastIndexOf);
            }
            return str.substring(lastIndexOf, indexOf);
        } catch (Exception unused) {
            return str;
        }
    }

    public static String getSubscribeKey(Bundle bundle) {
        return bundle.getString("_SUBSCRIBE_KEY");
    }

    public static int lastIndexOfWildRegex(String str) {
        Pattern pattern = LAST_INDEX_PATTERN;
        Matcher matcher = pattern.matcher(str + "$");
        int i2 = -1;
        while (matcher.find()) {
            i2 = matcher.start();
        }
        if (i2 == -1) {
            return str.lastIndexOf(47);
        }
        return i2;
    }

    public static String removeArg(String str, String str2) {
        String[] split = str.split("\\?");
        if (split.length == 2) {
            for (String str3 : split[1].split("\\&")) {
                if (str2.equalsIgnoreCase(str3.split("=")[0])) {
                    return cleanUpSplitter(str.replace(str3, ""));
                }
            }
        }
        return str;
    }

    public static String removeArgs(String str) {
        return str.split("\\?")[0];
    }

    public static String appendArgs(String str, String str2, String str3) {
        if (str2 == null || str3 == null) {
            return str;
        }
        String encode = encode(str3);
        String[] split = str.split("\\?");
        String str4 = "?";
        if (split.length == 2) {
            String[] split2 = split[1].split("\\&");
            int length = split2.length;
            int i2 = 0;
            while (i2 < length) {
                String str5 = split2[i2];
                if (!str2.equalsIgnoreCase(str5.split("=")[0])) {
                    i2++;
                } else if (split2[0].equals(str5)) {
                    return str.replace(str4.concat(str5), C0212a.n(str4, str2, "=", encode));
                } else {
                    return str.replace("&".concat(str5), C0212a.n("&", str2, "=", encode));
                }
            }
            str4 = "&";
        }
        return str + str4 + str2 + "=" + encode;
    }

    public static int getArgValue(String str, String str2, int i2) {
        return UnsafeCast.toInt(getArgValue(str, str2), i2);
    }

    public static long getArgValue(String str, String str2, long j2) {
        return UnsafeCast.toLong(getArgValue(str, str2), j2);
    }

    public static double getArgValue(String str, String str2, double d) {
        return UnsafeCast.toDouble(getArgValue(str, str2), d);
    }

    public static String getArgValue(String str, String str2, String str3) {
        String argValue = getArgValue(str, str2);
        return argValue != null ? argValue : str3;
    }

    public static String getArgValue(String str, String str2) {
        if ("_SUBSCRIBE_KEY".equals(str) || str == null) {
            return str;
        }
        String[] split = str.split("\\?");
        if (split.length == 2) {
            String[] split2 = split[1].split("\\&");
            int length = split2.length;
            int i2 = 0;
            while (i2 < length) {
                try {
                    String[] split3 = split2[i2].split("=");
                    if (split3[0].equalsIgnoreCase(str2)) {
                        return decode(split3[1]);
                    }
                    i2++;
                } catch (ArrayIndexOutOfBoundsException unused) {
                    Log.d("ArgumentsUtil", "wrong args format : ".concat(str));
                }
            }
        }
        return null;
    }
}
