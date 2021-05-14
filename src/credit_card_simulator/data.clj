(ns credit-card-simulator.data
  (:import [java.util UUID]))

(def clients [])
(def purchases [])
(def credit-cards [])

(defn add-client! [client]
  (def clients (conj clients client))
  client)

(defn add-credit-card! [credit-card]
  (def credit-cards (conj credit-cards credit-card))
  credit-card)

(defn add-purchase! [purchase]
  (def purchases (conj purchases purchase))
  purchase)

(defn get-purchases []
  purchases)

(defn new-client [name cpf email]
  (add-client!
    {:uuid  (UUID/randomUUID)
     :name  name
     :cpf   cpf
     :email email}))

(defn new-credit-card [number cvv validity limit client-id]
  (add-credit-card!
    {:uuid      (UUID/randomUUID)
     :number    number
     :cvv       cvv
     :validity  validity
     :limit     limit
     :client-id client-id}))

(defn new-purchase [date cost establishment category client-id credit-card-id]
  (add-purchase!
    {:uuid           (UUID/randomUUID)
     :date           date
     :cost           cost
     :establishment  establishment
     :category       category
     :client-id      client-id
     :credit-card-id credit-card-id}))


(defn create-data []

  ;clients
  (def client1 (new-client "Fernanda Gonçalves da Silva" "243.172.270-88" "fernanda@teste.com"))
  (def client2 (new-client "Fernanda Gonçalves da Silva" "055.768.180-40" "maria@teste.com"))
  (def client3 (new-client "Fernanda Gonçalves da Silva" "231.722.370-60" "joao@teste.com"))

  ;credit-cards
  (def credit-card1 (new-credit-card
                      "5251 5906 1395 0612" 431 "13/02/2023" 7000 (get client1 :uuid)))
  (def credit-card2 (new-credit-card
                      "5294 9627 6139 5933" 865 "13/03/2022" 3000 (get client2 :uuid)))
  (def credit-card3 (new-credit-card
                      "5155 9819 7190 9695" 461 "13/02/2022" 4000 (get client3 :uuid)))

  ;purchases
  (new-purchase
    "11/05/2021" 100 "Ifood" "foods" (get client1 :uuid) (get credit-card1 :uuid))
  (new-purchase
    "11/05/2021" 6000 "Apple" "phones" (get client1 :uuid) (get credit-card1 :uuid))
  (new-purchase
    "11/05/2021" 50 "Ifood" "foods" (get client2 :uuid) (get credit-card2 :uuid))
  (new-purchase
    "11/05/2021" 600 "Adidas" "shoes" (get client2 :uuid) (get credit-card2 :uuid))
  (new-purchase
    "11/05/2021" 150 "Ifood" "foods" (get client3 :uuid) (get credit-card3 :uuid))
  (new-purchase
    "11/05/2021" 350 "Saraiva" "books" (get client3 :uuid) (get credit-card3 :uuid)))

(defn clear-data []
  (def clients [])
  (def purchases [])
  (def credit-cards []))

