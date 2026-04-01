package com.samsung.android.gallery.app.ui.container.factory;

import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsFragment;
import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsFragment;
import com.samsung.android.gallery.app.ui.list.mtp.MtpFragment;
import com.samsung.android.gallery.app.ui.list.search.CollectionFragment;
import com.samsung.android.gallery.app.ui.list.sharings.factory.SharedAlbumFactory;
import com.samsung.android.gallery.app.ui.list.stories.StoriesFragment;
import com.samsung.android.gallery.app.ui.list.stories.category.StoriesCategory2Fragment;
import com.samsung.android.gallery.app.ui.list.stories.legacy.StoriesLegacyFragment;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewFragment;
import com.samsung.android.gallery.app.ui.list.timeline.Timeline2Fragment;
import com.samsung.android.gallery.app.ui.list.timeline.TimelineFragment;
import com.samsung.android.gallery.app.ui.list.trash.TrashFragment;
import com.samsung.android.gallery.app.ui.list.trash.container.TrashContainerFragment;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.HashMap;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ChildFragmentFactory {
    public static String BOTTOM_NORMAL = "bottom_normal";
    public static String BOTTOM_PICKER = "bottom_picker";
    public static String LIST_NORMAL = "list_normal";
    final HashMap<String, Supplier<MvpBaseFragment<?, ?>>> mFragmentSuppliers = new HashMap<>();

    private MvpBaseFragment<?, ?> createChildFragmentWithArgs(String str) {
        if (LocationKey.isTrash(str)) {
            return createTrashCompat(str);
        }
        return null;
    }

    private MvpBaseFragment<?, ?> createTrashCompat(String str) {
        if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH) || !ArgumentsUtil.getArgValue(str, "has_family_album", false)) {
            return new TrashFragment();
        }
        return new TrashContainerFragment();
    }

    public MvpBaseFragment<?, ?> create(ChildFragmentInfo childFragmentInfo, String str) {
        Supplier<MvpBaseFragment<?, ?>> supplier = getSupplier(str);
        if (supplier != null) {
            MvpBaseFragment<?, ?> mvpBaseFragment = supplier.get();
            childFragmentInfo.setFirstSelect(mvpBaseFragment.getLocationKey(), false);
            return mvpBaseFragment;
        } else if (!TextUtils.isEmpty(str)) {
            return createChildFragmentWithArgs(str);
        } else {
            return null;
        }
    }

    public MvpBaseFragment<?, ?> createAlbumsCompat() {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return new MxAlbumsFragment();
        }
        return new AlbumsFragment();
    }

    public MvpBaseFragment<?, ?> createCollectionCompat() {
        return new CollectionFragment();
    }

    public MvpBaseFragment<?, ?> createMtpCompat() {
        return new MtpFragment();
    }

    public MvpBaseFragment<?, ?> createSharingCompat() {
        return SharedAlbumFactory.create();
    }

    public MvpBaseFragment<?, ?> createStoriesCompat() {
        if (PreferenceFeatures.OneUi7x.STORY_ONE_UI_70) {
            return new StoriesCategory2Fragment();
        }
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            return new StoriesPinchViewFragment();
        }
        if (PreferenceFeatures.OneUi30.MEMORIES) {
            return new StoriesFragment();
        }
        return new StoriesLegacyFragment();
    }

    public MvpBaseFragment<?, ?> createTimelineCompat() {
        if (PocFeatures.QUICK_SEARCH) {
            return new Timeline2Fragment();
        }
        return new TimelineFragment();
    }

    public Supplier<MvpBaseFragment<?, ?>> getSupplier(String str) {
        return this.mFragmentSuppliers.get(str);
    }
}
