package com.samsung.android.motionphoto.utils.ex;

import java.io.FileDescriptor;
import kotlin.Metadata;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J#\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0007\u0010\bJA\u0010\u000f\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\t2\b\b\u0001\u0010\u000b\u001a\u00020\u00022\b\b\u0001\u0010\f\u001a\u00020\t2\b\b\u0001\u0010\r\u001a\u00020\u00022\b\b\u0001\u0010\u000e\u001a\u00020\tH&¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\b\u0001\u0010\u0012\u001a\u00020\u0011H&¢\u0006\u0004\b\u0014\u0010\u0015J#\u0010\u0017\u001a\u00020\u00062\b\b\u0001\u0010\u0012\u001a\u00020\u00112\b\b\u0001\u0010\u0016\u001a\u00020\tH&¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0006H&¢\u0006\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/XmpComposer;", "", "", "xmpSize", "", "hasHdr", "Lme/x;", "reserveXmp", "(IZ)V", "", "primaryItemLength", "primaryItemPadding", "secondItemLength", "secondItemPadding", "presentationTimestamp", "writeXmp", "(JIJIJ)V", "Ljava/io/FileDescriptor;", "fd", "Lcom/samsung/android/motionphoto/utils/ex/XMPInformation;", "calculateXmp", "(Ljava/io/FileDescriptor;)Lcom/samsung/android/motionphoto/utils/ex/XMPInformation;", "timestamp", "completeXmp", "(Ljava/io/FileDescriptor;J)V", "removeXmp", "()V", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface XmpComposer {
    XMPInformation calculateXmp(FileDescriptor fileDescriptor);

    void completeXmp(FileDescriptor fileDescriptor, long j2);

    void removeXmp();

    void reserveXmp(int i2, boolean z);

    void writeXmp(long j2, int i2, long j3, int i7, long j8);
}
