package androidx.work;

import Ae.b;
import i.C0212a;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010&\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "<name for destructuring parameter 0>", "", "", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Data$toString$1$content$1 extends k implements b {
    public static final Data$toString$1$content$1 INSTANCE = new Data$toString$1$content$1();

    public Data$toString$1$content$1() {
        super(1);
    }

    public final CharSequence invoke(Map.Entry<String, ? extends Object> entry) {
        j.e(entry, "<name for destructuring parameter 0>");
        Object value = entry.getValue();
        StringBuilder t = C0212a.t(entry.getKey(), " : ");
        if (value instanceof Object[]) {
            value = Arrays.toString((Object[]) value);
            j.d(value, "toString(this)");
        }
        t.append(value);
        return t.toString();
    }
}
