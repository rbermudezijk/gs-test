-- CASE 1 will work the next query?
select * from user, user_session;

-- CASE 2 duplicated values
SELECT
 username,
 count(*) as counter
FROM user GROUP BY username, email
HAVING counter > 1;

delete from user where id not in (
  select max(id) from user group by username, email
);

(SELECT * FROM user ORDER BY id ASC LIMIT 1)
UNION ALL
(SELECT * FROM user ORDER BY id DESC LIMIT 1);

select * from user where id in (
  (SELECT max(id) FROM user) UNION ALL (SELECT min(id) FROM user)
);


-- CASE 3
-- table #4 inventory, product_name, category, price
-- Question: we need the 3 products with highest price in each category

SELECT category FROM inventory GROUP BY category;

SELECT * FROM (
  SELECT
    category,
    price,
    ROW_NUMBER() OVER (PARTITION BY category ORDER BY price DESC) AS row_num -- dense_rank()
  FROM
    inventory;
) t  WHERE t.row_num <= 3;


-- Get product with maximum and minimum price from inventory table

select id, product_name, category, max(price) from inventory
union 
select id, product_name, category, min(price) from inventory;

SELECT *
FROM(
    SELECT
    employee.*,
    dense_rank() over(order by salary desc) as rank_desc,
    dense_rank() over(order by salary asc) as rank_asc
    from employee
) ranked
WHERE ranked.rank_desc = 1 OR ranked.rank_asc = 1;