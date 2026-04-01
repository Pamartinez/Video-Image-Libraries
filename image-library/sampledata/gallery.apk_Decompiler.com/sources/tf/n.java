package Tf;

import Ae.c;
import B1.a;
import Ge.e;
import Sf.r;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1196n;
import o1.C0246a;

public abstract class n extends v {
    public static int A0(CharSequence charSequence, char c5, int i2, int i7) {
        if ((i7 & 2) != 0) {
            i2 = 0;
        }
        j.e(charSequence, "<this>");
        if (charSequence instanceof String) {
            return ((String) charSequence).indexOf(c5, i2);
        }
        char[] cArr = {c5};
        if (charSequence instanceof String) {
            return ((String) charSequence).indexOf(cArr[0], i2);
        }
        if (i2 < 0) {
            i2 = 0;
        }
        int x02 = x0(charSequence);
        if (i2 > x02) {
            return -1;
        }
        while (true) {
            if (a.s(cArr[0], charSequence.charAt(i2), false)) {
                return i2;
            }
            if (i2 == x02) {
                return -1;
            }
            i2++;
        }
    }

    public static /* synthetic */ int B0(CharSequence charSequence, String str, int i2, int i7) {
        if ((i7 & 2) != 0) {
            i2 = 0;
        }
        return y0(charSequence, str, i2, false);
    }

