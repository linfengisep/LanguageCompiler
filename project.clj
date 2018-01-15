(defproject mylanguages "0.1.0-SNAPSHOT"
  :description "My first computer language"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
		 [instaparse "1.4.8"]
		]
  :main ^:skip-aot mylanguages.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
