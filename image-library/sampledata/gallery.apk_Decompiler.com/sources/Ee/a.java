package Ee;

import java.util.Random;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a extends d {
    public final int a(int i2) {
        return (d().nextInt() >>> (32 - i2)) & ((-i2) >> 31);
    }

    public final int b() {
        return d().nextInt();
    }

    public abstract Random d();
}
