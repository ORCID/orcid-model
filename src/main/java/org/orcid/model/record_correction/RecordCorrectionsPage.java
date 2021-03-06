package org.orcid.model.record_correction;

import java.io.Serializable;
import java.util.List;

public class RecordCorrectionsPage implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5194569943362678877L;
    Boolean haveNext = false;
    Boolean havePrevious = false;
    Long firstElementId;
    Long lastElementId;
    List<RecordCorrection> recordCorrections;

    public Boolean getHaveNext() {
        return haveNext;
    }

    public void setHaveNext(Boolean haveNext) {
        this.haveNext = haveNext;
    }

    public Boolean getHavePrevious() {
        return havePrevious;
    }

    public void setHavePrevious(Boolean havePrevious) {
        this.havePrevious = havePrevious;
    }

    public Long getFirstElementId() {
        return firstElementId;
    }

    public void setFirstElementId(Long firstElementId) {
        this.firstElementId = firstElementId;
    }

    public Long getLastElementId() {
        return lastElementId;
    }

    public void setLastElementId(Long lastElementId) {
        this.lastElementId = lastElementId;
    }

    public List<RecordCorrection> getRecordCorrections() {
        return recordCorrections;
    }

    public void setRecordCorrections(List<RecordCorrection> recordCorrections) {
        this.recordCorrections = recordCorrections;
    }
}
