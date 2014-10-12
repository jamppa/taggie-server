(ns taggie-server.handler
	(:use
		taggie-server.routes.subject-routes
		taggie-server.routes.user-routes)
	(:require 
		[compojure.core :refer :all]
		[compojure.handler :as handler]
		[compojure.route :as route]
		[ring.middleware.format-params :refer [wrap-restful-params]]
		[ring.middleware.format-response :refer [wrap-restful-response]]))

(defroutes app-routes
	(context "/api" [] subject-routes user-routes)
	(route/not-found "Requested URL not found"))

(def app
	(->
		(handler/site app-routes)
		(wrap-restful-params)
		(wrap-restful-response)))
