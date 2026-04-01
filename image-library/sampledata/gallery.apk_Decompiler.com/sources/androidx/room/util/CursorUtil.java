package androidx.room.util;

import Ae.b;
import B1.a;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.util.Log;
import i.C0212a;
import java.io.Closeable;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1192j;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u001a\u0015\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u001d\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\b\u001a\u001d\u0010\t\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\b\u001a\u001f\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"Landroid/database/Cursor;", "c", "copyAndClose", "(Landroid/database/Cursor;)Landroid/database/Cursor;", "", "name", "", "getColumnIndex", "(Landroid/database/Cursor;Ljava/lang/String;)I", "getColumnIndexOrThrow", "cursor", "findColumnIndexBySuffix", "room-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CursorUtil {
    public static final Cursor copyAndClose(Cursor cursor) {
        j.e(cursor, "c");
        Closeable closeable = cursor;
        try {
            Cursor cursor2 = (Cursor) closeable;
            MatrixCursor matrixCursor = new MatrixCursor(cursor2.getColumnNames(), cursor2.getCount());
            while (cursor2.moveToNext()) {
                Object[] objArr = new Object[cursor2.getColumnCount()];
                int columnCount = cursor.getColumnCount();
                for (int i2 = 0; i2 < columnCount; i2++) {
                    int type = cursor2.getType(i2);
                    if (type == 0) {
                        objArr[i2] = null;
                    } else if (type == 1) {
                        objArr[i2] = Long.valueOf(cursor2.getLong(i2));
                    } else if (type == 2) {
                        objArr[i2] = Double.valueOf(cursor2.getDouble(i2));
                    } else if (type == 3) {
                        objArr[i2] = cursor2.getString(i2);
                    } else if (type == 4) {
                        objArr[i2] = cursor2.getBlob(i2);
                    } else {
                        throw new IllegalStateException();
                    }
                }
                matrixCursor.addRow(objArr);
            }
            closeable.close();
            return matrixCursor;
        } catch (Throwable th) {
            a.g(closeable, th);
            throw th;
        }
    }

    private static final int findColumnIndexBySuffix(Cursor cursor, String str) {
        return -1;
    }

    public static final int getColumnIndex(Cursor cursor, String str) {
        j.e(cursor, "c");
        j.e(str, "name");
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        int columnIndex2 = cursor.getColumnIndex("`" + str + '`');
        if (columnIndex2 >= 0) {
            return columnIndex2;
        }
        return findColumnIndexBySuffix(cursor, str);
    }

    public static final int getColumnIndexOrThrow(Cursor cursor, String str) {
        String str2;
        j.e(cursor, "c");
        j.e(str, "name");
        int columnIndex = getColumnIndex(cursor, str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        try {
            String[] columnNames = cursor.getColumnNames();
            j.d(columnNames, "c.columnNames");
            str2 = C1192j.s0(columnNames, (String) null, (String) null, (String) null, (b) null, 63);
        } catch (Exception e) {
            Log.d("RoomCursorUtil", "Cannot collect column names for debug purposes", e);
            str2 = "unknown";
        }
        throw new IllegalArgumentException(C0212a.n("column '", str, "' does not exist. Available columns: ", str2));
    }
}
