package com.samsung.android.gallery.widget.abstraction;

import A9.a;
import H.b;
import N2.j;
import U6.d;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.View;
import androidx.core.util.Pair;
import androidx.fragment.app.FragmentTransaction;
import androidx.window.embedding.c;
import bc.C0584a;
import c4.C0431a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$transition;
import com.samsung.android.gallery.widget.abstraction.TransitionData;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedTransition {
    private static Runnable mStartPostPoneRunnable = null;
    private static final ConcurrentHashMap<Blackboard, SharedTransition> sInstance = new ConcurrentHashMap<>();
    private final Blackboard mBlackboard;
    /* access modifiers changed from: private */
    public Status mEnterTransitionStatus;
    private long mInitTime;
    /* access modifiers changed from: private */
    public TransitionListener mListener;
    private final AtomicBoolean mPostponed = new AtomicBoolean(false);
    private final Map<String, View> mRequestedMap = new HashMap();
    private final AtomicInteger mReturnPosition = new AtomicInteger(-1);
    /* access modifiers changed from: private */
    public Status mReturnTransitionStatus;
    private final AtomicInteger mSuggestionRemasterGroupingType = new AtomicInteger(-1);
    private TransitionInfo mTransitionInfo;
    /* access modifiers changed from: private */
    public final ArrayList<View> mUsedView = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EnterTransitionListener implements Transition.TransitionListener {
        private final Blackboard bb;
        long startTime;

        public /* synthetic */ EnterTransitionListener(Blackboard blackboard, int i2) {
            this(blackboard);
        }

        public void onTransitionCancel(Transition transition) {
            Log.st("SharedTransition", "onEnterTransitionCancel");
            SharedTransition o2 = SharedTransition.getInstance(this.bb);
            if (o2 != null) {
                o2.mEnterTransitionStatus = Status.END;
                o2.mUsedView.clear();
                o2.mListener = null;
            }
            if (transition != null) {
                transition.removeListener(this);
            }
        }

        public void onTransitionEnd(Transition transition) {
            double d;
            boolean z;
            Log.p("SharedTransition", "GalleryST onEnterTransitionEnd");
            long currentTimeMillis = System.currentTimeMillis() - this.startTime;
            if (transition != null) {
                d = ((double) transition.getDuration()) * 0.7d;
            } else {
                d = 9.223372036854776E18d;
            }
            long j2 = (long) d;
            if (currentTimeMillis >= j2) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                StringBuilder j3 = j.j(currentTimeMillis, "onEnterTransitionEnd failed : ", " < ");
                j3.append(j2);
                Log.st("SharedTransition", j3.toString());
                SharedTransition.dumpTransitionView(this.bb);
            }
            SharedTransition.clear(this.bb);
            this.bb.erase("lifecycle://on_fragment_enter_transition_start");
            this.bb.post("lifecycle://on_fragment_enter_transition_end", Boolean.valueOf(z));
            SharedTransition o2 = SharedTransition.getInstance(this.bb);
            if (o2 != null) {
                o2.mEnterTransitionStatus = Status.END;
                if (o2.mReturnTransitionStatus != Status.START_REQUESTED) {
                    o2.mUsedView.clear();
                    if (o2.mListener != null) {
                        o2.mListener.onEnterTransitionEndV2();
                    }
                    o2.mListener = null;
                }
            }
            if (transition != null) {
                transition.removeListener(this);
            }
        }

        public void onTransitionPause(Transition transition) {
            Log.st("SharedTransition", "onEnterTransitionPause");
        }

        public void onTransitionResume(Transition transition) {
            Log.st("SharedTransition", "onEnterTransitionResume");
        }

        public void onTransitionStart(Transition transition) {
            this.startTime = System.currentTimeMillis();
            SharedTransition o2 = SharedTransition.getInstance(this.bb);
            Log.p("SharedTransition", "GalleryST onEnterTransitionStart");
            this.bb.publish("lifecycle://on_fragment_enter_transition_start", (Object) null);
            if (o2 != null) {
                o2.mEnterTransitionStatus = Status.START;
                if (o2.mListener != null) {
                    o2.mListener.onEnterTransitionStartV2();
                }
            }
        }

        private EnterTransitionListener(Blackboard blackboard) {
            this.bb = blackboard;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Status {
        INIT,
        POSTPONED,
        START_REQUESTED,
        START,
        END;
        
        long time;
    }

    private SharedTransition(Blackboard blackboard) {
        Status status = Status.INIT;
        this.mEnterTransitionStatus = status;
        this.mReturnTransitionStatus = status;
        this.mBlackboard = blackboard;
    }

    private void addInternal(View view) {
        String transitionName = view.getTransitionName();
        if (transitionName == null) {
            Log.st("SharedTransition", "Transition name did not set. Ignore view:" + view);
            return;
        }
        if (this.mRequestedMap.containsValue(view)) {
            Log.st("SharedTransition", "find duplicated view :" + view);
            String[] strArr = new String[1];
            this.mRequestedMap.forEach(new a(13, view, strArr));
            String str = strArr[0];
            if (str != null) {
                this.mRequestedMap.remove(str);
            }
        }
        View put = this.mRequestedMap.put(transitionName, view);
        if (put != null && put != view) {
            Log.st("SharedTransition", "find duplicated : " + transitionName + ", old:" + put + ", new:" + view);
            put.setTransitionName((String) null);
        }
    }

    @SafeVarargs
    public static void addSharedElement(Blackboard blackboard, TransitionInfo transitionInfo, Pair<View, String>... pairArr) {
        ThreadUtil.assertUiThread("SharedTransition");
        SharedTransition instance = getInstance(blackboard);
        if (instance != null) {
            for (Pair<View, String> pair : pairArr) {
                instance.addSharedElement((View) pair.first, (String) pair.second);
            }
            instance.setTransitionInfo(transitionInfo);
        }
    }

    public static boolean applyTransaction(Blackboard blackboard, FragmentTransaction fragmentTransaction) {
        ThreadUtil.assertUiThread("SharedTransition");
        SharedTransition instance = getInstance(blackboard);
        return instance != null && instance.applyTransaction(fragmentTransaction);
    }

    /* access modifiers changed from: private */
    public static void clear(Blackboard blackboard) {
        SharedTransition instance = getInstance(blackboard);
        if (instance != null) {
            instance.mRequestedMap.clear();
        }
    }

    public static /* synthetic */ SharedTransition d(Blackboard blackboard) {
        return new SharedTransition(blackboard);
    }

    public static Transition getEnterSharedTransition(TransitionData transitionData) {
        TransitionSet transitionSet = getTransitionSet(transitionData, true);
        transitionSet.addListener(new EnterTransitionListener(transitionData.getBlackboard(), 0));
        return transitionSet;
    }

    /* access modifiers changed from: private */
    public static SharedTransition getInstance(Blackboard blackboard) {
        if (blackboard == null) {
            return null;
        }
        return sInstance.computeIfAbsent(blackboard, new C0431a(17));
    }

    public static int getReturnPosition(Blackboard blackboard) {
        SharedTransition instance = getInstance(blackboard);
        if (instance != null) {
            return instance.mReturnPosition.get();
        }
        return -1;
    }

    public static Transition getReturnSharedTransition(TransitionData transitionData) {
        TransitionSet transitionSet = getTransitionSet(transitionData, false);
        final Blackboard blackboard = transitionData.getBlackboard();
        transitionSet.addListener(new Transition.TransitionListener() {
            long startTime;

            public void onTransitionCancel(Transition transition) {
                Log.st("SharedTransition", "onReturnTransitionCancel");
                Blackboard.this.erase("lifecycle://on_fragment_return_transition");
                SharedTransition o2 = SharedTransition.getInstance(Blackboard.this);
                if (o2 != null) {
                    o2.mEnterTransitionStatus = Status.END;
                    o2.mUsedView.clear();
                    o2.mListener = null;
                }
                transition.removeListener(this);
            }

            public void onTransitionEnd(Transition transition) {
                Log.st("SharedTransition", "onReturnTransitionEnd");
                long currentTimeMillis = System.currentTimeMillis() - this.startTime;
                long duration = (long) (((double) transition.getDuration()) * 0.7d);
                if (currentTimeMillis < duration) {
                    StringBuilder j2 = j.j(currentTimeMillis, "onReturnTransitionEnd failed ", " < ");
                    j2.append(duration);
                    Log.st("SharedTransition", j2.toString());
                    SharedTransition.dumpTransitionView(Blackboard.this);
                }
                SharedTransition.clear(Blackboard.this);
                Blackboard.this.erase("lifecycle://on_fragment_return_transition");
                SharedTransition o2 = SharedTransition.getInstance(Blackboard.this);
                if (o2 != null) {
                    Status status = Status.END;
                    o2.mEnterTransitionStatus = status;
                    o2.mReturnTransitionStatus = status;
                    o2.mUsedView.clear();
                    if (o2.mListener != null) {
                        o2.mListener.onReturnTransitionEndV2();
                    }
                    o2.mListener = null;
                }
                transition.removeListener(this);
            }

            public void onTransitionPause(Transition transition) {
                Log.st("SharedTransition", "onReturnTransitionPause");
            }

            public void onTransitionResume(Transition transition) {
                Log.st("SharedTransition", "onReturnTransitionResume");
            }

            public void onTransitionStart(Transition transition) {
                this.startTime = System.currentTimeMillis();
                Log.st("SharedTransition", "onReturnTransitionStart");
                SharedTransition o2 = SharedTransition.getInstance(Blackboard.this);
                if (o2 != null) {
                    o2.mReturnTransitionStatus = Status.START;
                    if (o2.mListener != null) {
                        o2.mListener.onReturnTransitionStartV2();
                    }
                }
                Blackboard blackboard = Blackboard.this;
                blackboard.replace("lifecycle://on_fragment_return_transition", "s" + this.startTime);
            }
        });
        return transitionSet;
    }

    public static int getSuggestionRemasterGroupingType(Blackboard blackboard) {
        SharedTransition instance = getInstance(blackboard);
        if (instance != null) {
            return instance.mSuggestionRemasterGroupingType.get();
        }
        return -1;
    }

    public static String getTransitionInfoName(Blackboard blackboard) {
        TransitionInfo transitionInfo;
        SharedTransition instance = getInstance(blackboard);
        if (instance == null || (transitionInfo = instance.mTransitionInfo) == null) {
            return "";
        }
        return transitionInfo.name;
    }

    public static String getTransitionName(String str, MediaItem mediaItem) {
        StringBuilder t = C0212a.t(str, "/");
        t.append(getTransitionName(mediaItem));
        return t.toString();
    }

    private static TransitionSet getTransitionSet(TransitionData transitionData, boolean z) {
        int transitionId = transitionData.getTransitionId(z);
        HashMap<String, TransitionSet> transitionSetMap = getTransitionSetMap(transitionData.getBlackboard());
        return transitionSetMap.computeIfAbsent(z + "_" + transitionId + "_" + transitionData.getReparentWithOverlay(), new d(transitionData, transitionId, 1));
    }

    private static HashMap<String, TransitionSet> getTransitionSetMap(Blackboard blackboard) {
        return (HashMap) blackboard.computeIfAbsent("data://shared_transitionSet_map", new C0431a(18));
    }

    public static int getViewerEnterSharedTransitionResourceId() {
        return R$transition.oneui50_image_shared_element_transition;
    }

    private static boolean hasReturnPosition(Blackboard blackboard) {
        SharedTransition instance = getInstance(blackboard);
        if (instance == null || instance.mReturnPosition.get() == -1) {
            return false;
        }
        return true;
    }

    public static boolean isEnterTransitionFinished(Blackboard blackboard) {
        SharedTransition instance = getInstance(blackboard);
        if (instance == null || instance.mEnterTransitionStatus != Status.END) {
            return false;
        }
        return true;
    }

    public static boolean isEnterTransitionRunning(Blackboard blackboard) {
        SharedTransition instance = getInstance(blackboard);
        if (instance == null || instance.mEnterTransitionStatus != Status.START) {
            return false;
        }
        return true;
    }

    public static boolean isInReturnTransition(Blackboard blackboard) {
        if (blackboard.read("lifecycle://on_fragment_return_transition") != null) {
            return true;
        }
        return false;
    }

    public static boolean isSharedViewTransaction(Blackboard blackboard) {
        SharedTransition instance = getInstance(blackboard);
        if (instance == null || instance.mUsedView.isEmpty()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$addInternal$0(View view, String[] strArr, String str, View view2) {
        if (view.equals(view2)) {
            strArr[0] = str;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ TransitionSet lambda$getTransitionSet$1(TransitionData transitionData, int i2, String str) {
        TransitionSet transitionSet = (TransitionSet) TransitionInflater.from(BlackboardUtils.readActivity(transitionData.getBlackboard())).inflateTransition(i2);
        ChangeTransform changeTransform = new ChangeTransform();
        changeTransform.setReparentWithOverlay(transitionData.getReparentWithOverlay());
        transitionSet.addTransition(changeTransform);
        setInterpolatorAndDuration(transitionSet, i2);
        return transitionSet;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ HashMap lambda$getTransitionSetMap$2(String str) {
        return new HashMap();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$startPostponedEnterTransition$3(Blackboard blackboard) {
        EnterTransitionListener enterTransitionListener = new EnterTransitionListener(blackboard, 0);
        enterTransitionListener.onTransitionStart((Transition) null);
        enterTransitionListener.onTransitionEnd((Transition) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$startPostponedEnterTransition$4(SharedTransition sharedTransition, long j2, Blackboard blackboard) {
        Status status = sharedTransition.mEnterTransitionStatus;
        if (status == Status.START_REQUESTED && j2 == status.time) {
            Log.ste("SharedTransition", "startPostponedEnterTransition failed");
            SharedTransitionDebug.validateTransitionViews(sharedTransition, blackboard);
            sharedTransition.mEnterTransitionStatus = Status.END;
            ThreadUtil.runOnUiThread(new C0584a(21, blackboard));
        }
    }

    public static void onPrepare(TransitionData transitionData) {
        SharedTransition instance = getInstance(transitionData.getBlackboard());
        setListener(transitionData.getListener(), instance);
        if (hasReturnPosition(transitionData.getBlackboard()) || isSharedViewTransaction(transitionData.getBlackboard())) {
            safePostPoneEnterTransition(transitionData.getBlackboard(), transitionData.getListener());
        } else {
            setEnterTransitionStatusEnd(instance);
        }
        setSharedElementTransition(transitionData);
    }

    public static void onPrepareOthers(Blackboard blackboard, TransitionListener transitionListener) {
        TransitionData.Builder listener = new TransitionData.Builder().setBlackboard(blackboard).setListener(transitionListener);
        int i2 = R$transition.default_shared_element_transition;
        onPrepare(listener.setEnterTransitionId(i2).setReturnTransitionId(i2).setReparentWithOverlay(true).build());
    }

    public static void onPreparePictures(Blackboard blackboard, TransitionListener transitionListener, boolean z) {
        TransitionData.Builder listener = new TransitionData.Builder().setBlackboard(blackboard).setListener(transitionListener);
        int i2 = R$transition.image_shared_element_transition;
        onPrepare(listener.setEnterTransitionId(i2).setReturnTransitionId(i2).setReparentWithOverlay(z).build());
    }

    public static void onPrepareStories(Blackboard blackboard, TransitionListener transitionListener, boolean z) {
        int i2;
        if (z) {
            i2 = R$transition.stories_on_demand_shared_element_transition;
        } else {
            i2 = R$transition.stories_shared_element_transition;
        }
        onPrepare(new TransitionData.Builder().setBlackboard(blackboard).setListener(transitionListener).setEnterTransitionId(i2).setReturnTransitionId(i2).setReparentWithOverlay(true).build());
    }

    public static void onRelease(Blackboard blackboard) {
        setListener((TransitionListener) null, getInstance(blackboard));
        sInstance.remove(blackboard);
        removePostponedRunnable();
    }

    public static TransitionInfo popTransitionInfo(Blackboard blackboard) {
        SharedTransition instance = getInstance(blackboard);
        if (instance == null) {
            return null;
        }
        TransitionInfo transitionInfo = instance.mTransitionInfo;
        instance.mTransitionInfo = null;
        return transitionInfo;
    }

    public static void prepareReturn(TransitionData transitionData) {
        SharedTransition instance = getInstance(transitionData.getBlackboard());
        if (instance != null) {
            instance.mReturnTransitionStatus = Status.START_REQUESTED;
        }
        transitionData.getListener().setSharedElementReturnTransition(getReturnSharedTransition(transitionData));
    }

    private static void removePostponedRunnable() {
        Runnable runnable = mStartPostPoneRunnable;
        if (runnable != null) {
            ThreadUtil.removeCallbackOnUiThread(runnable);
            mStartPostPoneRunnable = null;
        }
    }

    public static void resetReturnInfo(Blackboard blackboard) {
        setReturnPosition(blackboard, -1, (TransitionInfo) null);
    }

    public static void safePostPoneEnterTransition(Blackboard blackboard, TransitionListener transitionListener) {
        SharedTransition instance = getInstance(blackboard);
        if (instance != null && instance.mPostponed.compareAndSet(false, true)) {
            Log.st("SharedTransition", "safePostPoneEnterTransition");
            transitionListener.postponeEnterTransitionV2();
            instance.mEnterTransitionStatus = Status.POSTPONED;
            c cVar = new c(24, transitionListener, blackboard);
            mStartPostPoneRunnable = cVar;
            ThreadUtil.postOnUiThreadDelayed(cVar, 200);
        }
    }

    private static void setEnterTransitionStatusEnd(SharedTransition sharedTransition) {
        if (sharedTransition != null) {
            sharedTransition.mEnterTransitionStatus = Status.END;
        }
    }

    private static void setInterpolatorAndDuration(TransitionSet transitionSet, int i2) {
        if (getViewerEnterSharedTransitionResourceId() == i2) {
            setViewerInterpolatorAndDuration(transitionSet);
        } else {
            transitionSet.setInterpolator(transitionSet.getInterpolator());
        }
    }

    private static void setListener(TransitionListener transitionListener, SharedTransition sharedTransition) {
        if (sharedTransition != null) {
            sharedTransition.mListener = transitionListener;
        }
    }

    public static void setReturnPosition(Blackboard blackboard, int i2) {
        setReturnPosition(blackboard, i2, (TransitionInfo) null);
    }

    public static void setSharedElementTransition(TransitionData transitionData) {
        if (isSharedViewTransaction(transitionData.getBlackboard())) {
            transitionData.getListener().setSharedElementEnterTransition(getEnterSharedTransition(transitionData));
        }
        transitionData.getListener().setSharedElementReturnTransition(getReturnSharedTransition(transitionData));
    }

    public static void setSuggestionRemasterGroupingType(Blackboard blackboard, int i2) {
        SharedTransition instance = getInstance(blackboard);
        if (instance != null && instance.mSuggestionRemasterGroupingType.get() != i2) {
            instance.mSuggestionRemasterGroupingType.set(i2);
        }
    }

    private void setTransitionInfo(TransitionInfo transitionInfo) {
        this.mTransitionInfo = transitionInfo;
    }

    public static void setTransitionName(View view, String str) {
        if (view != null) {
            view.setTransitionName(str);
        }
    }

    private static void setViewerInterpolatorAndDuration(TransitionSet transitionSet) {
        transitionSet.setDuration(200);
    }

    public static boolean startPostponedEnterTransition(TransitionListener transitionListener, Blackboard blackboard) {
        SharedTransition instance = getInstance(blackboard);
        if (instance != null && instance.mPostponed.compareAndSet(true, false)) {
            removePostponedRunnable();
            resetReturnInfo(blackboard);
            Log.p("SharedTransition", "GalleryST startPostponedEnterTransition" + Logger.vt(instance.mInitTime));
            transitionListener.startPostponedEnterTransitionV2();
            instance.mEnterTransitionStatus = Status.START_REQUESTED;
            if (!isSharedViewTransaction(blackboard)) {
                instance.mEnterTransitionStatus = Status.END;
                Log.st("SharedTransition", "startPostponedEnterTransition abort. non shared view");
                return false;
            }
            ThreadUtil.postOnBgThreadDelayed(new b(instance, instance.mEnterTransitionStatus.time, blackboard), 300);
        }
        return true;
    }

    public Map<String, View> getTransitionMap() {
        return this.mRequestedMap;
    }

    public static Map<String, View> getTransitionMap(Blackboard blackboard) {
        SharedTransition instance = getInstance(blackboard);
        if (instance != null) {
            return instance.mRequestedMap;
        }
        return null;
    }

    public static void setReturnPosition(Blackboard blackboard, int i2, TransitionInfo transitionInfo) {
        SharedTransition instance = getInstance(blackboard);
        if (instance != null) {
            if (instance.mReturnPosition.get() != i2) {
                instance.mReturnPosition.set(i2);
            }
            instance.mTransitionInfo = transitionInfo;
        }
    }

    private boolean applyTransaction(FragmentTransaction fragmentTransaction) {
        this.mEnterTransitionStatus = Status.INIT;
        this.mUsedView.clear();
        boolean z = false;
        if (this.mRequestedMap.isEmpty()) {
            Log.st("SharedTransition", "NO Shared Element");
            return false;
        }
        for (Map.Entry next : this.mRequestedMap.entrySet()) {
            String str = (String) next.getKey();
            View view = (View) next.getValue();
            if (!str.equals(view.getTransitionName())) {
                if (view.getTransitionName() == null) {
                    Log.st("SharedTransition", "ERROR : transition name of the view is null" + Logger.v(str));
                } else {
                    Log.st("SharedTransition", "ERROR : transition name of the view which already added as shared element is modified later" + Logger.v(view.getTransitionName(), str));
                }
            }
            try {
                fragmentTransaction.addSharedElement(view, str);
                this.mUsedView.add(view);
                z = true;
            } catch (Exception e) {
                Log.ste("SharedTransition", "ERROR : fail add to Transaction :" + Logger.v(e, view, str));
                throw e;
            }
        }
        return z;
    }

    public static void addSharedElement(Blackboard blackboard, View view, String str, TransitionInfo transitionInfo) {
        ThreadUtil.assertUiThread("SharedTransition");
        SharedTransition instance = getInstance(blackboard);
        if (instance != null) {
            instance.addSharedElement(view, str);
            instance.setTransitionInfo(transitionInfo);
        }
    }

    public static String getTransitionName(MediaItem mediaItem) {
        StringBuilder sb2;
        if (mediaItem.isCloud()) {
            sb2 = new StringBuilder("C");
            sb2.append(mediaItem.getFileId());
        } else {
            sb2 = new StringBuilder();
            sb2.append(mediaItem.getFileId());
            sb2.append("/");
            sb2.append(mediaItem.getPath());
        }
        return sb2.toString();
    }

    private void addSharedElement(View view, String str) {
        this.mInitTime = System.currentTimeMillis();
        this.mUsedView.clear();
        view.setTransitionName(str);
        addInternal(view);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface TransitionListener {
        void onEnterTransitionEndV2();

        void onEnterTransitionStartV2();

        void postponeEnterTransitionV2();

        void setSharedElementEnterTransition(Object obj);

        void setSharedElementReturnTransition(Object obj);

        void startPostponedEnterTransitionV2();

        void onReturnTransitionEndV2() {
        }

        void onReturnTransitionStartV2() {
        }
    }

    public static void dumpTransitionView(Blackboard blackboard) {
    }
}
