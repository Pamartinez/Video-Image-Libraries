package com.samsung.android.gallery.plugins.filebrowser;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import c0.C0086a;
import com.samsung.android.gallery.plugins.R$bool;
import com.samsung.android.gallery.plugins.R$drawable;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.filebrowser.OnBackPressCompat;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.scsp.media.file.FileApiContract;
import java.util.List;
import java.util.Optional;
import za.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class FileBaseFragment extends Fragment implements OnBackPressCompat.BackAllowCallback {
    protected final String TAG = getClass().getSimpleName();
    protected GalleryAppBarLayout mAppbar;
    protected Blackboard mBlackboard;
    protected Bundle mBundle = new Bundle();
    protected ViewGroup mContainer;
    protected OnBackPressCompat mOnBackPressCompat;
    protected String mPath = "";
    protected ViewGroup mRoot;
    protected Toolbar mToolbar;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindToolbar$0(View view) {
        view.postDelayed(new f(0, this), 120);
    }

    public final void commitFragment(Fragment fragment) {
        commitFragment(fragment, (List<View>) null);
    }

    public final void finishFragment() {
        Optional.ofNullable(getActivity()).ifPresent(new b(0));
    }

    public String getSubtitle() {
        return null;
    }

    public abstract String getTitle();

    public final Fragment getTopFragment() {
        List list = (List) Optional.ofNullable(getActivity()).map(new e(0)).orElse((Object) null);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (Fragment) C0086a.f(1, list);
    }

    public abstract View inflateView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    public final void notifyBackAllowStateChange() {
        OnBackPressCompat onBackPressCompat = this.mOnBackPressCompat;
        if (onBackPressCompat != null) {
            onBackPressCompat.notifyStateChange();
        }
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        int systemInsetsTop = WindowUtils.getSystemInsetsTop(windowInsets);
        int systemInsetsBottom = WindowUtils.getSystemInsetsBottom(windowInsets);
        ViewMarginUtils.setTopMargin(view, systemInsetsTop);
        ViewMarginUtils.setBottomMargin(view, systemInsetsBottom);
        return windowInsets;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        this.mBundle = arguments;
        String str = "";
        if (arguments != null) {
            str = arguments.getString(FileApiContract.Parameter.PATH, str);
        }
        this.mPath = str;
        this.mBlackboard = Blackboard.getInstance(context.toString());
        if (getResources().getBoolean(R$bool.enableOnBackInvokedCallback)) {
            this.mOnBackPressCompat = (OnBackPressCompat) this.mBlackboard.read("service://OnBackPressCompat", null);
        }
    }

    public abstract boolean onBackPressed();

    public void onBindAppbar(GalleryAppBarLayout galleryAppBarLayout) {
        galleryAppBarLayout.setTitle((CharSequence) getTitle());
        galleryAppBarLayout.setSubtitle(getSubtitle());
    }

    public void onBindToolbar(Toolbar toolbar) {
        toolbar.setTitle((CharSequence) getTitle());
        toolbar.setSubtitle((CharSequence) getSubtitle());
        toolbar.setNavigationIcon(R$drawable.tw_ic_ab_back_mtrl_with_inset);
        toolbar.setNavigationOnClickListener(new d(0, this));
    }

    public void onBindView(ViewGroup viewGroup) {
        Toolbar toolbar = (Toolbar) viewGroup.findViewById(R$id.toolbar);
        this.mToolbar = toolbar;
        if (toolbar != null) {
            onBindToolbar(toolbar);
        }
        GalleryAppBarLayout galleryAppBarLayout = (GalleryAppBarLayout) viewGroup.findViewById(R$id.appbar);
        this.mAppbar = galleryAppBarLayout;
        if (galleryAppBarLayout != null) {
            onBindAppbar(galleryAppBarLayout);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRoot == null) {
            this.mContainer = viewGroup;
            ViewGroup viewGroup2 = (ViewGroup) inflateView(layoutInflater, viewGroup);
            this.mRoot = viewGroup2;
            onBindView(viewGroup2);
        }
        return this.mRoot;
    }

    public void onDestroyView() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2 = this.mRoot;
        if (!(viewGroup2 == null || (viewGroup = this.mContainer) == null)) {
            viewGroup.removeView(viewGroup2);
        }
        super.onDestroyView();
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onViewCreated(View view, Bundle bundle) {
        if (SdkConfig.atLeast(SdkConfig.GED.B)) {
            view.setOnApplyWindowInsetsListener(new c(this));
        }
    }

    public final void commitFragment(Fragment fragment, List<View> list) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putString("caller", Logger.getSimpleName((Object) getTopFragment()));
        fragment.setArguments(arguments);
        try {
            FragmentTransaction beginTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            if (list != null && !list.isEmpty()) {
                beginTransaction.setReorderingAllowed(true);
                for (View next : list) {
                    beginTransaction.addSharedElement(next, next.getTransitionName());
                }
            }
            beginTransaction.replace(R$id.fragment_container, fragment, Logger.getSimpleName((Object) fragment)).addToBackStack((String) null).commitAllowingStateLoss();
        } catch (Exception e) {
            String str = this.TAG;
            Log.e((CharSequence) str, "commitFragment failed " + fragment, (Throwable) e);
        }
    }
}
