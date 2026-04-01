package com.samsung.android.gallery.app.service;

import com.samsung.android.gallery.module.fileio.compat.FileOpJob;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2514a;

    public /* synthetic */ b(int i2) {
        this.f2514a = i2;
    }

    public final Object apply(Object obj) {
        FileOpJob fileOpJob = (FileOpJob) obj;
        switch (this.f2514a) {
            case 0:
                return fileOpJob.errorCode;
            case 1:
                return FileOpData.lambda$log$10(fileOpJob);
            case 2:
                return Integer.valueOf(fileOpJob.operation);
            case 3:
                return String.valueOf(fileOpJob.source.getFileId());
            default:
                return Boolean.TRUE;
        }
    }
}
