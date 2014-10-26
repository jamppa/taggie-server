(ns taggie-server.routes.user-routes
    (:use 
        compojure.core
        taggie-server.routes.util)
    (:require
        [liberator.core :as liberator]
        [taggie-server.models.user :as user]
        [taggie-server.routes.auth :as auth]))

(defn- register-user [ctx]
    (let [new-user (payload-in-ctx ctx)]
        {:user (user/save new-user)}))

(defn- malformed-user? [ctx]
    (let [new-user (payload-in-ctx ctx)]
        (not (user/is-valid new-user))))

(defn- create-token [ctx]
    (auth/token (user-in-ctx ctx)))

(liberator/defresource register-user-resource
    :available-media-types ["application/json"]
    :allowed-methods [:post]
    :post! register-user
    :malformed? malformed-user?
    :handle-created create-token)

(defroutes user-routes
    (POST "/user" [] register-user-resource))
