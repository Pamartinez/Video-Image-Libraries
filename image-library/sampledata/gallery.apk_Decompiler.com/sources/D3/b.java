package D3;

import com.samsung.android.gallery.app.activity.external.LocationPickerActivity;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import com.samsung.android.gallery.module.idleworker.jobs.CacheTrimJob;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.ocr.stride.postocr.entity.URLCorrection;
import java.util.function.IntPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements IntPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2296a;

    public /* synthetic */ b(int i2) {
        this.f2296a = i2;
    }

    public final boolean test(int i2) {
        switch (this.f2296a) {
            case 0:
                return LocationPickerActivity.lambda$onRequestPermissionsResult$6(i2);
            case 1:
                return BucketUtils.isScreenshot(i2);
            case 2:
                return TextExtractionHelper.lambda$getFilteredTextData$9(i2);
            case 3:
                return CacheTrimJob.lambda$execute2$0(i2);
            default:
                return URLCorrection.lambda$correctHttpPrefix$0(i2);
        }
    }
}
