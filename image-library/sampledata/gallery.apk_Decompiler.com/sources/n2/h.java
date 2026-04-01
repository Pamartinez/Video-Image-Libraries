package N2;

import P0.b;
import U2.c;
import U2.d;
import U2.e;
import java.io.IOException;
import java.io.StringWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends d {
    public final int f;

    public h(int i2, int i7) {
        super(i2);
        this.f = i7;
    }

    public final int g() {
        int length = this.e.length;
        if (length == 0) {
            return 0;
        }
        g gVar = (g) d(length - 1);
        return gVar.b() + gVar.c();
    }

    public final void h(b bVar) {
        int i2;
        int i7 = bVar.b;
        int length = this.e.length;
        int i8 = 0;
        if (bVar.d()) {
            for (int i10 = 0; i10 < length; i10++) {
                g gVar = (g) d(i10);
                int b = gVar.b() * 2;
                String str = null;
                if (b != 0) {
                    int i11 = bVar.d;
                    int i12 = bVar.f570c - ((i11 / 2) + ((i11 * 2) + 8));
                    String e = gVar.e();
                    if (e != null) {
                        String str2 = "  " + gVar.d() + ": ";
                        int length2 = str2.length();
                        if (i12 == 0) {
                            i2 = e.length();
                        } else {
                            i2 = i12 - length2;
                        }
                        StringWriter stringWriter = new StringWriter((str2.length() + e.length()) * 3);
                        Jd.d dVar = new Jd.d(stringWriter, length2, i2, "");
                        try {
                            ((e) dVar.f3475h).write(str2);
                            ((e) dVar.f3476i).write(e);
                            StringBuffer stringBuffer = (StringBuffer) dVar.g;
                            StringBuffer stringBuffer2 = (StringBuffer) dVar.f;
                            e eVar = (e) dVar.f3476i;
                            e eVar2 = (e) dVar.f3475h;
                            try {
                                Jd.d.a(stringBuffer2, eVar2);
                                Jd.d.a(stringBuffer, eVar);
                                dVar.c();
                                Jd.d.a(stringBuffer2, eVar2);
                                while (stringBuffer2.length() != 0) {
                                    eVar.write(10);
                                    dVar.c();
                                }
                                Jd.d.a(stringBuffer, eVar);
                                while (stringBuffer.length() != 0) {
                                    eVar2.write(10);
                                    dVar.c();
                                }
                                str = stringWriter.toString();
                            } catch (IOException e7) {
                                throw new RuntimeException(e7);
                            }
                        } catch (IOException e8) {
                            throw new RuntimeException("shouldn't happen", e8);
                        }
                    }
                }
                if (str != null) {
                    bVar.b(b, str);
                } else if (b != 0) {
                    bVar.b(b, "");
                }
            }
        }
        while (i8 < length) {
            g gVar2 = (g) d(i8);
            try {
                gVar2.j(bVar);
                i8++;
            } catch (RuntimeException e9) {
                throw c.a(e9, "...while writing " + gVar2);
            }
        }
        int i13 = (bVar.b - i7) / 2;
        if (i13 != g()) {
            throw new RuntimeException("write length mismatch; expected " + g() + " but actually wrote " + i13);
        }
    }
}
