package com.samsung.android.gallery.widget.abstraction;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewPoolInjector {
    public static void inject(RecyclerView.RecycledViewPool recycledViewPool, final RecyclerView.ViewHolder viewHolder, int i2) {
        recycledViewPool.putRecycledView(new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            public int getItemCount() {
                throw new AssertionError("use this adapter only for create view holder");
            }

            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
                throw new AssertionError("use this adapter only for create view holder");
            }

            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
                return RecyclerView.ViewHolder.this;
            }
        }.createViewHolder((ViewGroup) null, i2));
    }
}
