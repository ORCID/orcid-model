package org.orcid.jaxb.model.statistics;

import java.io.Serializable;
import java.util.Map;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Represents a timeline of statistics.
 * 
 * @author tom
 *
 */
@XmlRootElement(name = "statistics-timeline")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatisticsTimeline implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "statistic-name")
    protected String statisticName;
    protected Map<Long, Long> timeline;

    public String getStatisticName() {
        return statisticName;
    }

    public void setStatisticName(String name) {
        this.statisticName = name;
    }

    public Map<Long, Long> getTimeline() {
        return timeline;
    }

    public void setTimeline(Map<Long, Long> timeline) {
        this.timeline = timeline;
    }
}
