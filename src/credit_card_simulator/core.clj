(ns credit-card-simulator.core
  (:require  [credit-card-simulator.logic :as logic]
             [credit-card-simulator.data :as db]))

;Representação dos dados do cliente (nome, cpf, email);
;Representação dos dados do cartão (número, cvv, validade, limite);
;Listagem de compras realizadas (data, valor, estabelecimento, categoria);
;Valor dos gastos agrupados por categoria.

(db/create-data)

(println "All purchases:")
(println (db/get-purchases))

(println "Total cost client by category:")
(println(logic/total-cost-client-category (db/get-purchases)))

(println "Total client billing month:")
(println (logic/total-billing (db/get-purchases)))

(println "Search by establishment or cost:")
(logic/search-purchases (db/get-purchases) "Ifood")
(logic/search-purchases (db/get-purchases) 150)


(db/clear-datas)



