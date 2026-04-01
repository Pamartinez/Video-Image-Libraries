package Vf;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* renamed from: Vf.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0866e {
    public static final /* synthetic */ AtomicIntegerFieldUpdater b = AtomicIntegerFieldUpdater.newUpdater(C0866e.class, "notCompletedCount$volatile");

    /* renamed from: a  reason: collision with root package name */
    public final G[] f3858a;
    private volatile /* synthetic */ int notCompletedCount$volatile;

    public C0866e(G[] gArr) {
        this.f3858a = gArr;
        this.notCompletedCount$volatile = gArr.length;
    }
}
