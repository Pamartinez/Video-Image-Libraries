package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import q4.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SortByAlbumSharedDialog extends BaseDialog {
    private LinearLayout mFirstLayout;
    private int mPreviousSortBy = 0;
    boolean mShowShared = true;
    private RadioGroup mSortAlbumRadioGroup;

    private void bindViews(View view) {
        this.mSortAlbumRadioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_sortby_album);
        this.mFirstLayout = (LinearLayout) view.findViewById(R.id.sortby_first_layout);
    }

    private void initDialog() {
        int i2;
        int i7;
        int albumsOrder = SortByType.getAlbumsOrder();
        this.mPreviousSortBy = albumsOrder;
        boolean z = true;
        if (SortByType.getOrderBy(albumsOrder) != 1) {
            z = false;
        }
        int sortBy = SortByType.getSortBy(this.mPreviousSortBy);
        if (sortBy == 30) {
            RadioGroup radioGroup = this.mSortAlbumRadioGroup;
            if (z) {
                i2 = R.id.radio_name_ascending;
            } else {
                i2 = R.id.radio_name_descending;
            }
            radioGroup.check(i2);
        } else if (sortBy == 50) {
            RadioGroup radioGroup2 = this.mSortAlbumRadioGroup;
            if (z) {
                i7 = R.id.radio_numbers_ascending;
            } else {
                i7 = R.id.radio_numbers_descending;
            }
            radioGroup2.check(i7);
        } else if (sortBy == 40 || sortBy == 41) {
            this.mSortAlbumRadioGroup.check(R.id.radio_custom_order);
        }
        if (!this.mShowShared) {
            setDisableView(this.mFirstLayout);
        }
    }

    private boolean loadEnableSharedFromBundle() {
        if (getArguments() == null || !BundleWrapper.getBoolean(getArguments(), "enable_create_shared", false)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onClickNegative(View view) {
        postAnalyticsLog(AnalyticsEventId.EVENT_ALBUM_SORT_BY_CANCEL);
        dismiss();
    }

    /* access modifiers changed from: private */
    public void onClickPositive(View view) {
        int i2;
        switch (this.mSortAlbumRadioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_name_ascending:
                i2 = 31;
                break;
            case R.id.radio_name_descending:
                i2 = 32;
                break;
            case R.id.radio_numbers_ascending:
                i2 = 51;
                break;
            case R.id.radio_numbers_descending:
                i2 = 52;
                break;
            default:
                i2 = 40;
                break;
        }
        getBlackboard().post("data://user/dialog/SortByAlbumShared", new Object[]{Integer.valueOf(this.mPreviousSortBy), Integer.valueOf(i2)});
        postAnalyticsLog(AnalyticsEventId.EVENT_ALBUM_SORT_BY_DONE, SortByType.getSortByAnalyticsDetail(i2));
        dismiss();
    }

    private void setDisableView(View view) {
        view.setVisibility(8);
    }

    public Dialog createDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.sortby_albumshared_dialog, (ViewGroup) null);
        this.mShowShared = loadEnableSharedFromBundle();
        bindViews(inflate);
        initDialog();
        return new AlertDialog.Builder(getActivity()).setTitle((int) R.string.sort_by_title).setView(inflate).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton((int) R.string.done, (DialogInterface.OnClickListener) null).create();
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_ALBUM_VIEW_NORMAL.toString();
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/SortByAlbumShared", (Object) null);
    }

    public void onResume() {
        super.onResume();
        ((AlertDialog) getDialog()).getButton(-1).setOnClickListener(new y(this, 0));
        ((AlertDialog) getDialog()).getButton(-2).setOnClickListener(new y(this, 1));
    }
}
