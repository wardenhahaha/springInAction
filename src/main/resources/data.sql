DELETE 
FROM
	Taco_Order_Tacos;
DELETE 
FROM
	Taco_Ingredients;
DELETE 
FROM
	Taco;
DELETE 
FROM
	Taco_Order;
DELETE 
FROM
	Ingredient;
INSERT INTO Ingredient ( id, NAME, type )
VALUES
	( 'FLTO', 'Flour Tortilla', 'WRAP' );
INSERT INTO Ingredient ( id, NAME, type )
VALUES
	( 'COTO', 'Corn Tortilla', 'WRAP' );
INSERT INTO Ingredient ( id, NAME, type )
VALUES
	( 'GRBF', 'Ground Beef', 'PROTEIN' );
INSERT INTO Ingredient ( id, NAME, type )
VALUES
	( 'CARN', 'Carnitas', 'PROTEIN' );
INSERT INTO Ingredient ( id, NAME, type )
VALUES
	( 'TMTO', 'Diced Tomatoes', 'VEGGIES' );
INSERT INTO Ingredient ( id, NAME, type )
VALUES
	( 'LETC', 'Lettuce', 'VEGGIES' );
INSERT INTO Ingredient ( id, NAME, type )
VALUES
	( 'CHED', 'Cheddar', 'CHEESE' );
INSERT INTO Ingredient ( id, NAME, type )
VALUES
	( 'JACK', 'Monterrey Jack', 'CHEESE' );
INSERT INTO Ingredient ( id, NAME, type )
VALUES
	( 'SLSA', 'Salsa', 'SAUCE' );
INSERT INTO Ingredient ( id, NAME, type )
VALUES
	( 'SRCR', 'Sour Cream', 'SAUCE' );