package mg;

import B0.a;
import Ed.b;
import He.C0748d;
import L1.d;
import gg.e;
import i.C0212a;
import ig.f;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kg.C1140v;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import lg.C;
import lg.C1174b;
import lg.i;
import lg.l;
import lg.m;
import lg.y;
import ng.c;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class h {

    /* renamed from: a  reason: collision with root package name */
    public static final i f4927a = new Object();

    public static final d a(Number number, String str, String str2) {
        j.e(str, "key");
        j.e(str2, "output");
        return c(-1, "Unexpected special floating-point value " + number + " with key " + str + ". By default, non-finite floating point values are prohibited because they do not conform JSON specification. It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'\nCurrent output: " + l(str2, -1));
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [mg.g, java.lang.IllegalArgumentException] */
    public static final g b(f fVar) {
        String str = "Value of type '" + fVar.i() + "' can't be used in JSON as a key in the map. It should have either primitive or enum kind, but its kind is '" + fVar.b() + "'.\nUse 'allowStructuredMapKeys = true' in 'Json {}' builder to convert such maps to [key1, value1, key2, value2,...] arrays.";
        j.e(str, "message");
        return new IllegalArgumentException(str);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [mg.d, java.lang.IllegalArgumentException] */
    public static final d c(int i2, String str) {
        j.e(str, "message");
        if (i2 >= 0) {
            str = C0212a.k(i2, "Unexpected JSON token at offset ", ": ", str);
        }
        j.e(str, "message");
        return new IllegalArgumentException(str);
    }

    public static final d d(int i2, String str, CharSequence charSequence) {
        j.e(str, "message");
        j.e(charSequence, "input");
        return c(i2, str + "\nJSON input: " + l(charSequence, i2));
    }

    public static final f e(f fVar, a aVar) {
        j.e(fVar, "<this>");
        j.e(aVar, "module");
        if (j.a(fVar.b(), ig.j.d)) {
            C0748d k = d.k(fVar);
            if (k == null) {
                return fVar;
            }
            c cVar = (c) ((Map) aVar.d).get(k);
            return fVar;
        } else if (fVar.isInline()) {
            return e(fVar.h(0), aVar);
        } else {
            return fVar;
        }
    }

    public static final byte f(char c5) {
        if (c5 < '~') {
            return b.b[c5];
        }
        return 0;
    }

    public static final String g(f fVar, C1174b bVar) {
        j.e(fVar, "<this>");
        j.e(bVar, "json");
        for (Annotation annotation : fVar.getAnnotations()) {
            if (annotation instanceof lg.h) {
                return ((lg.h) annotation).discriminator();
            }
        }
        return bVar.f4894a.f;
    }

    public static final Object h(lg.j jVar, gg.a aVar) {
        C c5;
        j.e(jVar, "<this>");
        j.e(aVar, "deserializer");
        if (!(aVar instanceof e)) {
            return aVar.deserialize(jVar);
        }
        i iVar = jVar.F().f4894a;
        String g = g(aVar.getDescriptor(), jVar.F());
        l e = jVar.e();
        f descriptor = aVar.getDescriptor();
        if (e instanceof y) {
            y yVar = (y) e;
            l lVar = (l) yVar.get(g);
            String str = null;
            if (lVar != null) {
                int i2 = m.f4903a;
                if (lVar instanceof C) {
                    c5 = (C) lVar;
                } else {
                    c5 = null;
                }
                if (c5 == null) {
                    throw new IllegalArgumentException("Element " + v.f4727a.b(lVar.getClass()) + " is not a JsonPrimitive");
                } else if (!(c5 instanceof lg.v)) {
                    str = c5.i();
                }
            }
            try {
                gg.a x9 = k.x((e) aVar, jVar, str);
                C1174b F4 = jVar.F();
                j.e(F4, "<this>");
                j.e(g, "discriminator");
                return h(new l(F4, yVar, g, x9.getDescriptor()), x9);
            } catch (gg.f e7) {
                String message = e7.getMessage();
                j.b(message);
                throw d(-1, message, yVar.toString());
            }
        } else {
            StringBuilder sb2 = new StringBuilder("Expected ");
            w wVar = v.f4727a;
            sb2.append(wVar.b(y.class));
            sb2.append(" as the serialized body of ");
            sb2.append(descriptor.i());
            sb2.append(", but had ");
            sb2.append(wVar.b(e.getClass()));
            throw c(-1, sb2.toString());
        }
    }

    public static final int i(f fVar, C1174b bVar, String str) {
        j.e(fVar, "<this>");
        j.e(bVar, "json");
        j.e(str, "name");
        m(fVar, bVar);
        int d = fVar.d(str);
        if (d != -3 || !bVar.f4894a.g) {
            return d;
        }
        G0.c cVar = bVar.f4895c;
        C1140v vVar = new C1140v(1, fVar, bVar);
        cVar.getClass();
        i iVar = f4927a;
        Object m = cVar.m(fVar, iVar);
        if (m == null) {
            m = vVar.invoke();
            ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) cVar.e;
            Object obj = concurrentHashMap.get(fVar);
            if (obj == null) {
                obj = new ConcurrentHashMap(2);
                concurrentHashMap.put(fVar, obj);
            }
            ((Map) obj).put(iVar, m);
        }
        Integer num = (Integer) ((Map) m).get(str);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    public static final int j(f fVar, C1174b bVar, String str, String str2) {
        j.e(fVar, "<this>");
        j.e(bVar, "json");
        j.e(str, "name");
        j.e(str2, "suffix");
        int i2 = i(fVar, bVar, str);
        if (i2 != -3) {
            return i2;
        }
        throw new IllegalArgumentException(fVar.i() + " does not contain element with name '" + str + '\'' + str2);
    }

    public static final void k(b bVar, String str) {
        bVar.o(bVar.b - 1, "Trailing comma before the end of JSON ".concat(str), "Trailing commas are non-complaint JSON and not allowed by default. Use 'allowTrailingCommas = true' in 'Json {}' builder to support them.");
        throw null;
    }

    public static final CharSequence l(CharSequence charSequence, int i2) {
        String str;
        j.e(charSequence, "<this>");
        if (charSequence.length() >= 200) {
            String str2 = ".....";
            if (i2 == -1) {
                int length = charSequence.length() - 60;
                if (length > 0) {
                    return str2 + charSequence.subSequence(length, charSequence.length()).toString();
                }
            } else {
                int i7 = i2 - 30;
                int i8 = i2 + 30;
                if (i7 <= 0) {
                    str = "";
                } else {
                    str = str2;
                }
                if (i8 >= charSequence.length()) {
                    str2 = "";
                }
                StringBuilder s = C0212a.s(str);
                if (i7 < 0) {
                    i7 = 0;
                }
                int length2 = charSequence.length();
                if (i8 > length2) {
                    i8 = length2;
                }
                s.append(charSequence.subSequence(i7, i8).toString());
                s.append(str2);
                return s.toString();
            }
        }
        return charSequence;
    }

    public static final void m(f fVar, C1174b bVar) {
        j.e(fVar, "<this>");
        j.e(bVar, "json");
        j.a(fVar.b(), ig.l.d);
    }

    public static final void n(b bVar, Number number) {
        b.p(bVar, "Unexpected special floating-point value " + number + ". By default, non-finite floating point values are prohibited because they do not conform JSON specification", 0, "It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'", 2);
        throw null;
    }

    public static final String o(byte b) {
        if (b == 1) {
            return "quotation mark '\"'";
        }
        if (b == 2) {
            return "string escape sequence '\\'";
        }
        if (b == 4) {
            return "comma ','";
        }
        if (b == 5) {
            return "colon ':'";
        }
        if (b == 6) {
            return "start of the object '{'";
        }
        if (b == 7) {
            return "end of the object '}'";
        }
        if (b == 8) {
            return "start of the array '['";
        }
        if (b == 9) {
            return "end of the array ']'";
        }
        if (b == 10) {
            return "end of the input";
        }
        if (b == Byte.MAX_VALUE) {
            return "invalid token";
        }
        return "valid token";
    }
}
