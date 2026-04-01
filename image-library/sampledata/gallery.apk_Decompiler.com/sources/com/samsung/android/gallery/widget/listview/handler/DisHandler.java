package com.samsung.android.gallery.widget.listview.handler;

import android.view.MotionEvent;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface DisHandler {
    List<DisHandler> getChildDisHandler();

    void handleCancel();

    void handleDown(MotionEvent motionEvent, int i2);

    void handleMove(int i2, int i7);

    void handleUp(int i2, int i7);

    boolean isHeader(ListViewHolder listViewHolder) {
        return ViewHolderValue.isHeader(listViewHolder.getItemViewType());
    }

    void onBindViewHolder(ListViewHolder listViewHolder, int i2);

    void release();

    void resetListener() {
    }

    void addHandler(DisHandler disHandler) {
    }

    void setOnUpdateSelectionListener(Consumer<Void> consumer) {
    }

    void useAdvMouseDnd(BooleanSupplier booleanSupplier) {
    }
}
