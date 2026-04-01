package androidx.core.util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Pair<F, S> {
    public final F first;
    public final S second;

    public Pair(F f, S s) {
        this.first = f;
        this.second = s;
    }

    public static <A, B> Pair<A, B> create(A a7, B b) {
        return new Pair<>(a7, b);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        if (!ObjectsCompat.equals(pair.first, this.first) || !ObjectsCompat.equals(pair.second, this.second)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i2;
        F f = this.first;
        int i7 = 0;
        if (f == null) {
            i2 = 0;
        } else {
            i2 = f.hashCode();
        }
        S s = this.second;
        if (s != null) {
            i7 = s.hashCode();
        }
        return i2 ^ i7;
    }

    public String toString() {
        return "Pair{" + this.first + " " + this.second + "}";
    }
}
