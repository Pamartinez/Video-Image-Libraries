package com.samsung.android.gallery.app.ui.list.search.recommendation.floating.suggestion;

import Ba.g;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.search.recommendation.abstraction.RecommendationViewListener;
import com.samsung.android.gallery.module.search.recommendation.IRecommendationItem;
import com.samsung.android.gallery.module.search.root.IntelligentSearchIndexFieldIcon;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.UtlTextView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Collection;
import java.util.LinkedList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingSuggestionListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final LinkedList<IRecommendationItem> mData = new LinkedList<>();
    private final RecommendationViewListener mViewListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mIconView;
        private final UtlTextView mTitleView;

        public ViewHolder(View view) {
            super(view);
            ImageView imageView = (ImageView) view.findViewById(R.id.floating_suggestion_icon);
            this.mIconView = imageView;
            this.mTitleView = (UtlTextView) view.findViewById(R.id.floating_suggestion_text);
            if (PreferenceFeatures.OneUi7x.SUPPORT_QS_UNIFIED_ITEM) {
                ViewUtils.setVisibleOrGone(imageView, false);
            }
        }

        public void setIcon(Drawable drawable) {
            if (PreferenceFeatures.OneUi7x.SUPPORT_QS_UNIFIED_ITEM) {
                ViewUtils.setVisibleOrGone(this.mIconView, false);
            } else {
                this.mIconView.setImageDrawable(drawable);
            }
        }

        public void setTitle(String str) {
            this.mTitleView.setText(str);
        }
    }

    public FloatingSuggestionListAdapter(RecommendationViewListener recommendationViewListener) {
        this.mViewListener = recommendationViewListener;
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
        synchronized (this.mData) {
            try {
                int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
                if (bindingAdapterPosition != -1) {
                    this.mViewListener.onSuggestionKeywordClick(this.mData.get(bindingAdapterPosition), bindingAdapterPosition);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void setIcon(ViewHolder viewHolder, IRecommendationItem iRecommendationItem) {
        if (!PreferenceFeatures.OneUi7x.SUPPORT_QS_UNIFIED_ITEM) {
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
    }

    public int getItemCount() {
        int min;
        synchronized (this.mData) {
            min = Math.min(this.mData.size(), 3);
        }
        return min;
    }

    public boolean isEmpty() {
        boolean isEmpty;
        synchronized (this.mData) {
            isEmpty = this.mData.isEmpty();
        }
        return isEmpty;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        ViewHolder viewHolder = new ViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_search_floating_suggestion, viewGroup, false));
        viewHolder.itemView.setOnClickListener(new g(4, this, viewHolder));
        return viewHolder;
    }

    public void setData(Collection<? extends IRecommendationItem> collection) {
        synchronized (this.mData) {
            try {
                this.mData.clear();
                if (collection != null) {
                    this.mData.addAll(collection);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        Log.d("FloatingSuggestionListAdapter", "setSuggestionItem size " + this.mData.size());
        notifyDataSetChanged();
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i2) {
        synchronized (this.mData) {
            IRecommendationItem iRecommendationItem = this.mData.get(i2);
            setIcon(viewHolder, iRecommendationItem);
            viewHolder.setTitle(iRecommendationItem.getButtonName());
        }
    }
}
