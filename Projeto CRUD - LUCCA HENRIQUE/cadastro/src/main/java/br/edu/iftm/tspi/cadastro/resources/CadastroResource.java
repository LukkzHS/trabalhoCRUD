package br.edu.iftm.tspi.cadastro.resources;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import br.edu.iftm.tspi.cadastro.dto.CadastroDTO;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class CadastroResource {

    private List<CadastroDTO> cadastros = new ArrayList<>();
    
    @GetMapping("/cadastro")
    public String showForm(Model model) {
        model.addAttribute("cadastro", new CadastroDTO());
        return "cadastro";
    }
    
    @PostMapping("/cadastroPost")
    public String create(@ModelAttribute CadastroDTO dto, Model model) {
        cadastros.add(dto);
        return "redirect:/cadastroGet";
    }

    @GetMapping("/cadastroGet")
    public String list(Model model) {
        model.addAttribute("cadastros", cadastros);
        return "lista";
    }

    @GetMapping("/editar/{id}")
    public String edit(@PathVariable Long id, Model model) {
        CadastroDTO cadastro = cadastros.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    
        if (cadastro != null) {
            model.addAttribute("cadastro", cadastro);
            return "editar"; 
        } else {
            return "redirect:/cadastroGet";
        }
    }

    @PostMapping("/editar")
    public String update(@ModelAttribute CadastroDTO dto, Model model) {
    
    for (int i = 0; i < cadastros.size(); i++) {
        CadastroDTO cadastro = cadastros.get(i);
        if (cadastro.getId().equals(dto.getId())) {
            cadastro.setEmail(dto.getEmail());
            cadastro.setFirstName(dto.getFirstName());
            cadastro.setLastName(dto.getLastName());
            break;
        }
    }

    return "redirect:/cadastroGet";
}
    
    @GetMapping("/excluir/{id}")
    public String delete(@PathVariable Long id) {
        cadastros.removeIf(c -> c.getId().equals(id));
        return "redirect:/cadastroGet";
    }
}
