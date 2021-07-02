package ru.gontarenko.tacojpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.gontarenko.tacojpa.entity.Ingredient;
import ru.gontarenko.tacojpa.entity.Ingredient.Type;
import ru.gontarenko.tacojpa.entity.Order;
import ru.gontarenko.tacojpa.entity.Taco;
import ru.gontarenko.tacojpa.repository.IngredientRepository;
import ru.gontarenko.tacojpa.repository.TacoRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("order")
public class TacoController {
    private final TacoRepository tacoRepository;
    private final IngredientRepository ingredientRepository;

    @ModelAttribute("order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute("tacoDesign")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute("ingredients")
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = (List<Ingredient>) ingredientRepository.findAll();
        for (Type type : Type.values()) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

    @Autowired
    public TacoController(TacoRepository tacoRepository, IngredientRepository ingredientRepository) {
        this.tacoRepository = tacoRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping(value = {"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/design")
    public String designForm() {
        return "design";
    }

    @PostMapping("/design")
    public String processDesign(@Valid Taco taco, Errors errors, Order order) {
        if (errors.hasErrors()) {
            return "design";
        }

        tacoRepository.save(taco);
        order.addTaco(taco);
        return "redirect:/order/current";
    }
}