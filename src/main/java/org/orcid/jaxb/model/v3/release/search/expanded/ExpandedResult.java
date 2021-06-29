package org.orcid.jaxb.model.v3.release.search.expanded;

import java.io.Serializable;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.orcid.jaxb.model.v3.release.common.FuzzyDate;

import io.swagger.annotations.ApiModel;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "orcidId", "givenNames", "familyNames", "creditName", "otherNames", "emails", "institutionNames",
		"lastModifiedDate" })
@ApiModel(value = "ExpandedResultV3_0")
public class ExpandedResult implements Serializable {

	private static final long serialVersionUID = -7750679507838375771L;

	@XmlElement(name = "orcid-id", namespace = "http://www.orcid.org/ns/expanded-search")
	protected String orcidId;

	@XmlElement(name = "given-names", namespace = "http://www.orcid.org/ns/expanded-search")
	protected String givenNames;

	@XmlElement(name = "family-names", namespace = "http://www.orcid.org/ns/expanded-search")
	protected String familyNames;

	@XmlElement(name = "credit-name", namespace = "http://www.orcid.org/ns/expanded-search")
	protected String creditName;

	@XmlElement(name = "other-name", namespace = "http://www.orcid.org/ns/expanded-search")
	protected String[] otherNames;

	@XmlElement(name = "email", namespace = "http://www.orcid.org/ns/expanded-search")
	protected String[] emails;

	@XmlElement(name = "institution-name", namespace = "http://www.orcid.org/ns/expanded-search")
	protected String[] institutionNames;

	@XmlElement(name = "last-modified-date", namespace = "http://www.orcid.org/ns/expanded-search")
	protected FuzzyDate lastModifiedDate;

	public String getOrcidId() {
		return orcidId;
	}

	public void setOrcidId(String orcidId) {
		this.orcidId = orcidId;
	}

	public String getGivenNames() {
		return givenNames;
	}

	public void setGivenNames(String givenNames) {
		this.givenNames = givenNames;
	}

	public String getFamilyNames() {
		return familyNames;
	}

	public void setFamilyNames(String familyNames) {
		this.familyNames = familyNames;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String[] getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(String[] otherNames) {
		this.otherNames = otherNames;
	}

	public String[] getEmails() {
		return emails;
	}

	public void setEmails(String[] emails) {
		this.emails = emails;
	}

	public String[] getInstitutionNames() {
		return institutionNames;
	}

	public void setInstitutionNames(String[] institutionNames) {
		this.institutionNames = institutionNames;
	}

	public FuzzyDate getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(FuzzyDate lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creditName == null) ? 0 : creditName.hashCode());
		result = prime * result + Arrays.hashCode(emails);
		result = prime * result + ((familyNames == null) ? 0 : familyNames.hashCode());
		result = prime * result + ((givenNames == null) ? 0 : givenNames.hashCode());
		result = prime * result + Arrays.hashCode(institutionNames);
		result = prime * result + ((lastModifiedDate == null) ? 0 : lastModifiedDate.hashCode());
		result = prime * result + ((orcidId == null) ? 0 : orcidId.hashCode());
		result = prime * result + Arrays.hashCode(otherNames);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExpandedResult other = (ExpandedResult) obj;
		if (creditName == null) {
			if (other.creditName != null)
				return false;
		} else if (!creditName.equals(other.creditName))
			return false;
		if (!Arrays.equals(emails, other.emails))
			return false;
		if (familyNames == null) {
			if (other.familyNames != null)
				return false;
		} else if (!familyNames.equals(other.familyNames))
			return false;
		if (givenNames == null) {
			if (other.givenNames != null)
				return false;
		} else if (!givenNames.equals(other.givenNames))
			return false;
		if (!Arrays.equals(institutionNames, other.institutionNames))
			return false;
		if (lastModifiedDate == null) {
			if (other.lastModifiedDate != null)
				return false;
		} else if (!lastModifiedDate.equals(other.lastModifiedDate))
			return false;
		if (orcidId == null) {
			if (other.orcidId != null)
				return false;
		} else if (!orcidId.equals(other.orcidId))
			return false;
		if (!Arrays.equals(otherNames, other.otherNames))
			return false;
		return true;
	}

}
