package com.samsung.android.gallery.plugins.portrait;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SeslProgressBar;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.R$layout;
import com.samsung.android.gallery.plugins.R$string;
import com.samsung.android.gallery.support.utils.StringCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProgressBarDialog {
    private final AlertDialog mDialog;
    private final SeslProgressBar mProgressBar;
    private final TextView mProgressPercent;

    public ProgressBarDialog(Context context, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View inflate = LayoutInflater.from(context).inflate(R$layout.simple_progress_dialog, (ViewGroup) null);
        this.mDialog = builder.setView(inflate).setNeutralButton(R$string.cancel, onClickListener).setCancelable(false).create();
        SeslProgressBar seslProgressBar = (SeslProgressBar) inflate.findViewById(R$id.progress_bar);
        this.mProgressBar = seslProgressBar;
        this.mProgressPercent = (TextView) inflate.findViewById(R$id.progress_percent);
        seslProgressBar.setMode(9);
    }

    public void dismiss() {
        this.mDialog.dismiss();
    }

    public void onUpdate(int i2) {
        SeslProgressBar seslProgressBar = this.mProgressBar;
        if (seslProgressBar != null) {
            seslProgressBar.setProgress(i2);
        }
        TextView textView = this.mProgressPercent;
        if (textView != null) {
            textView.setText(StringCompat.toReadablePercentage(i2));
        }
    }

    public void setTitle(String str) {
        this.mDialog.setTitle(str);
    }

    public void show() {
        this.mDialog.show();
    }
}
