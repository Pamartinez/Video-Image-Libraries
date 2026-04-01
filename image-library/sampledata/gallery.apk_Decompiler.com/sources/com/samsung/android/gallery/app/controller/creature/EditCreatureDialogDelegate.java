package com.samsung.android.gallery.app.controller.creature;

import A9.b;
import Da.g;
import H.a;
import I3.C0403a;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.database.dal.mp.helper.PeopleApi;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditCreatureDialogDelegate {
    private final String TAG = getClass().getSimpleName();
    private AlertDialog mContactUnlinkDialog;
    private AlertDialog mMergeWithLinkedContactDialog;
    private AlertDialog mTagAnotherCreatureDialog;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$mergeFaceGroup$5(CreatureNameData creatureNameData, Consumer consumer) {
        String str;
        String findCreatureUuidByRawId = new PeopleApi().findCreatureUuidByRawId(creatureNameData.getContactRawId());
        if (!TextUtils.isEmpty(findCreatureUuidByRawId)) {
            String str2 = this.TAG;
            if (TextUtils.isEmpty(findCreatureUuidByRawId)) {
                str = null;
            } else {
                str = findCreatureUuidByRawId.substring(0, Math.min(7, findCreatureUuidByRawId.length()));
            }
            Log.v(str2, "ContactLink mergeFaceGroup with original", str);
            creatureNameData.setCreatureUuid(findCreatureUuidByRawId);
        }
        ThreadUtil.postOnUiThread(new a(10, consumer, creatureNameData));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showMergeWithLinkedContactDialog$2(CreatureNameData creatureNameData, Consumer consumer, DialogInterface dialogInterface, int i2) {
        mergeFaceGroup(creatureNameData, consumer);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showMergeWithLinkedContactDialog$3(DialogInterface dialogInterface) {
        this.mMergeWithLinkedContactDialog = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showTagAnotherCreatureNameDialog$1(DialogInterface dialogInterface) {
        this.mTagAnotherCreatureDialog = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showUnlinkContactDialog$7(DialogInterface dialogInterface) {
        this.mContactUnlinkDialog = null;
    }

    private void mergeFaceGroup(CreatureNameData creatureNameData, Consumer<CreatureNameData> consumer) {
        if (!TextUtils.isEmpty(creatureNameData.getCreatureUuid())) {
            consumer.accept(creatureNameData);
        } else {
            SimpleThreadPool.getInstance().execute(new b(this, creatureNameData, consumer, 25));
        }
    }

    public boolean isShowing() {
        AlertDialog alertDialog = this.mTagAnotherCreatureDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            return true;
        }
        AlertDialog alertDialog2 = this.mMergeWithLinkedContactDialog;
        if (alertDialog2 != null && alertDialog2.isShowing()) {
            return true;
        }
        AlertDialog alertDialog3 = this.mContactUnlinkDialog;
        if (alertDialog3 == null || !alertDialog3.isShowing()) {
            return false;
        }
        return true;
    }

    public void showMergeWithLinkedContactDialog(Context context, String str, CreatureNameData creatureNameData, Consumer<CreatureNameData> consumer) {
        if (context != null) {
            AlertDialog create = new AlertDialog.Builder(context).setTitle((CharSequence) context.getString(R.string.tag_another_person_name_dialog_header, new Object[]{str})).setMessage((CharSequence) context.getString(R.string.tag_another_person_name_dialog_body, new Object[]{str, str})).setPositiveButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).setNegativeButton((int) R.string.merge_face_group, (DialogInterface.OnClickListener) new g(this, creatureNameData, consumer, 5)).setOnDismissListener(new I3.b(this, 2)).create();
            this.mMergeWithLinkedContactDialog = create;
            create.show();
        }
    }

    public void showTagAnotherCreatureNameDialog(Context context, String str, Runnable runnable) {
        if (context != null) {
            AlertDialog create = new AlertDialog.Builder(context).setTitle((CharSequence) context.getString(R.string.tag_another_person_name_dialog_header, new Object[]{str})).setMessage((CharSequence) context.getString(R.string.tag_another_person_name_dialog_body, new Object[]{str, str})).setPositiveButton((int) R.string.tag, (DialogInterface.OnClickListener) new C0403a(runnable, 1)).setNegativeButton((int) R.string.rename_people, (DialogInterface.OnClickListener) null).setOnDismissListener(new I3.b(this, 1)).create();
            this.mTagAnotherCreatureDialog = create;
            create.show();
        }
    }

    public void showUnlinkContactDialog(Context context, int i2, Runnable runnable) {
        if (context != null) {
            AlertDialog create = new AlertDialog.Builder(context).setTitle((CharSequence) context.getString(R.string.unlink_form_contact_q)).setMessage((CharSequence) context.getString(i2)).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) new C0403a(runnable, 0)).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).setOnDismissListener(new I3.b(this, 0)).create();
            this.mContactUnlinkDialog = create;
            create.show();
        }
    }
}
