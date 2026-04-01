package ag;

import java.util.concurrent.CompletionException;
import java.util.function.BiFunction;
import me.x;
import qe.C1227c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements BiFunction {
    public volatile C1227c cont;

    public final Object apply(Object obj, Object obj2) {
        CompletionException completionException;
        Throwable cause;
        Throwable th = (Throwable) obj2;
        C1227c cVar = this.cont;
        if (cVar != null) {
            if (th == null) {
                cVar.resumeWith(obj);
            } else {
                if (th instanceof CompletionException) {
                    completionException = (CompletionException) th;
                } else {
                    completionException = null;
                }
                if (!(completionException == null || (cause = completionException.getCause()) == null)) {
                    th = cause;
                }
                cVar.resumeWith(L2.a.l(th));
            }
        }
        return x.f4917a;
    }
}
