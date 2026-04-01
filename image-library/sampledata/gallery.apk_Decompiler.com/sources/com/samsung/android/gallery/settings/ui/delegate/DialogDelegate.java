package com.samsung.android.gallery.settings.ui.delegate;

import Da.g;
import Fa.C0558l;
import Ga.a;
import Ga.b;
import Ga.c;
import Ga.d;
import N2.j;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.DropDownPreference;
import androidx.preference.SwitchPreferenceCompat;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.module.settings.MiscSettingPreference;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.module.store.MarketHelper;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.module.trash.helper.TrashUpdateTask;
import com.samsung.android.gallery.module.trash.support.TrashExternalLogger;
import com.samsung.android.gallery.settings.R$color;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.ui.abstaction.IBasePreferenceView;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.dialog.ChinaGdprDialog;
import com.samsung.android.gallery.widget.dialog.PlaceGdprDialog;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DialogDelegate {
    private final IBasePreferenceView mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDataUsageDialogButtonClickListener {
        void onNegativeClicked();

        void onPositiveClicked();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDialogListener {
        void onConfirmed(int i2);

        void onDismiss(DropDownPreference dropDownPreference, int i2);
    }

    public DialogDelegate(IBasePreferenceView iBasePreferenceView) {
        this.mView = iBasePreferenceView;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$showDataUsageDialog$1(OnDataUsageDialogButtonClickListener onDataUsageDialogButtonClickListener, DialogInterface dialogInterface, int i2) {
        MiscSettingPreference.AllowDataUsageForChn.setEnabled(true);
        onDataUsageDialogButtonClickListener.onPositiveClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showGdprDialog$4(Consumer consumer, Object obj, Bundle bundle) {
        Boolean bool = (Boolean) obj;
        boolean booleanValue = bool.booleanValue();
        SettingPreference.LocationAuth.setAndNotifyIfChanged(this.mView.getContext(), booleanValue);
        if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
            if (booleanValue) {
                MiscSettingPreference.AllowDataUsageForChn.setEnabled(true);
                MarketHelper.setUpgradeCheckRequired(true);
            } else {
                MiscSettingPreference miscSettingPreference = MiscSettingPreference.AllowDataUsageForChn;
                if (!miscSettingPreference.isEnabled()) {
                    miscSettingPreference.setEnabled(false);
                    MarketHelper.setUpgradeCheckRequired(false);
                }
            }
        }
        consumer.accept(bool);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showTrashTurnOffDialog$2(DialogInterface dialogInterface, int i2) {
        this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_TRASH_TURN_OFF_DIALOG_CANCEL.toString());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showTrashTurnOffDialog$3(SwitchPreferenceCompat switchPreferenceCompat, Context context, DialogInterface dialogInterface, int i2) {
        switchPreferenceCompat.setChecked(false);
        SettingPreference.Trash.setAndNotifyIfChanged(context, false);
        DebugLogger deleteInstance = DebugLogger.getDeleteInstance();
        deleteInstance.insertLog("[" + TrashLogType.TRASH_ENABLE + "][false][Setting]");
        this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_TRASH_TURN_OFF_DIALOG_TURN_OFF.toString());
        new TrashUpdateTask(context, TrashExternalLogger.Task.TRASH_OFF).execute(new Void[0]);
    }

    public void showDataUsageDialog(Context context, OnDataUsageDialogButtonClickListener onDataUsageDialogButtonClickListener) {
        if (context == null) {
            Log.e("DialogDelegate", "showDataUsageDialog failed. Context is null");
            return;
        }
        new AlertDialog.Builder(context).setTitle((CharSequence) context.getString(R$string.allow_to_use_network_connection_chn, new Object[]{context.getApplicationInfo().loadLabel(context.getPackageManager()).toString()})).setNegativeButton(R$string.deny, (DialogInterface.OnClickListener) new a(onDataUsageDialogButtonClickListener, 0)).setPositiveButton(R$string.allow, (DialogInterface.OnClickListener) new a(onDataUsageDialogButtonClickListener, 1)).create().show();
    }

    public void showGdprDialog(FragmentActivity fragmentActivity, Consumer<Boolean> consumer) {
        Fragment fragment;
        if (fragmentActivity == null || this.mView.isDestroyed()) {
            Log.d("DialogDelegate", "showGdprDialog failed. already destroyed");
            return;
        }
        this.mView.getBlackboard().subscribeOnUi("data://user/dialog/GDPRLocation", new d(0, this, consumer));
        try {
            if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
                fragment = new ChinaGdprDialog();
            } else {
                fragment = new PlaceGdprDialog();
            }
            fragmentActivity.getSupportFragmentManager().beginTransaction().add(fragment, CommandKey.DATA_REQUEST_REVERT("data://user/dialog/GDPRLocation")).commitAllowingStateLoss();
        } catch (IllegalStateException | NullPointerException e) {
            j.u(e, new StringBuilder("showGdprDialog failed e="), "DialogDelegate");
        }
    }

    public void showSuggestedContentsRuleConfirmDialog(DropDownPreference dropDownPreference, int i2, OnDialogListener onDialogListener) {
        int i7;
        int i8;
        boolean isEnabled = Features.isEnabled(Features.SUPPORT_PET_ON_AUTO_ALBUM);
        AlertDialog.Builder builder = new AlertDialog.Builder(dropDownPreference.getContext());
        if (isEnabled) {
            builder.setTitle(R$string.switch_contents_with_all_creatures_confirm_title);
        }
        if (isEnabled) {
            i7 = R$string.all_the_selected_creatures_confirm_message;
        } else {
            i7 = R$string.all_the_selected_people_confirm_message;
        }
        AlertDialog.Builder negativeButton = builder.setMessage(i7).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) null);
        if (isEnabled) {
            i8 = R$string.switch_button;
        } else {
            i8 = R$string.ok;
        }
        AlertDialog create = negativeButton.setPositiveButton(i8, (DialogInterface.OnClickListener) new b(onDialogListener, i2)).setOnDismissListener(new c(dropDownPreference, i2, onDialogListener)).create();
        create.show();
        create.getButton(-1).setTextColor(dropDownPreference.getContext().getColor(R$color.settings_value_text_color));
    }

    public void showTrashTurnOffDialog(Context context, SwitchPreferenceCompat switchPreferenceCompat) {
        AlertDialog create = new AlertDialog.Builder(context).setTitle(R$string.trash_turn_off_dialog).setMessage(R$string.trash_turn_off_dialog_message).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) new C0558l(3, this)).setPositiveButton(R$string.turn_off, (DialogInterface.OnClickListener) new g(this, switchPreferenceCompat, context)).create();
        create.show();
        Utils.setPermanentDeleteButtonTextColor(create);
    }
}
