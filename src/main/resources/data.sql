-- ----------------------------
-- Customers
-- ----------------------------
INSERT INTO customers (id, name, currency, email, phone_number) VALUES
                                                                    ('75067466-5776-4bac-817d-9f600f4047b6', 'Adelin-Gabriel Cracea', 'EUR', 'adelincracea99@gmail.com', '0773829445'),
                                                                    ('f1cd9d54-bc77-4c89-8af0-f151388e4d8d', 'Maria Popescu', 'EUR', 'maria.popescu@example.com', '0721345678'),
                                                                    ('a8ce5a42-ee45-4ab3-b374-89b6c7316bf5', 'Andrei Ionescu', 'USD', 'andrei.ionescu@example.com', '0732123456'),
                                                                    ('9b54bfa7-34ff-4f4f-b3bc-a939a3c1818b', 'Elena Marinescu', 'GBP', 'elena.marinescu@example.com', '0756123456'),
                                                                    ('3ac096c2-9b6c-40c2-9ded-55163e0c339a', 'Victor Ciobanu', 'EUR', 'victor.ciobanu@example.com', '0789234567'),
                                                                    ('95cd13a1-6c3f-4e0e-bef0-470ab2246c5e', 'Ana-Maria Stancu', 'RON', 'ana.stancu@example.com', '0765123987'),
                                                                    ('6fd2c99c-df50-4dd6-9ad4-b02acf525d7a', 'George Mihalache', 'USD', 'george.mihalache@example.com', '0745123987'),
                                                                    ('e2faac09-214d-4f36-a824-3f4b91a489b8', 'Cristina Pavel', 'EUR', 'cristina.pavel@example.com', '0729988776'),
                                                                    ('1223b686-264e-4d49-81ea-bfdc31c2ca1a', 'Radu Alexandrescu', 'CAD', 'radu.alex@example.com', '0733556677'),
                                                                    ('7f330c99-5266-44f0-875f-b467e0ed1bb2', 'Ioana Dumitrescu', 'EUR', 'ioana.dumitrescu@example.com', '0753442211'),
                                                                    ('c86a8565-ddf3-4180-99fd-b4ab655cafa0', 'Czika Stefania', 'EUR', 'steficzika@gmail.com', '0746074113'),
                                                                    ('e3bd0e0f-274f-4311-be87-dbdaa9fe9ff0', 'a', 'EUR', 'czikastefania@yahoo.com', '0746074113');

-- ----------------------------
-- Contract Types
-- ----------------------------
INSERT INTO contract_type (id, name, type) VALUES
                                               ('d483e576-ab02-427d-954c-599bafc857d9','Standard Seller Contract','SELLER'),
                                               ('97267a65-3ff3-4a7c-8bba-ee4a194c4cdb','Standard Customer Contract','CUSTOMER'),
                                               ('ad1f5cee-047b-48d6-874a-577ffb68bdd1','Premium Seller Contract','SELLER'),
                                               ('f0c3e0e3-2d4b-4bf8-8a6a-91da52a7adb8','Premium Customer Contract','CUSTOMER'),
                                               ('b5e68a42-0ee2-4e07-9c28-ebcf447cfd15','Enterprise Seller Contract','SELLER'),
                                               ('a64d4dc5-2d76-41fd-8458-41f5826c1a56','Enterprise Customer Contract','CUSTOMER'),
                                               ('e33d2e62-eed3-4c35-9373-f92f1f4ceb77','Basic Seller Contract','SELLER'),
                                               ('5e884334-d334-48cf-88e0-fddbb1c2df80','Basic Customer Contract','CUSTOMER'),
                                               ('328a72f7-b55f-455d-8df4-f48a2c8cbad4','International Seller Contract','SELLER'),
                                               ('8df7fb37-3cc3-4b1d-bb88-63fe91d28234','International Customer Contract','CUSTOMER'),
                                               ('0ba47268-ded6-4e71-861f-6bcd7607907b','NTT','CUSTOMER');

