package Tf;

import Ae.b;
import java.util.regex.Matcher;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l extends h implements b {
    public static final l d = new h(1, h.class, "next", "next()Lkotlin/text/MatchResult;", 0);

    public final Object invoke(Object obj) {
        int i2;
        h hVar = (h) obj;
        j.e(hVar, "p0");
        j jVar = (j) hVar;
        CharSequence charSequence = jVar.b;
        Matcher matcher = jVar.f3823a;
        int end = matcher.end();
        if (matcher.end() == matcher.start()) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i7 = end + i2;
        if (i7 > charSequence.length()) {
            return null;
        }
        Matcher matcher2 = matcher.pattern().matcher(charSequence);
        j.d(matcher2, "matcher(...)");
        if (!matcher2.find(i7)) {
            return null;
        }
        return new j(matcher2, charSequence);
    }
}
