(ns credit-card-simulator.core
  (:use clojure.pprint)
  (:require [credit-card-simulator.logic :as logic]
            [credit-card-simulator.data :as db]
            [credit-card-simulator.model :as model]
            [datomic.api :as d]))

;(db/delete-database)
(def conn (db/open-connection))

(db/create-schema conn)

(let [
      customer1 (model/new-customer "Fernanda Gonçalves da Silva" "243.172.270-88" "fernanda@teste.com")
      customer2 (model/new-customer "Maria Gonçalves da Silva" "055.768.180-40" "maria@teste.com")
      customer3 (model/new-customer "Joao Gonçalves da Silva" "231.722.370-60" "joao@teste.com")
      credit-card1 (model/new-credit-card
                     "5251 5906 1395 0612" 431 "13/02/2023" 7000M (get customer1 :customer/id))
      credit-card2 (model/new-credit-card
                     "5294 9627 6139 5933" 865 "13/03/2022" 3000M (get customer2 :customer/id))
      credit-card3 (model/new-credit-card
                     "5155 9819 7190 9695" 461 "13/02/2022" 4000M (get customer3 :customer/id))
      transaction1 (model/new-transaction
                      "11/05/2021" 100M "Ifood" "foods" (get customer1 :customer/id) (get credit-card1 :credit-card/id))
      transaction2 (model/new-transaction
                   "11/05/2021" 6000M "Apple" "phones" (get customer1 :customer/id) (get credit-card1 :credit-card/id))
      transaction3  (model/new-transaction
                   "11/05/2021" 50M "Ifood" "foods" (get customer2 :customer/id) (get credit-card2 :credit-card/id))
      transaction4 (model/new-transaction
                   "11/05/2021" 600M "Adidas" "shoes" (get customer2 :customer/id) (get credit-card2 :credit-card/id))
      transaction5 (model/new-transaction
                   "11/05/2021" 150M "Ifood" "foods" (get customer3 :customer/id) (get credit-card3 :credit-card/id))
      transaction6 (model/new-transaction
                   "11/05/2021" 350M "Saraiva" "books" (get customer3 :customer/id) (get credit-card3 :credit-card/id))
      customers-result @(d/transact conn [customer1 customer2 customer3])
      id-first-customer (-> customers-result :tempids vals first)
      credit-cards-result @(d/transact conn [credit-card1 credit-card2 credit-card3])
      transactions-result @(d/transact conn [transaction1 transaction2 transaction3 transaction4 transaction5 transaction6])]

  ;(pprint @(d/transact conn [[:db/retract id-first-customer :customer/name "Fernanda Gonçalves da Silva"]]))
  (pprint @(d/transact conn [[:db/add id-first-customer :customer/name "Fernanda Gonçalves da Silva"]])))



(def database (d/db conn))
(pprint "---------------- Transactions --------------------")
(pprint (db/find-all database))



