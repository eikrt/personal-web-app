(ns personal-web-app.models.post
  (:require [clojure.java.jdbc :as sql]))

(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/posts?user=postgres&password=postgres"))


(defn all []
  
  (into [] (sql/query spec ["select * from posts order by id desc"])))

(defn computer []
  (into [] (sql/query spec ["select * from posts where category = 'computer' order by id desc"])))
(defn cooking []

  (into [] (sql/query spec ["select * from posts where category = 'cooking' order by id desc"])))
(defn other []
  
  (into [] (sql/query spec ["select * from posts where category = 'other' order by id desc"])))
(defn title []
  
  (into [] (sql/query spec ["select * from posts where category = 'other' order by id desc"])))
  )
(defn date []

  (into [] (sql/query spec ["select * from posts where category = 'other' order by id desc"])))
)
(defn create [post]
  (sql/insert-multi! spec :posts [{:body (first post) :category (second post) :title (nth post 2)}]) )
