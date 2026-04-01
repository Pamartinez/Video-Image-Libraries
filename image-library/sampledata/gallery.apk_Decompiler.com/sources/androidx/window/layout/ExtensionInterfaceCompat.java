package androidx.window.layout;

import android.app.Activity;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b`\u0018\u00002\u00020\u0001:\u0001\u000fJ\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H&¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH&¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH&¢\u0006\u0004\b\u000e\u0010\r¨\u0006\u0010"}, d2 = {"Landroidx/window/layout/ExtensionInterfaceCompat;", "", "", "validateExtensionInterface", "()Z", "Landroidx/window/layout/ExtensionInterfaceCompat$ExtensionCallbackInterface;", "extensionCallback", "Lme/x;", "setExtensionCallback", "(Landroidx/window/layout/ExtensionInterfaceCompat$ExtensionCallbackInterface;)V", "Landroid/app/Activity;", "activity", "onWindowLayoutChangeListenerAdded", "(Landroid/app/Activity;)V", "onWindowLayoutChangeListenerRemoved", "ExtensionCallbackInterface", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ExtensionInterfaceCompat {

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/window/layout/ExtensionInterfaceCompat$ExtensionCallbackInterface;", "", "Landroid/app/Activity;", "activity", "Landroidx/window/layout/WindowLayoutInfo;", "newLayout", "Lme/x;", "onWindowLayoutChanged", "(Landroid/app/Activity;Landroidx/window/layout/WindowLayoutInfo;)V", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ExtensionCallbackInterface {
        void onWindowLayoutChanged(Activity activity, WindowLayoutInfo windowLayoutInfo);
    }

    void onWindowLayoutChangeListenerAdded(Activity activity);

    void onWindowLayoutChangeListenerRemoved(Activity activity);

    void setExtensionCallback(ExtensionCallbackInterface extensionCallbackInterface);

    boolean validateExtensionInterface();
}
