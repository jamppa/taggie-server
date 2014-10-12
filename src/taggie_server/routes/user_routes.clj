(ns taggie-server.routes.user-routes
	(:use compojure.core)
	(:require
		[liberator.core :as liberator]
		[taggie-server.models.user :as user]))

(defn- register-user [ctx]
	(let [new-user (get-in ctx [:request :params])]
		(user/save new-user)))

(liberator/defresource register-user-resource
	:available-media-types ["application/json"]
	:allowed-methods [:post]
	:post! register-user)

(defroutes user-routes
	(POST "/user" [] register-user-resource))
