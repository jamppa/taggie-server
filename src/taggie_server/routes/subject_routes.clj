(ns taggie-server.routes.subject-routes
	(:use compojure.core)
	(:require [liberator.core :as liberator]))

(defroutes subject-routes
	(GET "/subject" []
		(liberator/resource
			:available-media-types ["application/json"]
			:handle-ok {:foo "bar"})))

