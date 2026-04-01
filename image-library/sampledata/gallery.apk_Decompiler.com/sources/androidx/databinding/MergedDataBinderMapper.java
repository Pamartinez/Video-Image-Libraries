package androidx.databinding;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MergedDataBinderMapper extends DataBinderMapper {
    private Set<Class<? extends DataBinderMapper>> mExistingMappers = new HashSet();
    private List<String> mFeatureBindingMappers = new CopyOnWriteArrayList();
    private List<DataBinderMapper> mMappers = new CopyOnWriteArrayList();
}
