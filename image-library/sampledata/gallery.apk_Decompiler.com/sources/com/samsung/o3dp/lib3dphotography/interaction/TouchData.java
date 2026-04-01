package com.samsung.o3dp.lib3dphotography.interaction;

import android.graphics.PointF;
import android.util.SparseArray;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TouchData {
    private static final String TAG = "TouchData";
    final SparseArray<PointerData> mActivePointers = new SparseArray<>();
    boolean mIsMultiTouch = false;
    boolean mIsTouched = false;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PointerData {
        public PointF dragDist;
        public PointF pos = new PointF();
        public PointF startPos = new PointF();
        public long startTime;

        public PointerData(long j2, float f, float f5) {
            PointF pointF = new PointF();
            this.dragDist = pointF;
            this.startTime = j2;
            PointF pointF2 = this.startPos;
            PointF pointF3 = this.pos;
            pointF3.x = f;
            pointF2.x = f;
            pointF3.y = f5;
            pointF2.y = f5;
            pointF.y = 0.0f;
            pointF.x = 0.0f;
        }
    }

    public int[] getActivePointerIds() {
        int[] iArr = new int[this.mActivePointers.size()];
        for (int i2 = 0; i2 < this.mActivePointers.size(); i2++) {
            iArr[i2] = this.mActivePointers.keyAt(i2);
        }
        return iArr;
    }

    public PointF getDragDist(int i2) {
        PointerData pointerData = this.mActivePointers.get(i2);
        if (pointerData == null) {
            return null;
        }
        return new PointF(pointerData.dragDist);
    }

    public boolean getIsMultiTouch() {
        return this.mIsMultiTouch;
    }

    public boolean getIsTouched() {
        return this.mIsTouched;
    }

    public int getNumActivePointers() {
        return this.mActivePointers.size();
    }

    public PointF getPos(int i2) {
        PointerData pointerData = this.mActivePointers.get(i2);
        if (pointerData == null) {
            return null;
        }
        return new PointF(pointerData.pos);
    }

    public PointF getStartPos(int i2) {
        PointerData pointerData = this.mActivePointers.get(i2);
        if (pointerData == null) {
            return null;
        }
        return new PointF(pointerData.startPos);
    }

    public long getStartTime(int i2) {
        PointerData pointerData = this.mActivePointers.get(i2);
        if (pointerData == null) {
            return 0;
        }
        return pointerData.startTime;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r2 != 6) goto L_0x009b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.MotionEvent r9) {
        /*
            r8 = this;
            int r0 = r9.getActionIndex()
            int r1 = r9.getPointerId(r0)
            int r2 = r9.getActionMasked()
            r3 = 1
            if (r2 == 0) goto L_0x0073
            r4 = 0
            if (r2 == r3) goto L_0x005b
            r5 = 2
            if (r2 == r5) goto L_0x0020
            r5 = 3
            if (r2 == r5) goto L_0x005b
            r5 = 5
            if (r2 == r5) goto L_0x0073
            r0 = 6
            if (r2 == r0) goto L_0x005b
            goto L_0x009b
        L_0x0020:
            int r0 = r9.getPointerCount()
            if (r4 >= r0) goto L_0x009b
            android.util.SparseArray<com.samsung.o3dp.lib3dphotography.interaction.TouchData$PointerData> r0 = r8.mActivePointers
            int r1 = r9.getPointerId(r4)
            java.lang.Object r0 = r0.get(r1)
            com.samsung.o3dp.lib3dphotography.interaction.TouchData$PointerData r0 = (com.samsung.o3dp.lib3dphotography.interaction.TouchData.PointerData) r0
            if (r0 == 0) goto L_0x0058
            android.graphics.PointF r1 = r0.pos
            float r2 = r9.getX(r4)
            r1.x = r2
            android.graphics.PointF r1 = r0.pos
            float r2 = r9.getY(r4)
            r1.y = r2
            android.graphics.PointF r1 = r0.dragDist
            android.graphics.PointF r2 = r0.pos
            float r5 = r2.x
            android.graphics.PointF r0 = r0.startPos
            float r6 = r0.x
            float r5 = r5 - r6
            r1.x = r5
            float r2 = r2.y
            float r0 = r0.y
            float r2 = r2 - r0
            r1.y = r2
        L_0x0058:
            int r4 = r4 + 1
            goto L_0x0020
        L_0x005b:
            android.util.SparseArray<com.samsung.o3dp.lib3dphotography.interaction.TouchData$PointerData> r0 = r8.mActivePointers
            r0.remove(r1)
            android.util.SparseArray<com.samsung.o3dp.lib3dphotography.interaction.TouchData$PointerData> r0 = r8.mActivePointers
            int r0 = r0.size()
            if (r0 != 0) goto L_0x006a
            r8.mIsMultiTouch = r4
        L_0x006a:
            int r9 = r9.getPointerCount()
            if (r9 > r3) goto L_0x009b
            r8.mIsTouched = r4
            goto L_0x009b
        L_0x0073:
            android.util.SparseArray<com.samsung.o3dp.lib3dphotography.interaction.TouchData$PointerData> r2 = r8.mActivePointers
            com.samsung.o3dp.lib3dphotography.interaction.TouchData$PointerData r4 = new com.samsung.o3dp.lib3dphotography.interaction.TouchData$PointerData
            long r5 = r9.getEventTime()
            float r7 = r9.getX(r0)
            float r0 = r9.getY(r0)
            r4.<init>(r5, r7, r0)
            r2.put(r1, r4)
            android.util.SparseArray<com.samsung.o3dp.lib3dphotography.interaction.TouchData$PointerData> r0 = r8.mActivePointers
            int r0 = r0.size()
            if (r0 <= r3) goto L_0x0093
            r8.mIsMultiTouch = r3
        L_0x0093:
            int r9 = r9.getPointerCount()
            if (r9 < r3) goto L_0x009b
            r8.mIsTouched = r3
        L_0x009b:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r0 = "onTouch: "
            r9.<init>(r0)
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            java.lang.String r9 = "TouchData"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.d(r9, r8)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.o3dp.lib3dphotography.interaction.TouchData.onTouch(android.view.MotionEvent):boolean");
    }

    public String toString() {
        String str = "TouchData { ";
        for (int i2 = 0; i2 < this.mActivePointers.size(); i2++) {
            PointerData valueAt = this.mActivePointers.valueAt(i2);
            StringBuilder t = C0212a.t(str, "pointer[");
            t.append(this.mActivePointers.keyAt(i2));
            t.append("] = time ");
            t.append(valueAt.startTime);
            t.append(", pos (");
            t.append(valueAt.pos.x);
            t.append(ArcCommonLog.TAG_COMMA);
            t.append(valueAt.pos.y);
            t.append(") start (");
            t.append(valueAt.startPos.x);
            t.append(ArcCommonLog.TAG_COMMA);
            t.append(valueAt.startPos.y);
            t.append(") ");
            str = t.toString();
        }
        return C0212a.A(str, " }");
    }
}
