(defproject dydra "0.1.0-SNAPSHOT"
  :description "Clojure binding for the Dydra Java NDK"
  :url "http://dydra.com"
  :license {:name "Unlicense"
            :url "http://unlicense.org"}
  ;; locate the dydra java ndk in a local repository
  :repositories [;; ["dydrarepo" "file:///development/lib"]
                 ["dydrarepo" "file:///home/arto/lib"]
                 ]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.slf4j/slf4j-jdk14 "1.7.5"]
                 ;; pick
                 [org.openrdf.sesame "2.7.3"]
                 [com.dydra/dydra-ndk "0.0.1"]
                ]
  :repl-options {:init-ns dydra.core}
  :min-lein-version "2.5.1"
  :main dydra.core
  :aot :all
  )
