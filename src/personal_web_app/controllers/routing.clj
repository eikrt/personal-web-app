(ns personal-web-app.controllers.routing
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [personal-web-app.views.display :as view]
            [personal-web-app.models.query :as model]
            [ring.util.response :refer [response redirect]]))
(defn index []
  (view/index (model/all)))
(defn post-route []
  (view/post (model/all)))
(defn computer []
  (view/computer [(model/computer)]))
(defn cooking []
  (view/cooking [(model/cooking)]))
(defn other []
  (view/other [(model/other)]))
(defn about []
  (view/about))
(defn create
  [post]
  (when-not (str/blank? (first post))
    (model/create post))
  (ring/redirect "/"))
(defroutes post-routes
  (GET "/post" [] (post-route))
  (POST "/post" [post] (create post)))
(defroutes routes
  (GET "/" [] (index))
  (GET "/computer" [] (computer))
  (GET "/cooking" [] (cooking))
  (GET "/other" [] (other))
  (GET "/about" [] (about)))
