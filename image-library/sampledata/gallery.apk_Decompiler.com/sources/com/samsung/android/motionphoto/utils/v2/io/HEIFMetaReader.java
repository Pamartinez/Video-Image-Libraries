package com.samsung.android.motionphoto.utils.v2.io;

import A.a;
import He.C0748d;
import com.samsung.android.motionphoto.utils.v2.CommonsKt;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.motionphoto.utils.v2.MediaFile;
import com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sum.core.SLog;
import i.C0212a;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 12\u00020\u00012\u00020\u0002:\u0003231B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\n\u0010\u0006J\u001f\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0011\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0011\u0010\u0010J\u001f\u0010\u0012\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0012\u0010\u0010J\u001f\u0010\u0013\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0013\u0010\u0010J\u001f\u0010\u0014\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0014\u0010\u0010J\u001f\u0010\u0015\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0015\u0010\u0010J\u001f\u0010\u001a\u001a\u0004\u0018\u00010\u00192\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u00072\b\b\u0002\u0010\u001c\u001a\u00020\u0003¢\u0006\u0004\b\u001d\u0010\u0006J\u0011\u0010\u001e\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0011\u0010 \u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0004\b \u0010\u001fJ\u0011\u0010!\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0004\b!\u0010\u001fR\u0016\u0010\u0004\u001a\u00020\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0004\u0010\"R\u0016\u0010#\u001a\u00020\u00178\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b#\u0010$R \u0010'\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00190%8\u0002X\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00170)8\u0002X\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00170)8\u0002X\u0004¢\u0006\u0006\n\u0004\b,\u0010+R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020-0)8\u0002X\u0004¢\u0006\u0006\n\u0004\b.\u0010+R\u001a\u00100\u001a\b\u0012\u0004\u0012\u00020/0)8\u0002X\u0004¢\u0006\u0006\n\u0004\b0\u0010+¨\u00064"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/HEIFMetaReader;", "Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaBase;", "Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "mediaFile", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "Lme/x;", "clear", "()V", "parseFile", "Ljava/io/FileInputStream;", "stream", "", "boxSize", "parseFreeBox", "(Ljava/io/FileInputStream;J)V", "parseIINFBox", "parseIREFBox", "parsePITMBox", "parseILOCBox", "parseMPVDBox", "", "", "metaIds", "Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader$Box;", "getMeta", "(Ljava/util/List;)Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader$Box;", "file", "reload", "getXMP", "()Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader$Box;", "getExif", "getCameraDebugInfo", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "coverImageId", "I", "", "", "boxes", "Ljava/util/Map;", "", "xmpIds", "Ljava/util/List;", "exifIds", "Lcom/samsung/android/motionphoto/utils/v2/io/HEIFMetaReader$ItemReference;", "references", "Lcom/samsung/android/motionphoto/utils/v2/io/HEIFMetaReader$ItemLocation;", "locations", "Companion", "ItemReference", "ItemLocation", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class HEIFMetaReader extends ImageMetaBase implements ImageMetaReader {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private final Map<String, ImageMetaReader.Box> boxes = new LinkedHashMap();
    private int coverImageId;
    private final List<Integer> exifIds = new ArrayList();
    private final List<ItemLocation> locations = new ArrayList();
    private MediaFile mediaFile;
    private final List<ItemReference> references = new ArrayList();
    private final List<Integer> xmpIds = new ArrayList();

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/HEIFMetaReader$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0010\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\u0012\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\r¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/HEIFMetaReader$ItemLocation;", "", "id", "", "baseOffset", "", "extentOffset", "extentLength", "<init>", "(IJJJ)V", "getId", "()I", "getBaseOffset", "()J", "getExtentOffset", "getExtentLength", "offset", "getOffset", "length", "getLength", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ItemLocation {
        private final long baseOffset;
        private final long extentLength;
        private final long extentOffset;
        private final int id;

        public ItemLocation(int i2, long j2, long j3, long j8) {
            this.id = i2;
            this.baseOffset = j2;
            this.extentOffset = j3;
            this.extentLength = j8;
        }

        public static /* synthetic */ ItemLocation copy$default(ItemLocation itemLocation, int i2, long j2, long j3, long j8, int i7, Object obj) {
            if ((i7 & 1) != 0) {
                i2 = itemLocation.id;
            }
            if ((i7 & 2) != 0) {
                j2 = itemLocation.baseOffset;
            }
            if ((i7 & 4) != 0) {
                j3 = itemLocation.extentOffset;
            }
            if ((i7 & 8) != 0) {
                j8 = itemLocation.extentLength;
            }
            long j10 = j8;
            long j11 = j3;
            return itemLocation.copy(i2, j2, j11, j10);
        }

        public final int component1() {
            return this.id;
        }

        public final long component2() {
            return this.baseOffset;
        }

        public final long component3() {
            return this.extentOffset;
        }

        public final long component4() {
            return this.extentLength;
        }

        public final ItemLocation copy(int i2, long j2, long j3, long j8) {
            return new ItemLocation(i2, j2, j3, j8);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ItemLocation)) {
                return false;
            }
            ItemLocation itemLocation = (ItemLocation) obj;
            if (this.id == itemLocation.id && this.baseOffset == itemLocation.baseOffset && this.extentOffset == itemLocation.extentOffset && this.extentLength == itemLocation.extentLength) {
                return true;
            }
            return false;
        }

        public final long getBaseOffset() {
            return this.baseOffset;
        }

        public final long getExtentLength() {
            return this.extentLength;
        }

        public final long getExtentOffset() {
            return this.extentOffset;
        }

        public final int getId() {
            return this.id;
        }

        public final long getLength() {
            return this.extentLength;
        }

        public final long getOffset() {
            return this.baseOffset + this.extentOffset;
        }

        public int hashCode() {
            return Long.hashCode(this.extentLength) + C0212a.c(C0212a.c(Integer.hashCode(this.id) * 31, 31, this.baseOffset), 31, this.extentOffset);
        }

        public String toString() {
            int i2 = this.id;
            long j2 = this.baseOffset;
            long j3 = this.extentOffset;
            long j8 = this.extentLength;
            StringBuilder sb2 = new StringBuilder("ItemLocation(id=");
            sb2.append(i2);
            sb2.append(", baseOffset=");
            sb2.append(j2);
            sb2.append(", extentOffset=");
            sb2.append(j3);
            sb2.append(", extentLength=");
            return C0212a.o(sb2, j8, ")");
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u0005J\u0010\u0010\u000b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0012\u0010\fJ\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\fR\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\u00198\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/HEIFMetaReader$ItemReference;", "", "", "id", "<init>", "(I)V", "index", "get", "(I)I", "Lme/x;", "add", "component1", "()I", "copy", "(I)Lcom/samsung/android/motionphoto/utils/v2/io/HEIFMetaReader$ItemReference;", "", "toString", "()Ljava/lang/String;", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getId", "", "items", "Ljava/util/List;", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ItemReference {
        private final int id;
        private final List<Integer> items = new ArrayList();

        public ItemReference(int i2) {
            this.id = i2;
        }

        public static /* synthetic */ ItemReference copy$default(ItemReference itemReference, int i2, int i7, Object obj) {
            if ((i7 & 1) != 0) {
                i2 = itemReference.id;
            }
            return itemReference.copy(i2);
        }

        public final void add(int i2) {
            this.items.add(Integer.valueOf(i2));
        }

        public final int component1() {
            return this.id;
        }

        public final ItemReference copy(int i2) {
            return new ItemReference(i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof ItemReference) && this.id == ((ItemReference) obj).id) {
                return true;
            }
            return false;
        }

        public final int get(int i2) {
            return this.items.get(i2).intValue();
        }

        public final int getId() {
            return this.id;
        }

        public int hashCode() {
            return Integer.hashCode(this.id);
        }

        public String toString() {
            return C0212a.j(this.id, "ItemReference(id=", ")");
        }
    }

    static {
        String tagOf = SLog.tagOf(HEIFMetaReader.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HEIFMetaReader(MediaFile mediaFile2) {
        super(mediaFile2);
        j.e(mediaFile2, "mediaFile");
        this.mediaFile = mediaFile2;
        parseFile(this.mediaFile);
    }

    private final void clear() {
        this.boxes.clear();
        this.xmpIds.clear();
        this.exifIds.clear();
        this.references.clear();
        this.locations.clear();
        this.coverImageId = 0;
    }

    private final ImageMetaReader.Box getMeta(List<Integer> list) {
        Object obj;
        Object obj2;
        if (list.isEmpty()) {
            return null;
        }
        Iterator it = this.references.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            ItemReference itemReference = (ItemReference) obj;
            int id = itemReference.getId();
            int i2 = itemReference.get(0);
            if (list.contains(Integer.valueOf(id)) && i2 == this.coverImageId) {
                break;
            }
        }
        ItemReference itemReference2 = (ItemReference) obj;
        if (itemReference2 != null) {
            Iterator it2 = this.locations.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj2 = null;
                    break;
                }
                obj2 = it2.next();
                if (((ItemLocation) obj2).getId() == itemReference2.getId()) {
                    break;
                }
            }
            ItemLocation itemLocation = (ItemLocation) obj2;
            if (itemLocation != null) {
                return new ImageMetaReader.Box(itemLocation.getOffset(), itemLocation.getLength(), getByteReader());
            }
        }
        return null;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void parseFile(com.samsung.android.motionphoto.utils.v2.MediaFile r14) {
        /*
            r13 = this;
            java.lang.String r0 = "free"
            java.lang.String r1 = "iloc"
            java.lang.String r2 = "iref"
            java.lang.String r3 = "pitm"
            java.lang.String r4 = "iinf"
            java.io.FileInputStream r14 = r14.newInputFileStream()
            r5 = 0
            java.nio.channels.FileChannel r6 = r14.getChannel()     // Catch:{ Exception -> 0x005c }
            r7 = 0
            r6.position(r7)     // Catch:{ Exception -> 0x005c }
        L_0x0018:
            java.util.Map<java.lang.String, com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader$Box> r6 = r13.boxes     // Catch:{ Exception -> 0x005c }
            java.util.Set r6 = r6.keySet()     // Catch:{ Exception -> 0x005c }
            java.lang.String[] r9 = new java.lang.String[]{r4, r3, r2, r1, r0}     // Catch:{ Exception -> 0x005c }
            java.util.List r9 = ne.C1195m.q0(r9)     // Catch:{ Exception -> 0x005c }
            java.util.Collection r9 = (java.util.Collection) r9     // Catch:{ Exception -> 0x005c }
            boolean r6 = r6.containsAll(r9)     // Catch:{ Exception -> 0x005c }
            if (r6 != 0) goto L_0x00c2
            r6 = 2
            r9 = 1
            java.nio.IntBuffer r6 = com.samsung.android.motionphoto.utils.v2.CommonsKt.readAsIntBuffer$default((java.io.InputStream) r14, (int) r9, (java.nio.ByteOrder) r5, (int) r6, (java.lang.Object) r5)     // Catch:{ Exception -> 0x005c }
            r10 = 0
            long r9 = com.samsung.android.motionphoto.utils.v2.CommonsKt.getLong$default(r6, r10, r9, r5)     // Catch:{ Exception -> 0x005c }
            r6 = 8
            long r11 = (long) r6     // Catch:{ Exception -> 0x005c }
            long r9 = r9 - r11
            int r6 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r6 <= 0) goto L_0x00c2
            r6 = 4
            java.lang.String r6 = com.samsung.android.motionphoto.utils.v2.CommonsKt.readAsString((java.io.InputStream) r14, (int) r6)     // Catch:{ Exception -> 0x005c }
            int r11 = r6.hashCode()     // Catch:{ Exception -> 0x005c }
            switch(r11) {
                case 3151468: goto L_0x009c;
                case 3232472: goto L_0x0090;
                case 3235383: goto L_0x0085;
                case 3240842: goto L_0x007a;
                case 3347973: goto L_0x006b;
                case 3358609: goto L_0x005e;
                case 3441202: goto L_0x004e;
                default: goto L_0x004d;
            }     // Catch:{ Exception -> 0x005c }
        L_0x004d:
            goto L_0x00a2
        L_0x004e:
            boolean r6 = r6.equals(r3)     // Catch:{ Exception -> 0x005c }
            if (r6 != 0) goto L_0x0055
            goto L_0x00a2
        L_0x0055:
            r13.parsePITMBox(r14, r9)     // Catch:{ Exception -> 0x005c }
            goto L_0x0018
        L_0x0059:
            r13 = move-exception
            goto L_0x00c6
        L_0x005c:
            r13 = move-exception
            goto L_0x00ac
        L_0x005e:
            java.lang.String r11 = "mpvd"
            boolean r6 = r6.equals(r11)     // Catch:{ Exception -> 0x005c }
            if (r6 != 0) goto L_0x0067
            goto L_0x00a2
        L_0x0067:
            r13.parseMPVDBox(r14, r9)     // Catch:{ Exception -> 0x005c }
            goto L_0x0018
        L_0x006b:
            java.lang.String r11 = "meta"
            boolean r6 = r6.equals(r11)     // Catch:{ Exception -> 0x005c }
            if (r6 != 0) goto L_0x0074
            goto L_0x00a2
        L_0x0074:
            r9 = 4
            r14.skip(r9)     // Catch:{ Exception -> 0x005c }
            goto L_0x0018
        L_0x007a:
            boolean r6 = r6.equals(r2)     // Catch:{ Exception -> 0x005c }
            if (r6 != 0) goto L_0x0081
            goto L_0x00a2
        L_0x0081:
            r13.parseIREFBox(r14, r9)     // Catch:{ Exception -> 0x005c }
            goto L_0x0018
        L_0x0085:
            boolean r6 = r6.equals(r1)     // Catch:{ Exception -> 0x005c }
            if (r6 != 0) goto L_0x008c
            goto L_0x00a2
        L_0x008c:
            r13.parseILOCBox(r14, r9)     // Catch:{ Exception -> 0x005c }
            goto L_0x0018
        L_0x0090:
            boolean r6 = r6.equals(r4)     // Catch:{ Exception -> 0x005c }
            if (r6 != 0) goto L_0x0097
            goto L_0x00a2
        L_0x0097:
            r13.parseIINFBox(r14, r9)     // Catch:{ Exception -> 0x005c }
            goto L_0x0018
        L_0x009c:
            boolean r6 = r6.equals(r0)     // Catch:{ Exception -> 0x005c }
            if (r6 != 0) goto L_0x00a7
        L_0x00a2:
            r14.skip(r9)     // Catch:{ Exception -> 0x005c }
            goto L_0x0018
        L_0x00a7:
            r13.parseFreeBox(r14, r9)     // Catch:{ Exception -> 0x005c }
            goto L_0x0018
        L_0x00ac:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x0059 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0059 }
            r1.<init>()     // Catch:{ all -> 0x0059 }
            java.lang.String r2 = "fail to parse: "
            r1.append(r2)     // Catch:{ all -> 0x0059 }
            r1.append(r13)     // Catch:{ all -> 0x0059 }
            java.lang.String r13 = r1.toString()     // Catch:{ all -> 0x0059 }
            com.samsung.android.sum.core.SLog.i((java.lang.String) r0, (java.lang.String) r13)     // Catch:{ all -> 0x0059 }
        L_0x00c2:
            B1.a.g(r14, r5)
            return
        L_0x00c6:
            throw r13     // Catch:{ all -> 0x00c7 }
        L_0x00c7:
            r0 = move-exception
            B1.a.g(r14, r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.io.HEIFMetaReader.parseFile(com.samsung.android.motionphoto.utils.v2.MediaFile):void");
    }

    private final void parseFreeBox(FileInputStream fileInputStream, long j2) {
        if (j2 >= 4) {
            long position = fileInputStream.getChannel().position();
            if (j.a(CommonsKt.readAsString((InputStream) fileInputStream, 4), MediaDefs.Image.HEIF.HEIF_CDIF_BOX)) {
                long j3 = (long) 4;
                this.boxes.put(MediaDefs.Image.HEIF.HEIF_CDIF_BOX, new ImageMetaReader.Box(position + j3, j2 - j3, getByteReader()));
                return;
            }
            fileInputStream.skip(j2 - ((long) 4));
            return;
        }
        fileInputStream.skip(j2);
    }

    private final void parseIINFBox(FileInputStream fileInputStream, long j2) {
        int i2;
        int i7;
        FileInputStream fileInputStream2 = fileInputStream;
        ImageMetaReader.Box box = new ImageMetaReader.Box(fileInputStream2.getChannel().position(), j2, getByteReader());
        int i8 = 1;
        ByteOrder byteOrder = null;
        if (CommonsKt.getByte(CommonsKt.readAsIntBuffer$default((InputStream) fileInputStream2, 1, (ByteOrder) null, 2, (Object) null).get(), 0) == 0) {
            i2 = CommonsKt.getInt(CommonsKt.readAsShortBuffer$default((InputStream) fileInputStream2, 1, (ByteOrder) null, 2, (Object) null));
        } else {
            i2 = CommonsKt.readAsIntBuffer$default((InputStream) fileInputStream2, 1, (ByteOrder) null, 2, (Object) null).get();
        }
        int i10 = 0;
        while (i10 < i2) {
            long position = fileInputStream2.getChannel().position();
            long long$default = CommonsKt.getLong$default(CommonsKt.readAsIntBuffer$default((InputStream) fileInputStream2, i8, byteOrder, 2, (Object) byteOrder), 0, i8, byteOrder);
            if (!j.a(CommonsKt.readAsString((InputStream) fileInputStream2, 4), MediaDefs.Image.HEIF.HEIF_INFE_BOX)) {
                fileInputStream2.skip(long$default - ((long) 8));
            } else {
                byte b = CommonsKt.getByte(CommonsKt.readAsIntBuffer$default((InputStream) fileInputStream2, i8, byteOrder, 2, (Object) byteOrder).get(), 0);
                if (b >= 2) {
                    if (b == 2) {
                        i7 = CommonsKt.getInt(CommonsKt.readAsShortBuffer$default((InputStream) fileInputStream2, i8, byteOrder, 2, (Object) byteOrder));
                    } else {
                        i7 = CommonsKt.readAsIntBuffer$default((InputStream) fileInputStream2, i8, byteOrder, 2, (Object) byteOrder).get();
                    }
                    fileInputStream2.skip(2);
                    String readAsString = CommonsKt.readAsString((InputStream) fileInputStream2, 4);
                    if (j.a(readAsString, MediaDefs.Image.HEIF.HEIF_MIME_BOX)) {
                        this.xmpIds.add(Integer.valueOf(i7));
                    } else if (j.a(readAsString, MediaDefs.Image.HEIF.HEIF_EXIF_BOX)) {
                        this.exifIds.add(Integer.valueOf(i7));
                    }
                    fileInputStream2.skip(long$default - (fileInputStream2.getChannel().position() - position));
                } else {
                    throw new IllegalArgumentException("Failed requirement.");
                }
            }
            i10++;
            i8 = 1;
            byteOrder = null;
        }
        this.boxes.put(MediaDefs.Image.HEIF.HEIF_IINF_BOX, box);
    }

    private final void parseILOCBox(FileInputStream fileInputStream, long j2) {
        Object obj;
        Object obj2;
        int i2;
        a aVar;
        FileInputStream fileInputStream2 = fileInputStream;
        ImageMetaReader.Box box = new ImageMetaReader.Box(fileInputStream2.getChannel().position(), j2, getByteReader());
        int i7 = 0;
        byte b = CommonsKt.getByte(CommonsKt.readAsIntBuffer$default((InputStream) fileInputStream2, 1, (ByteOrder) null, 2, (Object) null).get(), 0);
        if (b <= 2) {
            short s = CommonsKt.readAsShortBuffer$default((InputStream) fileInputStream2, 1, (ByteOrder) null, 2, (Object) null).get();
            int i8 = (s >> 8) & ScoverState.TYPE_NFC_SMART_COVER;
            w wVar = v.f4727a;
            Class<Integer> cls = Integer.class;
            C0748d b5 = wVar.b(cls);
            Class cls2 = Byte.TYPE;
            if (b5.equals(wVar.b(cls2))) {
                obj = Byte.valueOf((byte) i8);
            } else {
                obj = Integer.valueOf(i8);
            }
            Integer num = (Integer) obj;
            short s5 = s & 255;
            if (wVar.b(cls).equals(wVar.b(cls2))) {
                obj2 = Byte.valueOf((byte) s5);
            } else {
                obj2 = Integer.valueOf(s5);
            }
            int intValue = num.intValue();
            int intValue2 = ((Integer) obj2).intValue();
            int i10 = intValue >> 4;
            int i11 = intValue & 15;
            int i12 = intValue2 >> 4;
            if (b == 1 || b == 2) {
                i2 = intValue2 & 15;
            } else {
                i2 = 0;
            }
            if (b < 2) {
                aVar = new a(fileInputStream2, 0);
            } else {
                aVar = new a(fileInputStream2, 1);
            }
            int intValue3 = ((Number) aVar.invoke()).intValue();
            while (i7 < intValue3) {
                int intValue4 = ((Number) aVar.invoke()).intValue();
                a aVar2 = aVar;
                int i13 = intValue3;
                if (b == 1 || b == 2) {
                    fileInputStream2.skip(2);
                }
                fileInputStream2.skip(2);
                long parseILOCBox$lambda$9 = parseILOCBox$lambda$9(fileInputStream2, i12);
                if (CommonsKt.getInt(CommonsKt.readAsShortBuffer$default((InputStream) fileInputStream2, 1, (ByteOrder) null, 2, (Object) null)) == 1) {
                    if ((b == 1 || b == 2) && i2 > 0) {
                        fileInputStream2.skip(4);
                    }
                    this.locations.add(new ItemLocation(intValue4, parseILOCBox$lambda$9, parseILOCBox$lambda$9(fileInputStream2, i10), parseILOCBox$lambda$9(fileInputStream2, i11)));
                    i7++;
                    aVar = aVar2;
                    intValue3 = i13;
                } else {
                    throw new IllegalArgumentException("Failed requirement.");
                }
            }
            this.boxes.put(MediaDefs.Image.HEIF.HEIF_ILOC_BOX, box);
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    /* access modifiers changed from: private */
    public static final int parseILOCBox$lambda$7(FileInputStream fileInputStream) {
        return CommonsKt.readAsShortBuffer$default((InputStream) fileInputStream, 1, (ByteOrder) null, 2, (Object) null).get() & 65535;
    }

    /* access modifiers changed from: private */
    public static final int parseILOCBox$lambda$8(FileInputStream fileInputStream) {
        return CommonsKt.readAsIntBuffer$default((InputStream) fileInputStream, 1, (ByteOrder) null, 2, (Object) null).get();
    }

    private static final long parseILOCBox$lambda$9(FileInputStream fileInputStream, int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (i2 == 4) {
            return CommonsKt.getLong$default(CommonsKt.readAsIntBuffer$default((InputStream) fileInputStream, 1, (ByteOrder) null, 2, (Object) null), 0, 1, (Object) null);
        }
        if (i2 == 8) {
            return CommonsKt.readAsLongBuffer$default((InputStream) fileInputStream, 1, (ByteOrder) null, 2, (Object) null).get();
        }
        throw new IllegalArgumentException();
    }

    private final void parseIREFBox(FileInputStream fileInputStream, long j2) {
        a aVar;
        long position = fileInputStream.getChannel().position();
        long j3 = j2;
        ImageMetaReader.Box box = new ImageMetaReader.Box(position, j3, getByteReader());
        if (CommonsKt.getByte(CommonsKt.readAsIntBuffer$default((InputStream) fileInputStream, 1, (ByteOrder) null, 2, (Object) null).get(), 0) == 0) {
            aVar = new a(fileInputStream, 4);
        } else {
            aVar = new a(fileInputStream, 5);
        }
        while (fileInputStream.getChannel().position() - position < j3) {
            long long$default = CommonsKt.getLong$default(CommonsKt.readAsIntBuffer$default((InputStream) fileInputStream, 1, (ByteOrder) null, 2, (Object) null), 0, 1, (Object) null);
            if (j.a(CommonsKt.readAsString((InputStream) fileInputStream, 4), MediaDefs.Image.HEIF.HEIF_CDSC_BOX)) {
                int intValue = ((Number) aVar.invoke()).intValue();
                fileInputStream.skip(2);
                int intValue2 = ((Number) aVar.invoke()).intValue();
                ItemReference itemReference = new ItemReference(intValue);
                itemReference.add(intValue2);
                this.references.add(itemReference);
            } else {
                fileInputStream.skip(long$default - ((long) 8));
            }
        }
        this.boxes.put(MediaDefs.Image.HEIF.HEIF_IREF_BOX, box);
    }

    /* access modifiers changed from: private */
    public static final int parseIREFBox$lambda$1(FileInputStream fileInputStream) {
        return CommonsKt.readAsShortBuffer$default((InputStream) fileInputStream, 1, (ByteOrder) null, 2, (Object) null).get() & 65535;
    }

    /* access modifiers changed from: private */
    public static final int parseIREFBox$lambda$2(FileInputStream fileInputStream) {
        return CommonsKt.readAsIntBuffer$default((InputStream) fileInputStream, 1, (ByteOrder) null, 2, (Object) null).get();
    }

    private final void parseMPVDBox(FileInputStream fileInputStream, long j2) {
        long j3 = j2;
        this.boxes.put(MediaDefs.Image.HEIF.HEIF_MPVD_BOX, new ImageMetaReader.Box(fileInputStream.getChannel().position(), j3, getByteReader()));
        fileInputStream.skip(j3);
    }

    private final void parsePITMBox(FileInputStream fileInputStream, long j2) {
        a aVar;
        ImageMetaReader.Box box = new ImageMetaReader.Box(fileInputStream.getChannel().position(), j2, getByteReader());
        if (CommonsKt.getByte(CommonsKt.readAsIntBuffer$default((InputStream) fileInputStream, 1, (ByteOrder) null, 2, (Object) null).get(), 0) == 0) {
            aVar = new a(fileInputStream, 2);
        } else {
            aVar = new a(fileInputStream, 3);
        }
        this.coverImageId = ((Number) aVar.invoke()).intValue();
        this.boxes.put(MediaDefs.Image.HEIF.HEIF_PITM_BOX, box);
    }

    /* access modifiers changed from: private */
    public static final int parsePITMBox$lambda$4(FileInputStream fileInputStream) {
        return CommonsKt.readAsShortBuffer$default((InputStream) fileInputStream, 1, (ByteOrder) null, 2, (Object) null).get() & 65535;
    }

    /* access modifiers changed from: private */
    public static final int parsePITMBox$lambda$5(FileInputStream fileInputStream) {
        return CommonsKt.readAsIntBuffer$default((InputStream) fileInputStream, 1, (ByteOrder) null, 2, (Object) null).get();
    }

    public static /* synthetic */ void reload$default(HEIFMetaReader hEIFMetaReader, MediaFile mediaFile2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            mediaFile2 = hEIFMetaReader.mediaFile;
        }
        hEIFMetaReader.reload(mediaFile2);
    }

    public ImageMetaReader.Box getCameraDebugInfo() {
        return this.boxes.get(MediaDefs.Image.HEIF.HEIF_CDIF_BOX);
    }

    public ImageMetaReader.Box getExif() {
        ImageMetaReader.Box meta = getMeta(this.exifIds);
        if (meta == null) {
            return null;
        }
        int i2 = ByteBuffer.wrap(getByteReader().read(meta.getOffset(), 4)).asIntBuffer().get();
        if (i2 >= 6) {
            int i7 = i2 - 2;
            long j2 = (long) i7;
            ImageMetaReader.Box box = new ImageMetaReader.Box(meta.getOffset() + j2, meta.getLength() - j2, getByteReader());
            String str = TAG;
            long offset = box.getOffset();
            long length = box.getLength();
            StringBuilder h5 = a.h(i2, i7, "found exif box: tiffOffset=", ", exifOffset=", ", offset=");
            h5.append(offset);
            h5.append(", size=");
            h5.append(length);
            SLog.d(str, h5.toString());
            return box;
        }
        throw new IllegalStateException("Check failed.");
    }

    public ImageMetaReader.Box getXMP() {
        return getMeta(this.xmpIds);
    }

    public final void reload(MediaFile mediaFile2) {
        j.e(mediaFile2, "file");
        String str = TAG;
        String path = mediaFile2.path();
        SLog.i(str, "Reload HEIFMetaReader for file: " + path);
        try {
            clear();
            this.mediaFile = mediaFile2;
            parseFile(mediaFile2);
        } catch (Exception e) {
            String str2 = TAG;
            String message = e.getMessage();
            SLog.e(str2, "Failed to reload HEIFMetaReader: " + message);
        }
    }
}
