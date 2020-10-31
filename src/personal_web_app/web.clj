(ns personal-web-app.web
  (:require [compojure.core :refer [defroutes]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [hiccup.page :as page]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [personal-web-app.controllers.posts :as posts]
            [personal-web-app.views.layout :as layout]
            [personal-web-app.models.migration :as schema])
            (:gen-class))
  
(defroutes routes
  posts/routes
  (route/resources "/")
  (route/not-found (layout/not-found)))
(def application (wrap-defaults routes site-defaults))
(defn start [port]
 (def server (ring/run-jetty application {:port port :join? false})))

(defn init []
(schema/migrate)
(let [port (Integer. (or (System/getenv "PORT") "8080"))]
  (start port))
) 

