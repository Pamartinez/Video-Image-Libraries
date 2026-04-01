package androidx.window.embedding;

import A4.I;
import android.app.Activity;
import android.content.Intent;
import android.util.Pair;
import android.view.WindowMetrics;
import androidx.window.extensions.embedding.ActivityRule;
import androidx.window.extensions.embedding.ActivityStack;
import androidx.window.extensions.embedding.EmbeddingRule;
import androidx.window.extensions.embedding.SplitInfo;
import androidx.window.extensions.embedding.SplitPairRule;
import androidx.window.extensions.embedding.SplitPlaceholderRule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0007J\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tJ(\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f0\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\tH\u0007J(\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u000f0\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\tH\u0007J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u000e2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\tH\u0007J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\u000e2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\tH\u0007J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u000e2\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J*\u0010\u001d\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u001e\"\u0004\b\u0001\u0010\u001f*\u000e\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H\u001f0\u000fH\u0002¢\u0006\u0002\u0010 J*\u0010!\u001a\u0002H\u001f\"\u0004\b\u0000\u0010\u001e\"\u0004\b\u0001\u0010\u001f*\u000e\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H\u001f0\u000fH\u0002¢\u0006\u0002\u0010 ¨\u0006\""}, d2 = {"Landroidx/window/embedding/EmbeddingAdapter;", "", "()V", "translate", "Landroidx/window/embedding/SplitInfo;", "splitInfo", "Landroidx/window/extensions/embedding/SplitInfo;", "", "splitInfoList", "", "Landroidx/window/extensions/embedding/EmbeddingRule;", "rules", "Landroidx/window/embedding/EmbeddingRule;", "translateActivityIntentPredicates", "Ljava/util/function/Predicate;", "Landroid/util/Pair;", "Landroid/app/Activity;", "Landroid/content/Intent;", "splitPairFilters", "Landroidx/window/embedding/SplitPairFilter;", "translateActivityPairPredicates", "translateActivityPredicates", "activityFilters", "Landroidx/window/embedding/ActivityFilter;", "translateIntentPredicates", "translateParentMetricsPredicate", "Landroid/view/WindowMetrics;", "splitRule", "Landroidx/window/embedding/SplitRule;", "component1", "F", "S", "(Landroid/util/Pair;)Ljava/lang/Object;", "component2", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EmbeddingAdapter {
    private final <F, S> F component1(Pair<F, S> pair) {
        j.e(pair, "<this>");
        return pair.first;
    }

    private final <F, S> S component2(Pair<F, S> pair) {
        j.e(pair, "<this>");
        return pair.second;
    }

    /* access modifiers changed from: private */
    /* renamed from: translateActivityIntentPredicates$lambda-3  reason: not valid java name */
    public static final boolean m26translateActivityIntentPredicates$lambda3(EmbeddingAdapter embeddingAdapter, Set set, Pair pair) {
        j.e(embeddingAdapter, "this$0");
        j.e(set, "$splitPairFilters");
        j.d(pair, "(first, second)");
        Activity activity = (Activity) embeddingAdapter.component1(pair);
        Intent intent = (Intent) embeddingAdapter.component2(pair);
        if (set.isEmpty()) {
            return false;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (((SplitPairFilter) it.next()).matchesActivityIntentPair(activity, intent)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: translateActivityPairPredicates$lambda-1  reason: not valid java name */
    public static final boolean m27translateActivityPairPredicates$lambda1(EmbeddingAdapter embeddingAdapter, Set set, Pair pair) {
        j.e(embeddingAdapter, "this$0");
        j.e(set, "$splitPairFilters");
        j.d(pair, "(first, second)");
        Activity activity = (Activity) embeddingAdapter.component1(pair);
        Activity activity2 = (Activity) embeddingAdapter.component2(pair);
        if (set.isEmpty()) {
            return false;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (((SplitPairFilter) it.next()).matchesActivityPair(activity, activity2)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: translateActivityPredicates$lambda-6  reason: not valid java name */
    public static final boolean m28translateActivityPredicates$lambda6(Set set, Activity activity) {
        j.e(set, "$activityFilters");
        if (set.isEmpty()) {
            return false;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            j.d(activity, "activity");
            if (((ActivityFilter) it.next()).matchesActivity(activity)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: translateIntentPredicates$lambda-8  reason: not valid java name */
    public static final boolean m29translateIntentPredicates$lambda8(Set set, Intent intent) {
        j.e(set, "$activityFilters");
        if (set.isEmpty()) {
            return false;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            j.d(intent, "intent");
            if (((ActivityFilter) it.next()).matchesIntent(intent)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: translateParentMetricsPredicate$lambda-4  reason: not valid java name */
    public static final boolean m30translateParentMetricsPredicate$lambda4(SplitRule splitRule, WindowMetrics windowMetrics) {
        j.e(splitRule, "$splitRule");
        j.d(windowMetrics, "windowMetrics");
        return splitRule.checkParentMetrics(windowMetrics);
    }

    public final List<SplitInfo> translate(List<? extends SplitInfo> list) {
        j.e(list, "splitInfoList");
        Iterable<SplitInfo> iterable = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (SplitInfo translate : iterable) {
            arrayList.add(translate(translate));
        }
        return arrayList;
    }

    public final Predicate<Pair<Activity, Intent>> translateActivityIntentPredicates(Set<SplitPairFilter> set) {
        j.e(set, "splitPairFilters");
        return new b(this, set, 1);
    }

    public final Predicate<Pair<Activity, Activity>> translateActivityPairPredicates(Set<SplitPairFilter> set) {
        j.e(set, "splitPairFilters");
        return new b(this, set, 0);
    }

    public final Predicate<Activity> translateActivityPredicates(Set<ActivityFilter> set) {
        j.e(set, "activityFilters");
        return new a(1, set);
    }

    public final Predicate<Intent> translateIntentPredicates(Set<ActivityFilter> set) {
        j.e(set, "activityFilters");
        return new a(0, set);
    }

    public final Predicate<WindowMetrics> translateParentMetricsPredicate(SplitRule splitRule) {
        j.e(splitRule, "splitRule");
        return new I(13, splitRule);
    }

    private final SplitInfo translate(SplitInfo splitInfo) {
        boolean z;
        ActivityStack primaryActivityStack = splitInfo.getPrimaryActivityStack();
        j.d(primaryActivityStack, "splitInfo.primaryActivityStack");
        boolean z3 = false;
        try {
            z = primaryActivityStack.isEmpty();
        } catch (NoSuchMethodError unused) {
            z = false;
        }
        List activities = primaryActivityStack.getActivities();
        j.d(activities, "primaryActivityStack.activities");
        ActivityStack activityStack = new ActivityStack(activities, z);
        ActivityStack secondaryActivityStack = splitInfo.getSecondaryActivityStack();
        j.d(secondaryActivityStack, "splitInfo.secondaryActivityStack");
        try {
            z3 = secondaryActivityStack.isEmpty();
        } catch (NoSuchMethodError unused2) {
        }
        List activities2 = secondaryActivityStack.getActivities();
        j.d(activities2, "secondaryActivityStack.activities");
        return new SplitInfo(activityStack, new ActivityStack(activities2, z3), splitInfo.getSplitRatio());
    }

    public final Set<EmbeddingRule> translate(Set<? extends EmbeddingRule> set) {
        SplitPairRule splitPairRule;
        j.e(set, "rules");
        ArrayList arrayList = new ArrayList(C1196n.w0(set, 10));
        for (EmbeddingRule embeddingRule : set) {
            if (embeddingRule instanceof SplitPairRule) {
                SplitPairRule splitPairRule2 = (SplitPairRule) embeddingRule;
                splitPairRule = new SplitPairRule.Builder(translateActivityPairPredicates(splitPairRule2.getFilters()), translateActivityIntentPredicates(splitPairRule2.getFilters()), translateParentMetricsPredicate((SplitRule) embeddingRule)).setSplitRatio(splitPairRule2.getSplitRatio()).setLayoutDirection(splitPairRule2.getLayoutDirection()).setShouldFinishPrimaryWithSecondary(splitPairRule2.getFinishPrimaryWithSecondary()).setShouldFinishSecondaryWithPrimary(splitPairRule2.getFinishSecondaryWithPrimary()).setShouldClearTop(splitPairRule2.getClearTop()).build();
                j.d(splitPairRule, "SplitPairRuleBuilder(\n  …                 .build()");
            } else if (embeddingRule instanceof SplitPlaceholderRule) {
                SplitPlaceholderRule splitPlaceholderRule = (SplitPlaceholderRule) embeddingRule;
                splitPairRule = new SplitPlaceholderRule.Builder(splitPlaceholderRule.getPlaceholderIntent(), translateActivityPredicates(splitPlaceholderRule.getFilters()), translateIntentPredicates(splitPlaceholderRule.getFilters()), translateParentMetricsPredicate((SplitRule) embeddingRule)).setSplitRatio(splitPlaceholderRule.getSplitRatio()).setLayoutDirection(splitPlaceholderRule.getLayoutDirection()).build();
                j.d(splitPairRule, "SplitPlaceholderRuleBuil…                 .build()");
            } else if (embeddingRule instanceof ActivityRule) {
                ActivityRule activityRule = (ActivityRule) embeddingRule;
                splitPairRule = new ActivityRule.Builder(translateActivityPredicates(activityRule.getFilters()), translateIntentPredicates(activityRule.getFilters())).setShouldAlwaysExpand(activityRule.getAlwaysExpand()).build();
                j.d(splitPairRule, "ActivityRuleBuilder(\n   …                 .build()");
            } else {
                throw new IllegalArgumentException("Unsupported rule type");
            }
            arrayList.add((EmbeddingRule) splitPairRule);
        }
        return C1194l.p1(arrayList);
    }
}
