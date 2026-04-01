package com.samsung.android.gallery.app.service;

import com.samsung.android.gallery.module.fileio.compat.FileOpJob;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2516a;

    public /* synthetic */ f(int i2) {
        this.f2516a = i2;
    }

    public final boolean test(Object obj) {
        FileOpJob fileOpJob = (FileOpJob) obj;
        switch (this.f2516a) {
            case 0:
                return fileOpJob.source.isVideo();
            case 1:
                return fileOpJob.groupOp;
            case 2:
                return fileOpJob.source.isSimilarShot();
            default:
                return fileOpJob.source.isVideo();
        }
    }
}
