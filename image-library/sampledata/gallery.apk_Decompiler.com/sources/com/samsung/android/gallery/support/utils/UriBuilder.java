package com.samsung.android.gallery.support.utils;

import N2.j;
import android.text.TextUtils;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UriBuilder {
    private final HashMap<String, String> mMap = new HashMap<>();
    private String mUri;

    public UriBuilder(String str) {
        if (str.contains("?")) {
            initBuilder(str);
        } else {
            this.mUri = str;
        }
    }

    private void initBuilder(String str) {
        String[] split = str.split("\\?");
        this.mUri = split[0];
        for (String split2 : split[1].split("\\&")) {
            try {
                String[] split3 = split2.split("=");
                this.mMap.put(split3[0], split3[1]);
            } catch (ArrayIndexOutOfBoundsException unused) {
                Log.d("UriBuilder", "wrong args format : ".concat(str));
            }
        }
    }

    public UriBuilder appendArg(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            this.mMap.put(str, ArgumentsUtil.encode(str2));
        }
        return this;
    }

    public UriBuilder appendUri(String str) {
        this.mUri = j.f(new StringBuilder(), this.mUri, "/", str);
        return this;
    }

    public String build() {
        StringBuilder sb2 = new StringBuilder(this.mUri);
        if (this.mMap.size() <= 0) {
            return sb2.toString();
        }
        sb2.append("?");
        for (Map.Entry next : this.mMap.entrySet()) {
            String str = (String) next.getValue();
            sb2.append((String) next.getKey());
            sb2.append("=");
            if (str == null) {
                str = "";
            }
            sb2.append(str);
            sb2.append("&");
        }
        return sb2.substring(0, sb2.length() - 1);
    }

    public UriBuilder removeArg(String str) {
        if (str != null) {
            this.mMap.remove(str);
        }
        return this;
    }

    public UriBuilder appendArg(String str, String[] strArr) {
        return appendArg(str, strArr, ";");
    }

    public UriBuilder appendArg(String str, String[] strArr, String str2) {
        StringBuilder sb2 = new StringBuilder();
        for (String append : strArr) {
            sb2.append(append);
            sb2.append(str2);
        }
        return appendArg(str, sb2.length() > 0 ? sb2.substring(0, sb2.length() - 1) : "");
    }

    public UriBuilder appendArg(String str, boolean z) {
        this.mMap.put(str, z ? SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE : SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
        return this;
    }

    public UriBuilder appendArg(String str, int i2) {
        this.mMap.put(str, Integer.toString(i2));
        return this;
    }

    public UriBuilder appendArg(String str, long j2) {
        this.mMap.put(str, Long.toString(j2));
        return this;
    }

    public UriBuilder appendArg(String str, double d) {
        this.mMap.put(str, Double.toString(d));
        return this;
    }
}
