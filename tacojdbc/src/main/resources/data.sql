-- preload data for db
DELETE FROM "ingredient";
DELETE FROM "taco";
DELETE FROM "taco_ingredients";
DELETE FROM "order_table";
DELETE FROM "taco_order";

INSERT INTO "ingredient" (id, name, type) values ('FLTO', 'Flour Tortilla', 'WRAP'),
                                               ('COTO', 'Corn Tortilla', 'WRAP'),
                                               ('GRBF', 'Ground Beef', 'PROTEIN'),
                                               ('CARN', 'Carnitas', 'PROTEIN'),
                                               ('TMTO', 'Diced Tomatoes', 'VEGGIES'),
                                               ('LETC', 'Lettuce', 'VEGGIES'),
                                               ('CHED', 'Cheddar', 'CHEESE'),
                                               ('JACK', 'Monterrey Jack', 'CHEESE'),
                                               ('SLSA', 'Salsa', 'SAUCE'),
                                               ('SRCR', 'Sour Cream', 'SAUCE');