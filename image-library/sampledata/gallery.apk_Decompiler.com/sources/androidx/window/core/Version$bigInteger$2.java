package androidx.window.core;

import Ae.a;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.k;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Ljava/math/BigInteger;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Version$bigInteger$2 extends k implements a {
    final /* synthetic */ Version this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Version$bigInteger$2(Version version) {
        super(0);
        this.this$0 = version;
    }

    public final BigInteger invoke() {
        return BigInteger.valueOf((long) this.this$0.getMajor()).shiftLeft(32).or(BigInteger.valueOf((long) this.this$0.getMinor())).shiftLeft(32).or(BigInteger.valueOf((long) this.this$0.getPatch()));
    }
}
