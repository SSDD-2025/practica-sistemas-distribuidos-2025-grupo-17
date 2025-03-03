package es.codeurjc.web.controller;

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
        // Verifica si el código de estado es 404 (Not Found)
        if (statusCode != null && statusCode == 404) {
            if ("cast".equals(resource)) {
                model.addAttribute("errorMessage", "El miembro del elenco no fue encontrado");
                return "castNotFound_template";
            } else {
                model.addAttribute("errorMessage", "La película no fue encontrada");
                return "movieNotFound_template";
            }
        }

        // Maneja otros errores
        model.addAttribute("errorMessage", "Ocurrió un error inesperado");
        return "error_template";
    }
}