(ns taggie-server.models.user-test
	(:use midje.sweet)
	(:require
		[taggie-server.models.user :as user]
		[taggie-server.db.init :as db]))

(def new-user {:email "foo@bar.fi" :password "secret"})

(with-state-changes [(before :facts (db/reload-test-db))]

(fact "should find user by id"
	(user/find-by-id "509d513f61395f0ebbd5e32a") => db/user-teppo)

(fact "should save new user"
	(let [saved (user/save new-user)]
		(user/find-by-id (.toString (:_id saved))) => saved))

(fact "should find user by email and password"
	(user/find-by-email-and-pass "teppo@testaaja.fi" "secret") => db/user-teppo)

(fact "should not find non-existing user by email and password"
	(user/find-by-email-and-pass "foo@bar.fi" "secret") => nil)

)
