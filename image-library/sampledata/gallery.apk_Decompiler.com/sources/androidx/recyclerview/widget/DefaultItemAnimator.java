package androidx.recyclerview.widget;

import N2.j;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DefaultItemAnimator extends SimpleItemAnimator {
    private static final Interpolator ITEM_MOVE_INTERPOLATOR = new PathInterpolator(0.4f, 0.6f, 0.0f, 1.0f);
    private static TimeInterpolator sDefaultInterpolator;
    ArrayList<RecyclerView.ViewHolder> mAddAnimations = new ArrayList<>();
    ArrayList<ArrayList<RecyclerView.ViewHolder>> mAdditionsList = new ArrayList<>();
    ArrayList<RecyclerView.ViewHolder> mChangeAnimations = new ArrayList<>();
    ArrayList<ArrayList<ChangeInfo>> mChangesList = new ArrayList<>();
    private boolean mEnableRemoveDelay = false;
    private int mLastItemBottom = 0;
    ArrayList<RecyclerView.ViewHolder> mMoveAnimations = new ArrayList<>();
    ArrayList<ArrayList<MoveInfo>> mMovesList = new ArrayList<>();
    private ArrayList<RecyclerView.ViewHolder> mPendingAdditions = new ArrayList<>();
    /* access modifiers changed from: private */
    public int mPendingAnimFlag = 0;
    private ArrayList<ChangeInfo> mPendingChanges = new ArrayList<>();
    private ArrayList<MoveInfo> mPendingMoves = new ArrayList<>();
    private ArrayList<RecyclerView.ViewHolder> mPendingRemovals = new ArrayList<>();
    ArrayList<RecyclerView.ViewHolder> mRemoveAnimations = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MoveInfo {
        public int fromX;
        public int fromY;
        public RecyclerView.ViewHolder holder;
        public int toX;
        public int toY;

        public MoveInfo(RecyclerView.ViewHolder viewHolder, int i2, int i7, int i8, int i10) {
            this.holder = viewHolder;
            this.fromX = i2;
            this.fromY = i7;
            this.toX = i8;
            this.toY = i10;
        }
    }

    public static /* synthetic */ int access$072(DefaultItemAnimator defaultItemAnimator, int i2) {
        int i7 = i2 & defaultItemAnimator.mPendingAnimFlag;
        defaultItemAnimator.mPendingAnimFlag = i7;
        return i7;
    }

    public static /* synthetic */ int access$076(DefaultItemAnimator defaultItemAnimator, int i2) {
        int i7 = i2 | defaultItemAnimator.mPendingAnimFlag;
        defaultItemAnimator.mPendingAnimFlag = i7;
        return i7;
    }

    private void animateRemoveImpl(final RecyclerView.ViewHolder viewHolder) {
        final View view = viewHolder.itemView;
        final ViewPropertyAnimator animate = view.animate();
        long removeDuration = getRemoveDuration();
        if (view.getTag() != null && view.getTag().equals("preferencecategory")) {
            removeDuration = 0;
        }
        this.mRemoveAnimations.add(viewHolder);
        animate.setDuration(removeDuration).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                animate.setListener((Animator.AnimatorListener) null);
                view.setAlpha(1.0f);
                DefaultItemAnimator.this.dispatchRemoveFinished(viewHolder);
                DefaultItemAnimator.this.mRemoveAnimations.remove(viewHolder);
                DefaultItemAnimator.this.dispatchFinishedWhenDone();
                if ((DefaultItemAnimator.this.mPendingAnimFlag & 1) != 0) {
                    DefaultItemAnimator.access$072(DefaultItemAnimator.this, -2);
                }
            }

            public void onAnimationStart(Animator animator) {
                DefaultItemAnimator.this.dispatchRemoveStarting(viewHolder);
            }
        }).start();
    }

    private void endChangeAnimation(List<ChangeInfo> list, RecyclerView.ViewHolder viewHolder) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ChangeInfo changeInfo = list.get(size);
            if (endChangeAnimationIfNecessary(changeInfo, viewHolder) && changeInfo.oldHolder == null && changeInfo.newHolder == null) {
                list.remove(changeInfo);
            }
        }
    }

    private void endChangeAnimationIfNecessary(ChangeInfo changeInfo) {
        RecyclerView.ViewHolder viewHolder = changeInfo.oldHolder;
        if (viewHolder != null) {
            endChangeAnimationIfNecessary(changeInfo, viewHolder);
        }
        RecyclerView.ViewHolder viewHolder2 = changeInfo.newHolder;
        if (viewHolder2 != null) {
            endChangeAnimationIfNecessary(changeInfo, viewHolder2);
        }
    }

    private void resetAnimation(RecyclerView.ViewHolder viewHolder) {
        if (sDefaultInterpolator == null) {
            sDefaultInterpolator = new ValueAnimator().getInterpolator();
        }
        viewHolder.itemView.animate().setInterpolator(sDefaultInterpolator);
        endAnimation(viewHolder);
    }

    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        resetAnimation(viewHolder);
        viewHolder.itemView.setAlpha(0.0f);
        this.mPendingAdditions.add(viewHolder);
        int i2 = this.mPendingAnimFlag;
        if ((i2 & 8) != 0) {
            return true;
        }
        this.mPendingAnimFlag = i2 | 8;
        return true;
    }

    public void animateAddImpl(final RecyclerView.ViewHolder viewHolder) {
        final View view = viewHolder.itemView;
        final ViewPropertyAnimator animate = view.animate();
        long addDuration = getAddDuration();
        if (view.getTag() != null && view.getTag().equals("preferencecategory")) {
            addDuration = 0;
        }
        this.mAddAnimations.add(viewHolder);
        animate.alpha(1.0f).setDuration(addDuration).setListener(new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator animator) {
                view.setAlpha(1.0f);
            }

            public void onAnimationEnd(Animator animator) {
                animate.setListener((Animator.AnimatorListener) null);
                DefaultItemAnimator.this.dispatchAddFinished(viewHolder);
                DefaultItemAnimator.this.mAddAnimations.remove(viewHolder);
                DefaultItemAnimator.this.dispatchFinishedWhenDone();
                if ((DefaultItemAnimator.this.mPendingAnimFlag & 8) != 0) {
                    DefaultItemAnimator.access$072(DefaultItemAnimator.this, -9);
                }
                if ((DefaultItemAnimator.this.mPendingAnimFlag & 16) != 0) {
                    DefaultItemAnimator.access$072(DefaultItemAnimator.this, -17);
                }
            }

            public void onAnimationStart(Animator animator) {
                DefaultItemAnimator.this.dispatchAddStarting(viewHolder);
            }
        }).start();
    }

    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i2, int i7, int i8, int i10) {
        if (viewHolder == viewHolder2) {
            return animateMove(viewHolder, i2, i7, i8, i10);
        }
        float translationX = viewHolder.itemView.getTranslationX();
        float translationY = viewHolder.itemView.getTranslationY();
        float alpha = viewHolder.itemView.getAlpha();
        resetAnimation(viewHolder);
        int i11 = (int) (((float) (i8 - i2)) - translationX);
        int i12 = (int) (((float) (i10 - i7)) - translationY);
        viewHolder.itemView.setTranslationX(translationX);
        viewHolder.itemView.setTranslationY(translationY);
        viewHolder.itemView.setAlpha(alpha);
        if (viewHolder2 != null) {
            resetAnimation(viewHolder2);
            viewHolder2.itemView.setTranslationX((float) (-i11));
            viewHolder2.itemView.setTranslationY((float) (-i12));
            viewHolder2.itemView.setAlpha(0.0f);
        }
        this.mPendingChanges.add(new ChangeInfo(viewHolder, viewHolder2, i2, i7, i8, i10));
        int i13 = this.mPendingAnimFlag;
        if ((i13 & 4) != 0) {
            return true;
        }
        this.mPendingAnimFlag = i13 | 4;
        return true;
    }

    public void animateChangeImpl(final ChangeInfo changeInfo) {
        final View view;
        RecyclerView.ViewHolder viewHolder = changeInfo.oldHolder;
        final View view2 = null;
        if (viewHolder == null) {
            view = null;
        } else {
            view = viewHolder.itemView;
        }
        RecyclerView.ViewHolder viewHolder2 = changeInfo.newHolder;
        if (viewHolder2 != null) {
            view2 = viewHolder2.itemView;
        }
        if (view != null) {
            final ViewPropertyAnimator duration = view.animate().setDuration(getChangeDuration());
            this.mChangeAnimations.add(changeInfo.oldHolder);
            duration.translationX((float) (changeInfo.toX - changeInfo.fromX));
            duration.translationY((float) (changeInfo.toY - changeInfo.fromY));
            duration.alpha(0.0f).setDuration(getChangeDuration()).setInterpolator(ITEM_MOVE_INTERPOLATOR).setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    duration.setListener((Animator.AnimatorListener) null);
                    view.setAlpha(1.0f);
                    view.setTranslationX(0.0f);
                    view.setTranslationY(0.0f);
                    DefaultItemAnimator.this.dispatchChangeFinished(changeInfo.oldHolder, true);
                    DefaultItemAnimator.this.mChangeAnimations.remove(changeInfo.oldHolder);
                    DefaultItemAnimator.this.dispatchFinishedWhenDone();
                    if ((DefaultItemAnimator.this.mPendingAnimFlag & 4) != 0) {
                        DefaultItemAnimator.access$072(DefaultItemAnimator.this, -5);
                    }
                }

                public void onAnimationStart(Animator animator) {
                    DefaultItemAnimator.this.dispatchChangeStarting(changeInfo.oldHolder, true);
                }
            }).start();
        }
        if (view2 != null) {
            final ViewPropertyAnimator animate = view2.animate();
            this.mChangeAnimations.add(changeInfo.newHolder);
            animate.translationX(0.0f).translationY(0.0f).setDuration(getChangeDuration()).alpha(1.0f).setInterpolator(ITEM_MOVE_INTERPOLATOR).setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    animate.setListener((Animator.AnimatorListener) null);
                    view2.setAlpha(1.0f);
                    view2.setTranslationX(0.0f);
                    view2.setTranslationY(0.0f);
                    DefaultItemAnimator.this.dispatchChangeFinished(changeInfo.newHolder, false);
                    DefaultItemAnimator.this.mChangeAnimations.remove(changeInfo.newHolder);
                    DefaultItemAnimator.this.dispatchFinishedWhenDone();
                }

                public void onAnimationStart(Animator animator) {
                    DefaultItemAnimator.this.dispatchChangeStarting(changeInfo.newHolder, false);
                }
            }).start();
        }
    }

    public boolean animateMove(RecyclerView.ViewHolder viewHolder, int i2, int i7, int i8, int i10) {
        View view = viewHolder.itemView;
        int translationX = i2 + ((int) view.getTranslationX());
        int translationY = i7 + ((int) viewHolder.itemView.getTranslationY());
        resetAnimation(viewHolder);
        int i11 = i8 - translationX;
        int i12 = i10 - translationY;
        if (i11 == 0 && i12 == 0) {
            dispatchMoveFinished(viewHolder);
            return false;
        }
        if (i11 != 0) {
            view.setTranslationX((float) (-i11));
        }
        if (i12 != 0) {
            view.setTranslationY((float) (-i12));
        }
        this.mPendingMoves.add(new MoveInfo(viewHolder, translationX, translationY, i8, i10));
        int i13 = this.mPendingAnimFlag;
        if ((i13 & 2) != 0) {
            return true;
        }
        this.mPendingAnimFlag = i13 | 2;
        return true;
    }

    public void animateMoveImpl(RecyclerView.ViewHolder viewHolder, int i2, int i7, int i8, int i10) {
        final View view = viewHolder.itemView;
        final int i11 = i8 - i2;
        final int i12 = i10 - i7;
        if (i11 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i12 != 0) {
            view.animate().translationY(0.0f);
        }
        final ViewPropertyAnimator animate = view.animate();
        animate.setInterpolator(ITEM_MOVE_INTERPOLATOR);
        this.mMoveAnimations.add(viewHolder);
        if (getHostView() instanceof RecyclerView) {
            final RecyclerView recyclerView = (RecyclerView) getHostView();
            if (recyclerView.mBlackTop != -1 && viewHolder.getLayoutPosition() == recyclerView.mChildHelper.getChildCount() - 1) {
                animate.setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        recyclerView.invalidate();
                    }
                });
            }
        }
        final RecyclerView.ViewHolder viewHolder2 = viewHolder;
        animate.setDuration(getMoveDuration()).setListener(new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator animator) {
                if (i11 != 0) {
                    view.setTranslationX(0.0f);
                }
                if (i12 != 0) {
                    view.setTranslationY(0.0f);
                }
            }

            public void onAnimationEnd(Animator animator) {
                animate.setListener((Animator.AnimatorListener) null);
                DefaultItemAnimator.this.dispatchMoveFinished(viewHolder2);
                DefaultItemAnimator.this.mMoveAnimations.remove(viewHolder2);
                DefaultItemAnimator.this.dispatchFinishedWhenDone();
                if ((DefaultItemAnimator.this.mPendingAnimFlag & 2) != 0) {
                    DefaultItemAnimator.access$072(DefaultItemAnimator.this, -3);
                }
                if ((DefaultItemAnimator.this.mPendingAnimFlag & 8) != 0) {
                    DefaultItemAnimator.access$076(DefaultItemAnimator.this, 16);
                }
            }

            public void onAnimationStart(Animator animator) {
                DefaultItemAnimator.this.dispatchMoveStarting(viewHolder2);
            }
        }).start();
    }

    public boolean animateRemove(RecyclerView.ViewHolder viewHolder) {
        resetAnimation(viewHolder);
        this.mPendingRemovals.add(viewHolder);
        if (viewHolder.itemView.getBottom() > this.mLastItemBottom) {
            this.mLastItemBottom = viewHolder.itemView.getBottom();
        }
        int i2 = this.mPendingAnimFlag;
        if ((i2 & 1) == 0) {
            this.mPendingAnimFlag = i2 | 1;
        }
        return true;
    }

    public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder, List<Object> list) {
        if (!list.isEmpty() || super.canReuseUpdatedViewHolder(viewHolder, list)) {
            return true;
        }
        return false;
    }

    public void cancelAll(List<RecyclerView.ViewHolder> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).itemView.animate().cancel();
        }
    }

    public void clearPendingAnimFlag() {
        this.mPendingAnimFlag = 0;
    }

    public void dispatchFinishedWhenDone() {
        if (!isRunning()) {
            dispatchAnimationsFinished();
        }
    }

    public void endAnimation(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        view.animate().cancel();
        int size = this.mPendingMoves.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            } else if (this.mPendingMoves.get(size).holder == viewHolder) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                dispatchMoveFinished(viewHolder);
                this.mPendingMoves.remove(size);
            }
        }
        endChangeAnimation(this.mPendingChanges, viewHolder);
        if (this.mPendingRemovals.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchRemoveFinished(viewHolder);
        }
        if (this.mPendingAdditions.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAddFinished(viewHolder);
        }
        for (int size2 = this.mChangesList.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = this.mChangesList.get(size2);
            endChangeAnimation(arrayList, viewHolder);
            if (arrayList.isEmpty()) {
                this.mChangesList.remove(size2);
            }
        }
        for (int size3 = this.mMovesList.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList2 = this.mMovesList.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                } else if (((MoveInfo) arrayList2.get(size4)).holder == viewHolder) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    dispatchMoveFinished(viewHolder);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.mMovesList.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.mAdditionsList.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList3 = this.mAdditionsList.get(size5);
            if (arrayList3.remove(viewHolder)) {
                view.setAlpha(1.0f);
                dispatchAddFinished(viewHolder);
                if (arrayList3.isEmpty()) {
                    this.mAdditionsList.remove(size5);
                }
            }
        }
        this.mRemoveAnimations.remove(viewHolder);
        this.mAddAnimations.remove(viewHolder);
        this.mChangeAnimations.remove(viewHolder);
        this.mMoveAnimations.remove(viewHolder);
        dispatchFinishedWhenDone();
    }

    public void endAnimations() {
        int size = this.mPendingMoves.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            MoveInfo moveInfo = this.mPendingMoves.get(size);
            View view = moveInfo.holder.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            dispatchMoveFinished(moveInfo.holder);
            this.mPendingMoves.remove(size);
        }
        for (int size2 = this.mPendingRemovals.size() - 1; size2 >= 0; size2--) {
            dispatchRemoveFinished(this.mPendingRemovals.get(size2));
            this.mPendingRemovals.remove(size2);
        }
        int size3 = this.mPendingAdditions.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.ViewHolder viewHolder = this.mPendingAdditions.get(size3);
            viewHolder.itemView.setAlpha(1.0f);
            dispatchAddFinished(viewHolder);
            this.mPendingAdditions.remove(size3);
        }
        for (int size4 = this.mPendingChanges.size() - 1; size4 >= 0; size4--) {
            endChangeAnimationIfNecessary(this.mPendingChanges.get(size4));
        }
        this.mPendingChanges.clear();
        if (isRunning()) {
            for (int size5 = this.mMovesList.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList = this.mMovesList.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    MoveInfo moveInfo2 = (MoveInfo) arrayList.get(size6);
                    View view2 = moveInfo2.holder.itemView;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    dispatchMoveFinished(moveInfo2.holder);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.mMovesList.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.mAdditionsList.size() - 1; size7 >= 0; size7--) {
                ArrayList arrayList2 = this.mAdditionsList.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.ViewHolder viewHolder2 = (RecyclerView.ViewHolder) arrayList2.get(size8);
                    viewHolder2.itemView.setAlpha(1.0f);
                    dispatchAddFinished(viewHolder2);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.mAdditionsList.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.mChangesList.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList3 = this.mChangesList.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    endChangeAnimationIfNecessary((ChangeInfo) arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.mChangesList.remove(arrayList3);
                    }
                }
            }
            cancelAll(this.mRemoveAnimations);
            cancelAll(this.mMoveAnimations);
            cancelAll(this.mAddAnimations);
            cancelAll(this.mChangeAnimations);
            dispatchAnimationsFinished();
        }
    }

    public long getAddDuration() {
        return 200;
    }

    public long getChangeDuration() {
        return 400;
    }

    public int getLastItemBottom() {
        return this.mLastItemBottom;
    }

    public long getMoveDuration() {
        return 400;
    }

    public int getPendingAnimFlag() {
        return this.mPendingAnimFlag;
    }

    public long getRemoveDuration() {
        return 100;
    }

    public boolean isRunning() {
        if (!this.mPendingAdditions.isEmpty() || !this.mPendingChanges.isEmpty() || !this.mPendingMoves.isEmpty() || !this.mPendingRemovals.isEmpty() || !this.mMoveAnimations.isEmpty() || !this.mRemoveAnimations.isEmpty() || !this.mAddAnimations.isEmpty() || !this.mChangeAnimations.isEmpty() || !this.mMovesList.isEmpty() || !this.mAdditionsList.isEmpty() || !this.mChangesList.isEmpty()) {
            return true;
        }
        return false;
    }

    public void runPendingAnimations() {
        boolean isEmpty = this.mPendingRemovals.isEmpty();
        boolean isEmpty2 = this.mPendingMoves.isEmpty();
        boolean isEmpty3 = this.mPendingChanges.isEmpty();
        boolean isEmpty4 = this.mPendingAdditions.isEmpty();
        if (!isEmpty || !isEmpty2 || !isEmpty4 || !isEmpty3) {
            Iterator<RecyclerView.ViewHolder> it = this.mPendingRemovals.iterator();
            while (it.hasNext()) {
                animateRemoveImpl(it.next());
            }
            this.mPendingRemovals.clear();
            if (!isEmpty2) {
                final ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.mPendingMoves);
                this.mMovesList.add(arrayList);
                this.mPendingMoves.clear();
                AnonymousClass1 r62 = new Runnable() {
                    public void run() {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            MoveInfo moveInfo = (MoveInfo) it.next();
                            DefaultItemAnimator.this.animateMoveImpl(moveInfo.holder, moveInfo.fromX, moveInfo.fromY, moveInfo.toX, moveInfo.toY);
                        }
                        arrayList.clear();
                        DefaultItemAnimator.this.mMovesList.remove(arrayList);
                    }
                };
                if (isEmpty || !this.mEnableRemoveDelay) {
                    r62.run();
                } else {
                    ViewCompat.postOnAnimationDelayed(((MoveInfo) arrayList.get(0)).holder.itemView, r62, getRemoveDuration());
                }
            }
            if (!isEmpty3) {
                final ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.mPendingChanges);
                this.mChangesList.add(arrayList2);
                this.mPendingChanges.clear();
                AnonymousClass2 r63 = new Runnable() {
                    public void run() {
                        Iterator it = arrayList2.iterator();
                        while (it.hasNext()) {
                            DefaultItemAnimator.this.animateChangeImpl((ChangeInfo) it.next());
                        }
                        arrayList2.clear();
                        DefaultItemAnimator.this.mChangesList.remove(arrayList2);
                    }
                };
                if (isEmpty || !this.mEnableRemoveDelay) {
                    r63.run();
                } else {
                    ViewCompat.postOnAnimationDelayed(((ChangeInfo) arrayList2.get(0)).oldHolder.itemView, r63, getRemoveDuration());
                }
            }
            if (!isEmpty4) {
                final ArrayList arrayList3 = new ArrayList();
                arrayList3.addAll(this.mPendingAdditions);
                this.mAdditionsList.add(arrayList3);
                this.mPendingAdditions.clear();
                AnonymousClass3 r5 = new Runnable() {
                    public void run() {
                        Iterator it = arrayList3.iterator();
                        while (it.hasNext()) {
                            DefaultItemAnimator.this.animateAddImpl((RecyclerView.ViewHolder) it.next());
                        }
                        arrayList3.clear();
                        DefaultItemAnimator.this.mAdditionsList.remove(arrayList3);
                    }
                };
                if (!isEmpty || !isEmpty2 || !isEmpty3) {
                    View view = ((RecyclerView.ViewHolder) arrayList3.get(0)).itemView;
                    if (view.getTag() == null || !view.getTag().equals("preferencecategory")) {
                        ViewCompat.postOnAnimationDelayed(view, r5, 100);
                    } else {
                        r5.run();
                    }
                } else {
                    r5.run();
                }
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ChangeInfo {
        public int fromX;
        public int fromY;
        public RecyclerView.ViewHolder newHolder;
        public RecyclerView.ViewHolder oldHolder;
        public int toX;
        public int toY;

        private ChangeInfo(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            this.oldHolder = viewHolder;
            this.newHolder = viewHolder2;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("ChangeInfo{oldHolder=");
            sb2.append(this.oldHolder);
            sb2.append(", newHolder=");
            sb2.append(this.newHolder);
            sb2.append(", fromX=");
            sb2.append(this.fromX);
            sb2.append(", fromY=");
            sb2.append(this.fromY);
            sb2.append(", toX=");
            sb2.append(this.toX);
            sb2.append(", toY=");
            return j.e(sb2, this.toY, '}');
        }

        public ChangeInfo(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i2, int i7, int i8, int i10) {
            this(viewHolder, viewHolder2);
            this.fromX = i2;
            this.fromY = i7;
            this.toX = i8;
            this.toY = i10;
        }
    }

    private boolean endChangeAnimationIfNecessary(ChangeInfo changeInfo, RecyclerView.ViewHolder viewHolder) {
        boolean z = false;
        if (changeInfo.newHolder == viewHolder) {
            changeInfo.newHolder = null;
        } else if (changeInfo.oldHolder != viewHolder) {
            return false;
        } else {
            changeInfo.oldHolder = null;
            z = true;
        }
        viewHolder.itemView.setAlpha(1.0f);
        viewHolder.itemView.setTranslationX(0.0f);
        viewHolder.itemView.setTranslationY(0.0f);
        dispatchChangeFinished(viewHolder, z);
        return true;
    }
}
