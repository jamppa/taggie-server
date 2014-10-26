(ns taggie-server.routes.user-routes-test
	(:use
		midje.sweet
		ring.mock.request
		cheshire.core
		taggie-server.routes.user-routes)
	(:require
    [taggie-server.models.user :as user]
    [taggie-server.routes.auth :as auth]))

(def new-user {:email "tepi@testeri.fi" :password "secret"})
(def token {:token "foobar"})

(defn- taggie-request [method uri & payload]
	(-> (request method uri)
		(content-type "application/json")
      (merge {:params (first payload)})))

(fact "should save new user on user registration and return token"
	(user-routes (taggie-request :post "/user" new-user)) => (contains {:status 201} {:body (generate-string token)})
    (provided
      (user/is-valid new-user) => true :times 1
		  (user/save new-user) => new-user :times 1
      (auth/token new-user) => token :times 1))

(def invalid-user (dissoc new-user :email))
(fact "should have malformed payload with invalid user"
  (user-routes (taggie-request :post "/user" invalid-user)) => (contains {:status 400})
    (provided
        (user/is-valid invalid-user) => false :times 1
        (user/save invalid-user) => invalid-user :times 0))

(fact "should login with correct credentials and return token"
  (user-routes (taggie-request :get "/user/foo@bar.fi/qwe123")) => (contains {:status 200} {:body (generate-string token)})
    (provided
      (user/find-by-email-and-pwd "foo@bar.fi" "qwe123") => new-user :times 1
      (auth/token new-user) => token :times 1))

(fact "should not login with incorrect credentials"
  (user-routes (taggie-request :get "/user/foo@bar.fi/qwe123")) => (contains {:status 401})
  (provided
    (user/find-by-email-and-pwd "foo@bar.fi" "qwe123") => nil :times 1))