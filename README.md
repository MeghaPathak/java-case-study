# java-catalog-management

# Problem Statement

Design and code catalog management system for a generic ecommerce site to store
various kinds of SKUs(Stock keeping unit). Expected functionalities of the systems are
1. Provide basic CRUD operations to store, update and delete SKUs from the store
2. Provide search functionality to find products based on a keyword which can be in the
title or description of the SKU.


# Classes 
 1. Product
 2. Catalog
 3. CatalogProductRelationship


# Assumptions 
```
 1. SKU is referred as Product in this applicaiton.

 2. A catalog and product are independent entity and can exists one without the other.
 The application hence provides CRUD operations for Catalog and Products separately.

 3. A product can belong to more than one catalog. 
 For ex, Product "Butter" can belong to *Catalog Lotions* as well as to *Catalog Food*

 4. However, for a product to be listed in Search results, it has to be part of atleast one Catalog.
 The class CatalogProductRelationship keeps track of interrelation between Catalog and Product.
```

# Approach
**CRUD**:
Since usage of database is discouraged in this case study, the application exposes singleton instances of repositories for all the three classes.
These repositories internally uses HashMap to store data.

**Search:**
The application uses *'Inverted Index'* logic to store keywords against the CatalogProductRelationship. Care is taken to eliminate noise words before creating index.


# Sample Search Example
Let's say we have 3 products :

Product1 -> { 	title:Britiania biscuit, 
				desctiption:"Maida butter biscuit", 
				Catalog : "Food"
			}

Product2 -> { 	title:"Monaco", 
				desctiption:"Salty butter biscuit", 
				Catalog : "Food"
			}

Product3 -> { 	title:"Cocoa Butter Cream", 
				desctiption:"Cream with richness of cocoa butter", 
				Catalog : "Body Lotions"
			}

*Search for keyword "butter" will yeild 3 results where as biscuit will yield two results.*

```
******* 3 Search Results for 'Butter'  : *********
Product: Monaco in Catalog :Food
Product: Cocoa butter Cream in Catalog :Body Lotions
Product: Britinia Biscuits in Catalog :Food
```

# Test Coverage
The coverage is 75%
