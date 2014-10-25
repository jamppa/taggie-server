(defproject taggie-server "0.1.0-SNAPSHOT"
  :description "Taggie server"
  :url "http://github.com/jamppa/taggie-server"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.9"]
                 [liberator "0.12.0"]
                 [ring-middleware-format "0.4.0"]
                 [com.novemberain/monger "2.0.0"]
                 [com.novemberain/validateur "2.3.1"]
                 [http-kit "2.1.6"]
                 [clj-jwt "0.0.11"]
                 [clj-time "0.8.0"]]
  :plugins [[lein-ring "0.8.12"]]
  :ring {:handler taggie-server.handler/app}
  :main taggie-server.start
  :profiles
  {:dev
    {:dependencies
      [[javax.servlet/servlet-api "2.5"]
        [ring-mock "0.1.5"]
        [midje "1.6.3"]]
    :plugins [[lein-midje "3.1.3"]]}})