-- ----------------------------
-- Sellable Items (Parent Table)
-- ----------------------------
-- Contains names and IDs for BOTH Products and Services
INSERT INTO sellable_item (id, name) VALUES
-- Products
('5d2dcfce-fa49-4f77-a4b7-4d45803f50c2','Laptop'),
('45fb0809-c813-48a0-ab42-ebf863761dfb','Smartphone'),
('358a5000-ea8e-42ff-b737-6f1858b05649','Headphones'),
('6e7d8c9a-b0c1-d2e3-f4a5-6b7c8d9e0f1a','Monitor'),
('7f8e9d0b-c1d2-e3f4-a5b6-7c8d9e0f1a2b','Keyboard'),
('8g9h0i1j-2k3l-4m5n-6o7p-8q9r0s1t2u3v','Mouse'),
('9h0i1j2k-3l4m-5n6o-7p8q-9r0s1t2u3v4w','Printer'),
('0i1j2k3l-4m5n-6o7p-8q9r-0s1t2u3v4w5x','Webcam'),
('1j2k3l4m-5n6o-7p8q-9r0s-1t2u3v4w5x6y','Tablet'),
('2k3l4m5n-6o7p-8q9r-0s1t-2u3v4w5x6y7z','Gaming Console'),
-- Services
('eedbdf4d-c539-4834-bfd5-a5319c0feb83','Cleaning Service'),
('d6289943-e5e2-4c88-a564-b6a08064e329','Cooking Service'),
('3a9f499e-7eea-4d09-9ae6-be515c097ea8','Driving Service'),
('7741d7d0-7a0e-4f51-a2c3-9d413e11b8b6','Plumbing Service'),
('1c3f2d4e-5a6b-7c8d-9e0f-1a2b3c4d5e6f','IT Support'),
('2e4d3f5a-6b7c-8d9e-0f1a-2b3c4d5e6f7a','Gardening Service'),
('3f5e4d6b-7c8d-9e0f-1a2b-3c4d5e6f7a8b','Laundry Service'),
('4d6f5e7c-8d9e-0f1a-2b3c-4d5e6f7a8b9c','Pet Grooming'),
('5e7d6f8d-9e0f-1a2b-3c4d-5e6f7a8b9c0d','Delivery Service'),
('6f8e7d9e-0f1a-2b3c-4d5e-6f7a8b9c0d1e','Tutoring Service');

-- ----------------------------
-- Products (Child Table)
-- ----------------------------
INSERT INTO products (id, value, stock_quantity) VALUES
                                                     ('5d2dcfce-fa49-4f77-a4b7-4d45803f50c2', 1200.0, 15),
                                                     ('45fb0809-c813-48a0-ab42-ebf863761dfb', 800.0, 30),
                                                     ('358a5000-ea8e-42ff-b737-6f1858b05649', 150.0, 50),
                                                     ('6e7d8c9a-b0c1-d2e3-f4a5-6b7c8d9e0f1a', 350.0, 25),
                                                     ('7f8e9d0b-c1d2-e3f4-a5b6-7c8d9e0f1a2b', 75.0, 60),
                                                     ('8g9h0i1j-2k3l-4m5n-6o7p-8q9r0s1t2u3v', 30.0, 80),
                                                     ('9h0i1j2k-3l4m-5n6o-7p8q-9r0s1t2u3v4w', 250.0, 10),
                                                     ('0i1j2k3l-4m5n-6o7p-8q9r-0s1t2u3v4w5x', 50.0, 45),
                                                     ('1j2k3l4m-5n6o-7p8q-9r0s-1t2u3v4w5x6y', 600.0, 20),
                                                     ('2k3l4m5n-6o7p-8q9r-0s1t-2u3v4w5x6y7z', 500.0, 5);

-- ----------------------------
-- Services (Child Table)
-- ----------------------------
INSERT INTO services (id, status) VALUES
                                      ('eedbdf4d-c539-4834-bfd5-a5319c0feb83', 'ACTIVE'),
                                      ('d6289943-e5e2-4c88-a564-b6a08064e329', 'DOWN'),
                                      ('3a9f499e-7eea-4d09-9ae6-be515c097ea8', 'ACTIVE'),
                                      ('7741d7d0-7a0e-4f51-a2c3-9d413e11b8b6', 'ACTIVE'),
                                      ('1c3f2d4e-5a6b-7c8d-9e0f-1a2b3c4d5e6f', 'DOWN'),
                                      ('2e4d3f5a-6b7c-8d9e-0f1a-2b3c4d5e6f7a', 'ACTIVE'),
                                      ('3f5e4d6b-7c8d-9e0f-1a2b-3c4d5e6f7a8b', 'ACTIVE'),
                                      ('4d6f5e7c-8d9e-0f1a-2b3c-4d5e6f7a8b9c', 'DOWN'),
                                      ('5e7d6f8d-9e0f-1a2b-3c4d-5e6f7a8b9c0d', 'ACTIVE'),
                                      ('6f8e7d9e-0f1a-2b3c-4d5e-6f7a8b9c0d1e', 'ACTIVE');

