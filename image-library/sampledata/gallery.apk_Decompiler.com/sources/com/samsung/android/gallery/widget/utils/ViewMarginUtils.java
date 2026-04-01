package com.samsung.android.gallery.widget.utils;

import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewMarginUtils {
    public static int getBottomMargin(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view == null || (marginLayoutParams = getMarginLayoutParams(view)) == null) {
            return 0;
        }
        return marginLayoutParams.bottomMargin;
    }

    public static int getEndMargin(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view == null || (marginLayoutParams = getMarginLayoutParams(view)) == null) {
            return 0;
        }
        return marginLayoutParams.getMarginEnd();
    }

    public static int getHorizontalPadding(View view) {
        if (view == null) {
            return 0;
        }
        return view.getPaddingEnd() + view.getPaddingStart();
    }

    public static int getLeftMargin(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view == null || (marginLayoutParams = getMarginLayoutParams(view)) == null) {
            return 0;
        }
        return marginLayoutParams.leftMargin;
    }

    private static ViewGroup.MarginLayoutParams getMarginLayoutParams(View view) {
        ViewGroup.LayoutParams layoutParams;
        if (view != null) {
            layoutParams = view.getLayoutParams();
        } else {
            layoutParams = null;
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return (ViewGroup.MarginLayoutParams) layoutParams;
        }
        return null;
    }

    public static int getRightMargin(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view == null || (marginLayoutParams = getMarginLayoutParams(view)) == null) {
            return 0;
        }
        return marginLayoutParams.rightMargin;
    }

    public static int getStartMargin(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view == null || (marginLayoutParams = getMarginLayoutParams(view)) == null) {
            return 0;
        }
        return marginLayoutParams.getMarginStart();
    }

    public static int getTopMargin(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view == null || (marginLayoutParams = getMarginLayoutParams(view)) == null) {
            return 0;
        }
        return marginLayoutParams.topMargin;
    }

    public static int getVerticalPadding(View view) {
        if (view == null) {
            return 0;
        }
        return view.getPaddingBottom() + view.getPaddingTop();
    }

    public static void setBottomMargin(View view, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view != null && (marginLayoutParams = getMarginLayoutParams(view)) != null && marginLayoutParams.bottomMargin != i2) {
            marginLayoutParams.bottomMargin = i2;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void setBottomPadding(View view, int i2) {
        if (view != null) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), i2);
        }
    }

    public static void setEndMargin(View view, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view != null && (marginLayoutParams = getMarginLayoutParams(view)) != null && marginLayoutParams.getMarginEnd() != i2) {
            marginLayoutParams.setMarginEnd(i2);
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void setEndPadding(View view, int i2) {
        if (Features.isEnabled(Features.IS_RTL)) {
            setLeftPadding(view, i2);
        } else {
            setRightPadding(view, i2);
        }
    }

    public static void setHorizontalMargin(View view, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view != null && (marginLayoutParams = getMarginLayoutParams(view)) != null) {
            marginLayoutParams.setMarginStart(i2);
            marginLayoutParams.setMarginEnd(i2);
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void setHorizontalPadding(View view, int i2) {
        if (view != null) {
            view.setPaddingRelative(i2, view.getPaddingTop(), i2, view.getPaddingBottom());
        }
    }

    public static void setHorizontalRelativeMargin(View view, int i2, int i7) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view != null && (marginLayoutParams = getMarginLayoutParams(view)) != null) {
            marginLayoutParams.setMarginStart(i2);
            marginLayoutParams.setMarginEnd(i7);
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void setLeftMargin(View view, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view != null && (marginLayoutParams = getMarginLayoutParams(view)) != null && marginLayoutParams.leftMargin != i2) {
            marginLayoutParams.leftMargin = i2;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void setLeftPadding(View view, int i2) {
        if (view != null) {
            view.setPadding(i2, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    public static void setMargin(View view, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view != null && (marginLayoutParams = getMarginLayoutParams(view)) != null) {
            marginLayoutParams.bottomMargin = i2;
            marginLayoutParams.topMargin = i2;
            marginLayoutParams.rightMargin = i2;
            marginLayoutParams.leftMargin = i2;
            marginLayoutParams.setMarginStart(i2);
            marginLayoutParams.setMarginEnd(i2);
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void setMarginRelative(View view, Integer num, Integer num2, Integer num3, Integer num4) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view != null && (marginLayoutParams = getMarginLayoutParams(view)) != null) {
            if (num != null) {
                marginLayoutParams.setMarginStart(num.intValue());
            }
            if (num2 != null) {
                marginLayoutParams.topMargin = num2.intValue();
            }
            if (num3 != null) {
                marginLayoutParams.setMarginEnd(num3.intValue());
            }
            if (num4 != null) {
                marginLayoutParams.bottomMargin = num4.intValue();
            }
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void setPadding(View view, int i2) {
        if (view != null) {
            view.setPadding(i2, i2, i2, i2);
        }
    }

    public static void setPaddingRelative(View view, int i2, int i7, int i8, int i10) {
        if (view != null) {
            view.setPaddingRelative(i2, i7, i8, i10);
        }
    }

    public static void setRightMargin(View view, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view != null && (marginLayoutParams = getMarginLayoutParams(view)) != null && marginLayoutParams.rightMargin != i2) {
            marginLayoutParams.rightMargin = i2;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void setRightPadding(View view, int i2) {
        if (view != null) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), i2, view.getPaddingBottom());
        }
    }

    public static void setStartMargin(View view, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view != null && (marginLayoutParams = getMarginLayoutParams(view)) != null && marginLayoutParams.getMarginStart() != i2) {
            marginLayoutParams.setMarginStart(i2);
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void setStartPadding(View view, int i2) {
        if (Features.isEnabled(Features.IS_RTL)) {
            setRightPadding(view, i2);
        } else {
            setLeftPadding(view, i2);
        }
    }

    public static void setTopMargin(View view, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view != null && (marginLayoutParams = getMarginLayoutParams(view)) != null && marginLayoutParams.topMargin != i2) {
            marginLayoutParams.topMargin = i2;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void setTopPadding(View view, int i2) {
        if (view != null) {
            view.setPadding(view.getPaddingLeft(), i2, view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    public static void setVerticalMargin(View view, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view != null && (marginLayoutParams = getMarginLayoutParams(view)) != null) {
            marginLayoutParams.topMargin = i2;
            marginLayoutParams.bottomMargin = i2;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void setVerticalPadding(View view, int i2, int i7) {
        if (view != null) {
            view.setPadding(view.getPaddingLeft(), i2, view.getPaddingRight(), i7);
        }
    }

    public static void setHorizontalPadding(View view, int i2, int i7) {
        if (view != null) {
            view.setPaddingRelative(i2, view.getPaddingTop(), i7, view.getPaddingBottom());
        }
    }

    public static void setPadding(View view, int i2, int i7, int i8, int i10) {
        if (view != null) {
            view.setPadding(i2, i7, i8, i10);
        }
    }

    public static void setVerticalPadding(View view, int i2) {
        if (view != null) {
            view.setPadding(view.getPaddingLeft(), i2, view.getPaddingRight(), i2);
        }
    }

    public static void setHorizontalMargin(View view, int i2, int i7) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view != null && (marginLayoutParams = getMarginLayoutParams(view)) != null) {
            marginLayoutParams.leftMargin = i2;
            marginLayoutParams.rightMargin = i7;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void setMargin(View view, Integer num, Integer num2, Integer num3, Integer num4) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view != null && (marginLayoutParams = getMarginLayoutParams(view)) != null) {
            if (num != null) {
                marginLayoutParams.leftMargin = num.intValue();
            }
            if (num2 != null) {
                marginLayoutParams.topMargin = num2.intValue();
            }
            if (num3 != null) {
                marginLayoutParams.rightMargin = num3.intValue();
            }
            if (num4 != null) {
                marginLayoutParams.bottomMargin = num4.intValue();
            }
            view.setLayoutParams(marginLayoutParams);
        }
    }
}
