(ns credit-card-simulator.logic)

(defn total-cost-group [[category transactions-category]] =
    (println "Category:" category "Cost:" (reduce + (map :cost transactions-category))))

(defn total-cost-category [[customer-id transactions]]
  (println "Customer id:" customer-id
    (->> transactions
         (group-by :category)
         (map total-cost-group))))

(defn total-cost-customer-category [transactions]
  (->> transactions
       (group-by :customer-id)
       (map total-cost-category)))

(defn total-billing-customer-month [[customer-id customer-transactions]]
  (println "Customer id:" customer-id "Total billing cost:" (reduce + (map :cost customer-transactions))))


(defn total-billing [transactions]
  (->> transactions
       (group-by :customer-id)
       (map total-billing-customer-month)))


(defn search-transaction [transaction query]
  (or (= (get transaction :establishment) query)
      (= (get transaction :cost) query)))

(defn search-transactions [transactions query]
  (println (filterv #(search-transaction % query) transactions)))


