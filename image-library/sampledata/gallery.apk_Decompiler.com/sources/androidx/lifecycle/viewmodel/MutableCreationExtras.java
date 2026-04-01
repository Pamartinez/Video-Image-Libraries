package androidx.lifecycle.viewmodel;

import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J,\u0010\n\u001a\u00020\t\"\u0004\b\u0000\u0010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0006\u0010\b\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\n\u0010\u000bJ&\u0010\f\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0002¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/lifecycle/viewmodel/MutableCreationExtras;", "Landroidx/lifecycle/viewmodel/CreationExtras;", "initialExtras", "<init>", "(Landroidx/lifecycle/viewmodel/CreationExtras;)V", "T", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", "key", "t", "Lme/x;", "set", "(Landroidx/lifecycle/viewmodel/CreationExtras$Key;Ljava/lang/Object;)V", "get", "(Landroidx/lifecycle/viewmodel/CreationExtras$Key;)Ljava/lang/Object;", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MutableCreationExtras extends CreationExtras {
    public MutableCreationExtras() {
        this((CreationExtras) null, 1, (e) null);
    }

    public <T> T get(CreationExtras.Key<T> key) {
        j.e(key, "key");
        return getMap$lifecycle_viewmodel_release().get(key);
    }

    public final <T> void set(CreationExtras.Key<T> key, T t) {
        j.e(key, "key");
        getMap$lifecycle_viewmodel_release().put(key, t);
    }

    public MutableCreationExtras(CreationExtras creationExtras) {
        j.e(creationExtras, "initialExtras");
        getMap$lifecycle_viewmodel_release().putAll(creationExtras.getMap$lifecycle_viewmodel_release());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MutableCreationExtras(CreationExtras creationExtras, int i2, e eVar) {
        this((i2 & 1) != 0 ? CreationExtras.Empty.INSTANCE : creationExtras);
    }
}
