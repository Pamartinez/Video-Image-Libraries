package androidx.media3.extractor.flv;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.DiscardingTrackOutput;
import com.samsung.android.gallery.support.utils.MapUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ScriptTagPayloadReader extends TagPayloadReader {
    private long durationUs = -9223372036854775807L;
    private long[] keyFrameTagPositions = new long[0];
    private long[] keyFrameTimesUs = new long[0];

    public ScriptTagPayloadReader() {
        super(new DiscardingTrackOutput());
    }

    private static Boolean readAmfBoolean(ParsableByteArray parsableByteArray) {
        boolean z = true;
        if (parsableByteArray.readUnsignedByte() != 1) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    private static Object readAmfData(ParsableByteArray parsableByteArray, int i2) {
        if (i2 == 0) {
            return readAmfDouble(parsableByteArray);
        }
        if (i2 == 1) {
            return readAmfBoolean(parsableByteArray);
        }
        if (i2 == 2) {
            return readAmfString(parsableByteArray);
        }
        if (i2 == 3) {
            return readAmfObject(parsableByteArray);
        }
        if (i2 == 8) {
            return readAmfEcmaArray(parsableByteArray);
        }
        if (i2 == 10) {
            return readAmfStrictArray(parsableByteArray);
        }
        if (i2 != 11) {
            return null;
        }
        return readAmfDate(parsableByteArray);
    }

    private static Date readAmfDate(ParsableByteArray parsableByteArray) {
        Date date = new Date((long) readAmfDouble(parsableByteArray).doubleValue());
        parsableByteArray.skipBytes(2);
        return date;
    }

    private static Double readAmfDouble(ParsableByteArray parsableByteArray) {
        return Double.valueOf(Double.longBitsToDouble(parsableByteArray.readLong()));
    }

    private static HashMap<String, Object> readAmfEcmaArray(ParsableByteArray parsableByteArray) {
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        HashMap<String, Object> hashMap = new HashMap<>(readUnsignedIntToInt);
        for (int i2 = 0; i2 < readUnsignedIntToInt; i2++) {
            String readAmfString = readAmfString(parsableByteArray);
            Object readAmfData = readAmfData(parsableByteArray, readAmfType(parsableByteArray));
            if (readAmfData != null) {
                hashMap.put(readAmfString, readAmfData);
            }
        }
        return hashMap;
    }

    private static HashMap<String, Object> readAmfObject(ParsableByteArray parsableByteArray) {
        HashMap<String, Object> hashMap = new HashMap<>();
        while (true) {
            String readAmfString = readAmfString(parsableByteArray);
            int readAmfType = readAmfType(parsableByteArray);
            if (readAmfType == 9) {
                return hashMap;
            }
            Object readAmfData = readAmfData(parsableByteArray, readAmfType);
            if (readAmfData != null) {
                hashMap.put(readAmfString, readAmfData);
            }
        }
    }

    private static ArrayList<Object> readAmfStrictArray(ParsableByteArray parsableByteArray) {
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        ArrayList<Object> arrayList = new ArrayList<>(readUnsignedIntToInt);
        for (int i2 = 0; i2 < readUnsignedIntToInt; i2++) {
            Object readAmfData = readAmfData(parsableByteArray, readAmfType(parsableByteArray));
            if (readAmfData != null) {
                arrayList.add(readAmfData);
            }
        }
        return arrayList;
    }

    private static String readAmfString(ParsableByteArray parsableByteArray) {
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(readUnsignedShort);
        return new String(parsableByteArray.getData(), position, readUnsignedShort);
    }

    private static int readAmfType(ParsableByteArray parsableByteArray) {
        return parsableByteArray.readUnsignedByte();
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public long[] getKeyFrameTagPositions() {
        return this.keyFrameTagPositions;
    }

    public long[] getKeyFrameTimesUs() {
        return this.keyFrameTimesUs;
    }

    public boolean parseHeader(ParsableByteArray parsableByteArray) {
        return true;
    }

    public boolean parsePayload(ParsableByteArray parsableByteArray, long j2) {
        if (readAmfType(parsableByteArray) != 2 || !"onMetaData".equals(readAmfString(parsableByteArray)) || parsableByteArray.bytesLeft() == 0 || readAmfType(parsableByteArray) != 8) {
            return false;
        }
        HashMap<String, Object> readAmfEcmaArray = readAmfEcmaArray(parsableByteArray);
        Object obj = readAmfEcmaArray.get("duration");
        if (obj instanceof Double) {
            double doubleValue = ((Double) obj).doubleValue();
            if (doubleValue > MapUtil.INVALID_LOCATION) {
                this.durationUs = (long) (doubleValue * 1000000.0d);
            }
        }
        Object obj2 = readAmfEcmaArray.get("keyframes");
        if (obj2 instanceof Map) {
            Map map = (Map) obj2;
            Object obj3 = map.get("filepositions");
            Object obj4 = map.get("times");
            if ((obj3 instanceof List) && (obj4 instanceof List)) {
                List list = (List) obj3;
                List list2 = (List) obj4;
                int size = list2.size();
                this.keyFrameTimesUs = new long[size];
                this.keyFrameTagPositions = new long[size];
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    Object obj5 = list.get(i2);
                    Object obj6 = list2.get(i2);
                    if (!(obj6 instanceof Double) || !(obj5 instanceof Double)) {
                        this.keyFrameTimesUs = new long[0];
                        this.keyFrameTagPositions = new long[0];
                    } else {
                        this.keyFrameTimesUs[i2] = (long) (((Double) obj6).doubleValue() * 1000000.0d);
                        this.keyFrameTagPositions[i2] = ((Double) obj5).longValue();
                        i2++;
                    }
                }
                this.keyFrameTimesUs = new long[0];
                this.keyFrameTagPositions = new long[0];
            }
        }
        return false;
    }
}
