package tacos;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient.Type;

@Slf4j
@SpringBootTest
class DemoApplicationTests {
	public enum aenum{banana,huasheng,qiezi}
	@Test
	void contextLoads() {
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
			List<Ingredient> ingredientList=filterByType(ingredients, type);
			log.info(ingredientList.toString());
		}
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

}
