package P0;

import D0.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public static final b f568a;

    /* JADX WARNING: type inference failed for: r0v0, types: [P0.b, java.lang.Object] */
    static {
        ? obj = new Object();
        obj.f569a = true;
        int i2 = 4;
        while (i2 < 128) {
            i2 += i2;
        }
        obj.e = new String[i2];
        obj.f = new e[(i2 >> 1)];
        obj.d = i2 - 1;
        obj.b = 0;
        obj.f570c = (int) (((double) (((float) i2) * 0.75f)) + 0.5d);
        f568a = obj;
        obj.h("xml");
        obj.h("xmlns");
        obj.h("id");
        obj.h("name");
        obj.h("xsd");
        obj.h("xsi");
        obj.h("type");
        obj.h("soap");
        obj.h("SOAP-ENC");
        obj.h("SOAP-ENV");
        obj.h("Body");
        obj.h("Envelope");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [P0.b, java.lang.Object] */
    public static b a() {
        String[] strArr;
        e[] eVarArr;
        int i2;
        int i7;
        int i8;
        b bVar = f568a;
        synchronized (bVar) {
            strArr = (String[]) bVar.e;
            eVarArr = (e[]) bVar.f;
            i2 = bVar.b;
            i7 = bVar.f570c;
            i8 = bVar.d;
        }
        ? obj = new Object();
        obj.e = strArr;
        obj.f = eVarArr;
        obj.b = i2;
        obj.f570c = i7;
        obj.d = i8;
        obj.f569a = false;
        return obj;
    }
}
