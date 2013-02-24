// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.far.eventmanagerpanels.domain;

import com.far.eventmanagerpanels.domain.Painel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Painel_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Painel.entityManager;
    
    public static final EntityManager Painel.entityManager() {
        EntityManager em = new Painel().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Painel.countPainels() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Painel o", Long.class).getSingleResult();
    }
    
    public static List<Painel> Painel.findAllPainels() {
        return entityManager().createQuery("SELECT o FROM Painel o", Painel.class).getResultList();
    }
    
    public static Painel Painel.findPainel(Long id) {
        if (id == null) return null;
        return entityManager().find(Painel.class, id);
    }
    
    public static List<Painel> Painel.findPainelEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Painel o", Painel.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Painel.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Painel.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Painel attached = Painel.findPainel(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Painel.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Painel.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Painel Painel.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Painel merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
