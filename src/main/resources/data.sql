-- ==================================================================================
-- 1. MASTER DATA
-- ==================================================================================

-- ----------------------------------------------------------------------------------
-- Contract Types (10 Instances)
-- ----------------------------------------------------------------------------------
INSERT INTO contract_type (id, name, type) VALUES
('a1b2c3d4-e5f6-7890-1234-56789abcdef0', 'Standard Seller Agreement', 'SELLER'),
('b2c3d4e5-f678-9012-3456-789abcdef012', 'Standard Customer Agreement', 'CUSTOMER'),
('c3d4e5f6-7890-1234-5678-9abcdef01234', 'Premium Seller B2B', 'SELLER'),
('d4e5f678-9012-3456-7890-abcdef012345', 'Premium Customer B2B', 'CUSTOMER'),
('e5f67890-1234-5678-9012-bcdef0123456', 'Enterprise Partner Seller', 'SELLER'),
('f6789012-3456-7890-1234-cdef01234567', 'Enterprise Partner Customer', 'CUSTOMER'),
('07890123-4567-8901-2345-def012345678', 'Government Procurement', 'CUSTOMER'),
('18901234-5678-9012-3456-ef0123456789', 'Vendor Framework 2025', 'SELLER'),
('29012345-6789-0123-4567-f0123456789a', 'Internal Transfer Protocol', 'CUSTOMER'),
('30123456-7890-1234-5678-0123456789ab', 'Temporary Service Order', 'SELLER');

-- ----------------------------------------------------------------------------------
-- Units of Measure (10 Instances)
-- ----------------------------------------------------------------------------------
INSERT INTO unit_of_measure (id, name, symbol) VALUES
('41234567-89ab-cdef-0123-456789abcdef', 'Kilogram', 'kg'),
('52345678-9abc-def0-1234-56789abcdef0', 'Meter', 'm'),
('63456789-abcd-ef01-2345-6789abcdef01', 'Liter', 'L'),
('74567890-bcde-f012-3456-789abcdef012', 'Second', 's'),
('85678901-cdef-0123-4567-89abcdef0123', 'Piece', 'pcs'),
('96789012-def0-1234-5678-9abcdef01234', 'Box', 'box'),
('a7890123-ef01-2345-6789-abcdef012345', 'Pallet', 'plt'),
('b8901234-f012-3456-7890-bcdef0123456', 'Hour', 'hr'),
('c9012345-0123-4567-8901-cdef01234567', 'Day', 'day'),
('d0123456-1234-5678-9012-def012345678', 'Month', 'mo');

-- ----------------------------------------------------------------------------------
-- Sellable Items (Parent Table - 20 Total: 10 Products + 10 Services)
-- ----------------------------------------------------------------------------------
INSERT INTO sellable_item (id, name) VALUES
-- Products
('11e4d3f5-a6b7-4c8d-9e0f-1a2b3c4d5e6f', 'Dell XPS 15'),
('22f5e4a6-b7c8-4d9e-0f1a-2b3c4d5e6f7a', 'iPhone 15 Pro'),
('33a6b7c8-d9e0-4f1a-2b3c-4d5e6f7a8b9c', 'Logitech MX Master 3'),
('44b7c8d9-e0f1-4a2b-3c4d-5e6f7a8b9c0d', 'Samsung Odyssey Monitor'),
('55c8d9e0-f1a2-4b3c-4d5e-6f7a8b9c0d1e', 'Keychron K2 Keyboard'),
('66d9e0f1-a2b3-4c4d-5e6f-7a8b9c0d1e2f', 'Office Chair Ergo'),
('77e0f1a2-b3c4-4d5e-6f7a-8b9c0d1e2f3a', 'Standing Desk'),
('88f1a2b3-c4d5-4e6f-7a8b-9c0d1e2f3a4b', 'USB-C Docking Station'),
('99a2b3c4-d5e6-4f7a-8b9c-0d1e2f3a4b5c', 'Sony WH-1000XM5'),
('00b3c4d5-e6f7-4a8b-9c0d-1e2f3a4b5c6d', 'Webcam 4K Pro'),
-- Services
('aa112233-4455-6677-8899-aabbccddeeff', 'General Cleaning'),
('bb223344-5566-7788-99aa-bbccddeeff00', 'IT Support L1'),
('cc334455-6677-8899-aabb-ccddeeff0011', 'Cloud Consulting'),
('dd445566-7788-99aa-bbcc-ddeeff001122', 'Legal Advisory'),
('ee556677-8899-aabb-ccdd-eeff00112233', 'Security Auditing'),
('ff667788-99aa-bbcc-ddee-ff0011223344', 'Software Development'),
('00778899-aabb-ccdd-eeff-001122334455', 'Project Management'),
('118899aa-bbcc-ddee-ff00-112233445566', 'Training Workshop'),
('2299aabb-ccdd-eeff-0011-223344556677', 'Network Maintenance'),
('33aabbcc-ddee-ff00-1122-334455667788', 'Data Recovery');

