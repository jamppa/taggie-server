(ns groupie-server.start
	(:require [org.httpkit.server :as httpkit])
	(:use groupie-server.handler))

(defonce server (atom nil))

(defn -main [& args]
	(println "Starting groupie server...")
	(reset! server (httpkit/run-server #'app {:port 3000})))