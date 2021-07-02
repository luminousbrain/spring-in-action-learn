package ru.gontarenko.tacojdbc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.gontarenko.tacojdbc.entity.Ingredient;
import ru.gontarenko.tacojdbc.entity.Ingredient.Type;
import ru.gontarenko.tacojdbc.entity.Order;
import ru.gontarenko.tacojdbc.entity.Taco;
import ru.gontarenko.tacojdbc.repository.IngredientRepository;
import ru.gontarenko.tacojdbc.repository.TacoRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@SessionAttributes("order")
@RequestMapping("/design")
public class DesignTacoController {
    private final TacoRepository tacoRepo;
    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
    }

    // todo Потестить без этого
    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute()
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = (List<Ingredient>) ingredientRepo.findAll();
        for (Type type : Type.values()) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("tacoDesign", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(
            @Valid @ModelAttribute("tacoDesign") Taco tacoDesign,
            Errors errors, @ModelAttribute Order order
    ) {
        log.debug("Provided taco: " + tacoDesign.toString());
        if (errors.hasErrors()) {
            return "design";
        }
        Taco saved = tacoRepo.save(tacoDesign);
        order.addTaco(saved);
        log.info("Saved taco design into database: " + tacoDesign);
        return "redirect:/order/current";
    }
}