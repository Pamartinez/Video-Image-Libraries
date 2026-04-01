package com.samsung.android.gallery.app.controller;

import A.a;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DialogTask extends AsyncTask<Void, Void, Void> {
    protected final String TAG = getClass().getSimpleName();
    private Dialog mDialog = null;

    public void createDialog(Context context) {
        if (context != null) {
            try {
                this.mDialog = new AlertDialog.Builder(context, R.style.TransparentDialogStyle).setView(LayoutInflater.from(context).inflate(R.layout.spinner_dialog, (ViewGroup) null, false)).create();
            } catch (Error | Exception e) {
                a.z(e, new StringBuilder("unable to create dialog e="), this.TAG);
            }
        }
    }

    public void dismissDialog() {
        try {
            Dialog dialog = this.mDialog;
            if (dialog != null) {
                dialog.dismiss();
            }
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("unable to dismiss dialog e="), this.TAG);
        }
    }

    public void showDialog() {
        try {
            Dialog dialog = this.mDialog;
            if (dialog != null) {
                dialog.show();
            }
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("unable to show dialog e="), this.TAG);
        }
    }

    public void showToast(Context context, String str) {
        try {
            Toast.makeText(context, str, 0).show();
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("unable to show toast e="), this.TAG);
        }
    }
}
