(ns taggie-server.models.user-test
	(:use midje.sweet)
	(:require
		[taggie-server.models.user :as user]
		[taggie-server.db.init :as db]))

(with-state-changes [(before :facts (db/reload-test-db))]

(fact "should find user by id"
	(user/find-by-id "509d513f61395f0ebbd5e32a") => db/user-teppo)

)
