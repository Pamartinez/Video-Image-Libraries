package androidx.work;

import androidx.work.Data;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016¨\u0006\u0007"}, d2 = {"Landroidx/work/OverwritingInputMerger;", "Landroidx/work/InputMerger;", "()V", "merge", "Landroidx/work/Data;", "inputs", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class OverwritingInputMerger extends InputMerger {
    public Data merge(List<Data> list) {
        j.e(list, "inputs");
        Data.Builder builder = new Data.Builder();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Data keyValueMap : list) {
            linkedHashMap.putAll(keyValueMap.getKeyValueMap());
        }
        builder.putAll((Map<String, ? extends Object>) linkedHashMap);
        return builder.build();
    }
}
