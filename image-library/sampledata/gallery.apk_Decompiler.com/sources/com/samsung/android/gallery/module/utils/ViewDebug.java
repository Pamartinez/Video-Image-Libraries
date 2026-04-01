package com.samsung.android.gallery.module.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import qa.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewDebug {
    public static boolean MODE_GUI_DUMP;

    public static Bundle dumpBundleForGui() {
        Blackboard blackboard;
        Activity activity;
        ViewGroup viewGroup;
        String orElse = Blackboard.getBlackboardMap().keySet().stream().filter(new e(12)).findFirst().orElse((Object) null);
        if (orElse != null) {
            blackboard = Blackboard.getInstance(orElse);
        } else {
            blackboard = null;
        }
        if (blackboard != null) {
            activity = (Activity) blackboard.read("data://activity");
        } else {
            activity = null;
        }
        if (activity != null) {
            viewGroup = (ViewGroup) activity.findViewById(16908290);
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(byteArrayOutputStream);
        printViewHierarchy(printWriter, viewGroup, true);
        printWriter.flush();
        String byteArrayOutputStream2 = byteArrayOutputStream.toString();
        printWriter.close();
        Bundle bundle = new Bundle();
        bundle.putString("viewDump", byteArrayOutputStream2);
        return bundle;
    }

    private static String getViewMessage(View view, int i2, int i7, String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6 = "";
        if (view.getVisibility() != 0 || str.equals("ViewStub")) {
            return str6;
        }
        if (view.getMeasuredHeight() == 0 && view.getMeasuredWidth() == 0) {
            return str6;
        }
        String replace = new String(new char[i2]).replace("\u0000", " + ");
        if (i7 != -1) {
            try {
                str2 = Integer.toHexString(i7);
            } catch (Resources.NotFoundException unused) {
                if (MODE_GUI_DUMP) {
                    Drawable background = view.getBackground();
                    StringBuilder sb2 = new StringBuilder(" ");
                    sb2.append(background);
                    sb2.append("/");
                    if (background instanceof ColorDrawable) {
                        str6 = Integer.toHexString(((ColorDrawable) background).getColor());
                    }
                    sb2.append(str6);
                    return replace + "[" + str + "] #" + Integer.toHexString(System.identityHashCode(view)) + sb2.toString() + "\n";
                }
                return replace + "[" + str + "] #" + Integer.toHexString(System.identityHashCode(view)) + "\n";
            }
        } else {
            str2 = "no_id";
        }
        if (view.getResources() != null) {
            str3 = view.getResources().getResourceName(i7).replace("com.sec.android.gallery3d:id/", str6) + " :";
        } else {
            str3 = NumericEnum.SEP;
        }
        if (MODE_GUI_DUMP) {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            Drawable background2 = view.getBackground();
            StringBuilder sb3 = new StringBuilder();
            sb3.append("(x=" + pxToDp(iArr[0]) + "DP ,y=" + pxToDp(iArr[1]) + "DP, w=" + pxToDp(view.getMeasuredWidth()) + "DP ,h=" + pxToDp(view.getMeasuredHeight()) + "DP) ");
            sb3.append(" bg{");
            sb3.append(background2);
            sb3.append("/");
            if (background2 instanceof ColorDrawable) {
                str5 = Integer.toHexString(((ColorDrawable) background2).getColor()) + "}";
            } else {
                str5 = "}";
            }
            sb3.append(str5);
            str4 = sb3.toString();
        } else {
            str4 = (str6 + str2) + "(" + ((int) view.getX()) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + ((int) view.getY()) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + view.getMeasuredWidth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + view.getMeasuredHeight() + ")";
        }
        if (view.getAlpha() != 1.0f) {
            str4 = str4 + String.format(" (alpha %.01f%%)", new Object[]{Float.valueOf(view.getAlpha() * 100.0f)});
        }
        if (view instanceof ImageView) {
            if (str.startsWith("PhotoView")) {
                String view2 = view.toString();
                int indexOf = view2.indexOf("mBitmap=") + 8;
                str4 = str4 + " " + view2.substring(indexOf, view2.indexOf("}", indexOf) + 1);
            } else {
                Drawable drawable = ((ImageView) view).getDrawable();
                str4 = str4 + " " + Logger.toString(drawable);
            }
        } else if (view instanceof TextView) {
            str4 = str4 + " \"" + ((TextView) view).getText() + "\"";
        }
        Object tag = view.getTag(33554433);
        if (tag != null) {
            str4 = str4 + "{" + tag + "}";
        }
        if (MODE_GUI_DUMP) {
            return replace + "[" + str3 + str + "] " + str4 + "\n";
        }
        return replace + "[" + str3 + str + "] #" + Integer.toHexString(System.identityHashCode(view)) + " " + str4 + "\n";
    }

    private static boolean isSkipViewDetail(String str) {
        if (str.equals("TabView") || str.equals("SmartAlbumItem") || str.equals("FastOptionItem") || str.equals("OverflowMenuButton") || str.equals("BottomNavigationItemView")) {
            return true;
        }
        return false;
    }

    public static void printViewHierarchy(PrintWriter printWriter, ViewGroup viewGroup, boolean z) {
        MODE_GUI_DUMP = z;
        printViewHierarchy(printWriter, viewGroup);
        MODE_GUI_DUMP = false;
    }

    private static int pxToDp(int i2) {
        return Math.round(((float) i2) / 3.0f);
    }

    public static void printViewHierarchy(PrintWriter printWriter, ViewGroup viewGroup) {
        StringBuilder s = C0212a.s("\n");
        try {
            printViewHierarchy(viewGroup, s, 0, viewGroup.getClass().getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (printWriter != null) {
            printWriter.println(s.toString());
        } else {
            Log.e("DUMP", s.toString());
        }
    }

    private static void printViewHierarchy(ViewGroup viewGroup, StringBuilder sb2, int i2, String str) {
        String str2;
        sb2.append(getViewMessage(viewGroup, i2, viewGroup.getId(), str));
        if (!isSkipViewDetail(str)) {
            int i7 = i2 + 1;
            for (int i8 = 0; i8 < viewGroup.getChildCount(); i8++) {
                View childAt = viewGroup.getChildAt(i8);
                String simpleName = childAt.getClass().getSimpleName();
                if (simpleName.length() == 0) {
                    simpleName = childAt.getClass().toGenericString();
                }
                int id = childAt.getId();
                if (childAt instanceof ViewGroup) {
                    if (id != -1) {
                        try {
                            str2 = childAt.getResources().getResourceName(id);
                        } catch (Resources.NotFoundException unused) {
                            str2 = childAt.toString();
                        }
                    } else {
                        str2 = "";
                    }
                    if (str2.equals("com.sec.android.gallery3d:id/scroll_popup")) {
                        sb2.append(getViewMessage(childAt, i7, id, simpleName));
                    } else {
                        printViewHierarchy((ViewGroup) childAt, sb2, i7, simpleName);
                    }
                } else {
                    sb2.append(getViewMessage(childAt, i7, id, simpleName));
                }
            }
        }
    }
}
