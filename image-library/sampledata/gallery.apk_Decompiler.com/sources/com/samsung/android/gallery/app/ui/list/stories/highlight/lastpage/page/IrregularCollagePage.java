package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import Ad.C0720a;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageSaveHelper;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageType;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular.IrregularCollageViewFactory;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular.IrregularView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.PageSpec;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.motionphoto.utils.ex.b;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.stream.Collectors;
import n4.C0491c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IrregularCollagePage extends CollagePage implements IIrregularCollagePage {
    protected IrregularView mCollageView;
    private final IrregularGradientHelper mHelper = new IrregularGradientHelper(false);
    private PageItem mItem;

    public IrregularCollagePage(View view, IStoryHighlightView iStoryHighlightView, View view2) {
        super(view, iStoryHighlightView, view2);
    }

    private int getShapeCount() {
        IrregularView irregularView = this.mCollageView;
        if (irregularView != null) {
            return irregularView.getShapeCount();
        }
        return 0;
    }

    private void initCollageView(boolean z) {
        if (z) {
            this.mCollageView = createIrregularView(this.mCollageContainer);
        }
        initData(this.mCollageView);
    }

    /* access modifiers changed from: private */
    /* renamed from: onViewPrepared */
    public void lambda$handleSaveInternal$0(IrregularCollageLayoutBinder<IIrregularCollagePage> irregularCollageLayoutBinder, View view) {
        if (this.mCollageView.hasVideo()) {
            saveVideoCollage(irregularCollageLayoutBinder, view);
        } else {
            saveImageCollage(view);
        }
    }

    private void saveImageCollage(View view) {
        CollageSaveHelper.saveImageCollage(this.mView, view, getSaveResolution(true));
    }

    private void saveVideoCollage(IrregularCollageLayoutBinder<IIrregularCollagePage> irregularCollageLayoutBinder, View view) {
        ListViewHolder mainViewHolder = irregularCollageLayoutBinder.getMainViewHolder();
        if (mainViewHolder == null) {
            saveImageCollage(view);
        } else {
            saveVideoCollage(irregularCollageLayoutBinder, view, mainViewHolder);
        }
    }

    public void bind(PageItem pageItem) {
        boolean z;
        this.mItem = pageItem;
        CollageType collageType = this.mType;
        super.bind(pageItem);
        if (collageType != this.mType) {
            z = true;
        } else {
            z = false;
        }
        initCollageView(z);
        this.mHelper.setGradientBackground(this.mContentParent, this.mCollageView, pageItem, MediaItemStory.getStoryId(this.mView.getHeaderItem()));
    }

    public IrregularView createIrregularView(ViewGroup viewGroup) {
        IrregularView create = IrregularCollageViewFactory.create(this.mCollageInfo.getType());
        viewGroup.removeAllViews();
        create.createView(viewGroup);
        return create;
    }

    public MediaItem[] getCollageItems() {
        return (MediaItem[]) this.mCollageInfo.getItems().subList(0, getShapeCount()).toArray(new MediaItem[0]);
    }

    public Object getGradientInfo() {
        return new Object[]{Integer.valueOf(this.mHelper.getGradientDirection()), this.mHelper.getGradientColors()};
    }

    public ArrayList<String> getSmartCropRectList(MediaItem[] mediaItemArr) {
        IrregularView irregularView = this.mCollageView;
        if (irregularView != null) {
            return (ArrayList) irregularView.getCropRectFList(mediaItemArr).stream().map(new b(28, this)).collect(Collectors.toCollection(new C0720a(1)));
        }
        return null;
    }

    public void handleSaveInternal() {
        IrregularCollageLayoutBinder irregularCollageLayoutBinder = new IrregularCollageLayoutBinder(this);
        irregularCollageLayoutBinder.updateGradientView((ImageView) this.mContentParent.findViewById(R.id.gradient_view), this.mItem, MediaItemStory.getStoryId(this.mView.getHeaderItem()));
        irregularCollageLayoutBinder.setPreparedListener(new C0491c(14, this, irregularCollageLayoutBinder));
    }

    public void initData(IrregularView irregularView) {
        irregularView.setCollageItems(this.mCollageInfo.getItems());
    }

    public void initLayout(PageSpec.Config config) {
        super.initLayout(config);
        ArrayList<View> itemViews = this.mCollageView.getItemViews();
        for (int i2 = 0; i2 < itemViews.size(); i2++) {
            this.mEntryEffectHelper.addCollageItemEffect(itemViews.get(i2), i2, itemViews.size());
        }
    }

    public void recycle() {
        super.recycle();
        IrregularView irregularView = this.mCollageView;
        if (irregularView != null) {
            irregularView.recycle();
        }
    }

    public void setContentParentTopPadding(View view, int i2) {
        super.setContentParentTopPadding(view, i2);
        this.mHelper.updateFgViewTopPadding(view, i2);
    }

    public void startPreviewInternal() {
        IrregularView irregularView = this.mCollageView;
        if (irregularView != null) {
            irregularView.startPreview();
        }
    }

    public void stopPreviewInternal() {
        IrregularView irregularView = this.mCollageView;
        if (irregularView != null) {
            irregularView.stopPreview();
        }
    }

    public void updateCollageSize(IrregularView irregularView, PageSpec.Config config) {
        if (irregularView != null) {
            int i2 = config.width;
            irregularView.updateSize(i2, (int) (((float) i2) * 1.329f));
        }
    }

    public void updateLayout(PageSpec.Config config, int i2) {
        super.updateLayout(config, i2);
        updateCollageSize(this.mCollageView, config);
    }

    private void saveVideoCollage(IrregularCollageLayoutBinder<IIrregularCollagePage> irregularCollageLayoutBinder, View view, ListViewHolder listViewHolder) {
        CollageSaveHelper.saveIrregularVideoCollage(this.mView.getEventContext(), irregularCollageLayoutBinder.getBgForVideo(), irregularCollageLayoutBinder.getFgForVideo(), getSaveResolution(false), listViewHolder.getMediaItem(), getDisplayPositionRect(view, listViewHolder.itemView));
    }
}
