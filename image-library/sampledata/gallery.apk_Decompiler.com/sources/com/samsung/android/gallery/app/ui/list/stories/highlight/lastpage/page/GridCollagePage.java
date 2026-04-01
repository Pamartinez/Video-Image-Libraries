package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import C4.i;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageInfo;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageSaveHelper;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageType;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.PageSpec;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.smartrect.CoverRect;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GridCollagePage extends CollagePage implements IGridCollagePage {
    private final RecyclerView.OnChildAttachStateChangeListener mChildAttached = new RecyclerView.OnChildAttachStateChangeListener() {
        public void onChildViewAttachedToWindow(View view) {
            if (GridCollagePage.this.mEntryEffectHelper.effectRequired() && (view.getParent() instanceof ViewGroup)) {
                int indexOfChild = ((ViewGroup) view.getParent()).indexOfChild(view);
                GridCollagePage gridCollagePage = GridCollagePage.this;
                gridCollagePage.mEntryEffectHelper.addCollageItemEffect(view, indexOfChild, gridCollagePage.mCollageInfo.getFileCount());
            }
        }

        public void onChildViewDetachedFromWindow(View view) {
        }
    };
    private CollageView mCollageView;

    public GridCollagePage(View view, IStoryHighlightView iStoryHighlightView, View view2) {
        super(view, iStoryHighlightView, view2);
    }

    private String getCornerRadius(Context context, CollageType collageType, int i2, int i7) {
        int[] cornerRadius = collageType.getCornerRadius(context.getResources(), i2, i7);
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        stringJoiner.add(String.valueOf(cornerRadius[0]));
        stringJoiner.add(String.valueOf(cornerRadius[1]));
        stringJoiner.add(String.valueOf(cornerRadius[2]));
        stringJoiner.add(String.valueOf(cornerRadius[3]));
        return stringJoiner.toString();
    }

    private String getDisplayPositionRect(View view, RecyclerView recyclerView, int i2) {
        View view2;
        RecyclerView.ViewHolder findViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(i2);
        if (findViewHolderForAdapterPosition != null) {
            view2 = findViewHolderForAdapterPosition.itemView;
        } else {
            view2 = null;
        }
        return getDisplayPositionRect(view, view2);
    }

    /* access modifiers changed from: private */
    /* renamed from: onSaveLayoutBound */
    public void lambda$handleSaveInternal$0(GridCollageLayoutBinder<IGridCollagePage> gridCollageLayoutBinder, View view, int i2) {
        MediaItem mediaItem;
        if (i2 >= 0) {
            mediaItem = this.mCollageView.getMediaItem(i2);
        } else {
            mediaItem = null;
        }
        MediaItem mediaItem2 = mediaItem;
        if (mediaItem2 != null) {
            View view2 = view;
            CollageSaveHelper.saveGridVideoCollage(this.mView.getEventContext(), view2, getSaveResolution(false), mediaItem2, getDisplayPositionRect(view, gridCollageLayoutBinder.getListView(), i2), getCornerRadius(view.getContext(), this.mType, view.getWidth(), i2));
            return;
        }
        CollageSaveHelper.saveImageCollage(this.mView, view, getSaveResolution(true));
    }

    private void updateCollageListLayout(PageSpec.Config config, int i2) {
        updateViewSize(this.mCollageView.getListView(), config, i2);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mCollageView = new CollageView(view);
    }

    public CollageInfo getCollageInfo() {
        return this.mCollageInfo;
    }

    public MediaItem[] getCollageItems() {
        MediaItem[] collageItems = super.getCollageItems();
        if (CollageType.COLLAGE5.equals(this.mCollageInfo.getType()) && collageItems.length >= 6) {
            MediaItem mediaItem = collageItems[5];
            collageItems[5] = collageItems[4];
            collageItems[4] = mediaItem;
        }
        return collageItems;
    }

    public ArrayList<String> getSmartCropRectList(MediaItem[] mediaItemArr) {
        boolean z;
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < mediaItemArr.length; i2++) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) this.mCollageView.getListView().findViewHolderForAdapterPosition(i2);
            if (imageViewHolder == null) {
                arrayList.add("");
            } else {
                if (imageViewHolder.getImage().getWidth() >= imageViewHolder.getImage().getHeight()) {
                    z = true;
                } else {
                    z = false;
                }
                arrayList.add(rectToString(CoverRect.getSmartCropForCover((FileItemInterface) mediaItemArr[i2], z)));
            }
        }
        return arrayList;
    }

    public void handleSaveInternal() {
        int videoIndex = this.mCollageView.getVideoIndex();
        GridCollageLayoutBinder gridCollageLayoutBinder = new GridCollageLayoutBinder(this);
        gridCollageLayoutBinder.setPreparedListener(new i((Object) this, (Object) gridCollageLayoutBinder, videoIndex, 9));
    }

    public void initLayout(PageSpec.Config config) {
        super.initLayout(config);
        this.mCollageView.initListView(this.mRootParent, config.baseSize);
        this.mCollageView.initData(this.mType, this.mCollageInfo.getItems());
        this.mCollageView.getListView().addOnChildAttachStateChangeListener(this.mChildAttached);
    }

    public void recycle() {
        super.recycle();
        this.mCollageView.getListView().removeOnChildAttachStateChangeListener(this.mChildAttached);
    }

    public void startPreviewInternal() {
        this.mCollageView.startPreviewInternal();
    }

    public void stopPreviewInternal() {
        this.mCollageView.stopPreviewInternal();
    }

    public void updateLayout(PageSpec.Config config, int i2) {
        super.updateLayout(config, i2);
        updateCollageListLayout(config, i2);
    }
}
