package mf;

import java.util.Arrays;
import kotlin.jvm.internal.j;
import nf.C1204a;

/* renamed from: mf.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1178a extends C1204a {
    public static final C1178a f = new C1178a(1, 0, 7);

    static {
        new C1178a(new int[0]);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1178a(int... iArr) {
        super(Arrays.copyOf(iArr, iArr.length));
        j.e(iArr, "numbers");
    }
}
