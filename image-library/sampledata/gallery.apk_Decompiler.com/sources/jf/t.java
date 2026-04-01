package jf;

import Ae.b;
import c0.C0086a;
import kotlin.jvm.internal.j;

public final class t implements b {
    public static final t d = new Object();

    public final Object invoke(Object obj) {
        String str = (String) obj;
        j.e(str, "it");
        if (str.length() > 1) {
            return C0086a.h(';', "L", str);
        }
        return str;
    }
}
