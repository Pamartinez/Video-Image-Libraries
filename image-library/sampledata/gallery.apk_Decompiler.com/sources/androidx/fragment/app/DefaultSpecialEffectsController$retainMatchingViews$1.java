package androidx.fragment.app;

import Ae.b;
import android.view.View;
import androidx.core.view.ViewCompat;
import java.util.Collection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;
import ne.C1194l;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010'\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\"\u0010\u0002\u001a\u001e\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00060\u00060\u0003H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", "entry", "", "", "kotlin.jvm.PlatformType", "Landroid/view/View;", "invoke", "(Ljava/util/Map$Entry;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultSpecialEffectsController$retainMatchingViews$1 extends k implements b {
    final /* synthetic */ Collection<String> $names;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultSpecialEffectsController$retainMatchingViews$1(Collection<String> collection) {
        super(1);
        this.$names = collection;
    }

    public final Boolean invoke(Map.Entry<String, View> entry) {
        j.e(entry, "entry");
        return Boolean.valueOf(C1194l.G0(this.$names, ViewCompat.getTransitionName(entry.getValue())));
    }
}
