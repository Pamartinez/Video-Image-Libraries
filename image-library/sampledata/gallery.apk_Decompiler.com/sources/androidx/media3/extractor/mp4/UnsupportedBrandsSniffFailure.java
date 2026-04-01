package androidx.media3.extractor.mp4;

import J2.a;
import androidx.media3.extractor.SniffFailure;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UnsupportedBrandsSniffFailure implements SniffFailure {
    public final a compatibleBrands;
    public final int majorBrand;

    public UnsupportedBrandsSniffFailure(int i2, int[] iArr) {
        a aVar;
        this.majorBrand = i2;
        if (iArr != null) {
            a aVar2 = a.f;
            if (iArr.length == 0) {
                aVar = a.f;
            } else {
                aVar = new a(Arrays.copyOf(iArr, iArr.length));
            }
        } else {
            aVar = a.f;
        }
        this.compatibleBrands = aVar;
    }
}
