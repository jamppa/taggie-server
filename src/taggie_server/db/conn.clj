(ns taggie-server.db.conn
	(:require [monger.core :as monger])
	(:use taggie-server.config))

(def ^:dynamic *db* (config :db :name))

(defn get-db []
	(let [conn (monger/connect)]
		(monger/get-db conn *db*)))