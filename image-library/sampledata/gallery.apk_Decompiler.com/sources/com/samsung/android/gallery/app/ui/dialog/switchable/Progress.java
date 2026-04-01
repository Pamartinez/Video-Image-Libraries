package com.samsung.android.gallery.app.ui.dialog.switchable;

import android.widget.ProgressBar;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.dialog.switchable.SwitchableDialog;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Progress extends LayoutType {
    private TextView mPercent;
    private ProgressBar mProgressBar;
    private TextView mProgressText;
    private String mTitle;

    private void bindDescription() {
        if (this.mProgressText == null) {
            this.mProgressText = (TextView) this.mLayout.findViewById(R.id.progress_text);
        }
    }

    private void bindPercent() {
        if (this.mPercent == null) {
            this.mPercent = (TextView) this.mLayout.findViewById(R.id.progress_percent);
        }
    }

    private void bindProgressBar() {
        if (this.mProgressBar == null) {
            this.mProgressBar = (ProgressBar) this.mLayout.findViewById(R.id.progress_bar);
        }
    }

    private void updateProgress() {
        int i2 = 0;
        int i7 = BundleWrapper.getInt(this.mArguments, "count", 0);
        int i8 = BundleWrapper.getInt(this.mArguments, "total", 100);
        int i10 = BundleWrapper.getInt(this.mArguments, "percent", -1);
        if (i8 != 0) {
            if (i10 < 0) {
                i2 = (i7 * 100) / i8;
            } else {
                i2 = Math.min(i10, 100);
            }
        }
        this.mProgressBar.setProgress(i2);
        this.mProgressText.setText(StringCompat.toReadableProgress(i7, i8));
        this.mPercent.setText(StringCompat.toReadablePercentage(i2));
    }

    public void bindViewInternal() {
        bindDescription();
        bindPercent();
        bindProgressBar();
    }

    public int getLayoutId() {
        return R.id.switchable_progress_layout;
    }

    public int getTypeInteger() {
        return 1;
    }

    public void initViews() {
        String string = getString("progress_title");
        if (string == null) {
            string = this.mTitle;
        }
        this.mTitle = string;
        setTitle(string, false);
        updateProgress();
    }

    public boolean isCancelable() {
        return false;
    }

    public boolean onCancelClicked(SwitchableDialog.PublishInfo publishInfo) {
        publishInfo.mBlackboard.post("command://CancelDialog", (Object) null);
        return true;
    }
}
