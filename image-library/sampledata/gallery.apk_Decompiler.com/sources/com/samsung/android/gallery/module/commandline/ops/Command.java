package com.samsung.android.gallery.module.commandline.ops;

import W.a;
import android.content.Context;
import android.os.Bundle;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum Command {
    SET_VIEWER_ENTER_VI_DURATION("sevd", new ConfigureTestValue()),
    GUI_DUMP("guidump", new GuiDump()),
    DB_DUMP("*#9900#debugdbdump", new DbDump()),
    ENABLE_LABS_ALL("*#9900#debuglabs#all", new EnableLabsAll()),
    UX_TUNE("*#9900#debuguxtune", new ApplyUxTune()),
    ALBUM_BACKUP_AND_RESTORE("*#0911#func#albumbnr", new AlbumBnr()),
    APPLY_PREFERENCE("*#9900#debug#", new ApplyPreference()),
    __END__("__END__", new a(19));
    
    public final String cmd;
    final CommandOperator operator;
    String value;

    private Command(String str, CommandOperator commandOperator) {
        this.cmd = str.toLowerCase(Locale.ROOT);
        this.operator = commandOperator;
    }

    public Bundle operate(Context context, String[] strArr) {
        return this.operator.operate(this, context, strArr);
    }

    public void set(String str) {
        this.value = str;
    }
}
