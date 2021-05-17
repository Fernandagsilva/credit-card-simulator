(ns credit-card-simulator.data
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/client")

(defn open-connection []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn delete-database []
  (d/delete-database db-uri))

(def schema [{:db/ident       :client/id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/doc         "Client id"
              }
             {:db/ident       :client/name
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "Client name"
              }
             {:db/ident       :client/cpf
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "Client cpf"
              }
             {:db/ident        :client/email
              :db/valueType    :db.type/string
              :db/cardinality  :db.cardinality/one
              :db/doc          "Client email"
              }
             {:db/ident        :credit-card/id
              :db/valueType    :db.type/uuid
              :db/cardinality  :db.cardinality/one
              :db/doc          "Credit card id"
              }
             {:db/ident        :credit-card/number
              :db/valueType    :db.type/long
              :db/cardinality  :db.cardinality/one
              :db/doc          "Credit card number"
              }
             {:db/ident        :credit-card/cvv
              :db/valueType    :db.type/long
              :db/cardinality  :db.cardinality/one
              :db/doc          "Credit card cvv"
              }
             {:db/ident        :credit-card/validity
              :db/valueType    :db.type/string
              :db/cardinality  :db.cardinality/one
              :db/doc          "Credit card validity"
              }
             {:db/ident        :credit-card/limit
              :db/valueType    :db.type/bigdec
              :db/cardinality  :db.cardinality/one
              :db/doc          "Credit card limit"
              }
             {:db/ident        :credit-card/client-id
              :db/valueType    :db.type/uuid
              :db/cardinality  :db.cardinality/one
              :db/doc          "Credit card client id"
              }
             {:db/ident        :transaction/id
              :db/valueType    :db.type/uuid
              :db/cardinality  :db.cardinality/one
              :db/doc          "Transaction id"
              }
             {:db/ident        :transaction/date
              :db/valueType    :db.type/string
              :db/cardinality  :db.cardinality/one
              :db/doc          "Transaction date"
              }
             {:db/ident        :transaction/cost
              :db/valueType    :db.type/bigdec
              :db/cardinality  :db.cardinality/one
              :db/doc          "Transaction cost"
              }
             {:db/ident        :transaction/establishment
              :db/valueType    :db.type/string
              :db/cardinality  :db.cardinality/one
              :db/doc          "Transaction establishment"
              }
             {:db/ident        :transaction/category
              :db/valueType    :db.type/string
              :db/cardinality  :db.cardinality/one
              :db/doc          "Transaction category"
              }
             {:db/ident        :transaction/client-id
              :db/valueType    :db.type/uuid
              :db/cardinality  :db.cardinality/one
              :db/doc          "Transaction client id"
              }
             {:db/ident        :transaction/credit-card-id
              :db/valueType    :db.type/uuid
              :db/cardinality  :db.cardinality/one
              :db/doc          "Transaction credit card id"
              }])

(defn create-schema [conn]
  (d/transact conn schema)
  )

