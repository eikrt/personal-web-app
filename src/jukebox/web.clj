(ns jukebox.web
  (:require [compojure.core :refer [defroutes]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [hiccup.page :as page]
            [ring.util.anti-forgery :as anti-forgery]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [jukebox.controllers.routing :as posts]
            [jukebox.views.layout :as layout]
            [jukebox.models.migration :as schema])
  (:gen-class))

(defroutes routes
  posts/routes
  (route/resources "/")
  (route/not-found (layout/not-found)))
(def application (wrap-defaults routes site-defaults))
(defn server [port]
  (def server (ring/run-jetty application {:port port :join? false})))
(defn start-server []
  ;(schema/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8222"))]
    (server port)))

<<<<<<< HEAD:src/jukebox/web.clj
(defn -main [& args]
     (start-server))
=======
(defn start-crud []
  (schema/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8223"))]
    (crud-server port)))
(defn -main [target]
  (if (= target "crud")
    (start-crud) (start-server)))
>>>>>>> parent of 38fef46... added main function to take variadic args instead of fixed one:src/personal_web_app/web.clj
