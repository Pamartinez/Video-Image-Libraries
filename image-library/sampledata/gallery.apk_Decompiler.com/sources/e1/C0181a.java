package e1;

import L2.a;
import V0.f;
import W0.l;
import g1.C0191b;
import g1.C0192c;
import g1.C0193d;
import g1.C0194e;
import g1.C0195f;
import g1.C0196g;
import g1.h;
import g1.i;
import g1.k;
import i1.c;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/* renamed from: e1.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0181a extends a implements Serializable {
    public final l d;

    static {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put(String.class.getName(), new Object());
        C0194e eVar = C0194e.e;
        hashMap2.put(StringBuffer.class.getName(), eVar);
        hashMap2.put(StringBuilder.class.getName(), eVar);
        hashMap2.put(Character.class.getName(), eVar);
        hashMap2.put(Character.TYPE.getName(), eVar);
        hashMap2.put(Integer.class.getName(), new Object());
        hashMap2.put(Integer.TYPE.getName(), new Object());
        hashMap2.put(Long.class.getName(), new Object());
        hashMap2.put(Long.TYPE.getName(), new Object());
        String name = Byte.class.getName();
        C0195f fVar = C0195f.e;
        hashMap2.put(name, fVar);
        hashMap2.put(Byte.TYPE.getName(), fVar);
        String name2 = Short.class.getName();
        C0195f fVar2 = C0195f.f;
        hashMap2.put(name2, fVar2);
        hashMap2.put(Short.TYPE.getName(), fVar2);
        hashMap2.put(Double.class.getName(), new Object());
        hashMap2.put(Double.TYPE.getName(), new Object());
        String name3 = Float.class.getName();
        C0195f fVar3 = C0195f.d;
        hashMap2.put(name3, fVar3);
        hashMap2.put(Float.TYPE.getName(), fVar3);
        hashMap2.put(Boolean.TYPE.getName(), new Object());
        hashMap2.put(Boolean.class.getName(), new Object());
        hashMap2.put(BigInteger.class.getName(), new Object());
        hashMap2.put(BigDecimal.class.getName(), new Object());
        hashMap2.put(Calendar.class.getName(), C0191b.d);
        hashMap2.put(Date.class.getName(), C0191b.e);
        HashMap hashMap3 = new HashMap();
        hashMap3.put(URL.class, new Object());
        hashMap3.put(URI.class, new Object());
        hashMap3.put(Currency.class, new Object());
        hashMap3.put(UUID.class, new Object());
        hashMap3.put(Pattern.class, new Object());
        hashMap3.put(Locale.class, new Object());
        hashMap3.put(AtomicBoolean.class, C0196g.class);
        hashMap3.put(AtomicInteger.class, h.class);
        hashMap3.put(AtomicLong.class, i.class);
        hashMap3.put(File.class, C0193d.class);
        hashMap3.put(Class.class, C0192c.class);
        C0194e eVar2 = C0194e.d;
        hashMap3.put(Void.class, eVar2);
        hashMap3.put(Void.TYPE, eVar2);
        for (Map.Entry entry : hashMap3.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof f) {
                hashMap2.put(((Class) entry.getKey()).getName(), (f) value);
            } else {
                hashMap.put(((Class) entry.getKey()).getName(), (Class) value);
            }
        }
        hashMap.put(c.class.getName(), k.class);
    }

    public C0181a(l lVar) {
        this.d = lVar == null ? new l((o1.h[]) null, (o1.h[]) null, (C0183c[]) null) : lVar;
    }
}
