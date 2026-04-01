package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import androidx.core.content.ContextCompat;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B+\b\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fB\u0011\b\u0010\u0012\u0006\u0010\u0004\u001a\u00020\r¢\u0006\u0004\b\u000b\u0010\u000eJ9\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0015H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00028\u0000H&¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001f\u0010 J1\u0010(\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020!2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\t2\b\u0010'\u001a\u0004\u0018\u00010&H\u0016¢\u0006\u0004\b(\u0010)R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0007¢\u0006\f\n\u0004\b\u0004\u0010*\u001a\u0004\b+\u0010,R\u0017\u0010\u0006\u001a\u00020\u00058\u0007¢\u0006\f\n\u0004\b\u0006\u0010-\u001a\u0004\b.\u0010/R\u0017\u0010\b\u001a\u00020\u00078\u0007¢\u0006\f\n\u0004\b\b\u00100\u001a\u0004\b1\u00102R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0004¢\u0006\u0006\n\u0004\b\n\u00103R\u0017\u00105\u001a\u0002048G¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108¨\u00069"}, d2 = {"Landroidx/fragment/app/FragmentHostCallback;", "H", "Landroidx/fragment/app/FragmentContainer;", "Landroid/app/Activity;", "activity", "Landroid/content/Context;", "context", "Landroid/os/Handler;", "handler", "", "windowAnimations", "<init>", "(Landroid/app/Activity;Landroid/content/Context;Landroid/os/Handler;I)V", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "", "prefix", "Ljava/io/FileDescriptor;", "fd", "Ljava/io/PrintWriter;", "writer", "", "args", "Lme/x;", "onDump", "(Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V", "Landroid/view/LayoutInflater;", "onGetLayoutInflater", "()Landroid/view/LayoutInflater;", "onGetHost", "()Ljava/lang/Object;", "onSupportInvalidateOptionsMenu", "()V", "Landroidx/fragment/app/Fragment;", "fragment", "Landroid/content/Intent;", "intent", "requestCode", "Landroid/os/Bundle;", "options", "onStartActivityFromFragment", "(Landroidx/fragment/app/Fragment;Landroid/content/Intent;ILandroid/os/Bundle;)V", "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "I", "Landroidx/fragment/app/FragmentManager;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "getFragmentManager", "()Landroidx/fragment/app/FragmentManager;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FragmentHostCallback<H> extends FragmentContainer {
    private final Activity activity;
    private final Context context;
    private final FragmentManager fragmentManager;
    private final Handler handler;
    private final int windowAnimations;

    public FragmentHostCallback(Activity activity2, Context context2, Handler handler2, int i2) {
        j.e(context2, "context");
        j.e(handler2, "handler");
        this.activity = activity2;
        this.context = context2;
        this.handler = handler2;
        this.windowAnimations = i2;
        this.fragmentManager = new FragmentManagerImpl();
    }

    public final Activity getActivity() {
        return this.activity;
    }

    public final Context getContext() {
        return this.context;
    }

    public final FragmentManager getFragmentManager() {
        return this.fragmentManager;
    }

    public final Handler getHandler() {
        return this.handler;
    }

    public abstract void onDump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract H onGetHost();

    public abstract LayoutInflater onGetLayoutInflater();

    public void onStartActivityFromFragment(Fragment fragment, Intent intent, int i2, Bundle bundle) {
        j.e(fragment, "fragment");
        j.e(intent, "intent");
        if (i2 == -1) {
            ContextCompat.startActivity(this.context, intent, bundle);
            return;
        }
        throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
    }

    public abstract void onSupportInvalidateOptionsMenu();

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FragmentHostCallback(FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, new Handler(), 0);
        j.e(fragmentActivity, "activity");
    }
}
