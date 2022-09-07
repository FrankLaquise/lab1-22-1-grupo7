package pe.edu.pucp.gtics.lab1221.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.pucp.gtics.lab1221.entity.Distribuidoras;
import pe.edu.pucp.gtics.lab1221.repository.DistributorsRepository;

import java.util.List;
import java.util.Optional;

public class DistributorsController {
    @Autowired
    DistributorsRepository distributorsRepository;
    @GetMapping("/lista")
    public String listaDistribuidoras (Model model){
        List<Distribuidoras> lista=distributorsRepository.findAll(Sort.by("nombre"));
        model.addAllAttributes("lista",lista);

        return "distribuidoras/lista";

    };
    @GetMapping(value={"/editar"})
    public String editarDistribuidoras(Model model, @RequestParam("id") int id){

        Optional<Distribuidoras> optDistribuidoras = distributorsRepository.findById(id);
        if (optDistribuidoras.isPresent()) {
            Distribuidoras distribuidoras = optDistribuidoras.get();
            model.addAttribute("distribuidoras", distribuidoras);
            return "distribuidoras/editar";
        } else {
            return "redirect:/distribuidoras/lista";
        }
    }

    @GetMapping(value={"/nuevo"})
    public String nuevaDistribuidora(){


        return "/distribuidoras/nuevo";
    }


    @PostMapping(value={"/guardar"})
    public String guardarDistribuidora(Distribuidoras distribuidoras){

        distributorsRepository.save(distribuidoras);
        return "redirect:/distribuidoras/lista";

    }
    public String borrarDistribuidora(){
        return "";
    };

}
