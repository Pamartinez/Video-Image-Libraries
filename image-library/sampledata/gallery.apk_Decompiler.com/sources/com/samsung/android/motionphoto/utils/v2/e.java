package com.samsung.android.motionphoto.utils.v2;

import Ae.b;
import java.nio.channels.FileChannel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ FileChannel e;

    public /* synthetic */ e(FileChannel fileChannel, int i2) {
        this.d = i2;
        this.e = fileChannel;
    }

    public final Object invoke(Object obj) {
        long g;
        int i2 = this.d;
        FileChannel fileChannel = this.e;
        FileChannel fileChannel2 = (FileChannel) obj;
        switch (i2) {
            case 0:
                return MotionPhotoEditImpl.addMPVDBox$lambda$20$lambda$19(fileChannel, fileChannel2);
            case 1:
                g = SEFEditImpl.commit$lambda$39$lambda$38(fileChannel, fileChannel2);
                break;
            case 2:
                return SEFEditImpl.commit$lambda$35$lambda$34$lambda$33(fileChannel, fileChannel2);
            default:
                g = XMPEditImpl.commit$lambda$10$lambda$9(fileChannel, fileChannel2);
                break;
        }
        return Long.valueOf(g);
    }
}
