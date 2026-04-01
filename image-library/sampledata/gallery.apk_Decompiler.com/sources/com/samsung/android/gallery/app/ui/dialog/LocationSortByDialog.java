package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import q4.m;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocationSortByDialog extends BaseDialog {
    private int mPreviousSortByType = 0;
    RadioGroup mRadioGroup;

    private void cancelSortBy() {
        postAnalyticsLog(AnalyticsEventId.EVENT_SHARED_SORT_BY_CANCEL);
        dismiss();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$0(DialogInterface dialogInterface, int i2) {
        cancelSortBy();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$1(DialogInterface dialogInterface, int i2) {
        updateSortBy();
    }

    private void updateSortBy() {
        int i2;
        Integer num;
        if (this.mRadioGroup.getCheckedRadioButtonId() == R.id.radio_button_name) {
            i2 = 30;
        } else {
            i2 = 50;
        }
        Blackboard blackboard = getBlackboard();
        if (this.mPreviousSortByType != i2) {
            num = Integer.valueOf(i2);
        } else {
            num = null;
        }
        blackboard.post("data://user/dialog/LocationSortBy", num);
        dismiss();
    }

    public Dialog createDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.location_sort_by_dialog, (ViewGroup) null);
        initDialog(inflate);
        return new AlertDialog.Builder(inflate.getContext()).setTitle((int) R.string.sort_by_title).setView(inflate).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new m(this, 0)).setPositiveButton((int) R.string.done, (DialogInterface.OnClickListener) new m(this, 1)).create();
    }

    public String getScreenId() {
        return null;
    }

    public void initDialog(View view) {
        int i2;
        this.mRadioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        int loadInt = GalleryPreference.getInstance().loadInt(PreferenceName.LOCATION_SORT_BY, 50);
        this.mPreviousSortByType = loadInt;
        RadioGroup radioGroup = this.mRadioGroup;
        if (loadInt == 30) {
            i2 = R.id.radio_button_name;
        } else {
            i2 = R.id.radio_button_count;
        }
        radioGroup.check(i2);
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/LocationSortBy", (Object) null);
    }

    public void onPause() {
        super.onPause();
    }
}
