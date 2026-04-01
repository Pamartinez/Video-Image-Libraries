package ge;

import D1.f;
import E2.k;
import Gd.a;
import He.F;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import io.grpc.MethodDescriptor;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class N0 {

    /* renamed from: a  reason: collision with root package name */
    public final L0 f4462a;
    public final Map b;

    /* renamed from: c  reason: collision with root package name */
    public final Map f4463c;
    public final w1 d;
    public final Object e;
    public final Map f;

    public N0(L0 l0, HashMap hashMap, HashMap hashMap2, w1 w1Var, Object obj, Map map) {
        Map map2;
        this.f4462a = l0;
        this.b = Collections.unmodifiableMap(new HashMap(hashMap));
        this.f4463c = Collections.unmodifiableMap(new HashMap(hashMap2));
        this.d = w1Var;
        this.e = obj;
        if (map != null) {
            map2 = Collections.unmodifiableMap(new HashMap(map));
        } else {
            map2 = null;
        }
        this.f = map2;
    }

    public static N0 a(Map map, boolean z, int i2, int i7, Object obj) {
        w1 w1Var;
        Map map2;
        boolean z3;
        w1 w1Var2;
        Map g;
        boolean z7;
        boolean z9;
        Map map3 = map;
        boolean z10 = z;
        if (z10) {
            if (map3 == null || (g = C1043m0.g("retryThrottling", map3)) == null) {
                w1Var2 = null;
            } else {
                float floatValue = C1043m0.e("maxTokens", g).floatValue();
                float floatValue2 = C1043m0.e("tokenRatio", g).floatValue();
                if (floatValue > 0.0f) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                F.t(z7, "maxToken should be greater than zero");
                if (floatValue2 > 0.0f) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                F.t(z9, "tokenRatio should be greater than zero");
                w1Var2 = new w1(floatValue, floatValue2);
            }
            w1Var = w1Var2;
        } else {
            w1Var = null;
        }
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        if (map3 == null) {
            map2 = null;
        } else {
            map2 = C1043m0.g("healthCheckConfig", map3);
        }
        List<Map> c5 = C1043m0.c("methodConfig", map3);
        if (c5 == null) {
            c5 = null;
        } else {
            C1043m0.a(c5);
        }
        if (c5 == null) {
            return new N0((L0) null, hashMap, hashMap2, w1Var, obj, map2);
        }
        L0 l0 = null;
        for (Map map4 : c5) {
            L0 l02 = new L0(map4, z10, i2, i7);
            List<Map> c6 = C1043m0.c("name", map4);
            if (c6 == null) {
                c6 = null;
            } else {
                C1043m0.a(c6);
            }
            if (c6 != null && !c6.isEmpty()) {
                for (Map map5 : c6) {
                    String h5 = C1043m0.h(ServiceManagerUtil.PHOTO_EDIT_EXTRA_SERVICE_KEY, map5);
                    String h6 = C1043m0.h("method", map5);
                    if (a.h0(h5)) {
                        F.h("missing service name for method %s", h6, a.h0(h6));
                        if (l0 == null) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        F.h("Duplicate default method config in service config %s", map3, z3);
                        l0 = l02;
                    } else if (a.h0(h6)) {
                        F.h("Duplicate service %s", h5, !hashMap2.containsKey(h5));
                        hashMap2.put(h5, l02);
                    } else {
                        String a7 = MethodDescriptor.a(h5, h6);
                        F.h("Duplicate method name %s", a7, !hashMap.containsKey(a7));
                        hashMap.put(a7, l02);
                    }
                }
            }
        }
        return new N0(l0, hashMap, hashMap2, w1Var, obj, map2);
    }

    public final M0 b() {
        if (!this.f4463c.isEmpty() || !this.b.isEmpty() || this.f4462a != null) {
            return new M0(this);
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && N0.class == obj.getClass()) {
            N0 n02 = (N0) obj;
            if (!f.p(this.f4462a, n02.f4462a) || !f.p(this.b, n02.b) || !f.p(this.f4463c, n02.f4463c) || !f.p(this.d, n02.d) || !f.p(this.e, n02.e)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4462a, this.b, this.f4463c, this.d, this.e});
    }

    public final String toString() {
        k V = B1.a.V(this);
        V.a(this.f4462a, "defaultMethodConfig");
        V.a(this.b, "serviceMethodMap");
        V.a(this.f4463c, "serviceMap");
        V.a(this.d, "retryThrottling");
        V.a(this.e, "loadBalancingConfig");
        return V.toString();
    }
}
