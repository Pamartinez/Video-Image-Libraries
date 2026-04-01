package com.samsung.android.gallery.app.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SeslProgressBar;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import k7.j;
import o5.C0496a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleConfirmProgressDialog extends SimpleConfirmDialog {
    private String mDismissSubscribeKey;
    private final SubscriberListener mDismissSubscribeListener = new j(6, this);

    private void disableCancelButton() {
        Button button;
        AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog != null && (button = alertDialog.getButton(-3)) != null) {
            button.setOnClickListener((View.OnClickListener) null);
            button.setAlpha(0.4f);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Object obj, Bundle bundle) {
        dismissNowAllowingStateLoss();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPositiveButtonClickListener$1(View view) {
        publishInternal(1);
        replacePositiveButtonToProgress(view);
        disableCancelButton();
        subscribeDismissDialogEvent();
    }

    private void replacePositiveButtonToProgress(View view) {
        Context context = getContext();
        if (context != null) {
            RelativeLayout relativeLayout = new RelativeLayout(context);
            relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(view.getWidth(), -1));
            SeslProgressBar seslProgressBar = new SeslProgressBar(context);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(view.getHeight(), view.getHeight());
            layoutParams.addRule(13);
            seslProgressBar.setLayoutParams(layoutParams);
            relativeLayout.addView(seslProgressBar);
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                int indexOfChild = viewGroup.indexOfChild(view);
                ViewUtils.removeView(viewGroup, view);
                viewGroup.addView(relativeLayout, indexOfChild);
            }
        }
    }

    private void setPositiveButtonClickListener() {
        AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog != null) {
            alertDialog.getButton(-1).setOnClickListener(new C0496a(12, this));
        }
    }

    private void subscribeDismissDialogEvent() {
        if (!TextUtils.isEmpty(this.mDismissSubscribeKey)) {
            getBlackboard().subscribeOnUi(this.mDismissSubscribeKey, this.mDismissSubscribeListener);
        }
    }

    private void unsubscribeDismissDialogEvent() {
        if (!TextUtils.isEmpty(this.mDismissSubscribeKey)) {
            getBlackboard().unsubscribe(this.mDismissSubscribeKey, this.mDismissSubscribeListener);
        }
    }

    public void addPositiveButton(AlertDialog.Builder builder) {
        builder.setPositiveButton((CharSequence) this.mOption1, (DialogInterface.OnClickListener) null);
    }

    public String getPublishKey() {
        return "data://user/dialog/SimpleConfirmProgress";
    }

    public void loadArguments(Bundle bundle) {
        super.loadArguments(bundle);
        this.mDismissSubscribeKey = bundle.getString("dismissKey", (String) null);
    }

    public void onDestroy() {
        super.onDestroy();
        unsubscribeDismissDialogEvent();
    }

    public void onResume() {
        super.onResume();
        setPositiveButtonClickListener();
    }
}
