package com.samsung.android.app.sdk.deepsky.barcode.action.abstraction;

import L2.a;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.app.sdk.deepsky.barcode.logger.LibLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.k;
import me.x;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH&¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH&¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u000eJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u00028\u0004X\u0004¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "getTitleId", "()I", "Landroid/content/Intent;", "getIntent", "()Landroid/content/Intent;", "", "getActionId", "()Ljava/lang/String;", "getTag", "", "execute", "()Z", "appContext", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Action {
    private final Context appContext;

    public Action(Context context) {
        j.e(context, "context");
        Context applicationContext = context.getApplicationContext();
        j.d(applicationContext, "getApplicationContext(...)");
        this.appContext = applicationContext;
    }

    public boolean execute() {
        Object obj;
        try {
            this.appContext.startActivity(getIntent());
            obj = x.f4917a;
        } catch (Throwable th) {
            obj = a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 == null) {
            return true;
        }
        String tag = getTag();
        String message = a7.getMessage();
        LibLogger.i(tag, "execute failure " + message);
        return false;
    }

    public abstract String getActionId();

    public final Context getAppContext() {
        return this.appContext;
    }

    public abstract Intent getIntent();

    public String getTag() {
        return "Action";
    }

    public abstract int getTitleId();
}
