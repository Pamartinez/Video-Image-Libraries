package Nd;

import D1.f;
import Dd.C0732c;
import a.C0068a;
import android.app.Application;
import android.content.ContentValues;
import android.net.Uri;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends f {
    public final /* synthetic */ String g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ long f3539h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ D0.f f3540i;

    public a(D0.f fVar, String str, long j2) {
        this.f3540i = fVar;
        this.g = str;
        this.f3539h = j2;
    }

    public final void Q(String str, String str2, String str3) {
        ((Application) this.f3540i.e).getSharedPreferences(c.D("SATerms"), 0).edit().putLong(this.g, this.f3539h).apply();
        R(false);
    }

    public final void R(boolean z) {
        D0.f fVar = this.f3540i;
        Application application = (Application) fVar.e;
        if (910701000 <= C0068a.x(application.getApplicationContext())) {
            Uri parse = Uri.parse("content://com.sec.android.log.diagmonagent.sa/registrationHistory");
            ContentValues contentValues = new ContentValues();
            contentValues.put("tid", ((C0732c) fVar.f).f3331a);
            contentValues.put("eventTimestamp", Long.valueOf(this.f3539h));
            contentValues.put("sendTimestamp", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("apiType", 11);
            contentValues.put("result", Boolean.valueOf(z));
            try {
                application.getApplicationContext().getContentResolver().insert(parse, contentValues);
            } catch (Exception e) {
                C0068a.K("Send registration result failed : " + e.getMessage());
            }
        }
    }
}
