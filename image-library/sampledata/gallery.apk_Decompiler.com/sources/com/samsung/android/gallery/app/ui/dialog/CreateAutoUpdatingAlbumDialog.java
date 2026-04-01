package com.samsung.android.gallery.app.ui.dialog;

import C3.C0392b;
import android.content.DialogInterface;
import android.os.Bundle;
import com.samsung.android.gallery.module.dialog.ErrorType;
import com.samsung.android.sum.core.types.NumericEnum;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.Arrays;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateAutoUpdatingAlbumDialog extends CreateNameDialog {
    private String[] getAutoAlbumTitles() {
        String str;
        Bundle arguments = getArguments();
        if (arguments != null) {
            str = arguments.getString("autoAlbumTitles");
        } else {
            str = null;
        }
        if (str != null) {
            return str.split(NumericEnum.SEP);
        }
        return new String[0];
    }

    private void publishInternal(String str) {
        getBlackboard().post("data://user/dialog/AutoUpdatingAlbumName", new Object[]{str, Boolean.TRUE});
    }

    public String getDefaultName() {
        String str;
        Bundle arguments = getArguments();
        if (arguments != null) {
            str = arguments.getString("title");
        } else {
            str = null;
        }
        if (str != null) {
            return str;
        }
        String string = getString(R.string.auto_album);
        String[] autoAlbumTitles = getAutoAlbumTitles();
        int i2 = 1;
        while (true) {
            StringBuilder t = C0212a.t(string, " ");
            t.append(String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(i2)}));
            String sb2 = t.toString();
            if (Arrays.stream(autoAlbumTitles).noneMatch(new C0392b(sb2, 23))) {
                return sb2;
            }
            i2++;
        }
    }

    public String getHint() {
        return getString(R.string.enter_album_name);
    }

    public int getLayoutId() {
        return R.layout.alert_dialog_create_album;
    }

    public String getTitle() {
        return getString(R.string.name_your_auto_album);
    }

    public ErrorType isValid(String str) {
        if (str.contains("\n")) {
            str = str.replaceAll("\n", " ");
        }
        if (str.equals(".") || str.equals("..")) {
            return ErrorType.UNABLE_TO_CREATE_ALBUM;
        }
        return ErrorType.NONE;
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/AutoUpdatingAlbumName", (Object) null);
    }

    public void publishData(String str) {
        publishInternal(str);
    }

    public void publishCancel() {
    }
}
