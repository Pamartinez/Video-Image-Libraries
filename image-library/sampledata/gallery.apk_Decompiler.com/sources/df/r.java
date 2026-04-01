package Df;

import i.C0212a;
import kotlin.jvm.internal.j;
import pf.f;
import qf.C1235b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r {

    /* renamed from: a  reason: collision with root package name */
    public final Object f3363a;
    public final Object b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f3364c;
    public final f d;
    public final String e;
    public final C1235b f;

    public r(Object obj, Object obj2, f fVar, f fVar2, String str, C1235b bVar) {
        j.e(str, "filePath");
        this.f3363a = obj;
        this.b = obj2;
        this.f3364c = fVar;
        this.d = fVar2;
        this.e = str;
        this.f = bVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        if (this.f3363a.equals(rVar.f3363a) && j.a(this.b, rVar.b) && j.a(this.f3364c, rVar.f3364c) && this.d.equals(rVar.d) && j.a(this.e, rVar.e) && this.f.equals(rVar.f)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        int hashCode = this.f3363a.hashCode() * 31;
        int i7 = 0;
        Object obj = this.b;
        if (obj == null) {
            i2 = 0;
        } else {
            i2 = obj.hashCode();
        }
        int i8 = (hashCode + i2) * 31;
        Object obj2 = this.f3364c;
        if (obj2 != null) {
            i7 = obj2.hashCode();
        }
        return this.f.hashCode() + C0212a.d((this.d.hashCode() + ((i8 + i7) * 31)) * 31, 31, this.e);
    }

    public final String toString() {
        return "IncompatibleVersionErrorData(actualVersion=" + this.f3363a + ", compilerVersion=" + this.b + ", languageVersion=" + this.f3364c + ", expectedVersion=" + this.d + ", filePath=" + this.e + ", classId=" + this.f + ')';
    }
}
