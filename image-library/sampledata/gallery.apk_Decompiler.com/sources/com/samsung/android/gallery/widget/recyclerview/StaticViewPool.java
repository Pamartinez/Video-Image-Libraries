package com.samsung.android.gallery.widget.recyclerview;

import android.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.recyclerview.StaticViewPool;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class StaticViewPool<T extends StaticViewPool<?>> {
    private static final ConcurrentHashMap<Integer, RecyclerView.RecycledViewPool> sPoolMap = new ConcurrentHashMap<>();
    private final String TAG = getClass().getSimpleName();
    private final ArrayList<Pair<Integer, Integer>> mSize = new ArrayList<>();

    public static void clear() {
        sPoolMap.clear();
    }

    public void onDestroy(RecyclerView recyclerView) {
        if (!this.mSize.isEmpty()) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof RecyclableLayoutManager) {
                ((RecyclableLayoutManager) layoutManager).recycleAllViews();
                return;
            }
            return;
        }
        throw new IllegalStateException("have to set max size for each type");
    }

    public T setMaxSize(int i2, int i7) {
        this.mSize.add(new Pair(Integer.valueOf(i2), Integer.valueOf(i7)));
        return this;
    }

    public void setRecycledViewPool(RecyclerView recyclerView) {
        if (!this.mSize.isEmpty()) {
            int hashCode = recyclerView.getContext().getResources().getConfiguration().toString().hashCode();
            ConcurrentHashMap<Integer, RecyclerView.RecycledViewPool> concurrentHashMap = sPoolMap;
            RecyclerView.RecycledViewPool recycledViewPool = concurrentHashMap.get(Integer.valueOf(hashCode));
            if (recycledViewPool == null) {
                recycledViewPool = recyclerView.getRecycledViewPool();
                this.mSize.forEach(new a(recycledViewPool));
                if (concurrentHashMap.size() > 1) {
                    concurrentHashMap.clear();
                }
                concurrentHashMap.put(Integer.valueOf(hashCode), recycledViewPool);
            }
            recyclerView.setRecycledViewPool(recycledViewPool);
            return;
        }
        throw new IllegalStateException("have to set max size for each type");
    }
}
