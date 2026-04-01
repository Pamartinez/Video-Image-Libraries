package com.samsung.android.gallery.support.trace;

import android.util.Log;
import c0.C0086a;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Stack;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Trace {
    private static final TraceCompat sTraceCompat = TraceFactory.create();
    private static long sTraceLogBaseTime = 0;
    private static final String[] sTraceLogBuffer = new String[5];
    private static int sTraceLogBufferIndex = 0;
    private static boolean sTraceLogEnabled = true;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TraceFactory {
        public static TraceCompat create() {
            return new TraceCompatProfileableImpl();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TraceLog {
        private long mLapTimeInNano;
        /* access modifiers changed from: private */
        public long mReferenceTime;
        private int mTraceCount;
        protected final TraceArray<Object[]> mTraceList = new TraceArray<>(512);

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class TraceArray<E> {
            private final Object LOCK = new Object();
            private final int capacity;
            private final transient Object[] data;
            int size;

            public TraceArray(int i2) {
                this.capacity = i2;
                this.data = new Object[(i2 + 32)];
            }

            public void add(E e) {
                int i2;
                synchronized (this.LOCK) {
                    i2 = this.size;
                    this.size = i2 + 1;
                }
                if (i2 < this.capacity) {
                    this.data[i2] = e;
                }
            }

            public void clear() {
                this.size = 0;
                Arrays.fill(this.data, (Object) null);
            }

            public void forEach(Consumer<E> consumer) {
                Object[] objArr = this.data;
                int i2 = this.size;
                for (int i7 = 0; i7 < i2; i7++) {
                    Object obj = objArr[i7];
                    if (obj != null) {
                        consumer.accept(obj);
                    }
                }
            }

            public int size() {
                return this.size;
            }
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class TraceData {
            static long reference;
            long begin;
            long end;
            String section;

            public TraceData(String str, long j2, long j3) {
                this.section = str;
                this.begin = j2;
                this.end = j3;
            }

            public String toString() {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(this.section);
                sb2.append("(");
                sb2.append(this.begin - reference);
                long j2 = this.end;
                String str = "-";
                if (j2 != 0) {
                    if (j2 == this.begin) {
                        str = ",0";
                    } else {
                        str = str + (this.end - reference) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + (this.end - this.begin);
                    }
                }
                return C0212a.p(sb2, str, ") ");
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ Stack lambda$toString$0(Long l) {
            return new Stack();
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ ArrayList lambda$toString$1(Long l) {
            return new ArrayList();
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ ArrayList lambda$toString$2(Long l) {
            return new ArrayList();
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$toString$3(HashMap hashMap, HashMap hashMap2, HashMap hashMap3, Object[] objArr) {
            TraceData traceData;
            Long l = objArr[0];
            l.longValue();
            String str = objArr[2];
            long longValue = objArr[3].longValue();
            long longValue2 = objArr[4].longValue();
            hashMap.putIfAbsent(l, objArr[1]);
            Stack stack = (Stack) hashMap2.computeIfAbsent(l, new d(0));
            if (str == null) {
                if (stack.isEmpty()) {
                    traceData = null;
                } else {
                    traceData = (TraceData) stack.pop();
                }
                if (traceData != null) {
                    traceData.end = longValue2;
                    ((ArrayList) hashMap3.computeIfAbsent(l, new d(1))).add(traceData);
                }
            } else if (longValue <= 0 || longValue2 <= 0) {
                stack.push(new TraceData(str, longValue, longValue2));
            } else {
                ((ArrayList) hashMap3.computeIfAbsent(l, new d(2))).add(new TraceData(str, longValue, longValue2));
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$toString$4(StringBuilder sb2, Stack stack) {
            Objects.requireNonNull(sb2);
            stack.forEach(new c(sb2, 0));
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$toString$5(StringBuilder sb2, HashMap hashMap, HashMap hashMap2, Long l, ArrayList arrayList) {
            sb2.append((String) hashMap.get(l));
            sb2.append("(#");
            sb2.append(l);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(arrayList.size());
            sb2.append(")");
            sb2.append("{");
            arrayList.forEach(new c(sb2, 0));
            Optional.ofNullable((Stack) hashMap2.get(l)).ifPresent(new c(sb2, 1));
            sb2.replace(sb2.length() - 1, sb2.length(), "}\n");
        }

        public void beginSection(String str) {
            int i2 = this.mTraceCount + 1;
            this.mTraceCount = i2;
            if (i2 < 512) {
                Thread currentThread = Thread.currentThread();
                this.mTraceList.add(new Object[]{Long.valueOf(currentThread.getId()), currentThread.getName(), str, Long.valueOf(System.currentTimeMillis()), 0L});
            }
        }

        public void endSection() {
            int i2 = this.mTraceCount + 1;
            this.mTraceCount = i2;
            if (i2 < 512) {
                Thread currentThread = Thread.currentThread();
                this.mTraceList.add(new Object[]{Long.valueOf(currentThread.getId()), currentThread.getName(), null, 0L, Long.valueOf(System.currentTimeMillis())});
            }
        }

        public String getTraceLog() {
            String str;
            try {
                str = toString();
            } catch (Exception e) {
                Log.e("TraceLog", "getTraceLog failed", e);
                str = "";
            }
            this.mTraceList.clear();
            this.mTraceCount = 0;
            return str;
        }

        public void mark(String str) {
            int i2 = this.mTraceCount + 1;
            this.mTraceCount = i2;
            if (i2 < 512) {
                long currentTimeMillis = System.currentTimeMillis();
                Thread currentThread = Thread.currentThread();
                this.mTraceList.add(new Object[]{Long.valueOf(currentThread.getId()), currentThread.getName(), str, Long.valueOf(currentTimeMillis), Long.valueOf(currentTimeMillis)});
            }
        }

        public void recycle(long j2) {
            this.mReferenceTime = j2;
            this.mLapTimeInNano = 0;
            this.mTraceList.clear();
            this.mTraceCount = 0;
        }

        public String toString() {
            long currentTimeMillis = System.currentTimeMillis();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            TraceData.reference = this.mReferenceTime;
            try {
                this.mTraceList.forEach(new a(hashMap2, hashMap, hashMap3));
            } catch (Exception e) {
                Log.e("TraceLog", "log collection failed", e);
            }
            StringBuilder sb2 = new StringBuilder(4096);
            sb2.append("TraceDetail(");
            sb2.append(this.mTraceList.size());
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(((float) this.mLapTimeInNano) / 1000000.0f);
            sb2.append(")[\n");
            hashMap3.forEach(new b(sb2, hashMap2, hashMap));
            sb2.append("] +");
            sb2.append(System.currentTimeMillis() - currentTimeMillis);
            return sb2.toString();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TraceLogHolder {
        static final TraceLog sInstance = new TraceLog();
    }

    public static void beginSection(String str) {
        sTraceCompat.beginSection(str);
        if (sTraceLogEnabled) {
            TraceLogHolder.sInstance.beginSection(str);
        }
    }

    public static void clearLog() {
        sTraceLogEnabled = false;
        sTraceLogBaseTime = 0;
        TraceLogHolder.sInstance.recycle(0);
    }

    public static void dumpLog(long j2) {
        sTraceLogEnabled = false;
        sTraceLogBaseTime = 0;
        StringBuilder sb2 = new StringBuilder("Trace{");
        TraceLog traceLog = TraceLogHolder.sInstance;
        sb2.append(getLocalTime(traceLog.mReferenceTime));
        sb2.append("~");
        sb2.append(getLocalTime(j2));
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(j2 - traceLog.mReferenceTime);
        sb2.append("}\n");
        sb2.append(traceLog.getTraceLog());
        String sb3 = sb2.toString();
        String[] strArr = sTraceLogBuffer;
        int i2 = sTraceLogBufferIndex;
        sTraceLogBufferIndex = i2 + 1;
        strArr[i2 % 5] = sb3;
    }

    public static void endSection() {
        sTraceCompat.endSection();
        if (sTraceLogEnabled) {
            TraceLogHolder.sInstance.endSection();
        }
    }

    private static String getLocalTime(long j2) {
        String str;
        Object obj;
        Object obj2;
        Object obj3;
        Date date = new Date(j2);
        int hours = date.getHours();
        int minutes = date.getMinutes();
        int seconds = date.getSeconds();
        int i2 = (int) (j2 % 1000);
        StringBuilder sb2 = new StringBuilder();
        if (hours < 10) {
            str = C0086a.i(hours, "0");
        } else {
            str = Integer.toString(hours);
        }
        sb2.append(str);
        sb2.append(NumericEnum.SEP);
        if (minutes < 10) {
            obj = C0086a.i(minutes, "0");
        } else {
            obj = Integer.valueOf(minutes);
        }
        sb2.append(obj);
        sb2.append(NumericEnum.SEP);
        if (seconds < 10) {
            obj2 = C0086a.i(seconds, "0");
        } else {
            obj2 = Integer.valueOf(seconds);
        }
        sb2.append(obj2);
        sb2.append(".");
        if (i2 < 10) {
            obj3 = C0086a.i(i2, "00");
        } else if (i2 < 100) {
            obj3 = C0086a.i(i2, "0");
        } else {
            obj3 = Integer.valueOf(i2);
        }
        sb2.append(obj3);
        return sb2.toString();
    }

    public static String makeTag(Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        beginSection(simpleName.concat("#newInstance"));
        return simpleName;
    }

    public static void mark(String str) {
        if (sTraceLogEnabled) {
            if (sTraceLogBaseTime == 0) {
                sTraceLogBaseTime = System.currentTimeMillis();
                TraceLogHolder.sInstance.recycle(sTraceLogBaseTime);
            }
            TraceLogHolder.sInstance.mark(str);
        }
    }

    public static void recycleLog() {
        TraceLogHolder.sInstance.recycle(0);
        sTraceLogEnabled = true;
        sTraceLogBaseTime = 0;
    }

    public static void dumpLog(PrintWriter printWriter) {
        for (String str : sTraceLogBuffer) {
            if (str != null) {
                printWriter.println(str);
            }
        }
    }
}
