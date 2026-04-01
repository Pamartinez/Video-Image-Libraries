package com.samsung.android.gallery.app.ui.dialog;

import A5.b;
import G9.a;
import T8.C0578a;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;
import n5.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateNewDialog extends BaseDialog {
    /* access modifiers changed from: private */
    public static /* synthetic */ ViewGroup lambda$inflateView$0(Context context, String str) {
        return (ViewGroup) LayoutInflater.from(context).inflate(R.layout.create_new_dialog_item, (ViewGroup) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ViewGroup[] lambda$inflateView$1(int i2) {
        return new ViewGroup[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer[] lambda$inflateView$2(int i2) {
        return new Integer[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateView$3(int i2, View view) {
        getBlackboard().post("data://user/dialog/CreateNew", Integer.valueOf(i2));
        dismiss();
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        if (context != null) {
            return new AlertDialog.Builder(context).setView(inflateView(context)).setTitle((int) R.string.create_dialog_title).create();
        }
        Log.e(this.TAG, "createDialog failed null context");
        return super.createDialog(bundle);
    }

    public String[] getItems() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return null;
        }
        String string = arguments.getString("create_new_items");
        if (string != null) {
            return string.split(";");
        }
        return new String[0];
    }

    public View inflateView(Context context) {
        String[] items = getItems();
        if (items == null) {
            return null;
        }
        ScrollView scrollView = (ScrollView) LayoutInflater.from(context).inflate(R.layout.create_new_dialog_container, (ViewGroup) null);
        ViewGroup viewGroup = (ViewGroup) scrollView.findViewById(R.id.dialog_container);
        ViewGroup[] viewGroupArr = (ViewGroup[]) Arrays.stream(items).map(new a(context, 4)).toArray(new C0578a(24));
        Stream stream = Arrays.stream(viewGroupArr);
        Objects.requireNonNull(viewGroup);
        stream.forEach(new b(viewGroup, 1));
        for (int i2 = 0; i2 < items.length; i2++) {
            Integer[] numArr = (Integer[]) Arrays.stream(items[i2].split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).map(new e(18)).toArray(new C0578a(25));
            ((ImageView) viewGroupArr[i2].findViewById(R.id.create_icon)).setImageResource(numArr[0].intValue());
            ((TextView) viewGroupArr[i2].findViewById(R.id.create_title)).setText(numArr[1].intValue());
            ((TextView) viewGroupArr[i2].findViewById(R.id.create_description)).setText(numArr[2].intValue());
            viewGroupArr[i2].setOnClickListener(new q4.e(this, i2, 0));
            viewGroupArr[i2].setVisibility(0);
        }
        return scrollView;
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/CreateNew", (Object) null);
        super.onCancel(dialogInterface);
    }

    public boolean supportPopover() {
        return true;
    }
}
