(ns personal-web-app.controllers.posts
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [personal-web-app.views.posts :as view]
            [personal-web-app.models.post :as model]))


(defn index []
  (view/index))


(defn computer []
 (view/computer))
(defn cooking []
 (view/cooking))
(defn other []
 (view/other))
(defn about []
 (view/about))
(defn create
  [post]
  (when-not (str/blank? post)
    (model/create post))
  (ring/redirect "/"))

(defroutes routes
  (GET "/" [] (index))
  (GET "/computer" [] (computer))
  (GET "/cooking" [] (cooking))
  (GET "/other" [] (other))
  (GET "/about" [] (about))
  (POST "/" [post] (create post)))
