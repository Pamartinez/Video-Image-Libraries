package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1194l;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0005H\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/work/impl/StartStopTokensImpl;", "Landroidx/work/impl/StartStopTokens;", "()V", "runs", "", "Landroidx/work/impl/model/WorkGenerationalId;", "Landroidx/work/impl/StartStopToken;", "contains", "", "id", "remove", "", "workSpecId", "", "tokenFor", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class StartStopTokensImpl implements StartStopTokens {
    private final Map<WorkGenerationalId, StartStopToken> runs = new LinkedHashMap();

    public boolean contains(WorkGenerationalId workGenerationalId) {
        j.e(workGenerationalId, "id");
        return this.runs.containsKey(workGenerationalId);
    }

    public StartStopToken remove(WorkGenerationalId workGenerationalId) {
        j.e(workGenerationalId, "id");
        return this.runs.remove(workGenerationalId);
    }

    public StartStopToken tokenFor(WorkGenerationalId workGenerationalId) {
        j.e(workGenerationalId, "id");
        Map<WorkGenerationalId, StartStopToken> map = this.runs;
        StartStopToken startStopToken = map.get(workGenerationalId);
        if (startStopToken == null) {
            startStopToken = new StartStopToken(workGenerationalId);
            map.put(workGenerationalId, startStopToken);
        }
        return startStopToken;
    }

    public List<StartStopToken> remove(String str) {
        j.e(str, "workSpecId");
        Map<WorkGenerationalId, StartStopToken> map = this.runs;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : map.entrySet()) {
            if (j.a(((WorkGenerationalId) next.getKey()).getWorkSpecId(), str)) {
                linkedHashMap.put(next.getKey(), next.getValue());
            }
        }
        for (WorkGenerationalId remove : linkedHashMap.keySet()) {
            this.runs.remove(remove);
        }
        return C1194l.k1(linkedHashMap.values());
    }
}
