package com.samsung.android.gallery.support.utils;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWrapper;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import c0.C0086a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CursorCompat {
    public static final Cursor EMPTY_CURSOR = new CursorWrapper((Cursor) null) {
        public int getCount() {
            return 0;
        }

        public boolean isClosed() {
            return true;
        }

        public boolean moveToFirst() {
            return false;
        }

        public boolean moveToNext() {
            return false;
        }

        public void close() {
        }
    };
    final int columnCount;
    final String[] columnNames;
    final int[] columnTypes;
    final ArrayList<Object[]> list = new ArrayList<>();

    public CursorCompat(Cursor cursor, int i2) {
        String[] columnNames2 = cursor.getColumnNames();
        this.columnNames = columnNames2;
        int length = columnNames2.length;
        this.columnCount = length;
        this.columnTypes = new int[length];
        backup(cursor, i2);
    }

    public static CursorCompat fromJsonString(String str) {
        return (CursorCompat) GsonTool.fromJsonString(CursorCompat.class, str);
    }

    private void initColumnTypes(Cursor cursor) {
        for (int i2 = 0; i2 < this.columnCount; i2++) {
            this.columnTypes[i2] = cursor.getType(i2);
            if (this.columnTypes[i2] == 0 && "__media_cache".equals(this.columnNames[i2])) {
                this.columnTypes[i2] = 4;
            }
        }
    }

    private Object[] loadColumn(Cursor cursor) {
        Object[] objArr = new Object[this.columnCount];
        int i2 = 0;
        while (i2 < this.columnCount) {
            try {
                Object obj = null;
                try {
                    int i7 = this.columnTypes[i2];
                    if (i7 != 0) {
                        if (i7 == 1) {
                            objArr[i2] = Long.valueOf(cursor.getLong(i2));
                        } else if (i7 == 2) {
                            objArr[i2] = Float.valueOf(cursor.getFloat(i2));
                        } else if (i7 != 3) {
                            if (i7 == 4) {
                                objArr[i2] = null;
                            }
                        }
                        i2++;
                    }
                    objArr[i2] = cursor.getString(i2);
                } catch (SQLiteException e) {
                    Log.e("CursorCompat", "loadColumn failed {" + this.columnCount + "[" + i2 + "], type=" + cursor.getType(i2) + " vs " + this.columnTypes[i2] + " }. e=" + e.getMessage());
                    objArr[i2] = obj;
                }
                i2++;
            } catch (CursorIndexOutOfBoundsException e7) {
                Log.e("CursorCompat", "loadColumn failed {" + this.columnCount + "}. e=" + e7.getMessage());
            }
        }
        return objArr;
    }

    public CursorCompat backup(Cursor cursor, int i2) {
        this.list.clear();
        if (i2 < 0) {
            i2 = Integer.MAX_VALUE;
        }
        int min = Math.min(i2, cursor.getCount());
        if (cursor.moveToFirst()) {
            initColumnTypes(cursor);
            int i7 = 0;
            do {
                this.list.add(loadColumn(cursor));
                i7++;
                if (i7 >= min) {
                    break;
                }
            } while (!cursor.moveToNext());
        }
        return this;
    }

    public boolean equals(Object obj) {
        if (obj instanceof CursorCompat) {
            CursorCompat cursorCompat = (CursorCompat) obj;
            if (this.columnCount == cursorCompat.columnCount && this.list.size() == cursorCompat.list.size() && Arrays.equals(this.columnNames, cursorCompat.columnNames)) {
                for (int i2 = 0; i2 < this.list.size(); i2++) {
                    if (!Arrays.equals(this.list.get(i2), cursorCompat.list.get(i2))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public Cursor toCursor() {
        MatrixCursor matrixCursor = new MatrixCursor(this.columnNames);
        Iterator<Object[]> it = this.list.iterator();
        while (it.hasNext()) {
            matrixCursor.addRow(it.next());
        }
        return matrixCursor;
    }

    public String toJsonString() {
        return GsonTool.toJsonString(this, false);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("CursorCompat");
        sb2.append(Integer.toHexString(hashCode()));
        sb2.append('{');
        sb2.append(this.list.size());
        sb2.append(',');
        sb2.append(this.columnCount);
        sb2.append(',');
        return C0086a.m(sb2, TextUtils.join("|", this.columnNames), '}');
    }
}
