package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import q4.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleConfirmDialog extends BaseDialog {
    String mDescription;
    boolean mDismissAsCancel = false;
    String mEventDetail;
    String[] mEventIds = new String[3];
    boolean mHideCancel = false;
    String mOption1;
    String mOption2;
    String mScreenId;
    String mTitle;

    private boolean isPermanentDelete(String str) {
        if (TextUtils.equals(AnalyticsEventId.EVENT_DELETE_ITEM_DELETE.toString(), str) || TextUtils.equals(AnalyticsEventId.EVENT_RECYCLE_BIN_EMPTY_TRASH_OK.toString(), str) || TextUtils.equals(AnalyticsEventId.EVENT_RECYCLE_BIN_EMPTY_ALL_TRASH_OK.toString(), str) || TextUtils.equals(AnalyticsEventId.EVENT_DELETE_STORY_DELETE.toString(), str) || TextUtils.equals(AnalyticsEventId.EVENT_SHARED_VIEW_DELETE_DIALOG_OK.toString(), str) || TextUtils.equals(AnalyticsEventId.EVENT_SHARED_DETAIL_REMOVE_FROM_ALBUM_DIALOG_OK.toString(), str) || TextUtils.equals(AnalyticsEventId.EVENT_SHARED_DETAIL_FS_REMOVE_IMAGE_OK.toString(), str) || TextUtils.equals(AnalyticsEventId.EVENT_SHARED_DETAIL_FS_REMOVE_VIDEO_OK.toString(), str)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void onCancelClicked(DialogInterface dialogInterface, int i2) {
        publishInternal(0);
        dismissNowAllowingStateLoss();
    }

    /* access modifiers changed from: private */
    public void onOption1Clicked(DialogInterface dialogInterface, int i2) {
        publishInternal(1);
        dismissNowAllowingStateLoss();
    }

    /* access modifiers changed from: private */
    public void onOption2Clicked(DialogInterface dialogInterface, int i2) {
        publishInternal(2);
        dismissNowAllowingStateLoss();
    }

    private void refineTitleDescription() {
        String str = this.mTitle;
        if (str != null && this.mDescription == null) {
            this.mDescription = str;
            this.mTitle = null;
        }
    }

    public void addPositiveButton(AlertDialog.Builder builder) {
        builder.setPositiveButton((CharSequence) this.mOption1, (DialogInterface.OnClickListener) new u(this, 2));
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        if (context == null) {
            return super.createDialog(bundle);
        }
        loadArguments(getArguments());
        refineTitleDescription();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        String str = this.mTitle;
        if (str != null) {
            builder.setTitle((CharSequence) str);
        }
        String str2 = this.mDescription;
        if (str2 != null) {
            builder.setMessage((CharSequence) str2);
        }
        if (this.mOption1 != null) {
            addPositiveButton(builder);
        }
        String str3 = this.mOption2;
        if (str3 != null) {
            builder.setNegativeButton((CharSequence) str3, (DialogInterface.OnClickListener) new u(this, 0));
        }
        if (!this.mHideCancel) {
            builder.setNeutralButton((int) R.string.cancel, (DialogInterface.OnClickListener) new u(this, 1));
        }
        return builder.create();
    }

    public void dismissNowAllowingStateLoss() {
        try {
            if (getFragmentManager() != null) {
                dismissAllowingStateLoss();
            }
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "dismissNow failed", (Throwable) e);
        }
    }

    public String getPublishKey() {
        return "data://user/dialog/SimpleConfirm";
    }

    public String getScreenId() {
        return this.mScreenId;
    }

    public void loadArguments(Bundle bundle) {
        if (bundle != null) {
            this.mTitle = bundle.getString("title", (String) null);
            this.mDescription = bundle.getString("description", (String) null);
            this.mOption1 = bundle.getString("option1", (String) null);
            this.mOption2 = bundle.getString("option2", (String) null);
            this.mScreenId = bundle.getString("screenId", (String) null);
            boolean z = false;
            this.mEventIds[0] = bundle.getString("cancelEventId", (String) null);
            this.mEventIds[1] = bundle.getString("option1EventId", (String) null);
            this.mEventIds[2] = bundle.getString("option2EventId", (String) null);
            this.mEventDetail = bundle.getString("optionEventDetail", (String) null);
            this.mHideCancel = BundleWrapper.getBoolean(bundle, "hideCancel", false);
            if (BundleWrapper.getBoolean(bundle, "option1ColorRed") || isPermanentDelete(this.mEventIds[1])) {
                z = true;
            }
            this.mPositiveRedColor = z;
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        publishInternal((Integer) null);
        super.onCancel(dialogInterface);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.mDismissAsCancel) {
            onCancelClicked(dialogInterface, 0);
        } else {
            super.onDismiss(dialogInterface);
        }
    }

    public void onStart() {
        super.onStart();
        if (this.mPositiveRedColor) {
            Utils.setPermanentDeleteButtonTextColor(getDialog());
        }
    }

    public void publishInternal(Integer num) {
        Integer num2;
        Blackboard blackboard = getBlackboard();
        String publishKey = getPublishKey();
        if (num == null || num.intValue() == 0) {
            num2 = null;
        } else {
            num2 = num;
        }
        blackboard.post(publishKey, num2);
        if (num != null && num.intValue() >= 0) {
            int intValue = num.intValue();
            String[] strArr = this.mEventIds;
            if (intValue < strArr.length && strArr[num.intValue()] != null) {
                AnalyticsLogger.getInstance().postLog(getScreenId(), this.mEventIds[num.intValue()], this.mEventDetail);
            }
        }
    }

    public boolean supportPopover() {
        return true;
    }
}
