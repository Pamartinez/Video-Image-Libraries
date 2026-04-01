package com.samsung.android.gallery.widget.dialog;

import N2.j;
import a4.C0416a;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.samsung.android.gallery.module.settings.MiscSettingPreference;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Optional;
import tb.C0710b;
import tb.c;
import tb.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChinaGdprDialog extends BaseDialog implements CompoundButton.OnCheckedChangeListener {
    private AppCompatCheckBox mCheckBoxAll;
    private AppCompatCheckBox mCheckBoxLocation;
    private AppCompatCheckBox mCheckBoxNetwork;
    private final boolean mDataUsageAllowed = MiscSettingPreference.AllowDataUsageForChn.isEnabled();

    private String getDescription() {
        return getString(R$string.china_gdpr_description_with_options);
    }

    private View inflateView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R$layout.china_gdpr_layout, (ViewGroup) null);
        ViewUtils.setText((TextView) inflate.findViewById(R$id.description), getDescription());
        initLinkText(inflate.findViewById(R$id.checkbox_link));
        initCheckBoxView(inflate);
        return inflate;
    }

    private void initCheckBoxView(View view) {
        AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox) view.findViewById(R$id.checkbox_location);
        this.mCheckBoxLocation = appCompatCheckBox;
        appCompatCheckBox.setOnCheckedChangeListener(this);
        AppCompatCheckBox appCompatCheckBox2 = (AppCompatCheckBox) view.findViewById(R$id.checkbox_network);
        this.mCheckBoxNetwork = appCompatCheckBox2;
        appCompatCheckBox2.setOnCheckedChangeListener(this);
        this.mCheckBoxNetwork.setText(getCheckBoxNetworkDescription());
        ViewUtils.setText((TextView) view.findViewById(R$id.checkbox_network_subtitle), "- " + getString(R$string.wlan_mac_address));
        AppCompatCheckBox appCompatCheckBox3 = (AppCompatCheckBox) view.findViewById(R$id.checkbox_all);
        this.mCheckBoxAll = appCompatCheckBox3;
        appCompatCheckBox3.setOnClickListener(new d(this, 0));
    }

    private void initLinkText(View view) {
        Optional.ofNullable((TextView) view).ifPresent(new C0710b(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initLinkText$0(TextView textView) {
        textView.setText(getString(R$string.chn_gdpr_privacy_policy_details));
        textView.setOnClickListener(new d(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePositiveButton$1(AlertDialog alertDialog) {
        if (this.mCheckBoxAll != null) {
            alertDialog.getButton(-1).setEnabled(this.mCheckBoxAll.isChecked());
        }
    }

    /* access modifiers changed from: private */
    public void onCheckBoxAllClicked(View view) {
        boolean isChecked = ((AppCompatCheckBox) view).isChecked();
        this.mCheckBoxLocation.setChecked(isChecked);
        this.mCheckBoxNetwork.setChecked(isChecked);
    }

    /* access modifiers changed from: private */
    public void onLinkClicked(View view) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.settings.activity.ThirdPartyAccessActivity");
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            j.p(e, new StringBuilder("StartThirdPartyAccessActivity failed e="), this.TAG);
        }
    }

    /* access modifiers changed from: private */
    public void onNegativeButtonClicked(DialogInterface dialogInterface, int i2) {
        PreferenceName preferenceName;
        getBlackboard().post("data://user/dialog/GDPRLocation", Boolean.FALSE);
        dialogInterface.dismiss();
        postAnalyticsLog(AnalyticsEventId.EVENT_ALLOW_LOCATION_GDPR_DENY);
        GalleryPreference instance = GalleryPreference.getInstance();
        if (Features.isEnabled(Features.SUPPORT_SAMSUNG_PLACE)) {
            preferenceName = PreferenceName.IS_NEED_TO_SHOW_LOCATION_GDPR_POPUP_V2;
        } else {
            preferenceName = PreferenceName.IS_NEED_TO_SHOW_LOCATION_GDPR_POPUP;
        }
        instance.saveState(preferenceName, false);
        if (Features.isEnabled(Features.SHOW_BAIDU_LOCATION_AUTH_POPUP)) {
            GalleryPreference.getInstance().saveState(PreferenceName.IS_NEED_TO_SHOW_LOCATION_GDPR_POPUP_FOR_BAIDU, false);
        }
    }

    /* access modifiers changed from: private */
    public void onPositiveButtonClicked(DialogInterface dialogInterface, int i2) {
        getBlackboard().post("data://user/dialog/GDPRLocation", Boolean.TRUE);
        dialogInterface.dismiss();
        postAnalyticsLog(AnalyticsEventId.EVENT_ALLOW_LOCATION_GDPR_ALLOW);
    }

    private void updatePositiveButton() {
        Optional.ofNullable((AlertDialog) getDialog()).ifPresent(new C0710b(this, 0));
    }

    public Dialog createDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R$string.chn_gdpr_title);
        builder.setCancelable(false);
        builder.setView(inflateView());
        builder.setPositiveButton(R$string.agree, (DialogInterface.OnClickListener) new c(this, 0));
        builder.setNegativeButton(R$string.disagree, (DialogInterface.OnClickListener) new c(this, 1));
        builder.setOnKeyListener(new C0416a(3, this));
        return builder.create();
    }

    public String getCheckBoxNetworkDescription() {
        String charSequence = getContext().getApplicationInfo().loadLabel(getContext().getPackageManager()).toString();
        if (!this.mDataUsageAllowed) {
            return getContext().getString(R$string.chn_gdpr_allow_net_use_before_allowed, new Object[]{charSequence});
        }
        return getContext().getString(R$string.chn_gdpr_allow_net_use_after_allowed, new Object[]{charSequence});
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_ALLOW_LOCATION_GDPR_DLG.toString();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        boolean z3;
        AppCompatCheckBox appCompatCheckBox = this.mCheckBoxAll;
        if (!this.mCheckBoxLocation.isChecked() || !this.mCheckBoxNetwork.isChecked()) {
            z3 = false;
        } else {
            z3 = true;
        }
        appCompatCheckBox.setChecked(z3);
        updatePositiveButton();
    }

    public void onStart() {
        super.onStart();
        updatePositiveButton();
    }
}
