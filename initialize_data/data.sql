-- It will also insert a single row into the table with `id` set to NULL, `create_date` and `update_date` set to the current timestamp, `department` set to 'IT', and `name` set to 'John Doe'.
-- If the table already exists, the row will not be inserted.
INSERT INTO `employee_tbl` (`id`, `create_date`, `update_date`, `department`, `name`)
SELECT NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'IT', 'John Doe'
FROM DUAL
WHERE NOT EXISTS (
    SELECT *
    FROM `employee_tbl`
);