-- ----------------------------
-- Units of Measure
-- ----------------------------
INSERT INTO unit_of_measure (id, name, symbol) VALUES
                                                   ('43d1b8d9-a418-42df-adaf-44e8dc3c799e','Kilogram','kg'),
                                                   ('81005363-2bce-416e-925c-6894c24f820c','Meter','m'),
                                                   ('a567ba84-091a-4fe6-991f-0a52e9c3e73e','Liter','L'),
                                                   ('2e9c3e73-a091-49b2-a42e-1c6f9b2d8e4a','Second','s'),
                                                   ('b1c7d8a9-5f6e-43c2-8710-0a3b2c1d4e5f','Ampere','A'),
                                                   ('6g4h5i6j-7k8l-9m0n-1p2q-3r4s5t6u7v8w','Kelvin','K'),
                                                   ('c3d4e5f6-7a8b-9c0d-1e2f-3g4h5i6j7k8l','Mole','mol'),
                                                   ('d5e6f7g8-9h0i-1j2k-3l4m-5n6o7p8q9r0s','Candela','cd'),
                                                   ('e7f8g9h0-1i2j-3k4l-5m6n-7o8p9q0r1s2t','Hertz','Hz'),
                                                   ('f9g0h1i2-3j4k-5l6m-7n8o-9p0q1r2s3t4u','Joule','J');

-- ----------------------------
-- Contracts
-- ----------------------------
INSERT INTO contracts (id, name, contract_type_id, status, creation_date, expiration_date, customer_id) VALUES
                                                                                                            ('f12460f8-1b62-4f89-a60e-50629c872042','Contract A','d483e576-ab02-427d-954c-599bafc857d9','ACTIVE','2025-11-20','2025-12-20',NULL),
                                                                                                            ('ce68db62-ea51-40ec-bb02-1098d25859cf','Contract B','97267a65-3ff3-4a7c-8bba-ee4a194c4cdb','DOWN','2025-11-21','2026-01-21',NULL),
                                                                                                            ('a247d889-53f2-423f-bce3-a9d0afc886f1','Contract C','d483e576-ab02-427d-954c-599bafc857d9','ACTIVE','2025-10-01','2026-10-01',NULL),
                                                                                                            ('91eb09c0-15bd-4cf9-b50c-51e060b1e26d','Contract D','ad1f5cee-047b-48d6-874a-577ffb68bdd1','ACTIVE','2025-09-15','2026-09-15',NULL),
                                                                                                            ('f2b8e42a-17b2-4494-b162-454fb6f5cc4e','Contract G','ad1f5cee-047b-48d6-874a-577ffb68bdd1','ACTIVE','2025-08-20','2026-08-20',NULL),
                                                                                                            ('e30bd6fd-00e6-4fcb-9b1a-404d235b7f4b','Contract H','b5e68a42-0ee2-4e07-9c28-ebcf447cfd15','DOWN','2025-07-10','2026-07-10',NULL),
                                                                                                            ('c9e743e4-5106-4b8b-8acb-9b329a4b6ff3','Contract I','d483e576-ab02-427d-954c-599bafc857d9','ACTIVE','2025-06-05','2026-06-05',NULL),
                                                                                                            ('1ca2b902-5a37-4767-9e1e-0c553958e1a7','Contract J','f0c3e0e3-2d4b-4bf8-8a6a-91da52a7adb8','DOWN','2025-05-01','2026-05-01',NULL),
                                                                                                            ('34c9f2e1-8b7a-4d6c-9e5f-1a2b3c4d5e6f','Contract K','97267a65-3ff3-4a7c-8bba-ee4a194c4cdb','ACTIVE','2025-11-22','2025-12-22',NULL),
                                                                                                            ('56d0a3b2-9c8d-4e7f-0a1b-2c3d4e5f6a7b','Contract L','ad1f5cee-047b-48d6-874a-577ffb68bdd1','DOWN','2025-11-23','2025-12-23',NULL);

