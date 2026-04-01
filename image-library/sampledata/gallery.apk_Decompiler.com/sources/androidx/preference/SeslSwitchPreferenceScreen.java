package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import androidx.reflect.view.SeslViewReflector;
import androidx.reflect.widget.SeslHoverPopupWindowReflector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslSwitchPreferenceScreen extends SwitchPreferenceCompat {
    private View.OnKeyListener mSwitchKeyListener;

    public SeslSwitchPreferenceScreen(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        this.mSwitchKeyListener = new View.OnKeyListener() {
            public boolean onKey(View view, int i2, KeyEvent keyEvent) {
                int keyCode = keyEvent.getKeyCode();
                if (keyEvent.getAction() != 0) {
                    return false;
                }
                if (keyCode != 21) {
                    if (keyCode == 22 && !SeslSwitchPreferenceScreen.this.isChecked()) {
                        if (SeslSwitchPreferenceScreen.this.callChangeListener(Boolean.TRUE)) {
                            SeslSwitchPreferenceScreen.this.setChecked(true);
                        }
                        return true;
                    }
                } else if (SeslSwitchPreferenceScreen.this.isChecked()) {
                    if (SeslSwitchPreferenceScreen.this.callChangeListener(Boolean.FALSE)) {
                        SeslSwitchPreferenceScreen.this.setChecked(false);
                    }
                    return true;
                }
                return false;
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Preference, i2, i7);
        String string = obtainStyledAttributes.getString(R$styleable.Preference_android_fragment);
        if (string == null || string.equals("")) {
            Log.w("SwitchPreferenceScreen", "SwitchPreferenceScreen should getfragment property. Fragment property does not exsit in SwitchPreferenceScreen");
        }
        setLayoutResource(R$layout.sesl_preference_switch_screen);
        setWidgetLayoutResource(R$layout.sesl_switch_preference_screen_widget_divider);
        obtainStyledAttributes.recycle();
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.itemView.setOnKeyListener(this.mSwitchKeyListener);
        TextView textView = (TextView) preferenceViewHolder.findViewById(16908310);
        View findViewById = preferenceViewHolder.findViewById(16908352);
        View findViewById2 = preferenceViewHolder.findViewById(R$id.switch_widget);
        if (textView != null && findViewById != null && findViewById2 != null) {
            SeslViewReflector.semSetHoverPopupType(findViewById, SeslHoverPopupWindowReflector.getField_TYPE_NONE());
            findViewById.setContentDescription(textView.getText().toString());
            findViewById2.setContentDescription(textView.getText().toString());
        }
    }

    public SeslSwitchPreferenceScreen(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public SeslSwitchPreferenceScreen(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.switchPreferenceStyle);
    }

    public SeslSwitchPreferenceScreen(Context context) {
        this(context, (AttributeSet) null);
    }

    public void callClickListener() {
    }

    public void onClick() {
    }
}
