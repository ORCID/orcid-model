package org.orcid.record.v3.common;

import org.junit.Test;
import org.orcid.jaxb.model.common.PeerReviewSubjectType;
import org.orcid.jaxb.model.common.WorkType;

public class PeerReviewSubjectTypesMatchesWorkTypesTest {

	@Test
	public void allWorkTypesHaveAPeerReviewSubjectTypeMatchTest() throws Exception {
		WorkType workType = null;
		try {
			for (WorkType w : WorkType.values()) {
				workType = w;
				PeerReviewSubjectType.fromValue(w.value());
			}
		} catch (IllegalArgumentException e) {
			throw new Exception("Work type " + workType + " does not have a match in PeerReviewSubjectType enum");
		}
	}
}
