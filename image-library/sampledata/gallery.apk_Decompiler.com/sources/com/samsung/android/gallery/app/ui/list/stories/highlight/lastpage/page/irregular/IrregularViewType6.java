package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular;

import android.graphics.RectF;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular.IrregularView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IrregularViewType6 extends IrregularView {
    private IrregularView.Position mMainShapePosition;
    private IrregularView.Position mSubLeftTopShapePosition;
    private IrregularView.Position mSubRightBottomShapePosition;
    private IrregularView.Position mSubRightTopShapePosition;
    private ImageView mSubShapeLeftTop;
    private ImageView mSubShapeRightBottom;
    private ImageView mSubShapeRightTop;

    public int getLayoutId() {
        return R.layout.irregular_collage_type_6;
    }

    public RectF getMainFaceInset() {
        return new RectF(0.2f, 0.2f, 0.2f, 0.2f);
    }

    public IrregularView.Position getMainShapePosition() {
        return this.mMainShapePosition;
    }

    public int getShapeCount() {
        return 4;
    }

    public IrregularView.Position[] getSubShapePosition() {
        return new IrregularView.Position[]{this.mSubLeftTopShapePosition, this.mSubRightTopShapePosition, this.mSubRightBottomShapePosition};
    }

    public ImageView[] getSubShapes() {
        return new ImageView[]{this.mSubShapeLeftTop, this.mSubShapeRightTop, this.mSubShapeRightBottom};
    }

    public IrregularView.ViewDirection[] getViewDirections() {
        IrregularView.ViewDirection viewDirection = IrregularView.ViewDirection.PORTRAIT;
        return new IrregularView.ViewDirection[]{viewDirection, viewDirection, viewDirection, viewDirection};
    }

    public void onViewCreated(ViewGroup viewGroup) {
        this.mSubShapeLeftTop = (ImageView) viewGroup.findViewById(R.id.sub_shape_left_top);
        this.mSubShapeRightTop = (ImageView) viewGroup.findViewById(R.id.sub_shape_right_top);
        this.mSubShapeRightBottom = (ImageView) viewGroup.findViewById(R.id.sub_shape_right_bottom);
        this.mMainShapePosition = new IrregularView.Position(getFloatDimen(R.dimen.type6_main_x), getFloatDimen(R.dimen.type6_main_y));
        this.mSubLeftTopShapePosition = new IrregularView.Position(getFloatDimen(R.dimen.type6_sub_left_top_x), getFloatDimen(R.dimen.type6_sub_left_top_y));
        this.mSubRightTopShapePosition = new IrregularView.Position(getFloatDimen(R.dimen.type6_sub_right_top_x), getFloatDimen(R.dimen.type6_sub_right_top_y));
        this.mSubRightBottomShapePosition = new IrregularView.Position(getFloatDimen(R.dimen.type6_sub_right_bottom_x), getFloatDimen(R.dimen.type6_sub_right_bottom_y));
    }

    public void restoreDrawable() {
        this.mSubShapeLeftTop.setImageResource(R.drawable.irregular_collage_shape_1_1);
        this.mSubShapeRightTop.setImageResource(R.drawable.irregular_collage_shape_1_2);
        this.mSubShapeRightBottom.setImageResource(R.drawable.irregular_collage_shape_1_4);
    }

    public void setCollageItemsInternal(ArrayList<MediaItem> arrayList) {
        bindImage(this.mSubShapeLeftTop, arrayList.get(1), new RectF(0.15f, 0.15f, 0.15f, 0.2f));
        bindImage(this.mSubShapeRightTop, arrayList.get(2), new RectF(0.2f, 0.2f, 0.2f, 0.2f));
        bindImage(this.mSubShapeRightBottom, arrayList.get(3), new RectF(0.2f, 0.2f, 0.15f, 0.2f));
    }
}
