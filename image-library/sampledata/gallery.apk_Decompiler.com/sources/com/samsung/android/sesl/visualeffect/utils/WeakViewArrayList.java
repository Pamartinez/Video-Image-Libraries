package com.samsung.android.sesl.visualeffect.utils;

import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002`\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\nJ\u001b\u0010\u000f\u001a\u00020\u000e2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\f¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/utils/WeakViewArrayList;", "Ljava/util/ArrayList;", "Ljava/lang/ref/WeakReference;", "Landroid/view/View;", "Lkotlin/collections/ArrayList;", "<init>", "()V", "v", "", "add", "(Landroid/view/View;)Z", "remove", "Ljava/util/function/Consumer;", "action", "Lme/x;", "forEachAlive", "(Ljava/util/function/Consumer;)V", "clear", "Lcom/samsung/android/sesl/visualeffect/utils/VUID;", "weakRefManager", "Lcom/samsung/android/sesl/visualeffect/utils/VUID;", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WeakViewArrayList extends ArrayList<WeakReference<View>> {
    private final VUID weakRefManager = new VUID(0, 1, (e) null);

    public final boolean add(View view) {
        j.e(view, "v");
        return add(this.weakRefManager.getOrCreateMemoized(view));
    }

    public void clear() {
        this.weakRefManager.clear();
        super.clear();
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof WeakReference)) {
            return false;
        }
        return contains((WeakReference<View>) (WeakReference) obj);
    }

    public final void forEachAlive(Consumer<View> consumer) {
        j.e(consumer, "action");
        ArrayList arrayList = new ArrayList();
        Iterator it = iterator();
        while (it.hasNext()) {
            View view = (View) ((WeakReference) it.next()).get();
            if (view != null) {
                arrayList.add(view);
            }
        }
        arrayList.forEach(consumer);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof WeakReference)) {
            return -1;
        }
        return indexOf((WeakReference<View>) (WeakReference) obj);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof WeakReference)) {
            return -1;
        }
        return lastIndexOf((WeakReference<View>) (WeakReference) obj);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj instanceof WeakReference)) {
            return false;
        }
        return remove((WeakReference<View>) (WeakReference) obj);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public /* bridge */ boolean contains(WeakReference<View> weakReference) {
        return super.contains(weakReference);
    }

    public /* bridge */ int indexOf(WeakReference<View> weakReference) {
        return super.indexOf(weakReference);
    }

    public /* bridge */ int lastIndexOf(WeakReference<View> weakReference) {
        return super.lastIndexOf(weakReference);
    }

    public /* bridge */ boolean remove(WeakReference<View> weakReference) {
        return super.remove(weakReference);
    }

    public final boolean remove(View view) {
        j.e(view, "v");
        WeakReference<View> forget = this.weakRefManager.forget(view);
        if (forget != null) {
            return super.remove(forget);
        }
        return false;
    }
}
