package androidx.lifecycle;

import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.core.os.BundleKt;
import androidx.savedstate.SavedStateRegistry;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;
import ne.z;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u001f\b\u0016\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006B\t\b\u0016¢\u0006\u0004\b\u0005\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\t\u0010\nJ(\u0010\u000f\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0004\b\u000f\u0010\u0010R\"\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00118\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R \u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u00118\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0013R$\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u00118\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0013R(\u0010\u0016\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00010\u00118\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0013R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0017¨\u0006\u0019"}, d2 = {"Landroidx/lifecycle/SavedStateHandle;", "", "", "", "initialState", "<init>", "(Ljava/util/Map;)V", "()V", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "savedStateProvider", "()Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "T", "key", "value", "Lme/x;", "set", "(Ljava/lang/String;Ljava/lang/Object;)V", "", "regular", "Ljava/util/Map;", "savedStateProviders", "liveDatas", "flows", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "Companion", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SavedStateHandle {
    /* access modifiers changed from: private */
    public static final Class<? extends Object>[] ACCEPTABLE_CLASSES = {Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};
    public static final Companion Companion = new Companion((e) null);
    private final Map<String, Object> flows;
    private final Map<String, Object> liveDatas;
    private final Map<String, Object> regular;
    private final SavedStateRegistry.SavedStateProvider savedStateProvider;
    private final Map<String, SavedStateRegistry.SavedStateProvider> savedStateProviders;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0007J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0007R \u0010\u0003\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\b\u0001\u0012\u00020\u0001\u0018\u00010\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/lifecycle/SavedStateHandle$Companion;", "", "()V", "ACCEPTABLE_CLASSES", "", "Ljava/lang/Class;", "[Ljava/lang/Class;", "KEYS", "", "VALUES", "createHandle", "Landroidx/lifecycle/SavedStateHandle;", "restoredState", "Landroid/os/Bundle;", "defaultState", "validateValue", "", "value", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final SavedStateHandle createHandle(Bundle bundle, Bundle bundle2) {
            if (bundle != null) {
                ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
                ArrayList parcelableArrayList2 = bundle.getParcelableArrayList(StateHandler.VALUES);
                if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
                    throw new IllegalStateException("Invalid bundle passed as restored state");
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                int size = parcelableArrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    Object obj = parcelableArrayList.get(i2);
                    j.c(obj, "null cannot be cast to non-null type kotlin.String");
                    linkedHashMap.put((String) obj, parcelableArrayList2.get(i2));
                }
                return new SavedStateHandle(linkedHashMap);
            } else if (bundle2 == null) {
                return new SavedStateHandle();
            } else {
                HashMap hashMap = new HashMap();
                for (String next : bundle2.keySet()) {
                    j.d(next, "key");
                    hashMap.put(next, bundle2.get(next));
                }
                return new SavedStateHandle(hashMap);
            }
        }

        public final boolean validateValue(Object obj) {
            if (obj == null) {
                return true;
            }
            for (Class cls : SavedStateHandle.ACCEPTABLE_CLASSES) {
                j.b(cls);
                if (cls.isInstance(obj)) {
                    return true;
                }
            }
            return false;
        }

        private Companion() {
        }
    }

    public SavedStateHandle(Map<String, ? extends Object> map) {
        j.e(map, "initialState");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.regular = linkedHashMap;
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.savedStateProvider = new a(this);
        linkedHashMap.putAll(map);
    }

    /* access modifiers changed from: private */
    public static final Bundle savedStateProvider$lambda$0(SavedStateHandle savedStateHandle) {
        j.e(savedStateHandle, "this$0");
        for (Map.Entry entry : z.f0(savedStateHandle.savedStateProviders).entrySet()) {
            savedStateHandle.set((String) entry.getKey(), ((SavedStateRegistry.SavedStateProvider) entry.getValue()).saveState());
        }
        Set<String> keySet = savedStateHandle.regular.keySet();
        ArrayList arrayList = new ArrayList(keySet.size());
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (String next : keySet) {
            arrayList.add(next);
            arrayList2.add(savedStateHandle.regular.get(next));
        }
        return BundleKt.bundleOf(new i("keys", arrayList), new i(StateHandler.VALUES, arrayList2));
    }

    public final SavedStateRegistry.SavedStateProvider savedStateProvider() {
        return this.savedStateProvider;
    }

    public final <T> void set(String str, T t) {
        MutableLiveData mutableLiveData;
        j.e(str, "key");
        if (Companion.validateValue(t)) {
            Object obj = this.liveDatas.get(str);
            if (obj instanceof MutableLiveData) {
                mutableLiveData = (MutableLiveData) obj;
            } else {
                mutableLiveData = null;
            }
            if (mutableLiveData != null) {
                mutableLiveData.setValue(t);
            } else {
                this.regular.put(str, t);
            }
            if (this.flows.get(str) != null) {
                throw new ClassCastException();
            }
            return;
        }
        StringBuilder sb2 = new StringBuilder("Can't put value with type ");
        j.b(t);
        sb2.append(t.getClass());
        sb2.append(" into saved state");
        throw new IllegalArgumentException(sb2.toString());
    }

    public SavedStateHandle() {
        this.regular = new LinkedHashMap();
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.savedStateProvider = new a(this);
    }
}
