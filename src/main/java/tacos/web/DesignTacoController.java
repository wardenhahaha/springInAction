package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.Ingredient.Type;
import tacos.Order;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	
	private final IngredientRepository ingredientRepo;

	private TacoRepository designRepo;
	
	@ModelAttribute(name="order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name="taco")
	public Taco taco() {
		return new Taco();
	}
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo,TacoRepository designRepo) {
		this.ingredientRepo = ingredientRepo;
		this.designRepo=designRepo;
	}
	
	private List<Ingredient> filterByType(List<Ingredient> list,Type type) {
		List<Ingredient> returnList=list.stream().filter(new Predicate<Ingredient>() {
			@Override
			public boolean test(Ingredient t) {
				if(t.getType()==type)
					return true;
				else
					return false;
			}
		}).collect(Collectors.toList());
		return returnList;
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		
		List<Ingredient> ingredients=new ArrayList<>();
		ingredientRepo.findAll().forEach(i->ingredients.add(i));
		
		Type[] types=Ingredient.Type.values();
		for(Type type:types) {
			model.addAttribute(type.toString().toLowerCase(),
			filterByType(ingredients, type));
			
			model.addAttribute("design",new Taco());
		}
		
		return "design";
		
		/*
		//hardcode数据
		List<Ingredient> ingredients=Arrays.asList(
			new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
			new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
			new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
			new Ingredient("CARN", "Carnitas", Type.PROTEIN),
			new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
			new Ingredient("LETC", "Lettuce", Type.VEGGIES),
			new Ingredient("CHED", "Cheddar", Type.CHEESE),
			new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
			new Ingredient("SLSA", "Salsa", Type.SAUCE),
			new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		);
		Type[] types=Type.values();
		for(Type type:types) {
			model.addAttribute(type.toString().toLowerCase(),
					filterByType(ingredients, type));
			
			model.addAttribute("design",new Taco());
		}
		return "design";*/
	}
	
	@PostMapping
	public String processDesign(@ModelAttribute("design") Taco design,Errors errors,@ModelAttribute Order order) {
		if(errors.hasErrors()) {
			log.info("error!!~!"+errors.getFieldError().getField());
			return "design";
		}
		
		Taco saved = designRepo.save(design);
		order.addDesign(saved);
		
		log.info("Processing design: " + design.getIngredients().toString());
		return "redirect:/orders/current";
	}
}
