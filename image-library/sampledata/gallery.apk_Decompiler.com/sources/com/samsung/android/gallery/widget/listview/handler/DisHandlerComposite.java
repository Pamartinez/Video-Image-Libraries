package com.samsung.android.gallery.widget.listview.handler;

import E9.a;
import android.view.MotionEvent;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DisHandlerComposite implements DisHandler {
    private ArrayList<DisHandler> mHandlerList;
    private final DisHandler mParent;

    public DisHandlerComposite(DisHandler disHandler) {
        ArrayList<DisHandler> arrayList = new ArrayList<>();
        this.mHandlerList = arrayList;
        this.mParent = disHandler;
        arrayList.add(disHandler);
    }

    /* renamed from: addHandler */
    public void lambda$onBindViewHolder$0(DisHandler disHandler) {
        if (disHandler != null && !this.mHandlerList.contains(disHandler)) {
            this.mHandlerList.add(disHandler);
        }
    }

    public List<DisHandler> getChildDisHandler() {
        return this.mParent.getChildDisHandler();
    }

    public void handleCancel() {
        Iterator<DisHandler> it = this.mHandlerList.iterator();
        while (it.hasNext()) {
            it.next().handleCancel();
        }
    }

    public void handleDown(MotionEvent motionEvent, int i2) {
        Iterator<DisHandler> it = this.mHandlerList.iterator();
        while (it.hasNext()) {
            it.next().handleDown(motionEvent, i2);
        }
    }

    public void handleMove(int i2, int i7) {
        Iterator<DisHandler> it = this.mHandlerList.iterator();
        while (it.hasNext()) {
            it.next().handleMove(i2, i7);
        }
    }

    public void handleUp(int i2, int i7) {
        Iterator<DisHandler> it = this.mHandlerList.iterator();
        while (it.hasNext()) {
            it.next().handleUp(i2, i7);
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        List<DisHandler> childDisHandler;
        if (isHeader(listViewHolder) && (childDisHandler = this.mParent.getChildDisHandler()) != null) {
            childDisHandler.stream().forEach(new a(19, this));
        }
        Iterator<DisHandler> it = this.mHandlerList.iterator();
        while (it.hasNext()) {
            it.next().onBindViewHolder(listViewHolder, i2);
        }
    }

    public void release() {
        Iterator<DisHandler> it = this.mHandlerList.iterator();
        while (it.hasNext()) {
            it.next().release();
        }
        this.mHandlerList.clear();
    }

    public void setOnUpdateSelectionListener(Consumer<Void> consumer) {
        Iterator<DisHandler> it = this.mHandlerList.iterator();
        while (it.hasNext()) {
            it.next().setOnUpdateSelectionListener(consumer);
        }
    }

    public void useAdvMouseDnd(BooleanSupplier booleanSupplier) {
        Iterator<DisHandler> it = this.mHandlerList.iterator();
        while (it.hasNext()) {
            it.next().useAdvMouseDnd(booleanSupplier);
        }
    }
}
