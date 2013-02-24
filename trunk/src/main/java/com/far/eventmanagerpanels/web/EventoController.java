package com.far.eventmanagerpanels.web;

import com.far.eventmanagerpanels.domain.Evento;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/eventoes")
@Controller
@RooWebScaffold(path = "eventoes", formBackingObject = Evento.class)
public class EventoController {
}
