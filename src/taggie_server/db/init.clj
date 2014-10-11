(ns taggie-server.db.init
	(:use
		taggie-server.db.conn
		taggie-server.config)
	(:require
		[monger.db :as mongerdb]
		[monger.collection :as mongercoll])
	(:import [org.bson.types ObjectId]))

(def user-teppo {:_id (ObjectId. "509d513f61395f0ebbd5e32a") :email "teppo@testaaja.fi" :password "secret"})

(defn- insert-docs [coll docs]
	(doseq [doc docs]
		(mongercoll/insert (get-db) coll doc)))

(defn- drop-db []
	(mongerdb/drop-db (get-db)))

(defn- setup-db []
	(insert-docs "users" [user-teppo]))

(defn- reload-db []
	(drop-db)
	(setup-db))

(defn reload-test-db []
    (set-test-db!)
    (reload-db))