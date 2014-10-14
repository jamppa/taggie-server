(ns taggie-server.models.user
    (:require
        [taggie-server.db.conn :as db]
        [monger.collection :as monger]
        [monger.json :refer :all]
        [taggie-server.db.util :as db-util])
    (:import [org.bson.types ObjectId]))

(def user-collection "users")

(defn find-by-id [id-as-str]
    (monger/find-map-by-id (db/get-db) user-collection (ObjectId. id-as-str)))

(defn find-by-email-and-pwd [email pwd]
    (monger/find-one-as-map (db/get-db) user-collection {:email email :password pwd}))

(defn save [user]
    (monger/insert-and-return (db/get-db) user-collection (db-util/with-oid user)))