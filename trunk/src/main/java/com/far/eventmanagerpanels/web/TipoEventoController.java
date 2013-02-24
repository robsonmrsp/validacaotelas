package com.far.eventmanagerpanels.web;

import com.far.eventmanagerpanels.domain.TipoEvento;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tipoeventoes")
@Controller
@RooWebScaffold(path = "tipoeventoes", formBackingObject = TipoEvento.class)
public class TipoEventoController {
}
