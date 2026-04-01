package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ListPreference extends DialogPreference {
    private CharSequence[] mEntries;
    private CharSequence[] mEntryValues;
    private String mSummary;
    private String mValue;
    private boolean mValueSet;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SimpleSummaryProvider implements Preference.SummaryProvider<ListPreference> {
        private static SimpleSummaryProvider sSimpleSummaryProvider;

        private SimpleSummaryProvider() {
        }

        public static SimpleSummaryProvider getInstance() {
            if (sSimpleSummaryProvider == null) {
                sSimpleSummaryProvider = new SimpleSummaryProvider();
            }
            return sSimpleSummaryProvider;
        }

        public CharSequence provideSummary(ListPreference listPreference) {
            if (TextUtils.isEmpty(listPreference.getEntry())) {
                return listPreference.getContext().getString(R$string.not_set);
            }
            return listPreference.getEntry();
        }
    }

    public ListPreference(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ListPreference, i2, i7);
        this.mEntries = TypedArrayUtils.getTextArray(obtainStyledAttributes, R$styleable.ListPreference_entries, R$styleable.ListPreference_android_entries);
        this.mEntryValues = TypedArrayUtils.getTextArray(obtainStyledAttributes, R$styleable.ListPreference_entryValues, R$styleable.ListPreference_android_entryValues);
        int i8 = R$styleable.ListPreference_useSimpleSummaryProvider;
        if (TypedArrayUtils.getBoolean(obtainStyledAttributes, i8, i8, false)) {
            setSummaryProvider(SimpleSummaryProvider.getInstance());
        }
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R$styleable.Preference, i2, i7);
        this.mSummary = TypedArrayUtils.getString(obtainStyledAttributes2, R$styleable.Preference_summary, R$styleable.Preference_android_summary);
        obtainStyledAttributes2.recycle();
    }

    private int getValueIndex() {
        return findIndexOfValue(this.mValue);
    }

    public int findIndexOfValue(String str) {
        CharSequence[] charSequenceArr;
        if (str == null || (charSequenceArr = this.mEntryValues) == null) {
            return -1;
        }
        for (int length = charSequenceArr.length - 1; length >= 0; length--) {
            if (TextUtils.equals(this.mEntryValues[length].toString(), str)) {
                return length;
            }
        }
        return -1;
    }

    public CharSequence[] getEntries() {
        return this.mEntries;
    }

    public CharSequence getEntry() {
        CharSequence[] charSequenceArr;
        int valueIndex = getValueIndex();
        if (valueIndex < 0 || (charSequenceArr = this.mEntries) == null) {
            return null;
        }
        return charSequenceArr[valueIndex];
    }

    public CharSequence[] getEntryValues() {
        return this.mEntryValues;
    }

    public CharSequence getSummary() {
        if (getSummaryProvider() != null) {
            return getSummaryProvider().provideSummary(this);
        }
        Object entry = getEntry();
        CharSequence summary = super.getSummary();
        String str = this.mSummary;
        if (str != null) {
            if (entry == null) {
                entry = "";
            }
            String format = String.format(str, new Object[]{entry});
            if (!TextUtils.equals(format, summary)) {
                Log.w("ListPreference", "Setting a summary with a String formatting marker is no longer supported. You should use a SummaryProvider instead.");
                return format;
            }
        }
        return summary;
    }

    public String getValue() {
        return this.mValue;
    }

    public Object onGetDefaultValue(TypedArray typedArray, int i2) {
        return typedArray.getString(i2);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setValue(savedState.mValue);
    }

    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (isPersistent()) {
            return onSaveInstanceState;
        }
        SavedState savedState = new SavedState(onSaveInstanceState);
        savedState.mValue = getValue();
        return savedState;
    }

    public void onSetInitialValue(Object obj) {
        setValue(getPersistedString((String) obj));
    }

    public void setEntries(CharSequence[] charSequenceArr) {
        this.mEntries = charSequenceArr;
    }

    public void setSummary(CharSequence charSequence) {
        super.setSummary(charSequence);
        if (charSequence == null) {
            this.mSummary = null;
        } else {
            this.mSummary = charSequence.toString();
        }
    }

    public void setValue(String str) {
        boolean equals = TextUtils.equals(this.mValue, str);
        if (!equals || !this.mValueSet) {
            this.mValue = str;
            this.mValueSet = true;
            persistString(str);
            if (!equals) {
                notifyChanged();
            }
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
        String mValue;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.mValue = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeString(this.mValue);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public void setEntries(int i2) {
        setEntries(getContext().getResources().getTextArray(i2));
    }

    public ListPreference(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public ListPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, R$attr.dialogPreferenceStyle, 16842897));
    }
}
