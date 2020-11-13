(ns jukebox.display
  (:require [jukebox.views.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]
            [ring.util.anti-forgery :as anti-forgery]
            [clojure.string :as str]))


(defn display-index []
  [:div {:class "index"}
   [:p {:class "index"} "Welcome to Jukebox"]

[:audio
  {:src "../resources/audio/Track1.wav", :controls "controls"}
  "\n            Your browser does not support the\n            "
  [:code "audio"]
  " element.\n    \n"]

   ])
(defn index []
  (layout/common "Jukebox"
                 ;; (post-form)
                 [:div {:class "clear"}]
                 (display-index)))
