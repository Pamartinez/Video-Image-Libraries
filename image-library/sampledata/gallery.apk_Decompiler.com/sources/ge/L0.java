package ge;

import D1.f;
import E2.k;
import He.F;
import L2.a;
import com.samsung.android.gallery.support.utils.MapUtil;
import ee.Y;
import io.grpc.CallOptions;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class L0 {
    public static final CallOptions.Key g = new CallOptions.Key("io.grpc.internal.ManagedChannelServiceConfig.MethodInfo");

    /* renamed from: a  reason: collision with root package name */
    public final Long f4457a;
    public final Boolean b;

    /* renamed from: c  reason: collision with root package name */
    public final Integer f4458c;
    public final Integer d;
    public final x1 e;
    public final C1007a0 f;

    public L0(Map map, boolean z, int i2, int i7) {
        Map map2;
        long j2;
        boolean z3;
        x1 x1Var;
        Map map3;
        C1007a0 a0Var;
        boolean z7;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        boolean z14;
        boolean z15;
        boolean z16;
        boolean z17;
        boolean z18;
        Map map4 = map;
        this.f4457a = C1043m0.i("timeout", map4);
        this.b = C1043m0.b("waitForReady", map4);
        Integer f5 = C1043m0.f("maxResponseMessageBytes", map4);
        this.f4458c = f5;
        if (f5 != null) {
            if (f5.intValue() >= 0) {
                z18 = true;
            } else {
                z18 = false;
            }
            F.h("maxInboundMessageSize %s exceeds bounds", f5, z18);
        }
        Integer f8 = C1043m0.f("maxRequestMessageBytes", map4);
        this.d = f8;
        if (f8 != null) {
            if (f8.intValue() >= 0) {
                z17 = true;
            } else {
                z17 = false;
            }
            F.h("maxOutboundMessageSize %s exceeds bounds", f8, z17);
        }
        if (z) {
            map2 = C1043m0.g("retryPolicy", map4);
        } else {
            map2 = null;
        }
        if (map2 == null) {
            j2 = 0;
            x1Var = null;
            z3 = true;
        } else {
            Integer f10 = C1043m0.f("maxAttempts", map2);
            F.n(f10, "maxAttempts cannot be empty");
            int intValue = f10.intValue();
            if (intValue >= 2) {
                z10 = true;
            } else {
                z10 = false;
            }
            F.g(intValue, "maxAttempts must be greater than 1: %s", z10);
            int min = Math.min(intValue, i2);
            Long i8 = C1043m0.i("initialBackoff", map2);
            F.n(i8, "initialBackoff cannot be empty");
            long longValue = i8.longValue();
            if (longValue > 0) {
                z11 = true;
            } else {
                z11 = false;
            }
            F.k(z11, "initialBackoffNanos must be greater than 0: %s", longValue);
            Long i10 = C1043m0.i("maxBackoff", map2);
            F.n(i10, "maxBackoff cannot be empty");
            long longValue2 = i10.longValue();
            if (longValue2 > 0) {
                z12 = true;
            } else {
                z12 = false;
            }
            j2 = 0;
            z3 = true;
            F.k(z12, "maxBackoff must be greater than 0: %s", longValue2);
            Double e7 = C1043m0.e("backoffMultiplier", map2);
            F.n(e7, "backoffMultiplier cannot be empty");
            double doubleValue = e7.doubleValue();
            if (doubleValue > MapUtil.INVALID_LOCATION) {
                z13 = true;
            } else {
                z13 = false;
            }
            F.h("backoffMultiplier must be greater than 0: %s", e7, z13);
            Long i11 = C1043m0.i("perAttemptRecvTimeout", map2);
            if (i11 == null || i11.longValue() >= 0) {
                z14 = true;
            } else {
                z14 = false;
            }
            F.h("perAttemptRecvTimeout cannot be negative: %s", i11, z14);
            Set a7 = I1.a("retryableStatusCodes", map2);
            if (a7 != null) {
                z15 = true;
            } else {
                z15 = false;
            }
            a.G("%s is required in retry policy", "retryableStatusCodes", z15);
            a.G("%s must not contain OK", "retryableStatusCodes", !a7.contains(Y.OK));
            if (i11 != null || !a7.isEmpty()) {
                z16 = true;
            } else {
                z16 = false;
            }
            F.i("retryableStatusCodes cannot be empty without perAttemptRecvTimeout", z16);
            x1Var = new x1(min, longValue, longValue2, doubleValue, i11, a7);
        }
        this.e = x1Var;
        if (z) {
            map3 = C1043m0.g("hedgingPolicy", map4);
        } else {
            map3 = null;
        }
        if (map3 == null) {
            a0Var = null;
        } else {
            Integer f11 = C1043m0.f("maxAttempts", map3);
            F.n(f11, "maxAttempts cannot be empty");
            int intValue2 = f11.intValue();
            if (intValue2 >= 2) {
                z7 = z3;
            } else {
                z7 = false;
            }
            F.g(intValue2, "maxAttempts must be greater than 1: %s", z7);
            int min2 = Math.min(intValue2, i7);
            Long i12 = C1043m0.i("hedgingDelay", map3);
            F.n(i12, "hedgingDelay cannot be empty");
            long longValue3 = i12.longValue();
            if (longValue3 >= j2) {
                z9 = z3;
            } else {
                z9 = false;
            }
            F.k(z9, "hedgingDelay must not be negative: %s", longValue3);
            Set<T> a10 = I1.a("nonFatalStatusCodes", map3);
            if (a10 == null) {
                a10 = Collections.unmodifiableSet(EnumSet.noneOf(Y.class));
            } else {
                a.G("%s must not contain OK", "nonFatalStatusCodes", !a10.contains(Y.OK));
            }
            a0Var = new C1007a0(min2, longValue3, a10);
        }
        this.f = a0Var;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof L0)) {
            return false;
        }
        L0 l0 = (L0) obj;
        if (!f.p(this.f4457a, l0.f4457a) || !f.p(this.b, l0.b) || !f.p(this.f4458c, l0.f4458c) || !f.p(this.d, l0.d) || !f.p(this.e, l0.e) || !f.p(this.f, l0.f)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4457a, this.b, this.f4458c, this.d, this.e, this.f});
    }

    public final String toString() {
        k V = B1.a.V(this);
        V.a(this.f4457a, "timeoutNanos");
        V.a(this.b, "waitForReady");
        V.a(this.f4458c, "maxInboundMessageSize");
        V.a(this.d, "maxOutboundMessageSize");
        V.a(this.e, "retryPolicy");
        V.a(this.f, "hedgingPolicy");
        return V.toString();
    }
}
