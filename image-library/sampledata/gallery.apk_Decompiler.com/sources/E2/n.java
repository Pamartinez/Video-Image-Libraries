package E2;

import G0.c;
import He.F;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n implements Iterator {
    public a d = a.NOT_READY;
    public String e;
    public final CharSequence f;
    public final b g;

    /* renamed from: h  reason: collision with root package name */
    public int f170h = 0;

    /* renamed from: i  reason: collision with root package name */
    public int f171i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ c f172j;

    public n(c cVar, o oVar, CharSequence charSequence) {
        this.f172j = cVar;
        this.g = oVar.f173a;
        this.f171i = Integer.MAX_VALUE;
        this.f = charSequence;
    }

    public final boolean hasNext() {
        boolean z;
        String str;
        int i2;
        a aVar = this.d;
        a aVar2 = a.FAILED;
        if (aVar != aVar2) {
            z = true;
        } else {
            z = false;
        }
        F.r(z);
        int ordinal = this.d.ordinal();
        if (ordinal == 0) {
            return true;
        }
        if (ordinal != 2) {
            this.d = aVar2;
            int i7 = this.f170h;
            while (true) {
                int i8 = this.f170h;
                if (i8 == -1) {
                    this.d = a.DONE;
                    str = null;
                    break;
                }
                c cVar = (c) this.f172j.e;
                CharSequence charSequence = this.f;
                int length = charSequence.length();
                F.o(i8, length);
                while (true) {
                    if (i8 >= length) {
                        i8 = -1;
                        break;
                    } else if (cVar.a(charSequence.charAt(i8))) {
                        break;
                    } else {
                        i8++;
                    }
                }
                if (i8 == -1) {
                    i8 = charSequence.length();
                    this.f170h = -1;
                } else {
                    this.f170h = i8 + 1;
                }
                int i10 = this.f170h;
                if (i10 == i7) {
                    int i11 = i10 + 1;
                    this.f170h = i11;
                    if (i11 > charSequence.length()) {
                        this.f170h = -1;
                    }
                } else {
                    while (true) {
                        b bVar = this.g;
                        if (i7 < i8 && bVar.a(charSequence.charAt(i7))) {
                            i7++;
                        }
                    }
                    while (i2 > i7 && bVar.a(charSequence.charAt(i2 - 1))) {
                        i8 = i2 - 1;
                    }
                    int i12 = this.f171i;
                    if (i12 == 1) {
                        i2 = charSequence.length();
                        this.f170h = -1;
                        while (i2 > i7 && bVar.a(charSequence.charAt(i2 - 1))) {
                            i2--;
                        }
                    } else {
                        this.f171i = i12 - 1;
                    }
                    str = charSequence.subSequence(i7, i2).toString();
                }
            }
            this.e = str;
            if (this.d != a.DONE) {
                this.d = a.READY;
                return true;
            }
        }
        return false;
    }

    public final Object next() {
        if (hasNext()) {
            this.d = a.NOT_READY;
            String str = this.e;
            this.e = null;
            return str;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
