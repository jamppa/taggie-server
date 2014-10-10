(ns taggie-server.db.conn-test
	(:use
		midje.sweet
		taggie-server.db.conn)
	(:require [monger.core :as monger]))

(def db {})

(fact "should get database"
	(get-db) => db
	(provided 
		(monger/connect) => anything :times 1
		(monger/get-db anything *db*) => db :times 1))

