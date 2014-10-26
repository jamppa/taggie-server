(ns taggie-server.routes.auth-test
    (:use
        midje.sweet
        taggie-server.routes.auth))

(def user {:_id "123abc"})

(fact "should create authentication token from user"
  (:token (token user)) => (contains "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."))

(fact "should verify authentication successfully for valid token"
  (valid? "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMjNhYmMiLCJpc3MiOiJ0YWdnaWUiLCJpYXQiOjE0MTQyNTM5MTV9.guozhZZ36JSN6ae-xycGf3KcrtQxiP3ZI730VyrqTEA") => true)