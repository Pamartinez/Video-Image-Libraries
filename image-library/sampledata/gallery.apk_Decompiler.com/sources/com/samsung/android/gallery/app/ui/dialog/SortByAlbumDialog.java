package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatRadioButton;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import q4.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SortByAlbumDialog extends BaseDialog {
    private int mPreviousSortBy = 0;
    private AppCompatRadioButton mRadioButtonCountAsc;
    private AppCompatRadioButton mRadioButtonCountDes;
    private AppCompatRadioButton mRadioButtonDateModifiedAsc;
    private AppCompatRadioButton mRadioButtonDateModifiedDes;
    private RadioGroup mSortRadioGroup;

    private void bindViews(View view) {
        this.mSortRadioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_sortby_type);
        this.mRadioButtonDateModifiedAsc = (AppCompatRadioButton) view.findViewById(R.id.radio_name_ascending);
        this.mRadioButtonDateModifiedDes = (AppCompatRadioButton) view.findViewById(R.id.radio_name_descending);
        this.mRadioButtonCountAsc = (AppCompatRadioButton) view.findViewById(R.id.radio_count_ascending);
        this.mRadioButtonCountDes = (AppCompatRadioButton) view.findViewById(R.id.radio_count_descending);
    }

    private void initDialog() {
        int albumsOrder = SortByType.getAlbumsOrder();
        this.mPreviousSortBy = albumsOrder;
        if (albumsOrder == 31) {
            this.mSortRadioGroup.check(R.id.radio_name_ascending);
        } else if (albumsOrder == 32) {
            this.mSortRadioGroup.check(R.id.radio_name_descending);
        } else if (albumsOrder == 40 || albumsOrder == 41) {
            this.mSortRadioGroup.check(R.id.radio_custom_order);
        } else if (albumsOrder == 51) {
            this.mSortRadioGroup.check(R.id.radio_count_ascending);
        } else if (albumsOrder == 52) {
            this.mSortRadioGroup.check(R.id.radio_count_descending);
        }
    }

    /* access modifiers changed from: private */
    public void onClickNegative(View view) {
        postAnalyticsLog(AnalyticsEventId.EVENT_ALBUM_SORT_BY_CANCEL);
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
            case R.id.radio_date_modified_ascending:
            case R.id.radio_date_modified_descending:
                i2 = 0;
                break;
            case R.id.radio_name_ascending:
                i2 = 31;
                break;
            case R.id.radio_name_descending:
                i2 = 32;
                break;
            default:
                i2 = 40;
                break;
        }
        getBlackboard().post("data://user/dialog/SortByAlbum", new Object[]{Integer.valueOf(this.mPreviousSortBy), Integer.valueOf(i2)});
        postAnalyticsLog(AnalyticsEventId.EVENT_ALBUM_SORT_BY_DONE, SortByType.getSortByAnalyticsDetail(i2));
        dismiss();
    }

    public Dialog createDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.sortby_album_dialog, (ViewGroup) null);
        bindViews(inflate);
        if (PreferenceFeatures.OneUi40.ALBUM_SORT_BY_COUNT) {
            this.mRadioButtonCountAsc.setVisibility(0);
            this.mRadioButtonCountDes.setVisibility(0);
        }
        initDialog();
        return new AlertDialog.Builder(getActivity()).setTitle((int) R.string.sort_by_title).setView(inflate).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton((int) R.string.done, (DialogInterface.OnClickListener) null).create();
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_ALBUM_VIEW_NORMAL.toString();
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/SortByAlbum", (Object) null);
    }

    public void onResume() {
        super.onResume();
        ((AlertDialog) getDialog()).getButton(-1).setOnClickListener(new x(this, 0));
        ((AlertDialog) getDialog()).getButton(-2).setOnClickListener(new x(this, 1));
    }

    public boolean supportPopover() {
        return true;
    }
}
