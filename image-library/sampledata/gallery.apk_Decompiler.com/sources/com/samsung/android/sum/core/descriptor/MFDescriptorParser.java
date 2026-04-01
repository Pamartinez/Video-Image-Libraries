package com.samsung.android.sum.core.descriptor;

import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MFDescriptorParser {

    /* renamed from: com.samsung.android.sum.core.descriptor.MFDescriptorParser$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sum$core$descriptor$MFDescriptorParser$Type;

        static {
            int[] iArr = new int[Type.values().length];
            $SwitchMap$com$samsung$android$sum$core$descriptor$MFDescriptorParser$Type = iArr;
            try {
                iArr[Type.JSON.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        JSON
    }

    static MFDescriptorParser of(Type type) {
        if (AnonymousClass1.$SwitchMap$com$samsung$android$sum$core$descriptor$MFDescriptorParser$Type[type.ordinal()] == 1) {
            return new MFDescriptorJsonParser();
        }
        throw new UnsupportedOperationException("unknown type");
    }

    MFDescriptor parse(InputStream inputStream);
}
