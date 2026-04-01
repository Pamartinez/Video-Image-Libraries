package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import androidx.appcompat.widget.SeslSeekBar;
import androidx.preference.Preference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeekBarPreference extends Preference {
    boolean mAdjustable;
    private int mMax;
    int mMin;
    SeslSeekBar mSeekBar;
    private final SeslSeekBar.OnSeekBarChangeListener mSeekBarChangeListener;
    private int mSeekBarIncrement;
    private final View.OnKeyListener mSeekBarKeyListener;
    int mSeekBarValue;
    private boolean mShowSeekBarValue;
    boolean mTrackingTouch;
    boolean mUpdatesContinuously;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnSeekBarPreferenceChangeListener {
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        this.mSeekBarChangeListener = new SeslSeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeslSeekBar seslSeekBar, int i2, boolean z) {
                if (z) {
                    SeekBarPreference seekBarPreference = SeekBarPreference.this;
                    if (seekBarPreference.mUpdatesContinuously || !seekBarPreference.mTrackingTouch) {
                        seekBarPreference.syncValueInternal(seslSeekBar);
                    }
                }
                OnSeekBarPreferenceChangeListener unused = SeekBarPreference.this.getClass();
            }

            public void onStartTrackingTouch(SeslSeekBar seslSeekBar) {
                SeekBarPreference seekBarPreference = SeekBarPreference.this;
                seekBarPreference.mTrackingTouch = true;
                OnSeekBarPreferenceChangeListener unused = seekBarPreference.getClass();
            }

            public void onStopTrackingTouch(SeslSeekBar seslSeekBar) {
                SeekBarPreference.this.mTrackingTouch = false;
                int progress = seslSeekBar.getProgress();
                SeekBarPreference seekBarPreference = SeekBarPreference.this;
                if (progress + seekBarPreference.mMin != seekBarPreference.mSeekBarValue) {
                    seekBarPreference.syncValueInternal(seslSeekBar);
                }
                OnSeekBarPreferenceChangeListener unused = SeekBarPreference.this.getClass();
            }
        };
        this.mSeekBarKeyListener = new View.OnKeyListener() {
            public boolean onKey(View view, int i2, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 0) {
                    return false;
                }
                SeekBarPreference seekBarPreference = SeekBarPreference.this;
                if ((!seekBarPreference.mAdjustable && (i2 == 21 || i2 == 22)) || i2 == 23 || i2 == 66) {
                    return false;
                }
                SeslSeekBar seslSeekBar = seekBarPreference.mSeekBar;
                if (seslSeekBar != null) {
                    return seslSeekBar.onKeyDown(i2, keyEvent);
                }
                Log.e("SeekBarPreference", "SeekBar view is null and hence cannot be adjusted.");
                return false;
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SeekBarPreference, i2, i7);
        this.mMin = obtainStyledAttributes.getInt(R$styleable.SeekBarPreference_min, 0);
        setMax(obtainStyledAttributes.getInt(R$styleable.SeekBarPreference_android_max, 100));
        setSeekBarIncrement(obtainStyledAttributes.getInt(R$styleable.SeekBarPreference_seekBarIncrement, 0));
        this.mAdjustable = obtainStyledAttributes.getBoolean(R$styleable.SeekBarPreference_adjustable, true);
        this.mShowSeekBarValue = obtainStyledAttributes.getBoolean(R$styleable.SeekBarPreference_showSeekBarValue, false);
        this.mUpdatesContinuously = obtainStyledAttributes.getBoolean(R$styleable.SeekBarPreference_updatesContinuously, false);
        obtainStyledAttributes.recycle();
    }

    private void setValueInternal(int i2, boolean z) {
        int i7 = this.mMin;
        if (i2 < i7) {
            i2 = i7;
        }
        int i8 = this.mMax;
        if (i2 > i8) {
            i2 = i8;
        }
        if (i2 != this.mSeekBarValue) {
            this.mSeekBarValue = i2;
            persistInt(i2);
            if (z) {
                notifyChanged();
            }
        }
    }

    /* access modifiers changed from: private */
    public void syncValueInternal(SeslSeekBar seslSeekBar) {
        int progress = seslSeekBar.getProgress() + this.mMin;
        if (progress == this.mSeekBarValue) {
            return;
        }
        if (callChangeListener(Integer.valueOf(progress))) {
            setValueInternal(progress, false);
        } else {
            seslSeekBar.setProgress(this.mSeekBarValue - this.mMin);
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.itemView.setOnKeyListener(this.mSeekBarKeyListener);
        SeslSeekBar seslSeekBar = (SeslSeekBar) preferenceViewHolder.findViewById(R$id.seekbar);
        this.mSeekBar = seslSeekBar;
        if (seslSeekBar == null) {
            Log.e("SeekBarPreference", "SeekBar view is null in onBindViewHolder.");
            return;
        }
        seslSeekBar.setOnSeekBarChangeListener(this.mSeekBarChangeListener);
        this.mSeekBar.setMax(this.mMax - this.mMin);
        int i2 = this.mSeekBarIncrement;
        if (i2 != 0) {
            this.mSeekBar.setKeyProgressIncrement(i2);
        } else {
            this.mSeekBarIncrement = this.mSeekBar.getKeyProgressIncrement();
        }
        this.mSeekBar.setProgress(this.mSeekBarValue - this.mMin);
        this.mSeekBar.setEnabled(isEnabled());
    }

    public Object onGetDefaultValue(TypedArray typedArray, int i2) {
        return Integer.valueOf(typedArray.getInt(i2, 0));
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mSeekBarValue = savedState.mSeekBarValue;
        this.mMin = savedState.mMin;
        this.mMax = savedState.mMax;
        notifyChanged();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (isPersistent()) {
            return onSaveInstanceState;
        }
        SavedState savedState = new SavedState(onSaveInstanceState);
        savedState.mSeekBarValue = this.mSeekBarValue;
        savedState.mMin = this.mMin;
        savedState.mMax = this.mMax;
        return savedState;
    }

    public void onSetInitialValue(Object obj) {
        if (obj == null) {
            obj = 0;
        }
        setValue(getPersistedInt(((Integer) obj).intValue()));
    }

    public final void setMax(int i2) {
        int i7 = this.mMin;
        if (i2 < i7) {
            i2 = i7;
        }
        if (i2 != this.mMax) {
            this.mMax = i2;
            notifyChanged();
        }
    }

    public final void setSeekBarIncrement(int i2) {
        if (i2 != this.mSeekBarIncrement) {
            this.mSeekBarIncrement = Math.min(this.mMax - this.mMin, Math.abs(i2));
            notifyChanged();
        }
    }

    public void setValue(int i2) {
        setValueInternal(i2, true);
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
        int mMax;
        int mMin;
        int mSeekBarValue;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.mSeekBarValue = parcel.readInt();
            this.mMin = parcel.readInt();
            this.mMax = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.mSeekBarValue);
            parcel.writeInt(this.mMin);
            parcel.writeInt(this.mMax);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.seekBarPreferenceStyle);
    }
}
