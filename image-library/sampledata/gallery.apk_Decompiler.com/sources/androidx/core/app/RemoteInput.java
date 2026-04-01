package androidx.core.app;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RemoteInput {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api20Impl {
        public static android.app.RemoteInput fromCompat(RemoteInput remoteInput) {
            throw null;
        }
    }

    public static android.app.RemoteInput[] fromCompat(RemoteInput[] remoteInputArr) {
        if (remoteInputArr == null) {
            return null;
        }
        android.app.RemoteInput[] remoteInputArr2 = new android.app.RemoteInput[remoteInputArr.length];
        for (int i2 = 0; i2 < remoteInputArr.length; i2++) {
            RemoteInput remoteInput = remoteInputArr[i2];
            remoteInputArr2[i2] = fromCompat((RemoteInput) null);
        }
        return remoteInputArr2;
    }

    public static android.app.RemoteInput fromCompat(RemoteInput remoteInput) {
        return Api20Impl.fromCompat(remoteInput);
    }
}
