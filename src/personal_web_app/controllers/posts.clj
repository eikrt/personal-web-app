(ns personal-web-app.controllers.posts
  (:require [compojure.core :refer [defroutes context GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [personal-web-app.views.posts :as view]
            [personal-web-app.models.post :as model]
            [buddy.hashers :as hashers]
            [buddy.auth.accessrules :refer [restrict]]
            [ring.util.response :refer [response redirect]])
  )
(defn uuid [] (java.util.UUID/randomUUID))
(def userstore (atom {}))
(defn is-authenticated [{user :user :as req}]
  (not (nil? user)))



(defn post-login [{{username "username" password "password"} :form-params
                   session :session :as req}]
  (pr username)
  (if-let [user (get-user-by-username-and-password username password)]

    ; If authenticated
    (assoc (ring/redirect "/")
           :session (assoc session :identity (:id user)))

    ; Otherwise
    (ring/redirect "/login")))

(defn post-logout [{session :session}]
  (assoc (redirect "/computer")
         :session (dissoc session :identity)))




(defn get-user-by-username-and-password [username password]
  (prn username password)
  (reduce (fn [_ user]
            (if (and (= (:username user) username)
                     (hashers/check password (:password-hash user)))
              (reduced user))) (vals @userstore)))


(defn create-user! [user]
  (let [password (:password user)
        user-id (uuid)]
    (-> user
        (assoc :id user-id :password-hash (hashers/encrypt password))
        (dissoc :password)
        (->> (swap! userstore assoc user-id)))))

(defn index []
  (view/index (model/all)))

(defn login []
  (view/login (model/all)))
(defn post-route []
(view/post (model/all)))

(defn computer []
 (view/computer [(model/computer)] ))
(defn cooking []
 (view/cooking [(model/cooking)]))
(defn other []
 (view/other [(model/other)]))
(defn about []
 (view/about))
(defn create
  [post]
  (when-not (str/blank? (first post))
    (model/create post))
  (ring/redirect "/"))

(defroutes post-routes
  (GET "/" [] (post-route))
  (POST "/" [post] (create post)))

(defroutes routes
  
  (create-user! {:username "admin" :password "1234"})
  (GET "/" [] (index))
  
  ;;(POST "/post" [post] (create post))
  (GET "/computer" [] (computer))
  (GET "/cooking" [] (cooking))
  (GET "/other" [] (other))
  (GET "/about" [] (about))
  (GET "/login" [] (login))
  (POST "/login" [post] (post-login "username=admin&password=1234"))
  
  (POST "/logout"  [] post-logout)
  (context "/post" []
  (restrict post-routes {:handler is-authenticated}))
  )
