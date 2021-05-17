(ns credit-card-simulator.core
  (:use clojure.pprint)
  (:require  [credit-card-simulator.logic :as logic]
             [credit-card-simulator.data :as db]
             [credit-card-simulator.model :as model]
             [datomic.api :as d]))

;(db/delete-database)
(def conn (db/open-connection))

(db/create-schema conn)

(let [
      client1 (model/new-client "Fernanda Gonçalves da Silva" "243.172.270-88" "fernanda@teste.com")
      client2 (model/new-client "Maria Gonçalves da Silva" "055.768.180-40" "maria@teste.com")
      client3 (model/new-client "Joao Gonçalves da Silva" "231.722.370-60" "joao@teste.com")
      credit-card1 (model/new-credit-card
                     "5251 5906 1395 0612" 431 "13/02/2023" 7000M (get client1 :client/id))
      credit-card2 (model/new-credit-card
                     "5294 9627 6139 5933" 865 "13/03/2022" 3000M (get client2 :client/id))
      credit-card3 (model/new-credit-card
                     "5155 9819 7190 9695" 461 "13/02/2022" 4000M (get client3 :client/id))
      transaction1 (model/new-transaction
                      "11/05/2021" 100M "Ifood" "foods" (get client1 :client/id) (get credit-card1 :credit-card/id))
      transaction2 (model/new-transaction
                   "11/05/2021" 6000M "Apple" "phones" (get client1 :client/id) (get credit-card1 :credit-card/id))
      transaction3  (model/new-transaction
                   "11/05/2021" 50M "Ifood" "foods" (get client2 :client/id) (get credit-card2 :credit-card/id))
      transaction4 (model/new-transaction
                   "11/05/2021" 600M "Adidas" "shoes" (get client2 :client/id) (get credit-card2 :credit-card/id))
      transaction5 (model/new-transaction
                   "11/05/2021" 150M "Ifood" "foods" (get client3 :client/id) (get credit-card3 :credit-card/id))
      transaction6 (model/new-transaction
                   "11/05/2021" 350M "Saraiva" "books" (get client3 :client/id) (get credit-card3 :credit-card/id))]

  (d/transact conn [client1 client2 client3])
  (d/transact conn [credit-card1 credit-card2 credit-card3])
  (d/transact conn [transaction1 transaction2 transaction3 transaction4 transaction5 transaction6]))

(def db (d/db conn))
(pprint "---------------- Transactions --------------------")
(pprint (d/q '[:find (pull ?id [*])
       :where [?id :transaction/id]] db))



