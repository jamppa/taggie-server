(ns taggie-server.routes.util)

(defn payload-in-ctx [ctx]
    (get-in ctx [:request :params]))

(defn user-in-ctx [ctx]
    (get-in ctx [:user]))