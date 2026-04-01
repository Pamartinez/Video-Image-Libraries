package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular;

import android.graphics.RectF;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular.IrregularView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IrregularViewType7 extends IrregularView {
    private IrregularView.Position mMainShapePosition;
    private IrregularView.Position mSubLeftShapePosition;
    private IrregularView.Position mSubRightShapePosition;
    private ImageView mSubShapeLeft;
    private ImageView mSubShapeRight;

    public int getLayoutId() {
        return R.layout.irregular_collage_type_7;
    }

    public RectF getMainFaceInset() {
        return this.DEFAULT_INSET;
    }

    public IrregularView.Position getMainShapePosition() {
        return this.mMainShapePosition;
    }

    public int getShapeCount() {
        return 3;
    }

    public IrregularView.Position[] getSubShapePosition() {
        return new IrregularView.Position[]{this.mSubLeftShapePosition, this.mSubRightShapePosition};
    }

    public ImageView[] getSubShapes() {
        return new ImageView[]{this.mSubShapeLeft, this.mSubShapeRight};
    }

    public IrregularView.ViewDirection[] getViewDirections() {
        IrregularView.ViewDirection viewDirection = IrregularView.ViewDirection.LANDSCAPE;
        IrregularView.ViewDirection viewDirection2 = IrregularView.ViewDirection.PORTRAIT;
        return new IrregularView.ViewDirection[]{viewDirection, viewDirection2, viewDirection2};
    }

    public void onViewCreated(ViewGroup viewGroup) {
        this.mSubShapeLeft = (ImageView) viewGroup.findViewById(R.id.sub_shape_left);
        this.mSubShapeRight = (ImageView) viewGroup.findViewById(R.id.sub_shape_right);
        this.mMainShapePosition = new IrregularView.Position(getFloatDimen(R.dimen.type7_main_x), getFloatDimen(R.dimen.type7_main_y));
        this.mSubLeftShapePosition = new IrregularView.Position(getFloatDimen(R.dimen.type7_sub_left_x), getFloatDimen(R.dimen.type7_sub_left_y));
        this.mSubRightShapePosition = new IrregularView.Position(getFloatDimen(R.dimen.type7_sub_right_x), getFloatDimen(R.dimen.type7_sub_right_y));
    }

    public void restoreDrawable() {
        this.mSubShapeLeft.setImageResource(R.drawable.irregular_collage_shape_2_2);
        this.mSubShapeRight.setImageResource(R.drawable.irregular_collage_shape_2_2);
    }

    public void setCollageItemsInternal(ArrayList<MediaItem> arrayList) {
        bindImage(this.mSubShapeLeft, arrayList.get(1), this.DEFAULT_INSET);
        bindImage(this.mSubShapeRight, arrayList.get(2), this.DEFAULT_INSET);
    }
}
