package com.samsung.android.gallery.app.ui.list.search.editcreature;

import Ab.d;
import B2.i;
import C3.C0392b;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.oneui.dividerbuttonlayout.DividerButtonLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingBottomLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.module.abstraction.CustomRelationshipKeySet;
import com.samsung.android.gallery.module.creature.CreatureNameDataLoaderFactory;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.LengthFilter;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import h.C0199b;
import java.util.ArrayList;
import java.util.List;
import n0.C0235b;
import q5.a;
import q5.b;
import q5.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditCreatureNameFragment extends MvpBaseFragment<IEditCreatureNameView, EditCreatureNamePresenter> implements IEditCreatureNameView {
    /* access modifiers changed from: private */
    public TagCreatureAdapter mAutoCompleteAdapter;
    ImageView mBackground;
    ImageView mChangeCreatureCoverView;
    ImageView mCreatureIcon;
    DividerButtonLayout mDividerButtonLayout;
    FloatingBottomLayout mDividerButtonLayoutContainer;
    LinearLayout mEditCreatureNameLayout;
    AutoCompleteTextView mEditTextView;
    ImageView mFaceView;
    RelativeLayout mFaceViewLayout;
    FloatingToolbarLayout mFloatingToolbarLayout;
    LinearLayout mFrequentlyContactList;
    /* access modifiers changed from: private */
    public boolean mIsDoneButtonEnabled = false;
    private boolean mIsLandScape = false;
    private boolean mIsPet;
    View mLinkToContactBackground;
    ImageView mLinkToContactIcon;
    TextView mLinkToContactTitle;
    View mMainLayout;
    private String mOldCreatureName;
    private String mOldRelationship;
    TextView mRelationView;
    ImageView mRelationshipDeleteIcon;
    ImageView mRelationshipIcon;
    FrameLayout mRelationshipInput;
    private String mScreenId = AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_FACE_SUGGESTION_LIST.toString();
    NestedScrollView mScrollView;
    LinearLayout mSuggestedNameList;
    private ArrayList<CreatureNameData> mTaggedCreatureNameList = new ArrayList<>();
    private final TextWatcher mTextWatcher = new TextWatcher() {
        public void onTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
            EditCreatureNameFragment.this.mIsDoneButtonEnabled = true;
            EditCreatureNameFragment.this.enableSaveButton();
            EditCreatureNameFragment.this.updateCreatureIconTint();
            EditCreatureNameFragment.this.mAutoCompleteAdapter.filter(charSequence.toString());
            ((EditCreatureNamePresenter) EditCreatureNameFragment.this.mPresenter).updateSelectedName(charSequence.toString());
        }

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
        }
    };
    GalleryToolbar mToolbar;

    /* access modifiers changed from: private */
    public void checkVisibility() {
        boolean z;
        boolean z3;
        GalleryRecyclerView galleryRecyclerView = (GalleryRecyclerView) this.mFrequentlyContactList.findViewById(R.id.recycler_list);
        GalleryListAdapter adapter = galleryRecyclerView.getAdapter();
        GalleryRecyclerView galleryRecyclerView2 = (GalleryRecyclerView) this.mSuggestedNameList.findViewById(R.id.recycler_list);
        GalleryListAdapter adapter2 = galleryRecyclerView2.getAdapter();
        if (adapter != null && adapter2 != null) {
            if (adapter2.getItemCount() <= 0) {
                z = true;
            } else {
                z = false;
            }
            if (adapter.getItemCount() <= 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (!z || !z3) {
                setListVisibleOrGone(true);
                if (z) {
                    galleryRecyclerView2.getLayoutParams().height = -1;
                    this.mSuggestedNameList.getLayoutParams().height = -1;
                } else if (z3) {
                    galleryRecyclerView.getLayoutParams().height = -1;
                    this.mFrequentlyContactList.getLayoutParams().height = -1;
                } else {
                    galleryRecyclerView2.getLayoutParams().height = -2;
                    this.mSuggestedNameList.getLayoutParams().height = -2;
                    galleryRecyclerView.getLayoutParams().height = -1;
                    this.mFrequentlyContactList.getLayoutParams().height = -1;
                }
            } else {
                setListVisibleOrGone(false);
            }
        }
    }

    private void createAutoCompleteAdapter(Context context) {
        this.mAutoCompleteAdapter = new TagCreatureAdapter(context);
    }

    private void createEditRelationshipView(View view) {
        this.mRelationshipInput = (FrameLayout) view.findViewById(R.id.creature_relationship_input);
        this.mRelationView = (TextView) view.findViewById(R.id.add_creature_relationship);
        this.mRelationshipIcon = (ImageView) view.findViewById(R.id.relationship_icon);
        this.mRelationshipDeleteIcon = (ImageView) view.findViewById(R.id.relationship_delete_icon);
        if (!this.mIsPet && Features.isEnabled(Features.SUPPORT_RELATIONSHIP_SEARCH)) {
            this.mRelationshipInput.setVisibility(0);
            String initialRelationship = ((EditCreatureNamePresenter) this.mPresenter).getInitialRelationship();
            this.mOldRelationship = initialRelationship;
            this.mRelationView.setText(initialRelationship);
            updateRelationShipIconTint();
            updateRelationShipDeleteIconVisible();
        }
    }

    private void createEditTextView(View view) {
        this.mCreatureIcon = (ImageView) view.findViewById(R.id.creature_name_icon);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.add_creature_name);
        this.mEditTextView = autoCompleteTextView;
        autoCompleteTextView.setDropDownAnchor(R.id.edit_creature_name_fragment_dropdown_anchor);
        String initialCreatureName = ((EditCreatureNamePresenter) this.mPresenter).getInitialCreatureName();
        this.mOldCreatureName = initialCreatureName;
        this.mEditTextView.setText(initialCreatureName);
        this.mEditTextView.requestFocus();
        this.mEditTextView.setFilters(new InputFilter[]{new LengthFilter(AppResources.getAppContext(), 100)});
        this.mEditTextView.addTextChangedListener(this.mTextWatcher);
        createAutoCompleteAdapter(getContext());
        this.mEditTextView.setOnItemClickListener(new d(1, this));
        this.mEditTextView.setAdapter(this.mAutoCompleteAdapter);
        updateCreatureIcon();
        CreatureNameDataLoaderFactory.get(this.mIsPet).load(new a(this, 1), CreatureNameData.ContactType.CONTACT);
        CreatureNameDataLoaderFactory.get(this.mIsPet).load(new a(this, 2), CreatureNameData.ContactType.TAGGED);
    }

    private void createFrequentlyContactView() {
        createRecyclerView((GalleryRecyclerView) this.mFrequentlyContactList.findViewById(R.id.recycler_list), CreatureNameData.ContactType.FREQUENTLY_CONTACT);
    }

    private void createHeaderView(View view) {
        this.mSuggestedNameList = (LinearLayout) view.findViewById(R.id.suggested_name_list);
        this.mFrequentlyContactList = (LinearLayout) view.findViewById(R.id.frequently_contacted_list);
        this.mFaceViewLayout = (RelativeLayout) view.findViewById(R.id.face_image_layout);
        this.mFaceView = (ImageView) view.findViewById(R.id.face_image);
        TextView textView = (TextView) this.mSuggestedNameList.findViewById(R.id.gallery_header_title_text);
        TextView textView2 = (TextView) this.mFrequentlyContactList.findViewById(R.id.gallery_header_title_text);
        textView.setText(R.string.suggested_name);
        textView2.setText(R.string.frequently_contact);
        textView.setVisibility(8);
        textView2.setVisibility(8);
        ((EditCreatureNamePresenter) this.mPresenter).loadFaceImage();
        this.mFaceView.setClipToOutline(true);
    }

    private void createRecyclerView(GalleryRecyclerView galleryRecyclerView, final CreatureNameData.ContactType... contactTypeArr) {
        galleryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        galleryRecyclerView.setClipToPadding(true);
        galleryRecyclerView.setHasFixedSize(true);
        galleryRecyclerView.drawBottomColor();
        final RegisteredCreatureAdapter registeredCreatureAdapter = new RegisteredCreatureAdapter(this);
        galleryRecyclerView.setAdapter(registeredCreatureAdapter);
        registeredCreatureAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            public void onChanged() {
                int i2;
                EditCreatureNameFragment editCreatureNameFragment = EditCreatureNameFragment.this;
                if (editCreatureNameFragment.mFrequentlyContactList != null && editCreatureNameFragment.mSuggestedNameList != null) {
                    if (registeredCreatureAdapter.getItemCount() > 0) {
                        i2 = 0;
                    } else {
                        i2 = 8;
                    }
                    CreatureNameData.ContactType[] contactTypeArr = contactTypeArr;
                    if (contactTypeArr.length <= 0 || contactTypeArr[0] != CreatureNameData.ContactType.FREQUENTLY_CONTACT) {
                        EditCreatureNameFragment.this.mSuggestedNameList.findViewById(R.id.gallery_header_title_text).setVisibility(i2);
                    } else {
                        EditCreatureNameFragment.this.mFrequentlyContactList.findViewById(R.id.gallery_header_title_text).setVisibility(i2);
                    }
                    EditCreatureNameFragment.this.checkVisibility();
                }
            }
        });
        SimpleThreadPool.getInstance().execute(new C0235b(this, registeredCreatureAdapter, contactTypeArr, 9));
    }

    private void createSuggestionNameView() {
        SimpleThreadPool.getInstance().execute(new c(this, 0));
    }

    /* access modifiers changed from: private */
    public void enableSaveButton() {
        float f;
        if (this.mIsLandScape) {
            this.mToolbar.getMenu().findItem(R.id.menu_app_bar_save).setEnabled(this.mIsDoneButtonEnabled);
            return;
        }
        View findViewById = this.mDividerButtonLayout.findViewById(R.id.menu_app_bar_save);
        MenuItem findItem = this.mDividerButtonLayout.getMenu().findItem(R.id.menu_app_bar_save);
        if (findViewById != null) {
            if (this.mIsDoneButtonEnabled) {
                f = 1.0f;
            } else {
                f = 0.4f;
            }
            findViewById.setAlpha(f);
            findItem.setEnabled(this.mIsDoneButtonEnabled);
        }
    }

    private boolean existTaggedFromMyProfile() {
        return ((EditCreatureNamePresenter) this.mPresenter).existTaggedFromMyProfile();
    }

    private CreatureNameData.ContactType[] getSuggestionContactTypes(boolean z) {
        ArrayList arrayList = new ArrayList();
        if (!this.mIsPet && !z) {
            arrayList.add(CreatureNameData.ContactType.MY_PROFILE);
        }
        if (Features.isEnabled(Features.SUPPORT_RECOMMEND_SIMILAR_CONTACT)) {
            arrayList.add(CreatureNameData.ContactType.RECOMMEND_CONTACT);
        }
        return (CreatureNameData.ContactType[]) arrayList.toArray(new CreatureNameData.ContactType[0]);
    }

    private void initDividerButtonLayout(View view) {
        if (this.mDividerButtonLayout == null) {
            this.mDividerButtonLayoutContainer = (FloatingBottomLayout) view.findViewById(R.id.divider_button_floating_container);
            DividerButtonLayout dividerButtonLayout = (DividerButtonLayout) view.findViewById(R.id.divider_button_layout);
            this.mDividerButtonLayout = dividerButtonLayout;
            dividerButtonLayout.c(R.menu.menu_appbar_cancel_save);
            this.mDividerButtonLayout.setOnMenuItemClickListener(new a(this, 3));
        }
    }

    private void initSyncWithInsetsAnimation() {
        getView().setWindowInsetsAnimationCallback(new WindowInsetsAnimation.Callback(1) {
            public void onPrepare(WindowInsetsAnimation windowInsetsAnimation) {
                EditCreatureNameFragment.super.onPrepare(windowInsetsAnimation);
                EditCreatureNameFragment.this.mEditTextView.dismissDropDown();
            }

            public WindowInsets onProgress(WindowInsets windowInsets, List<WindowInsetsAnimation> list) {
                EditCreatureNameFragment.this.lambda$initSyncWithInsetsAnimation$3(windowInsets);
                return windowInsets;
            }
        });
        getView().setOnApplyWindowInsetsListener(new Ca.c(6, this));
    }

    private void initToolbar(View view) {
        if (this.mToolbar == null) {
            this.mFloatingToolbarLayout = (FloatingToolbarLayout) view.findViewById(R.id.sesl_floating_toolbar_layout);
            GalleryToolbar galleryToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
            this.mToolbar = galleryToolbar;
            galleryToolbar.inflateMenu(R.menu.menu_appbar_cancel_save);
            this.mToolbar.getMenu().findItem(R.id.menu_app_bar_save).setShowAsAction(2);
            this.mToolbar.getMenu().findItem(R.id.menu_app_bar_cancel).setShowAsAction(2);
            this.mToolbar.setOnMenuItemClickListener(new a(this, 0));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createEditTextView$0(AdapterView adapterView, View view, int i2, long j2) {
        onItemClicked(this.mAutoCompleteAdapter.getContactNameData(i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createRecyclerView$13(RegisteredCreatureAdapter registeredCreatureAdapter, CreatureNameData.ContactType[] contactTypeArr) {
        P p6 = this.mPresenter;
        if (p6 != null) {
            registeredCreatureAdapter.load(this.mIsPet, ((EditCreatureNamePresenter) p6).getFaceGroupIds(), contactTypeArr);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSuggestionNameView$1(CreatureNameData.ContactType[] contactTypeArr) {
        createRecyclerView((GalleryRecyclerView) this.mSuggestedNameList.findViewById(R.id.recycler_list), contactTypeArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSuggestionNameView$2() {
        ThreadUtil.runOnUiThread(new C0199b(25, this, getSuggestionContactTypes(existTaggedFromMyProfile())));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ WindowInsets lambda$initSyncWithInsetsAnimation$4(View view, WindowInsets windowInsets) {
        onApplyWindowInsets(view, windowInsets);
        if (getView() != null) {
            getView().post(new C0199b(27, this, windowInsets));
        }
        return windowInsets;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onResume$10() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            ((EditCreatureNamePresenter) p6).showSoftInput(getContext(), this.mEditTextView);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setChangeCoverView$8(View view) {
        ((EditCreatureNamePresenter) this.mPresenter).onFaceLayoutClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setHeaderImage$12(Bitmap bitmap) {
        ImageView imageView = this.mFaceView;
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setRelationDeleteIconClickListener$7(View view) {
        getBlackboard().post("command://event/RelationshipSelected", "");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setRelationshipViewTouchAndOnClickListener$5(View view) {
        onRelationshipClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setRelationshipViewTouchAndOnClickListener$6(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1) {
            return false;
        }
        this.mRelationshipInput.callOnClick();
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateContactLinkView$9(View view) {
        ((EditCreatureNamePresenter) this.mPresenter).onContactLinkAction();
    }

    /* access modifiers changed from: private */
    public boolean onOptionsMenuSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_app_bar_cancel:
                postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_FACE_TAG_CANCEL);
                ((EditCreatureNamePresenter) this.mPresenter).onNegativeClicked();
                return true;
            case R.id.menu_app_bar_save:
                postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_FACE_TAG_SAVE);
                ((EditCreatureNamePresenter) this.mPresenter).onPositiveClicked();
                return true;
            default:
                return true;
        }
    }

    private void setChangeCoverView(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.change_creature_cover_icon);
        this.mChangeCreatureCoverView = imageView;
        imageView.setVisibility(0);
        this.mChangeCreatureCoverView.setOnClickListener(new b(this, 3));
    }

    private void setContactLinkView(View view) {
        this.mLinkToContactBackground = view.findViewById(R.id.contact_link_view);
        this.mLinkToContactIcon = (ImageView) view.findViewById(R.id.contact_link_icon);
        this.mLinkToContactTitle = (TextView) view.findViewById(R.id.contact_link_title);
        ViewUtils.setViewShape(this.mLinkToContactBackground, 1, getResources().getDimension(R.dimen.contact_link_view_min_bg_height));
        updateContactLinkView();
    }

    private void setListVisibleOrGone(boolean z) {
        ViewUtils.setVisibleOrGone(this.mBackground, z);
        ViewUtils.setVisibleOrInvisible(this.mSuggestedNameList, z);
    }

    private void setRelationDeleteIconClickListener() {
        this.mRelationshipDeleteIcon.setOnClickListener(new b(this, 0));
    }

    private void setRelationshipViewTouchAndOnClickListener() {
        this.mRelationshipInput.setOnClickListener(new b(this, 1));
        this.mRelationView.setOnTouchListener(new i(28, this));
    }

    private void setScreenId(String str) {
        this.mScreenId = str;
    }

    /* access modifiers changed from: private */
    public void updateAutoCompleteData(ArrayList<CreatureNameData> arrayList) {
        this.mAutoCompleteAdapter.setData(arrayList);
    }

    private void updateCreatureIcon() {
        int i2;
        if (this.mCreatureIcon != null && getContext() != null) {
            ImageView imageView = this.mCreatureIcon;
            Context context = getContext();
            if (this.mIsPet) {
                i2 = R.drawable.gallery_ic_search_edit_pet_name;
            } else {
                i2 = R.drawable.gallery_ic_search_edit_contacts_name;
            }
            imageView.setImageDrawable(context.getDrawable(i2));
            updateCreatureIconTint();
        }
    }

    /* access modifiers changed from: private */
    public void updateCreatureIconTint() {
        updateIconTint(this.mCreatureIcon, !TextUtils.isEmpty(this.mEditTextView.getText()));
    }

    private void updateDropdownHeight() {
        int i2;
        Rect rect = new Rect();
        this.mEditTextView.getGlobalVisibleRect(rect);
        int height = ViewUtils.getHeight(this.mMainLayout) - WindowUtils.getIMEInsetsBottom(this.mEditTextView.getRootWindowInsets());
        if (ViewUtils.isVisible(this.mDividerButtonLayoutContainer)) {
            i2 = this.mDividerButtonLayoutContainer.getHeight();
        } else {
            i2 = 0;
        }
        this.mEditTextView.setDropDownHeight(Math.max((height - i2) - rect.bottom, 0));
    }

    private void updateFaceLayout() {
        if (getContext() != null) {
            if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
                ViewMarginUtils.setTopMargin(this.mEditCreatureNameLayout, getResources().getDimensionPixelOffset(R.dimen.search_edit_creature_header_top_margin_v85));
                ViewUtils.setViewShape(this.mFaceView, 1, (float) getResources().getDimensionPixelOffset(R.dimen.search_edit_creature_header_item_radius));
                return;
            }
            ViewMarginUtils.setTopMargin(this.mEditCreatureNameLayout, getResources().getDimensionPixelOffset(R.dimen.search_edit_creature_header_top_margin));
            ViewUtils.setWidth(this.mFaceViewLayout, getResources().getDimensionPixelOffset(R.dimen.search_edit_creature_header_item_size));
            ViewUtils.setHeight(this.mFaceViewLayout, getResources().getDimensionPixelOffset(R.dimen.search_edit_creature_header_item_size));
            ViewUtils.setForeground(this.mFaceView, getContext().getDrawable(R.drawable.people_header_stroke));
            ViewUtils.setBackground(this.mFaceView, getContext().getDrawable(R.drawable.circular_thumb_background));
        }
    }

    private void updateIconTint(ImageView imageView, boolean z) {
        if (imageView == null) {
            return;
        }
        if (z) {
            imageView.getDrawable().setTint(getContext().getColor(R.color.person_icon_color));
        } else {
            imageView.setImageTintList((ColorStateList) null);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateLayoutBottomPadding */
    public void lambda$initSyncWithInsetsAnimation$3(WindowInsets windowInsets) {
        if (getContext() != null) {
            int iMEInsetsBottom = WindowUtils.getIMEInsetsBottom(windowInsets);
            ViewMarginUtils.setBottomPadding(this.mMainLayout, WindowUtils.getSystemInsetsBottom(windowInsets) + iMEInsetsBottom);
        }
    }

    private void updateMainLayout(WindowInsets windowInsets) {
        if (isInMultiWindowMode() || this.mIsLandScape) {
            ViewMarginUtils.setTopPadding(this.mMainLayout, WindowUtils.getSystemInsetsTop(windowInsets));
        }
        ViewMarginUtils.setStartPadding(this.mMainLayout, WindowUtils.getSystemInsetsStart(windowInsets));
        ViewMarginUtils.setEndPadding(this.mMainLayout, WindowUtils.getSystemInsetsEnd(windowInsets));
    }

    private void updateMainLayoutSideSpacing() {
        NestedScrollView nestedScrollView = this.mScrollView;
        if (nestedScrollView != null) {
            ViewUtils.setMainLayoutFlexibleSideSpacing((View) nestedScrollView, (Activity) getActivity());
        }
    }

    private void updateMenuVisibility() {
        if (this.mIsLandScape) {
            ViewUtils.setVisibility(this.mDividerButtonLayoutContainer, 8);
            ViewUtils.setVisibility(this.mFloatingToolbarLayout, 0);
        } else {
            ViewUtils.setVisibility(this.mDividerButtonLayoutContainer, 0);
            ViewUtils.setVisibility(this.mFloatingToolbarLayout, 8);
        }
        enableSaveButton();
    }

    private void updateRelationShipDeleteIconVisible() {
        ViewUtils.setVisibleOrGone(this.mRelationshipDeleteIcon, !TextUtils.isEmpty(this.mRelationView.getText()));
    }

    private void updateRelationShipIconTint() {
        updateIconTint(this.mRelationshipIcon, !TextUtils.isEmpty(this.mRelationView.getText()));
    }

    private void updateScrollViewPadding(WindowInsets windowInsets) {
        int i2;
        int i7;
        int i8;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mScrollView.getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mBackground.getLayoutParams();
        int i10 = 0;
        if (this.mIsLandScape) {
            i2 = SystemUi.getToolBarHeightWithPadding(getContext());
        } else {
            i2 = 0;
        }
        layoutParams.topMargin = i2;
        if (this.mIsLandScape) {
            i7 = SystemUi.getToolBarHeightWithPadding(getContext());
        } else {
            i7 = 0;
        }
        layoutParams2.topMargin = i7;
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.search_edit_creature_divider_menu_top_margin) + getResources().getDimensionPixelOffset(R.dimen.search_edit_creature_divider_menu_bottom_margin) + WindowUtils.getSystemInsetsBottom(windowInsets) + this.mDividerButtonLayoutContainer.getHeight();
        NestedScrollView nestedScrollView = this.mScrollView;
        if (this.mIsLandScape) {
            i8 = 0;
        } else {
            i8 = dimensionPixelOffset;
        }
        ViewMarginUtils.setBottomPadding(nestedScrollView, i8);
        ImageView imageView = this.mBackground;
        if (!this.mIsLandScape) {
            i10 = dimensionPixelOffset;
        }
        ViewMarginUtils.setBottomPadding(imageView, i10);
    }

    /* access modifiers changed from: private */
    public void updateTaggedCreatureNameData(ArrayList<CreatureNameData> arrayList) {
        this.mTaggedCreatureNameList = arrayList;
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mMainLayout = view.findViewById(R.id.main_layout);
        NestedScrollView nestedScrollView = (NestedScrollView) view.findViewById(R.id.edit_creature_scroll_view);
        this.mScrollView = nestedScrollView;
        nestedScrollView.seslSetFadingEdgeEnabled(true, true);
        this.mEditCreatureNameLayout = (LinearLayout) view.findViewById(R.id.edit_creature_name_layout);
        this.mBackground = (ImageView) view.findViewById(R.id.background_layer);
        createHeaderView(view);
        createEditTextView(view);
        createEditRelationshipView(view);
        createSuggestionNameView();
        createFrequentlyContactView();
        initToolbar(view);
        initDividerButtonLayout(view);
        updateMainLayoutSideSpacing();
        updateFaceLayout();
        setRelationshipViewTouchAndOnClickListener();
        setRelationDeleteIconClickListener();
        if (PreferenceFeatures.OneUi8x.SEARCH_CREATURE_COVER_CHOICE && !((Boolean) this.mBlackboard.read("data://user/faceCluster/isFromFaceMerge", Boolean.FALSE)).booleanValue()) {
            setChangeCoverView(view);
        }
        if (((EditCreatureNamePresenter) this.mPresenter).supportContactLink()) {
            setContactLinkView(view);
        }
    }

    public String getCurrentName() {
        AutoCompleteTextView autoCompleteTextView = this.mEditTextView;
        if (autoCompleteTextView != null) {
            return String.valueOf(autoCompleteTextView.getText());
        }
        return null;
    }

    public int getLayoutId() {
        return R.layout.edit_creature_name_fragment;
    }

    public String getScreenId() {
        return this.mScreenId;
    }

    public void handleResolutionChange(int i2) {
        updateMainLayoutSideSpacing();
        updateFaceLayout();
    }

    public boolean isCreatureNameChanged(String str) {
        return !TextUtils.equals(str, this.mOldCreatureName);
    }

    public boolean isRelationshipChanged(String str) {
        return !TextUtils.equals(str, this.mOldRelationship);
    }

    public boolean isTaggedName(String str) {
        if (this.mAutoCompleteAdapter.hasSameTaggedName(str) || this.mTaggedCreatureNameList.stream().anyMatch(new C0392b(str, 25))) {
            return true;
        }
        return false;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        updateMainLayout(windowInsets);
        updateScrollViewPadding(windowInsets);
        updateDropdownHeight();
        return windowInsets;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mIsPet = !((EditCreatureNamePresenter) this.mPresenter).isPeople();
    }

    public boolean onBackPressed() {
        if (((Boolean) getBlackboard().pop("data://user/faceCluster/isFromFaceMerge", Boolean.FALSE)).booleanValue()) {
            getBlackboard().post("data://user/faceCluster/assignedIdentityInfo", (Object) null);
        }
        return super.onBackPressed();
    }

    public void onCancel(DialogInterface dialogInterface) {
        ((EditCreatureNamePresenter) this.mPresenter).publishNegativeResult();
    }

    public void onConfigurationChanged(Configuration configuration) {
        boolean z;
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            z = true;
        } else {
            z = false;
        }
        if (z != this.mIsLandScape) {
            this.mIsLandScape = z;
            updateMenuVisibility();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        CustomRelationshipKeySet.getInstance().clearCloneData();
    }

    public void onDestroyView() {
        ViewUtils.setVisibility(this.mDividerButtonLayoutContainer, 8);
        this.mEditTextView.removeTextChangedListener(this.mTextWatcher);
        super.onDestroyView();
    }

    public void onHeaderChanged() {
        this.mIsDoneButtonEnabled = true;
        enableSaveButton();
    }

    public void onItemClicked(CreatureNameData creatureNameData) {
        if (creatureNameData.getContactType() == CreatureNameData.ContactType.CONTACT || creatureNameData.getContactType() == CreatureNameData.ContactType.FREQUENTLY_CONTACT) {
            setScreenId(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_FACE_CONTACT_LIST.toString());
            postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_FACE_CONTACT_SUGGESTED_NAME);
        } else {
            setScreenId(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_FACE_SUGGESTION_LIST.toString());
            postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_FACE_SUGGESTED_NAME);
        }
        ((EditCreatureNamePresenter) this.mPresenter).updateNameData(creatureNameData);
    }

    public void onRelationshipClicked() {
        if (PreferenceFeatures.OneUi7x.REDUCED_RELATIONSHIP_TYPE && !((Boolean) getBlackboard().read("data:///CustomRelationshipRefresh", Boolean.FALSE)).booleanValue()) {
            getBlackboard().publish("data:///CustomRelationshipRefresh", Boolean.TRUE);
            CustomRelationshipKeySet.getInstance().updatePreference();
        }
        CustomRelationshipKeySet.getInstance().createCloneData();
        ((EditCreatureNamePresenter) this.mPresenter).onRelationshipClicked();
    }

    public void onResume() {
        super.onResume();
        if (!((EditCreatureNamePresenter) this.mPresenter).isDialogShowing()) {
            this.mEditTextView.requestFocus();
            updateCreatureIcon();
            getActivity().getWindow().setSoftInputMode(48);
            this.mEditTextView.postDelayed(new c(this, 1), 300);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        boolean z;
        super.onViewCreated(view, bundle);
        if (getResources().getConfiguration().orientation == 2) {
            z = true;
        } else {
            z = false;
        }
        this.mIsLandScape = z;
        updateMenuVisibility();
        initSyncWithInsetsAnimation();
    }

    public void registerWindowInsetListener(List<View> list) {
        list.add(this.mMainLayout);
    }

    public void setHeaderImage(Bitmap bitmap) {
        ImageView imageView = this.mFaceView;
        if (imageView != null) {
            imageView.post(new C0199b(26, this, bitmap));
        }
    }

    public void updateContactLinkView() {
        int i2;
        int i7;
        Boolean isContactLinked = ((EditCreatureNamePresenter) this.mPresenter).isContactLinked();
        if (isContactLinked == null) {
            ViewUtils.setVisibility(this.mLinkToContactBackground, 8);
            return;
        }
        ViewUtils.setVisibility(this.mLinkToContactBackground, 0);
        if (getContext() != null) {
            ImageView imageView = this.mLinkToContactIcon;
            Context context = getContext();
            if (isContactLinked.booleanValue()) {
                i2 = R.drawable.ic_unlink;
            } else {
                i2 = R.drawable.ic_link;
            }
            imageView.setImageDrawable(context.getDrawable(i2));
            TextView textView = this.mLinkToContactTitle;
            if (isContactLinked.booleanValue()) {
                i7 = R.string.unlink_from_contact;
            } else {
                i7 = R.string.link_to_contact;
            }
            textView.setText(i7);
            this.mLinkToContactBackground.setOnClickListener(new b(this, 2));
        }
    }

    public void updateCreatureName(String str) {
        this.mEditTextView.setText(str);
        AutoCompleteTextView autoCompleteTextView = this.mEditTextView;
        autoCompleteTextView.setSelection(autoCompleteTextView.getText().length());
    }

    public void updateRelationship(String str) {
        this.mIsDoneButtonEnabled = true;
        enableSaveButton();
        this.mRelationView.setText(str);
        updateRelationShipIconTint();
        updateRelationShipDeleteIconVisible();
    }

    public EditCreatureNamePresenter createPresenter(IEditCreatureNameView iEditCreatureNameView) {
        return new EditCreatureNamePresenter(getBlackboard(), iEditCreatureNameView);
    }

    public void initView(View view) {
    }
}
