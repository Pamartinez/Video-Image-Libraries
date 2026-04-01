package Te;

import Ae.a;
import Bf.c;
import Hf.C0754c;
import Hf.C0774x;
import Hf.T;
import Hf.X;
import Hf.d0;
import Qe.A;
import Qe.C0812b;
import Qe.C0813c;
import Qe.C0822l;
import Qe.C0826p;
import Qe.C0827q;
import Qe.O;
import Qe.Q;
import Qe.S;
import Qf.k;
import Re.h;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import k6.b;
import kotlin.jvm.internal.j;
import n2.C0238a;
import n2.C0239b;
import n2.C0240c;
import n2.C0241d;
import n2.C0242e;
import ne.C1195m;
import qf.C1240g;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class G {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3751a;
    public Object b;

    /* renamed from: c  reason: collision with root package name */
    public Object f3752c;
    public Object d;
    public Object e;
    public Object f;
    public Object g;

    /* renamed from: h  reason: collision with root package name */
    public final Object f3753h;

    /* renamed from: i  reason: collision with root package name */
    public final Object f3754i;

    /* renamed from: j  reason: collision with root package name */
    public final Object f3755j;
    public final Object k;

    public G(RectF rectF) {
        this.b = new RectF();
        SpringAnimation springAnimation = new SpringAnimation(rectF, new C0238a(this, this, 0));
        SpringForce springForce = new SpringForce(rectF.left * 100.0f);
        springForce.setDampingRatio(1.0f);
        springForce.setStiffness(1500.0f);
        springAnimation.setSpring(springForce);
        springAnimation.addEndListener(new C0239b(this));
        this.f3752c = springAnimation;
        SpringAnimation springAnimation2 = new SpringAnimation(rectF, new C0238a(this, this, 1));
        SpringForce springForce2 = new SpringForce(rectF.top * 100.0f);
        springForce2.setDampingRatio(1.0f);
        springForce2.setStiffness(1500.0f);
        springAnimation2.setSpring(springForce2);
        springAnimation2.addEndListener(new C0240c(this));
        this.d = springAnimation2;
        SpringAnimation springAnimation3 = new SpringAnimation(rectF, new C0238a(this, this, 2));
        SpringForce springForce3 = new SpringForce(rectF.right * 100.0f);
        springForce3.setDampingRatio(1.0f);
        springForce3.setStiffness(1500.0f);
        springAnimation3.setSpring(springForce3);
        springAnimation3.addEndListener(new C0241d(this));
        this.e = springAnimation3;
        SpringAnimation springAnimation4 = new SpringAnimation(rectF, new C0238a(this, this, 3));
        SpringForce springForce4 = new SpringForce(rectF.bottom * 100.0f);
        springForce4.setDampingRatio(1.0f);
        springForce4.setStiffness(1500.0f);
        springAnimation4.setSpring(springForce4);
        springAnimation4.addEndListener(new C0242e(this));
        this.f = springAnimation4;
        this.g = C1195m.q0(springAnimation, springAnimation2, springAnimation3, springAnimation4);
        this.f3753h = new ArrayList();
        this.f3754i = new Handler(Looper.getMainLooper());
        this.f3755j = Collections.synchronizedList(new ArrayList());
        this.k = Collections.synchronizedList(new ArrayList());
    }

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        int i8 = i2;
        if (i8 == 1 || i8 == 2 || i8 == 3 || i8 == 5 || i8 == 7 || i8 == 9 || i8 == 11 || i8 == 19 || i8 == 13 || i8 == 14 || i8 == 16 || i8 == 17) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i8 == 1 || i8 == 2 || i8 == 3 || i8 == 5 || i8 == 7 || i8 == 9 || i8 == 11 || i8 == 19 || i8 == 13 || i8 == 14 || i8 == 16 || i8 == 17) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        switch (i8) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 7:
            case 9:
            case 11:
            case 13:
            case 14:
            case 16:
            case 17:
            case 19:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl$CopyConfiguration";
                break;
            case 4:
                objArr[0] = "type";
                break;
            case 6:
                objArr[0] = "modality";
                break;
            case 8:
                objArr[0] = "visibility";
                break;
            case 10:
                objArr[0] = "kind";
                break;
            case 12:
                objArr[0] = "typeParameters";
                break;
            case 15:
                objArr[0] = "substitution";
                break;
            case 18:
                objArr[0] = "name";
                break;
            default:
                objArr[0] = "owner";
                break;
        }
        if (i8 == 1) {
            objArr[1] = "setOwner";
        } else if (i8 == 2) {
            objArr[1] = "setOriginal";
        } else if (i8 == 3) {
            objArr[1] = "setPreserveSourceElement";
        } else if (i8 == 5) {
            objArr[1] = "setReturnType";
        } else if (i8 == 7) {
            objArr[1] = "setModality";
        } else if (i8 == 9) {
            objArr[1] = "setVisibility";
        } else if (i8 == 11) {
            objArr[1] = "setKind";
        } else if (i8 == 19) {
            objArr[1] = "setName";
        } else if (i8 == 13) {
            objArr[1] = "setTypeParameters";
        } else if (i8 == 14) {
            objArr[1] = "setDispatchReceiverParameter";
        } else if (i8 == 16) {
            objArr[1] = "setSubstitution";
        } else if (i8 != 17) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl$CopyConfiguration";
        } else {
            objArr[1] = "setCopyOverrides";
        }
        switch (i8) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 7:
            case 9:
            case 11:
            case 13:
            case 14:
            case 16:
            case 17:
            case 19:
                break;
            case 4:
                objArr[2] = "setReturnType";
                break;
            case 6:
                objArr[2] = "setModality";
                break;
            case 8:
                objArr[2] = "setVisibility";
                break;
            case 10:
                objArr[2] = "setKind";
                break;
            case 12:
                objArr[2] = "setTypeParameters";
                break;
            case 15:
                objArr[2] = "setSubstitution";
                break;
            case 18:
                objArr[2] = "setName";
                break;
            default:
                objArr[2] = "setOwner";
                break;
        }
        String format = String.format(str, objArr);
        if (i8 == 1 || i8 == 2 || i8 == 3 || i8 == 5 || i8 == 7 || i8 == 9 || i8 == 11 || i8 == 19 || i8 == 13 || i8 == 14 || i8 == 16 || i8 == 17) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public static final void b(G g3) {
        Iterable<SpringAnimation> iterable = (List) g3.g;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            for (SpringAnimation isRunning : iterable) {
                if (isRunning.isRunning()) {
                    return;
                }
            }
        }
        List<a> list = (List) g3.k;
        j.d(list, "endListeners");
        for (a invoke : list) {
            invoke.invoke();
        }
    }

    public static final void c(G g3, RectF rectF) {
        ((RectF) g3.b).set(rectF);
        if (!g3.f3751a) {
            g3.f3751a = true;
            ((Handler) g3.f3754i).post(new b(10, g3));
        }
    }

    public H d() {
        u uVar;
        u uVar2;
        I i2;
        J j2;
        r rVar;
        r rVar2;
        a aVar;
        J setter;
        C0774x xVar;
        I getter;
        u uVar3;
        u uVar4;
        H h5 = (H) this.k;
        H G02 = h5.G0((C0822l) this.b, (A) this.f3752c, (C0826p) this.d, (O) this.e, (C0813c) this.f, (C1240g) this.f3754i);
        List typeParameters = h5.getTypeParameters();
        ArrayList arrayList = new ArrayList(((ArrayList) typeParameters).size());
        X A10 = C0754c.A(typeParameters, (T) this.g, G02, arrayList);
        C0774x xVar2 = (C0774x) this.f3755j;
        C0774x i7 = A10.i(xVar2, d0.OUT_VARIANCE);
        if (i7 != null) {
            d0 d0Var = d0.IN_VARIANCE;
            C0774x i8 = A10.i(xVar2, d0Var);
            if (i8 != null) {
                G02.K0(i8);
            }
            u uVar5 = (u) this.f3753h;
            if (uVar5 != null) {
                u F02 = uVar5.c(A10);
                if (F02 != null) {
                    uVar = F02;
                }
            } else {
                uVar = null;
            }
            u uVar6 = h5.y;
            if (uVar6 != null) {
                C0774x i10 = A10.i(uVar6.getType(), d0Var);
                if (i10 == null) {
                    uVar4 = null;
                } else {
                    uVar6.E0();
                    uVar4 = new u(G02, new c(G02, i10), uVar6.getAnnotations());
                }
                uVar2 = uVar4;
            } else {
                uVar2 = null;
            }
            ArrayList arrayList2 = new ArrayList();
            for (u uVar7 : h5.w) {
                C0774x i11 = A10.i(uVar7.getType(), d0.IN_VARIANCE);
                if (i11 == null) {
                    uVar3 = null;
                } else {
                    C1240g C02 = ((Bf.b) uVar7.E0()).C0();
                    uVar7.E0();
                    uVar3 = new u(G02, new Bf.b((C0812b) G02, i11, C02), uVar7.getAnnotations());
                }
                if (uVar3 != null) {
                    arrayList2.add(uVar3);
                }
            }
            H h6 = G02;
            h6.L0(i7, arrayList, uVar, uVar2, arrayList2);
            H h8 = h6;
            I i12 = h5.f3756A;
            S s = Q.f3662a;
            if (i12 == null) {
                i2 = null;
            } else {
                h annotations = i12.getAnnotations();
                A a7 = (A) this.f3752c;
                C0826p visibility = h5.f3756A.getVisibility();
                if (((C0813c) this.f) == C0813c.FAKE_OVERRIDE && C0827q.e(C0827q.f(visibility.f3674a.c()))) {
                    visibility = C0827q.f3677h;
                }
                C0826p pVar = visibility;
                I i13 = h5.f3756A;
                boolean z = i13.f3747i;
                boolean z3 = i13.f3748j;
                boolean z7 = i13.m;
                C0813c cVar = (C0813c) this.f;
                O o2 = (O) this.e;
                if (o2 == null) {
                    getter = null;
                } else {
                    getter = o2.getGetter();
                }
                i2 = new I(h8, annotations, a7, pVar, z, z3, z7, cVar, getter, s);
            }
            if (i2 != null) {
                I i14 = h5.f3756A;
                C0774x xVar3 = i14.q;
                i2.f3750p = H.H0(A10, i14);
                if (xVar3 != null) {
                    xVar = A10.i(xVar3, d0.OUT_VARIANCE);
                } else {
                    xVar = null;
                }
                i2.H0(xVar);
            }
            J j3 = h5.B;
            if (j3 == null) {
                j2 = null;
            } else {
                h annotations2 = j3.getAnnotations();
                A a10 = (A) this.f3752c;
                C0826p visibility2 = h5.B.getVisibility();
                if (((C0813c) this.f) == C0813c.FAKE_OVERRIDE && C0827q.e(C0827q.f(visibility2.f3674a.c()))) {
                    visibility2 = C0827q.f3677h;
                }
                C0826p pVar2 = visibility2;
                F f5 = h5.B;
                boolean z9 = f5.f3747i;
                boolean z10 = f5.f3748j;
                boolean z11 = f5.m;
                C0813c cVar2 = (C0813c) this.f;
                O o3 = (O) this.e;
                if (o3 == null) {
                    setter = null;
                } else {
                    setter = o3.getSetter();
                }
                j2 = new J(h8, annotations2, a10, pVar2, z9, z10, z11, cVar2, setter, s);
            }
            if (j2 != null) {
                List I02 = t.I0(j2, h5.B.B(), A10, false, false, (boolean[]) null);
                if (I02 == null) {
                    I02 = Collections.singletonList(J.G0(j2, C1353d.e((C0822l) this.b).n(), ((Q) h5.B.B().get(0)).getAnnotations()));
                }
                if (I02.size() == 1) {
                    j2.f3750p = H.H0(A10, h5.B);
                    Q q = (Q) I02.get(0);
                    if (q != null) {
                        j2.q = q;
                    } else {
                        J.w0(6);
                        throw null;
                    }
                } else {
                    throw new IllegalStateException();
                }
            }
            r rVar3 = h5.f3757C;
            if (rVar3 == null) {
                rVar = null;
            } else {
                rVar = new r(rVar3.getAnnotations(), h8);
            }
            r rVar4 = h5.D;
            if (rVar4 == null) {
                rVar2 = null;
            } else {
                rVar2 = new r(rVar4.getAnnotations(), h8);
            }
            h8.I0(i2, j2, rVar, rVar2);
            if (this.f3751a) {
                int i15 = Qf.h.f;
                Qf.h e7 = k.e();
                for (O c5 : h5.h()) {
                    e7.add(c5.c(A10));
                }
                h8.f3759o = e7;
            }
            if (h5.U() && (aVar = h5.l) != null) {
                h8.J0(h5.k, aVar);
            }
            return h8;
        }
        return null;
    }

    public void e() {
        Float valueOf = Float.valueOf(1.0f);
        Float valueOf2 = Float.valueOf(361.0f);
        for (SpringAnimation springAnimation : (List) this.g) {
            Log.d("RectFAnimation", "setSpringForce " + springAnimation.isRunning() + ' ' + valueOf + ' ' + valueOf2);
            SpringForce spring = springAnimation.getSpring();
            spring.setStiffness(361.0f);
            spring.setDampingRatio(1.0f);
        }
    }

    public G(H h5) {
        this.k = h5;
        this.b = h5.g();
        this.f3752c = h5.k();
        this.d = h5.getVisibility();
        this.e = null;
        this.f = h5.b();
        this.g = T.f3436a;
        this.f3751a = true;
        this.f3753h = h5.f3761x;
        this.f3754i = h5.getName();
        this.f3755j = h5.getType();
    }
}
