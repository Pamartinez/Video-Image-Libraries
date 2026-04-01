package Ke;

import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v0 extends Error {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public v0(int i2) {
        this("An operation is not implemented.", 2);
        switch (i2) {
            case 3:
                super("Kotlin reflection implementation is not found at runtime. Make sure you have kotlin-reflect.jar in the classpath");
                return;
            default:
                return;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public v0(String str, int i2) {
        super(str);
        switch (i2) {
            case 2:
                j.e(str, "message");
                super(str);
                return;
            default:
                j.e(str, "message");
                return;
        }
    }
}
