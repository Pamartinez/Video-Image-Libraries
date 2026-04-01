package com.samsung.android.motionphoto.utils.v2;

import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sum.core.SLog;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010!\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 S2\u00020\u00012\u00020\u0002:\u0001SB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000b\u0010\u000fJ\u001d\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\u0014JE\u0010\u001c\u001a\n \u001b*\u0004\u0018\u00010\u00020\u00022\r\b\u0001\u0010\u0017\u001a\u00070\u0015¢\u0006\u0002\b\u00162\r\b\u0001\u0010\u0018\u001a\u00070\u0015¢\u0006\u0002\b\u00162\r\b\u0001\u0010\u001a\u001a\u00070\u0019¢\u0006\u0002\b\u0016H\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ6\u0010!\u001a\n \u001b*\u0004\u0018\u00010\u00020\u00022\r\b\u0001\u0010\u001e\u001a\u00070\u0015¢\u0006\u0002\b\u00162\r\b\u0001\u0010 \u001a\u00070\u001f¢\u0006\u0002\b\u0016H\u0001¢\u0006\u0004\b!\u0010\"JE\u0010%\u001a\n \u001b*\u0004\u0018\u00010\u00020\u00022\r\b\u0001\u0010\u001e\u001a\u00070\u0015¢\u0006\u0002\b\u00162\r\b\u0001\u0010#\u001a\u00070\u0015¢\u0006\u0002\b\u00162\r\b\u0001\u0010$\u001a\u00070\u0019¢\u0006\u0002\b\u0016H\u0001¢\u0006\u0004\b%\u0010\u001dJ6\u0010&\u001a\n \u001b*\u0004\u0018\u00010\u00020\u00022\r\b\u0001\u0010\u0017\u001a\u00070\u0015¢\u0006\u0002\b\u00162\r\b\u0001\u0010\u0018\u001a\u00070\u0015¢\u0006\u0002\b\u0016H\u0001¢\u0006\u0004\b&\u0010'J6\u0010(\u001a\n \u001b*\u0004\u0018\u00010\u00020\u00022\r\b\u0001\u0010\u001e\u001a\u00070\u0015¢\u0006\u0002\b\u00162\r\b\u0001\u0010#\u001a\u00070\u0015¢\u0006\u0002\b\u0016H\u0001¢\u0006\u0004\b(\u0010'J'\u0010)\u001a\n \u001b*\u0004\u0018\u00010\u00020\u00022\r\b\u0001\u0010\u001e\u001a\u00070\u0015¢\u0006\u0002\b\u0016H\u0001¢\u0006\u0004\b)\u0010*J\u0018\u0010+\u001a\n \u001b*\u0004\u0018\u00010\u00020\u0002H\u0001¢\u0006\u0004\b+\u0010,JE\u0010-\u001a\n \u001b*\u0004\u0018\u00010\u00020\u00022\r\b\u0001\u0010\u001e\u001a\u00070\u0015¢\u0006\u0002\b\u00162\r\b\u0001\u0010#\u001a\u00070\u0015¢\u0006\u0002\b\u00162\r\b\u0001\u0010$\u001a\u00070\u0019¢\u0006\u0002\b\u0016H\u0001¢\u0006\u0004\b-\u0010\u001dJ\u0010\u0010/\u001a\u00020.H\u0001¢\u0006\u0004\b/\u00100J\u0010\u00101\u001a\u00020.H\u0001¢\u0006\u0004\b1\u00100J\u0010\u00102\u001a\u00020.H\u0001¢\u0006\u0004\b2\u00100J\u001f\u00104\u001a\u00020.2\r\b\u0001\u00103\u001a\u00070\u0015¢\u0006\u0002\b\u0016H\u0001¢\u0006\u0004\b4\u00105J\u001f\u00106\u001a\u00020.2\r\b\u0001\u00103\u001a\u00070\u0015¢\u0006\u0002\b\u0016H\u0001¢\u0006\u0004\b6\u00105J.\u00109\u001a\u00020.2\r\b\u0001\u00107\u001a\u00070\u0015¢\u0006\u0002\b\u00162\r\b\u0001\u00108\u001a\u00070\u0015¢\u0006\u0002\b\u0016H\u0001¢\u0006\u0004\b9\u0010:J4\u0010=\u001a&\u0012\f\u0012\n \u001b*\u0004\u0018\u00010\u00150\u0015 \u001b*\u0012\u0012\f\u0012\n \u001b*\u0004\u0018\u00010\u00150\u0015\u0018\u00010<0;H\u0001¢\u0006\u0004\b=\u0010>J4\u0010?\u001a&\u0012\f\u0012\n \u001b*\u0004\u0018\u00010\u00150\u0015 \u001b*\u0012\u0012\f\u0012\n \u001b*\u0004\u0018\u00010\u00150\u0015\u0018\u00010<0;H\u0001¢\u0006\u0004\b?\u0010>J6\u0010@\u001a\n \u001b*\u0004\u0018\u00010\u00150\u00152\r\b\u0001\u0010\u0017\u001a\u00070\u0015¢\u0006\u0002\b\u00162\r\b\u0001\u0010\u0018\u001a\u00070\u0015¢\u0006\u0002\b\u0016H\u0001¢\u0006\u0004\b@\u0010AJ6\u0010B\u001a\n \u001b*\u0004\u0018\u00010\u00150\u00152\r\b\u0001\u0010\u001e\u001a\u00070\u0015¢\u0006\u0002\b\u00162\r\b\u0001\u0010#\u001a\u00070\u0015¢\u0006\u0002\b\u0016H\u0001¢\u0006\u0004\bB\u0010AJ \u0010E\u001a\n \u001b*\u0004\u0018\u00010\u00150\u00152\u0006\u0010D\u001a\u00020CH\u0001¢\u0006\u0004\bE\u0010FJ\u0018\u0010G\u001a\n \u001b*\u0004\u0018\u00010\u00150\u0015H\u0001¢\u0006\u0004\bG\u0010HR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010IR\u0014\u0010M\u001a\u00020J8BX\u0004¢\u0006\u0006\u001a\u0004\bK\u0010LR\u0014\u0010Q\u001a\u00020N8@X\u0004¢\u0006\u0006\u001a\u0004\bO\u0010PR\u0014\u0010/\u001a\u00020.8@X\u0004¢\u0006\u0006\u001a\u0004\bR\u00100¨\u0006T"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MPXMPEdit;", "Lcom/samsung/android/motionphoto/utils/v2/MPEditComponent;", "Lcom/samsung/android/motionphoto/utils/v2/XMPEdit;", "xmpEdit", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/XMPEdit;)V", "Lcom/samsung/android/motionphoto/utils/v2/MPEditMediator;", "mpMediator", "Lme/x;", "setMotionPhotoMediator", "(Lcom/samsung/android/motionphoto/utils/v2/MPEditMediator;)V", "commit", "()V", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "file", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "", "newSize", "videoPosition", "updateMotionPhotoInfo", "(JJ)V", "", "Lkotlin/jvm/internal/EnhancedNullability;", "schemaNS", "propertyName", "", "propertyValue", "kotlin.jvm.PlatformType", "setProperty", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Lcom/samsung/android/motionphoto/utils/v2/XMPEdit;", "itemName", "Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "mime", "setItem", "(Ljava/lang/String;Lcom/samsung/android/motionphoto/utils/v2/MimeType;)Lcom/samsung/android/motionphoto/utils/v2/XMPEdit;", "fieldName", "fieldValue", "setItemField", "removeProperty", "(Ljava/lang/String;Ljava/lang/String;)Lcom/samsung/android/motionphoto/utils/v2/XMPEdit;", "removeItemField", "removeItem", "(Ljava/lang/String;)Lcom/samsung/android/motionphoto/utils/v2/XMPEdit;", "removeAll", "()Lcom/samsung/android/motionphoto/utils/v2/XMPEdit;", "replaceItemField", "", "isDirty", "()Z", "isEmpty", "isNotEmpty", "name", "containsProperty", "(Ljava/lang/String;)Z", "containsItem", "item", "field", "containsItemField", "(Ljava/lang/String;Ljava/lang/String;)Z", "", "", "getPropertyNames", "()Ljava/util/List;", "getItemNames", "getProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "getItemField", "", "index", "getItemNameAt", "(I)Ljava/lang/String;", "getLastItem", "()Ljava/lang/String;", "Lcom/samsung/android/motionphoto/utils/v2/XMPEdit;", "Lcom/samsung/android/motionphoto/utils/v2/XMPEditImpl;", "getImpl", "()Lcom/samsung/android/motionphoto/utils/v2/XMPEditImpl;", "impl", "Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;", "getXmpInfo$motionphoto_utils_v2_0_17_release", "()Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;", "xmpInfo", "isDirty$motionphoto_utils_v2_0_17_release", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MPXMPEdit extends MPEditComponent implements XMPEdit {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private final XMPEdit xmpEdit;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MPXMPEdit$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String tagOf = SLog.tagOf(MPXMPEdit.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public MPXMPEdit(XMPEdit xMPEdit) {
        j.e(xMPEdit, "xmpEdit");
        this.xmpEdit = xMPEdit;
    }

    private final XMPEditImpl getImpl() {
        XMPEdit xMPEdit = this.xmpEdit;
        j.c(xMPEdit, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.XMPEditImpl");
        return (XMPEditImpl) xMPEdit;
    }

    public void commit() {
        this.xmpEdit.commit();
        MPEditMediator mpMediator = getMpMediator();
        if (mpMediator != null) {
            mpMediator.notify(MPEditEvent.WRITE_XMP, this);
        }
    }

    public boolean containsItem(String str) {
        j.e(str, "name");
        return this.xmpEdit.containsItem(str);
    }

    public boolean containsItemField(String str, String str2) {
        j.e(str, "item");
        j.e(str2, "field");
        return this.xmpEdit.containsItemField(str, str2);
    }

    public boolean containsProperty(String str) {
        j.e(str, "name");
        return this.xmpEdit.containsProperty(str);
    }

    public String getItemField(String str, String str2) {
        j.e(str, "itemName");
        j.e(str2, "fieldName");
        return this.xmpEdit.getItemField(str, str2);
    }

    public String getItemNameAt(int i2) {
        return this.xmpEdit.getItemNameAt(i2);
    }

    public List<String> getItemNames() {
        return this.xmpEdit.getItemNames();
    }

    public String getLastItem() {
        return this.xmpEdit.getLastItem();
    }

    public String getProperty(String str, String str2) {
        j.e(str, "schemaNS");
        j.e(str2, "propertyName");
        return this.xmpEdit.getProperty(str, str2);
    }

    public List<String> getPropertyNames() {
        return this.xmpEdit.getPropertyNames();
    }

    public final XMPInfo getXmpInfo$motionphoto_utils_v2_0_17_release() {
        return getImpl().getXmpInfo$motionphoto_utils_v2_0_17_release();
    }

    public boolean isDirty() {
        return this.xmpEdit.isDirty();
    }

    public final boolean isDirty$motionphoto_utils_v2_0_17_release() {
        return getImpl().isDirty$motionphoto_utils_v2_0_17_release();
    }

    public boolean isEmpty() {
        return this.xmpEdit.isEmpty();
    }

    public boolean isNotEmpty() {
        return this.xmpEdit.isNotEmpty();
    }

    public XMPEdit removeAll() {
        return this.xmpEdit.removeAll();
    }

    public XMPEdit removeItem(String str) {
        j.e(str, "itemName");
        return this.xmpEdit.removeItem(str);
    }

    public XMPEdit removeItemField(String str, String str2) {
        j.e(str, "itemName");
        j.e(str2, "fieldName");
        return this.xmpEdit.removeItemField(str, str2);
    }

    public XMPEdit removeProperty(String str, String str2) {
        j.e(str, "schemaNS");
        j.e(str2, "propertyName");
        return this.xmpEdit.removeProperty(str, str2);
    }

    public XMPEdit replaceItemField(String str, String str2, Object obj) {
        j.e(str, "itemName");
        j.e(str2, "fieldName");
        j.e(obj, "fieldValue");
        return this.xmpEdit.replaceItemField(str, str2, obj);
    }

    public XMPEdit setItem(String str, MimeType mimeType) {
        j.e(str, "itemName");
        j.e(mimeType, MediaDefs.Image.HEIF.HEIF_MIME_BOX);
        return this.xmpEdit.setItem(str, mimeType);
    }

    public XMPEdit setItemField(String str, String str2, Object obj) {
        j.e(str, "itemName");
        j.e(str2, "fieldName");
        j.e(obj, "fieldValue");
        return this.xmpEdit.setItemField(str, str2, obj);
    }

    public void setMotionPhotoMediator(MPEditMediator mPEditMediator) {
        j.e(mPEditMediator, "mpMediator");
        SLog.d(TAG, "setMotionPhotoMediator");
        setMpMediator(mPEditMediator);
    }

    public XMPEdit setProperty(String str, String str2, Object obj) {
        j.e(str, "schemaNS");
        j.e(str2, "propertyName");
        j.e(obj, "propertyValue");
        return this.xmpEdit.setProperty(str, str2, obj);
    }

    public final void updateMotionPhotoInfo(long j2, long j3) {
        if (getImpl().contains(MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO) && j3 > 0) {
            long j8 = j2 - j3;
            String str = TAG;
            StringBuilder j10 = N2.j.j(j8, "update MotionPhoto::Length to ", ", size=");
            j10.append(j2);
            j10.append(", pos=");
            j10.append(j3);
            SLog.i(str, j10.toString());
            getImpl().replaceItemField(MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO, MediaDefs.Meta.XMP.XMP_KEY_LENGTH, Long.valueOf(j8)).commit(getImpl().getOutputFile$motionphoto_utils_v2_0_17_release());
        }
    }

    public void commit(MediaFile mediaFile) {
        j.e(mediaFile, "file");
        this.xmpEdit.commit(mediaFile);
        MPEditMediator mpMediator = getMpMediator();
        if (mpMediator != null) {
            mpMediator.notify(MPEditEvent.WRITE_XMP, this);
        }
    }
}
