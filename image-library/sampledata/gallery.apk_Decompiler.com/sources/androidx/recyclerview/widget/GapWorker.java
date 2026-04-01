package androidx.recyclerview.widget;

import androidx.core.os.TraceCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class GapWorker implements Runnable {
    static final ThreadLocal<GapWorker> sGapWorker = new ThreadLocal<>();
    static Comparator<Task> sTaskComparator = new Comparator<Task>() {
        public int compare(Task task, Task task2) {
            RecyclerView recyclerView = task.view;
            if ((recyclerView == null) != (task2.view == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z = task.immediate;
            if (z != task2.immediate) {
                return z ? -1 : 1;
            }
            int i2 = task2.viewVelocity - task.viewVelocity;
            if (i2 != 0) {
                return i2;
            }
            int i7 = task.distanceToItem - task2.distanceToItem;
            if (i7 != 0) {
                return i7;
            }
            return 0;
        }
    };
    long mFrameIntervalNs;
    long mPostTimeNs;
    ArrayList<RecyclerView> mRecyclerViews = new ArrayList<>();
    private ArrayList<Task> mTasks = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayoutPrefetchRegistryImpl implements RecyclerView.LayoutManager.LayoutPrefetchRegistry {
        int mCount;
        int[] mPrefetchArray;
        int mPrefetchDx;
        int mPrefetchDy;

        public void addPosition(int i2, int i7) {
            if (i2 < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            } else if (i7 >= 0) {
                int i8 = this.mCount;
                int i10 = i8 * 2;
                int[] iArr = this.mPrefetchArray;
                if (iArr == null) {
                    int[] iArr2 = new int[4];
                    this.mPrefetchArray = iArr2;
                    Arrays.fill(iArr2, -1);
                } else if (i10 >= iArr.length) {
                    int[] iArr3 = new int[(i8 * 4)];
                    this.mPrefetchArray = iArr3;
                    System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                }
                int[] iArr4 = this.mPrefetchArray;
                iArr4[i10] = i2;
                iArr4[i10 + 1] = i7;
                this.mCount++;
            } else {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
        }

        public void clearPrefetchPositions() {
            int[] iArr = this.mPrefetchArray;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.mCount = 0;
        }

        public void collectPrefetchPositionsFromView(RecyclerView recyclerView, boolean z) {
            this.mCount = 0;
            int[] iArr = this.mPrefetchArray;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.mLayout;
            if (recyclerView.mAdapter != null && layoutManager != null && layoutManager.isItemPrefetchEnabled()) {
                if (z) {
                    if (!recyclerView.mAdapterHelper.hasPendingUpdates()) {
                        layoutManager.collectInitialPrefetchPositions(recyclerView.mAdapter.getItemCount(), this);
                    }
                } else if (!recyclerView.hasPendingAdapterUpdates()) {
                    layoutManager.collectAdjacentPrefetchPositions(this.mPrefetchDx, this.mPrefetchDy, recyclerView.mState, this);
                }
                int i2 = this.mCount;
                if (i2 > layoutManager.mPrefetchMaxCountObserved) {
                    layoutManager.mPrefetchMaxCountObserved = i2;
                    layoutManager.mPrefetchMaxObservedInInitialPrefetch = z;
                    recyclerView.mRecycler.updateViewCacheSize();
                }
            }
        }

        public boolean lastPrefetchIncludedPosition(int i2) {
            if (this.mPrefetchArray != null) {
                int i7 = this.mCount * 2;
                for (int i8 = 0; i8 < i7; i8 += 2) {
                    if (this.mPrefetchArray[i8] == i2) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void setPrefetchVector(int i2, int i7) {
            this.mPrefetchDx = i2;
            this.mPrefetchDy = i7;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Task {
        public int distanceToItem;
        public boolean immediate;
        public int position;
        public RecyclerView view;
        public int viewVelocity;

        public void clear() {
            this.immediate = false;
            this.viewVelocity = 0;
            this.distanceToItem = 0;
            this.view = null;
            this.position = 0;
        }
    }

    private void buildTaskList() {
        Task task;
        boolean z;
        int size = this.mRecyclerViews.size();
        int i2 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            RecyclerView recyclerView = this.mRecyclerViews.get(i7);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.mPrefetchRegistry.collectPrefetchPositionsFromView(recyclerView, false);
                i2 += recyclerView.mPrefetchRegistry.mCount;
            }
        }
        this.mTasks.ensureCapacity(i2);
        int i8 = 0;
        for (int i10 = 0; i10 < size; i10++) {
            RecyclerView recyclerView2 = this.mRecyclerViews.get(i10);
            if (recyclerView2.getWindowVisibility() == 0) {
                LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = recyclerView2.mPrefetchRegistry;
                int abs = Math.abs(layoutPrefetchRegistryImpl.mPrefetchDy) + Math.abs(layoutPrefetchRegistryImpl.mPrefetchDx);
                for (int i11 = 0; i11 < layoutPrefetchRegistryImpl.mCount * 2; i11 += 2) {
                    if (i8 >= this.mTasks.size()) {
                        task = new Task();
                        this.mTasks.add(task);
                    } else {
                        task = this.mTasks.get(i8);
                    }
                    int[] iArr = layoutPrefetchRegistryImpl.mPrefetchArray;
                    int i12 = iArr[i11 + 1];
                    if (i12 <= abs) {
                        z = true;
                    } else {
                        z = false;
                    }
                    task.immediate = z;
                    task.viewVelocity = abs;
                    task.distanceToItem = i12;
                    task.view = recyclerView2;
                    task.position = iArr[i11];
                    i8++;
                }
            }
        }
        Collections.sort(this.mTasks, sTaskComparator);
    }

    private void flushTaskWithDeadline(Task task, long j2) {
        long j3;
        if (task.immediate) {
            j3 = Long.MAX_VALUE;
        } else {
            j3 = j2;
        }
        RecyclerView.ViewHolder prefetchPositionWithDeadline = prefetchPositionWithDeadline(task.view, task.position, j3);
        if (prefetchPositionWithDeadline != null && prefetchPositionWithDeadline.mNestedRecyclerView != null && prefetchPositionWithDeadline.isBound() && !prefetchPositionWithDeadline.isInvalid()) {
            prefetchInnerRecyclerViewWithDeadline(prefetchPositionWithDeadline.mNestedRecyclerView.get(), j2);
        }
    }

    private void flushTasksWithDeadline(long j2) {
        int i2 = 0;
        while (i2 < this.mTasks.size()) {
            Task task = this.mTasks.get(i2);
            if (task.view != null) {
                flushTaskWithDeadline(task, j2);
                task.clear();
                i2++;
            } else {
                return;
            }
        }
    }

    public static boolean isPrefetchPositionAttached(RecyclerView recyclerView, int i2) {
        int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
        for (int i7 = 0; i7 < unfilteredChildCount; i7++) {
            RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.getUnfilteredChildAt(i7));
            if (childViewHolderInt.mPosition == i2 && !childViewHolderInt.isInvalid()) {
                return true;
            }
        }
        return false;
    }

    private void prefetchInnerRecyclerViewWithDeadline(RecyclerView recyclerView, long j2) {
        if (recyclerView != null) {
            if (recyclerView.mDataSetHasChangedAfterLayout && recyclerView.mChildHelper.getUnfilteredChildCount() != 0) {
                recyclerView.removeAndRecycleViews();
            }
            LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = recyclerView.mPrefetchRegistry;
            layoutPrefetchRegistryImpl.collectPrefetchPositionsFromView(recyclerView, true);
            if (layoutPrefetchRegistryImpl.mCount != 0) {
                try {
                    TraceCompat.beginSection("RV Nested Prefetch");
                    recyclerView.mState.prepareForNestedPrefetch(recyclerView.mAdapter);
                    for (int i2 = 0; i2 < layoutPrefetchRegistryImpl.mCount * 2; i2 += 2) {
                        prefetchPositionWithDeadline(recyclerView, layoutPrefetchRegistryImpl.mPrefetchArray[i2], j2);
                    }
                } finally {
                    TraceCompat.endSection();
                }
            }
        }
    }

    private RecyclerView.ViewHolder prefetchPositionWithDeadline(RecyclerView recyclerView, int i2, long j2) {
        if (isPrefetchPositionAttached(recyclerView, i2)) {
            return null;
        }
        RecyclerView.Recycler recycler = recyclerView.mRecycler;
        try {
            recyclerView.onEnterLayoutOrScroll();
            RecyclerView.ViewHolder tryGetViewHolderForPositionByDeadline = recycler.tryGetViewHolderForPositionByDeadline(i2, false, j2);
            if (tryGetViewHolderForPositionByDeadline != null) {
                if (!tryGetViewHolderForPositionByDeadline.isBound() || tryGetViewHolderForPositionByDeadline.isInvalid()) {
                    recycler.addViewHolderToRecycledViewPool(tryGetViewHolderForPositionByDeadline, false);
                } else {
                    recycler.recycleView(tryGetViewHolderForPositionByDeadline.itemView);
                }
            }
            return tryGetViewHolderForPositionByDeadline;
        } finally {
            recyclerView.onExitLayoutOrScroll(false);
        }
    }

    public void add(RecyclerView recyclerView) {
        if (!RecyclerView.sDebugAssertionsEnabled || !this.mRecyclerViews.contains(recyclerView)) {
            this.mRecyclerViews.add(recyclerView);
            return;
        }
        throw new IllegalStateException("RecyclerView already present in worker list!");
    }

    public void postFromTraversal(RecyclerView recyclerView, int i2, int i7) {
        if (recyclerView.isAttachedToWindow()) {
            if (RecyclerView.sDebugAssertionsEnabled && !this.mRecyclerViews.contains(recyclerView)) {
                throw new IllegalStateException("attempting to post unregistered view!");
            } else if (this.mPostTimeNs == 0) {
                this.mPostTimeNs = recyclerView.getNanoTime();
                recyclerView.post(this);
            }
        }
        recyclerView.mPrefetchRegistry.setPrefetchVector(i2, i7);
    }

    public void prefetch(long j2) {
        buildTaskList();
        flushTasksWithDeadline(j2);
    }

    public void remove(RecyclerView recyclerView) {
        boolean remove = this.mRecyclerViews.remove(recyclerView);
        if (RecyclerView.sDebugAssertionsEnabled && !remove) {
            throw new IllegalStateException("RecyclerView removal failed!");
        }
    }

    public void run() {
        try {
            TraceCompat.beginSection("RV Prefetch");
            if (!this.mRecyclerViews.isEmpty()) {
                int size = this.mRecyclerViews.size();
                long j2 = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    RecyclerView recyclerView = this.mRecyclerViews.get(i2);
                    if (recyclerView.getWindowVisibility() == 0) {
                        j2 = Math.max(recyclerView.getDrawingTime(), j2);
                    }
                }
                if (j2 != 0) {
                    prefetch(TimeUnit.MILLISECONDS.toNanos(j2) + this.mFrameIntervalNs);
                }
            }
        } finally {
            this.mPostTimeNs = 0;
            TraceCompat.endSection();
        }
    }
}
