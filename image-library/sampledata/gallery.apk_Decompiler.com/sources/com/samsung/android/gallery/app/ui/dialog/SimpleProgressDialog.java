package com.samsung.android.gallery.app.ui.dialog;

import Fa.C0558l;
import a4.C0416a;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SeslProgressBar;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import o5.C0496a;
import q4.v;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleProgressDialog extends BaseDialog {
    private TextView mCountView;
    private final SubscriberListener mDismissListener = new v(this, 0);
    private TextView mPausedView;
    private TextView mPercentView;
    private SeslProgressBar mProgressBar;
    private boolean mSupportBackKey;
    private boolean mSupportPause;
    private String mTitle;
    private final SubscriberListener mUpdateListener = new v(this, 1);

    private Activity initBlackboard() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Blackboard blackboard = getBlackboard();
            blackboard.subscribeOnUi("command://DismissDialog", this.mDismissListener);
            blackboard.subscribeOnUi("command://UpdateProgress", this.mUpdateListener);
            return activity;
        }
        throw new AssertionError("fail to refer blackboard");
    }

    private View initView() {
        View inflate = initBlackboard().getLayoutInflater().inflate(R.layout.simple_progress_dialog, (ViewGroup) null, false);
        this.mCountView = (TextView) inflate.findViewById(R.id.progress_text);
        this.mPercentView = (TextView) inflate.findViewById(R.id.progress_percent);
        this.mProgressBar = (SeslProgressBar) inflate.findViewById(R.id.progress_bar);
        this.mPausedView = (TextView) inflate.findViewById(R.id.paused_text);
        this.mProgressBar.setMax(100);
        this.mProgressBar.setMode(9);
        return inflate;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Object obj, Bundle bundle) {
        onDismissDialog();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(Object obj, Bundle bundle) {
        onUpdateProgress(bundle);
    }

    /* access modifiers changed from: private */
    public void onCancelClicked(DialogInterface dialogInterface, int i2) {
        Blackboard blackboard = getBlackboard();
        if (blackboard != null) {
            blackboard.post("command://CancelDialog", (Object) null);
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0014 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void onDismissDialog() {
        /*
            r3 = this;
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r3.getBlackboard()
            if (r0 == 0) goto L_0x0014
            java.lang.String r1 = "command://DismissDialog"
            com.samsung.android.gallery.support.blackboard.SubscriberListener r2 = r3.mDismissListener     // Catch:{ IllegalArgumentException -> 0x000d }
            r0.unsubscribe(r1, r2)     // Catch:{ IllegalArgumentException -> 0x000d }
        L_0x000d:
            java.lang.String r1 = "command://UpdateProgress"
            com.samsung.android.gallery.support.blackboard.SubscriberListener r2 = r3.mUpdateListener     // Catch:{ IllegalArgumentException -> 0x0014 }
            r0.unsubscribe(r1, r2)     // Catch:{ IllegalArgumentException -> 0x0014 }
        L_0x0014:
            r3.dismissAllowingStateLoss()     // Catch:{ Exception -> 0x0018 }
            goto L_0x0025
        L_0x0018:
            r0 = move-exception
            java.lang.String r3 = r3.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "dismiss dialog failed e="
            r1.<init>(r2)
            A.a.s(r0, r1, r3)
        L_0x0025:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.dialog.SimpleProgressDialog.onDismissDialog():void");
    }

    /* access modifiers changed from: private */
    public void onPositiveClicked(View view) {
        if (getBlackboard() == null) {
            return;
        }
        if (ViewUtils.isVisible(this.mPausedView)) {
            ((Button) view).setText(R.string.pause);
            ViewUtils.setVisibleOrGone(this.mCountView, true);
            ViewUtils.setVisibleOrGone(this.mPausedView, false);
            return;
        }
        ((Button) view).setText(R.string.slideshow_resume);
        ViewUtils.setVisibleOrGone(this.mCountView, false);
        ViewUtils.setVisibleOrGone(this.mPausedView, true);
    }

    private void onUpdateProgress(Bundle bundle) {
        if (bundle != null) {
            int i2 = 0;
            int i7 = BundleWrapper.getInt(bundle, "count", 0);
            int i8 = BundleWrapper.getInt(bundle, "total", 100);
            int i10 = BundleWrapper.getInt(bundle, "percent", -1);
            if (i8 != 0) {
                if (i10 < 0) {
                    i2 = (i7 * 100) / i8;
                } else {
                    i2 = Math.min(i10, 100);
                }
            }
            SeslProgressBar seslProgressBar = this.mProgressBar;
            if (seslProgressBar != null) {
                seslProgressBar.setProgress(i2);
            }
            TextView textView = this.mCountView;
            if (textView != null && i8 > 0) {
                textView.setText(StringCompat.toReadableProgress(i7, i8));
            }
            TextView textView2 = this.mPercentView;
            if (textView2 != null) {
                textView2.setText(StringCompat.toReadablePercentage(i2));
            }
        }
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        if (context == null) {
            return super.createDialog(bundle);
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mTitle = BundleWrapper.getString(arguments, "progress_title", getString(R.string.please_wait));
            this.mSupportPause = BundleWrapper.getBoolean(arguments, "support_pause", false);
            this.mSupportBackKey = BundleWrapper.getBoolean(arguments, "support_back_key", false);
        }
        View initView = initView();
        onUpdateProgress(arguments);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle((CharSequence) this.mTitle).setView(initView).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new C0558l(15, this));
        if (this.mSupportPause) {
            builder.setPositiveButton((int) R.string.pause, (DialogInterface.OnClickListener) null);
        }
        if (this.mSupportBackKey) {
            builder.setOnKeyListener(new C0416a(1, this));
        }
        setCancelable(false);
        return builder.create();
    }

    public boolean onKeyClicked(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        if (i2 != 4) {
            return false;
        }
        onCancelClicked(dialogInterface, -2);
        return true;
    }

    public void onResume() {
        AlertDialog alertDialog;
        super.onResume();
        if (this.mSupportPause && (alertDialog = (AlertDialog) getDialog()) != null) {
            alertDialog.getButton(-1).setOnClickListener(new C0496a(13, this));
        }
    }
}
