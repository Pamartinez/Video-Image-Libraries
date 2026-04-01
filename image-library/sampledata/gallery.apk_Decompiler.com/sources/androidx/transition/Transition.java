package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.graphics.Path;
import android.os.Build;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowId;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Consumer;
import androidx.core.view.ViewCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Transition implements Cloneable {
    private static final int[] DEFAULT_MATCH_ORDER = {2, 1, 3, 4};
    private static final Animator[] EMPTY_ANIMATOR_ARRAY = new Animator[0];
    private static final PathMotion STRAIGHT_PATH_MOTION = new PathMotion() {
        public Path getPath(float f, float f5, float f8, float f10) {
            Path path = new Path();
            path.moveTo(f, f5);
            path.lineTo(f8, f10);
            return path;
        }
    };
    private static ThreadLocal<ArrayMap<Animator, AnimationInfo>> sRunningAnimators = new ThreadLocal<>();
    private Animator[] mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
    ArrayList<Animator> mAnimators = new ArrayList<>();
    boolean mCanRemoveViews = false;
    /* access modifiers changed from: private */
    public Transition mCloneParent = null;
    ArrayList<Animator> mCurrentAnimators = new ArrayList<>();
    long mDuration = -1;
    private TransitionValuesMaps mEndValues = new TransitionValuesMaps();
    private ArrayList<TransitionValues> mEndValuesList;
    boolean mEnded = false;
    private EpicenterCallback mEpicenterCallback;
    private TimeInterpolator mInterpolator = null;
    private ArrayList<TransitionListener> mListeners = null;
    private TransitionListener[] mListenersCache;
    private int[] mMatchOrder = DEFAULT_MATCH_ORDER;
    private String mName = getClass().getName();
    private ArrayMap<String, String> mNameOverrides;
    int mNumInstances = 0;
    TransitionSet mParent = null;
    private PathMotion mPathMotion = STRAIGHT_PATH_MOTION;
    private boolean mPaused = false;
    SeekController mSeekController;
    long mSeekOffsetInParent;
    private long mStartDelay = -1;
    private TransitionValuesMaps mStartValues = new TransitionValuesMaps();
    private ArrayList<TransitionValues> mStartValuesList;
    private ArrayList<View> mTargetChildExcludes = null;
    private ArrayList<View> mTargetExcludes = null;
    private ArrayList<Integer> mTargetIdChildExcludes = null;
    private ArrayList<Integer> mTargetIdExcludes = null;
    ArrayList<Integer> mTargetIds = new ArrayList<>();
    private ArrayList<String> mTargetNameExcludes = null;
    private ArrayList<String> mTargetNames = null;
    private ArrayList<Class<?>> mTargetTypeChildExcludes = null;
    private ArrayList<Class<?>> mTargetTypeExcludes = null;
    private ArrayList<Class<?>> mTargetTypes = null;
    ArrayList<View> mTargets = new ArrayList<>();
    long mTotalDuration;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AnimationInfo {
        Animator mAnimator;
        String mName;
        Transition mTransition;
        TransitionValues mValues;
        View mView;
        WindowId mWindowId;

        public AnimationInfo(View view, String str, Transition transition, WindowId windowId, TransitionValues transitionValues, Animator animator) {
            this.mView = view;
            this.mName = str;
            this.mValues = transitionValues;
            this.mWindowId = windowId;
            this.mTransition = transition;
            this.mAnimator = animator;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class EpicenterCallback {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Impl26 {
        public static long getTotalDuration(Animator animator) {
            return animator.getTotalDuration();
        }

        public static void setCurrentPlayTime(Animator animator, long j2) {
            ((AnimatorSet) animator).setCurrentPlayTime(j2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SeekController extends TransitionListenerAdapter implements TransitionSeekController, DynamicAnimation.OnAnimationUpdateListener {
        private long mCurrentPlayTime = -1;
        private boolean mIsCanceled;
        private boolean mIsReady;
        private Consumer<TransitionSeekController>[] mListenerCache = null;
        private ArrayList<Consumer<TransitionSeekController>> mOnProgressListeners = null;
        private ArrayList<Consumer<TransitionSeekController>> mOnReadyListeners = null;
        private Runnable mResetToStartState;
        private SpringAnimation mSpringAnimation;
        private final VelocityTracker1D mVelocityTracker = new VelocityTracker1D();

        public SeekController() {
        }

        private void callProgressListeners() {
            ArrayList<Consumer<TransitionSeekController>> arrayList = this.mOnProgressListeners;
            if (arrayList != null && !arrayList.isEmpty()) {
                int size = this.mOnProgressListeners.size();
                if (this.mListenerCache == null) {
                    this.mListenerCache = new Consumer[size];
                }
                Consumer<TransitionSeekController>[] consumerArr = (Consumer[]) this.mOnProgressListeners.toArray(this.mListenerCache);
                this.mListenerCache = null;
                for (int i2 = 0; i2 < size; i2++) {
                    consumerArr[i2].accept(this);
                    consumerArr[i2] = null;
                }
                this.mListenerCache = consumerArr;
            }
        }

        private void ensureAnimation() {
            if (this.mSpringAnimation == null) {
                this.mVelocityTracker.addDataPoint(AnimationUtils.currentAnimationTimeMillis(), (float) this.mCurrentPlayTime);
                this.mSpringAnimation = new SpringAnimation(new FloatValueHolder());
                SpringForce springForce = new SpringForce();
                springForce.setDampingRatio(1.0f);
                springForce.setStiffness(200.0f);
                this.mSpringAnimation.setSpring(springForce);
                this.mSpringAnimation.setStartValue((float) this.mCurrentPlayTime);
                this.mSpringAnimation.addUpdateListener(this);
                this.mSpringAnimation.setStartVelocity(this.mVelocityTracker.calculateVelocity());
                this.mSpringAnimation.setMaxValue((float) (getDurationMillis() + 1));
                this.mSpringAnimation.setMinValue(-1.0f);
                this.mSpringAnimation.setMinimumVisibleChange(4.0f);
                this.mSpringAnimation.addEndListener(new a(this));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$ensureAnimation$0(DynamicAnimation dynamicAnimation, boolean z, float f, float f5) {
            if (z) {
                return;
            }
            if (f < 1.0f) {
                long durationMillis = getDurationMillis();
                Transition transitionAt = ((TransitionSet) Transition.this).getTransitionAt(0);
                Transition access$000 = transitionAt.mCloneParent;
                Transition unused = transitionAt.mCloneParent = null;
                Transition.this.setCurrentPlayTimeMillis(-1, this.mCurrentPlayTime);
                Transition.this.setCurrentPlayTimeMillis(durationMillis, -1);
                this.mCurrentPlayTime = durationMillis;
                Runnable runnable = this.mResetToStartState;
                if (runnable != null) {
                    runnable.run();
                }
                Transition.this.mAnimators.clear();
                if (access$000 != null) {
                    access$000.notifyListeners(TransitionNotification.ON_END, true);
                    return;
                }
                return;
            }
            Transition.this.notifyListeners(TransitionNotification.ON_END, false);
        }

        public void animateToEnd() {
            ensureAnimation();
            this.mSpringAnimation.animateToFinalPosition((float) (getDurationMillis() + 1));
        }

        public void animateToStart(Runnable runnable) {
            this.mResetToStartState = runnable;
            ensureAnimation();
            this.mSpringAnimation.animateToFinalPosition(0.0f);
        }

        public long getDurationMillis() {
            return Transition.this.getTotalDurationMillis();
        }

        public void initPlayTime() {
            long j2 = 0;
            if (getDurationMillis() == 0) {
                j2 = 1;
            }
            Transition.this.setCurrentPlayTimeMillis(j2, this.mCurrentPlayTime);
            this.mCurrentPlayTime = j2;
        }

        public boolean isReady() {
            return this.mIsReady;
        }

        public void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f5) {
            long max = Math.max(-1, Math.min(getDurationMillis() + 1, Math.round((double) f)));
            Transition.this.setCurrentPlayTimeMillis(max, this.mCurrentPlayTime);
            this.mCurrentPlayTime = max;
            callProgressListeners();
        }

        public void onTransitionCancel(Transition transition) {
            this.mIsCanceled = true;
        }

        public void ready() {
            this.mIsReady = true;
            ArrayList<Consumer<TransitionSeekController>> arrayList = this.mOnReadyListeners;
            if (arrayList != null) {
                this.mOnReadyListeners = null;
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    arrayList.get(i2).accept(this);
                }
            }
            callProgressListeners();
        }

        public void setCurrentPlayTimeMillis(long j2) {
            if (this.mSpringAnimation != null) {
                throw new IllegalStateException("setCurrentPlayTimeMillis() called after animation has been started");
            } else if (j2 != this.mCurrentPlayTime && isReady()) {
                if (!this.mIsCanceled) {
                    if (j2 != 0 || this.mCurrentPlayTime <= 0) {
                        long durationMillis = getDurationMillis();
                        if (j2 == durationMillis && this.mCurrentPlayTime < durationMillis) {
                            j2 = 1 + durationMillis;
                        }
                    } else {
                        j2 = -1;
                    }
                    long j3 = this.mCurrentPlayTime;
                    if (j2 != j3) {
                        Transition.this.setCurrentPlayTimeMillis(j2, j3);
                        this.mCurrentPlayTime = j2;
                    }
                }
                callProgressListeners();
                this.mVelocityTracker.addDataPoint(AnimationUtils.currentAnimationTimeMillis(), (float) j2);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface TransitionListener {
        void onTransitionCancel(Transition transition);

        void onTransitionEnd(Transition transition);

        void onTransitionEnd(Transition transition, boolean z) {
            onTransitionEnd(transition);
        }

        void onTransitionPause(Transition transition);

        void onTransitionResume(Transition transition);

        void onTransitionStart(Transition transition);

        void onTransitionStart(Transition transition, boolean z) {
            onTransitionStart(transition);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface TransitionNotification {
        public static final TransitionNotification ON_CANCEL = new b(2);
        public static final TransitionNotification ON_END = new b(1);
        public static final TransitionNotification ON_PAUSE = new b(3);
        public static final TransitionNotification ON_RESUME = new b(4);
        public static final TransitionNotification ON_START = new b(0);

        void notifyListener(TransitionListener transitionListener, Transition transition, boolean z);
    }

    private void addUnmatched(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2) {
        for (int i2 = 0; i2 < arrayMap.size(); i2++) {
            TransitionValues valueAt = arrayMap.valueAt(i2);
            if (isValidTarget(valueAt.view)) {
                this.mStartValuesList.add(valueAt);
                this.mEndValuesList.add((Object) null);
            }
        }
        for (int i7 = 0; i7 < arrayMap2.size(); i7++) {
            TransitionValues valueAt2 = arrayMap2.valueAt(i7);
            if (isValidTarget(valueAt2.view)) {
                this.mEndValuesList.add(valueAt2);
                this.mStartValuesList.add((Object) null);
            }
        }
    }

    private static void addViewValues(TransitionValuesMaps transitionValuesMaps, View view, TransitionValues transitionValues) {
        transitionValuesMaps.mViewValues.put(view, transitionValues);
        int id = view.getId();
        if (id >= 0) {
            if (transitionValuesMaps.mIdValues.indexOfKey(id) >= 0) {
                transitionValuesMaps.mIdValues.put(id, (Object) null);
            } else {
                transitionValuesMaps.mIdValues.put(id, view);
            }
        }
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            if (transitionValuesMaps.mNameValues.containsKey(transitionName)) {
                transitionValuesMaps.mNameValues.put(transitionName, null);
            } else {
                transitionValuesMaps.mNameValues.put(transitionName, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (transitionValuesMaps.mItemIdValues.indexOfKey(itemIdAtPosition) >= 0) {
                    View view2 = transitionValuesMaps.mItemIdValues.get(itemIdAtPosition);
                    if (view2 != null) {
                        view2.setHasTransientState(false);
                        transitionValuesMaps.mItemIdValues.put(itemIdAtPosition, null);
                        return;
                    }
                    return;
                }
                view.setHasTransientState(true);
                transitionValuesMaps.mItemIdValues.put(itemIdAtPosition, view);
            }
        }
    }

    private void captureHierarchy(View view, boolean z) {
        if (view != null) {
            int id = view.getId();
            ArrayList<Integer> arrayList = this.mTargetIdExcludes;
            if (arrayList == null || !arrayList.contains(Integer.valueOf(id))) {
                ArrayList<View> arrayList2 = this.mTargetExcludes;
                if (arrayList2 == null || !arrayList2.contains(view)) {
                    ArrayList<Class<?>> arrayList3 = this.mTargetTypeExcludes;
                    if (arrayList3 != null) {
                        int size = arrayList3.size();
                        int i2 = 0;
                        while (i2 < size) {
                            if (!this.mTargetTypeExcludes.get(i2).isInstance(view)) {
                                i2++;
                            } else {
                                return;
                            }
                        }
                    }
                    if (view.getParent() instanceof ViewGroup) {
                        TransitionValues transitionValues = new TransitionValues(view);
                        if (z) {
                            captureStartValues(transitionValues);
                        } else {
                            captureEndValues(transitionValues);
                        }
                        transitionValues.mTargetedTransitions.add(this);
                        capturePropagationValues(transitionValues);
                        if (z) {
                            addViewValues(this.mStartValues, view, transitionValues);
                        } else {
                            addViewValues(this.mEndValues, view, transitionValues);
                        }
                    }
                    if (view instanceof ViewGroup) {
                        ArrayList<Integer> arrayList4 = this.mTargetIdChildExcludes;
                        if (arrayList4 == null || !arrayList4.contains(Integer.valueOf(id))) {
                            ArrayList<View> arrayList5 = this.mTargetChildExcludes;
                            if (arrayList5 == null || !arrayList5.contains(view)) {
                                ArrayList<Class<?>> arrayList6 = this.mTargetTypeChildExcludes;
                                if (arrayList6 != null) {
                                    int size2 = arrayList6.size();
                                    int i7 = 0;
                                    while (i7 < size2) {
                                        if (!this.mTargetTypeChildExcludes.get(i7).isInstance(view)) {
                                            i7++;
                                        } else {
                                            return;
                                        }
                                    }
                                }
                                ViewGroup viewGroup = (ViewGroup) view;
                                for (int i8 = 0; i8 < viewGroup.getChildCount(); i8++) {
                                    captureHierarchy(viewGroup.getChildAt(i8), z);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static ArrayMap<Animator, AnimationInfo> getRunningAnimators() {
        ArrayMap<Animator, AnimationInfo> arrayMap = sRunningAnimators.get();
        if (arrayMap != null) {
            return arrayMap;
        }
        ArrayMap<Animator, AnimationInfo> arrayMap2 = new ArrayMap<>();
        sRunningAnimators.set(arrayMap2);
        return arrayMap2;
    }

    private static boolean isValueChanged(TransitionValues transitionValues, TransitionValues transitionValues2, String str) {
        Object obj = transitionValues.values.get(str);
        Object obj2 = transitionValues2.values.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return !obj.equals(obj2);
    }

    private void matchIds(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            View valueAt = sparseArray.valueAt(i2);
            if (valueAt != null && isValidTarget(valueAt) && (view = sparseArray2.get(sparseArray.keyAt(i2))) != null && isValidTarget(view)) {
                TransitionValues transitionValues = arrayMap.get(valueAt);
                TransitionValues transitionValues2 = arrayMap2.get(view);
                if (!(transitionValues == null || transitionValues2 == null)) {
                    this.mStartValuesList.add(transitionValues);
                    this.mEndValuesList.add(transitionValues2);
                    arrayMap.remove(valueAt);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    private void matchInstances(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2) {
        TransitionValues remove;
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            View keyAt = arrayMap.keyAt(size);
            if (keyAt != null && isValidTarget(keyAt) && (remove = arrayMap2.remove(keyAt)) != null && isValidTarget(remove.view)) {
                this.mStartValuesList.add(arrayMap.removeAt(size));
                this.mEndValuesList.add(remove);
            }
        }
    }

    private void matchItemIds(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2, LongSparseArray<View> longSparseArray, LongSparseArray<View> longSparseArray2) {
        View view;
        int size = longSparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            View valueAt = longSparseArray.valueAt(i2);
            if (valueAt != null && isValidTarget(valueAt) && (view = longSparseArray2.get(longSparseArray.keyAt(i2))) != null && isValidTarget(view)) {
                TransitionValues transitionValues = arrayMap.get(valueAt);
                TransitionValues transitionValues2 = arrayMap2.get(view);
                if (!(transitionValues == null || transitionValues2 == null)) {
                    this.mStartValuesList.add(transitionValues);
                    this.mEndValuesList.add(transitionValues2);
                    arrayMap.remove(valueAt);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    private void matchNames(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2, ArrayMap<String, View> arrayMap3, ArrayMap<String, View> arrayMap4) {
        View view;
        int size = arrayMap3.size();
        for (int i2 = 0; i2 < size; i2++) {
            View valueAt = arrayMap3.valueAt(i2);
            if (valueAt != null && isValidTarget(valueAt) && (view = arrayMap4.get(arrayMap3.keyAt(i2))) != null && isValidTarget(view)) {
                TransitionValues transitionValues = arrayMap.get(valueAt);
                TransitionValues transitionValues2 = arrayMap2.get(view);
                if (!(transitionValues == null || transitionValues2 == null)) {
                    this.mStartValuesList.add(transitionValues);
                    this.mEndValuesList.add(transitionValues2);
                    arrayMap.remove(valueAt);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    private void matchStartAndEnd(TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2) {
        ArrayMap arrayMap = new ArrayMap((SimpleArrayMap) transitionValuesMaps.mViewValues);
        ArrayMap arrayMap2 = new ArrayMap((SimpleArrayMap) transitionValuesMaps2.mViewValues);
        int i2 = 0;
        while (true) {
            int[] iArr = this.mMatchOrder;
            if (i2 < iArr.length) {
                int i7 = iArr[i2];
                if (i7 == 1) {
                    matchInstances(arrayMap, arrayMap2);
                } else if (i7 == 2) {
                    matchNames(arrayMap, arrayMap2, transitionValuesMaps.mNameValues, transitionValuesMaps2.mNameValues);
                } else if (i7 == 3) {
                    matchIds(arrayMap, arrayMap2, transitionValuesMaps.mIdValues, transitionValuesMaps2.mIdValues);
                } else if (i7 == 4) {
                    matchItemIds(arrayMap, arrayMap2, transitionValuesMaps.mItemIdValues, transitionValuesMaps2.mItemIdValues);
                }
                i2++;
            } else {
                addUnmatched(arrayMap, arrayMap2);
                return;
            }
        }
    }

    private void notifyFromTransition(Transition transition, TransitionNotification transitionNotification, boolean z) {
        Transition transition2 = this.mCloneParent;
        if (transition2 != null) {
            transition2.notifyFromTransition(transition, transitionNotification, z);
        }
        ArrayList<TransitionListener> arrayList = this.mListeners;
        if (arrayList != null && !arrayList.isEmpty()) {
            int size = this.mListeners.size();
            TransitionListener[] transitionListenerArr = this.mListenersCache;
            if (transitionListenerArr == null) {
                transitionListenerArr = new TransitionListener[size];
            }
            this.mListenersCache = null;
            TransitionListener[] transitionListenerArr2 = (TransitionListener[]) this.mListeners.toArray(transitionListenerArr);
            for (int i2 = 0; i2 < size; i2++) {
                transitionNotification.notifyListener(transitionListenerArr2[i2], transition, z);
                transitionListenerArr2[i2] = null;
            }
            this.mListenersCache = transitionListenerArr2;
        }
    }

    private void runAnimator(Animator animator, final ArrayMap<Animator, AnimationInfo> arrayMap) {
        if (animator != null) {
            animator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    arrayMap.remove(animator);
                    Transition.this.mCurrentAnimators.remove(animator);
                }

                public void onAnimationStart(Animator animator) {
                    Transition.this.mCurrentAnimators.add(animator);
                }
            });
            animate(animator);
        }
    }

    public Transition addListener(TransitionListener transitionListener) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
        }
        this.mListeners.add(transitionListener);
        return this;
    }

    public Transition addTarget(View view) {
        this.mTargets.add(view);
        return this;
    }

    public void animate(Animator animator) {
        if (animator == null) {
            end();
            return;
        }
        if (getDuration() >= 0) {
            animator.setDuration(getDuration());
        }
        if (getStartDelay() >= 0) {
            animator.setStartDelay(animator.getStartDelay() + getStartDelay());
        }
        if (getInterpolator() != null) {
            animator.setInterpolator(getInterpolator());
        }
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                Transition.this.end();
                animator.removeListener(this);
            }
        });
        animator.start();
    }

    public void cancel() {
        int size = this.mCurrentAnimators.size();
        Animator[] animatorArr = (Animator[]) this.mCurrentAnimators.toArray(this.mAnimatorCache);
        this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
        for (int i2 = size - 1; i2 >= 0; i2--) {
            Animator animator = animatorArr[i2];
            animatorArr[i2] = null;
            animator.cancel();
        }
        this.mAnimatorCache = animatorArr;
        notifyListeners(TransitionNotification.ON_CANCEL, false);
    }

    public abstract void captureEndValues(TransitionValues transitionValues);

    public abstract void captureStartValues(TransitionValues transitionValues);

    public void captureValues(ViewGroup viewGroup, boolean z) {
        ArrayMap<String, String> arrayMap;
        ArrayList<String> arrayList;
        ArrayList<Class<?>> arrayList2;
        clearValues(z);
        if ((this.mTargetIds.size() > 0 || this.mTargets.size() > 0) && (((arrayList = this.mTargetNames) == null || arrayList.isEmpty()) && ((arrayList2 = this.mTargetTypes) == null || arrayList2.isEmpty()))) {
            for (int i2 = 0; i2 < this.mTargetIds.size(); i2++) {
                View findViewById = viewGroup.findViewById(this.mTargetIds.get(i2).intValue());
                if (findViewById != null) {
                    TransitionValues transitionValues = new TransitionValues(findViewById);
                    if (z) {
                        captureStartValues(transitionValues);
                    } else {
                        captureEndValues(transitionValues);
                    }
                    transitionValues.mTargetedTransitions.add(this);
                    capturePropagationValues(transitionValues);
                    if (z) {
                        addViewValues(this.mStartValues, findViewById, transitionValues);
                    } else {
                        addViewValues(this.mEndValues, findViewById, transitionValues);
                    }
                }
            }
            for (int i7 = 0; i7 < this.mTargets.size(); i7++) {
                View view = this.mTargets.get(i7);
                TransitionValues transitionValues2 = new TransitionValues(view);
                if (z) {
                    captureStartValues(transitionValues2);
                } else {
                    captureEndValues(transitionValues2);
                }
                transitionValues2.mTargetedTransitions.add(this);
                capturePropagationValues(transitionValues2);
                if (z) {
                    addViewValues(this.mStartValues, view, transitionValues2);
                } else {
                    addViewValues(this.mEndValues, view, transitionValues2);
                }
            }
        } else {
            captureHierarchy(viewGroup, z);
        }
        if (!z && (arrayMap = this.mNameOverrides) != null) {
            int size = arrayMap.size();
            ArrayList arrayList3 = new ArrayList(size);
            for (int i8 = 0; i8 < size; i8++) {
                arrayList3.add(this.mStartValues.mNameValues.remove(this.mNameOverrides.keyAt(i8)));
            }
            for (int i10 = 0; i10 < size; i10++) {
                View view2 = (View) arrayList3.get(i10);
                if (view2 != null) {
                    this.mStartValues.mNameValues.put(this.mNameOverrides.valueAt(i10), view2);
                }
            }
        }
    }

    public void clearValues(boolean z) {
        if (z) {
            this.mStartValues.mViewValues.clear();
            this.mStartValues.mIdValues.clear();
            this.mStartValues.mItemIdValues.clear();
            return;
        }
        this.mEndValues.mViewValues.clear();
        this.mEndValues.mIdValues.clear();
        this.mEndValues.mItemIdValues.clear();
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    public void createAnimators(ViewGroup viewGroup, TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        boolean z;
        View view;
        TransitionValues transitionValues;
        AnimatorSet animatorSet;
        Animator animator;
        Transition transition = this;
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = arrayList.size();
        if (transition.getRootTransition().mSeekController != null) {
            z = true;
        } else {
            z = false;
        }
        for (int i2 = 0; i2 < size; i2++) {
            TransitionValues transitionValues2 = arrayList.get(i2);
            TransitionValues transitionValues3 = arrayList2.get(i2);
            if (transitionValues2 != null && !transitionValues2.mTargetedTransitions.contains(transition)) {
                transitionValues2 = null;
            }
            if (transitionValues3 != null && !transitionValues3.mTargetedTransitions.contains(transition)) {
                transitionValues3 = null;
            }
            if (!(transitionValues2 == null && transitionValues3 == null) && (transitionValues2 == null || transitionValues3 == null || transition.isTransitionRequired(transitionValues2, transitionValues3))) {
                ViewGroup viewGroup2 = viewGroup;
                Animator createAnimator = transition.createAnimator(viewGroup2, transitionValues2, transitionValues3);
                if (createAnimator != null) {
                    if (transitionValues3 != null) {
                        view = transitionValues3.view;
                        String[] transitionProperties = transition.getTransitionProperties();
                        if (transitionProperties != null && transitionProperties.length > 0) {
                            transitionValues = new TransitionValues(view);
                            TransitionValues transitionValues4 = transitionValuesMaps2.mViewValues.get(view);
                            if (transitionValues4 != null) {
                                int i7 = 0;
                                while (i7 < transitionProperties.length) {
                                    Map<String, Object> map = transitionValues.values;
                                    String[] strArr = transitionProperties;
                                    String str = strArr[i7];
                                    map.put(str, transitionValues4.values.get(str));
                                    i7++;
                                    transitionProperties = strArr;
                                    createAnimator = createAnimator;
                                }
                            }
                            Animator animator2 = createAnimator;
                            int size2 = runningAnimators.size();
                            int i8 = 0;
                            while (true) {
                                if (i8 >= size2) {
                                    animator = animator2;
                                    break;
                                }
                                AnimationInfo animationInfo = runningAnimators.get(runningAnimators.keyAt(i8));
                                if (animationInfo.mValues != null && animationInfo.mView == view && animationInfo.mName.equals(getName()) && animationInfo.mValues.equals(transitionValues)) {
                                    animator = null;
                                    break;
                                }
                                i8++;
                            }
                        } else {
                            TransitionValuesMaps transitionValuesMaps3 = transitionValuesMaps2;
                            animator = createAnimator;
                            transitionValues = null;
                        }
                        createAnimator = animator;
                    } else {
                        TransitionValuesMaps transitionValuesMaps4 = transitionValuesMaps2;
                        Animator animator3 = createAnimator;
                        view = transitionValues2.view;
                        transitionValues = null;
                    }
                    View view2 = view;
                    if (createAnimator != null) {
                        Animator animator4 = createAnimator;
                        transition = this;
                        AnimationInfo animationInfo2 = new AnimationInfo(view2, getName(), transition, viewGroup2.getWindowId(), transitionValues, animator4);
                        if (z) {
                            AnimatorSet animatorSet2 = new AnimatorSet();
                            animatorSet2.play(animator4);
                            animatorSet = animatorSet2;
                        } else {
                            animatorSet = animator4;
                        }
                        runningAnimators.put(animatorSet, animationInfo2);
                        transition.mAnimators.add(animatorSet);
                    } else {
                        transition = this;
                    }
                }
            } else {
                ViewGroup viewGroup3 = viewGroup;
            }
        }
        if (sparseIntArray.size() != 0) {
            for (int i10 = 0; i10 < sparseIntArray.size(); i10++) {
                AnimationInfo animationInfo3 = runningAnimators.get(transition.mAnimators.get(sparseIntArray.keyAt(i10)));
                animationInfo3.mAnimator.setStartDelay(animationInfo3.mAnimator.getStartDelay() + (((long) sparseIntArray.valueAt(i10)) - Long.MAX_VALUE));
            }
        }
    }

    public TransitionSeekController createSeekController() {
        SeekController seekController = new SeekController();
        this.mSeekController = seekController;
        addListener(seekController);
        return this.mSeekController;
    }

    public void end() {
        int i2 = this.mNumInstances - 1;
        this.mNumInstances = i2;
        if (i2 == 0) {
            notifyListeners(TransitionNotification.ON_END, false);
            for (int i7 = 0; i7 < this.mStartValues.mItemIdValues.size(); i7++) {
                View valueAt = this.mStartValues.mItemIdValues.valueAt(i7);
                if (valueAt != null) {
                    valueAt.setHasTransientState(false);
                }
            }
            for (int i8 = 0; i8 < this.mEndValues.mItemIdValues.size(); i8++) {
                View valueAt2 = this.mEndValues.mItemIdValues.valueAt(i8);
                if (valueAt2 != null) {
                    valueAt2.setHasTransientState(false);
                }
            }
            this.mEnded = true;
        }
    }

    public long getDuration() {
        return this.mDuration;
    }

    public EpicenterCallback getEpicenterCallback() {
        return this.mEpicenterCallback;
    }

    public TimeInterpolator getInterpolator() {
        return this.mInterpolator;
    }

    public TransitionValues getMatchedTransitionValues(View view, boolean z) {
        ArrayList<TransitionValues> arrayList;
        ArrayList<TransitionValues> arrayList2;
        TransitionSet transitionSet = this.mParent;
        if (transitionSet != null) {
            return transitionSet.getMatchedTransitionValues(view, z);
        }
        if (z) {
            arrayList = this.mStartValuesList;
        } else {
            arrayList = this.mEndValuesList;
        }
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                i2 = -1;
                break;
            }
            TransitionValues transitionValues = arrayList.get(i2);
            if (transitionValues == null) {
                return null;
            }
            if (transitionValues.view == view) {
                break;
            }
            i2++;
        }
        if (i2 < 0) {
            return null;
        }
        if (z) {
            arrayList2 = this.mEndValuesList;
        } else {
            arrayList2 = this.mStartValuesList;
        }
        return arrayList2.get(i2);
    }

    public String getName() {
        return this.mName;
    }

    public PathMotion getPathMotion() {
        return this.mPathMotion;
    }

    public TransitionPropagation getPropagation() {
        return null;
    }

    public final Transition getRootTransition() {
        TransitionSet transitionSet = this.mParent;
        if (transitionSet != null) {
            return transitionSet.getRootTransition();
        }
        return this;
    }

    public long getStartDelay() {
        return this.mStartDelay;
    }

    public List<Integer> getTargetIds() {
        return this.mTargetIds;
    }

    public List<String> getTargetNames() {
        return this.mTargetNames;
    }

    public List<Class<?>> getTargetTypes() {
        return this.mTargetTypes;
    }

    public List<View> getTargets() {
        return this.mTargets;
    }

    public final long getTotalDurationMillis() {
        return this.mTotalDuration;
    }

    public String[] getTransitionProperties() {
        return null;
    }

    public TransitionValues getTransitionValues(View view, boolean z) {
        TransitionValuesMaps transitionValuesMaps;
        TransitionSet transitionSet = this.mParent;
        if (transitionSet != null) {
            return transitionSet.getTransitionValues(view, z);
        }
        if (z) {
            transitionValuesMaps = this.mStartValues;
        } else {
            transitionValuesMaps = this.mEndValues;
        }
        return transitionValuesMaps.mViewValues.get(view);
    }

    public boolean hasAnimators() {
        return !this.mCurrentAnimators.isEmpty();
    }

    public boolean isSeekingSupported() {
        return false;
    }

    public boolean isTransitionRequired(TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (!(transitionValues == null || transitionValues2 == null)) {
            String[] transitionProperties = getTransitionProperties();
            if (transitionProperties != null) {
                for (String isValueChanged : transitionProperties) {
                    if (isValueChanged(transitionValues, transitionValues2, isValueChanged)) {
                        return true;
                    }
                }
            } else {
                for (String isValueChanged2 : transitionValues.values.keySet()) {
                    if (isValueChanged(transitionValues, transitionValues2, isValueChanged2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isValidTarget(View view) {
        ArrayList<Class<?>> arrayList;
        ArrayList<String> arrayList2;
        int id = view.getId();
        ArrayList<Integer> arrayList3 = this.mTargetIdExcludes;
        if (arrayList3 != null && arrayList3.contains(Integer.valueOf(id))) {
            return false;
        }
        ArrayList<View> arrayList4 = this.mTargetExcludes;
        if (arrayList4 != null && arrayList4.contains(view)) {
            return false;
        }
        ArrayList<Class<?>> arrayList5 = this.mTargetTypeExcludes;
        if (arrayList5 != null) {
            int size = arrayList5.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.mTargetTypeExcludes.get(i2).isInstance(view)) {
                    return false;
                }
            }
        }
        if (this.mTargetNameExcludes != null && ViewCompat.getTransitionName(view) != null && this.mTargetNameExcludes.contains(ViewCompat.getTransitionName(view))) {
            return false;
        }
        if ((this.mTargetIds.size() == 0 && this.mTargets.size() == 0 && (((arrayList = this.mTargetTypes) == null || arrayList.isEmpty()) && ((arrayList2 = this.mTargetNames) == null || arrayList2.isEmpty()))) || this.mTargetIds.contains(Integer.valueOf(id)) || this.mTargets.contains(view)) {
            return true;
        }
        ArrayList<String> arrayList6 = this.mTargetNames;
        if (arrayList6 != null && arrayList6.contains(ViewCompat.getTransitionName(view))) {
            return true;
        }
        if (this.mTargetTypes != null) {
            for (int i7 = 0; i7 < this.mTargetTypes.size(); i7++) {
                if (this.mTargetTypes.get(i7).isInstance(view)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void notifyListeners(TransitionNotification transitionNotification, boolean z) {
        notifyFromTransition(this, transitionNotification, z);
    }

    public void pause(View view) {
        if (!this.mEnded) {
            int size = this.mCurrentAnimators.size();
            Animator[] animatorArr = (Animator[]) this.mCurrentAnimators.toArray(this.mAnimatorCache);
            this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
            for (int i2 = size - 1; i2 >= 0; i2--) {
                Animator animator = animatorArr[i2];
                animatorArr[i2] = null;
                animator.pause();
            }
            this.mAnimatorCache = animatorArr;
            notifyListeners(TransitionNotification.ON_PAUSE, false);
            this.mPaused = true;
        }
    }

    public void playTransition(ViewGroup viewGroup) {
        AnimationInfo animationInfo;
        this.mStartValuesList = new ArrayList<>();
        this.mEndValuesList = new ArrayList<>();
        matchStartAndEnd(this.mStartValues, this.mEndValues);
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        int size = runningAnimators.size();
        WindowId windowId = viewGroup.getWindowId();
        for (int i2 = size - 1; i2 >= 0; i2--) {
            Animator keyAt = runningAnimators.keyAt(i2);
            if (!(keyAt == null || (animationInfo = runningAnimators.get(keyAt)) == null || animationInfo.mView == null || !windowId.equals(animationInfo.mWindowId))) {
                TransitionValues transitionValues = animationInfo.mValues;
                View view = animationInfo.mView;
                TransitionValues transitionValues2 = getTransitionValues(view, true);
                TransitionValues matchedTransitionValues = getMatchedTransitionValues(view, true);
                if (transitionValues2 == null && matchedTransitionValues == null) {
                    matchedTransitionValues = this.mEndValues.mViewValues.get(view);
                }
                if (!(transitionValues2 == null && matchedTransitionValues == null) && animationInfo.mTransition.isTransitionRequired(transitionValues, matchedTransitionValues)) {
                    Transition transition = animationInfo.mTransition;
                    if (transition.getRootTransition().mSeekController != null) {
                        keyAt.cancel();
                        transition.mCurrentAnimators.remove(keyAt);
                        runningAnimators.remove(keyAt);
                        if (transition.mCurrentAnimators.size() == 0) {
                            transition.notifyListeners(TransitionNotification.ON_CANCEL, false);
                            if (!transition.mEnded) {
                                transition.mEnded = true;
                                transition.notifyListeners(TransitionNotification.ON_END, false);
                            }
                        }
                    } else if (keyAt.isRunning() || keyAt.isStarted()) {
                        keyAt.cancel();
                    } else {
                        runningAnimators.remove(keyAt);
                    }
                }
            }
        }
        createAnimators(viewGroup, this.mStartValues, this.mEndValues, this.mStartValuesList, this.mEndValuesList);
        if (this.mSeekController == null) {
            runAnimators();
        } else if (Build.VERSION.SDK_INT >= 34) {
            prepareAnimatorsForSeeking();
            this.mSeekController.initPlayTime();
            this.mSeekController.ready();
        }
    }

    public void prepareAnimatorsForSeeking() {
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        this.mTotalDuration = 0;
        for (int i2 = 0; i2 < this.mAnimators.size(); i2++) {
            Animator animator = this.mAnimators.get(i2);
            AnimationInfo animationInfo = runningAnimators.get(animator);
            if (!(animator == null || animationInfo == null)) {
                if (getDuration() >= 0) {
                    animationInfo.mAnimator.setDuration(getDuration());
                }
                if (getStartDelay() >= 0) {
                    animationInfo.mAnimator.setStartDelay(animationInfo.mAnimator.getStartDelay() + getStartDelay());
                }
                if (getInterpolator() != null) {
                    animationInfo.mAnimator.setInterpolator(getInterpolator());
                }
                this.mCurrentAnimators.add(animator);
                this.mTotalDuration = Math.max(this.mTotalDuration, Impl26.getTotalDuration(animator));
            }
        }
        this.mAnimators.clear();
    }

    public Transition removeListener(TransitionListener transitionListener) {
        Transition transition;
        ArrayList<TransitionListener> arrayList = this.mListeners;
        if (arrayList != null) {
            if (!arrayList.remove(transitionListener) && (transition = this.mCloneParent) != null) {
                transition.removeListener(transitionListener);
            }
            if (this.mListeners.size() == 0) {
                this.mListeners = null;
            }
        }
        return this;
    }

    public Transition removeTarget(View view) {
        this.mTargets.remove(view);
        return this;
    }

    public void resume(View view) {
        if (this.mPaused) {
            if (!this.mEnded) {
                int size = this.mCurrentAnimators.size();
                Animator[] animatorArr = (Animator[]) this.mCurrentAnimators.toArray(this.mAnimatorCache);
                this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
                for (int i2 = size - 1; i2 >= 0; i2--) {
                    Animator animator = animatorArr[i2];
                    animatorArr[i2] = null;
                    animator.resume();
                }
                this.mAnimatorCache = animatorArr;
                notifyListeners(TransitionNotification.ON_RESUME, false);
            }
            this.mPaused = false;
        }
    }

    public void runAnimators() {
        start();
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        Iterator<Animator> it = this.mAnimators.iterator();
        while (it.hasNext()) {
            Animator next = it.next();
            if (runningAnimators.containsKey(next)) {
                start();
                runAnimator(next, runningAnimators);
            }
        }
        this.mAnimators.clear();
        end();
    }

    public void setCurrentPlayTimeMillis(long j2, long j3) {
        boolean z;
        long j8 = j2;
        long totalDurationMillis = getTotalDurationMillis();
        int i2 = 0;
        if (j8 < j3) {
            z = true;
        } else {
            z = false;
        }
        int i7 = (j3 > 0 ? 1 : (j3 == 0 ? 0 : -1));
        if ((i7 < 0 && j8 >= 0) || (j3 > totalDurationMillis && j8 <= totalDurationMillis)) {
            this.mEnded = false;
            notifyListeners(TransitionNotification.ON_START, z);
        }
        int size = this.mCurrentAnimators.size();
        Animator[] animatorArr = (Animator[]) this.mCurrentAnimators.toArray(this.mAnimatorCache);
        this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
        while (i2 < size) {
            Animator animator = animatorArr[i2];
            animatorArr[i2] = null;
            Impl26.setCurrentPlayTime(animator, Math.min(Math.max(0, j8), Impl26.getTotalDuration(animator)));
            i2++;
            totalDurationMillis = totalDurationMillis;
        }
        long j10 = totalDurationMillis;
        this.mAnimatorCache = animatorArr;
        int i8 = (j8 > j10 ? 1 : (j8 == j10 ? 0 : -1));
        if ((i8 > 0 && j3 <= j10) || (j8 < 0 && i7 >= 0)) {
            if (i8 > 0) {
                this.mEnded = true;
            }
            notifyListeners(TransitionNotification.ON_END, z);
        }
    }

    public Transition setDuration(long j2) {
        this.mDuration = j2;
        return this;
    }

    public void setEpicenterCallback(EpicenterCallback epicenterCallback) {
        this.mEpicenterCallback = epicenterCallback;
    }

    public Transition setInterpolator(TimeInterpolator timeInterpolator) {
        this.mInterpolator = timeInterpolator;
        return this;
    }

    public void setPathMotion(PathMotion pathMotion) {
        if (pathMotion == null) {
            this.mPathMotion = STRAIGHT_PATH_MOTION;
        } else {
            this.mPathMotion = pathMotion;
        }
    }

    public Transition setStartDelay(long j2) {
        this.mStartDelay = j2;
        return this;
    }

    public void start() {
        if (this.mNumInstances == 0) {
            notifyListeners(TransitionNotification.ON_START, false);
            this.mEnded = false;
        }
        this.mNumInstances++;
    }

    public String toString() {
        return toString("");
    }

    public Transition clone() {
        try {
            Transition transition = (Transition) super.clone();
            transition.mAnimators = new ArrayList<>();
            transition.mStartValues = new TransitionValuesMaps();
            transition.mEndValues = new TransitionValuesMaps();
            transition.mStartValuesList = null;
            transition.mEndValuesList = null;
            transition.mSeekController = null;
            transition.mCloneParent = this;
            transition.mListeners = null;
            return transition;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString(String str) {
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(getClass().getSimpleName());
        sb2.append(Log.TAG_SEPARATOR);
        sb2.append(Integer.toHexString(hashCode()));
        sb2.append(": ");
        if (this.mDuration != -1) {
            sb2.append("dur(");
            sb2.append(this.mDuration);
            sb2.append(") ");
        }
        if (this.mStartDelay != -1) {
            sb2.append("dly(");
            sb2.append(this.mStartDelay);
            sb2.append(") ");
        }
        if (this.mInterpolator != null) {
            sb2.append("interp(");
            sb2.append(this.mInterpolator);
            sb2.append(") ");
        }
        if (this.mTargetIds.size() > 0 || this.mTargets.size() > 0) {
            sb2.append("tgts(");
            if (this.mTargetIds.size() > 0) {
                for (int i2 = 0; i2 < this.mTargetIds.size(); i2++) {
                    if (i2 > 0) {
                        sb2.append(ArcCommonLog.TAG_COMMA);
                    }
                    sb2.append(this.mTargetIds.get(i2));
                }
            }
            if (this.mTargets.size() > 0) {
                for (int i7 = 0; i7 < this.mTargets.size(); i7++) {
                    if (i7 > 0) {
                        sb2.append(ArcCommonLog.TAG_COMMA);
                    }
                    sb2.append(this.mTargets.get(i7));
                }
            }
            sb2.append(")");
        }
        return sb2.toString();
    }

    public void capturePropagationValues(TransitionValues transitionValues) {
    }

    public void setPropagation(TransitionPropagation transitionPropagation) {
    }
}
