package com.samsung.android.sdk.scs.ai.language;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum SuggestionCategory {
    NOW_BRIEF_WEATHER("NOW_BRIEF_WEATHER"),
    NOW_BRIEF_INSIGHT("NOW_BRIEF_INSIGHT"),
    CLIP_QNA("CLIP_QNA"),
    TOUCH_TO_SEARCH_ENTITY_SUGGESTION("TOUCH_TO_SEARCH_ENTITY_SUGGESTION"),
    FILE_RENAME("FILE_RENAME"),
    TITLE_GENERATION("TITLE_GENERATION"),
    NOTE_TRAVEL_TIP("NOTE_TRAVEL_TIP"),
    AI_SELECT_AUTO_FILL("AI_SELECT_AUTO_FILL"),
    IMAGE_TRANSLATION_STRATEGY("IMAGE_TRANSLATION_STRATEGY"),
    FINDER_SEARCH_ANSWER_SYNTHESIS("FINDER_SEARCH_ANSWER_SYNTHESIS"),
    VOICE_RECORDER_FILENAME_AND_TAGS("VOICE_RECORDER_FILENAME_AND_TAGS"),
    CALL_DECLINE_MESSAGE("CALL_DECLINE_MESSAGE"),
    OTHERS("OTHERS");
    
    private final String name;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MultiModal {
        MYFILES_FILE_RENAME("MYFILES_FILE_RENAME");
        
        private final String name;

        private MultiModal(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    private SuggestionCategory(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
