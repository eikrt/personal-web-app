(ns personal-web-app.controllers.posts
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [personal-web-app.views.posts :as view]
            [personal-web-app.models.post :as model]))


(defn index []
  (view/index (model/all)))
(defn post-route []
(view/post (model/all)))

(defn computer []
 (view/computer [(model/computer) "pumpeti" "imuri"] ))
(defn cooking []
 (view/cooking (model/cooking)))
(defn other []
 (view/other (model/other)))
(defn about []
 (view/about))
(defn create
  [post]
  (when-not (str/blank? (first post))
    (model/create post))
  (ring/redirect "/"))

(defroutes routes
  (GET "/" [] (index))
  (POST "/post" [post] (create post))
  (GET "/computer" [] (computer))
  (GET "/cooking" [] (cooking))
  (GET "/other" [] (other))
  (GET "/about" [] (about))
  (GET "/post" [] (post-route))
  )
