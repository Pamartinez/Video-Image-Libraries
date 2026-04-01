package androidx.window.embedding;

import Tf.n;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0016\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0014J\b\u0010\u0019\u001a\u00020\u0006H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u001a"}, d2 = {"Landroidx/window/embedding/SplitPairFilter;", "", "primaryActivityName", "Landroid/content/ComponentName;", "secondaryActivityName", "secondaryActivityIntentAction", "", "(Landroid/content/ComponentName;Landroid/content/ComponentName;Ljava/lang/String;)V", "getPrimaryActivityName", "()Landroid/content/ComponentName;", "getSecondaryActivityIntentAction", "()Ljava/lang/String;", "getSecondaryActivityName", "equals", "", "other", "hashCode", "", "matchesActivityIntentPair", "primaryActivity", "Landroid/app/Activity;", "secondaryActivityIntent", "Landroid/content/Intent;", "matchesActivityPair", "secondaryActivity", "toString", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SplitPairFilter {
    private final ComponentName primaryActivityName;
    private final String secondaryActivityIntentAction;
    private final ComponentName secondaryActivityName;

    public SplitPairFilter(ComponentName componentName, ComponentName componentName2, String str) {
        j.e(componentName, "primaryActivityName");
        j.e(componentName2, "secondaryActivityName");
        this.primaryActivityName = componentName;
        this.secondaryActivityName = componentName2;
        this.secondaryActivityIntentAction = str;
        String packageName = componentName.getPackageName();
        j.d(packageName, "primaryActivityName.packageName");
        String className = componentName.getClassName();
        j.d(className, "primaryActivityName.className");
        String packageName2 = componentName2.getPackageName();
        j.d(packageName2, "secondaryActivityName.packageName");
        String className2 = componentName2.getClassName();
        j.d(className2, "secondaryActivityName.className");
        if (packageName.length() == 0 || packageName2.length() == 0) {
            throw new IllegalArgumentException("Package name must not be empty");
        } else if (className.length() == 0 || className2.length() == 0) {
            throw new IllegalArgumentException("Activity class name must not be empty.");
        } else if (n.u0(packageName, "*") && n.B0(packageName, "*", 0, 6) != packageName.length() - 1) {
            throw new IllegalArgumentException("Wildcard in package name is only allowed at the end.");
        } else if (n.u0(className, "*") && n.B0(className, "*", 0, 6) != className.length() - 1) {
            throw new IllegalArgumentException("Wildcard in class name is only allowed at the end.");
        } else if (n.u0(packageName2, "*") && n.B0(packageName2, "*", 0, 6) != packageName2.length() - 1) {
            throw new IllegalArgumentException("Wildcard in package name is only allowed at the end.");
        } else if (n.u0(className2, "*") && n.B0(className2, "*", 0, 6) != className2.length() - 1) {
            throw new IllegalArgumentException("Wildcard in class name is only allowed at the end.");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SplitPairFilter)) {
            return false;
        }
        SplitPairFilter splitPairFilter = (SplitPairFilter) obj;
        if (j.a(this.primaryActivityName, splitPairFilter.primaryActivityName) && j.a(this.secondaryActivityName, splitPairFilter.secondaryActivityName) && j.a(this.secondaryActivityIntentAction, splitPairFilter.secondaryActivityIntentAction)) {
            return true;
        }
        return false;
    }

    public final ComponentName getPrimaryActivityName() {
        return this.primaryActivityName;
    }

    public final String getSecondaryActivityIntentAction() {
        return this.secondaryActivityIntentAction;
    }

    public final ComponentName getSecondaryActivityName() {
        return this.secondaryActivityName;
    }

    public int hashCode() {
        int i2;
        int hashCode = (this.secondaryActivityName.hashCode() + (this.primaryActivityName.hashCode() * 31)) * 31;
        String str = this.secondaryActivityIntentAction;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        return hashCode + i2;
    }

    public final boolean matchesActivityIntentPair(Activity activity, Intent intent) {
        j.e(activity, "primaryActivity");
        j.e(intent, "secondaryActivityIntent");
        ComponentName componentName = activity.getComponentName();
        MatcherUtils matcherUtils = MatcherUtils.INSTANCE;
        if (!matcherUtils.areComponentsMatching$window_release(componentName, this.primaryActivityName) || !matcherUtils.areComponentsMatching$window_release(intent.getComponent(), this.secondaryActivityName)) {
            return false;
        }
        String str = this.secondaryActivityIntentAction;
        if (str == null || j.a(str, intent.getAction())) {
            return true;
        }
        return false;
    }

    public final boolean matchesActivityPair(Activity activity, Activity activity2) {
        boolean z;
        j.e(activity, "primaryActivity");
        j.e(activity2, "secondaryActivity");
        MatcherUtils matcherUtils = MatcherUtils.INSTANCE;
        if (!matcherUtils.areComponentsMatching$window_release(activity.getComponentName(), this.primaryActivityName) || !matcherUtils.areComponentsMatching$window_release(activity2.getComponentName(), this.secondaryActivityName)) {
            z = false;
        } else {
            z = true;
        }
        if (activity2.getIntent() == null) {
            return z;
        }
        if (z) {
            Intent intent = activity2.getIntent();
            j.d(intent, "secondaryActivity.intent");
            if (matchesActivityIntentPair(activity, intent)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "SplitPairFilter{primaryActivityName=" + this.primaryActivityName + ", secondaryActivityName=" + this.secondaryActivityName + ", secondaryActivityAction=" + this.secondaryActivityIntentAction + '}';
    }
}
