package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PreferenceGroup extends Preference {
    private boolean mAttachedToHierarchy;
    private final Runnable mClearRecycleCacheRunnable;
    private int mCurrentPreferenceOrder;
    private final Handler mHandler;
    final SimpleArrayMap<String, Long> mIdRecycleCache;
    private int mInitialExpandedChildrenCount;
    private boolean mOrderingAsAdded;
    private final List<Preference> mPreferences;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnExpandButtonClickListener {
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        this.mIdRecycleCache = new SimpleArrayMap<>();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mOrderingAsAdded = true;
        this.mCurrentPreferenceOrder = 0;
        this.mAttachedToHierarchy = false;
        this.mInitialExpandedChildrenCount = Integer.MAX_VALUE;
        this.mClearRecycleCacheRunnable = new Runnable() {
            public void run() {
                synchronized (this) {
                    PreferenceGroup.this.mIdRecycleCache.clear();
                }
            }
        };
        this.mPreferences = new ArrayList();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PreferenceGroup, i2, i7);
        int i8 = R$styleable.PreferenceGroup_orderingFromXml;
        this.mOrderingAsAdded = TypedArrayUtils.getBoolean(obtainStyledAttributes, i8, i8, true);
        int i10 = R$styleable.PreferenceGroup_initialExpandedChildrenCount;
        if (obtainStyledAttributes.hasValue(i10)) {
            setInitialExpandedChildrenCount(TypedArrayUtils.getInt(obtainStyledAttributes, i10, i10, Integer.MAX_VALUE));
        }
        obtainStyledAttributes.recycle();
    }

    private boolean removePreferenceInt(Preference preference) {
        boolean remove;
        synchronized (this) {
            try {
                preference.onPrepareForRemoval();
                if (preference.getParent() == this) {
                    preference.assignParent((PreferenceGroup) null);
                }
                remove = this.mPreferences.remove(preference);
                if (remove) {
                    String key = preference.getKey();
                    if (key != null) {
                        this.mIdRecycleCache.put(key, Long.valueOf(preference.getId()));
                        this.mHandler.removeCallbacks(this.mClearRecycleCacheRunnable);
                        this.mHandler.post(this.mClearRecycleCacheRunnable);
                    }
                    if (this.mAttachedToHierarchy) {
                        preference.onDetached();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return remove;
    }

    public void addItemFromInflater(Preference preference) {
        addPreference(preference);
    }

    public boolean addPreference(Preference preference) {
        long j2;
        if (this.mPreferences.contains(preference)) {
            return true;
        }
        if (preference.getKey() != null) {
            PreferenceGroup preferenceGroup = this;
            while (preferenceGroup.getParent() != null) {
                preferenceGroup = preferenceGroup.getParent();
            }
            String key = preference.getKey();
            if (preferenceGroup.findPreference(key) != null) {
                Log.e("PreferenceGroup", "Found duplicated key: \"" + key + "\". This can cause unintended behaviour, please use unique keys for every preference.");
            }
        }
        if (preference.getOrder() == Integer.MAX_VALUE) {
            if (this.mOrderingAsAdded) {
                int i2 = this.mCurrentPreferenceOrder;
                this.mCurrentPreferenceOrder = i2 + 1;
                preference.setOrder(i2);
            }
            if (preference instanceof PreferenceGroup) {
                ((PreferenceGroup) preference).setOrderingAsAdded(this.mOrderingAsAdded);
            }
        }
        int binarySearch = Collections.binarySearch(this.mPreferences, preference);
        if (binarySearch < 0) {
            binarySearch = (binarySearch * -1) - 1;
        }
        if (!onPrepareAddPreference(preference)) {
            return false;
        }
        synchronized (this) {
            this.mPreferences.add(binarySearch, preference);
        }
        PreferenceManager preferenceManager = getPreferenceManager();
        String key2 = preference.getKey();
        if (key2 == null || !this.mIdRecycleCache.containsKey(key2)) {
            j2 = preferenceManager.getNextId();
        } else {
            j2 = this.mIdRecycleCache.get(key2).longValue();
            this.mIdRecycleCache.remove(key2);
        }
        preference.onAttachedToHierarchy(preferenceManager, j2);
        preference.assignParent(this);
        if (this.mAttachedToHierarchy) {
            preference.onAttached();
        }
        notifyHierarchyChanged();
        return true;
    }

    public void dispatchRestoreInstanceState(Bundle bundle) {
        super.dispatchRestoreInstanceState(bundle);
        int preferenceCount = getPreferenceCount();
        for (int i2 = 0; i2 < preferenceCount; i2++) {
            getPreference(i2).dispatchRestoreInstanceState(bundle);
        }
    }

    public void dispatchSaveInstanceState(Bundle bundle) {
        super.dispatchSaveInstanceState(bundle);
        int preferenceCount = getPreferenceCount();
        for (int i2 = 0; i2 < preferenceCount; i2++) {
            getPreference(i2).dispatchSaveInstanceState(bundle);
        }
    }

    public <T extends Preference> T findPreference(CharSequence charSequence) {
        T findPreference;
        if (charSequence == null) {
            throw new IllegalArgumentException("Key cannot be null");
        } else if (TextUtils.equals(getKey(), charSequence)) {
            return this;
        } else {
            int preferenceCount = getPreferenceCount();
            for (int i2 = 0; i2 < preferenceCount; i2++) {
                T preference = getPreference(i2);
                if (TextUtils.equals(preference.getKey(), charSequence)) {
                    return preference;
                }
                if ((preference instanceof PreferenceGroup) && (findPreference = ((PreferenceGroup) preference).findPreference(charSequence)) != null) {
                    return findPreference;
                }
            }
            return null;
        }
    }

    public int getInitialExpandedChildrenCount() {
        return this.mInitialExpandedChildrenCount;
    }

    public OnExpandButtonClickListener getOnExpandButtonClickListener() {
        return null;
    }

    public Preference getPreference(int i2) {
        return this.mPreferences.get(i2);
    }

    public int getPreferenceCount() {
        return this.mPreferences.size();
    }

    public boolean isOnSameScreenAsChildren() {
        return true;
    }

    public void notifyDependencyChange(boolean z) {
        super.notifyDependencyChange(z);
        int preferenceCount = getPreferenceCount();
        for (int i2 = 0; i2 < preferenceCount; i2++) {
            getPreference(i2).onParentChanged(this, z);
        }
    }

    public void onAttached() {
        super.onAttached();
        this.mAttachedToHierarchy = true;
        int preferenceCount = getPreferenceCount();
        for (int i2 = 0; i2 < preferenceCount; i2++) {
            getPreference(i2).onAttached();
        }
    }

    public void onDetached() {
        super.onDetached();
        this.mAttachedToHierarchy = false;
        int preferenceCount = getPreferenceCount();
        for (int i2 = 0; i2 < preferenceCount; i2++) {
            getPreference(i2).onDetached();
        }
    }

    public boolean onPrepareAddPreference(Preference preference) {
        preference.onParentChanged(this, shouldDisableDependents());
        return true;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.mInitialExpandedChildrenCount = savedState.mInitialExpandedChildrenCount;
        super.onRestoreInstanceState(savedState.getSuperState());
    }

    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.mInitialExpandedChildrenCount);
    }

    public void removeAll() {
        synchronized (this) {
            try {
                List<Preference> list = this.mPreferences;
                for (int size = list.size() - 1; size >= 0; size--) {
                    removePreferenceInt(list.get(0));
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        notifyHierarchyChanged();
    }

    public boolean removePreference(Preference preference) {
        boolean removePreferenceInt = removePreferenceInt(preference);
        notifyHierarchyChanged();
        return removePreferenceInt;
    }

    public void setInitialExpandedChildrenCount(int i2) {
        if (i2 != Integer.MAX_VALUE && !hasKey()) {
            Log.e("PreferenceGroup", getClass().getSimpleName().concat(" should have a key defined if it contains an expandable preference"));
        }
        this.mInitialExpandedChildrenCount = i2;
    }

    public void setOrderingAsAdded(boolean z) {
        this.mOrderingAsAdded = z;
    }

    public void sortPreferences() {
        synchronized (this) {
            Collections.sort(this.mPreferences);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        int mInitialExpandedChildrenCount;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.mInitialExpandedChildrenCount = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.mInitialExpandedChildrenCount);
        }

        public SavedState(Parcelable parcelable, int i2) {
            super(parcelable);
            this.mInitialExpandedChildrenCount = i2;
        }
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