-- ----------------------------------------------------------------------------------
-- Products (Child Table - 10 Instances)
-- ----------------------------------------------------------------------------------
INSERT INTO products (id, value, stock_quantity) VALUES
('11e4d3f5-a6b7-4c8d-9e0f-1a2b3c4d5e6f', 2500.00, 50),
('22f5e4a6-b7c8-4d9e-0f1a-2b3c4d5e6f7a', 1200.00, 100),
('33a6b7c8-d9e0-4f1a-2b3c-4d5e6f7a8b9c', 99.99, 200),
('44b7c8d9-e0f1-4a2b-3c4d-5e6f7a8b9c0d', 450.00, 30),
('55c8d9e0-f1a2-4b3c-4d5e-6f7a8b9c0d1e', 120.00, 75),
('66d9e0f1-a2b3-4c4d-5e6f-7a8b9c0d1e2f', 300.00, 20),
('77e0f1a2-b3c4-4d5e-6f7a-8b9c0d1e2f3a', 600.00, 15),
('88f1a2b3-c4d5-4e6f-7a8b-9c0d1e2f3a4b', 150.00, 60),
('99a2b3c4-d5e6-4f7a-8b9c-0d1e2f3a4b5c', 350.00, 40),
('00b3c4d5-e6f7-4a8b-9c0d-1e2f3a4b5c6d', 200.00, 80);

-- ----------------------------------------------------------------------------------
-- Services (Child Table - 10 Instances)
-- ----------------------------------------------------------------------------------
INSERT INTO services (id, status) VALUES
('aa112233-4455-6677-8899-aabbccddeeff', 'ACTIVE'),
('bb223344-5566-7788-99aa-bbccddeeff00', 'ACTIVE'),
('cc334455-6677-8899-aabb-ccddeeff0011', 'ACTIVE'),
('dd445566-7788-99aa-bbcc-ddeeff001122', 'DOWN'),
('ee556677-8899-aabb-ccdd-eeff00112233', 'ACTIVE'),
('ff667788-99aa-bbcc-ddee-ff0011223344', 'ACTIVE'),
('00778899-aabb-ccdd-eeff-001122334455', 'DOWN'),
('118899aa-bbcc-ddee-ff00-112233445566', 'ACTIVE'),
('2299aabb-ccdd-eeff-0011-223344556677', 'ACTIVE'),
('33aabbcc-ddee-ff00-1122-334455667788', 'ACTIVE');


-- ==================================================================================
-- 2. CUSTOMERS (10 Instances)
-- ==================================================================================