-- ----------------------------
-- Contract Lines
-- ----------------------------
INSERT INTO contract_lines (id, contract_id, item_id, unit_id, quantity) VALUES
                                                                             ('d90d4f3e-9b0a-4eaa-8410-e82a48da40e4','f12460f8-1b62-4f89-a60e-50629c872042','5d2dcfce-fa49-4f77-a4b7-4d45803f50c2','43d1b8d9-a418-42df-adaf-44e8dc3c799e',10.0),
                                                                             ('b4d610f7-6de9-4a1c-8ca4-b52d087aac1e','ce68db62-ea51-40ec-bb02-1098d25859cf','8g9h0i1j-2k3l-4m5n-6o7p-8q9r0s1t2u3v','81005363-2bce-416e-925c-6894c24f820c',5.0),
                                                                             ('e2ac6139-02b3-4962-aa2e-13ec39bea5f0','a247d889-53f2-423f-bce3-a9d0afc886f1','7f8e9d0b-c1d2-e3f4-a5b6-7c8d9e0f1a2b','a567ba84-091a-4fe6-991f-0a52e9c3e73e',12.0),
                                                                             ('4fbfb335-4a5d-4a42-8b52-37199f091c50','91eb09c0-15bd-4cf9-b50c-51e060b1e26d','6e7d8c9a-b0c1-d2e3-f4a5-6b7c8d9e0f1a','a567ba84-091a-4fe6-991f-0a52e9c3e73e',3.0),
                                                                             ('05e53d4c-9382-4bfb-9d0c-8052244912bd','f2b8e42a-17b2-4494-b162-454fb6f5cc4e','7f8e9d0b-c1d2-e3f4-a5b6-7c8d9e0f1a2b','a567ba84-091a-4fe6-991f-0a52e9c3e73e',7.0),
                                                                             ('bff7c768-8873-47c4-9fd7-56189fad5db8','e30bd6fd-00e6-4fcb-9b1a-404d235b7f4b','8g9h0i1j-2k3l-4m5n-6o7p-8q9r0s1t2u3v','81005363-2bce-416e-925c-6894c24f820c',20.0),
                                                                             ('60b1204f-8858-4b9a-a0b9-46c67d78ef77','c9e743e4-5106-4b8b-8acb-9b329a4b6ff3','358a5000-ea8e-42ff-b737-6f1858b05649','e7f8g9h0-1i2j-3k4l-5m6n-7o8p9q0r1s2t',4.0),
                                                                             ('d3952b23-ea73-4fc9-bf8b-6b5ba884c8fe','1ca2b902-5a37-4767-9e1e-0c553958e1a7','0i1j2k3l-4m5n-6o7p-8q9r-0s1t2u3v4w5x','a567ba84-091a-4fe6-991f-0a52e9c3e73e',6.0),
                                                                             ('a8c82025-5e5f-4b22-af09-9e6285d03047','f12460f8-1b62-4f89-a60e-50629c872042','358a5000-ea8e-42ff-b737-6f1858b05649','e7f8g9h0-1i2j-3k4l-5m6n-7o8p9q0r1s2t',9.0),
                                                                             ('6b2fb3f9-98d4-46ba-a1fd-2400ef41b0a7','ce68db62-ea51-40ec-bb02-1098d25859cf','2k3l4m5n-6o7p-8q9r-0s1t-2u3v4w5x6y7z','a567ba84-091a-4fe6-991f-0a52e9c3e73e',8.0);

-- ----------------------------
-- Orders
-- ----------------------------
INSERT INTO orders (id, name, customer_id, contract_id) VALUES
                                                            ('659b743f-8bc2-4e88-a701-6994c0acb293','Order A','75067466-5776-4bac-817d-9f600f4047b6','f12460f8-1b62-4f89-a60e-50629c872042'),
                                                            ('27ca2ae5-a110-4819-a871-ea5e77e31ee8','Order B','f1cd9d54-bc77-4c89-8af0-f151388e4d8d','ce68db62-ea51-40ec-bb02-1098d25859cf'),
                                                            ('7b2d434e-e2af-4b7a-9a1b-d2bbd2d436f2','Order C','a8ce5a42-ee45-4ab3-b374-89b6c7316bf5','a247d889-53f2-423f-bce3-a9d0afc886f1'),
                                                            ('fa354a86-89a6-4636-9e66-c6325e4b7c82','Order D','9b54bfa7-34ff-4f4f-b3bc-a939a3c1818b','91eb09c0-15bd-4cf9-b50c-51e060b1e26d'),
                                                            ('9a69173d-a045-4b91-96e7-640fa2446b1f','Order E','3ac096c2-9b6c-40c2-9ded-55163e0c339a','f2b8e42a-17b2-4494-b162-454fb6f5cc4e'),
                                                            ('b1adca3a-0ed8-4b78-b3c1-f450fe08c3d7','Order F','95cd13a1-6c3f-4e0e-bef0-470ab2246c5e','e30bd6fd-00e6-4fcb-9b1a-404d235b7f4b'),
                                                            ('1cb4cd01-18f0-4ab6-b5cc-cacd7bf0de97','Order G','6fd2c99c-df50-4dd6-9ad4-b02acf525d7a','c9e743e4-5106-4b8b-8acb-9b329a4b6ff3'),
                                                            ('f0e35b25-8971-490c-aa90-23fb3adfbe37','Order H','e2faac09-214d-4f36-a824-3f4b91a489b8','1ca2b902-5a37-4767-9e1e-0c553958e1a7'),
                                                            ('6d7090f6-7f44-4c41-9b94-602c88088f2a','Order I','1223b686-264e-4d49-81ea-bfdc31c2ca1a','f12460f8-1b62-4f89-a60e-50629c872042'),
                                                            ('eeb104cf-f044-44f4-9f9e-5b7fbb9dfc3f','Order J','7f330c99-5266-44f0-875f-b467e0ed1bb2','ce68db62-ea51-40ec-bb02-1098d25859cf');

