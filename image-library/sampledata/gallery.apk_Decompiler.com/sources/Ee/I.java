package ee;

import E2.g;
import He.F;
import L1.d;
import i.C0212a;
import java.util.BitSet;
import java.util.Locale;
import java.util.logging.Level;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class I {
    public static final BitSet d;

    /* renamed from: a  reason: collision with root package name */
    public final String f4272a;
    public final byte[] b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f4273c;

    static {
        BitSet bitSet = new BitSet(127);
        bitSet.set(45);
        bitSet.set(95);
        bitSet.set(46);
        for (char c5 = '0'; c5 <= '9'; c5 = (char) (c5 + 1)) {
            bitSet.set(c5);
        }
        for (char c6 = 'a'; c6 <= 'z'; c6 = (char) (c6 + 1)) {
            bitSet.set(c6);
        }
        d = bitSet;
    }

    public I(String str, Object obj, boolean z) {
        String lowerCase = str.toLowerCase(Locale.ROOT);
        F.n(lowerCase, "name");
        F.i("token must have at least 1 tchar", !lowerCase.isEmpty());
        if (lowerCase.equals("connection")) {
            M.f4274c.log(Level.WARNING, "Metadata key is 'Connection', which should not be used. That is used by HTTP/1 for connection-specific headers which are not to be forwarded. There is probably an HTTP/1 conversion bug. Simply removing the Connection header is not enough; you should remove all headers it references as well. See RFC 7230 section 6.1", new RuntimeException("exception to show backtrace"));
        }
        for (int i2 = 0; i2 < lowerCase.length(); i2++) {
            char charAt = lowerCase.charAt(i2);
            if (!(z && charAt == ':' && i2 == 0) && !d.get(charAt)) {
                throw new IllegalArgumentException(d.r("Invalid character '%s' in key name '%s'", Character.valueOf(charAt), lowerCase));
            }
        }
        this.f4272a = lowerCase;
        this.b = lowerCase.getBytes(g.f166a);
        this.f4273c = obj;
    }

    public static G a(String str, H h5) {
        return new G(str, h5);
    }

    public abstract byte[] b(Object obj);

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f4272a.equals(((I) obj).f4272a);
    }

    public final int hashCode() {
        return this.f4272a.hashCode();
    }

    public final String toString() {
        return C0212a.p(new StringBuilder("Key{name='"), this.f4272a, "'}");
    }
}
