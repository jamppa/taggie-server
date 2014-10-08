(ns taggie-server.start
	(:require [org.httpkit.server :as httpkit])
	(:use taggie-server.handler))

(defonce server (atom nil))

(defn -main [& args]
	(println "Starting taggie server...")
	(reset! server (httpkit/run-server #'app {:port 3000})))