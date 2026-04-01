package com.samsung.android.gallery.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InputDialogBuilder {
    AlertDialog.Builder builder;
    LinearLayout linearLayout;
    private Context mContext;

    public InputDialogBuilder(Context context, String str) {
        this.mContext = context;
        this.builder = new AlertDialog.Builder(context);
        LinearLayout linearLayout2 = new LinearLayout(context);
        this.linearLayout = linearLayout2;
        linearLayout2.setOrientation(1);
        this.linearLayout.setPadding(100, 100, 100, 100);
        this.builder.setTitle((CharSequence) str);
        this.builder.setView((View) this.linearLayout);
    }

    public EditText addInputField(String str, String str2) {
        TextView textView = new TextView(this.mContext);
        textView.setText(str);
        this.linearLayout.addView(textView);
        EditText editText = new EditText(this.mContext);
        editText.setHint(str);
        if (str2 != null) {
            editText.setText(str2);
        }
        this.linearLayout.addView(editText);
        return editText;
    }

    public void buildAndShow() {
        this.builder.create().show();
    }

    public void setNegativeButton(String str, DialogInterface.OnClickListener onClickListener) {
        this.builder.setNegativeButton((CharSequence) str, onClickListener);
    }

    public void setPositiveButton(String str, DialogInterface.OnClickListener onClickListener) {
        this.builder.setPositiveButton((CharSequence) str, onClickListener);
    }
}
