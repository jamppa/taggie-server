(ns taggie-server.db.conn
	(:require 
    [monger.core :as monger]
    [environ.core :refer [env]])
	(:use taggie-server.config))

(def db (atom ""))

(defn set-db! []
	(reset! db (config :db :name)))

(defn set-test-db! []
	(reset! db (config :db :name-test)))

(defn db-host []
  {:host (env :db-port-27017-tcp-addr)})

(defn get-db []
	(let [conn (monger/connect (db-host))]
		(monger/get-db conn @db)))