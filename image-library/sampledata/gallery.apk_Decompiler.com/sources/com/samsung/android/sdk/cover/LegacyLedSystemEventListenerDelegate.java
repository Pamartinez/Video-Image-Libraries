package com.samsung.android.sdk.cover;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.samsung.android.cover.INfcLedCoverTouchListenerCallback;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LegacyLedSystemEventListenerDelegate extends INfcLedCoverTouchListenerCallback.Stub {
    private static final int MSG_SYSTEM_COVER_EVENT = 0;
    private static final String SYSTEM_EVENT_LED_OFF_COMMAND = "led_off_command";
    /* access modifiers changed from: private */
    public static final String TAG = "LegacyLedSystemEventListenerDelegate";
    private ListenerDelegateHandler mHandler;
    private Object mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ListenerDelegateHandler extends Handler {
        private final Object mListener;

        public ListenerDelegateHandler(Looper looper, Object obj) {
            super(looper);
            this.mListener = obj;
        }

        /* JADX WARNING: Removed duplicated region for block: B:13:0x003b  */
        /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r8) {
            /*
                r7 = this;
                java.lang.String r0 = "Error getting onSystemCoverEvent method"
                java.lang.String r1 = "Error: system event args empty: "
                java.lang.Object r2 = r7.mListener
                if (r2 == 0) goto L_0x00c2
                int r3 = r8.what
                if (r3 == 0) goto L_0x000e
                goto L_0x00c2
            L_0x000e:
                java.lang.Object r3 = r8.obj
                int[] r3 = (int[]) r3
                java.lang.Class r2 = r2.getClass()     // Catch:{ SecurityException -> 0x0027, NoSuchMethodException -> 0x0025 }
                java.lang.String r4 = "onSystemCoverEvent"
                java.lang.Class r5 = java.lang.Integer.TYPE     // Catch:{ SecurityException -> 0x0027, NoSuchMethodException -> 0x0025 }
                java.lang.Class<android.os.Bundle> r6 = android.os.Bundle.class
                java.lang.Class[] r5 = new java.lang.Class[]{r5, r6}     // Catch:{ SecurityException -> 0x0027, NoSuchMethodException -> 0x0025 }
                java.lang.reflect.Method r0 = r2.getMethod(r4, r5)     // Catch:{ SecurityException -> 0x0027, NoSuchMethodException -> 0x0025 }
                goto L_0x0039
            L_0x0025:
                r2 = move-exception
                goto L_0x0029
            L_0x0027:
                r2 = move-exception
                goto L_0x0031
            L_0x0029:
                java.lang.String r4 = com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate.TAG
                android.util.Log.e(r4, r0, r2)
                goto L_0x0038
            L_0x0031:
                java.lang.String r4 = com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate.TAG
                android.util.Log.e(r4, r0, r2)
            L_0x0038:
                r0 = 0
            L_0x0039:
                if (r0 == 0) goto L_0x00c2
                java.lang.String r2 = "Error invoking "
                if (r3 == 0) goto L_0x0067
                int r4 = r3.length     // Catch:{ IllegalAccessException -> 0x0065, IllegalArgumentException -> 0x0063, InvocationTargetException -> 0x0061 }
                r5 = 1
                if (r4 >= r5) goto L_0x0044
                goto L_0x0067
            L_0x0044:
                android.os.Bundle r1 = new android.os.Bundle     // Catch:{ IllegalAccessException -> 0x0065, IllegalArgumentException -> 0x0063, InvocationTargetException -> 0x0061 }
                r1.<init>()     // Catch:{ IllegalAccessException -> 0x0065, IllegalArgumentException -> 0x0063, InvocationTargetException -> 0x0061 }
                java.lang.String r4 = "led_off_command"
                r5 = 0
                r3 = r3[r5]     // Catch:{ IllegalAccessException -> 0x0065, IllegalArgumentException -> 0x0063, InvocationTargetException -> 0x0061 }
                r1.putInt(r4, r3)     // Catch:{ IllegalAccessException -> 0x0065, IllegalArgumentException -> 0x0063, InvocationTargetException -> 0x0061 }
                java.lang.Object r7 = r7.mListener     // Catch:{ IllegalAccessException -> 0x0065, IllegalArgumentException -> 0x0063, InvocationTargetException -> 0x0061 }
                int r8 = r8.arg1     // Catch:{ IllegalAccessException -> 0x0065, IllegalArgumentException -> 0x0063, InvocationTargetException -> 0x0061 }
                java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ IllegalAccessException -> 0x0065, IllegalArgumentException -> 0x0063, InvocationTargetException -> 0x0061 }
                java.lang.Object[] r8 = new java.lang.Object[]{r8, r1}     // Catch:{ IllegalAccessException -> 0x0065, IllegalArgumentException -> 0x0063, InvocationTargetException -> 0x0061 }
                r0.invoke(r7, r8)     // Catch:{ IllegalAccessException -> 0x0065, IllegalArgumentException -> 0x0063, InvocationTargetException -> 0x0061 }
                goto L_0x00c2
            L_0x0061:
                r7 = move-exception
                goto L_0x007b
            L_0x0063:
                r7 = move-exception
                goto L_0x0093
            L_0x0065:
                r7 = move-exception
                goto L_0x00ab
            L_0x0067:
                java.lang.String r7 = com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate.TAG     // Catch:{ IllegalAccessException -> 0x0065, IllegalArgumentException -> 0x0063, InvocationTargetException -> 0x0061 }
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IllegalAccessException -> 0x0065, IllegalArgumentException -> 0x0063, InvocationTargetException -> 0x0061 }
                r8.<init>(r1)     // Catch:{ IllegalAccessException -> 0x0065, IllegalArgumentException -> 0x0063, InvocationTargetException -> 0x0061 }
                r8.append(r3)     // Catch:{ IllegalAccessException -> 0x0065, IllegalArgumentException -> 0x0063, InvocationTargetException -> 0x0061 }
                java.lang.String r8 = r8.toString()     // Catch:{ IllegalAccessException -> 0x0065, IllegalArgumentException -> 0x0063, InvocationTargetException -> 0x0061 }
                android.util.Log.e(r7, r8)     // Catch:{ IllegalAccessException -> 0x0065, IllegalArgumentException -> 0x0063, InvocationTargetException -> 0x0061 }
                goto L_0x00c2
            L_0x007b:
                java.lang.String r8 = com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate.TAG
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>(r2)
                java.lang.String r0 = r0.getName()
                r1.append(r0)
                java.lang.String r0 = r1.toString()
                android.util.Log.e(r8, r0, r7)
                goto L_0x00c2
            L_0x0093:
                java.lang.String r8 = com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate.TAG
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>(r2)
                java.lang.String r0 = r0.getName()
                r1.append(r0)
                java.lang.String r0 = r1.toString()
                android.util.Log.e(r8, r0, r7)
                goto L_0x00c2
            L_0x00ab:
                java.lang.String r8 = com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate.TAG
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>(r2)
                java.lang.String r0 = r0.getName()
                r1.append(r0)
                java.lang.String r0 = r1.toString()
                android.util.Log.e(r8, r0, r7)
            L_0x00c2:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate.ListenerDelegateHandler.handleMessage(android.os.Message):void");
        }
    }

    static {
        Class<LegacyLedSystemEventListenerDelegate> cls = LegacyLedSystemEventListenerDelegate.class;
    }

    public LegacyLedSystemEventListenerDelegate(Object obj, Handler handler, Context context) {
        Looper looper;
        this.mListener = obj;
        if (handler == null) {
            looper = context.getMainLooper();
        } else {
            looper = handler.getLooper();
        }
        this.mHandler = new ListenerDelegateHandler(looper, this.mListener);
    }

    public Object getListener() {
        return this.mListener;
    }

    public void onSystemCoverEvent(int i2, int[] iArr) {
        Message obtainMessage = this.mHandler.obtainMessage(0);
        obtainMessage.arg1 = i2;
        obtainMessage.obj = iArr;
        obtainMessage.sendToTarget();
    }

    public void onCoverTouchAccept() {
    }

    public void onCoverTouchReject() {
    }
}
