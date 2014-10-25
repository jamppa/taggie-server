(ns taggie-server.routes.auth
    (:use
      clj-jwt.core)
    (:require [clj-time.core :refer [now]]))

(def secret "kikkeliskokkelisnii")

(defn- claim [user]
  (merge {:iss "taggie" :iat (now)} user))

(defn auth [user]
    {:token (-> (claim user) jwt (sign :HS256 secret) to-str)})