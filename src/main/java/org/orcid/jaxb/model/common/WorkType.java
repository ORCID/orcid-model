package org.orcid.jaxb.model.common;

import java.io.Serializable;

public enum WorkType implements Serializable {
    ANNOTATION("annotation"),
    ARTISTIC_PERFORMANCE("artistic-performance"),
    BLOG_POST("blog-post"),
    BOOK_CHAPTER("book-chapter"),
    BOOK_REVIEW("book-review"),
    BOOK("book"),
    CARTOGRAPHIC_MATERIAL("cartographic-material"),
    CLINICAL_STUDY("clinical-study"),
    CONFERENCE_ABSTRACT("conference-abstract"),
    CONFERENCE_OUTPUT("conference-output"),
    CONFERENCE_PAPER("conference-paper"),
    CONFERENCE_POSTER("conference-poster"),
    CONFERENCE_PRESENTATION("conference-presentation"),
    CONFERENCE_PROCEEDINGS("conference-proceedings"),
    DATA_MANAGEMENT_PLAN("data-management-plan"),
    DATA_SET("data-set"),
    DESIGN("design"),
    DICTIONARY_ENTRY("dictionary-entry"),
    DISCLOSURE("disclosure"),
    DISSERTATION_THESIS("dissertation-thesis"),
    EDITED_BOOK("edited-book"),
    ENCYCLOPEDIA_ENTRY("encyclopedia-entry"),
    IMAGE("image"),
    INVENTION("invention"),
    JOURNAL_ARTICLE("journal-article"),
    JOURNAL_ISSUE("journal-issue"),
    LEARNING_OBJECT("learning-object"),
    LECTURE_SPEECH("lecture-speech"),
    LICENSE("license"),
    MAGAZINE_ARTICLE("magazine-article"),
    MANUAL("manual"),
    MOVING_IMAGE("moving-image"),
    MUSICAL_COMPOSITION("musical-composition"),
    NEWSLETTER_ARTICLE("newsletter-article"),
    NEWSPAPER_ARTICLE("newspaper-article"),
    ONLINE_RESOURCE("online-resource"),
    OTHER("other"),
    PATENT("patent"),
    PHYSICAL_OBJECT("physical-object"),
    PREPRINT("preprint"),
    REGISTERED_COPYRIGHT("registered-copyright"),
    REPORT("report"),
    RESEARCH_TECHNIQUE("research-technique"),
    RESEARCH_TOOL("research-tool"),
    REVIEW("review"),
    SOFTWARE("software"),
    SOUND("sound"),
    SPIN_OFF_COMPANY("spin-off-company"),
    STANDARDS_AND_POLICY("standards-and-policy"),
    SUPERVISED_STUDENT_PUBLICATION("supervised-student-publication"),
    TECHNICAL_STANDARD("technical-standard"),
    TEST("test"),
    TRADEMARK("trademark"),
    TRANSCRIPTION("transcription"),
    TRANSLATION("translation"),    
    WEBSITE("website"),
    WORKING_PAPER("working-paper"),
    UNDEFINED("undefined");
            
    private final String value;

    WorkType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WorkType fromValue(String v) {
        for (WorkType c : WorkType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }

        // Known maps
        switch (v) {
        case "article-journal":
            return WorkType.JOURNAL_ARTICLE;
        case "chapter":
            return WorkType.BOOK_CHAPTER;
        case "dataset":
            return WorkType.DATA_SET;
        case "standard":
            return WorkType.STANDARDS_AND_POLICY;
        }

        throw new IllegalArgumentException(v);
    }
}
