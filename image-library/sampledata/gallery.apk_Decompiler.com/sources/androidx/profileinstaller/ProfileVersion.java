package androidx.profileinstaller;

import com.samsung.android.sum.core.types.NumericEnum;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ProfileVersion {
    static final byte[] METADATA_V001_N = {48, 48, 49, 0};
    static final byte[] METADATA_V002 = {48, 48, 50, 0};
    static final byte[] V001_N = {48, 48, 49, 0};
    static final byte[] V005_O = {48, 48, 53, 0};
    static final byte[] V009_O_MR1 = {48, 48, 57, 0};
    static final byte[] V010_P = {48, 49, 48, 0};
    static final byte[] V015_S = {48, 49, 53, 0};

    public static String dexKeySeparator(byte[] bArr) {
        if (!Arrays.equals(bArr, V001_N) && !Arrays.equals(bArr, V005_O)) {
            return "!";
        }
        return NumericEnum.SEP;
    }
}
