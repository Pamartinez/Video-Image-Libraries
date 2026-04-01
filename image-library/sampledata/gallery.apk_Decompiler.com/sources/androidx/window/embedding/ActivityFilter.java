package androidx.window.embedding;

import Tf.n;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u000e\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0005H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0017"}, d2 = {"Landroidx/window/embedding/ActivityFilter;", "", "componentName", "Landroid/content/ComponentName;", "intentAction", "", "(Landroid/content/ComponentName;Ljava/lang/String;)V", "getComponentName", "()Landroid/content/ComponentName;", "getIntentAction", "()Ljava/lang/String;", "equals", "", "other", "hashCode", "", "matchesActivity", "activity", "Landroid/app/Activity;", "matchesIntent", "intent", "Landroid/content/Intent;", "toString", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ActivityFilter {
    private final ComponentName componentName;
    private final String intentAction;

    public ActivityFilter(ComponentName componentName2, String str) {
        j.e(componentName2, "componentName");
        this.componentName = componentName2;
        this.intentAction = str;
        String packageName = componentName2.getPackageName();
        j.d(packageName, "componentName.packageName");
        String className = componentName2.getClassName();
        j.d(className, "componentName.className");
        if (packageName.length() <= 0) {
            throw new IllegalArgumentException("Package name must not be empty");
        } else if (className.length() <= 0) {
            throw new IllegalArgumentException("Activity class name must not be empty.");
        } else if (n.u0(packageName, "*") && n.B0(packageName, "*", 0, 6) != packageName.length() - 1) {
            throw new IllegalArgumentException("Wildcard in package name is only allowed at the end.");
        } else if (n.u0(className, "*") && n.B0(className, "*", 0, 6) != className.length() - 1) {
            throw new IllegalArgumentException("Wildcard in class name is only allowed at the end.");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActivityFilter)) {
            return false;
        }
        ActivityFilter activityFilter = (ActivityFilter) obj;
        if (j.a(this.componentName, activityFilter.componentName) && j.a(this.intentAction, activityFilter.intentAction)) {
            return true;
        }
        return false;
    }

    public final ComponentName getComponentName() {
        return this.componentName;
    }

    public final String getIntentAction() {
        return this.intentAction;
    }

    public int hashCode() {
        int i2;
        int hashCode = this.componentName.hashCode() * 31;
        String str = this.intentAction;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        return hashCode + i2;
    }

    public final boolean matchesActivity(Activity activity) {
        String str;
        j.e(activity, "activity");
        if (!MatcherUtils.INSTANCE.areActivityOrIntentComponentsMatching$window_release(activity, this.componentName)) {
            return false;
        }
        String str2 = this.intentAction;
        if (str2 == null) {
            return true;
        }
        Intent intent = activity.getIntent();
        if (intent == null) {
            str = null;
        } else {
            str = intent.getAction();
        }
        if (j.a(str2, str)) {
            return true;
        }
        return false;
    }

    public final boolean matchesIntent(Intent intent) {
        j.e(intent, "intent");
        if (!MatcherUtils.INSTANCE.areComponentsMatching$window_release(intent.getComponent(), this.componentName)) {
            return false;
        }
        String str = this.intentAction;
        if (str == null || j.a(str, intent.getAction())) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "ActivityFilter(componentName=" + this.componentName + ", intentAction=" + this.intentAction + ')';
    }
}
