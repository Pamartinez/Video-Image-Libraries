package R2;

import T2.e;
import U2.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class f implements g {
    public final n d;
    public final p e;
    public final k f;
    public final l g;

    public f(n nVar, p pVar, k kVar, l lVar) {
        if (nVar == null) {
            throw new NullPointerException("opcode == null");
        } else if (pVar == null) {
            throw new NullPointerException("position == null");
        } else if (lVar != null) {
            this.d = nVar;
            this.e = pVar;
            this.f = kVar;
            this.g = lVar;
        } else {
            throw new NullPointerException("sources == null");
        }
    }

    public final String a() {
        String e7 = e();
        StringBuffer stringBuffer = new StringBuffer(80);
        stringBuffer.append(this.e);
        stringBuffer.append(": ");
        n nVar = this.d;
        String str = nVar.g;
        if (str == null) {
            str = nVar.toString();
        }
        stringBuffer.append(str);
        if (e7 != null) {
            stringBuffer.append("(");
            stringBuffer.append(e7);
            stringBuffer.append(")");
        }
        k kVar = this.f;
        if (kVar == null) {
            stringBuffer.append(" .");
        } else {
            stringBuffer.append(" ");
            stringBuffer.append(kVar.f(true));
        }
        stringBuffer.append(" <-");
        l lVar = this.g;
        int length = lVar.e.length;
        if (length == 0) {
            stringBuffer.append(" .");
        } else {
            for (int i2 = 0; i2 < length; i2++) {
                stringBuffer.append(" ");
                stringBuffer.append(((k) lVar.d(i2)).f(true));
            }
        }
        return stringBuffer.toString();
    }

    public abstract void c(e eVar);

    public abstract e d();

    public String e() {
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public final String toString() {
        String e7 = e();
        StringBuffer stringBuffer = new StringBuffer(80);
        stringBuffer.append("Insn{");
        stringBuffer.append(this.e);
        stringBuffer.append(' ');
        stringBuffer.append(this.d);
        if (e7 != null) {
            stringBuffer.append(' ');
            stringBuffer.append(e7);
        }
        stringBuffer.append(" :: ");
        k kVar = this.f;
        if (kVar != null) {
            stringBuffer.append(kVar);
            stringBuffer.append(" <- ");
        }
        stringBuffer.append(this.g);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
