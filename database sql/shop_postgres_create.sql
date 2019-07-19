CREATE TABLE "users" (
	"id" serial NOT NULL,
	"email" varchar(255) NOT NULL UNIQUE,
	"password" varchar(255) NOT NULL,
	CONSTRAINT "users_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "products" (
	"id" serial NOT NULL,
	"name" varchar(255) NOT NULL,
	"price" FLOAT NOT NULL,
	"currency" varchar(5) NOT NULL,
	"description" varchar(3000) NOT NULL,
	"productcategory_id" integer NOT NULL,
	"supplier_id" integer NOT NULL,
	"active" varchar(255) NOT NULL,
	"image" varchar(255) NOT NULL,
	CONSTRAINT "products_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "addresses" (
	"id" serial NOT NULL,
	"firstname" varchar(255) NOT NULL,
	"lastname" varchar(255) NOT NULL,
	"address" varchar(255) NOT NULL,
	"postalcode" varchar(255) NOT NULL,
	"city" varchar(255) NOT NULL,
	"country" varchar(255) NOT NULL,
	"phonenumber" varchar(255) NOT NULL,
	"user_id" integer NOT NULL,
	CONSTRAINT "addresses_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "carts" (
	"id" serial NOT NULL,
	"user_id" integer NOT NULL,
	"active" varchar(255) NOT NULL,
	"historydate" varchar(255) NOT NULL,
	"product01" integer NOT NULL,
	"product02" integer NOT NULL,
	"product03" integer NOT NULL,
	"product04" integer NOT NULL,
	"product05" integer NOT NULL,
	"product06" integer NOT NULL,
	"product07" integer NOT NULL,
	"product08" integer NOT NULL,
	"product09" integer NOT NULL,
	"product10" integer NOT NULL,
	"product11" integer NOT NULL,
	"product12" integer NOT NULL,
	"product13" integer NOT NULL,
	"product14" integer NOT NULL,
	"product15" integer NOT NULL,
	"quantity01" integer NOT NULL,
	"quantity02" integer NOT NULL,
	"quantity03" integer NOT NULL,
	"quantity04" integer NOT NULL,
	"quantity05" integer NOT NULL,
	"quantity06" integer NOT NULL,
	"quantity07" integer NOT NULL,
	"quantity08" integer NOT NULL,
	"quantity09" integer NOT NULL,
	"quantity10" integer NOT NULL,
	"quantity11" integer NOT NULL,
	"quantity12" integer NOT NULL,
	"quantity13" integer NOT NULL,
	"quantity14" integer NOT NULL,
	"quantity15" integer NOT NULL,
	"address_id" integer NOT NULL,
	"totalprice" integer NOT NULL,
	CONSTRAINT "carts_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "productcategories" (
	"id" serial NOT NULL,
	"name" varchar(255) NOT NULL,
	"description" varchar(255) NOT NULL,
	"department" varchar(255) NOT NULL,
	CONSTRAINT "productcategories_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "suppliers" (
	"id" serial NOT NULL,
	"name" varchar(255) NOT NULL,
	"description" varchar(255) NOT NULL,
	CONSTRAINT "suppliers_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);




ALTER TABLE "products" ADD CONSTRAINT "products_fk0" FOREIGN KEY ("productcategory_id") REFERENCES "productcategories"("id");
ALTER TABLE "products" ADD CONSTRAINT "products_fk1" FOREIGN KEY ("supplier_id") REFERENCES "suppliers"("id");

ALTER TABLE "addresses" ADD CONSTRAINT "addresses_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("id");

ALTER TABLE "carts" ADD CONSTRAINT "carts_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("id");
ALTER TABLE "carts" ADD CONSTRAINT "carts_fk1" FOREIGN KEY ("address_id") REFERENCES "addresses"("id");



