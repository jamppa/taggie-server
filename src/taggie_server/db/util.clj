(ns taggie-server.db.util
	(:import [org.bson.types ObjectId]))

(defn with-oid [obj]
	(merge obj {:_id (ObjectId.)}))