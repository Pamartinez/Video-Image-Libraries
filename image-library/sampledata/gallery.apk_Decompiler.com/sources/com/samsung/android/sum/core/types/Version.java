package com.samsung.android.sum.core.types;

import C9.a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Version implements Serializable, Comparable<Version> {
    private static final int MAXNUM_VERSION_UNITS = 3;
    private static final String TAG = SLog.tagOf(Version.class);
    private static final long serialVersionUID = 1;
    private final int major;
    private final int minor;
    private final int patch;

    public Version(int i2, int i7, int i8) {
        this.major = i2;
        this.minor = i7;
        this.patch = i8;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$new$0(String str, String str2) {
        try {
            return Integer.valueOf(str2);
        } catch (Exception unused) {
            String str3 = TAG;
            SLog.w(str3, "fail to convert: " + str2 + ", version=" + str);
            return 0;
        }
    }

    public static Version of(String str) {
        return new Version(str);
    }

    public long getCode() {
        return (((long) this.minor) * 100) + (((long) this.major) * 10000) + ((long) this.patch);
    }

    public int getMajor() {
        return this.major;
    }

    public int getMinor() {
        return this.minor;
    }

    public int getPatch() {
        return this.patch;
    }

    public boolean isEqual(Version version) {
        if (compareTo(version) == 0) {
            return true;
        }
        return false;
    }

    public boolean isGreaterOrEqual(Version version) {
        if (compareTo(version) >= 0) {
            return true;
        }
        return false;
    }

    public boolean isGreaterThan(Version version) {
        if (compareTo(version) > 0) {
            return true;
        }
        return false;
    }

    public boolean isLessOrEqual(Version version) {
        if (compareTo(version) <= 0) {
            return true;
        }
        return false;
    }

    public boolean isLessThan(Version version) {
        if (compareTo(version) < 0) {
            return true;
        }
        return false;
    }

    public String toString() {
        return this.major + "." + this.minor + "." + this.patch;
    }

    public static Version of(int i2, int i7, int i8) {
        return new Version(i2, i7, i8);
    }

    public int compareTo(Version version) {
        return (int) (getCode() - version.getCode());
    }

    public Version(int i2) {
        this(i2, 0, 0);
    }

    public Version(int i2, int i7) {
        this(i2, i7, 0);
    }

    public Version(String str) {
        if (str != null) {
            int length = str.length();
            int i2 = 0;
            while (i2 < length) {
                int codePointAt = str.codePointAt(i2);
                if (!Character.isWhitespace(codePointAt)) {
                    Def.require(str.matches(".*\\d\\..*\\d\\..*\\d"), String.join(ArcCommonLog.TAG_COMMA, new CharSequence[]{"invalid version string: ".concat(str), "version should be given as major.{minor}.{patch} format(ex: 1, 1.0, 1.0.0)"}), new Object[0]);
                    List list = (List) Arrays.stream(str.replaceAll("^[^0-9]", "").split("\\.")).map(new a(str, 6)).collect(Collectors.toList());
                    Def.require(!list.isEmpty() && list.size() <= 3, "version should be given as major.{minor}.{patch} format(ex: 1, 1.0, 1.0.0)", new Object[0]);
                    Integer[] numArr = new Integer[(3 - list.size())];
                    Arrays.fill(numArr, 0);
                    List list2 = (List) Stream.concat(list.stream(), Stream.of(numArr)).collect(Collectors.toList());
                    this.major = ((Integer) list2.get(0)).intValue();
                    this.minor = ((Integer) list2.get(1)).intValue();
                    this.patch = ((Integer) list2.get(2)).intValue();
                    return;
                }
                i2 += Character.charCount(codePointAt);
            }
        }
        SLog.i(TAG, "empty string is given, set version as 0.0.0");
        this.major = 0;
        this.minor = 0;
        this.patch = 0;
    }
}
