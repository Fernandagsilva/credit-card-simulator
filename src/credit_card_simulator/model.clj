(ns credit-card-simulator.model
  (:import [java.util UUID]))

(defn new-customer [name cpf email]
  {:customer/id  (UUID/randomUUID)
   :customer/name  name
   :customer/cpf   cpf
   :customer/email email})

(defn new-credit-card [number cvv validity limit customer-id]
  {:credit-card/id      (UUID/randomUUID)
   :credit-card/number    number
   :credit-card/cvv       cvv
   :credit-card/validity  validity
   :credit-card/limit     limit
   :credit-card/customer-id customer-id})

(defn new-transaction [date cost establishment category customer-id credit-card-id]
  {:transaction/id           (UUID/randomUUID)
   :transaction/date           date
   :transaction/cost           cost
   :transaction/establishment  establishment
   :transaction/category       category
   :transaction/customer-id    customer-id
   :transaction/credit-card-id credit-card-id})
