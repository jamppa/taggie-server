(ns taggie-server.routes.user-routes
    (:use compojure.core)
    (:require
        [liberator.core :as liberator]
        [taggie-server.models.user :as user]))

(defn- register-user [ctx]
    (let [new-user (get-in ctx [:request :params])]
        {:user (user/save new-user)}))

(defn- malformed-user? [ctx]
    (let [new-user (get-in ctx [:request :params])]
        (not (user/is-valid new-user))))

(liberator/defresource register-user-resource
    :available-media-types ["application/json"]
    :allowed-methods [:post]
    :post! register-user
    :malformed? malformed-user?
    :handle-created (fn [ctx] (get-in ctx [:user])))

(defroutes user-routes
    (POST "/user" [] register-user-resource))
