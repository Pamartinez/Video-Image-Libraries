package com.samsung.android.gallery.app.ui.list.stories.category;

import A4.C0366a;
import Ab.b;
import K.a;
import Qb.e;
import android.content.Context;
import android.text.TextUtils;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.category.IStoriesCategory2View;
import com.samsung.android.gallery.app.ui.list.stories.category.category.StoriesCatBaseViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.ItemProperty;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryTransition<T extends IStoriesCategory2View> {
    private static final boolean STORIES_TOP_COLOR_EFFECT = PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesTopColorEffect);
    private Blackboard mBlackboard;
    private SimpleAutoScroller mChildAutoScroller;
    private final T mView;

    public CategoryTransition(T t) {
        this.mView = t;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, java.util.function.Function] */
    private StoriesCatBaseViewHolder findCategoryWith(String str) {
        GalleryListView galleryListView = (GalleryListView) Optional.ofNullable(this.mView.getHeader()).map(new Object()).orElse((Object) null);
        if (galleryListView != null && !TextUtils.isEmpty(str)) {
            for (int i2 = 0; i2 < galleryListView.getChildCount(); i2++) {
                StoriesCatBaseViewHolder storiesCatBaseViewHolder = (StoriesCatBaseViewHolder) galleryListView.findContainingViewHolder(galleryListView.getChildAt(i2));
                if (storiesCatBaseViewHolder != null && str.equals(storiesCatBaseViewHolder.getDataKey())) {
                    return storiesCatBaseViewHolder;
                }
            }
        }
        return null;
    }

    private String getReturnCategory() {
        return ArgumentsUtil.getArgValue(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard), "categoryKey");
    }

    private void invalidateTransitoryStoriesPosition() {
        int intValue = ((Integer) this.mView.getBlackboard().read("data://story_transition_return_position", -1)).intValue();
        if (isReturnToTransitory(intValue) || (PreferenceFeatures.OneUi8x.STORY_ONE_UI_80 && "location://stories/category/transitory".equals(getReturnCategory()))) {
            new InboundScroller(this.mView).setHeader(this.mView.getHeader()).setDataLocationKey("location://stories/category/transitory").setDataPosition(intValue).startOnPreDraw(this.mView.getListView());
        }
    }

    private boolean isReturnToMore(String str, int i2) {
        if (TextUtils.isEmpty(str) || "location://stories/category/more".equals(str)) {
            return true;
        }
        if (!"location://stories/category/transitory".equals(str) || isReturnToTransitory(i2)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startCategoryAutoScroll$3() {
        Optional.ofNullable(this.mView.getToolbar()).ifPresent(new C0366a(0));
        this.mBlackboard.pop("data://story_transition_return_position", -1);
        this.mChildAutoScroller = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startCategoryAutoScroll$4(RecyclerView recyclerView, int i2) {
        a aVar;
        SimpleAutoScroller appbar = new SimpleAutoScroller(this.mBlackboard, recyclerView, i2).setAppbar(this.mView.getAppbarLayout());
        if (STORIES_TOP_COLOR_EFFECT) {
            aVar = new a(28);
        } else {
            aVar = null;
        }
        SimpleAutoScroller themeColor = appbar.setThemeColor(aVar);
        T t = this.mView;
        Objects.requireNonNull(t);
        SimpleAutoScroller withFinishAction = themeColor.withStartAction(new g(3, t)).withFinishAction(new e(27, this));
        this.mChildAutoScroller = withFinishAction;
        withFinishAction.start();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startSimpleAutoScroller$1(String str, int i2, Consumer consumer) {
        StoriesCatBaseViewHolder findCategoryWith = findCategoryWith(str);
        if (findCategoryWith != null) {
            startCategoryAutoScroll(findCategoryWith.getListView(), i2);
        } else {
            consumer.accept(Boolean.FALSE);
        }
    }

    private void startCategoryAutoScroll(RecyclerView recyclerView, int i2) {
        new InboundScroller(this.mView).setDataPosition(i2).setDataLocationKey(getReturnCategory()).setHeader(this.mView.getHeader()).setDelay(0).start();
        this.mView.getListView().postDelayed(new b((Object) this, (Object) recyclerView, i2, 29), 70);
    }

    public boolean isReturnToTransitory(int i2) {
        if (i2 >= 100000) {
            return true;
        }
        return false;
    }

    public void onAttach() {
        this.mBlackboard = this.mView.getBlackboard();
    }

    public void startDecoAnimationForReturn() {
        int i2;
        String returnCategory = getReturnCategory();
        Context context = this.mView.getContext();
        if (context != null && !LocationKey.isStories(returnCategory)) {
            if ("location://stories/category/more".equals(returnCategory) || TextUtils.isEmpty(returnCategory)) {
                i2 = this.mView.getTransitionRadius();
            } else {
                i2 = ItemProperty.getItemRadius(context, returnCategory);
            }
            this.mView.getBlackboard().publish("data://story_transition_view_radius", new int[]{0, i2});
        }
    }

    public void startShrinkAnimation() {
        invalidateTransitoryStoriesPosition();
    }

    public void startSimpleAutoScroller(int i2, Consumer<Boolean> consumer) {
        String returnCategory = getReturnCategory();
        GalleryListView listView = this.mView.getListView();
        if (listView == null) {
            consumer.accept(Boolean.FALSE);
        } else if (isReturnToMore(returnCategory, i2)) {
            listView.postDelayed(new U5.a(0, consumer), 70);
        } else {
            if (isReturnToTransitory(i2)) {
                i2 -= 100000;
            }
            ViewUtils.post(listView, new F9.e((Object) this, (Object) returnCategory, i2, (Object) consumer, 4));
        }
    }
}
