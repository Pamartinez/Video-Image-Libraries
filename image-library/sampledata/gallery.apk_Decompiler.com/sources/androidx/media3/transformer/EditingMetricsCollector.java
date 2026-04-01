package androidx.media3.transformer;

import Ba.m;
import F2.U;
import I.a;
import android.content.Context;
import android.hardware.DataSpace;
import android.media.metrics.EditingEndedEvent;
import android.media.metrics.EditingSession;
import android.media.metrics.LogSessionId;
import android.media.metrics.MediaItemInfo;
import android.media.metrics.MediaMetricsManager;
import android.util.Size;
import android.util.SparseIntArray;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.transformer.ExportResult;
import c0.C0086a;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.OCDHelperConstant;
import com.samsung.android.livetranslation.task.LiveTranslationTask;
import com.samsung.android.sum.solution.filter.UniImgp;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class EditingMetricsCollector {
    private static final SparseIntArray DATA_SPACE_RANGE_CONVERSION_MAP;
    private static final SparseIntArray DATA_SPACE_STANDARD_CONVERSION_MAP;
    private static final SparseIntArray DATA_SPACE_TRANSFER_CONVERSION_MAP;
    private static final SparseIntArray ERROR_CODE_CONVERSION_MAP;
    private final String exporterName;
    private final MetricsReporter metricsReporter;
    private final String muxerName;
    private final long startTimeMs = Clock.DEFAULT.elapsedRealtime();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DefaultMetricsReporter implements MetricsReporter {
        private EditingSession editingSession;
        private boolean metricsReported;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Factory implements MetricsReporter.Factory {
            private final Context context;

            public Factory(Context context2) {
                this.context = context2;
            }

            public MetricsReporter create() {
                return new DefaultMetricsReporter(this.context);
            }
        }

        public void close() {
            EditingSession editingSession2 = this.editingSession;
            if (editingSession2 != null) {
                editingSession2.close();
                this.editingSession = null;
            }
        }

        public LogSessionId getLogSessionId() {
            EditingSession editingSession2 = this.editingSession;
            if (editingSession2 != null) {
                return editingSession2.getSessionId();
            }
            return null;
        }

        public void reportMetrics(EditingEndedEvent editingEndedEvent) {
            EditingSession editingSession2;
            if (!this.metricsReported && (editingSession2 = this.editingSession) != null) {
                editingSession2.reportEditingEndedEvent(editingEndedEvent);
                this.metricsReported = true;
            }
        }

        private DefaultMetricsReporter(Context context) {
            MediaMetricsManager b = m.b(context.getSystemService("media_metrics"));
            if (b != null) {
                this.editingSession = b.createEditingSession();
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface MetricsReporter extends AutoCloseable {

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public interface Factory {
            MetricsReporter create();
        }

        void reportMetrics(EditingEndedEvent editingEndedEvent);
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        ERROR_CODE_CONVERSION_MAP = sparseIntArray;
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        DATA_SPACE_STANDARD_CONVERSION_MAP = sparseIntArray2;
        SparseIntArray sparseIntArray3 = new SparseIntArray();
        DATA_SPACE_RANGE_CONVERSION_MAP = sparseIntArray3;
        SparseIntArray sparseIntArray4 = new SparseIntArray();
        DATA_SPACE_TRANSFER_CONVERSION_MAP = sparseIntArray4;
        sparseIntArray.put(1000, 1);
        sparseIntArray.put(1001, 2);
        sparseIntArray.put(2000, 3);
        sparseIntArray.put(2001, 4);
        sparseIntArray.put(2002, 5);
        sparseIntArray.put(2003, 3);
        sparseIntArray.put(2004, 6);
        sparseIntArray.put(UniImgp.OPTION_PREFERRED_COLOR_FORMAT, 7);
        sparseIntArray.put(2006, 8);
        sparseIntArray.put(2007, 9);
        sparseIntArray.put(2008, 10);
        sparseIntArray.put(3001, 11);
        sparseIntArray.put(3002, 12);
        sparseIntArray.put(LiveTranslationTask.ERRORTYPE.ERR_INVALID_PARSING_DATA, 13);
        sparseIntArray.put(4001, 14);
        sparseIntArray.put(4002, 15);
        sparseIntArray.put(4003, 16);
        sparseIntArray.put(5001, 17);
        sparseIntArray.put(6001, 18);
        sparseIntArray.put(7001, 19);
        sparseIntArray.put(7002, 2);
        sparseIntArray2.put(-1, 0);
        sparseIntArray2.put(2, 131072);
        sparseIntArray2.put(1, 65536);
        sparseIntArray2.put(6, 393216);
        sparseIntArray3.put(-1, 0);
        sparseIntArray3.put(2, 268435456);
        sparseIntArray3.put(1, 134217728);
        sparseIntArray4.put(-1, 0);
        sparseIntArray4.put(1, OCDHelperConstant.TEMP_TO_CHECK_OBJECT_CAPTURE);
        sparseIntArray4.put(3, 12582912);
        sparseIntArray4.put(2, 8388608);
        sparseIntArray4.put(10, 16777216);
        sparseIntArray4.put(6, 29360128);
        sparseIntArray4.put(7, 33554432);
    }

    public EditingMetricsCollector(MetricsReporter metricsReporter2, String str, String str2) {
        this.metricsReporter = metricsReporter2;
        this.exporterName = str;
        this.muxerName = str2;
    }

    private EditingEndedEvent.Builder createEditingEndedEventBuilder(int i2) {
        EditingEndedEvent.Builder g = a.c(i2).setTimeSinceCreatedMillis(Clock.DEFAULT.elapsedRealtime() - this.startTimeMs).setExporterName(this.exporterName);
        String str = this.muxerName;
        if (str != null) {
            g.setMuxerName(str);
        }
        return g;
    }

    private static long getDataTypes(String str) {
        long j2;
        if (MimeTypes.isAudio(str)) {
            j2 = 4;
        } else {
            j2 = 0;
        }
        if (MimeTypes.isVideo(str)) {
            j2 |= 2;
        }
        if (MimeTypes.isImage(str)) {
            return j2 | 1;
        }
        return j2;
    }

    private static int getEditingEndedEventErrorCode(int i2) {
        return ERROR_CODE_CONVERSION_MAP.get(i2, 1);
    }

    private static List<MediaItemInfo> getInputMediaItemInfos(U u) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < u.size(); i2++) {
            ExportResult.ProcessedInput processedInput = (ExportResult.ProcessedInput) u.get(i2);
            MediaItemInfo.Builder i7 = a.i();
            i7.setClipDurationMillis(Util.usToMs(processedInput.durationUs));
            String str = processedInput.videoDecoderName;
            if (str != null) {
                i7.addCodecName(str);
            }
            String str2 = processedInput.audioDecoderName;
            if (str2 != null) {
                i7.addCodecName(str2);
            }
            Format format = processedInput.videoFormat;
            if (format != null) {
                String str3 = format.containerMimeType;
                if (str3 != null) {
                    i7.setContainerMimeType(str3);
                }
                String str4 = format.sampleMimeType;
                if (str4 != null) {
                    i7.addSampleMimeType(str4);
                    i7.addDataType(getDataTypes(format.sampleMimeType));
                }
                float f = format.frameRate;
                if (f != -1.0f) {
                    i7.setVideoFrameRate(f);
                }
                int i8 = format.width;
                if (i8 == -1) {
                    i8 = -1;
                }
                int i10 = format.height;
                if (i10 == -1) {
                    i10 = -1;
                }
                i7.setVideoSize(new Size(i8, i10));
                ColorInfo colorInfo = format.colorInfo;
                if (colorInfo != null) {
                    i7.setVideoDataSpace(getVideoDataSpace(colorInfo));
                }
            }
            Format format2 = processedInput.audioFormat;
            if (format2 != null) {
                String str5 = format2.sampleMimeType;
                if (str5 != null) {
                    i7.addSampleMimeType(str5);
                    i7.addDataType(getDataTypes(format2.sampleMimeType));
                }
                int i11 = format2.channelCount;
                if (i11 != -1) {
                    i7.setAudioChannelCount(i11);
                }
                int i12 = format2.sampleRate;
                if (i12 != -1) {
                    i7.setAudioSampleRateHz(i12);
                }
            }
            arrayList.add(i7.build());
        }
        return arrayList;
    }

    private static MediaItemInfo getOutputMediaItemInfo(ExportResult exportResult) {
        MediaItemInfo.Builder i2 = a.i();
        long j2 = exportResult.durationMs;
        if (j2 != -9223372036854775807L) {
            i2.setDurationMillis(j2);
        }
        String str = exportResult.audioMimeType;
        if (str != null) {
            i2.addSampleMimeType(str);
            i2.addDataType(getDataTypes(exportResult.audioMimeType));
        }
        String str2 = exportResult.videoMimeType;
        if (str2 != null) {
            i2.addSampleMimeType(str2);
            i2.addDataType(getDataTypes(exportResult.videoMimeType));
        }
        int i7 = exportResult.channelCount;
        int i8 = -1;
        if (i7 != -1) {
            i2.setAudioChannelCount(i7);
        }
        int i10 = exportResult.sampleRate;
        if (i10 != -2147483647) {
            i2.setAudioSampleRateHz(i10);
        }
        String str3 = exportResult.audioEncoderName;
        if (str3 != null) {
            i2.addCodecName(str3);
        }
        String str4 = exportResult.videoEncoderName;
        if (str4 != null) {
            i2.addCodecName(str4);
        }
        i2.setVideoSampleCount((long) exportResult.videoFrameCount);
        int i11 = exportResult.width;
        if (i11 == -1) {
            i11 = -1;
        }
        int i12 = exportResult.height;
        if (i12 != -1) {
            i8 = i12;
        }
        i2.setVideoSize(new Size(i11, i8));
        ColorInfo colorInfo = exportResult.colorInfo;
        if (colorInfo != null) {
            i2.setVideoDataSpace(getVideoDataSpace(colorInfo));
        }
        return i2.build();
    }

    private static int getVideoDataSpace(ColorInfo colorInfo) {
        return DataSpace.pack(DATA_SPACE_STANDARD_CONVERSION_MAP.get(colorInfo.colorSpace, 0), DATA_SPACE_TRANSFER_CONVERSION_MAP.get(colorInfo.colorTransfer, 0), DATA_SPACE_RANGE_CONVERSION_MAP.get(colorInfo.colorRange, 0));
    }

    public void onExportCancelled(int i2) {
        EditingEndedEvent.Builder createEditingEndedEventBuilder = createEditingEndedEventBuilder(2);
        if (i2 != -1) {
            createEditingEndedEventBuilder.setFinalProgressPercent((float) i2);
        }
        this.metricsReporter.reportMetrics(createEditingEndedEventBuilder.build());
        try {
            C0086a.v(this.metricsReporter);
        } catch (Exception e) {
            Log.e("EditingMetricsCollector", "error while closing the metrics reporter", e);
        }
    }

    public void onExportError(int i2, ExportException exportException, ExportResult exportResult) {
        EditingEndedEvent.Builder e = createEditingEndedEventBuilder(3).setErrorCode(getEditingEndedEventErrorCode(exportException.errorCode));
        if (i2 != -1) {
            e.setFinalProgressPercent((float) i2);
        }
        List<MediaItemInfo> inputMediaItemInfos = getInputMediaItemInfos(exportResult.processedInputs);
        for (int i7 = 0; i7 < inputMediaItemInfos.size(); i7++) {
            e.addInputMediaItemInfo(a.k(inputMediaItemInfos.get(i7)));
        }
        e.setOutputMediaItemInfo(getOutputMediaItemInfo(exportResult));
        this.metricsReporter.reportMetrics(e.build());
        try {
            C0086a.v(this.metricsReporter);
        } catch (Exception e7) {
            Log.e("EditingMetricsCollector", "error while closing the metrics reporter", e7);
        }
    }

    public void onExportSuccess(ExportResult exportResult) {
        EditingEndedEvent.Builder d = createEditingEndedEventBuilder(1).setFinalProgressPercent(100.0f);
        List<MediaItemInfo> inputMediaItemInfos = getInputMediaItemInfos(exportResult.processedInputs);
        for (int i2 = 0; i2 < inputMediaItemInfos.size(); i2++) {
            d.addInputMediaItemInfo(a.k(inputMediaItemInfos.get(i2)));
        }
        d.setOutputMediaItemInfo(getOutputMediaItemInfo(exportResult));
        this.metricsReporter.reportMetrics(d.build());
        try {
            C0086a.v(this.metricsReporter);
        } catch (Exception e) {
            Log.e("EditingMetricsCollector", "error while closing the metrics reporter", e);
        }
    }
}
