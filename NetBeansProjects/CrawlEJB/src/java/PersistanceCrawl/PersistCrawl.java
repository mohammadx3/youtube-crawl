/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistanceCrawl;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mohammad Abbas
 */
@Entity
public class PersistCrawl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
private long ViewCount;
private String VideoID;

    public String getVideoID() {
        return VideoID;
    }

    public void setVideoID(String VideoID) {
        this.VideoID = VideoID;
    }

    public String getVideoTitle() {
        return VideoTitle;
    }

    public void setVideoTitle(String VideoTitle) {
        this.VideoTitle = VideoTitle;
    }

    public long getViewCount() {
        return ViewCount;
    }

    public void setViewCount(long ViewCount) {
        this.ViewCount = ViewCount;
    }
private String VideoTitle;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersistCrawl)) {
            return false;
        }
        PersistCrawl other = (PersistCrawl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersistanceCrawl.NewEntity[ id=" + id + " ]";
    }
    
}
