package com.samsung.android.gallery.app.ui.dialog.permission;

import a4.C0416a;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PermissionHelper;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import x4.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PermissionRationaleDialog extends BaseDialog {
    private boolean mFinishByCancel;
    private int mFunctionNameResId;
    private ArrayList<String> mPermissionList;

    private boolean finishActivity() {
        if (!this.mFinishByCancel) {
            return false;
        }
        try {
            getActivity().finish();
            return true;
        } catch (Exception unused) {
            Log.e(this.TAG, "finishActivity failed");
            return false;
        }
    }

    private ArrayList<String> getRequestList(Bundle bundle) {
        int parseInt = Integer.parseInt(bundle.getString("size"));
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < parseInt; i2++) {
            arrayList.add(bundle.getString("request" + i2));
        }
        return arrayList;
    }

    private void initListView(View view, ArrayList<String> arrayList) {
        ListView listView = (ListView) view.findViewById(R.id.request_permission_rationale_list);
        listView.setAdapter(new PermissionRationaleDialogAdapter(getContext(), arrayList));
        listView.setDivider((Drawable) null);
        listView.setFocusable(false);
    }

    private void initPopupText(View view) {
        int i2;
        if (this.mFunctionNameResId != 0) {
            i2 = R.string.permission_rationale_dialog_description;
        } else {
            i2 = R.string.permission_rationale_dialog_app_name_description;
        }
        Resources resources = getResources();
        int i7 = this.mFunctionNameResId;
        if (i7 == 0) {
            i7 = R.string.app_name;
        }
        String string = resources.getString(i7);
        String naturalizeText = SeApiCompat.naturalizeText(getResources().getString(i2, new Object[]{string}));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(naturalizeText);
        spannableStringBuilder.setSpan(new StyleSpan(1), naturalizeText.indexOf(string), string.length() + naturalizeText.indexOf(string), 33);
        ((TextView) view.findViewById(R.id.request_permission_rationale_title)).setText(spannableStringBuilder);
    }

    private void loadArguments() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mPermissionList = getRequestList(arguments);
            this.mFinishByCancel = UnsafeCast.toBoolean(arguments.getString("isFinishActivity"));
            this.mFunctionNameResId = Integer.parseInt(arguments.getString("function", "0"));
        }
    }

    /* access modifiers changed from: private */
    public void onClickNegative(DialogInterface dialogInterface, int i2) {
        if (!finishActivity()) {
            dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void onClickPositive(DialogInterface dialogInterface, int i2) {
        dismiss();
        PermissionHelper.launchApplicationDetailSettings(getContext());
    }

    public Dialog createDialog(Bundle bundle) {
        loadArguments();
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.request_permission_rationale_popup, (ViewGroup) null);
        initListView(inflate, this.mPermissionList);
        initPopupText(inflate);
        return new AlertDialog.Builder(getContext()).setView(inflate).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new a(this, 0)).setPositiveButton((int) R.string.settings, (DialogInterface.OnClickListener) new a(this, 1)).setCancelable(false).create();
    }

    public boolean onBackPressed(DialogInterface dialogInterface) {
        Log.d(this.TAG, "onBackPressed");
        return finishActivity();
    }

    public void onDestroy() {
        Log.d(this.TAG, "onDestroy");
        super.onDestroy();
    }

    public boolean onKeyClicked(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        if ((i2 != 4 || !onBackPressed(dialogInterface)) && !super.onKeyClicked(dialogInterface, i2, keyEvent)) {
            return false;
        }
        return true;
    }

    public void onResume() {
        super.onResume();
        Log.d(this.TAG, "onResume");
        getDialog().setOnKeyListener(new C0416a(5, this));
        setCancelable(false);
    }
}
