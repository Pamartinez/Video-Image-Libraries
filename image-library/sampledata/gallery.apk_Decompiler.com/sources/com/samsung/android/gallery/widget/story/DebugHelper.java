package com.samsung.android.gallery.widget.story;

import a6.C0419b;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.samsung.android.gallery.database.dbtype.StoryLevel2Cat;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DebugHelper {
    private static final boolean ENABLED = false;

    private static TextView bindView(View view, int i2, Rect rect) {
        int i7;
        TextView textView = (TextView) view.findViewWithTag("debugInfoView");
        if (textView != null) {
            return textView;
        }
        ViewGroup viewGroup = (ViewGroup) view.findViewById(i2);
        if (viewGroup == null) {
            return null;
        }
        TextView textView2 = new TextView(view.getContext());
        if (viewGroup instanceof RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(12);
            layoutParams.addRule(21);
            textView2.setLayoutParams(layoutParams);
        } else if (viewGroup instanceof FrameLayout) {
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
            if (i2 == R$id.content) {
                i7 = 1;
            } else {
                i7 = 8388613;
            }
            layoutParams2.gravity = i7 | 80;
            textView2.setLayoutParams(layoutParams2);
        }
        textView2.setGravity(8388693);
        textView2.setTag("debugInfoView");
        textView2.setTextSize(7.0f);
        textView2.setTextColor(-1);
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        textView2.setSingleLine(true);
        textView2.setPadding(rect.left, rect.top, rect.right, rect.bottom);
        viewGroup.addView(textView2);
        return textView2;
    }

    private static String getDebugInfo(MediaItem mediaItem, boolean z) {
        int storyId = MediaItemStory.getStoryId(mediaItem);
        int storyType = MediaItemStory.getStoryType(mediaItem);
        int storySaType = MediaItemStory.getStorySaType(mediaItem);
        Enum category = StoryLevel2Cat.getCategory(storySaType);
        if (StoryLevel2Cat.DEFAULT.equals(category)) {
            category = StoryType.getTypeByValue(storyType);
        }
        String name = category.name();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(storyId);
        sb2.append("   ");
        sb2.append(storyType);
        sb2.append(" | ");
        sb2.append(storySaType);
        sb2.append(" | ");
        if (z) {
            name = trim(name);
        }
        sb2.append(name);
        sb2.append(" | ");
        sb2.append(StoryLevel2Cat.owner(storyType, storySaType));
        return sb2.toString();
    }

    public static void showInfoView(ListViewHolder listViewHolder, int i2, boolean z) {
        showInfoView(listViewHolder, i2, z ? new Rect(5, 10, 55, 20) : new Rect(5, 5, 5, 5));
    }

    private static String trim(String str) {
        String replace = str.replace("_WITH", "").replace("_OF", "").replace("_THE", "");
        return replace.substring(0, Math.min(replace.length(), 17));
    }

    public static void showInfoView(ListViewHolder listViewHolder, int i2, Rect rect) {
        showInfoView(listViewHolder, listViewHolder.getMediaItem(), i2, rect);
    }

    public static void showInfoView(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, Rect rect) {
        TextView bindView;
        try {
            if (ENABLED) {
                if (mediaItem == null) {
                    mediaItem = listViewHolder.getMediaItem();
                }
                View view = listViewHolder.itemView;
                boolean z = true;
                if (!(mediaItem == null || (bindView = bindView(view, i2, rect)) == null)) {
                    bindView.setText(getDebugInfo(mediaItem, true));
                    bindView.setOnClickListener(new C0419b(3, mediaItem));
                }
                View findViewWithTag = view.findViewWithTag("debugInfoView");
                if (mediaItem == null) {
                    z = false;
                }
                ViewUtils.setVisibleOrGone(findViewWithTag, z);
            }
        } catch (Exception unused) {
        }
    }
}
