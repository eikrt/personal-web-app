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
  [:div {:class "index"}
   [:p {:class "index"} "Welcome to my website! I host here some of my personal projects, texts and other things!"]])
(defn display-computer []
  [:div {:class "index"}
   [:p {:class "index"} "Latest posts on computers: "]])
(defn display-cooking []
  [:div {:class "index"}
   [:p {:class "index"} "Latest posts on cooking: "]])

(defn display-other []
  [:div {:class "index"}
   [:p {:class "index"} "Miscellineous posts come here :-)"]])

(defn display-about []
  [:div {:class "index"}
   [:p {:class "index"} "I study data and information technology in the University of Turku. I'm interested in many things related to computers, movies and music!"] [:p {class "index"} "e-mail: e.i.korte@gmail.com"] [:p {class "index"} "source code of this page:"] [:a {:class "index" :href "https://github.com/eikrt/personal-web-app"} "https://github.com/eikrt/personal-web-app"]])

(defn index []
  (layout/common "eikrt website"
                ;; (post-form)
                 [:div {:class "clear"}]
                 (display-index)))

(defn computer []
  (layout/common "computer"
                ;; (post-form)
                 [:div {:class "clear"}]
                 (display-computer )))
(defn cooking []
  (layout/common "cooking"
                ;; (post-form)
                 [:div {:class "clear"}]
                 (display-cooking )))
(defn other []
  (layout/common "other"
                ;; (post-form)
                 [:div {:class "clear"}]
                 (display-other )))
(defn about []
  (layout/common "blog"
                ;; (post-form)
                 [:div {:class "clear"}]
                 (display-about )))
