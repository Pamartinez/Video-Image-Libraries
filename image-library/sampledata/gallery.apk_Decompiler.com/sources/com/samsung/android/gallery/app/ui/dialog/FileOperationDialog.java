package com.samsung.android.gallery.app.ui.dialog;

import Fa.K;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.scsp.media.file.FileApiContract;
import com.sec.android.gallery3d.R;
import k7.j;
import o5.C0496a;
import q4.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileOperationDialog extends BaseDialog {
    private CheckBox mCheckBox;
    private final SubscriberListener mDismissListener = new j(3, this);
    private String mFilePath;
    private String mScreenId;

    private void initBlackBoard() {
        if (getActivity() != null) {
            getBlackboard().subscribe(getClass().getSimpleName(), this.mDismissListener);
            getBlackboard().subscribe("command://DismissDialog", this.mDismissListener);
            return;
        }
        throw new AssertionError("fail to refer blackboard");
    }

    private View initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.file_operation_dialog, (ViewGroup) null, false);
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.operation_checkbox);
        this.mCheckBox = checkBox;
        if (checkBox != null) {
            checkBox.setOnCheckedChangeListener(new K(5, this));
        }
        return inflate;
    }

    private boolean isChecked() {
        CheckBox checkBox = this.mCheckBox;
        if (checkBox == null || !checkBox.isChecked()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Object obj, Bundle bundle) {
        onDismissDialog();
    }

    /* access modifiers changed from: private */
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        postAnalyticsLog(AnalyticsEventId.EVENT_COPY_MOVE_FILE_NAME_IN_USE_ALL_APPLY_ONOFF, AnalyticsDetail.getOnOff(z));
    }

    /* access modifiers changed from: private */
    public void onClickNegative(DialogInterface dialogInterface, int i2) {
        publishOptionSelected(i2);
        postAnalyticsLog(AnalyticsEventId.EVENT_COPY_MOVE_FILE_NAME_IN_USE_REPLACE);
    }

    /* access modifiers changed from: private */
    public void onClickNeutral(DialogInterface dialogInterface, int i2) {
        publishOptionSelected(i2);
        postAnalyticsLog(AnalyticsEventId.EVENT_COPY_MOVE_FILE_NAME_IN_USE_SKIP);
    }

    /* access modifiers changed from: private */
    public void onClickPositive(View view) {
        if (isChecked()) {
            dismiss();
        }
        publishOptionSelected(-1);
        postAnalyticsLog(AnalyticsEventId.EVENT_COPY_MOVE_FILE_NAME_IN_USE_RENAME);
    }

    private void onDismissDialog() {
        try {
            dismissAllowingStateLoss();
        } catch (Exception unused) {
            Log.w(this.TAG, "fail dismiss");
        }
    }

    private void publishCancelSelected() {
        Blackboard blackboard = getBlackboard();
        if (blackboard != null) {
            blackboard.post("command://CancelDialog", (Object) null);
        }
    }

    private void publishOptionSelected(int i2) {
        Blackboard blackboard = getBlackboard();
        if (blackboard != null) {
            blackboard.post(new UriBuilder("command://OperationSelected").appendArg("target", i2).appendArg("checked", isChecked()).build(), this.mFilePath);
        }
    }

    public Dialog createDialog(Bundle bundle) {
        boolean z;
        String str;
        Context context = getContext();
        if (context == null) {
            return super.createDialog(bundle);
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            str = BundleWrapper.getString(arguments, "title", (String) null);
            this.mFilePath = BundleWrapper.getString(arguments, FileApiContract.Parameter.PATH, (String) null);
            this.mScreenId = BundleWrapper.getString(arguments, "screenId", (String) null);
            z = BundleWrapper.getBoolean(arguments, "disable_check_box", false);
        } else {
            z = false;
            str = null;
        }
        initBlackBoard();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle((int) R.string.file_rename_or_replace).setPositiveButton((int) R.string.rename, (DialogInterface.OnClickListener) null).setNegativeButton((int) R.string.replace, (DialogInterface.OnClickListener) new k(this, 0)).setNeutralButton((int) R.string.skip, (DialogInterface.OnClickListener) new k(this, 1));
        if (!TextUtils.isEmpty(str)) {
            builder.setMessage((CharSequence) getString(R.string.copy_exist, str));
        }
        if (!z) {
            builder.setView(initView(context));
        }
        AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(false);
        return create;
    }

    public String getScreenId() {
        return this.mScreenId;
    }

    public void onCancel(DialogInterface dialogInterface) {
        publishCancelSelected();
        postAnalyticsLog(AnalyticsEventId.EVENT_COPY_MOVE_FILE_NAME_IN_USE_SKIP);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        getBlackboard().unsubscribe(getClass().getSimpleName(), this.mDismissListener);
        getBlackboard().unsubscribe("command://DismissDialog", this.mDismissListener);
        super.onDismiss(dialogInterface);
    }

    public void onStart() {
        super.onStart();
        AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog != null) {
            alertDialog.getButton(-1).setOnClickListener(new C0496a(11, this));
        }
    }
}
