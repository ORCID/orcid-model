package org.orcid.jaxb.model.v3.release.record.summary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "educations", namespace = "http://www.orcid.org/ns/activities")
@Schema(description = "EducationsSummaryV3_0")
public class Educations extends Affiliations<EducationSummary> implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3333110263222877435L;

    public Educations() {

    }

    public Educations(Collection<AffiliationGroup<EducationSummary>> groups) {
        super();
        this.groups = groups;
    }

    public Collection<AffiliationGroup<EducationSummary>> getEducationGroups() {
        if (this.groups == null) {
            this.groups = new ArrayList<AffiliationGroup<EducationSummary>>();
        }
        return (Collection<AffiliationGroup<EducationSummary>>) this.groups;
    }

    @Override
    public Collection<AffiliationGroup<EducationSummary>> retrieveGroups() {
        return getEducationGroups();
    }
}
