;;;
;;; just exercise the basic interface operators

(import com.dydra.ndk.sesame.DydraRepositoryManager)
;(import com.dydra.ndk.Repository)

(def manager (DydraRepositoryManager.))

(def repository (.getRepository manager "marti/test"))
(.initialize repository)

(def connection (.getConnection repository))

(def query (.prepareTupleQuery connection org.openrdf.query.QueryLanguage/SPARQL "SELECT * WHERE {?s ?p ?o}"))

(def results (.evaluate query))

(loop []
  (when (.hasNext results)
    (println (.next results))
    (recur)))

(.close results)