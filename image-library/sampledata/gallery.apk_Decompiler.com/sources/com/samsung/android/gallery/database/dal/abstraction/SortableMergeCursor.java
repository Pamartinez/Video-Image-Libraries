package com.samsung.android.gallery.database.dal.abstraction;

import A.a;
import android.database.Cursor;
import android.database.MergeCursor;
import android.util.Pair;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SortableMergeCursor extends MergeCursor {
    private final Map<Integer, Pair<Integer, Integer>> mCursorIndexer = new ConcurrentHashMap();
    protected final Cursor[] mCursors;
    private final List<Pair<String, Boolean>> mOrderBy = new ArrayList();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OrderBy {
        private final Cursor[] cursors;
        private final List<Boolean> descending;
        private final List<Pair<Integer, Integer>> orderByColumns;

        public /* synthetic */ OrderBy(Cursor[] cursorArr, int i2) {
            this(cursorArr);
        }

        /* access modifiers changed from: private */
        public int count() {
            return this.orderByColumns.size();
        }

        /* access modifiers changed from: private */
        public boolean descendingOrder(int i2) {
            return this.descending.get(i2).booleanValue();
        }

        /* access modifiers changed from: private */
        public int getColumnIndex(int i2) {
            return ((Integer) this.orderByColumns.get(i2).first).intValue();
        }

        /* access modifiers changed from: private */
        public int getType(int i2) {
            return ((Integer) this.orderByColumns.get(i2).second).intValue();
        }

        /* access modifiers changed from: private */
        public List<OrderingRecord> orderBy() {
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            while (true) {
                Cursor[] cursorArr = this.cursors;
                if (i2 < cursorArr.length) {
                    Cursor cursor = cursorArr[i2];
                    if (cursor == null || !cursor.moveToFirst()) {
                        i2++;
                    } else {
                        do {
                            arrayList.add(new OrderingRecord(i2, this, cursor, 0));
                        } while (cursor.moveToNext());
                        i2++;
                    }
                } else {
                    Collections.sort(arrayList);
                    return arrayList;
                }
            }
        }

        private void parseColumnInfo(List<Pair<String, Boolean>> list) {
            Cursor[] cursorArr = this.cursors;
            if (cursorArr == null) {
                Log.w("SortableMergeCursor", "no cursors");
                return;
            }
            int length = cursorArr.length;
            int i2 = 0;
            while (i2 < length) {
                Cursor cursor = cursorArr[i2];
                if (cursor != null && cursor.moveToFirst()) {
                    for (Pair next : list) {
                        int columnIndex = cursor.getColumnIndex((String) next.first);
                        if (columnIndex != -1) {
                            this.orderByColumns.add(new Pair(Integer.valueOf(columnIndex), Integer.valueOf(cursor.getType(columnIndex))));
                            this.descending.add((Boolean) next.second);
                        } else {
                            Log.w("SortableMergeCursor", "unsupported column=" + ((String) next.first));
                        }
                    }
                }
                if (this.orderByColumns.isEmpty()) {
                    i2++;
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: private */
        public OrderBy setOrderBy(List<Pair<String, Boolean>> list) {
            parseColumnInfo(list);
            return this;
        }

        private OrderBy(Cursor[] cursorArr) {
            this.orderByColumns = new ArrayList();
            this.descending = new ArrayList();
            this.cursors = cursorArr;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OrderingRecord implements Comparable<OrderingRecord> {
        private final List<Object> comparableValues;
        /* access modifiers changed from: private */
        public final int cursorIndex;
        private final OrderBy mOrderBy;
        /* access modifiers changed from: private */
        public int position;

        public /* synthetic */ OrderingRecord(int i2, OrderBy orderBy, Cursor cursor, int i7) {
            this(i2, orderBy, cursor);
        }

        private Object getValue(int i2) {
            return this.comparableValues.get(i2);
        }

        private void loadData(Cursor cursor) {
            this.position = cursor.getPosition();
            OrderBy orderBy = this.mOrderBy;
            for (int i2 = 0; i2 < orderBy.count(); i2++) {
                int c5 = orderBy.getColumnIndex(i2);
                int d = orderBy.getType(i2);
                if (d == 1) {
                    this.comparableValues.add(Long.valueOf(cursor.getLong(c5)));
                } else if (d != 2) {
                    Log.e("SortableMergeCursor", "invalid column type {" + cursor.getColumnName(c5) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + d + "}");
                } else {
                    this.comparableValues.add(Float.valueOf(cursor.getFloat(c5)));
                }
            }
        }

        private OrderingRecord(int i2, OrderBy orderBy, Cursor cursor) {
            this.comparableValues = new ArrayList();
            this.cursorIndex = i2;
            this.mOrderBy = orderBy;
            loadData(cursor);
        }

        public int compareTo(OrderingRecord orderingRecord) {
            int compare;
            for (int i2 = 0; i2 < this.comparableValues.size(); i2++) {
                int d = this.mOrderBy.getType(i2);
                if (d == 1) {
                    int compare2 = Long.compare(((Long) getValue(i2)).longValue(), ((Long) orderingRecord.getValue(i2)).longValue());
                    if (compare2 != 0) {
                        return this.mOrderBy.descendingOrder(i2) ? compare2 : -compare2;
                    }
                } else if (d == 2 && (compare = Float.compare(((Float) getValue(i2)).floatValue(), ((Float) orderingRecord.getValue(i2)).floatValue())) != 0) {
                    return this.mOrderBy.descendingOrder(i2) ? compare : -compare;
                }
            }
            return 0;
        }
    }

    public SortableMergeCursor(Cursor[] cursorArr) {
        super(cursorArr);
        this.mCursors = cursorArr;
    }

    private int indexingOrderedCursors(List<OrderingRecord> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            OrderingRecord orderingRecord = list.get(i2);
            this.mCursorIndexer.put(Integer.valueOf(i2), new Pair(Integer.valueOf(orderingRecord.cursorIndex), Integer.valueOf(orderingRecord.position)));
        }
        return this.mCursorIndexer.size();
    }

    public SortableMergeCursor addOrderBy(String str, boolean z) {
        this.mOrderBy.add(new Pair(str, Boolean.valueOf(z)));
        return this;
    }

    public final int getMovePosition(int i2) {
        Pair pair = this.mCursorIndexer.get(Integer.valueOf(i2));
        if (pair == null) {
            return -1;
        }
        int i7 = 0;
        for (int i8 = 0; i8 < ((Integer) pair.first).intValue(); i8++) {
            i7 += this.mCursors[i8].getCount();
        }
        return ((Integer) pair.second).intValue() + i7;
    }

    public boolean onMove(int i2, int i7) {
        int movePosition = getMovePosition(i7);
        if (movePosition != -1) {
            return super.onMove(i2, movePosition);
        }
        return super.onMove(i2, i7);
    }

    public void orderBy() {
        Cursor[] cursorArr = this.mCursors;
        if (cursorArr != null && cursorArr.length != 0) {
            a.x(C0086a.o(indexingOrderedCursors(new OrderBy(this.mCursors, 0).setOrderBy(this.mOrderBy).orderBy()), "orderBy {", "} + "), System.currentTimeMillis(), "SortableMergeCursor");
        }
    }
}
