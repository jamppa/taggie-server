(ns taggie-server.db.conn-test
	(:use
		midje.sweet
		taggie-server.db.conn)
	(:require [monger.core :as monger]))

(def mock-db {})

(background (before :facts (set-test-db!)))

(fact "should get database"
	(get-db) => mock-db
	(provided
    (db-host) => {:host "db"} :times 1
		(monger/connect {:host "db"}) => anything :times 1
		(monger/get-db anything "taggie-test") => mock-db :times 1))

