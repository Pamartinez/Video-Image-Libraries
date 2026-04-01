package com.samsung.android.gallery.widget.listview.pinch.data;

import android.util.SparseArray;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AnimPositionCache {
    private final HashMap<Integer, SparseArray<Position>> mPositionCache = new HashMap<>();
    private final HashMap<Integer, Integer> mSpanCountCache = new HashMap<>();
    private final HashMap<Integer, Integer> mViewCountCache = new HashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Position {
        Integer dataPosition;
        Integer rowSpanCount;
        Integer spanSize;
        Integer startSpan;
        Integer toViewPosition;
        Integer viewHeight;

        public /* synthetic */ Position(int i2) {
            this();
        }

        private Position() {
        }
    }

    private Position getPositionCache(int i2, int i7) {
        SparseArray sparseArray = this.mPositionCache.get(Integer.valueOf(i7));
        if (sparseArray == null) {
            sparseArray = new SparseArray();
            this.mPositionCache.put(Integer.valueOf(i7), sparseArray);
        }
        Position position = (Position) sparseArray.get(i2);
        if (position != null) {
            return position;
        }
        Position position2 = new Position(0);
        sparseArray.put(i2, position2);
        return position2;
    }

    public void cacheViewSizeInfo(PinchLayoutManager pinchLayoutManager, int i2, int i7) {
        getHintRowSpan(pinchLayoutManager, i2, i7);
        getHintColumnSpan(pinchLayoutManager, i2, i7);
        getHintStartSpan(pinchLayoutManager, i2, i7);
        getHintViewHeight(pinchLayoutManager, i2, i7);
    }

    public void clear() {
        this.mPositionCache.clear();
        this.mSpanCountCache.clear();
        this.mViewCountCache.clear();
    }

    public int getHintColumnSpan(PinchLayoutManager pinchLayoutManager, int i2, int i7) {
        Position positionCache = getPositionCache(i2, i7);
        if (positionCache.spanSize == null) {
            positionCache.spanSize = Integer.valueOf(pinchLayoutManager.getHintColumnSpan(i2, i7));
        }
        return positionCache.spanSize.intValue();
    }

    public int getHintRowSpan(PinchLayoutManager pinchLayoutManager, int i2, int i7) {
        Position positionCache = getPositionCache(i2, i7);
        if (positionCache.rowSpanCount == null) {
            positionCache.rowSpanCount = Integer.valueOf(pinchLayoutManager.getHintRowSpan(i2, i7));
        }
        return positionCache.rowSpanCount.intValue();
    }

    public int getHintSpanCount(PinchLayoutManager pinchLayoutManager, int i2) {
        Integer num = this.mSpanCountCache.get(Integer.valueOf(i2));
        if (num == null) {
            num = Integer.valueOf(pinchLayoutManager.getHintSpanCount(i2));
            this.mSpanCountCache.put(Integer.valueOf(i2), num);
        }
        return num.intValue();
    }

    public int getHintStartSpan(PinchLayoutManager pinchLayoutManager, int i2, int i7) {
        Position positionCache = getPositionCache(i2, i7);
        if (positionCache.startSpan == null) {
            positionCache.startSpan = Integer.valueOf(pinchLayoutManager.getHintStartSpan(i2, i7));
        }
        return positionCache.startSpan.intValue();
    }

    public int getHintViewCount(PinchLayoutManager pinchLayoutManager, int i2) {
        Integer num = this.mViewCountCache.get(Integer.valueOf(i2));
        if (num == null) {
            num = Integer.valueOf(pinchLayoutManager.getHintViewCount(i2));
            this.mViewCountCache.put(Integer.valueOf(i2), num);
        }
        return num.intValue();
    }

    public int getHintViewHeight(PinchLayoutManager pinchLayoutManager, int i2, int i7) {
        Position positionCache = getPositionCache(i2, i7);
        if (positionCache.viewHeight == null) {
            positionCache.viewHeight = Integer.valueOf(pinchLayoutManager.getHintViewHeight(i2, i7));
        }
        return positionCache.viewHeight.intValue();
    }

    public int getHintViewPosition(PinchLayoutManager pinchLayoutManager, int i2, int i7, int i8) {
        Position positionCache = getPositionCache(i2, i7);
        if (positionCache.dataPosition == null) {
            positionCache.dataPosition = Integer.valueOf(pinchLayoutManager.getHintDataPosition(i2, i7));
        }
        if (positionCache.toViewPosition == null) {
            positionCache.toViewPosition = Integer.valueOf(pinchLayoutManager.getHintViewPosition(positionCache.dataPosition.intValue(), i8));
        }
        return positionCache.toViewPosition.intValue();
    }
}
