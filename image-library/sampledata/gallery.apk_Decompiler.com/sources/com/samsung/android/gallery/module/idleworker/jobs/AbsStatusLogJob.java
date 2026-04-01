package com.samsung.android.gallery.module.idleworker.jobs;

import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.support.analytics.AnalyticsStatusId;
import com.samsung.android.gallery.support.utils.GalleryPreference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AbsStatusLogJob extends IdleWorkerJob {
    final GalleryPreference mPreference = GalleryPreference.getInstance();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SaStringBuilder extends GalleryPreference.Builder<String> {
        public SaStringBuilder append(AnalyticsStatusId analyticsStatusId, String str) {
            return (SaStringBuilder) append(analyticsStatusId.id(), str);
        }

        public SaStringBuilder append(AnalyticsStatusId analyticsStatusId, int i2) {
            return append(analyticsStatusId, String.valueOf(i2));
        }
    }

    public AbsStatusLogJob(int i2, IdleWorkerJob.Type type) {
        super(i2, type);
    }
}
