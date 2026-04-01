package com.samsung.android.gallery.app.ui.dialog;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.Arrays;
import java.util.Optional;
import n4.C0491c;
import n5.e;
import q4.f;
import q4.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateNewDialogV2 extends CreateNewDialog {
    private static final int MAX_SPAN_COUNT;
    private String[] mItems;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CreateNewCircleListViewHolder extends CreateNewListViewHolder {
        View mCircleLayout;

        public CreateNewCircleListViewHolder(View view) {
            super(view);
        }

        public void bind(int i2, int i7, int i8) {
            int i10;
            super.bind(i2, i7, i8);
            if (i8 == 0) {
                this.mIconView.setImageTintList((ColorStateList) null);
            }
            View view = this.mCircleLayout;
            if (CreateNewDialogV2.isBlurEnabled()) {
                i10 = R.drawable.bottom_menu_list_circle_bg;
            } else {
                i10 = R.drawable.bottom_menu_list_none_blur_circle_bg;
            }
            ViewUtils.setBackgroundResource(view, i10);
        }

        public void bindView(View view) {
            super.bindView(view);
            this.mCircleLayout = view.findViewById(R.id.create_new_circle_layout);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CreateNewListAdapter extends RecyclerView.Adapter<CreateNewListViewHolder> {
        private final String[] mListItems;
        private View.OnClickListener mOnClickListener;

        public CreateNewListAdapter(String[] strArr) {
            this.mListItems = strArr;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ Integer[] lambda$onBindViewHolder$0(int i2) {
            return new Integer[i2];
        }

        public int getItemCount() {
            return this.mListItems.length;
        }

        public CreateNewListAdapter setOnClickListener(View.OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
            return this;
        }

        public void onBindViewHolder(CreateNewListViewHolder createNewListViewHolder, int i2) {
            Integer[] numArr = (Integer[]) Arrays.stream(this.mListItems[i2].split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).map(new e(18)).toArray(new a(0));
            createNewListViewHolder.bind(numArr[0].intValue(), numArr[1].intValue(), numArr[3].intValue());
            createNewListViewHolder.itemView.setTag(Integer.valueOf(i2));
            createNewListViewHolder.itemView.setOnClickListener(this.mOnClickListener);
        }

        public CreateNewListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            boolean z = PreferenceFeatures.OneUi8x.IS_ONE_UI_85;
            View d = C0086a.d(viewGroup, z ? R.layout.recycler_item_create_new_dialog_circle_layout : R.layout.create_new_dialog_item_layout, viewGroup, false);
            return z ? new CreateNewCircleListViewHolder(d) : new CreateNewListViewHolder(d);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CreateNewListViewHolder extends RecyclerView.ViewHolder {
        ImageView mIconView;
        TextView mTitleView;

        public CreateNewListViewHolder(View view) {
            super(view);
            bindView(view);
        }

        public void bind(int i2, int i7, int i8) {
            this.mIconView.setImageResource(i2);
            this.mTitleView.setText(i7);
            View view = this.itemView;
            view.setContentDescription(view.getContext().getString(i7));
        }

        public void bindView(View view) {
            this.mIconView = (ImageView) view.findViewById(R.id.create_new_icon);
            this.mTitleView = (TextView) view.findViewById(R.id.create_new_title);
        }
    }

    static {
        int i2;
        if (!PreferenceFeatures.OneUi8x.IS_ONE_UI_85 || Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
            i2 = 3;
        } else {
            i2 = 4;
        }
        MAX_SPAN_COUNT = i2;
    }

    private void createVideoStudioButtonView(View view) {
        if (isVideoStudioButtonVisible()) {
            Optional.ofNullable(view.findViewById(R.id.video_studio_button)).ifPresent(new f(this, 0));
        }
    }

    /* access modifiers changed from: private */
    public static boolean isBlurEnabled() {
        if (Features.isEnabled(Features.IS_THEME_INSTALLED) || Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY) || !Features.isEnabled(Features.SUPPORT_REALTIME_BLUR)) {
            return false;
        }
        return true;
    }

    private boolean isVideoStudioButtonVisible() {
        Bundle arguments = getArguments();
        if (arguments == null || !SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(arguments.getString("video_studio_button"))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createVideoStudioButtonView$1(View view) {
        ((TextView) view.findViewById(R.id.video_studio_button_title)).setText(SeApiCompat.naturalizeText(getString(R.string.video_studio_title, getString(R.string.video_studio_app_name))));
        view.setOnClickListener(new g(this, 1));
        view.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateView$0(View view, RecyclerView recyclerView) {
        final int min = Math.min(this.mItems.length, MAX_SPAN_COUNT);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), min));
        recyclerView.setAdapter(new CreateNewListAdapter(this.mItems).setOnClickListener(new g(this, 0)));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                int i2 = childAdapterPosition % min;
                int dimensionPixelSize = view.getResources().getDimensionPixelSize(R.dimen.create_new_dialog_v2_item_gap);
                int i7 = min;
                rect.left = (i2 * dimensionPixelSize) / i7;
                rect.right = dimensionPixelSize - (((i2 + 1) * dimensionPixelSize) / i7);
                if (childAdapterPosition >= i7) {
                    rect.top = dimensionPixelSize;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onListItemClicked$2(Integer num) {
        Log.d(this.TAG, "onItemClicked", num);
        getBlackboard().post("data://user/dialog/CreateNew", num);
        dismiss();
    }

    /* access modifiers changed from: private */
    public void onListItemClicked(View view) {
        Optional.ofNullable((Integer) view.getTag()).ifPresent(new f(this, 1));
    }

    /* access modifiers changed from: private */
    public void onVideoStudioButtonClicked(View view) {
        Log.d(this.TAG, "onVideoStudioButtonClicked");
        getBlackboard().post("data://user/dialog/CreateNew", "video_studio_button");
        dismiss();
    }

    public View inflateView(Context context) {
        int i2;
        String[] items = getItems();
        this.mItems = items;
        if (items == null) {
            return null;
        }
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
            i2 = R.layout.create_new_dialog_circle_layout;
        } else {
            i2 = R.layout.create_new_dialog_layout;
        }
        View inflate = LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
        Optional.ofNullable((RecyclerView) inflate.findViewById(R.id.create_new_list_view)).ifPresent(new C0491c(11, this, inflate));
        createVideoStudioButtonView(inflate);
        return inflate;
    }
}
