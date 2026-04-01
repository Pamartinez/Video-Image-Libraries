package lf;

import Dd.C0730a;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.OCDHelperConstant;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rf.C1252b;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1263m;
import rf.C1267q;
import rf.u;

/* renamed from: lf.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1155h extends C1263m {

    /* renamed from: A  reason: collision with root package name */
    public List f4832A;
    public List B;

    /* renamed from: C  reason: collision with root package name */
    public X f4833C;
    public List D;
    public e0 E;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f4834h;

    /* renamed from: i  reason: collision with root package name */
    public int f4835i;

    /* renamed from: j  reason: collision with root package name */
    public int f4836j;
    public List k;
    public List l;
    public List m;
    public List n;

    /* renamed from: o  reason: collision with root package name */
    public List f4837o;

    /* renamed from: p  reason: collision with root package name */
    public List f4838p;
    public List q;
    public List r;
    public List s;
    public List t;
    public List u;
    public List v;
    public int w;

    /* renamed from: x  reason: collision with root package name */
    public Q f4839x;
    public int y;
    public List z;

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.m, lf.h] */
    public static C1155h f() {
        ? mVar = new C1263m();
        mVar.f4834h = 6;
        List list = Collections.EMPTY_LIST;
        mVar.k = list;
        mVar.l = list;
        mVar.m = list;
        mVar.n = list;
        mVar.f4837o = list;
        mVar.f4838p = list;
        mVar.q = list;
        mVar.r = list;
        mVar.s = list;
        mVar.t = list;
        mVar.u = list;
        mVar.v = list;
        mVar.f4839x = Q.w;
        mVar.z = list;
        mVar.f4832A = list;
        mVar.B = list;
        mVar.f4833C = X.f4794j;
        mVar.D = list;
        mVar.E = e0.f4826h;
        return mVar;
    }

    public final C1252b a() {
        C1157j e = e();
        if (e.isInitialized()) {
            return e;
        }
        throw new C0730a();
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        C1157j jVar;
        C1157j jVar2 = null;
        try {
            C1157j.f4841N.getClass();
            g(new C1157j(fVar, hVar));
            return this;
        } catch (u e) {
            jVar = (C1157j) e.d;
            throw e;
        } catch (Throwable th) {
            th = th;
            jVar2 = jVar;
        }
        if (jVar2 != null) {
            g(jVar2);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        g((C1157j) qVar);
        return this;
    }

    public final Object clone() {
        C1155h f = f();
        f.g(e());
        return f;
    }

    public final C1157j e() {
        C1157j jVar = new C1157j(this);
        int i2 = this.g;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        jVar.g = this.f4834h;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        jVar.f4850h = this.f4835i;
        if ((i2 & 4) == 4) {
            i7 |= 4;
        }
        jVar.f4851i = this.f4836j;
        if ((i2 & 8) == 8) {
            this.k = Collections.unmodifiableList(this.k);
            this.g &= -9;
        }
        jVar.f4852j = this.k;
        if ((this.g & 16) == 16) {
            this.l = Collections.unmodifiableList(this.l);
            this.g &= -17;
        }
        jVar.k = this.l;
        if ((this.g & 32) == 32) {
            this.m = Collections.unmodifiableList(this.m);
            this.g &= -33;
        }
        jVar.l = this.m;
        if ((this.g & 64) == 64) {
            this.n = Collections.unmodifiableList(this.n);
            this.g &= -65;
        }
        jVar.n = this.n;
        if ((this.g & 128) == 128) {
            this.f4837o = Collections.unmodifiableList(this.f4837o);
            this.g &= -129;
        }
        jVar.f4854p = this.f4837o;
        if ((this.g & 256) == 256) {
            this.f4838p = Collections.unmodifiableList(this.f4838p);
            this.g &= -257;
        }
        jVar.q = this.f4838p;
        if ((this.g & 512) == 512) {
            this.q = Collections.unmodifiableList(this.q);
            this.g &= -513;
        }
        jVar.s = this.q;
        if ((this.g & 1024) == 1024) {
            this.r = Collections.unmodifiableList(this.r);
            this.g &= -1025;
        }
        jVar.t = this.r;
        if ((this.g & 2048) == 2048) {
            this.s = Collections.unmodifiableList(this.s);
            this.g &= -2049;
        }
        jVar.u = this.s;
        if ((this.g & 4096) == 4096) {
            this.t = Collections.unmodifiableList(this.t);
            this.g &= -4097;
        }
        jVar.v = this.t;
        if ((this.g & SerializeOptions.SORT) == 8192) {
            this.u = Collections.unmodifiableList(this.u);
            this.g &= -8193;
        }
        jVar.w = this.u;
        if ((this.g & 16384) == 16384) {
            this.v = Collections.unmodifiableList(this.v);
            this.g &= -16385;
        }
        jVar.f4855x = this.v;
        if ((i2 & 32768) == 32768) {
            i7 |= 8;
        }
        jVar.z = this.w;
        if ((i2 & 65536) == 65536) {
            i7 |= 16;
        }
        jVar.f4842A = this.f4839x;
        if ((i2 & 131072) == 131072) {
            i7 |= 32;
        }
        jVar.B = this.y;
        if ((this.g & 262144) == 262144) {
            this.z = Collections.unmodifiableList(this.z);
            this.g &= -262145;
        }
        jVar.f4843C = this.z;
        if ((this.g & 524288) == 524288) {
            this.f4832A = Collections.unmodifiableList(this.f4832A);
            this.g &= -524289;
        }
        jVar.E = this.f4832A;
        if ((this.g & MediaDefs.Meta.SEF.SEF_MIN_SIZE) == 1048576) {
            this.B = Collections.unmodifiableList(this.B);
            this.g &= -1048577;
        }
        jVar.f4844F = this.B;
        if ((i2 & 2097152) == 2097152) {
            i7 |= 64;
        }
        jVar.f4846H = this.f4833C;
        if ((this.g & OCDHelperConstant.TEMP_TO_CHECK_OBJECT_CAPTURE) == 4194304) {
            this.D = Collections.unmodifiableList(this.D);
            this.g &= -4194305;
        }
        jVar.f4847I = this.D;
        if ((i2 & 8388608) == 8388608) {
            i7 |= 128;
        }
        jVar.f4848J = this.E;
        jVar.f = i7;
        return jVar;
    }

    public final void g(C1157j jVar) {
        e0 e0Var;
        X x9;
        Q q10;
        if (jVar != C1157j.f4840M) {
            int i2 = jVar.f;
            if ((i2 & 1) == 1) {
                int i7 = jVar.g;
                this.g = 1 | this.g;
                this.f4834h = i7;
            }
            if ((i2 & 2) == 2) {
                int i8 = jVar.f4850h;
                this.g = 2 | this.g;
                this.f4835i = i8;
            }
            if ((i2 & 4) == 4) {
                int i10 = jVar.f4851i;
                this.g = 4 | this.g;
                this.f4836j = i10;
            }
            if (!jVar.f4852j.isEmpty()) {
                if (this.k.isEmpty()) {
                    this.k = jVar.f4852j;
                    this.g &= -9;
                } else {
                    if ((this.g & 8) != 8) {
                        this.k = new ArrayList(this.k);
                        this.g |= 8;
                    }
                    this.k.addAll(jVar.f4852j);
                }
            }
            if (!jVar.k.isEmpty()) {
                if (this.l.isEmpty()) {
                    this.l = jVar.k;
                    this.g &= -17;
                } else {
                    if ((this.g & 16) != 16) {
                        this.l = new ArrayList(this.l);
                        this.g |= 16;
                    }
                    this.l.addAll(jVar.k);
                }
            }
            if (!jVar.l.isEmpty()) {
                if (this.m.isEmpty()) {
                    this.m = jVar.l;
                    this.g &= -33;
                } else {
                    if ((this.g & 32) != 32) {
                        this.m = new ArrayList(this.m);
                        this.g |= 32;
                    }
                    this.m.addAll(jVar.l);
                }
            }
            if (!jVar.n.isEmpty()) {
                if (this.n.isEmpty()) {
                    this.n = jVar.n;
                    this.g &= -65;
                } else {
                    if ((this.g & 64) != 64) {
                        this.n = new ArrayList(this.n);
                        this.g |= 64;
                    }
                    this.n.addAll(jVar.n);
                }
            }
            if (!jVar.f4854p.isEmpty()) {
                if (this.f4837o.isEmpty()) {
                    this.f4837o = jVar.f4854p;
                    this.g &= -129;
                } else {
                    if ((this.g & 128) != 128) {
                        this.f4837o = new ArrayList(this.f4837o);
                        this.g |= 128;
                    }
                    this.f4837o.addAll(jVar.f4854p);
                }
            }
            if (!jVar.q.isEmpty()) {
                if (this.f4838p.isEmpty()) {
                    this.f4838p = jVar.q;
                    this.g &= -257;
                } else {
                    if ((this.g & 256) != 256) {
                        this.f4838p = new ArrayList(this.f4838p);
                        this.g |= 256;
                    }
                    this.f4838p.addAll(jVar.q);
                }
            }
            if (!jVar.s.isEmpty()) {
                if (this.q.isEmpty()) {
                    this.q = jVar.s;
                    this.g &= -513;
                } else {
                    if ((this.g & 512) != 512) {
                        this.q = new ArrayList(this.q);
                        this.g |= 512;
                    }
                    this.q.addAll(jVar.s);
                }
            }
            if (!jVar.t.isEmpty()) {
                if (this.r.isEmpty()) {
                    this.r = jVar.t;
                    this.g &= -1025;
                } else {
                    if ((this.g & 1024) != 1024) {
                        this.r = new ArrayList(this.r);
                        this.g |= 1024;
                    }
                    this.r.addAll(jVar.t);
                }
            }
            if (!jVar.u.isEmpty()) {
                if (this.s.isEmpty()) {
                    this.s = jVar.u;
                    this.g &= -2049;
                } else {
                    if ((this.g & 2048) != 2048) {
                        this.s = new ArrayList(this.s);
                        this.g |= 2048;
                    }
                    this.s.addAll(jVar.u);
                }
            }
            if (!jVar.v.isEmpty()) {
                if (this.t.isEmpty()) {
                    this.t = jVar.v;
                    this.g &= -4097;
                } else {
                    if ((this.g & 4096) != 4096) {
                        this.t = new ArrayList(this.t);
                        this.g |= 4096;
                    }
                    this.t.addAll(jVar.v);
                }
            }
            if (!jVar.w.isEmpty()) {
                if (this.u.isEmpty()) {
                    this.u = jVar.w;
                    this.g &= -8193;
                } else {
                    if ((this.g & SerializeOptions.SORT) != 8192) {
                        this.u = new ArrayList(this.u);
                        this.g |= SerializeOptions.SORT;
                    }
                    this.u.addAll(jVar.w);
                }
            }
            if (!jVar.f4855x.isEmpty()) {
                if (this.v.isEmpty()) {
                    this.v = jVar.f4855x;
                    this.g &= -16385;
                } else {
                    if ((this.g & 16384) != 16384) {
                        this.v = new ArrayList(this.v);
                        this.g |= 16384;
                    }
                    this.v.addAll(jVar.f4855x);
                }
            }
            int i11 = jVar.f;
            if ((i11 & 8) == 8) {
                int i12 = jVar.z;
                this.g |= 32768;
                this.w = i12;
            }
            if ((i11 & 16) == 16) {
                Q q11 = jVar.f4842A;
                if ((this.g & 65536) != 65536 || (q10 = this.f4839x) == Q.w) {
                    this.f4839x = q11;
                } else {
                    P p6 = Q.p(q10);
                    p6.g(q11);
                    this.f4839x = p6.e();
                }
                this.g |= 65536;
            }
            if ((jVar.f & 32) == 32) {
                int i13 = jVar.B;
                this.g |= 131072;
                this.y = i13;
            }
            if (!jVar.f4843C.isEmpty()) {
                if (this.z.isEmpty()) {
                    this.z = jVar.f4843C;
                    this.g &= -262145;
                } else {
                    if ((this.g & 262144) != 262144) {
                        this.z = new ArrayList(this.z);
                        this.g |= 262144;
                    }
                    this.z.addAll(jVar.f4843C);
                }
            }
            if (!jVar.E.isEmpty()) {
                if (this.f4832A.isEmpty()) {
                    this.f4832A = jVar.E;
                    this.g &= -524289;
                } else {
                    if ((this.g & 524288) != 524288) {
                        this.f4832A = new ArrayList(this.f4832A);
                        this.g |= 524288;
                    }
                    this.f4832A.addAll(jVar.E);
                }
            }
            if (!jVar.f4844F.isEmpty()) {
                if (this.B.isEmpty()) {
                    this.B = jVar.f4844F;
                    this.g &= -1048577;
                } else {
                    if ((this.g & MediaDefs.Meta.SEF.SEF_MIN_SIZE) != 1048576) {
                        this.B = new ArrayList(this.B);
                        this.g |= MediaDefs.Meta.SEF.SEF_MIN_SIZE;
                    }
                    this.B.addAll(jVar.f4844F);
                }
            }
            if ((jVar.f & 64) == 64) {
                X x10 = jVar.f4846H;
                if ((this.g & 2097152) != 2097152 || (x9 = this.f4833C) == X.f4794j) {
                    this.f4833C = x10;
                } else {
                    C1153f g3 = X.g(x9);
                    g3.k(x10);
                    this.f4833C = g3.f();
                }
                this.g |= 2097152;
            }
            if (!jVar.f4847I.isEmpty()) {
                if (this.D.isEmpty()) {
                    this.D = jVar.f4847I;
                    this.g &= -4194305;
                } else {
                    if ((this.g & OCDHelperConstant.TEMP_TO_CHECK_OBJECT_CAPTURE) != 4194304) {
                        this.D = new ArrayList(this.D);
                        this.g |= OCDHelperConstant.TEMP_TO_CHECK_OBJECT_CAPTURE;
                    }
                    this.D.addAll(jVar.f4847I);
                }
            }
            if ((jVar.f & 128) == 128) {
                e0 e0Var2 = jVar.f4848J;
                if ((this.g & 8388608) != 8388608 || (e0Var = this.E) == e0.f4826h) {
                    this.E = e0Var2;
                } else {
                    C1160m mVar = new C1160m(2);
                    mVar.g = Collections.EMPTY_LIST;
                    mVar.l(e0Var);
                    mVar.l(e0Var2);
                    this.E = mVar.g();
                }
                this.g |= 8388608;
            }
            d(jVar);
            this.d = this.d.p(jVar.e);
        }
    }
}
