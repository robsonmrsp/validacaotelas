package com.far.eventmanagerpanels.domain;

import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Evento {

    @NotNull
    @Size(min = 3)
    private String nome;

    @Size(min = 3)
    private String descricao;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date dataHoraInicio;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date dataHoraFim;

    @ManyToOne
    private TipoEvento tipoEvento;

    @ManyToOne
    private Painel painel;

    @NotNull
    @Size(min = 3)
    private String imagemLogo;

    @NotNull
    @Size(min = 3)
    private String imagemPlanoFundo;
}
