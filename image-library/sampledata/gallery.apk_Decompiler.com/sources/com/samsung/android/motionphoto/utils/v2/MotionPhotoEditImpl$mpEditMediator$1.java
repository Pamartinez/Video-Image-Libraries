package com.samsung.android.motionphoto.utils.v2;

import com.samsung.android.sum.core.SLog;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J!\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bR\u0016\u0010\n\u001a\u00020\t8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"com/samsung/android/motionphoto/utils/v2/MotionPhotoEditImpl$mpEditMediator$1", "Lcom/samsung/android/motionphoto/utils/v2/MPEditMediator;", "Lcom/samsung/android/motionphoto/utils/v2/MPEditEvent;", "event", "Lcom/samsung/android/motionphoto/utils/v2/MPEditComponent;", "sender", "Lme/x;", "notify", "(Lcom/samsung/android/motionphoto/utils/v2/MPEditEvent;Lcom/samsung/android/motionphoto/utils/v2/MPEditComponent;)V", "", "lastSize", "J", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MotionPhotoEditImpl$mpEditMediator$1 implements MPEditMediator {
    private long lastSize;
    final /* synthetic */ MotionPhotoEditImpl this$0;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.samsung.android.motionphoto.utils.v2.MPEditEvent[] r0 = com.samsung.android.motionphoto.utils.v2.MPEditEvent.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.samsung.android.motionphoto.utils.v2.MPEditEvent r1 = com.samsung.android.motionphoto.utils.v2.MPEditEvent.WRITE_SEF     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.samsung.android.motionphoto.utils.v2.MPEditEvent r1 = com.samsung.android.motionphoto.utils.v2.MPEditEvent.WRITE_XMP     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.samsung.android.motionphoto.utils.v2.MPEditEvent r1 = com.samsung.android.motionphoto.utils.v2.MPEditEvent.RESERVE_XMP     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.samsung.android.motionphoto.utils.v2.MPEditEvent r1 = com.samsung.android.motionphoto.utils.v2.MPEditEvent.WRITE_MPVD     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl$mpEditMediator$1.WhenMappings.<clinit>():void");
        }
    }

    public MotionPhotoEditImpl$mpEditMediator$1(MotionPhotoEditImpl motionPhotoEditImpl) {
        this.this$0 = motionPhotoEditImpl;
        this.lastSize = motionPhotoEditImpl.getOutputFile().size();
    }

    public void notify(MPEditEvent mPEditEvent, MPEditComponent mPEditComponent) {
        j.e(mPEditEvent, "event");
        String access$getTAG$cp = MotionPhotoEditImpl.TAG;
        long j2 = this.lastSize;
        long size = this.this$0.getOutputFile().size();
        SLog.i(access$getTAG$cp, "notify: sender=" + mPEditComponent + ", event=" + mPEditEvent + ", lastSize=" + j2 + ", newSize=" + size);
        int i2 = WhenMappings.$EnumSwitchMapping$0[mPEditEvent.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    SEFInfo sefInfo$motionphoto_utils_v2_0_17_release = this.this$0.getSefEdit().getSefInfo$motionphoto_utils_v2_0_17_release();
                    j.c(sefInfo$motionphoto_utils_v2_0_17_release, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.SEFInfoImpl");
                    ((SEFInfoImpl) sefInfo$motionphoto_utils_v2_0_17_release).reload$motionphoto_utils_v2_0_17_release();
                } else if (i2 == 4) {
                    SEFInfo sefInfo$motionphoto_utils_v2_0_17_release2 = this.this$0.getSefEdit().getSefInfo$motionphoto_utils_v2_0_17_release();
                    j.c(sefInfo$motionphoto_utils_v2_0_17_release2, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.SEFInfoImpl");
                    SEFInfoImpl sEFInfoImpl = (SEFInfoImpl) sefInfo$motionphoto_utils_v2_0_17_release2;
                    sEFInfoImpl.loadAllData$motionphoto_utils_v2_0_17_release();
                    sEFInfoImpl.setDataOffsetFromEnd$motionphoto_utils_v2_0_17_release(0);
                } else {
                    throw new RuntimeException();
                }
            } else if (this.lastSize != this.this$0.getOutputFile().size()) {
                this.lastSize = this.this$0.getOutputFile().size();
                this.this$0.getSefEdit().updateSize(this.lastSize);
            }
        } else if (this.lastSize != this.this$0.getOutputFile().size()) {
            this.lastSize = this.this$0.getOutputFile().size();
            this.this$0.getXmpEdit().updateMotionPhotoInfo(this.lastSize, this.this$0.getVideoPosition());
        }
    }
}
