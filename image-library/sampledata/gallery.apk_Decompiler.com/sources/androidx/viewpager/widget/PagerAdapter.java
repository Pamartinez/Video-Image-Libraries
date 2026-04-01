package androidx.viewpager.widget;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PagerAdapter {
    private final DataSetObservable mObservable = new DataSetObservable();
    private DataSetObserver mViewPagerObserver;

    @Deprecated
    public abstract void destroyItem(View view, int i2, Object obj);

    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        destroyItem((View) viewGroup, i2, obj);
    }

    @Deprecated
    public abstract void finishUpdate(View view);

    public void finishUpdate(ViewGroup viewGroup) {
        finishUpdate((View) viewGroup);
    }

    public abstract int getCount();

    public abstract int getItemPosition(Object obj);

    public CharSequence getPageTitle(int i2) {
        return null;
    }

    public float getPageWidth(int i2) {
        return 1.0f;
    }

    @Deprecated
    public abstract Object instantiateItem(View view, int i2);

    public Object instantiateItem(ViewGroup viewGroup, int i2) {
        return instantiateItem((View) viewGroup, i2);
    }

    public abstract boolean isViewFromObject(View view, Object obj);

    public void notifyDataSetChanged() {
        synchronized (this) {
            try {
                DataSetObserver dataSetObserver = this.mViewPagerObserver;
                if (dataSetObserver != null) {
                    dataSetObserver.onChanged();
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.mObservable.notifyChanged();
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.mObservable.registerObserver(dataSetObserver);
    }

    public abstract Parcelable saveState();

    @Deprecated
    public void setPrimaryItem(View view, int i2, Object obj) {
    }

    public void setViewPagerObserver(DataSetObserver dataSetObserver) {
        synchronized (this) {
            this.mViewPagerObserver = dataSetObserver;
        }
    }

    @Deprecated
    public abstract void startUpdate(View view);

    public void startUpdate(ViewGroup viewGroup) {
        startUpdate((View) viewGroup);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.mObservable.unregisterObserver(dataSetObserver);
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i2, Object obj) {
        setPrimaryItem((View) viewGroup, i2, obj);
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }
}
