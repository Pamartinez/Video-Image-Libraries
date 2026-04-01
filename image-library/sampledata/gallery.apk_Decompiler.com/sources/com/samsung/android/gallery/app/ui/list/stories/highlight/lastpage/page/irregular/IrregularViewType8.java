package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular;

import android.graphics.RectF;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular.IrregularView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IrregularViewType8 extends IrregularView {
    private IrregularView.Position mMainShapePosition;
    private IrregularView.Position mSubLeftTopShapePosition;
    private IrregularView.Position mSubRightBottomShapePosition;
    private IrregularView.Position mSubRightMidShapePosition;
    private IrregularView.Position mSubRightTopShapePosition;
    private ImageView mSubShapeLeftTop;
    private ImageView mSubShapeRightBottom;
    private ImageView mSubShapeRightMid;
    private ImageView mSubShapeRightTop;

    public int getLayoutId() {
        return R.layout.irregular_collage_type_8;
    }

    public RectF getMainFaceInset() {
        return new RectF(0.15f, 0.15f, 0.15f, 0.15f);
    }

    public IrregularView.Position getMainShapePosition() {
        return this.mMainShapePosition;
    }

    public int getShapeCount() {
        return 5;
    }

    public IrregularView.Position[] getSubShapePosition() {
        return new IrregularView.Position[]{this.mSubLeftTopShapePosition, this.mSubRightTopShapePosition, this.mSubRightMidShapePosition, this.mSubRightBottomShapePosition};
    }

    public ImageView[] getSubShapes() {
        return new ImageView[]{this.mSubShapeLeftTop, this.mSubShapeRightTop, this.mSubShapeRightMid, this.mSubShapeRightBottom};
    }

    public IrregularView.ViewDirection[] getViewDirections() {
        IrregularView.ViewDirection viewDirection = IrregularView.ViewDirection.PORTRAIT;
        return new IrregularView.ViewDirection[]{viewDirection, viewDirection, viewDirection, viewDirection, viewDirection};
    }

    public void onViewCreated(ViewGroup viewGroup) {
        this.mSubShapeLeftTop = (ImageView) viewGroup.findViewById(R.id.sub_shape_left_top);
        this.mSubShapeRightTop = (ImageView) viewGroup.findViewById(R.id.sub_shape_right_top);
        this.mSubShapeRightMid = (ImageView) viewGroup.findViewById(R.id.sub_shape_right_mid);
        this.mSubShapeRightBottom = (ImageView) viewGroup.findViewById(R.id.sub_shape_right_bottom);
        this.mMainShapePosition = new IrregularView.Position(getFloatDimen(R.dimen.type8_main_x), getFloatDimen(R.dimen.type8_main_y));
        this.mSubLeftTopShapePosition = new IrregularView.Position(getFloatDimen(R.dimen.type8_sub_left_top_x), getFloatDimen(R.dimen.type8_sub_left_top_y));
        this.mSubRightTopShapePosition = new IrregularView.Position(getFloatDimen(R.dimen.type8_sub_right_top_x), getFloatDimen(R.dimen.type8_sub_right_top_y));
        this.mSubRightMidShapePosition = new IrregularView.Position(getFloatDimen(R.dimen.type8_sub_right_mid_x), getFloatDimen(R.dimen.type8_sub_right_mid_y));
        this.mSubRightBottomShapePosition = new IrregularView.Position(getFloatDimen(R.dimen.type8_sub_right_bottom_x), getFloatDimen(R.dimen.type8_sub_right_bottom_y));
    }

    public void restoreDrawable() {
        this.mSubShapeLeftTop.setImageResource(R.drawable.irregular_collage_shape_3_1);
        this.mSubShapeRightTop.setImageResource(R.drawable.irregular_collage_shape_3_2);
        this.mSubShapeRightMid.setImageResource(R.drawable.irregular_collage_shape_3_4);
        this.mSubShapeRightBottom.setImageResource(R.drawable.irregular_collage_shape_3_4);
    }

    public void setCollageItemsInternal(ArrayList<MediaItem> arrayList) {
        bindImage(this.mSubShapeLeftTop, arrayList.get(1), this.DEFAULT_INSET);
        bindImage(this.mSubShapeRightTop, arrayList.get(2), this.DEFAULT_INSET);
        bindImage(this.mSubShapeRightMid, arrayList.get(3), this.DEFAULT_INSET);
        bindImage(this.mSubShapeRightBottom, arrayList.get(4), this.DEFAULT_INSET);
    }
}
