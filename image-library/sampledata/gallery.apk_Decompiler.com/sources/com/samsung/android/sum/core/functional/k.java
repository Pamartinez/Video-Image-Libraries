package com.samsung.android.sum.core.functional;

import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.format.UpdatableMediaFormat;
import com.samsung.android.sum.core.functional.OperatorWrapper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements MediaFormatUpdater {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4108a;

    public /* synthetic */ k(int i2) {
        this.f4108a = i2;
    }

    public final void update(MediaFormat mediaFormat, MutableMediaFormat mutableMediaFormat) {
        switch (this.f4108a) {
            case 0:
                mutableMediaFormat.setRows(mediaFormat.getRows()).setCols(mediaFormat.getCols());
                return;
            case 1:
                mutableMediaFormat.setShape(((UpdatableMediaFormat) mediaFormat).getCroppedShape());
                return;
            case 2:
                OperatorWrapper.AnonymousClass1.lambda$new$2(mediaFormat, mutableMediaFormat);
                return;
            case 3:
                OperatorWrapper.AnonymousClass1.lambda$new$3(mediaFormat, mutableMediaFormat);
                return;
            case 4:
                mutableMediaFormat.setDataType(mediaFormat.getDataType());
                return;
            case 5:
                OperatorWrapper.AnonymousClass1.lambda$new$5(mediaFormat, mutableMediaFormat);
                return;
            case 6:
                mutableMediaFormat.setColorFormat(mediaFormat.getColorFormat());
                return;
            default:
                OperatorWrapper.lambda$new$0(mediaFormat, mutableMediaFormat);
                return;
        }
    }
}
