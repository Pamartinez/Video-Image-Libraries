package com.samsung.android.gallery.module.commandline.ops;

import Ba.g;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.window.embedding.c;
import bc.d;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.module.debugger.BugReporter;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.scsp.common.ContentType;
import i.C0212a;
import z2.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DbDump implements CommandOperator {
    r mSnackbar;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$operate$1(Context context) {
        ThreadUtil.postOnUiThread(new d((Object) this, (Object) context, (Object) new BugReporter(context).archiveLogs(true), 7));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$showSnackbar$2(Context context, String str, View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", FileProviderUtil.getUri(context, str));
        intent.setType(ContentType.OCTET_STREAM);
        context.startActivity(Intent.createChooser(intent, "Share dump file"));
    }

    public Bundle operate(Command command, Context context, String[] strArr) {
        r j2 = r.j(((Activity) context).getWindow().getDecorView(), -2, 0, false, "It takes time to collect database. please wait");
        this.mSnackbar = j2;
        j2.l();
        SimpleThreadPool.getInstance().execute(new c(20, this, context));
        return null;
    }

    /* renamed from: showSnackbar */
    public void lambda$operate$0(Context context, String str) {
        r rVar = this.mSnackbar;
        if (rVar != null) {
            rVar.a(3);
            this.mSnackbar = null;
        }
        r j2 = r.j(((Activity) context).getWindow().getDecorView(), 0, 0, false, C0212a.m("Dump completed\n\n", str.replace(FileUtils.EXTERNAL_STORAGE_DIR, "/Internal storage"), "\n\nDo you want to share dump file?"));
        j2.k("Share", new g(19, context, str));
        j2.l();
    }
}
