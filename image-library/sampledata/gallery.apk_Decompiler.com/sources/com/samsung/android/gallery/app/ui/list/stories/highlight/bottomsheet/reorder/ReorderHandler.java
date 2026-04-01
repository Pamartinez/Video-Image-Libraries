package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder;

import U5.d;
import android.os.CountDownTimer;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Timer;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sum.core.filter.f;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import k6.b;
import m7.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReorderHandler implements DragListener {
    private static final int START_TIMER_ID = Timer.getTimerId();
    private CountDownTimer mAlarm;
    private final DragHandler mDragHandler;
    private int mDragOverIndex = -1;
    /* access modifiers changed from: private */
    public boolean mIsReordering;
    RecyclerView.AdapterDataObserver mObserver = new RecyclerView.AdapterDataObserver() {
        public void onItemRangeMoved(int i2, int i7, int i8) {
            boolean z;
            if (ReorderHandler.this.mRecyclerView.findViewHolderForAdapterPosition(i2) == null && ReorderHandler.this.mRecyclerView.findViewHolderForAdapterPosition(i7) == null) {
                z = false;
            } else {
                z = true;
            }
            if ((i7 == 0 || i2 == 0) && z && ReorderHandler.this.mIsReordering) {
                ReorderHandler.this.mRecyclerView.scrollToPosition(0);
            }
        }
    };
    /* access modifiers changed from: private */
    public final RecyclerView mRecyclerView;
    private ReorderListener mReorderListener;

    public ReorderHandler(RecyclerView recyclerView, Supplier<Boolean> supplier) {
        this.mDragHandler = new DragHandler(recyclerView, this, supplier);
        this.mRecyclerView = recyclerView;
    }

    private boolean canReorder() {
        if (this.mIsReordering || !isScrollIdle()) {
            return false;
        }
        return true;
    }

    private void cancelAlarm() {
        Optional.ofNullable(this.mAlarm).ifPresent(new c(14));
    }

    private RecyclerView.Adapter<?> getAdapter() {
        return this.mRecyclerView.getAdapter();
    }

    private boolean isScrollIdle() {
        if (this.mRecyclerView.getScrollState() == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onDrop$1(int i2, int i7, ReorderListener reorderListener) {
        boolean z;
        if (i2 != i7) {
            z = true;
        } else {
            z = false;
        }
        reorderListener.drop(i2, i7, z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reorder$2() {
        this.mIsReordering = false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$tryReorder$0(ListViewHolder listViewHolder, int i2) {
        boolean beginDrag = this.mDragHandler.beginDrag(listViewHolder);
        if (beginDrag) {
            registerObserver();
        }
        Log.d("ReorderHandler", "begin Drag", Boolean.valueOf(beginDrag));
    }

    private void registerObserver() {
        try {
            RecyclerView.Adapter adapter = this.mRecyclerView.getAdapter();
            Objects.requireNonNull(adapter);
            adapter.registerAdapterDataObserver(this.mObserver);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public void reorder(int i2) {
        int i7;
        boolean z;
        ReorderListener reorderListener;
        DragHandler dragHandler = this.mDragHandler;
        if (dragHandler != null) {
            i7 = dragHandler.getFromPosition();
        } else {
            i7 = -1;
        }
        RecyclerView.Adapter<?> adapter = getAdapter();
        boolean z3 = true;
        if (!(adapter == null || i7 == -1 || i7 == i2 || (reorderListener = this.mReorderListener) == null)) {
            this.mIsReordering = true;
            reorderListener.reorder(i7, i2);
            adapter.notifyItemMoved(i7, i2);
            this.mDragHandler.updateDragPosition(i2);
            ThreadUtil.postOnUiThreadDelayed(new b(19, this), 300);
        }
        Integer valueOf = Integer.valueOf(i7);
        Integer valueOf2 = Integer.valueOf(i2);
        if (i7 != i2) {
            z = true;
        } else {
            z = false;
        }
        Boolean valueOf3 = Boolean.valueOf(z);
        if (adapter == null) {
            z3 = false;
        }
        Log.d("ReorderHandler", "reorder", valueOf, valueOf2, valueOf3, Boolean.valueOf(z3));
    }

    private void setReorderAlarm(int i2) {
        cancelAlarm();
        final int i7 = i2;
        AnonymousClass1 r0 = new CountDownTimer(500, 100) {
            public void onFinish() {
                ReorderHandler.this.reorder(i7);
            }

            public void onTick(long j2) {
            }
        };
        this.mAlarm = r0;
        r0.start();
        Log.d("ReorderHandler", "setReorderAlarm to", Integer.valueOf(i7));
    }

    private void unregisterObserver() {
        try {
            RecyclerView.Adapter adapter = this.mRecyclerView.getAdapter();
            Objects.requireNonNull(adapter);
            adapter.unregisterAdapterDataObserver(this.mObserver);
        } catch (Exception unused) {
        }
    }

    public int getReorderingPosition() {
        return this.mDragHandler.getFromPosition();
    }

    public void onDragBegin() {
        Optional.ofNullable(this.mReorderListener).ifPresent(new c(13));
    }

    public void onDragEnd() {
        unregisterObserver();
        Optional.ofNullable(this.mReorderListener).ifPresent(new c(15));
        this.mDragOverIndex = -1;
    }

    public void onDragOver(int i2, float f, float f5) {
        if (this.mRecyclerView.findViewHolderForAdapterPosition(i2) != null && this.mDragHandler.isDragging()) {
            if (i2 == -1) {
                cancelAlarm();
                this.mDragOverIndex = -1;
            } else if (canReorder() && this.mDragOverIndex != i2) {
                setReorderAlarm(i2);
                this.mDragOverIndex = i2;
            }
        }
    }

    public void onDragStart() {
        Optional.ofNullable(this.mReorderListener).ifPresent(new c(12));
    }

    public void onDrop(int i2, int i7) {
        Optional.ofNullable(this.mReorderListener).ifPresent(new f(i2, i7, 4));
    }

    public boolean scrollable() {
        return canReorder();
    }

    public void setAutoScroller(AutoScroller autoScroller) {
        this.mDragHandler.setAutoScroller(autoScroller);
    }

    public void setReorderListener(ReorderListener reorderListener) {
        this.mReorderListener = reorderListener;
    }

    public void tryReorder(ListViewHolder listViewHolder) {
        int i2 = START_TIMER_ID;
        Timer.stopTimer(i2);
        Timer.startTimer(i2, 800, new d(2, this, listViewHolder));
    }
}
