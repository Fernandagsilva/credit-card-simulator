(ns credit-card-simulator.model
  (:import [java.util UUID]))

(defn new-client [name cpf email]
  {:client/id  (UUID/randomUUID)
   :client/name  name
   :client/cpf   cpf
   :client/email email})

(defn new-credit-card [number cvv validity limit client-id]
  {:credit-card/id      (UUID/randomUUID)
   :credit-card/number    number
   :credit-card/cvv       cvv
   :credit-card/validity  validity
   :credit-card/limit     limit
   :credit-card/client-id client-id})

(defn new-transaction [date cost establishment category client-id credit-card-id]
  {:transaction/id           (UUID/randomUUID)
   :transaction/date           date
   :transaction/cost           cost
   :transaction/establishment  establishment
   :transaction/category       category
   :transaction/client-id      client-id
   :transaction/credit-card-id credit-card-id})
