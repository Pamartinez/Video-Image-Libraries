package com.samsung.android.gallery.app.ui.dialog.switchable;

import Fa.K;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.dialog.switchable.SwitchableDialog;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.scsp.media.file.FileApiContract;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileOperation extends LayoutType {
    private CheckBox mCheckBox;
    private TextView mDescription;
    private String mFilePath;
    private String mScreenId;

    private void bindCheckBox() {
        if (this.mCheckBox == null && isCheckBoxEnabled()) {
            CheckBox checkBox = (CheckBox) this.mLayout.findViewById(R.id.confirm_checkbox);
            this.mCheckBox = checkBox;
            checkBox.setOnCheckedChangeListener(new K(6, this));
        }
        ViewUtils.setVisibleOrGone(this.mCheckBox, isCheckBoxEnabled());
    }

    private void bindDescription() {
        if (this.mDescription == null) {
            this.mDescription = (TextView) this.mLayout.findViewById(R.id.confirm_description);
        }
    }

    private String getDescription(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.mLayout.getContext().getString(R.string.copy_exist, new Object[]{str});
    }

    private String getFileName() {
        return BundleWrapper.getString(this.mArguments, "title", (String) null);
    }

    private boolean isCheckBoxEnabled() {
        return !BundleWrapper.getBoolean(this.mArguments, "disable_check_box", false);
    }

    private boolean isChecked() {
        CheckBox checkBox = this.mCheckBox;
        if (checkBox == null || !checkBox.isChecked()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        AnalyticsLogger.getInstance().postLog(this.mScreenId, AnalyticsEventId.EVENT_COPY_MOVE_FILE_NAME_IN_USE_ALL_APPLY_ONOFF.toString(), AnalyticsDetail.getOnOff(z));
    }

    private void publishOptionSelected(SwitchableDialog.PublishInfo publishInfo, int i2) {
        publishInfo.mBlackboard.post(new UriBuilder("command://OperationSelected").appendArg("target", i2).appendArg("checked", isChecked()).build(), this.mFilePath);
        postAnalyticsLog(publishInfo);
    }

    public void bindViewInternal() {
        bindDescription();
        bindCheckBox();
    }

    public String[] getButtonStrings(Resources resources) {
        return new String[]{resources.getString(R.string.skip), resources.getString(R.string.rename), resources.getString(R.string.replace)};
    }

    public int getLayoutId() {
        return R.id.switchable_confirm_layout;
    }

    public int getTypeInteger() {
        return 2;
    }

    public void initValues(Bundle bundle) {
        super.initValues(bundle);
        this.mScreenId = BundleWrapper.getString(this.mArguments, "screenId", (String) null);
        this.mFilePath = BundleWrapper.getString(this.mArguments, FileApiContract.Parameter.PATH, (String) null);
    }

    public void initViews() {
        setTitle(this.mLayout.getContext().getString(R.string.file_rename_or_replace), true);
        initTextView(this.mDescription, getDescription(getFileName()));
    }

    public boolean isCancelable() {
        return false;
    }

    public void onCancel(SwitchableDialog.PublishInfo publishInfo) {
        publishInfo.mBlackboard.post("command://CancelDialog", (Object) null);
    }

    public boolean onCancelClicked(SwitchableDialog.PublishInfo publishInfo) {
        publishOptionSelected(publishInfo, -3);
        return false;
    }

    public void onOption1Clicked(SwitchableDialog.PublishInfo publishInfo) {
        publishOptionSelected(publishInfo, -1);
    }

    public void onOption2Clicked(SwitchableDialog.PublishInfo publishInfo) {
        publishOptionSelected(publishInfo, -2);
    }
}
