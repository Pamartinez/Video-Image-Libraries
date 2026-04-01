package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import q4.A;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SortBySharingDialog extends BaseDialog {
    private int mPreviousSortBy = 0;
    private RadioGroup mSortRadioGroup;

    private void bindViews(View view) {
        this.mSortRadioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_sortby_type);
    }

    /* access modifiers changed from: private */
    public void onClickNegative(View view) {
        dismiss();
    }

    /* access modifiers changed from: private */
    public void onClickPositive(View view) {
        int i2;
        switch (this.mSortRadioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_count_ascending:
                i2 = 51;
                break;
            case R.id.radio_count_descending:
                i2 = 52;
                break;
            case R.id.radio_date_modified_descending:
                i2 = 22;
                break;
            case R.id.radio_name_ascending:
                i2 = 31;
                break;
            case R.id.radio_name_descending:
                i2 = 32;
                break;
            default:
                i2 = 21;
                break;
        }
        getBlackboard().post("data://user/dialog/SortBySharing", new Object[]{Integer.valueOf(this.mPreviousSortBy), Integer.valueOf(i2)});
        postAnalyticsLog(AnalyticsEventId.EVENT_SHARED_VIEW_SORTING_DIALOG_DONE_BUTTON);
        dismiss();
    }

    public Dialog createDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.sortby_sharing_dialog, (ViewGroup) null);
        bindViews(inflate);
        initDialog();
        return new AlertDialog.Builder(getActivity()).setTitle((int) R.string.sort_by_title).setView(inflate).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton((int) R.string.done, (DialogInterface.OnClickListener) null).create();
    }

    public String getScreenId() {
        return null;
    }

    public void initDialog() {
        int sharingOrder = SortByType.getSharingOrder();
        this.mPreviousSortBy = sharingOrder;
        if (sharingOrder == 21) {
            this.mSortRadioGroup.check(R.id.radio_date_modified_ascending);
        } else if (sharingOrder == 22) {
            this.mSortRadioGroup.check(R.id.radio_date_modified_descending);
        } else if (sharingOrder == 31) {
            this.mSortRadioGroup.check(R.id.radio_name_ascending);
        } else if (sharingOrder == 32) {
            this.mSortRadioGroup.check(R.id.radio_name_descending);
        } else if (sharingOrder == 51) {
            this.mSortRadioGroup.check(R.id.radio_count_ascending);
        } else if (sharingOrder == 52) {
            this.mSortRadioGroup.check(R.id.radio_count_descending);
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/SortBySharing", (Object) null);
    }

    public void onResume() {
        super.onResume();
        ((AlertDialog) getDialog()).getButton(-1).setOnClickListener(new A(this, 0));
        ((AlertDialog) getDialog()).getButton(-2).setOnClickListener(new A(this, 1));
    }

    public boolean supportPopover() {
        return true;
    }
}
