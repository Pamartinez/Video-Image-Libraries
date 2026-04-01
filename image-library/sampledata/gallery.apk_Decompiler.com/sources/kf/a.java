package Kf;

import Hf.P;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends ArrayList implements f {
    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof P)) {
            return false;
        }
        return super.contains((P) obj);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof P)) {
            return -1;
        }
        return super.indexOf((P) obj);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof P)) {
            return -1;
        }
        return super.lastIndexOf((P) obj);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj instanceof P)) {
            return false;
        }
        return super.remove((P) obj);
    }
}
