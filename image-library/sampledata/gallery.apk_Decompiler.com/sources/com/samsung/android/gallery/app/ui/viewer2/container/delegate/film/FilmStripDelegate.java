package com.samsung.android.gallery.app.ui.viewer2.container.delegate.film;

import A.a;
import A4.C0368c;
import Fa.C0569x;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewStub;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MutableValue;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripAdapter;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripLayoutManager;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripView;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.OnFilmStripItemClickListener;
import com.samsung.android.gallery.widget.filmstrip3.SeekerView;
import com.samsung.android.gallery.widget.recyclerview.FilmStripViewPool;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;
import ic.l;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import k6.b;
import l7.C0483a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilmStripDelegate extends AbsVuDelegate<IVuContainerView> implements OnFilmStripItemClickListener {
    private final MutableValue<Boolean> heavyLog = new MutableValue<>(Boolean.FALSE);
    private FilmStripViewHolder mCurrentVh;
    /* access modifiers changed from: private */
    public boolean mDataChanging;
    /* access modifiers changed from: private */
    public final Handler mFilmScrollHandler = new Handler(Looper.getMainLooper());
    protected FilmStripAdapter mFilmStripAdapter;
    private final RecyclerView.OnScrollListener mFilmStripScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (i2 == 1) {
                FilmStripDelegate.this.mActionInvoker.invoke(ViewerAction.TURN_OFF_HDR_MODE_NO_DELAY, new Object[0]);
                FilmStripLayoutManager filmStripLayoutManager = (FilmStripLayoutManager) FilmStripDelegate.this.mFilmStripView.getLayoutManager();
                if (filmStripLayoutManager == null || !filmStripLayoutManager.isSeekerMode()) {
                    AnalyticsLogger.getInstance().postLog(((IVuContainerView) FilmStripDelegate.this.mView).getScreenId(), AnalyticsEventId.EVENT_DETAIL_VIEW_FILM_STRIP.toString(), AnalyticsDetail.FILM_STRIP_FLICK.toString());
                } else {
                    AnalyticsLogger.getInstance().postLog(((IVuContainerView) FilmStripDelegate.this.mView).getScreenId(), AnalyticsEventId.EVENT_DETAIL_VIEW_VIDEO_SEEK.toString());
                }
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i2, int i7) {
            FilmStripViewHolder centerViewHolder;
            boolean z;
            FilmStripDelegate.this.mRecoverFocusHandler.removeCallbacksAndMessages((Object) null);
            FilmStripDelegate filmStripDelegate = FilmStripDelegate.this;
            if (filmStripDelegate.mFilmStripView != null && !filmStripDelegate.mDataChanging && (centerViewHolder = FilmStripDelegate.this.mFilmStripView.getCenterViewHolder()) != null && !FilmStripDelegate.this.ignoreScroll(centerViewHolder)) {
                if (i2 != 0) {
                    FilmStripDelegate.this.onFilmFocusOut();
                }
                FilmStripDelegate.this.mFilmScrollHandler.removeCallbacksAndMessages((Object) null);
                if (centerViewHolder.isExpanded()) {
                    String access$300 = FilmStripDelegate.this.TAG;
                    Log.d(access$300, "Focus IN EXPANDED!!! pos = " + centerViewHolder.getViewHolderPosition());
                    FilmStripDelegate.this.focusIn(centerViewHolder);
                    return;
                }
                FilmStripDelegate filmStripDelegate2 = FilmStripDelegate.this;
                if (i2 == 0 && i7 == 0) {
                    z = true;
                } else {
                    z = false;
                }
                filmStripDelegate2.confirmFilmStrip(centerViewHolder, z);
            }
        }
    };
    protected FilmStripView mFilmStripView;
    private final FilmStripViewPool mFilmStripViewPool = ((FilmStripViewPool) ((FilmStripViewPool) ((FilmStripViewPool) ((FilmStripViewPool) new FilmStripViewPool().setMaxSize(1, 30)).setMaxSize(20, 30)).setMaxSize(2, 30)).setMaxSize(21, 10));
    private final ArrayList<OnFilmStripItemClickListener> mOnFilmStripItemClickListener = new ArrayList<>();
    /* access modifiers changed from: private */
    public final Handler mRecoverFocusHandler = new Handler(Looper.getMainLooper());
    private SeekerView mSeekerView;
    private ViewPager2 mViewPager;

    public FilmStripDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private void bindFilmStripView(View view) {
        FilmStripView filmStripView = this.mFilmStripView;
        if (filmStripView != null) {
            filmStripView.clearOnScrollListeners();
        }
        View filmStripLayout = ((ContainerModel) this.mModel).getFilmStripLayout();
        if (filmStripLayout != null) {
            ViewUtils.setVisibility(filmStripLayout, 0);
            filmStripLayout.setBackground((Drawable) null);
        }
        FilmStripView filmStripView2 = (FilmStripView) view.findViewById(R.id.film_strip_view);
        this.mFilmStripView = filmStripView2;
        filmStripView2.setVisibility(4);
        this.mFilmStripView.addOnScrollListener(this.mFilmStripScrollListener);
        this.mSeekerView = (SeekerView) view.findViewById(R.id.seeker_view);
    }

    /* access modifiers changed from: private */
    public void confirmFilmStrip(FilmStripViewHolder filmStripViewHolder, boolean z) {
        boolean z3 = z;
        this.mFilmScrollHandler.postDelayed(new C0569x(this, z3, filmStripViewHolder, filmStripViewHolder.getViewHolderPosition(), 3), 100);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$confirmFilmStrip$5(boolean z, FilmStripViewHolder filmStripViewHolder, int i2) {
        if (!z && !isFilmStripScrollStateIdle()) {
            Log.w(this.TAG, "FilmStrip not idle");
        } else if (!isViewPagerScrollStateIdle()) {
            if (this.heavyLog.setValue(Boolean.TRUE)) {
                Log.d(this.TAG, "ViewPager scrolling");
            }
            confirmFilmStrip(filmStripViewHolder, false);
        } else {
            String str = this.TAG;
            Log.d(str, "ViewPager scrolled " + filmStripViewHolder);
            this.heavyLog.setValue(Boolean.FALSE);
            focusIn(filmStripViewHolder);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$recoverFocus$4(int i2) {
        FilmStripViewHolder filmStripViewHolder;
        try {
            FilmStripView filmStripView = this.mFilmStripView;
            if (filmStripView != null) {
                filmStripViewHolder = filmStripView.getCenterViewHolder();
            } else {
                filmStripViewHolder = null;
            }
            if (filmStripViewHolder == null || filmStripViewHolder.isFocused()) {
                Log.d(this.TAG, "recover failed", this.mCurrentVh, filmStripViewHolder, Integer.valueOf(i2));
                recoverFocus(i2 - 1);
                return;
            }
            Log.i(this.TAG, "recoverFocus", filmStripViewHolder);
            focusIn(filmStripViewHolder);
        } catch (Exception e) {
            a.s(e, new StringBuilder("recoverFocus failed. e="), this.TAG);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$0(Object[] objArr) {
        Optional.ofNullable(this.mFilmStripView).ifPresent(new l(26));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$1(Object[] objArr) {
        onViewPagerScrolled(objArr[0].intValue(), objArr[1].floatValue(), objArr[2].intValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setFilmStripAdapter$3() {
        FilmStripView filmStripView = this.mFilmStripView;
        if (filmStripView != null) {
            filmStripView.scrollToPosition(((ContainerModel) this.mModel).getPosition());
            this.mFilmStripView.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void onEnterTransitionStart(Object... objArr) {
        FilmStripView filmStripView = this.mFilmStripView;
        if (filmStripView != null) {
            this.mFilmStripViewPool.setRecycledViewPool(filmStripView);
            this.mFilmStripView.setAdapter(this.mFilmStripAdapter);
            this.mFilmStripView.scrollToPosition(((ContainerModel) this.mModel).getPosition());
            this.mFilmStripView.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void onFilmFocusOut() {
        FilmStripViewHolder filmStripViewHolder = this.mCurrentVh;
        if (filmStripViewHolder != null) {
            filmStripViewHolder.onFocusOut(this.mFilmStripView);
            this.mCurrentVh.setSelected(false);
            this.mCurrentVh.removeViewHolderListener(this);
            this.mCurrentVh = null;
            this.mSeekerView.setClingView((FilmStripViewHolder) null);
        }
    }

    /* access modifiers changed from: private */
    public void onReenterTransitionEnd(Object... objArr) {
        recoverFocus(1);
    }

    private void onViewPagerScrolled(int i2, float f, int i7) {
        this.mFilmScrollHandler.removeCallbacksAndMessages((Object) null);
        if (f != 0.0f) {
            onFilmFocusOut();
            return;
        }
        FilmStripViewHolder centerViewHolder = this.mFilmStripView.getCenterViewHolder();
        if (centerViewHolder != null) {
            confirmFilmStrip(centerViewHolder, true);
        }
    }

    private void recoverFocus(int i2) {
        this.mRecoverFocusHandler.removeCallbacksAndMessages((Object) null);
        if (i2 > 0) {
            this.mRecoverFocusHandler.postDelayed(new C0368c(this, i2, 28), 300);
        }
    }

    private void replaceFilmStripView() {
        View filmStripLayout = ((ContainerModel) this.mModel).getFilmStripLayout();
        ViewStub filmStripViewStub = ((ContainerModel) this.mModel).getFilmStripViewStub();
        if (filmStripLayout != null && filmStripViewStub != null) {
            ViewUtils.replaceView(filmStripLayout, filmStripViewStub);
            View inflate = filmStripViewStub.inflate();
            ((ContainerModel) this.mModel).setFilmStripLayout(inflate);
            bindFilmStripView(inflate);
            setFilmStripAdapter();
            this.mActionInvoker.invoke(ViewerAction.FILM_STRIP_LAYOUT_REPLACED, inflate);
        }
    }

    public void addFilmStripItemListener(OnFilmStripItemClickListener onFilmStripItemClickListener) {
        if (onFilmStripItemClickListener != null) {
            FilmStripAdapter filmStripAdapter = this.mFilmStripAdapter;
            if (filmStripAdapter != null) {
                filmStripAdapter.addFilmStripItemClickListener(onFilmStripItemClickListener);
            } else {
                this.mOnFilmStripItemClickListener.add(onFilmStripItemClickListener);
            }
        }
    }

    public void focusIn(FilmStripViewHolder filmStripViewHolder) {
        FilmStripViewHolder filmStripViewHolder2 = this.mCurrentVh;
        if (!(filmStripViewHolder2 == null || filmStripViewHolder2 == filmStripViewHolder || !filmStripViewHolder2.isFocused() || this.mCurrentVh.getViewHolderPosition() == filmStripViewHolder.getViewHolderPosition())) {
            String str = this.TAG;
            Log.v(str, "force.focus-out " + this.mCurrentVh.getViewHolderPosition());
            onFilmFocusOut();
        }
        this.mCurrentVh = filmStripViewHolder;
        filmStripViewHolder.addViewHolderListener(this);
        this.mSeekerView.setClingView(filmStripViewHolder);
        this.mCurrentVh.onFocused(this.mFilmStripView);
        this.mCurrentVh.setSelected(true);
    }

    public boolean ignoreScroll(FilmStripViewHolder filmStripViewHolder) {
        int width = (this.mFilmStripView.getWidth() / 2) + ((int) this.mFilmStripView.getX());
        if (filmStripViewHolder != this.mCurrentVh) {
            return false;
        }
        if (filmStripViewHolder.isExpanded() || filmStripViewHolder.isAnimating() || width == filmStripViewHolder.getCenterPos()) {
            return true;
        }
        return false;
    }

    public boolean isFilmStripScrollStateIdle() {
        FilmStripView filmStripView = this.mFilmStripView;
        if (filmStripView == null || filmStripView.getScrollState() != 0) {
            return false;
        }
        return true;
    }

    public boolean isFilmStripScrollStateSettling() {
        FilmStripView filmStripView = this.mFilmStripView;
        if (filmStripView == null || filmStripView.getScrollState() != 2) {
            return false;
        }
        return true;
    }

    public boolean isViewPagerScrollStateIdle() {
        ViewPager2 viewPager2 = this.mViewPager;
        if (viewPager2 == null || viewPager2.getScrollState() != 0) {
            return false;
        }
        return true;
    }

    public void onBindView(View view) {
        this.mViewPager = ((ContainerModel) this.mModel).getViewPager();
        bindFilmStripView(view);
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        if (z7) {
            replaceFilmStripView();
        }
    }

    public void onDestroy() {
        FilmStripViewHolder filmStripViewHolder = this.mCurrentVh;
        if (filmStripViewHolder != null) {
            filmStripViewHolder.removeViewHolderListener(this);
            this.mCurrentVh = null;
        }
        this.mFilmStripViewPool.onDestroy(this.mFilmStripView);
        this.mFilmStripAdapter.onDestroy();
        this.mFilmStripAdapter = null;
        this.mFilmStripView = null;
        this.mViewPager = null;
        this.mSeekerView = null;
        this.mRecoverFocusHandler.removeCallbacksAndMessages((Object) null);
        this.mOnFilmStripItemClickListener.clear();
    }

    public void onDump(PrintWriter printWriter, String str) {
        Object obj;
        super.onDump(printWriter, str);
        StringBuilder t = C0212a.t(str, "  mFilmStripView : ");
        t.append(this.mFilmStripView);
        FilmStripView filmStripView = this.mFilmStripView;
        if (filmStripView != null) {
            obj = Integer.valueOf(filmStripView.getVisibility());
        } else {
            obj = "";
        }
        t.append(obj);
        Logger.dumpLog(printWriter, t.toString());
        Logger.dumpLog(printWriter, str + "  mCurrentVh vh: " + this.mCurrentVh);
        if (this.mCurrentVh != null) {
            StringBuilder t3 = C0212a.t(str, "    mCurrentVh vh pos: ");
            t3.append(this.mCurrentVh.getViewHolderPosition());
            Logger.dumpLog(printWriter, t3.toString());
            Logger.dumpLog(printWriter, str + "    mCurrentVh vh mediaItem: " + this.mCurrentVh.getMediaItem());
        }
    }

    public void onItemExpanded(int i2, FilmStripViewHolder filmStripViewHolder) {
        a.k(i2, "onItemExpanded = ", this.TAG);
        FilmStripViewHolder filmStripViewHolder2 = this.mCurrentVh;
        if (filmStripViewHolder == filmStripViewHolder2) {
            this.mSeekerView.enableExpandedSeeker(filmStripViewHolder2.isExpanded());
        }
    }

    public void onItemRestored(int i2, FilmStripViewHolder filmStripViewHolder) {
        if (filmStripViewHolder == this.mCurrentVh) {
            this.mSeekerView.enableExpandedSeeker(false);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0141  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMediaDataChanged(int r17, int r18) {
        /*
            r16 = this;
            r0 = r16
            M r1 = r0.mModel
            com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel r1 = (com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel) r1
            com.samsung.android.gallery.module.dataset.MediaData r1 = r1.getMediaData()
            M r2 = r0.mModel
            com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel r2 = (com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel) r2
            int r2 = r2.getPosition()
            r3 = 1
            r0.mDataChanging = r3
            android.os.Handler r4 = r0.mFilmScrollHandler
            r5 = 0
            r4.removeCallbacksAndMessages(r5)
            com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder r4 = r0.mCurrentVh
            if (r4 == 0) goto L_0x0024
            int r4 = r4.getLayoutPosition()
            goto L_0x0025
        L_0x0024:
            r4 = -1
        L_0x0025:
            com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder r5 = r0.mCurrentVh
            java.lang.String r6 = " to "
            r7 = 0
            if (r5 == 0) goto L_0x0113
            boolean r5 = r5.isExpanded()
            if (r5 == 0) goto L_0x0113
            com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder r5 = r0.mCurrentVh
            com.samsung.android.gallery.module.data.MediaItem r5 = r5.getMediaItem()
            com.samsung.android.gallery.module.data.MediaItem r8 = r1.read(r2)
            boolean r9 = com.samsung.android.gallery.module.data.MediaItemUtil.equals(r5, r8)
            if (r9 != 0) goto L_0x00ee
            java.lang.String r9 = " "
            java.lang.String r10 = "null"
            java.lang.String r11 = ","
            if (r5 != 0) goto L_0x004c
            r5 = r10
            goto L_0x0090
        L_0x004c:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r5)
            r12.append(r9)
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = r5.getTitle()
            r13.append(r14)
            r13.append(r11)
            java.lang.String r14 = r5.getPath()
            r13.append(r14)
            r13.append(r11)
            double r14 = r5.getLatitude()
            r13.append(r14)
            r13.append(r11)
            double r14 = r5.getLongitude()
            r13.append(r14)
            java.lang.String r5 = r13.toString()
            java.lang.String r5 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r5)
            r12.append(r5)
            java.lang.String r5 = r12.toString()
        L_0x0090:
            if (r8 != 0) goto L_0x0093
            goto L_0x00d7
        L_0x0093:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r8)
            r10.append(r9)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r12 = r8.getTitle()
            r9.append(r12)
            r9.append(r11)
            java.lang.String r12 = r8.getPath()
            r9.append(r12)
            r9.append(r11)
            double r12 = r8.getLatitude()
            r9.append(r12)
            r9.append(r11)
            double r11 = r8.getLongitude()
            r9.append(r11)
            java.lang.String r8 = r9.toString()
            java.lang.String r8 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r8)
            r10.append(r8)
            java.lang.String r10 = r10.toString()
        L_0x00d7:
            java.lang.String r8 = r0.TAG
            java.lang.String r9 = ", newItem = "
            java.lang.String r11 = ", pos = "
            java.lang.String r12 = "onMediaDataChanged : restoreFrameView oldItem = "
            java.lang.StringBuilder r5 = c0.C0086a.q(r12, r5, r9, r10, r11)
            A.a.w(r5, r2, r8)
            com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder r5 = r0.mCurrentVh
            com.samsung.android.gallery.widget.filmstrip3.FilmStripView r8 = r0.mFilmStripView
            r5.restoreFrameView(r8, r7)
            goto L_0x0113
        L_0x00ee:
            if (r4 >= r2) goto L_0x0111
            java.lang.String r5 = r0.TAG
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "onMediaDataChanged : restoreFrameView from "
            r8.<init>(r9)
            r8.append(r4)
            r8.append(r6)
            r8.append(r2)
            java.lang.String r8 = r8.toString()
            com.samsung.android.gallery.support.utils.Log.d(r5, r8)
            com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder r5 = r0.mCurrentVh
            com.samsung.android.gallery.widget.filmstrip3.FilmStripView r8 = r0.mFilmStripView
            r5.restoreFrameView(r8)
            goto L_0x0113
        L_0x0111:
            r5 = r3
            goto L_0x0114
        L_0x0113:
            r5 = r7
        L_0x0114:
            r0.updateMediaData(r1)
            java.lang.String r1 = r0.TAG
            java.lang.String r8 = "onMediaDataChanged from "
            java.lang.String r4 = N2.j.b(r4, r2, r8, r6)
            com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder r6 = r0.mCurrentVh
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.samsung.android.gallery.support.utils.Log.d(r1, r4, r6)
            com.samsung.android.gallery.widget.filmstrip3.FilmStripAdapter r1 = r0.mFilmStripAdapter
            if (r1 == 0) goto L_0x0139
            r4 = r18
            if (r4 != r3) goto L_0x0136
            r3 = r17
            r1.notifyItemChanged(r3)
            goto L_0x0139
        L_0x0136:
            r1.notifyDataSetChanged()
        L_0x0139:
            if (r5 == 0) goto L_0x0141
            com.samsung.android.gallery.widget.filmstrip3.FilmStripView r1 = r0.mFilmStripView
            r1.setScrolledPosition(r2)
            goto L_0x0146
        L_0x0141:
            com.samsung.android.gallery.widget.filmstrip3.FilmStripView r1 = r0.mFilmStripView
            r1.scrollToPosition(r2)
        L_0x0146:
            r0.mDataChanging = r7
            r1 = 5
            r0.recoverFocus(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripDelegate.onMediaDataChanged(int, int):void");
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.mFilmStripAdapter == null) {
            this.mFilmStripAdapter = new FilmStripAdapter(((IVuContainerView) this.mView).getContext(), ((ContainerModel) this.mModel).getMediaData());
            Iterator<OnFilmStripItemClickListener> it = this.mOnFilmStripItemClickListener.iterator();
            while (it.hasNext()) {
                this.mFilmStripAdapter.addFilmStripItemClickListener(it.next());
            }
            this.mOnFilmStripItemClickListener.clear();
        }
    }

    public void removeFilmStripItemListener(OnFilmStripItemClickListener onFilmStripItemClickListener) {
        if (onFilmStripItemClickListener != null) {
            FilmStripAdapter filmStripAdapter = this.mFilmStripAdapter;
            if (filmStripAdapter != null) {
                filmStripAdapter.removeFilmStripItemClickListener(onFilmStripItemClickListener);
            } else {
                this.mOnFilmStripItemClickListener.remove(onFilmStripItemClickListener);
            }
        }
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.BACK_KEY_PRESSED, new C0483a(this, 0));
        actionInvoker.add(ViewerAction.VIEW_PAGER_PAGE_SCROLLED, new C0483a(this, 1));
        actionInvoker.add(ViewerAction.ENTER_TRANSITION_START, new C0483a(this, 2));
        actionInvoker.add(ViewerAction.VIDEO_EDITOR_REENTER_TRANSITION_END, new C0483a(this, 3));
    }

    public void setFilmStripAdapter() {
        this.mFilmStripView.setAdapter(this.mFilmStripAdapter);
        ThreadUtil.postOnUiThreadDelayed(new b(6, this), 50);
    }

    public void stopScroll() {
        FilmStripView filmStripView = this.mFilmStripView;
        if (filmStripView != null) {
            filmStripView.stopScroll();
        }
    }

    public void updateMediaData(MediaData mediaData) {
        boolean z;
        FilmStripAdapter filmStripAdapter = this.mFilmStripAdapter;
        if (filmStripAdapter != null) {
            MediaData mediaData2 = filmStripAdapter.getMediaData();
            FilmStripAdapter filmStripAdapter2 = this.mFilmStripAdapter;
            if (mediaData2 != mediaData) {
                z = true;
            } else {
                z = false;
            }
            filmStripAdapter2.setFilmStripData(mediaData, z);
        }
    }

    public void onEnterTransitionEnd() {
    }
}
