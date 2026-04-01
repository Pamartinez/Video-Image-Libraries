package com.samsung.android.gallery.widget.dialog;

import N2.j;
import a4.C0416a;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Optional;
import o5.C0496a;
import tb.h;
import tb.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlaceGdprDialog extends BaseDialog implements CompoundButton.OnCheckedChangeListener {
    private static final boolean IS_KOREAN_DEVICE = Features.isEnabled(Features.IS_KOREAN_DEVICE);
    private static final boolean SUPPORT_SAMSUNG_PLACE = Features.isEnabled(Features.SUPPORT_SAMSUNG_PLACE);
    private AppCompatCheckBox mCheckBox;

    private String getDescription() {
        if (!SUPPORT_SAMSUNG_PLACE) {
            return getString(R$string.place_gdpr_dialog_link_description);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getString(R$string.samsung_place_gdpr_dialog_description_1));
        sb2.append(" ");
        sb2.append(getString(R$string.samsung_place_gdpr_dialog_description_2));
        sb2.append(" ");
        sb2.append(getString(R$string.samsung_place_gdpr_dialog_description_3));
        if (!IS_KOREAN_DEVICE) {
            sb2.append(" ");
            sb2.append(getString(R$string.samsung_place_gdpr_dialog_description_4));
        }
        return sb2.toString();
    }

    private View inflateView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R$layout.place_gdpr_layout, (ViewGroup) null);
        ViewUtils.setText((TextView) inflate.findViewById(R$id.description_1), getDescription());
        initLinkText(inflate);
        ViewUtils.setVisibleOrGone(inflate.findViewById(R$id.description_2), !SUPPORT_SAMSUNG_PLACE);
        initCheckBoxView(inflate);
        return inflate;
    }

    private void initCheckBoxView(View view) {
        int i2;
        AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox) view.findViewById(R$id.checkbox);
        this.mCheckBox = appCompatCheckBox;
        appCompatCheckBox.setOnCheckedChangeListener(this);
        AppCompatCheckBox appCompatCheckBox2 = this.mCheckBox;
        if (SUPPORT_SAMSUNG_PLACE) {
            i2 = R$string.samsung_place_gdpr_dialog_check_box_description;
        } else {
            i2 = R$string.place_gdpr_dialog_check_box_description;
        }
        appCompatCheckBox2.setText(i2);
    }

    private void initLinkText(View view) {
        Optional.ofNullable((TextView) view.findViewById(R$id.link)).ifPresent(new h(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initLinkText$0(TextView textView) {
        int i2;
        boolean z = SUPPORT_SAMSUNG_PLACE;
        if (!z || !IS_KOREAN_DEVICE) {
            if (z) {
                i2 = R$string.chn_gdpr_privacy_policy_details;
            } else {
                i2 = R$string.place_gdpr_dialog_link;
            }
            String string = getString(i2);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
            spannableStringBuilder.setSpan(new UnderlineSpan(), 0, string.length(), 18);
            textView.setText(spannableStringBuilder);
            textView.setOnClickListener(new C0496a(19, this));
            return;
        }
        textView.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePositiveButton$1(AlertDialog alertDialog) {
        alertDialog.getButton(-1).setEnabled(this.mCheckBox.isChecked());
    }

    /* access modifiers changed from: private */
    public void onLinkClicked(View view) {
        String str;
        try {
            if (SUPPORT_SAMSUNG_PLACE) {
                str = "https://account.samsung.com/membership/terms/privacypolicy";
            } else {
                str = "https://foursquare.com/legal/privacy";
            }
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_PLACE_GDPR_DIALOG_PRIVACY_POLICY);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), R$string.no_internet_browser_toast, 0).show();
            j.p(e, new StringBuilder("error="), this.TAG);
        }
    }

    /* access modifiers changed from: private */
    public void onNegativeButtonClicked(DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_PLACE_GDPR_DIALOG_CANCEL);
    }

    /* access modifiers changed from: private */
    public void onPositiveButtonClicked(DialogInterface dialogInterface, int i2) {
        getBlackboard().post("data://user/dialog/GDPRLocation", Boolean.TRUE);
        dialogInterface.dismiss();
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_PLACE_GDPR_DIALOG_OK);
    }

    private void updatePositiveButton() {
        Optional.ofNullable((AlertDialog) getDialog()).ifPresent(new h(this, 0));
    }

    public Dialog createDialog(Bundle bundle) {
        int i2;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (SUPPORT_SAMSUNG_PLACE) {
            i2 = R$string.samsung_place_gdpr_dialog_title;
        } else {
            i2 = R$string.place_gdpr_dialog_title;
        }
        builder.setTitle(i2);
        builder.setCancelable(false);
        builder.setView(inflateView());
        builder.setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) new i(this, 0));
        builder.setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) new i(this, 1));
        builder.setOnKeyListener(new C0416a(4, this));
        return builder.create();
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SETTING.toString();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_PLACE_GDPR_DIALOG_AGREE, AnalyticsDetail.getOnOff(z));
        updatePositiveButton();
    }

    public void onStart() {
        super.onStart();
        updatePositiveButton();
    }
}
