(ns taggie-server.routes.auth-test
    (:use
        midje.sweet
        taggie-server.routes.auth))

(def user {:_id "123abc"})

(fact "should create authentication token from user"
    (:token (auth user)) => (contains "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."))