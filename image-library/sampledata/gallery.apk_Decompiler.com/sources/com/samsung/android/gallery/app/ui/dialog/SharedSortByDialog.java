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
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import q4.s;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedSortByDialog extends BaseDialog {
    private int mPreviousSortBy = 0;
    RadioGroup sortOrderRadioGroup;
    RadioGroup sortRadioGroup;

    private void cancelSortBy() {
        postAnalyticsLog(AnalyticsEventId.EVENT_SHARED_SORT_BY_CANCEL);
        dismiss();
    }

    private MediaItem getCurrentAlbum() {
        return (MediaItem) getBlackboard().read("data://albums/current", null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$0(DialogInterface dialogInterface, int i2) {
        cancelSortBy();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$1(DialogInterface dialogInterface, int i2) {
        setSortBy();
    }

    private void setSortBy() {
        int i2;
        int i7;
        if (this.sortRadioGroup.getCheckedRadioButtonId() != R.id.radio_modified_time) {
            i2 = 10;
        } else {
            i2 = 20;
        }
        if (this.sortOrderRadioGroup.getCheckedRadioButtonId() != R.id.radio_descending) {
            i7 = i2 + 1;
        } else {
            i7 = i2 + 2;
        }
        getBlackboard().post("data://user/dialog/SharedSortBy", new Object[]{Integer.valueOf(this.mPreviousSortBy), Integer.valueOf(i7)});
        postAnalyticsLog(AnalyticsEventId.EVENT_SHARED_SORT_BY_DONE, SortByType.getSortByAnalyticsDetail(i7));
        dismiss();
    }

    public Dialog createDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.sortby_shared_dialog, (ViewGroup) null);
        this.sortOrderRadioGroup = (RadioGroup) inflate.findViewById(R.id.radiogroup_sortby_order);
        this.sortRadioGroup = (RadioGroup) inflate.findViewById(R.id.radiogroup_sortby_type);
        initDialog();
        return new AlertDialog.Builder(getActivity()).setTitle((int) R.string.sort_by_title).setView(inflate).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new s(this, 0)).setPositiveButton((int) R.string.done, (DialogInterface.OnClickListener) new s(this, 1)).create();
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SHARED_DETAIL_VIEW_NORMAL.toString();
    }

    public void initDialog() {
        MediaItem currentAlbum = getCurrentAlbum();
        if (currentAlbum != null) {
            this.mPreviousSortBy = GalleryPreference.getInstance().loadSharedSortBy(MediaItemMde.getSpaceId(currentAlbum), 20);
        } else {
            this.mPreviousSortBy = 22;
        }
        if (SortByType.getSortBy(this.mPreviousSortBy) != 10) {
            this.sortRadioGroup.check(R.id.radio_modified_time);
        } else {
            this.sortRadioGroup.check(R.id.radio_created_time);
        }
        if (SortByType.getOrderBy(this.mPreviousSortBy) != 1) {
            this.sortOrderRadioGroup.check(R.id.radio_descending);
        } else {
            this.sortOrderRadioGroup.check(R.id.radio_ascending);
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/SharedSortBy", (Object) null);
    }

    public void onPause() {
        super.onPause();
    }
}
