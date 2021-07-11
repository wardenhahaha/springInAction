package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository{
	
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public Iterable<Ingredient> findAll() {
		return jdbc.query("SELECT id,name,type FROM Ingredient", this::mapRowToIngredient);
	}

	@Override
	public Ingredient findOne(String id) {
		return jdbc.queryForObject("select id, name, type from Ingredient where id=?",this::mapRowToIngredient, id);
	}

	@Override
	public Ingredient saveOne(Ingredient ingredient) {
		jdbc.update("insert into Ingredient (id, name, type) values (?, ?, ?)",
				ingredient.getId(),
				ingredient.getName(),
				ingredient.getType().toString());
		
		return ingredient;
	}
	
	private Ingredient mapRowToIngredient(ResultSet rs,int rowNum) throws SQLException {
		return new Ingredient(rs.getString("id"),rs.getString("name"),Ingredient.Type.valueOf(rs.getString("type")));
	}

}
