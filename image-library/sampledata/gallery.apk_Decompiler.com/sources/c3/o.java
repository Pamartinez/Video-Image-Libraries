package C3;

import com.samsung.android.gallery.app.activity.VersionUpdateHandler;
import com.samsung.android.gallery.database.dal.local.table.LocalTable;
import java.util.HashSet;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2275a;
    public final /* synthetic */ HashSet b;

    public /* synthetic */ o(HashSet hashSet, int i2) {
        this.f2275a = i2;
        this.b = hashSet;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2275a;
        HashSet hashSet = this.b;
        switch (i2) {
            case 0:
                return VersionUpdateHandler.lambda$updateVersion15$0(hashSet, (String) obj);
            case 1:
                return hashSet.contains((String) obj);
            case 2:
                return LocalTable.lambda$restore$4(hashSet, (String) obj);
            default:
                return hashSet.contains((Long) obj);
        }
    }
}
