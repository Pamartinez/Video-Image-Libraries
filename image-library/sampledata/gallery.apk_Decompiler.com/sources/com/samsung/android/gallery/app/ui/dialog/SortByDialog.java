package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.FontUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import gc.a;
import q4.z;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SortByDialog extends BaseDialog {
    private LinearLayout mGroupByDate;
    private SwitchCompat mGroupByDateButton;
    private int mPreviousSortBy = 0;
    private RadioGroup mSortRadioGroup;

    private void bindViews(View view) {
        this.mSortRadioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_sortby_type);
        this.mGroupByDate = (LinearLayout) view.findViewById(R.id.group_by_date);
        this.mGroupByDateButton = (SwitchCompat) view.findViewById(R.id.group_by_date_button);
        resizeTextSize(view);
    }

    private void cancelSortBy() {
        postAnalyticsLog(AnalyticsEventId.EVENT_ALBUM_FILE_SORT_BY_CANCEL);
        dismiss();
    }

    private void disableGroupByDate() {
        this.mGroupByDate.setAlpha(0.3f);
        this.mGroupByDateButton.setClickable(false);
    }

    private void enableGroupByDate() {
        this.mGroupByDate.setAlpha(1.0f);
        this.mGroupByDateButton.setClickable(true);
    }

    private MediaItem getCurrentAlbum() {
        return (MediaItem) getBlackboard().read("data://albums/current", null);
    }

    private MediaItem getSortedTarget() {
        return (MediaItem) getBlackboard().read("data://sorted/target", getCurrentAlbum());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$0(DialogInterface dialogInterface, int i2) {
        cancelSortBy();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$1(DialogInterface dialogInterface, int i2) {
        setSortBy();
    }

    /* access modifiers changed from: private */
    public void onButtonChanged(RadioGroup radioGroup, int i2) {
        if (i2 == R.id.radio_name_ascending || i2 == R.id.radio_name_descending) {
            disableGroupByDate();
        } else {
            enableGroupByDate();
        }
    }

    private void resizeTextSize(View view) {
        int childCount = this.mSortRadioGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            FontUtils.resizeUpToExtraLarge(getContext(), (TextView) this.mSortRadioGroup.getChildAt(i2));
        }
        FontUtils.resizeUpToExtraLarge(getContext(), (TextView) view.findViewById(R.id.group_by_date_button));
    }

    private void setSortBy() {
        int i2;
        int checkedRadioButtonId = this.mSortRadioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId != R.id.radio_created_time_descending) {
            switch (checkedRadioButtonId) {
                case R.id.radio_modified_time_ascending:
                    i2 = 21;
                    break;
                case R.id.radio_modified_time_descending:
                    i2 = 22;
                    break;
                case R.id.radio_name_ascending:
                    i2 = 31;
                    break;
                case R.id.radio_name_descending:
                    i2 = 32;
                    break;
                default:
                    i2 = 11;
                    break;
            }
        } else {
            i2 = 12;
        }
        if (this.mGroupByDateButton.isChecked()) {
            i2 += 100;
        }
        getBlackboard().post("data://user/dialog/SortBy", new Object[]{Integer.valueOf(this.mPreviousSortBy), Integer.valueOf(i2)});
        postAnalyticsLog(AnalyticsEventId.EVENT_ALBUM_FILE_SORT_BY_DONE, SortByType.getSortByAnalyticsDetail(i2));
        dismiss();
    }

    public Dialog createDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.sortby_dialog, (ViewGroup) null);
        bindViews(inflate);
        initDialog();
        return new AlertDialog.Builder(getActivity()).setTitle((int) R.string.sort_by_title).setView(inflate).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new z(this, 0)).setPositiveButton((int) R.string.done, (DialogInterface.OnClickListener) new z(this, 1)).create();
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SPLIT_VIEW_NORMAL.toString();
    }

    public void initDialog() {
        boolean z;
        Integer num;
        MediaItem sortedTarget = getSortedTarget();
        this.mSortRadioGroup.setOnCheckedChangeListener(new a(2, this));
        if (sortedTarget != null) {
            this.mPreviousSortBy = GalleryPreference.getInstance().loadSortBy(String.valueOf(sortedTarget.getAlbumID()), 12);
        } else {
            this.mPreviousSortBy = 11;
        }
        int sortAndOrderBy = SortByType.getSortAndOrderBy(this.mPreviousSortBy);
        if (sortAndOrderBy == 11) {
            this.mSortRadioGroup.check(R.id.radio_created_time_ascending);
        } else if (sortAndOrderBy == 12) {
            this.mSortRadioGroup.check(R.id.radio_created_time_descending);
        } else if (sortAndOrderBy == 21) {
            this.mSortRadioGroup.check(R.id.radio_modified_time_ascending);
        } else if (sortAndOrderBy == 22) {
            this.mSortRadioGroup.check(R.id.radio_modified_time_descending);
        } else if (sortAndOrderBy == 31) {
            this.mSortRadioGroup.check(R.id.radio_name_ascending);
            disableGroupByDate();
        } else if (sortAndOrderBy != 32) {
            this.mSortRadioGroup.check(R.id.radio_created_time);
        } else {
            this.mSortRadioGroup.check(R.id.radio_name_descending);
            disableGroupByDate();
        }
        SwitchCompat switchCompat = this.mGroupByDateButton;
        if (SortByType.getGroupByDate(this.mPreviousSortBy) == 100) {
            z = true;
        } else {
            z = false;
        }
        switchCompat.setChecked(z);
        String str = this.TAG;
        if (sortedTarget != null) {
            num = Integer.valueOf(sortedTarget.getAlbumID());
        } else {
            num = null;
        }
        Log.d(str, "initDialog", num, Integer.valueOf(this.mPreviousSortBy));
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/SortBy", (Object) null);
    }

    public boolean supportPopover() {
        return true;
    }
}