INSERT INTO customers (id, name, currency, email, phone_number) VALUES
('9a8b7c6d-5e4f-3a2b-1c0d-9e8f7a6b5c4d', 'TechCorp Solutions', 'USD', 'contact@techcorp.com', '0722145678'),
('8b7c6d5e-4f3a-2b1c-0d9e-8f7a6b5c4d3e', 'GreenLeaf Energy', 'EUR', 'info@greenleaf.eu', '0745982103'),
('7c6d5e4f-3a2b-1c0d-9e8f-7a6b5c4d3e2f', 'Rapid Logistics', 'GBP', 'support@rapidlog.uk', '0766334455'),
('6d5e4f3a-2b1c-0d9e-8f7a-6b5c4d3e2f1a', 'Alpha Medical', 'USD', 'sales@alphamed.com', '0771556677'),
('5e4f3a2b-1c0d-9e8f-7a6b-5c4d3e2f1a0b', 'BlueSky Aviation', 'EUR', 'ops@bluesky.com', '0733448899'),
('4f3a2b1c-0d9e-8f7a-6b5c-4d3e2f1a0b9c', 'Urban Construction', 'RON', 'office@urbanconst.ro', '0755112233'),
('3a2b1c0d-9e8f-7a6b-5c4d-3e2f1a0b9c8d', 'Quantum Finance', 'USD', 'hello@quantumfi.com', '0729887766'),
('2b1c0d9e-8f7a-6b5c-4d3e-2f1a0b9c8d7e', 'Solaris Power', 'EUR', 'contact@solaris.eu', '0740554433'),
('1c0d9e8f-7a6b-5c4d-3e2f-1a0b9c8d7e6f', 'Nordic Designs', 'SEK', 'design@nordic.se', '0770123987'),
('0d9e8f7a-6b5c-4d3e-2f1a-0b9c8d7e6f5a', 'Pacific Imports', 'USD', 'trade@pacific.com', '0761234509');

-- ==================================================================================
-- 3. CONTRACTS (10 Instances - 1 per Customer minimum)
-- ==================================================================================

INSERT INTO contracts (id, name, contract_type_id, status, creation_date, expiration_date, customer_id) VALUES
-- TechCorp (Active)
('c01a1b2c-3d4e-5f67-8901-23456789abcd', 'TechCorp 2025 MSA', 'b2c3d4e5-f678-9012-3456-789abcdef012', 'ACTIVE', '2025-01-01', '2026-01-01', '9a8b7c6d-5e4f-3a2b-1c0d-9e8f7a6b5c4d'),
-- GreenLeaf (Active)
('c02b2c3d-4e5f-6789-0123-456789abcdef', 'GreenLeaf Supply Agmt', 'd4e5f678-9012-3456-7890-abcdef012345', 'ACTIVE', '2025-02-01', '2026-02-01', '8b7c6d5e-4f3a-2b1c-0d9e-8f7a6b5c4d3e'),
-- Rapid Logistics (Active)
('c03c3d4e-5f67-8901-2345-6789abcdef01', 'RapidLog Service Level', 'e5f67890-1234-5678-9012-bcdef0123456', 'ACTIVE', '2025-03-01', '2026-03-01', '7c6d5e4f-3a2b-1c0d-9e8f-7a6b5c4d3e2f'),
-- Alpha Medical (Down)
('c04d4e5f-6789-0123-4567-89abcdef0123', 'Alpha Med Expired', 'b2c3d4e5-f678-9012-3456-789abcdef012', 'DOWN', '2023-01-01', '2024-01-01', '6d5e4f3a-2b1c-0d9e-8f7a-6b5c4d3e2f1a'),
-- Alpha Medical (Active - Replacement)
('c05e5f67-8901-2345-6789-abcdef012345', 'Alpha Med 2025 Renewal', 'b2c3d4e5-f678-9012-3456-789abcdef012', 'ACTIVE', '2025-01-15', '2026-01-15', '6d5e4f3a-2b1c-0d9e-8f7a-6b5c4d3e2f1a'),
-- BlueSky (Active)
('c06f6789-0123-4567-8901-cdef01234567', 'BlueSky Maintenance', 'f6789012-3456-7890-1234-cdef01234567', 'ACTIVE', '2025-04-01', '2026-04-01', '5e4f3a2b-1c0d-9e8f-7a6b-5c4d3e2f1a0b'),
-- Urban Const (Active)
('c0701234-5678-9012-3456-def012345678', 'Urban Construction B2B', 'a1b2c3d4-e5f6-7890-1234-56789abcdef0', 'ACTIVE', '2025-05-01', '2026-05-01', '4f3a2b1c-0d9e-8f7a-6b5c-4d3e2f1a0b9c'),
-- Quantum (Active)
('c0812345-6789-0123-4567-ef0123456789', 'Quantum IT Support', 'c3d4e5f6-7890-1234-5678-9abcdef01234', 'ACTIVE', '2025-06-01', '2026-06-01', '3a2b1c0d-9e8f-7a6b-5c4d-3e2f1a0b9c8d'),
-- Solaris (Active)
('c0923456-7890-1234-5678-f0123456789a', 'Solaris Framework', '07890123-4567-8901-2345-def012345678', 'ACTIVE', '2025-07-01', '2026-07-01', '2b1c0d9e-8f7a-6b5c-4d3e-2f1a0b9c8d7e'),
-- Nordic (Active)
('c1034567-8901-2345-6789-0123456789ab', 'Nordic Design Retainer', '30123456-7890-1234-5678-0123456789ab', 'ACTIVE', '2025-08-01', '2026-08-01', '1c0d9e8f-7a6b-5c4d-3e2f-1a0b9c8d7e6f'),
-- Pacific (Down)
('c1145678-9012-3456-7890-123456789abc', 'Pacific Old Deal', 'd4e5f678-9012-3456-7890-abcdef012345', 'DOWN', '2022-01-01', '2022-12-31', '0d9e8f7a-6b5c-4d3e-2f1a-0b9c8d7e6f5a');


