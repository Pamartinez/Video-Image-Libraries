package q1;

import android.view.View;
import java.util.List;

/* renamed from: q1.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface C0265a {
    int getAlignContent();

    int getAlignItems();

    int getChildHeightMeasureSpec(int i2, int i7, int i8);

    int getChildWidthMeasureSpec(int i2, int i7, int i8);

    int getDecorationLengthCrossAxis(View view);

    int getDecorationLengthMainAxis(View view, int i2, int i7);

    int getFlexDirection();

    View getFlexItemAt(int i2);

    int getFlexItemCount();

    List getFlexLinesInternal();

    int getFlexWrap();

    int getLargestMainSize();

    int getMaxLine();

    int getPaddingBottom();

    int getPaddingEnd();

    int getPaddingLeft();

    int getPaddingRight();

    int getPaddingStart();

    int getPaddingTop();

    View getReorderedFlexItemAt(int i2);

    int getSumOfCrossSize();

    boolean isMainAxisDirectionHorizontal();

    void onNewFlexItemAdded(View view, int i2, int i7, c cVar);

    void onNewFlexLineAdded(c cVar);

    void setFlexLines(List list);

    void updateViewCache(int i2, View view);
}
