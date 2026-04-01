package com.samsung.android.gallery.widget.listview.selection;

import android.os.Handler;
import android.os.Looper;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import i.C0212a;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelectionManager {
    private final LinkedHashSet<Integer> mDividerPositionList = new LinkedHashSet<>();
    Handler mHandlerChange;
    private int mMaxCount = 0;
    private OnItemCheckChangeListener mOnItemCheckChangeListener;
    private int mSelectMode = 0;
    SelectionManagerInterface mSelectableListener;
    private int mTotalCount;
    private Runnable mUpdateToolbarRunnable;
    private final LinkedHashSet<Integer> mViewPositionList = new LinkedHashSet<>();

    public SelectionManager(SelectionManagerInterface selectionManagerInterface) {
        this.mSelectableListener = selectionManagerInterface;
        this.mHandlerChange = new Handler(Looper.getMainLooper());
    }

    private void add(Integer num, boolean z) {
        synchronized (this.mViewPositionList) {
            try {
                if (!this.mViewPositionList.contains(num)) {
                    this.mViewPositionList.add(num);
                    postUpdateToolbar();
                    if (z) {
                        notifyOnChangeListener(num, true);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void addDivider(Integer num, boolean z) {
        if (!this.mDividerPositionList.contains(num)) {
            this.mDividerPositionList.add(num);
            postUpdateToolbar();
            if (z) {
                notifyOnChangeListener(num, true);
            }
        }
    }

    private void clear(boolean z, boolean z3) {
        synchronized (this.mViewPositionList) {
            try {
                this.mViewPositionList.clear();
                this.mDividerPositionList.clear();
                if (z3) {
                    for (int i2 = 0; i2 < this.mTotalCount; i2++) {
                        if (this.mSelectableListener.isItemSelectable(i2)) {
                            if (this.mSelectableListener.isDivider(i2)) {
                                this.mDividerPositionList.add(Integer.valueOf(i2));
                            } else if (this.mSelectableListener.isData(i2) && !this.mViewPositionList.contains(Integer.valueOf(i2))) {
                                this.mViewPositionList.add(Integer.valueOf(i2));
                            }
                        }
                    }
                }
            } finally {
                while (true) {
                }
            }
        }
        if (z) {
            postUpdateToolbar();
            OnItemCheckChangeListener onItemCheckChangeListener = this.mOnItemCheckChangeListener;
            if (onItemCheckChangeListener != null) {
                onItemCheckChangeListener.onItemCheckChangedAll(z3);
            }
        }
    }

    private int getItemListSize() {
        int size;
        synchronized (this.mViewPositionList) {
            size = this.mViewPositionList.size();
        }
        return size;
    }

    private boolean isSelectedInternal(Integer num) {
        if (this.mViewPositionList.contains(num) || this.mDividerPositionList.contains(num)) {
            return true;
        }
        return false;
    }

    private void notifyOnChangeListener(Integer num, boolean z) {
        OnItemCheckChangeListener onItemCheckChangeListener = this.mOnItemCheckChangeListener;
        if (onItemCheckChangeListener != null) {
            onItemCheckChangeListener.onItemCheckChanged(num, z);
        }
    }

    private void postUpdateToolbar() {
        if (this.mUpdateToolbarRunnable != null) {
            this.mHandlerChange.removeCallbacksAndMessages((Object) null);
            this.mHandlerChange.post(this.mUpdateToolbarRunnable);
        }
    }

    private void remove(Integer num, boolean z) {
        synchronized (this.mViewPositionList) {
            try {
                this.mViewPositionList.remove(num);
                postUpdateToolbar();
                if (z) {
                    notifyOnChangeListener(num, false);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void removeDivider(Integer num, boolean z) {
        this.mDividerPositionList.remove(num);
        postUpdateToolbar();
        if (z) {
            notifyOnChangeListener(num, false);
        }
    }

    public void clearAll(boolean z) {
        clear(z, false);
    }

    public int getMaxCount() {
        int i2 = this.mMaxCount;
        if (i2 == 0) {
            return -1;
        }
        return i2;
    }

    public ArrayList<Integer> getSelectedDividerList() {
        synchronized (this.mDividerPositionList) {
            try {
                if (this.mSelectMode != 1) {
                    return null;
                }
                ArrayList<Integer> arrayList = new ArrayList<>(this.mDividerPositionList);
                return arrayList;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int getSelectedItemCount() {
        int i2 = this.mSelectMode;
        if (i2 == 1 || i2 == 2) {
            return getItemListSize();
        }
        return 0;
    }

    public ArrayList<Integer> getSelectedList() {
        synchronized (this.mViewPositionList) {
            try {
                if (this.mSelectMode != 1) {
                    return null;
                }
                ArrayList<Integer> arrayList = new ArrayList<>(this.mViewPositionList);
                return arrayList;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean isMultipleSelectMode() {
        if (this.mSelectMode == 1) {
            return true;
        }
        return false;
    }

    public boolean isSelectMode() {
        if (this.mSelectMode != 0) {
            return true;
        }
        return false;
    }

    public boolean isSelected(Integer num) {
        if (this.mSelectMode == 1) {
            return isSelectedInternal(num);
        }
        return false;
    }

    public boolean isSelectedData(Integer num) {
        return this.mViewPositionList.contains(num);
    }

    public boolean isSingleSelectMode() {
        if (this.mSelectMode == 2) {
            return true;
        }
        return false;
    }

    public void onDump(PrintWriter printWriter, String str) {
        StringBuilder t = C0212a.t(str, "SelectionManager");
        t.append(Logger.v(Integer.valueOf(this.mTotalCount), Integer.valueOf(this.mMaxCount), Integer.valueOf(this.mSelectMode), Integer.valueOf(this.mViewPositionList.size())));
        Logger.dumpLog(printWriter, t.toString());
        Logger.dumpLog(printWriter, str + "SelectionManager.mDividerPositionList : " + this.mDividerPositionList.size());
    }

    public void replaceSelection(int i2, List<Integer> list) {
        StringBuilder o2 = C0086a.o(i2, "replaceSelection : ", ArcCommonLog.TAG_COMMA);
        o2.append(list.size());
        Log.d("SelectionManager", o2.toString());
        this.mTotalCount = i2;
        int i7 = 1;
        if (this.mMaxCount == 1 && !this.mSelectableListener.isMultiPick()) {
            i7 = 2;
        }
        this.mSelectMode = i7;
        synchronized (this.mViewPositionList) {
            this.mViewPositionList.clear();
            this.mDividerPositionList.clear();
            this.mViewPositionList.addAll(list);
        }
    }

    public void select(Integer num, boolean z, boolean z3) {
        if (this.mSelectableListener.isDivider(num.intValue())) {
            if (z) {
                addDivider(num, false);
            } else {
                removeDivider(num, false);
            }
        } else if (z) {
            add(num, z3);
        } else {
            remove(num, z3);
        }
    }

    public void selectAll(boolean z, boolean z3) {
        this.mSelectMode = 1;
        synchronized (this.mViewPositionList) {
            try {
                this.mViewPositionList.clear();
                this.mDividerPositionList.clear();
                int i2 = this.mTotalCount - (z3 ? 1 : 0);
                for (int i7 = z; i7 < i2; i7++) {
                    if (this.mSelectableListener.isDivider(i7)) {
                        this.mDividerPositionList.add(Integer.valueOf(i7));
                    } else {
                        this.mViewPositionList.add(Integer.valueOf(i7));
                    }
                }
            } finally {
                while (true) {
                }
            }
        }
        OnItemCheckChangeListener onItemCheckChangeListener = this.mOnItemCheckChangeListener;
        if (onItemCheckChangeListener != null) {
            onItemCheckChangeListener.onSelectAll();
            this.mOnItemCheckChangeListener.onItemCheckChangedAll(true);
        }
    }

    public void setOnItemCheckChangedListener(OnItemCheckChangeListener onItemCheckChangeListener) {
        this.mOnItemCheckChangeListener = onItemCheckChangeListener;
    }

    public void setUpdateToolbarRunnable(Runnable runnable) {
        this.mUpdateToolbarRunnable = runnable;
    }

    public boolean startSelect(int i2, int i7) {
        if (!this.mSelectableListener.isListSelectable()) {
            Log.d("SelectionManager", "list is not selectable");
            return false;
        }
        this.mTotalCount = i2;
        this.mMaxCount = i7;
        if (i7 != 1 || this.mSelectableListener.isMultiPick()) {
            this.mSelectMode = 1;
        } else {
            this.mSelectMode = 2;
        }
        return true;
    }

    public void stopSelect() {
        this.mSelectMode = 0;
        clear(true, false);
    }

    public void syncAllDivider(boolean z) {
        this.mDividerPositionList.clear();
        int i2 = -1;
        boolean z3 = true;
        for (int i7 = z; i7 < this.mTotalCount; i7++) {
            if (this.mSelectableListener.isDivider(i7)) {
                if (i2 != -1 && z3) {
                    this.mDividerPositionList.add(Integer.valueOf(i2));
                }
                i2 = i7;
            } else if (!z3 || !this.mViewPositionList.contains(Integer.valueOf(i7))) {
                z3 = false;
            }
            z3 = true;
        }
        if (i2 != -1 && z3) {
            this.mDividerPositionList.add(Integer.valueOf(i2));
        }
    }

    public void unSelectAll() {
        this.mSelectMode = 1;
        clear(true, false);
        OnItemCheckChangeListener onItemCheckChangeListener = this.mOnItemCheckChangeListener;
        if (onItemCheckChangeListener != null) {
            onItemCheckChangeListener.onUnSelectAll();
        }
    }

    public void updateTotalCount(int i2) {
        this.mTotalCount = i2;
    }
}
