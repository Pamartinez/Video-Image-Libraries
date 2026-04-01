package androidx.room;

import Xf.i;
import androidx.room.InvalidationTracker;
import java.util.Set;
import kotlin.Metadata;
import me.x;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001d\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"androidx/room/CoroutinesRoom$Companion$createFlow$1$1$observer$1", "Landroidx/room/InvalidationTracker$Observer;", "", "", "tables", "Lme/x;", "onInvalidated", "(Ljava/util/Set;)V", "room-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CoroutinesRoom$Companion$createFlow$1$1$observer$1 extends InvalidationTracker.Observer {
    final /* synthetic */ i $observerChannel;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CoroutinesRoom$Companion$createFlow$1$1$observer$1(String[] strArr, i iVar) {
        super(strArr);
        this.$observerChannel = iVar;
    }

    public void onInvalidated(Set<String> set) {
        this.$observerChannel.e(x.f4917a);
    }
}
