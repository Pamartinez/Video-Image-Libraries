package Sd;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n extends C0212a {

    /* renamed from: a  reason: collision with root package name */
    public final String f3710a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f3711c;
    public final Integer d;
    public final long e;

    public n(String str, String str2, String str3, Integer num, long j2) {
        this.f3710a = str;
        this.b = str2;
        this.f3711c = str3;
        this.d = num;
        this.e = j2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        if (this.e != nVar.e || !Objects.equals(this.f3710a, nVar.f3710a) || !Objects.equals(this.b, nVar.b) || !Objects.equals(this.f3711c, nVar.f3711c) || !Objects.equals(this.d, nVar.d)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int hashCode = Objects.hashCode(this.f3710a);
        int hashCode2 = Objects.hashCode(this.b);
        int hashCode3 = Objects.hashCode(this.f3711c);
        return Objects.hashCode(this.d) + ((hashCode3 + ((hashCode2 + ((hashCode + (Long.hashCode(this.e) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        String[] strArr;
        Object[] objArr = {this.f3710a, this.b, this.f3711c, this.d, Long.valueOf(this.e)};
        if ("a;b;c;d;e".length() == 0) {
            strArr = new String[0];
        } else {
            strArr = "a;b;c;d;e".split(";");
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(n.class.getSimpleName());
        sb2.append("[");
        for (int i2 = 0; i2 < strArr.length; i2++) {
            sb2.append(strArr[i2]);
            sb2.append("=");
            sb2.append(objArr[i2]);
            if (i2 != strArr.length - 1) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
        }
        sb2.append("]");
        return sb2.toString();
    }
}
