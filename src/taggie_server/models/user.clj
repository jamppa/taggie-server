(ns taggie-server.models.user
    (:require
        [taggie-server.db.conn :as db]
        [monger.collection :as monger]
        [monger.json :refer :all]
        [taggie-server.db.util :as db-util]
        [validateur.validation :refer :all])
    (:import [org.bson.types ObjectId]))

(def user-collection "users")

(def user-validation-set
    (validation-set
        (presence-of :email)
        (presence-of :password)))

(defn is-valid [user]
    (valid? user-validation-set user))

(defn find-by-id [id-as-str]
    (monger/find-map-by-id (db/get-db) user-collection (ObjectId. id-as-str)))

(defn find-by-email-and-pwd [email pwd]
    (monger/find-one-as-map (db/get-db) user-collection {:email email :password pwd}))

(defn save [user]
    (monger/insert-and-return (db/get-db) user-collection (db-util/with-oid user)))