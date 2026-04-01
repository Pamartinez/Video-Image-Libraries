package com.samsung.android.gallery.app.ui.list.stories.category;

import A4.C0368c;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent;
import com.samsung.android.gallery.app.ui.list.stories.category.category.StoriesCatBaseViewHolder;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.ListScrollBuilder;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InboundScroller {
    GalleryAppBarLayout mAppbar;
    String mDataLocationKey;
    int mDataPosition;
    int mDelayMs;
    boolean mFromHeader;
    StoriesCategory2Header mHeader;
    boolean mIsRtl = Features.isEnabled(Features.IS_RTL);
    GalleryListView mList;
    IBaseListView mView;

    public InboundScroller(IBaseListView iBaseListView) {
        this.mView = iBaseListView;
        this.mAppbar = iBaseListView.getAppbarLayout();
        this.mList = iBaseListView.getListView();
    }

    private boolean computeChildScroll(ListScrollBuilder listScrollBuilder, int i2) {
        StoriesCatBaseViewHolder storiesCatBaseViewHolder;
        StoriesCategory2Header storiesCategory2Header = this.mHeader;
        if (storiesCategory2Header != null) {
            storiesCatBaseViewHolder = getCategory(storiesCategory2Header.getList(), this.mDataLocationKey);
        } else {
            storiesCatBaseViewHolder = null;
        }
        if (!this.mFromHeader || storiesCatBaseViewHolder == null) {
            return false;
        }
        View findHScrollTarget = findHScrollTarget(this.mList.getAdapter(), i2);
        if ("location://stories/category/transitory".equals(this.mDataLocationKey) || findHScrollTarget == null) {
            return false;
        }
        listScrollBuilder.append(storiesCatBaseViewHolder.getListView(), this.mDataPosition, getChildDataHorizontalOffset(storiesCatBaseViewHolder.getListView(), findHScrollTarget));
        return true;
    }

    private boolean computeListScroll(ListScrollBuilder listScrollBuilder, int i2) {
        int i7;
        boolean z;
        int i8;
        View view;
        GalleryAppBarLayout galleryAppBarLayout;
        GalleryAppBarLayout galleryAppBarLayout2;
        View findVScrollTarget = findVScrollTarget(this.mList.getAdapter(), i2);
        if (findVScrollTarget == null) {
            return false;
        }
        if (this.mFromHeader) {
            i7 = 0;
        } else {
            i7 = getViewPosition(i2);
        }
        RectF viewRect = ViewUtils.getViewRect(this.mList);
        GalleryAppBarLayout galleryAppBarLayout3 = this.mAppbar;
        if (galleryAppBarLayout3 == null || galleryAppBarLayout3.seslIsCollapsed()) {
            z = false;
        } else {
            z = true;
        }
        int i10 = (int) viewRect.top;
        int i11 = (int) viewRect.bottom;
        if (!z || (galleryAppBarLayout2 = this.mAppbar) == null) {
            i8 = 0;
        } else {
            i8 = galleryAppBarLayout2.getHeight();
        }
        int i12 = i11 - i8;
        if (this.mFromHeader) {
            view = this.mHeader.getList();
        } else {
            view = findVScrollTarget;
        }
        float f = ViewUtils.getViewRect(view).top;
        float f5 = ViewUtils.getViewRect(findVScrollTarget).top;
        float f8 = (float) i10;
        if (f5 < f8) {
            listScrollBuilder.append(this.mList, i7, (int) (f - f5));
        } else if (!z || ((float) findVScrollTarget.getHeight()) + f5 <= ((float) (i12 - this.mAppbar.getVisibleHeight()))) {
            float f10 = (float) i12;
            if (((float) findVScrollTarget.getHeight()) + f5 > f10) {
                if (z) {
                    galleryAppBarLayout = this.mAppbar;
                } else {
                    galleryAppBarLayout = null;
                }
                listScrollBuilder.withAppbarCollapsing(galleryAppBarLayout).append(this.mList, i7, (int) ((f - f8) - ((f5 + ((float) findVScrollTarget.getHeight())) - f10)));
            } else if (this.mFromHeader) {
                return false;
            } else {
                listScrollBuilder.append(this.mList, i7, 0);
            }
        } else {
            listScrollBuilder.withAppbarCollapsing(this.mAppbar).append(this.mList, i7, 0);
        }
        return true;
    }

    private View findHScrollTarget(RecyclerView.Adapter<?> adapter, int i2) {
        StoriesCatBaseViewHolder storiesCatBaseViewHolder;
        RecyclerView.ViewHolder childViewHolder;
        if (adapter instanceof BaseListViewAdapter) {
            if (this.mFromHeader) {
                StoriesCategory2Header storiesCategory2Header = this.mHeader;
                if (storiesCategory2Header != null) {
                    storiesCatBaseViewHolder = getCategory(storiesCategory2Header.getList(), this.mDataLocationKey);
                } else {
                    storiesCatBaseViewHolder = null;
                }
                if (storiesCatBaseViewHolder == null || (childViewHolder = storiesCatBaseViewHolder.getChildViewHolder(i2)) == null) {
                    return null;
                }
                return childViewHolder.itemView;
            }
            RecyclerView.ViewHolder findViewHolderForAdapterPosition = this.mList.findViewHolderForAdapterPosition(((BaseListViewAdapter) adapter).getViewPosition(i2));
            if (findViewHolderForAdapterPosition != null) {
                return findViewHolderForAdapterPosition.itemView;
            }
        }
        return null;
    }

    private View findVScrollTarget(RecyclerView.Adapter<?> adapter, int i2) {
        StoriesCatBaseViewHolder storiesCatBaseViewHolder;
        if (adapter instanceof BaseListViewAdapter) {
            if (this.mFromHeader) {
                StoriesCategory2Header storiesCategory2Header = this.mHeader;
                if (storiesCategory2Header != null) {
                    storiesCatBaseViewHolder = getCategory(storiesCategory2Header.getList(), this.mDataLocationKey);
                } else {
                    storiesCatBaseViewHolder = null;
                }
                if (storiesCatBaseViewHolder != null) {
                    return storiesCatBaseViewHolder.getListView();
                }
                return null;
            }
            RecyclerView.ViewHolder findViewHolderForAdapterPosition = this.mList.findViewHolderForAdapterPosition(((BaseListViewAdapter) adapter).getViewPosition(i2));
            if (findViewHolderForAdapterPosition != null) {
                return findViewHolderForAdapterPosition.itemView;
            }
        }
        return null;
    }

    private StoriesCatBaseViewHolder getCategory(RecyclerView recyclerView, String str) {
        for (int i2 = 0; i2 < recyclerView.getChildCount(); i2++) {
            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(recyclerView.getChildAt(i2));
            if (childViewHolder instanceof StoriesCatBaseViewHolder) {
                StoriesCatBaseViewHolder storiesCatBaseViewHolder = (StoriesCatBaseViewHolder) childViewHolder;
                if (str.equals(storiesCatBaseViewHolder.getDataKey())) {
                    return storiesCatBaseViewHolder;
                }
            }
        }
        return null;
    }

    private int getChildDataHorizontalOffset(RecyclerView recyclerView, View view) {
        RectF viewRect = ViewUtils.getViewRect(recyclerView);
        RectF viewRect2 = ViewUtils.getViewRect(view);
        if (this.mIsRtl) {
            if (viewRect2.right >= viewRect.right) {
                return 0;
            }
        } else if (viewRect2.right <= viewRect.right) {
            return 0;
        }
        return (int) ((viewRect.width() - ((float) (recyclerView.getPaddingEnd() + recyclerView.getPaddingStart()))) - viewRect2.width());
    }

    private int getViewPosition(int i2) {
        if (this.mList.getAdapter() != null) {
            return this.mList.getAdapter().getViewPosition(i2);
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPositionWithoutScroll$0(int i2) {
        setViewpagerItemPosition(this.mHeader, i2);
    }

    /* access modifiers changed from: private */
    public void setPositionWithoutScroll(int i2) {
        if (this.mHeader != null && "location://stories/category/transitory".equals(this.mDataLocationKey)) {
            int i7 = this.mDelayMs;
            if (i7 > 0) {
                this.mList.postDelayed(new C0368c(this, i2, 17), (long) i7);
            } else {
                setViewpagerItemPosition(this.mHeader, i2);
            }
        }
    }

    private void setViewpagerItemPosition(StoriesCategory2Header storiesCategory2Header, int i2) {
        storiesCategory2Header.onHandleInternalEvent(InternalEvent.SET_CURRENT_ITEM, Integer.valueOf(i2), Boolean.FALSE);
    }

    public InboundScroller setDataLocationKey(String str) {
        this.mDataLocationKey = str;
        this.mFromHeader = !"location://stories/category/more".equals(str);
        return this;
    }

    public InboundScroller setDataPosition(int i2) {
        this.mDataPosition = i2;
        return this;
    }

    public InboundScroller setDelay(int i2) {
        this.mDelayMs = i2;
        return this;
    }

    public InboundScroller setHeader(StoriesCategory2Header storiesCategory2Header) {
        this.mHeader = storiesCategory2Header;
        return this;
    }

    public void start() {
        GalleryListView galleryListView;
        if (this.mDataPosition >= 0 && (galleryListView = this.mList) != null && galleryListView.getAdapter() != null) {
            ListScrollBuilder withDelay = new ListScrollBuilder().withDelay((long) this.mDelayMs);
            if (computeListScroll(withDelay, this.mDataPosition) || computeChildScroll(withDelay, this.mDataPosition)) {
                withDelay.apply();
            }
            setPositionWithoutScroll(this.mDataPosition);
        }
    }

    public void startOnPreDraw(final View view) {
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                if ("location://stories/category/transitory".equals(InboundScroller.this.mDataLocationKey)) {
                    InboundScroller inboundScroller = InboundScroller.this;
                    inboundScroller.setPositionWithoutScroll(inboundScroller.mDataPosition);
                    return true;
                }
                InboundScroller.this.start();
                return true;
            }
        });
    }
}
