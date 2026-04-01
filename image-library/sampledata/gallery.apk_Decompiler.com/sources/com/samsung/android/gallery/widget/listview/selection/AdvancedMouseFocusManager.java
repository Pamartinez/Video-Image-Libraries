package com.samsung.android.gallery.widget.listview.selection;

import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AdvancedMouseFocusManager {
    private float mDownX;
    private float mDownY;
    private boolean mIsFirstAcceptableMoveForDrag;
    private int mLastSelectedViewPosition = 0;
    private int mLastSelectedViewPositionOnMotionEventDown = -1;
    private boolean mValidItemSelectedOnActionDown;
    private final LinkedHashSet<Integer> mViewPositionList = new LinkedHashSet<>();

    private boolean isAvailableAdvancedMouseDragThreshold(float f, float f5, float f8, float f10) {
        float abs = Math.abs(f - f5);
        float abs2 = Math.abs(f8 - f10);
        if (abs > 10.0f || abs2 > 10.0f) {
            return true;
        }
        return false;
    }

    private void updateAdvancedMouseFocus(MotionEvent motionEvent, int i2, Runnable runnable) {
        if (i2 == -1) {
            clearViewPosition();
            runnable.run();
        } else if (motionEvent.getButtonState() == 2 && !containsViewPosition(i2)) {
            clearViewPosition();
            addViewPosition(i2);
            runnable.run();
        }
    }

    public boolean addViewPosition(int i2) {
        return addViewPosition(i2, false);
    }

    public boolean checkConsumeTouchEventOnAdvancedMouseAction(MotionEvent motionEvent, Function<Integer, Boolean> function, Runnable runnable) {
        if (motionEvent.getToolType(0) == 3) {
            if (motionEvent.getAction() == 0) {
                this.mIsFirstAcceptableMoveForDrag = false;
                this.mDownX = motionEvent.getX();
                this.mDownY = motionEvent.getY();
                int i2 = this.mLastSelectedViewPositionOnMotionEventDown;
                if (i2 == -1 || !function.apply(Integer.valueOf(i2)).booleanValue()) {
                    updateAdvancedMouseFocus(motionEvent, i2, runnable);
                    this.mValidItemSelectedOnActionDown = false;
                } else {
                    updateAdvancedMouseFocus(motionEvent, i2, runnable);
                    this.mValidItemSelectedOnActionDown = true;
                    return false;
                }
            } else if (motionEvent.getAction() == 2 && this.mValidItemSelectedOnActionDown && !this.mIsFirstAcceptableMoveForDrag && isAvailableAdvancedMouseDragThreshold(motionEvent.getX(), this.mDownX, motionEvent.getY(), this.mDownY)) {
                this.mIsFirstAcceptableMoveForDrag = true;
                return false;
            }
            if (motionEvent.getAction() == 3 || !isAvailableAdvancedMouseDragThreshold(motionEvent.getX(), this.mDownX, motionEvent.getY(), this.mDownY)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void clearLastSelectedViewPosition() {
        this.mLastSelectedViewPosition = -1;
    }

    public void clearViewPosition() {
        synchronized (this.mViewPositionList) {
            this.mViewPositionList.clear();
        }
    }

    public boolean containsViewPosition(int i2) {
        boolean contains;
        synchronized (this.mViewPositionList) {
            contains = this.mViewPositionList.contains(Integer.valueOf(i2));
        }
        return contains;
    }

    public int getLastSelectedViewPosition() {
        return this.mLastSelectedViewPosition;
    }

    public ArrayList<Integer> getSelectedList() {
        ArrayList<Integer> arrayList;
        synchronized (this.mViewPositionList) {
            arrayList = new ArrayList<>(this.mViewPositionList);
        }
        return arrayList;
    }

    public int getTotalCount() {
        int size;
        synchronized (this.mViewPositionList) {
            size = this.mViewPositionList.size();
        }
        return size;
    }

    public boolean removeViewPosition(int i2) {
        boolean remove;
        synchronized (this.mViewPositionList) {
            this.mLastSelectedViewPosition = i2;
            remove = this.mViewPositionList.remove(Integer.valueOf(i2));
        }
        return remove;
    }

    public void setLastSelectedViewPosition(int i2) {
        this.mLastSelectedViewPosition = i2;
    }

    public void setLastSelectedViewPositionOnMotionEventDown(int i2) {
        this.mLastSelectedViewPositionOnMotionEventDown = i2;
    }

    public boolean addViewPosition(int i2, boolean z) {
        synchronized (this.mViewPositionList) {
            try {
                if (containsViewPosition(i2)) {
                    return true;
                }
                if (!z) {
                    this.mLastSelectedViewPosition = i2;
                }
                boolean add = this.mViewPositionList.add(Integer.valueOf(i2));
                return add;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
