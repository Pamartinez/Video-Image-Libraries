package androidx.recyclerview.widget;

import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SimplePool;
import androidx.recyclerview.widget.OpReorderer;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class AdapterHelper implements OpReorderer.Callback {
    final Callback mCallback;
    final boolean mDisableRecycler;
    private int mExistingUpdateTypes;
    Runnable mOnItemProcessedCallback;
    final OpReorderer mOpReorderer;
    final ArrayList<UpdateOp> mPendingUpdates;
    final ArrayList<UpdateOp> mPostponedList;
    private Pools$Pool<UpdateOp> mUpdateOpPool;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Callback {
        RecyclerView.ViewHolder findViewHolder(int i2);

        void markViewHoldersUpdated(int i2, int i7, Object obj);

        void offsetPositionsForAdd(int i2, int i7);

        void offsetPositionsForMove(int i2, int i7);

        void offsetPositionsForRemovingInvisible(int i2, int i7);

        void offsetPositionsForRemovingLaidOutOrNewView(int i2, int i7);

        void onDispatchFirstPass(UpdateOp updateOp);

        void onDispatchSecondPass(UpdateOp updateOp);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UpdateOp {
        int cmd;
        int itemCount;
        Object payload;
        int positionStart;

        public UpdateOp(int i2, int i7, int i8, Object obj) {
            this.cmd = i2;
            this.positionStart = i7;
            this.itemCount = i8;
            this.payload = obj;
        }

        public String cmdToString() {
            int i2 = this.cmd;
            if (i2 == 1) {
                return "add";
            }
            if (i2 == 2) {
                return "rm";
            }
            if (i2 == 4) {
                return "up";
            }
            if (i2 != 8) {
                return "??";
            }
            return "mv";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof UpdateOp)) {
                return false;
            }
            UpdateOp updateOp = (UpdateOp) obj;
            int i2 = this.cmd;
            if (i2 != updateOp.cmd) {
                return false;
            }
            if (i2 == 8 && Math.abs(this.itemCount - this.positionStart) == 1 && this.itemCount == updateOp.positionStart && this.positionStart == updateOp.itemCount) {
                return true;
            }
            if (this.itemCount != updateOp.itemCount || this.positionStart != updateOp.positionStart) {
                return false;
            }
            Object obj2 = this.payload;
            if (obj2 != null) {
                if (!obj2.equals(updateOp.payload)) {
                    return false;
                }
            } else if (updateOp.payload != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.cmd * 31) + this.positionStart) * 31) + this.itemCount;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + cmdToString() + ",s:" + this.positionStart + "c:" + this.itemCount + ",p:" + this.payload + "]";
        }
    }

    public AdapterHelper(Callback callback) {
        this(callback, false);
    }

    private void applyAdd(UpdateOp updateOp) {
        postponeAndUpdateViewHolders(updateOp);
    }

    private void applyMove(UpdateOp updateOp) {
        postponeAndUpdateViewHolders(updateOp);
    }

    private void applyRemove(UpdateOp updateOp) {
        char c5;
        boolean z;
        boolean z3;
        int i2 = updateOp.positionStart;
        int i7 = updateOp.itemCount + i2;
        char c6 = 65535;
        int i8 = i2;
        int i10 = 0;
        while (i8 < i7) {
            if (this.mCallback.findViewHolder(i8) != null || canFindInPreLayout(i8)) {
                if (c6 == 0) {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(2, i2, i10, (Object) null));
                    z3 = true;
                } else {
                    z3 = false;
                }
                c5 = 1;
            } else {
                if (c6 == 1) {
                    postponeAndUpdateViewHolders(obtainUpdateOp(2, i2, i10, (Object) null));
                    z = true;
                } else {
                    z = false;
                }
                c5 = 0;
            }
            if (z) {
                i8 -= i10;
                i7 -= i10;
                i10 = 1;
            } else {
                i10++;
            }
            i8++;
            c6 = c5;
        }
        if (i10 != updateOp.itemCount) {
            recycleUpdateOp(updateOp);
            updateOp = obtainUpdateOp(2, i2, i10, (Object) null);
        }
        if (c6 == 0) {
            dispatchAndUpdateViewHolders(updateOp);
        } else {
            postponeAndUpdateViewHolders(updateOp);
        }
    }

    private void applyUpdate(UpdateOp updateOp) {
        int i2 = updateOp.positionStart;
        int i7 = updateOp.itemCount + i2;
        int i8 = 0;
        boolean z = true;
        int i10 = i2;
        while (i2 < i7) {
            if (this.mCallback.findViewHolder(i2) != null || canFindInPreLayout(i2)) {
                if (!z) {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(4, i10, i8, updateOp.payload));
                    i10 = i2;
                    i8 = 0;
                }
                z = true;
            } else {
                if (z) {
                    postponeAndUpdateViewHolders(obtainUpdateOp(4, i10, i8, updateOp.payload));
                    i10 = i2;
                    i8 = 0;
                }
                z = false;
            }
            i8++;
            i2++;
        }
        if (i8 != updateOp.itemCount) {
            Object obj = updateOp.payload;
            recycleUpdateOp(updateOp);
            updateOp = obtainUpdateOp(4, i10, i8, obj);
        }
        if (!z) {
            dispatchAndUpdateViewHolders(updateOp);
        } else {
            postponeAndUpdateViewHolders(updateOp);
        }
    }

    private boolean canFindInPreLayout(int i2) {
        int size = this.mPostponedList.size();
        for (int i7 = 0; i7 < size; i7++) {
            UpdateOp updateOp = this.mPostponedList.get(i7);
            int i8 = updateOp.cmd;
            if (i8 == 8) {
                if (findPositionOffset(updateOp.itemCount, i7 + 1) == i2) {
                    return true;
                }
            } else if (i8 == 1) {
                int i10 = updateOp.positionStart;
                int i11 = updateOp.itemCount + i10;
                while (i10 < i11) {
                    if (findPositionOffset(i10, i7 + 1) == i2) {
                        return true;
                    }
                    i10++;
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    private void dispatchAndUpdateViewHolders(UpdateOp updateOp) {
        int i2;
        int i7 = updateOp.cmd;
        if (i7 == 1 || i7 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int updatePositionWithPostponed = updatePositionWithPostponed(updateOp.positionStart, i7);
        int i8 = updateOp.positionStart;
        int i10 = updateOp.cmd;
        if (i10 == 2) {
            i2 = 0;
        } else if (i10 == 4) {
            i2 = 1;
        } else {
            throw new IllegalArgumentException("op should be remove or update." + updateOp);
        }
        int i11 = 1;
        for (int i12 = 1; i12 < updateOp.itemCount; i12++) {
            int updatePositionWithPostponed2 = updatePositionWithPostponed((i2 * i12) + updateOp.positionStart, updateOp.cmd);
            int i13 = updateOp.cmd;
            if (i13 == 2 ? updatePositionWithPostponed2 != updatePositionWithPostponed : !(i13 == 4 && updatePositionWithPostponed2 == updatePositionWithPostponed + 1)) {
                UpdateOp obtainUpdateOp = obtainUpdateOp(i13, updatePositionWithPostponed, i11, updateOp.payload);
                dispatchFirstPassAndUpdateViewHolders(obtainUpdateOp, i8);
                recycleUpdateOp(obtainUpdateOp);
                if (updateOp.cmd == 4) {
                    i8 += i11;
                }
                i11 = 1;
                updatePositionWithPostponed = updatePositionWithPostponed2;
            } else {
                i11++;
            }
        }
        Object obj = updateOp.payload;
        recycleUpdateOp(updateOp);
        if (i11 > 0) {
            UpdateOp obtainUpdateOp2 = obtainUpdateOp(updateOp.cmd, updatePositionWithPostponed, i11, obj);
            dispatchFirstPassAndUpdateViewHolders(obtainUpdateOp2, i8);
            recycleUpdateOp(obtainUpdateOp2);
        }
    }

    private void postponeAndUpdateViewHolders(UpdateOp updateOp) {
        this.mPostponedList.add(updateOp);
        int i2 = updateOp.cmd;
        if (i2 == 1) {
            this.mCallback.offsetPositionsForAdd(updateOp.positionStart, updateOp.itemCount);
        } else if (i2 == 2) {
            this.mCallback.offsetPositionsForRemovingLaidOutOrNewView(updateOp.positionStart, updateOp.itemCount);
        } else if (i2 == 4) {
            this.mCallback.markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
        } else if (i2 == 8) {
            this.mCallback.offsetPositionsForMove(updateOp.positionStart, updateOp.itemCount);
        } else {
            throw new IllegalArgumentException("Unknown update op type for " + updateOp);
        }
    }

    private int updatePositionWithPostponed(int i2, int i7) {
        int i8;
        int i10;
        for (int size = this.mPostponedList.size() - 1; size >= 0; size--) {
            UpdateOp updateOp = this.mPostponedList.get(size);
            int i11 = updateOp.cmd;
            if (i11 == 8) {
                int i12 = updateOp.positionStart;
                int i13 = updateOp.itemCount;
                if (i12 < i13) {
                    i10 = i12;
                    i8 = i13;
                } else {
                    i8 = i12;
                    i10 = i13;
                }
                if (i2 < i10 || i2 > i8) {
                    if (i2 < i12) {
                        if (i7 == 1) {
                            updateOp.positionStart = i12 + 1;
                            updateOp.itemCount = i13 + 1;
                        } else if (i7 == 2) {
                            updateOp.positionStart = i12 - 1;
                            updateOp.itemCount = i13 - 1;
                        }
                    }
                } else if (i10 == i12) {
                    if (i7 == 1) {
                        updateOp.itemCount = i13 + 1;
                    } else if (i7 == 2) {
                        updateOp.itemCount = i13 - 1;
                    }
                    i2++;
                } else {
                    if (i7 == 1) {
                        updateOp.positionStart = i12 + 1;
                    } else if (i7 == 2) {
                        updateOp.positionStart = i12 - 1;
                    }
                    i2--;
                }
            } else {
                int i14 = updateOp.positionStart;
                if (i14 <= i2) {
                    if (i11 == 1) {
                        i2 -= updateOp.itemCount;
                    } else if (i11 == 2) {
                        i2 += updateOp.itemCount;
                    }
                } else if (i7 == 1) {
                    updateOp.positionStart = i14 + 1;
                } else if (i7 == 2) {
                    updateOp.positionStart = i14 - 1;
                }
            }
        }
        for (int size2 = this.mPostponedList.size() - 1; size2 >= 0; size2--) {
            UpdateOp updateOp2 = this.mPostponedList.get(size2);
            if (updateOp2.cmd == 8) {
                int i15 = updateOp2.itemCount;
                if (i15 == updateOp2.positionStart || i15 < 0) {
                    this.mPostponedList.remove(size2);
                    recycleUpdateOp(updateOp2);
                }
            } else if (updateOp2.itemCount <= 0) {
                this.mPostponedList.remove(size2);
                recycleUpdateOp(updateOp2);
            }
        }
        return i2;
    }

    public int applyPendingUpdatesToPosition(int i2) {
        int size = this.mPendingUpdates.size();
        for (int i7 = 0; i7 < size; i7++) {
            UpdateOp updateOp = this.mPendingUpdates.get(i7);
            int i8 = updateOp.cmd;
            if (i8 != 1) {
                if (i8 == 2) {
                    int i10 = updateOp.positionStart;
                    if (i10 <= i2) {
                        int i11 = updateOp.itemCount;
                        if (i10 + i11 > i2) {
                            return -1;
                        }
                        i2 -= i11;
                    } else {
                        continue;
                    }
                } else if (i8 == 8) {
                    int i12 = updateOp.positionStart;
                    if (i12 == i2) {
                        i2 = updateOp.itemCount;
                    } else {
                        if (i12 < i2) {
                            i2--;
                        }
                        if (updateOp.itemCount <= i2) {
                            i2++;
                        }
                    }
                }
            } else if (updateOp.positionStart <= i2) {
                i2 += updateOp.itemCount;
            }
        }
        return i2;
    }

    public void consumePostponedUpdates() {
        int size = this.mPostponedList.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mCallback.onDispatchSecondPass(this.mPostponedList.get(i2));
        }
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }

    public void consumeUpdatesInOnePass() {
        consumePostponedUpdates();
        int size = this.mPendingUpdates.size();
        for (int i2 = 0; i2 < size; i2++) {
            UpdateOp updateOp = this.mPendingUpdates.get(i2);
            int i7 = updateOp.cmd;
            if (i7 == 1) {
                this.mCallback.onDispatchSecondPass(updateOp);
                this.mCallback.offsetPositionsForAdd(updateOp.positionStart, updateOp.itemCount);
            } else if (i7 == 2) {
                this.mCallback.onDispatchSecondPass(updateOp);
                this.mCallback.offsetPositionsForRemovingInvisible(updateOp.positionStart, updateOp.itemCount);
            } else if (i7 == 4) {
                this.mCallback.onDispatchSecondPass(updateOp);
                this.mCallback.markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
            } else if (i7 == 8) {
                this.mCallback.onDispatchSecondPass(updateOp);
                this.mCallback.offsetPositionsForMove(updateOp.positionStart, updateOp.itemCount);
            }
            Runnable runnable = this.mOnItemProcessedCallback;
            if (runnable != null) {
                runnable.run();
            }
        }
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        this.mExistingUpdateTypes = 0;
    }

    public void dispatchFirstPassAndUpdateViewHolders(UpdateOp updateOp, int i2) {
        this.mCallback.onDispatchFirstPass(updateOp);
        int i7 = updateOp.cmd;
        if (i7 == 2) {
            this.mCallback.offsetPositionsForRemovingInvisible(i2, updateOp.itemCount);
        } else if (i7 == 4) {
            this.mCallback.markViewHoldersUpdated(i2, updateOp.itemCount, updateOp.payload);
        } else {
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    public int findPositionOffset(int i2) {
        return findPositionOffset(i2, 0);
    }

    public boolean hasAnyUpdateTypes(int i2) {
        if ((this.mExistingUpdateTypes & i2) != 0) {
            return true;
        }
        return false;
    }

    public boolean hasPendingUpdates() {
        if (this.mPendingUpdates.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean hasUpdates() {
        if (this.mPostponedList.isEmpty() || this.mPendingUpdates.isEmpty()) {
            return false;
        }
        return true;
    }

    public UpdateOp obtainUpdateOp(int i2, int i7, int i8, Object obj) {
        UpdateOp acquire = this.mUpdateOpPool.acquire();
        if (acquire == null) {
            return new UpdateOp(i2, i7, i8, obj);
        }
        acquire.cmd = i2;
        acquire.positionStart = i7;
        acquire.itemCount = i8;
        acquire.payload = obj;
        return acquire;
    }

    public boolean onItemRangeChanged(int i2, int i7, Object obj) {
        if (i7 < 1) {
            return false;
        }
        this.mPendingUpdates.add(obtainUpdateOp(4, i2, i7, obj));
        this.mExistingUpdateTypes |= 4;
        if (this.mPendingUpdates.size() == 1) {
            return true;
        }
        return false;
    }

    public boolean onItemRangeInserted(int i2, int i7) {
        if (i7 < 1) {
            return false;
        }
        this.mPendingUpdates.add(obtainUpdateOp(1, i2, i7, (Object) null));
        this.mExistingUpdateTypes |= 1;
        if (this.mPendingUpdates.size() == 1) {
            return true;
        }
        return false;
    }

    public boolean onItemRangeMoved(int i2, int i7, int i8) {
        if (i2 == i7) {
            return false;
        }
        if (i8 == 1) {
            this.mPendingUpdates.add(obtainUpdateOp(8, i2, i7, (Object) null));
            this.mExistingUpdateTypes |= 8;
            if (this.mPendingUpdates.size() == 1) {
                return true;
            }
            return false;
        }
        throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
    }

    public boolean onItemRangeRemoved(int i2, int i7) {
        if (i7 < 1) {
            return false;
        }
        this.mPendingUpdates.add(obtainUpdateOp(2, i2, i7, (Object) null));
        this.mExistingUpdateTypes |= 2;
        if (this.mPendingUpdates.size() == 1) {
            return true;
        }
        return false;
    }

    public void preProcess() {
        this.mOpReorderer.reorderOps(this.mPendingUpdates);
        int size = this.mPendingUpdates.size();
        for (int i2 = 0; i2 < size; i2++) {
            UpdateOp updateOp = this.mPendingUpdates.get(i2);
            int i7 = updateOp.cmd;
            if (i7 == 1) {
                applyAdd(updateOp);
            } else if (i7 == 2) {
                applyRemove(updateOp);
            } else if (i7 == 4) {
                applyUpdate(updateOp);
            } else if (i7 == 8) {
                applyMove(updateOp);
            }
            Runnable runnable = this.mOnItemProcessedCallback;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.mPendingUpdates.clear();
    }

    public void recycleUpdateOp(UpdateOp updateOp) {
        if (!this.mDisableRecycler) {
            updateOp.payload = null;
            this.mUpdateOpPool.release(updateOp);
        }
    }

    public void recycleUpdateOpsAndClearList(List<UpdateOp> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            recycleUpdateOp(list.get(i2));
        }
        list.clear();
    }

    public void reset() {
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }

    public AdapterHelper(Callback callback, boolean z) {
        this.mUpdateOpPool = new Pools$SimplePool(30);
        this.mPendingUpdates = new ArrayList<>();
        this.mPostponedList = new ArrayList<>();
        this.mExistingUpdateTypes = 0;
        this.mCallback = callback;
        this.mDisableRecycler = z;
        this.mOpReorderer = new OpReorderer(this);
    }

    public int findPositionOffset(int i2, int i7) {
        int size = this.mPostponedList.size();
        while (i7 < size) {
            UpdateOp updateOp = this.mPostponedList.get(i7);
            int i8 = updateOp.cmd;
            if (i8 == 8) {
                int i10 = updateOp.positionStart;
                if (i10 == i2) {
                    i2 = updateOp.itemCount;
                } else {
                    if (i10 < i2) {
                        i2--;
                    }
                    if (updateOp.itemCount <= i2) {
                        i2++;
                    }
                }
            } else {
                int i11 = updateOp.positionStart;
                if (i11 > i2) {
                    continue;
                } else if (i8 == 2) {
                    int i12 = updateOp.itemCount;
                    if (i2 < i11 + i12) {
                        return -1;
                    }
                    i2 -= i12;
                } else if (i8 == 1) {
                    i2 += updateOp.itemCount;
                }
            }
            i7++;
        }
        return i2;
    }
}
