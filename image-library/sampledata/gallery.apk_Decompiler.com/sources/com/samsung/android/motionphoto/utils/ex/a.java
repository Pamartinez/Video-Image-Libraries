package com.samsung.android.motionphoto.utils.ex;

import android.net.Uri;
import com.samsung.android.gallery.widget.dragdrop.dragshadow.DragShadowCreator;
import com.samsung.o3dp.jpeg3dcontainer.util.ByteUtil;
import java.util.function.BinaryOperator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements BinaryOperator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3243a;

    public /* synthetic */ a(int i2) {
        this.f3243a = i2;
    }

    public final Object apply(Object obj, Object obj2) {
        switch (this.f3243a) {
            case 0:
                return MotionPhotoVideoUtils.lambda$sortSEFDataMap$1((byte[]) obj, (byte[]) obj2);
            case 1:
                return ByteUtil.concat((byte[]) obj, (byte[]) obj2);
            default:
                return DragShadowCreator.lambda$getBitmapFromRef$3((Uri) obj, (Uri) obj2);
        }
    }
}
