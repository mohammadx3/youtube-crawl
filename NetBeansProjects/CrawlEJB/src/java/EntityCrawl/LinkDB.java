/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityCrawl;

import PersistanceCrawl.PersistCrawl;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Mohammad Abbas
 */
@Stateless
public class LinkDB implements LinkDBLocal {
    @PersistenceContext(unitName = "CrawlEJBPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
public void add(PersistCrawl PerCraw){
    persist(PerCraw);
}
    
    
}
