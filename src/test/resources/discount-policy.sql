-- Absolute discount
INSERT INTO discount_policies (id, unit_threshold , absolute_discount, percentage_discount)
VALUES
    ('550e8400-e29b-41d4-a716-446655440000', 10, 50.00, NULL);

-- Percentage discount
INSERT INTO discount_policies (id, unit_threshold, absolute_discount, percentage_discount)
VALUES
    ('a8098c1a-f86e-11da-bd1a-00112444be1e', 20, NULL, 15);