package com.samsung.android.gallery.widget.dialog;

import D3.i;
import a4.C0416a;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.popover.PopoverHelper;
import com.samsung.android.gallery.widget.popover.PopoverInfo;
import com.samsung.android.gallery.widget.utils.SystemUi;
import java.util.Optional;
import o6.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BaseDialog extends AppCompatDialogFragment {
    protected final String TAG = getClass().getSimpleName();
    private Blackboard mBlackboard;
    private int mPopoverType;
    protected boolean mPositiveRedColor;

    private void enableBackgroundBlur(Dialog dialog) {
        if (supportBackgroundBlur() && (dialog instanceof AlertDialog)) {
            ((AlertDialog) dialog).seslSetBackgroundBlurEnabled(true);
        }
    }

    /* access modifiers changed from: private */
    public void onPopoverAnchorChanged(View view) {
        Dialog dialog = getDialog();
        boolean z = true;
        if (this.mPopoverType != 1) {
            z = false;
        }
        SeApiCompat.setPopoverDialogStyle(dialog, view, z);
    }

    private void setPopoverAnchorView() {
        PopoverInfo popoverInfo = PopoverHelper.getPopoverInfo(getBlackboard());
        if (popoverInfo != null) {
            this.mPopoverType = popoverInfo.getAnchorType();
            onPopoverAnchorChanged(popoverInfo.getAnchorView());
            popoverInfo.setOnAnchorUpdateListener(new p(15, this));
        }
    }

    public Dialog createDialog(Bundle bundle) {
        return super.onCreateDialog(bundle);
    }

    public final Context getApplicationContext() {
        return (Context) Optional.ofNullable(getContext()).map(new i(21)).orElse((Object) null);
    }

    public final Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    public int getNaviUpResourceId() {
        return R$drawable.tw_ic_ab_back_mtrl_with_inset;
    }

    public String getScreenId() {
        return null;
    }

    public final boolean isKnox() {
        return Features.isEnabled(Features.IS_KNOX_MODE);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (SystemUi.supportPopoverUi(getContext(), false) && supportPopover()) {
            setPopoverAnchorView();
        }
    }

    public void onAttach(Context context) {
        String obj = context.toString();
        String str = this.TAG;
        Log.l(str, "onAttach " + Logger.getSimpleName(obj));
        this.mBlackboard = Blackboard.getInstance(obj);
        super.onAttach(context);
    }

    public final Dialog onCreateDialog(Bundle bundle) {
        Dialog createDialog = createDialog(bundle);
        enableBackgroundBlur(createDialog);
        createDialog.setOnKeyListener(new C0416a(2, this));
        return createDialog;
    }

    public void onDestroy() {
        PopoverHelper.clearPopoverInfo(getBlackboard());
        super.onDestroy();
    }

    public void onDetach() {
        super.onDetach();
    }

    public boolean onKeyClicked(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        if (i2 != 84) {
            return false;
        }
        return true;
    }

    public void postAnalyticsLog() {
        AnalyticsLogger.getInstance().postLog(getScreenId());
    }

    public void setNavigationUpButton(Toolbar toolbar) {
        toolbar.setNavigationIcon(getNaviUpResourceId());
        toolbar.setNavigationContentDescription(R$string.navigate_up);
    }

    public boolean supportBackgroundBlur() {
        if (!SdkConfig.atLeast(SdkConfig.SEM.B) || Features.isEnabled(Features.IS_THEME_INSTALLED) || Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY)) {
            return false;
        }
        return true;
    }

    public final boolean supportClipboardEx() {
        return Features.isEnabled(Features.SUPPORT_SAMSUNG_CLIPBOARD);
    }

    public final boolean supportCmh() {
        return Features.isEnabled(Features.USE_CMH);
    }

    public boolean supportPopover() {
        return false;
    }

    public final void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
        AnalyticsLogger.getInstance().postLog(getScreenId(), analyticsEventId.toString());
    }

    public final void postAnalyticsLog(AnalyticsEventId analyticsEventId, String str) {
        AnalyticsLogger.getInstance().postLog(getScreenId(), analyticsEventId.toString(), str);
    }
}
