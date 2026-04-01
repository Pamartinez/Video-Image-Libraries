package H2;

import He.F;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class d {

    /* renamed from: c  reason: collision with root package name */
    public static final c f338c = new c("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");

    /* renamed from: a  reason: collision with root package name */
    public final a f339a;
    public final Character b;

    static {
        new c("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");
        new d("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567");
        new d("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV");
        new b(new a("base16()", "0123456789ABCDEF".toCharArray()));
    }

    public d(a aVar, Character ch) {
        boolean z;
        aVar.getClass();
        this.f339a = aVar;
        if (ch != null) {
            char charValue = ch.charValue();
            byte[] bArr = aVar.g;
            if (charValue < bArr.length && bArr[charValue] != -1) {
                z = false;
                F.h("Padding character %s was already in alphabet", ch, z);
                this.b = ch;
            }
        }
        z = true;
        F.h("Padding character %s was already in alphabet", ch, z);
        this.b = ch;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof d) {
            d dVar = (d) obj;
            if (!this.f339a.equals(dVar.f339a) || !Objects.equals(this.b, dVar.b)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.b) ^ this.f339a.hashCode();
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("BaseEncoding.");
        a aVar = this.f339a;
        sb2.append(aVar);
        if (8 % aVar.d != 0) {
            Character ch = this.b;
            if (ch == null) {
                sb2.append(".omitPadding()");
            } else {
                sb2.append(".withPadChar('");
                sb2.append(ch);
                sb2.append("')");
            }
        }
        return sb2.toString();
    }

    public d(String str, String str2) {
        this(new a(str, str2.toCharArray()), (Character) '=');
    }
}
