-- Insert test data
INSERT INTO agreement (agreement_number, company_name, company_address, company_phone_number)
VALUES ('AB1234567891234567', 'Premium Coffee Traders', '789 Brew Blvd, Aroma Town', '555-8765'),
       ('BC2345678912345678', 'Global Coffee Distributors', '321 Espresso Ave, Grind City', '555-4321'),
       ('CD3456789123456789', 'The Coffee Warehouse', '654 Latte Ln, Mocha City', '555-6789'),
       ('DE4567891234567890', 'Coffee Supply Co.', '123 Coffee St, Beansville', '555-1234'),
       ('EF5678912345678901', 'Java Importers Inc.', '456 Java Rd, Roast City', '555-5678');


INSERT INTO client (fio, agreement, phone_number, email)
VALUES ('Иван Сергей Иванов', 1, '+79012345678', 'ivanov@example.com'),
       ('Анастасия Олеговна Смирнова', 2, '+79123456789', 'smirnova@example.com'),
       ('Алексей Дмитриевич Кузнецов', 3, '+79234567890', 'kuznetsov@example.com'),
       ('Мария Павловна Сидорова', 4, '+79345678901', 'sidorova@example.com'),
       ('Игорь Анатольевич Васильев', 5, '+79456789012', 'vasiliev@example.com'),
       ('Елена Викторовна Попова', 1, '+79567890123', 'popova@example.com'),
       ('Дмитрий Александрович Фёдоров', 2, '+79678901234', 'fedorov@example.com'),
       ('Ольга Валентиновна Николаева', 3, '+79789012345', 'nikolaeva@example.com');



INSERT INTO category (name)
VALUES ('Coffee Beans'),
       ('Milk'),
       ('Syrups'),
       ('Coffee Equipment'),
       ('Coffee Accessories');

INSERT INTO product (name, description, price, category)
VALUES ('Arabica Beans', 'High-quality Arabica coffee beans.', 15.99, 1),
       ('Robusta Beans', 'Strong and bold Robusta coffee beans.', 12.99, 1),
       ('Colombian Beans', 'Rich and smooth Colombian coffee beans.', 17.99, 1),
       ('Ethiopian Beans', 'Floral and fruity Ethiopian coffee beans.', 18.99, 1),
       ('Whole Milk', 'Fresh whole milk.', 1.99, 2),
       ('Almond Milk', 'Nutty and creamy almond milk.', 2.99, 2),
       ('Vanilla Syrup', 'Sweet and smooth vanilla syrup.', 6.99, 3),
       ('Caramel Syrup', 'Rich and buttery caramel syrup.', 6.99, 3),
       ('Espresso Machine', 'Top-notch espresso machine for home use.', 299.99, 4),
       ('French Press', 'Stainless steel French press for perfect coffee.', 19.99, 4),
       ('Coffee Grinder', 'Electric coffee grinder with multiple settings.', 49.99, 4),
       ('Cold Brew Kit', 'Complete kit for making cold brew coffee.', 39.99, 4),
       ('Travel Mug', 'Insulated travel mug to keep your coffee hot.', 14.99, 5),
       ('Reusable Coffee Filter', 'Eco-friendly reusable coffee filter.', 9.99, 5),
       ('Coffee Scoop', 'Stainless steel coffee scoop.', 4.99, 5),
       ('Milk Frother', 'Handheld milk frother for creamy lattes.', 12.99, 5);

INSERT INTO warehouse (address, title, capacity)
VALUES ('202 Coffee Way, Bean City', 'North Warehouse', 7000),
       ('303 Caffeine Rd, Roastville', 'South Warehouse', 8000);

INSERT INTO warehouse_product (warehouse_id, product_id, amount)
VALUES (1, 1, 100),
       (1, 2, 150),
       (1, 3, 50),
       (1, 4, 60),
       (2, 5, 200),
       (2, 6, 150),
       (2, 7, 100),
       (2, 8, 80),
       (1, 9, 20),
       (1, 10, 30),
       (2, 11, 40),
       (2, 12, 25),
       (1, 13, 50),
       (1, 14, 30),
       (2, 15, 60),
       (2, 16, 70);

INSERT INTO status (name)
VALUES ('Pending'),
       ('Processing'),
       ('Shipped'), --The order has been dispatched and is en route to the customer.
       ('Delivered'),--The order has been received by the customer.
       ('Cancelled');

INSERT INTO "order" (product, client, amount, address, status_id)
VALUES (1, 1, 2, '123 Coffee St, Beansville', 1),
       (3, 2, 1, '456 Java Rd, Roast City', 2),
       (5, 3, 4, '789 Brew Blvd, Aroma Town', 3),
       (7, 4, 2, '321 Espresso Ave, Grind City', 4),
       (9, 5, 1, '654 Latte Ln, Mocha City', 5),
       (11, 6, 2, '101 Caffeine St, Roast City', 1),
       (13, 1, 1, '202 Coffee Way, Bean City', 2),
       (15, 2, 3, '303 Caffeine Rd, Roastville', 3);
