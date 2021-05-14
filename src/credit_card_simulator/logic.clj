(ns credit-card-simulator.logic)

(defn total-cost-group [[category purchases-category]] =
    (println "Category:" category "Cost:" (reduce + (map :cost purchases-category))))

(defn total-cost-category [[client-id purchases]]
  (println "Client id:" client-id
    (->> purchases
         (group-by :category)
         (map total-cost-group))))

(defn total-cost-client-category [purchases]
  (->> purchases
       (group-by :client-id)
       (map total-cost-category)))

(defn total-billing-client-month [[client-id client-purchases]]
  (println "Cliente id:" client-id "Total billing cost:" (reduce + (map :cost client-purchases))))


(defn total-billing [purchases]
  (->> purchases
       (group-by :client-id)
       (map total-billing-client-month)))


(defn search-purchase [purchase query]
  (or (= (get purchase :establishment) query)
      (= (get purchase :cost) query)))

(defn search-purchases [purchases query]
  (println (filterv #(search-purchase % query) purchases)))


