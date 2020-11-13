(ns personal-web-app.web
  (:require [compojure.core :refer [defroutes]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [hiccup.page :as page]
            [ring.util.anti-forgery :as anti-forgery]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [personal-web-app.controllers.routing :as posts]
            [personal-web-app.views.layout :as layout]
            [personal-web-app.models.migration :as schema])
  (:gen-class))

(defroutes crud-routes
  posts/post-routes)
(defroutes routes
  posts/routes
  (route/resources "/")
  (route/not-found (layout/not-found)))
(def application (wrap-defaults routes site-defaults))
(def crud (wrap-defaults crud-routes site-defaults))
(defn server [port]
  (def server (ring/run-jetty application {:port port :join? false})))
(defn crud-server [port]
  (def crud (ring/run-jetty crud {:port port :join? false})))
(defn start-server []
  (schema/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8222"))]
    (server port)))

(defn start-crud []
  (schema/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8223"))]
    (crud-server port)))
(defn -main [& args]
  (if (= (first args) "crud")
    (start-crud) (start-server)))
