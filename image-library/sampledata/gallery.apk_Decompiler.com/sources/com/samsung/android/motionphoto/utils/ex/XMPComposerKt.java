package com.samsung.android.motionphoto.utils.ex;

import Tf.a;
import android.media.ExifInterface;
import android.util.Log;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.XMPPathFactory;
import com.adobe.internal.xmp.XMPSchemaRegistry;
import com.adobe.internal.xmp.options.PropertyOptions;
import com.adobe.internal.xmp.properties.XMPProperty;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import i.C0212a;
import java.io.FileInputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u0002\u001aZ\u0010\u0006\u001a\u00020\u00032\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0001\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u000e\u001a\u00020\u000b2\b\b\u0001\u0010\u000f\u001a\u00020\r2\b\b\u0003\u0010\u0010\u001a\u00020\u000b2\b\b\u0001\u0010\u0011\u001a\u00020\u0012H\u0002\u001a\u0018\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0002\u001a:\u0010\u0014\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000bH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"EXIF_HEADER", "", "hasHdrmap", "Lcom/adobe/internal/xmp/XMPMeta;", "iStream", "Ljava/io/FileInputStream;", "composeXMP", "meta", "primaryType", "Lcom/samsung/android/motionphoto/utils/ex/MimeType;", "primaryItemLength", "", "primaryItemPadding", "", "secondItemLength", "secondItemPadding", "duration", "hasHDR", "", "recomposeXMP", "getXMPMeta", "motionphoto_utils_v2.0.17_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class XMPComposerKt {
    /* access modifiers changed from: private */
    public static final byte[] EXIF_HEADER;

    static {
        byte[] bytes = "Exif\u0000".getBytes(a.f3815a);
        j.d(bytes, "getBytes(...)");
        EXIF_HEADER = bytes;
    }

    /* access modifiers changed from: private */
    public static final XMPMeta composeXMP(XMPMeta xMPMeta, MimeType mimeType, long j2, int i2, long j3, int i7, long j8, boolean z) {
        XMPProperty xMPProperty;
        XMPProperty structField;
        if (!z) {
            return getXMPMeta(mimeType, j2, i2, j3, i7, j8);
        }
        XMPSchemaRegistry schemaRegistry = XMPMetaFactory.getSchemaRegistry();
        schemaRegistry.registerNamespace(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, "GCamera");
        schemaRegistry.registerNamespace(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container");
        schemaRegistry.registerNamespace(MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item");
        schemaRegistry.registerNamespace(MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_NS, "hdrgm");
        XMPMeta create = XMPMetaFactory.create();
        if (xMPMeta != null) {
            xMPProperty = xMPMeta.getProperty(MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_NS, MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_FORMAT_VERSION);
        } else {
            xMPProperty = null;
        }
        if (xMPProperty != null) {
            create.setProperty(MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_NS, MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_FORMAT_VERSION, xMPProperty.getValue());
        }
        create.setProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO, 1);
        create.setProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, MediaDefs.Meta.XMP.XMP_MOTION_PHOTO_FORMAT_VERSION, 1);
        create.setProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, "MotionPhotoPresentationTimestampUs", Long.valueOf(j8));
        XMPMeta xMPMeta2 = create;
        xMPMeta2.appendArrayItem(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Directory", new PropertyOptions().setArrayOrdered(true), (String) null, new PropertyOptions().setStruct(true));
        xMPMeta2.appendArrayItem(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Directory", new PropertyOptions().setArrayOrdered(true), (String) null, new PropertyOptions().setStruct(true));
        xMPMeta2.appendArrayItem(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Directory", new PropertyOptions().setArrayOrdered(true), (String) null, new PropertyOptions().setStruct(true));
        String A10 = C0212a.A(XMPPathFactory.composeArrayItemPath("Directory", 1), "/Container:Item");
        String mimeType2 = mimeType.toString();
        String str = MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS;
        xMPMeta2.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A10, str, "Mime", mimeType2);
        xMPMeta2.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A10, str, "Semantic", MediaDefs.Meta.XMP.XMP_KEY_PRIMARY);
        if (xMPMeta != null) {
            XMPProperty structField2 = xMPMeta.getStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A10, str, MediaDefs.Meta.XMP.XMP_KEY_LENGTH);
            if (structField2 != null) {
                xMPMeta2.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A10, str, MediaDefs.Meta.XMP.XMP_KEY_LENGTH, structField2.getValue().toString());
            }
            XMPProperty structField3 = xMPMeta.getStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A10, str, MediaDefs.Meta.XMP.XMP_KEY_PADDING);
            if (structField3 != null) {
                xMPMeta2.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A10, str, MediaDefs.Meta.XMP.XMP_KEY_PADDING, structField3.getValue().toString());
            }
        }
        String A11 = C0212a.A(XMPPathFactory.composeArrayItemPath("Directory", 2), "/Container:Item");
        xMPMeta2.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A11, str, "Semantic", "Gainmap");
        xMPMeta2.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A11, str, "Mime", mimeType.toString());
        if (!(xMPMeta == null || (structField = xMPMeta.getStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A11, str, MediaDefs.Meta.XMP.XMP_KEY_LENGTH)) == null)) {
            xMPMeta2.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A11, str, MediaDefs.Meta.XMP.XMP_KEY_LENGTH, structField.getValue().toString());
        }
        xMPMeta2.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A11, str, MediaDefs.Meta.XMP.XMP_KEY_PADDING, String.valueOf(i2));
        String A12 = C0212a.A(XMPPathFactory.composeArrayItemPath("Directory", 3), "/Container:Item");
        xMPMeta2.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A12, str, "Mime", MimeType.MP4.toString());
        xMPMeta2.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A12, str, "Semantic", MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO);
        String valueOf = String.valueOf(j3);
        String str2 = A12;
        String str3 = str;
        xMPMeta2.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, str2, str3, MediaDefs.Meta.XMP.XMP_KEY_LENGTH, valueOf);
        xMPMeta2.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, str2, str3, MediaDefs.Meta.XMP.XMP_KEY_PADDING, "0");
        return xMPMeta2;
    }

    public static /* synthetic */ XMPMeta composeXMP$default(XMPMeta xMPMeta, MimeType mimeType, long j2, int i2, long j3, int i7, long j8, boolean z, int i8, Object obj) {
        long j10;
        if ((i8 & 64) != 0) {
            j10 = -1;
        } else {
            j10 = j8;
        }
        return composeXMP(xMPMeta, mimeType, j2, i2, j3, i7, j10, z);
    }

    private static final XMPMeta getXMPMeta(MimeType mimeType, long j2, int i2, long j3, int i7, long j8) {
        String tAG$motionphoto_utils_v2_0_17_release = XMPParser.Companion.getTAG$motionphoto_utils_v2_0_17_release();
        Log.d(tAG$motionphoto_utils_v2_0_17_release, "primary-type=" + mimeType + ", primary-padding=" + i2 + ", primary-len=" + j2 + ", motionphoto-len=" + j3 + ", motionphoto-padding=" + i7 + ", duration=" + j8);
        XMPSchemaRegistry schemaRegistry = XMPMetaFactory.getSchemaRegistry();
        schemaRegistry.registerNamespace(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, "GCamera");
        schemaRegistry.registerNamespace(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container");
        schemaRegistry.registerNamespace(MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item");
        XMPMeta create = XMPMetaFactory.create();
        create.setProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO, 1);
        create.setProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, MediaDefs.Meta.XMP.XMP_MOTION_PHOTO_FORMAT_VERSION, 1);
        create.setProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, "MotionPhotoPresentationTimestampUs", Long.valueOf(j8));
        create.appendArrayItem(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Directory", new PropertyOptions().setArrayOrdered(true), (String) null, new PropertyOptions().setStruct(true));
        create.appendArrayItem(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Directory", new PropertyOptions().setArrayOrdered(true), (String) null, new PropertyOptions().setStruct(true));
        String A10 = C0212a.A(XMPPathFactory.composeArrayItemPath("Directory", 1), "/Container:Item");
        String mimeType2 = mimeType.toString();
        String str = MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS;
        create.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A10, str, "Mime", mimeType2);
        create.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A10, str, "Semantic", MediaDefs.Meta.XMP.XMP_KEY_PRIMARY);
        create.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A10, str, MediaDefs.Meta.XMP.XMP_KEY_LENGTH, "0");
        create.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A10, str, MediaDefs.Meta.XMP.XMP_KEY_PADDING, String.valueOf(i2));
        String A11 = C0212a.A(XMPPathFactory.composeArrayItemPath("Directory", 2), "/Container:Item");
        create.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A11, str, "Mime", MimeType.MP4.toString());
        create.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A11, str, "Semantic", MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO);
        create.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A11, str, MediaDefs.Meta.XMP.XMP_KEY_LENGTH, String.valueOf(j3));
        create.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A11, str, MediaDefs.Meta.XMP.XMP_KEY_PADDING, "0");
        return create;
    }

    public static /* synthetic */ XMPMeta getXMPMeta$default(MimeType mimeType, long j2, int i2, long j3, int i7, long j8, int i8, Object obj) {
        long j10;
        if ((i8 & 32) != 0) {
            j10 = -1;
        } else {
            j10 = j8;
        }
        return getXMPMeta(mimeType, j2, i2, j3, i7, j10);
    }

    /* access modifiers changed from: private */
    public static final XMPMeta hasHdrmap(FileInputStream fileInputStream) {
        fileInputStream.getChannel().position(0);
        byte[] attributeBytes = new ExifInterface(fileInputStream).getAttributeBytes("Xmp");
        if (attributeBytes == null) {
            Log.w(XMPParser.Companion.getTAG$motionphoto_utils_v2_0_17_release(), "Fail to get xmp buffer");
            return null;
        }
        try {
            XMPMeta parseFromBuffer = XMPMetaFactory.parseFromBuffer(attributeBytes);
            if (parseFromBuffer == null) {
                Log.w(XMPParser.Companion.getTAG$motionphoto_utils_v2_0_17_release(), "Fail to get hdrmeta");
                return null;
            }
            XMPProperty property = parseFromBuffer.getProperty(MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_NS, MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_FORMAT_VERSION);
            j.d(property, "getProperty(...)");
            if (!j.a(property.getValue().toString(), "1.0")) {
                Log.w(XMPParser.Companion.getTAG$motionphoto_utils_v2_0_17_release(), "Fail to get gainmap prop");
                return null;
            }
            Log.d(XMPParser.Companion.getTAG$motionphoto_utils_v2_0_17_release(), "Success to find gainmap");
            return parseFromBuffer;
        } catch (XMPException | NullPointerException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static final XMPMeta recomposeXMP(XMPMeta xMPMeta, MimeType mimeType) {
        XMPSchemaRegistry schemaRegistry = XMPMetaFactory.getSchemaRegistry();
        schemaRegistry.registerNamespace(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, "GCamera");
        schemaRegistry.registerNamespace(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container");
        schemaRegistry.registerNamespace(MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item");
        schemaRegistry.registerNamespace(MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_NS, "hdrgm");
        String str = MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_NS;
        XMPMeta create = XMPMetaFactory.create();
        create.setProperty(str, MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_FORMAT_VERSION, xMPMeta.getProperty(str, MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_FORMAT_VERSION).getValue());
        create.appendArrayItem(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Directory", new PropertyOptions().setArrayOrdered(true), (String) null, new PropertyOptions().setStruct(true));
        create.appendArrayItem(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Directory", new PropertyOptions().setArrayOrdered(true), (String) null, new PropertyOptions().setStruct(true));
        String A10 = C0212a.A(XMPPathFactory.composeArrayItemPath("Directory", 1), "/Container:Item");
        String mimeType2 = mimeType.toString();
        String str2 = MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS;
        create.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A10, str2, "Mime", mimeType2);
        create.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A10, str2, "Semantic", MediaDefs.Meta.XMP.XMP_KEY_PRIMARY);
        XMPProperty structField = xMPMeta.getStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A10, str2, MediaDefs.Meta.XMP.XMP_KEY_LENGTH);
        if (structField != null) {
            create.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A10, str2, MediaDefs.Meta.XMP.XMP_KEY_LENGTH, structField.getValue().toString());
        }
        XMPProperty structField2 = xMPMeta.getStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A10, str2, MediaDefs.Meta.XMP.XMP_KEY_PADDING);
        if (structField2 != null) {
            create.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A10, str2, MediaDefs.Meta.XMP.XMP_KEY_PADDING, structField2.getValue().toString());
        }
        String A11 = C0212a.A(XMPPathFactory.composeArrayItemPath("Directory", 2), "/Container:Item");
        create.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A11, str2, "Semantic", "Gainmap");
        create.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A11, str2, "Mime", mimeType.toString());
        XMPProperty structField3 = xMPMeta.getStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A11, str2, MediaDefs.Meta.XMP.XMP_KEY_LENGTH);
        if (structField3 != null) {
            create.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A11, str2, MediaDefs.Meta.XMP.XMP_KEY_LENGTH, structField3.getValue().toString());
        }
        create.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, A11, str2, MediaDefs.Meta.XMP.XMP_KEY_PADDING, "0");
        return create;
    }
}
