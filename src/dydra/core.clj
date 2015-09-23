(ns dydra.core
    (:gen-class)
    (import
     org.openrdf.model.URI
     org.openrdf.model.Resource
     org.openrdf.model.Statement

     org.openrdf.model.impl.StatementImpl
     org.openrdf.model.impl.URIImpl

     org.openrdf.repository.Repository
     org.openrdf.repository.manager.RepositoryManager
     org.openrdf.repository.http.HTTPRepository
     org.openrdf.repository.RepositoryConnection

     org.openrdf.repository.sail.SailRepository;
     org.openrdf.sail.memory.MemoryStore;

     org.openrdf.query.resultio.TupleQueryResultFormat

     com.dydra.ndk.sesame.DydraRepositoryManager
     com.dydra.ndk.sesame.DydraRepository)
    )

(def repository-manager (ref nil))
(def repositories (ref {}))

(defn ensure-repository-manager []
  (or @repository-manager
      (let [manager (DydraRepositoryManager. )]
        (dosync (alter repository-manager (fn [old new] new) manager))
        manager)))

(defn ensure-repository [name]
  (or (get @repositories name nil)
      (let [repository (.getRepository (ensure-repository-manager) name)]
         (.initialize repository)
	 (dosync (alter repositories assoc name repository))
	 repository)))

(defn run-sparql
  "Execute a SPARQL query and return the result solution field."
  [repo query]
  (let [connection (.getConnection (ensure-repository repo))
        prepared-query (.prepareTupleQuery connection org.openrdf.query.QueryLanguage/SPARQL query)]
    (.evaluate prepared-query)))

;;; (run-sparql "marti/test" "select (count(*) as ?count) where {?s ?p ?o}")
;;; the equivalent, as individual steps:
;;;
;;; (def repository-manager (DydraRepositoryManager. ))
;;; (def repository (.getRepository repository-manager "marti/test"))
;;; (.initialize repository)
;;; (.evaluate (.prepareTupleQuery (.getConnection repository) org.openrdf.query.QueryLanguage/SPARQL  "select (count(*) as ?count) where {?s ?p ?o}"))