-- ==================================================================================
-- 4. ORDERS (12 Instances)
-- ==================================================================================

INSERT INTO orders (id, name, customer_id, contract_id) VALUES
-- TechCorp (2 Orders)
('o01a1b2c-3d4e-5f67-8901-23456789abcd', 'TechCorp PO #1001', '9a8b7c6d-5e4f-3a2b-1c0d-9e8f7a6b5c4d', 'c01a1b2c-3d4e-5f67-8901-23456789abcd'),
('o02b2c3d-4e5f-6789-0123-456789abcdef', 'TechCorp PO #1002', '9a8b7c6d-5e4f-3a2b-1c0d-9e8f7a6b5c4d', 'c01a1b2c-3d4e-5f67-8901-23456789abcd'),

-- GreenLeaf (1 Order)
('o03c3d4e-5f67-8901-2345-6789abcdef01', 'GreenLeaf Initial Stock', '8b7c6d5e-4f3a-2b1c-0d9e-8f7a6b5c4d3e', 'c02b2c3d-4e5f-6789-0123-456789abcdef'),

-- Rapid Logistics (1 Order)
('o04d4e5f-6789-0123-4567-89abcdef0123', 'Rapid Fleet Upgrade', '7c6d5e4f-3a2b-1c0d-9e8f-7a6b5c4d3e2f', 'c03c3d4e-5f67-8901-2345-6789abcdef01'),

-- Alpha Medical (1 Order on NEW Contract)
('o05e5f67-8901-2345-6789-abcdef012345', 'Alpha Med Supplies', '6d5e4f3a-2b1c-0d9e-8f7a-6b5c4d3e2f1a', 'c05e5f67-8901-2345-6789-abcdef012345'),

-- BlueSky (2 Orders)
('o06f6789-0123-4567-8901-cdef01234567', 'BlueSky Q1', '5e4f3a2b-1c0d-9e8f-7a6b-5c4d3e2f1a0b', 'c06f6789-0123-4567-8901-cdef01234567'),
('o0701234-5678-9012-3456-def012345678', 'BlueSky Q2', '5e4f3a2b-1c0d-9e8f-7a6b-5c4d3e2f1a0b', 'c06f6789-0123-4567-8901-cdef01234567'),

-- Urban Const (1 Order)
('o0812345-6789-0123-4567-ef0123456789', 'Urban Site Equip', '4f3a2b1c-0d9e-8f7a-6b5c-4d3e2f1a0b9c', 'c0701234-5678-9012-3456-def012345678'),

-- Quantum (1 Order)
('o0923456-7890-1234-5678-f0123456789a', 'Quantum Server Rack', '3a2b1c0d-9e8f-7a6b-5c4d-3e2f1a0b9c8d', 'c0812345-6789-0123-4567-ef0123456789'),

-- Solaris (1 Order)
('o1034567-8901-2345-6789-0123456789ab', 'Solaris PV Cells', '2b1c0d9e-8f7a-6b5c-4d3e-2f1a0b9c8d7e', 'c0923456-7890-1234-5678-f0123456789a'),

-- Nordic (1 Order)
('o1145678-9012-3456-7890-123456789abc', 'Nordic Office Setup', '1c0d9e8f-7a6b-5c4d-3e2f-1a0b9c8d7e6f', 'c1034567-8901-2345-6789-0123456789ab'),

