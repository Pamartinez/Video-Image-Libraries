package Jd;

import A0.l;
import Dd.C0731b;
import Dd.C0732c;
import Od.d;
import a.C0068a;
import android.content.ContentValues;
import android.content.Context;
import android.os.Trace;
import android.text.TextUtils;
import be.C0913a;
import be.C0915c;
import com.samsung.context.sdk.samsunganalytics.internal.sender.a;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import o1.C0246a;
import og.k;
import t1.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends a {
    public final b e;
    public boolean f = false;
    public int g = 0;

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Object, Jd.b] */
    public c(Context context, C0732c cVar) {
        super(context, cVar);
        if (k.f == 2) {
            G0.c cVar2 = new G0.c(5, (Object) this);
            ? obj = new Object();
            obj.f3473a = false;
            obj.b = false;
            obj.f3474c = context;
            obj.e = new a(obj, cVar2);
            this.e = obj;
            obj.a();
        }
    }

    public final int c(Map map) {
        Trace.beginSection("DMALogSender send");
        if (k.f == 3) {
            ContentValues contentValues = new ContentValues();
            Context context = this.f4194a;
            boolean b0 = C0246a.b0(context);
            C0732c cVar = this.b;
            if (!b0) {
                C0246a.H(context, contentValues, cVar);
            } else if (!com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(context).getBoolean("sendCommonSuccess", false)) {
                f();
            }
            if (map.containsKey("pd")) {
                String str = (String) map.get("pd");
                if (!TextUtils.isEmpty(str)) {
                    contentValues.put("pd", str);
                }
                map.remove("pd");
            }
            if (map.containsKey("ps")) {
                String str2 = (String) map.get("ps");
                if (!TextUtils.isEmpty(str2)) {
                    contentValues.put("ps", str2);
                }
                map.remove("ps");
            }
            boolean parseBoolean = Boolean.parseBoolean((String) map.remove("is"));
            cVar.getClass();
            contentValues.put("tcType", 0);
            contentValues.put("agree", Integer.valueOf(cVar.d.M() ? 1 : 0));
            contentValues.put("tid", cVar.f3331a);
            contentValues.put("logType", a.a(map).a());
            contentValues.put("timeStamp", Long.valueOf((String) map.get("ts")));
            d(map);
            contentValues.put("body", C0246a.f0(map, d.ONE_DEPTH));
            if (!C0246a.b0(context)) {
                contentValues.put("networkType", -1);
                contentValues.put("isSummary", Boolean.valueOf(parseBoolean));
            }
            d dVar = new d(context, 2, contentValues);
            this.d.getClass();
            i.d(dVar);
        } else {
            b bVar = this.e;
            if (bVar.f3473a) {
                Trace.endSection();
                return -8;
            } else if (this.g != 0) {
                Trace.endSection();
                return this.g;
            } else {
                b(map);
                if (!bVar.b) {
                    bVar.a();
                } else if (((C0915c) bVar.d) != null) {
                    e();
                    if (this.f) {
                        f();
                        this.f = false;
                    }
                }
            }
        }
        Trace.endSection();
        return this.g;
    }

    public final Map d(Map map) {
        map.put("tz", String.valueOf(C0246a.Y()));
        return map;
    }

    public final void e() {
        if (k.f == 2 && this.g == 0) {
            LinkedBlockingQueue b = this.f4195c.b(0);
            while (!b.isEmpty()) {
                l lVar = new l(3);
                lVar.e = (com.samsung.context.sdk.samsunganalytics.internal.sender.d) b.poll();
                lVar.f = (C0915c) this.e.d;
                lVar.g = this.b;
                this.d.getClass();
                i.d(lVar);
            }
        }
    }

    public final void f() {
        Trace.beginSection("DMALogSender sendCommon");
        C0732c cVar = this.b;
        cVar.getClass();
        String str = cVar.f3331a;
        HashMap hashMap = new HashMap();
        Context context = this.f4194a;
        hashMap.put("av", C0068a.G(context));
        hashMap.put("uv", cVar.f3332c);
        hashMap.put("v", C0731b.b);
        d dVar = d.ONE_DEPTH;
        String f02 = C0246a.f0(hashMap, dVar);
        HashMap hashMap2 = new HashMap();
        String str2 = null;
        if (!TextUtils.isEmpty((CharSequence) null)) {
            hashMap2.put("auid", (Object) null);
            hashMap2.put("at", String.valueOf(cVar.e));
            str2 = C0246a.f0(hashMap2, dVar);
        }
        if (k.f == 3) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("tcType", 0);
            contentValues.put("tid", str);
            contentValues.put("data", f02);
            contentValues.put("did", str2);
            d dVar2 = new d(context, 1, contentValues);
            this.d.getClass();
            i.d(dVar2);
        } else {
            try {
                this.g = ((C0913a) ((C0915c) this.e.d)).a(str, f02, str2);
            } catch (Exception e7) {
                C0068a.K("failed to send app common" + e7.getMessage());
                this.g = -9;
            }
        }
        Trace.endSection();
    }
}
