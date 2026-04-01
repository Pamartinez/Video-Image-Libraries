package com.samsung.android.gallery.module.mtp;

import android.hardware.usb.UsbDevice;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MtpDeviceLoader f3041a;
    public final /* synthetic */ StringBuilder b;

    public /* synthetic */ a(MtpDeviceLoader mtpDeviceLoader, StringBuilder sb2) {
        this.f3041a = mtpDeviceLoader;
        this.b = sb2;
    }

    public final void accept(Object obj, Object obj2) {
        this.f3041a.lambda$getDump$0(this.b, (String) obj, (UsbDevice) obj2);
    }
}
