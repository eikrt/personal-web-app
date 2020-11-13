(ns jukebox.controllers.routing
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [jukebox.views.display :as view]
            [jukebox.models.query :as model]
            [ring.util.response :refer [response redirect]]))

(defn index []
  (view/index ))

(defroutes routes
  (GET "/" [] (index)))
