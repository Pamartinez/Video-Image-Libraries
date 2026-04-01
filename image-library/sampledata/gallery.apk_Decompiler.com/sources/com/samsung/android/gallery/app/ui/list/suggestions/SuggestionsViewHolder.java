package com.samsung.android.gallery.app.ui.list.suggestions;

import Ba.g;
import C0.f;
import D0.e;
import H7.A;
import M9.o;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.samsung.android.gallery.database.dbtype.SuggestedType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.suggested.SuggestedHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Objects;
import x0.C0319A;
import x0.I;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestionsViewHolder extends ListViewHolder {
    TextView mDescription;
    View mDivider;
    LinearLayout mDividerLayout;
    private GridLayoutManager mGridLayoutManager;
    LinearLayout mGroupHeader;
    LottieAnimationView mGroupIcon;
    TextView mGroupText;
    RelativeLayout mHeaderLayout;
    private SuggestionsItemAdapter mItemAdapter;
    RecyclerView mListView;
    ImageView mNewBadge;
    TextView mTitle;

    public SuggestionsViewHolder(View view, int i2) {
        super(view, i2);
    }

    private void bindGroupHeader(MediaItem mediaItem) {
        if (MediaItemSuggest.isCleanOutGroup(mediaItem)) {
            this.mGroupIcon.setImageResource(R.drawable.ic_suggestions_clean_gallery);
            this.mGroupIcon.setAnimation("lottie_ico_gallery_suggestions_clean_out.json");
            this.mGroupText.setText(R.string.clean_out);
        } else {
            this.mGroupIcon.setImageResource(R.drawable.ic_suggestions_intelligence);
            this.mGroupIcon.setAnimation("lottie_ico_gallery_suggestions_fix_up.json");
            this.mGroupText.setText(R.string.fix_up);
        }
        this.mGroupIcon.setProgress(0.0f);
        setTintLottieAnimationView(this.mGroupIcon, R.color.search_recommend_icon_color);
    }

    private void bindListView(MediaItem mediaItem) {
        Context context = this.itemView.getContext();
        int i2 = context.getResources().getIntArray(R.array.oneui30_suggestion_column_count)[0];
        if (this.mItemAdapter == null) {
            SuggestionsItemAdapter suggestionsItemAdapter = new SuggestionsItemAdapter(context);
            this.mItemAdapter = suggestionsItemAdapter;
            this.mListView.setAdapter(suggestionsItemAdapter);
            this.mGridLayoutManager = new GridLayoutManager(context, i2);
            this.mItemAdapter.setOnClickListener(new g(6, this, mediaItem));
        }
        this.mItemAdapter.setData(mediaItem, i2);
        this.mGridLayoutManager.setSpanCount(i2);
        this.mListView.setLayoutManager(this.mGridLayoutManager);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindListView$1(MediaItem mediaItem, View view) {
        onItemClickInternal(MediaItemSuggest.getType(mediaItem));
        postLog(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        onClickHeader();
    }

    private void postLog(boolean z) {
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_SUGGEST_VIEW.toString(), SuggestedType.getEventId(MediaItemSuggest.getType(this.mMediaItem), MediaItemSuggest.getCleanOutDeleteType(this.mMediaItem), z));
    }

    private void setText(MediaItem mediaItem) {
        String[] typeDescription = SuggestedType.getTypeDescription(MediaItemSuggest.getType(mediaItem), MediaItemSuggest.getCleanOutDeleteType(mediaItem));
        this.mTitle.setText(typeDescription[0]);
        this.mDescription.setText(typeDescription[1]);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        if (PreferenceFeatures.OneUi41.SHOW_SUGGESTION_HEADER) {
            bindGroupHeader(mediaItem);
        }
        setText(mediaItem);
        updateNewBadgeVisibility(mediaItem);
        bindListView(mediaItem);
    }

    public final void bindView(View view) {
        this.mHeaderLayout = (RelativeLayout) view.findViewById(R.id.header_layout);
        this.mTitle = (TextView) view.findViewById(R.id.suggestions_header_title);
        this.mDescription = (TextView) view.findViewById(R.id.suggestions_header_description);
        this.mNewBadge = (ImageView) view.findViewById(R.id.suggestions_header_new_badge);
        this.mListView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        this.mGroupHeader = (LinearLayout) view.findViewById(R.id.suggestions_group_header);
        this.mGroupIcon = (LottieAnimationView) view.findViewById(R.id.suggestions_group_icon);
        this.mGroupText = (TextView) view.findViewById(R.id.suggestions_group_text);
        this.mDividerLayout = (LinearLayout) view.findViewById(R.id.suggestions_card_divider_layout);
        this.mDivider = view.findViewById(R.id.suggestions_card_divider);
        this.mHeaderLayout.setOnClickListener(new A(13, this));
        LottieAnimationView lottieAnimationView = this.mGroupIcon;
        Objects.requireNonNull(lottieAnimationView);
        ViewUtils.postDelayed(lottieAnimationView, new o(5, lottieAnimationView), 10);
    }

    public View getDecoView(int i2) {
        if (i2 == 70) {
            return this.mDividerLayout;
        }
        return super.getDecoView(i2);
    }

    public int getNewBadgeVisibility() {
        return this.mNewBadge.getVisibility();
    }

    public void hideNewBadge() {
        ViewUtils.setVisibility(this.mNewBadge, 8);
    }

    public void onClickHeader() {
        onItemClickInternal(MediaItemSuggest.getType(this.mMediaItem));
        postLog(true);
    }

    public void onConfigurationChanged() {
        SuggestionsItemAdapter suggestionsItemAdapter = this.mItemAdapter;
        if (suggestionsItemAdapter != null) {
            suggestionsItemAdapter.notifyDataSetChanged();
        }
    }

    public void recycle() {
        super.recycle();
        this.mItemAdapter = null;
        RecyclerView recyclerView = this.mListView;
        if (recyclerView != null) {
            recyclerView.setAdapter((RecyclerView.Adapter) null);
        }
        this.mGridLayoutManager = null;
    }

    public void setDividerVisibility(boolean z) {
        int i2;
        LinearLayout linearLayout = this.mDividerLayout;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(linearLayout, i2);
    }

    public void setGroupHeaderVisibility(boolean z) {
        int i2;
        LinearLayout linearLayout = this.mGroupHeader;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(linearLayout, i2);
    }

    public void setTintLottieAnimationView(LottieAnimationView lottieAnimationView, int i2) {
        I i7 = new I(ContextCompat.getColor(lottieAnimationView.getContext(), i2));
        f fVar = new f("**");
        e eVar = new e(i7);
        lottieAnimationView.f1099h.a(fVar, C0319A.f2028F, eVar);
    }

    public void updateBottomMargin(boolean z) {
        int i2;
        if (z) {
            i2 = this.itemView.getResources().getDimensionPixelOffset(R.dimen.suggestions_card_bottom_margin);
        } else {
            i2 = 0;
        }
        ViewMarginUtils.setBottomMargin(this.itemView, i2);
    }

    public void updateNewBadgeVisibility(MediaItem mediaItem) {
        boolean z;
        SuggestedType type = SuggestedType.getType(MediaItemSuggest.getType(mediaItem));
        if (type != null) {
            if (SuggestedType.CLEANOUT_MOTION_PHOTO_CLIP.equals(type)) {
                int i2 = PreferenceCache.CleanOutMotionPhotoClipCount.getInt();
                z = false;
                int count = mediaItem.getCount();
                if (i2 != -1 ? count - i2 >= 10 : count >= 5) {
                    z = true;
                }
            } else {
                z = SuggestedHelper.comparePreference(type, MediaItemSuggest.getCleanOutDeleteType(mediaItem));
            }
            ViewUtils.setVisibleOrGone(this.mNewBadge, z);
        }
    }

    public void updateTopPadding(boolean z) {
        int i2;
        if (z) {
            i2 = this.itemView.getResources().getDimensionPixelOffset(R.dimen.suggestions_card_top_padding);
        } else {
            i2 = 0;
        }
        ViewMarginUtils.setTopPadding(this.itemView, i2);
    }
}
