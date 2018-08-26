/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.jittagornp.profile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jittagornp &lt;http://jittagornp.me&gt; create : 2018/04/09
 */
@Controller
public class AdminTemplateCtrl {

  @GetMapping({"/portfolio/admin-template"})
  public ModelAndView getIndex(@RequestParam(value = "page", required = false, defaultValue = "index") String page) {
    ModelAndView mav = new ModelAndView("portfolio/admin-template/template");
    mav.addObject("container", "portfolio/admin-template/" + page);
    return mav;
  }

}
