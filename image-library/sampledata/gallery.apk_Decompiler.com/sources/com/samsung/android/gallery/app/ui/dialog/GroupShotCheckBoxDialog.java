package com.samsung.android.gallery.app.ui.dialog;

import A.a;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import k7.j;
import q4.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupShotCheckBoxDialog extends BaseDialog {
    private CheckBox mCheckBox = null;
    private String mCheckBoxDescription;
    private String mDescription;
    private final SubscriberListener mDismissListener = new j(4, this);
    private String mEventId;
    private int mOption1 = R.string.ok;
    private int mOption2 = R.string.cancel;
    private String mTitle;

    private View getCheckboxView(Context context, String str, boolean z) {
        int i2 = 0;
        View inflate = LayoutInflater.from(context).inflate(R.layout.save_burst_shot_check_box, (ViewGroup) null, false);
        int paddingStart = inflate.getPaddingStart();
        if (!z) {
            i2 = inflate.getPaddingTop();
        }
        inflate.setPaddingRelative(paddingStart, i2, inflate.getPaddingEnd(), inflate.getPaddingBottom());
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.operation_checkbox);
        this.mCheckBox = checkBox;
        checkBox.setText(str);
        return inflate;
    }

    private String getPublishDataKey() {
        return "data://user/dialog/GroupShotCheckbox";
    }

    private boolean isChecked() {
        CheckBox checkBox = this.mCheckBox;
        if (checkBox == null || !checkBox.isChecked()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$1(DialogInterface dialogInterface, int i2) {
        int i7;
        if (isChecked()) {
            i7 = 2;
        } else {
            i7 = 1;
        }
        publishInternal(i7);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$2(DialogInterface dialogInterface, int i2) {
        publishInternal(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Object obj, Bundle bundle) {
        onDismissDialog();
    }

    private void loadArguments(Bundle bundle) {
        if (bundle != null) {
            this.mTitle = BundleWrapper.getString(bundle, "title", (String) null);
            this.mDescription = BundleWrapper.getString(bundle, "description", (String) null);
            this.mCheckBoxDescription = BundleWrapper.getString(bundle, "check_box_description", (String) null);
            this.mOption1 = BundleWrapper.getInt(bundle, "option1", R.string.ok);
            this.mOption2 = BundleWrapper.getInt(bundle, "option2", R.string.cancel);
            this.mEventId = BundleWrapper.getString(bundle, "option1EventId", (String) null);
            this.mPositiveRedColor = BundleWrapper.getBoolean(bundle, "option1ColorRed");
        }
    }

    private void onDismissDialog() {
        try {
            dismissAllowingStateLoss();
        } catch (Exception e) {
            N2.j.s(e, new StringBuilder("fail dismiss e="), this.TAG);
        }
    }

    private void publishInternal(int i2) {
        getBlackboard().post(getPublishDataKey(), Integer.valueOf(i2));
    }

    private void refineTitleDescription() {
        String str = this.mTitle;
        if (str != null && this.mDescription == null) {
            this.mDescription = str;
            this.mTitle = null;
        }
    }

    private void subscribeOrUnsubscribe(boolean z) {
        if (z) {
            try {
                getBlackboard().subscribe("command://DismissDialog", this.mDismissListener);
            } catch (Exception e) {
                String str = this.TAG;
                StringBuilder sb2 = new StringBuilder("unable to (un)subscribe [");
                sb2.append(z);
                sb2.append("].");
                a.s(e, sb2, str);
            }
        } else {
            getBlackboard().unsubscribe("command://DismissDialog", this.mDismissListener);
        }
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        Bundle arguments = getArguments();
        if (context == null || arguments == null) {
            return super.createDialog(bundle);
        }
        subscribeOrUnsubscribe(true);
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
        String str3 = this.mCheckBoxDescription;
        if (str3 != null) {
            builder.setView(getCheckboxView(context, str3, true ^ TextUtils.isEmpty(str3)));
        }
        builder.setPositiveButton(this.mOption1, (DialogInterface.OnClickListener) new l(this, 0)).setNegativeButton(this.mOption2, (DialogInterface.OnClickListener) new l(this, 1));
        return builder.create();
    }

    public void onCancel(DialogInterface dialogInterface) {
        publishInternal(0);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        subscribeOrUnsubscribe(false);
        super.onDismiss(dialogInterface);
    }

    public void onStart() {
        super.onStart();
        if (this.mPositiveRedColor) {
            Utils.setPermanentDeleteButtonTextColor(getDialog());
        }
    }

    public boolean supportPopover() {
        return true;
    }
}
