package org.orcid.jaxb.model.v3.release.record.summary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "distinctions", namespace = "http://www.orcid.org/ns/activities")
@Schema(description = "DistinctionsSummaryV3_0")
public class Distinctions extends Affiliations<DistinctionSummary> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3281485714665085184L;

    public Distinctions() {

    }

    public Distinctions(Collection<AffiliationGroup<DistinctionSummary>> groups) {
        super();
        this.groups = groups;
    }
    
    public Collection<AffiliationGroup<DistinctionSummary>> getDistinctionGroups() {
        if (this.groups == null) {
            this.groups = new ArrayList<AffiliationGroup<DistinctionSummary>>();
        }
        return (Collection<AffiliationGroup<DistinctionSummary>>) this.groups;
    }

    @Override
    public Collection<AffiliationGroup<DistinctionSummary>> retrieveGroups() {
        return getDistinctionGroups();
    }

}
