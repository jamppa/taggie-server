(ns taggie-server.config)

(def conf {
	:db {
		:name "taggie"
		:name-test "taggie-test"
	}
})

(defn config [& keys]
	(get-in conf keys))