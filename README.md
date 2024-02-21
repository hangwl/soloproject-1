# Project Outline

## Simple E-commerce Application

### Features

- User Registration + Login (username, password, email)

- Product Browsing through a product catalogue which contains product details (name, price, description)

- ShoppingCart Management
	- Adding products to shopping cart through CartItem entity which links products to the user's shopping cart and includes the quantity of each product
	- Display list of selected products and `totalAmount` of all products in the cart

- Checkout and Order Creation
	- Users should be able to checkout their shopping cart, which then creates an order
	- Each product and quantity in the order should then be managed through the `OrderItem` entity
	- Each order is linked to the user and includes an `orderDate` and `totalAmount` for that order

- Order History
	- Display details of past orders

### Entity Relationship Diagram

![alt text](https://github.com/hangwl/soloproject-1/blob/master/images/ERD.png)
