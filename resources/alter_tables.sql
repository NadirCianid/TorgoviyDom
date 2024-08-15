
ALTER TABLE warehouse_product
    ADD CONSTRAINT uq_warehouse_product UNIQUE (warehouse_id, product_id);