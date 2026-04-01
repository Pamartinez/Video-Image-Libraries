package com.samsung.android.gallery.app.ui.viewer2.crop;

import A4.B;
import Ad.j;
import Fa.C0558l;
import I4.b;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import h2.t;
import java.util.Optional;
import k2.d;
import k2.q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CropMenuDelegate {
    private final CharSequence TAG;
    private Blackboard mBlackboard;
    private BottomNavigationView mBottomNavigationView;
    private AlertDialog mConfirmDialog;

    public CropMenuDelegate(CharSequence charSequence) {
        this.TAG = charSequence;
    }

    /* access modifiers changed from: private */
    public static WindowInsetsCompat lambda$initBottomNavigation$0(View view, WindowInsetsCompat windowInsetsCompat, t tVar) {
        int i2 = tVar.d - windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars()).bottom;
        tVar.d = i2;
        ViewCompat.setPaddingRelative(view, tVar.f1778a, tVar.b, tVar.f1779c, i2);
        return windowInsetsCompat;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setNavigationMenuTitle$1(String str, MenuItem menuItem) {
        menuItem.setTitle(str);
        menuItem.setContentDescription(str);
    }

    /* access modifiers changed from: private */
    public void onDialogClicked(DialogInterface dialogInterface, int i2) {
        Log.d(this.TAG, "onDialogClicked", Integer.valueOf(i2));
        if (i2 == -1) {
            saveCropChange();
        } else if (i2 == -2) {
            discardCropChange();
        }
        AlertDialog alertDialog = this.mConfirmDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.mConfirmDialog = null;
    }

    private void setNavigationMenuTitle(int i2, String str) {
        Optional.ofNullable(this.mBottomNavigationView.getMenu().findItem(i2)).ifPresent(new B(str, 9));
    }

    public void bindViewInternal(View view) {
        this.mBottomNavigationView = (BottomNavigationView) view.findViewById(R.id.edit_bottom_navigation);
    }

    public void discardCropChange() {
        Optional.ofNullable(this.mBlackboard).ifPresent(new b(6));
    }

    public void initBottomNavigation(String[] strArr, q qVar) {
        this.mBottomNavigationView.inflateMenu(R.menu.menu_crop_cancel_done);
        if (strArr != null) {
            setNavigationMenuTitle(R.id.menu_edit_app_bar_done, strArr[0]);
            String str = strArr[1];
            if (str != null) {
                setNavigationMenuTitle(R.id.menu_edit_app_bar_cancel, str);
            } else {
                d dVar = (d) this.mBottomNavigationView.findViewById(R.id.menu_edit_app_bar_cancel);
                if (dVar != null) {
                    ViewUtils.removeSelf(dVar);
                }
            }
        }
        ViewUtils.doOnApplyWindowInsets(this.mBottomNavigationView, new j(26));
        this.mBottomNavigationView.setOnItemSelectedListener(qVar);
    }

    public void saveCropChange() {
        Optional.ofNullable(this.mBlackboard).ifPresent(new b(7));
    }

    public void setBlackboard(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    public void showBottomNavigation() {
        ViewUtils.setVisibility(this.mBottomNavigationView, 0);
    }

    public void showConfirmDialog(Context context, boolean z) {
        if (!z || context == null) {
            discardCropChange();
            return;
        }
        AlertDialog create = new AlertDialog.Builder(context).setMessage(R.string.crop_back_key_confirm_dialog_body).setPositiveButton(R.string.save, new C0558l(5, this)).setNegativeButton(R.string.crop_back_key_confirm_dialog_discard, new C0558l(5, this)).setNeutralButton(R.string.cancel, new C0558l(5, this)).create();
        this.mConfirmDialog = create;
        create.show();
    }

    public void unbindView() {
        this.mConfirmDialog = null;
        this.mBottomNavigationView = null;
    }
}
