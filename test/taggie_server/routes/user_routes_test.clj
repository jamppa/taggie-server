(ns taggie-server.routes.user-routes-test
	(:use
		midje.sweet
		ring.mock.request
		cheshire.core
		taggie-server.routes.user-routes)
	(:require [taggie-server.models.user :as user]))

(def saved-user {:email "tepi@testeri.fi" :password "secret"})

(defn- taggie-request [method uri]
	(-> (request method uri)
		(content-type "application/json")))

(fact "should save new user on user registration"
	(user-routes (taggie-request :post "/user")) => (contains {:status 201} {:body (generate-string saved-user)})
	(provided
		(user/save anything) => saved-user :times 1))