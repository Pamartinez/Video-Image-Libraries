package com.samsung.android.gallery.widget.listview;

import androidx.recyclerview.widget.RecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SynchronizedViewPool extends RecyclerView.RecycledViewPool {
    public void clear() {
        try {
            super.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized RecyclerView.ViewHolder getRecycledView(int i2) {
        return super.getRecycledView(i2);
    }

    public synchronized void putRecycledView(RecyclerView.ViewHolder viewHolder) {
        super.putRecycledView(viewHolder);
    }
}
