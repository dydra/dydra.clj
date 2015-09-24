(defproject dydra "0.1.0-SNAPSHOT"
  :description "Clojure binding for the Dydra Java NDK"
  :url "http://dydra.com"
  :license {:name "Unlicense"
            :url "http://unlicense.org"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.dydra/dydra-ndk "0.0.1"]
                 ;; pick
                 [org.openrdf.sesame "2.7.3"]
                 [org.slf4j/slf4j-api "1.7.12"]
                 [org.slf4j/slf4j-log4j12 "1.7.12"]
                ]
  ;; locate the dydra java ndk in a local repository
  :repositories [;; ["dydrarepo" "file:///development/lib"]
                 ["dydrarepo" "file:///home/james/lib"]
                 ]
  :repl-options {:init-ns dydra.core}
  :min-lein-version "2.5.1"
  :main dydra.core
  :aot :all
  )
