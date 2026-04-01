package com.samsung.android.gallery.module.debugger;

import N2.j;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MemoryUtils;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LeakTracker {
    private static final LeakTracHandler handler = new LeakTracHandler(ThreadUtil.createBackgroundThreadLooper("LeakTracker"));
    /* access modifiers changed from: private */
    public static final ArrayList<MemInfo> sHistory = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LeakTracHandler extends Handler {
        public LeakTracHandler(Looper looper) {
            super(looper);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$checkViewerBitmap$0(String str) {
            if (str.contains("viewer") || str.contains("slideshow") || str.contains("location://story/albums/storyHighlight") || str.contains("location://story/albums/spotify")) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$checkViewerBitmap$1(AtomicBoolean atomicBoolean, String str) {
            if ((str.contains("data://bitmap/viewer") || str.contains("data://viewer_ppp_bitmap/") || str.contains("data://viewer_first_bitmap")) && !str.contains("module.debugger") && !str.contains("[NULL]")) {
                Log.e("LeakTracker", "viewer bitmap or it's listener may be leaked : ".concat(str));
                atomicBoolean.set(true);
            }
        }

        /* JADX WARNING: type inference failed for: r0v3, types: [java.util.function.Consumer, java.lang.Object] */
        public void checkActivity(Message message) {
            MemInfo c5 = LeakTracker.execMemInfo((String) message.obj);
            Log.d("LeakTracker", c5.toString());
            if (c5.activities > 4) {
                String dumpHprof = MemoryUtils.dumpHprof();
                Log.e("LeakTracker", " =========== too many activities : " + c5.activities + " ========");
                Blackboard.getActivityBlackboardList().forEach(new Object());
                synchronized (LeakTracker.sHistory) {
                    try {
                        Iterator it = LeakTracker.sHistory.iterator();
                        while (it.hasNext()) {
                            Log.e("LeakTracker", ((MemInfo) it.next()).dump());
                        }
                        LeakTracker.sHistory.clear();
                    } catch (Throwable th) {
                        while (true) {
                            throw th;
                        }
                    }
                }
                if (dumpHprof != null) {
                    throw new AssertionError("Context Leak(Memory Leak) detected. Please check heap dump file at ".concat(dumpHprof));
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:17:0x011d, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void checkPss(android.os.Message r12) {
            /*
                r11 = this;
                java.lang.Object r12 = r12.obj
                java.lang.String r12 = (java.lang.String) r12
                com.samsung.android.gallery.module.debugger.LeakTracker$MemInfo r12 = com.samsung.android.gallery.module.debugger.LeakTracker.execMemInfo(r12)
                java.lang.String r0 = "LeakTracker"
                java.lang.String r12 = r12.toString()
                com.samsung.android.gallery.support.utils.Log.d(r0, r12)
                java.util.ArrayList r12 = com.samsung.android.gallery.module.debugger.LeakTracker.sHistory
                monitor-enter(r12)
                java.util.ArrayList r0 = com.samsung.android.gallery.module.debugger.LeakTracker.sHistory     // Catch:{ all -> 0x0024 }
                int r0 = r0.size()     // Catch:{ all -> 0x0024 }
                r1 = 10
                if (r0 >= r1) goto L_0x0027
                monitor-exit(r12)     // Catch:{ all -> 0x0024 }
                return
            L_0x0024:
                r11 = move-exception
                goto L_0x011e
            L_0x0027:
                java.util.ArrayList r1 = com.samsung.android.gallery.module.debugger.LeakTracker.sHistory     // Catch:{ all -> 0x0024 }
                r2 = 3
                java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0024 }
                com.samsung.android.gallery.module.debugger.LeakTracker$MemInfo r1 = (com.samsung.android.gallery.module.debugger.LeakTracker.MemInfo) r1     // Catch:{ all -> 0x0024 }
                java.util.ArrayList r3 = com.samsung.android.gallery.module.debugger.LeakTracker.sHistory     // Catch:{ all -> 0x0024 }
                int r4 = r0 + -1
                java.lang.Object r3 = r3.get(r4)     // Catch:{ all -> 0x0024 }
                com.samsung.android.gallery.module.debugger.LeakTracker$MemInfo r3 = (com.samsung.android.gallery.module.debugger.LeakTracker.MemInfo) r3     // Catch:{ all -> 0x0024 }
                int r4 = r3.pss     // Catch:{ all -> 0x0024 }
                int r5 = r1.pss     // Catch:{ all -> 0x0024 }
                int r4 = r4 - r5
                r5 = 100000(0x186a0, float:1.4013E-40)
                if (r4 <= r5) goto L_0x011c
                java.util.StringJoiner r5 = new java.util.StringJoiner     // Catch:{ all -> 0x0024 }
                java.lang.String r6 = ","
                r5.<init>(r6)     // Catch:{ all -> 0x0024 }
                java.util.StringJoiner r6 = new java.util.StringJoiner     // Catch:{ all -> 0x0024 }
                java.lang.String r7 = ","
                r6.<init>(r7)     // Catch:{ all -> 0x0024 }
                java.util.ArrayList r7 = com.samsung.android.gallery.module.debugger.LeakTracker.sHistory     // Catch:{ all -> 0x0024 }
                java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x0024 }
            L_0x005e:
                boolean r8 = r7.hasNext()     // Catch:{ all -> 0x0024 }
                if (r8 == 0) goto L_0x0097
                java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x0024 }
                com.samsung.android.gallery.module.debugger.LeakTracker$MemInfo r8 = (com.samsung.android.gallery.module.debugger.LeakTracker.MemInfo) r8     // Catch:{ all -> 0x0024 }
                java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0024 }
                r9.<init>()     // Catch:{ all -> 0x0024 }
                int r10 = r8.pss     // Catch:{ all -> 0x0024 }
                r9.append(r10)     // Catch:{ all -> 0x0024 }
                java.lang.String r10 = ""
                r9.append(r10)     // Catch:{ all -> 0x0024 }
                java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0024 }
                r5.add(r9)     // Catch:{ all -> 0x0024 }
                java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0024 }
                r9.<init>()     // Catch:{ all -> 0x0024 }
                int r8 = r8.views     // Catch:{ all -> 0x0024 }
                r9.append(r8)     // Catch:{ all -> 0x0024 }
                java.lang.String r8 = ""
                r9.append(r8)     // Catch:{ all -> 0x0024 }
                java.lang.String r8 = r9.toString()     // Catch:{ all -> 0x0024 }
                r6.add(r8)     // Catch:{ all -> 0x0024 }
                goto L_0x005e
            L_0x0097:
                int r0 = r0 - r2
                float r7 = (float) r4     // Catch:{ all -> 0x0024 }
                float r8 = (float) r0     // Catch:{ all -> 0x0024 }
                float r7 = r7 / r8
                int r3 = r3.views     // Catch:{ all -> 0x0024 }
                int r1 = r1.views     // Catch:{ all -> 0x0024 }
                int r3 = r3 - r1
                float r1 = (float) r3     // Catch:{ all -> 0x0024 }
                float r1 = r1 / r8
                java.lang.String r8 = "LeakTracker"
                java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0024 }
                r9.<init>()     // Catch:{ all -> 0x0024 }
                java.lang.String r10 = "pss history : "
                r9.append(r10)     // Catch:{ all -> 0x0024 }
                java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0024 }
                r9.append(r5)     // Catch:{ all -> 0x0024 }
                java.lang.String r5 = r9.toString()     // Catch:{ all -> 0x0024 }
                com.samsung.android.gallery.support.utils.Log.w(r8, r5)     // Catch:{ all -> 0x0024 }
                java.lang.String r5 = "LeakTracker"
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0024 }
                r8.<init>()     // Catch:{ all -> 0x0024 }
                java.lang.String r9 = "view history : "
                r8.append(r9)     // Catch:{ all -> 0x0024 }
                java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0024 }
                r8.append(r6)     // Catch:{ all -> 0x0024 }
                java.lang.String r6 = r8.toString()     // Catch:{ all -> 0x0024 }
                com.samsung.android.gallery.support.utils.Log.w(r5, r6)     // Catch:{ all -> 0x0024 }
                java.lang.String r5 = "LeakTracker"
                java.lang.String r6 = "pss increase : %d / %d = %.3f"
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0024 }
                java.lang.Integer r8 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0024 }
                java.lang.Float r7 = java.lang.Float.valueOf(r7)     // Catch:{ all -> 0x0024 }
                java.lang.Object[] r4 = new java.lang.Object[]{r4, r8, r7}     // Catch:{ all -> 0x0024 }
                java.lang.String r4 = java.lang.String.format(r6, r4)     // Catch:{ all -> 0x0024 }
                com.samsung.android.gallery.support.utils.Log.w(r5, r4)     // Catch:{ all -> 0x0024 }
                java.lang.String r4 = "LeakTracker"
                java.lang.String r5 = "view increase : %d / %d = %.3f"
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0024 }
                java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0024 }
                java.lang.Float r1 = java.lang.Float.valueOf(r1)     // Catch:{ all -> 0x0024 }
                java.lang.Object[] r0 = new java.lang.Object[]{r3, r0, r1}     // Catch:{ all -> 0x0024 }
                java.lang.String r0 = java.lang.String.format(r5, r0)     // Catch:{ all -> 0x0024 }
                com.samsung.android.gallery.support.utils.Log.w(r4, r0)     // Catch:{ all -> 0x0024 }
                android.os.Message r0 = r11.obtainMessage(r2)     // Catch:{ all -> 0x0024 }
                r11.sendMessage(r0)     // Catch:{ all -> 0x0024 }
                java.util.ArrayList r11 = com.samsung.android.gallery.module.debugger.LeakTracker.sHistory     // Catch:{ all -> 0x0024 }
                r11.clear()     // Catch:{ all -> 0x0024 }
            L_0x011c:
                monitor-exit(r12)     // Catch:{ all -> 0x0024 }
                return
            L_0x011e:
                monitor-exit(r12)     // Catch:{ all -> 0x0024 }
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.debugger.LeakTracker.LeakTracHandler.checkPss(android.os.Message):void");
        }

        /* JADX WARNING: type inference failed for: r0v1, types: [java.util.function.Predicate, java.lang.Object] */
        public void checkViewerBitmap(Message message) {
            Stack stack;
            Blackboard blackboard = (Blackboard) message.obj;
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            if (blackboard != null) {
                blackboard.dump("", printWriter);
                stack = (Stack) blackboard.read("debug://locationKeyStack");
            } else {
                stack = null;
            }
            if (stack != null && !stack.stream().anyMatch(new Object())) {
                AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                Arrays.stream(stringWriter.toString().split("\n")).forEach(new c(atomicBoolean));
                if (atomicBoolean.get() && !Debug.isDebuggerConnected()) {
                    throw new AssertionError("there is no viewer in stack, but viewer bitmap or it's listener remained in black board");
                }
            }
        }

        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 3) {
                MemoryUtils.dumpHprof();
            } else if (i2 == 2) {
                System.gc();
            } else if (i2 == 0) {
                checkActivity(message);
            } else if (i2 == 1) {
                checkPss(message);
            } else if (i2 == 4) {
                checkViewerBitmap(message);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MemInfo {
        int activities = -1;
        String lastActivity;
        int pss = -1;
        String s;
        long time;
        int views = -1;

        public MemInfo(String str) {
            this.lastActivity = str;
            this.time = System.currentTimeMillis();
        }

        public String dump() {
            StringBuilder sb2 = new StringBuilder("MemData{");
            sb2.append(TimeUtil.getLocalizedTime(this.time));
            sb2.append(", lastActivity=");
            sb2.append(this.lastActivity);
            sb2.append(", activities=");
            sb2.append(this.activities);
            sb2.append(", views=");
            sb2.append(this.views);
            sb2.append(", pss=");
            return j.e(sb2, this.pss, '}');
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("MemData{");
            sb2.append(TimeUtil.getLocalizedTime(this.time));
            sb2.append(", activities=");
            sb2.append(this.activities);
            sb2.append(", views=");
            sb2.append(this.views);
            sb2.append(", pss=");
            return j.e(sb2, this.pss, '}');
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00d2, code lost:
        if (r3 == null) goto L_0x00d5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.samsung.android.gallery.module.debugger.LeakTracker.MemInfo execMemInfo(java.lang.String r10) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.samsung.android.gallery.module.debugger.LeakTracker$MemInfo r1 = new com.samsung.android.gallery.module.debugger.LeakTracker$MemInfo
            r1.<init>(r10)
            java.lang.String r10 = "/system/bin/sh"
            java.lang.String r2 = "-c"
            java.lang.String r3 = "dumpsys meminfo com.sec.android.gallery3d"
            java.lang.String[] r10 = new java.lang.String[]{r10, r2, r3}
            r2 = 0
            r3 = 0
            java.lang.Runtime r4 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x00c4 }
            java.lang.Process r3 = r4.exec(r10)     // Catch:{ IOException -> 0x00c4 }
            java.io.BufferedReader r10 = new java.io.BufferedReader     // Catch:{ IOException -> 0x00c4 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x00c4 }
            java.io.InputStream r5 = r3.getInputStream()     // Catch:{ IOException -> 0x00c4 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x00c4 }
            r10.<init>(r4)     // Catch:{ IOException -> 0x00c4 }
        L_0x002c:
            java.lang.String r4 = r10.readLine()     // Catch:{ all -> 0x0063 }
            if (r4 == 0) goto L_0x00bb
            r0.append(r4)     // Catch:{ all -> 0x0063 }
            java.lang.String r5 = "\n"
            r0.append(r5)     // Catch:{ all -> 0x0063 }
            java.lang.String r5 = "\\s+"
            java.lang.String[] r4 = r4.split(r5)     // Catch:{ all -> 0x0063 }
            int r5 = r4.length     // Catch:{ all -> 0x0063 }
            r6 = 2
            r7 = 1
            r8 = 3
            if (r5 <= r8) goto L_0x0065
            r5 = r4[r7]     // Catch:{ all -> 0x0063 }
            java.lang.String r9 = "TOTAL"
            boolean r5 = r5.equals(r9)     // Catch:{ all -> 0x0063 }
            if (r5 == 0) goto L_0x0065
            r5 = r4[r6]     // Catch:{ all -> 0x0063 }
            java.lang.String r9 = "PSS:"
            boolean r5 = r5.equals(r9)     // Catch:{ all -> 0x0063 }
            if (r5 == 0) goto L_0x0065
            r4 = r4[r8]     // Catch:{ all -> 0x0063 }
            int r4 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r4, r2)     // Catch:{ all -> 0x0063 }
            r1.pss = r4     // Catch:{ all -> 0x0063 }
            goto L_0x002c
        L_0x0063:
            r4 = move-exception
            goto L_0x00c6
        L_0x0065:
            int r5 = r4.length     // Catch:{ all -> 0x0063 }
            if (r5 <= r8) goto L_0x007b
            r5 = r4[r7]     // Catch:{ all -> 0x0063 }
            java.lang.String r7 = "Views:"
            boolean r5 = r5.equals(r7)     // Catch:{ all -> 0x0063 }
            if (r5 == 0) goto L_0x007b
            r4 = r4[r6]     // Catch:{ all -> 0x0063 }
            int r4 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r4, r2)     // Catch:{ all -> 0x0063 }
            r1.views = r4     // Catch:{ all -> 0x0063 }
            goto L_0x002c
        L_0x007b:
            int r5 = r4.length     // Catch:{ all -> 0x0063 }
            if (r5 <= r8) goto L_0x002c
            r5 = r4[r8]     // Catch:{ all -> 0x0063 }
            java.lang.String r6 = "Activities:"
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0063 }
            if (r5 == 0) goto L_0x002c
            r5 = 4
            r4 = r4[r5]     // Catch:{ all -> 0x0063 }
            int r4 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r4, r2)     // Catch:{ all -> 0x0063 }
            r1.activities = r4     // Catch:{ all -> 0x0063 }
            java.util.ArrayList r4 = com.samsung.android.gallery.support.blackboard.Blackboard.getActivityBlackboardList()     // Catch:{ all -> 0x0063 }
            java.util.stream.Stream r4 = r4.stream()     // Catch:{ all -> 0x0063 }
            V8.a r5 = new V8.a     // Catch:{ all -> 0x0063 }
            r6 = 19
            r5.<init>(r6)     // Catch:{ all -> 0x0063 }
            java.util.stream.Stream r4 = r4.map(r5)     // Catch:{ all -> 0x0063 }
            S3.d r5 = new S3.d     // Catch:{ all -> 0x0063 }
            r6 = 20
            r5.<init>(r6)     // Catch:{ all -> 0x0063 }
            java.util.stream.Stream r4 = r4.filter(r5)     // Catch:{ all -> 0x0063 }
            long r4 = r4.count()     // Catch:{ all -> 0x0063 }
            int r4 = (int) r4     // Catch:{ all -> 0x0063 }
            int r5 = r1.activities     // Catch:{ all -> 0x0063 }
            int r5 = r5 - r4
            r1.activities = r5     // Catch:{ all -> 0x0063 }
            goto L_0x002c
        L_0x00bb:
            r10.close()     // Catch:{ IOException -> 0x00c4 }
        L_0x00be:
            r3.destroy()
            goto L_0x00d5
        L_0x00c2:
            r10 = move-exception
            goto L_0x00f9
        L_0x00c4:
            r10 = move-exception
            goto L_0x00cf
        L_0x00c6:
            r10.close()     // Catch:{ all -> 0x00ca }
            goto L_0x00ce
        L_0x00ca:
            r10 = move-exception
            r4.addSuppressed(r10)     // Catch:{ IOException -> 0x00c4 }
        L_0x00ce:
            throw r4     // Catch:{ IOException -> 0x00c4 }
        L_0x00cf:
            r10.printStackTrace()     // Catch:{ all -> 0x00c2 }
            if (r3 == 0) goto L_0x00d5
            goto L_0x00be
        L_0x00d5:
            java.lang.String r10 = r0.toString()
            r1.s = r10
            java.util.ArrayList<com.samsung.android.gallery.module.debugger.LeakTracker$MemInfo> r10 = sHistory
            monitor-enter(r10)
            int r0 = r10.size()     // Catch:{ all -> 0x00f0 }
            r3 = 999(0x3e7, float:1.4E-42)
            if (r0 <= r3) goto L_0x00f2
            r0 = 100
            java.util.List r0 = r10.subList(r2, r0)     // Catch:{ all -> 0x00f0 }
            r0.clear()     // Catch:{ all -> 0x00f0 }
            goto L_0x00f2
        L_0x00f0:
            r0 = move-exception
            goto L_0x00f7
        L_0x00f2:
            r10.add(r1)     // Catch:{ all -> 0x00f0 }
            monitor-exit(r10)     // Catch:{ all -> 0x00f0 }
            return r1
        L_0x00f7:
            monitor-exit(r10)     // Catch:{ all -> 0x00f0 }
            throw r0
        L_0x00f9:
            if (r3 == 0) goto L_0x00fe
            r3.destroy()
        L_0x00fe:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.debugger.LeakTracker.execMemInfo(java.lang.String):com.samsung.android.gallery.module.debugger.LeakTracker$MemInfo");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$execMemInfo$0(String str) {
        if (str.startsWith("com.samsung.android.gallery.devtool.") || str.startsWith("com.samsung.android.gallery.plugins.")) {
            return true;
        }
        return false;
    }

    public static void resetCheckViewerBitmap() {
    }

    public static void checkViewerBitmap(Blackboard blackboard) {
    }
}
