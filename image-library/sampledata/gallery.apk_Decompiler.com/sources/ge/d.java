package Ge;

import java.util.NoSuchElementException;
import ne.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends y {
    public final int d;
    public final int e;
    public boolean f;
    public int g;

    public d(int i2, int i7, int i8) {
        this.d = i8;
        this.e = i7;
        boolean z = false;
        if (i8 <= 0 ? i2 >= i7 : i2 <= i7) {
            z = true;
        }
        this.f = z;
        this.g = !z ? i7 : i2;
    }

    public final boolean hasNext() {
        return this.f;
    }

    public final int nextInt() {
        int i2 = this.g;
        if (i2 != this.e) {
            this.g = this.d + i2;
            return i2;
        } else if (this.f) {
            this.f = false;
            return i2;
        } else {
            throw new NoSuchElementException();
        }
    }
}