-- Pacific (0 Orders because contract is DOWN)
-- Note: Logic prevents creating orders on DOWN contracts.

-- Extra Order for TechCorp
('o1256789-0123-4567-8901-23456789abcd', 'TechCorp Emergency', '9a8b7c6d-5e4f-3a2b-1c0d-9e8f7a6b5c4d', 'c01a1b2c-3d4e-5f67-8901-23456789abcd');


-- ==================================================================================
-- 5. CONTRACT LINES (11 Instances)
-- ==================================================================================
INSERT INTO contract_lines (id, contract_id, item_id, unit_id, quantity) VALUES
('cl01a1b2-c3d4-e5f6-7890-123456789abc', 'c01a1b2c-3d4e-5f67-8901-23456789abcd', '11e4d3f5-a6b7-4c8d-9e0f-1a2b3c4d5e6f', '85678901-cdef-0123-4567-89abcdef0123', 50.0), -- TechCorp Laptops
('cl02b2c3-d4e5-f678-9012-3456789abcde', 'c02b2c3d-4e5f-6789-0123-456789abcdef', '22f5e4a6-b7c8-4d9e-0f1a-2b3c4d5e6f7a', '85678901-cdef-0123-4567-89abcdef0123', 100.0), -- GreenLeaf iPhones
('cl03c3d4-e5f6-7890-1234-56789abcdef0', 'c03c3d4e-5f67-8901-2345-6789abcdef01', 'aa112233-4455-6677-8899-aabbccddeeff', 'b8901234-f012-3456-7890-bcdef0123456', 500.0), -- Rapid Cleaning Hrs
('cl04d4e5-f678-9012-3456-789abcdef012', 'c05e5f67-8901-2345-6789-abcdef012345', 'bb223344-5566-7788-99aa-bbccddeeff00', 'd0123456-1234-5678-9012-def012345678', 12.0), -- Alpha Med IT Support Months
('cl05e5f6-7890-1234-5678-9abcdef01234', 'c06f6789-0123-4567-8901-cdef01234567', 'ee556677-8899-aabb-ccdd-eeff00112233', 'b8901234-f012-3456-7890-bcdef0123456', 200.0), -- BlueSky Security Audit
('cl06f678-9012-3456-7890-abcdef012345', 'c0701234-5678-9012-3456-def012345678', '66d9e0f1-a2b3-4c4d-5e6f-7a8b9c0d1e2f', '85678901-cdef-0123-4567-89abcdef0123', 20.0), -- Urban Chairs
('cl070123-4567-8901-2345-bcdef0123456', 'c0812345-6789-0123-4567-ef0123456789', 'ff667788-99aa-bbcc-ddee-ff0011223344', 'c9012345-0123-4567-8901-cdef01234567', 30.0), -- Quantum Dev Days
('cl081234-5678-9012-3456-cdef01234567', 'c0923456-7890-1234-5678-f0123456789a', '118899aa-bbcc-ddee-ff00-112233445566', 'b8901234-f012-3456-7890-bcdef0123456', 40.0), -- Solaris Training Hrs
('cl092345-6789-0123-4567-def012345678', 'c1034567-8901-2345-6789-0123456789ab', '77e0f1a2-b3c4-4d5e-6f7a-8b9c0d1e2f3a', '85678901-cdef-0123-4567-89abcdef0123', 10.0), -- Nordic Desks
('cl103456-7890-1234-5678-ef0123456789', 'c01a1b2c-3d4e-5f67-8901-23456789abcd', '33a6b7c8-d9e0-4f1a-2b3c-4d5e6f7a8b9c', '85678901-cdef-0123-4567-89abcdef0123', 100.0),-- TechCorp Mice
('cl114567-8901-2345-6789-f0123456789a', 'c05e5f67-8901-2345-6789-abcdef012345', '44b7c8d9-e0f1-4a2b-3c4d-5e6f7a8b9c0d', '85678901-cdef-0123-4567-89abcdef0123', 5.0); -- Alpha Monitors


