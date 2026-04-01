package com.samsung.android.gallery.widget.abstraction;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SharedTransitionDebug {
    private static String getResourceId(Resources resources, int i2) {
        if (resources == null) {
            return "no_resources";
        }
        if (i2 == -1) {
            return "no_id";
        }
        return Integer.toHexString(i2) + "/" + resources.getResourceName(i2).replace("com.sec.android.gallery3d:id/", "");
    }

    private static String getTrNameDumpString(String str) {
        return " { trName = " + Logger.getEncodedString(str) + " }";
    }

    public static String getTransitionViewsDump(Blackboard blackboard, SharedTransition sharedTransition) {
        return getTransitionViewsDump(blackboard, (ArrayList<View>) null, sharedTransition);
    }

    private static String getViewMessage(View view, int i2, int i7, String str, ArrayList<View> arrayList, SharedTransition sharedTransition) {
        String str2;
        String str3;
        String transitionName = view.getTransitionName();
        if (TextUtils.isEmpty(transitionName) || str.equals("ViewStub")) {
            return "";
        }
        if (arrayList != null) {
            arrayList.add(view);
        }
        String replace = new String(new char[i2]).replace("\u0000", " + ");
        try {
            String str4 = getResourceId(view.getResources(), i7) + "(" + view.getMeasuredWidth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + view.getMeasuredHeight() + ")";
            if (view instanceof ImageView) {
                Drawable drawable = ((ImageView) view).getDrawable();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str4);
                if (drawable == null) {
                    str3 = " (null)";
                } else {
                    str3 = Logger.toString(drawable);
                }
                sb2.append(str3);
                str4 = sb2.toString();
            } else if (view instanceof TextView) {
                str4 = str4 + " \"" + ((TextView) view).getText() + "\"";
            }
            String str5 = str4 + getTrNameDumpString(transitionName);
            int visibility = view.getVisibility();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str5);
            if (visibility == 0) {
                str2 = " VISIBLE";
            } else if (visibility == 4) {
                str2 = " INVISIBLE";
            } else if (visibility == 8) {
                str2 = " GONE";
            } else {
                str2 = " UNKNOWN(" + visibility + ')';
            }
            sb3.append(str2);
            return replace + ("[" + str + "] #" + Integer.toHexString(System.identityHashCode(view)) + " " + sb3.toString()) + "\n";
        } catch (Resources.NotFoundException unused) {
            return replace + "[" + str + "] #" + Integer.toHexString(System.identityHashCode(view)) + "\n";
        }
    }

    private static boolean isSkipViewDetail(String str) {
        if (str.equals("TabView") || str.equals("SmartAlbumItem") || str.equals("FastOptionItem") || str.equals("OverflowMenuButton") || str.equals("BottomNavigationItemView")) {
            return true;
        }
        return false;
    }

    private static void printViewHierarchy(PrintWriter printWriter, ViewGroup viewGroup, ArrayList<View> arrayList, SharedTransition sharedTransition) {
        StringBuilder s = C0212a.s("\n");
        try {
            printViewHierarchy(viewGroup, s, 0, viewGroup.getClass().getSimpleName(), arrayList, sharedTransition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (printWriter != null) {
            printWriter.println(s.toString());
        } else {
            Log.e("DUMP", s.toString());
        }
    }

    public static void validateTransitionViews(SharedTransition sharedTransition, Blackboard blackboard) {
        int i2;
        int i7;
        try {
            String transitionViewsDump = getTransitionViewsDump(blackboard, sharedTransition);
            Log.stv("SharedTransition", "=========== DUMP TRANSITION VIEW ===========" + transitionViewsDump);
            for (String next : sharedTransition.getTransitionMap().keySet()) {
                int indexOf = transitionViewsDump.indexOf(next);
                int length = next.length();
                int i8 = indexOf + length;
                if (indexOf >= 0) {
                    i2 = transitionViewsDump.substring(i8).indexOf(next);
                } else {
                    i2 = -1;
                }
                int i10 = length + i2 + i8;
                if (i2 >= 0) {
                    i7 = transitionViewsDump.substring(i10).indexOf(next);
                } else {
                    i7 = -1;
                }
                if (indexOf == -1) {
                    Log.ste("SharedTransition", "[CHECK IF TRANSITION NOT WORK] no source view and target view for TR NAME=" + Logger.getEncodedString(next));
                } else if (i2 == -1) {
                    Log.ste("SharedTransition", "[CHECK IF TRANSITION NOT WORK] has SourceView, but no target view" + Logger.v(next, Integer.valueOf(indexOf), Integer.valueOf(i2)));
                } else if (i7 != -1) {
                    Log.ste("SharedTransition", "[CHECK IF TRANSITION NOT WORK] SourceView and TargetView is not a pair" + Logger.v(next, Integer.valueOf(indexOf), Integer.valueOf(i2), Integer.valueOf(i7)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getTransitionViewsDump(Blackboard blackboard, ArrayList<View> arrayList, SharedTransition sharedTransition) {
        Activity readActivity = BlackboardUtils.readActivity(blackboard);
        if (readActivity == null) {
            return "";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(byteArrayOutputStream);
        printViewHierarchy(printWriter, (ViewGroup) readActivity.getWindow().getDecorView().findViewById(16908290), arrayList, sharedTransition);
        printWriter.flush();
        printWriter.close();
        return new String(byteArrayOutputStream.toByteArray());
    }

    private static void printViewHierarchy(ViewGroup viewGroup, StringBuilder sb2, int i2, String str, ArrayList<View> arrayList, SharedTransition sharedTransition) {
        String str2;
        ViewGroup viewGroup2 = viewGroup;
        int i7 = i2;
        String str3 = str;
        ArrayList<View> arrayList2 = arrayList;
        SharedTransition sharedTransition2 = sharedTransition;
        String viewMessage = getViewMessage(viewGroup2, i7, viewGroup.getId(), str3, arrayList2, sharedTransition2);
        SharedTransition sharedTransition3 = sharedTransition2;
        ArrayList<View> arrayList3 = arrayList2;
        sb2.append(viewMessage);
        if (!isSkipViewDetail(str3)) {
            int i8 = i7 + 1;
            for (int i10 = 0; i10 < viewGroup2.getChildCount(); i10++) {
                View childAt = viewGroup2.getChildAt(i10);
                String simpleName = childAt.getClass().getSimpleName();
                if (simpleName.length() == 0) {
                    simpleName = childAt.getClass().toGenericString();
                }
                String str4 = simpleName;
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
                        sb2.append(getViewMessage(childAt, i8, id, str4, arrayList3, sharedTransition3));
                    } else {
                        int i11 = i8;
                        printViewHierarchy((ViewGroup) childAt, sb2, i11, str4, arrayList3, sharedTransition3);
                        i8 = i11;
                    }
                } else {
                    sb2.append(getViewMessage(childAt, i8, id, str4, arrayList3, sharedTransition3));
                }
            }
        }
    }
}
