package androidx.cursoradapter.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.cursoradapter.widget.CursorFilter;
import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CursorAdapter extends BaseAdapter implements Filterable, CursorFilter.CursorFilterClient {
    protected boolean mAutoRequery;
    protected ChangeObserver mChangeObserver;
    protected Context mContext;
    protected Cursor mCursor;
    protected CursorFilter mCursorFilter;
    protected DataSetObserver mDataSetObserver;
    protected boolean mDataValid;
    protected int mRowIDColumn;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ChangeObserver extends ContentObserver {
        public ChangeObserver() {
            super(new Handler());
        }

        public boolean deliverSelfNotifications() {
            return true;
        }

        public void onChange(boolean z) {
            CursorAdapter.this.onContentChanged();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class MyDataSetObserver extends DataSetObserver {
        public MyDataSetObserver() {
        }

        public void onChanged() {
            CursorAdapter cursorAdapter = CursorAdapter.this;
            cursorAdapter.mDataValid = true;
            cursorAdapter.notifyDataSetChanged();
        }

        public void onInvalidated() {
            CursorAdapter cursorAdapter = CursorAdapter.this;
            cursorAdapter.mDataValid = false;
            cursorAdapter.notifyDataSetInvalidated();
        }
    }

    public CursorAdapter(Context context, Cursor cursor, boolean z) {
        int i2;
        if (z) {
            i2 = 1;
        } else {
            i2 = 2;
        }
        init(context, cursor, i2);
    }

    public abstract void bindView(View view, Context context, Cursor cursor);

    public void changeCursor(Cursor cursor) {
        Cursor swapCursor = swapCursor(cursor);
        if (swapCursor != null) {
            swapCursor.close();
        }
    }

    public abstract CharSequence convertToString(Cursor cursor);

    public int getCount() {
        Cursor cursor;
        if (!this.mDataValid || (cursor = this.mCursor) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    public Cursor getCursor() {
        return this.mCursor;
    }

    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        if (!this.mDataValid) {
            return null;
        }
        this.mCursor.moveToPosition(i2);
        if (view == null) {
            view = newDropDownView(this.mContext, this.mCursor, viewGroup);
        }
        bindView(view, this.mContext, this.mCursor);
        return view;
    }

    public Filter getFilter() {
        if (this.mCursorFilter == null) {
            this.mCursorFilter = new CursorFilter(this);
        }
        return this.mCursorFilter;
    }

    public Object getItem(int i2) {
        Cursor cursor;
        if (!this.mDataValid || (cursor = this.mCursor) == null) {
            return null;
        }
        cursor.moveToPosition(i2);
        return this.mCursor;
    }

    public long getItemId(int i2) {
        Cursor cursor;
        if (!this.mDataValid || (cursor = this.mCursor) == null || !cursor.moveToPosition(i2)) {
            return 0;
        }
        return this.mCursor.getLong(this.mRowIDColumn);
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (!this.mDataValid) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        } else if (this.mCursor.moveToPosition(i2)) {
            if (view == null) {
                view = newView(this.mContext, this.mCursor, viewGroup);
            }
            bindView(view, this.mContext, this.mCursor);
            return view;
        } else {
            throw new IllegalStateException(C0086a.i(i2, "couldn't move cursor to position "));
        }
    }

    public void init(Context context, Cursor cursor, int i2) {
        int i7;
        boolean z = false;
        if ((i2 & 1) == 1) {
            i2 |= 2;
            this.mAutoRequery = true;
        } else {
            this.mAutoRequery = false;
        }
        if (cursor != null) {
            z = true;
        }
        this.mCursor = cursor;
        this.mDataValid = z;
        this.mContext = context;
        if (z) {
            i7 = cursor.getColumnIndexOrThrow("_id");
        } else {
            i7 = -1;
        }
        this.mRowIDColumn = i7;
        if ((i2 & 2) == 2) {
            this.mChangeObserver = new ChangeObserver();
            this.mDataSetObserver = new MyDataSetObserver();
        } else {
            this.mChangeObserver = null;
            this.mDataSetObserver = null;
        }
        if (z) {
            ChangeObserver changeObserver = this.mChangeObserver;
            if (changeObserver != null) {
                cursor.registerContentObserver(changeObserver);
            }
            DataSetObserver dataSetObserver = this.mDataSetObserver;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    public abstract View newDropDownView(Context context, Cursor cursor, ViewGroup viewGroup);

    public abstract View newView(Context context, Cursor cursor, ViewGroup viewGroup);

    public void onContentChanged() {
        Cursor cursor;
        if (this.mAutoRequery && (cursor = this.mCursor) != null && !cursor.isClosed()) {
            this.mDataValid = this.mCursor.requery();
        }
    }

    public Cursor swapCursor(Cursor cursor) {
        Cursor cursor2 = this.mCursor;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            ChangeObserver changeObserver = this.mChangeObserver;
            if (changeObserver != null) {
                cursor2.unregisterContentObserver(changeObserver);
            }
            DataSetObserver dataSetObserver = this.mDataSetObserver;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.mCursor = cursor;
        if (cursor != null) {
            ChangeObserver changeObserver2 = this.mChangeObserver;
            if (changeObserver2 != null) {
                cursor.registerContentObserver(changeObserver2);
            }
            DataSetObserver dataSetObserver2 = this.mDataSetObserver;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.mRowIDColumn = cursor.getColumnIndexOrThrow("_id");
            this.mDataValid = true;
            notifyDataSetChanged();
            return cursor2;
        }
        this.mRowIDColumn = -1;
        this.mDataValid = false;
        notifyDataSetInvalidated();
        return cursor2;
    }
}
