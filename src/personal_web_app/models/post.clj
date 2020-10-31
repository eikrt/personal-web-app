(ns personal-web-app.models.post
  (:require [clojure.java.jdbc :as sql]))

(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/posts?user=postgres&password=postgres"))

(defn all []
  (into [] (sql/query spec ["select * from posts order by id desc"])))

(defn create [post]
  (sql/insert! spec :posts [:body] [post]))
