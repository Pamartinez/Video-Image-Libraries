package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.module.store.AppRatingHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import o5.C0496a;
import q4.C0505b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AppRatingDialog extends BaseDialog {
    private String makeDescription(Context context, String str) {
        int i2;
        if (PackageMonitorCompat.getInstance().isInstallerPackageOfGallery("com.android.vending")) {
            i2 = R.string.google_play_store;
        } else {
            i2 = R.string.galaxy_store;
        }
        return SeApiCompat.naturalizeText(context.getString(R.string.app_rating_popup_dialog_description, new Object[]{str, getString(i2)})).replaceFirst("\\.\\s*", ".\n");
    }

    /* access modifiers changed from: private */
    public void onLaterButtonClicked(DialogInterface dialogInterface, int i2) {
        AppRatingHelper.postpone();
        postAnalyticsLog(AnalyticsEventId.EVENT_APP_RATING_DIALOG_LATER);
    }

    /* access modifiers changed from: private */
    public void onNoThanksButtonClicked(DialogInterface dialogInterface, int i2) {
        AppRatingHelper.decline();
        postAnalyticsLog(AnalyticsEventId.EVENT_APP_RATING_DIALOG_NO_THANKS);
    }

    /* access modifiers changed from: private */
    public void onRateItNowClicked(View view) {
        AppRatingHelper.startStore(getContext());
        postAnalyticsLog(AnalyticsEventId.EVENT_APP_RATING_DIALOG_RATE_IT_NOW);
        dismiss();
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        if (context != null) {
            Log.d(this.TAG, "createDialog");
            String string = context.getString(R.string.samsung_gallery_app_name);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle((CharSequence) getString(R.string.app_rating_popup_dialog_title, string));
            builder.setPositiveButton((int) R.string.app_rating_popup_nothanks_btn_text, (DialogInterface.OnClickListener) new C0505b(this, 0));
            builder.setNegativeButton((int) R.string.app_rating_popup_later_btn_text, (DialogInterface.OnClickListener) new C0505b(this, 1));
            View inflate = LayoutInflater.from(context).inflate(R.layout.app_rating_dialog, (ViewGroup) null, false);
            AlertDialog create = builder.setView(inflate).create();
            ((TextView) inflate.findViewById(R.id.rating_dialog_body_text)).setText(makeDescription(context, string));
            ViewGroup viewGroup = (ViewGroup) inflate.findViewById(R.id.rate_it_now_box);
            viewGroup.setOnClickListener(new C0496a(9, this));
            ViewUtils.setTouchArea(viewGroup, getResources().getDimensionPixelSize(R.dimen.rating_dialog_side_padding), getResources().getDimensionPixelSize(R.dimen.rating_dialog_body_text_margin_bottom), getResources().getDimensionPixelSize(R.dimen.rating_dialog_side_padding), getResources().getDimensionPixelSize(R.dimen.rating_dialog_margin_bottom));
            create.setOnDismissListener(this);
            create.setOnCancelListener(this);
            return create;
        }
        Log.e(this.TAG, "createDialog failed null context");
        return super.createDialog(bundle);
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_APP_RATING_DIALOG.toString();
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (dialogInterface != null) {
            AppRatingHelper.postpone();
        }
    }
}
