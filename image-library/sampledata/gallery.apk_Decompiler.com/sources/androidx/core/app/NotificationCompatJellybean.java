package androidx.core.app;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class NotificationCompatJellybean {
    private static final Object sActionsLock = new Object();
    private static final Object sExtrasLock = new Object();

    public static Bundle getBundleForAction(NotificationCompat.Action action) {
        int i2;
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        IconCompat iconCompat = action.getIconCompat();
        if (iconCompat != null) {
            i2 = iconCompat.getResId();
        } else {
            i2 = 0;
        }
        bundle2.putInt("icon", i2);
        bundle2.putCharSequence("title", action.getTitle());
        bundle2.putParcelable("actionIntent", action.getActionIntent());
        if (action.getExtras() != null) {
            bundle = new Bundle(action.getExtras());
        } else {
            bundle = new Bundle();
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
        bundle2.putBundle("extras", bundle);
        bundle2.putParcelableArray("remoteInputs", toBundleArray(action.getRemoteInputs()));
        bundle2.putBoolean("showsUserInterface", action.getShowsUserInterface());
        bundle2.putInt("semanticAction", action.getSemanticAction());
        return bundle2;
    }

    private static Bundle toBundle(RemoteInput remoteInput) {
        new Bundle();
        throw null;
    }

    private static Bundle[] toBundleArray(RemoteInput[] remoteInputArr) {
        if (remoteInputArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[remoteInputArr.length];
        for (int i2 = 0; i2 < remoteInputArr.length; i2++) {
            RemoteInput remoteInput = remoteInputArr[i2];
            bundleArr[i2] = toBundle((RemoteInput) null);
        }
        return bundleArr;
    }
}
