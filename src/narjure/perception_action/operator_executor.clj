(ns narjure.perception-action.operator-executor
  (:require
    [co.paralleluniverse.pulsar.actors :refer [self ! whereis cast! Server gen-server register! shutdown! unregister! set-state!]]
    [co.paralleluniverse.pulsar [core :refer [defsfn]]]
    [narjure.actor.utils :refer [defactor]]
    [taoensso.timbre :refer [debug info]])
  (:refer-clojure :exclude [promise await]))

(def aname :operator-executor)

(defn operator-execution-handler
  "Processes an :operator-execution-msg:
    executes operation with optionally supplied parameters
    if feedback msg required posts :sentence-msg to task creator"
  [from [msg operator & params]]
  (debug aname "process-operator-execution-request-msg"))

(defn shutdown-handler
  "Processes :shutdown-msg and shuts down actor"
  [from msg]
  (unregister!)
  (shutdown!))

(defn initialise
  "Initialises actor:
      registers actor and sets actor state"
  [aname actor-ref]
  (register! aname actor-ref)
  (set-state! {:task-creator (whereis :task-creator)}))

(defn msg-handler
  "Identifies message type and selects the correct message handler.
   if there is no match it generates a log message for the unhandled message "
  [from [type :as message]]
  (case type
    :operator-execution-msg (operator-execution-handler from message)
    :shutdown (shutdown-handler from message)
    (debug aname (str "unhandled msg: " type))))

(def operator-executor (gen-server
                       (reify Server
                         (init [_] (initialise aname @self))
                         (terminate [_ cause] #_(info (str aname " terminated.")))
                         (handle-cast [_ from id message] (msg-handler from message)))))
