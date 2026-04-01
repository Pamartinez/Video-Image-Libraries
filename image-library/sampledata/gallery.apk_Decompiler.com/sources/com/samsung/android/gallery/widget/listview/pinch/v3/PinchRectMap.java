package com.samsung.android.gallery.widget.listview.pinch.v3;

import Jb.c;
import android.graphics.RectF;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.data.AnimPositionCache;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinchRectMap {
    private final PinchRectDelegate mDelegate;
    private final HashMap<Integer, ArrayList<RectF>> mRectMap = new HashMap<>();

    public PinchRectMap(PinchLayoutManager pinchLayoutManager, AnimPositionCache animPositionCache, PinchRange pinchRange, int i2, GridInfoSupplier gridInfoSupplier) {
        this.mDelegate = new PinchRectDelegate(pinchLayoutManager, animPositionCache, pinchRange, i2, gridInfoSupplier);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getLog$0(StringBuilder sb2, Integer num, ArrayList arrayList) {
        if (arrayList != null) {
            sb2.append("\n[");
            sb2.append(num);
            sb2.append("]");
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                sb2.append(((RectF) it.next()).toShortString());
            }
        }
    }

    public void fillDefaultRange() {
        this.mDelegate.fillDefaultRange(this.mRectMap);
    }

    public RectF get(int i2, int i7) {
        ArrayList<RectF> rectList = getRectList(i2);
        if (rectList == null) {
            rectList = this.mDelegate.addRow(this.mRectMap, i2);
        }
        if (rectList.isEmpty()) {
            return null;
        }
        if (rectList.size() <= i7) {
            this.mDelegate.addColumn(rectList, i7);
        }
        return rectList.get(i7);
    }

    public String getLog() {
        StringBuilder sb2 = new StringBuilder("PinchRectMap");
        this.mRectMap.forEach(new c(sb2, 0));
        return sb2.toString();
    }

    public int getMinRow() {
        return this.mDelegate.getMinRow(this.mRectMap).intValue();
    }

    public ArrayList<RectF> getRectList(int i2) {
        return this.mRectMap.get(Integer.valueOf(i2));
    }

    public RectF getTopRect() {
        return getRectList(getMinRow()).get(0);
    }

    public void setItemGap(int i2, int i7) {
        this.mDelegate.setItemGap(i2, i7);
    }

    public void setStartOffset(int i2) {
        this.mDelegate.setStartOffset(i2);
    }
}
