package androidx.preference;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.reflect.os.SeslUserHandleReflector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslRingtonePreference extends Preference {
    private int mRingtoneType;
    private boolean mShowDefault;
    private boolean mShowSilent;
    private int mUserId;

    public SeslRingtonePreference(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RingtonePreference, i2, i7);
        this.mRingtoneType = obtainStyledAttributes.getInt(R$styleable.RingtonePreference_android_ringtoneType, 1);
        this.mShowDefault = obtainStyledAttributes.getBoolean(R$styleable.RingtonePreference_android_showDefault, true);
        this.mShowSilent = obtainStyledAttributes.getBoolean(R$styleable.RingtonePreference_android_showSilent, true);
        setIntent(new Intent("android.intent.action.RINGTONE_PICKER"));
        setUserId(SeslUserHandleReflector.myUserId());
        obtainStyledAttributes.recycle();
    }

    public void onAttachedToHierarchy(PreferenceManager preferenceManager) {
        super.onAttachedToHierarchy(preferenceManager);
    }

    public Object onGetDefaultValue(TypedArray typedArray, int i2) {
        return typedArray.getString(i2);
    }

    public void onSaveRingtone(Uri uri) {
        String str;
        if (uri != null) {
            str = uri.toString();
        } else {
            str = "";
        }
        persistString(str);
    }

    public void onSetInitialValue(boolean z, Object obj) {
        String str = (String) obj;
        if (!z && !TextUtils.isEmpty(str)) {
            onSaveRingtone(Uri.parse(str));
        }
    }

    public void setUserId(int i2) {
        this.mUserId = i2;
    }

    public SeslRingtonePreference(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public SeslRingtonePreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.ringtonePreferenceStyle);
    }
}
