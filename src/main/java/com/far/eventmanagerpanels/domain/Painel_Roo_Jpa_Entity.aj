// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.far.eventmanagerpanels.domain;

import com.far.eventmanagerpanels.domain.Painel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Painel_Roo_Jpa_Entity {
    
    declare @type: Painel: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Painel.id;
    
    @Version
    @Column(name = "version")
    private Integer Painel.version;
    
    public Long Painel.getId() {
        return this.id;
    }
    
    public void Painel.setId(Long id) {
        this.id = id;
    }
    
    public Integer Painel.getVersion() {
        return this.version;
    }
    
    public void Painel.setVersion(Integer version) {
        this.version = version;
    }
    
}
