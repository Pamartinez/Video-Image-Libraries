package vf;

import Hf.C0774x;
import Ne.i;
import Ne.l;
import Qe.C;
import java.util.Arrays;
import kotlin.jvm.internal.j;

/* renamed from: vf.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1325e extends C1337q {
    public final C0774x a(C c5) {
        j.e(c5, "module");
        i f = c5.f();
        f.getClass();
        return f.s(l.CHAR);
    }

    public final String toString() {
        String str;
        Object obj = this.f5158a;
        Integer valueOf = Integer.valueOf(((Character) obj).charValue());
        char charValue = ((Character) obj).charValue();
        switch (charValue) {
            case 8:
                str = "\\b";
                break;
            case 9:
                str = "\\t";
                break;
            case 10:
                str = "\\n";
                break;
            case 12:
                str = "\\f";
                break;
            case 13:
                str = "\\r";
                break;
            default:
                byte type = (byte) Character.getType(charValue);
                if (type != 0 && type != 13 && type != 14 && type != 15 && type != 16 && type != 18 && type != 19) {
                    str = String.valueOf(charValue);
                    break;
                } else {
                    str = "?";
                    break;
                }
        }
        return String.format("\\u%04X ('%s')", Arrays.copyOf(new Object[]{valueOf, str}, 2));
    }
}
