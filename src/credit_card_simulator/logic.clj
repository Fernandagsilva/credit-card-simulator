(ns credit-card-simulator.logic)

(defn total-cost-group [[category transactions-category]] =
    (println "Category:" category "Cost:" (reduce + (map :cost transactions-category))))

(defn total-cost-category [[client-id transactions]]
  (println "Client id:" client-id
    (->> transactions
         (group-by :category)
         (map total-cost-group))))

(defn total-cost-client-category [transactions]
  (->> transactions
       (group-by :client-id)
       (map total-cost-category)))

(defn total-billing-client-month [[client-id client-transactions]]
  (println "Cliente id:" client-id "Total billing cost:" (reduce + (map :cost client-transactions))))


(defn total-billing [transactions]
  (->> transactions
       (group-by :client-id)
       (map total-billing-client-month)))


(defn search-transaction [transaction query]
  (or (= (get transaction :establishment) query)
      (= (get transaction :cost) query)))

(defn search-transactions [transactions query]
  (println (filterv #(search-transaction % query) transactions)))


