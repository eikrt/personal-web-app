(ns personal-web-app.views.posts
  (:require [personal-web-app.views.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]
            
            [ring.util.anti-forgery :as anti-forgery]
            [clojure.string :as str]))

(defn post-form []
  [:div {:id "post-form" :class "sixteen columns alpha omega"}
   (form/form-to [:post "/post"]
                 (anti-forgery/anti-forgery-field)
                 (form/label "post" "Post here.")
                 (form/text-area "post")
                 
                 (form/drop-down "post" ["computer" "cooking" "other"])

                 (form/text-field "post")
                 (form/submit-button "submit"))])
  

(defn display-post []
  [:div {:class "post"}
   [:p {:class "post"} "Post here"]])
  
(defn display-posts [posts]
  [:div {:class "posts sixteen columns alpha omega"}
   (map
    (fn [post] [:p {:class "post"} (h (:body post))])
    posts)])

(defn display-login []
  [:div {:id "login-form" }
  (form/form-to [:post "/login"]
   (anti-forgery/anti-forgery-field)
   (form/text-field "post")
   (form/password-field "post")
   (form/submit-button "submit"))
  ])
  ;[:div {:class "index"}
   ;[:li [:a {:href "/"} "Home"]]
   ; [:li [:a {:href "/login/"} "Login"]]
   ; [:li [:a {:href "/admin/"} "Admin"]]]
   ;[:form
   ; {:method "post", :action "/login/"}
   ; [:label {:for "username"} "Username:"]
   ; [:input {:name "username", :type "text"}]
   ; [:label {:for "password"} "Password:"]
   ; [:input {:name "password", :type "password"}]
   ; [:input {:value "Sign In", :type "submit"}]])
(defn display-index []
  [:div {:class "index"}
   [:p {:class "index"} "Welcome to my website! I host here some of my personal projects, texts and other things!"]])
(defn display-computer [posts]
  [:div {:class "display-computer"}
   [:h2 {:class "intro"} "Latest posts on computers: "]]

  [:div {:class "computer-posts"}
   (map
    (fn [post] [:p {:class "post"}
                [:h1 (h(:title post)) "    "][:p (h (first (str/split (str (:created_at post)) #"\.")))]
                [:p "-------------------------------------------------------------------------"]
                [:p (h (:body post))]
                [:p "-------------------------------------------------------------------------"]]
      )
    (first posts))])

(defn display-cooking [posts]
  [:div {:class "display-computer"}
   [:h2 {:class "intro"} "Latest posts on cooking: "]]

  [:div {:class "cooking-posts"}
   (map
    (fn [post] [:p {:class "post"}
                [:h1 (h(:title post)) "    "][:p (h (first (str/split (str (:created_at post)) #"\.")))]
                [:p "-------------------------------------------------------------------------"]
                [:p (h (:body post))]
                [:p "-------------------------------------------------------------------------"]]
      )
    (first posts))])
(defn display-other [posts]
  [:div {:class "display-computer"}
   [:h2 {:class "intro"} "Latest posts on other things: "]]

  [:div {:class "other-posts"}
   (map
    (fn [post] [:p {:class "post"}
                [:h1 (h(:title post)) "    "][:p (h (first (str/split (str (:created_at post)) #"\.")))]
                [:p "-------------------------------------------------------------------------"]
                [:p (h (:body post))]
                [:p "-------------------------------------------------------------------------"]]
      )
    (first posts))])

(defn display-about []
  [:div {:class "index"}
   [:p {:class "index"} "I study information and communication technology in the University of Turku. I'm interested in many things related to computers, movies and music!"] [:p {class "index"} "e-mail: e.i.korte@gmail.com"] [:p {class "index"} "source code of this page:"] [:a {:class "index" :href "https://github.com/eikrt/personal-web-app"} "https://github.com/eikrt/personal-web-app"]])


(defn index [posts]
  (layout/common "posts"
                 ;; (post-form)
                 [:div {:class "clear"}]
                 (display-index)))

(defn login [posts]
  (layout/common "posts"
                 ;; (post-form)
                 [:div {:class "clear"}]
                 (display-login)))

(defn computer [posts]
  (layout/common "computer"
                ;; (post-form)
                 [:div {:class "clear"}]
                 
                 [:h1 "Latest posts on computers: "]
                 (display-computer posts)))
(defn cooking [posts]
  (layout/common "cooking"
                ;; (post-form)
                 [:div {:class "clear"}]
                 
                 [:h1 "Latest posts on cooking: "]
                 (display-cooking posts)))
(defn other [posts]
  (layout/common "other"
                ;; (post-form)
                 [:div {:class "clear"}]
                 
                 [:h1 "Latest posts on other things: "]
                 (display-other posts)))
(defn about []
  (layout/common "blog"
                ;; (post-form)
                 [:div {:class "clear"}]
                 (display-about )))
(defn post [posts]
  (layout/common "post"
                 (post-form)
                 [:div {:class "clear"}]
                 (display-posts posts)))
