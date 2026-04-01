package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import h.C0199b;
import q4.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleSpinnerDialog extends BaseDialog {
    private final SubscriberListener mDismissListener = new w(this, 0);
    private final Handler mHandler = new Handler();
    private TextView mMessageView;
    private final SubscriberListener mUpdateListener = new w(this, 1);

    private void finalizeBlackboard() {
        Blackboard blackboard = getBlackboard();
        if (blackboard != null) {
            try {
                blackboard.erase("command://DismissSpinner");
                blackboard.unsubscribe("command://DismissSpinner", this.mDismissListener);
            } catch (IllegalArgumentException unused) {
            }
            try {
                blackboard.unsubscribe("command://UpdateSpinner", this.mUpdateListener);
            } catch (IllegalArgumentException unused2) {
            }
            blackboard.erase("spinner_running");
        }
    }

    private void initBlackboard() {
        Blackboard blackboard = getBlackboard();
        if (!blackboard.isEmpty("command://DismissSpinner")) {
            Log.w(this.TAG, "dismiss flag is on, so skip show spinner.");
            dismissAllowingStateLoss();
            return;
        }
        blackboard.subscribe("command://DismissSpinner", this.mDismissListener);
        blackboard.subscribe("command://UpdateSpinner", this.mUpdateListener);
        blackboard.publish("spinner_running", Boolean.TRUE);
    }

    private View initView(Context context, Bundle bundle) {
        String str;
        boolean z;
        int i2;
        if (bundle != null) {
            z = BundleWrapper.getBoolean(bundle, "light_theme", false);
            str = BundleWrapper.getString(bundle, "message", (String) null);
        } else {
            z = false;
            str = null;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.spinner_dialog, (ViewGroup) null, false);
        TextView textView = (TextView) inflate.findViewById(R.id.message_text);
        this.mMessageView = textView;
        textView.setText(str);
        View findViewById = inflate.findViewById(R.id.progress_circle);
        if (ViewUtils.isViewStub(findViewById)) {
            if (z) {
                i2 = R.layout.theme_sesl_progress_bar;
            } else {
                i2 = R.layout.basic_sesl_progress_bar;
            }
            ViewStub viewStub = (ViewStub) findViewById;
            viewStub.setLayoutResource(i2);
            viewStub.inflate();
        }
        return inflate;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onUpdateMessage$0(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("message");
            TextView textView = this.mMessageView;
            if (textView != null) {
                textView.setText(string);
            }
        }
    }

    /* access modifiers changed from: private */
    public void onDismissDialog(Object obj, Bundle bundle) {
        dismissAllowingStateLoss();
    }

    /* access modifiers changed from: private */
    public void onUpdateMessage(Object obj, Bundle bundle) {
        this.mHandler.post(new C0199b(24, this, bundle));
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        if (context == null) {
            return super.createDialog(bundle);
        }
        initBlackboard();
        AlertDialog create = new AlertDialog.Builder(getContext(), R.style.TransparentNoDimDialogStyle).setView(initView(context, getArguments())).create();
        if (create.getWindow() != null) {
            create.getWindow().setGravity(17);
        }
        setCancelable(false);
        return create;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        finalizeBlackboard();
        super.onDismiss(dialogInterface);
    }

    public boolean supportBackgroundBlur() {
        return false;
    }
}
