package Kd;

import D0.e;
import Dd.C0732c;
import Hf.M;
import If.c;
import P1.f;
import Qe.C0812b;
import Qe.C0819i;
import Qe.V;
import a.C0068a;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.samsung.context.sdk.samsunganalytics.internal.sender.d;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import ge.C1019e0;
import ge.C1031i0;
import ge.O0;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledFuture;
import kotlin.jvm.internal.j;
import og.k;
import tf.C1298b;
import tf.C1299c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements O0, c {
    public static a g;
    public boolean d;
    public Object e;
    public Object f;

    public a(Context context, boolean z) {
        if (z) {
            this.e = new e(context, 12);
        }
        G0.c cVar = new G0.c(7, false);
        cVar.e = new LinkedBlockingQueue(25);
        this.f = cVar;
        this.d = z;
    }

    public static a c(Context context, C0732c cVar) {
        if (g == null) {
            synchronized (a.class) {
                try {
                    if (g == null) {
                        if (k.f != 0) {
                            g = new a(context, false);
                        } else if (com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(context).getString("lgt", "").equals("rtb")) {
                            cVar.getClass();
                            g = new a(context, true);
                        } else {
                            g = new a(context, false);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return g;
    }

    public boolean a(M m, M m4) {
        boolean z = this.d;
        C0812b bVar = (C0812b) this.e;
        C0812b bVar2 = (C0812b) this.f;
        j.e(m, "c1");
        j.e(m4, "c2");
        if (m.equals(m4)) {
            return true;
        }
        C0819i g3 = m.g();
        C0819i g10 = m4.g();
        if (!(g3 instanceof V) || !(g10 instanceof V)) {
            return false;
        }
        C1298b bVar3 = new C1298b(bVar, bVar2);
        return C1299c.d.d((V) g3, (V) g10, z, bVar3);
    }

    public LinkedBlockingQueue b(int i2) {
        LinkedBlockingQueue linkedBlockingQueue;
        String str;
        boolean z = this.d;
        if (z) {
            if (z) {
                ((Ld.a) ((e) this.e).e).getWritableDatabase().delete("logs_v2", A.a.f("timestamp <= ", System.currentTimeMillis() - (((long) 5) * MediaApiContract.DAY_IN_MILLI)), (String[]) null);
            }
            if (i2 <= 0) {
                linkedBlockingQueue = ((e) this.e).W("select * from logs_v2");
            } else {
                e eVar = (e) this.e;
                eVar.getClass();
                linkedBlockingQueue = eVar.W("select * from logs_v2 LIMIT " + i2);
            }
        } else {
            linkedBlockingQueue = (LinkedBlockingQueue) ((G0.c) this.f).e;
        }
        if (!linkedBlockingQueue.isEmpty()) {
            StringBuilder sb2 = new StringBuilder("get log from ");
            if (this.d) {
                str = "Database ";
            } else {
                str = "Queue ";
            }
            sb2.append(str);
            sb2.append("(");
            sb2.append(linkedBlockingQueue.size());
            sb2.append(")");
            C0068a.e(sb2.toString());
        }
        return linkedBlockingQueue;
    }

    public void d(d dVar) {
        if (this.d) {
            ((e) this.e).M(dVar);
            return;
        }
        LinkedBlockingQueue linkedBlockingQueue = (LinkedBlockingQueue) ((G0.c) this.f).e;
        if (!linkedBlockingQueue.offer(dVar)) {
            C0068a.c("QueueManager", "queue size over. remove oldest log");
            linkedBlockingQueue.poll();
            linkedBlockingQueue.offer(dVar);
        }
    }

    public void e(ArrayList arrayList) {
        if (!arrayList.isEmpty() && this.d) {
            SQLiteDatabase writableDatabase = ((Ld.a) ((e) this.e).e).getWritableDatabase();
            writableDatabase.beginTransaction();
            try {
                int size = arrayList.size();
                int i2 = 0;
                while (size > 0) {
                    int i7 = 900;
                    if (size < 900) {
                        i7 = size;
                    }
                    int i8 = i2 + i7;
                    List subList = arrayList.subList(i2, i8);
                    writableDatabase.delete("logs_v2", ("_id IN(" + new String(new char[(subList.size() - 1)]).replaceAll("\u0000", "?,")) + "?)", (String[]) subList.toArray(new String[0]));
                    size -= i7;
                    i2 = i8;
                }
                arrayList.clear();
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
            } catch (Exception e7) {
                C0068a.K("failed to delete" + e7.getMessage());
                writableDatabase.endTransaction();
            } catch (Throwable th) {
                writableDatabase.endTransaction();
                throw th;
            }
        }
    }

    public void f(ScheduledFuture scheduledFuture) {
        synchronized (this.e) {
            try {
                if (!this.d) {
                    this.f = scheduledFuture;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void g(boolean z) {
        C1031i0 i0Var = (C1031i0) this.f;
        i0Var.k.execute(new V1.c(i0Var, (C1019e0) this.e, z, 1));
    }

    public void h(f fVar) {
        synchronized (this.e) {
            try {
                if (((ArrayDeque) this.f) == null) {
                    this.f = new ArrayDeque();
                }
                ((ArrayDeque) this.f).add(fVar);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0012, code lost:
        r1 = r2.e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0 = (P1.f) ((java.util.ArrayDeque) r2.f).poll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        if (r0 != null) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        r2.d = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0024, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0026, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0028, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0029, code lost:
        r0.a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x002e, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void i(P1.h r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.e
            monitor-enter(r0)
            java.lang.Object r1 = r2.f     // Catch:{ all -> 0x002f }
            java.util.ArrayDeque r1 = (java.util.ArrayDeque) r1     // Catch:{ all -> 0x002f }
            if (r1 == 0) goto L_0x0031
            boolean r1 = r2.d     // Catch:{ all -> 0x002f }
            if (r1 == 0) goto L_0x000e
            goto L_0x0031
        L_0x000e:
            r1 = 1
            r2.d = r1     // Catch:{ all -> 0x002f }
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
        L_0x0012:
            java.lang.Object r1 = r2.e
            monitor-enter(r1)
            java.lang.Object r0 = r2.f     // Catch:{ all -> 0x0026 }
            java.util.ArrayDeque r0 = (java.util.ArrayDeque) r0     // Catch:{ all -> 0x0026 }
            java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x0026 }
            P1.f r0 = (P1.f) r0     // Catch:{ all -> 0x0026 }
            if (r0 != 0) goto L_0x0028
            r3 = 0
            r2.d = r3     // Catch:{ all -> 0x0026 }
            monitor-exit(r1)     // Catch:{ all -> 0x0026 }
            return
        L_0x0026:
            r2 = move-exception
            goto L_0x002d
        L_0x0028:
            monitor-exit(r1)     // Catch:{ all -> 0x0026 }
            r0.a(r3)
            goto L_0x0012
        L_0x002d:
            monitor-exit(r1)     // Catch:{ all -> 0x0026 }
            throw r2
        L_0x002f:
            r2 = move-exception
            goto L_0x0033
        L_0x0031:
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return
        L_0x0033:
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: Kd.a.i(P1.h):void");
    }

    public a(Object obj) {
        this.e = obj;
    }
}
