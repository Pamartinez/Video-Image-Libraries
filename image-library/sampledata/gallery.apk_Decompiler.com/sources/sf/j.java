package Sf;

import Ae.a;
import Ae.b;
import java.util.Iterator;
import me.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j implements k {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3736a = 0;
    public final Object b;

    /* renamed from: c  reason: collision with root package name */
    public final c f3737c;

    public j(a aVar, b bVar) {
        kotlin.jvm.internal.j.e(bVar, "getNextValue");
        this.b = aVar;
        this.f3737c = bVar;
    }

    public final Iterator iterator() {
        switch (this.f3736a) {
            case 0:
                return new i(this);
            default:
                return new Tf.b(this);
        }
    }

    public j(CharSequence charSequence, Ae.c cVar) {
        kotlin.jvm.internal.j.e(charSequence, "input");
        this.b = charSequence;
        this.f3737c = cVar;
    }
}
