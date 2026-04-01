package R2;

import L2.a;
import T2.b;
import T2.c;
import T2.e;
import U2.d;
import i.C0212a;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n {

    /* renamed from: a  reason: collision with root package name */
    public final int f652a;
    public final c b;

    /* renamed from: c  reason: collision with root package name */
    public final e f653c;
    public final e d;
    public final int e;
    public final boolean f;
    public final String g;

    public n(int i2, c cVar, e eVar, e eVar2, int i7, boolean z, String str) {
        if (cVar == null) {
            throw new NullPointerException("result == null");
        } else if (eVar == null) {
            throw new NullPointerException("sources == null");
        } else if (eVar2 == null) {
            throw new NullPointerException("exceptions == null");
        } else if (i7 < 1 || i7 > 6) {
            throw new IllegalArgumentException("bogus branchingness");
        } else if (((d) eVar2).e.length == 0 || i7 == 6) {
            this.f652a = i2;
            this.b = cVar;
            this.f653c = eVar;
            this.d = eVar2;
            this.e = i7;
            this.f = z;
            this.g = str;
        } else {
            throw new IllegalArgumentException("exceptions / branchingness mismatch");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        if (this.f652a == nVar.f652a && this.e == nVar.e && this.b == nVar.b && this.f653c.equals(nVar.f653c) && this.d.equals(nVar.d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int d2 = C0212a.d(((this.f652a * 31) + this.e) * 31, 31, this.b.d);
        return Arrays.hashCode(((d) this.d).e) + ((Arrays.hashCode(((d) this.f653c).e) + d2) * 31);
    }

    public final String toString() {
        String str;
        StringBuffer stringBuffer = new StringBuffer(40);
        stringBuffer.append("Rop{");
        int i2 = this.f652a;
        switch (i2) {
            case 1:
                str = "nop";
                break;
            case 2:
                str = "move";
                break;
            case 3:
                str = "move-param";
                break;
            case 4:
                str = "move-exception";
                break;
            case 5:
                str = "const";
                break;
            case 6:
                str = "goto";
                break;
            case 7:
                str = "if-eq";
                break;
            case 8:
                str = "if-ne";
                break;
            case 9:
                str = "if-lt";
                break;
            case 10:
                str = "if-ge";
                break;
            case 11:
                str = "if-le";
                break;
            case 12:
                str = "if-gt";
                break;
            case 13:
                str = "switch";
                break;
            case 14:
                str = "add";
                break;
            case 15:
                str = "sub";
                break;
            case 16:
                str = "mul";
                break;
            case 17:
                str = "div";
                break;
            case 18:
                str = "rem";
                break;
            case 19:
                str = "neg";
                break;
            case 20:
                str = "and";
                break;
            case 21:
                str = "or";
                break;
            case 22:
                str = "xor";
                break;
            case 23:
                str = "shl";
                break;
            case 24:
                str = "shr";
                break;
            case 25:
                str = "ushr";
                break;
            case 26:
                str = "not";
                break;
            case 27:
                str = "cmpl";
                break;
            case 28:
                str = "cmpg";
                break;
            case 29:
                str = "conv";
                break;
            case 30:
                str = "to-byte";
                break;
            case 31:
                str = "to-char";
                break;
            case 32:
                str = "to-short";
                break;
            case 33:
                str = "return";
                break;
            case 34:
                str = "array-length";
                break;
            case 35:
                str = "throw";
                break;
            case 36:
                str = "monitor-enter";
                break;
            case 37:
                str = "monitor-exit";
                break;
            case 38:
                str = "aget";
                break;
            case 39:
                str = "aput";
                break;
            case 40:
                str = "new-instance";
                break;
            case 41:
                str = "new-array";
                break;
            case 42:
                str = "filled-new-array";
                break;
            case 43:
                str = "check-cast";
                break;
            case 44:
                str = "instance-of";
                break;
            case 45:
                str = "get-field";
                break;
            case 46:
                str = "get-static";
                break;
            case 47:
                str = "put-field";
                break;
            case 48:
                str = "put-static";
                break;
            case 49:
                str = "invoke-static";
                break;
            case 50:
                str = "invoke-virtual";
                break;
            case 51:
                str = "invoke-super";
                break;
            case 52:
                str = "invoke-direct";
                break;
            case 53:
                str = "invoke-interface";
                break;
            case 55:
                str = "move-result";
                break;
            case 56:
                str = "move-result-pseudo";
                break;
            case 57:
                str = "fill-array-data";
                break;
            default:
                str = "unknown-".concat(a.B(i2));
                break;
        }
        stringBuffer.append(str);
        c cVar = c.r;
        c cVar2 = this.b;
        if (cVar2 != cVar) {
            stringBuffer.append(" ");
            stringBuffer.append(cVar2);
        } else {
            stringBuffer.append(" .");
        }
        stringBuffer.append(" <-");
        e eVar = this.f653c;
        int length = ((d) eVar).e.length;
        if (length == 0) {
            stringBuffer.append(" .");
        } else {
            for (int i7 = 0; i7 < length; i7++) {
                stringBuffer.append(' ');
                stringBuffer.append(eVar.getType(i7));
            }
        }
        if (this.f) {
            stringBuffer.append(" call");
        }
        e eVar2 = this.d;
        int length2 = ((d) eVar2).e.length;
        if (length2 != 0) {
            stringBuffer.append(" throws");
            for (int i8 = 0; i8 < length2; i8++) {
                stringBuffer.append(' ');
                if (eVar2.getType(i8) == c.f832x) {
                    stringBuffer.append("<any>");
                } else {
                    stringBuffer.append(eVar2.getType(i8));
                }
            }
        } else {
            int i10 = this.e;
            if (i10 == 1) {
                stringBuffer.append(" flows");
            } else if (i10 == 2) {
                stringBuffer.append(" returns");
            } else if (i10 == 3) {
                stringBuffer.append(" gotos");
            } else if (i10 == 4) {
                stringBuffer.append(" ifs");
            } else if (i10 != 5) {
                stringBuffer.append(" ".concat(a.B(i10)));
            } else {
                stringBuffer.append(" switches");
            }
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public n(int i2, c cVar, e eVar, int i7, String str) {
        this(i2, cVar, eVar, b.f, i7, false, str);
    }

    public n(int i2, c cVar, e eVar, String str) {
        this(i2, cVar, eVar, b.f, 1, false, str);
    }

    public n(int i2, c cVar, e eVar, e eVar2, String str) {
        this(i2, cVar, eVar, eVar2, 6, false, str);
    }

    public n(int i2, b bVar, e eVar) {
        this(i2, c.r, bVar, eVar, 6, true, (String) null);
    }
}
