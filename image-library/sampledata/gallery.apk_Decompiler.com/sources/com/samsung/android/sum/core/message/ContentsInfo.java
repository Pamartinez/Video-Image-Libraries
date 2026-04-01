package com.samsung.android.sum.core.message;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.C0923a;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.DataType;
import i.C0212a;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContentsInfo implements Parcelable {
    public static final Parcelable.Creator<ContentsInfo> CREATOR = new Parcelable.Creator<ContentsInfo>() {
        public ContentsInfo createFromParcel(Parcel parcel) {
            return new ContentsInfo(parcel);
        }

        public ContentsInfo[] newArray(int i2) {
            return new ContentsInfo[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) ContentsInfo.class);
    private final Map<String, Object> data;

    public ContentsInfo() {
        this.data = new HashMap();
        setStatusCode(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$toString$0(String str) {
        StringBuilder t = C0212a.t(str, "=");
        t.append(this.data.get(str));
        return t.toString();
    }

    public static ContentsInfo wrap(Message message) {
        return new ContentsInfo(message);
    }

    public ContentsInfo compose(ContentsInfo contentsInfo) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public int describeContents() {
        return 0;
    }

    public boolean getAsBoolean(String str) {
        return ((Boolean) getData(str)).booleanValue();
    }

    public boolean getAsBooleanOr(String str, boolean z) {
        return ((Boolean) getDataOr(str, Boolean.valueOf(z))).booleanValue();
    }

    public byte getAsByte(String str) {
        return ((Byte) getData(str)).byteValue();
    }

    public byte[] getAsByteArray(String str) {
        return (byte[]) getData(str);
    }

    public byte[] getAsByteArrayOr(String str, byte[] bArr) {
        return (byte[]) getDataOr(str, bArr);
    }

    public byte getAsByteOr(String str, byte b) {
        return ((Byte) getDataOr(str, Byte.valueOf(b))).byteValue();
    }

    public double getAsDouble(String str) {
        return ((Double) getData(str)).doubleValue();
    }

    public double getAsDoubleOr(String str, double d) {
        return ((Double) getDataOr(str, Double.valueOf(d))).doubleValue();
    }

    public float getAsFloat(String str) {
        return ((Float) getData(str)).floatValue();
    }

    public float getAsFloatOr(String str, float f) {
        return ((Float) getDataOr(str, Float.valueOf(f))).floatValue();
    }

    public int getAsInteger(String str) {
        return ((Integer) getData(str)).intValue();
    }

    public int getAsIntegerOr(String str, int i2) {
        return ((Integer) getDataOr(str, Integer.valueOf(i2))).intValue();
    }

    public long getAsLong(String str) {
        return ((Long) getData(str)).longValue();
    }

    public long getAsLongOr(String str, long j2) {
        return ((Long) getDataOr(str, Long.valueOf(j2))).longValue();
    }

    public short getAsShort(String str) {
        return ((Short) getData(str)).shortValue();
    }

    public short getAsShortOr(String str, short s) {
        return ((Short) getDataOr(str, Short.valueOf(s))).shortValue();
    }

    public String getAsString(String str) {
        return (String) getData(str);
    }

    public String getAsStringOr(String str, String str2) {
        return (String) getDataOr(str, str2);
    }

    public long getAudioDuration(TimeUnit timeUnit) {
        return timeUnit.convert(getAsLong("last-audio-timestamp-us"), TimeUnit.MICROSECONDS);
    }

    public int getContentsId() {
        return getAsInteger("media-id");
    }

    public <T> T getData(String str) {
        return this.data.get(str);
    }

    public <T> T getDataOr(String str, T t) {
        return this.data.getOrDefault(str, t);
    }

    public long getDuration(TimeUnit timeUnit) {
        return timeUnit.convert(getAsLongOr("duration", -1), TimeUnit.MILLISECONDS);
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(getAsLong(Message.KEY_END_TIME_MS), TimeUnit.MILLISECONDS);
    }

    public int getHeight() {
        return getAsInteger("height");
    }

    public int getId() {
        return getAsInteger("id");
    }

    public ColorFormat getOriginalColorFormat() {
        return (ColorFormat) this.data.getOrDefault("original-color-format", ColorFormat.NONE);
    }

    public DataType getOriginalDataType() {
        return (DataType) this.data.getOrDefault("original-data-type", DataType.NONE);
    }

    public String getOutPath() {
        return getAsString(Message.KEY_OUT_FILE);
    }

    public int getPosition() {
        return getAsInteger(Message.KEY_POSITION);
    }

    public int getProcessedFrames() {
        return getAsIntegerOr(Message.KEY_PROCESSED_FRAMES, 0);
    }

    public long getProcessingTime(TimeUnit timeUnit) {
        return timeUnit.convert(getAsLongOr(Message.KEY_END_TIME_MS, 0) - getAsLongOr(Message.KEY_START_TIME_MS, 0), TimeUnit.MILLISECONDS);
    }

    public int getRotation() {
        if (this.data.containsKey(Message.KEY_ROTATION)) {
            return getAsInteger(Message.KEY_ROTATION);
        }
        return 0;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(getAsLong(Message.KEY_START_TIME_MS), TimeUnit.MILLISECONDS);
    }

    public int getStatusCode() {
        return getAsInteger(Message.KEY_STATUS);
    }

    public long getVideoDuration(TimeUnit timeUnit) {
        return timeUnit.convert(getAsLong("last-video-timestamp-us"), TimeUnit.MICROSECONDS);
    }

    public int getWholeFrames() {
        return getAsIntegerOr(Message.KEY_WHOLE_FRAMES, -1);
    }

    public int getWidth() {
        return getAsInteger("width");
    }

    public boolean isFullyProcessed() {
        if (getWholeFrames() <= getProcessedFrames()) {
            return true;
        }
        return false;
    }

    public boolean isStatusError() {
        return Message.isError(getStatusCode());
    }

    public boolean isStatusOk() {
        return Message.isOk(getStatusCode());
    }

    public boolean isStatusWarn() {
        return Message.isWarn(getStatusCode());
    }

    public ContentsInfo join(ContentsInfo contentsInfo) {
        this.data.putAll(contentsInfo.data);
        return this;
    }

    public ContentsInfo setContentsId(int i2) {
        this.data.put("media-id", Integer.valueOf(i2));
        return this;
    }

    public ContentsInfo setData(String str, Object obj) {
        this.data.put(str, obj);
        return this;
    }

    public ContentsInfo setDuration(TimeUnit timeUnit, long j2) {
        this.data.put("duration", Long.valueOf(timeUnit.toMillis(j2)));
        return this;
    }

    public ContentsInfo setEndTime(TimeUnit timeUnit, long j2) {
        this.data.put(Message.KEY_END_TIME_MS, Long.valueOf(timeUnit.toMillis(j2)));
        return this;
    }

    public ContentsInfo setHeight(int i2) {
        this.data.put("height", Integer.valueOf(i2));
        return this;
    }

    public ContentsInfo setId(int i2) {
        this.data.put("id", Integer.valueOf(i2));
        return this;
    }

    public ContentsInfo setOriginalColorFormat(ColorFormat colorFormat) {
        this.data.put("original-color-format", colorFormat);
        return this;
    }

    public ContentsInfo setOriginalDataType(DataType dataType) {
        this.data.put("original-data-type", dataType);
        return this;
    }

    public ContentsInfo setOutPath(String str) {
        this.data.put(Message.KEY_OUT_FILE, str);
        return this;
    }

    public ContentsInfo setPosition(int i2) {
        this.data.put(Message.KEY_POSITION, Integer.valueOf(i2));
        return this;
    }

    public ContentsInfo setProcessedFrames(int i2) {
        this.data.put(Message.KEY_PROCESSED_FRAMES, Integer.valueOf(i2));
        return this;
    }

    public ContentsInfo setRotation(int i2) {
        this.data.put(Message.KEY_ROTATION, Integer.valueOf(i2));
        return this;
    }

    public ContentsInfo setStartTime(TimeUnit timeUnit, long j2) {
        this.data.put(Message.KEY_START_TIME_MS, Long.valueOf(timeUnit.toMillis(j2)));
        return this;
    }

    public ContentsInfo setStatusCode(int i2) {
        this.data.put(Message.KEY_STATUS, Integer.valueOf(i2));
        return this;
    }

    public ContentsInfo setWholeFrames(int i2) {
        this.data.put(Message.KEY_WHOLE_FRAMES, Integer.valueOf(i2));
        return this;
    }

    public ContentsInfo setWidth(int i2) {
        this.data.put("width", Integer.valueOf(i2));
        return this;
    }

    public Stream<Map.Entry<String, Object>> stream() {
        return this.data.entrySet().stream();
    }

    public String toString() {
        return (String) this.data.keySet().stream().map(new a(0, this)).collect(Collectors.joining(ArcCommonLog.TAG_COMMA, "{", "}"));
    }

    public void writeToParcel(Parcel parcel, int i2) {
        boolean z;
        if (this.data != null) {
            z = true;
        } else {
            z = false;
        }
        Def.require(z);
        parcel.writeMap(this.data);
    }

    public ContentsInfo(ContentValues contentValues) {
        this.data = (Map) contentValues.valueSet().stream().collect(Collectors.toMap(new C0923a(17), new C0923a(18)));
        setStatusCode(0);
    }

    public ContentsInfo(Parcel parcel) {
        this();
        parcel.readMap(this.data, (ClassLoader) null);
    }

    public ContentsInfo(Message message) {
        this.data = message.get();
    }
}
