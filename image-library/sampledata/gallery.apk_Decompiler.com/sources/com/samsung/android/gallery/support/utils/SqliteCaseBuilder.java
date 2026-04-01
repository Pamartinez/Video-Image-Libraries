package com.samsung.android.gallery.support.utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SqliteCaseBuilder {
    String as;
    String elseThen;
    ArrayList<String[]> list = new ArrayList<>();

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$build$0(String[] strArr) {
        return "WHEN " + strArr[0] + " THEN " + strArr[1];
    }

    public String build() {
        StringBuilder sb2 = new StringBuilder("(CASE");
        if (!this.list.isEmpty()) {
            sb2.append(" ");
            sb2.append((String) this.list.stream().map(new C0670h(26)).collect(Collectors.joining(" ")));
        }
        if (this.elseThen != null) {
            sb2.append(" ELSE ");
            sb2.append(this.elseThen);
        }
        sb2.append(" END)");
        if (this.as != null) {
            sb2.append(" AS ");
            sb2.append(this.as);
        }
        return sb2.toString();
    }

    public SqliteCaseBuilder elseThen(String str) {
        this.elseThen = str;
        return this;
    }

    public SqliteCaseBuilder whenThen(String str, String str2) {
        this.list.add(new String[]{str, str2});
        return this;
    }

    public SqliteCaseBuilder elseThen(int i2) {
        return elseThen(String.valueOf(i2));
    }

    public SqliteCaseBuilder whenThen(String str, int i2) {
        return whenThen(str, String.valueOf(i2));
    }
}
