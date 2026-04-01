package com.samsung.o3dp.jpeg3dcontainer;

import com.samsung.o3dp.jpeg3dcontainer.exception.JpegParseException;
import com.samsung.o3dp.jpeg3dcontainer.model.DataSegment;
import com.samsung.o3dp.jpeg3dcontainer.model.Jpeg3d;
import com.samsung.o3dp.jpeg3dcontainer.model.Marker;
import com.samsung.o3dp.jpeg3dcontainer.model.Segment;
import com.samsung.o3dp.jpeg3dcontainer.model.SosSegment;
import com.samsung.o3dp.jpeg3dcontainer.util.ByteUtil;
import com.samsung.o3dp.jpeg3dcontainer.util.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Jpeg3dParser {
    private static final int NUM_BYTES_OF_DATA_LENGTH_FIELD = 2;
    private static final String TAG = "Jpeg3dParser";

    /* renamed from: com.samsung.o3dp.jpeg3dcontainer.Jpeg3dParser$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$o3dp$jpeg3dcontainer$model$Marker;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.samsung.o3dp.jpeg3dcontainer.model.Marker[] r0 = com.samsung.o3dp.jpeg3dcontainer.model.Marker.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$o3dp$jpeg3dcontainer$model$Marker = r0
                com.samsung.o3dp.jpeg3dcontainer.model.Marker r1 = com.samsung.o3dp.jpeg3dcontainer.model.Marker.SOS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$o3dp$jpeg3dcontainer$model$Marker     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.o3dp.jpeg3dcontainer.model.Marker r1 = com.samsung.o3dp.jpeg3dcontainer.model.Marker.EOI     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.o3dp.jpeg3dcontainer.Jpeg3dParser.AnonymousClass1.<clinit>():void");
        }
    }

    private byte[] readBytesAfterEoi(InputStream inputStream) {
        try {
            return ByteUtil.toByteArray(inputStream);
        } catch (IOException unused) {
            throw new RuntimeException("Error while reading bytes after EOI marker");
        }
    }

    private static int readDataLengthField(InputStream inputStream) {
        return ByteUtil.toInt16(ByteUtil.readNBytes(inputStream, 2), ByteOrder.BIG_ENDIAN);
    }

    private static DataSegment readDataSegment(InputStream inputStream, Marker marker) {
        return new DataSegment(marker, ByteUtil.readNBytes(inputStream, readDataLengthField(inputStream) - 2));
    }

    private byte[] readImageBytes(InputStream inputStream) {
        return ByteUtil.readUntilStopSequence(inputStream, 65497);
    }

    private Marker readMarker(InputStream inputStream) {
        byte[] readNBytes = ByteUtil.readNBytes(inputStream, 2);
        byte b = readNBytes[0];
        if ((b & 255) == 255) {
            return Marker.fromValue((readNBytes[1] & 255) | ((b & 255) << 8));
        }
        throw new JpegParseException("Invalid marker bytes");
    }

    private List<Segment> readSegments(InputStream inputStream) {
        ArrayList arrayList = new ArrayList();
        try {
            if (readMarker(inputStream) == Marker.SOI) {
                while (true) {
                    if (inputStream.available() == 0) {
                        break;
                    }
                    Marker readMarker = readMarker(inputStream);
                    int i2 = AnonymousClass1.$SwitchMap$com$samsung$o3dp$jpeg3dcontainer$model$Marker[readMarker.ordinal()];
                    if (i2 == 1 || i2 == 2) {
                        arrayList.add(readSosSegment(inputStream));
                    } else {
                        arrayList.add(readDataSegment(inputStream, readMarker));
                    }
                }
                arrayList.add(readSosSegment(inputStream));
                return arrayList;
            }
            throw new JpegParseException("Jpeg's first two bytes are not SOI");
        } catch (IOException e) {
            throw new RuntimeException("Error while reading input stream of JPEG", e);
        }
    }

    private SosSegment readSosSegment(InputStream inputStream) {
        return new SosSegment(readImageBytes(inputStream));
    }

    public Jpeg3d parse(InputStream inputStream) {
        LogUtil.d(TAG, "Parse jpeg3d");
        return new Jpeg3d(readSegments(inputStream), readBytesAfterEoi(inputStream));
    }
}
