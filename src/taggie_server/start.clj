(ns taggie-server.start
	(:require
		[org.httpkit.server :as httpkit])
	(:use
		taggie-server.handler
		taggie-server.db.conn))

(defonce server (atom nil))

(defn -main [& args]
	(println "Starting Taggie server...")
	(set-db!)
	(reset! server (httpkit/run-server #'app {:port 3000})))