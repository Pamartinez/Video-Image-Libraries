package com.samsung.android.gallery.app.ui.container.clipboard;

import A4.C0369d;
import B2.i;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.container.clipboard.ClipboardPresenter;
import com.samsung.android.gallery.app.ui.container.clipboard.IClipboardView;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import i4.C0468a;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClipboardFragment<V extends IClipboardView, P extends ClipboardPresenter> extends MvpBaseFragment<V, P> implements IClipboardView {
    ViewGroup mClipboardLayout;
    RecyclerView mClipboardView;
    private String mCurrentScreenId;
    TextView mNoItem;

    /* access modifiers changed from: private */
    public boolean onTouchNoItemView(View view, MotionEvent motionEvent) {
        return true;
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mClipboardLayout = (ViewGroup) view.findViewById(R.id.clipboard_layout);
        this.mNoItem = (TextView) view.findViewById(R.id.no_item_selected);
        this.mClipboardView = (RecyclerView) view.findViewById(R.id.recycler_list);
    }

    public void createClipboardView() {
        this.mClipboardView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false) {
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    Log.pke("ClipboardFragment", "onLayoutChildren fail=" + e.getMessage());
                    new InternalException("onLayoutChildren fail").post();
                }
            }
        });
        RecyclerView recyclerView = this.mClipboardView;
        ClipboardPresenter clipboardPresenter = (ClipboardPresenter) this.mPresenter;
        Objects.requireNonNull(clipboardPresenter);
        recyclerView.setOnTouchListener(new b(clipboardPresenter));
        this.mNoItem.setOnTouchListener(new i(21, this));
        this.mNoItem.setText(PickerUtil.getUsageDescription(this.mBlackboard));
    }

    public RecyclerView getClipboardView() {
        return this.mClipboardView;
    }

    public int getLayoutId() {
        return R.layout.clipboard_custom;
    }

    public String getScreenId() {
        return this.mCurrentScreenId;
    }

    public void handleDensityChange(int i2) {
        updateClipboardLayout();
    }

    public void handleResolutionChange(int i2) {
        if (!isConfigStateChanged(2)) {
            updateClipboardLayout();
        }
    }

    public void onBindView(View view) {
        createClipboardView();
    }

    public void onDestroy() {
        ((ClipboardPresenter) this.mPresenter).destroy();
        super.onDestroy();
    }

    public void restoreClipboardView() {
        createClipboardView();
    }

    public void setClipboardViewVisibility(int i2) {
        boolean z;
        if (i2 == 8) {
            z = true;
        } else {
            z = false;
        }
        updateVisibility(z);
    }

    public void setScreenId(String str) {
        this.mCurrentScreenId = str;
    }

    public boolean supportActivityToolbar() {
        return true;
    }

    public void updateClipboardLayout() {
        Context context = getContext();
        if (context != null) {
            int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen.clipboard_layout_height_without_search);
            Optional.ofNullable((ViewGroup) this.mClipboardLayout.getParent()).map(new C0468a(0)).ifPresent(new C0369d(dimensionPixelOffset, 24));
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(getLayoutId(), (ViewGroup) null, false);
            TextView textView = (TextView) viewGroup.findViewById(R.id.no_item_selected);
            RecyclerView recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recycler_list);
            ViewUtils.removeAllViews(viewGroup);
            ViewUtils.removeAllViews(this.mClipboardLayout);
            this.mClipboardLayout.getLayoutParams().height = dimensionPixelOffset;
            this.mClipboardLayout.addView(textView);
            this.mClipboardLayout.addView(recyclerView);
            this.mNoItem = textView;
            this.mClipboardView = recyclerView;
            restoreClipboardView();
            ((ClipboardPresenter) this.mPresenter).restore();
        }
    }

    public void updateVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mNoItem, z);
        ViewUtils.setVisibleOrGone(this.mClipboardView, !z);
    }

    public ClipboardPresenter createPresenter(IClipboardView iClipboardView) {
        return new ClipboardPresenter(this.mBlackboard, iClipboardView);
    }

    public void postAnalyticsLog() {
    }

    public void initView(View view) {
    }
}
