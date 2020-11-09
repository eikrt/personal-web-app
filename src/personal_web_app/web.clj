(ns personal-web-app.web
  (:require [compojure.core :refer [defroutes]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [hiccup.page :as page]
            [buddy.hashers :as hashers]
            [ring.util.anti-forgery :as anti-forgery]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [personal-web-app.controllers.posts :as posts]
            [personal-web-app.views.layout :as layout]
            [personal-web-app.models.migration :as schema]
            [buddy.auth.backends.session :refer [session-backend]]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.params :refer [wrap-params]])
            (:gen-class))
  

(defn get-user [user-id]
  (get @posts/userstore user-id))

(defn wrap-user [handler]
  (fn [{user-id :identity :as req}]
    (handler (assoc req :user (get-user user-id)))))




(defroutes routes
  posts/routes
  (route/resources "/")
  (route/not-found (layout/not-found)))
(def backend (session-backend))
(def application (-> #'routes
                     ;(wrap-defaults api-defaults)
                     (wrap-user)
                     (wrap-authentication backend)
                     (wrap-authorization backend)
                     (wrap-session)
                     (wrap-params)
                     ))
(defn start [port]
  
  
 (def server (ring/run-jetty application {:port port :join? false})))

(defn init []
(schema/migrate)
(let [port (Integer. (or (System/getenv "PORT") "8080"))]
  (start port))
) 

