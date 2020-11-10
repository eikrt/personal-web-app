(ns personal-web-app.views.layout
  (:require [hiccup.page :as h]))

(defn common [title & body]
  (h/html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
    [:meta {:name "viewport" :content
            "width=device-width, initial-scale=1, maximum-scale=1"}]
    [:title title]
    (h/include-css "/stylesheets/style.css")]
   [:body
    [:div {:id "header"}
     [:div {:id "header_box"}
      [:h1 {:class "container"} [:a {:href "/" :id "stamp"} "eikrt blog: "] [:a {:href "/computer"} "computer stuff"] [:a {:href "/cooking"} "cooking"] [:a {:href "/other"} "other"] [:a {:href "/about"} "about me"]]]]

    [:div {:id "content" :class "container"} body]]))

(defn not-found []
  (common "Page Not Found"
          [:div {:id "not found"}
           "The page you requested could not be found"]))
