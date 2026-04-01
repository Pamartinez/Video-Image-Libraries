package com.samsung.o3dp.jpeg3dcontainer.model;

import Wc.a;
import android.util.Log;
import com.adobe.internal.xmp.XMPException;
import com.samsung.android.sum.core.buffer.t;
import com.samsung.android.sum.core.filter.n;
import com.samsung.o3dp.jpeg3dcontainer.exception.JpegParseException;
import com.samsung.o3dp.jpeg3dcontainer.model.GContainer;
import com.samsung.o3dp.jpeg3dcontainer.util.ByteUtil;
import com.samsung.o3dp.jpeg3dcontainer.util.LogUtil;
import com.samsung.o3dp.jpeg3dcontainer.util.XmpUtil;
import h4.C0464a;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Jpeg3d {
    private static final String TAG = "Jpeg3d";
    private static final String XMP_IDENTIFIER = "http://ns.adobe.com/xap/1.0/\u0000";
    private byte[] bytesAfterEoi;
    private final List<Segment> segments;

    public Jpeg3d(List<Segment> list, byte[] bArr) {
        if (list.stream().anyMatch(new a(this, 1))) {
            LogUtil.i(TAG, "JPEG has GContainer, cut off invalid bytes of bytesAfterEOI");
            this.segments = list;
            GContainer gContainer = getGContainer();
            if (gContainer != null) {
                this.bytesAfterEoi = cutOffBytes(gContainer, bArr);
                return;
            }
            throw new JpegParseException("Failed to create jpeg3d, jpeg doesn't have gContainer");
        }
        LogUtil.i(TAG, "JPEG does not have GContainer, remove all MPF segments from segments.");
        this.segments = (List) list.stream().filter(new a(this, 2)).collect(Collectors.toList());
        this.bytesAfterEoi = null;
    }

    private byte[] buildUpdatedXmpBytes(GContainer gContainer) {
        try {
            return ("http://ns.adobe.com/xap/1.0/\u0000" + gContainer.toXmpStr()).getBytes(StandardCharsets.UTF_8);
        } catch (XMPException e) {
            throw new JpegParseException("Failed to parse xmp of jpeg3d", e);
        }
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [java.lang.Object, java.util.function.BinaryOperator] */
    private byte[] cutOffBytes(GContainer gContainer, byte[] bArr) {
        int intValue = ((Integer) gContainer.getItems().stream().reduce(0, new a(3), new Object())).intValue();
        if (intValue <= bArr.length) {
            return Arrays.copyOfRange(bArr, 0, intValue);
        }
        throw new JpegParseException("gContainer length is greater than bytes after EOI length.");
    }

    private byte[] deleteXmpIdentifier(byte[] bArr) {
        Charset charset = StandardCharsets.UTF_8;
        return new String(bArr, charset).replaceFirst("http://ns.adobe.com/xap/1.0/\u0000", "").getBytes(charset);
    }

    private int[] findSemanticByteRange(GContainer gContainer, String str, byte[] bArr) {
        int i2 = 0;
        for (GContainer.Item next : gContainer.getItems()) {
            String lengthStr = next.getLengthStr();
            String semantic = next.getSemantic();
            if (!(lengthStr == null || semantic == null)) {
                int parseInt = Integer.parseInt(lengthStr);
                int i7 = i2 + parseInt;
                if (i7 > bArr.length) {
                    throw new JpegParseException("Bytes after eoi length is less than gContainer's written length. Please check gContainer of jpeg.");
                } else if (semantic.equals(str)) {
                    return new int[]{i2, parseInt};
                } else {
                    i2 = i7;
                }
            }
        }
        return new int[]{-1, -1};
    }

    private DataSegment getGContainerSegment() {
        List list = (List) getXmpSegments().stream().filter(new a(this, 1)).map(new n(19)).collect(Collectors.toList());
        if (list.size() > 1) {
            throw new JpegParseException("More than one GContainer segment found. This is not supported yet.");
        } else if (list.isEmpty()) {
            return null;
        } else {
            return (DataSegment) list.get(0);
        }
    }

    private byte[] getSpecificBytesAfterEoi(GContainer gContainer, String str) {
        if (this.bytesAfterEoi != null) {
            int i2 = 0;
            for (GContainer.Item next : gContainer.getItems()) {
                String lengthStr = next.getLengthStr();
                String semantic = next.getSemantic();
                if (!(lengthStr == null || semantic == null)) {
                    int parseInt = Integer.parseInt(lengthStr) + i2;
                    if (parseInt > this.bytesAfterEoi.length) {
                        throw new JpegParseException("Bytes after eoi length is less than gContainer's written length. Please check gContainer of jpeg.");
                    } else if (semantic.equals(str)) {
                        return Arrays.copyOfRange(this.bytesAfterEoi, i2, parseInt);
                    } else {
                        i2 = parseInt;
                    }
                }
            }
            return null;
        }
        throw new JpegParseException("There are no bytes after EOI marker in JPEG. It means there are no additional data after jpeg primary image");
    }

    private boolean hasSemanticItem(GContainer gContainer, String str) {
        Stream<R> filter = gContainer.getItems().stream().map(new n(14)).filter(new C0464a(13));
        Objects.requireNonNull(str);
        return filter.anyMatch(new t(3, str));
    }

    private int indexOf(Predicate<Segment> predicate) {
        for (int i2 = 0; i2 < this.segments.size(); i2++) {
            if (predicate.test(this.segments.get(i2))) {
                return i2;
            }
        }
        return -1;
    }

    private boolean isGContainerAfterMpf() {
        if (indexOf(new a(this, 1)) > indexOf(new a(this, 0))) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isGContainerSegment(Segment segment) {
        if (segment instanceof DataSegment) {
            DataSegment dataSegment = (DataSegment) segment;
            if (dataSegment.getMarker() == Marker.APP1) {
                try {
                    return GContainer.isGContainerXmp(XmpUtil.parse(deleteXmpIdentifier(dataSegment.getData())));
                } catch (XMPException unused) {
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isMpfSegment(Segment segment) {
        if (segment instanceof DataSegment) {
            return new String(((DataSegment) segment).getData(), StandardCharsets.UTF_8).startsWith(Mpf.IDENTIFIER);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isXmpSegment(Segment segment) {
        DataSegment dataSegment = (DataSegment) segment;
        Marker marker = dataSegment.getMarker();
        String str = new String(dataSegment.getData(), StandardCharsets.UTF_8);
        if (marker != Marker.APP1) {
            return false;
        }
        return str.startsWith("http://ns.adobe.com/xap/1.0/\u0000");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$cutOffBytes$1(Integer num, GContainer.Item item) {
        if (item.getLengthStr() == null) {
            return num;
        }
        return Integer.valueOf(num.intValue() + Integer.parseInt(item.getLengthStr()));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DataSegment lambda$getGContainerSegment$2(Segment segment) {
        return (DataSegment) segment;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getMpfSegment$4(Segment segment) {
        return segment instanceof DataSegment;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getXmpSegments$3(Segment segment) {
        return segment instanceof DataSegment;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$0(Segment segment) {
        return !isMpfSegment(segment);
    }

    private static byte[] removeRange(byte[] bArr, int i2, int i7) {
        byte[] bArr2 = new byte[(bArr.length - i7)];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        int i8 = i7 + i2;
        System.arraycopy(bArr, i8, bArr2, i2, bArr.length - i8);
        return bArr2;
    }

    private void removeSemanticItem(String str) {
        int i2;
        GContainer gContainer = getGContainer();
        if (gContainer == null) {
            Log.w(TAG, "Failed to remove item. Cannot find gContainer in jpeg3d");
            return;
        }
        DataSegment gContainerSegment = getGContainerSegment();
        if (gContainerSegment != null) {
            i2 = gContainerSegment.getData().length;
        } else {
            i2 = 0;
        }
        byte[] bArr = this.bytesAfterEoi;
        if (bArr == null) {
            Log.w(TAG, "Failed to remove item. Bytes after EOI Marker is null");
            return;
        }
        int[] findSemanticByteRange = findSemanticByteRange(gContainer, str, bArr);
        int i7 = findSemanticByteRange[0];
        int i8 = findSemanticByteRange[1];
        gContainer.deleteItem(str);
        byte[] buildUpdatedXmpBytes = buildUpdatedXmpBytes(gContainer);
        replaceFirstGContainerSegment(buildUpdatedXmpBytes);
        byte[] bArr2 = this.bytesAfterEoi;
        if (bArr2 != null && i7 >= 0 && i8 >= 0) {
            this.bytesAfterEoi = removeRange(bArr2, i7, i8);
        }
        int length = buildUpdatedXmpBytes.length - i2;
        if (length != 0 && hasMpf()) {
            updateMpf(length);
        }
    }

    private void replaceFirstGContainerSegment(byte[] bArr) {
        for (int i2 = 0; i2 < this.segments.size(); i2++) {
            if (isGContainerSegment(this.segments.get(i2))) {
                this.segments.set(i2, new DataSegment(Marker.APP1, bArr));
                return;
            }
        }
        throw new JpegParseException("No GContainer segment found to replace.");
    }

    public void createGContainer(byte[] bArr, byte[] bArr2) {
        LogUtil.i(TAG, "Create GContainer");
        GContainer create = GContainer.create();
        create.addItem(new GContainer.Item(GContainer.SEMANTIC_MODEL_3D, GContainer.MIME_MODEL_GLB_BINARY, String.valueOf(bArr.length), (String) null, (String) null, (String) null));
        create.addItem(new GContainer.Item(GContainer.SEMANTIC_CAMERA_ANIMATION, GContainer.MIME_APPLICATION_ANIMATION_JSON, String.valueOf(bArr2.length), (String) null, (String) null, (String) null));
        DataSegment dataSegment = new DataSegment(Marker.APP1, ("http://ns.adobe.com/xap/1.0/\u0000" + create.toXmpStr()).getBytes(StandardCharsets.UTF_8));
        this.segments.add(this.segments.size() + -1, dataSegment);
    }

    public byte[] getBytesAfterEoi() {
        return this.bytesAfterEoi;
    }

    public byte[] getCameraAnimation() {
        GContainer gContainer = getGContainer();
        if (gContainer == null) {
            return null;
        }
        return getSpecificBytesAfterEoi(gContainer, GContainer.SEMANTIC_CAMERA_ANIMATION);
    }

    public GContainer getGContainer() {
        DataSegment gContainerSegment = getGContainerSegment();
        if (gContainerSegment == null) {
            return null;
        }
        try {
            return GContainer.createFromXmp(XmpUtil.parse(deleteXmpIdentifier(gContainerSegment.getData())));
        } catch (XMPException e) {
            throw new JpegParseException("Failed to deal with XMP", e);
        }
    }

    public byte[] getGainMap() {
        GContainer gContainer = getGContainer();
        if (gContainer == null) {
            return null;
        }
        return getSpecificBytesAfterEoi(gContainer, GContainer.SEMANTIC_GAIN_MAP);
    }

    public byte[] getImageBytes() {
        byte[] bytes = Marker.SOI.toBytes();
        return ByteUtil.concat(ByteUtil.concat(ByteUtil.concat(new byte[0], bytes), (byte[]) this.segments.stream().map(new n(20)).reduce(new byte[0], new com.samsung.android.motionphoto.utils.ex.a(1))), Marker.EOI.toBytes());
    }

    public int getLength() {
        int i2;
        int segmentsLength = getSegmentsLength();
        byte[] bArr = this.bytesAfterEoi;
        if (bArr == null) {
            i2 = 0;
        } else {
            i2 = bArr.length;
        }
        return segmentsLength + i2;
    }

    public byte[] getModel3D() {
        GContainer gContainer = getGContainer();
        if (gContainer == null) {
            return null;
        }
        return getSpecificBytesAfterEoi(gContainer, GContainer.SEMANTIC_MODEL_3D);
    }

    public Segment getMpfSegment() {
        return (Segment) ((List) this.segments.stream().filter(new com.samsung.android.gallery.module.dynamicview.a(14)).filter(new a(this, 0)).collect(Collectors.toList())).get(0);
    }

    public List<Segment> getSegments() {
        return this.segments;
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [java.lang.Object, java.util.function.BinaryOperator] */
    public int getSegmentsLength() {
        int length = Marker.getLength();
        return ((Integer) this.segments.stream().reduce(0, new a(4), new Object())).intValue() + length + Marker.getLength();
    }

    public List<Segment> getXmpSegments() {
        return (List) this.segments.stream().filter(new com.samsung.android.gallery.module.dynamicview.a(15)).filter(new a(this, 3)).collect(Collectors.toList());
    }

    public boolean hasCameraAnimation() {
        GContainer gContainer = getGContainer();
        if (gContainer == null || !hasSemanticItem(gContainer, GContainer.SEMANTIC_CAMERA_ANIMATION)) {
            return false;
        }
        return true;
    }

    public boolean hasGContainer() {
        return getXmpSegments().stream().anyMatch(new a(this, 1));
    }

    public boolean hasModel3D() {
        GContainer gContainer = getGContainer();
        if (gContainer == null || !hasSemanticItem(gContainer, GContainer.SEMANTIC_MODEL_3D)) {
            return false;
        }
        return true;
    }

    public boolean hasMpf() {
        return this.segments.stream().anyMatch(new a(this, 0));
    }

    public void prepareGContainer(byte[] bArr, byte[] bArr2) {
        try {
            if (hasGContainer()) {
                updateGContainer(bArr, bArr2);
            } else {
                createGContainer(bArr, bArr2);
            }
        } catch (XMPException e) {
            throw new JpegParseException("Failed to deal with XMP", e);
        }
    }

    public void removeCameraAnimation() {
        removeSemanticItem(GContainer.SEMANTIC_CAMERA_ANIMATION);
    }

    public void removeModel3D() {
        removeSemanticItem(GContainer.SEMANTIC_MODEL_3D);
    }

    public byte[] toBytes() {
        byte[] imageBytes = getImageBytes();
        byte[] bArr = this.bytesAfterEoi;
        if (bArr != null) {
            return ByteUtil.concat(imageBytes, bArr);
        }
        return imageBytes;
    }

    public void updateGContainer(byte[] bArr, byte[] bArr2) {
        LogUtil.i(TAG, "Update GContainer");
        GContainer gContainer = getGContainer();
        if (gContainer == null) {
            LogUtil.w(TAG, "Can't update gContainer, Jpeg3d doesn't have gContainer");
            return;
        }
        gContainer.deleteItem(GContainer.SEMANTIC_MODEL_3D);
        gContainer.deleteItem(GContainer.SEMANTIC_CAMERA_ANIMATION);
        if (bArr != null) {
            gContainer.addItem(new GContainer.Item(GContainer.SEMANTIC_MODEL_3D, GContainer.MIME_MODEL_GLB_BINARY, String.valueOf(bArr.length), (String) null, (String) null, (String) null));
        }
        if (bArr2 != null) {
            gContainer.addItem(new GContainer.Item(GContainer.SEMANTIC_CAMERA_ANIMATION, GContainer.MIME_APPLICATION_ANIMATION_JSON, String.valueOf(bArr2.length), (String) null, (String) null, (String) null));
        }
        byte[] bytes = ("http://ns.adobe.com/xap/1.0/\u0000" + gContainer.toXmpStr()).getBytes(StandardCharsets.UTF_8);
        for (int i2 = 0; i2 < this.segments.size(); i2++) {
            if (isGContainerSegment(this.segments.get(i2))) {
                this.segments.set(i2, new DataSegment(Marker.APP1, bytes));
                return;
            }
        }
    }

    public void updateMpf(int i2) {
        LogUtil.i(TAG, "Update Mpf");
        Mpf mpf = new Mpf(((DataSegment) getMpfSegment()).getData());
        mpf.applyDeltaToFirstImageSize(i2);
        if (isGContainerAfterMpf()) {
            mpf.applyDeltaToFollowingImageOffsets(i2);
        }
        byte[] data = mpf.getData();
        for (int i7 = 0; i7 < this.segments.size(); i7++) {
            if (isMpfSegment(this.segments.get(i7))) {
                this.segments.set(i7, new DataSegment(Marker.APP2, data));
                return;
            }
        }
    }

    public Jpeg3d(List<Segment> list) {
        this.segments = list;
        this.bytesAfterEoi = null;
    }
}
