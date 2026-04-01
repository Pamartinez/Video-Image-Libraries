package com.samsung.android.gallery.support.utils;

import android.database.AbstractCursor;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CollectionCursor<E> extends AbstractCursor implements Cloneable {
    private final String[] columnNames;
    private final ArrayList<E> list;

    public CollectionCursor(String str) {
        this(str, (Collection) null);
    }

    public void add(E e) {
        this.list.add(e);
    }

    public void addAll(Collection<E> collection) {
        this.list.addAll(collection);
    }

    public void close() {
        this.list.clear();
        super.close();
    }

    public E get() {
        int position = getPosition();
        if (position < 0) {
            throw new CursorIndexOutOfBoundsException("Before first row.");
        } else if (position < getCount()) {
            return this.list.get(position);
        } else {
            throw new CursorIndexOutOfBoundsException("After last row.");
        }
    }

    public String[] getColumnNames() {
        return this.columnNames;
    }

    public int getCount() {
        return this.list.size();
    }

    public double getDouble(int i2) {
        Object obj = get();
        if (obj == null) {
            return MapUtil.INVALID_LOCATION;
        }
        if (obj instanceof Double) {
            return ((Double) obj).doubleValue();
        }
        return Double.parseDouble(obj.toString());
    }

    public float getFloat(int i2) {
        Object obj = get();
        if (obj == null) {
            return 0.0f;
        }
        if (obj instanceof Float) {
            return ((Float) obj).floatValue();
        }
        return Float.parseFloat(obj.toString());
    }

    public int getInt(int i2) {
        Object obj = get();
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return Integer.parseInt(obj.toString());
    }

    public long getLong(int i2) {
        Object obj = get();
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        return Long.parseLong(obj.toString());
    }

    public ArrayList<E> getRawData() {
        return this.list;
    }

    public short getShort(int i2) {
        Object obj = get();
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Short) {
            return ((Short) obj).shortValue();
        }
        return Short.parseShort(obj.toString());
    }

    public String getString(int i2) {
        Object obj = get();
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        return obj.toString();
    }

    public int indexOf(E e) {
        return this.list.indexOf(e);
    }

    public boolean isNull(int i2) {
        if (get() == null) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "CollectionCursor[" + this.list.size() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getExtras() + "]";
    }

    public CollectionCursor(String str, Collection<E> collection) {
        ArrayList<E> arrayList = new ArrayList<>();
        this.list = arrayList;
        this.columnNames = new String[]{str};
        if (collection != null) {
            arrayList.addAll(collection);
        }
    }

    public void addAll(Object[] objArr, Function<Object, E> function) {
        for (Object apply : objArr) {
            this.list.add(function.apply(apply));
        }
    }

    public CollectionCursor<E> clone() {
        try {
            return (CollectionCursor) super.clone();
        } catch (CloneNotSupportedException e) {
            String simpleName = getClass().getSimpleName();
            Log.e(simpleName, "clone failed e=" + e.getMessage());
            return new CollectionCursor<>(this.columnNames[0], this.list);
        }
    }

    public CollectionCursor(Cursor cursor) {
        this((CollectionCursor) cursor);
    }

    public CollectionCursor(CollectionCursor<E> collectionCursor) {
        this(collectionCursor.columnNames[0], collectionCursor.list);
    }
}
