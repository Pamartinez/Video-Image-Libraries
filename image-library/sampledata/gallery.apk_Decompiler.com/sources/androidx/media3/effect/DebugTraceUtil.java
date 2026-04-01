package androidx.media3.effect;

import F2.C0040v;
import F2.O0;
import F2.U;
import F2.X;
import F2.Y;
import android.util.JsonWriter;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Util;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DebugTraceUtil {
    private static final Y COMPONENTS_TO_EVENTS;
    private static final Map<String, Map<String, EventLogger>> componentsToEventsToLogs = new LinkedHashMap();
    public static boolean enableTracing = false;
    private static long startTimeMs = Clock.DEFAULT.elapsedRealtime();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class EventLog {
        public final long eventTimeMs;
        public final String extra;
        public final long presentationTimeUs;

        public String toString() {
            String str;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Util.formatInvariant("%s@%dms", DebugTraceUtil.presentationTimeToString(this.presentationTimeUs), Long.valueOf(this.eventTimeMs)));
            if (this.extra.isEmpty()) {
                str = "";
            } else {
                str = Util.formatInvariant("(%s)", this.extra);
            }
            sb2.append(str);
            return sb2.toString();
        }

        private EventLog(long j2, long j3, String str) {
            this.presentationTimeUs = j2;
            this.eventTimeMs = j3;
            this.extra = str;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class EventLogger {
        private final List<EventLog> firstLogs = new ArrayList(10);
        private final Queue<EventLog> lastLogs = new ArrayDeque(10);
        private int totalCount = 0;

        public void addLog(EventLog eventLog) {
            if (this.firstLogs.size() < 10) {
                this.firstLogs.add(eventLog);
            } else {
                this.lastLogs.add(eventLog);
                if (this.lastLogs.size() > 10) {
                    this.lastLogs.remove();
                }
            }
            this.totalCount++;
        }

        public void toJson(JsonWriter jsonWriter) {
            jsonWriter.beginObject().name("count").value((long) this.totalCount).name("first").beginArray();
            for (EventLog eventLog : this.firstLogs) {
                jsonWriter.value(eventLog.toString());
            }
            jsonWriter.endArray();
            if (!this.lastLogs.isEmpty()) {
                jsonWriter.name("last").beginArray();
                for (EventLog eventLog2 : this.lastLogs) {
                    jsonWriter.value(eventLog2.toString());
                }
                jsonWriter.endArray();
            }
            jsonWriter.endObject();
        }
    }

    static {
        X x9 = new X(4);
        x9.b("TransformerInternal", U.B("Start"));
        x9.b("AssetLoader", U.D("InputFormat", "OutputFormat"));
        Object[] objArr = {"InputFormat", "OutputFormat", "AcceptedInput", "ProducedOutput", "InputEnded", "OutputEnded"};
        C0040v.a(6, objArr);
        x9.b("AudioDecoder", U.w(6, objArr));
        x9.b("AudioGraph", U.D("RegisterNewInputStream", "OutputEnded"));
        Object[] objArr2 = {"RegisterNewInputStream", "OutputFormat", "ProducedOutput"};
        C0040v.a(3, objArr2);
        x9.b("AudioMixer", U.w(3, objArr2));
        Object[] objArr3 = {"InputFormat", "OutputFormat", "AcceptedInput", "ProducedOutput", "InputEnded", "OutputEnded"};
        C0040v.a(6, objArr3);
        x9.b("AudioEncoder", U.w(6, objArr3));
        Object[] objArr4 = {"InputFormat", "OutputFormat", "AcceptedInput", "ProducedOutput", "InputEnded", "OutputEnded"};
        C0040v.a(6, objArr4);
        x9.b("VideoDecoder", U.w(6, objArr4));
        Object[] objArr5 = {"RegisterNewInputStream", "SurfaceTextureInput", "QueueFrame", "QueueBitmap", "QueueTexture", "RenderedToOutputSurface", "OutputTextureRendered", "ReceiveEndOfAllInput", "SignalEnded"};
        C0040v.a(9, objArr5);
        x9.b("VideoFrameProcessor", U.w(9, objArr5));
        x9.b("ExternalTextureManager", U.D("SignalEOS", "SurfaceTextureTransformFix"));
        x9.b("BitmapTextureManager", U.B("SignalEOS"));
        x9.b("TexIdTextureManager", U.B("SignalEOS"));
        x9.b("Compositor", U.B("OutputTextureRendered"));
        Object[] objArr6 = {"InputFormat", "OutputFormat", "AcceptedInput", "ProducedOutput", "InputEnded", "OutputEnded"};
        C0040v.a(6, objArr6);
        x9.b("VideoEncoder", U.w(6, objArr6));
        x9.b("Muxer", U.E("InputFormat", "CanWriteSample", "AcceptedInput", "InputEnded", "OutputEnded"));
        COMPONENTS_TO_EVENTS = x9.a();
    }

    public static synchronized String generateTraceSummary() {
        synchronized (DebugTraceUtil.class) {
            if (!enableTracing) {
                return "\"Tracing disabled\"";
            }
            StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            try {
                jsonWriter.beginObject();
                O0 v = COMPONENTS_TO_EVENTS.entrySet().iterator();
                while (v.hasNext()) {
                    Map.Entry entry = (Map.Entry) v.next();
                    String str = (String) entry.getKey();
                    jsonWriter.name(str);
                    Map map = componentsToEventsToLogs.get(str);
                    jsonWriter.beginObject();
                    for (String str2 : (List) entry.getValue()) {
                        jsonWriter.name(str2);
                        if (map == null || !map.containsKey(str2)) {
                            jsonWriter.value("No events");
                        } else {
                            ((EventLogger) Assertions.checkNotNull((EventLogger) map.get(str2))).toJson(jsonWriter);
                        }
                    }
                    jsonWriter.endObject();
                }
                jsonWriter.endObject();
                String stringWriter2 = stringWriter.toString();
                Util.closeQuietly(jsonWriter);
                return stringWriter2;
            } catch (IOException unused) {
                Util.closeQuietly(jsonWriter);
                return "\"Error generating trace summary\"";
            }
        }
    }

    private static String getCodecComponent(boolean z, boolean z3) {
        if (z) {
            if (z3) {
                return "VideoDecoder";
            }
            return "AudioDecoder";
        } else if (z3) {
            return "VideoEncoder";
        } else {
            return "AudioEncoder";
        }
    }

    /* JADX INFO: finally extract failed */
    public static synchronized void logCodecEvent(boolean z, boolean z3, String str, long j2, String str2, Object... objArr) {
        synchronized (DebugTraceUtil.class) {
            try {
                logEvent(getCodecComponent(z, z3), str, j2, str2, objArr);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public static synchronized void logEvent(String str, String str2, long j2, String str3, Object... objArr) {
        synchronized (DebugTraceUtil.class) {
            try {
                if (enableTracing) {
                    long elapsedRealtime = Clock.DEFAULT.elapsedRealtime() - startTimeMs;
                    Map<String, Map<String, EventLogger>> map = componentsToEventsToLogs;
                    if (!map.containsKey(str)) {
                        map.put(str, new LinkedHashMap());
                    }
                    Map map2 = map.get(str);
                    if (!map2.containsKey(str2)) {
                        map2.put(str2, new EventLogger());
                    }
                    ((EventLogger) map2.get(str2)).addLog(new EventLog(j2, elapsedRealtime, Util.formatInvariant(str3, objArr)));
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static String presentationTimeToString(long j2) {
        if (j2 == -9223372036854775807L) {
            return "UNSET";
        }
        if (j2 == Long.MIN_VALUE) {
            return "EOS";
        }
        return j2 + "us";
    }

    public static synchronized void reset() {
        synchronized (DebugTraceUtil.class) {
            componentsToEventsToLogs.clear();
            startTimeMs = Clock.DEFAULT.elapsedRealtime();
        }
    }

    /* JADX INFO: finally extract failed */
    public static synchronized void logEvent(String str, String str2, long j2) {
        synchronized (DebugTraceUtil.class) {
            try {
                logEvent(str, str2, j2, "", new Object[0]);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }
}
