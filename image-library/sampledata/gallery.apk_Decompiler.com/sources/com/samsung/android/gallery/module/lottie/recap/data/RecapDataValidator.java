package com.samsung.android.gallery.module.lottie.recap.data;

import A.a;
import com.samsung.android.gallery.module.lottie.recap.data.parser.AnalyzedData;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RecapDataValidator {
    public static boolean isValid(AnalyzedData analyzedData, RecapTemplate recapTemplate) {
        int i2;
        if (analyzedData.isDevTest()) {
            return true;
        }
        int size = analyzedData.mFaceCandidates.size();
        if (!recapTemplate.isValidPeoples(size)) {
            a.B(size, "not enough peoples : ", "RecapDataValidator");
            return false;
        }
        int size2 = analyzedData.mLocationCandidates.size();
        if (!recapTemplate.isValidLocations(size2)) {
            a.B(size2, "not enough locations : ", "RecapDataValidator");
            return false;
        } else if (analyzedData.getType() != 3288 || (i2 = analyzedData.totalDays) >= 2) {
            return true;
        } else {
            a.B(i2, "not enough active days : ", "RecapDataValidator");
            return false;
        }
    }
}
