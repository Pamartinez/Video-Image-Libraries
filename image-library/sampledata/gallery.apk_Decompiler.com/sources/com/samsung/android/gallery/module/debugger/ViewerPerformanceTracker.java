package com.samsung.android.gallery.module.debugger;

import C3.i;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.ocr.MOCRLang;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewerPerformanceTracker {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Offset {
        USER_EVENT((String) null, 0),
        VF_HQ(r3, 0),
        CF_PV_HQ(r3, 0),
        CF_PV(r3, 0),
        CF_PV_HQ_LOADED(r3, 0),
        CF_CREATE((String) null, 0),
        CF_RESUME(r4, 150),
        CF_TRANS_ENTER(r3, 100),
        VF_TRANS_END((String) null, 150),
        CF_TRANS_END((String) null, 0),
        CP_TRANS_END((String) null, 250);
        
        private long elapsed;
        private final long expectedMax;
        private final Offset from;
        /* access modifiers changed from: private */
        public long time;

        private Offset(Offset offset, long j2) {
            this.from = offset;
            this.expectedMax = j2;
        }

        public static String dumpAll(boolean z) {
            StringBuilder sb2 = new StringBuilder(MOCRLang.GURMUKHI);
            int i2 = 0;
            if (z) {
                Offset[] values = values();
                int length = values.length;
                while (i2 < length) {
                    sb2.append(values[i2].toString());
                    i2++;
                }
            } else {
                Offset[] values2 = values();
                int length2 = values2.length;
                while (i2 < length2) {
                    sb2.append(values2[i2].dumpIfNecessary());
                    i2++;
                }
            }
            if (sb2.length() > 0) {
                sb2.deleteCharAt(sb2.length() - 1);
            }
            return sb2.toString();
        }

        public static void resetAll() {
            for (Offset offset : values()) {
                offset.time = 0;
                offset.elapsed = 0;
            }
        }

        public void begin() {
            this.elapsed = System.currentTimeMillis();
        }

        public String dumpIfNecessary() {
            long j2;
            Offset offset = this.from;
            if (offset != null) {
                long j3 = this.time;
                if (j3 > 0) {
                    j2 = j3 - offset.time;
                } else {
                    j2 = -1;
                }
                if (j2 <= this.expectedMax) {
                    return "";
                }
                return name() + "(" + j2 + "),";
            } else if (this.elapsed <= this.expectedMax) {
                return "";
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(name());
                sb2.append("(");
                return C0212a.o(sb2, this.elapsed, "),");
            }
        }

        public void end() {
            this.elapsed = System.currentTimeMillis() - this.elapsed;
        }

        public void set() {
            this.time = System.currentTimeMillis();
        }

        public String toString() {
            long j2;
            if (this.from != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(name());
                sb2.append("(");
                long j3 = this.time;
                if (j3 > 0) {
                    j2 = j3 - this.from.time;
                } else {
                    j2 = -1;
                }
                return C0212a.o(sb2, j2, "),");
            } else if (this.elapsed <= 0) {
                return "";
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(name());
                sb3.append("(");
                return C0212a.o(sb3, this.elapsed, "),");
            }
        }
    }

    public static void beginTrace(Offset offset) {
        offset.begin();
    }

    public static void dump() {
        ThreadUtil.postOnBgThreadDelayed(new i(18), 300);
    }

    public static void endTrace(Offset offset) {
        offset.end();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$dump$0() {
        if (Offset.USER_EVENT.time > 0) {
            Log.i("ViewerPerformance", Offset.dumpAll(true));
            Offset.resetAll();
        }
    }

    public static void setTime(Offset offset) {
        offset.set();
    }
}
