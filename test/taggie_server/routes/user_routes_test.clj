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
		(user/save new-user) => new-user :times 1))