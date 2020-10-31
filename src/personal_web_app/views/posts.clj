(ns personal-web-app.views.posts
  (:require [personal-web-app.views.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]
            [ring.util.anti-forgery :as anti-forgery]))

(defn post-form []
  [:div {:id "post-form" :class "sixteen columns alpha omega"}
   (form/form-to [:post "/"]
                 (anti-forgery/anti-forgery-field)
                 (form/label "post" "What do you want to POST?")
                 (form/text-area "post")
                 (form/submit-button "POST!"))])

(defn display-posts [posts]
  [:div {:class "posts sixteen columns alpha omega"}
   (map
    (fn [post] [:h2 {:class "post"} (h (:body post))])
    posts)])
(defn display-index []
  [:div {:class ""}
   (map(fn [post] [:h2 {:class "index"} (h (:body post))]))])

(defn index []
  (layout/common "POSTS"
                ;; (post-form)
                 [:div {:class "clear"}]
                 (display-index)))

(defn blog [posts]
  (layout/common "POSTS"
                ;; (post-form)
                 [:div {:class "clear"}]
                 (display-posts posts)))
