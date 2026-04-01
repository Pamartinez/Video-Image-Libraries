package nb;

import android.view.MenuItem;
import java.util.HashSet;
import java.util.function.Consumer;

/* renamed from: nb.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0702c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ HashSet e;

    public /* synthetic */ C0702c(HashSet hashSet, int i2) {
        this.d = i2;
        this.e = hashSet;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        HashSet hashSet = this.e;
        MenuItem menuItem = (MenuItem) obj;
        switch (i2) {
            case 0:
                hashSet.add(Integer.valueOf(menuItem.getItemId()));
                return;
            default:
                hashSet.add(Integer.valueOf(menuItem.getItemId()));
                return;
        }
    }
}
