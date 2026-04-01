package Tf;

import java.io.Serializable;
import java.util.regex.Pattern;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m implements Serializable {
    public final /* synthetic */ int d = 1;
    public Serializable e;

    public /* synthetic */ m() {
    }

    public String toString() {
        switch (this.d) {
            case 0:
                String pattern = ((Pattern) this.e).toString();
                j.d(pattern, "toString(...)");
                return pattern;
            default:
                return super.toString();
        }
    }

    public m(String str) {
        Pattern compile = Pattern.compile(str);
        j.d(compile, "compile(...)");
        this.e = compile;
    }
}
