(ns taggie-server.db.conn
	(:require [monger.core :as monger])
	(:use taggie-server.config))

(def db (atom ""))

(defn set-db! []
	(reset! db (config :db :name)))

(defn set-test-db! []
	(reset! db (config :db :name-test)))

(defn get-db []
	(let [conn (monger/connect)]
		(monger/get-db conn @db)))