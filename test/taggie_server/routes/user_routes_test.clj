(ns taggie-server.routes.user-routes-test
	(:use
		midje.sweet
		ring.mock.request
		cheshire.core
		taggie-server.routes.user-routes)
	(:require [taggie-server.models.user :as user]))

(def new-user {:email "tepi@testeri.fi" :password "secret"})

(defn- taggie-request [method uri & payload]
	(-> (request method uri)
		(content-type "application/json")
        (merge {:params (first payload)})))

(fact "should save new user on user registration"
	(user-routes (taggie-request :post "/user" new-user)) => (contains {:status 201} {:body (generate-string new-user)})
	(provided
        (user/is-valid new-user) => true :times 1
		(user/save new-user) => new-user :times 1))

(def invalid-user (dissoc new-user :email))
(fact "should have malformed payload with invalid user"
    (user-routes (taggie-request :post "/user" invalid-user)) => (contains {:status 400})
        (provided
            (user/is-valid invalid-user) => false :times 1
            (user/save invalid-user) => invalid-user :times 0))