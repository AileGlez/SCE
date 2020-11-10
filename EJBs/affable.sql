connect 'jdbc:derby://localhost:1527/affablebean;user=app;password=app';

DROP TABLE ordered_product;
DROP TABLE customer_order;
DROP TABLE product;
DROP TABLE category;
DROP TABLE customer;

------------------------------- 
--     CUSTOMER
-------------------------------
CREATE  TABLE  customer 
(
  id INT  NOT NULL GENERATED ALWAYS AS IDENTITY 
     (START WITH 1 ,INCREMENT BY 1) 
     CONSTRAINT CUSTOMER_PK PRIMARY KEY,
  name VARCHAR(45) NOT NULL ,
  email VARCHAR(45) NOT NULL ,
  phone VARCHAR(45) NOT NULL ,
  address VARCHAR(45) NOT NULL ,
  city_region VARCHAR(2) NOT NULL ,
  cc_number VARCHAR(19) NOT NULL
  );

------------------------------- 
--     CUSTOMER_ORDER
-------------------------------
CREATE  TABLE  customer_order 
(
  id INT  NOT NULL GENERATED ALWAYS AS IDENTITY 
     (START WITH 1 ,INCREMENT BY 1) 
     CONSTRAINT CUSTOMER_ORDER_PK PRIMARY KEY,
  amount DECIMAL(6,2) NOT NULL ,
  date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  confirmation_number INT  NOT NULL ,
  customer_id INT  NOT NULL CONSTRAINT CUST_ORD_FK REFERENCES customer
);
------------------------------- 
--     CATEGORY
-------------------------------
CREATE  TABLE  category 
(
  id INT  NOT NULL GENERATED ALWAYS AS IDENTITY 
     (START WITH 1 ,INCREMENT BY 1) 
     CONSTRAINT CATEGORY_PK PRIMARY KEY,
  name VARCHAR(45) NOT NULL 
);

INSERT INTO category (name) VALUES 
('dairy'),
('meats'),
('bakery'),
('fruit & veg');

------------------------------- 
--     PRODUCTS
-------------------------------

CREATE  TABLE  product 
(
  id INT  NOT NULL GENERATED ALWAYS AS IDENTITY 
     (START WITH 1 ,INCREMENT BY 1), 
  name VARCHAR(45) NOT NULL ,
  price DECIMAL(5,2) NOT NULL ,
  description VARCHAR(50),
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  category_id INT  NOT NULL ,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_product_category
    FOREIGN KEY (category_id )
    REFERENCES category (id )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

INSERT INTO product (name,price,description,last_update,category_id) VALUES
('milk', 1.70, 'semi skimmed (1L)',TIMESTAMP('2020-10-06 09:00:00'), 1),
('cheese', 2.39, 'mild cheddar (330g)',TIMESTAMP('2020-10-06 09:00:00'), 1),
('butter', 1.09, 'unsalted (250g)', TIMESTAMP('2020-10-06 09:00:00'),1),
('free range eggs', 1.76, 'medium-sized (6 eggs)', TIMESTAMP('2020-10-06 09:00:00'),1),
('organic meat patties', 2.29, 'rolled in fresh herbs patties (250g)', TIMESTAMP('2020-10-06 09:00:00'),2),
('parma ham', 3.49, 'matured, organic (70g)', TIMESTAMP('2020-10-06 09:00:00'),2),
('chicken leg', 2.59, 'free range (250g)', TIMESTAMP('2020-10-06 09:00:00'),2),
('sausages', 3.55, 'reduced fat, pork 3 sausages (350g)', TIMESTAMP('2020-10-06 09:00:00'),2),
('sunflower seed loaf', 1.89, '600g', TIMESTAMP('2020-10-06 09:00:00'),3),
('sesame seed bagel', 1.19, '4 bagels', TIMESTAMP('2020-10-06 09:00:00'),3),
('pumpkin seed bun', 1.15, '4 buns', TIMESTAMP('2020-10-06 09:00:00'),3),
('chocolate cookies', 2.39, 'contain peanuts (3 cookies)', TIMESTAMP('2020-10-06 09:00:00'),3),
('corn on the cob', 1.59, '2 pieces', TIMESTAMP('2020-10-06 09:00:00'),4),
('red currants', 2.49, '150g', TIMESTAMP('2020-10-06 09:00:00'),4),
('broccoli', 1.29, '500g', TIMESTAMP('2020-10-06 09:00:00'),4),
('seedless watermelon', 1.49, '250g', TIMESTAMP('2020-10-06 09:00:00'),4);

------------------------------- 
--     ORDERED_PRODUCTS
-------------------------------

CREATE  TABLE ordered_product
 (
  customer_order_id INT  NOT NULL  
     (START WITH 1 ,INCREMENT BY 1), 
  product_id INT  NOT NULL ,
  quantity SMALLINT  NOT NULL DEFAULT 1 ,
  PRIMARY KEY (customer_order_id, product_id) ,
  CONSTRAINT fk_ordered_product_customer_order
    FOREIGN KEY (customer_order_id )
    REFERENCES customer_order (id )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_ordered_product_product
    FOREIGN KEY (product_id )
    REFERENCES product (id )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

disconnect;
