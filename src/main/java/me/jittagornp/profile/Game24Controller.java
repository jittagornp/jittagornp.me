/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.jittagornp.profile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import me.jittagornp.profile.game24.Equations;
import static org.springframework.util.StringUtils.hasText;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jitta
 */
@RestController
public class Game24Controller {

    private final Map<String, Set<String>> cached = new HashMap<>();

    private boolean isValidInput(String q) {
        return hasText(q)
                && q.length() == 4
                && q.matches("^[\\d]+$");
    }

    @GetMapping("/game24")
    public Set<String> getResult(@RequestParam(value = "q", required = false) String q, HttpServletResponse httpResp) throws IOException {
        if (!isValidInput(q)) {
            httpResp.sendError(HttpServletResponse.SC_BAD_REQUEST, "require query string \"q\" as a number 4 digit.");
            return null;
        }
        Set<String> result = cached.get(q);
        if (result == null) {
            result = Equations.withAnswer(24).andNumbers(q).get();
            cached.put(q, result);
        }
        return result;
    }

}
