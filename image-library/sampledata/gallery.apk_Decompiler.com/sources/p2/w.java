package P2;

import android.view.View;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class w implements Comparator {
    public final /* synthetic */ int d;

    public /* synthetic */ w(int i2) {
        this.d = i2;
    }

    public final int compare(Object obj, Object obj2) {
        switch (this.d) {
            case 0:
                return ((A) obj).b().compareTo(((A) obj2).b());
            default:
                return ((View) obj).getTop() - ((View) obj2).getTop();
        }
    }
}
