/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.jittagornp.profile;

import java.util.Set;
import me.jittagornp.profile.game24.Equations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jitta
 */
@RestController
public class Game24Controller {

    @GetMapping("/game24")
    public Set<String> getResult(@RequestParam("q") String q) {
        return Equations.withAnswer(24).andNumbers(q).get();
    }

}
