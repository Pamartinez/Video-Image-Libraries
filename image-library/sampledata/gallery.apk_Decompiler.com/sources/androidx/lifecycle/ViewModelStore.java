package androidx.lifecycle;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\rH\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\b¢\u0006\u0004\b\u0010\u0010\u0003R \u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u00118\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/lifecycle/ViewModelStore;", "", "<init>", "()V", "", "key", "Landroidx/lifecycle/ViewModel;", "viewModel", "Lme/x;", "put", "(Ljava/lang/String;Landroidx/lifecycle/ViewModel;)V", "get", "(Ljava/lang/String;)Landroidx/lifecycle/ViewModel;", "", "keys", "()Ljava/util/Set;", "clear", "", "map", "Ljava/util/Map;", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewModelStore {
    private final Map<String, ViewModel> map = new LinkedHashMap();

    public final void clear() {
        for (ViewModel clear : this.map.values()) {
            clear.clear();
        }
        this.map.clear();
    }

    public final ViewModel get(String str) {
        j.e(str, "key");
        return this.map.get(str);
    }

    public final Set<String> keys() {
        return new HashSet(this.map.keySet());
    }

    public final void put(String str, ViewModel viewModel) {
        j.e(str, "key");
        j.e(viewModel, "viewModel");
        ViewModel put = this.map.put(str, viewModel);
        if (put != null) {
            put.onCleared();
        }
    }
}
