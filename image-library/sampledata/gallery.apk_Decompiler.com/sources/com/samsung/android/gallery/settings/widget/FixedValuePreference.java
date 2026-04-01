package com.samsung.android.gallery.settings.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class FixedValuePreference extends BasePreference {
    protected String mValue;

    public FixedValuePreference(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setOnPreferenceClickListener(new a(this));
        this.mValue = GalleryPreference.getInstance().loadString(getKey(), getDefaultValue());
    }

    private String getDpValue(String str) {
        for (String str2 : getValueList()) {
            if (str2.equals(str)) {
                return str2;
            }
        }
        return getValueList()[1];
    }

    private int getIndex(String str) {
        int length = getValueList().length;
        for (int i2 = 0; i2 < length; i2++) {
            if (getValueList()[i2].equals(str)) {
                return i2;
            }
        }
        return 1;
    }

    /* access modifiers changed from: private */
    public void onCancelClicked(DialogInterface dialogInterface, int i2) {
        this.mValue = GalleryPreference.getInstance().loadString(getKey(), getDefaultValue());
        dialogInterface.dismiss();
    }

    /* access modifiers changed from: private */
    public void onItemClick(DialogInterface dialogInterface, int i2) {
        this.mValue = getValueList()[i2];
    }

    /* access modifiers changed from: private */
    public boolean onPreferenceClick(Preference preference) {
        AlertDialog create = new AlertDialog.Builder(getContext()).setTitle((CharSequence) "Select").setCancelable(false).setSingleChoiceItems((CharSequence[]) getValueList(), getIndex(this.mValue), (DialogInterface.OnClickListener) new b(this, 0)).setPositiveButton(17039379, (DialogInterface.OnClickListener) new b(this, 1)).setNegativeButton(17039369, (DialogInterface.OnClickListener) new b(this, 2)).create();
        create.show();
        if (create.getWindow() == null) {
            return true;
        }
        create.getWindow().setLayout(-1, 1200);
        return true;
    }

    public abstract String getDefaultValue();

    public int getLayoutId() {
        return R$layout.labs_thumbnail_gap_layout;
    }

    public abstract String[] getValueList();

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        TextView textView = (TextView) preferenceViewHolder.itemView.findViewById(R$id.dp_value);
        if (textView != null) {
            String dpValue = getDpValue(this.mValue);
            if (TextUtils.isEmpty(dpValue)) {
                textView.setVisibility(8);
                return;
            }
            textView.setVisibility(0);
            textView.setText(dpValue);
        }
    }

    public void onDoneClicked(DialogInterface dialogInterface, int i2) {
        GalleryPreference.getInstance().saveState(getKey(), this.mValue);
        Utils.restartGalleryActivity(getContext());
    }

    public boolean supportCustomLayout() {
        return true;
    }

    public FixedValuePreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
