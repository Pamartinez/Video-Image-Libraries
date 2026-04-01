package com.samsung.android.gallery.settings.ui;

import A4.C0368c;
import Ab.b;
import D7.g;
import Da.f;
import E3.c;
import Fa.F;
import Fa.Z;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Outline;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.helper.Troubleshooting;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TroubleshootingFragment extends BaseFragment {
    private TroubleshootingAdapter mAdapter;
    private final AtomicBoolean mDiagnosed = new AtomicBoolean(false);
    private View mProgressBar;
    private RecyclerView mTroubleshootingView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TroubleResolverViewHolder extends RecyclerView.ViewHolder {
        final Button mButton;
        OnViewHolderClickListener mListener;
        Troubleshooting.TroubleResolver mResolver;
        final TextView mSummary;
        final TextView mTitle;
        final TextView mTodo;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public interface OnViewHolderClickListener {
        }

        public TroubleResolverViewHolder(View view) {
            super(view);
            this.mTitle = (TextView) view.findViewById(R$id.title);
            this.mSummary = (TextView) view.findViewById(R$id.summary);
            this.mTodo = (TextView) view.findViewById(R$id.todo);
            Button button = (Button) view.findViewById(R$id.button);
            this.mButton = button;
            button.setOnClickListener(new O(1, this));
            setRoundCorner(view, 48);
        }

        /* access modifiers changed from: private */
        public void lambda$new$0(View view) {
            OnViewHolderClickListener onViewHolderClickListener = this.mListener;
            if (onViewHolderClickListener != null) {
                ((TroubleshootingFragment) ((y) onViewHolderClickListener).e).onListItemClicked(getAdapterPosition(), this, this.mResolver);
            }
        }

        private void setRoundCorner(View view, final int i2) {
            view.setOutlineProvider(new ViewOutlineProvider() {
                public void getOutline(View view, Outline outline) {
                    if (view.getWidth() > 0) {
                        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), (float) i2);
                    }
                }
            });
            view.setClipToOutline(true);
        }

        public void bind(Troubleshooting.TroubleResolver troubleResolver, OnViewHolderClickListener onViewHolderClickListener) {
            int i2;
            this.mResolver = troubleResolver;
            this.mTitle.setText(troubleResolver.getTitle());
            this.mSummary.setText(troubleResolver.getSummary());
            this.mTodo.setText(troubleResolver.getTodo());
            TextView textView = this.mTodo;
            int i7 = 8;
            if (troubleResolver.hasIssues()) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            textView.setVisibility(i2);
            Button button = this.mButton;
            if (troubleResolver.hasResolver()) {
                i7 = 0;
            }
            button.setVisibility(i7);
            this.mListener = onViewHolderClickListener;
        }

        public void recycle() {
            this.mTitle.setText((CharSequence) null);
            this.mSummary.setText((CharSequence) null);
            this.mTodo.setText((CharSequence) null);
            this.mTodo.setVisibility(8);
            this.mButton.setVisibility(8);
            this.mResolver = null;
            this.mListener = null;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class TroubleshootingAdapter extends RecyclerView.Adapter<TroubleResolverViewHolder> {
        protected final Object LOCK = new Object();
        private final TroubleResolverViewHolder.OnViewHolderClickListener mClickListener;
        protected ArrayList<Troubleshooting.TroubleResolver> mData = new ArrayList<>();
        protected LayoutInflater mLayoutInflater;

        public TroubleshootingAdapter(TroubleshootingFragment troubleshootingFragment) {
            Objects.requireNonNull(troubleshootingFragment);
            this.mClickListener = new y(4, troubleshootingFragment);
        }

        public int getItemCount() {
            return this.mData.size();
        }

        public final LayoutInflater getLayoutInflater() {
            if (this.mLayoutInflater == null) {
                this.mLayoutInflater = LayoutInflater.from(TroubleshootingFragment.this.getContext());
            }
            return this.mLayoutInflater;
        }

        public boolean swap(ArrayList<Troubleshooting.TroubleResolver> arrayList) {
            if (arrayList == null) {
                return true;
            }
            synchronized (this.LOCK) {
                this.mData = arrayList;
            }
            notifyDataSetChanged();
            return true;
        }

        public void onBindViewHolder(TroubleResolverViewHolder troubleResolverViewHolder, int i2) {
            troubleResolverViewHolder.bind(this.mData.get(i2), this.mClickListener);
        }

        public TroubleResolverViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return new TroubleResolverViewHolder(getLayoutInflater().inflate(R$layout.troubleshooting_item_layout, viewGroup, false));
        }

        public void onViewRecycled(TroubleResolverViewHolder troubleResolverViewHolder) {
            troubleResolverViewHolder.recycle();
        }
    }

    private void executeDiagnose() {
        if (!this.mDiagnosed.getAndSet(true)) {
            SimpleThreadPool.getInstance().execute(new c(this, System.currentTimeMillis(), 1));
        }
    }

    private TroubleshootingAdapter getAdapter() {
        if (this.mAdapter == null) {
            this.mAdapter = new TroubleshootingAdapter(this);
        }
        return this.mAdapter;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeDiagnose$0(ArrayList arrayList) {
        getAdapter().swap(arrayList);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeDiagnose$1() {
        this.mProgressBar.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeDiagnose$2(long j2) {
        long j3;
        ArrayList<Troubleshooting.TroubleResolver> resolver = Troubleshooting.getInstance().getResolver();
        resolver.forEach(new F(7));
        ThreadUtil.postOnUiThread(new f(25, this, resolver));
        long currentTimeMillis = System.currentTimeMillis() - j2;
        if (currentTimeMillis > 1000) {
            j3 = 10;
        } else {
            j3 = 1000 - currentTimeMillis;
        }
        ThreadUtil.postOnUiThreadDelayed(new g(17, this), j3);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onListItemClicked$3(Troubleshooting.TroubleResolver troubleResolver, int i2, DialogInterface dialogInterface, int i7) {
        resolveIssue(troubleResolver, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resolveIssue$4(int i2) {
        getAdapter().notifyItemChanged(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resolveIssue$5(Troubleshooting.TroubleResolver troubleResolver, int i2) {
        int resolve = troubleResolver.resolve();
        String str = this.TAG;
        Log.d(str, "resolve result : " + resolve);
        troubleResolver.test();
        ThreadUtil.postOnUiThread(new C0368c(this, i2, 6));
        if (resolve > 0) {
            BlackboardUtils.forceRefreshPicturesData(getBlackboard(), false);
        }
    }

    /* access modifiers changed from: private */
    public void onListItemClicked(int i2, TroubleResolverViewHolder troubleResolverViewHolder, Troubleshooting.TroubleResolver troubleResolver) {
        String str = this.TAG;
        Log.d(str, "onListItemClicked " + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + troubleResolver);
        if (!troubleResolver.requireConfirm()) {
            resolveIssue(troubleResolver, i2);
        } else {
            new AlertDialog.Builder(getContext()).setTitle((CharSequence) troubleResolver.getTitle()).setMessage((CharSequence) "Are you sure?").setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) new Z(this, troubleResolver, i2)).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) null).setCancelable(true).create().show();
        }
    }

    private void resolveIssue(Troubleshooting.TroubleResolver troubleResolver, int i2) {
        SimpleThreadPool.getInstance().execute(new b((Object) this, (Object) troubleResolver, i2, 15));
    }

    public void bindViews(ViewGroup viewGroup) {
        if (this.mTroubleshootingView == null) {
            RecyclerView recyclerView = (RecyclerView) viewGroup.findViewById(R$id.troubleshooting);
            this.mTroubleshootingView = recyclerView;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
            this.mTroubleshootingView.setAdapter(getAdapter());
            executeDiagnose();
        }
        if (this.mProgressBar == null) {
            this.mProgressBar = viewGroup.findViewById(R$id.progress);
        }
    }

    public int getLayoutId() {
        return R$layout.fragment_troubleshooting_layout;
    }

    public int getTitleId() {
        return R$string.labs_title_troubleshooting;
    }

    public void onConfigurationChanged(Configuration configuration) {
        RecyclerView recyclerView = this.mTroubleshootingView;
        if (recyclerView != null) {
            recyclerView.setAdapter((RecyclerView.Adapter) null);
            this.mTroubleshootingView = null;
        }
        super.onConfigurationChanged(configuration);
    }
}
