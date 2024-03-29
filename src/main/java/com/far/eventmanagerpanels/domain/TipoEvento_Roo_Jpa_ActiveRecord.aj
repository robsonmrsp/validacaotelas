// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.far.eventmanagerpanels.domain;

import com.far.eventmanagerpanels.domain.TipoEvento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TipoEvento_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager TipoEvento.entityManager;
    
    public static final EntityManager TipoEvento.entityManager() {
        EntityManager em = new TipoEvento().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long TipoEvento.countTipoEventoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM TipoEvento o", Long.class).getSingleResult();
    }
    
    public static List<TipoEvento> TipoEvento.findAllTipoEventoes() {
        return entityManager().createQuery("SELECT o FROM TipoEvento o", TipoEvento.class).getResultList();
    }
    
    public static TipoEvento TipoEvento.findTipoEvento(Long id) {
        if (id == null) return null;
        return entityManager().find(TipoEvento.class, id);
    }
    
    public static List<TipoEvento> TipoEvento.findTipoEventoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM TipoEvento o", TipoEvento.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void TipoEvento.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void TipoEvento.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            TipoEvento attached = TipoEvento.findTipoEvento(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void TipoEvento.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void TipoEvento.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public TipoEvento TipoEvento.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        TipoEvento merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