-- ----------------------------
-- Order Lines
-- ----------------------------
INSERT INTO order_lines (id, order_id, item_id, unit_id, quantity) VALUES
                                                                       ('9c5974ba-f49c-420e-8713-86bae00d5566','659b743f-8bc2-4e88-a701-6994c0acb293','5d2dcfce-fa49-4f77-a4b7-4d45803f50c2','43d1b8d9-a418-42df-adaf-44e8dc3c799e',2.0),
                                                                       ('01d1b6c3-a719-4127-b916-51ea9534e645','27ca2ae5-a110-4819-a871-ea5e77e31ee8','358a5000-ea8e-42ff-b737-6f1858b05649','81005363-2bce-416e-925c-6894c24f820c',5.0),
                                                                       ('5c904965-8412-4dbc-88e0-a252ea9af473','7b2d434e-e2af-4b7a-9a1b-d2bbd2d436f2','45fb0809-c813-48a0-ab42-ebf863761dfb','a567ba84-091a-4fe6-991f-0a52e9c3e73e',10.0),
                                                                       ('1c2d3e4f-5a6b-7c8d-9e0f-1a2b3c4d5e6f','fa354a86-89a6-4636-9e66-c6325e4b7c82','eedbdf4d-c539-4834-bfd5-a5319c0feb83','43d1b8d9-a418-42df-adaf-44e8dc3c799e',4.0),
                                                                       ('2b3c4d5e-6f7a-8b9c-0d1e-2f3a4b5c6d7e','9a69173d-a045-4b91-96e7-640fa2446b1f','7f8e9d0b-c1d2-e3f4-a5b6-7c8d9e0f1a2b','a567ba84-091a-4fe6-991f-0a52e9c3e73e',25.0),
                                                                       ('3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f','b1adca3a-0ed8-4b78-b3c1-f450fe08c3d7','d6289943-e5e2-4c88-a564-b6a08064e329','2e9c3e73-a091-49b2-a42e-1c6f9b2d8e4a',12.0),
                                                                       ('4d5e6f7a-8b9c-0d1e-2f3a-4b5c6d7e8f9a','1cb4cd01-18f0-4ab6-b5cc-cacd7bf0de97','8g9h0i1j-2k3l-4m5n-6o7p-8q9r0s1t2u3v','81005363-2bce-416e-925c-6894c24f820c',100.0),
                                                                       ('5e6f7a8b-9c0d-1e2f-3a4b-5c6d7e8f9a0b','f0e35b25-8971-490c-aa90-23fb3adfbe37','2e4d3f5a-6b7c-8d9e-0f1a-2b3c4d5e6f7a','43d1b8d9-a418-42df-adaf-44e8dc3c799e',3.0),
                                                                       ('6f7a8b9c-0d1e-2f3a-4b5c-6d7e8f9a0b1c','6d7090f6-7f44-4c41-9b94-602c88088f2a','0i1j2k3l-4m5n-6o7p-8q9r-0s1t2u3v4w5x','a567ba84-091a-4fe6-991f-0a52e9c3e73e',500.0),
                                                                       ('7a8b9c0d-1e2f-3a4b-5c6d-7e8f9a0b1c2d','eeb104cf-f044-44f4-9f9e-5b7fbb9dfc3f','2k3l4m5n-6o7p-8q9r-0s1t-2u3v4w5x6y7z','a567ba84-091a-4fe6-991f-0a52e9c3e73e',15.0);