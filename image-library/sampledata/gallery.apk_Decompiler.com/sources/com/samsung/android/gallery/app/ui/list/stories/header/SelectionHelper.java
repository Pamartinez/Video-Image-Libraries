package com.samsung.android.gallery.app.ui.list.stories.header;

import b6.C0428c;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelectionHelper<V> {
    private final List<V> mSelected = new CopyOnWriteArrayList();

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$add$0(Object obj) {
        Optional.ofNullable(obj).ifPresent(new C0428c(this, 1));
    }

    public void add(V v) {
        if (!this.mSelected.contains(v)) {
            this.mSelected.add(v);
        }
    }

    public void clear() {
        this.mSelected.clear();
    }

    public List<V> getSelected() {
        return new ArrayList(this.mSelected);
    }

    public int getSize() {
        return this.mSelected.size();
    }

    public boolean invalidate(List<V> list) {
        return this.mSelected.retainAll(list);
    }

    public boolean isSelected(V v) {
        return this.mSelected.contains(v);
    }

    public void remove(V v) {
        this.mSelected.remove(v);
    }

    public void toggle(V v) {
        if (this.mSelected.contains(v)) {
            this.mSelected.remove(v);
        } else {
            this.mSelected.add(v);
        }
    }

    public void add(List<V> list) {
        if (list != null) {
            list.forEach(new C0428c(this, 0));
        }
    }
}
