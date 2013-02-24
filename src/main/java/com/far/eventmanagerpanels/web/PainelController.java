package com.far.eventmanagerpanels.web;

import com.far.eventmanagerpanels.domain.Painel;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/painels")
@Controller
@RooWebScaffold(path = "painels", formBackingObject = Painel.class)
public class PainelController {
}
