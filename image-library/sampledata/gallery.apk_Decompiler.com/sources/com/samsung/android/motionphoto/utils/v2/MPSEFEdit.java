package com.samsung.android.motionphoto.utils.v2;

import androidx.core.util.Pair;
import com.samsung.android.sum.core.SLog;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 Q2\u00020\u00012\u00020\u0002:\u0001QB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\r\u0010\bJ\u0017\u0010\r\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\r\u0010\u0010J\u0015\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J \u0010\u0018\u001a\n \u0017*\u0004\u0018\u00010\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0015H\u0001¢\u0006\u0004\b\u0018\u0010\u0019J>\u0010\u001f\u001a\n \u0017*\u0004\u0018\u00010\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00152\r\b\u0001\u0010\u001c\u001a\u00070\u001a¢\u0006\u0002\b\u001b2\r\b\u0001\u0010\u001e\u001a\u00070\u001d¢\u0006\u0002\b\u001bH\u0001¢\u0006\u0004\b\u001f\u0010 J>\u0010\u001f\u001a\n \u0017*\u0004\u0018\u00010\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00152\r\b\u0001\u0010\u001c\u001a\u00070\u001a¢\u0006\u0002\b\u001b2\r\b\u0001\u0010!\u001a\u00070\u000e¢\u0006\u0002\b\u001bH\u0001¢\u0006\u0004\b\u001f\u0010\"J/\u0010\u001f\u001a\n \u0017*\u0004\u0018\u00010\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00152\r\b\u0001\u0010\u001e\u001a\u00070\u001d¢\u0006\u0002\b\u001bH\u0001¢\u0006\u0004\b\u001f\u0010#J6\u0010\u001f\u001a\n \u0017*\u0004\u0018\u00010\u00020\u00022\r\b\u0001\u0010\u001c\u001a\u00070\u001a¢\u0006\u0002\b\u001b2\r\b\u0001\u0010\u001e\u001a\u00070\u001d¢\u0006\u0002\b\u001bH\u0001¢\u0006\u0004\b\u001f\u0010$J/\u0010\u001f\u001a\n \u0017*\u0004\u0018\u00010\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00152\r\b\u0001\u0010!\u001a\u00070\u000e¢\u0006\u0002\b\u001bH\u0001¢\u0006\u0004\b\u001f\u0010%J6\u0010\u001f\u001a\n \u0017*\u0004\u0018\u00010\u00020\u00022\r\b\u0001\u0010\u001c\u001a\u00070\u001a¢\u0006\u0002\b\u001b2\r\b\u0001\u0010!\u001a\u00070\u000e¢\u0006\u0002\b\u001bH\u0001¢\u0006\u0004\b\u001f\u0010&JR\u0010)\u001a\n \u0017*\u0004\u0018\u00010\u00020\u000228\u0010(\u001a(\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u001a0\u001a \u0017*\u0014\u0012\u000e\b\u0001\u0012\n \u0017*\u0004\u0018\u00010\u001a0\u001a\u0018\u00010'0'\"\n \u0017*\u0004\u0018\u00010\u001a0\u001aH\u0001¢\u0006\u0004\b)\u0010*J,\u0010)\u001a\n \u0017*\u0004\u0018\u00010\u00020\u00022\u0012\u0010,\u001a\n \u0017*\u0004\u0018\u00010+0+\"\u00020\u0015H\u0001¢\u0006\u0004\b)\u0010-J\u0010\u0010/\u001a\u00020.H\u0001¢\u0006\u0004\b/\u00100J\u0010\u00101\u001a\u00020\u0015H\u0001¢\u0006\u0004\b1\u00102J\u0010\u00103\u001a\u00020\u0015H\u0001¢\u0006\u0004\b3\u00102J4\u00106\u001a&\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u001a0\u001a \u0017*\u0012\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u001a0\u001a\u0018\u00010504H\u0001¢\u0006\u0004\b6\u00107J\u001f\u00109\u001a\u00020.2\r\b\u0001\u00108\u001a\u00070\u001a¢\u0006\u0002\b\u001bH\u0001¢\u0006\u0004\b9\u0010:J\u0018\u0010;\u001a\u00020.2\u0006\u0010\u0016\u001a\u00020\u0015H\u0001¢\u0006\u0004\b;\u0010<J \u0010=\u001a\n \u0017*\u0004\u0018\u00010\u001d0\u001d2\u0006\u0010\u0016\u001a\u00020\u0015H\u0001¢\u0006\u0004\b=\u0010>J'\u0010=\u001a\n \u0017*\u0004\u0018\u00010\u001d0\u001d2\r\b\u0001\u0010\u001c\u001a\u00070\u001a¢\u0006\u0002\b\u001bH\u0001¢\u0006\u0004\b=\u0010?J/\u0010=\u001a\n \u0017*\u0004\u0018\u00010\u001d0\u001d2\u0006\u0010\u0016\u001a\u00020\u00152\r\b\u0001\u0010\u001c\u001a\u00070\u001a¢\u0006\u0002\b\u001bH\u0001¢\u0006\u0004\b=\u0010@JX\u0010B\u001aB\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u00110\u0011\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u00150\u0015 \u0017* \u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u00110\u0011\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u00150\u0015\u0018\u00010A0A2\u0006\u0010\u0016\u001a\u00020\u0015H\u0001¢\u0006\u0004\bB\u0010CJ_\u0010B\u001aB\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u00110\u0011\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u00150\u0015 \u0017* \u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u00110\u0011\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u00150\u0015\u0018\u00010A0A2\r\b\u0001\u0010\u001c\u001a\u00070\u001a¢\u0006\u0002\b\u001bH\u0001¢\u0006\u0004\bB\u0010DJ\u0010\u0010E\u001a\u00020\u0011H\u0001¢\u0006\u0004\bE\u0010FR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010GR\u0014\u0010K\u001a\u00020H8@X\u0004¢\u0006\u0006\u001a\u0004\bI\u0010JR\u0014\u0010/\u001a\u00020.8@X\u0004¢\u0006\u0006\u001a\u0004\bL\u00100R\u0014\u0010P\u001a\u00020M8BX\u0004¢\u0006\u0006\u001a\u0004\bN\u0010O¨\u0006R"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MPSEFEdit;", "Lcom/samsung/android/motionphoto/utils/v2/MPEditComponent;", "Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "sefEdit", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;)V", "Lme/x;", "loadAllData", "()V", "Lcom/samsung/android/motionphoto/utils/v2/MPEditMediator;", "mpMediator", "setMotionPhotoMediator", "(Lcom/samsung/android/motionphoto/utils/v2/MPEditMediator;)V", "commit", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "file", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "", "newSize", "updateSize", "(J)V", "", "type", "kotlin.jvm.PlatformType", "setPrimaryType", "(I)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "", "Lkotlin/jvm/internal/EnhancedNullability;", "name", "", "data", "putData", "(ILjava/lang/String;[B)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "dataFile", "(ILjava/lang/String;Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "(I[B)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "(Ljava/lang/String;[B)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "(ILcom/samsung/android/motionphoto/utils/v2/MediaFile;)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "(Ljava/lang/String;Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "", "names", "removeData", "([Ljava/lang/String;)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "", "types", "([I)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "", "isDirty", "()Z", "getVersion", "()I", "getDataCount", "", "", "getKeys", "()Ljava/util/List;", "key", "containsKey", "(Ljava/lang/String;)Z", "containsType", "(I)Z", "getData", "(I)[B", "(Ljava/lang/String;)[B", "(ILjava/lang/String;)[B", "Landroidx/core/util/Pair;", "getDataPositionLength", "(I)Landroidx/core/util/Pair;", "(Ljava/lang/String;)Landroidx/core/util/Pair;", "getSize", "()J", "Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "Lcom/samsung/android/motionphoto/utils/v2/SEFInfo;", "getSefInfo$motionphoto_utils_v2_0_17_release", "()Lcom/samsung/android/motionphoto/utils/v2/SEFInfo;", "sefInfo", "isDirty$motionphoto_utils_v2_0_17_release", "Lcom/samsung/android/motionphoto/utils/v2/SEFEditImpl;", "getImpl", "()Lcom/samsung/android/motionphoto/utils/v2/SEFEditImpl;", "impl", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MPSEFEdit extends MPEditComponent implements SEFEdit {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private final SEFEdit sefEdit;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MPSEFEdit$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String tagOf = SLog.tagOf(MPSEFEdit.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public MPSEFEdit(SEFEdit sEFEdit) {
        j.e(sEFEdit, "sefEdit");
        this.sefEdit = sEFEdit;
    }

    private final SEFEditImpl getImpl() {
        SEFEdit sEFEdit = this.sefEdit;
        j.c(sEFEdit, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.SEFEditImpl");
        return (SEFEditImpl) sEFEdit;
    }

    public void commit() {
        this.sefEdit.commit();
        MPEditMediator mpMediator = getMpMediator();
        if (mpMediator != null) {
            mpMediator.notify(MPEditEvent.WRITE_SEF, this);
        }
    }

    public boolean containsKey(String str) {
        j.e(str, "key");
        return this.sefEdit.containsKey(str);
    }

    public boolean containsType(int i2) {
        return this.sefEdit.containsType(i2);
    }

    public byte[] getData(int i2) {
        return this.sefEdit.getData(i2);
    }

    public int getDataCount() {
        return this.sefEdit.getDataCount();
    }

    public Pair<Long, Integer> getDataPositionLength(int i2) {
        return this.sefEdit.getDataPositionLength(i2);
    }

    public List<String> getKeys() {
        return this.sefEdit.getKeys();
    }

    public final SEFInfo getSefInfo$motionphoto_utils_v2_0_17_release() {
        return getImpl().getSefInfo();
    }

    public long getSize() {
        return this.sefEdit.getSize();
    }

    public int getVersion() {
        return this.sefEdit.getVersion();
    }

    public boolean isDirty() {
        return this.sefEdit.isDirty();
    }

    public final boolean isDirty$motionphoto_utils_v2_0_17_release() {
        return getImpl().isDirty$motionphoto_utils_v2_0_17_release();
    }

    public final void loadAllData() {
        getImpl().loadAllData();
    }

    public SEFEdit putData(int i2, MediaFile mediaFile) {
        j.e(mediaFile, "dataFile");
        return this.sefEdit.putData(i2, mediaFile);
    }

    public SEFEdit removeData(int... iArr) {
        return this.sefEdit.removeData(iArr);
    }

    public void setMotionPhotoMediator(MPEditMediator mPEditMediator) {
        j.e(mPEditMediator, "mpMediator");
        SLog.d(TAG, "setMotionPhotoMediator");
        setMpMediator(mPEditMediator);
    }

    public SEFEdit setPrimaryType(int i2) {
        return this.sefEdit.setPrimaryType(i2);
    }

    public final void updateSize(long j2) {
        SEFInfo sefInfo$motionphoto_utils_v2_0_17_release = getSefInfo$motionphoto_utils_v2_0_17_release();
        j.c(sefInfo$motionphoto_utils_v2_0_17_release, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.SEFInfoImpl");
        ((SEFInfoImpl) sefInfo$motionphoto_utils_v2_0_17_release).adjustPosition$motionphoto_utils_v2_0_17_release(j2);
    }

    public byte[] getData(int i2, String str) {
        j.e(str, "name");
        return this.sefEdit.getData(i2, str);
    }

    public Pair<Long, Integer> getDataPositionLength(String str) {
        j.e(str, "name");
        return this.sefEdit.getDataPositionLength(str);
    }

    public SEFEdit putData(int i2, String str, MediaFile mediaFile) {
        j.e(str, "name");
        j.e(mediaFile, "dataFile");
        return this.sefEdit.putData(i2, str, mediaFile);
    }

    public SEFEdit removeData(String... strArr) {
        return this.sefEdit.removeData(strArr);
    }

    public void commit(MediaFile mediaFile) {
        j.e(mediaFile, "file");
        this.sefEdit.commit(mediaFile);
        MPEditMediator mpMediator = getMpMediator();
        if (mpMediator != null) {
            mpMediator.notify(MPEditEvent.WRITE_SEF, this);
        }
    }

    public byte[] getData(String str) {
        j.e(str, "name");
        return this.sefEdit.getData(str);
    }

    public SEFEdit putData(int i2, String str, byte[] bArr) {
        j.e(str, "name");
        j.e(bArr, "data");
        return this.sefEdit.putData(i2, str, bArr);
    }

    public SEFEdit putData(int i2, byte[] bArr) {
        j.e(bArr, "data");
        return this.sefEdit.putData(i2, bArr);
    }

    public SEFEdit putData(String str, MediaFile mediaFile) {
        j.e(str, "name");
        j.e(mediaFile, "dataFile");
        return this.sefEdit.putData(str, mediaFile);
    }

    public SEFEdit putData(String str, byte[] bArr) {
        j.e(str, "name");
        j.e(bArr, "data");
        return this.sefEdit.putData(str, bArr);
    }
}
