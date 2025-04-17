package es.codeurjc.web.controller.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(@RequestParam(name = "status", required = false) Integer statusCode,
            @RequestParam(name = "resource", required = false) String resource,
            Model model) {
        // Not Found errors
        if (statusCode != null && statusCode == 404) {
            if ("cast".equals(resource)) {
                model.addAttribute("errorMessage", "El miembro del elenco no fue encontrado");
                return "castNotFound_template";
            } else {
                model.addAttribute("errorMessage", "La película no fue encontrada");
                return "movieNotFound_template";
            }
        //Forbidden errors
        } else if (statusCode != null && statusCode == 403) {
            if (resource != null) {
                model.addAttribute("errorMessage", resource);
            } else {
                model.addAttribute("errorMessage",
                        "Lo sentimos, pero no tienes el permiso necesario para realizar la acción o ir a la página");
            }
            return "error_template";
        }

        // Other errors
        model.addAttribute("errorMessage", "Lo sentimos, pero la página que estás buscando no existe.");
        return "error_template";
    }
}