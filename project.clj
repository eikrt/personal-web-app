(defproject personal-web-app "0.1.0-SNAPSHOT"
  :description "Personal website"
  :url "https://github.com/eikrt/personal-web-app"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [ring "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [ring/ring-defaults "0.1.2"]
                 [compojure "1.4.0"]
                 [hiccup "1.0.5"]
                 ]
  :repl-options {:init-ns personal-web-app.web}
:main ^:skip-aot personal-web-app.web
:uberjar-name "app.jar"
:ring {:handler personal-web-app/server
       :init personal-web-app/migration/migrate}
:profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                [ring-mock "0.1.5"]]}
           :uberjar {:aot :all}}
)
