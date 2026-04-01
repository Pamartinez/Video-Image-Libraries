package androidx.transition;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;
import c0.C0086a;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TransitionSet extends Transition {
    private int mChangeFlags = 0;
    int mCurrentListeners;
    private boolean mPlayTogether = true;
    boolean mStarted = false;
    ArrayList<Transition> mTransitions = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TransitionSetListener extends TransitionListenerAdapter {
        TransitionSet mTransitionSet;

        public TransitionSetListener(TransitionSet transitionSet) {
            this.mTransitionSet = transitionSet;
        }

        public void onTransitionEnd(Transition transition) {
            TransitionSet transitionSet = this.mTransitionSet;
            int i2 = transitionSet.mCurrentListeners - 1;
            transitionSet.mCurrentListeners = i2;
            if (i2 == 0) {
                transitionSet.mStarted = false;
                transitionSet.end();
            }
            transition.removeListener(this);
        }

        public void onTransitionStart(Transition transition) {
            TransitionSet transitionSet = this.mTransitionSet;
            if (!transitionSet.mStarted) {
                transitionSet.start();
                this.mTransitionSet.mStarted = true;
            }
        }
    }

    private void addTransitionInternal(Transition transition) {
        this.mTransitions.add(transition);
        transition.mParent = this;
    }

    private int indexOfTransition(long j2) {
        for (int i2 = 1; i2 < this.mTransitions.size(); i2++) {
            if (this.mTransitions.get(i2).mSeekOffsetInParent > j2) {
                return i2 - 1;
            }
        }
        return this.mTransitions.size() - 1;
    }

    private void setupStartEndListeners() {
        TransitionSetListener transitionSetListener = new TransitionSetListener(this);
        Iterator<Transition> it = this.mTransitions.iterator();
        while (it.hasNext()) {
            it.next().addListener(transitionSetListener);
        }
        this.mCurrentListeners = this.mTransitions.size();
    }

    public TransitionSet addTransition(Transition transition) {
        addTransitionInternal(transition);
        long j2 = this.mDuration;
        if (j2 >= 0) {
            transition.setDuration(j2);
        }
        if ((this.mChangeFlags & 1) != 0) {
            transition.setInterpolator(getInterpolator());
        }
        if ((this.mChangeFlags & 2) != 0) {
            getPropagation();
            transition.setPropagation((TransitionPropagation) null);
        }
        if ((this.mChangeFlags & 4) != 0) {
            transition.setPathMotion(getPathMotion());
        }
        if ((this.mChangeFlags & 8) != 0) {
            transition.setEpicenterCallback(getEpicenterCallback());
        }
        return this;
    }

    public void cancel() {
        super.cancel();
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).cancel();
        }
    }

    public void captureEndValues(TransitionValues transitionValues) {
        if (isValidTarget(transitionValues.view)) {
            Iterator<Transition> it = this.mTransitions.iterator();
            while (it.hasNext()) {
                Transition next = it.next();
                if (next.isValidTarget(transitionValues.view)) {
                    next.captureEndValues(transitionValues);
                    transitionValues.mTargetedTransitions.add(next);
                }
            }
        }
    }

    public void capturePropagationValues(TransitionValues transitionValues) {
        super.capturePropagationValues(transitionValues);
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).capturePropagationValues(transitionValues);
        }
    }

    public void captureStartValues(TransitionValues transitionValues) {
        if (isValidTarget(transitionValues.view)) {
            Iterator<Transition> it = this.mTransitions.iterator();
            while (it.hasNext()) {
                Transition next = it.next();
                if (next.isValidTarget(transitionValues.view)) {
                    next.captureStartValues(transitionValues);
                    transitionValues.mTargetedTransitions.add(next);
                }
            }
        }
    }

    public void createAnimators(ViewGroup viewGroup, TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        long startDelay = getStartDelay();
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            Transition transition = this.mTransitions.get(i2);
            if (startDelay > 0 && (this.mPlayTogether || i2 == 0)) {
                long startDelay2 = transition.getStartDelay();
                if (startDelay2 > 0) {
                    transition.setStartDelay(startDelay2 + startDelay);
                } else {
                    transition.setStartDelay(startDelay);
                }
            }
            transition.createAnimators(viewGroup, transitionValuesMaps, transitionValuesMaps2, arrayList, arrayList2);
        }
    }

    public Transition getTransitionAt(int i2) {
        if (i2 < 0 || i2 >= this.mTransitions.size()) {
            return null;
        }
        return this.mTransitions.get(i2);
    }

    public int getTransitionCount() {
        return this.mTransitions.size();
    }

    public boolean hasAnimators() {
        for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
            if (this.mTransitions.get(i2).hasAnimators()) {
                return true;
            }
        }
        return false;
    }

    public boolean isSeekingSupported() {
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!this.mTransitions.get(i2).isSeekingSupported()) {
                return false;
            }
        }
        return true;
    }

    public void pause(View view) {
        super.pause(view);
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).pause(view);
        }
    }

    public void prepareAnimatorsForSeeking() {
        this.mTotalDuration = 0;
        AnonymousClass2 r0 = new TransitionListenerAdapter() {
            public void onTransitionCancel(Transition transition) {
                TransitionSet.this.mTransitions.remove(transition);
                if (!TransitionSet.this.hasAnimators()) {
                    TransitionSet.this.notifyListeners(Transition.TransitionNotification.ON_CANCEL, false);
                    TransitionSet transitionSet = TransitionSet.this;
                    transitionSet.mEnded = true;
                    transitionSet.notifyListeners(Transition.TransitionNotification.ON_END, false);
                }
            }
        };
        for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
            Transition transition = this.mTransitions.get(i2);
            transition.addListener(r0);
            transition.prepareAnimatorsForSeeking();
            long totalDurationMillis = transition.getTotalDurationMillis();
            if (this.mPlayTogether) {
                this.mTotalDuration = Math.max(this.mTotalDuration, totalDurationMillis);
            } else {
                long j2 = this.mTotalDuration;
                transition.mSeekOffsetInParent = j2;
                this.mTotalDuration = j2 + totalDurationMillis;
            }
        }
    }

    public void resume(View view) {
        super.resume(view);
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).resume(view);
        }
    }

    public void runAnimators() {
        if (this.mTransitions.isEmpty()) {
            start();
            end();
            return;
        }
        setupStartEndListeners();
        if (!this.mPlayTogether) {
            for (int i2 = 1; i2 < this.mTransitions.size(); i2++) {
                final Transition transition = this.mTransitions.get(i2);
                this.mTransitions.get(i2 - 1).addListener(new TransitionListenerAdapter() {
                    public void onTransitionEnd(Transition transition) {
                        transition.runAnimators();
                        transition.removeListener(this);
                    }
                });
            }
            Transition transition2 = this.mTransitions.get(0);
            if (transition2 != null) {
                transition2.runAnimators();
                return;
            }
            return;
        }
        Iterator<Transition> it = this.mTransitions.iterator();
        while (it.hasNext()) {
            it.next().runAnimators();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setCurrentPlayTimeMillis(long r20, long r22) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r3 = r22
            long r5 = r0.getTotalDurationMillis()
            androidx.transition.TransitionSet r7 = r0.mParent
            r8 = 0
            if (r7 == 0) goto L_0x0022
            int r7 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r7 >= 0) goto L_0x0018
            int r7 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r7 < 0) goto L_0x00c2
        L_0x0018:
            int r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0022
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0022
            goto L_0x00c2
        L_0x0022:
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r10 = 0
            r11 = 1
            if (r7 >= 0) goto L_0x002a
            r12 = r11
            goto L_0x002b
        L_0x002a:
            r12 = r10
        L_0x002b:
            int r13 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r13 < 0) goto L_0x0033
            int r14 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r14 < 0) goto L_0x003b
        L_0x0033:
            int r14 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r14 > 0) goto L_0x0042
            int r14 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r14 <= 0) goto L_0x0042
        L_0x003b:
            r0.mEnded = r10
            androidx.transition.Transition$TransitionNotification r14 = androidx.transition.Transition.TransitionNotification.ON_START
            r0.notifyListeners(r14, r12)
        L_0x0042:
            boolean r14 = r0.mPlayTogether
            if (r14 == 0) goto L_0x005f
        L_0x0046:
            java.util.ArrayList<androidx.transition.Transition> r7 = r0.mTransitions
            int r7 = r7.size()
            if (r10 >= r7) goto L_0x005c
            java.util.ArrayList<androidx.transition.Transition> r7 = r0.mTransitions
            java.lang.Object r7 = r7.get(r10)
            androidx.transition.Transition r7 = (androidx.transition.Transition) r7
            r7.setCurrentPlayTimeMillis(r1, r3)
            int r10 = r10 + 1
            goto L_0x0046
        L_0x005c:
            r16 = r8
            goto L_0x00a7
        L_0x005f:
            int r10 = r0.indexOfTransition(r3)
            if (r7 < 0) goto L_0x008a
        L_0x0065:
            java.util.ArrayList<androidx.transition.Transition> r7 = r0.mTransitions
            int r7 = r7.size()
            if (r10 >= r7) goto L_0x005c
            java.util.ArrayList<androidx.transition.Transition> r7 = r0.mTransitions
            java.lang.Object r7 = r7.get(r10)
            androidx.transition.Transition r7 = (androidx.transition.Transition) r7
            long r14 = r7.mSeekOffsetInParent
            r16 = r8
            long r8 = r1 - r14
            int r18 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1))
            if (r18 >= 0) goto L_0x0080
            goto L_0x00a7
        L_0x0080:
            long r14 = r3 - r14
            r7.setCurrentPlayTimeMillis(r8, r14)
            int r10 = r10 + 1
            r8 = r16
            goto L_0x0065
        L_0x008a:
            r16 = r8
        L_0x008c:
            if (r10 < 0) goto L_0x00a7
            java.util.ArrayList<androidx.transition.Transition> r7 = r0.mTransitions
            java.lang.Object r7 = r7.get(r10)
            androidx.transition.Transition r7 = (androidx.transition.Transition) r7
            long r8 = r7.mSeekOffsetInParent
            long r14 = r1 - r8
            long r8 = r3 - r8
            r7.setCurrentPlayTimeMillis(r14, r8)
            int r7 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r7 < 0) goto L_0x00a4
            goto L_0x00a7
        L_0x00a4:
            int r10 = r10 + -1
            goto L_0x008c
        L_0x00a7:
            androidx.transition.TransitionSet r7 = r0.mParent
            if (r7 == 0) goto L_0x00c2
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 <= 0) goto L_0x00b3
            int r2 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r2 <= 0) goto L_0x00b9
        L_0x00b3:
            if (r13 >= 0) goto L_0x00c2
            int r2 = (r3 > r16 ? 1 : (r3 == r16 ? 0 : -1))
            if (r2 < 0) goto L_0x00c2
        L_0x00b9:
            if (r1 <= 0) goto L_0x00bd
            r0.mEnded = r11
        L_0x00bd:
            androidx.transition.Transition$TransitionNotification r1 = androidx.transition.Transition.TransitionNotification.ON_END
            r0.notifyListeners(r1, r12)
        L_0x00c2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.TransitionSet.setCurrentPlayTimeMillis(long, long):void");
    }

    public void setEpicenterCallback(Transition.EpicenterCallback epicenterCallback) {
        super.setEpicenterCallback(epicenterCallback);
        this.mChangeFlags |= 8;
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).setEpicenterCallback(epicenterCallback);
        }
    }

    public TransitionSet setOrdering(int i2) {
        if (i2 == 0) {
            this.mPlayTogether = true;
            return this;
        } else if (i2 == 1) {
            this.mPlayTogether = false;
            return this;
        } else {
            throw new AndroidRuntimeException(C0086a.i(i2, "Invalid parameter for TransitionSet ordering: "));
        }
    }

    public void setPathMotion(PathMotion pathMotion) {
        super.setPathMotion(pathMotion);
        this.mChangeFlags |= 4;
        if (this.mTransitions != null) {
            for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
                this.mTransitions.get(i2).setPathMotion(pathMotion);
            }
        }
    }

    public void setPropagation(TransitionPropagation transitionPropagation) {
        super.setPropagation(transitionPropagation);
        this.mChangeFlags |= 2;
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).setPropagation(transitionPropagation);
        }
    }

    public String toString(String str) {
        String transition = super.toString(str);
        for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
            StringBuilder t = C0212a.t(transition, "\n");
            t.append(this.mTransitions.get(i2).toString(str + "  "));
            transition = t.toString();
        }
        return transition;
    }

    public TransitionSet addListener(Transition.TransitionListener transitionListener) {
        return (TransitionSet) super.addListener(transitionListener);
    }

    public TransitionSet addTarget(View view) {
        for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
            this.mTransitions.get(i2).addTarget(view);
        }
        return (TransitionSet) super.addTarget(view);
    }

    public Transition clone() {
        TransitionSet transitionSet = (TransitionSet) super.clone();
        transitionSet.mTransitions = new ArrayList<>();
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            transitionSet.addTransitionInternal(this.mTransitions.get(i2).clone());
        }
        return transitionSet;
    }

    public TransitionSet removeListener(Transition.TransitionListener transitionListener) {
        return (TransitionSet) super.removeListener(transitionListener);
    }

    public TransitionSet removeTarget(View view) {
        for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
            this.mTransitions.get(i2).removeTarget(view);
        }
        return (TransitionSet) super.removeTarget(view);
    }

    public TransitionSet setDuration(long j2) {
        ArrayList<Transition> arrayList;
        super.setDuration(j2);
        if (this.mDuration >= 0 && (arrayList = this.mTransitions) != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.mTransitions.get(i2).setDuration(j2);
            }
        }
        return this;
    }

    public TransitionSet setInterpolator(TimeInterpolator timeInterpolator) {
        this.mChangeFlags |= 1;
        ArrayList<Transition> arrayList = this.mTransitions;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.mTransitions.get(i2).setInterpolator(timeInterpolator);
            }
        }
        return (TransitionSet) super.setInterpolator(timeInterpolator);
    }

    public TransitionSet setStartDelay(long j2) {
        return (TransitionSet) super.setStartDelay(j2);
    }
}
