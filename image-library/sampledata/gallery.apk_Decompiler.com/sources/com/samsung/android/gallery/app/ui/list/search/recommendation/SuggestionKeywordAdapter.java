package com.samsung.android.gallery.app.ui.list.search.recommendation;

import B2.i;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.search.recommendation.abstraction.RecommendationViewListener;
import com.samsung.android.gallery.module.search.recommendation.IRecommendationItem;
import com.samsung.android.gallery.module.search.root.IntelligentSearchIndexFieldIcon;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SuggestionKeywordAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Function<String, Boolean> mInputBlock;
    private final LinkedList<IRecommendationItem> mList = new LinkedList<>();
    private final RecommendationViewListener mViewListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mIconView;
        private final TextView mTitleView;

        public ViewHolder(View view) {
            super(view);
            this.mIconView = (ImageView) view.findViewById(R.id.recommendation_icon);
            this.mTitleView = (TextView) view.findViewById(R.id.recommendation_text);
        }

        public void setIcon(Drawable drawable) {
            this.mIconView.setImageDrawable(drawable);
        }

        public void setTitle(String str) {
            this.mTitleView.setText(str);
        }

        public void updateMargin() {
            int dimensionPixelOffset = this.itemView.getResources().getDimensionPixelOffset(R.dimen.search_recommend_item_text_margin_start);
            int dimensionPixelOffset2 = this.itemView.getResources().getDimensionPixelOffset(R.dimen.search_recommend_item_text_margin_end);
            if (ResourceCompat.isSmallScreenSize(this.itemView.getResources())) {
                dimensionPixelOffset /= 2;
                dimensionPixelOffset2 /= 2;
            }
            ViewMarginUtils.setHorizontalRelativeMargin(this.mIconView, dimensionPixelOffset, dimensionPixelOffset2);
        }
    }

    public SuggestionKeywordAdapter(RecommendationViewListener recommendationViewListener, Function<String, Boolean> function) {
        this.mViewListener = recommendationViewListener;
        this.mInputBlock = function;
    }

    private int getIconResId(IRecommendationItem.IconType iconType) {
        if (iconType == IRecommendationItem.IconType.TIME || iconType == IRecommendationItem.IconType.RECENT) {
            return R.drawable.gallery_ic_search_suggested_recent;
        }
        if (iconType == IRecommendationItem.IconType.LOCATION) {
            return R.drawable.gallery_ic_search_suggested_location;
        }
        if (iconType == IRecommendationItem.IconType.VIDEOS) {
            return R.drawable.gallery_ic_search_suggested_videos;
        }
        if (iconType == IRecommendationItem.IconType.SMILES) {
            return R.drawable.gallery_ic_search_suggested_expression;
        }
        if (iconType == IRecommendationItem.IconType.CATEGORY || iconType == IRecommendationItem.IconType.BLURRY) {
            return R.drawable.gallery_ic_search_suggested_category;
        }
        return R.drawable.gallery_ic_search_suggested_documents;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateViewHolder$0(ViewHolder viewHolder, View view) {
        synchronized (this.mList) {
            try {
                int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
                if (this.mInputBlock.apply("SuggestionKeywordAdapter_createViewHolderInternal").booleanValue() && bindingAdapterPosition != -1) {
                    this.mViewListener.onSuggestionKeywordClick(this.mList.get(bindingAdapterPosition), bindingAdapterPosition);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void setIcon(ViewHolder viewHolder, IRecommendationItem iRecommendationItem) {
        IntelligentSearchIndexFieldIcon fieldIcon = iRecommendationItem.getFieldIcon();
        Context context = viewHolder.itemView.getContext();
        if (context != null) {
            if (fieldIcon == null || fieldIcon.getDrawableResId() == null) {
                Drawable drawable = context.getDrawable(getIconResId(iRecommendationItem.getIconType()));
                if (drawable != null) {
                    drawable.setTint(context.getColor(R.color.search_recommend_icon_color));
                    viewHolder.setIcon(drawable);
                    return;
                }
                return;
            }
            Drawable drawable2 = context.getDrawable(fieldIcon.getDrawableResId().intValue());
            if (drawable2 != null) {
                if (fieldIcon.getTintColorResId() != null) {
                    drawable2.setTint(context.getColor(fieldIcon.getTintColorResId().intValue()));
                } else {
                    drawable2.setTint(context.getColor(R.color.search_recommend_icon_color));
                }
                viewHolder.setIcon(drawable2);
            }
        }
    }

    public int getItemCount() {
        int min;
        synchronized (this.mList) {
            min = Math.min(this.mList.size(), 3);
        }
        return min;
    }

    public boolean isEmpty() {
        boolean isEmpty;
        synchronized (this.mList) {
            isEmpty = this.mList.isEmpty();
        }
        return isEmpty;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        View d = C0086a.d(viewGroup, R.layout.recycler_item_recommendation_suggestion_keyword_layout, viewGroup, false);
        RecommendationViewListener recommendationViewListener = this.mViewListener;
        Objects.requireNonNull(recommendationViewListener);
        d.setOnTouchListener(new i(3, recommendationViewListener));
        ViewHolder viewHolder = new ViewHolder(d);
        viewHolder.itemView.setOnClickListener(new b(this, viewHolder, 1));
        return viewHolder;
    }

    public void setData(Collection<? extends IRecommendationItem> collection) {
        synchronized (this.mList) {
            try {
                this.mList.clear();
                if (collection != null) {
                    this.mList.addAll(collection);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        notifyDataSetChanged();
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i2, List<Object> list) {
        if (list.contains("updateSideMargin")) {
            viewHolder.updateMargin();
        }
        super.onBindViewHolder(viewHolder, i2, list);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i2) {
        synchronized (this.mList) {
            IRecommendationItem iRecommendationItem = this.mList.get(i2);
            setIcon(viewHolder, iRecommendationItem);
            viewHolder.setTitle(iRecommendationItem.getButtonName());
        }
    }
}
