package Fa;

import android.content.DialogInterface;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Fa.w  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0568w implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AtomicInteger e;

    public /* synthetic */ C0568w(AtomicInteger atomicInteger, int i2) {
        this.d = i2;
        this.e = atomicInteger;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        AtomicInteger atomicInteger = this.e;
        switch (i7) {
            case 0:
                atomicInteger.set(i2);
                return;
            case 1:
                atomicInteger.set(i2);
                return;
            default:
                atomicInteger.set(i2);
                return;
        }
    }
}
