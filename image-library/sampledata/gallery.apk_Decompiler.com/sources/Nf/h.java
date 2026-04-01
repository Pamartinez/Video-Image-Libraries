package Nf;

import Ae.b;
import Tf.m;
import java.util.Arrays;
import java.util.Collection;
import kotlin.jvm.internal.j;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    public final C1240g f3587a;
    public final m b;

    /* renamed from: c  reason: collision with root package name */
    public final Collection f3588c;
    public final b d;
    public final d[] e;

    public h(C1240g gVar, m mVar, Collection collection, b bVar, d... dVarArr) {
        this.f3587a = gVar;
        this.b = mVar;
        this.f3588c = collection;
        this.d = bVar;
        this.e = dVarArr;
    }

    public /* synthetic */ h(C1240g gVar, d[] dVarArr) {
        this(gVar, dVarArr, (b) g.e);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public h(C1240g gVar, d[] dVarArr, b bVar) {
        this(gVar, (m) null, (Collection) null, bVar, (d[]) Arrays.copyOf(dVarArr, dVarArr.length));
        j.e(gVar, "name");
    }

    public /* synthetic */ h(Collection collection, d[] dVarArr) {
        this(collection, dVarArr, (b) g.g);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public h(Collection collection, d[] dVarArr, b bVar) {
        this((C1240g) null, (m) null, collection, bVar, (d[]) Arrays.copyOf(dVarArr, dVarArr.length));
        j.e(collection, "nameList");
    }
}
