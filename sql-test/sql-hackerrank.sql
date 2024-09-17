SELECT
  any_value(w.address) as address,
  count(t.amount) as transactions,
  sum(t.amount) as balance
FROM wallets w
inner join transactions t on w.id = t.wallet_id
where t.confirmations >= 10
group by t.wallet_id
having balance > 0
order by balance DESC;


select 
  c.mac_address,
  count(s.client_id) as streams,
  sum(s.traffic) as total_traffic
from clients c
inner join streams s on c.id = s.client_id
where replace(quality,'p','') >= 720
group by s.client_id
order by total_traffic desc;