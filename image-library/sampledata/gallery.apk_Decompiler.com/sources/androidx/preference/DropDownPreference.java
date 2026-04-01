package androidx.preference;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import androidx.appcompat.R$layout;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.SeslArrayAdapter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DropDownPreference extends ListPreference {
    private final ArrayAdapter mAdapter;
    private final Context mContext;
    private final AdapterView.OnItemSelectedListener mItemSelectedListener;
    private AppCompatSpinner mSpinner;

    public DropDownPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.dropdownPreferenceStyle);
    }

    private int findSpinnerIndexOfValue(String str) {
        CharSequence[] entryValues = getEntryValues();
        if (str == null || entryValues == null) {
            return -1;
        }
        for (int length = entryValues.length - 1; length >= 0; length--) {
            if (TextUtils.equals(entryValues[length].toString(), str)) {
                return length;
            }
        }
        return -1;
    }

    private void updateEntries() {
        this.mAdapter.clear();
        if (getEntries() != null) {
            for (CharSequence charSequence : getEntries()) {
                this.mAdapter.add(charSequence.toString());
            }
        }
    }

    public ArrayAdapter createAdapter() {
        return new SeslArrayAdapter(this.mContext, R$layout.support_simple_spinner_dropdown_item);
    }

    public void notifyChanged() {
        super.notifyChanged();
        ArrayAdapter arrayAdapter = this.mAdapter;
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        AppCompatSpinner appCompatSpinner = (AppCompatSpinner) preferenceViewHolder.itemView.findViewById(R$id.spinner);
        this.mSpinner = appCompatSpinner;
        appCompatSpinner.setSoundEffectsEnabled(false);
        if (!this.mAdapter.equals(this.mSpinner.getAdapter())) {
            this.mSpinner.setAdapter((SpinnerAdapter) this.mAdapter);
        }
        this.mSpinner.setOnItemSelectedListener(this.mItemSelectedListener);
        this.mSpinner.setSelection(findSpinnerIndexOfValue(getValue()));
        super.onBindViewHolder(preferenceViewHolder);
    }

    public void onClick() {
        this.mSpinner.performClick();
    }

    public AppCompatSpinner seslGetSpinner() {
        return this.mSpinner;
    }

    public void setEntries(CharSequence[] charSequenceArr) {
        super.setEntries(charSequenceArr);
        updateEntries();
    }

    public void setValueIndex(int i2) {
        setValue(getEntryValues()[i2].toString());
    }

    public DropDownPreference(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public DropDownPreference(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j2) {
                if (i2 >= 0) {
                    String charSequence = DropDownPreference.this.getEntryValues()[i2].toString();
                    if (!charSequence.equals(DropDownPreference.this.getValue()) && DropDownPreference.this.callChangeListener(charSequence)) {
                        DropDownPreference.this.setValue(charSequence);
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        this.mContext = context;
        this.mAdapter = createAdapter();
        updateEntries();
    }
}
