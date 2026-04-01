package com.samsung.android.sesl.visualeffect.utils;

import Tf.n;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/utils/ColorUtil;", "", "<init>", "()V", "", "color", "", "toHexString", "(I)Ljava/lang/String;", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ColorUtil {
    public static final ColorUtil INSTANCE = new ColorUtil();

    private ColorUtil() {
    }

    public final String toHexString(int i2) {
        String hexString = Integer.toHexString(i2);
        j.b(hexString);
        String upperCase = n.F0(hexString, 8, 'F').toUpperCase(Locale.ROOT);
        j.d(upperCase, "toUpperCase(...)");
        return "#".concat(upperCase);
    }
}
