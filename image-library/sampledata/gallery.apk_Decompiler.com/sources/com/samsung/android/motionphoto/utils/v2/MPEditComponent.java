package com.samsung.android.motionphoto.utils.v2;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0007\u0010\bR$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MPEditComponent;", "", "<init>", "()V", "Lcom/samsung/android/motionphoto/utils/v2/MPEditMediator;", "mpMediator", "Lme/x;", "setMotionPhotoMediator", "(Lcom/samsung/android/motionphoto/utils/v2/MPEditMediator;)V", "Lcom/samsung/android/motionphoto/utils/v2/MPEditMediator;", "getMpMediator", "()Lcom/samsung/android/motionphoto/utils/v2/MPEditMediator;", "setMpMediator", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MPEditComponent {
    private MPEditMediator mpMediator;

    public final MPEditMediator getMpMediator() {
        return this.mpMediator;
    }

    public abstract void setMotionPhotoMediator(MPEditMediator mPEditMediator);

    public final void setMpMediator(MPEditMediator mPEditMediator) {
        this.mpMediator = mPEditMediator;
    }
}