-- ==================================================================================
-- 6. ORDER LINES (13 Instances)
-- ==================================================================================
INSERT INTO order_lines (id, order_id, item_id, unit_id, quantity) VALUES
('ol01a1b2-c3d4-e5f6-7890-123456789abc', 'o01a1b2c-3d4e-5f67-8901-23456789abcd', '11e4d3f5-a6b7-4c8d-9e0f-1a2b3c4d5e6f', '85678901-cdef-0123-4567-89abcdef0123', 2.0), -- TechCorp Order 1 (2 Laptops)
('ol02b2c3-d4e5-f678-9012-3456789abcde', 'o01a1b2c-3d4e-5f67-8901-23456789abcd', '33a6b7c8-d9e0-4f1a-2b3c-4d5e6f7a8b9c', '85678901-cdef-0123-4567-89abcdef0123', 5.0), -- TechCorp Order 1 (5 Mice)

('ol03c3d4-e5f6-7890-1234-56789abcdef0', 'o02b2c3d-4e5f-6789-0123-456789abcdef', '11e4d3f5-a6b7-4c8d-9e0f-1a2b3c4d5e6f', '85678901-cdef-0123-4567-89abcdef0123', 1.0), -- TechCorp Order 2 (1 Laptop)

('ol04d4e5-f678-9012-3456-789abcdef012', 'o03c3d4e-5f67-8901-2345-6789abcdef01', '22f5e4a6-b7c8-4d9e-0f1a-2b3c4d5e6f7a', '85678901-cdef-0123-4567-89abcdef0123', 10.0), -- GreenLeaf Order (10 Phones)

('ol05e5f6-7890-1234-5678-9abcdef01234', 'o04d4e5f-6789-0123-4567-89abcdef0123', 'aa112233-4455-6677-8899-aabbccddeeff', 'b8901234-f012-3456-7890-bcdef0123456', 20.0), -- Rapid Order (20 Hours Cleaning)

('ol06f678-9012-3456-7890-abcdef012345', 'o05e5f67-8901-2345-6789-abcdef012345', 'bb223344-5566-7788-99aa-bbccddeeff00', 'd0123456-1234-5678-9012-def012345678', 1.0), -- Alpha Order (1 Month IT)
('ol070123-4567-8901-2345-bcdef0123456', 'o05e5f67-8901-2345-6789-abcdef012345', '44b7c8d9-e0f1-4a2b-3c4d-5e6f7a8b9c0d', '85678901-cdef-0123-4567-89abcdef0123', 2.0), -- Alpha Order (2 Monitors)

('ol081234-5678-9012-3456-cdef01234567', 'o06f6789-0123-4567-8901-cdef01234567', 'ee556677-8899-aabb-ccdd-eeff00112233', 'b8901234-f012-3456-7890-bcdef0123456', 50.0), -- BlueSky Order 1 (50 Hrs Audit)
('ol092345-6789-0123-4567-def012345678', 'o0701234-5678-9012-3456-def012345678', 'ee556677-8899-aabb-ccdd-eeff00112233', 'b8901234-f012-3456-7890-bcdef0123456', 15.0), -- BlueSky Order 2 (15 Hrs Audit)

('ol103456-7890-1234-5678-ef0123456789', 'o0812345-6789-0123-4567-ef0123456789', '66d9e0f1-a2b3-4c4d-5e6f-7a8b9c0d1e2f', '85678901-cdef-0123-4567-89abcdef0123', 5.0),  -- Urban Order (5 Chairs)

('ol114567-8901-2345-6789-f0123456789a', 'o0923456-7890-1234-5678-f0123456789a', 'ff667788-99aa-bbcc-ddee-ff0011223344', 'c9012345-0123-4567-8901-cdef01234567', 10.0), -- Quantum Order (10 Days Dev)

('ol125678-9012-3456-7890-0123456789ab', 'o1034567-8901-2345-6789-0123456789ab', '118899aa-bbcc-ddee-ff00-112233445566', 'b8901234-f012-3456-7890-bcdef0123456', 8.0),  -- Solaris Order (8 Hours Training)

('ol136789-0123-4567-8901-123456789abc', 'o1145678-9012-3456-7890-123456789abc', '77e0f1a2-b3c4-4d5e-6f7a-8b9c0d1e2f3a', '85678901-cdef-0123-4567-89abcdef0123', 2.0);  -- Nordic Order (2 Desks)