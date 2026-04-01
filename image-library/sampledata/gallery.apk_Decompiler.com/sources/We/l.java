package We;

import Ae.b;
import He.C0750f;
import java.lang.reflect.Member;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l extends g implements b {
    public static final l d = new g(1);

    public final String getName() {
        return "isSynthetic";
    }

    public final C0750f getOwner() {
        return v.f4727a.b(Member.class);
    }

    public final String getSignature() {
        return "isSynthetic()Z";
    }

    public final Object invoke(Object obj) {
        Member member = (Member) obj;
        j.e(member, "p0");
        return Boolean.valueOf(member.isSynthetic());
    }
}