    public static boolean C0(CharSequence charSequence) {
        j.e(charSequence, "<this>");
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            if (!a.I(charSequence.charAt(i2))) {
                return false;
            }
        }
        return true;
    }

    public static char D0(CharSequence charSequence) {
        if (charSequence.length() != 0) {
            return charSequence.charAt(x0(charSequence));
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    public static int E0(int i2, int i7, String str, String str2) {
        if ((i7 & 2) != 0) {
            i2 = x0(str);
        }
        j.e(str, "<this>");
        j.e(str2, "string");
        return str.lastIndexOf(str2, i2);
    }

    public static String F0(String str, int i2, char c5) {
        CharSequence charSequence;
        j.e(str, "<this>");
        if (i2 >= 0) {
            if (i2 <= str.length()) {
                charSequence = str.subSequence(0, str.length());
            } else {
                StringBuilder sb2 = new StringBuilder(i2);
                int length = i2 - str.length();
                int i7 = 1;
                if (1 <= length) {
                    while (true) {
                        sb2.append(c5);
                        if (i7 == length) {
                            break;
                        }
                        i7++;
                    }
                }
                sb2.append(str);
                charSequence = sb2;
            }
            return charSequence.toString();
        }
        throw new IllegalArgumentException(C0212a.j(i2, "Desired length ", " is less than zero."));
    }

    public static final boolean G0(CharSequence charSequence, int i2, CharSequence charSequence2, int i7, int i8, boolean z) {
        j.e(charSequence, "<this>");
        j.e(charSequence2, "other");
        if (i7 < 0 || i2 < 0 || i2 > charSequence.length() - i8 || i7 > charSequence2.length() - i8) {
            return false;
        }
        for (int i10 = 0; i10 < i8; i10++) {
            if (!a.s(charSequence.charAt(i2 + i10), charSequence2.charAt(i7 + i10), z)) {
                return false;
            }
        }
        return true;
    }

    public static String H0(String str, String str2) {
        j.e(str, "<this>");
        if (!v.t0(str, str2)) {
            return str;
        }
        String substring = str.substring(str2.length());
        j.d(substring, "substring(...)");
        return substring;
    }

    public static String I0(String str, String str2) {
        j.e(str, "<this>");
        if (!v.o0(str, str2)) {
            return str;
        }
        String substring = str.substring(0, str.length() - str2.length());
        j.d(substring, "substring(...)");
        return substring;
    }

    public static final List J0(CharSequence charSequence, String str) {
        int y0 = y0(charSequence, str, 0, false);
        if (y0 == -1) {
            return C0246a.e0(charSequence.toString());
        }
        ArrayList arrayList = new ArrayList(10);
        int i2 = 0;
        do {
            arrayList.add(charSequence.subSequence(i2, y0).toString());
            i2 = str.length() + y0;
            y0 = y0(charSequence, str, i2, false);
        } while (y0 != -1);
        arrayList.add(charSequence.subSequence(i2, charSequence.length()).toString());
        return arrayList;
    }

    public static List K0(CharSequence charSequence, String[] strArr) {
        j.e(charSequence, "<this>");
        if (strArr.length == 1) {
            String str = strArr[0];
            if (str.length() != 0) {
                return J0(charSequence, str);
            }
        }
        Sf.j<e> jVar = new Sf.j(charSequence, (c) new w(0, C1192j.a0(strArr)));
        ArrayList arrayList = new ArrayList(C1196n.w0(new r(0, jVar), 10));
        for (e eVar : jVar) {
            j.e(eVar, "range");
            arrayList.add(charSequence.subSequence(eVar.d, eVar.e + 1).toString());
        }
        return arrayList;
    }

    public static boolean L0(String str, char c5) {
        if (str.length() <= 0 || !a.s(str.charAt(0), c5, false)) {
            return false;
        }
        return true;
    }

    public static String M0(char c5, String str, String str2) {
        int A02 = A0(str, c5, 0, 6);
        if (A02 == -1) {
            return str2;
        }
        String substring = str.substring(A02 + 1, str.length());
        j.d(substring, "substring(...)");
        return substring;
    }

    public static String N0(String str, String str2) {
        j.e(str2, "delimiter");
        int B02 = B0(str, str2, 0, 6);
        if (B02 == -1) {
            return str;
        }
        String substring = str.substring(str2.length() + B02, str.length());
        j.d(substring, "substring(...)");
        return substring;
    }

    public static String O0(String str, char c5) {
        j.e(str, "<this>");
        j.e(str, "missingDelimiterValue");
        int lastIndexOf = str.lastIndexOf(c5, x0(str));
        if (lastIndexOf == -1) {
            return str;
        }
        String substring = str.substring(lastIndexOf + 1, str.length());
        j.d(substring, "substring(...)");
        return substring;
    }

    public static String P0(String str, char c5) {
        j.e(str, "<this>");
        j.e(str, "missingDelimiterValue");
        int A02 = A0(str, c5, 0, 6);
        if (A02 == -1) {
            return str;
        }
        String substring = str.substring(0, A02);
        j.d(substring, "substring(...)");
        return substring;
    }

    public static String Q0(String str, String str2) {
        j.e(str, "<this>");
        j.e(str, "missingDelimiterValue");
        int B02 = B0(str, str2, 0, 6);
        if (B02 == -1) {
            return str;
        }
        String substring = str.substring(0, B02);
        j.d(substring, "substring(...)");
        return substring;
    }

    public static CharSequence R0(String str) {
        int i2;
        j.e(str, "<this>");
        int length = str.length() - 1;
        int i7 = 0;
        boolean z = false;
        while (i7 <= length) {
            if (!z) {
                i2 = i7;
            } else {
                i2 = length;
            }
            boolean I6 = a.I(str.charAt(i2));
            if (!z) {
                if (!I6) {
                    z = true;
                } else {
                    i7++;
                }
            } else if (!I6) {
                break;
            } else {
                length--;
            }
        }
        return str.subSequence(i7, length + 1);
    }

    public static boolean u0(CharSequence charSequence, CharSequence charSequence2) {
        j.e(charSequence, "<this>");
        j.e(charSequence2, "other");
        if (!(charSequence2 instanceof String)) {
            if (z0(charSequence, charSequence2, 0, charSequence.length(), false, false) >= 0) {
                return true;
            }
        } else if (B0(charSequence, (String) charSequence2, 0, 2) >= 0) {
            return true;
        }
        return false;
    }

    public static boolean v0(String str, char c5) {
        j.e(str, "<this>");
        if (A0(str, c5, 0, 2) >= 0) {
            return true;
        }
        return false;
    }

    public static String w0(int i2, String str) {
        j.e(str, "<this>");
        if (i2 >= 0) {
            int length = str.length();
            if (i2 > length) {
                i2 = length;
            }
            String substring = str.substring(i2);
            j.d(substring, "substring(...)");
            return substring;
        }
        throw new IllegalArgumentException(C0212a.j(i2, "Requested character count ", " is less than zero.").toString());
    }

    public static int x0(CharSequence charSequence) {
        j.e(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    public static final int y0(CharSequence charSequence, String str, int i2, boolean z) {
        j.e(charSequence, "<this>");
        j.e(str, "string");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(str, i2);
        }
        return z0(charSequence, str, i2, charSequence.length(), z, false);
    }

    public static final int z0(CharSequence charSequence, CharSequence charSequence2, int i2, int i7, boolean z, boolean z3) {
        Ge.c cVar;
        CharSequence charSequence3 = charSequence2;
        int i8 = i2;
        int i10 = i7;
        if (!z3) {
            if (i8 < 0) {
                i8 = 0;
            }
            int length = charSequence.length();
            if (i10 > length) {
                i10 = length;
            }
            cVar = new Ge.c(i8, i10, 1);
        } else {
            int x02 = x0(charSequence);
            if (i8 > x02) {
                i8 = x02;
            }
            if (i10 < 0) {
                i10 = 0;
            }
            cVar = new Ge.c(i8, i10, -1);
        }
        boolean z7 = charSequence instanceof String;
        int i11 = cVar.f;
        int i12 = cVar.e;
        int i13 = cVar.d;
        if (!z7 || !(charSequence3 instanceof String)) {
            if ((i11 > 0 && i13 <= i12) || (i11 < 0 && i12 <= i13)) {
                int i14 = i13;
                while (true) {
                    if (!G0(charSequence3, 0, charSequence, i14, charSequence3.length(), z)) {
                        if (i14 == i12) {
                            break;
                        }
                        i14 += i11;
                        charSequence3 = charSequence2;
                    } else {
                        return i14;
                    }
                }
            }
        } else if ((i11 > 0 && i13 <= i12) || (i11 < 0 && i12 <= i13)) {
            int i15 = i13;
            while (true) {
                String str = (String) charSequence3;
                if (!v.q0(str, 0, z, (String) charSequence, i15, str.length())) {
                    if (i15 == i12) {
                        break;
                    }
                    i15 += i11;
                } else {
                    return i15;
                }
            }
        }
        return -1;
    }
}
