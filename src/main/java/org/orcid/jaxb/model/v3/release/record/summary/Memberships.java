package org.orcid.jaxb.model.v3.release.record.summary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "memberships", namespace = "http://www.orcid.org/ns/activities")
@Schema(description = "MembershipsV3_0")
public class Memberships extends Affiliations<MembershipSummary> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1045628720886435629L;

    public Memberships() {

    }
    
    public Memberships(Collection<AffiliationGroup<MembershipSummary>> groups) {
        super();
        this.groups = groups;
    }

    public Collection<AffiliationGroup<MembershipSummary>> getMembershipGroups() {
        if (this.groups == null) {
            this.groups = new ArrayList<AffiliationGroup<MembershipSummary>>();
        }
        return (Collection<AffiliationGroup<MembershipSummary>>) this.groups;
    }

    @Override
    public Collection<AffiliationGroup<MembershipSummary>> retrieveGroups() {
        return getMembershipGroups();
    }
}
