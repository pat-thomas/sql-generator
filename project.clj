(defproject sql-generator "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure       "1.7.0-beta2"]
                 [org.clojure/clojurescript "0.0-3269"]
                 [org.clojure/core.async    "0.1.267.0-0d7780-alpha"]
                 [org.omcljs/om             "0.8.8"]
                 [om-utils                  "0.5.0"]]
  :plugins [[lein-cljsbuild "1.0.6"]
            [lein-figwheel "0.3.1"]]
  :source-paths ["src"]
  :cljsbuild {:builds [{:id           "sql-generator"
                        :source-paths ["src"]
                        :figwheel     {:on-jsload "sql-generator.core/reload-hook"}
                        :compiler     {:asset-path    "js/out"
                                       :output-to     "resources/public/js/sql_generator.js"
                                       :output-dir    "resources/public/js/out"
                                       :optimizations :none
                                       :source-map    true}}]}
  :figwheel {:http-server-root "public"
             :css-dirs         ["resources/public/css"]})
