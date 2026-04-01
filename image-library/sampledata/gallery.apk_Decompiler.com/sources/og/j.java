package og;

import com.adobe.internal.xmp.options.SerializeOptions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f5016a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public int f5017c;
    public boolean d;
    public final boolean e;
    public j f;
    public j g;

    public j() {
        this.f5016a = new byte[SerializeOptions.SORT];
        this.e = true;
        this.d = false;
    }

    public final j a() {
        j jVar;
        j jVar2 = this.f;
        if (jVar2 != this) {
            jVar = jVar2;
        } else {
            jVar = null;
        }
        j jVar3 = this.g;
        jVar3.f = jVar2;
        this.f.g = jVar3;
        this.f = null;
        this.g = null;
        return jVar;
    }

    public final void b(j jVar) {
        jVar.g = this;
        jVar.f = this.f;
        this.f.g = jVar;
        this.f = jVar;
    }

    public final j c() {
        this.d = true;
        return new j(this.f5016a, this.b, this.f5017c);
    }

    public final void d(j jVar, int i2) {
        boolean z = jVar.e;
        byte[] bArr = jVar.f5016a;
        if (z) {
            int i7 = jVar.f5017c;
            int i8 = i7 + i2;
            if (i8 > 8192) {
                if (!jVar.d) {
                    int i10 = jVar.b;
                    if (i8 - i10 <= 8192) {
                        System.arraycopy(bArr, i10, bArr, 0, i7 - i10);
                        jVar.f5017c -= jVar.b;
                        jVar.b = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            System.arraycopy(this.f5016a, this.b, bArr, jVar.f5017c, i2);
            jVar.f5017c += i2;
            this.b += i2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public j(byte[] bArr, int i2, int i7) {
        this.f5016a = bArr;
        this.b = i2;
        this.f5017c = i7;
        this.d = true;
        this.e = false;
    }
}
