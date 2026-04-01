package com.samsung.android.vexfwk.imagestyletransfer;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import te.C1295a;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/imagestyletransfer/VexFwkImageStyleTransferStyles;", "", "<init>", "()V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageStyleTransferStyles {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lcom/samsung/android/vexfwk/imagestyletransfer/VexFwkImageStyleTransferStyles$Companion;", "", "<init>", "()V", "fromIntToStyleTransferType", "Lcom/samsung/android/vexfwk/imagestyletransfer/VexFwkImageStyleTransferStyles$Companion$StyleTransferType;", "value", "", "StyleTransferType", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/vexfwk/imagestyletransfer/VexFwkImageStyleTransferStyles$Companion$StyleTransferType;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "STYLE_COLOR_PENCIL", "STYLE_COMIC", "STYLE_WATERCOLOR", "STYLE_BLUE_INK", "STYLE_PASTEL", "STYLE_MARKER", "STYLE_LINE_ART", "STYLE_OIL_PAINT", "STYLE_CUBIST", "STYLE_PEN_AND_WASH", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public enum StyleTransferType {
            STYLE_COLOR_PENCIL(0),
            STYLE_COMIC(1),
            STYLE_WATERCOLOR(2),
            STYLE_BLUE_INK(3),
            STYLE_PASTEL(4),
            STYLE_MARKER(5),
            STYLE_LINE_ART(6),
            STYLE_OIL_PAINT(7),
            STYLE_CUBIST(8),
            STYLE_PEN_AND_WASH(9);
            
            private final int value;

            static {
                StyleTransferType[] $values;
                $ENTRIES = c.t($values);
            }

            private StyleTransferType(int i2) {
                this.value = i2;
            }

            public static C1295a getEntries() {
                return $ENTRIES;
            }

            public final int getValue() {
                return this.value;
            }
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final StyleTransferType fromIntToStyleTransferType(int i2) {
            for (StyleTransferType styleTransferType : StyleTransferType.values()) {
                if (styleTransferType.getValue() == i2) {
                    return styleTransferType;
                }
            }
            return null;
        }

        private Companion() {
        }
    }
}
