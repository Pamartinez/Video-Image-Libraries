package androidx.window.embedding;

import Tf.n;
import Tf.v;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\fJ\u001f\u0010\r\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\u000fJ\u0018\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/window/embedding/MatcherUtils;", "", "()V", "sDebugMatchers", "", "sMatchersTag", "", "areActivityOrIntentComponentsMatching", "activity", "Landroid/app/Activity;", "ruleComponent", "Landroid/content/ComponentName;", "areActivityOrIntentComponentsMatching$window_release", "areComponentsMatching", "activityComponent", "areComponentsMatching$window_release", "wildcardMatch", "name", "pattern", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MatcherUtils {
    public static final MatcherUtils INSTANCE = new MatcherUtils();
    public static final boolean sDebugMatchers = false;
    public static final String sMatchersTag = "SplitRuleResolution";

    private MatcherUtils() {
    }

    private final boolean wildcardMatch(String str, String str2) {
        if (!n.u0(str2, "*")) {
            return false;
        }
        if (str2.equals("*")) {
            return true;
        }
        if (n.B0(str2, "*", 0, 6) != n.E0(0, 6, str2, "*") || !v.o0(str2, "*")) {
            throw new IllegalArgumentException("Name pattern with a wildcard must only contain a single wildcard in the end");
        }
        String substring = str2.substring(0, str2.length() - 1);
        j.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return v.t0(str, substring);
    }

    public final boolean areActivityOrIntentComponentsMatching$window_release(Activity activity, ComponentName componentName) {
        ComponentName component;
        j.e(activity, "activity");
        j.e(componentName, "ruleComponent");
        if (areComponentsMatching$window_release(activity.getComponentName(), componentName)) {
            return true;
        }
        Intent intent = activity.getIntent();
        if (intent == null || (component = intent.getComponent()) == null) {
            return false;
        }
        return INSTANCE.areComponentsMatching$window_release(component, componentName);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0086 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0089 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean areComponentsMatching$window_release(android.content.ComponentName r6, android.content.ComponentName r7) {
        /*
            r5 = this;
            java.lang.String r0 = "ruleComponent"
            kotlin.jvm.internal.j.e(r7, r0)
            java.lang.String r0 = "*"
            r1 = 1
            r2 = 0
            if (r6 != 0) goto L_0x0021
            java.lang.String r5 = r7.getPackageName()
            boolean r5 = kotlin.jvm.internal.j.a(r5, r0)
            if (r5 == 0) goto L_0x0089
            java.lang.String r5 = r7.getClassName()
            boolean r5 = kotlin.jvm.internal.j.a(r5, r0)
            if (r5 == 0) goto L_0x0089
            goto L_0x0088
        L_0x0021:
            java.lang.String r3 = r6.toString()
            java.lang.String r4 = "activityComponent.toString()"
            kotlin.jvm.internal.j.d(r3, r4)
            boolean r0 = Tf.n.u0(r3, r0)
            if (r0 != 0) goto L_0x008a
            java.lang.String r0 = r6.getPackageName()
            java.lang.String r3 = r7.getPackageName()
            boolean r0 = kotlin.jvm.internal.j.a(r0, r3)
            if (r0 != 0) goto L_0x0059
            java.lang.String r0 = r6.getPackageName()
            java.lang.String r3 = "activityComponent.packageName"
            kotlin.jvm.internal.j.d(r0, r3)
            java.lang.String r3 = r7.getPackageName()
            java.lang.String r4 = "ruleComponent.packageName"
            kotlin.jvm.internal.j.d(r3, r4)
            boolean r0 = r5.wildcardMatch(r0, r3)
            if (r0 == 0) goto L_0x0057
            goto L_0x0059
        L_0x0057:
            r0 = r2
            goto L_0x005a
        L_0x0059:
            r0 = r1
        L_0x005a:
            java.lang.String r3 = r6.getClassName()
            java.lang.String r4 = r7.getClassName()
            boolean r3 = kotlin.jvm.internal.j.a(r3, r4)
            if (r3 != 0) goto L_0x0083
            java.lang.String r6 = r6.getClassName()
            java.lang.String r3 = "activityComponent.className"
            kotlin.jvm.internal.j.d(r6, r3)
            java.lang.String r7 = r7.getClassName()
            java.lang.String r3 = "ruleComponent.className"
            kotlin.jvm.internal.j.d(r7, r3)
            boolean r5 = r5.wildcardMatch(r6, r7)
            if (r5 == 0) goto L_0x0081
            goto L_0x0083
        L_0x0081:
            r5 = r2
            goto L_0x0084
        L_0x0083:
            r5 = r1
        L_0x0084:
            if (r0 == 0) goto L_0x0089
            if (r5 == 0) goto L_0x0089
        L_0x0088:
            return r1
        L_0x0089:
            return r2
        L_0x008a:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Wildcard can only be part of the rule."
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.MatcherUtils.areComponentsMatching$window_release(android.content.ComponentName, android.content.ComponentName):boolean");
    }
}
