package com.samsung.android.gallery.app.ui.list.search.category;

import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MenuFactory {
    public static MenuDataBinding create(String str) {
        if (str == null) {
            return null;
        }
        if (LocationKey.isSearchCategoryPeople(str) || LocationKey.isSearchCategoryPeopleAndPets(str) || LocationKey.isSearchCategoryCreatureSelect(str) || LocationKey.isSearchCategoryHiddenPeopleMatch(str) || LocationKey.isSearchCategoryHiddenPeopleAndPetsMatch(str)) {
            return createSearchCategoryPeopleMenu(str);
        }
        if (LocationKey.isSelectMe(str)) {
            return createSelectMeMenu();
        }
        if (LocationKey.isSearchCategoryPeopleSelectForRelation(str)) {
            return createSuggestedCreatureSelectMenu();
        }
        if (LocationKey.isSearchCategoryLocation(str)) {
            return createCategoryLocationMenu();
        }
        if (LocationKey.isSearchCategoryPeopleSelectForRelation(str)) {
            return createCategorySuggestedCreatureSelectMenu();
        }
        if (LocationKey.isSearchCategoryDocuments(str) || LocationKey.isSearchCategoryShotMode(str)) {
            return createCategoryDocumentsMenu();
        }
        if (LocationKey.isSearchCategoryMyQuery(str)) {
            return createCategoryMyQueryMenu();
        }
        return null;
    }

    private static MenuDataBinding createCategoryDocumentsMenu() {
        return new MenuDataBinding(R.menu.menu_search_category_documents);
    }

    private static MenuDataBinding createCategoryLocationMenu() {
        return new MenuDataBinding(R.menu.menu_search_category_location);
    }

    private static MenuDataBinding createCategoryMyQueryMenu() {
        return new MenuDataBinding(R.menu.menu_search_category_myquery);
    }

    private static MenuDataBinding createCategorySuggestedCreatureSelectMenu() {
        return new MenuDataBinding(R.menu.menu_search_category_suggested_creature_select);
    }

    private static MenuDataBinding createSearchCategoryPeopleMenu(final String str) {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_search_category_people);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_select) {
            public boolean getDefaultVisibility() {
                if (!PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE || !LocationKey.isSearchCategoryPeopleAndPets(str)) {
                    return false;
                }
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_hide_creature_bottom) {
            public int getDefaultIconResId() {
                if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                    return super.getDefaultIconResId();
                }
                return R.drawable.ic_gallery_ic_stories_bottom_bar_hide;
            }

            public String getDefaultTitle() {
                if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                    return super.getDefaultTitle();
                }
                return AppResources.getString(R.string.hide);
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
        return menuDataBinding;
    }

    private static MenuDataBinding createSelectMeMenu() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_search_category_select_me);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_set_as_me) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        return menuDataBinding;
    }

    private static MenuDataBinding createSuggestedCreatureSelectMenu() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_search_category_suggested_creature_select);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_set_as_relation) {
        });
        return menuDataBinding;
    }
}
