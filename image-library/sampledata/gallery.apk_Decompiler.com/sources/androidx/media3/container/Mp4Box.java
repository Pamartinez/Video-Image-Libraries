package androidx.media3.container;

import androidx.media3.common.util.ParsableByteArray;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Mp4Box {
    public final int type;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ContainerBox extends Mp4Box {
        public final List<ContainerBox> containerChildren = new ArrayList();
        public final long endPosition;
        public final List<LeafBox> leafChildren = new ArrayList();

        public ContainerBox(int i2, long j2) {
            super(i2);
            this.endPosition = j2;
        }

        public void add(LeafBox leafBox) {
            this.leafChildren.add(leafBox);
        }

        public ContainerBox getContainerBoxOfType(int i2) {
            int size = this.containerChildren.size();
            for (int i7 = 0; i7 < size; i7++) {
                ContainerBox containerBox = this.containerChildren.get(i7);
                if (containerBox.type == i2) {
                    return containerBox;
                }
            }
            return null;
        }

        public LeafBox getLeafBoxOfType(int i2) {
            int size = this.leafChildren.size();
            for (int i7 = 0; i7 < size; i7++) {
                LeafBox leafBox = this.leafChildren.get(i7);
                if (leafBox.type == i2) {
                    return leafBox;
                }
            }
            return null;
        }

        public String toString() {
            return Mp4Box.getBoxTypeString(this.type) + " leaves: " + Arrays.toString(this.leafChildren.toArray()) + " containers: " + Arrays.toString(this.containerChildren.toArray());
        }

        public void add(ContainerBox containerBox) {
            this.containerChildren.add(containerBox);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LeafBox extends Mp4Box {
        public final ParsableByteArray data;

        public LeafBox(int i2, ParsableByteArray parsableByteArray) {
            super(i2);
            this.data = parsableByteArray;
        }
    }

    public static String getBoxTypeString(int i2) {
        return "" + ((char) ((i2 >> 24) & ScoverState.TYPE_NFC_SMART_COVER)) + ((char) ((i2 >> 16) & ScoverState.TYPE_NFC_SMART_COVER)) + ((char) ((i2 >> 8) & ScoverState.TYPE_NFC_SMART_COVER)) + ((char) (i2 & ScoverState.TYPE_NFC_SMART_COVER));
    }

    public String toString() {
        return getBoxTypeString(this.type);
    }

    private Mp4Box(int i2) {
        this.type = i2;
    }
}
