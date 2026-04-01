package ge;

import Gd.a;
import ee.C0967D;

/* renamed from: ge.b1  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1011b1 extends C0967D {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f4497a;

    static {
        boolean z;
        if (a.h0(System.getenv("GRPC_EXPERIMENTAL_ENABLE_NEW_PICK_FIRST")) || !Boolean.parseBoolean(System.getenv("GRPC_EXPERIMENTAL_ENABLE_NEW_PICK_FIRST"))) {
            z = false;
        } else {
            z = true;
        }
        f4497a = z;
    }
}
