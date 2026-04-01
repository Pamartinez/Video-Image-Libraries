package androidx.core.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.view.inputmethod.InputContentInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class InputContentInfoCompat {
    private final InputContentInfoCompatImpl mImpl;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class InputContentInfoCompatApi25Impl implements InputContentInfoCompatImpl {
        final InputContentInfo mObject;

        public InputContentInfoCompatApi25Impl(Object obj) {
            this.mObject = (InputContentInfo) obj;
        }

        public Uri getContentUri() {
            return this.mObject.getContentUri();
        }

        public ClipDescription getDescription() {
            return this.mObject.getDescription();
        }

        public Object getInputContentInfo() {
            return this.mObject;
        }

        public Uri getLinkUri() {
            return this.mObject.getLinkUri();
        }

        public void requestPermission() {
            this.mObject.requestPermission();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface InputContentInfoCompatImpl {
        Uri getContentUri();

        ClipDescription getDescription();

        Object getInputContentInfo();

        Uri getLinkUri();

        void requestPermission();
    }

    private InputContentInfoCompat(InputContentInfoCompatImpl inputContentInfoCompatImpl) {
        this.mImpl = inputContentInfoCompatImpl;
    }

    public static InputContentInfoCompat wrap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new InputContentInfoCompat(new InputContentInfoCompatApi25Impl(obj));
    }

    public Uri getContentUri() {
        return this.mImpl.getContentUri();
    }

    public ClipDescription getDescription() {
        return this.mImpl.getDescription();
    }

    public Uri getLinkUri() {
        return this.mImpl.getLinkUri();
    }

    public void requestPermission() {
        this.mImpl.requestPermission();
    }

    public Object unwrap() {
        return this.mImpl.getInputContentInfo();
    }
}
