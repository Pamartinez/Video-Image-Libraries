package com.samsung.android.gallery.app.ui.dialog;

import Fa.C0558l;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.ui.list.stories.highlight.utils.CustomRatioHelper;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import gc.a;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HighlightExportOptionsDialog extends BaseDialog {
    private RadioGroup mOrientation;
    private final Integer[] mOrientationIds = {Integer.valueOf(R.id.radio_portrait), Integer.valueOf(R.id.radio_landscape)};
    private RadioGroup mRatio;
    private RadioButton mRatio16To9;
    private RadioButton mRatio1To1;
    private RadioButton mRatio4To3;
    private final Integer[] mRatioIds = {Integer.valueOf(R.id.radio_full), Integer.valueOf(R.id.radio_1_to_1), Integer.valueOf(R.id.radio_4_to_3), Integer.valueOf(R.id.radio_16_to_9)};
    private final Integer[][] mRatioStringRes = {new Integer[]{Integer.valueOf(R.string.export_ratio_3_4), Integer.valueOf(R.string.export_ratio_9_16)}, new Integer[]{Integer.valueOf(R.string.export_ratio_4_3), Integer.valueOf(R.string.export_ratio_16_9)}};
    private final Integer[][] mRatioValue = {new Integer[]{3, 4, 9, 16}, new Integer[]{4, 3, 16, 9}};

    private void bindViews(View view) {
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_group_orientation);
        this.mOrientation = radioGroup;
        radioGroup.setOnCheckedChangeListener(new a(1, this));
        RadioGroup radioGroup2 = (RadioGroup) view.findViewById(R.id.radio_group_ratio);
        this.mRatio = radioGroup2;
        this.mRatio1To1 = (RadioButton) radioGroup2.findViewById(R.id.radio_1_to_1);
        this.mRatio4To3 = (RadioButton) this.mRatio.findViewById(R.id.radio_4_to_3);
        this.mRatio16To9 = (RadioButton) this.mRatio.findViewById(R.id.radio_16_to_9);
    }

    private int getCurrentRatio(Bundle bundle) {
        int i2;
        if (bundle != null) {
            i2 = BundleWrapper.getInt(bundle, "index");
        } else {
            i2 = -1;
        }
        if (i2 < 0) {
            return CustomRatioHelper.getDefaultRatio(getResources());
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$0(DialogInterface dialogInterface, int i2) {
        onDoneClicked();
    }

    private void onDoneClicked() {
        getBlackboard().post("data://user/dialog/HighlightExportOptions", Integer.valueOf(CustomRatioHelper.getRatio(List.of(this.mOrientationIds).indexOf(Integer.valueOf(this.mOrientation.getCheckedRadioButtonId())), List.of(this.mRatioIds).indexOf(Integer.valueOf(this.mRatio.getCheckedRadioButtonId())))));
    }

    /* access modifiers changed from: private */
    public void onOrientationSelected(RadioGroup radioGroup, int i2) {
        updateRatioText(List.of(this.mOrientationIds).indexOf(Integer.valueOf(i2)));
    }

    private void setRatioDescription(View view, int i2, int i7) {
        ViewUtils.setContentDescription(view, AppResources.getString(R.string.ratio_description, Integer.valueOf(i2), Integer.valueOf(i7)));
    }

    private void updateRatioText(int i2) {
        this.mRatio4To3.setText(this.mRatioStringRes[i2][0].intValue());
        this.mRatio16To9.setText(this.mRatioStringRes[i2][1].intValue());
        setRatioDescription(this.mRatio1To1, 1, 1);
        setRatioDescription(this.mRatio4To3, this.mRatioValue[i2][0].intValue(), this.mRatioValue[i2][1].intValue());
        setRatioDescription(this.mRatio16To9, this.mRatioValue[i2][2].intValue(), this.mRatioValue[i2][3].intValue());
    }

    public Dialog createDialog(Bundle bundle) {
        Context context;
        if (getContext() != null) {
            context = getContext();
        } else {
            context = AppResources.getAppContext();
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.highlight_export_options_dialog, (ViewGroup) null);
        bindViews(inflate);
        updateRadioButton();
        return new AlertDialog.Builder(context).setTitle((int) R.string.export_options).setView(inflate).setPositiveButton((int) R.string.done, (DialogInterface.OnClickListener) new C0558l(13, this)).create();
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/HighlightExportOptions", (Object) null);
    }

    public void updateRadioButton() {
        int currentRatio = getCurrentRatio(getArguments());
        int orientationIndex = CustomRatioHelper.getOrientationIndex(currentRatio);
        this.mOrientation.check(this.mOrientationIds[orientationIndex].intValue());
        this.mRatio.check(this.mRatioIds[CustomRatioHelper.getRatioIndex(currentRatio)].intValue());
        updateRatioText(orientationIndex);
    }
}
