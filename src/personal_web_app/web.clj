(ns personal-web-app.web
  (:require [compojure.core :refer [defroutes]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [hiccup.page :as page]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]])
            (:gen-class))
  
(defroutes routes
  (GET "/" [] (index)))
(defn index
  []
  (page/html5  [:head
                [:title]]
                 [:body
                  [:div {id "content"} "hello werld"]]))
(def application (wrap-defaults routes site-defaults))

(defn -main []
  (ring/run-jetty application {:port 8080 :join? false}))
