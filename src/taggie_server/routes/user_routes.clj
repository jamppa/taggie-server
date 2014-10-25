(ns taggie-server.routes.user-routes
    (:use 
        compojure.core
        taggie-server.routes.util)
    (:require
        [liberator.core :as liberator]
        [taggie-server.models.user :as user]))

(defn- register-user [ctx]
    (let [new-user (payload-in-ctx ctx)]
        {:user (user/save new-user)}))

(defn- malformed-user? [ctx]
    (let [new-user (payload-in-ctx ctx)]
        (not (user/is-valid new-user))))

(liberator/defresource register-user-resource
    :available-media-types ["application/json"]
    :allowed-methods [:post]
    :post! register-user
    :malformed? malformed-user?
    :handle-created user-in-ctx)

(defroutes user-routes
    (POST "/user" [] register-user-resource))
