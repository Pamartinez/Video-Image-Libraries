package com.samsung.android.gallery.app.ui.dialog.creature.relationship;

import C3.C0392b;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.dialog.CreateNameDialog;
import com.samsung.android.gallery.module.dialog.ErrorType;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditRelationShipDialog extends CreateNameDialog {
    private static final Character[] INVALID_CHARS = {'\\', '/', ':', '*', '?', '\"', '<', '>', '|', ','};
    private ArrayList<String> mExistNameList;
    private int mOperationType;

    private boolean checkNameExists(String str) {
        return getCurrentRelationshipNameList().stream().anyMatch(new C0392b(str, 29));
    }

    private ArrayList<String> getCurrentRelationshipNameList() {
        Bundle arguments;
        String string;
        if (!(this.mExistNameList != null || (arguments = getArguments()) == null || (string = arguments.getString("relationship_name_list")) == null)) {
            this.mExistNameList = new ArrayList<>(Arrays.asList(string.split("/")));
        }
        return this.mExistNameList;
    }

    private int getRenamePosition() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return BundleWrapper.getInt(arguments, Message.KEY_POSITION, -1);
        }
        return -1;
    }

    private ErrorType isDuplicatedName(String str) {
        if (!checkNameExists(str)) {
            return ErrorType.NONE;
        }
        Log.i(this.TAG, "name is exist or default name");
        if (!str.equals(this.mOrgName)) {
            return ErrorType.NAME_ALREADY_EXIST;
        }
        return ErrorType.DEFAULT_NAME;
    }

    private ErrorType isValidName(String str) {
        if (str.contains("\n")) {
            str = str.replaceAll("\n", " ");
        }
        if (str.equals(".") || str.equals("..")) {
            return ErrorType.UNABLE_TO_ADD_ITEM;
        }
        return ErrorType.NONE;
    }

    private void publishInternal(String str) {
        if (this.mOperationType == 0) {
            getBlackboard().post("command://event/RelationshipCustomNameAdded", new Object[]{str});
        } else {
            getBlackboard().post("command://event/RelationshipCustomNameChanged", new Object[]{str, Integer.valueOf(getRenamePosition())});
        }
    }

    public String getDefaultName() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return "";
        }
        String string = arguments.getString("name");
        this.mOrgName = string;
        this.mOperationType = TextUtils.isEmpty(string) ^ true ? 1 : 0;
        return this.mOrgName;
    }

    public String getHint() {
        return "";
    }

    public Character[] getInvalidChars() {
        return INVALID_CHARS;
    }

    public int getPositiveButtonResource() {
        return R.string.move_mode_create;
    }

    public String getTitle() {
        return getString(R.string.create_custom_field);
    }

    public ErrorType isValid(String str) {
        ErrorType isDuplicatedName = isDuplicatedName(str);
        if (!isDuplicatedName.isNone()) {
            return isDuplicatedName;
        }
        return isValidName(str);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/EditRelationshipName", (Object) null);
        super.onDismiss(dialogInterface);
    }

    public void publishData(String str) {
        publishInternal(str);
    }

    public void publishCancel() {
    }
}
